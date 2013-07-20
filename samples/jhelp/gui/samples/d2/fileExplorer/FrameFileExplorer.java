package jhelp.gui.samples.d2.fileExplorer;

import jhelp.gui.twoD.JHelpBorderLayout;
import jhelp.gui.twoD.JHelpBorderLayout.JHelpBorderLayoutConstraints;
import jhelp.gui.twoD.JHelpFileExplorer2D;
import jhelp.gui.twoD.JHelpFrame2D;

public class FrameFileExplorer
      extends JHelpFrame2D
{
   public FrameFileExplorer()
   {
      super(new JHelpBorderLayout());

      final JHelpFileExplorer2D fileExplorer2D = new JHelpFileExplorer2D();
      this.addComponent2D(fileExplorer2D, JHelpBorderLayoutConstraints.LEFT);
   }
}