package jhelp.gui.twoD;

/**
 * Listener of list changed
 * 
 * @author JHelp
 * @param <INFORMATION>
 *           Information type
 */
public interface JHelpListListener<INFORMATION>
{
   /**
    * Called when list element selected
    * 
    * @param list2d
    *           List where selectio changed
    * @param selecttedIndex
    *           New selected value (-1 means that nothing is selected)
    * @param information
    *           Selected information (If nothing is selected, {@code null})
    */
   public void listSelectionChanged(JHelpList2D<INFORMATION> list2d, int selecttedIndex, INFORMATION information);
}