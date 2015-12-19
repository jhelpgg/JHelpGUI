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

/**
 * List model changed listener
 * 
 * @author JHelp
 * @param <INFORMATION>
 *           Information type
 */
public interface JHelpListModelListener<INFORMATION>
{
   /**
    * Called when list model changed
    * 
    * @param listModel
    *           List model that change
    */
   public void listModelChanged(JHelpListModel<INFORMATION> listModel);
}