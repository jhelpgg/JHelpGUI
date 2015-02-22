package jhelp.gui.game;

import jhelp.util.gui.dynamic.AnimationPosition;
import jhelp.util.gui.dynamic.JHelpDynamicImage;
import jhelp.util.gui.dynamic.Position;
import jhelp.util.gui.dynamic.SinusInterpolation;

/**
 * Utilities for dynamic game
 * 
 * @author JHelp
 */
public class UtilDynamic
{
   /**
    * Create an animation that change the position of a sensitive animation
    * 
    * @param sensitiveAnimation
    *           Animation to move
    * @param destinationX
    *           Future location X
    * @param destinationY
    *           Future location Y
    * @param millisecond
    *           Time, in milliseconds, for go from actual position to the specified one
    * @return Created animation
    */
   public static AnimationPosition createTranslateSensitiveAnimation(final SensitiveAnimation sensitiveAnimation, final int destinationX,
         final int destinationY, final int millisecond)
   {
      if(sensitiveAnimation == null)
      {
         throw new NullPointerException("sensitiveAnimation musn't be null");
      }

      final AnimationPosition animationPosition = new AnimationPosition(sensitiveAnimation, 1, SinusInterpolation.SINUS_INTERPOLATION);
      animationPosition.addFrame(Math.max(1, UtilDynamic.millisecondToNumberOfFrame(millisecond)), new Position(destinationX, destinationY));
      return animationPosition;
   }

   /**
    * Create an animation that change the position of a sensitive element
    * 
    * @param sensitiveElement
    *           Element to move
    * @param destinationX
    *           Future location X
    * @param destinationY
    *           Future location Y
    * @param millisecond
    *           Time, in milliseconds, for go from actual position to the specified one
    * @return Created animation
    */
   public static AnimationPosition createTranslateSensitiveElement(final SensitiveElement sensitiveElement, final int destinationX, final int destinationY,
         final int millisecond)
   {
      if(sensitiveElement == null)
      {
         throw new NullPointerException("sensitiveElement musn't be null");
      }

      final AnimationPosition animationPosition = new AnimationPosition(sensitiveElement, 1, SinusInterpolation.SINUS_INTERPOLATION);
      animationPosition.addFrame(Math.max(1, UtilDynamic.millisecondToNumberOfFrame(millisecond)), new Position(destinationX, destinationY));
      return animationPosition;
   }

   /**
    * Transform time, in milliseconds, to number of frame
    * 
    * @param millisecond
    *           Time, in milliseconds, to convert
    * @return Number of frame
    */
   public static final int millisecondToNumberOfFrame(final int millisecond)
   {
      return (millisecond * JHelpDynamicImage.FPS) / 1000;
   }

   /**
    * Create and play an animation that translate a sensitive animation
    * 
    * @param gameDynamic
    *           Game dynamic where the animation will be play
    * @param sensitiveAnimation
    *           Animation to move
    * @param destinationX
    *           Future location X
    * @param destinationY
    *           Future location Y
    * @param millisecond
    *           Time, in milliseconds, for go from actual position to the specified one
    */
   public static void translateSensitiveAnimation(final JHelpGameDynamic gameDynamic, final SensitiveAnimation sensitiveAnimation, final int destinationX,
         final int destinationY, final int millisecond)
   {
      gameDynamic.playAnimation(UtilDynamic.createTranslateSensitiveAnimation(sensitiveAnimation, destinationX, destinationY, millisecond));
   }

   /**
    * Create and play an animation that translate a sensitive element
    * 
    * @param gameDynamic
    *           Game dynamic where the animation will be play
    * @param sensitiveElement
    *           Element to move
    * @param destinationX
    *           Future location X
    * @param destinationY
    *           Future location Y
    * @param millisecond
    *           Time, in milliseconds, for go from actual position to the specified one
    */
   public static void translateSensitiveElement(final JHelpGameDynamic gameDynamic, final SensitiveElement sensitiveElement, final int destinationX,
         final int destinationY, final int millisecond)
   {
      gameDynamic.playAnimation(UtilDynamic.createTranslateSensitiveElement(sensitiveElement, destinationX, destinationY, millisecond));
   }
}