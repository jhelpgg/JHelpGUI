package jhelp.gui.samples.swing.colorchooser;

import jhelp.gui.samples.swing.colorchooser.gui.ColorChooserFrame;
import jhelp.util.gui.UtilGUI;

/**
 * Color chooser
 * 
 * @author JHelp
 */
public class ColorChooser
{
   /**
    * Launch the color chooser
    * 
    * @param args
    *           Unused
    */
   public static void main(final String[] args)
   {
      UtilGUI.initializeGUI();

      final ColorChooserFrame colorChooserFrame = new ColorChooserFrame();
      colorChooserFrame.setVisible(true);
   }
}