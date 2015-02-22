package jhelp.gui.twoD;

/**
 * Check box listener
 * 
 * @author JHelp
 */
public interface CheckBox2DListener
{
   /**
    * Called when check box status changed
    * 
    * @param checkbox2d
    *           Check box where event happen
    * @param checked
    *           Indicates new check box status
    */
   public void checkBoxCheckChange(JHelpCheckbox2D checkbox2d, boolean checked);
}