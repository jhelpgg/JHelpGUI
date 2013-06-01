package jhelp.gui.twoD;

/**
 * Liostener of tree model changes
 * 
 * @author JHelp
 * @param <INFORMATION>
 *           Information type
 */
public interface JHelpTreeModelListener<INFORMATION>
{
   /**
    * Called when tree model changed
    * 
    * @param treeModel
    *           Tree model changed
    */
   public void treeModelChanged(JHelpTreeModel<INFORMATION> treeModel);
}