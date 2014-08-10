package jhelp.gui.smooth;

/**
 * Generic container
 * 
 * @author JHelp
 */
public abstract class JHelpContainerSmooth
      extends JHelpComponentSmooth
{
   /**
    * Create a new instance of JHelpContainerSmooth
    */
   public JHelpContainerSmooth()
   {
   }

   /**
    * Obtain a component of container
    * 
    * @param index
    *           Component index
    * @return Component at index
    */
   public abstract JHelpComponentSmooth getChild(int index);

   /**
    * Number of children
    * 
    * @return Number of children
    */
   public abstract int getNumberOfChildren();

}