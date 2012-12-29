/**
 * Project : JHelpGUI<br>
 * Package : jhelp.gui.undoRedo<br>
 * Class : UndoRedoAction<br>
 * Date : 4 fevr. 2009<br>
 * By JHelp
 */
package jhelp.gui.undoRedo;

/**
 * Action can be Undo and redo <br>
 * <br>
 * Last modification : 4 fevr. 2009<br>
 * Version 0.0.0<br>
 * 
 * @author JHelp
 */
public interface UndoRedoAction
{
   /**
    * Redo the action
    */
   public void redo();

   /**
    * Undo the action
    */
   public void undo();

   /**
    * Try compress to one undo/redo action.<br>
    * If the compression fail, {@code null} is return.<br>
    * This is call when we reach the undo/redo limit. Before discard first action if we can't gain more place on compression
    * 
    * @param nextUndoRedo
    *           Next action try to compress with
    * @return A compress undo/redo or {@code null} if the two actions can't be compress together
    */
   public UndoRedoAction compress(UndoRedoAction nextUndoRedo);
}