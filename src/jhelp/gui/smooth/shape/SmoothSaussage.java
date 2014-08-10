package jhelp.gui.smooth.shape;

import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;

/**
 * Sausage shape
 * 
 * @author JHelp
 */
public class SmoothSaussage
      extends SmoothShape
{
   /** Singleton of sausage shape */
   public static final SmoothSaussage SAUSSAGE = new SmoothSaussage();

   /**
    * Create a new instance of SmoothSaussage
    */
   private SmoothSaussage()
   {
   }

   /**
    * Compute shape to use <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           X
    * @param y
    *           Y
    * @param width
    *           Width
    * @param height
    *           Height
    * @param level
    *           Shadow level
    * @return Sausage shape
    * @see jhelp.gui.smooth.shape.SmoothShape#computeShape(int, int, int, int, jhelp.gui.smooth.shape.ShadowLevel)
    */
   @Override
   protected ShapeInformation computeShape(final int x, final int y, final int width, final int height, final ShadowLevel level)
   {
      final int arc = Math.min(width, height);
      int moreX = 0;
      int moreY = 0;

      if(width == height)
      {
         moreX = arc >> 1;
         moreY = arc >> 1;
      }
      else if(width > height)
      {
         moreX = arc >> 1;
      }
      else
      {
         moreY = arc >> 1;
      }

      final int numberOfPixels = level.getNumberOfPixels() + 1;
      final RoundRectangle2D background = new RoundRectangle2D.Double(x, y, width - numberOfPixels, height - (numberOfPixels << 1), arc, arc);
      final Rectangle bounds = background.getBounds();
      final Rectangle inside = new Rectangle(bounds.x + moreX, bounds.y + moreY, bounds.width - (moreX << 1), bounds.height - (moreY << 1));

      return new ShapeInformation(background, inside);
   }

   /**
    * Compute insets for sausage <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param level
    *           Shadow level
    * @param width
    *           Width
    * @param height
    *           Height
    * @return Insets to use
    * @see jhelp.gui.smooth.shape.SmoothShape#computeInsets(jhelp.gui.smooth.shape.ShadowLevel, int, int)
    */
   @Override
   public Insets computeInsets(final ShadowLevel level, final int width, final int height)
   {
      final int arc = Math.min(width >> 1, height >> 1);
      int moreX = 0;
      int moreY = 0;

      if(width == height)
      {
         moreX = arc;
         moreY = arc;
      }
      else if(width > height)
      {
         moreX = arc;
      }
      else
      {
         moreY = arc;
      }

      final int numberOfPixels = level.getNumberOfPixels() + 1;
      return new Insets(moreY, moreX, moreY + (numberOfPixels << 1), moreX + numberOfPixels);
   }
}