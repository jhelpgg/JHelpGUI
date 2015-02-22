package jhelp.gui.smooth.event;

import jhelp.gui.smooth.JHelpOptionPaneSmooth;
import jhelp.gui.smooth.OptionPaneButton;

/**
 * Listener of user action in {@link JHelpOptionPaneSmooth}
 * 
 * @author JHelp
 */
public interface JHelpOptionPaneSmoothListener
{
   /**
    * Called when user press a button in {@link JHelpOptionPaneSmooth}
    * 
    * @param optionPane
    *           Option pane source
    * @param button
    *           Button pressed
    */
   public void optionPaneButtonClicked(JHelpOptionPaneSmooth optionPane, OptionPaneButton button);
}