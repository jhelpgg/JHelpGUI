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
package jhelp.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import jhelp.util.gui.ImageArea;
import jhelp.util.gui.ImageArea.OverPosition;
import jhelp.util.gui.ImageArea.Rectangle;
import jhelp.util.gui.JHelpImage;

/**
 * Component that shows an {@link ImageArea}
 * 
 * @author JHelp
 */
public class ComponentImageArea
      extends LabelJHelpImage
{
   /**
    * User event manager
    * 
    * @author JHelp
    */
   class EventManager
         implements MouseListener, MouseMotionListener, KeyListener
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
       *           Event description
       * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
       */
      @Override
      public void keyPressed(final KeyEvent e)
      {
         ComponentImageArea.this.doKeyPressed(e.getKeyCode());
      }

      /**
       * Called when key released <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param e
       *           Event description
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
       *           Event description
       * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
       */
      @Override
      public void keyTyped(final KeyEvent e)
      {
      }

      /**
       * Called when mouse clicked <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Event description
       * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseClicked(final MouseEvent mouseEvent)
      {
      }

      /**
       * Called when mouse dragged <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Event description
       * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseDragged(final MouseEvent mouseEvent)
      {
         ComponentImageArea.this.doMouseDrag(mouseEvent.getX(), mouseEvent.getY());
      }

      /**
       * Called when mouse enter <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Event description
       * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseEntered(final MouseEvent mouseEvent)
      {
      }

      /**
       * Called when mouse exited <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Event description
       * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseExited(final MouseEvent mouseEvent)
      {
      }

      /**
       * Called when mouse moved <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Event description
       * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseMoved(final MouseEvent mouseEvent)
      {
         ComponentImageArea.this.doMouseMove(mouseEvent.getX(), mouseEvent.getY());
      }

      /**
       * Called when mouse pressed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Event description
       * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
       */
      @Override
      public void mousePressed(final MouseEvent mouseEvent)
      {
         ComponentImageArea.this.doMouseDown(mouseEvent.getX(), mouseEvent.getY());
         ComponentImageArea.this.requestFocus();
         ComponentImageArea.this.requestFocusInWindow();
      }

      /**
       * Called when mouse released <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Event description
       * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseReleased(final MouseEvent mouseEvent)
      {
         ComponentImageArea.this.doMouseUp(mouseEvent.getX(), mouseEvent.getY());
      }
   }

   /** ComponentImageArea.java [long] */
   private static final long serialVersionUID   = -7766069743452556445L;

   /** Initial step : no point defined */
   private static final int  STATE_0_NO_POINT   = 0;
   /** Step : one point defined */
   private static final int  STATE_1_ONE_POINT  = 1;
   /** Step one point moving */
   private static final int  STATE_2_MOVE_POINT = 2;
   /** Image area to draw */
   private ImageArea         imageArea;
   /** Indicates if show or hide the areas */
   private boolean           makeTransparent;
   /** Mouse relative position */
   private OverPosition      overPosition;
   /** Actual state */
   private int               state;

   /**
    * Create a new instance of ComponentImageArea
    */
   public ComponentImageArea()
   {
      this.initialize();
   }

   /**
    * Create a new instance of ComponentImageArea
    * 
    * @param imageArea
    *           Image area to draw
    */
   public ComponentImageArea(final ImageArea imageArea)
   {
      super(imageArea.getBase());
      this.imageArea = imageArea;
      this.initialize();
   }

   /**
    * Create a new instance of ComponentImageArea
    * 
    * @param image
    *           Base image from where create the image area
    */
   public ComponentImageArea(final JHelpImage image)
   {
      super(image);
      this.imageArea = new ImageArea(image);
      this.initialize();
   }

   /**
    * Component initialization
    */
   private void initialize()
   {
      this.state = ComponentImageArea.STATE_0_NO_POINT;
      this.overPosition = OverPosition.OUTSIDE;
      this.makeTransparent = false;
      final EventManager eventManager = new EventManager();
      this.addMouseListener(eventManager);
      this.addMouseMotionListener(eventManager);
      this.setResize(true);
      this.setFocusable(true);
      this.requestFocus();
      this.requestFocusInWindow();
      this.addKeyListener(eventManager);
   }

   /**
    * Called when key pressed
    * 
    * @param keyCode
    *           Key code pressed
    */
   void doKeyPressed(final int keyCode)
   {
      final Rectangle rectangle = this.imageArea.getOverRectangle();

      if(rectangle == null)
      {
         return;
      }

      final int x = rectangle.getX();
      final int y = rectangle.getY();
      final int x2 = (x + rectangle.getWidth()) - 1;
      final int y2 = (y + rectangle.getHeight()) - 1;
      int t = 0;

      switch(keyCode)
      {
         case KeyEvent.VK_UP:
            t = y - y2 - 1;

            if((t + y) < 0)
            {
               t = -y;
            }

            this.imageArea.setOverRectangle(x, y + t, x2, y2 + t);
         break;
         case KeyEvent.VK_DOWN:
            t = (y2 - y) + 1;

            if((t + y2) > this.imageArea.getBase().getHeight())
            {
               t = this.imageArea.getBase().getHeight() - y2;
            }

            this.imageArea.setOverRectangle(x, y + t, x2, y2 + t);
         break;
         case KeyEvent.VK_LEFT:
            t = x - x2 - 1;

            if((t + x) < 0)
            {
               t = -x;
            }

            this.imageArea.setOverRectangle(x + t, y, x2 + t, y2);
         break;
         case KeyEvent.VK_RIGHT:
            t = (x2 - x) + 1;

            if((t + x2) > this.imageArea.getBase().getWidth())
            {
               t = this.imageArea.getBase().getWidth() - x2;
            }

            this.imageArea.setOverRectangle(x + t, y, x2 + t, y2);
         break;
      }
   }

   /**
    * Called when mouse button clicked
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    */
   void doMouseDown(int x, int y)
   {
      if(this.imageArea == null)
      {
         return;
      }

      if(this.isResize() == true)
      {
         x = (x * this.imageArea.getBase().getWidth()) / this.getWidth();
         y = (y * this.imageArea.getBase().getHeight()) / this.getHeight();
      }

      if(this.makeTransparent == true)
      {
         this.makeTransparent = false;
         this.imageArea.makeTransparent(x, y);
         return;
      }

      switch(this.state)
      {
         case STATE_0_NO_POINT:
            this.imageArea.setPointUpLeft(x, y);
            this.state = ComponentImageArea.STATE_1_ONE_POINT;
         break;
         case STATE_1_ONE_POINT:
            this.imageArea.setPointDownRight(x, y);
            this.state = ComponentImageArea.STATE_2_MOVE_POINT;
         break;
         case STATE_2_MOVE_POINT:
            this.overPosition = this.imageArea.obtainPosition(x, y);
         break;
      }

      this.refresh();
   }

   /**
    * Called when mouse dragged
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    */
   void doMouseDrag(int x, int y)
   {
      if(this.imageArea == null)
      {
         return;
      }

      if(this.isResize() == true)
      {
         x = (x * this.imageArea.getBase().getWidth()) / this.getWidth();
         y = (y * this.imageArea.getBase().getHeight()) / this.getHeight();
      }

      if(this.state == ComponentImageArea.STATE_2_MOVE_POINT)
      {
         this.imageArea.setPoint(x, y, this.overPosition);
      }

      this.refresh();
   }

   /**
    * Called when mouse just move
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    */
   void doMouseMove(int x, int y)
   {
      if(this.imageArea == null)
      {
         return;
      }

      if(this.isResize() == true)
      {
         x = (x * this.imageArea.getBase().getWidth()) / this.getWidth();
         y = (y * this.imageArea.getBase().getHeight()) / this.getHeight();
      }

      switch(this.state)
      {
         case STATE_0_NO_POINT:
            this.imageArea.setOnePoint(x, y);
         break;
         case STATE_1_ONE_POINT:
            this.imageArea.setPointDownRight(x, y);
         break;
         case STATE_2_MOVE_POINT:
            this.setCursor(this.imageArea.obtainPosition(x, y).getCursor());
         break;
      }

      this.refresh();
   }

   /**
    * Called when mouse button released
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    */
   void doMouseUp(final int x, final int y)
   {
      this.overPosition = OverPosition.OUTSIDE;
   }

   /**
    * Divide the area in, regular cell
    * 
    * @param horizontal
    *           Number of cell in horizontal
    * @param vertical
    *           Number of cell in vertical
    */
   public void divide(final int horizontal, final int vertical)
   {
      this.imageArea.divide(horizontal, vertical);
      this.state = ComponentImageArea.STATE_2_MOVE_POINT;
   }

   /**
    * Image area
    * 
    * @return Image area
    */
   public ImageArea getImageArea()
   {
      return this.imageArea;
   }

   /**
    * Says that the next user click will clear the touched color
    */
   public void makeNextClickColorTransparent()
   {
      this.makeTransparent = true;
   }

   /**
    * Remove image area <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.LabelJHelpImage#removeImage()
    */
   @Override
   public void removeImage()
   {
      this.state = ComponentImageArea.STATE_0_NO_POINT;
      this.overPosition = OverPosition.OUTSIDE;
      this.setGrid(1, 1);
      this.makeTransparent = false;
      this.imageArea = null;
      super.removeImage();
   }

   /**
    * Remove image area on try keeping same dimension <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.LabelJHelpImage#removeImageWithoutChangeSize()
    */
   @Override
   public void removeImageWithoutChangeSize()
   {
      this.state = ComponentImageArea.STATE_0_NO_POINT;
      this.overPosition = OverPosition.OUTSIDE;
      this.setGrid(1, 1);
      this.makeTransparent = false;
      this.imageArea = null;
      super.removeImageWithoutChangeSize();
   }

   /**
    * Define a grid
    * 
    * @param horizontal
    *           Number cell in horizontal
    * @param vertical
    *           Number cell in vertical
    */
   public void setGrid(final int horizontal, final int vertical)
   {
      if(this.imageArea != null)
      {
         this.imageArea.setGrid(horizontal, vertical);
      }
   }

   /**
    * Change the current image area
    * 
    * @param imageArea
    *           New image area
    */
   public void setImageArea(final ImageArea imageArea)
   {
      this.state = ComponentImageArea.STATE_0_NO_POINT;
      this.overPosition = OverPosition.OUTSIDE;
      this.makeTransparent = false;
      this.imageArea = imageArea;
      super.setJHelpImage(this.imageArea.getBase());
      this.setGrid(1, 1);
   }

   /**
    * Change image area on creating a new one based on given image <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param image
    *           Image to convert into image area
    * @see jhelp.gui.LabelJHelpImage#setJHelpImage(jhelp.util.gui.JHelpImage)
    */
   @Override
   public void setJHelpImage(final JHelpImage image)
   {
      this.setImageArea(new ImageArea(image));
   }
}