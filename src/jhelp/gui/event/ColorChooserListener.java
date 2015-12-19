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