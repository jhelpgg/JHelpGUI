package jhelp.gui.game;

import java.applet.Applet;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jhelp.security.io.JHelpBase;
import jhelp.util.MemorySweeper;
import jhelp.util.debug.Debug;
import jhelp.util.debug.DebugLevel;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpSprite;
import jhelp.util.list.EnumerationIterator;
import jhelp.util.list.Pair;
import jhelp.util.text.StringCutter;
import jhelp.util.text.UtilText;
import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedVerySimpleTask;
import netscape.javascript.JSObject;

/**
 * Generic Applet for 2D animation
 * 
 * @author JHelp
 */
public abstract class JHelpApplet
      extends Applet
{
   /** Pause text */
   private static final String                        PAUSE                     = "PAUSE";
   /** serialVersionUID */
   private static final long                          serialVersionUID          = -7614961825604057497L;
   /** Font to use for write pause text */
   public static final Font                           FONT_PAUSE                = new Font("Courrier", Font.BOLD, 32);
   /** Semi transparent grey color */
   public static final Color                          SEMI_TRANSPARENT_GREY     = new Color(128, 128, 128, 128);
   /** Actual linked cookie content */
   private HashMap<String, String>                    cookieContent;
   /** Access to HTML document */
   private JSObject                                   embedDocument;
   /** Frame linked to applet */
   private Frame                                      frame;
   /** Java script manipulation */
   private JSObject                                   javaScriptAccess;
   /** Task for animate applet */
   final ThreadedVerySimpleTask                       animationLoopTask         = new ThreadedVerySimpleTask()
                                                                                {

                                                                                   /**
                                                                                    * Call when animation is cancel <br>
                                                                                    * <br>
                                                                                    * <b>Parent documentation:</b><br>
                                                                                    * {@inheritDoc}
                                                                                    * 
                                                                                    * @see jhelp.util.thread.ThreadedTask#canceled()
                                                                                    */
                                                                                   @Override
                                                                                   protected void canceled()
                                                                                   {
                                                                                      final Dimension size = JHelpApplet.this.getSize();

                                                                                      JHelpApplet.this.image = new JHelpImage(size.width, size.height, 0xFFFFFFFF);
                                                                                      JHelpApplet.this.image.register(JHelpApplet.this);

                                                                                      ThreadManager.THREAD_MANAGER.delayedThread(JHelpApplet.this.initializeJHelpAppletTask, null, 1024);
                                                                                   }

                                                                                   /**
                                                                                    * Animate one loop <br>
                                                                                    * <br>
                                                                                    * <b>Parent documentation:</b><br>
                                                                                    * {@inheritDoc}
                                                                                    * 
                                                                                    * @see jhelp.util.thread.ThreadedVerySimpleTask#doVerySimpleAction()
                                                                                    */
                                                                                   @Override
                                                                                   protected void doVerySimpleAction()
                                                                                   {
                                                                                      if(JHelpApplet.this.pause == false)
                                                                                      {
                                                                                         Pair<JHelpAnimation, JHelpSprite> animation;

                                                                                         final int length = JHelpApplet.this.animations.size();

                                                                                         for(int i = length - 1; i >= 0; i--)
                                                                                         {
                                                                                            animation = JHelpApplet.this.animations.get(i);

                                                                                            if(animation.element1.animate(animation.element2) == false)
                                                                                            {
                                                                                               JHelpApplet.this.animations.remove(i);
                                                                                            }
                                                                                         }

                                                                                         JHelpApplet.this.animationLoop(JHelpApplet.this.image, Collections.unmodifiableMap(JHelpApplet.this.keyState), JHelpApplet.this.mouseX,
                                                                                               JHelpApplet.this.mouseY, JHelpApplet.this.buttonLeft, JHelpApplet.this.buttonMidle, JHelpApplet.this.buttonRight);

                                                                                         JHelpApplet.this.image.update();
                                                                                      }
                                                                                      else
                                                                                      {
                                                                                         for(final Pair<JHelpAnimation, JHelpSprite> animation : JHelpApplet.this.animations)
                                                                                         {
                                                                                            animation.element1.pauseMode();
                                                                                         }

                                                                                         JHelpApplet.this.repaint();

                                                                                         synchronized(JHelpApplet.this.LOCK)
                                                                                         {
                                                                                            try
                                                                                            {
                                                                                               JHelpApplet.this.isLocked = true;

                                                                                               JHelpApplet.this.LOCK.wait(16384);
                                                                                            }
                                                                                            catch(final Exception exception)
                                                                                            {
                                                                                            }

                                                                                            JHelpApplet.this.isLocked = false;
                                                                                         }

                                                                                         for(final Pair<JHelpAnimation, JHelpSprite> animation : JHelpApplet.this.animations)
                                                                                         {
                                                                                            animation.element1.pauseMode();
                                                                                         }

                                                                                         JHelpApplet.this.repaint();
                                                                                      }
                                                                                   }
                                                                                };
   /** Animation list */
   final ArrayList<Pair<JHelpAnimation, JHelpSprite>> animations                = new ArrayList<Pair<JHelpAnimation, JHelpSprite>>();
   /** Current mouse button left state */
   boolean                                            buttonLeft;
   /** Current mouse button middle state */
   boolean                                            buttonMidle;
   /** Current mouse button right state */
   boolean                                            buttonRight;
   /** Image where draw applet */
   JHelpImage                                         image;
   /** Task for initialize the applet */
   final ThreadedVerySimpleTask                       initializeJHelpAppletTask = new ThreadedVerySimpleTask()
                                                                                {
                                                                                   /**
                                                                                    * Initialize the applet <br>
                                                                                    * <br>
                                                                                    * <b>Parent documentation:</b><br>
                                                                                    * {@inheritDoc}
                                                                                    * 
                                                                                    * @see jhelp.util.thread.ThreadedVerySimpleTask#doVerySimpleAction()
                                                                                    */
                                                                                   @Override
                                                                                   protected void doVerySimpleAction()
                                                                                   {
                                                                                      JHelpApplet.this.image.startDrawMode();

                                                                                      JHelpApplet.this.initializeJHelpApplet(JHelpApplet.this.image);

                                                                                      JHelpApplet.this.threadID = ThreadManager.THREAD_MANAGER.repeatThread(JHelpApplet.this.animationLoopTask, null, 1024, 32);
                                                                                   }
                                                                                };
   /** Lock state */
   boolean                                            isLocked                  = false;
   /** Actual action key state */
   HashMap<ActionKey, Boolean>                        keyState;
   /** Lock for synchronization */
   final Object                                       LOCK                      = new Object();
   /** Current mouse X position */
   int                                                mouseX;
   /** Current mouse Y position */
   int                                                mouseY;
   /** Indicates if where are in pause */
   boolean                                            pause                     = true;
   /** Animation pause thread ID */
   int                                                threadID                  = -1;

   /**
    * Create a new instance of JHelpApplet
    */
   public JHelpApplet()
   {
   }

   /**
    * Compute String with the actual cookie content
    * 
    * @return Created String
    */
   private String createContentString()
   {
      final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

      BufferedWriter bufferedWriter = null;

      try
      {
         bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));

         for(final String key : this.cookieContent.keySet())
         {
            bufferedWriter.append(key);
            bufferedWriter.append("_");
            bufferedWriter.append(this.cookieContent.get(key));
            bufferedWriter.append("|");
         }
      }
      catch(final Exception exception)
      {
         Debug.printException(exception, "Issue while creating the cookie String");
      }
      finally
      {
         if(bufferedWriter != null)
         {
            try
            {
               bufferedWriter.flush();
            }
            catch(final IOException exception)
            {
            }

            try
            {
               bufferedWriter.close();
            }
            catch(final IOException exception)
            {
            }
         }
      }

      return new String(byteArrayOutputStream.toByteArray(), UtilText.UTF8);
   }

   /**
    * Extract cookie value
    */
   private void extractCookie()
   {
      this.cookieContent = new HashMap<String, String>();

      final String cookie = (String) this.embedDocument.getMember("cookie");

      Debug.println(DebugLevel.VERBOSE, "Actual cookie content : ", cookie);

      if(cookie != null)
      {
         final StringCutter stringCutter = new StringCutter(cookie, ';');
         int indexEqual;

         String information = stringCutter.next();

         if(information != null)
         {
            information = information.trim();

            indexEqual = information.indexOf('=');

            if(indexEqual > 0)
            {
               this.parseContentString(information.substring(indexEqual + 1).trim());
            }
         }
      }
   }

   /**
    * Parse cookie value to fill the cookie content
    * 
    * @param toParse
    *           Cookie value to parse
    */
   private void parseContentString(final String toParse)
   {
      Debug.println(DebugLevel.VERBOSE, toParse);

      BufferedReader bufferedReader = null;

      try
      {
         bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(toParse.getBytes(UtilText.UTF8))));

         String line = bufferedReader.readLine();
         StringCutter stringCutter;
         String equality;
         int index;

         while(line != null)
         {
            Debug.println(DebugLevel.VERBOSE, "line=", line);

            stringCutter = new StringCutter(line, '|');
            equality = stringCutter.next();

            while(equality != null)
            {
               Debug.println(DebugLevel.VERBOSE, "equality=", equality);
               index = equality.indexOf('_');
               Debug.println(DebugLevel.VERBOSE, "index=", index);

               if(index >= 0)
               {
                  this.cookieContent.put(equality.substring(0, index), equality.substring(index + 1));
               }

               equality = stringCutter.next();
            }

            Debug.println(DebugLevel.VERBOSE, "cookieContent=", this.cookieContent);

            line = bufferedReader.readLine();
         }
      }
      catch(final Exception exception)
      {
         Debug.printException(exception, "Issue while parsing the cookie String : ", toParse);
      }
      finally
      {
         if(bufferedReader != null)
         {
            try
            {
               bufferedReader.close();
            }
            catch(final IOException exception)
            {
            }
         }
      }
   }

   /**
    * Update the cookie value
    */
   private void updateCookie()
   {
      final StringBuilder stringBuilder = new StringBuilder();

      stringBuilder.append("VALUE");
      stringBuilder.append('=');
      stringBuilder.append(this.createContentString());

      stringBuilder.append("; ");

      final Calendar calendar = Calendar.getInstance();
      calendar.add(java.util.Calendar.MONTH, 1);

      stringBuilder.append("expires=");
      stringBuilder.append(calendar.getTime().toString());

      Debug.println(DebugLevel.VERBOSE, "New cookie content : ", stringBuilder.toString());

      this.embedDocument.setMember("cookie", stringBuilder.toString());

      Debug.println(DebugLevel.VERBOSE, "AFTER : ", this.embedDocument.getMember("cookie"));
   }

   /**
    * Update mouse status
    * 
    * @param mouseEvent
    *           Mouse event description
    */
   private void updateMouse(final MouseEvent mouseEvent)
   {
      this.mouseX = mouseEvent.getX();
      this.mouseY = mouseEvent.getY();

      final int modifiers = mouseEvent.getModifiersEx();

      this.buttonLeft = (modifiers & InputEvent.BUTTON1_DOWN_MASK) == InputEvent.BUTTON1_DOWN_MASK;
      this.buttonMidle = (modifiers & InputEvent.BUTTON2_DOWN_MASK) == InputEvent.BUTTON2_DOWN_MASK;
      this.buttonRight = (modifiers & InputEvent.BUTTON3_DOWN_MASK) == InputEvent.BUTTON3_DOWN_MASK;
   }

   /**
    * Call at each loop, to animate the applet
    * 
    * @param image
    *           Parent image where draw
    * @param actionKeyState
    *           Current action kety state
    * @param mouseX
    *           Mouse X position
    * @param mouseY
    *           Mouse Y position
    * @param buttonLeftDown
    *           Mouse button left state
    * @param buttonMiddleDow
    *           Mouse button middle state
    * @param buttonRightDown
    *           Mouse button right state
    */
   protected abstract void animationLoop(final JHelpImage image, final Map<ActionKey, Boolean> actionKeyState, final int mouseX, final int mouseY, final boolean buttonLeftDown, final boolean buttonMiddleDow,
         final boolean buttonRightDown);

   /**
    * Create a visible sprite for the applet
    * 
    * @param x
    *           Start X position
    * @param y
    *           Start Y position
    * @param witdh
    *           Sprite width
    * @param height
    *           Sprite height
    * @return Created sprite
    */
   protected final JHelpSprite createSprite(final int x, final int y, final int witdh, final int height)
   {
      boolean draw = false;

      if(this.image.isDrawMode() == true)
      {
         draw = true;
         this.image.endDrawMode();
      }

      final JHelpSprite sprite = this.image.createSprite(x, y, witdh, height);

      sprite.getImage().startDrawMode();
      sprite.getImage().clear(0x80808080);

      sprite.setVisible(true);

      if(draw == true)
      {
         this.image.startDrawMode();
      }

      return sprite;
   }

   /**
    * Call when applet initialize, use to create sprites, animations, ... will be manipule on
    * {@link #animationLoop(JHelpImage, Map, int, int, boolean, boolean, boolean)}
    * 
    * @param image
    *           Image parent where draw
    */
   protected abstract void initializeJHelpApplet(JHelpImage image);

   /**
    * Draw something over the screen while applet is on pause.<br>
    * You can override this method to do something different than default pause screen
    * 
    * @param g
    *           Graphics to use to draw the pause screen
    */
   protected void pauseScreen(final Graphics g)
   {
      g.setColor(JHelpApplet.SEMI_TRANSPARENT_GREY);
      g.fillRect(0, 0, this.getWidth(), this.getHeight());

      g.setColor(Color.BLACK);
      g.setFont(JHelpApplet.FONT_PAUSE);
      final FontMetrics fontMetrics = g.getFontMetrics(JHelpApplet.FONT_PAUSE);

      final int w = fontMetrics.stringWidth(JHelpApplet.PAUSE);
      final int h = fontMetrics.getHeight();

      g.drawString(JHelpApplet.PAUSE, (this.getWidth() - w) >> 1, fontMetrics.getAscent() + ((this.getHeight() - h) >> 1));
   }

   /**
    * Launch an animation for a sprite
    * 
    * @param animation
    *           Animation to play
    * @param sprite
    *           Animated sprite
    */
   protected final void playAnimation(final JHelpAnimation animation, final JHelpSprite sprite)
   {
      animation.startAnimation(sprite);

      this.animations.add(new Pair<JHelpAnimation, JHelpSprite>(animation, sprite));
   }

   /**
    * Action when focus changed <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Focus event description
    * @see java.awt.Component#processFocusEvent(java.awt.event.FocusEvent)
    */
   @Override
   protected final void processFocusEvent(final FocusEvent e)
   {
      this.pause = e.getID() == FocusEvent.FOCUS_LOST;

      synchronized(this.LOCK)
      {
         try
         {
            if(this.isLocked == true && this.pause == false)
            {
               this.LOCK.notify();
            }
         }
         catch(final Exception exception)
         {
         }
      }
   }

   /**
    * Action when key event happen <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Key event description
    * @see java.awt.Component#processKeyEvent(java.awt.event.KeyEvent)
    */
   @Override
   protected final void processKeyEvent(final KeyEvent e)
   {
      if(e.getID() == KeyEvent.KEY_TYPED)
      {
         return;
      }

      final boolean down = e.getID() == KeyEvent.KEY_PRESSED;

      switch(e.getKeyCode())
      {
         case KeyEvent.VK_X:
            this.keyState.put(ActionKey.ACTION_ACTION, down);
         break;
         case KeyEvent.VK_ESCAPE:
            this.keyState.put(ActionKey.ACTION_CANCEL, down);
         break;
         case KeyEvent.VK_DOWN:
            this.keyState.put(ActionKey.ACTION_DOWN, down);
         break;
         case KeyEvent.VK_E:
            this.keyState.put(ActionKey.ACTION_EXIT, down);
         break;
         case KeyEvent.VK_SPACE:
            this.keyState.put(ActionKey.ACTION_FIRE, down);
         break;
         case KeyEvent.VK_CONTROL:
            this.keyState.put(ActionKey.ACTION_FIRE_2, down);
         break;
         case KeyEvent.VK_LEFT:
            this.keyState.put(ActionKey.ACTION_LEFT, down);
         break;
         case KeyEvent.VK_RIGHT:
            this.keyState.put(ActionKey.ACTION_RIGHT, down);
         break;
         case KeyEvent.VK_W:
            this.keyState.put(ActionKey.ACTION_MENU, down);
         break;
         case KeyEvent.VK_PAGE_DOWN:
            this.keyState.put(ActionKey.ACTION_NEXT, down);
         break;
         case KeyEvent.VK_P:
            this.keyState.put(ActionKey.ACTION_PAUSE, down);
         break;
         case KeyEvent.VK_PAGE_UP:
            this.keyState.put(ActionKey.ACTION_PREVIOUS, down);
         break;
         case KeyEvent.VK_C:
            this.keyState.put(ActionKey.ACTION_SPECIAL, down);
         break;
         case KeyEvent.VK_S:
            this.keyState.put(ActionKey.ACTION_START, down);
         break;
         case KeyEvent.VK_UP:
            this.keyState.put(ActionKey.ACTION_UP, down);
         break;
      }
   }

   /**
    * Action when mouse event happen <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event description
    * @see java.awt.Component#processMouseEvent(java.awt.event.MouseEvent)
    */
   @Override
   protected final void processMouseEvent(final MouseEvent e)
   {
      this.updateMouse(e);
   }

   /**
    * Action when mouse motion event happen <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse motion event description
    * @see java.awt.Component#processMouseMotionEvent(java.awt.event.MouseEvent)
    */
   @Override
   protected final void processMouseMotionEvent(final MouseEvent e)
   {
      this.updateMouse(e);
   }

   /**
    * Action when mouse wheel event happen <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse wheel event description
    * @see java.awt.Component#processMouseWheelEvent(java.awt.event.MouseWheelEvent)
    */
   @Override
   protected final void processMouseWheelEvent(final MouseWheelEvent e)
   {
      this.updateMouse(e);
   }

   /**
    * Call when applet is destroy <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see java.applet.Applet#destroy()
    */
   @Override
   public final void destroy()
   {
      if(this.threadID >= 0 && this.image != null)
      {
         this.image.unregister(this);

         ThreadManager.THREAD_MANAGER.cancelTask(this.threadID);

         this.threadID = -1;
      }

      synchronized(this.LOCK)
      {
         try
         {
            if(this.isLocked == true)
            {
               this.LOCK.notify();
            }
         }
         catch(final Exception exception)
         {
         }
      }

      MemorySweeper.stop();

      super.destroy();
   }

   /**
    * List of actual stored values
    * 
    * @return List of actual stored values
    */
   public final EnumerationIterator<String> getCookieInformationKeys()
   {
      return new EnumerationIterator<String>(this.cookieContent.keySet().iterator());
   }

   /**
    * Obtain the linked frame
    * 
    * @return The linked frame
    */
   public final Frame getFrame()
   {
      if(this.frame == null)
      {
         Component component = this;

         while(component != null)
         {
            if((component instanceof Frame) == true)
            {
               this.frame = (Frame) component;
               break;
            }

            component = component.getParent();
         }

         if(this.frame == null)
         {
            Debug.println(DebugLevel.WARNING, "Frame is not a parent of the applet");

            this.frame = new Frame(this.getGraphicsConfiguration());
         }
      }

      return this.frame;
   }

   /**
    * Obtain a boolean parameter
    * 
    * @param key
    *           Parameter key
    * @param defaultValue
    *           Value to use if parameter is not present
    * @return Parameter value
    */
   public final boolean getParameter(final String key, final boolean defaultValue)
   {
      final String value = this.getParameter(key);

      if(value == null)
      {
         return defaultValue;
      }

      if("true".equalsIgnoreCase(value) == true)
      {
         return true;
      }

      if("false".equalsIgnoreCase(value) == true)
      {
         return false;
      }

      return defaultValue;
   }

   /**
    * Obtain an integer parameter
    * 
    * @param key
    *           Parameter key
    * @param defaultValue
    *           Value to return if parameter is not present
    * @return Parameter value
    */
   public final int getParameter(final String key, final int defaultValue)
   {
      final String value = this.getParameter(key);

      if(value == null)
      {
         return defaultValue;
      }

      try
      {
         return Integer.parseInt(value);
      }
      catch(final Exception exception)
      {
         return defaultValue;
      }
   }

   /**
    * Obtain a String parameter
    * 
    * @param key
    *           Parameter key
    * @param defaultValue
    *           Value to return if parameter is not present
    * @return Parameter value
    */
   public final String getParameter(final String key, final String defaultValue)
   {
      final String value = this.getParameter(key);

      if(value == null)
      {
         return defaultValue;
      }

      return value;
   }

   /**
    * Call when applet is initialized <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see java.applet.Applet#init()
    */
   @Override
   public final void init()
   {
      MemorySweeper.launch();

      final Dimension size = this.getSize();

      this.image = new JHelpImage(size.width, size.height, 0xFFFFFFFF);
      this.image.register(this);

      this.enableEvents(AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_WHEEL_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK);

      this.keyState = new HashMap<ActionKey, Boolean>();

      for(final ActionKey actionKey : ActionKey.values())
      {
         this.keyState.put(actionKey, Boolean.FALSE);
      }

      this.javaScriptAccess = JSObject.getWindow(this);
      this.embedDocument = (JSObject) this.javaScriptAccess.getMember("document");

      this.extractCookie();

      ThreadManager.THREAD_MANAGER.delayedThread(this.initializeJHelpAppletTask, null, 1024);
   }

   /**
    * Obtain a cookie information
    * 
    * @param informationKey
    *           Information key
    * @return Information value or {@code null} if key dosen't exists
    */
   public final String obtainCookieInformation(final String informationKey)
   {
      return this.cookieContent.get(informationKey);
   }

   /**
    * Obtain a boolean cookie information
    * 
    * @param informationKey
    *           Information key
    * @param defaultValue
    *           Value to use if information not found
    * @return Information value or the default if value not exists
    */
   public final boolean obtainCookieInformation(final String informationKey, final boolean defaultValue)
   {
      final String information = this.obtainCookieInformation(informationKey);

      if("true".equalsIgnoreCase(information) == true)
      {
         return true;
      }

      if("false".equalsIgnoreCase(information) == true)
      {
         return false;
      }

      this.putCookieInformation(informationKey, defaultValue);

      return defaultValue;
   }

   /**
    * Obtain a int cookie information
    * 
    * @param informationKey
    *           Information key
    * @param defaultValue
    *           Value to use if information not found
    * @return Information value or the default if value not exists
    */
   public final int obtainCookieInformation(final String informationKey, final int defaultValue)
   {
      final String information = this.obtainCookieInformation(informationKey);

      if(information != null)
      {
         try
         {
            return Integer.parseInt(information);
         }
         catch(final Exception exception)
         {
            Debug.printException(exception, "The information ", informationKey, " is not a valid integer : ", information);
         }
      }

      this.putCookieInformation(informationKey, defaultValue);

      return defaultValue;
   }

   /**
    * Obtain a String cookie information
    * 
    * @param informationKey
    *           Information key
    * @param defaultValue
    *           Value to use if information not found
    * @return Information value or the default if value not exists
    */
   public final String obtainCookieInformation(final String informationKey, final String defaultValue)
   {
      final String information = this.obtainCookieInformation(informationKey);

      if(information != null)
      {
         return information;
      }

      this.putCookieInformation(informationKey, defaultValue);

      return defaultValue;
   }

   /**
    * Obtain a byte array cookie information
    * 
    * @param informationKey
    *           Information key
    * @return Stored byte array or {@code null} if information not exists
    */
   public final byte[] obtainCookieInformationByteArray(final String informationKey)
   {
      final String information = this.obtainCookieInformation(informationKey);

      if(information == null)
      {
         return null;
      }

      return JHelpBase.decode(information);
   }

   /**
    * Draw the applet <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param g
    *           Graphics environment
    * @see java.awt.Container#paint(java.awt.Graphics)
    */
   @Override
   public final void paint(final Graphics g)
   {
      g.drawImage(this.image.getImage(), 0, 0, this);

      if(this.pause == true)
      {
         this.pauseScreen(g);
      }
   }

   /**
    * Change/create a boolean cookie information
    * 
    * @param informationKey
    *           Information key
    * @param information
    *           Information value
    */
   public final void putCookieInformation(final String informationKey, final boolean information)
   {
      this.putCookieInformation(informationKey, String.valueOf(information));
   }

   /**
    * Change/create a byte array cookie information
    * 
    * @param informationKey
    *           Information key
    * @param information
    *           Information value
    */
   public final void putCookieInformation(final String informationKey, final byte[] information)
   {
      this.putCookieInformation(informationKey, JHelpBase.encode(information));
   }

   /**
    * Change/create a int cookie information
    * 
    * @param informationKey
    *           Information key
    * @param information
    *           Information value
    */
   public final void putCookieInformation(final String informationKey, final int information)
   {
      this.putCookieInformation(informationKey, String.valueOf(information));
   }

   /**
    * Change/create a String cookie information
    * 
    * @param informationKey
    *           Information key
    * @param information
    *           Information value
    */
   public final void putCookieInformation(final String informationKey, final String information)
   {
      this.cookieContent.put(informationKey, information.replace('_', '-').replace('|', ' ').replace(';', ':').replace('=', '#'));

      this.updateCookie();
   }

   /**
    * Remove a cookie information
    * 
    * @param informationKey
    *           Information key to remove
    */
   public final void removeCookieInformation(final String informationKey)
   {
      this.cookieContent.remove(informationKey);

      this.updateCookie();
   }

   /**
    * Call when applet resized <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param width
    *           New width
    * @param height
    *           New height
    * @see java.applet.Applet#resize(int, int)
    */
   @Override
   public final void resize(final int width, final int height)
   {
      super.resize(width, height);

      if(this.threadID >= 0 && this.image != null)
      {
         this.image.unregister(this);

         ThreadManager.THREAD_MANAGER.cancelTask(this.threadID);

         this.threadID = -1;
      }
   }

   /**
    * Call when applet update <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param g
    *           Graphics environment
    * @see java.awt.Container#update(java.awt.Graphics)
    */
   @Override
   public final void update(final Graphics g)
   {
      this.paint(g);
   }
}