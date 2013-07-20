package jhelp.gui.samples.d2.list;


/**
 * Sample of list
 * 
 * @author JHelp
 */
public class SampleList
{

   /**
    * Launch sample of list
    * 
    * @param args
    *           Unused
    */
   public static void main(final String[] args)
   {
      // Create the frame
      final FrameList frameList = new FrameList();

      // Show the frame
      // It is recommend to put the visibility outside of the constructor
      frameList.setVisible(true);

      // Activate automatic refresh for animated component like fold panel and scroll
      // It is strongly recommend to activate the automatic refresh after the first show of the frame
      frameList.setAutomaticRefresh(true);
   }
}