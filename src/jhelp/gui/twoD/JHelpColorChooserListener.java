package jhelp.gui.twoD;

/**
 * Listener of color choose
 * 
 * @author JHelp
 */
public interface JHelpColorChooserListener
{
   /**
    * Called when color changed
    * 
    * @param colorChooser
    *           Color chooser source
    * @param color
    *           New color
    */
   public void colorChanged(JHelpColorChooser colorChooser, int color);
}