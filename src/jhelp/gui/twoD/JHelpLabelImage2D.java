package jhelp.gui.twoD;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import jhelp.util.gui.GIF;
import jhelp.util.gui.JHelpFont;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpTextAlign;
import jhelp.util.gui.JHelpTextLine;
import jhelp.util.io.FileImageInformation;
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
    * Image location inside the component
    * 
    * @author JHelp
    */
   public enum PlaceImage
   {
      /** Place image at bottom and center in horizontal of the component */
      BOTTOM_CENTER(JHelpLabelImage2D.VALUE_BOTTOM_RIGHT, JHelpLabelImage2D.VALUE_CENTER),
      /** Place image at bottom left of the component */
      BOTTOM_LEFT(JHelpLabelImage2D.VALUE_BOTTOM_RIGHT, JHelpLabelImage2D.VALUE_TOP_LEFT),
      /** Place image at bottom right of the component */
      BOTTOM_RIGHT(JHelpLabelImage2D.VALUE_BOTTOM_RIGHT, JHelpLabelImage2D.VALUE_BOTTOM_RIGHT),
      /** Place the image at center of the component */
      CENTER_CENTER(JHelpLabelImage2D.VALUE_CENTER, JHelpLabelImage2D.VALUE_CENTER),
      /** Place the image at verical center and left of the component */
      CENTER_LEFT(JHelpLabelImage2D.VALUE_CENTER, JHelpLabelImage2D.VALUE_TOP_LEFT),
      /** Place the image at vertical center and right of the component */
      CENTER_RIGHT(JHelpLabelImage2D.VALUE_CENTER, JHelpLabelImage2D.VALUE_BOTTOM_RIGHT),
      /** Place the image at top and horizontal center of the component */
      TOP_CENTER(JHelpLabelImage2D.VALUE_TOP_LEFT, JHelpLabelImage2D.VALUE_CENTER),
      /** Place the image at top left of the component */
      TOP_LEFT(JHelpLabelImage2D.VALUE_TOP_LEFT, JHelpLabelImage2D.VALUE_TOP_LEFT),
      /** Place the image at top right of the component */
      TOP_RIGHT(JHelpLabelImage2D.VALUE_TOP_LEFT, JHelpLabelImage2D.VALUE_BOTTOM_RIGHT);
      /** Horizontal policy */
      private int horizontal;
      /** Vertical policy */
      private int vertical;

      /**
       * Create a new instance of PlaceImage
       * 
       * @param vertical
       *           Vertical policy
       * @param horizontal
       *           Horizontal policy
       */
      PlaceImage(final int vertical, final int horizontal)
      {
         this.horizontal = horizontal;
         this.vertical = vertical;
      }

      /**
       * Horizontal policy
       * 
       * @return Horizontal policy
       */
      public int getHorizontal()
      {
         return this.horizontal;
      }

      /**
       * Vertical policy
       * 
       * @return Vertical policy
       */
      public int getVertical()
      {
         return this.vertical;
      }
   }

   /** Vertical policy bottom. Horizontal policy right */
   public static final int VALUE_BOTTOM_RIGHT = 1;
   /** Vertical or horizontal policy center */
   public static final int VALUE_CENTER       = 0;
   /** Vertical policy top. Horizontal policy left */
   public static final int VALUE_TOP_LEFT     = -1;

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

   /** Gif animation */
   private GIF        gif;
   /** Carry image */
   private JHelpImage image;
   /** Image location */
   private PlaceImage placeImage;
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
      this.placeImage = PlaceImage.CENTER_CENTER;
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
    * @see jhelp.gui.twoD.JHelpComponent2D#getPreferredSize(int, int)
    */
   @Override
   protected Dimension computePreferredSize(final int parentWidth, final int parentHeight)
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

      int xx = x;
      int yy = y;

      switch(this.placeImage.getVertical())
      {
         case VALUE_BOTTOM_RIGHT:
            yy = (y + bounds.height) - this.preferredSize.height;
         break;
         case VALUE_CENTER:
            yy = y + ((bounds.height - this.preferredSize.height) >> 1);
         break;
         case VALUE_TOP_LEFT:
            yy = y;
         break;
      }

      switch(this.placeImage.getHorizontal())
      {
         case VALUE_BOTTOM_RIGHT:
            xx = (x + bounds.width) - this.preferredSize.width;
         break;
         case VALUE_CENTER:
            xx = x + ((bounds.width - this.preferredSize.width) >> 1);
         break;
         case VALUE_TOP_LEFT:
            xx = x;
         break;
      }

      if(this.gif == null)
      {
         parent.drawImage(xx, yy, this.image);
      }
      else
      {
         parent.drawImage(xx, yy, this.gif.getImageFromStartAnimation());
      }
   }

   /**
    * Associated GIF animation
    * 
    * @return Associated GIF animation
    */
   public GIF getGIF()
   {
      return this.gif;
   }

   /**
    * The image
    * 
    * @return The image
    */
   public JHelpImage getImage()
   {
      if(this.image == null)
      {
         return this.gif.getImageFromStartAnimation();
      }

      return this.image;
   }

   /**
    * Image location in component
    * 
    * @return Image location in component
    */
   public PlaceImage getPlaceImage()
   {
      return this.placeImage;
   }

   /**
    * Change image from a file.<br>
    * If the file is not an image, the label have the dummy image.<br>
    * If the file is an animated gif, the animation will be played on the label
    * 
    * @param fileImage
    *           File to get the image
    */
   public void setFile(final File fileImage)
   {
      final FileImageInformation fileImageInformation = new FileImageInformation(fileImage);

      if((fileImageInformation.getWidth() < 1) || (fileImageInformation.getHeight() < 1))
      {
         this.setImage(JHelpImage.DUMMY);
         return;
      }

      if(fileImageInformation.getFormatName().equalsIgnoreCase("GIF") == true)
      {
         try
         {
            final GIF gif = new GIF(new FileInputStream(fileImage));
            this.setGIF(gif);
            return;
         }
         catch(final Exception exception)
         {
         }
      }

      try
      {
         this.setImage(JHelpImage.loadImage(fileImage));
      }
      catch(final Exception exception)
      {
         this.setImage(JHelpImage.DUMMY);
      }
   }

   /**
    * Give animated gif to the label.<br>
    * The animation will be played on it
    * 
    * @param gif
    *           GIF animation
    */
   public void setGIF(final GIF gif)
   {
      if(gif == null)
      {
         throw new NullPointerException("gif musn't be null");
      }

      this.gif = gif;
      this.image = null;

      gif.startAnimation();
      this.preferredSize = new Dimension(gif.getWidth(), gif.getHeight());
      this.invalidate();
   }

   /**
    * Change the image
    * 
    * @param image
    *           New image
    */
   public void setImage(final JHelpImage image)
   {
      if(image == null)
      {
         throw new NullPointerException("image musn't be null");
      }

      this.gif = null;
      this.image = image;

      this.preferredSize = new Dimension(image.getWidth(), image.getHeight());
      this.invalidate();
   }

   /**
    * Change image location in component
    * 
    * @param placeImage
    *           New image location
    */
   public void setPlaceImage(final PlaceImage placeImage)
   {
      if(placeImage == null)
      {
         throw new NullPointerException("placeImage musn't be null");
      }

      this.placeImage = placeImage;
   }
}