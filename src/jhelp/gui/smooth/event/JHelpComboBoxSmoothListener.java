package jhelp.gui.smooth.event;

import jhelp.gui.smooth.JHelpComboBoxSmooth;

/**
 * Listener of smooth combo box events
 * 
 * @author JHelp
 */
public interface JHelpComboBoxSmoothListener
{
   /**
    * Called when combo box choice changed
    * 
    * @param comboBox
    *           Combo box changed
    * @param choice
    *           New choice
    */
   public void comboBoxSmoothValueChanged(JHelpComboBoxSmooth comboBox, String choice);
}