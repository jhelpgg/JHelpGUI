package jhelp.gui.smooth;

/**
 * Button on option pane
 * 
 * @author JHelp
 */
public enum OptionPaneButton
      implements JHelpConstantsSmooth
{
   /** Cancel button */
   CANCEL(JHelpConstantsSmooth.TEXT_KEY_CANCEL),
   /** No button */
   NO(JHelpConstantsSmooth.TEXT_KEY_NO),
   /** Ok button */
   OK(JHelpConstantsSmooth.TEXT_KEY_OK),
   /** Yes button */
   YES(JHelpConstantsSmooth.TEXT_KEY_YES);
   /** Button's text key */
   private final String keyText;

   /**
    * Create a new instance of OptionPaneButton
    * 
    * @param keyText
    *           Button's text key
    */
   OptionPaneButton(final String keyText)
   {
      this.keyText = keyText;
   }

   /**
    * Button's text key
    * 
    * @return Button's text key
    */
   public String getKeyText()
   {
      return this.keyText;
   }
}