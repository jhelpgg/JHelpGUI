package jhelp.gui.twoD;

import java.awt.event.MouseEvent;

/**
 * 2D utilities
 * 
 * @author JHelp
 */
public class UtilTwoD
{
   /**
    * Obtain component 2D embed in mouse event
    * 
    * @param mouseEvent
    *           Mouse event that contains the component
    * @return Component extract or {@code null} if mouse event dosen't contains a component
    */
   public static JHelpComponent2D getComponent2DFromMouseEvent(final MouseEvent mouseEvent)
   {
      final Object source = mouseEvent.getSource();

      if(source == null)
      {
         return null;
      }

      if(source instanceof JHelpComponent2D)
      {
         return (JHelpComponent2D) source;
      }

      return null;
   }
}