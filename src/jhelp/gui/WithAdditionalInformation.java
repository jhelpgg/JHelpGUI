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
package jhelp.gui;

/**
 * Object with additional customizable information
 * 
 * @author JHelp
 */
public interface WithAdditionalInformation
{
   /**
    * Get additional information TODO Explains what does the method getAdditionalInformation in jhelp.gui [JHelpGUI]
    * 
    * @return Information
    */
   public Object getAdditionalInformation();

   /**
    * Change additional information TODO Explains what does the method setAdditionalInformation in jhelp.gui [JHelpGUI]
    * 
    * @param information
    *           New information
    */
   public void setAdditionalInformation(Object information);
}