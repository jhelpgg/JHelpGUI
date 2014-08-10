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
    * Try compress to one undo/redo action.<br>
    * The compression result <b>MUST</b> act as we played this and given actions. For undo : undo given one then undo this. For
    * redo : redo this and redo given<br>
    * Use a list, or couple for solve compression is a bad idea, the aim of compression is to free memory<br>
    * An example of good compression : imagine an action that change text color of a component green to blue, then an other one
    * blue to red for same component, then compression is just action change color text of the component green to red.<br>
    * If the compression not possible, {@code null} is return.<br>
    * This is call when we reach the undo/redo limit. Before discard first action if we can't gain more place on compression
    * 
    * @param nextUndoRedo
    *           Next action try to compress with
    * @return A compress undo/redo or {@code null} if the two actions can't be compress together
    */
   public UndoRedoAction compress(UndoRedoAction nextUndoRedo);

   /**
    * Redo the action
    */
   public void redo();

   /**
    * Undo the action
    */
   public void undo();
}