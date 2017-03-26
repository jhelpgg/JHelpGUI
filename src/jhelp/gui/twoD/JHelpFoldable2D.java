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
package jhelp.gui.twoD;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jhelp.gui.JHelpMouseListener;
import jhelp.util.gui.Bounds;
import jhelp.util.gui.JHelpGradientHorizontal;
import jhelp.util.gui.JHelpGradientVertical;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpPaint;
import jhelp.util.list.Pair;
import jhelp.util.thread.Mutex;
import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedVerySimpleTask;

/**
 * 2D container that can be fold
 * 
 * @author JHelp
 */
public class JHelpFoldable2D
      extends JHelpContainer2D
{
   /** Folding area position */
   public enum FoldingAreaPosition
   {
      /** Under the content */
      BOTTOM,
      /** Left to the content */
      LEFT,
      /** Right to the content */
      RIGHT,
      /** Above the content */
      TOP
   }

   /** Folding speed */
   private static final int                 TIME              = 1;
   /** Number of step for folding */
   static int                               valueMax          = 512;
   /** Default folding area */
   public static final FoldingAreaInterface DEFAULT_FOLDING_AREA;
   /** Default paint in horizontal way */
   public static final JHelpPaint           DEFAULT_PAINT_HORIZONTAL;
   /** Default paint in vertical way */
   public static final JHelpPaint           DEFAULT_PAINT_VERTICAL;
   /** Number maximum of folding steps */
   public static final int                  MAXIMUM_VALUE_MAX = 1 << 14;
   /** Number minimum of folding steps */
   public static final int                  MINIMUM_VALUE_MAX = 1;
   static
   {
      final JHelpGradientVertical gradientVertical = new JHelpGradientVertical(0xFFFFC800, 0xFFFFC800);
      gradientVertical.addColor(50, 0xFFFFFF00);
      DEFAULT_PAINT_HORIZONTAL = gradientVertical;

      final JHelpGradientHorizontal gradientHorizontal = new JHelpGradientHorizontal(0xFFFFC800, 0xFFFFC800);
      gradientHorizontal.addColor(50, 0xFFFFFF00);
      DEFAULT_PAINT_VERTICAL = gradientHorizontal;

      DEFAULT_FOLDING_AREA = new FoldingAreaSimple();
   }

   /**
    * Number of folding steps
    * 
    * @return Number of folding steps
    */
   public static int getValueMax()
   {
      return JHelpFoldable2D.valueMax;
   }

   /**
    * Change number of folding step
    * 
    * @param valueMax
    *           New number of folding steps
    */
   public static void setValueMax(final int valueMax)
   {
      if((valueMax < JHelpFoldable2D.MINIMUM_VALUE_MAX) || (valueMax > JHelpFoldable2D.MAXIMUM_VALUE_MAX))
      {
         throw new IllegalArgumentException("valueMax must be in [" + JHelpFoldable2D.MINIMUM_VALUE_MAX + ", " + JHelpFoldable2D.MAXIMUM_VALUE_MAX + "] not "
               + valueMax);
      }

      JHelpFoldable2D.valueMax = valueMax;
   }

   /** Describes how draw folding area */
   private final FoldingAreaInterface    foldingArea;
   /** Describes folding area location */
   private final FoldingAreaPosition     foldingAreaPosition;
   /** Limit of component height */
   private int                           limitHeight;
   /** Limit of component width */
   private int                           limitWidth;
   /** Listeners of fold sate change */
   private final ArrayList<FoldListener> listeners;
   /** Mouse event manager */
   private final JHelpMouseListener      mouseListener = new JHelpMouseListener()
                                                       {
                                                          /**
                                                           * Called when mouse clicked <br>
                                                           * <br>
                                                           * <b>Parent documentation:</b><br>
                                                           * {@inheritDoc}
                                                           * 
                                                           * @param e
                                                           *           Mouse event description
                                                           * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
                                                           */
                                                          @Override
                                                          public void mouseClicked(final MouseEvent e)
                                                          {
                                                             JHelpFoldable2D.this.changeFold();
                                                          }

                                                          /**
                                                           * Called when mouse dragged <br>
                                                           * <br>
                                                           * <b>Parent documentation:</b><br>
                                                           * {@inheritDoc}
                                                           * 
                                                           * @param e
                                                           *           Mouse event description
                                                           * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
                                                           */
                                                          @Override
                                                          public void mouseDragged(final MouseEvent e)
                                                          {
                                                          }

                                                          /**
                                                           * Called when mouse entered <br>
                                                           * <br>
                                                           * <b>Parent documentation:</b><br>
                                                           * {@inheritDoc}
                                                           * 
                                                           * @param e
                                                           *           Mouse event description
                                                           * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
                                                           */
                                                          @Override
                                                          public void mouseEntered(final MouseEvent e)
                                                          {
                                                          }

                                                          /**
                                                           * Called when mouse exited <br>
                                                           * <br>
                                                           * <b>Parent documentation:</b><br>
                                                           * {@inheritDoc}
                                                           * 
                                                           * @param e
                                                           *           Mouse event description
                                                           * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
                                                           */
                                                          @Override
                                                          public void mouseExited(final MouseEvent e)
                                                          {
                                                          }

                                                          /**
                                                           * Called when mouse moved <br>
                                                           * <br>
                                                           * <b>Parent documentation:</b><br>
                                                           * {@inheritDoc}
                                                           * 
                                                           * @param e
                                                           *           Mouse event description
                                                           * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
                                                           */
                                                          @Override
                                                          public void mouseMoved(final MouseEvent e)
                                                          {
                                                          }

                                                          /**
                                                           * Called when mouse pressed <br>
                                                           * <br>
                                                           * <b>Parent documentation:</b><br>
                                                           * {@inheritDoc}
                                                           * 
                                                           * @param e
                                                           *           Mouse event description
                                                           * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
                                                           */
                                                          @Override
                                                          public void mousePressed(final MouseEvent e)
                                                          {
                                                          }

                                                          /**
                                                           * Called when mouse released <br>
                                                           * <br>
                                                           * <b>Parent documentation:</b><br>
                                                           * {@inheritDoc}
                                                           * 
                                                           * @param e
                                                           *           Mouse event description
                                                           * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
                                                           */
                                                          @Override
                                                          public void mouseReleased(final MouseEvent e)
                                                          {
                                                          }

                                                          /**
                                                           * Called when mouse wheel moved <br>
                                                           * <br>
                                                           * <b>Parent documentation:</b><br>
                                                           * {@inheritDoc}
                                                           * 
                                                           * @param e
                                                           *           Mouse event description
                                                           * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
                                                           */
                                                          @Override
                                                          public void mouseWheelMoved(final MouseWheelEvent e)
                                                          {
                                                          }
                                                       };
   /** Task for update folding animation */
   private final ThreadedVerySimpleTask  updateFold    = new ThreadedVerySimpleTask()
                                                       {
                                                          /**
                                                           * Update folding animation <br>
                                                           * <br>
                                                           * <b>Parent documentation:</b><br>
                                                           * {@inheritDoc}
                                                           * 
                                                           * @see jhelp.util.thread.ThreadedVerySimpleTask#doVerySimpleAction()
                                                           */
                                                          @Override
                                                          protected void doVerySimpleAction()
                                                          {
                                                             JHelpFoldable2D.this.mutex.lock();
                                                             boolean alertListeners = false;

                                                             JHelpFoldable2D.this.onFolding = true;

                                                             JHelpFoldable2D.this.foldPercent += JHelpFoldable2D.this.foldWay;

                                                             if((JHelpFoldable2D.this.foldPercent > 0)
                                                                   && (JHelpFoldable2D.this.foldPercent < JHelpFoldable2D.valueMax))
                                                             {
                                                                ThreadManager.THREAD_MANAGER.delayedThread(this, null, JHelpFoldable2D.TIME);
                                                             }
                                                             else
                                                             {
                                                                JHelpFoldable2D.this.onFolding = false;
                                                                alertListeners = true;
                                                             }

                                                             if(alertListeners)
                                                             {
                                                                JHelpFoldable2D.this.fireFoldChanged();
                                                             }

                                                             JHelpFoldable2D.this.invalidate();
                                                             JHelpFoldable2D.this.mutex.unlock();
                                                          }
                                                       };
   /** Component content */
   JHelpComponent2D                      component2d;
   /** Folding "percent" */
   int                                   foldPercent;
   /** Way of folding */
   int                                   foldWay;
   /** Synchronization mutex */
   final Mutex                           mutex;
   /** Indicates that folding/unfolding on process */
   boolean                               onFolding;

   /**
    * Create a new instance of JHelpFoldable2D
    * 
    * @param component2d
    *           Component content
    */
   public JHelpFoldable2D(final JHelpComponent2D component2d)
   {
      this(component2d, JHelpFoldable2D.DEFAULT_FOLDING_AREA, FoldingAreaPosition.TOP);
   }

   /**
    * Create a new instance of JHelpFoldable2D
    * 
    * @param component2d
    *           Component content
    * @param foldingArea
    *           Describes how draw folding header
    */
   public JHelpFoldable2D(final JHelpComponent2D component2d, final FoldingAreaInterface foldingArea)
   {
      this(component2d, foldingArea, FoldingAreaPosition.TOP);
   }

   /**
    * Create a new instance of JHelpFoldable2D
    * 
    * @param component2d
    *           Component content
    * @param foldingArea
    *           Describes how draw fold header
    * @param foldingAreaPosition
    *           Position of folding
    */
   public JHelpFoldable2D(final JHelpComponent2D component2d, final FoldingAreaInterface foldingArea, final FoldingAreaPosition foldingAreaPosition)
   {
      if(foldingArea == null)
      {
         throw new NullPointerException("foldingArea musn't be null");
      }

      if(foldingAreaPosition == null)
      {
         throw new NullPointerException("foldingAreaPosition musn't be null");
      }

      component2d.setParent(this);

      this.listeners = new ArrayList<FoldListener>();
      this.foldingArea = foldingArea;
      this.foldingAreaPosition = foldingAreaPosition;
      this.component2d = component2d;
      this.foldPercent = JHelpFoldable2D.valueMax;
      this.foldWay = 1;
      this.onFolding = false;
      this.mutex = new Mutex();
      this.setMouseListener(this.mouseListener);
      this.limitWidth = Integer.MAX_VALUE;
      this.limitHeight = Integer.MAX_VALUE;
   }

   /**
    * Create a new instance of JHelpFoldable2D
    * 
    * @param component2d
    *           Component content
    * @param foldingAreaPosition
    *           Position of folding
    */
   public JHelpFoldable2D(final JHelpComponent2D component2d, final FoldingAreaPosition foldingAreaPosition)
   {
      this(component2d, JHelpFoldable2D.DEFAULT_FOLDING_AREA, foldingAreaPosition);
   }

   /**
    * Signal to listeners that the fold stte just changed
    */
   void fireFoldChanged()
   {
      final boolean isFold = this.foldWay < 0;

      for(final FoldListener foldListener : this.listeners)
      {
         foldListener.foldChanged(this, isFold);
      }
   }

   /**
    * Signal to listeners that fold state will change
    */
   void fireFoldWillChange()
   {
      final boolean futureFold = this.foldWay < 0;

      for(final FoldListener foldListener : this.listeners)
      {
         foldListener.willFoldChanged(this, futureFold);
      }
   }

   /**
    * Compute component preferred size <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param parentWidth
    *           Container width
    * @param parentHeight
    *           Container height
    * @return Component preferred size
    * @see jhelp.gui.twoD.JHelpComponent2D#getPreferredSize(int, int)
    */
   @Override
   protected Dimension computePreferredSize(final int parentWidth, final int parentHeight)
   {
      if(!this.isVisible())
      {
         return new Dimension();
      }

      final Dimension dimension = this.component2d.getPreferredSize(parentWidth, parentHeight);
      final Dimension minimum = this.foldingArea.minimumSize();

      dimension.width = Math.min(dimension.width, this.limitWidth);
      dimension.height = Math.min(dimension.height, this.limitHeight);

      int x = 0;
      int y = 0;

      int width = minimum.width;
      int height = minimum.height;

      switch(this.foldingAreaPosition)
      {
         case BOTTOM:
            width = Math.max(width, dimension.width);
            height += (dimension.height * this.foldPercent) / JHelpFoldable2D.valueMax;
         break;
         case TOP:
            y = minimum.height;
            width = Math.max(width, dimension.width);
            height += (dimension.height * this.foldPercent) / JHelpFoldable2D.valueMax;
         break;
         case LEFT:
            x = minimum.width;
            width += (dimension.width * this.foldPercent) / JHelpFoldable2D.valueMax;
            height = Math.max(height, dimension.height);
         break;
         case RIGHT:
            width += (dimension.width * this.foldPercent) / JHelpFoldable2D.valueMax;
            height = Math.max(height, dimension.height);
         break;
      }

      this.component2d.setBounds(x, y, dimension.width, dimension.height);

      return new Dimension(width, height);
   }

   /**
    * Obtain component and associated mouse listener at mouse position <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           Mouse X
    * @param y
    *           Mouse Y
    * @return Component and associated mouse listener
    * @see jhelp.gui.twoD.JHelpComponent2D#mouseOver(int, int)
    */
   @Override
   protected Pair<JHelpComponent2D, JHelpMouseListener> mouseOver(final int x, final int y)
   {
      final Pair<JHelpComponent2D, JHelpMouseListener> pair = super.mouseOver(x, y);

      if(pair == null)
      {
         return null;
      }

      final Bounds bounds = this.getScreenBounds();
      final Dimension minimum = this.foldingArea.minimumSize();

      boolean overPanel = false;

      switch(this.foldingAreaPosition)
      {
         case BOTTOM:
            if(y < (bounds.getyMax() - minimum.height))
            {
               overPanel = true;
            }
         break;
         case LEFT:
            if(x > (bounds.getxMin() + minimum.width))
            {
               overPanel = true;
            }
         break;
         case RIGHT:
            if(x < (bounds.getxMax() - minimum.width))
            {
               overPanel = true;
            }
         break;
         case TOP:
            if(y > (bounds.getyMin() + minimum.height))
            {
               overPanel = true;
            }
         break;
      }

      if(!this.component2d.isVisible())
      {
         return null;
      }

      if(overPanel)
      {
         return this.component2d.mouseOver(x, y);
      }

      return pair;
   }

   /**
    * Paint the component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param x
    *           X position
    * @param y
    *           Y position
    * @param parent
    *           Image where draw
    * @see jhelp.gui.twoD.JHelpComponent2D#paint(int, int, jhelp.util.gui.JHelpImage)
    */
   @Override
   protected void paint(final int x, final int y, final JHelpImage parent)
   {
      final Rectangle bounds = this.getBounds();
      parent.startDrawMode();
      int xPanel = 0;
      int yPanel = 0;
      int xx = 0;
      int yy = 0;
      int widthPanel = 0;
      int heightPanel = 0;
      final Dimension minimum = this.foldingArea.minimumSize();
      final Rectangle panelBounds = this.component2d.getBounds();

      switch(this.foldingAreaPosition)
      {
         case BOTTOM:
            this.foldingArea.paintArea(x, (y + bounds.height) - minimum.height, bounds.width, minimum.height, parent, this.foldWay < 0,
                  this.foldingAreaPosition);
            widthPanel = bounds.width;
            heightPanel = bounds.height - minimum.height;
            xx = 0;
            yy = heightPanel - panelBounds.height;
            xPanel = 0;
            yPanel = 0;
         break;
         case LEFT:
            this.foldingArea.paintArea(x, y, minimum.width, bounds.height, parent, this.foldWay < 0, this.foldingAreaPosition);
            widthPanel = bounds.width - minimum.width;
            heightPanel = bounds.height;
            xx = 0;
            yy = 0;
            xPanel = minimum.width;
            yPanel = 0;
         break;
         case RIGHT:
            this.foldingArea.paintArea((x + bounds.width) - minimum.width, y, minimum.width, bounds.height, parent, this.foldWay < 0, this.foldingAreaPosition);
            widthPanel = bounds.width - minimum.width;
            heightPanel = bounds.height;
            xx = widthPanel - panelBounds.width;
            yy = 0;
            xPanel = 0;
            yPanel = 0;
         break;
         case TOP:
            this.foldingArea.paintArea(x, y, bounds.width, minimum.height, parent, this.foldWay < 0, this.foldingAreaPosition);
            widthPanel = bounds.width;
            heightPanel = bounds.height - minimum.height;
            xx = 0;
            yy = 0;
            xPanel = 0;
            yPanel = minimum.height;
         break;
      }

      if((widthPanel > 0) && (heightPanel > 0) && (this.component2d.isVisible()))
      {
         final JHelpImage image = new JHelpImage(widthPanel, heightPanel);
         image.startDrawMode();
         this.component2d.paintInternal(xx, yy, image);
         image.endDrawMode();

         parent.drawImage(x + xPanel, y + yPanel, image);
         this.component2d.translateAfterDraw(x + xPanel, y + yPanel);
      }
   }

   /**
    * Change fold state
    */
   public void changeFold()
   {
      this.mutex.lock();

      this.foldWay = -this.foldWay;
      this.fireFoldWillChange();

      if(!this.onFolding)
      {
         this.onFolding = true;
         ThreadManager.THREAD_MANAGER.delayedThread(this.updateFold, null, JHelpFoldable2D.TIME);
      }

      this.mutex.unlock();
   }

   /**
    * Children list. Here just contains the content component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Children list
    * @see jhelp.gui.twoD.JHelpContainer2D#children()
    */
   @Override
   public List<JHelpComponent2D> children()
   {
      final ArrayList<JHelpComponent2D> children = new ArrayList<JHelpComponent2D>(1);
      children.add(this.component2d);

      return Collections.unmodifiableList(children);
   }

   /**
    * Content component
    * 
    * @return Content component
    */
   public JHelpComponent2D getComponent2D()
   {
      return this.component2d;
   }

   /**
    * Component limit height
    * 
    * @return Component limit height
    */
   public int getLimitHeight()
   {
      return this.limitHeight;
   }

   /**
    * Component limit width
    * 
    * @return Component limit width
    */
   public int getLimitWidth()
   {
      return this.limitWidth;
   }

   /**
    * Register a listener for know when fold state change
    * 
    * @param foldListener
    *           Listener to register
    */
   public void registerFoldListener(final FoldListener foldListener)
   {
      JHelpFoldable2D.this.mutex.lock();
      this.listeners.add(foldListener);
      JHelpFoldable2D.this.mutex.unlock();
   }

   /**
    * Change the component limit height
    * 
    * @param limitHeight
    *           New height limit (Use {@link Integer#MAX_VALUE} for no limit)
    */
   public void setLimitHeight(final int limitHeight)
   {
      this.limitHeight = Math.max(128, limitHeight);
   }

   /**
    * Change the component limit width
    * 
    * @param limitWidth
    *           New width limit (Use {@link Integer#MAX_VALUE} for no limit)
    */
   public void setLimitWidth(final int limitWidth)
   {
      this.limitWidth = Math.max(128, limitWidth);
   }

   /**
    * Unregister a listener for know when fold state change
    * 
    * @param foldListener
    *           Listener to unregister
    */
   public void unregisterFoldListener(final FoldListener foldListener)
   {
      JHelpFoldable2D.this.mutex.lock();
      this.listeners.remove(foldListener);
      JHelpFoldable2D.this.mutex.unlock();
   }
}