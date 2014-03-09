package jhelp.gui.twoD;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jhelp.gui.JHelpMouseListener;
import jhelp.util.gui.JHelpImage;
import jhelp.util.list.Pair;

/**
 * 2D panel, to place some components inside on using a layout {@link JHelpLayout}
 * 
 * @author JHelp
 */
public class JHelpPanel2D
      extends JHelpContainer2D
{
   /** Layout to use */
   private final JHelpLayout                                           layout;
   /** Children associated */
   protected final ArrayList<Pair<JHelpComponent2D, JHelpConstraints>> children;

   /**
    * Create a new instance of JHelpPanel2D with {@link JHelpBorderLayout}
    */
   public JHelpPanel2D()
   {
      this(new JHelpBorderLayout());
   }

   /**
    * Create a new instance of JHelpPanel2D
    * 
    * @param layout
    *           Layout to use
    */
   public JHelpPanel2D(final JHelpLayout layout)
   {
      if(layout == null)
      {
         throw new NullPointerException("layout musn't be null");
      }

      this.layout = layout;
      this.children = new ArrayList<Pair<JHelpComponent2D, JHelpConstraints>>();
   }

   /**
    * Add component inside the panel
    * 
    * @param component
    *           Component to add
    * @param constraints
    *           Layout constraints to place the component
    * @param invalidate
    *           Indicates if this add invalidate the panel
    */
   void addComponent2D(final JHelpComponent2D component, final JHelpConstraints constraints, final boolean invalidate)
   {
      if(this.layout.compatible(constraints) == false)
      {
         throw new IllegalArgumentException("The constraints " + constraints + " not compatible with layout " + this.layout);
      }

      component.setParent(this);

      synchronized(this.children)
      {
         this.children.add(new Pair<JHelpComponent2D, JHelpConstraints>(component, constraints));
      }

      if(invalidate == true)
      {
         this.invalidate();
      }
   }

   /**
    * Compute panel preferred size <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param parentWidth
    *           Parent with, -1 if unknown
    * @param parentHeight
    *           Parent height, -1 if unknown
    * @return Preferred size
    * @see jhelp.gui.twoD.JHelpComponent2D#computePreferredSize(int, int)
    */
   @Override
   protected Dimension computePreferredSize(final int parentWidth, final int parentHeight)
   {
      if(this.isVisible() == false)
      {
         return new Dimension();
      }
      Rectangle bounds = null;
      synchronized(this.children)
      {
         bounds = this.layout.computeBounds(this.children, parentWidth, parentHeight);
      }
      this.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
      return bounds.getSize();
   }

   /**
    * Called when mouse is over the panel and give a pair of component and its associated listener that corresponds to the mouse
    * position <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    * @return the pair of component and listener, or {@code null} if there are no such mouse sensitive component in given
    *         position
    * @see jhelp.gui.twoD.JHelpComponent2D#mouseOver(int, int)
    */
   @Override
   protected Pair<JHelpComponent2D, JHelpMouseListener> mouseOver(final int x, final int y)
   {
      if(this.isVisible() == false)
      {
         return null;
      }

      Pair<JHelpComponent2D, JHelpMouseListener> pair;

      synchronized(this.children)
      {
         for(final Pair<JHelpComponent2D, JHelpConstraints> child : this.children)
         {
            if(child.element1.isVisible() == false)
            {
               continue;
            }

            pair = child.element1.mouseOver(x, y);

            if(pair != null)
            {
               return pair;
            }
         }
      }

      return null;
   }

   /**
    * Add component inside the panel
    * 
    * @param component
    *           Component to add
    * @param constraints
    *           Constraints to use
    */
   public void addComponent2D(final JHelpComponent2D component, final JHelpConstraints constraints)
   {
      this.addComponent2D(component, constraints, true);
   }

   /**
    * Panel inside components <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Panel inside components
    * @see jhelp.gui.twoD.JHelpContainer2D#children()
    */
   @Override
   public List<JHelpComponent2D> children()
   {
      final ArrayList<JHelpComponent2D> children = new ArrayList<JHelpComponent2D>();

      synchronized(this.children)
      {
         for(final Pair<JHelpComponent2D, JHelpConstraints> child : this.children)
         {
            children.add(child.element1);
         }
      }

      return Collections.unmodifiableList(children);
   }

   /**
    * Remove all components from the panel
    */
   public void clearComponents()
   {
      synchronized(this.children)
      {
         final int size = this.children.size();
         Pair<JHelpComponent2D, JHelpConstraints> child;

         for(int i = size - 1; i >= 0; i--)
         {
            child = this.children.get(i);
            child.element1.willRemove();
            this.children.remove(i);
            child.element1.removeParent();
         }
      }
   }

   /**
    * Draw the panel <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           X location in parent
    * @param y
    *           Y location in parent
    * @param parent
    *           Parent where draw
    * @see jhelp.gui.twoD.JHelpComponent2D#paint(int, int, jhelp.util.gui.JHelpImage)
    */
   @Override
   public void paint(final int x, final int y, final JHelpImage parent)
   {
      final Rectangle panelBounds = this.getBounds();
      Rectangle bounds;
      int bx, by;
      final int x0 = panelBounds.x;
      final int y0 = panelBounds.y;
      final int xx = Math.min(parent.getWidth(), panelBounds.x + panelBounds.width);
      final int yy = Math.min(parent.getHeight(), panelBounds.y + panelBounds.height);
      synchronized(this.children)
      {
         for(final Pair<JHelpComponent2D, JHelpConstraints> child : this.children)
         {
            if(child.element1.isVisible() == false)
            {
               continue;
            }

            bounds = child.element1.getBounds();
            bx = bounds.x + x;
            by = bounds.y + y;

            if((y0 < (by + bounds.height)) && (by < yy) && (x0 < (bx + bounds.width)) && (bx < xx))
            {
               child.element1.paintInternal(bx, by, parent, x, y, panelBounds.width, panelBounds.height);
            }
         }
      }
   }

   /**
    * Remove a component from the panel
    * 
    * @param component
    *           Component to remove
    */
   public void removeComponent2D(final JHelpComponent2D component)
   {
      synchronized(this.children)
      {
         final int size = this.children.size();
         Pair<JHelpComponent2D, JHelpConstraints> child;

         for(int i = 0; i < size; i++)
         {
            child = this.children.get(i);

            if(child.element1.equals(component) == true)
            {
               child.element1.willRemove();
               this.children.remove(i);
               child.element1.removeParent();

               break;
            }
         }
      }
   }
}