package jhelp.gui.smooth.shape;

/**
 * Shadow level
 * 
 * @author JHelp
 */
public enum ShadowLevel
{
   /** Component far, shadow is big */
   FAR(9),
   /** Component near, shadow is small */
   NEAR(3),
   /** Component is on the container, no shadow */
   NO_SHADOW(0),
   /** Component at normal distance, shadow is normal */
   NORMAL(5);
   /** Number of pixels for shadow size */
   private final int numberOfPixels;

   /**
    * Create a new instance of ShadowLevel
    * 
    * @param numberOfPixels
    *           Number of pixels for shadow size
    */
   ShadowLevel(final int numberOfPixels)
   {
      this.numberOfPixels = numberOfPixels;
   }

   /**
    * Number of pixels for shadow size
    * 
    * @return Number of pixels for shadow size
    */
   public int getNumberOfPixels()
   {
      return this.numberOfPixels;
   }
}