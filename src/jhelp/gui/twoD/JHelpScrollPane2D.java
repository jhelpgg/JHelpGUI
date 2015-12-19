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
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingUtilities;

import jhelp.gui.JHelpMouseListener;
import jhelp.util.gui.JHelpImage;
import jhelp.util.list.Pair;
import jhelp.util.math.UtilMath;

/**
 * Panel that contains a big component 2D.<br>
 * To able to see all the content, scroll by dragging the mouse or use mouse wheel
 * 
 * @author JHelp
 */
public class JHelpScrollPane2D
      extends JHelpContainer2D
      implements JHelpMouseListener
{
   /** Actual X translation */
   private int                    dx;
   /** Actual Y translation */
   private int                    dy;
   /** Inertia value */
   private int                    inertia;
   /** Inertia factor */
   private int                    inertiaFactor;
   /** Maximum height */
   private int                    maxiumHeight;
   /** Maximum width */
   private int                    maxiumWidth;
   /** Last mouse X position */
   private int                    mouseX;
   /** Last mouse Y position */
   private int                    mouseY;
   /** Indicates is scroll only if drag with right mouse button */
   private boolean                onlyOnRightClick;
   /** Scrolled content component */
   private final JHelpComponent2D scrollView;
   /** Actual X scroll */
   private int                    scrollX;
   /** Actual Y scroll */
   private int                    scrollY;

   /**
    * Create a new instance of JHelpScrollPane2D
    * 
    * @param scrollView
    *           Content view witch scroll
    */
   public JHelpScrollPane2D(final JHelpComponent2D scrollView)
   {
      this.maxiumWidth = Integer.MAX_VALUE;
      this.maxiumHeight = Integer.MAX_VALUE;

      scrollView.setParent(this);

      this.onlyOnRightClick = false;
      this.scrollView = scrollView;
      this.setMouseListener(this);

      this.setInertia(5);
   }

   /**
    * Compute scroll pane preferred dimension <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param parentWidth
    *           Parent width
    * @param parentHeight
    *           Parent height
    * @return Preferred dimension
    * @see jhelp.gui.twoD.JHelpComponent2D#computePreferredSize(int, int)
    */
   @Override
   protected Dimension computePreferredSize(final int parentWidth, final int parentHeight)
   {
      if((this.isVisible() == false) || (this.scrollView.isVisible() == false))
      {
         return new Dimension();
      }

      final Dimension preferred = this.scrollView.getPreferredSize(parentWidth, parentHeight);

      this.scrollView.setBounds(0, 0, preferred.width, preferred.height);

      if((parentWidth < 0) || (parentHeight < 0))
      {
         return new Dimension(UtilMath.minIntegers(this.maxiumWidth, preferred.width), UtilMath.minIntegers(this.maxiumHeight, preferred.height));
      }

      return new Dimension(UtilMath.minIntegers(this.maxiumWidth, parentWidth, preferred.width), UtilMath.minIntegers(this.maxiumHeight, parentHeight,
            preferred.height));
   }

   /**
    * Scrolled view
    * 
    * @return Scrolled view
    */
   protected final JHelpComponent2D getScrollView()
   {
      return this.scrollView;
   }

   /**
    * Test if mouse over the scroll pane.<br>
    * If not only right click :<br>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;it returns the component itself and associated listener else {@code null} is return<br>
    * Else:<br>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;it redirects the asking to the scroll view :<br>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if this one return not {@code null} :<br>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;this result is returned<br>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;else<br>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;it returns the component itself and associated listener else
    * {@code null} is return <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    * @return The correct component and its mouse listener
    * @see jhelp.gui.twoD.JHelpComponent2D#mouseOver(int, int)
    */
   @Override
   protected Pair<JHelpComponent2D, JHelpMouseListener> mouseOver(final int x, final int y)
   {
      if(this.onlyOnRightClick == false)
      {
         return super.mouseOver(x, y);
      }

      final Pair<JHelpComponent2D, JHelpMouseListener> pair = this.scrollView.mouseOver(x, y);

      if(pair == null)
      {
         return super.mouseOver(x, y);
      }

      return pair;
   }

   /**
    * Paint the component in a image <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           X position on the image
    * @param y
    *           Y position on the image
    * @param parent
    *           Image where draw
    * @see jhelp.gui.twoD.JHelpComponent2D#paint(int, int, jhelp.util.gui.JHelpImage)
    */
   @Override
   protected void paint(final int x, final int y, final JHelpImage parent)
   {
      final Rectangle bounds = this.getBounds();
      if((this.scrollView.isVisible() == false) || (bounds.width < 1) || (bounds.height < 1))
      {
         return;
      }
      final Rectangle view = this.scrollView.getBounds();

      final int w = bounds.width - view.width;
      final int h = bounds.height - view.height;

      this.scrollX += this.dx;
      this.scrollY += this.dy;

      if(this.scrollX < w)
      {
         this.scrollX = w;
         this.dx = 0;
      }

      if(this.scrollX > 0)
      {
         this.scrollX = 0;
         this.dx = 0;
      }

      if(this.scrollY < h)
      {
         this.scrollY = h;
         this.dy = 0;
      }

      if(this.scrollY > 0)
      {
         this.scrollY = 0;
         this.dy = 0;
      }

      this.dx = (this.dx * this.inertiaFactor) / this.inertia;
      this.dy = (this.dy * this.inertiaFactor) / this.inertia;

      this.scrollView.paintInternal(this.scrollX + x, this.scrollY + y, parent, x, y, bounds.width, bounds.height);
   }

   /**
    * Children list <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Children list
    * @see jhelp.gui.twoD.JHelpContainer2D#children()
    */
   @Override
   public List<JHelpComponent2D> children()
   {
      final ArrayList<JHelpComponent2D> children = new ArrayList<JHelpComponent2D>(1);
      children.add(this.scrollView);

      return Collections.unmodifiableList(children);
   }

   /**
    * Obtain component at a position
    * 
    * @param x
    *           X
    * @param y
    *           Y
    * @return Found component
    */
   public JHelpComponent2D getComponent(final int x, final int y)
   {
      final Pair<JHelpComponent2D, JHelpMouseListener> pair = this.scrollView.mouseOver(x, y);
      if(pair == null)
      {
         return null;
      }

      return pair.element1;
   }

   /**
    * Inertia
    * 
    * @return Inertia
    */
   public int getInertia()
   {
      return this.inertia;
   }

   /**
    * Maximum height
    * 
    * @return Maximum height
    */
   public int getMaxiumHeight()
   {
      return this.maxiumHeight;
   }

   /**
    * Maximum width
    * 
    * @return Maximum width
    */
   public int getMaxiumWidth()
   {
      return this.maxiumWidth;
   }

   /**
    * Indicates if only use right click for scrolling
    * 
    * @return {@code true} if only use right click for scrolling
    */
   public boolean isOnlyOnRightClick()
   {
      return this.onlyOnRightClick;
   }

   /**
    * Called when mouse clicked on the component <br>
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
      JHelpMouseListener mouseListener = null;

      final int x = e.getX();
      final int y = e.getY();

      final Pair<JHelpComponent2D, JHelpMouseListener> pair = this.scrollView.mouseOver(x, y);

      if(pair != null)
      {
         mouseListener = pair.element2;
      }

      if(mouseListener != null)
      {
         final Rectangle bounds = pair.element1.getBounds();
         e.translatePoint(-this.scrollX - bounds.x, -this.scrollY - bounds.y);
         e.setSource(pair.element1);
         mouseListener.mouseClicked(e);
      }
   }

   /**
    * Called when mouse dragged on the component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event description
    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseDragged(final MouseEvent e)
   {
      if((this.onlyOnRightClick == true) && (SwingUtilities.isRightMouseButton(e) == false))
      {
         final int x = e.getX();
         final int y = e.getY();

         JHelpMouseListener mouseListener = null;
         final Pair<JHelpComponent2D, JHelpMouseListener> pair = this.scrollView.mouseOver(x, y);

         if(pair != null)
         {
            mouseListener = pair.element2;
         }

         if(mouseListener != null)
         {
            final Rectangle bounds = pair.element1.getBounds();
            e.translatePoint(-this.scrollX - bounds.x, -this.scrollY - bounds.y);
            e.setSource(pair.element1);
            mouseListener.mouseDragged(e);
         }

         return;
      }

      this.dx += e.getX() - this.mouseX;
      this.dy += e.getY() - this.mouseY;

      this.mouseX = e.getX();
      this.mouseY = e.getY();
   }

   /**
    * Called when mouse entred on the component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event description
    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseEntered(final MouseEvent e)
   {
      final int x = e.getX();
      final int y = e.getY();

      JHelpMouseListener mouseListener = null;
      final Pair<JHelpComponent2D, JHelpMouseListener> pair = this.scrollView.mouseOver(x, y);

      if(pair != null)
      {
         mouseListener = pair.element2;
      }

      if(mouseListener != null)
      {
         final Rectangle bounds = pair.element1.getBounds();

         e.translatePoint(-this.scrollX - bounds.x, -this.scrollY - bounds.y);
         e.setSource(pair.element1);
         mouseListener.mouseEntered(e);
      }
   }

   /**
    * Called when mouse exited of the component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event description
    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseExited(final MouseEvent e)
   {
      final int x = e.getX();
      final int y = e.getY();

      JHelpMouseListener mouseListener = null;
      final Pair<JHelpComponent2D, JHelpMouseListener> pair = this.scrollView.mouseOver(x, y);

      if(pair != null)
      {
         mouseListener = pair.element2;
      }

      if(mouseListener != null)
      {
         final Rectangle bounds = pair.element1.getBounds();

         e.translatePoint(-this.scrollX - bounds.x, -this.scrollY - bounds.y);
         e.setSource(pair.element1);
         mouseListener.mouseExited(e);
      }
   }

   /**
    * Called when mouse moved on the component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event description
    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseMoved(final MouseEvent e)
   {
      final int x = e.getX();
      final int y = e.getY();

      JHelpMouseListener mouseListener = null;
      final Pair<JHelpComponent2D, JHelpMouseListener> pair = this.scrollView.mouseOver(x, y);

      if(pair != null)
      {
         mouseListener = pair.element2;
      }

      if(mouseListener != null)
      {
         final Rectangle bounds = pair.element1.getBounds();

         e.translatePoint(-this.scrollX - bounds.x, -this.scrollY - bounds.y);
         e.setSource(pair.element1);
         mouseListener.mouseMoved(e);
      }
   }

   /**
    * Called when mouse pressed on the component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event description
    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
    */
   @Override
   public void mousePressed(final MouseEvent e)
   {
      if((this.onlyOnRightClick == true) && (SwingUtilities.isRightMouseButton(e) == false))
      {
         final int x = e.getX();
         final int y = e.getY();

         JHelpMouseListener mouseListener = null;
         final Pair<JHelpComponent2D, JHelpMouseListener> pair = this.scrollView.mouseOver(x, y);

         if(pair != null)
         {
            mouseListener = pair.element2;
         }

         if(mouseListener != null)
         {
            final Rectangle bounds = pair.element1.getBounds();

            e.translatePoint(-this.scrollX - bounds.x, -this.scrollY - bounds.y);
            e.setSource(pair.element1);
            mouseListener.mousePressed(e);
         }

         return;
      }

      this.mouseX = e.getX();
      this.mouseY = e.getY();
   }

   /**
    * Called when mouse released on the component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event description
    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseReleased(final MouseEvent e)
   {
      if((this.onlyOnRightClick == true) && (SwingUtilities.isRightMouseButton(e) == false))
      {
         final int x = e.getX();
         final int y = e.getY();

         JHelpMouseListener mouseListener = null;
         final Pair<JHelpComponent2D, JHelpMouseListener> pair = this.scrollView.mouseOver(x, y);

         if(pair != null)
         {
            mouseListener = pair.element2;
         }

         if(mouseListener != null)
         {
            final Rectangle bounds = pair.element1.getBounds();

            e.translatePoint(-this.scrollX - bounds.x, -this.scrollY - bounds.y);
            e.setSource(pair.element1);
            mouseListener.mouseReleased(e);
         }

         return;
      }

      this.mouseX = e.getX();
      this.mouseY = e.getY();
   }

   /**
    * Called when mouse wheel moved on the component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event description
    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseWheelMoved(final MouseWheelEvent e)
   {
      this.dy -= e.getUnitsToScroll() * 5;
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
    * Change maximum height
    * 
    * @param maxiumHeight
    *           New maximum height
    */
   public void setMaxiumHeight(final int maxiumHeight)
   {
      this.maxiumHeight = Math.max(256, maxiumHeight);
      this.invalidate();
   }

   /**
    * Change maximum width
    * 
    * @param maxiumWidth
    *           New maximum width
    */
   public void setMaxiumWidth(final int maxiumWidth)
   {
      this.maxiumWidth = Math.max(256, maxiumWidth);
      this.invalidate();
   }

   /**
    * Change the status of react only on right button drag
    * 
    * @param onlyOnRightClick
    *           {@code true} for react only on right button drag
    */
   public void setOnlyOnRightClick(final boolean onlyOnRightClick)
   {
      this.onlyOnRightClick = onlyOnRightClick;
   }

   /**
    * Try make a specific rectangle area visible on automatic scrolling
    * 
    * @param rectangle
    *           Recangle area try to be visible
    */
   public void tryMakeVisible(final Rectangle rectangle)
   {
      final Rectangle bounds = this.getBounds();
      if((this.scrollView.isVisible() == false) || (bounds.width < 1) || (bounds.height < 1))
      {
         return;
      }

      rectangle.x += this.scrollX;
      rectangle.y += this.scrollY;
      if(UtilMath.computeIntresectedArea(rectangle, bounds) > ((rectangle.width * rectangle.height * 3) >> 2))
      {
         return;
      }

      int x = rectangle.x;
      int y = rectangle.y;

      if(bounds.contains(x, y) == true)
      {
         x = rectangle.width;
         y = rectangle.height;
      }

      int sx = this.scrollX - x;
      int sy = this.scrollY - y;

      final Rectangle view = this.scrollView.getBounds();

      final int w = bounds.width - view.width;
      final int h = bounds.height - view.height;

      if(sx < w)
      {
         sx = w;
      }

      if(sx > 0)
      {
         sx = 0;
      }

      if(sy < h)
      {
         sy = h;
      }

      if(sy > 0)
      {
         sy = 0;
      }

      this.scrollX = sx;
      this.scrollY = sy;
      this.dx = this.dy = 0;
   }
}