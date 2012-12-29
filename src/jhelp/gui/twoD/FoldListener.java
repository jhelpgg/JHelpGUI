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