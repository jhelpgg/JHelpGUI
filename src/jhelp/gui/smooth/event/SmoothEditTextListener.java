package jhelp.gui.smooth.event;

import jhelp.gui.smooth.JHelpEditTextSmooth;

/**
 * Listener of text validation by "ENTER" key in {@link JHelpEditTextSmooth}
 * 
 * @author JHelp
 */
public interface SmoothEditTextListener
{
   /**
    * Called when "ENTER" is pressed in {@link JHelpEditTextSmooth}
    * 
    * @param editTextSmooth
    *           {@link JHelpEditTextSmooth} where "ENTER" is pressed
    */
   public void editTextEnterTyped(JHelpEditTextSmooth editTextSmooth);
}