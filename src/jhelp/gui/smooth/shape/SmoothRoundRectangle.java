package jhelp.gui.smooth.shape;

import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;

/**
 * Round rectangle shape
 * 
 * @author JHelp
 */
public class SmoothRoundRectangle
      extends SmoothShape
{
   /** Arc width */
   private final int arcHeight;
   /** Arc height */
   private final int arcWidth;

   /**
    * Create a new instance of SmoothRoundRectangle with arc width and height 32
    */
   public SmoothRoundRectangle()
   {
      this(32, 32);
   }

   /**
    * Create a new instance of SmoothRoundRectangle
    * 
    * @param arcWidth
    *           Arc width
    * @param arcHeight
    *           Arc height
    */
   public SmoothRoundRectangle(final int arcWidth, final int arcHeight)
   {
      this.arcWidth = arcWidth;
      this.arcHeight = arcHeight;
   }

   /**
    * Compute round rectangle shape <br>
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
    * @return Computed shape
    * @see jhelp.gui.smooth.shape.SmoothShape#computeShape(int, int, int, int, jhelp.gui.smooth.shape.ShadowLevel)
    */
   @Override
   protected ShapeInformation computeShape(final int x, final int y, final int width, final int height, final ShadowLevel level)
   {
      final int numberOfPixels = level.getNumberOfPixels() + 1;
      final RoundRectangle2D background = new RoundRectangle2D.Double(x, y, width - numberOfPixels, height - (numberOfPixels << 1), this.arcWidth, this.arcHeight);
      final Rectangle bounds = background.getBounds();
      final Rectangle inside = new Rectangle(bounds.x + this.arcWidth, bounds.y + this.arcHeight, bounds.width - (this.arcWidth << 1), bounds.height - (this.arcHeight << 1));

      return new ShapeInformation(background, inside);
   }

   /**
    * Compute insets to use width round rectangle shape <br>
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
      final int numberOfPixels = level.getNumberOfPixels() + 1;
      return new Insets(0, 0, numberOfPixels << 1, numberOfPixels);
   }
}