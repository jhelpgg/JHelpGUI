package jhelp.gui.samples.d2.tree;

/**
 * Sampe frame with a tree
 * 
 * @author JHelp
 */
public class SampleTree
{

   /**
    * Launch sample frame with tree
    * 
    * @param args
    *           Unused
    */
   public static void main(final String[] args)
   {
      // Create the frame
      final FrameTree frameTree = new FrameTree();

      // Show the frame
      // It is recommend to put the visibility outside of the constructor
      frameTree.setVisible(true);

      // Activate automatic refresh for animated component like fold panel and scroll
      // It is strongly recommend to activate the automatic refresh after the first show of the frame
      frameTree.setAutomaticRefresh(true);
   }
}