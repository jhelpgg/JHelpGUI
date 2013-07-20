package jhelp.gui.twoD;

import java.io.File;

/**
 * Listener of file explorer
 * 
 * @author JHelp
 */
public interface JHelpFileExplorerListener
{
   /**
    * Called when file is choosen
    * 
    * @param fileExplorer2D
    *           File explorer source
    * @param selectedFile
    *           Choosen file
    * @param selectedIndex
    *           Choosen index
    */
   public void fileExplorerFileChoosen(JHelpFileExplorer2D fileExplorer2D, File selectedFile, int selectedIndex);

   /**
    * Called when file selection changed
    * 
    * @param fileExplorer2D
    *           File explorer source
    * @param selectedFile
    *           Selected file or {@code null} if selection is removed
    * @param selectedIndex
    *           Selected index or -1 if selection is removed
    */
   public void fileExplorerSelectionChange(JHelpFileExplorer2D fileExplorer2D, File selectedFile, int selectedIndex);
}