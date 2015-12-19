/**
 * <h1>License :</h1> <br>
 * The following code is deliver as is. I take care that code compile and work, but I am not responsible about any damage it may
 * cause.<br>
 * You can use, modify, the code as your need for any usage. But you can't do any action that avoid me or other person use,
 * modify this code. The code is free for usage and modification, you can't change that fact.<br>
 * <br>
 * 
 * @author JHelp
 */
package jhelp.gui.samples.game.ui;

import jhelp.gui.game.JHelpGameDynamic;
import jhelp.util.debug.Debug;
import jhelp.util.gui.JHelpImage;

public class SampleDynamicFrame
      extends JHelpGameDynamic
{
   private JHelpImage imageUI;

   public SampleDynamicFrame()
   {
      super("Sample Dynamic");
   }

   @Override
   protected void constructGameInstance()
   {
      try
      {
         this.imageUI = JHelpImage.loadImage(SampleDynamicFrame.class.getResourceAsStream("574.png"));

      }
      catch(final Exception exception)
      {
         Debug.printException(exception);
      }
   }
}