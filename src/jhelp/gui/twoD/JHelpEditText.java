package jhelp.gui.twoD;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.List;

import jhelp.gui.JHelpMouseListener;
import jhelp.util.gui.JHelpFont;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpTextAlign;
import jhelp.util.gui.JHelpTextLine;
import jhelp.util.list.Pair;

/**
 * Edit text 2D
 * 
 * @author JHelp
 */
public class JHelpEditText
      extends JHelpComponent2D
      implements KeyListener, JHelpMouseListener
{
   /** Default color */
   public static final int                      DEFAULT_COLOR   = 0xFF000000;
   /** Default number of columns */
   public static final int                      DEFAULT_COLUMNS = 16;
   /** Default font */
   public static final JHelpFont                DEFAULT_FONT    = new JHelpFont("Monospaced", 16);
   /** Number of columns */
   private final int                            columns;
   /** Cursor color */
   private final int                            cursorColor;
   /** Cursor height */
   private final int                            cursorHeight;
   /** Cursor position */
   private int                                  cursorPosition;
   /** Cursor width */
   private final int                            cursorWidth;
   /** Edit text height */
   private final int                            editHeight;
   /** Edit text width */
   private final int                            editWidth;
   /** Edit text font */
   private final JHelpFont                      font;
   /** Foregrounf color */
   private final int                            foregroundColor;
   /** Edit text associated image */
   private final JHelpImage                     imageEdit;
   /** Number of lines */
   private final int                            lines;
   /** Text in edit text */
   private final StringBuilder                  text;
   /** Text align */
   private final JHelpTextAlign                 textAlign;
   /** Formated text */
   private Pair<List<JHelpTextLine>, Dimension> textFormated;

   /**
    * Create a new instance of JHelpEditText
    */
   public JHelpEditText()
   {
      this(JHelpEditText.DEFAULT_COLUMNS);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param columns
    *           Number of columns
    */
   public JHelpEditText(final int columns)
   {
      this(JHelpEditText.DEFAULT_FONT, columns);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param foregroundColor
    *           Foreground color
    * @param columns
    *           Number of columns
    */
   public JHelpEditText(final int foregroundColor, final int columns)
   {
      this(foregroundColor, columns, JHelpTextAlign.LEFT);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param foregroundColor
    *           Foreground color
    * @param columns
    *           Number of columns
    * @param textAlign
    *           Text align
    */
   public JHelpEditText(final int foregroundColor, final int columns, final JHelpTextAlign textAlign)
   {
      this(JHelpEditText.DEFAULT_FONT, columns, textAlign);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param columns
    *           Number of columns
    * @param textAlign
    *           Text align
    */
   public JHelpEditText(final int columns, final JHelpTextAlign textAlign)
   {
      this(JHelpEditText.DEFAULT_COLOR, columns, textAlign);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param font
    *           Font to use
    */
   public JHelpEditText(final JHelpFont font)
   {
      this(font, JHelpEditText.DEFAULT_COLUMNS);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param font
    *           Font to use
    * @param columns
    *           Number of columns
    */
   public JHelpEditText(final JHelpFont font, final int columns)
   {
      this(font, JHelpEditText.DEFAULT_COLOR, columns);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param font
    *           Font to use
    * @param foregroundColor
    *           Foreground color
    * @param columns
    *           Number of columns
    */
   public JHelpEditText(final JHelpFont font, final int foregroundColor, final int columns)
   {
      this(font, foregroundColor, columns, 1);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param font
    *           Font to use
    * @param foregroundColor
    *           Foreground color
    * @param columns
    *           Nulber of columns
    * @param lines
    *           Number of lines
    */
   public JHelpEditText(final JHelpFont font, final int foregroundColor, final int columns, final int lines)
   {
      this(font, foregroundColor, foregroundColor, columns, lines);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param font
    *           Font to use
    * @param foregroundColor
    *           Foreground color
    * @param cursorColor
    *           Cursor color
    * @param columns
    *           Number of columns
    * @param lines
    *           Number of lines
    */
   public JHelpEditText(final JHelpFont font, final int foregroundColor, final int cursorColor, final int columns, final int lines)
   {
      this(font, foregroundColor, cursorColor, columns, lines, JHelpTextAlign.LEFT);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param font
    *           Font to use
    * @param foregroundColor
    *           Foreground color
    * @param cursorColor
    *           Cursor color
    * @param columns
    *           Number of columns
    * @param lines
    *           Number of lines
    * @param textAlign
    *           Text align
    */
   public JHelpEditText(final JHelpFont font, final int foregroundColor, final int cursorColor, final int columns, final int lines, final JHelpTextAlign textAlign)
   {
      if(font == null)
      {
         throw new NullPointerException("font musn't be null");
      }

      if(textAlign == null)
      {
         throw new NullPointerException("textAlign musn't be null");
      }

      this.font = font;
      this.textAlign = textAlign;
      this.foregroundColor = foregroundColor | 0xFF000000;
      this.cursorColor = (cursorColor & 0x00FFFFFF) | 0x80000000;
      this.cursorPosition = 0;
      this.columns = Math.max(8, columns);
      this.lines = Math.max(1, lines);

      final int w = font.getMaximumCharacterWidth();
      final int h = font.getHeight();
      this.editWidth = (w * this.columns) + 2;
      this.editHeight = (h * this.lines) + 2;
      this.cursorWidth = Math.max(2, w >> 3);
      this.cursorHeight = h;

      this.imageEdit = new JHelpImage(this.editWidth, this.editHeight);
      this.text = new StringBuilder();

      this.updateEditText();

      this.setKeyListener(this);
      this.setMouseListener(this);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param font
    *           Font to use
    * @param foregroundColor
    *           Forground color
    * @param cursorColor
    *           Cursor color
    * @param columns
    *           Number of columns
    * @param textAlign
    *           Text align
    */
   public JHelpEditText(final JHelpFont font, final int foregroundColor, final int cursorColor, final int columns, final JHelpTextAlign textAlign)
   {
      this(font, foregroundColor, cursorColor, columns, 1, textAlign);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param font
    *           Font to use
    * @param foregroundColor
    *           Forground color
    * @param columns
    *           Number of columns
    * @param textAlign
    *           Text align
    */
   public JHelpEditText(final JHelpFont font, final int foregroundColor, final int columns, final JHelpTextAlign textAlign)
   {
      this(font, foregroundColor, foregroundColor, columns, textAlign);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param font
    *           Font to use
    * @param columns
    *           Number of columns
    * @param textAlign
    *           Text align
    */
   public JHelpEditText(final JHelpFont font, final int columns, final JHelpTextAlign textAlign)
   {
      this(font, JHelpEditText.DEFAULT_COLOR, columns, textAlign);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param font
    *           Font to use
    * @param textAlign
    *           Text align
    */
   public JHelpEditText(final JHelpFont font, final JHelpTextAlign textAlign)
   {
      this(font, JHelpEditText.DEFAULT_COLUMNS, textAlign);
   }

   /**
    * Create a new instance of JHelpEditText
    * 
    * @param textAlign
    *           Text align
    */
   public JHelpEditText(final JHelpTextAlign textAlign)
   {
      this(JHelpEditText.DEFAULT_FONT, textAlign);
   }

   /**
    * Clear the edit text
    * 
    * @param update
    *           Indicates if update is need
    */
   private void clear(final boolean update)
   {
      if(this.text.length() == 0)
      {
         return;
      }

      this.text.delete(0, this.text.length());
      if(update == true)
      {
         this.cursorPosition = 0;
         this.updateEditText();
      }
   }

   /**
    * Compute cursor coordinates
    * 
    * @return Cursour coordinates
    */
   private Point computeCursorCoordinate()
   {
      if((this.textFormated == null) || (this.textFormated.element1.isEmpty() == true))
      {
         return new Point(0, 0);
      }

      int cursorPosition = this.cursorPosition;
      int length;
      int y = 0;

      for(final JHelpTextLine textLine : this.textFormated.element1)
      {
         length = textLine.getText().length();
         if(cursorPosition <= length)
         {
            return new Point(cursorPosition, y);
         }

         cursorPosition -= length;
         y++;
      }

      y = this.textFormated.element1.size() - 1;
      final JHelpTextLine textLine = this.textFormated.element1.get(y);
      return new Point(textLine.getText().length(), y);

   }

   /**
    * Place the cursor nearest as possible to the given location
    * 
    * @param x
    *           X
    * @param y
    *           Y
    */
   private void locateCursorCoordinate(final int x, final int y)
   {
      if(this.textFormated == null)
      {
         this.cursorPosition = 0;
         return;
      }

      if(y <= 0)
      {
         if(x <= 0)
         {
            this.cursorPosition = 0;
            return;
         }

         final JHelpTextLine textLine = this.textFormated.element1.get(0);
         final int length = textLine.getText().length();

         if(x >= length)
         {
            this.cursorPosition = length;
            return;
         }

         this.cursorPosition = x;
         return;
      }

      final int maxY = this.textFormated.element1.size() - 1;
      if(y > maxY)
      {
         this.cursorPosition = this.text.length();
         return;
      }

      int before = 0;
      for(int i = 0; i < y; i++)
      {
         before += this.textFormated.element1.get(i).getText().length();
      }

      if(x <= 0)
      {
         this.cursorPosition = before;
         return;
      }

      final JHelpTextLine textLine = this.textFormated.element1.get(y);
      final int length = textLine.getText().length();

      if(x >= length)
      {
         this.cursorPosition = before + length;
         return;
      }

      this.cursorPosition = x + before;
   }

   /**
    * Update edit text
    */
   private void updateEditText()
   {
      this.textFormated = this.font.computeTextLines(this.text.toString(), this.textAlign, this.editWidth, this.editHeight, false);

      synchronized(this.imageEdit)
      {
         this.imageEdit.startDrawMode();
         this.imageEdit.clear(0);

         for(final JHelpTextLine textLine : this.textFormated.element1)
         {
            this.imageEdit.paintMask(1 + textLine.getX(), 1 + textLine.getY(), textLine.getMask(), this.foregroundColor, 0, false);
         }

         final Point position = this.computeLocation(this.cursorPosition);
         this.imageEdit.fillRectangle(1 + position.x, 1 + position.y, this.cursorWidth, this.cursorHeight, this.cursorColor);

         this.imageEdit.drawHorizontalLine(0, this.editWidth, 0, 0xFF000000);
         this.imageEdit.drawVerticalLine(0, 0, this.editHeight, 0xFF000000);
         this.imageEdit.drawHorizontalLine(0, this.editWidth, this.editHeight - 1, 0xFFFFFFFF);
         this.imageEdit.drawVerticalLine(this.editWidth - 1, 0, this.editHeight, 0xFFFFFFFF);

         this.imageEdit.endDrawMode();
      }
   }

   /**
    * Compute edit text preferred size <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param parentWidth
    *           Parent width
    * @param parentHeight
    *           Parent height
    * @return Preffered size
    * @see jhelp.gui.twoD.JHelpComponent2D#computePreferredSize(int, int)
    */
   @Override
   protected Dimension computePreferredSize(final int parentWidth, final int parentHeight)
   {
      return new Dimension(this.editWidth, this.editHeight);
   }

   /**
    * Paint the edit text <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           X location
    * @param y
    *           Y location
    * @param parent
    *           Parent where draw
    * @see jhelp.gui.twoD.JHelpComponent2D#paint(int, int, jhelp.util.gui.JHelpImage)
    */
   @Override
   protected void paint(final int x, final int y, final JHelpImage parent)
   {
      parent.drawImage(x, y, this.imageEdit);
   }

   /**
    * Append text to current text
    * 
    * @param text
    *           Text to add
    */
   public void append(final String text)
   {
      if(text == null)
      {
         throw new NullPointerException("text musn't be null");
      }

      this.text.append(text);
      this.cursorPosition = Math.min(this.cursorPosition, this.text.length());
      this.updateEditText();
   }

   /**
    * Clear the edit text
    */
   public void clear()
   {
      this.clear(true);
   }

   /**
    * Compute cursor nearest position from a given location
    * 
    * @param x
    *           X location
    * @param y
    *           Y location
    * @return Cursor position
    */
   public int computeCursorPosition(final int x, final int y)
   {
      if(((x <= 0) && (y <= 0)) || (this.textFormated == null))
      {
         return 0;
      }

      if((x >= this.textFormated.element2.width) && (y >= this.textFormated.element2.height))
      {
         return this.text.length();
      }

      JHelpTextLine textLineOver = null;
      int size = 0;

      for(final JHelpTextLine textLine : this.textFormated.element1)
      {
         if((y >= textLine.getY()) && (y <= (textLine.getHeight() + textLine.getY())))
         {
            textLineOver = textLine;
            break;
         }

         size += textLine.getText().length();
      }

      if(textLineOver == null)
      {
         return this.text.length();
      }

      if(x <= textLineOver.getX())
      {
         return size;
      }

      final int length = textLineOver.getText().length();

      if(x >= (textLineOver.getX() + textLineOver.getWidth()))
      {
         return size + length;
      }

      int min = 0;
      int max = length;
      int pos;
      int width1;
      int width2;

      while((min + 1) < max)
      {
         pos = (max + min) >> 1;
         width1 = this.font.stringWidth(this.text.substring(size, size + pos));
         width2 = this.font.stringWidth(this.text.substring(size, size + pos + 1));

         if((x >= width1) && (x < width2))
         {
            return size + pos;
         }

         if(x < width1)
         {
            max = pos;
         }
         else
         // x>=width2
         {
            min = pos;
         }
      }

      return size + min;
   }

   /**
    * Compute cursor location from cursor position
    * 
    * @param cursorPosition
    *           Cusror position
    * @return Cursor location
    */
   public Point computeLocation(int cursorPosition)
   {
      if((this.textFormated == null) || (this.textFormated.element1.isEmpty() == true))
      {
         return new Point(0, 0);
      }

      cursorPosition = Math.max(0, Math.min(this.text.length(), cursorPosition));
      int length;
      int width;

      for(final JHelpTextLine textLine : this.textFormated.element1)
      {
         length = textLine.getText().length();
         if(cursorPosition <= length)
         {
            if(cursorPosition == 0)
            {
               width = 0;
            }
            else
            {
               width = this.font.stringWidth(textLine.getText().substring(0, cursorPosition));
            }

            return new Point(textLine.getX() + width, textLine.getY());
         }

         cursorPosition -= length;
      }

      final JHelpTextLine textLine = this.textFormated.element1.get(this.textFormated.element1.size() - 1);
      return new Point(textLine.getX() + textLine.getWidth(), textLine.getY());
   }

   /**
    * Current edit text
    * 
    * @return Current edit text
    */
   public String getText()
   {
      return this.text.toString();
   }

   /**
    * Called when key is pressed <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param keyEvent
    *           Key event
    * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
    */
   @Override
   public void keyPressed(final KeyEvent keyEvent)
   {
      final Point location = this.computeCursorCoordinate();
      final int insertPosition = Math.min(this.cursorPosition + location.y, this.text.length());
      switch(keyEvent.getKeyCode())
      {
         case KeyEvent.VK_UP:
            this.locateCursorCoordinate(location.x, location.y - 1);
            this.updateEditText();
            return;
         case KeyEvent.VK_DOWN:
            this.locateCursorCoordinate(location.x, location.y + 1);
            this.updateEditText();
            return;
         case KeyEvent.VK_LEFT:
            this.locateCursorCoordinate(location.x - 1, location.y);
            this.updateEditText();
            return;
         case KeyEvent.VK_RIGHT:
            this.locateCursorCoordinate(location.x + 1, location.y);
            this.updateEditText();
            return;
         case KeyEvent.VK_DELETE:
            if(this.cursorPosition < this.text.length())
            {
               this.text.deleteCharAt(insertPosition);
               this.updateEditText();
            }
            return;
         case KeyEvent.VK_BACK_SPACE:
            if(this.cursorPosition > 0)
            {
               this.cursorPosition--;
               this.text.deleteCharAt(insertPosition - 1);
               this.updateEditText();
            }
            return;
         case KeyEvent.VK_ENTER:
            this.text.insert(insertPosition, '\n');
            this.cursorPosition++;
            this.updateEditText();
            return;
         case KeyEvent.VK_SHIFT:
         case KeyEvent.VK_CONTROL:
         case KeyEvent.VK_ALT:
         case KeyEvent.VK_ALT_GRAPH:
            return;
         case KeyEvent.VK_TAB:
            this.text.insert(insertPosition, "   ");
            this.cursorPosition += 3;
            this.updateEditText();
            return;

      }

      final char keyChar = keyEvent.getKeyChar();

      if(keyChar >= ' ')
      {
         this.text.insert(insertPosition, keyChar);
         this.cursorPosition++;
         this.updateEditText();

         return;
      }
   }

   /**
    * Called when key released <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param keyEvent
    *           Key event
    * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
    */
   @Override
   public void keyReleased(final KeyEvent keyEvent)
   {
   }

   /**
    * Called whne key typed <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param keyEvent
    *           Key event
    * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
    */
   @Override
   public void keyTyped(final KeyEvent keyEvent)
   {
   }

   /**
    * Called when mouse button clicked <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event
    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseClicked(final MouseEvent e)
   {
      this.cursorPosition = this.computeCursorPosition(e.getX(), e.getY());
      this.updateEditText();
   }

   /**
    * Called when mouse dragged (Move with button down) <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event
    * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseDragged(final MouseEvent e)
   {
   }

   /**
    * Called when mouse entered <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event
    * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseEntered(final MouseEvent e)
   {
   }

   /**
    * Called when mouse exited <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event
    * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseExited(final MouseEvent e)
   {
   }

   /**
    * Called when mouse moved <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event
    * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseMoved(final MouseEvent e)
   {
   }

   /**
    * Called when mouse button pressed <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event
    * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
    */
   @Override
   public void mousePressed(final MouseEvent e)
   {
   }

   /**
    * Called when mouse button released <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event
    * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseReleased(final MouseEvent e)
   {
   }

   /**
    * Called when mouse wheel moved <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Mouse event
    * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
    */
   @Override
   public void mouseWheelMoved(final MouseWheelEvent e)
   {
   }

   /**
    * Change edit text text
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

      this.clear(false);
      this.append(text);
   }
}