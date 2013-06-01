package jhelp.gui.samples.d2.tree;

import jhelp.gui.twoD.JHelpBorderLayout;
import jhelp.gui.twoD.JHelpBorderLayout.JHelpBorderLayoutConstraints;
import jhelp.gui.twoD.JHelpFrame2D;
import jhelp.gui.twoD.JHelpScrollPane2D;
import jhelp.gui.twoD.JHelpTree2D;

/**
 * Frame with a tree
 * 
 * @author JHelp
 */
public class FrameTree
      extends JHelpFrame2D
{
   /**
    * Create a new instance of FrameTree
    */
   public FrameTree()
   {
      super(new JHelpBorderLayout());

      final JHelpTree2D<TreeNodeSample> tree = new JHelpTree2D<TreeNodeSample>(new TreeModelSample());
      this.addComponent2D(new JHelpScrollPane2D(tree), JHelpBorderLayoutConstraints.CENTER);
   }
}