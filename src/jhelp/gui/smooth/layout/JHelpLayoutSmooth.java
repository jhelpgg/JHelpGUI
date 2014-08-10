package jhelp.gui.smooth.layout;

import java.awt.Dimension;
import java.awt.Rectangle;

import jhelp.gui.smooth.JHelpPanelSmooth;

/**
 * Layout component inside container
 * 
 * @author JHelp
 */
public interface JHelpLayoutSmooth
{
   /**
    * Indicates if a constraints can be used with the layout
    * 
    * @param contraints
    *           Constraints to test
    * @return {@code true} if constraints is accepted
    */
   public boolean acceptConstraints(JHelpConstraintsSmooth contraints);

   /**
    * Compute container preferred size
    * 
    * @param container
    *           Container to get its preferred size
    * @return Preferred size
    */
   public Dimension computePreferredSize(JHelpPanelSmooth container);

   /**
    * Layout components inside the container and return suggested container bounds.<br>
    * The suggested bounds is exactly the given bounds if the layout adapt components to the given size.<br>
    * If the result size is different the suggest bounds result this new bounds
    * 
    * @param container
    *           Container to layout its content
    * @param x
    *           Container X at start
    * @param y
    *           Container Y at start
    * @param parentWidth
    *           Container width at start
    * @param parentHeight
    *           Container height at start
    * @return Suggested container bounds
    */
   public Rectangle layoutComponents(JHelpPanelSmooth container, int x, int y, int parentWidth, int parentHeight);
}