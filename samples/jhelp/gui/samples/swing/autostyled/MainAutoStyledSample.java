package jhelp.gui.samples.swing.autostyled;

import jhelp.gui.samples.swing.autostyled.gui.FrameAutostyledSample;

/**
 * Sample to show how use auto styled text
 * 
 * @author JHelp
 */
public class MainAutoStyledSample
{
   /**
    * Launch the sample
    * 
    * @param args
    *           Unused
    */
   public static void main(final String[] args)
   {
      // Create the frame
      final FrameAutostyledSample frameAutostyledSample = new FrameAutostyledSample();

      // Show the frame
      // It is recommend to put the visibility outside of the constructor
      frameAutostyledSample.setVisible(true);
   }
}