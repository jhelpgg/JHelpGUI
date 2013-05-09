package jhelp.gui;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jhelp.util.filter.FileFilter;

/**
 * File explorer can be used inside an interface like a component or self alone inside a dialog
 * @author JHelp
 *
 */
public class JHelpFileExplorer
      extends JPanel
{
private JTextField textFieldCurrentDirectory;private File currentDirectory;private File selectedFile;private FileFilter fileFilter;private JPanel panelFiles;private ArrayList<File> fileList;private JButton buttonGoParent;
}