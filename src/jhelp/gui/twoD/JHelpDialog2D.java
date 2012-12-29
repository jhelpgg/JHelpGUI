package jhelp.gui.twoD;

import java.awt.Dimension;

import jhelp.gui.JHelpMouseListener;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpSprite;
import jhelp.util.list.Pair;

/**
 * Dialog 2D.<br>
 * Dialog are created by {@link JHelpFrame2D#createDialog(JHelpComponent2D)}.<br>
 * By default, click outside the dialog, close it, you can avoid this behavior with {@link #setClikOutClose(boolean)}, but in
 * this case don't forget to add an other way to close it
 * 
 * @author JHelp
 */
public class JHelpDialog2D
{
   /** Indicates if clicking outside the dialog, it's close it */
   private boolean                clikOutClose;
   /** Main component of the dialog */
   private final JHelpComponent2D component2d;
   /** Frame parent where dialog draw */
   private final JHelpFrame2D     parent;
   /** Sprite for draw the dialog */
   private final JHelpSprite      sprite;

   /**
    * Create a new instance of JHelpDialog2D
    * 
    * @param component2d
    *           Main component
    * @param parent
    *           Frame parent
    */
   JHelpDialog2D(final JHelpComponent2D component2d, final JHelpFrame2D parent)
   {
      this.component2d = component2d;
      this.parent = parent;
      this.clikOutClose = true;

      final Dimension preferred = component2d.getPrefrerredSize(-1, -1);

      final JHelpImage parentImage = parent.getImage();

      this.sprite = parentImage.createSprite((parentImage.getWidth() - preferred.width) >> 1, (parentImage.getHeight() - preferred.height) >> 1, preferred.width, preferred.height);
      this.updateImage();
   }

   /**
    * Compute component under the mouse
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    * @return Component and associate listener
    */
   Pair<JHelpComponent2D, JHelpMouseListener> mouseOver(final int x, final int y)
   {
      return this.component2d.mouseOver(x, y);
   }

   /**
    * Update the dialog image
    */
   void updateImage()
   {
      final boolean visible = this.sprite.isVisible();

      if(visible == true)
      {
         this.sprite.setVisible(false);
      }

      final JHelpImage image = this.sprite.getImage();
      image.startDrawMode();

      image.clear(0);

      if(this.component2d.isVisible() == true)
      {
         final Dimension preferred = this.component2d.getPrefrerredSize(-1, -1);
         this.component2d.setBounds(0, 0, preferred.width, preferred.height);
         this.component2d.paintInternal(0, 0, image);
      }

      image.endDrawMode();

      if(visible == true)
      {
         this.sprite.setVisible(true);
      }
   }

   /**
    * Update dialog visibility
    * 
    * @param visible
    *           New visibility status
    */
   void updateVisible(final boolean visible)
   {
      if(visible == true)
      {
         final JHelpImage parentImage = this.parent.getImage();
         this.sprite.setPosition((parentImage.getWidth() - this.sprite.getWidth()) >> 1, (parentImage.getHeight() - this.sprite.getHeight()) >> 1);
      }

      this.sprite.setVisible(visible);
   }

   /**
    * Compute dialog main component size
    * 
    * @return Dialog main component size
    */
   public Dimension componentIternSize()
   {
      return new Dimension(this.component2d.getPrefrerredSize(-1, -1));
   }

   /**
    * Dialog height
    * 
    * @return Dialog height
    */
   public int getHeight()
   {
      return this.sprite.getHeight();
   }

   /**
    * Dialog width
    * 
    * @return Dialog width
    */
   public int getWidth()
   {
      return this.sprite.getWidth();
   }

   /**
    * Dialog X location
    * 
    * @return Dialog x location
    */
   public int getX()
   {
      return this.sprite.getX();
   }

   /**
    * Dialog Y location
    * 
    * @return Dialog Y location
    */
   public int getY()
   {
      return this.sprite.getY();
   }

   /**
    * Indicates if dialog will be close if user click outside it
    * 
    * @return {@code true} if dialog will be close if user click outside it
    */
   public boolean isClikOutClose()
   {
      return this.clikOutClose;
   }

   /**
    * Indicates if dialog is visible
    * 
    * @return {@code true} if dialog is visible
    */
   public boolean isVisible()
   {
      return this.sprite.isVisible();
   }

   /**
    * Change the state of click out close
    * 
    * @param clikOutClose
    *           New click out close status
    */
   public void setClikOutClose(final boolean clikOutClose)
   {
      this.clikOutClose = clikOutClose;
   }

   /**
    * Change dialog visibility
    * 
    * @param visible
    *           New visibility status
    */
   public void setVisible(final boolean visible)
   {
      this.parent.setDialogVisible(this, visible);
   }
}