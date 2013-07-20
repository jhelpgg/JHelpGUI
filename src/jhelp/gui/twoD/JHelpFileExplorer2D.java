package jhelp.gui.twoD;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jhelp.gui.ResourcesGUI;
import jhelp.util.debug.Debug;
import jhelp.util.filter.FileFilter;
import jhelp.util.io.UtilIO;
import jhelp.util.list.Pair;
import jhelp.util.list.Triplet;
import jhelp.util.text.UtilText;
import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedSimpleTask;

/**
 * File explorer.<br>
 * Its a simple list of file with additional behavior :
 * <ul>
 * <li><b>F2</b> Rename selected file/directory</li>
 * <li><b>F3</b> Create new directory</li>
 * <li><b>F4</b> go in parent directory</li>
 * <li><b>Suppr/Delete</b> Delete selected file/directory</li>
 * </ul>
 * 
 * @author JHelp
 */
public class JHelpFileExplorer2D
      extends JHelpList2D<File>
      implements JHelpListListener<File>, JHelpOptionPaneListener
{
   /** Dialog for change file name, parameter {@link File} to rename */
   private static final int                                                                                              DIALOG_CHANGE_NAME      = 1;
   /** Dialog for delete a file, parameter {@link File} to delete */
   private static final int                                                                                              DIALOG_CONFIRM_DELETE   = 2;

   /** Dailog for confirm overwrite while rename, parameter {@link Pair}({@link File source}, {@link File destination}) */
   private static final int                                                                                              DIALOG_CONFIRM_RENAME   = 3;

   /** Dialog for create directory, no parameter */
   private static final int                                                                                              DIALOG_CREATE_DIRECTORY = 4;
   /** Task signal to a lister that file is choosen */
   private static final ThreadedSimpleTask<Triplet<JHelpFileExplorer2D, JHelpFileExplorerListener, Pair<File, Integer>>> TASK_FILE_CHOOSEN       = new ThreadedSimpleTask<Triplet<JHelpFileExplorer2D, JHelpFileExplorerListener, Pair<File, Integer>>>()
                                                                                                                                                 {
                                                                                                                                                    /**
                                                                                                                                                     * Signal
                                                                                                                                                     * to
                                                                                                                                                     * listener
                                                                                                                                                     * that
                                                                                                                                                     * a
                                                                                                                                                     * file
                                                                                                                                                     * is
                                                                                                                                                     * choose
                                                                                                                                                     * <br>
                                                                                                                                                     * <br>
                                                                                                                                                     * <
                                                                                                                                                     * b
                                                                                                                                                     * >
                                                                                                                                                     * Parent
                                                                                                                                                     * documentation
                                                                                                                                                     * :
                                                                                                                                                     * <
                                                                                                                                                     * /
                                                                                                                                                     * b
                                                                                                                                                     * >
                                                                                                                                                     * <br>
                                                                                                                                                     * {@inheritDoc}
                                                                                                                                                     * 
                                                                                                                                                     * @param parameter
                                                                                                                                                     *           [
                                                                                                                                                     *           File
                                                                                                                                                     *           explorer
                                                                                                                                                     *           source
                                                                                                                                                     *           ,
                                                                                                                                                     *           Listener
                                                                                                                                                     *           to
                                                                                                                                                     *           alert
                                                                                                                                                     *           ,
                                                                                                                                                     *           [
                                                                                                                                                     *           Selected
                                                                                                                                                     *           file
                                                                                                                                                     *           ,
                                                                                                                                                     *           Selected
                                                                                                                                                     *           index
                                                                                                                                                     *           ]
                                                                                                                                                     *           ]
                                                                                                                                                     * @see jhelp.util.thread.ThreadedSimpleTask#doSimpleAction(java.lang.Object)
                                                                                                                                                     */
                                                                                                                                                    @Override
                                                                                                                                                    protected void doSimpleAction(
                                                                                                                                                          final Triplet<JHelpFileExplorer2D, JHelpFileExplorerListener, Pair<File, Integer>> parameter)
                                                                                                                                                    {
                                                                                                                                                       parameter.element2.fileExplorerFileChoosen(parameter.element1,
                                                                                                                                                             parameter.element3.element1, parameter.element3.element2);
                                                                                                                                                    }
                                                                                                                                                 };
   /** Task signal to a listener selected file change */
   private static final ThreadedSimpleTask<Triplet<JHelpFileExplorer2D, JHelpFileExplorerListener, Pair<File, Integer>>> TASK_SELECTION_CHANGE   = new ThreadedSimpleTask<Triplet<JHelpFileExplorer2D, JHelpFileExplorerListener, Pair<File, Integer>>>()
                                                                                                                                                 {
                                                                                                                                                    /**
                                                                                                                                                     * Signal
                                                                                                                                                     * to
                                                                                                                                                     * listener
                                                                                                                                                     * that
                                                                                                                                                     * a
                                                                                                                                                     * file
                                                                                                                                                     * is
                                                                                                                                                     * selected
                                                                                                                                                     * <br>
                                                                                                                                                     * <br>
                                                                                                                                                     * <
                                                                                                                                                     * b
                                                                                                                                                     * >
                                                                                                                                                     * Parent
                                                                                                                                                     * documentation
                                                                                                                                                     * :
                                                                                                                                                     * <
                                                                                                                                                     * /
                                                                                                                                                     * b
                                                                                                                                                     * >
                                                                                                                                                     * <br>
                                                                                                                                                     * {@inheritDoc}
                                                                                                                                                     * 
                                                                                                                                                     * @param parameter
                                                                                                                                                     *           [
                                                                                                                                                     *           File
                                                                                                                                                     *           explorer
                                                                                                                                                     *           source
                                                                                                                                                     *           ,
                                                                                                                                                     *           Listener
                                                                                                                                                     *           to
                                                                                                                                                     *           alert
                                                                                                                                                     *           ,
                                                                                                                                                     *           [
                                                                                                                                                     *           Selected
                                                                                                                                                     *           file
                                                                                                                                                     *           ,
                                                                                                                                                     *           Selected
                                                                                                                                                     *           index
                                                                                                                                                     *           ]
                                                                                                                                                     *           ]
                                                                                                                                                     * @see jhelp.util.thread.ThreadedSimpleTask#doSimpleAction(java.lang.Object)
                                                                                                                                                     */
                                                                                                                                                    @Override
                                                                                                                                                    protected void doSimpleAction(
                                                                                                                                                          final Triplet<JHelpFileExplorer2D, JHelpFileExplorerListener, Pair<File, Integer>> parameter)
                                                                                                                                                    {
                                                                                                                                                       parameter.element2.fileExplorerSelectionChange(parameter.element1,
                                                                                                                                                             parameter.element3.element1, parameter.element3.element2);
                                                                                                                                                    }
                                                                                                                                                 };
   /** File explorer listeners */
   private final List<JHelpFileExplorerListener>                                                                         listeners;

   /**
    * Create a new instance of JHelpFileExplorer2D
    */
   public JHelpFileExplorer2D()
   {
      super(false, new JHelpFileExplorerModel());

      this.setListListener(this);
      this.listeners = new ArrayList<JHelpFileExplorerListener>();
   }

   /**
    * Rename a file
    * 
    * @param source
    *           Source
    * @param destination
    *           Destination
    */
   private void renameFile(final File source, final File destination)
   {
      try
      {
         UtilIO.rename(source, destination);
      }
      catch(final IOException exception)
      {
         Debug.printException(exception, "Failed to rename ", source.getAbsolutePath(), " to ", destination.getAbsolutePath());

         this.showOptionPaneMessage(OptionPaneMessageType.ERROR, UtilText.replaceHole(ResourcesGUI.RESOURCE_TEXT.getText(ResourcesGUI.FILE_EXPLORER_ERROR_FAILED_TO_RENAME), source.getAbsolutePath(), destination.getAbsolutePath()));
      }

      ((JHelpFileExplorerModel) this.getListModel()).refreshContent();
   }

   /**
    * Called when key down <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param keyCode
    *           Key code
    * @see jhelp.gui.twoD.JHelpList2D#keyDown(int)
    */
   @Override
   protected void keyDown(final int keyCode)
   {
      this.getSelectedFile();
      switch(keyCode)
      {
         case KeyEvent.VK_F2:
            this.renameSelectedFile();
         break;
         case KeyEvent.VK_F3:
            this.createNewDirectory();
         break;
         case KeyEvent.VK_F4:
            this.goParentFile();
         break;
         case KeyEvent.VK_DELETE:
            this.deleteSelectedFile();
         break;
      }
   }

   /**
    * Launch create new directory in current one procedure
    */
   public void createNewDirectory()
   {
      this.showOptionPaneInput(ResourcesGUI.RESOURCE_TEXT.getText(ResourcesGUI.FILE_EXPLORER_NEW_DIRECTORY_NAME), "", true, false, this, JHelpFileExplorer2D.DIALOG_CREATE_DIRECTORY, null);
   }

   /**
    * Launch delete current selected file procedure<br>
    * Does nothing if no file is selected
    */
   public void deleteSelectedFile()
   {
      final File file = this.getSelectedFile();

      if((file == null) || (file == JHelpFileExplorerModel.PARENT))
      {
         return;
      }

      this.showOptionPaneQuestion(ResourcesGUI.RESOURCE_TEXT.getText(ResourcesGUI.FILE_EXPLORER_DELETE_FILE_TITLE),
            UtilText.replaceHole(ResourcesGUI.RESOURCE_TEXT.getText(ResourcesGUI.FILE_EXPLORER_DELETE_FILE_MESSAGE), file.getAbsolutePath()), false, true, this, JHelpFileExplorer2D.DIALOG_CREATE_DIRECTORY, file);
   }

   /**
    * Current directory
    * 
    * @return Current directory
    */
   public File getCurrentDirectory()
   {
      return ((JHelpFileExplorerModel) this.getListModel()).getDirectory();
   }

   /**
    * Selected file<br>
    * {@code null} if no selection
    * 
    * @return Selected file or {@code null} if no selection
    */
   public File getSelectedFile()
   {
      return this.getSelectedInformation();
   }

   /**
    * Go in parent file.<br>
    * Does nothing if theire no parent
    */
   public void goParentFile()
   {
      final File current = this.getCurrentDirectory();
      final File temp = current.getParentFile();

      if(temp != null)
      {
         this.setSelectedIndex(-1);
         ((JHelpFileExplorerModel) this.getListModel()).setDirectory(temp);
         this.setSelectedFile(current);
      }
   }

   /**
    * Called when slection change in list <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param list2d
    *           List source
    * @param selecttedIndex
    *           Selected index
    * @param information
    *           File selected
    * @param clickCount
    *           Number of click on selection
    * @see jhelp.gui.twoD.JHelpListListener#listSelectionChanged(jhelp.gui.twoD.JHelpList2D, int, java.lang.Object, int)
    */
   @Override
   public void listSelectionChanged(final JHelpList2D<File> list2d, final int selecttedIndex, final File information, final int clickCount)
   {
      if((information == null) || (clickCount <= 1))
      {
         synchronized(this.listeners)
         {
            for(final JHelpFileExplorerListener listener : this.listeners)
            {
               ThreadManager.THREAD_MANAGER.doThread(JHelpFileExplorer2D.TASK_SELECTION_CHANGE, new Triplet<JHelpFileExplorer2D, JHelpFileExplorerListener, Pair<File, Integer>>(this, listener, new Pair<File, Integer>(information,
                     selecttedIndex)));
            }
         }

         return;
      }

      final JHelpFileExplorerModel model = (JHelpFileExplorerModel) this.getListModel();
      model.getDirectory();

      if(information == JHelpFileExplorerModel.PARENT)
      {
         this.goParentFile();

         return;
      }

      if(information.isDirectory() == true)
      {
         this.setSelectedIndex(-1);
         model.setDirectory(information);
         this.setSelectedIndex(0);

         return;
      }

      synchronized(this.listeners)
      {
         for(final JHelpFileExplorerListener listener : this.listeners)
         {
            ThreadManager.THREAD_MANAGER.doThread(JHelpFileExplorer2D.TASK_FILE_CHOOSEN, new Triplet<JHelpFileExplorer2D, JHelpFileExplorerListener, Pair<File, Integer>>(this, listener, new Pair<File, Integer>(information,
                  selecttedIndex)));
         }
      }
   }

   /**
    * Called when option pane is cancel <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param actionID
    *           Action id
    * @param developerInformation
    *           Information
    * @see jhelp.gui.twoD.JHelpOptionPaneListener#optionPaneCancel(int, java.lang.Object)
    */
   @Override
   public void optionPaneCancel(final int actionID, final Object developerInformation)
   {
   }

   /**
    * Called when option pane no button is choose <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param actionID
    *           Action ID
    * @param developerInformation
    *           Information
    * @see jhelp.gui.twoD.JHelpOptionPaneListener#optionPaneNo(int, java.lang.Object)
    */
   @Override
   public void optionPaneNo(final int actionID, final Object developerInformation)
   {
   }

   /**
    * Called when input text is typed <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param actionID
    *           Action ID
    * @param developerInformation
    *           Information
    * @param text
    *           Text typed
    * @see jhelp.gui.twoD.JHelpOptionPaneListener#optionPaneTextTyped(int, java.lang.Object, java.lang.String)
    */
   @Override
   public void optionPaneTextTyped(final int actionID, final Object developerInformation, String text)
   {
      File file, temp;
      String name;
      text = text.trim();

      switch(actionID)
      {
         case DIALOG_CHANGE_NAME:
            file = (File) developerInformation;
            name = file.getName();
            if((text.length() > 0) && (name.equals(text) == false))
            {
               temp = new File(file.getParentFile(), text);
               if(temp.exists() == true)
               {
                  if(temp.isDirectory() != file.isDirectory())
                  {
                     this.showOptionPaneMessage(OptionPaneMessageType.WARNING, ResourcesGUI.RESOURCE_TEXT.getText(ResourcesGUI.FILE_EXPLORER_OVERWRITE_IMPOSSIBLE));
                     break;
                  }

                  this.showOptionPaneQuestion(ResourcesGUI.RESOURCE_TEXT.getText(ResourcesGUI.FILE_EXPLORER_OVERWRITE_FILE_TITLE),
                        UtilText.replaceHole(ResourcesGUI.RESOURCE_TEXT.getText(ResourcesGUI.FILE_EXPLORER_OVERWRITE_FILE_MESSAGE), temp.getAbsolutePath()), false, true, this, JHelpFileExplorer2D.DIALOG_CONFIRM_RENAME,
                        new Pair<File, File>(file, temp));
                  break;
               }

               this.renameFile(file, temp);
            }
         break;
         case DIALOG_CREATE_DIRECTORY:
            if(text.length() > 0)
            {
               file = new File(this.getCurrentDirectory(), text);
               if(file.exists() == true)
               {
                  this.showOptionPaneMessage(OptionPaneMessageType.WARNING, UtilText.replaceHole(ResourcesGUI.RESOURCE_TEXT.getText(ResourcesGUI.FILE_EXPLORER_WARNING_DIRECTORY_EXISTS), file.getAbsolutePath()));
                  break;
               }

               UtilIO.createDirectory(file);

               ((JHelpFileExplorerModel) this.getListModel()).refreshContent();
            }
         break;
      }
   }

   /**
    * Called when option pane Ok or Yes choose <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param actionID
    *           Action ID
    * @param developerInformation
    *           Information
    * @see jhelp.gui.twoD.JHelpOptionPaneListener#optionPaneYesOk(int, java.lang.Object)
    */
   @SuppressWarnings("unchecked")
   @Override
   public void optionPaneYesOk(final int actionID, final Object developerInformation)
   {
      Pair<File, File> pairRenameFile;

      switch(actionID)
      {
         case DIALOG_CONFIRM_RENAME:
            pairRenameFile = (Pair<File, File>) developerInformation;
            this.renameFile(pairRenameFile.element1, pairRenameFile.element2);
         break;
         case DIALOG_CONFIRM_DELETE:
            UtilIO.delete((File) developerInformation);
            ((JHelpFileExplorerModel) this.getListModel()).refreshContent();
         break;
      }
   }

   /**
    * Register a file explorer listener
    * 
    * @param listener
    *           Listener to register
    */
   public void registerFileExplorerListener(final JHelpFileExplorerListener listener)
   {
      if(listener == null)
      {
         throw new NullPointerException("listener musn't be null");
      }

      synchronized(this.listeners)
      {
         if(this.listeners.contains(listener) == false)
         {
            this.listeners.add(listener);
         }
      }
   }

   /**
    * Launch rename selected file procedure.<br>
    * Does nothing if no selection
    */
   public void renameSelectedFile()
   {
      final File file = this.getSelectedFile();
      if((file == null) || (file == JHelpFileExplorerModel.PARENT))
      {
         return;
      }

      final String name = file.getName();
      this.showOptionPaneInput(ResourcesGUI.RESOURCE_TEXT.getText(ResourcesGUI.FILE_EXPLORER_OTHER_NAME), name, true, false, this, JHelpFileExplorer2D.DIALOG_CHANGE_NAME, file);
   }

   /**
    * Change current file
    * 
    * @param file
    *           New current file
    */
   public void setCurrentFile(final File file)
   {
      this.setDirectory(file.getParentFile());
      this.setSelectedFile(file);
   }

   /**
    * Change current directory.<br>
    * {@code null} for root
    * 
    * @param directory
    *           New current directory, {@code null} for root
    */
   public void setDirectory(final File directory)
   {
      ((JHelpFileExplorerModel) this.getListModel()).setDirectory(directory);
   }

   /**
    * Change file filter.<br>
    * {@code null} for no filter
    * 
    * @param fileFilter
    *           New file filter, {@code null} for no filter
    */
   public void setFileFilter(final FileFilter fileFilter)
   {
      ((JHelpFileExplorerModel) this.getListModel()).setFilter(fileFilter);
   }

   /**
    * Change selected file.<br>
    * {@code null} or file outside the directory unselect files
    * 
    * @param file
    *           Change selected file. {@code null} or file outside the directory unselect files
    */
   public void setSelectedFile(final File file)
   {
      int selection = -1;
      final JHelpFileExplorerModel model = (JHelpFileExplorerModel) this.getListModel();
      final int size = model.numberOfElement();

      for(int i = 0; i < size; i++)
      {
         if(model.getElement(i).equals(file) == true)
         {
            selection = i;
            break;
         }
      }

      this.setSelectedIndex(selection);
   }

   /**
    * Unregister file explorer lister
    * 
    * @param listener
    *           Listener to unregister
    */
   public void unregisterFileExplorerListener(final JHelpFileExplorerListener listener)
   {
      synchronized(this.listeners)
      {
         this.listeners.remove(listener);
      }
   }
}