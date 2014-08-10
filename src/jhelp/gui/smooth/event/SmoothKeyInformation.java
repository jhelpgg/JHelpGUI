package jhelp.gui.smooth.event;

/**
 * Describe a key event inside
 * 
 * @author JHelp
 */
public class SmoothKeyInformation
{
   /** Indicates if <b>ALT</b> is down */
   public final boolean altDown;
   /** Indicates if <b>CONTROL</b> is down */
   public final boolean controlDown;
   /** Character representation of the key */
   public final char    keyChar;
   /** Key code */
   public final int     keyCode;
   /** Indicates if <b>SHIFT</b> is down */
   public final boolean shiftDown;

   /**
    * Create a new instance of SmoothKeyInformation
    * 
    * @param keyCode
    *           Key code
    * @param keyChar
    *           Character representation of the key
    * @param shiftDown
    *           Indicates if <b>SHIFT</b> is down
    * @param controlDown
    *           Indicates if <b>CONTROL</b> is down
    * @param altDown
    *           Indicates if <b>ALT</b> is down
    */
   public SmoothKeyInformation(final int keyCode, final char keyChar, final boolean shiftDown, final boolean controlDown, final boolean altDown)
   {
      this.keyCode = keyCode;
      this.keyChar = keyChar;
      this.shiftDown = shiftDown;
      this.controlDown = controlDown;
      this.altDown = altDown;
   }
}