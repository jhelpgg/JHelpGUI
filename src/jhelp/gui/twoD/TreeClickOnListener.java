package jhelp.gui.twoD;

/**
 * Listener to know when user click on element tree
 * 
 * @author JHelp
 * @param <INFORMATION>
 *           Information type
 */
public interface TreeClickOnListener<INFORMATION>
{
   /**
    * Called when user click on element tree
    * 
    * @param information
    *           Information carry on clicked element
    */
   public void clickOn(INFORMATION information);
}