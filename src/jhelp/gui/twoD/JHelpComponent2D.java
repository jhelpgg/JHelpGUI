package jhelp.gui.twoD;

import java.awt.Dimension;
import java.awt.Rectangle;

import jhelp.gui.JHelpMouseListener;
import jhelp.util.gui.JHelpImage;
import jhelp.util.list.Pair;

/**
 * Generic component 2D.<br>
 * All components 2D have to extends this class
 * 
 * @author JHelp
 */
public abstract class JHelpComponent2D
{
   /**
    * Indicates if a component can be add inside a container to avoid cyclic inclusion
    * 
    * @param parent
    *           Parent where we want put the component
    * @param component2d
    *           Component to add
    * @return {@code true} if component can be add
    */
   static final boolean validHerarchy(final JHelpContainer2D parent, final JHelpComponent2D component2d)
   {
      if(parent.equals(component2d) == true)
      {
         return false;
      }

      if((component2d instanceof JHelpContainer2D) == false)
      {
         return true;
      }

      final JHelpContainer2D container2d = (JHelpContainer2D) component2d;

      for(final JHelpComponent2D child : container2d.children())
      {
         if(JHelpComponent2D.validHerarchy(parent, child) == false)
         {
            return false;
         }
      }

      return true;
   }

   /** Last computed bounds */
   private final Rectangle    bounds;
   /** Associated mouse listener */
   private JHelpMouseListener mouseListener;
   /** Actual parent */
   private JHelpContainer2D   parent;
   /** Indicates if component is visible */
   private boolean            visible;
   /** X absolute position */
   private int                xAbsolute;
   /** Y absolute position */
   private int                yAbsolute;

   /**
    * Create a new instance of JHelpComponent2D
    */
   public JHelpComponent2D()
   {
      this.bounds = new Rectangle();
      this.visible = true;
   }

   /**
    * Paint the component it self
    * 
    * @param x
    *           X location on parent
    * @param y
    *           Y location on parent
    * @param parent
    *           Parent where draw
    */
   final void paintInternal(final int x, final int y, final JHelpImage parent)
   {
      if(this.visible == false)
      {
         return;
      }

      this.xAbsolute = x;
      this.yAbsolute = y;

      this.paint(x, y, parent);
   }

   /**
    * Detach the component from its parent
    */
   final void removeParent()
   {
      this.parent = null;
   }

   /**
    * Change components bounds
    * 
    * @param x
    *           New X
    * @param y
    *           New Y
    * @param width
    *           New width
    * @param height
    *           New height
    */
   final void setBounds(final int x, final int y, final int width, final int height)
   {
      this.bounds.x = x;
      this.bounds.y = y;
      this.bounds.width = width;
      this.bounds.height = height;
   }

   /**
    * Define the parent.Can only be called once
    * 
    * @param parent
    *           The parent
    */
   final void setParent(final JHelpContainer2D parent)
   {
      if(this.parent != null)
      {
         throw new IllegalStateException("The component " + this + " have already as parent " + this.parent + " so can't give it " + parent + " as parent");
      }

      if(JHelpComponent2D.validHerarchy(parent, this) == false)
      {
         throw new IllegalStateException("The component " + this + " or one of its children contains the container " + parent + " where you try to add it");
      }

      this.parent = parent;
   }

   /**
    * Compute the component preferred
    * 
    * @param parentWidth
    *           Parent width, -1 if not already known
    * @param parentHeight
    *           Parent height, -1 if not already known
    * @return Preferred size
    */
   protected abstract Dimension getPrefrerredSize(int parentWidth, int parentHeight);

   /**
    * Test if mouse is over the component.<br>
    * If over it returns the component itself and associated listener else {@code null} is return
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    * @return This component and its mouse listener OR {@code null}
    */
   protected Pair<JHelpComponent2D, JHelpMouseListener> mouseOver(final int x, final int y)
   {
      if(this.visible == false)
      {
         return null;
      }

      final Rectangle bounds = this.getBounds();
      if((this.mouseListener == null) || (x < 0) || (y < 0) || (x >= bounds.width) || (y >= bounds.height))
      {
         return null;
      }

      return new Pair<JHelpComponent2D, JHelpMouseListener>(this, this.mouseListener);
   }

   /**
    * Draw the component itself
    * 
    * @param x
    *           X location on parent
    * @param y
    *           Y location on parent
    * @param parent
    *           Parent where draw
    */
   protected abstract void paint(int x, int y, JHelpImage parent);

   /**
    * X absolute position
    * 
    * @return X absolute position
    */
   public int getAbsoluteX()
   {
      return this.xAbsolute;
   }

   /**
    * Y absolute position
    * 
    * @return Y absolute position
    */
   public int getAbsoluteY()
   {
      return this.yAbsolute;
   }

   /**
    * Last computed bounds
    * 
    * @return Last computed bounds
    */
   public final Rectangle getBounds()
   {
      return new Rectangle(this.bounds);
   }

   /**
    * Associated mouse listener
    * 
    * @return Associated mouse listener
    */
   public JHelpMouseListener getMouseListener()
   {
      return this.mouseListener;
   }

   /**
    * Container parent
    * 
    * @return Container parent
    */
   public JHelpContainer2D getParent()
   {
      return this.parent;
   }

   /**
    * Indicates if component is visible
    * 
    * @return {@code true} if component is visible
    */
   public boolean isVisible()
   {
      return this.visible;
   }

   /**
    * Define, change, or remove (On using {@code null}) the mouse listener
    * 
    * @param mouseListener
    *           New mouse listener OR {@code null} to remove mouse listener
    */
   public void setMouseListener(final JHelpMouseListener mouseListener)
   {
      this.mouseListener = mouseListener;
   }

   /**
    * Change component visibility
    * 
    * @param visible
    *           New visibility state
    */
   public void setVisible(final boolean visible)
   {
      this.visible = visible;
   }
}