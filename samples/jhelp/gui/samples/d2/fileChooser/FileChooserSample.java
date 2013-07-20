package jhelp.gui.samples.d2.fileChooser;

import java.io.File;

import jhelp.gui.twoD.JHelpFileChooser2D;
import jhelp.gui.twoD.JHelpFileChooser2DListener;
import jhelp.util.MemorySweeper;
import jhelp.util.debug.Debug;
import jhelp.util.debug.DebugLevel;

public class FileChooserSample
      implements JHelpFileChooser2DListener
{

   /**
    * TODO Explains what does the method main in jhelp.gui.samples.d2.fileChooser [JHelpGUI]
    * 
    * @param args
    */
   public static void main(final String[] args)
   {
      JHelpFileChooser2D.showFileChooser(new FileChooserSample());
      Debug.printMark(DebugLevel.VERBOSE, "After can do something in waiting for file choose");
   }

   @Override
   public void cancelChoose()
   {
      Debug.printMark(DebugLevel.VERBOSE, "CANCEL");
      MemorySweeper.exit(0);
   }

   @Override
   public void fileChoose(final File file)
   {
      Debug.printMark(DebugLevel.VERBOSE, "CHOOSE : " + file.getAbsolutePath());
      MemorySweeper.exit(0);
   }
}