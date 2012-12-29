package jhelp.gui.game;


/**
 * Received the captured key code in {@link JHelpGame2D}
 * 
 * @author JHelp
 */
public interface CaptureKeyCodeListener
{
   /**
    * Indicates if the listener can loose focus. That is to say there are no more key to capture
    * 
    * @return {@code true} if the listener can loose focus
    */
   public boolean canLooseFocus();

   /**
    * Called when the key code is captured
    * 
    * @param keycode
    *           Captured key code
    */
   public void capturedkeyCode(int keycode);
}