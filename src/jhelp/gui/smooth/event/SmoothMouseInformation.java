package jhelp.gui.smooth.event;

import java.awt.event.MouseEvent;

import jhelp.gui.smooth.JHelpComponentSmooth;
import jhelp.util.text.UtilText;

/**
 * Mouse event description
 * 
 * @author JHelp
 */
public final class SmoothMouseInformation
{
   /** Number of click count */
   private final int                  clickcount;
   /** Component where mouse event happen */
   private final JHelpComponentSmooth componentSmooth;
   /** Indicates if mouse left button is down */
   private final boolean              leftDown;
   /** Indicates if mouse middle button is down */
   private final boolean              middleDown;
   /** Mouse x on the main frame */
   private final int                  mouseX;
   /** Mouse x on the component */
   private final int                  mouseXonComponent;
   /** Mouse y on the main frame */
   private final int                  mouseY;
   /** Mouse y on the component */
   private final int                  mouseYonComponent;
   /** Indicates if mouse right button is down */
   private final boolean              rightDown;
   /** Mouse event type */
   private final int                  type;
   /** Number of wheel rotation */
   private final int                  whellRotation;

   /**
    * Create a new instance of SmoothMouseInformation
    * 
    * @param type
    *           Mouse event type
    * @param componentSmooth
    *           Component where mouse event happen
    * @param mouseXonComponent
    *           Mouse x on the component
    * @param mouseYonComponent
    *           Mouse y on the component
    * @param mouseX
    *           Mouse x on the main frame
    * @param mouseY
    *           Mouse y on the main frame
    * @param leftDown
    *           Indicates if mouse left button is down
    * @param middleDown
    *           Indicates if mouse middle button is down
    * @param rightDown
    *           Indicates if mouse right button is down
    * @param clickcount
    *           Number of click count
    * @param whellRotation
    *           Number of wheel rotation
    */
   public SmoothMouseInformation(final int type, final JHelpComponentSmooth componentSmooth, final int mouseXonComponent, final int mouseYonComponent, final int mouseX, final int mouseY, final boolean leftDown,
         final boolean middleDown, final boolean rightDown, final int clickcount, final int whellRotation)
   {
      this.type = type;
      this.componentSmooth = componentSmooth;
      this.mouseXonComponent = mouseXonComponent;
      this.mouseYonComponent = mouseYonComponent;
      this.mouseX = mouseX;
      this.mouseY = mouseY;
      this.leftDown = leftDown;
      this.middleDown = middleDown;
      this.rightDown = rightDown;
      this.clickcount = clickcount;
      this.whellRotation = whellRotation;
   }

   /**
    * Number of click count
    * 
    * @return Number of click count
    */
   public int getClickcount()
   {
      return this.clickcount;
   }

   /**
    * Component where mouse event happen
    * 
    * @return Component where mouse event happen
    */
   public JHelpComponentSmooth getComponentSmooth()
   {
      return this.componentSmooth;
   }

   /**
    * Mouse x on the main frame
    * 
    * @return Mouse x on the main frame
    */
   public int getMouseX()
   {
      return this.mouseX;
   }

   /**
    * Mouse x on the component
    * 
    * @return Mouse x on the component
    */
   public int getMouseXonComponent()
   {
      return this.mouseXonComponent;
   }

   /**
    * Mouse y on the main frame
    * 
    * @return Mouse y on the main frame
    */
   public int getMouseY()
   {
      return this.mouseY;
   }

   /**
    * Mouse y on the component
    * 
    * @return Mouse y on the component
    */
   public int getMouseYonComponent()
   {
      return this.mouseYonComponent;
   }

   /**
    * Mouse event type
    * 
    * @return Mouse event type
    */
   public int getType()
   {
      return this.type;
   }

   /**
    * Mouse event type name
    * 
    * @return Mouse event type name
    */
   public String getTypeName()
   {
      switch(this.type)
      {
         case MouseEvent.MOUSE_PRESSED:
            return "MOUSE_PRESSED";
         case MouseEvent.MOUSE_RELEASED:
            return "MOUSE_RELEASED";
         case MouseEvent.MOUSE_CLICKED:
            return "MOUSE_CLICKED";
         case MouseEvent.MOUSE_ENTERED:
            return "MOUSE_ENTERED";
         case MouseEvent.MOUSE_EXITED:
            return "MOUSE_EXITED";
         case MouseEvent.MOUSE_MOVED:
            return "MOUSE_MOVED";
         case MouseEvent.MOUSE_DRAGGED:
            return "MOUSE_DRAGGED";
         case MouseEvent.MOUSE_WHEEL:
            return "MOUSE_WHEEL";
         default:
            return "unknown mouse type";
      }
   }

   /**
    * Number of wheel rotation
    * 
    * @return Number of wheel rotation
    */
   public int getWhellRotation()
   {
      return this.whellRotation;
   }

   /**
    * Indicates if mouse left button is down
    * 
    * @return Indicates if mouse left button is down
    */
   public boolean isLeftDown()
   {
      return this.leftDown;
   }

   /**
    * Indicates if mouse middle button is down
    * 
    * @return Indicates if mouse middle button is down
    */
   public boolean isMiddleDown()
   {
      return this.middleDown;
   }

   /**
    * Indicates if mouse right button is down
    * 
    * @return Indicates if mouse right button is down
    */
   public boolean isRightDown()
   {
      return this.rightDown;
   }

   /**
    * String representation <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return String representation
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return UtilText.concatenate(this.getTypeName(), " (", this.mouseXonComponent, ", ", this.mouseYonComponent, ") [", this.mouseX, ", ", this.mouseY, "] buttons[", this.leftDown, ", ", this.middleDown, ", ", this.rightDown,
            "] count=", this.clickcount, " wheel=", this.whellRotation, " ON ", this.componentSmooth);
   }
}