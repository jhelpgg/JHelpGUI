/**
 * Project : JHelpGUI<br>
 * Package : jhelp.gui<br>
 * Class : DirectoryChooser<br>
 * Date : 23 avr. 2009<br>
 * By JHelp
 */
package jhelp.gui;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;

/**
 * Chooser of directory <br>
 * <br>
 * Last modification : 23 avr. 2009<br>
 * Version 0.0.0<br>
 * 
 * @author JHelp
 */
public class DirectoryChooser
{
   /** Chooser of directory singleton */
   public static final DirectoryChooser DIRECTORY_CHOOSER = new DirectoryChooser();
   /** Linked file chooser */
   private final JFileChooser           fileChooser;

   /**
    * Constructs DirectoryChooser
    */
   private DirectoryChooser()
   {
      this.fileChooser = new JFileChooser("Choose directory");
      this.fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
   }

   /**
    * Open the dialog for choose a directory
    * 
    * @param parent
    *           Component parent
    * @return Chosen directory or {@code null}
    */
   public File chooseOneDirectory(final Component parent)
   {
      this.fileChooser.setMultiSelectionEnabled(false);

      if(this.fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
      {
         return this.fileChooser.getSelectedFile();
      }

      return null;
   }

   /**
    * Choose one directory
    * 
    * @param parent
    *           Component parent
    * @param startDirectory
    *           Start directory
    * @return Choosen directory or {@code null} if user cancel the operation
    */
   public File chooseOneDirectory(final Component parent, final File startDirectory)
   {
      this.fileChooser.setMultiSelectionEnabled(false);
      this.fileChooser.setCurrentDirectory(startDirectory);

      if(this.fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
      {
         return this.fileChooser.getSelectedFile();
      }

      return null;
   }

   /**
    * Open dialog for choose several directories
    * 
    * @param parent
    *           Component parent
    * @return Chosen directories
    */
   public File[] chooseSeveralDirectories(final Component parent)
   {
      this.fileChooser.setMultiSelectionEnabled(true);

      if(this.fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
      {
         return this.fileChooser.getSelectedFiles();
      }

      return null;
   }

   /**
    * Choose a list of directories
    * 
    * @param parent
    *           Component parent
    * @param startDirectory
    *           Starting directory
    * @return Selected file list or {@code null} if user cancel operation
    */
   public File[] chooseSeveralDirectories(final Component parent, final File startDirectory)
   {
      this.fileChooser.setMultiSelectionEnabled(true);
      this.fileChooser.setCurrentDirectory(startDirectory);

      if(this.fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
      {
         return this.fileChooser.getSelectedFiles();
      }

      return null;
   }

   /**
    * Change the ok button text
    * 
    * @param text
    *           New OK button text
    */
   public void setApproveButtonText(final String text)
   {
      if(text == null)
      {
         throw new NullPointerException("text musn't be null");
      }

      this.fileChooser.setApproveButtonText(text);
   }

   /**
    * Change the ok button
    * 
    * @param text
    *           OK button text
    * @param tooltip
    *           OK button tooltip
    */
   public void setApproveButtonText(final String text, final String tooltip)
   {
      if(text == null)
      {
         throw new NullPointerException("text musn't be null");
      }

      if(tooltip == null)
      {
         throw new NullPointerException("tooltip musn't be null");
      }

      this.fileChooser.setApproveButtonText(text);
      this.fileChooser.setApproveButtonToolTipText(tooltip);
   }

   /**
    * Change the dialog title
    * 
    * @param title
    *           New title
    */
   public void setTitle(final String title)
   {
      if(title == null)
      {
         throw new NullPointerException("title musn't be null");
      }

      this.fileChooser.setDialogTitle(title);
   }
}