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

import jhelp.gui.smooth.JHelpListSmooth;

/**
 * Listener of selection in {@link JHelpListSmooth}
 * 
 * @author JHelp
 * @param <ELEMENT>
 *           List element type
 */
public interface JHelpListSmoothSelectListener<ELEMENT>
{
   /**
    * Called when a item is selected inside {@link JHelpListSmooth}
    * 
    * @param list
    *           {@link JHelpListSmooth} where selection done
    * @param element
    *           Selected element
    * @param index
    *           Selected index
    */
   public void elementSelected(JHelpListSmooth<ELEMENT> list, ELEMENT element, int index);
}