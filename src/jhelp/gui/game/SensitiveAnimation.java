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
package jhelp.gui.game;

import java.util.ArrayList;
import java.util.List;

import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpSprite;
import jhelp.util.gui.dynamic.DynamicAnimation;
import jhelp.util.gui.dynamic.Position;
import jhelp.util.gui.dynamic.Positionable;
import jhelp.util.math.random.JHelpRandom;

/**
 * Animation with area that are mouse sensitive
 * 
 * @author JHelp
 */
public class SensitiveAnimation
      implements Positionable
{
   /**
    * Describe a sensitive area, mouse reaction and animate
    * 
    * @author JHelp
    */
   class SensitiveArea
         implements MouseSensitiveAreaListener, DynamicAnimation
   {
      /** Absolute frame when animation started */
      private float startAbslouteFrame;

      /**
       * Create a new instance of SensitiveArea
       */
      SensitiveArea()
      {
      }

      /**
       * Play the animation <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param absoluteFrame
       *           Absolute frame
       * @param image
       *           Image parent
       * @return {@code true} if aniation will continue OR {@code false} if animation finished
       * @see jhelp.util.gui.dynamic.DynamicAnimation#animate(float, jhelp.util.gui.JHelpImage)
       */
      @Override
      public boolean animate(final float absoluteFrame, final JHelpImage image)
      {
         return SensitiveAnimation.this.doAnimate(absoluteFrame - this.startAbslouteFrame, image);
      }

      /**
       * Called when animation finished <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param image
       *           Image parent
       * @see jhelp.util.gui.dynamic.DynamicAnimation#endAnimation(jhelp.util.gui.JHelpImage)
       */
      @Override
      public void endAnimation(final JHelpImage image)
      {
         SensitiveAnimation.this.destroySprite(image);
      }

      /**
       * Called when mouse enter inside the area <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseSensitiveArea
       *           Area where mouse interact
       * @param relativeX
       *           Mouse X relative to the area
       * @param relativeY
       *           Mouse Y relative to the area
       * @param abslouteX
       *           Mouse X
       * @param absoluteY
       *           Mouse Y
       * @param leftButtonDown
       *           Indicates if left button is down
       * @param middleButtonDown
       *           Indicates if middle button is down
       * @param rightButtonDown
       *           Indicates if right button is down
       * @see jhelp.gui.game.MouseSensitiveAreaListener#mouseEnterArea(jhelp.gui.game.MouseSensitiveArea, int, int, int, int,
       *      boolean, boolean, boolean)
       */
      @Override
      public void mouseEnterArea(final MouseSensitiveArea mouseSensitiveArea, final int relativeX, final int relativeY, final int abslouteX,
            final int absoluteY, final boolean leftButtonDown, final boolean middleButtonDown, final boolean rightButtonDown)
      {
         if(mouseSensitiveArea.getIdentifer() == SensitiveAnimation.this.id)
         {
            SensitiveAnimation.this.enterArea(relativeX, relativeY);
         }
      }

      /**
       * Called when mouse exit outside the area <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseSensitiveArea
       *           Area where mouse interact
       * @param relativeX
       *           Mouse X relative to the area
       * @param relativeY
       *           Mouse Y relative to the area
       * @param abslouteX
       *           Mouse X
       * @param absoluteY
       *           Mouse Y
       * @param leftButtonDown
       *           Indicates if left button is down
       * @param middleButtonDown
       *           Indicates if middle button is down
       * @param rightButtonDown
       *           Indicates if right button is down
       * @see jhelp.gui.game.MouseSensitiveAreaListener#mouseExitArea(jhelp.gui.game.MouseSensitiveArea, int, int, int, int,
       *      boolean, boolean, boolean)
       */
      @Override
      public void mouseExitArea(final MouseSensitiveArea mouseSensitiveArea, final int relativeX, final int relativeY, final int abslouteX,
            final int absoluteY, final boolean leftButtonDown, final boolean middleButtonDown, final boolean rightButtonDown)
      {
         if(mouseSensitiveArea.getIdentifer() == SensitiveAnimation.this.id)
         {
            SensitiveAnimation.this.exitArea(relativeX, relativeY);
         }
      }

      /**
       * Called when mouse move over the area <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseSensitiveArea
       *           Area where mouse interact
       * @param relativeX
       *           Mouse X relative to the area
       * @param relativeY
       *           Mouse Y relative to the area
       * @param abslouteX
       *           Mouse X
       * @param absoluteY
       *           Mouse Y
       * @param leftButtonDown
       *           Indicates if left button is down
       * @param middleButtonDown
       *           Indicates if middle button is down
       * @param rightButtonDown
       *           Indicates if right button is down
       * @see jhelp.gui.game.MouseSensitiveAreaListener#mouseOverArea(jhelp.gui.game.MouseSensitiveArea, int, int, int, int,
       *      boolean, boolean, boolean)
       */
      @Override
      public void mouseOverArea(final MouseSensitiveArea mouseSensitiveArea, final int relativeX, final int relativeY, final int abslouteX,
            final int absoluteY, final boolean leftButtonDown, final boolean middleButtonDown, final boolean rightButtonDown)
      {
         if(mouseSensitiveArea.getIdentifer() == SensitiveAnimation.this.id)
         {
            SensitiveAnimation.this.moveArea(relativeX, relativeY, leftButtonDown, middleButtonDown, rightButtonDown);
         }
      }

      /**
       * Called when animation started <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param startAbslouteFrame
       *           Absolute frame where animation started
       * @param image
       *           Image parent
       * @see jhelp.util.gui.dynamic.DynamicAnimation#startAnimation(float, jhelp.util.gui.JHelpImage)
       */
      @Override
      public void startAnimation(final float startAbslouteFrame, final JHelpImage image)
      {
         this.startAbslouteFrame = startAbslouteFrame;
         SensitiveAnimation.this.createSprite(image);
      }
   }

   /** Color used in shine effect */
   private static final int             SHINE_COLOR      = 0x33FFFFFF;
   /** Shine effect height */
   private static final int             SHINE_HEIGHT     = 32;
   /** Shine effect width */
   private static final int             SHINE_WIDTH      = 16;
   /** Number step in shine effect */
   private static final int             TOTAL_SHINE_STEP = 64;
   /** Background color (Used if {@link #backgroundImage} is {@code null} */
   private int                          backgroundColor;
   /** Background image */
   private JHelpImage                   backgroundImage;
   /** Image where animation is draw */
   private final JHelpImage             image;
   /** Last element that the user clicked */
   private SensitiveElement             lastDownSensitiveElement;
   /** Mouse sensitive area for mouse capture */
   private final MouseSensitiveArea     mouseSensitiveArea;
   /** Indicates if animation is already registered inside a {@link JHelpGameDynamic} */
   private boolean                      registered;
   /** Sensitive area that manage mouse */
   private final SensitiveArea          sensitiveArea;
   /** Last over element */
   private SensitiveElement             sensitiveElementOver;
   /** All sensitive elements */
   private final List<SensitiveElement> sensitiveElements;
   /** Sensitive element where shine effect playing */
   private SensitiveElement             sensitiveElementShine;
   /** Actual shine effect step */
   private int                          shineStep;
   /** Sprite where animation draw */
   private JHelpSprite                  sprite;
   /** X location in parent */
   private int                          x;
   /** Y location in parent */
   private int                          y;
   /** Animation ID */
   final int                            id;

   /**
    * Create a new instance of SensitiveAnimation
    * 
    * @param id
    *           Animation ID
    * @param x
    *           X location in parent
    * @param y
    *           Y location in parent
    * @param width
    *           Animation width
    * @param height
    *           Animation height
    */
   public SensitiveAnimation(final int id, final int x, final int y, final int width, final int height)
   {
      this.id = id;
      this.x = x;
      this.y = y;
      this.registered = false;
      this.backgroundColor = 0x44444444;
      this.image = new JHelpImage(width, height);
      this.mouseSensitiveArea = MouseSensitiveArea.createRectangleArea(id, 0, 0, width, height);
      this.mouseSensitiveArea.setTranslation(x, y);
      this.sensitiveArea = new SensitiveArea();
      this.sensitiveElements = new ArrayList<SensitiveElement>();
   }

   /**
    * Compute sensitive element under a given position
    * 
    * @param x
    *           X position
    * @param y
    *           Y position
    * @return Element under the position OR {@code null} if no element there
    */
   private SensitiveElement obtainSensitiveElement(final int x, final int y)
   {
      synchronized(this.sensitiveElements)
      {
         final int size = this.sensitiveElements.size();
         SensitiveElement sensitiveElement;

         for(int index = size - 1; index >= 0; index--)
         {
            sensitiveElement = this.sensitiveElements.get(index);

            if((sensitiveElement.isVisible() == true) && (sensitiveElement.isEnable() == true)
                  && (sensitiveElement.containsPosition(x - this.x, y - this.y) == true))
            {
               return sensitiveElement;
            }
         }
      }

      return null;
   }

   /**
    * Update element over from a given position
    * 
    * @param x
    *           Position X
    * @param y
    *           Position Y
    */
   private void updateOverElement(final int x, final int y)
   {
      final SensitiveElement sensitiveElement = this.obtainSensitiveElement(x, y);

      if(this.sensitiveElementOver != sensitiveElement)
      {
         if(this.sensitiveElementOver != null)
         {
            this.sensitiveElementOver.mouseExit(this);
         }

         this.sensitiveElementOver = sensitiveElement;

         if(this.sensitiveElementOver != null)
         {
            this.sensitiveElementOver.mouseEnter(this);
         }
      }
   }

   /**
    * Create the sprite for the animation
    * 
    * @param parent
    *           Image parent
    */
   void createSprite(final JHelpImage parent)
   {
      this.sprite = parent.createSprite(this.x, this.y, this.image);
      this.sprite.setVisible(true);
   }

   /**
    * Destroy properly the sprite
    * 
    * @param parent
    *           Image parent
    */
   void destroySprite(final JHelpImage parent)
   {
      this.sprite.setVisible(false);
      parent.removeSprite(this.sprite);
      this.sprite = null;
   }

   /**
    * Play the animation
    * 
    * @param frame
    *           Relative frame
    * @param image
    *           Image where draw
    * @return {@code true} if animation continue OR {@code false} if animation finished
    */
   boolean doAnimate(final float frame, final JHelpImage image)
   {
      synchronized(this.image)
      {
         this.image.startDrawMode();

         if(this.backgroundImage == null)
         {
            this.image.clear(this.backgroundColor);
         }
         else
         {
            this.image.clear(0);
            this.image.drawImage(0, 0, this.backgroundImage);
         }

         synchronized(this.sensitiveElements)
         {
            final List<SensitiveElement> canShine = new ArrayList<SensitiveElement>();

            for(final SensitiveElement sensitiveElement : this.sensitiveElements)
            {
               if(sensitiveElement.isVisible() == true)
               {
                  this.image.drawImage(sensitiveElement.getX(), sensitiveElement.getY(), sensitiveElement.getImage());

                  if(sensitiveElement.isEnable() == true)
                  {
                     canShine.add(sensitiveElement);
                  }
               }
            }

            if(((this.sensitiveElementShine == null) || (this.sensitiveElementShine.isEnable() == false) || (this.sensitiveElementShine.isVisible() == false))
                  && (canShine.isEmpty() == false))
            {
               this.shineStep = 0;
               this.sensitiveElementShine = JHelpRandom.random(canShine);
            }

            if(this.sensitiveElementShine != null)
            {
               final int shineX = (this.sensitiveElementShine.getX() + (((SensitiveAnimation.TOTAL_SHINE_STEP - this.shineStep) * this.sensitiveElementShine.getWidth()) / SensitiveAnimation.TOTAL_SHINE_STEP))
                     - (SensitiveAnimation.SHINE_WIDTH >> 1);
               final int shineY = (this.sensitiveElementShine.getY() + (((SensitiveAnimation.TOTAL_SHINE_STEP - this.shineStep) * this.sensitiveElementShine.getHeight()) / SensitiveAnimation.TOTAL_SHINE_STEP))
                     - (SensitiveAnimation.SHINE_HEIGHT >> 1);
               this.image.fillEllipse(shineX, shineY, SensitiveAnimation.SHINE_WIDTH, SensitiveAnimation.SHINE_HEIGHT, SensitiveAnimation.SHINE_COLOR);
               this.shineStep++;

               if(this.shineStep > SensitiveAnimation.TOTAL_SHINE_STEP)
               {
                  this.shineStep = 0;
                  this.sensitiveElementShine = null;
               }
            }
         }

         this.image.endDrawMode();
      }

      // this.sprite.setVisible(false);
      // this.sprite.setVisible(true);

      return true;
   }

   /**
    * Called when mouse enter the area
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    */
   void enterArea(final int x, final int y)
   {
      this.updateOverElement(x, y);
   }

   /**
    * Called when mouse exit the area
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    */
   void exitArea(final int x, final int y)
   {
      this.updateOverElement(x, y);
   }

   /**
    * Called when mouse move over the area
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    * @param leftButtonDown
    *           Indicates if left button is down
    * @param middleButtonDown
    *           Indicates if middle button is down
    * @param rightButtonDown
    *           Indicates if right button is down
    */
   void moveArea(final int x, final int y, final boolean leftButtonDown, final boolean middleButtonDown, final boolean rightButtonDown)
   {
      this.updateOverElement(x, y);

      if(this.sensitiveElementOver != null)
      {
         if(leftButtonDown == true)
         {
            if(this.lastDownSensitiveElement == null)
            {
               this.lastDownSensitiveElement = this.sensitiveElementOver;
            }
         }
         else if(this.lastDownSensitiveElement == this.sensitiveElementOver)
         {
            this.sensitiveElementOver.mouseClick(this);
            this.lastDownSensitiveElement = null;
         }
         else
         {
            this.lastDownSensitiveElement = null;
         }
      }
      else if(leftButtonDown == false)
      {
         this.lastDownSensitiveElement = null;
      }
   }

   /**
    * Add a sensitive element inside the animation.<br>
    * It will be callback on mouse event over it
    * 
    * @param sensitiveElement
    *           Sensitive element to add
    */
   public void addSensitiveElement(final SensitiveElement sensitiveElement)
   {
      if(sensitiveElement == null)
      {
         throw new NullPointerException("sensitiveElement musn't be null");
      }

      synchronized(this.sensitiveElements)
      {
         if(this.sensitiveElements.contains(sensitiveElement) == false)
         {
            this.sensitiveElements.add(sensitiveElement);
         }
      }
   }

   /**
    * Animation height
    * 
    * @return Animation height
    */
   public int getHeight()
   {
      return this.image.getHeight();
   }

   /**
    * Animation position on parent <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Animation position on parent
    * @see jhelp.util.gui.dynamic.Positionable#getPosition()
    */
   @Override
   public Position getPosition()
   {
      return new Position(this.x, this.y);
   }

   /**
    * Animation width
    * 
    * @return Animation width
    */
   public int getWidth()
   {
      return this.image.getWidth();
   }

   /**
    * Register/link the animation inside a game dynamic to be visible and played
    * 
    * @param gameDynamic
    *           Game dynamic to link with
    */
   public void registerInside(final JHelpGameDynamic gameDynamic)
   {
      if(this.registered == true)
      {
         return;
      }

      this.registered = true;
      gameDynamic.addArea(this.mouseSensitiveArea);
      gameDynamic.registerMouseSensitiveAreaListener(this.sensitiveArea);
      gameDynamic.playAnimation(this.sensitiveArea);
   }

   /**
    * Make the background to unify color
    * 
    * @param color
    *           New background color
    */
   public void setBackground(final int color)
   {
      synchronized(this.image)
      {
         this.backgroundImage = null;
         this.backgroundColor = color;
      }
   }

   /**
    * Make the background as a texture image
    * 
    * @param background
    *           Image background ({@code null} will return to last unify color)
    */
   public void setBackground(JHelpImage background)
   {
      synchronized(this.image)
      {
         if(background != null)
         {
            background = JHelpImage.createResizedImage(background, this.image.getWidth(), this.image.getHeight());
         }

         this.backgroundImage = background;
      }
   }

   /**
    * Change animation position in parent <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param position
    *           New position
    * @see jhelp.util.gui.dynamic.Positionable#setPosition(jhelp.util.gui.dynamic.Position)
    */
   @Override
   public void setPosition(final Position position)
   {
      this.x = position.getX();
      this.y = position.getY();
      this.mouseSensitiveArea.setTranslation(this.x, this.y);

      if(this.sprite != null)
      {
         this.sprite.setPosition(this.x, this.y);
      }
   }
}