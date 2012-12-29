package jhelp.gui.twoD;

import java.awt.Dimension;

import jhelp.gui.twoD.JHelpFoldable2D.FoldingAreaPosition;
import jhelp.util.gui.JHelpImage;

/**
 * Describe how draw folding panel {@link JHelpFoldable2D} header
 * 
 * @author JHelp
 */
public interface FoldingAreaInterface
{
   /**
    * Header minimum size
    * 
    * @return Header minimum size
    */
   public Dimension minimumSize();

   /**
    * Draw the header
    * 
    * @param x
    *           X location
    * @param y
    *           Y location
    * @param width
    *           Width
    * @param height
    *           Height
    * @param parent
    *           Image where draw
    * @param fold
    *           Indicates if the panel is fold or not
    * @param foldingAreaPosition
    *           Header position relative to content component
    */
   public void paintArea(final int x, final int y, final int width, final int height, final JHelpImage parent, final boolean fold, final FoldingAreaPosition foldingAreaPosition);
}