/**
 * Project : JHelpSceneGraph<br>
 * Package : jhelp.gui.ui<br>
 * Class : HaveHeader<br>
 * Date : 18 janv. 2009<br>
 * By JHelp
 */
package jhelp.gui;

import javax.swing.JComponent;

/**
 * For object with a component header for the move on detach (Drag/drop and windowable panel) <br>
 * <br>
 * Last modification : 18 janv. 2009<br>
 * Version 0.0.0<br>
 * 
 * @author JHelp
 */
public interface HaveHeader
{
   /**
    * Component we hang for move
    * 
    * @return Component we hang for move
    */
   public JComponent obtainTheComponentForMove();
}