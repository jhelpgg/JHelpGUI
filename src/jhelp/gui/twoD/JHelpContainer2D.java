package jhelp.gui.twoD;

import java.util.List;


/**
 * Container 2D
 * 
 * @author JHelp
 */
public abstract class JHelpContainer2D
      extends JHelpComponent2D
{
   /**
    * Children list
    * 
    * @return Children list
    */
   public abstract List<JHelpComponent2D> children();
}