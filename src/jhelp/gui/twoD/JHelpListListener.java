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
    * @param clickCount
    *           NumberOfClick
    */
   public void listSelectionChanged(JHelpList2D<INFORMATION> list2d, int selecttedIndex, INFORMATION information, int clickCount);
}