package jhelp.gui.smooth.event;

import jhelp.gui.smooth.model.JHelpListSmoothModel;

/**
 * Listener of {@link JHelpListSmoothModel} changes
 * 
 * @author JHelp
 * @param <ELEMENT>
 *           Element type
 */
public interface JHelpListSmoothModelListener<ELEMENT>
{
   /**
    * Called when model as completely change
    * 
    * @param model
    *           Change model
    */
   public void completlyChanged(JHelpListSmoothModel<ELEMENT> model);

   /**
    * Called when one element was inserted in the model
    * 
    * @param model
    *           Changed model
    * @param index
    *           Insert index
    */
   public void elementAdded(JHelpListSmoothModel<ELEMENT> model, int index);

   /**
    * Called when one element was removed in the model
    * 
    * @param model
    *           Changed model
    * @param index
    *           Remove index
    */
   public void elementRemoved(JHelpListSmoothModel<ELEMENT> model, int index);
}