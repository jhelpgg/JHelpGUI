package jhelp.gui.twoD;

/**
 * Listener of spinner changes
 * 
 * @author JHelp
 * @param <VALUE>
 *           Value type
 */
public interface JHelpSpinner2DListener<VALUE>
{
   /**
    * Called when spinner value changed
    * 
    * @param spinner2d
    *           Spinner that value chaged
    * @param newValue
    *           New value
    */
   public void spinnerValueChanged(JHelpSpinner2D<VALUE> spinner2d, VALUE newValue);
}