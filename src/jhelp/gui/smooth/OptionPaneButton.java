package jhelp.gui.smooth;

public enum OptionPaneButton
      implements JHelpConstantsSmooth
{
   CANCEL(JHelpConstantsSmooth.TEXT_KEY_CANCEL), NO(JHelpConstantsSmooth.TEXT_KEY_NO), OK(JHelpConstantsSmooth.TEXT_KEY_OK), YES(JHelpConstantsSmooth.TEXT_KEY_YES);
   private final String keyText;

   OptionPaneButton(final String keyText)
   {
      this.keyText = keyText;
   }

   public String getKeyText()
   {
      return this.keyText;
   }
}