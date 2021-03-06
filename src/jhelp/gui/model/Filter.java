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
package jhelp.gui.model;

/**
 * Filter on elements
 *
 * @author JHelp <br>
 * @param <OBJECT>
 *           Type of filtered elements
 */
public interface Filter<OBJECT>
{
   /**
    * Indicates if element is filtered
    *
    * @param object
    *           Element tested
    * @return {@code true} if element is filtered
    */
   public boolean isFiltered(OBJECT object);

   /**
    * Register listener to filter changes.<br>
    * Change means the list of accepted elements have changed
    *
    * @param listener
    *           Listener to register
    */
   public void registerFilterListener(FilterListener<OBJECT> listener);

   /**
    * Unregister a listener from listener changes
    *
    * @param listener
    *           Listener to unregister
    */
   public void unregisterFilterListener(FilterListener<OBJECT> listener);
}