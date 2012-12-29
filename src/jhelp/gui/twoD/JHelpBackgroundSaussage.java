package jhelp.gui.twoD;

import jhelp.util.gui.JHelpImage;

/**
 * Background component with sausage look
 * 
 * @author JHelp
 */
public class JHelpBackgroundSaussage
      extends JHelpBackground2D
{
   /** Background color */
   private final int colorBackground;

   /**
    * Create a new instance of JHelpBackgroundSaussage
    * 
    * @param component2d
    *           Component content
    */
   public JHelpBackgroundSaussage(final JHelpComponent2D component2d)
   {
      this(component2d, 10, 0x80808080);
   }

   /**
    * Create a new instance of JHelpBackgroundSaussage
    * 
    * @param component2d
    *           Component content
    * @param colorBackground
    *           Background color
    */
   public JHelpBackgroundSaussage(final JHelpComponent2D component2d, final int colorBackground)
   {
      this(component2d, 10, colorBackground);
   }

   /**
    * Create a new instance of JHelpBackgroundSaussage
    * 
    * @param component2d
    *           Component content
    * @param space
    *           Border size
    * @param colorBackground
    *           Background color
    */
   public JHelpBackgroundSaussage(final JHelpComponent2D component2d, final int space, final int colorBackground)
   {
      super(component2d, space, space, space, space);

      this.colorBackground = colorBackground;
   }

   /**
    * Draw the background <br>
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
    * @param parent
    *           Image where draw
    * @param top
    *           Border top
    * @param left
    *           Border left
    * @param right
    *           Border right
    * @param bottom
    *           Border bottom
    * @see jhelp.gui.twoD.JHelpBackground2D#paintBackground(int, int, int, int, jhelp.util.gui.JHelpImage, int, int, int, int)
    */
   @Override
   protected void paintBackground(final int x, final int y, final int width, final int height, final JHelpImage parent, final int top, final int left, final int right, final int bottom)
   {
      final int arc = Math.min(width, height);

      parent.fillRoundRectangle(x, y, width, height, arc, arc, this.colorBackground);
   }
}