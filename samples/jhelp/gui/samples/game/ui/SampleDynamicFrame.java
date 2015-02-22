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