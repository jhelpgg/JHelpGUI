package jhelp.gui.twoD;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jhelp.gui.JHelpMouseListener;
import jhelp.util.gui.JHelpImage;
import jhelp.util.list.Pair;

/**
 * Component background of an other component
 * 
 * @author JHelp
 */
public abstract class JHelpBackground2D
      extends JHelpContainer2D
{
   /** Border bottom */
   private final int              bottom;
   /** Content component */
   private final JHelpComponent2D carryComponent;
   /** Border left */
   private final int              left;
   /** Border right */
   private final int              right;
   /** Border top */
   private final int              top;

   /**
    * Create a new instance of JHelpBackground2D
    * 
    * @param carryComponent
    *           Content component
    * @param top
    *           Border top
    * @param left
    *           Border left
    * @param right
    *           Border right
    * @param bottom
    *           Border bottom
    */
   public JHelpBackground2D(final JHelpComponent2D carryComponent, final int top, final int left, final int right, final int bottom)
   {
      if(carryComponent == null)
      {
         throw new NullPointerException("carryComponent musn't be null");
      }

      if((top < 0) || (left < 0) || (right < 0) || (bottom < 0))
      {
         throw new IllegalArgumentException("top, left, bottom and right must be >=0");
      }

      carryComponent.setParent(this);

      this.carryComponent = carryComponent;
      this.top = top;
      this.left = left;
      this.right = right;
      this.bottom = bottom;
   }

   /**
    * Compute preferred size <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param parentWidth
    *           Parent width
    * @param parentHeight
    *           Parent height
    * @return Computed size
    * @see jhelp.gui.twoD.JHelpComponent2D#computePreferredSize(int, int)
    */
   @Override
   protected final Dimension computePreferredSize(final int parentWidth, final int parentHeight)
   {
      if(this.carryComponent.isVisible() == false)
      {
         return new Dimension(this.left + this.right, this.top + this.bottom);
      }

      final Dimension dimension = this.carryComponent.getPreferredSize(parentWidth - this.left - this.right, parentHeight - this.top - this.bottom);

      return new Dimension(dimension.width + this.left + this.right, dimension.height + this.top + this.bottom);
   }

   /**
    * Obtain component under mouse position <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    * @return Component and associated listener
    * @see jhelp.gui.twoD.JHelpComponent2D#mouseOver(int, int)
    */
   @Override
   protected Pair<JHelpComponent2D, JHelpMouseListener> mouseOver(final int x, final int y)
   {
      if(this.isVisible() == false)
      {
         return null;
      }

      final Pair<JHelpComponent2D, JHelpMouseListener> pair = super.mouseOver(x, y);

      final Rectangle bounds = this.getBounds();

      if((x < this.left) || (x > (bounds.width - this.right)) || (y < this.top) || (y > (bounds.height - this.bottom)))
      {
         return pair;
      }

      return this.carryComponent.mouseOver(x - this.left, y - this.top);
   }

   /**
    * Draw the component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           X
    * @param y
    *           Y
    * @param parent
    *           Image where draw
    * @see jhelp.gui.twoD.JHelpComponent2D#paint(int, int, jhelp.util.gui.JHelpImage)
    */
   @Override
   protected final void paint(final int x, final int y, final JHelpImage parent)
   {
      final Rectangle bounds = this.getBounds();

      this.paintBackground(x, y, bounds.width, bounds.height, parent, this.top, this.left, this.right, this.bottom);

      if(this.carryComponent.isVisible() == true)
      {
         this.carryComponent.setBounds(this.left, this.top, bounds.width - this.left - this.right, bounds.height - this.top - this.bottom);
         this.carryComponent.paintInternal(x + this.left, y + this.top, parent);
         this.carryComponent.setBounds(this.left, this.top, bounds.width - this.left - this.right, bounds.height - this.top - this.bottom);
      }
   }

   /**
    * Draw the background
    * 
    * @param x
    *           X
    * @param y
    *           Y
    * @param width
    *           Width
    * @param height
    *           Height
    * @param parent
    *           Image where draw
    * @param top
    *           Border top
    * @param left
    *           Border left
    * @param right
    *           Border right
    * @param bottom
    *           Border bottom
    */
   protected abstract void paintBackground(int x, int y, int width, int height, JHelpImage parent, final int top, final int left, final int right, final int bottom);

   /**
    * List of children <br>
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
      children.add(this.carryComponent);

      return Collections.unmodifiableList(children);
   }
}