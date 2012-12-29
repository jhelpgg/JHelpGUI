/**
 * Project : JHelpSceneGraph<br>
 * Package : jhelp.gui.event<br>
 * Class : WindowablePanelEvent<br>
 * Date : 19 janv. 2009<br>
 * By JHelp
 */
package jhelp.gui.event;

import java.awt.AWTEvent;

import jhelp.gui.WindowablePanel;

/**
 * Events can append to a windowable panel <br>
 * <br>
 * Last modification : 25 janv. 2009<br>
 * Version 0.0.1<br>
 * 
 * @author JHelp
 */
public class WindowablePanelEvent
      extends AWTEvent
{
   /** Close event */
   public static final int EVENT_WINDOWABLE_PANEL_CLOSE  = AWTEvent.RESERVED_ID_MAX + 1;
   /** Open event */
   public static final int EVENT_WINDOWABLE_PANEL_OPEN   = AWTEvent.RESERVED_ID_MAX + 2;
   /** Hide event */
   public static final int EVENT_WINDOWABLE_PANEL_HIDE   = AWTEvent.RESERVED_ID_MAX + 3;
   /** Show event */
   public static final int EVENT_WINDOWABLE_PANEL_SHOW   = AWTEvent.RESERVED_ID_MAX + 4;
   /** Detach event */
   public static final int EVENT_WINDOWABLE_PANEL_DETACH = AWTEvent.RESERVED_ID_MAX + 5;
   /** Attach event */
   public static final int EVENT_WINDOWABLE_PANEL_ATTACH = AWTEvent.RESERVED_ID_MAX + 6;

   /**
    * Constructs WindowablePanelEvent
    * 
    * @param source
    *           Windowable panel where append the event
    * @param id
    *           Event ID
    */
   public WindowablePanelEvent(WindowablePanel source, int id)
   {
      super(source, id);
   }

   /**
    * The windowable panel where event append
    * 
    * @return The windowable panel where event append
    */
   public WindowablePanel getWindowablePanelSource()
   {
      return (WindowablePanel) this.source;
   }
}