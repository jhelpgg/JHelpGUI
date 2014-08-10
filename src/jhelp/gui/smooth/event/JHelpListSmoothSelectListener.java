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