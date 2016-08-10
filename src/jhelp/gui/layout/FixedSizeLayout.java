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
package jhelp.gui.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 * Layout for panel with one component.<br>
 * It ix the child size
 *
 * @author JHelp <br>
 */
public class FixedSizeLayout
      implements LayoutManager
{
   /** Maximum height */
   private final int fixedHeight;
   /** Maximum width */
   private final int fixedWidth;

   /**
    * Create a new instance of LimitedSizeLayout
    *
    * @param fixedWidth
    *           Fixed width.
    * @param fixedHeight
    *           Fixed height.
    */
   public FixedSizeLayout(final int fixedWidth, final int fixedHeight)
   {
      this.fixedWidth = fixedWidth;
      this.fixedHeight = fixedHeight;
   }

   /**
    * Add component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param name
    *           Component name
    * @param comp
    *           Component to add
    * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String, java.awt.Component)
    */
   @Override
   public void addLayoutComponent(final String name, final Component comp)
   {
      // Nothing to do
   }

   /**
    * Layout a container <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param parent
    *           Container to layout
    * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
    */
   @Override
   public void layoutContainer(final Container parent)
   {
      final Dimension parentSize = parent.getSize();
      final Insets insets = parent.getInsets();
      parentSize.width = parentSize.width - insets.right - insets.right;
      parentSize.height = parentSize.height - insets.top - insets.bottom;

      synchronized(parent.getTreeLock())
      {
         if(parent.getComponentCount() == 0)
         {
            return;
         }

         final Component component = parent.getComponent(0);

         if(component.isVisible() == false)
         {
            return;
         }

         component.setBounds(insets.left, insets.right, this.fixedWidth, this.fixedHeight);
      }
   }

   /**
    * Compute minimum size of container <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param parent
    *           Container
    * @return Minium size
    * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
    */
   @Override
   public Dimension minimumLayoutSize(final Container parent)
   {
      return new Dimension(this.fixedWidth, this.fixedHeight);
   }

   /**
    * Compute preferred size of a container <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param parent
    *           Container
    * @return Preferred size
    * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
    */
   @Override
   public Dimension preferredLayoutSize(final Container parent)
   {
      return new Dimension(this.fixedWidth, this.fixedHeight);
   }

   /**
    * Remove a component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param comp
    *           Component to remove
    * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
    */
   @Override
   public void removeLayoutComponent(final Component comp)
   {
      // Nothing to do
   }
}