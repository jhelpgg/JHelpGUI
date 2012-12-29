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