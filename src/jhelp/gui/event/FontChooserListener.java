/**
 * Project : JHelpGUI<br>
 * Package : jhelp.gui.event<br>
 * Class : FontChosserListener<br>
 * Date : 12 mai 2009<br>
 * By JHelp
 */
package jhelp.gui.event;

import java.util.EventListener;

/**
 * Listener of choose a font change <br>
 * <br>
 * Last modification : 12 mai 2009<br>
 * Version 0.0.0<br>
 * 
 * @author JHelp
 */
public interface FontChooserListener
      extends EventListener
{
   /**
    * Call when the select font change
    * 
    * @param fontChooserEvent
    *           Event description
    */
   public void fontChanged(FontChooserEvent fontChooserEvent);
}