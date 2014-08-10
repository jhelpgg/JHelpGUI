package jhelp.gui.smooth.model;

import java.util.ArrayList;
import java.util.List;

import jhelp.gui.smooth.event.JHelpListSmoothModelListener;

/**
 * Abstract list model that manage model change listeners.<br>
 * For signal changes just call the good fire ({@link #fireAllChange()}, {@link #fireElementAdded(int)} or
 * {@link #fireElementRemoved(int)}) at good time.
 * 
 * @author JHelp
 * @param <ELEMENT>
 *           Element type
 */
public abstract class JHelpListSmoothModelAbstract<ELEMENT>
      implements JHelpListSmoothModel<ELEMENT>
{
   /** Listeners to alert */
   private final List<JHelpListSmoothModelListener<ELEMENT>> listeners;

   /**
    * Create a new instance of JHelpListSmoothModelAbstract
    */
   public JHelpListSmoothModelAbstract()
   {
      this.listeners = new ArrayList<JHelpListSmoothModelListener<ELEMENT>>();
   }

   /**
    * Signal to listeners that all model changed
    */
   protected final void fireAllChange()
   {
      for(final JHelpListSmoothModelListener<ELEMENT> listener : this.listeners)
      {
         listener.completlyChanged(this);
      }
   }

   /**
    * Signal to listeners that one element is add
    * 
    * @param index
    *           Element add index
    */
   protected final void fireElementAdded(final int index)
   {
      for(final JHelpListSmoothModelListener<ELEMENT> listener : this.listeners)
      {
         listener.elementAdded(this, index);
      }
   }

   /**
    * Signal to listeners that one element removed
    * 
    * @param index
    *           Element removed index
    */
   protected final void fireElementRemoved(final int index)
   {
      for(final JHelpListSmoothModelListener<ELEMENT> listener : this.listeners)
      {
         listener.elementRemoved(this, index);
      }
   }

   /**
    * Register a listener to know model changes <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param listener
    *           Listener to register
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#registerModelListener(jhelp.gui.smooth.event.JHelpListSmoothModelListener)
    */
   @Override
   public final void registerModelListener(final JHelpListSmoothModelListener<ELEMENT> listener)
   {
      if((listener == null) || (this.listeners.contains(listener) == true))
      {
         return;
      }

      this.listeners.add(listener);
   }

   /**
    * Unregister a listener to no more be alert on model change <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param listener
    *           Listener to unregister
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#unregisterModelListener(jhelp.gui.smooth.event.JHelpListSmoothModelListener)
    */
   @Override
   public final void unregisterModelListener(final JHelpListSmoothModelListener<ELEMENT> listener)
   {
      this.listeners.remove(listener);
   }
}