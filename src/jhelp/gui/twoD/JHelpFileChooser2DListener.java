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
package jhelp.gui.twoD;

import java.io.File;

/**
 * File chooser listener
 * 
 * @author JHelp
 */
public interface JHelpFileChooser2DListener
{
   /**
    * Called when file choose is canceled
    */
   public void cancelChoose();

   /**
    * Called when file is choose
    * 
    * @param file
    *           Choosen file
    */
   public void fileChoose(File file);
}