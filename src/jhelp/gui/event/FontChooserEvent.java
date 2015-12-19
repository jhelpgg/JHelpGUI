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
   public FontChooserEvent(final Object source, final int id)
   {
      super(source, id);
   }
}