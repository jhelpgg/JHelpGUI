package jhelp.gui;

import java.awt.Graphics;

/**
 * Something that can be draw over the image of {@link LabelJHelpImage}
 * 
 * @author JHelp
 */
public interface OverLabelJHelpImage
{
   /**
    * Draw the object over the image
    * 
    * @param g
    *           Graphics context
    * @param width
    *           Area width
    * @param height
    *           Area height
    */
   public void draw(Graphics g, int width, int height);
}