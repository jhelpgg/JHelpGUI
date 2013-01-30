package jhelp.gui.game;

import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;

import jhelp.gui.JHelpFrameImage;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpSprite;
import jhelp.util.io.UtilIO;
import jhelp.util.list.Pair;
import jhelp.util.preference.Preferences;
import jhelp.util.text.UtilText;
import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedVerySimpleTask;

/**
 * Frame for making a 2D game.<br>
 * It simplify life for keyboard/mouse management. Just implements {@link EventManager} and set it via
 * {@link #setEventManager(EventManager)}.<br>
 * You can also do animation {@link JHelpAnimation} and associate it to a sprite {@link JHelpSprite} va
 * {@link #playAnimmation(JHelpAnimation, JHelpSprite)}.<br>
 * You can have access to the image through the method {@link #getImage()}, a short cut for create sprite exists :
 * {@link #createSprite(int, int, int, int)}.<br>
 * A preferences file is automatically created and associated to the game, the name depends on the title, and to have an access
 * to this preferences, use {@link #getPreferences()}.<br>
 * The key are some action keys, listed in {@link ActionKey}, you can set/get key mapping to action via
 * {@link #setKeyCode(ActionKey, int)} and {@link #getKeyCode(ActionKey)}.<br>
 * The base name {@link #getBaseName()} is used to know the external directory name where stored preferences, it is a good idea
 * to store other game external things here, think about {@link UtilIO#obtainExternalFile(String)
 * UtilIO.obtainExternalFile(jhelpGame2D.getBaseName())} or {@link #obtainRecommendedExternalDirectory()} to have the directory.
 * 
 * @author JHelp
 */
public abstract class JHelpGame2D
      extends JHelpFrameImage
{
   /**
    * Embed listener of mouse and keys
    * 
    * @author JHelp
    */
   class MouseKeyListener
         implements KeyListener, MouseListener, MouseMotionListener
   {
      /**
       * Create a new instance of MouseKeyListener
       */
      MouseKeyListener()
      {
      }

      /**
       * Change mouse state
       * 
       * @param mouseEvent
       *           Mouse event description
       */
      private void mouseState(final MouseEvent mouseEvent)
      {
         JHelpGame2D.this.mouseX = mouseEvent.getX();
         JHelpGame2D.this.mouseY = mouseEvent.getY();

         final int modifiers = mouseEvent.getModifiersEx();

         JHelpGame2D.this.buttonLeft = (modifiers & InputEvent.BUTTON1_DOWN_MASK) == InputEvent.BUTTON1_DOWN_MASK;
         JHelpGame2D.this.buttonMiddle = (modifiers & InputEvent.BUTTON2_DOWN_MASK) == InputEvent.BUTTON2_DOWN_MASK;
         JHelpGame2D.this.buttonRight = (modifiers & InputEvent.BUTTON3_DOWN_MASK) == InputEvent.BUTTON3_DOWN_MASK;
      }

      /**
       * Called when key pressed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param event
       *           Event description
       * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
       */
      @Override
      public void keyPressed(final KeyEvent event)
      {
         if(JHelpGame2D.this.captureKeyCodeListener != null)
         {
            JHelpGame2D.this.captureKeyCodeListener.capturedkeyCode(event.getKeyCode());

            return;
         }

         JHelpGame2D.this.changeKeyState(event.getKeyCode(), true);
      }

      /**
       * Called when key released <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param event
       *           Event description
       * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
       */
      @Override
      public void keyReleased(final KeyEvent event)
      {
         if((JHelpGame2D.this.captureKeyCodeListener != null) && (JHelpGame2D.this.captureKeyCodeListener.canLooseFocus() == true))
         {
            JHelpGame2D.this.captureKeyCodeListener = null;

            return;
         }

         JHelpGame2D.this.changeKeyState(event.getKeyCode(), false);
      }

      /**
       * Called when key typed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param event
       *           Event description
       * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
       */
      @Override
      public void keyTyped(final KeyEvent event)
      {
      }

      /**
       * Called when mouse button clicked <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param event
       *           Event description
       * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseClicked(final MouseEvent event)
      {
         this.mouseState(event);
      }

      /**
       * Called when mouse dragged <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param event
       *           Event description
       * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseDragged(final MouseEvent event)
      {
         this.mouseState(event);
      }

      /**
       * Called when mouse entered <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param event
       *           Event description
       * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseEntered(final MouseEvent event)
      {
         this.mouseState(event);
      }

      /**
       * Called when mouse exited <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param event
       *           Event description
       * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseExited(final MouseEvent event)
      {
         this.mouseState(event);
      }

      /**
       * Called when mouse moved <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param event
       *           Event description
       * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseMoved(final MouseEvent event)
      {
         this.mouseState(event);
      }

      /**
       * Called when mouse button pressed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param event
       *           Event description
       * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
       */
      @Override
      public void mousePressed(final MouseEvent event)
      {
         this.mouseState(event);
      }

      /**
       * Called when mouse button released <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param event
       *           Event description
       * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseReleased(final MouseEvent event)
      {
         this.mouseState(event);
      }
   }

   /** serialVersionUID */
   private static final long                            serialVersionUID    = -229625270965052131L;
   /** Actual action key state */
   private HashMap<ActionKey, Boolean>                  actionStates;
   /** Actual playing animations */
   private ArrayList<Pair<JHelpAnimation, JHelpSprite>> animations;
   /** Animate animations */
   private final ThreadedVerySimpleTask                 animator            = new ThreadedVerySimpleTask()
                                                                            {
                                                                               /**
                                                                                * Animate animations in separated thread <br>
                                                                                * <br>
                                                                                * <b>Parent documentation:</b><br>
                                                                                * {@inheritDoc}
                                                                                * 
                                                                                * @see jhelp.util.thread.ThreadedVerySimpleTask#doVerySimpleAction()
                                                                                */
                                                                               @Override
                                                                               protected void doVerySimpleAction()
                                                                               {
                                                                                  JHelpGame2D.this.animate();
                                                                               }
                                                                            };
   /** Indicates if at least one animation is playing */
   private boolean                                      atLeastOneAnimationPlaying;
   /** Base name for external directory */
   private String                                       baseName;
   /** Event manager to react to actual action key state, mouse position and mouse buttons state */
   private EventManager                                 eventManager;
   /** Manage key and mouse events */
   private final ThreadedVerySimpleTask                 eventManagerTask    = new ThreadedVerySimpleTask()
                                                                            {
                                                                               /**
                                                                                * Manage key and mouse events in separate thread <br>
                                                                                * <br>
                                                                                * <b>Parent documentation:</b><br>
                                                                                * {@inheritDoc}
                                                                                * 
                                                                                * @see jhelp.util.thread.ThreadedVerySimpleTask#doVerySimpleAction()
                                                                                */
                                                                               @Override
                                                                               protected void doVerySimpleAction()
                                                                               {
                                                                                  JHelpGame2D.this.manageEvents();
                                                                               }
                                                                            };
   /** Game FPS */
   private int                                          fps;
   /**
    * Manage game image initialization/refresh
    */
   private final ThreadedVerySimpleTask                 gameImageManagement = new ThreadedVerySimpleTask()
                                                                            {
                                                                               /**
                                                                                * Do the management <br>
                                                                                * <br>
                                                                                * <b>Parent documentation:</b><br>
                                                                                * {@inheritDoc}
                                                                                * 
                                                                                * @see jhelp.util.thread.ThreadedVerySimpleTask#doVerySimpleAction()
                                                                                */
                                                                               @Override
                                                                               protected void doVerySimpleAction()
                                                                               {
                                                                                  if(JHelpGame2D.this.gameInitialzed == true)
                                                                                  {
                                                                                     JHelpGame2D.this.refreshGame();
                                                                                     return;
                                                                                  }

                                                                                  JHelpGame2D.this.initializeGame();
                                                                               }
                                                                            };
   /** Association of key code and action key */
   private HashMap<Integer, ActionKey>                  keyAssociations;
   /** Last measured time */
   private long                                         lastTime;
   /** Mouse and key listener */
   private MouseKeyListener                             mouseKeyListener;
   /** Listeners of mouse sensitive areas */
   private ArrayList<MouseSensitiveAreaListener>        mouseSensitiveAreaListeners;
   /** Mouse sensitive areas */
   private ArrayList<MouseSensitiveArea>                mouseSensitiveAreas;
   /** Area under the mouse */
   private MouseSensitiveArea                           overArea;
   /** Associated preferences */
   private Preferences                                  preferences;
   /** Recommended external directory where found external resources */
   private File                                         recommendedExternalDirectory;

   /** For synchronization */
   private ReentrantLock                                reentrantLock;
   /** Sprite that shows areas */
   private JHelpSprite                                  spriteShowAreas;
   /** Indicates if mouse button left is actually down */
   boolean                                              buttonLeft;
   /** Indicates if mouse button middle is actually down */
   boolean                                              buttonMiddle;
   /** Indicates if mouse button right is actually down */
   boolean                                              buttonRight;
   /** Listener that received the next pressed key code */
   CaptureKeyCodeListener                               captureKeyCodeListener;
   /** Indicates if game is initialized */
   boolean                                              gameInitialzed;
   /** Actual mouse X position */
   int                                                  mouseX;

   /** Actual mouse Y position */
   int                                                  mouseY;

   /**
    * Create a new instance of JHelpGame2D with default title
    */
   public JHelpGame2D()
   {
      this.initialize("Game");
   }

   /**
    * Create a new instance of JHelpGame2D
    * 
    * @param title
    *           Game title/name
    */
   public JHelpGame2D(final String title)
   {
      super(title);

      this.initialize(title);
   }

   /**
    * Initialize the game frame
    * 
    * @param name
    *           Game name
    */
   private void initialize(final String name)
   {
      this.gameInitialzed = false;
      this.fps = 25;
      this.baseName = name;

      if(this.baseName == null)
      {
         this.baseName = "Game";
      }
      else
      {
         this.baseName = UtilText.removeWhiteCharacters(name).replace('-', '_').replace('/', '_').replace('\\', '_').replace('\'', '_').replace('"', '_').replace('$', '_');

         if(this.baseName.length() == 0)
         {
            this.baseName = "Game";
         }
      }

      this.mouseSensitiveAreas = new ArrayList<MouseSensitiveArea>();
      this.mouseSensitiveAreaListeners = new ArrayList<MouseSensitiveAreaListener>();

      this.reentrantLock = new ReentrantLock(true);
      this.atLeastOneAnimationPlaying = false;

      this.animations = new ArrayList<Pair<JHelpAnimation, JHelpSprite>>();

      this.mouseKeyListener = new MouseKeyListener();

      this.preferences = new Preferences(UtilIO.obtainExternalFile(this.baseName + File.separator + "preferences.pref"));

      this.actionStates = new HashMap<ActionKey, Boolean>();
      this.keyAssociations = new HashMap<Integer, ActionKey>();

      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_ACTION.name(), KeyEvent.VK_C), ActionKey.ACTION_ACTION);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_CANCEL.name(), KeyEvent.VK_ESCAPE), ActionKey.ACTION_CANCEL);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_DOWN.name(), KeyEvent.VK_DOWN), ActionKey.ACTION_DOWN);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_FIRE.name(), KeyEvent.VK_SPACE), ActionKey.ACTION_FIRE);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_FIRE_2.name(), KeyEvent.VK_ALT), ActionKey.ACTION_FIRE_2);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_LEFT.name(), KeyEvent.VK_LEFT), ActionKey.ACTION_LEFT);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_MENU.name(), KeyEvent.VK_W), ActionKey.ACTION_MENU);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_NEXT.name(), KeyEvent.VK_PAGE_DOWN), ActionKey.ACTION_NEXT);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_PAUSE.name(), KeyEvent.VK_P), ActionKey.ACTION_PAUSE);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_PREVIOUS.name(), KeyEvent.VK_PAGE_UP), ActionKey.ACTION_PREVIOUS);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_RIGHT.name(), KeyEvent.VK_RIGHT), ActionKey.ACTION_RIGHT);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_SPECIAL.name(), KeyEvent.VK_X), ActionKey.ACTION_SPECIAL);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_START.name(), KeyEvent.VK_S), ActionKey.ACTION_START);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_EXIT.name(), KeyEvent.VK_E), ActionKey.ACTION_EXIT);
      this.keyAssociations.put(this.preferences.getValue(ActionKey.ACTION_UP.name(), KeyEvent.VK_UP), ActionKey.ACTION_UP);

      for(final ActionKey actionKey : ActionKey.values())
      {
         this.actionStates.put(actionKey, false);
      }

      this.mouseX = this.mouseY = Integer.MIN_VALUE;
      this.buttonLeft = this.buttonMiddle = this.buttonRight = false;

      this.componentAddKeyListener(this.mouseKeyListener);
      this.componentAddMouseListener(this.mouseKeyListener);
      this.componentAddMouseMotionListener(this.mouseKeyListener);

      ThreadManager.THREAD_MANAGER.repeatThread(this.eventManagerTask, null, 1024, 1);
      ThreadManager.THREAD_MANAGER.delayedThread(this.gameImageManagement, null, 512);
   }

   /**
    * Animate animations
    */
   void animate()
   {
      boolean loop = false;
      int max = 1;

      this.reentrantLock.lock();

      try
      {
         int length = this.animations.size();

         Pair<JHelpAnimation, JHelpSprite> pair;

         for(int i = length - 1; i >= 0; i--)
         {
            pair = this.animations.get(i);

            if(pair.element1.animate(pair.element2) == false)
            {
               this.animations.remove(i);
               length--;
            }
            else
            {
               max = Math.max(max, pair.element1.getFPS());
            }
         }

         loop = this.atLeastOneAnimationPlaying = length > 0;
      }
      finally
      {
         this.reentrantLock.unlock();
      }

      this.getImage().update();

      if(loop == true)
      {
         ThreadManager.THREAD_MANAGER.delayedThread(this.animator, null, 1000 / max);
      }
   }

   /**
    * Change actual key state
    * 
    * @param keyCode
    *           Key code
    * @param down
    *           Indicates if key is down or not
    */
   void changeKeyState(final int keyCode, final boolean down)
   {
      this.reentrantLock.lock();

      try
      {
         final ActionKey actionKey = this.keyAssociations.get(keyCode);

         if(actionKey != null)
         {
            this.actionStates.put(actionKey, down);
         }
      }
      finally
      {
         this.reentrantLock.unlock();
      }
   }

   /**
    * Initialize the game
    */
   void initializeGame()
   {
      final JHelpImage image = this.getImage();

      image.startDrawMode();
      this.gameInitialize(image);
      image.endDrawMode();

      this.createGameSrpties();

      this.gameInitialzed = true;

      ThreadManager.THREAD_MANAGER.doThread(this.gameImageManagement, null);
   }

   /**
    * Manage mouse and key events
    */
   void manageEvents()
   {
      if(this.captureKeyCodeListener != null)
      {
         return;
      }

      MouseSensitiveArea over = null;

      for(final MouseSensitiveArea mouseSensitiveArea : this.mouseSensitiveAreas)
      {
         if((mouseSensitiveArea.isEnbale() == true) && (mouseSensitiveArea.contains(this.mouseX, this.mouseY) == true))
         {
            over = mouseSensitiveArea;

            break;
         }
      }

      if((this.overArea != null) && (this.overArea != over))
      {
         final Rectangle bounds = this.overArea.getBounds();
         final int relativeX = this.mouseX - bounds.x;
         final int relativeY = this.mouseY - bounds.y;

         for(final MouseSensitiveAreaListener mouseSensitiveAreaListener : this.mouseSensitiveAreaListeners)
         {
            mouseSensitiveAreaListener.mouseExitArea(this.overArea, relativeX, relativeY, this.mouseX, this.mouseY, this.buttonLeft, this.buttonMiddle, this.buttonRight);
         }
      }

      if((over != null) && (over == this.overArea))
      {
         final Rectangle bounds = over.getBounds();
         final int relativeX = this.mouseX - bounds.x;
         final int relativeY = this.mouseY - bounds.y;

         for(final MouseSensitiveAreaListener mouseSensitiveAreaListener : this.mouseSensitiveAreaListeners)
         {
            mouseSensitiveAreaListener.mouseOverArea(over, relativeX, relativeY, this.mouseX, this.mouseY, this.buttonLeft, this.buttonMiddle, this.buttonRight);
         }
      }

      if((over != null) && (over != this.overArea))
      {
         final Rectangle bounds = over.getBounds();
         final int relativeX = this.mouseX - bounds.x;
         final int relativeY = this.mouseY - bounds.y;

         for(final MouseSensitiveAreaListener mouseSensitiveAreaListener : this.mouseSensitiveAreaListeners)
         {
            mouseSensitiveAreaListener.mouseEnterArea(over, relativeX, relativeY, this.mouseX, this.mouseY, this.buttonLeft, this.buttonMiddle, this.buttonRight);
         }
      }

      this.overArea = over;

      this.reentrantLock.lock();

      try
      {
         if(this.eventManager != null)
         {
            if(this.eventManager.actionState(Collections.unmodifiableMap(this.actionStates), this.mouseX, this.mouseY, this.buttonLeft, this.buttonMiddle, this.buttonRight) == true)
            {
               for(final ActionKey actionKey : this.actionStates.keySet())
               {
                  this.actionStates.put(actionKey, false);
               }
            }
         }
      }
      finally
      {
         this.reentrantLock.unlock();
      }
   }

   /**
    * Refresh the game
    */
   void refreshGame()
   {
      this.lastTime = System.currentTimeMillis();
      final JHelpImage image = this.getImage();

      image.startDrawMode();
      this.gameRefresh(image);
      image.endDrawMode();

      final long laps = System.currentTimeMillis() - this.lastTime;
      final long delay = Math.max(1L, (1000L / this.fps) - laps);

      ThreadManager.THREAD_MANAGER.delayedThread(this.gameImageManagement, null, delay);
   }

   /**
    * Recommended moment for create main (all) game sprites
    */
   protected abstract void createGameSrpties();

   /**
    * Called when game initialization start
    * 
    * @param image
    *           Game image where draw, already in draw mode
    */
   protected abstract void gameInitialize(JHelpImage image);

   /**
    * Call at each game frame.
    * 
    * @param image
    *           Game image where draw, already in draw mode
    */
   protected abstract void gameRefresh(JHelpImage image);

   /**
    * add a mouse sensitive area
    * 
    * @param mouseSensitiveArea
    *           Mouse sensitive area to add
    */
   public void addArea(final MouseSensitiveArea mouseSensitiveArea)
   {
      if(mouseSensitiveArea == null)
      {
         throw new NullPointerException("clickableArea musn't be null");
      }

      this.mouseSensitiveAreas.add(0, mouseSensitiveArea);
   }

   /**
    * Pass in special mode to be able to capture any next pressed key.<br>
    * Use full for settings action key by example
    * 
    * @param captureKeyCodeListener
    *           Listener to call back when key is pressed
    */
   public void captureNextPressedKey(final CaptureKeyCodeListener captureKeyCodeListener)
   {
      this.captureKeyCodeListener = captureKeyCodeListener;
   }

   /**
    * Create a sprite for embed image, this sprite can be animated with {@link #playAnimmation(JHelpAnimation, JHelpSprite)}.<br>
    * Remember, sprite are by default hide, to show them, use {@link JHelpSprite#setVisible(boolean) sprite.setVisible(true);}
    * 
    * @param x
    *           Start sprite X position
    * @param y
    *           Start sprite Y position
    * @param width
    *           Sprite width
    * @param height
    *           Sprite height
    * @return Created sprite
    */
   public JHelpSprite createSprite(final int x, final int y, final int width, final int height)
   {
      final JHelpImage image = this.getImage();

      final boolean drawMode = image.isDrawMode();

      if(drawMode == true)
      {
         image.endDrawMode();
      }

      final JHelpSprite sprite = image.createSprite(x, y, width, height);

      if(drawMode == true)
      {
         image.startDrawMode();
      }

      return sprite;
   }

   /**
    * Create an invisible sprite with a specific image
    * 
    * @param x
    *           X position
    * @param y
    *           Y position
    * @param source
    *           Ilage to use
    * @return Created sprite
    */
   public JHelpSprite createSprite(final int x, final int y, final JHelpImage source)
   {
      final JHelpImage image = this.getImage();

      final boolean drawMode = image.isDrawMode();

      if(drawMode == true)
      {
         image.endDrawMode();
      }

      final JHelpSprite sprite = image.createSprite(x, y, source);

      if(drawMode == true)
      {
         image.startDrawMode();
      }

      return sprite;
   }

   /**
    * Find area by ID
    * 
    * @param identifier
    *           ID searched
    * @return Area found
    */
   public MouseSensitiveArea findArea(final int identifier)
   {
      for(final MouseSensitiveArea mouseSensitiveArea : this.mouseSensitiveAreas)
      {
         if(mouseSensitiveArea.getIdentifer() == identifier)
         {
            return mouseSensitiveArea;
         }
      }

      return null;
   }

   /**
    * The base name of the game.<br>
    * It is the recommended external directory name to use for game data, use {@link UtilIO#obtainExternalFile(String)
    * UtilIO.obtainExternalFile(jhelpGame2D.getBaseName())} or {@link #obtainRecommendedExternalDirectory()} to have the
    * directory
    * 
    * @return The base name of the game
    */
   public String getBaseName()
   {
      return this.baseName;
   }

   /**
    * The key action state and mouse state listener
    * 
    * @return The key action state and mouse state listener
    */
   public EventManager getEventManager()
   {
      return this.eventManager;
   }

   /**
    * Actual game FPS
    * 
    * @return Actual game FPS
    */
   public int getFps()
   {
      return this.fps;
   }

   /**
    * Obtain the associate key code of a key action
    * 
    * @param actionKey
    *           Key action to have its key code
    * @return Associated key code or -1 if not associated
    */
   public int getKeyCode(final ActionKey actionKey)
   {
      for(final Entry<Integer, ActionKey> entry : this.keyAssociations.entrySet())
      {
         if(entry.getValue().equals(actionKey) == true)
         {
            return entry.getKey();
         }
      }

      return -1;
   }

   /**
    * Obtain the textual representation of the key code associated to a key action
    * 
    * @param actionKey
    *           Key action to have its key code textual representation
    * @return Key code textual representation
    */
   public String getKeyName(final ActionKey actionKey)
   {
      return KeyEvent.getKeyText(this.getKeyCode(actionKey));
   }

   /**
    * Preferences associates to the game.
    * 
    * @return Preferences associates to the game
    */
   public Preferences getPreferences()
   {
      return this.preferences;
   }

   /**
    * Recommended external directory where found external resources
    * 
    * @return Recommended external directory where found external resources
    */
   public File obtainRecommendedExternalDirectory()
   {
      if(this.recommendedExternalDirectory == null)
      {
         this.recommendedExternalDirectory = UtilIO.obtainExternalFile(this.getBaseName());
      }

      return this.recommendedExternalDirectory;
   }

   /**
    * Launch an animation using a sprite
    * 
    * @param animation
    *           Animation to play
    * @param sprite
    *           Sprite to move
    */
   public void playAnimmation(final JHelpAnimation animation, final JHelpSprite sprite)
   {
      if(animation == null)
      {
         throw new NullPointerException("animation musn't be null");
      }

      if(sprite == null)
      {
         throw new NullPointerException("sprite musn't be null");
      }

      boolean launch = false;

      this.reentrantLock.lock();

      try
      {
         this.animations.add(new Pair<JHelpAnimation, JHelpSprite>(animation, sprite));

         launch = this.atLeastOneAnimationPlaying == false;

         this.atLeastOneAnimationPlaying = true;
      }
      finally
      {
         this.reentrantLock.unlock();
      }

      animation.startAnimation(sprite);

      if(launch == true)
      {
         ThreadManager.THREAD_MANAGER.delayedThread(this.animator, null, 1000 / animation.getFPS());
      }
   }

   /**
    * Register a mouse sensitive area listener
    * 
    * @param mouseSensitiveAreaListener
    *           Listener to register
    */
   public void registerMouseSensitiveAreaListener(final MouseSensitiveAreaListener mouseSensitiveAreaListener)
   {
      if(mouseSensitiveAreaListener == null)
      {
         throw new NullPointerException("mouseAreaListener musn't be null");
      }

      this.mouseSensitiveAreaListeners.add(mouseSensitiveAreaListener);
   }

   /**
    * Remove a mouse sensitive area
    * 
    * @param mouseSensitiveArea
    *           Area to remove
    */
   public void removeArea(final MouseSensitiveArea mouseSensitiveArea)
   {
      this.mouseSensitiveAreas.remove(mouseSensitiveArea);
   }

   /**
    * Remove a sprite
    * 
    * @param sprite
    *           Sprite to remove
    */
   public void removeSprite(final JHelpSprite sprite)
   {

      final JHelpImage image = this.getImage();

      final boolean drawMode = image.isDrawMode();

      if(drawMode == true)
      {
         image.endDrawMode();
      }

      image.removeSprite(sprite);

      if(drawMode == true)
      {
         image.startDrawMode();
      }
   }

   /**
    * Change areas visible status
    * 
    * @param visible
    *           New areas visible status
    */
   public void setAreasVisible(final boolean visible)
   {
      if((this.spriteShowAreas == null) && (visible == false))
      {
         return;
      }

      if(visible == false)
      {
         this.spriteShowAreas.setVisible(false);
         this.removeSprite(this.spriteShowAreas);
         this.spriteShowAreas = null;

         return;
      }

      if(this.spriteShowAreas == null)
      {
         this.spriteShowAreas = this.createSprite(0, 0, this.getImage().getWidth(), this.getImage().getHeight());
      }

      final JHelpImage image = this.spriteShowAreas.getImage();

      image.startDrawMode();

      image.clear(0);

      Rectangle bounds;

      for(final MouseSensitiveArea mouseSensitiveArea : this.mouseSensitiveAreas)
      {
         bounds = mouseSensitiveArea.getDetection();

         image.fillRectangle(bounds.x, bounds.y, bounds.width, bounds.height, 0x80FF0000);
      }

      image.endDrawMode();
      image.update();

      this.spriteShowAreas.setVisible(false);
      this.spriteShowAreas.setVisible(true);
   }

   /**
    * Change the key action state and mouse state listener
    * 
    * @param eventManager
    *           New key action state and mouse state listener
    */
   public void setEventManager(final EventManager eventManager)
   {
      this.reentrantLock.lock();

      try
      {
         this.eventManager = eventManager;
      }
      finally
      {
         this.reentrantLock.unlock();
      }
   }

   /**
    * Change game FPS
    * 
    * @param fps
    *           New FPS
    */
   public void setFps(final int fps)
   {
      this.fps = Math.max(1, Math.min(1000, fps));
   }

   /**
    * Change key code associated to a action key.<br>
    * If the key code is already mapped to a different action, then the mapped action is unmapped (that is to say that the
    * action have no more key code associated), then the desired association is done. We return the action that have loose is
    * association (or {@code null} if none) to let a chance to developer to alert the user that a key an action is unmapped and
    * do appropriate action.
    * 
    * @param actionKey
    *           Action key to change its key code
    * @param keyCode
    *           New associated key code
    * @return Previous action key associated to the key code (This action have no more key code associated).Return {@code null}
    *         if the action have already this key code or no action is previously associated to this key code
    */
   public ActionKey setKeyCode(final ActionKey actionKey, final int keyCode)
   {
      final ActionKey oldAssociated = this.keyAssociations.get(keyCode);

      if(oldAssociated == actionKey)
      {
         return null;
      }

      int remove = 0;

      for(final Entry<Integer, ActionKey> entry : this.keyAssociations.entrySet())
      {
         if(entry.getValue() == actionKey)
         {
            remove = entry.getKey();

            break;
         }
      }

      this.keyAssociations.remove(remove);
      this.keyAssociations.put(keyCode, actionKey);

      this.preferences.setValue(actionKey.name(), keyCode);

      return oldAssociated;
   }

   /**
    * Unregister a mouse area listener
    * 
    * @param mouseSensitiveAreaListener
    *           Listener to remove
    */
   public void unregisterMouseSensitiveAreaListener(final MouseSensitiveAreaListener mouseSensitiveAreaListener)
   {
      this.mouseSensitiveAreaListeners.remove(mouseSensitiveAreaListener);
   }
}