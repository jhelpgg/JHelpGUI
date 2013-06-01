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