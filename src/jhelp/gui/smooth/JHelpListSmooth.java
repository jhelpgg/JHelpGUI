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
package jhelp.gui.smooth;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import jhelp.gui.smooth.event.JHelpListSmoothModelListener;
import jhelp.gui.smooth.event.JHelpListSmoothSelectListener;
import jhelp.gui.smooth.event.SmoothKeyInformation;
import jhelp.gui.smooth.event.SmoothMouseInformation;
import jhelp.gui.smooth.event.SmoothMouseListener;
import jhelp.gui.smooth.layout.JHelpConstraintsSmooth;
import jhelp.gui.smooth.layout.JHelpHorizontalConstraintsSmooth;
import jhelp.gui.smooth.layout.JHelpHorizontalLayoutSmooth;
import jhelp.gui.smooth.layout.JHelpLayoutSmooth;
import jhelp.gui.smooth.layout.JHelpVerticalConstraintsSmooth;
import jhelp.gui.smooth.layout.JHelpVerticalLayoutSmooth;
import jhelp.gui.smooth.model.JHelpListSmoothModel;
import jhelp.gui.smooth.renderer.JHelpListSmoothRenderer;

/**
 * List of elements.<br>
 * List have a model {@link JHelpListSmoothModel} that carry data, a {@link JHelpListSmoothRenderer} to know how draw a list
 * cell and a way of scroll.<br>
 * A selection event an element usually the "ENTER" or double click, but it is also possible to have select event by only one
 * click
 * 
 * @author JHelp
 * @param <ELEMENT>
 *           Elements carry type
 */
public class JHelpListSmooth<ELEMENT>
      extends JHelpScrollPaneSmooth
{
   /**
    * Manage list events : model change and mouse events
    * 
    * @author JHelp
    */
   class EventManager
         implements JHelpListSmoothModelListener<ELEMENT>, SmoothMouseListener
   {
      /**
       * Create a new instance of EventManager
       */
      EventManager()
      {
      }

      /**
       * Called when model completely change <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param model
       *           Changed model
       * @see jhelp.gui.smooth.event.JHelpListSmoothModelListener#completlyChanged(jhelp.gui.smooth.model.JHelpListSmoothModel)
       */
      @Override
      public void completlyChanged(final JHelpListSmoothModel<ELEMENT> model)
      {
         JHelpListSmooth.this.updateComplete();
      }

      /**
       * Called when element add in model <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param model
       *           Model changed
       * @param index
       *           Element insert index
       * @see jhelp.gui.smooth.event.JHelpListSmoothModelListener#elementAdded(jhelp.gui.smooth.model.JHelpListSmoothModel, int)
       */
      @Override
      public void elementAdded(final JHelpListSmoothModel<ELEMENT> model, final int index)
      {
         JHelpListSmooth.this.insertElement(index);
      }

      /**
       * Called when element removed from model <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param model
       *           Model changed
       * @param index
       *           Removed element index
       * @see jhelp.gui.smooth.event.JHelpListSmoothModelListener#elementRemoved(jhelp.gui.smooth.model.JHelpListSmoothModel,
       *      int)
       */
      @Override
      public void elementRemoved(final JHelpListSmoothModel<ELEMENT> model, final int index)
      {
         JHelpListSmooth.this.removeElement(index);
      }

      /**
       * Called when mouse clicked <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseInformation
       *           Mouse event description
       * @see jhelp.gui.smooth.event.SmoothMouseListener#mouseClicked(jhelp.gui.smooth.event.SmoothMouseInformation)
       */
      @Override
      public void mouseClicked(final SmoothMouseInformation mouseInformation)
      {
         JHelpListSmooth.this.clickOn(mouseInformation.getComponentSmooth());

         if((JHelpListSmooth.this.selectBySimpleClick) || (mouseInformation.getClickcount() > 1))
         {
            JHelpListSmooth.this.fireSelectElement();
         }
      }

      /**
       * Called when mouse dragged <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseInformation
       *           Mouse event description
       * @see jhelp.gui.smooth.event.SmoothMouseListener#mouseDragged(jhelp.gui.smooth.event.SmoothMouseInformation)
       */
      @Override
      public void mouseDragged(final SmoothMouseInformation mouseInformation)
      {
      }

      /**
       * Called when mouse enter <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseInformation
       *           Mouse event description
       * @see jhelp.gui.smooth.event.SmoothMouseListener#mouseEnter(jhelp.gui.smooth.event.SmoothMouseInformation)
       */
      @Override
      public void mouseEnter(final SmoothMouseInformation mouseInformation)
      {
      }

      /**
       * Called when mouse exit <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseInformation
       *           Mouse event description
       * @see jhelp.gui.smooth.event.SmoothMouseListener#mouseExit(jhelp.gui.smooth.event.SmoothMouseInformation)
       */
      @Override
      public void mouseExit(final SmoothMouseInformation mouseInformation)
      {
      }

      /**
       * Called when mouse moved <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseInformation
       *           Mouse event description
       * @see jhelp.gui.smooth.event.SmoothMouseListener#mouseMoved(jhelp.gui.smooth.event.SmoothMouseInformation)
       */
      @Override
      public void mouseMoved(final SmoothMouseInformation mouseInformation)
      {
      }

      /**
       * Called when mouse pressed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseInformation
       *           Mouse event description
       * @see jhelp.gui.smooth.event.SmoothMouseListener#mousePressed(jhelp.gui.smooth.event.SmoothMouseInformation)
       */
      @Override
      public void mousePressed(final SmoothMouseInformation mouseInformation)
      {
      }

      /**
       * Called when mouse released <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseInformation
       *           Mouse event description
       * @see jhelp.gui.smooth.event.SmoothMouseListener#mouseReleased(jhelp.gui.smooth.event.SmoothMouseInformation)
       */
      @Override
      public void mouseReleased(final SmoothMouseInformation mouseInformation)
      {
      }

      /**
       * Called when mouse moved <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseInformation
       *           Mouse event description
       * @see jhelp.gui.smooth.event.SmoothMouseListener#mouseWhellMoved(jhelp.gui.smooth.event.SmoothMouseInformation)
       */
      @Override
      public void mouseWhellMoved(final SmoothMouseInformation mouseInformation)
      {
      }
   }

   /**
    * Create a layout, depends on scroll way
    * 
    * @param scrollMode
    *           Scroll way
    * @return Created layout
    */
   private static JHelpLayoutSmooth createLayout(final JHelpScrollModeSmooth scrollMode)
   {
      switch(scrollMode)
      {
         case SCROLL_BOTH:
            return new JHelpVerticalLayoutSmooth();
         case SCROLL_HORIZONTAL:
            return new JHelpHorizontalLayoutSmooth();
         case SCROLL_VERTICAL:
            return new JHelpVerticalLayoutSmooth();
      }

      return null;
   }

   /**
    * Obtain constraints used for a scroll way
    * 
    * @param scrollMode
    *           Scroll way
    * @return Constraints to use
    */
   private static JHelpConstraintsSmooth obtainConstraints(final JHelpScrollModeSmooth scrollMode)
   {
      switch(scrollMode)
      {
         case SCROLL_BOTH:
            return JHelpVerticalConstraintsSmooth.EXPAND;
         case SCROLL_HORIZONTAL:
            return JHelpHorizontalConstraintsSmooth.EXPAND;
         case SCROLL_VERTICAL:
            return JHelpVerticalConstraintsSmooth.EXPAND;
      }

      return null;
   }

   /** Event manager */
   private final EventManager                                 eventManager;
   /** List model */
   private final JHelpListSmoothModel<ELEMENT>                model;
   /** List cell renderer */
   private final JHelpListSmoothRenderer<ELEMENT>             renderer;
   /** Current selected index */
   private int                                                selectIndex;
   /** Selection ebvent listeners */
   private final List<JHelpListSmoothSelectListener<ELEMENT>> selectListeners;
   /** Indicates if select by one click is enough to have a selection event */
   boolean                                                    selectBySimpleClick;

   /**
    * Create a new instance of JHelpListSmooth
    * 
    * @param model
    *           Model that carry data
    * @param renderer
    *           Renderer for list cells
    * @param scrollMode
    *           Scroll way
    */
   public JHelpListSmooth(final JHelpListSmoothModel<ELEMENT> model, final JHelpListSmoothRenderer<ELEMENT> renderer, final JHelpScrollModeSmooth scrollMode)
   {
      super(new JHelpPanelSmooth(JHelpListSmooth.createLayout(scrollMode)), scrollMode);

      if(model == null)
      {
         throw new NullPointerException("model musn't be null");
      }

      if(renderer == null)
      {
         throw new NullPointerException("renderer musn't be null");
      }

      this.eventManager = new EventManager();
      this.selectListeners = new ArrayList<JHelpListSmoothSelectListener<ELEMENT>>();
      this.model = model;
      this.model.registerModelListener(this.eventManager);
      this.renderer = renderer;
      this.selectIndex = -1;
      this.selectBySimpleClick = false;

      this.updateComplete();
   }

   /**
    * Refresh list cells appearance and scroll to put the current selection visible on screen
    */
   private void refresh()
   {
      final JHelpPanelSmooth panel = (JHelpPanelSmooth) this.getChild(0);
      final int count = Math.min(panel.getNumberOfChildren(), this.model.getNumberOfElement());

      for(int i = 0; i < count; i++)
      {
         this.renderer.transformComponent(panel.getChild(i), this.model.getElement(i), i, i == this.selectIndex);
      }

      if(this.selectIndex >= 0)
      {
         final JHelpComponentSmooth componentSmooth = panel.getChild(this.selectIndex);

         final Rectangle bounds = this.getBounds();
         final int xMin = bounds.x;
         final int yMin = bounds.y;
         final int xMax = bounds.x + bounds.width;
         final int yMax = bounds.y + bounds.height;

         final Rectangle boundsComponent = componentSmooth.getBounds();
         final int x = boundsComponent.x;
         final int y = boundsComponent.y;
         final int xx = boundsComponent.x + boundsComponent.width;
         final int yy = boundsComponent.y + boundsComponent.height;

         int vx = 0;
         int vy = 0;

         if(x < xMin)
         {
            vx = x - xMin;
         }
         else if(xx > xMax)
         {
            vx = xx - xMax;
         }

         if(y < yMin)
         {
            vy = y - yMin;
         }
         else if(yy > yMax)
         {
            vy = yy - yMax;
         }

         this.scroll(vx, vy);
      }
   }

   /**
    * Called when click append on one list cell
    * 
    * @param componentSmooth
    *           Clicked cell
    */
   void clickOn(final JHelpComponentSmooth componentSmooth)
   {
      final int index = componentSmooth.getIndexInParent();

      if(this.selectIndex == index)
      {
         return;
      }

      this.selectIndex = index;
      this.refresh();
   }

   /**
    * Called when an element is insert
    * 
    * @param index
    *           Insert index
    */
   void insertElement(final int index)
   {
      final JHelpPanelSmooth panel = (JHelpPanelSmooth) this.getChild(0);
      final JHelpComponentSmooth componentSmooth = this.renderer.createComponent(this.model.getElement(index));
      componentSmooth.registerMouseListener(this.eventManager);
      panel.insertComponent(index, componentSmooth, JHelpListSmooth.obtainConstraints(this.getScrollMode()));
      this.refresh();
   }

   /**
    * Called when an element is removed
    * 
    * @param index
    *           Removed index
    */
   void removeElement(final int index)
   {
      final JHelpPanelSmooth panel = (JHelpPanelSmooth) this.getChild(0);
      panel.getChild(index).unregisterMouseListener(this.eventManager);
      panel.removeComponent(index);
      this.refresh();
   }

   /**
    * Update totally the list
    */
   void updateComplete()
   {
      final JHelpPanelSmooth panel = (JHelpPanelSmooth) this.getChild(0);
      panel.clearPanel();
      final JHelpConstraintsSmooth constraintsSmooth = JHelpListSmooth.obtainConstraints(this.getScrollMode());
      final int count = this.model.getNumberOfElement();
      JHelpComponentSmooth componentSmooth;

      for(int i = 0; i < count; i++)
      {
         componentSmooth = this.renderer.createComponent(this.model.getElement(i));
         componentSmooth.registerMouseListener(this.eventManager);
         panel.addComponent(componentSmooth, constraintsSmooth);
      }

      this.refresh();
   }

   /**
    * Signal to listeners that select event happen
    */
   protected final void fireSelectElement()
   {
      if(this.selectIndex < 0)
      {
         return;
      }

      final ELEMENT element = this.model.getElement(this.selectIndex);

      for(final JHelpListSmoothSelectListener<ELEMENT> selectListener : this.selectListeners)
      {
         selectListener.elementSelected(this, element, this.selectIndex);
      }
   }

   /**
    * Process key events <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param keyInformation
    *           Key event description
    * @see jhelp.gui.smooth.JHelpComponentSmooth#processKeyEvent(jhelp.gui.smooth.event.SmoothKeyInformation)
    */
   @Override
   protected void processKeyEvent(final SmoothKeyInformation keyInformation)
   {
      final JHelpScrollModeSmooth mode = this.getScrollMode();
      int index;

      switch(keyInformation.keyCode)
      {
         case KeyEvent.VK_UP:
            if((mode != JHelpScrollModeSmooth.SCROLL_HORIZONTAL) && (this.selectIndex > 0))
            {
               this.selectIndex--;
               this.refresh();
            }
         break;
         case KeyEvent.VK_DOWN:
            if((mode != JHelpScrollModeSmooth.SCROLL_HORIZONTAL) && (this.selectIndex < (this.model.getNumberOfElement() - 1)))
            {
               this.selectIndex++;
               this.refresh();
            }
         break;
         case KeyEvent.VK_LEFT:
            if((mode != JHelpScrollModeSmooth.SCROLL_VERTICAL) && (this.selectIndex > 0))
            {
               this.selectIndex--;
               this.refresh();
            }
         break;
         case KeyEvent.VK_RIGHT:
            if((mode != JHelpScrollModeSmooth.SCROLL_VERTICAL) && (this.selectIndex < (this.model.getNumberOfElement() - 1)))
            {
               this.selectIndex++;
               this.refresh();
            }
         break;
         case KeyEvent.VK_ESCAPE:
            if(this.selectIndex > 0)
            {
               this.selectIndex = -1;
               this.refresh();
            }
         break;
         case KeyEvent.VK_PAGE_DOWN:
            index = Math.min(this.selectIndex + 10, this.model.getNumberOfElement() - 1);

            if(index != this.selectIndex)
            {
               this.selectIndex = index;
               this.refresh();
            }
         break;
         case KeyEvent.VK_PAGE_UP:
            index = Math.max(this.selectIndex - 10, Math.min(0, this.model.getNumberOfElement() - 1));

            if(index != this.selectIndex)
            {
               this.selectIndex = index;
               this.refresh();
            }
         break;
         case KeyEvent.VK_ENTER:
            this.fireSelectElement();
         break;
      }
   }

   /**
    * List model
    * 
    * @return List model
    */
   public JHelpListSmoothModel<ELEMENT> getListModel()
   {
      return this.model;
   }

   /**
    * Current selected element.<br>
    * {@code null} if no selection
    * 
    * @return Current selected element OR {@code null} if no selection
    */
   public ELEMENT getSelectedElement()
   {
      if(this.selectIndex < 0)
      {
         return null;
      }

      return this.model.getElement(this.selectIndex);
   }

   /**
    * Selected index<br>
    * -1 means no selection
    * 
    * @return Selected index OR -1 if no selection
    */
   public int getSelectIndex()
   {
      return this.selectIndex;
   }

   /**
    * Indicates if list is focusable <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return {@code true} if focusable
    * @see jhelp.gui.smooth.JHelpComponentSmooth#isFocusable()
    */
   @Override
   public boolean isFocusable()
   {
      return this.isVisible();
   }

   /**
    * Indicates if one click alert listeners of selection
    * 
    * @return {@code true} if one click enough for select event
    */
   public boolean isSelectBySimpleClick()
   {
      return this.selectBySimpleClick;
   }

   /**
    * Register a selection listener
    * 
    * @param selectListener
    *           Listener to register
    */
   public final void registerSelectListener(final JHelpListSmoothSelectListener<ELEMENT> selectListener)
   {
      if((selectListener != null) && (!this.selectListeners.contains(selectListener)))
      {
         this.selectListeners.add(selectListener);
      }
   }

   /**
    * Change the select by one click.<br>
    * If {@code true} every simple click on list element signal slecion change to listeners, else only doble click does that
    * 
    * @param selectBySimpleClick
    *           New select by one click policy
    */
   public void setSelectBySimpleClick(final boolean selectBySimpleClick)
   {
      this.selectBySimpleClick = selectBySimpleClick;
   }

   /**
    * Change the selected index.<br>
    * Use -1 for no selection
    * 
    * @param selectIndex
    *           New select index OR -1
    */
   public void setSelectIndex(final int selectIndex)
   {
      final int index = Math.max(-1, Math.min(this.model.getNumberOfElement() - 1, selectIndex));

      if(index == this.selectIndex)
      {
         return;
      }

      this.selectIndex = index;
      this.refresh();
   }

   /**
    * Unregister listener for no more receive select event
    * 
    * @param selectListener
    *           Listener to unregister
    */
   public final void unregisterSelectListener(final JHelpListSmoothSelectListener<ELEMENT> selectListener)
   {
      this.selectListeners.remove(selectListener);
   }
}