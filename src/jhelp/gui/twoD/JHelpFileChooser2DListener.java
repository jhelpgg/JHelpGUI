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