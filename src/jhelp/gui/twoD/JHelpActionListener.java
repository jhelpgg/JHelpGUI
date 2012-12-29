package jhelp.gui.twoD;


/**
 * Listener for action
 * 
 * @author JHelp
 */
public interface JHelpActionListener
{
   /**
    * Called when an action happen
    * 
    * @param component2d
    *           Component where action happen
    * @param identifier
    *           Action identifier
    */
   public void actionAppend(JHelpComponent2D component2d, int identifier);
}