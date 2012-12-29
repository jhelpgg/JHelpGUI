package jhelp.gui.game;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Area mouse sensitive
 * 
 * @author JHelp
 */
public final class MouseSensitiveArea
{
   /**
    * Create a circle sensitive area
    * 
    * @param identifier
    *           Area identifier
    * @param centerX
    *           Circle center X
    * @param centerY
    *           Circle center Y
    * @param radius
    *           Circle radius
    * @return Created area
    */
   public static MouseSensitiveArea createCircleArea(final int identifier, final int centerX, final int centerY, final int radius)
   {
      return new MouseSensitiveArea(identifier, new Ellipse2D.Double(centerX - radius, centerY - radius, radius << 1, radius << 1));
   }

   /**
    * Create an ellipse sensitive area
    * 
    * @param identifier
    *           Area identifier
    * @param x
    *           X upper left corner
    * @param y
    *           Y upper left corner
    * @param width
    *           Ellipse width
    * @param height
    *           Ellipse height
    * @return Created area
    */
   public static MouseSensitiveArea createEllipseArea(final int identifier, final int x, final int y, final int width, final int height)
   {
      return new MouseSensitiveArea(identifier, new Ellipse2D.Double(x, y, width, height));
   }

   /**
    * Create a rectangle sensitive area
    * 
    * @param identifier
    *           Area identifier
    * @param x
    *           X upper left corner
    * @param y
    *           Y upper left corner
    * @param width
    *           Rectangle width
    * @param height
    *           Rectangle height
    * @return Created area
    */
   public static MouseSensitiveArea createRectangleArea(final int identifier, final int x, final int y, final int width, final int height)
   {
      return new MouseSensitiveArea(identifier, new Rectangle(x, y, width, height));
   }

   /** Area bounds */
   private final Rectangle bounds;
   /** Indicates if area is enable */
   private boolean         enbale;
   /** Area identifier */
   private final int       identifer;
   /** Area shape */
   private final Shape     shape;
   /** Translation X */
   private int             x;
   /** Translation Y */
   private int             y;

   /**
    * Create a new instance of MouseSensitiveArea
    * 
    * @param identifer
    *           Area identifier
    * @param shape
    *           Area shape
    */
   public MouseSensitiveArea(final int identifer, final Shape shape)
   {
      if(shape == null)
      {
         throw new NullPointerException("shape musn't be null");
      }

      this.identifer = identifer;
      this.shape = shape;
      this.bounds = shape.getBounds();
      this.enbale = true;
   }

   /**
    * Indicates if a position is inside the area
    * 
    * @param x
    *           X
    * @param y
    *           Y
    * @return {@code true} if position is inside
    */
   public boolean contains(final int x, final int y)
   {
      return this.shape.contains(x - this.x, y - this.y);
   }

   /**
    * Area bounds
    * 
    * @return Area bounds
    */
   public Rectangle getBounds()
   {
      return new Rectangle(this.bounds);
   }

   /**
    * Detection border
    * 
    * @return Detection border
    */
   public Rectangle getDetection()
   {
      return new Rectangle(this.bounds.x + this.x, this.bounds.y + this.y, this.bounds.width, this.bounds.height);
   }

   /**
    * Area identifier
    * 
    * @return Area identifier
    */
   public int getIdentifer()
   {
      return this.identifer;
   }

   /**
    * X translation
    * 
    * @return X translation
    */
   public int getX()
   {
      return this.x;
   }

   /**
    * Y translation
    * 
    * @return Y translation
    */
   public int getY()
   {
      return this.y;
   }

   /**
    * Indicates if area is enable
    * 
    * @return {@code true} if area is enable
    */
   public boolean isEnbale()
   {
      return this.enbale;
   }

   /**
    * Change area enable state
    * 
    * @param enbale
    *           New area enable state
    */
   public void setEnbale(final boolean enbale)
   {
      this.enbale = enbale;
   }

   /**
    * Translate the area
    * 
    * @param x
    *           Translation X
    * @param y
    *           Translation Y
    * @return The area
    */
   public MouseSensitiveArea setTranslation(final int x, final int y)
   {
      this.x = x;
      this.y = y;

      return this;
   }
}