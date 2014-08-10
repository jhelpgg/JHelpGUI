package jhelp.gui.smooth;

import java.awt.Dimension;
import java.awt.Rectangle;

import jhelp.util.gui.JHelpImage;

/**
 * Component that draw one image
 * 
 * @author JHelp
 */
public class JHelpLabelImageSmooth
      extends JHelpComponentSmooth
{
   /** Lock for synchronization */
   private final Object lock = new Object();
   /** Original image to show */
   private JHelpImage   original;
   /** Resized image to fit the current layout of the component */
   private JHelpImage   precomputed;

   /**
    * Create a new instance of JHelpLabelImageSmooth with no image
    */
   public JHelpLabelImageSmooth()
   {
   }

   /**
    * Create a new instance of JHelpLabelImageSmooth
    * 
    * @param image
    *           Image to draw
    */
   public JHelpLabelImageSmooth(final JHelpImage image)
   {
      this.setImage(image);
   }

   /**
    * Compute the resized image
    * 
    * @param width
    *           Resized image width
    * @param height
    *           Resized image height
    */
   private void precomputeImage(final int width, final int height)
   {
      if((this.precomputed != null) && (this.precomputed.getWeight() == width) && (this.precomputed.getHeight() == height))
      {
         return;
      }

      this.precomputed = null;

      if(this.original != null)
      {
         this.precomputed = JHelpImage.createResizedImage(this.original, width, height);
      }
   }

   /**
    * Compute the component preferred size <br>
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
      synchronized(this.lock)
      {
         if(this.original == null)
         {
            return new Dimension(64, 64);
         }

         return new Dimension(this.original.getWidth(), this.original.getHeight());
      }
   }

   /**
    * Draw the component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param image
    *           Image parent where draw the component
    * @param x
    *           X
    * @param y
    *           Y
    * @param width
    *           Width
    * @param height
    *           Height
    * @param parentWidth
    *           Parent width
    * @param parentHeight
    *           Parent height
    * @see jhelp.gui.smooth.JHelpComponentSmooth#paint(jhelp.util.gui.JHelpImage, int, int, int, int, int, int)
    */
   @Override
   protected void paint(final JHelpImage image, int x, int y, int width, int height, final int parentWidth, final int parentHeight)
   {
      this.drawBackground(image, x, y, width, height);
      // Drawing background may have change the bounds
      final Rectangle bounds = this.getBounds();
      x = bounds.x;
      y = bounds.y;
      width = bounds.width;
      height = bounds.height;

      synchronized(this.lock)
      {
         this.precomputeImage(Math.max(1, width), Math.max(1, height));

         if(this.precomputed != null)
         {
            image.drawImage(x, y, this.precomputed);
         }
      }
   }

   /**
    * The original image to draw
    * 
    * @return Image to draw
    */
   public JHelpImage getImage()
   {
      return this.original;
   }

   /**
    * Force the component to refresh to show last image changes
    */
   public void refresh()
   {
      synchronized(this.lock)
      {
         this.precomputed = null;
      }
   }

   /**
    * Defines/change/remove the image
    * 
    * @param image
    *           New image or {@code null} for remove image
    */
   public void setImage(final JHelpImage image)
   {
      synchronized(this.lock)
      {
         this.original = image;
         this.precomputed = null;
      }
   }
}