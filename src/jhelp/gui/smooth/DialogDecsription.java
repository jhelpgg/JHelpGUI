package jhelp.gui.smooth;

import jhelp.gui.smooth.shape.ShadowLevel;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpPaint;

/**
 * Describe a dialog
 * 
 * @author JHelp
 */
public class DialogDecsription
{
   /** Dialog background color */
   final int                  background;
   /** Dialog main component */
   final JHelpComponentSmooth mainComponent;
   /** Dialog background paint */
   final JHelpPaint           paintBackground;
   /** Dialog shadow color */
   final int                  shadow;
   /** Dialog shadow level */
   final ShadowLevel          shadowLevel;
   /** Dialog texture background */
   final JHelpImage           textureBackground;
   /** Dialog X in main frame */
   final int                  x;
   /** Dialog Y in main frame */
   final int                  y;

   /**
    * Create a new instance of DialogDecsription
    * 
    * @param x
    *           X in frame
    * @param y
    *           Y in frame
    * @param mainComponent
    *           Component to draw inside the dialog
    * @param background
    *           Background color
    * @param shadow
    *           Shadow color
    * @param shadowLevel
    *           Shadow level
    */
   public DialogDecsription(final int x, final int y, final JHelpComponentSmooth mainComponent, final int background, final int shadow, final ShadowLevel shadowLevel)
   {
      if(mainComponent == null)
      {
         throw new NullPointerException("mainComponent musn't be null");
      }

      if(shadowLevel == null)
      {
         throw new NullPointerException("shadowLevel musn't be null");
      }

      this.x = x;
      this.y = y;
      this.mainComponent = mainComponent;
      this.background = background;
      this.paintBackground = null;
      this.textureBackground = null;
      this.shadow = shadow;
      this.shadowLevel = shadowLevel;
   }

   /**
    * Create a new instance of DialogDecsription
    * 
    * @param x
    *           X in frame
    * @param y
    *           Y in frame
    * @param mainComponent
    *           Component to draw inside the dialog
    * @param background
    *           Background texture
    * @param shadow
    *           Shadow color
    * @param shadowLevel
    *           Shadow level
    */
   public DialogDecsription(final int x, final int y, final JHelpComponentSmooth mainComponent, final JHelpImage background, final int shadow, final ShadowLevel shadowLevel)
   {
      if(mainComponent == null)
      {
         throw new NullPointerException("mainComponent musn't be null");
      }

      if(background == null)
      {
         throw new NullPointerException("background musn't be null");
      }

      if(shadowLevel == null)
      {
         throw new NullPointerException("shadowLevel musn't be null");
      }

      this.x = x;
      this.y = y;
      this.mainComponent = mainComponent;
      this.background = 0;
      this.paintBackground = null;
      this.textureBackground = background;
      this.shadow = shadow;
      this.shadowLevel = shadowLevel;
   }

   /**
    * Create a new instance of DialogDecsription
    * 
    * @param x
    *           X in frame
    * @param y
    *           Y in frame
    * @param mainComponent
    *           Component to draw inside the dialog
    * @param background
    *           Background paint
    * @param shadow
    *           Shadow color
    * @param shadowLevel
    *           Shadow level
    */
   public DialogDecsription(final int x, final int y, final JHelpComponentSmooth mainComponent, final JHelpPaint background, final int shadow, final ShadowLevel shadowLevel)
   {
      if(mainComponent == null)
      {
         throw new NullPointerException("mainComponent musn't be null");
      }

      if(background == null)
      {
         throw new NullPointerException("background musn't be null");
      }

      if(shadowLevel == null)
      {
         throw new NullPointerException("shadowLevel musn't be null");
      }

      this.x = x;
      this.y = y;
      this.mainComponent = mainComponent;
      this.background = 0;
      this.paintBackground = background;
      this.textureBackground = null;
      this.shadow = shadow;
      this.shadowLevel = shadowLevel;
   }
}