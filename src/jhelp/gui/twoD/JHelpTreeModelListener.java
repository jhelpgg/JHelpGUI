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