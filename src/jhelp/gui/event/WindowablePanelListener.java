/**
 * Project : JHelpSceneGraph<br>
 * Package : jhelp.gui.event<br>
 * Class : WindowablePanelListener<br>
 * Date : 19 janv. 2009<br>
 * By JHelp
 */
package jhelp.gui.event;

import java.util.EventListener;

/**
 * Listener of windowable panel events <br>
 * <br>
 * Last modification : 25 janv. 2009<br>
 * Version 0.0.1<br>
 * 
 * @author JHelp
 */
public interface WindowablePanelListener
      extends EventListener
{
   /**
    * Call when the windowable panel is close
    * 
    * @param windowablePanelEvent
    *           Event description
    */
   public void windowablePanelClose(WindowablePanelEvent windowablePanelEvent);

   /**
    * Call when the windowable panel is open
    * 
    * @param windowablePanelEvent
    *           Event description
    */
   public void windowablePanelOpen(WindowablePanelEvent windowablePanelEvent);

   /**
    * Call when the windowable panel is hide
    * 
    * @param windowablePanelEvent
    *           Event description
    */
   public void windowablePanelHide(WindowablePanelEvent windowablePanelEvent);

   /**
    * Call when the windowable panel is show
    * 
    * @param windowablePanelEvent
    *           Event description
    */
   public void windowablePanelShow(WindowablePanelEvent windowablePanelEvent);

   /**
    * Call when the windowable panel is attach
    * 
    * @param windowablePanelEvent
    *           Event description
    */
   public void windowablePanelAttach(WindowablePanelEvent windowablePanelEvent);

   /**
    * Call when the windowable panel is detach
    * 
    * @param windowablePanelEvent
    *           Event description
    */
   public void windowablePanelDetach(WindowablePanelEvent windowablePanelEvent);
}