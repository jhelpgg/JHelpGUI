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
package jhelp.gui.smooth.event;

/**
 * Listener of mouse event
 * 
 * @author JHelp
 */
public interface SmoothMouseListener
{
   /**
    * Called when mouse clicked
    * 
    * @param mouseInformation
    *           Mouse event description
    */
   public void mouseClicked(SmoothMouseInformation mouseInformation);

   /**
    * Called when mouse dragged
    * 
    * @param mouseInformation
    *           Mouse event description
    */
   public void mouseDragged(SmoothMouseInformation mouseInformation);

   /**
    * Called when mouse enter in a component
    * 
    * @param mouseInformation
    *           Mouse event description
    */
   public void mouseEnter(SmoothMouseInformation mouseInformation);

   /**
    * Called when mouse exit from a component
    * 
    * @param mouseInformation
    *           Mouse event description
    */
   public void mouseExit(SmoothMouseInformation mouseInformation);

   /**
    * Called when mouse moved
    * 
    * @param mouseInformation
    *           Mouse event description
    */
   public void mouseMoved(SmoothMouseInformation mouseInformation);

   /**
    * Called when mouse pressed
    * 
    * @param mouseInformation
    *           Mouse event description
    */
   public void mousePressed(SmoothMouseInformation mouseInformation);

   /**
    * Called when mouse released
    * 
    * @param mouseInformation
    *           Mouse event description
    */
   public void mouseReleased(SmoothMouseInformation mouseInformation);

   /**
    * Called when mouse wheel moved
    * 
    * @param mouseInformation
    *           Mouse event description
    */
   public void mouseWhellMoved(SmoothMouseInformation mouseInformation);
}