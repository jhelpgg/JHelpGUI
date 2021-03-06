/**
 * <h1>License :</h1> <br>
 * The following code is deliver as is. I take care that code compile and work, but I am not responsible about any damage it may
 * cause.<br>
 * You can use, modify, the code as your need for any usage. But you can't do any action that avoid me or other person use,
 * modify this code. The code is free for usage and modification, you can't change that fact.<br>
 * <br>
 * 
 * @author JHelp
 */
package jhelp.gui.twoD;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.concurrent.atomic.AtomicBoolean;

import jhelp.gui.JHelpMouseListener;
import jhelp.gui.ResourcesGUI;
import jhelp.gui.twoD.JHelpHorizontalLayout.JHelpHorizontalLayoutConstraints;
import jhelp.gui.twoD.JHelpVerticalLayout.JHelpVerticalLayoutConstraints;
import jhelp.util.Utilities;
import jhelp.util.debug.Debug;
import jhelp.util.gui.GIF;
import jhelp.util.gui.JHelpFont;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpMask;

/**
 * A list od elements.<br>
 * Its possible to navigate inside list by up,down button page-up/page-down (Quick scroll), Enter launch a
 * {@link JHelpListListener#listSelectionChanged(JHelpList2D, int, Object, int)} like if double click
 * 
 * @author JHelp
 * @param <INFORMATION>
 *           Elements type
 */
public class JHelpList2D<INFORMATION>
      extends JHelpScrollPane2D
{
   /**
    * Event manager to react to model change and mouse events
    * 
    * @author JHelp
    */
   class EventManager
         implements JHelpListModelListener<INFORMATION>, JHelpMouseListener, KeyListener
   {
      /**
       * Create a new instance of EventManager
       */
      EventManager()
      {
      }

      /**
       * Called when key pressed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param e
       *           Key event description
       * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
       */
      @Override
      public void keyPressed(final KeyEvent e)
      {
         final boolean shift = e.isShiftDown();
         final boolean control = e.isControlDown();
         final boolean alt = e.isAltDown();

         if((JHelpList2D.this.specialKeyListener != null) && ((shift) || (control) || (alt)))
         {
            INFORMATION information = null;

            if(JHelpList2D.this.selectedIndex >= 0)
            {
               information = JHelpList2D.this.getListModel().getElement(JHelpList2D.this.selectedIndex);
            }

            JHelpList2D.this.specialKeyListener.specialKeyClicked(e.getKeyCode(), shift, control, alt, JHelpList2D.this, information,
                  JHelpList2D.this.selectedIndex);
            return;
         }

         int selection = JHelpList2D.this.selectedIndex;
         final int size = JHelpList2D.this.listModel.numberOfElement();

         try
         {
            switch(e.getKeyCode())
            {
               case KeyEvent.VK_UP:
                  if(selection > 0)
                  {
                     JHelpList2D.this.setSelectedIndex(selection - 1, 0);
                  }
               break;
               case KeyEvent.VK_DOWN:
                  if(selection < (size - 1))
                  {
                     JHelpList2D.this.setSelectedIndex(selection + 1, 0);
                  }
               break;
               case KeyEvent.VK_PAGE_UP:
                  if(selection > 0)
                  {
                     JHelpList2D.this.setSelectedIndex(Math.max(selection - 10, 0), 0);
                  }
               break;
               case KeyEvent.VK_PAGE_DOWN:
                  if(selection < (size - 1))
                  {
                     JHelpList2D.this.setSelectedIndex(Math.min(selection + 10, size - 1), 0);
                  }
               break;
               case KeyEvent.VK_ENTER:
                  if(selection >= 0)
                  {
                     JHelpList2D.this.setSelectedIndex(selection, 2);
                  }
               break;
               default:
                  JHelpList2D.this.keyDown(e.getKeyCode());
               break;
            }
         }
         catch(final Exception exception)
         {
            try
            {
               Utilities.sleep(128);

               selection = JHelpList2D.this.selectedIndex;

               if(selection > 0)
               {
                  JHelpList2D.this.setSelectedIndex(selection - 1, 0);
               }
               else if(selection < (size - 1))
               {
                  JHelpList2D.this.setSelectedIndex(selection + 1, 0);
               }

               JHelpList2D.this.setSelectedIndex(selection, 0);
            }
            catch(final Exception ignored)
            {
            }
         }
      }

      /**
       * Called when key released <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param e
       *           Key event
       * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
       */
      @Override
      public void keyReleased(final KeyEvent e)
      {
      }

      /**
       * Called when key typed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param e
       *           Key event
       * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
       */
      @Override
      public void keyTyped(final KeyEvent e)
      {
      }

      /**
       * Called when list model changed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param listModel
       *           List model that changed
       * @see jhelp.gui.twoD.JHelpListModelListener#listModelChanged(jhelp.gui.twoD.JHelpListModel)
       */
      @Override
      public void listModelChanged(final JHelpListModel<INFORMATION> listModel)
      {
         JHelpList2D.this.updateList();
      }

      /**
       * Called when mouse clicked <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param e
       *           Mouse event description
       * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseClicked(final MouseEvent e)
      {
         final JHelpComponent2D component2d = UtilTwoD.getComponent2DFromMouseEvent(e);
         assert component2d != null;
         JHelpList2D.this.setSelectedIndex(component2d.getId(), e.getClickCount());
      }

      /**
       * Called when mouse dragged <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param e
       *           Mouse event description
       * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseDragged(final MouseEvent e)
      {
      }

      /**
       * Callled when mouse enter <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param e
       *           Mouse event description
       * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseEntered(final MouseEvent e)
      {
      }

      /**
       * Called when mouse exit <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param e
       *           Mouse event description
       * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseExited(final MouseEvent e)
      {
      }

      /**
       * Called when mouse moved <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param e
       *           Mouse event description
       * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseMoved(final MouseEvent e)
      {
      }

      /**
       * Called when mouse pressed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param e
       *           Mouse event description
       * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
       */
      @Override
      public void mousePressed(final MouseEvent e)
      {
      }

      /**
       * Called when mouse release <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param e
       *           Mouse event description
       * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseReleased(final MouseEvent e)
      {
      }

      /**
       * Called when mouse whell moved <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param e
       *           Mouse event description
       * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
       */
      @Override
      public void mouseWheelMoved(final MouseWheelEvent e)
      {
      }
   }

   /**
    * Item in the list
    * 
    * @author JHelp
    */
   class ListItem
         extends JHelpComponent2D
   {
      /** Item size */
      private final Dimension   dimension;
      /** Carry information */
      private final INFORMATION information;

      /**
       * Create a new instance of ListItem
       * 
       * @param information
       *           Carry information
       */
      ListItem(final INFORMATION information)
      {
         this.information = information;
         final Dimension size = JHelpList2D.this.listModel.getCellSize(information);
         if(size == null)
         {
            final JHelpImage image = this.computeImage();
            this.dimension = new Dimension(image.getWidth(), image.getHeight());
         }
         else
         {
            this.dimension = size;
         }

         this.setBounds(0, 0, this.dimension.width, this.dimension.height);
      }

      /**
       * Compute item image representation
       * 
       * @return Item image representation
       */
      synchronized JHelpImage computeImage()
      {
         JHelpImage image = null;
         String text = null;

         if(JHelpList2D.this.listModel.useImageRepresentation(this.information))
         {
            image = JHelpList2D.this.listModel.obtainImageRepresentation(this.information);
         }

         if(image == null)
         {
            text = JHelpList2D.this.listModel.obtainTextRepresentation(this.information);

            if(text == null)
            {
               text = "null";
            }

            final JHelpMask mask = JHelpList2D.this.font.createMask(text);
            image = new JHelpImage(mask.getWidth(), mask.getHeight());
            image.startDrawMode();
            image.paintMask(0, 0, mask, JHelpList2D.this.foreground, 0, false);
            image.endDrawMode();
         }

         return image;
      }

      /**
       * Compute item preferred size <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param parentWidth
       *           Parent width
       * @param parentHeight
       *           Parent height
       * @return Preferred size
       * @see jhelp.gui.twoD.JHelpComponent2D#computePreferredSize(int, int)
       */
      @Override
      protected Dimension computePreferredSize(final int parentWidth, final int parentHeight)
      {
         return this.dimension;
      }

      /**
       * Paint the item <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param x
       *           X in parent
       * @param y
       *           Y in parent
       * @param parent
       *           Parent where draw
       * @see jhelp.gui.twoD.JHelpComponent2D#paint(int, int, jhelp.util.gui.JHelpImage)
       */
      @Override
      protected void paint(final int x, final int y, final JHelpImage parent)
      {
         parent.drawImage(x, y, this.computeImage());
      }
   }

   /** Loading animation images */
   private static final JHelpImage[]        LOADING;
   /** Loading animation height */
   private static final int                 LOADING_HEIGHT;
   /** Loading animation number of images */
   private static final int                 LOADING_LENGTH;
   /** Loading animation width */
   private static final int                 LOADING_WIDH;
   /** Default background color */
   public static final int                  BACKGROUND         = 0xFFFFFFFF;
   /** Default font for texts */
   public static final JHelpFont            FONT               = new JHelpFont("Arial", 16);
   /** Default foreground color */
   public static final int                  FOREGROUND         = 0xFF000000;
   /** Default selection color */
   public static final int                  SELECTION          = 0xFFC0C0FF;
   static
   {
      JHelpImage[] loading = null;
      int length = 0;
      int width = 0;
      int height = 0;
      try
      {
         final GIF gif = new GIF(ResourcesGUI.RESOURCES.obtainResourceStream("loader.gif"));
         length = gif.numberOfImage();
         width = gif.getWidth();
         height = gif.getHeight();
         loading = new JHelpImage[length];
         for(int i = 0; i < length; i++)
         {
            loading[i] = gif.getImage(i);
         }
         gif.destroy();
      }
      catch(final Exception exception)
      {
         Debug.printException(exception, "Failed to get loading image");
      }

      LOADING = loading;
      LOADING_LENGTH = length;
      LOADING_WIDH = width;
      LOADING_HEIGHT = height;
   }
   /** Indicates if refresh can continue */
   private final AtomicBoolean              canContinueRefresh = new AtomicBoolean(false);
   /** Indicates if the list is horizontal or vertical */
   private final boolean                    horizontal;
   /** Listener of list events */
   private JHelpListListener<INFORMATION>   listListener;
   /** Loading animation current index */
   private int                              loadingIndex       = 0;
   /** Loading percent */
   private int                              percent;
   /** Indicates if refresh is processing */
   private boolean                          refreshing;
   /** Actual background color */
   int                                      background;
   /** Event manager */
   final EventManager                       eventManager;
   /** Actual font for texts */
   JHelpFont                                font;
   /** Actual foreground color */
   int                                      foreground;
   /** Embed model */
   JHelpListModel<INFORMATION>              listModel;
   /** Selected index */
   int                                      selectedIndex;
   /** Current selection color */
   int                                      selectionColor;
   /** CurrentSpecial keys listener */
   JHelpListSpecialKeyListener<INFORMATION> specialKeyListener;

   /**
    * Create a new instance of JHelpList2D
    * 
    * @param horizontal
    *           Indicates if the list is horizontal ({@code true}) or vertical ({@code false})
    * @param listModel
    *           Model to use
    */
   public JHelpList2D(final boolean horizontal, final JHelpListModel<INFORMATION> listModel)
   {
      super(new JHelpPanel2D(horizontal
            ? new JHelpHorizontalLayout()
            : new JHelpVerticalLayout()));

      this.font = JHelpTree2D.FONT;
      this.background = JHelpTree2D.BACKGROUND;
      this.foreground = JHelpTree2D.FOREGROUND;
      this.selectionColor = JHelpList2D.SELECTION;

      this.selectedIndex = -1;
      this.horizontal = horizontal;
      this.eventManager = new EventManager();
      this.listModel = listModel;
      this.updateList();
      listModel.registerJHelpListModelListener(this.eventManager);
      this.setKeyListener(this.eventManager);
   }

   /**
    * Slect an item index
    * 
    * @param selectedIndex
    *           Selected index
    * @param clickCount
    *           Number of click for selection
    */
   void setSelectedIndex(final int selectedIndex, final int clickCount)
   {
      final int size = this.listModel.numberOfElement();
      final int newSelectedIndex = (selectedIndex >= 0) && (selectedIndex < size)
            ? selectedIndex
            : -1;

      if((newSelectedIndex == this.selectedIndex) && (clickCount <= 1))
      {
         return;
      }

      final JHelpPanel2D panel2d = (JHelpPanel2D) this.getScrollView();

      if(this.selectedIndex >= 0)
      {
         final JHelpBackgroundRoundRectangle roundRectangle = (JHelpBackgroundRoundRectangle) panel2d.children().get(this.selectedIndex);
         roundRectangle.setColorBackground(this.background);
      }

      this.selectedIndex = newSelectedIndex;
      INFORMATION information = null;

      if(this.selectedIndex >= 0)
      {
         final JHelpBackgroundRoundRectangle roundRectangle = (JHelpBackgroundRoundRectangle) panel2d.children().get(this.selectedIndex);
         roundRectangle.setColorBackground(this.selectionColor);

         this.tryMakeVisible(roundRectangle.getBounds());

         information = this.listModel.getElement(this.selectedIndex);
      }

      if(this.listListener != null)
      {
         this.listListener.listSelectionChanged(this, this.selectedIndex, information, clickCount);
      }
   }

   /**
    * Update the list (Called when model changed)
    */
   void updateList()
   {
      synchronized(this.canContinueRefresh)
      {
         while(this.refreshing)
         {
            this.canContinueRefresh.set(false);

            try
            {
               this.canContinueRefresh.wait();
            }
            catch(final Exception ignored)
            {
            }
         }
      }

      this.canContinueRefresh.set(true);
      this.refreshing = true;
      this.percent = 0;

      final JHelpPanel2D panel2d = (JHelpPanel2D) this.getScrollView();
      panel2d.clearComponents();

      final int size = this.listModel.numberOfElement();
      this.selectedIndex = (this.selectedIndex >= 0) && (this.selectedIndex < size)
            ? this.selectedIndex
            : -1;
      INFORMATION information;
      JHelpBackgroundRoundRectangle backgroundRoundRectangle;
      ListItem listItem;
      final JHelpConstraints constraints = this.horizontal
            ? JHelpHorizontalLayoutConstraints.EXPANDED
            : JHelpVerticalLayoutConstraints.EXPANDED;

      int mod = 1;

      for(int i = 0; (i < size) && (this.canContinueRefresh.get()); i++)
      {
         information = this.listModel.getElement(i);

         final String tips = this.listModel.getToolTip(information);
         listItem = new ListItem(information);
         listItem.setId(i);
         listItem.setMouseListener(this.eventManager);
         listItem.setToolTip(tips);
         backgroundRoundRectangle = new JHelpBackgroundRoundRectangle(listItem,//
               i == this.selectedIndex
                     ? this.selectionColor
                     : this.background);
         backgroundRoundRectangle.setId(i);
         backgroundRoundRectangle.setMouseListener(this.eventManager);
         backgroundRoundRectangle.setToolTip(tips);
         panel2d.addComponent2D(backgroundRoundRectangle, constraints, i >= mod);
         if(i >= mod)
         {
            mod += 1 + (mod >> 3);
         }

         this.percent = (i * 100) / size;
      }

      this.refreshing = false;
      synchronized(this.canContinueRefresh)
      {
         if(this.canContinueRefresh.get())
         {
            panel2d.invalidate();
            this.updateFinished();
            this.invalidate();

            this.percent = 100;
         }
         else
         {
            this.canContinueRefresh.notify();
         }
      }

      this.invalidate();
   }

   /**
    * Called when key down (Other than Up, down, page-u, page-down, or Enter/Return) to do specific things.<br>
    * Do nothing by default
    * 
    * @param keyCode
    *           Key code down
    */
   protected void keyDown(final int keyCode)
   {
      // Do nothing by default;
   }

   /**
    * Paint the list <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           X on parent
    * @param y
    *           Y on parent
    * @param parent
    *           Parent where draw
    * @see jhelp.gui.twoD.JHelpScrollPane2D#paint(int, int, jhelp.util.gui.JHelpImage)
    */
   @Override
   protected void paint(final int x, final int y, final JHelpImage parent)
   {
      super.paint(x, y, parent);

      if((this.refreshing) && (JHelpList2D.LOADING_LENGTH > 0))
      {
         this.getBounds();
         final int xx = x + 3;
         final int yy = y + 3;

         parent.drawImage(xx, yy, JHelpList2D.LOADING[this.loadingIndex]);
         parent.drawStringCenter(xx + (JHelpList2D.LOADING_WIDH >> 1), yy + (JHelpList2D.LOADING_HEIGHT >> 1), this.percent + "%", JHelpList2D.FONT, 0xFF000000);

         this.loadingIndex = (this.loadingIndex + 1) % JHelpList2D.LOADING_LENGTH;
      }
   }

   /**
    * Called when update is finished.<br>
    * Do nothing by default
    */
   protected void updateFinished()
   {
      // Do nothing by default;
   }

   /**
    * Background color
    * 
    * @return Background color
    */
   public int getBackground()
   {
      return this.background;
   }

   /**
    * Text font
    * 
    * @return Text font
    */
   public JHelpFont getFont()
   {
      return this.font;
   }

   /**
    * Text color
    * 
    * @return Text color
    */
   public int getForeground()
   {
      return this.foreground;
   }

   /**
    * Linked list listener
    * 
    * @return List listener
    */
   public JHelpListListener<INFORMATION> getListListener()
   {
      return this.listListener;
   }

   /**
    * List model
    * 
    * @return List model
    */
   public JHelpListModel<INFORMATION> getListModel()
   {
      return this.listModel;
   }

   /**
    * Selected index.<br>
    * Value &lt;0 for no selection
    * 
    * @return Selected index or -1 for no selection
    */
   public int getSelectedIndex()
   {
      return this.selectedIndex;
   }

   /**
    * Selected information
    * 
    * @return Selected information
    */
   public INFORMATION getSelectedInformation()
   {
      if(this.selectedIndex < 0)
      {
         return null;
      }

      return this.listModel.getElement(this.selectedIndex);
   }

   /**
    * Selection color
    * 
    * @return Selection color
    */
   public int getSelectionColor()
   {
      return this.selectionColor;
   }

   /**
    * The current special keys listener
    * 
    * @return The current special keys listener
    */
   public JHelpListSpecialKeyListener<INFORMATION> getSpecialKeyListener()
   {
      return this.specialKeyListener;
   }

   /**
    * Get list tooltip for a specific position.<br>
    * It look for the item under this position in the list and give its tool tips. If no item found default tolltip is returned <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           X position
    * @param y
    *           Y position
    * @return The tootltip or {@code null} if no tooltip
    * @see jhelp.gui.twoD.JHelpComponent2D#getToolTip(int, int)
    */
   @Override
   public String getToolTip(final int x, final int y)
   {
      final JHelpComponent2D component2d = this.getComponent(x, y);
      if(component2d == null)
      {
         return this.getToolTip();
      }

      return component2d.getToolTip(x, y);
   }

   /**
    * Number of elements in the list
    * 
    * @return Number of elements in the list
    */
   public int numberOfComponents()
   {
      return ((JHelpPanel2D) this.getScrollView()).children().size();
   }

   /**
    * Change background color
    * 
    * @param background
    *           New background color
    */
   public void setBackground(final int background)
   {
      if(this.background == background)
      {
         return;
      }

      this.background = background;
      this.updateList();
   }

   /**
    * Change font for texts
    * 
    * @param font
    *           New font for texts
    */
   public void setFont(final JHelpFont font)
   {
      if(font == null)
      {
         throw new NullPointerException("font musn't be null");
      }

      if(this.font.equals(font))
      {
         return;
      }

      this.font = font;
      this.updateList();
   }

   /**
    * Change texts color
    * 
    * @param foreground
    *           New texts color
    */
   public void setForeground(final int foreground)
   {
      if(this.foreground == foreground)
      {
         return;
      }

      this.foreground = foreground;
      this.updateList();
   }

   /**
    * Defines the list listener.<br>
    * Use {@code null} for remove listener
    * 
    * @param listListener
    *           New list listener or {@code null} for no listener
    */
   public void setListListener(final JHelpListListener<INFORMATION> listListener)
   {
      this.listListener = listListener;
   }

   /**
    * Change list model
    * 
    * @param listModel
    *           New list model
    */
   public void setListModel(final JHelpListModel<INFORMATION> listModel)
   {
      if(listModel.equals(this.listModel))
      {
         return;
      }

      this.selectedIndex = -1;
      this.listModel.unregisterJHelpListModelListener(this.eventManager);
      this.listModel = listModel;
      this.updateList();
      listModel.registerJHelpListModelListener(this.eventManager);
   }

   /**
    * Change selected index
    * 
    * @param selectedIndex
    *           New selected index or -1 for no selection
    */
   public void setSelectedIndex(final int selectedIndex)
   {
      this.setSelectedIndex(selectedIndex, 0);
   }

   /**
    * Change selection color
    * 
    * @param selectionColor
    *           New selection color
    */
   public void setSelectionColor(final int selectionColor)
   {
      if(this.selectionColor == selectionColor)
      {
         return;
      }

      this.selectionColor = selectionColor;
      this.updateList();
   }

   /**
    * Change/define the special keys listener
    * 
    * @param specialKeyListener
    *           New special key listener (Can use {@code null} to remove current special key listener)
    */
   public void setSpecialKeyListener(final JHelpListSpecialKeyListener<INFORMATION> specialKeyListener)
   {
      this.specialKeyListener = specialKeyListener;
   }
}