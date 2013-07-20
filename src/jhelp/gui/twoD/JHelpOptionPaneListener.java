package jhelp.gui.twoD;

/**
 * Listener of option pane user response.<br>
 * If the user click on yes or ok when the dialog is for type some text, then it
 * {@link #optionPaneTextTyped(int, Object, String)} called
 * 
 * @author JHelp
 */
public interface JHelpOptionPaneListener
{
   /**
    * Called when user click on cancel button
    * 
    * @param actionID
    *           Action ID given when option pane was shown
    * @param developerInformation
    *           Object given when option pane was shown
    */
   public void optionPaneCancel(int actionID, Object developerInformation);

   /**
    * Called when user click on no button
    * 
    * @param actionID
    *           Action ID given when option pane was shown
    * @param developerInformation
    *           Object given when option pane was shown
    */
   public void optionPaneNo(int actionID, Object developerInformation);

   /**
    * Called when user click on yes or ok button and theire input text
    * 
    * @param actionID
    *           Action ID given when option pane was shown
    * @param developerInformation
    *           Object given when option pane was shown
    * @param text
    *           Typed text
    */
   public void optionPaneTextTyped(int actionID, Object developerInformation, String text);

   /**
    * Called when user click on tes or ok button and theire are no input text
    * 
    * @param actionID
    *           Action ID given when option pane was shown
    * @param developerInformation
    *           Object given when option pane was shown
    */
   public void optionPaneYesOk(int actionID, Object developerInformation);
}