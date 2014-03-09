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
 * Container 2D that have only one child center on it
 * 
 * @author JHelp
 */
public class JHelpCenterComponent
      extends JHelpContainer2D
{
   /** The child */
   private final JHelpComponent2D component2d;

   /**
    * Create a new instance of JHelpCenterComponent
    * 
    * @param component2d
    *           Component carry
    */
   public JHelpCenterComponent(final JHelpComponent2D component2d)
   {
      component2d.setParent(this);

      this.component2d = component2d;
   }

   /**
    * Compute container preferred size <br>
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
      if(this.component2d.isVisible() == false)
      {
         return new Dimension();
      }

      return this.component2d.getPreferredSize(parentWidth, parentHeight);
   }

   /**
    * Get the component under the mouse <br>
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

      return this.component2d.mouseOver(x, y);
   }

   /**
    * Draw the container <br>
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
   protected void paint(final int x, final int y, final JHelpImage parent)
   {
      if(this.component2d.isVisible() == false)
      {
         return;
      }

      final Rectangle bounds = this.getBounds();
      final Dimension prefrerred = this.component2d.getPreferredSize(bounds.width, bounds.height);

      final int xx = (bounds.width - prefrerred.width) >> 1;
      final int yy = (bounds.height - prefrerred.height) >> 1;
      this.component2d.setBounds(xx, yy, prefrerred.width, prefrerred.height);
      this.component2d.paintInternal(xx + x, yy + y, parent);
   }

   /**
    * Children list. Here only contain the unic child <br>
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
      children.add(this.component2d);

      return Collections.unmodifiableList(children);
   }
}