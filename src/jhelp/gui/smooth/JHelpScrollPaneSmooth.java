package jhelp.gui.smooth;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jhelp.gui.smooth.event.SmoothMouseInformation;
import jhelp.gui.smooth.shape.ShadowLevel;
import jhelp.util.gui.JHelpImage;

/**
 * Scroll pane for scroll on big component.<br>
 * For scroll just drag the mouse on scroll pane. Or use wheel.<br>
 * It is possible to limit scroll at only right mouse button, to let left drag free for content components<br>
 * The inertia is the factor for slow down, more is small, faster scroll stopped, more is big, more scroll take time to stop.<br>
 * The wheel step is the number of pixels scroll when use mouse wheel.<br>
 * Mouse wheel will scroll horizontally only for horizontal pane, for others (vertical or able scroll in both way) wheel scroll
 * vertically.
 * 
 * @author JHelp
 */
public class JHelpScrollPaneSmooth
      extends JHelpContainerSmooth
{
   /** Component to scroll */
   private final JHelpComponentSmooth  component;

   /** Actual X translation */
   private int                         dx;

   /** Actual Y translation */
   private int                         dy;

   /** Inertia value */
   private int                         inertia;
   /** Inertia factor */
   private int                         inertiaFactor;
   /** Last delegate mouse component */
   private JHelpComponentSmooth        lastDelegate;
   /** Scroll pane scroll mode */
   private final JHelpScrollModeSmooth scrollMode;
   /** Actual scroll X */
   private int                         scrollX;
   /** Actual scroll Y */
   private int                         scrollY;
   /** Indicates if only right drag scroll */
   private boolean                     scroolWithRightButtonOnly;
   /** Starting X */
   private int                         startX;
   /** Starting Y */
   private int                         startY;
   /** Number of pixels for wheel rotation */
   private int                         wheelStep;

   /**
    * Create a new instance of JHelpScrollPaneSmooth that scroll vertically
    * 
    * @param component
    *           Component to scroll
    */
   public JHelpScrollPaneSmooth(final JHelpComponentSmooth component)
   {
      this(component, JHelpScrollModeSmooth.SCROLL_VERTICAL);
   }

   /**
    * Create a new instance of JHelpScrollPaneSmooth
    * 
    * @param component
    *           Component to scroll
    * @param scrollMode
    *           Scroll mode to use
    */
   public JHelpScrollPaneSmooth(final JHelpComponentSmooth component, final JHelpScrollModeSmooth scrollMode)
   {
      if(component == null)
      {
         throw new NullPointerException("component musn't be null");
      }

      if(scrollMode == null)
      {
         throw new NullPointerException("scrollMode musn't be null");
      }

      this.component = component;
      this.component.setParent(this, 0);
      this.scrollMode = scrollMode;
      this.scroolWithRightButtonOnly = false;
      this.wheelStep = 128;
      this.setInertia(5);
      this.setShadowLevel(ShadowLevel.NO_SHADOW);
   }

   /**
    * Scroll the view
    * 
    * @param vx
    *           Vector X
    * @param vy
    *           Vector Y
    */
   void scroll(final int vx, final int vy)
   {
      int sx = this.scrollX;
      int sy = this.scrollY;

      switch(this.scrollMode)
      {
         case SCROLL_BOTH:
            sx += vx;
            sy += vy;
         break;
         case SCROLL_HORIZONTAL:
            sx += vx;
         break;
         case SCROLL_VERTICAL:
            sy += vy;
         break;
      }

      final Rectangle bounds = this.getBounds();
      final Dimension preferred = this.component.getPreferredSize();

      if(sx > (preferred.width - bounds.width))
      {
         sx = preferred.width - bounds.width;
      }

      if(sx < 0)
      {
         sx = 0;
      }

      if(sy > (preferred.height - bounds.height))
      {
         sy = preferred.height - bounds.height;
      }

      if(sy < 0)
      {
         sy = 0;
      }

      this.scrollX = sx;
      this.scrollY = sy;
   }

   /**
    * Compute scroll pane preferred size <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Preferred size
    * @see jhelp.gui.smooth.JHelpComponentSmooth#getPreferredSizeInternal()
    */
   @Override
   protected Dimension getPreferredSizeInternal()
   {
      return this.component.getPreferredSize();
   }

   /**
    * Scroll mode
    * 
    * @return Scroll mode
    */
   protected JHelpScrollModeSmooth getScrollMode()
   {
      return this.scrollMode;
   }

   /**
    * Draw the scroll pane <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param image
    *           Image where draw
    * @param x
    *           X
    * @param y
    *           Y
    * @param width
    *           Width
    * @param height
    *           Height
    * @param parentWidth
    *           Parent limit width
    * @param parentHeight
    *           Parent limit height
    * @see jhelp.gui.smooth.JHelpComponentSmooth#paint(jhelp.util.gui.JHelpImage, int, int, int, int, int, int)
    */
   @Override
   protected void paint(final JHelpImage image, final int x, final int y, final int width, final int height, final int parentWidth, final int parentHeight)
   {
      this.scroll(this.dx, this.dy);
      this.dx = (this.dx * this.inertiaFactor) / this.inertia;
      this.dy = (this.dy * this.inertiaFactor) / this.inertia;

      this.drawBackground(image, x, y, width, height);

      final Dimension preferred = this.component.getPreferredSize();

      switch(this.scrollMode)
      {
         case SCROLL_BOTH:
         break;
         case SCROLL_HORIZONTAL:
            preferred.height = Math.min(height, preferred.height);
         break;
         case SCROLL_VERTICAL:
            preferred.width = Math.min(width, preferred.width);
         break;
      }

      this.component.setBounds(x - this.scrollX, y - this.scrollY, preferred.width, preferred.height);

      image.pushClipIntersect(x, y, width, height);
      this.component.paint(image, x - this.scrollX, y - this.scrollY, preferred.width, preferred.height, parentWidth, parentHeight);
      image.popClip();
   }

   /**
    * Manage mouse event before dispatching <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param mouseInformation
    *           Mouse event description
    * @see jhelp.gui.smooth.JHelpComponentSmooth#processMouseEvent(jhelp.gui.smooth.event.SmoothMouseInformation)
    */
   @Override
   protected synchronized void processMouseEvent(SmoothMouseInformation mouseInformation)
   {
      final int x = mouseInformation.getMouseX();
      final int y = mouseInformation.getMouseY();
      final JHelpComponentSmooth delegate = this.component.obtainComponentUnder(x, y, mouseInformation.isRightDown());
      Rectangle bounds;
      final boolean scroll = (this.scroolWithRightButtonOnly == false) || (mouseInformation.isRightDown() == true);

      // Manage here a consistent enter/exit report
      if(this.lastDelegate != delegate)
      {
         if(this.lastDelegate != null)
         {
            bounds = this.lastDelegate.getBounds();
            final SmoothMouseInformation mouseInformationCopy = new SmoothMouseInformation(MouseEvent.MOUSE_EXITED, this.lastDelegate, x - bounds.x, y
                  - bounds.y, x, y, mouseInformation.isLeftDown(), mouseInformation.isMiddleDown(), mouseInformation.isRightDown(),
                  mouseInformation.getClickcount(), mouseInformation.getWhellRotation());
            this.lastDelegate.processMouseEvent(mouseInformationCopy);
         }

         this.lastDelegate = delegate;

         if(this.lastDelegate != null)
         {
            bounds = this.lastDelegate.getBounds();
            final SmoothMouseInformation mouseInformationCopy = new SmoothMouseInformation(MouseEvent.MOUSE_ENTERED, this.lastDelegate, x - bounds.x, y
                  - bounds.y, x, y, mouseInformation.isLeftDown(), mouseInformation.isMiddleDown(), mouseInformation.isRightDown(),
                  mouseInformation.getClickcount(), mouseInformation.getWhellRotation());
            this.lastDelegate.processMouseEvent(mouseInformationCopy);
         }
      }

      bounds = this.component.getBounds();

      switch(mouseInformation.getType())
      {
         case MouseEvent.MOUSE_ENTERED:
         case MouseEvent.MOUSE_EXITED:
            this.startX = mouseInformation.getMouseX();
            this.startY = mouseInformation.getMouseY();
            // We consume the event, because enter/exit already managed
            return;
         case MouseEvent.MOUSE_WHEEL:
            if(this.scrollMode == JHelpScrollModeSmooth.SCROLL_HORIZONTAL)
            {
               this.dx += mouseInformation.getWhellRotation() * this.wheelStep;
            }
            else
            {
               this.dy += mouseInformation.getWhellRotation() * this.wheelStep;
            }
            // We consume the event, because report wheel move and scroll in same time is inconsistent
            return;
         case MouseEvent.MOUSE_MOVED:
            this.startX = mouseInformation.getMouseX();
            this.startY = mouseInformation.getMouseY();
         // We can transfer safely the event
         break;
         case MouseEvent.MOUSE_PRESSED:
            this.startX = mouseInformation.getMouseX();
            this.startY = mouseInformation.getMouseY();

            if(scroll == true)
            {
               // We consume the event, because report press and scroll in same time is inconsistent
               return;
            }

         break;
         case MouseEvent.MOUSE_DRAGGED:
         case MouseEvent.MOUSE_RELEASED:
            if(scroll == true)
            {
               this.dx += this.startX - mouseInformation.getMouseX();
               this.dy += this.startY - mouseInformation.getMouseY();
            }

            this.startX = mouseInformation.getMouseX();
            this.startY = mouseInformation.getMouseY();

            if(scroll == true)
            {
               // We consume the event, because report drag/release and scroll in same time is inconsistent
               return;
            }

         break;
      }

      if(this.lastDelegate != null)
      {
         bounds = this.lastDelegate.getBounds();
         mouseInformation = new SmoothMouseInformation(mouseInformation.getType(), this.lastDelegate, x - bounds.x, y - bounds.y, x, y,
               mouseInformation.isLeftDown(), mouseInformation.isMiddleDown(), mouseInformation.isRightDown(), mouseInformation.getClickcount(),
               mouseInformation.getWhellRotation());
         this.lastDelegate.processMouseEvent(mouseInformation);
      }
   }

   /**
    * Obtain the carry component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param index
    *           Component index
    * @return Component carray
    * @see jhelp.gui.smooth.JHelpContainerSmooth#getChild(int)
    */
   @Override
   public JHelpComponentSmooth getChild(final int index)
   {
      return this.component;
   }

   /**
    * Actual inertia
    * 
    * @return Actual inertia
    */
   public int getInertia()
   {
      return this.inertia;
   }

   /**
    * Number of children <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return 1
    * @see jhelp.gui.smooth.JHelpContainerSmooth#getNumberOfChildren()
    */
   @Override
   public int getNumberOfChildren()
   {
      return 1;
   }

   /**
    * Number of pixels by wheel rotation
    * 
    * @return Number of pixels by wheel rotation
    */
   public int getWheelStep()
   {
      return this.wheelStep;
   }

   /**
    * Indicates if only right drag scroll
    * 
    * @return {@code true} if only right drag scroll
    */
   public boolean isScroolWithRightButtonOnly()
   {
      return this.scroolWithRightButtonOnly;
   }

   /**
    * Try scroll to make a rectangle in carry component visible
    * 
    * @param bounds
    *           Rectangle to try to make visible
    */
   public void makeVisible(Rectangle bounds)
   {
      bounds = new Rectangle(bounds);
      final Rectangle rect = this.component.getBounds();
      final Rectangle ref = this.getBounds();
      bounds.x += rect.x - ref.x;
      bounds.y += rect.y - ref.y;
      int vx = 0;
      int vy = 0;

      if(bounds.x < 0)
      {
         vx = bounds.x;
      }
      else if((bounds.x + bounds.width) > ref.width)
      {
         vx = (bounds.x + bounds.width) - ref.width;
      }

      if(bounds.y < 0)
      {
         vy = bounds.y;
      }
      else if((bounds.y + bounds.height) > ref.height)
      {
         vy = (bounds.y + bounds.height) - ref.height;
      }

      this.scroll(vx, vy);
   }

   /**
    * Obtain the component under a given position <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           X
    * @param y
    *           Y
    * @param rightButton
    *           Indicates if mouse button right is down
    * @return Component under the position OR {@code null} if none
    * @see jhelp.gui.smooth.JHelpComponentSmooth#obtainComponentUnder(int, int, boolean)
    */
   @Override
   public JHelpComponentSmooth obtainComponentUnder(final int x, final int y, final boolean rightButton)
   {
      if((rightButton == true) || (this.scroolWithRightButtonOnly == false))
      {
         return super.obtainComponentUnder(x, y, rightButton);
      }

      return this.component.obtainComponentUnder(x, y, rightButton);
   }

   /**
    * Search component by ID <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param id
    *           ID searched
    * @return The component or {@code null} if not found
    * @see jhelp.gui.smooth.JHelpComponentSmooth#searchComponent(int)
    */
   @Override
   public final JHelpComponentSmooth searchComponent(final int id)
   {
      if(id == this.getId())
      {
         return this;
      }

      return this.component.searchComponent(id);
   }

   /**
    * Change the inertia
    * 
    * @param inertia
    *           New inertia
    */
   public void setInertia(final int inertia)
   {
      this.inertia = Math.max(1, inertia);
      this.inertiaFactor = this.inertia - 1;
   }

   /**
    * Change the right mouse button scroll state
    * 
    * @param scroolWithRightButtonOnly
    *           New right mouse button scroll state
    */
   public void setScroolWithRightButtonOnly(final boolean scroolWithRightButtonOnly)
   {
      this.scroolWithRightButtonOnly = scroolWithRightButtonOnly;
   }

   /**
    * Change number of pixels per wheel rotation
    * 
    * @param wheelStep
    *           New number of pixels per wheel rotation
    */
   public void setWheelStep(final int wheelStep)
   {
      this.wheelStep = Math.max(wheelStep, 1);
   }
}