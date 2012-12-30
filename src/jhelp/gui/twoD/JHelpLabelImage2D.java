package jhelp.gui.twoD;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.List;

import jhelp.util.gui.JHelpFont;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpTextAlign;
import jhelp.util.gui.JHelpTextLine;
import jhelp.util.list.Pair;

/**
 * Label 2D that carry an image
 * 
 * @author JHelp
 */
public class JHelpLabelImage2D
      extends JHelpComponent2D
{
   /**
    * Helper for create a label image initialized with a simple text
    * 
    * @param text
    *           Text to write
    * @param font
    *           Font to use
    * @param colorForeground
    *           Foreground color
    * @param colorBackground
    *           Background color
    * @param align
    *           How text is aligned (right, left, center)
    * @return Created label
    */
   public static JHelpLabelImage2D createTextLabel(final String text, final JHelpFont font, final int colorForeground, final int colorBackground, final JHelpTextAlign align)
   {
      final Pair<List<JHelpTextLine>, Dimension> textLines = font.computeTextLines(text, align);

      final JHelpImage image = new JHelpImage(textLines.element2.width, textLines.element2.height, colorBackground);
      image.startDrawMode();

      for(final JHelpTextLine textLine : textLines.element1)
      {
         image.paintMask(textLine.getX(), textLine.getY(), textLine.getMask(), colorForeground, 0, true);
      }

      image.endDrawMode();

      return new JHelpLabelImage2D(image);
   }

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