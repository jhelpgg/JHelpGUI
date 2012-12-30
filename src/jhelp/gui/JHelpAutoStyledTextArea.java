/**
 * Project : JHelpGUI<br>
 * Package : jhelp.gui<br>
 * Class : JHelpAutoStyledTextArea<br>
 * Date : 23 sept. 2010<br>
 * By JHelp
 */
package jhelp.gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.JEditorPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedVerySimpleTask;

/**
 * Text area with automatic styling while typing text.<br>
 * It detects each words. If a word have an associated style, we use the associated style else the default style.<br>
 * Symbols <code>[](){}&|\"'-@=/\*+.,?;:!<></code> can have also their own style<br>
 * You have first register a style with {@link #createStyle(String, String, int, boolean, boolean, boolean, Color, Color)} then
 * associate words with {@link #associate(String, String...)}, {@link #setSymbolStyle(String)} (for symbol) or
 * {@link #setDefaultStyle(String)} (for change default style) <br>
 * The key short cut format is actually hard coded to Ctrl+Shift+F<br>
 * Last modification : 23 sept. 2010<br>
 * Version 0.0.0<br>
 * 
 * @author JHelp
 */
public class JHelpAutoStyledTextArea
      extends JEditorPane
{
   /**
    * Manage text area events <br>
    * <br>
    * Last modification : 15 janv. 2011<br>
    * Version 0.0.0<br>
    * 
    * @author JHelp
    */
   class EventManager
         implements KeyListener
   {
      /**
       * Call if key press
       * 
       * @param e
       *           Event description
       * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
       */
      @Override
      public void keyPressed(final KeyEvent e)
      {
         if((e.getKeyCode() == KeyEvent.VK_F) && (e.isControlDown() == true) && (e.isShiftDown() == true))
         {
            ThreadManager.THREAD_MANAGER.delayedThread(JHelpAutoStyledTextArea.this.refreshFormat, null, 123L);
         }
      }

      /**
       * Call if key release
       * 
       * @param e
       *           Event description
       * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
       */
      @Override
      public void keyReleased(final KeyEvent e)
      {
         // Nothing to do
      }

      /**
       * Call if key type
       * 
       * @param e
       *           Event description
       * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
       */
      @Override
      public void keyTyped(final KeyEvent e)
      {
         // Nothing to do
      }
   }

   /**
    * Refresh format background thread <br>
    * <br>
    * Last modification : 15 janv. 2011<br>
    * Version 0.0.0<br>
    * 
    * @author JHelp
    */
   class RefreshFormat
         extends ThreadedVerySimpleTask
   {
      /**
       * Refresh the formating <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @see jhelp.util.thread.ThreadedVerySimpleTask#doVerySimpleAction()
       */
      @Override
      protected void doVerySimpleAction()
      {
         final String text = JHelpAutoStyledTextArea.this.getText();
         int length = text.length();
         final StringBuilder stringBuilder = new StringBuilder(length + (length / 10) + 1);

         final StringTokenizer stringTokenizer = new StringTokenizer(text, "\n", true);
         String line;
         int start;
         int tabCount = 0;
         int index1, index2, index;
         char character;
         int read = 0;
         int end = 0;

         while(stringTokenizer.hasMoreTokens() == true)
         {
            read = end;
            line = stringTokenizer.nextToken();
            end += line.length();

            line = line.trim();

            start = 0;
            length = line.length();

            if(length > 0)
            {
               index1 = line.indexOf('{');
               index2 = line.indexOf('}');
               character = ' ';

               while((index1 >= 0) || (index2 >= 0))
               {
                  index = -1;
                  if((index1 >= 0) && (index2 >= 0))
                  {
                     index = Math.min(index1, index2);
                  }
                  else if(index1 >= 0)
                  {
                     index = index1;
                  }
                  else
                  {
                     index = index2;
                  }

                  character = line.charAt(index);

                  if(start < index)
                  {
                     for(int i = 0; i < tabCount; i++)
                     {
                        stringBuilder.append("   ");
                     }

                     stringBuilder.append(line.substring(start, index));
                     stringBuilder.append('\n');
                  }

                  if(character == '}')
                  {
                     tabCount--;
                  }

                  for(int i = 0; i < tabCount; i++)
                  {
                     stringBuilder.append("   ");
                  }
                  stringBuilder.append(character);

                  if((index + 1) < length)
                  {
                     stringBuilder.append('\n');
                  }

                  if(character == '{')
                  {
                     tabCount++;
                  }

                  start = index + 1;

                  index1 = line.indexOf('{', start);
                  index2 = line.indexOf('}', start);
               }

               for(int i = 0; i < tabCount; i++)
               {
                  stringBuilder.append("   ");
               }

               stringBuilder.append(line.substring(start));

               if(stringTokenizer.hasMoreElements() == true)
               {
                  stringBuilder.append('\n');
               }
            }
            else if((read > 0) && (text.charAt(read - 1) == '\n'))
            {
               stringBuilder.append('\n');
            }
         }

         final int carret = JHelpAutoStyledTextArea.this.getCaretPosition();

         JHelpAutoStyledTextArea.this.setText(stringBuilder.toString());

         JHelpAutoStyledTextArea.this.setCaretPosition((carret * stringBuilder.length()) / text.length());
      }
   }

   /**
    * Refresh style background thread <br>
    * <br>
    * Last modification : 15 janv. 2011<br>
    * Version 0.0.0<br>
    * 
    * @author JHelp
    */
   class RefreshStyle
         extends ThreadedVerySimpleTask
   {
      /**
       * Refresh the style <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @see jhelp.util.thread.ThreadedVerySimpleTask#doVerySimpleAction()
       */
      @Override
      protected void doVerySimpleAction()
      {
         final StringTokenizer stringTokenizer = new StringTokenizer(JHelpAutoStyledTextArea.this.getText(), JHelpAutoStyledTextArea.CUT_STRINGS, true);
         int start = 0;
         int end;
         String styleName;
         Style style;
         String word;
         char car;
         boolean lineReturn;

         while(stringTokenizer.hasMoreElements() == true)
         {
            lineReturn = false;

            word = stringTokenizer.nextToken();
            end = start + word.length();
            styleName = JHelpAutoStyledTextArea.this.defaultStyle;

            if(word.length() == 1)
            {
               car = word.charAt(0);

               if(JHelpAutoStyledTextArea.isSymbol(car) == true)
               {
                  styleName = JHelpAutoStyledTextArea.this.symbolStyle;

                  if(styleName == null)
                  {
                     styleName = JHelpAutoStyledTextArea.this.defaultStyle;
                  }
               }
               else if(car == '\n')
               {
                  lineReturn = true;
               }
               else
               {
                  styleName = JHelpAutoStyledTextArea.this.associatedStyle.get(word);

                  if(styleName == null)
                  {
                     styleName = JHelpAutoStyledTextArea.this.defaultStyle;
                  }
               }
            }
            else
            {
               styleName = JHelpAutoStyledTextArea.this.associatedStyle.get(word);

               if(styleName == null)
               {
                  styleName = JHelpAutoStyledTextArea.this.defaultStyle;
               }
            }

            if(styleName == null)
            {
               styleName = JHelpAutoStyledTextArea.DEFAULT_STYLE;
            }

            if(lineReturn == false)
            {
               style = JHelpAutoStyledTextArea.this.autoStyledDocument.getStyle(styleName);
               JHelpAutoStyledTextArea.this.autoStyledDocument.setCharacterAttributes(start, end - start, style, true);
            }

            start = end;
         }

         JHelpAutoStyledTextArea.this.invalidate();
         JHelpAutoStyledTextArea.this.repaint();
      }
   }

   /** Word separator list */
   private static final String CUT_STRINGS      = JHelpAutoStyledTextArea.SYMBOLS + " \n\r\t";

   /**
	 * 
	 */
   private static final long   serialVersionUID = -6141838702983157203L;

   /** Symbols list */
   private static final String SYMBOLS          = "[](){}&|\"'-@=/\\*+.,?;:!<>";

   /** Default name of default style */
   public static final String  DEFAULT_STYLE    = "jhelp.DEFAULT_STYLE";

   /**
    * Indicates if a character is separator
    * 
    * @param character
    *           Tested character
    * @return {@code true} if it is symbol
    */
   static boolean isSymbol(final char character)
   {
      return JHelpAutoStyledTextArea.SYMBOLS.indexOf(character) >= 0;
   }

   /** Editor kit linked */
   private final StyledEditorKit autoStyledEditorKit;
   /** Style association map */
   HashMap<String, String>       associatedStyle;
   /** Document to add style */
   DefaultStyledDocument         autoStyledDocument;
   /** Default style name */
   String                        defaultStyle;
   /** Automatic refresh */
   RefreshFormat                 refreshFormat;
   /** Symbol style name */
   String                        symbolStyle;

   /**
    * Constructs JHelpAutoStyledTextArea
    */
   public JHelpAutoStyledTextArea()
   {
      this.autoStyledEditorKit = new StyledEditorKit();
      this.setEditorKit(this.autoStyledEditorKit);

      this.autoStyledDocument = (DefaultStyledDocument) this.getDocument();

      this.createStyle(JHelpAutoStyledTextArea.DEFAULT_STYLE, "Arial", 12, false, false, false, Color.BLACK, Color.WHITE);

      this.associatedStyle = new HashMap<String, String>();
      this.defaultStyle = JHelpAutoStyledTextArea.DEFAULT_STYLE;
      this.symbolStyle = JHelpAutoStyledTextArea.DEFAULT_STYLE;

      this.setBackground(Color.WHITE);
      this.setForeground(Color.BLACK);

      ThreadManager.THREAD_MANAGER.repeatThread(new RefreshStyle(), null, 1234L, 1234L);

      this.refreshFormat = new RefreshFormat();

      this.addKeyListener(new EventManager());
   }

   /**
    * Associated a style to words
    * 
    * @param styleName
    *           Style name
    * @param keyWords
    *           Words list
    */
   public void associate(final String styleName, final String... keyWords)
   {
      final Style style = this.autoStyledDocument.getStyle(styleName);
      if(style == null)
      {
         throw new IllegalArgumentException("The style " + styleName + " doesn't exists !");
      }

      for(final String keyWord : keyWords)
      {
         this.associatedStyle.put(keyWord, styleName);
      }
   }

   /**
    * Create a style
    * 
    * @param name
    *           Style name
    * @param fontFamily
    *           Font family
    * @param fontSize
    *           Font size
    * @param bold
    *           Indicates if bold
    * @param italic
    *           Indicates if italic
    * @param underline
    *           Indicates if underline
    * @param foreground
    *           Foreground color
    * @param background
    *           Background color
    */
   public void createStyle(final String name, final String fontFamily, final int fontSize, final boolean bold, final boolean italic, final boolean underline, final Color foreground, final Color background)
   {
      if(name == null)
      {
         throw new NullPointerException("name musn't be null");
      }

      if(fontFamily == null)
      {
         throw new NullPointerException("fontFamily musn't be null");
      }

      if(foreground == null)
      {
         throw new NullPointerException("foreground musn't be null");
      }

      if(background == null)
      {
         throw new NullPointerException("background musn't be null");
      }

      final Style style = this.autoStyledDocument.addStyle(name, null);

      StyleConstants.setFontFamily(style, fontFamily);
      StyleConstants.setFontSize(style, fontSize);
      StyleConstants.setBold(style, bold);
      StyleConstants.setItalic(style, italic);
      StyleConstants.setUnderline(style, underline);
      StyleConstants.setForeground(style, foreground);
      StyleConstants.setBackground(style, background);
   }

   /**
    * Return defaultStyle
    * 
    * @return defaultStyle
    */
   public String getDefaultStyle()
   {
      return this.defaultStyle;
   }

   /**
    * Return symbolStyle
    * 
    * @return symbolStyle
    */
   public String getSymbolStyle()
   {
      return this.symbolStyle;
   }

   /**
    * Modify defaultStyle
    * 
    * @param defaultStyle
    *           New defaultStyle value
    */
   public void setDefaultStyle(final String defaultStyle)
   {
      this.defaultStyle = defaultStyle;
   }

   /**
    * Modify symbolStyle
    * 
    * @param symbolStyle
    *           New symbolStyle value
    */
   public void setSymbolStyle(final String symbolStyle)
   {
      if(symbolStyle == null)
      {
         this.symbolStyle = null;

         return;
      }

      final Style style = this.autoStyledDocument.getStyle(symbolStyle);
      if(style == null)
      {
         throw new IllegalArgumentException("The style " + symbolStyle + " doesn't exists !");
      }

      this.symbolStyle = symbolStyle;
   }
}