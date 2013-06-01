package jhelp.gui.twoD;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import jhelp.gui.JHelpMouseListener;
import jhelp.gui.ResourcesGUI;
import jhelp.util.debug.Debug;
import jhelp.util.gui.JHelpFont;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpMask;

/**
 * Tree of elements
 * 
 * @author JHelp
 * @param <INFORMATION>
 *           Elements type
 */
public class JHelpTree2D<INFORMATION>
      extends JHelpComponent2D
{
   /**
    * Mouse sensitive area
    * 
    * @author JHelp
    * @param <ELEMENT>
    *           Carry element type
    */
   static class Area<ELEMENT>
   {
      /** Area height */
      final int        height;
      /** Image representation */
      final JHelpImage image;
      /** Information carry */
      final ELEMENT    node;
      /** Area type */
      final int        type;
      /** Area width */
      final int        width;
      /** Area top left corner X */
      final int        x;
      /** Area bottom right corner X */
      final int        xx;
      /** Area top left corner Y */
      int              y;
      /** Area bottom right corner Y */
      int              yy;

      /**
       * Create a new instance of Area
       * 
       * @param type
       *           Area type
       * @param node
       *           Information
       * @param x
       *           Top left corner X
       * @param y
       *           Top left corner Y
       * @param width
       *           Area width
       * @param height
       *           Area height
       * @param image
       *           Image representation
       */
      Area(final int type, final ELEMENT node, final int x, final int y, final int width, final int height, final JHelpImage image)
      {
         this.type = type;
         this.node = node;
         this.x = x;
         this.y = y;
         this.width = width;
         this.height = height;
         this.xx = x + width;
         this.yy = y + height;
         this.image = image;
      }

      /**
       * Indicates if a point is inside the area
       * 
       * @param x
       *           Point X
       * @param y
       *           Point Y
       * @return {@code true} if point is inside
       */
      boolean inside(final int x, final int y)
      {
         return (x >= this.x) && (x <= this.xx) && (y >= this.y) && (y <= this.yy);
      }
   }

   /**
    * Event manager for react to tree model changed and mouse events
    * 
    * @author JHelp
    */
   class EventManager
         implements JHelpTreeModelListener<INFORMATION>, JHelpMouseListener
   {
      /** Secondary mouse listener */
      JHelpMouseListener secondMouseListener;

      /**
       * Create a new instance of EventManager
       */
      EventManager()
      {
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
         final Area<INFORMATION> area = JHelpTree2D.this.obtainArea(e);
         if(area != null)
         {
            switch(area.type)
            {
               case AREA_COLLAPSE_BUTTON:
                  JHelpTree2D.this.treeModel.setExpand(area.node, false);
               break;
               case AREA_EXPAND_BUTTON:
                  JHelpTree2D.this.treeModel.setExpand(area.node, true);
               break;
            }
         }
         else if(this.secondMouseListener != null)
         {
            this.secondMouseListener.mouseClicked(e);
         }
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
         if(this.secondMouseListener != null)
         {
            this.secondMouseListener.mouseDragged(e);
         }
      }

      /**
       * Called when mouse entered <br>
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
         if(this.secondMouseListener != null)
         {
            this.secondMouseListener.mouseEntered(e);
         }
      }

      /**
       * Called when mouse exited <br>
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
         if(this.secondMouseListener != null)
         {
            this.secondMouseListener.mouseExited(e);
         }
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
         if(this.secondMouseListener != null)
         {
            this.secondMouseListener.mouseMoved(e);
         }
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
         if(this.secondMouseListener != null)
         {
            this.secondMouseListener.mousePressed(e);
         }
      }

      /**
       * Called when mouse released <br>
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
         if(this.secondMouseListener != null)
         {
            this.secondMouseListener.mouseReleased(e);
         }
      }

      /**
       * Called when mouse wheel moved <br>
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
         if(this.secondMouseListener != null)
         {
            this.secondMouseListener.mouseWheelMoved(e);
         }
      }

      /**
       * Czlled when tree model changed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param treeModel
       *           Tree model that changed
       * @see jhelp.gui.twoD.JHelpTreeModelListener#treeModelChanged(jhelp.gui.twoD.JHelpTreeModel)
       */
      @Override
      public void treeModelChanged(final JHelpTreeModel<INFORMATION> treeModel)
      {
         JHelpTree2D.this.refreshTreeImage();
      }
   }

   /** Image used for collapse */
   private static final JHelpImage            IMAGE_COLLAPSE;
   /** Image used for expand */
   private static final JHelpImage            IMAGE_EXPAND;
   /** X translation */
   private static final int                   MOVE_X;
   /** Y translation */
   private static final int                   MOVE_Y;
   /** Area type : button collapse */
   static final int                           AREA_COLLAPSE_BUTTON = 0x0;
   /** Area type : expand button */
   static final int                           AREA_EXPAND_BUTTON   = 0x1;
   /** Area type : Node representation */
   static final int                           AREA_NODE            = 0x2;
   /** Default background color */
   public static final int                    BACKGROUND           = 0xFFFFFFFF;
   /** Default text font */
   public static final JHelpFont              FONT;
   /** Default text color */
   public static final int                    FOREGROUND           = 0xFF000000;
   static
   {
      JHelpImage image = null;
      try
      {
         image = ResourcesGUI.RESOURCES.obtainJHelpImage("JHelpTreeExpand.png");
      }
      catch(final Exception exception)
      {
         Debug.printException(exception, "Failed to get expand image");
         image = new JHelpImage(1, 1);
      }
      IMAGE_EXPAND = image;

      image = null;
      try
      {
         image = ResourcesGUI.RESOURCES.obtainJHelpImage("JHelpTreeCollapse.png");
      }
      catch(final Exception exception)
      {
         Debug.printException(exception, "Failed to get collapse image");
         image = new JHelpImage(1, 1);
      }
      IMAGE_COLLAPSE = image;

      MOVE_X = Math.max(JHelpTree2D.IMAGE_EXPAND.getWidth(), JHelpTree2D.IMAGE_COLLAPSE.getWidth());
      MOVE_Y = Math.max(JHelpTree2D.IMAGE_EXPAND.getHeight(), JHelpTree2D.IMAGE_COLLAPSE.getHeight());
      FONT = new JHelpFont("Arial", Math.max(JHelpTree2D.MOVE_X, JHelpTree2D.MOVE_Y));
   }
   /** Mouse sensitive areas */
   private final ArrayList<Area<INFORMATION>> areas;
   /** Backgorund color */
   private int                                background;
   /** Event manager */
   private final EventManager                 eventManager;
   /** Text font */
   private JHelpFont                          font;
   /** Foreground color */
   private int                                foreground;
   /** Synchronization lock */
   private final Object                       lock                 = new Object();
   /** Image of the tree */
   private JHelpImage                         treeImage;
   /** Tree model */
   JHelpTreeModel<INFORMATION>                treeModel;

   /**
    * Create a new instance of JHelpTree2D
    * 
    * @param treeModel
    *           Tree model to use
    */
   public JHelpTree2D(final JHelpTreeModel<INFORMATION> treeModel)
   {
      this.font = JHelpTree2D.FONT;
      this.background = JHelpTree2D.BACKGROUND;
      this.foreground = JHelpTree2D.FOREGROUND;
      this.areas = new ArrayList<JHelpTree2D.Area<INFORMATION>>();

      this.eventManager = new EventManager();
      this.treeModel = treeModel;
      this.refreshTreeImage();

      this.treeModel.registerTreeModelListener(this.eventManager);
      super.setMouseListener(this.eventManager);
   }

   /**
    * Compute mouse sensitive areas for an information and all its children
    * 
    * @param information
    *           Information (and its children) to compute areas
    * @param x
    *           X of top left corner to start the first area
    * @param y
    *           Y of top left corner to start the first area
    * @return Total size of computed areas
    */
   private Dimension computeAreas(final INFORMATION information, final int x, final int y)
   {
      final int numberOfChildren = this.treeModel.numberOfChildren(information);
      final boolean expand = this.treeModel.isExpanded(information);
      JHelpImage image = null;
      String text = null;

      if(this.treeModel.useImageRepresentation(information) == true)
      {
         image = this.treeModel.obtainImageRepresentation(information);
      }

      if(image == null)
      {
         text = this.treeModel.obtainTextRepresentation(information);
      }

      if((image == null) && (text == null))
      {
         text = "null";
      }

      if(image == null)
      {
         final JHelpMask mask = this.font.createMask(text);
         image = new JHelpImage(mask.getWidth(), mask.getHeight());
         image.startDrawMode();
         image.paintMask(0, 0, mask, this.foreground, 0, false);
         image.endDrawMode();
      }

      int height = Math.max(JHelpTree2D.MOVE_Y, image.getHeight());

      synchronized(this.lock)
      {
         if(numberOfChildren > 0)
         {
            if(expand == true)
            {
               this.areas.add(new Area<INFORMATION>(JHelpTree2D.AREA_COLLAPSE_BUTTON, information, x, y + ((height - JHelpTree2D.MOVE_Y) >> 1), JHelpTree2D.MOVE_X, JHelpTree2D.MOVE_Y, JHelpTree2D.IMAGE_COLLAPSE));
            }
            else
            {
               this.areas.add(new Area<INFORMATION>(JHelpTree2D.AREA_EXPAND_BUTTON, information, x, y + ((height - JHelpTree2D.MOVE_Y) >> 1), JHelpTree2D.MOVE_X, JHelpTree2D.MOVE_Y, JHelpTree2D.IMAGE_EXPAND));
            }
         }

         this.areas.add(new Area<INFORMATION>(JHelpTree2D.AREA_NODE, information, x + JHelpTree2D.MOVE_X, y, image.getWidth(), height, image));
      }

      int width = x + JHelpTree2D.MOVE_X + image.getWidth();

      if(expand == true)
      {
         Dimension size;
         for(int child = 0; child < numberOfChildren; child++)
         {
            size = this.computeAreas(this.treeModel.getChild(information, child), x + JHelpTree2D.MOVE_X, y + height);

            width = Math.max(width, size.width);
            height += size.height;
         }
      }

      return new Dimension(width, height);
   }

   /**
    * Compute area under a position
    * 
    * @param x
    *           X position
    * @param y
    *           Y position
    * @return Area under position
    */
   Area<INFORMATION> obtainArea(final int x, final int y)
   {
      synchronized(this.lock)
      {
         for(final Area<INFORMATION> area : this.areas)
         {
            if(area.inside(x, y) == true)
            {
               return area;
            }
         }
      }

      return null;
   }

   /**
    * Obtain area under mouse event
    * 
    * @param mouseEvent
    *           Mouse event description
    * @return Area under mouse event
    */
   Area<INFORMATION> obtainArea(final MouseEvent mouseEvent)
   {
      return this.obtainArea(mouseEvent.getX(), mouseEvent.getY());
   }

   /**
    * Refresh image tree
    */
   void refreshTreeImage()
   {
      synchronized(this.lock)
      {
         this.areas.clear();
      }
      final Dimension size = this.computeAreas(this.treeModel.getRoot(), 0, 0);

      final JHelpImage image = new JHelpImage(Math.max(size.width, 1), Math.max(1, size.height), this.background);

      synchronized(this.lock)
      {
         image.startDrawMode();
         for(final Area<INFORMATION> area : this.areas)
         {
            image.drawImage(area.x, area.y, area.image);
         }
         image.endDrawMode();
      }

      this.treeImage = image;
   }

   /**
    * Called by java to free memory <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @throws Throwable
    *            On issue
    * @see java.lang.Object#finalize()
    */
   @Override
   protected void finalize() throws Throwable
   {
      this.treeModel.unregisterTreeModelListener(this.eventManager);

      super.finalize();
   }

   /**
    * Compute tree preferred size <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param parentWidth
    *           Parent width
    * @param parentHeight
    *           Parent height
    * @return Preferred size
    * @see jhelp.gui.twoD.JHelpComponent2D#getPrefrerredSize(int, int)
    */
   @Override
   protected Dimension getPrefrerredSize(final int parentWidth, final int parentHeight)
   {
      if(this.treeImage != null)
      {
         return new Dimension(this.treeImage.getWidth(), this.treeImage.getHeight());
      }

      return new Dimension(128, 128);
   }

   /**
    * Draw the tree <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           X location
    * @param y
    *           Y location
    * @param parent
    *           Image where draw the tree
    * @see jhelp.gui.twoD.JHelpComponent2D#paint(int, int, jhelp.util.gui.JHelpImage)
    */
   @Override
   protected void paint(final int x, final int y, final JHelpImage parent)
   {
      if(this.treeImage == null)
      {
         return;
      }

      parent.drawImage(x, y, this.treeImage);
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
    * Font text
    * 
    * @return Font text
    */
   public JHelpFont getFont()
   {
      return this.font;
   }

   /**
    * Texts color
    * 
    * @return Texts color
    */
   public int getForeground()
   {
      return this.foreground;
   }

   /**
    * Tree model
    * 
    * @return Tree model
    */
   public JHelpTreeModel<INFORMATION> getTreeModel()
   {
      return this.treeModel;
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
      this.refreshTreeImage();
   }

   /**
    * change text font
    * 
    * @param font
    *           New text font
    */
   public void setFont(final JHelpFont font)
   {
      if(font == null)
      {
         throw new NullPointerException("font musn't be null");
      }

      if(this.font.equals(font) == true)
      {
         return;
      }

      this.font = font;
      this.refreshTreeImage();
   }

   /**
    * Cahnge texts color
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
      this.refreshTreeImage();
   }

   /**
    * Defines the mouse listener.<br>
    * Use {@code nuul} for no mouse listener <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param mouseListener
    *           New mouse listener or {@code null} for remove mouse listener
    * @see jhelp.gui.twoD.JHelpComponent2D#setMouseListener(jhelp.gui.JHelpMouseListener)
    */
   @Override
   public void setMouseListener(final JHelpMouseListener mouseListener)
   {
      this.eventManager.secondMouseListener = mouseListener;
   }

   /**
    * Change the tree model
    * 
    * @param treeModel
    *           New tree model
    */
   public void setTreeModel(final JHelpTreeModel<INFORMATION> treeModel)
   {
      if(treeModel.equals(this.treeModel) == true)
      {
         return;
      }

      this.treeModel.unregisterTreeModelListener(this.eventManager);
      this.treeModel = treeModel;
      this.treeModel.registerTreeModelListener(this.eventManager);

      this.refreshTreeImage();
   }
}