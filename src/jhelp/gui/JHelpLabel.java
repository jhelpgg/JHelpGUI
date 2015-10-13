package jhelp.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import jhelp.gui.smooth.JHelpConstantsSmooth;

/**
 * Label with text, select and focus status
 * 
 * @author JHelp
 */
public class JHelpLabel
      extends JComponent
{
   /** Focus status */
   private boolean     focused;
   /** Focus color */
   private Color       focusedColor;
   /** Font metrics */
   private FontMetrics fontMetrics;
   /** Selection status */
   private boolean     selected;
   /** Selection color */
   private Color       selectedColor;
   /** Text to draw */
   private String      text;
   /** Text ascent */
   private int         textAscent;
   /** Text height */
   private int         textHeight;
   /** Text width */
   private int         textWidth;

   /**
    * Create a new instance of JHelpLabel
    */
   public JHelpLabel()
   {
      this("");
   }

   /**
    * Create a new instance of JHelpLabel
    * 
    * @param text
    *           Text on label
    */
   public JHelpLabel(final String text)
   {
      if(text == null)
      {
         throw new NullPointerException("text musn't be null");
      }

      this.selectedColor = new Color(JHelpConstantsSmooth.COLOR_ALPHA_HINT | (JHelpConstantsSmooth.COLOR_CYAN_0400 & JHelpConstantsSmooth.MASK_COLOR));
      this.selected = false;
      this.focusedColor = Color.BLACK;
      this.focused = false;
      this.text = text;
      this.setFont(JHelpConstantsSmooth.FONT_DISPLAY_1.getFont());
   }

   /**
    * Update text size
    */
   private void updateSize()
   {
      this.textWidth = this.fontMetrics.stringWidth(this.text);
      this.textHeight = this.fontMetrics.getHeight();
      this.textAscent = this.fontMetrics.getAscent();

      final Dimension dimension = new Dimension(this.textWidth, this.textHeight);
      this.setSize(dimension);
      this.setPreferredSize(dimension);
      this.setMinimumSize(dimension);
      this.setMaximumSize(dimension);

      this.repaint();
   }

   /**
    * Draw the label <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param graphics
    *           Graphics where draw
    * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
    */
   @Override
   protected void paintComponent(final Graphics graphics)
   {
      final int width = this.getWidth();
      final int height = this.getHeight();
      Color background = this.getBackground();

      if(background == null)
      {
         background = Color.WHITE;
      }

      graphics.setColor(background);
      graphics.fillRect(0, 0, width, height);

      Color foreground = this.getForeground();

      if(foreground == null)
      {
         foreground = Color.BLACK;
      }

      graphics.setColor(foreground);
      graphics.setFont(this.getFont());
      final int x = (width - this.textWidth) >> 1;
      final int y = (height - this.textHeight) >> 1;
      graphics.drawString(this.text, x, this.textAscent + y);

      if(this.selected == true)
      {
         graphics.setColor(this.selectedColor);
         graphics.fillRect(x, y, this.textWidth, this.textHeight);
      }

      if(this.focused == true)
      {
         graphics.setColor(this.focusedColor);
         graphics.drawRect(x, y, this.textWidth, this.textHeight);
      }
   }

   /**
    * Focused color
    * 
    * @return Focused color
    */
   public Color getFocusedColor()
   {
      return this.focusedColor;
   }

   /**
    * Selected color
    * 
    * @return Selected color
    */
   public Color getSelectedColor()
   {
      return this.selectedColor;
   }

   /**
    * Current text
    * 
    * @return Current text
    */
   public String getText()
   {
      return this.text;
   }

   /**
    * Indicates if selected
    * 
    * @return {@code true} if selected
    */
   public boolean isFocused()
   {
      return this.focused;
   }

   /**
    * Indicates if selected
    * 
    * @return {@code true} if selected
    */
   public boolean isSelected()
   {
      return this.selected;
   }

   /**
    * Change background <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param color
    *           New background color
    * @see javax.swing.JComponent#setBackground(java.awt.Color)
    */
   @Override
   public void setBackground(final Color color)
   {
      super.setBackground(color);
      this.repaint();
   }

   /**
    * Change focus status
    * 
    * @param focused
    *           New focus status
    */
   public void setFocused(final boolean focused)
   {
      if(this.focused == focused)
      {
         return;
      }

      this.focused = focused;
      this.repaint();
   }

   /**
    * Change focus color.
    * 
    * @param focusedColor
    *           New focus color
    */
   public void setFocusedColor(final Color focusedColor)
   {
      if(focusedColor == null)
      {
         throw new NullPointerException("focusedColor musn't be null");
      }

      this.focusedColor = focusedColor;

      if(this.focused == true)
      {
         this.repaint();
      }
   }

   /**
    * Change font <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param font
    *           New font
    * @see javax.swing.JComponent#setFont(java.awt.Font)
    */
   @Override
   public void setFont(final Font font)
   {
      final BufferedImage bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
      final Graphics2D graphics2d = bufferedImage.createGraphics();
      this.fontMetrics = graphics2d.getFontMetrics(font);
      graphics2d.dispose();
      bufferedImage.flush();

      super.setFont(font);
      this.updateSize();
   }

   /**
    * Change foreground <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param color
    *           New foreground
    * @see javax.swing.JComponent#setForeground(java.awt.Color)
    */
   @Override
   public void setForeground(final Color color)
   {
      super.setForeground(color);
      this.repaint();
   }

   /**
    * Change selected status
    * 
    * @param selected
    *           New selected status
    */
   public void setSelected(final boolean selected)
   {
      if(this.selected == selected)
      {
         return;
      }

      this.selected = selected;
      this.repaint();
   }

   /**
    * Change selection color.<br>
    * It is recommended to use transparency to be able see text under selection
    * 
    * @param selectedColor
    *           New selection color
    */
   public void setSelectedColor(final Color selectedColor)
   {
      if(selectedColor == null)
      {
         throw new NullPointerException("selectedColor musn't be null");
      }

      this.selectedColor = selectedColor;

      if(this.selected == true)
      {
         this.repaint();
      }
   }

   /**
    * Change the text
    * 
    * @param text
    *           New text
    */
   public void setText(final String text)
   {
      if(text == null)
      {
         throw new NullPointerException("text musn't be null");
      }

      this.text = text;
      this.updateSize();
   }
}