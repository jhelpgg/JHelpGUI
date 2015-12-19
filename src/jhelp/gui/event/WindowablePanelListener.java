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
    * Call when the windowable panel is attach
    * 
    * @param windowablePanelEvent
    *           Event description
    */
   public void windowablePanelAttach(WindowablePanelEvent windowablePanelEvent);

   /**
    * Call when the windowable panel is close
    * 
    * @param windowablePanelEvent
    *           Event description
    */
   public void windowablePanelClose(WindowablePanelEvent windowablePanelEvent);

   /**
    * Call when the windowable panel is detach
    * 
    * @param windowablePanelEvent
    *           Event description
    */
   public void windowablePanelDetach(WindowablePanelEvent windowablePanelEvent);

   /**
    * Call when the windowable panel is hide
    * 
    * @param windowablePanelEvent
    *           Event description
    */
   public void windowablePanelHide(WindowablePanelEvent windowablePanelEvent);

   /**
    * Call when the windowable panel is open
    * 
    * @param windowablePanelEvent
    *           Event description
    */
   public void windowablePanelOpen(WindowablePanelEvent windowablePanelEvent);

   /**
    * Call when the windowable panel is show
    * 
    * @param windowablePanelEvent
    *           Event description
    */
   public void windowablePanelShow(WindowablePanelEvent windowablePanelEvent);
}