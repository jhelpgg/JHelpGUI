package jhelp.gui.smooth.model;

import jhelp.gui.smooth.JHelpListSmooth;
import jhelp.gui.smooth.event.JHelpListSmoothModelListener;

/**
 * Model used by {@link JHelpListSmooth}.<br>
 * It give access to the list data
 * 
 * @author JHelp
 * @param <ELEMENT>
 *           Element type
 */
public interface JHelpListSmoothModel<ELEMENT>
{
   /**
    * Obtain a element
    * 
    * @param index
    *           Element index
    * @return Element
    */
   public ELEMENT getElement(int index);

   /**
    * Number of elements
    * 
    * @return Number of elements
    */
   public int getNumberOfElement();

   /**
    * Register a listener to be alert on model changes
    * 
    * @param listener
    *           Listener to register
    */
   public void registerModelListener(JHelpListSmoothModelListener<ELEMENT> listener);

   /**
    * Unregister a listener to be no more alert on model changes
    * 
    * @param listener
    *           Listener to unregister
    */
   public void unregisterModelListener(JHelpListSmoothModelListener<ELEMENT> listener);
}