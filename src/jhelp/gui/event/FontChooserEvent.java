/**
 * Project : JHelpGUI<br>
 * Package : jhelp.gui.event<br>
 * Class : FontChooserEvent<br>
 * Date : 12 mai 2009<br>
 * By JHelp
 */
package jhelp.gui.event;

import java.awt.AWTEvent;

/**
 * Event on font changing<br>
 * <br>
 * Last modification : 12 mai 2009<br>
 * Version 0.0.0<br>
 * 
 * @author JHelp
 */
public class FontChooserEvent
      extends AWTEvent
{
   /** Font changed ID */
   public static final int FONT_CHANGED = AWTEvent.RESERVED_ID_MAX + 1;

   /**
    * Constructs FontChooserEvent
    * 
    * @param source
    *           Source of change
    * @param id
    *           Event ID
    */
   public FontChooserEvent(Object source, int id)
   {
      super(source, id);
   }
}