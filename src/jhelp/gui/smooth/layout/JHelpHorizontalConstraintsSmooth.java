package jhelp.gui.smooth.layout;

/**
 * Constraints used by {@link JHelpHorizontalLayoutSmooth} to know how place component vertically inside its container
 * 
 * @author JHelp
 */
public enum JHelpHorizontalConstraintsSmooth
      implements JHelpConstraintsSmooth
{
   /** Center the component in height */
   CENTER,
   /** Put the component at down its container */
   DOWN,
   /** Expands the component to take the all height */
   EXPAND,
   /** Put the component at up its container */
   UP
}