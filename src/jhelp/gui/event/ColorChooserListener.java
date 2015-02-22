package jhelp.gui.event;

import jhelp.gui.ColorChooser;

/**
 * Listener of user action in {@link ColorChooser}
 * 
 * @author JHelp
 */
public interface ColorChooserListener
{
   /**
    * Called when user cancel the {@link ColorChooser}
    * 
    * @param colorChooser
    *           Color chooser canceled
    */
   public void colorChooseCanceled(ColorChooser colorChooser);

   /**
    * Called when user choose a color
    * 
    * @param colorChooser
    *           Color chooser source
    * @param color
    *           Chosen color
    */
   public void colorChoosed(ColorChooser colorChooser, int color);
}