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