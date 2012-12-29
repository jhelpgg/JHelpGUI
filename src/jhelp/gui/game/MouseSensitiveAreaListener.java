package jhelp.gui.game;


/**
 * Area mouse sensitive listener
 * 
 * @author JHelp
 */
public interface MouseSensitiveAreaListener
{
   /**
    * Called when mouse enter in an area
    * 
    * @param mouseSensitiveArea
    *           Area where mouse enter in
    * @param relativeX
    *           Mouse X relative to the area
    * @param relativeY
    *           Mouse y relative to the area
    * @param abslouteX
    *           Mouse X absolute from main frame
    * @param absoluteY
    *           Mouse Y absolute from main frame
    * @param leftButtonDown
    *           Indicates if mouse left button is down
    * @param middleButtonDown
    *           Indicates if mouse middle button is down
    * @param rightButtonDown
    *           Indicates if mouse right button is down
    */
   public void mouseEnterArea(MouseSensitiveArea mouseSensitiveArea, int relativeX, int relativeY, int abslouteX, int absoluteY, boolean leftButtonDown, boolean middleButtonDown, boolean rightButtonDown);

   /**
    * Called when mouse exit of an area
    * 
    * @param mouseSensitiveArea
    *           Area where mouse exit from
    * @param relativeX
    *           Mouse X relative to the area
    * @param relativeY
    *           Mouse y relative to the area
    * @param abslouteX
    *           Mouse X absolute from main frame
    * @param absoluteY
    *           Mouse Y absolute from main frame
    * @param leftButtonDown
    *           Indicates if mouse left button is down
    * @param middleButtonDown
    *           Indicates if mouse middle button is down
    * @param rightButtonDown
    *           Indicates if mouse right button is down
    */
   public void mouseExitArea(MouseSensitiveArea mouseSensitiveArea, int relativeX, int relativeY, int abslouteX, int absoluteY, boolean leftButtonDown, boolean middleButtonDown, boolean rightButtonDown);

   /**
    * Called when mouse move over an area
    * 
    * @param mouseSensitiveArea
    *           Area where mouse move over
    * @param relativeX
    *           Mouse X relative to the area
    * @param relativeY
    *           Mouse y relative to the area
    * @param abslouteX
    *           Mouse X absolute from main frame
    * @param absoluteY
    *           Mouse Y absolute from main frame
    * @param leftButtonDown
    *           Indicates if mouse left button is down
    * @param middleButtonDown
    *           Indicates if mouse middle button is down
    * @param rightButtonDown
    *           Indicates if mouse right button is down
    */
   public void mouseOverArea(MouseSensitiveArea mouseSensitiveArea, int relativeX, int relativeY, int abslouteX, int absoluteY, boolean leftButtonDown, boolean middleButtonDown, boolean rightButtonDown);
}