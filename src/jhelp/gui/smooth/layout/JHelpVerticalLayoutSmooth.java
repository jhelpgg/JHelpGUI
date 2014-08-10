package jhelp.gui.smooth.layout;

import java.awt.Dimension;
import java.awt.Rectangle;

import jhelp.gui.smooth.JHelpComponentSmooth;
import jhelp.gui.smooth.JHelpPanelSmooth;
import jhelp.util.list.Pair;

/**
 * Layout components vertically.<br>
 * It only accepts {@link JHelpVerticalConstraintsSmooth}
 * 
 * @author JHelp
 */
public class JHelpVerticalLayoutSmooth
      implements JHelpLayoutSmooth
{
   /**
    * Create a new instance of JHelpVerticalLayoutSmooth
    */
   public JHelpVerticalLayoutSmooth()
   {
   }

   /**
    * Indicates if constraints is accepted <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param constraints
    *           Tested constraints
    * @return {@code true} if constraints is accepted
    * @see jhelp.gui.smooth.layout.JHelpLayoutSmooth#acceptConstraints(jhelp.gui.smooth.layout.JHelpConstraintsSmooth)
    */
   @Override
   public boolean acceptConstraints(final JHelpConstraintsSmooth constraints)
   {
      return (constraints != null) && (constraints instanceof JHelpVerticalConstraintsSmooth);
   }

   /**
    * Compute container preferred size <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param container
    *           Container to have its preferred size
    * @return Container preferred size
    * @see jhelp.gui.smooth.layout.JHelpLayoutSmooth#computePreferredSize(jhelp.gui.smooth.JHelpPanelSmooth)
    */
   @Override
   public Dimension computePreferredSize(final JHelpPanelSmooth container)
   {
      int width = 0;
      int height = 0;
      final int count = container.getNumberOfChildren();
      JHelpComponentSmooth componentSmooth;
      Dimension preferred;

      for(int i = 0; i < count; i++)
      {
         componentSmooth = container.getChild(i);

         if(componentSmooth.isVisible() == true)
         {
            preferred = componentSmooth.getPreferredSize();
            width = Math.max(width, preferred.width);
            height += preferred.height;
         }
      }

      return new Dimension(width, height);
   }

   /**
    * Layout component in container and return suggested bounds <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param container
    *           Container to layout
    * @param x
    *           X
    * @param y
    *           Y
    * @param parentWidth
    *           Width
    * @param parentHeight
    *           Height
    * @return Suggested bounds
    * @see jhelp.gui.smooth.layout.JHelpLayoutSmooth#layoutComponents(jhelp.gui.smooth.JHelpPanelSmooth, int, int, int, int)
    */
   @Override
   public Rectangle layoutComponents(final JHelpPanelSmooth container, final int x, final int y, final int parentWidth, final int parentHeight)
   {
      int yy = y;
      final int count = container.getNumberOfChildren();
      JHelpComponentSmooth componentSmooth;
      JHelpVerticalConstraintsSmooth constraintsSmooth;
      Pair<JHelpComponentSmooth, JHelpConstraintsSmooth> pair;
      Dimension preferred;
      int width;

      for(int i = 0; i < count; i++)
      {
         pair = container.getChildAndConstraints(i);
         componentSmooth = pair.element1;

         if(componentSmooth.isVisible() == true)
         {
            constraintsSmooth = (JHelpVerticalConstraintsSmooth) pair.element2;
            preferred = componentSmooth.getPreferredSize();
            width = Math.min(preferred.width, parentWidth);

            switch(constraintsSmooth)
            {
               case CENTER:
                  componentSmooth.setBounds(x + ((parentWidth - width) >> 1), yy, width, preferred.height);
               break;
               case EXPAND:
                  componentSmooth.setBounds(x, yy, parentWidth, preferred.height);
               break;
               case LEFT:
                  componentSmooth.setBounds(x, yy, width, preferred.height);
               break;
               case RIGHT:
                  componentSmooth.setBounds(x + (parentWidth - width), yy, width, preferred.height);
               break;
            }

            yy += preferred.height;
         }
      }

      return new Rectangle(x, y, parentWidth, yy - y);
   }
}