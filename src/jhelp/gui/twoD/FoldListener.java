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
 * Listener of folding change in {@link JHelpFoldable2D} component
 * 
 * @author JHelp
 */
public interface FoldListener
{
   /**
    * Called when the fold state of a {@link JHelpFoldable2D} just changed
    * 
    * @param foldable2d
    *           {@link JHelpFoldable2D} source
    * @param isFold
    *           The new fold state
    */
   public void foldChanged(JHelpFoldable2D foldable2d, boolean isFold);

   /**
    * Called just before the fold state of a {@link JHelpFoldable2D} will change
    * 
    * @param foldable2d
    *           {@link JHelpFoldable2D} source
    * @param futureFold
    *           The future fold state
    */
   public void willFoldChanged(JHelpFoldable2D foldable2d, boolean futureFold);
}