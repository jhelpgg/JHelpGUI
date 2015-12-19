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
package jhelp.gui.dnd;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * Drop target<br>
 * <br>
 * Last modification : 2 fevr. 2009<br>
 * Version 0.0.0<br>
 * 
 * @author JHelp
 */
public abstract class DropTarget
{
   /** List of created drop target */
   private static ArrayList<DropTarget> arrayList;

   /**
    * Obtain drop target for a screen position
    * 
    * @param x
    *           X position
    * @param y
    *           Y position
    * @return Found land on the position
    */
   public static DropTarget obtainDropTargetForScreenPosition(final int x, final int y)
   {
      if(DropTarget.arrayList == null)
      {
         return null;
      }

      Point position;
      Dimension size;
      for(final DropTarget dropTarget : DropTarget.arrayList)
      {
         if(dropTarget.obtainDropComponent().isVisible() == true)
         {
            position = dropTarget.obtainDropComponent().getLocationOnScreen();
            size = dropTarget.obtainDropComponent().getSize();
            if((x >= position.x) && (y >= position.y) && (x < (position.x + size.width)) && (y < (position.y + size.height)))
            {
               return dropTarget;
            }
         }
      }

      return null;
   }

   /**
    * Constructs DropTarget
    */
   public DropTarget()
   {
      if(DropTarget.arrayList == null)
      {
         DropTarget.arrayList = new ArrayList<DropTarget>();
      }

      DropTarget.arrayList.add(this);
   }

   /**
    * Call when drop done
    * 
    * @param information
    *           Information give to target
    * @param x
    *           X position on target component
    * @param y
    *           Y position on target component
    */
   public abstract void dropDone(Object information, int x, int y);

   /**
    * Indicates if information is valid for this target
    * 
    * @param information
    *           Information test
    * @return {@code true} if the information is accept by this target
    */
   public abstract boolean isAcceptableInformation(Object information);

   /**
    * Component link for the drop area
    * 
    * @return Linked component
    */
   public abstract JComponent obtainDropComponent();
}