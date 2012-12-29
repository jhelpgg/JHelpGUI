package jhelp.gui.twoD;

import java.awt.Dimension;
import java.awt.Rectangle;

import jhelp.util.gui.JHelpImage;

/**
 * Label 2D that carry an image
 * 
 * @author JHelp
 */
public class JHelpLabelImage2D
      extends JHelpComponent2D
{
   /** Carry image */
   private JHelpImage image;
   /** Preferred size */
   private Dimension  preferredSize;

   /**
    * Create a new instance of JHelpLabelImage2D
    * 
    * @param image
    *           Image to carry
    */
   public JHelpLabelImage2D(final JHelpImage image)
   {
      this.image = image;

      this.preferredSize = new Dimension(image.getWidth(), image.getHeight());
   }

   /**
    * Compute the label preferred size <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param parentWidth
    *           Ignore here
    * @param parentHeight
    *           Ignore here
    * @return Label preferred size
    * @see jhelp.gui.twoD.JHelpComponent2D#getPrefrerredSize(int, int)
    */
   @Override
   protected Dimension getPrefrerredSize(final int parentWidth, final int parentHeight)
   {
      return new Dimension(this.preferredSize);
   }

   /**
    * Draw the label <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           X position
    * @param y
    *           Y position
    * @param parent
    *           Image where draw
    * @see jhelp.gui.twoD.JHelpComponent2D#paint(int, int, jhelp.util.gui.JHelpImage)
    */
   @Override
   protected void paint(final int x, final int y, final JHelpImage parent)
   {
      final Rectangle bounds = this.getBounds();

      parent.drawImage(x + ((bounds.width - this.preferredSize.width) >> 1),//
            y + ((bounds.height - this.preferredSize.height) >> 1), this.image);
   }

   /**
    * The image
    * 
    * @return The image
    */
   public JHelpImage getImage()
   {
      return this.image;
   }

   /**
    * Change the image
    * 
    * @param image
    *           New image
    */
   public void setImage(final JHelpImage image)
   {
      this.image = image;

      this.preferredSize = new Dimension(image.getWidth(), image.getHeight());
   }
}