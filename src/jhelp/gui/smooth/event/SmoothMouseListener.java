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