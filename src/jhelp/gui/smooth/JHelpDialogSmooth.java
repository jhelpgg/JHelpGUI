package jhelp.gui.smooth;

import java.awt.Dimension;
import java.awt.Rectangle;

import jhelp.gui.smooth.layout.JHelpConstraintsSmooth;
import jhelp.gui.smooth.layout.JHelpLayoutSmooth;
import jhelp.gui.smooth.shape.SmoothRoundRectangle;
import jhelp.util.gui.JHelpImage;
import jhelp.util.math.UtilMath;

/**
 * Dialog smooth.<br>
 * Dialogs are created with {@link DialogDecsription} in {@link JHelpFrameSmooth#createDialog(int)}
 * 
 * @author JHelp
 */
class JHelpDialogSmooth
{
   /**
    * Special layout for dialogs
    * 
    * @author JHelp
    */
   class DialogLayout
         implements JHelpLayoutSmooth
   {
      /**
       * Create a new instance of DialogLayout
       */
      DialogLayout()
      {
      }

      /**
       * Indicates if constraints are accepted <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param contraints
       *           Tested constraints
       * @return {@code true}
       * @see jhelp.gui.smooth.layout.JHelpLayoutSmooth#acceptConstraints(jhelp.gui.smooth.layout.JHelpConstraintsSmooth)
       */
      @Override
      public boolean acceptConstraints(final JHelpConstraintsSmooth contraints)
      {
         return true;
      }

      /**
       * Compute container preferred size <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param container
       *           Container
       * @return Container preferred size
       * @see jhelp.gui.smooth.layout.JHelpLayoutSmooth#computePreferredSize(jhelp.gui.smooth.JHelpPanelSmooth)
       */
      @Override
      public Dimension computePreferredSize(final JHelpPanelSmooth container)
      {
         final Dimension preferred = container.getChild(0).getPreferredSize();
         return new Dimension(preferred.width + JHelpDialogSmooth.MORE_SIZE, preferred.height + JHelpDialogSmooth.MORE_SIZE);
      }

      /**
       * Layout components inside container <br>
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
       *           Parent width
       * @param parentHeight
       *           Parent height
       * @return Suggested container bounds
       * @see jhelp.gui.smooth.layout.JHelpLayoutSmooth#layoutComponents(jhelp.gui.smooth.JHelpPanelSmooth, int, int, int, int)
       */
      @Override
      public Rectangle layoutComponents(final JHelpPanelSmooth container, final int x, final int y, final int parentWidth, final int parentHeight)
      {
         final JHelpComponentSmooth componentSmooth = container.getChild(0);
         componentSmooth.setBounds(x + (JHelpDialogSmooth.MORE_SIZE >> 1), y + (JHelpDialogSmooth.MORE_SIZE >> 1), parentWidth - JHelpDialogSmooth.MORE_SIZE,
               parentHeight - JHelpDialogSmooth.MORE_SIZE);
         return new Rectangle(x, y, parentWidth, parentHeight);
      }
   }

   /** Animation time in milliseconds */
   private static final long                   ANIMATION_TIME = 2048l;

   /** Constriants special dialog */
   private static final JHelpConstraintsSmooth CONSTRAINTS    = new JHelpConstraintsSmooth()
                                                              {
                                                              };
   /** Additional pixels to size */
   static final int                            MORE_SIZE      = 64;
   /** Main panel that carry dialog main component */
   private final JHelpPanelSmooth              mainPanel;
   /** Start animation time */
   private long                                startTime;
   /** Dialog X */
   private final int                           x;
   /** Dailog Y */
   private final int                           y;

   /**
    * Create a new instance of JHelpDialogSmooth
    * 
    * @param dialogDecsription
    *           Describe the dialog
    */
   JHelpDialogSmooth(final DialogDecsription dialogDecsription)
   {
      this.x = dialogDecsription.x;
      this.y = dialogDecsription.y;
      this.mainPanel = new JHelpPanelSmooth(new DialogLayout());
      this.mainPanel.addComponent(dialogDecsription.mainComponent, JHelpDialogSmooth.CONSTRAINTS);

      if(dialogDecsription.textureBackground != null)
      {
         this.mainPanel.setTextureBackground(dialogDecsription.textureBackground);
      }
      else if(dialogDecsription.paintBackground != null)
      {
         this.mainPanel.setPaintBackground(dialogDecsription.paintBackground);
      }
      else
      {
         this.mainPanel.setBackground(dialogDecsription.background);
      }

      this.mainPanel.setShadowColor(dialogDecsription.shadow);
      this.mainPanel.setShadowLevel(dialogDecsription.shadowLevel);
      this.mainPanel.setShape(new SmoothRoundRectangle());
   }

   /**
    * Draw the dialog
    * 
    * @param parent
    *           Image where draw
    */
   void drawDialog(final JHelpImage parent)
   {
      final Dimension size = this.mainPanel.getPreferredSize();
      final int width = Math.min(parent.getWidth(), size.width);
      final int height = Math.min(parent.getHeight(), size.height);

      double factor = 1;
      final long time = System.currentTimeMillis() - this.startTime;

      if(time < JHelpDialogSmooth.ANIMATION_TIME)
      {
         factor = UtilMath.interpolationSinus((double) time / ((double) JHelpDialogSmooth.ANIMATION_TIME));
      }

      int x = this.x;
      int y = this.y;
      final int w = (int) (width * factor);
      final int h = (int) (height * factor);

      if(x == DialogDecsription.CENTER_IN_PARENT)
      {
         x = (parent.getWidth() - w) >> 1;
      }

      if(y == DialogDecsription.CENTER_IN_PARENT)
      {
         y = (parent.getHeight() - h) >> 1;
      }

      if((x + w) > parent.getWidth())
      {
         x = parent.getWidth() - w;
      }

      if((y + h) > parent.getHeight())
      {
         y = parent.getHeight() - h;
      }

      this.mainPanel.setBounds(x, y, w, h);

      parent.pushClip(x - 10, y - 10, w + 20, h + 20);
      this.mainPanel.paint(parent, Math.max(0, x - 10), Math.max(0, y - 10), w, h, width + 20, height + 20);
      parent.popClip();
   }

   /**
    * Dialog root panel
    * 
    * @return Dialog root panel
    */
   JHelpContainerSmooth getRootPanel()
   {
      return this.mainPanel;
   }

   /**
    * Search component under mouse position
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    * @param rightButton
    *           Indicates if mouse right button is down
    * @return Component or {@code null} if not found
    */
   JHelpComponentSmooth obtainComponentUnder(final int x, final int y, final boolean rightButton)
   {
      return this.mainPanel.obtainComponentUnder(x, y, rightButton);
   }

   /**
    * Start animation dialog show at zero
    */
   void startAnimationComeToScreen()
   {
      this.startTime = System.currentTimeMillis();
   }
}