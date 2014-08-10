package jhelp.gui.smooth.layout;

/**
 * Constraints used by {@link JHelpVerticalLayoutSmooth} to know how place component horizontally in container
 * 
 * @author JHelp
 */
public enum JHelpVerticalConstraintsSmooth
      implements JHelpConstraintsSmooth
{
   /** Center component in width */
   CENTER,
   /** Expand component width to fit the container */
   EXPAND,
   /** Put component at left */
   LEFT,
   /** Put component at right */
   RIGHT
}