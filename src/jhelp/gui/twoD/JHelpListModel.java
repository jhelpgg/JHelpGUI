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
package jhelp.gui.twoD;

import java.awt.Dimension;
import java.util.ArrayList;

import jhelp.util.gui.JHelpImage;
import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedSimpleTask;

/**
 * List model
 * 
 * @author JHelp
 * @param <INFORMATION>
 *           Information type
 */
public abstract class JHelpListModel<INFORMATION>
{
   /** List model change listener */
   private final ArrayList<JHelpListModelListener<INFORMATION>>          listeners;
   /** Task for signal that model changed */
   private final ThreadedSimpleTask<JHelpListModelListener<INFORMATION>> taskFireModelChanged = new ThreadedSimpleTask<JHelpListModelListener<INFORMATION>>()
                                                                                              {
                                                                                                 /**
                                                                                                  * Signal that model changed <br>
                                                                                                  * <br>
                                                                                                  * <b>Parent documentation:</b><br>
                                                                                                  * {@inheritDoc}
                                                                                                  * 
                                                                                                  * @param parameter
                                                                                                  *           Listener to alert
                                                                                                  * @see jhelp.util.thread.ThreadedSimpleTask#doSimpleAction(java.lang.Object)
                                                                                                  */
                                                                                                 @Override
                                                                                                 protected void doSimpleAction(
                                                                                                       final JHelpListModelListener<INFORMATION> parameter)
                                                                                                 {
                                                                                                    parameter.listModelChanged(JHelpListModel.this);
                                                                                                 }
                                                                                              };

   /**
    * Create a new instance of JHelpListModel
    */
   public JHelpListModel()
   {
      this.listeners = new ArrayList<JHelpListModelListener<INFORMATION>>();
   }

   /**
    * Signal to listeners that model changed
    */
   protected final synchronized void fireModelChanged()
   {
      for(final JHelpListModelListener<INFORMATION> listener : this.listeners)
      {
         ThreadManager.THREAD_MANAGER.doThread(this.taskFireModelChanged, listener);
      }
   }

   /**
    * Force the model to refresh
    */
   public void forceRefresh()
   {
      this.fireModelChanged();
   }

   /**
    * List item size.<br>
    * If need to create the item itself before know the size, just return {@code null}.<br>
    * Return {@code null} by default
    * 
    * @param information
    *           Information to get its size
    * @return List item size, if known without create item itself
    */
   public Dimension getCellSize(final INFORMATION information)
   {
      return null;
   }

   /**
    * Obtain a list element
    * 
    * @param index
    *           Element index
    * @return Element asked
    */
   public abstract INFORMATION getElement(int index);

   /**
    * Obtain item tooltip.<br>
    * Return {@code null} if no tooltip
    * 
    * @param information
    *           Item information
    * @return Associated tooltip
    */
   public abstract String getToolTip(INFORMATION information);

   /**
    * Number of elements
    * 
    * @return Number of elements
    */
   public abstract int numberOfElement();

   /**
    * Image representation of information.<br>
    * Use {@code null} if no image representation
    * 
    * @param information
    *           Information
    * @return Image representation or {@code null} if no image representation
    */
   public abstract JHelpImage obtainImageRepresentation(INFORMATION information);

   /**
    * Text representation of information.<br>
    * Use {@code null} if no text representation
    * 
    * @param information
    *           Information
    * @return Text representation or {@code null} if no text representation
    */
   public abstract String obtainTextRepresentation(INFORMATION information);

   /**
    * Register listener of model changed
    * 
    * @param listener
    *           Listener to register
    */
   public final synchronized void registerJHelpListModelListener(final JHelpListModelListener<INFORMATION> listener)
   {
      if(listener == null)
      {
         throw new NullPointerException("listener musn't be null");
      }

      this.listeners.add(listener);
   }

   /**
    * Unregister to model changed
    * 
    * @param listener
    *           Listener to unregister
    */
   public final synchronized void unregisterJHelpListModelListener(final JHelpListModelListener<INFORMATION> listener)
   {
      this.listeners.add(listener);
   }

   /**
    * Indicates if image representation is to use for an information
    * 
    * @param information
    *           Information
    * @return {@code true} if use image for information
    */
   public abstract boolean useImageRepresentation(INFORMATION information);
}