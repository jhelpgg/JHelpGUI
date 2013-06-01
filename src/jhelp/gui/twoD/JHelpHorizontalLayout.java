package jhelp.gui.twoD;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;

import jhelp.util.list.Pair;

/**
 * Layout 2D for place components in horizontal
 * 
 * @author JHelp
 */
public class JHelpHorizontalLayout
      extends JHelpLayout
{
   /**
    * Horizontal layout constraints applied to content components
    * 
    * @author JHelp
    */
   public enum JHelpHorizontalLayoutConstraints
         implements JHelpConstraints
   {
      /** Attach to container bottom */
      BOTTOM,
      /** Center the component */
      CENTER,
      /** Force component have same height than container */
      EXPANDED,
      /** Attach to container top */
      TOP
   }

   /** Border height */
   private final int borderHeight;
   /** Border width */
   private final int borderWidth;
   /** Space between each component */
   private final int spaceBetweenComponents;

   /**
    * Create a new instance of JHelpHorizontalLayout
    */
   public JHelpHorizontalLayout()
   {
      this(1, 1, 1);
   }

   /**
    * Create a new instance of JHelpHorizontalLayout
    * 
    * @param spaceBetweenComponents
    *           Space between each component
    */
   public JHelpHorizontalLayout(final int spaceBetweenComponents)
   {
      this(1, 1, spaceBetweenComponents);
   }

   /**
    * Create a new instance of JHelpHorizontalLayout
    * 
    * @param borderWidth
    *           Border width
    * @param borderHeight
    *           Border height
    */
   public JHelpHorizontalLayout(final int borderWidth, final int borderHeight)
   {
      this(borderWidth, borderHeight, 1);
   }

   /**
    * Create a new instance of JHelpHorizontalLayout
    * 
    * @param borderWidth
    *           Border width
    * @param borderHeight
    *           Border height
    * @param spaceBetweenComponents
    *           Space between each component
    */
   public JHelpHorizontalLayout(final int borderWidth, final int borderHeight, final int spaceBetweenComponents)
   {
      if((borderHeight < 0) || (borderWidth < 0) || (spaceBetweenComponents < 0))
      {
         throw new IllegalArgumentException("One of specified value is negative !");
      }

      this.borderWidth = borderWidth;
      this.borderHeight = borderHeight;
      this.spaceBetweenComponents = spaceBetweenComponents;
   }

   /**
    * Indicates if constraints is compatible to this layout. In other words, if constraints is
    * {@link JHelpHorizontalLayoutConstraints} <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param constraints
    *           Constraints tested
    * @return {@code true} if compatible
    * @see jhelp.gui.twoD.JHelpLayout#compatible(jhelp.gui.twoD.JHelpConstraints)
    */
   @Override
   public boolean compatible(final JHelpConstraints constraints)
   {
      return (constraints != null) && (constraints instanceof JHelpHorizontalLayoutConstraints);
   }

   /**
    * Compute layout bounds and layout content components <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param children
    *           Content component
    * @param parentWidth
    *           Parent width
    * @param parentHeight
    *           Parent height
    * @return Computing bounds
    * @see jhelp.gui.twoD.JHelpLayout#computeBounds(java.util.ArrayList, int, int)
    */
   @Override
   public Rectangle computeBounds(final ArrayList<Pair<JHelpComponent2D, JHelpConstraints>> children, final int parentWidth, final int parentHeight)
   {
      int width = 0;
      int height = 0;
      Dimension prefferedSize;

      for(final Pair<JHelpComponent2D, JHelpConstraints> child : children)
      {
         if(child.element1.isVisible() == false)
         {
            continue;
         }

         prefferedSize = child.element1.getPrefrerredSize(-1, -1);

         switch((JHelpHorizontalLayoutConstraints) child.element2)
         {
            case TOP:
            case BOTTOM:
               height = Math.max(height, prefferedSize.height + this.borderHeight);

               if(width > 0)
               {
                  width += this.spaceBetweenComponents;
               }

               width += prefferedSize.width;

            break;
            case CENTER:
               height = Math.max(height, prefferedSize.height + (this.borderHeight * 2));

               if(width > 0)
               {
                  width += this.spaceBetweenComponents;
               }

               width += prefferedSize.width;

            break;
            case EXPANDED:
               height = Math.max(height, prefferedSize.height + (this.borderHeight * 2));

               if(width > 0)
               {
                  width += this.spaceBetweenComponents;
               }

               width += prefferedSize.width;

            break;
         }
      }

      height = Math.max(height, parentHeight);
      int x = this.borderWidth;

      for(final Pair<JHelpComponent2D, JHelpConstraints> child : children)
      {
         if(child.element1.isVisible() == false)
         {
            continue;
         }

         prefferedSize = child.element1.getPrefrerredSize(-1, -1);

         switch((JHelpHorizontalLayoutConstraints) child.element2)
         {
            case TOP:
               child.element1.setBounds(x, this.borderHeight, prefferedSize.width, prefferedSize.height);
            break;
            case BOTTOM:
               child.element1.setBounds(x, height - this.borderHeight - prefferedSize.height, prefferedSize.width, prefferedSize.height);
            break;
            case CENTER:
               child.element1.setBounds(x, (height - prefferedSize.height) >> 1, prefferedSize.width, prefferedSize.height);
            break;
            case EXPANDED:
               child.element1.setBounds(x, this.borderHeight, prefferedSize.width, height - (this.borderHeight * 2));
            break;
         }

         x += prefferedSize.width + this.spaceBetweenComponents;
      }

      return new Rectangle(0, 0, width + (this.borderWidth * 2), height);
   }

   /**
    * Border height
    * 
    * @return Border height
    */
   public int getBorderHeight()
   {
      return this.borderHeight;
   }

   /**
    * Border width
    * 
    * @return Border width
    */
   public int getBorderWidth()
   {
      return this.borderWidth;
   }

   /**
    * Space between content components
    * 
    * @return Space between content components
    */
   public int getSpaceBetweenComponents()
   {
      return this.spaceBetweenComponents;
   }
}