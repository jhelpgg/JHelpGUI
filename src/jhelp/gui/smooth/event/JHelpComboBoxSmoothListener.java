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