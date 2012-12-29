package jhelp.gui.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import jhelp.util.gui.UtilGUI;

/**
 * Layout for center one component in container <br>
 * <br>
 * Last modification : 31 janv. 2009<br>
 * Version 0.0.0<br>
 * 
 * @author JHelp
 */
public class CenterLayout
      implements LayoutManager
{
   /**
    * Constructs CenterLayout
    */
   public CenterLayout()
   {
   }

   /**
    * Add component to the layout.<br>
    * Do nothing here
    * 
    * @param name
    *           Constraints description
    * @param comp
    *           Component to add
    * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String, java.awt.Component)
    */
   @Override
   public void addLayoutComponent(final String name, final Component comp)
   {
   }

   /**
    * Layout a container
    * 
    * @param parent
    *           Container to layout
    * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
    */
   @Override
   public void layoutContainer(final Container parent)
   {
      if(parent.getComponentCount() < 1)
      {
         return;
      }
      final Dimension dim = parent.getSize();
      final Component component = parent.getComponent(0);
      final Dimension pref = UtilGUI.computePreferredDimension(component);
      component.setBounds((dim.width - pref.width) / 2, (dim.height - pref.height) / 2, pref.width, pref.height);
   }

   /**
    * Compute minimum size
    * 
    * @param parent
    *           Container who want's know it's minimum size
    * @return Minimum size
    * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
    */
   @Override
   public Dimension minimumLayoutSize(final Container parent)
   {
      if(parent.getComponentCount() < 1)
      {
         return new Dimension(10, 10);
      }
      final Component component = parent.getComponent(0);
      return UtilGUI.computeMinimumDimension(component);
   }

   /**
    * Compute preferred size
    * 
    * @param parent
    *           Container who want's know it's preferred size
    * @return Preferred size
    * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
    */
   @Override
   public Dimension preferredLayoutSize(final Container parent)
   {
      if(parent.getComponentCount() < 1)
      {
         return new Dimension(10, 10);
      }
      final Component component = parent.getComponent(0);
      return UtilGUI.computePreferredDimension(component);
   }

   /**
    * Remove component for layout.<br>
    * Do nothing here
    * 
    * @param comp
    *           Component to remove
    * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
    */
   @Override
   public void removeLayoutComponent(final Component comp)
   {
   }
}