package jhelp.gui.samples.game.ui;

import jhelp.gui.FrameEditArea;
import jhelp.util.gui.UtilGUI;

public class SampleFrameEditArea
{

   /**
    * @param args
    */
   public static void main(final String[] args)
   {
      UtilGUI.initializeGUI();
      final FrameEditArea frameEditArea = new FrameEditArea();
      frameEditArea.setVisible(true);
   }
}