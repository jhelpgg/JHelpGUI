/**
 * <h1>License :</h1> <br>
 * The following code is deliver as is. I take care that code compile and work, but I am not responsible about any damage it may
 * cause.<br>
 * You can use, modify, the code as your need for any usage. But you can't do any action that avoid me or other person use,
 * modify this code. The code is free for usage and modification, you can't change that fact.<br>
 * <br>
 * 
 * @author JHelp
 */
package jhelp.gui.samples.swing.fontchooser;

import jhelp.gui.FontChooser;
import jhelp.util.gui.UtilGUI;

public class FontChooserSample
{

   /**
    * TODO Explains what does the method main in jhelp.gui.samples.swing.fontchooser [JHelpGUI]
    * 
    * @param args
    */
   public static void main(final String[] args)
   {
      UtilGUI.initializeGUI();
      FontChooser.showFontChooserDialog(null, null);
   }
}