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
package jhelp.gui.game.aplletSample;

import java.util.Map;

import jhelp.gui.game.ActionKey;
import jhelp.gui.game.JHelpAnimation;
import jhelp.gui.game.JHelpApplet;
import jhelp.util.debug.Debug;
import jhelp.util.gui.JHelpFont;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpSprite;
import jhelp.util.math.random.JHelpRandom;

/**
 * Sample of applet
 * 
 * @author JHelp
 */
public class AppletSample
      extends JHelpApplet
{
   /** serialVersionUID */
   private static final long serialVersionUID = 8762415065742123332L;
   /** Animation to play */
   private JHelpAnimation    animation;
   /** Animate sprite */
   private JHelpSprite       spriteAnimate;
   /** Blue square sprite (Move alone) */
   private JHelpSprite       spriteBlueSquare;
   /** Green triangle sprite (React to arrow key and Escape) */
   private JHelpSprite       spriteGreenTriangle;
   /** Red circle sprite (Follow the mouse) */
   private JHelpSprite       spriteRedCircle;
   /** Start way X for blue square sprite */
   private int               wayX             = JHelpRandom.random(1, 10);
   /** Start way Y for blue square sprite */
   private int               wayY             = JHelpRandom.random(1, 10);

   /**
    * Call when animation loop <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param image
    *           Image parent where draw
    * @param actionKeyState
    *           State of action key codes
    * @param mouseX
    *           Mouse X position
    * @param mouseY
    *           Mouse Y position
    * @param buttonLeftDown
    *           Mouse button left state
    * @param buttonMiddleDow
    *           Mouse button middle state
    * @param buttonRightDown
    *           Mouse button right state
    * @see jhelp.gui.game.JHelpApplet#animationLoop(jhelp.util.gui.JHelpImage, java.util.Map, int, int, boolean, boolean,
    *      boolean)
    */
   @Override
   protected void animationLoop(final JHelpImage image, final Map<ActionKey, Boolean> actionKeyState, final int mouseX, final int mouseY,
         final boolean buttonLeftDown, final boolean buttonMiddleDow, final boolean buttonRightDown)
   {
      final int width = image.getWidth();
      final int height = image.getHeight();

      final int x = this.spriteBlueSquare.getX();
      final int y = this.spriteBlueSquare.getY();

      if((x + this.wayX) <= 0)
      {
         this.wayX = JHelpRandom.random(1, 10);
      }

      if((x + this.wayX) >= (width - this.spriteBlueSquare.getWidth()))
      {
         this.wayX = JHelpRandom.random(-10, -1);
      }

      if((y + this.wayY) <= 0)
      {
         this.wayY = JHelpRandom.random(1, 10);
      }

      if((y + this.wayY) >= (height - this.spriteBlueSquare.getHeight()))
      {
         this.wayY = JHelpRandom.random(-10, -1);
      }

      this.spriteBlueSquare.translate(this.wayX, this.wayY);

      this.spriteRedCircle.setPosition(mouseX - (this.spriteRedCircle.getWidth() >> 1), mouseY - (this.spriteRedCircle.getHeight() >> 1));

      if(actionKeyState.get(ActionKey.ACTION_UP))
      {
         this.spriteGreenTriangle.translate(0, -5);
      }

      if(actionKeyState.get(ActionKey.ACTION_DOWN))
      {
         this.spriteGreenTriangle.translate(0, 5);
      }

      if(actionKeyState.get(ActionKey.ACTION_LEFT))
      {
         this.spriteGreenTriangle.translate(-5, 0);
      }

      if(actionKeyState.get(ActionKey.ACTION_RIGHT))
      {
         this.spriteGreenTriangle.translate(5, 0);
      }

      if(actionKeyState.get(ActionKey.ACTION_CANCEL))
      {
         this.spriteGreenTriangle.setPosition((width - this.spriteGreenTriangle.getWidth()) >> 1, (height - this.spriteGreenTriangle.getHeight()) >> 1);
      }

      if((actionKeyState.get(ActionKey.ACTION_FIRE)) && (!this.animation.isPlaying()))
      {
         this.playAnimation(this.animation, this.spriteAnimate);
      }
   }

   /**
    * Call when applet initialize <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param image
    *           Image where draw
    * @see jhelp.gui.game.JHelpApplet#initializeJHelpApplet(jhelp.util.gui.JHelpImage)
    */
   @Override
   protected void initializeJHelpApplet(final JHelpImage image)
   {
      final int width = image.getWidth();
      final int height = image.getHeight();

      final int w = width / 10;
      final int h = height / 10;

      this.spriteBlueSquare = this.createSprite(0, 0, w, h);
      JHelpImage image2 = this.spriteBlueSquare.getImage();
      image2.clear(0);
      image2.fillRectangle(0, 0, w, h, 0x800000FF, false);
      image2.update();

      this.spriteRedCircle = this.createSprite(width - w, height - h, w, h);
      image2 = this.spriteRedCircle.getImage();
      image2.clear(0);
      image2.fillEllipse(0, 0, w, h, 0x80FF0000, false);
      image2.update();

      this.spriteGreenTriangle = this.createSprite((width - w) >> 1, (height - h) >> 1, w, h);
      image2 = this.spriteGreenTriangle.getImage();
      image2.clear(0);
      image2.fillPolygon(new int[]
      {
            w >> 1, w, 0
      }, new int[]
      {
            0, h, h
      }, 0x8000FF00, false);
      image2.update();

      this.spriteAnimate = this.createSprite((width - w) >> 1, (height - h) >> 1, w, h);
      this.animation = new JHelpAnimation(10);
      this.animation.addFrame(100, 0, 0, JHelpAnimation.INTERPOLATION_SINUS);
      this.animation.addFrame(125, width - w, height - h, JHelpAnimation.INTERPOLATION_EXPONENTIAL);
      this.animation.addFrame(175, 0, (height - h) >> 1, JHelpAnimation.INTERPOLATION_LOGARITHM);
      this.animation.addFrame(200, width - w, (height - h) >> 1, JHelpAnimation.INTERPOLATION_LINEAR);

      this.playAnimation(this.animation, this.spriteAnimate);

      try
      {
         final JHelpImage background = JHelpImage.loadImage(AppletSample.class.getResourceAsStream("back.jpg"));

         image.fillRectangle(0, 0, width, height, background, false);
      }
      catch(final Exception exception)
      {
         Debug.printException(exception, "Issue while trying get/apply background");
      }

      final int count = this.obtainCookieInformation("count", 0) + 1;
      this.putCookieInformation("count", count);
      this.putCookieInformation("test", "test");

      image.drawRectangle(0, 0, width, height, 0xFF000000);
      image.drawStringCenter(width >> 1, height >> 1, "TEST " + count, new JHelpFont("Courrier", 32, true, false, true), 0xFF000000);
   }
}