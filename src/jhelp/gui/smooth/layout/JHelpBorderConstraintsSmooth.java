package jhelp.gui.smooth.layout;

/**
 * Constraints for {@link JHelpBorderLayoutSmooth}
 * 
 * @author JHelp
 */
public enum JHelpBorderConstraintsSmooth
      implements JHelpConstraintsSmooth
{
   /** Place the component at the center */
   CENTER,
   /** Place the component at left-down corner */
   CORNER_LEFT_DOWN,
   /** Place the component at left-up corner */
   CORNER_LEFT_UP,
   /** Place the component at right-down corner */
   CORNER_RIGHT_DOWN,
   /** Place the component at right-up corner */
   CORNER_RIGHT_UP,
   /** Place the component down. It let corners free */
   DOWN,
   /** Place the component down and take the all width */
   DOWN_EXPAND,
   /** Place the component left. It let corners free */
   LEFT,
   /** Place the component left and take the all height */
   LEFT_EXPAND,
   /** Place the component right. It let corners free */
   RIGHT,
   /** Place the component right and take the all height */
   RIGHT_EXPAND,
   /** Place the component up. It let corners free */
   UP,
   /** Place the component up and take the all width */
   UP_EXPAND
}