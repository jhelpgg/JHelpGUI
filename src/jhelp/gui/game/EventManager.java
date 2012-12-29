package jhelp.gui.game;

import java.util.Map;


/**
 * Event state manager.<br>
 * The method {@link #actionState(Map, int, int, boolean, boolean, boolean)} is called in loop, even if nothing change
 * 
 * @author JHelp
 */
public interface EventManager
{
   /**
    * Actual action key and mouse state
    * 
    * @param actionsStates
    *           Action states. Just use {@link Map#get(Object) actionsStates.get(actionKey)} to know a action key status
    * @param mouseX
    *           Mouse X position
    * @param mouseY
    *           Mouse Y position
    * @param buttonLeft
    *           Indicates if mouse button left is down
    * @param buttonMiddle
    *           Indicates if mouse button middle is down
    * @param buttonRight
    *           Indicates if mouse button right is down
    * @return {@code true} if key events are consumed
    */
   public boolean actionState(Map<ActionKey, Boolean> actionsStates, int mouseX, int mouseY, boolean buttonLeft, boolean buttonMiddle, boolean buttonRight);
}