package jhelp.gui.smooth;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import javax.swing.SwingUtilities;

import jhelp.gui.JHelpFrame;
import jhelp.gui.LabelJHelpImage;
import jhelp.gui.action.GenericAction;
import jhelp.gui.smooth.event.SmoothKeyInformation;
import jhelp.gui.smooth.event.SmoothMouseInformation;
import jhelp.gui.smooth.layout.JHelpBorderLayoutSmooth;
import jhelp.gui.smooth.layout.JHelpConstraintsSmooth;
import jhelp.gui.smooth.layout.JHelpLayoutSmooth;
import jhelp.gui.smooth.shape.ShadowLevel;
import jhelp.gui.undoRedo.UndoRedoManager;
import jhelp.util.debug.Debug;
import jhelp.util.debug.DebugLevel;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpPaint;
import jhelp.util.list.ArrayInt;
import jhelp.util.list.HashInt;
import jhelp.util.list.Pair;
import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedSimpleTask;
import jhelp.util.thread.ThreadedVerySimpleTask;
import jhelp.util.time.LapsTime;

/**
 * Main frame for smooth components.<br>
 * It carry components, manage mouse and key events<br>
 * Its a good practice to define first the layout with {@link #setLayout(JHelpLayoutSmooth)} and after add component with
 * {@link #addComponent(JHelpComponentSmooth, JHelpConstraintsSmooth)}. Keep in mind that change the layout removes all the
 * frame components.<br>
 * It is possible to define key short cuts with {@link #defineShortCut(ShortCut, GenericAction)}<br>
 * Good practice for show frame is to not call {@link #setVisible(boolean)} inside the constructor, but after the construction
 * is complete.<br>
 * To close frame call {@link #closeFrame()}. By default closing frame will exit immediately, but if you want a chance to alert
 * user about something, like unsaved data, before exiting, override {@link #canCloseNow()} which is called before exiting the
 * frame.<br>
 * Dialogs are shown by there ID, on first dialog show {@link #createDialog(int)} is called to know how create the frame. After
 * the same dialog is reused. The over dialog take the hand, no other components can receive mouse or key events. So think about
 * add a way (button, timer, ..) to close the dialog.
 * 
 * @author JHelp
 */
public abstract class JHelpFrameSmooth
      extends JHelpFrame
      implements JHelpConstantsSmooth

{
   /**
    * Threaded task that dispatch mouse information to components
    * 
    * @author JHelp
    */
   class DispachMouseInformation
         extends ThreadedSimpleTask<List<SmoothMouseInformation>>
   {
      /**
       * Create a new instance of DispachMouseInformation
       */
      DispachMouseInformation()
      {
      }

      /**
       * Do the dispatch <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseInformations
       *           Mouse event description list to dispatch
       * @see jhelp.util.thread.ThreadedSimpleTask#doSimpleAction(java.lang.Object)
       */
      @Override
      protected void doSimpleAction(final List<SmoothMouseInformation> mouseInformations)
      {
         for(final SmoothMouseInformation mouseInformation : mouseInformations)
         {
            mouseInformation.getComponentSmooth().processMouseEvent(mouseInformation);
         }
      }
   }

   /**
    * Manage mouse and key events.<br>
    * It also refresh the frame automatically
    * 
    * @author JHelp
    */
   class EventManager
         extends ThreadedVerySimpleTask
         implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener
   {
      /** Show FPS information in console */
      private static final boolean   FPS        = false;
      /** Number of frame since last measure */
      private int                    countFrame = 0;
      /** Synchronization lock */
      private final Object           lock       = new Object();
      /** Last mouse events happen since last dispatch */
      private final List<MouseEvent> mouseEvents;
      /** Measure time since last FPS print */
      private LapsTime               totalTime  = new LapsTime();

      /**
       * Create a new instance of EventManager
       */
      EventManager()
      {
         this.mouseEvents = new ArrayList<MouseEvent>();
      }

      /**
       * Refresh the frame drawing <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @see jhelp.util.thread.ThreadedVerySimpleTask#doVerySimpleAction()
       */
      @Override
      protected void doVerySimpleAction()
      {
         if(EventManager.FPS)
         {
            LapsTime.startMeasure();
         }

         JHelpFrameSmooth.this.doRefreshFrame();

         synchronized(this.lock)
         {
            for(final MouseEvent mouseEvent : this.mouseEvents)
            {
               final List<SmoothMouseInformation> mouseInformation = JHelpFrameSmooth.this.createMouseInformation(mouseEvent);

               if(mouseInformation != null)
               {
                  ThreadManager.THREAD_MANAGER.doThread(JHelpFrameSmooth.this.dispachMouseInformation, mouseInformation);
               }
            }

            this.mouseEvents.clear();
         }

         if(EventManager.FPS)
         {
            this.totalTime = LapsTime.addition(LapsTime.endMeasure(), this.totalTime, this.totalTime);
            this.countFrame++;

            if(this.totalTime.getSecond() > 0)
            {
               Debug.println(DebugLevel.VERBOSE, this.countFrame, " : ", this.totalTime);
               this.countFrame = 0;
               this.totalTime.resetTime();
            }
         }
      }

      /**
       * Called when key pressed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param keyEvent
       *           Key event description
       * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
       */
      @Override
      public void keyPressed(final KeyEvent keyEvent)
      {
         JHelpFrameSmooth.this.processKey(keyEvent.getKeyCode(), keyEvent.getKeyChar(), keyEvent.isShiftDown(), keyEvent.isControlDown(), keyEvent.isAltDown());
      }

      /**
       * Called when key released <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param keyEvent
       *           Key event description
       * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
       */
      @Override
      public void keyReleased(final KeyEvent keyEvent)
      {
      }

      /**
       * Called when key typed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param keyEvent
       *           Key event description
       * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
       */
      @Override
      public void keyTyped(final KeyEvent keyEvent)
      {
      }

      /**
       * Called when mouse clicked <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Mouse event description
       * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseClicked(final MouseEvent mouseEvent)
      {
         synchronized(this.lock)
         {
            this.mouseEvents.add(mouseEvent);
         }
      }

      /**
       * Called when mouse dragged <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Mouse event description
       * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseDragged(final MouseEvent mouseEvent)
      {
         synchronized(this.lock)
         {
            this.mouseEvents.add(mouseEvent);
         }
      }

      /**
       * Called when mouse entered <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Mouse event description
       * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseEntered(final MouseEvent mouseEvent)
      {
         synchronized(this.lock)
         {
            this.mouseEvents.add(mouseEvent);
         }
      }

      /**
       * Called when mouse exited <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Mouse event description
       * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseExited(final MouseEvent mouseEvent)
      {
         synchronized(this.lock)
         {
            this.mouseEvents.add(mouseEvent);
         }
      }

      /**
       * Called when mouse moved <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Mouse event description
       * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseMoved(final MouseEvent mouseEvent)
      {
         synchronized(this.lock)
         {
            this.mouseEvents.add(mouseEvent);
         }
      }

      /**
       * Called when mouse pressed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Mouse event description
       * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
       */
      @Override
      public void mousePressed(final MouseEvent mouseEvent)
      {
         synchronized(this.lock)
         {
            this.mouseEvents.add(mouseEvent);
         }
      }

      /**
       * Called when mouse released <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseEvent
       *           Mouse event description
       * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseReleased(final MouseEvent mouseEvent)
      {
         synchronized(this.lock)
         {
            this.mouseEvents.add(mouseEvent);
         }
      }

      /**
       * Called when mouse wheel moved <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param mouseWheelEvent
       *           Mouse event description
       * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
       */
      @Override
      public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent)
      {
         synchronized(this.lock)
         {
            this.mouseEvents.add(mouseWheelEvent);
         }
      }
   }

   /**
    * Threaded task for process key events
    * 
    * @author JHelp
    */
   class TaskProcessKeyEvent
         extends ThreadedSimpleTask<Pair<JHelpComponentSmooth, SmoothKeyInformation>>
   {
      /**
       * Create a new instance of TaskProcessKeyEvent
       */
      TaskProcessKeyEvent()
      {
      }

      /**
       * Process key event <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param parameter
       *           Couple of focused component and key event description
       * @see jhelp.util.thread.ThreadedSimpleTask#doSimpleAction(java.lang.Object)
       */
      @Override
      protected void doSimpleAction(final Pair<JHelpComponentSmooth, SmoothKeyInformation> parameter)
      {
         parameter.element1.processKeyEvent(parameter.element2);
      }
   }

   /** Created dialogs */
   private final HashInt<JHelpDialogSmooth>       dialogs;
   /** Draw dialog stack */
   private final ArrayInt                         dialogStack;
   /** Manage key and mouse events. It refresh the frame */
   private EventManager                           eventManager;
   /** Component that have the focus */
   private JHelpComponentSmooth                   focusableComponent;
   /** Indicates if have to scroll to be able see the focus component */
   private boolean                                forcedViewFocus;
   /** Lock for synchronization */
   private final Object                           lock                    = new Object();
   /** Main component for draw the frame */
   private LabelJHelpImage                        mainComponent;
   /** Main image where frame is draw */
   private JHelpImage                             mainImage;
   /** Indicates if have to scan to get the focus element */
   private boolean                                needSearchFocusable     = true;
   /** Main panel that carry all frame components */
   private JHelpPanelSmooth                       rootPanel;
   /** Defined short cut keys */
   private final HashMap<ShortCut, GenericAction> shortCuts               = new HashMap<ShortCut, GenericAction>();
   /** Task that process key events */
   private final TaskProcessKeyEvent              taskProcessKeyEvent     = new TaskProcessKeyEvent();
   /** Current component under the mouse */
   private JHelpComponentSmooth                   underMouseComponent;
   /** Task for dispatch mouse information */
   final DispachMouseInformation                  dispachMouseInformation = new DispachMouseInformation();

   /**
    * Create a new instance of JHelpFrameSmooth
    * 
    * @param title
    *           Frame title
    */
   public JHelpFrameSmooth(final String title)
   {
      super(title, true, false);

      this.dialogs = new HashInt<JHelpDialogSmooth>();
      this.dialogStack = new ArrayInt();
      this.setFocusTraversalKeysEnabled(false);

      ThreadManager.THREAD_MANAGER.repeatThread(this.eventManager, null, 512, 1);
   }

   /**
    * Resize, if need, the main component
    */
   private void updateMainComponent()
   {
      final int width = this.mainComponent.getWidth();
      final int height = this.mainComponent.getHeight();

      if((this.mainImage == null) || (width != this.mainImage.getWidth()) || (height != this.mainImage.getHeight()))
      {
         this.mainImage = new JHelpImage(width, height);
         this.mainComponent.setJHelpImage(this.mainImage);
      }
   }

   /**
    * Compute the list of mouse description that provokes a mouse event
    * 
    * @param mouseEvent
    *           Mouse event
    * @return List of mouse description or{@code null} if no description
    */
   List<SmoothMouseInformation> createMouseInformation(final MouseEvent mouseEvent)
   {
      final int x = mouseEvent.getX();
      final int y = mouseEvent.getY();
      final List<SmoothMouseInformation> list = new ArrayList<SmoothMouseInformation>();
      Rectangle bounds;
      JHelpComponentSmooth component = null;

      int count = this.dialogStack.getSize();
      final boolean right = SwingUtilities.isRightMouseButton(mouseEvent);

      if(count > 0)
      {
         final int dialog = this.dialogStack.getInteger(count - 1);
         final JHelpDialogSmooth dialogSmooth = this.dialogs.get(dialog);
         component = dialogSmooth.obtainComponentUnder(x, y, right);
      }
      else
      {
         component = this.rootPanel.obtainComponentUnder(x, y, right);
      }

      final boolean left = SwingUtilities.isLeftMouseButton(mouseEvent);
      final boolean middle = SwingUtilities.isMiddleMouseButton(mouseEvent);

      count = mouseEvent.getClickCount();

      if(this.underMouseComponent != component)
      {
         if(this.underMouseComponent != null)
         {
            bounds = this.underMouseComponent.getBounds();
            list.add(new SmoothMouseInformation(MouseEvent.MOUSE_EXITED, this.underMouseComponent, x - bounds.x, y - bounds.y, x, y, left, middle, right,
                  count, 0));
         }

         this.underMouseComponent = component;

         if(this.underMouseComponent != null)
         {
            bounds = this.underMouseComponent.getBounds();
            list.add(new SmoothMouseInformation(MouseEvent.MOUSE_ENTERED, this.underMouseComponent, x - bounds.x, y - bounds.y, x, y, left, middle, right,
                  count, 0));
         }
      }

      if(this.underMouseComponent != null)
      {
         int type = mouseEvent.getID();
         int whell = 0;

         switch(type)
         {
            case MouseEvent.MOUSE_ENTERED:
            case MouseEvent.MOUSE_EXITED:
               type = MouseEvent.MOUSE_MOVED;
            break;
            case MouseEvent.MOUSE_WHEEL:
               final MouseWheelEvent mouseWheelEvent = (MouseWheelEvent) mouseEvent;
               whell = mouseWheelEvent.getWheelRotation();
            break;
            case MouseEvent.MOUSE_CLICKED:
               JHelpComponentSmooth componentSmooth = this.underMouseComponent;

               while(componentSmooth != null)
               {
                  if((componentSmooth.isFocusable() == true) && (componentSmooth != this.focusableComponent))
                  {
                     if(this.focusableComponent != null)
                     {
                        this.focusableComponent.lostFocus();
                     }

                     this.focusableComponent = componentSmooth;
                     this.focusableComponent.gainFocus();
                  }

                  if(componentSmooth.isFocusable() == true)
                  {
                     break;
                  }

                  componentSmooth = componentSmooth.getParent();
               }
            break;
         }

         bounds = this.underMouseComponent.getBounds();
         list.add(new SmoothMouseInformation(type, this.underMouseComponent, x - bounds.x, y - bounds.y, x, y, left, middle, right, count, whell));
      }

      if(list.isEmpty() == true)
      {
         return null;
      }

      return list;
   }

   /**
    * Refresh the frame
    */
   void doRefreshFrame()
   {
      if(((this.focusableComponent == null) && (this.needSearchFocusable == true))
            || ((this.focusableComponent != null) && (this.focusableComponent.isFocusable() == false)))
      {
         this.goNextFocusable();
      }

      this.needSearchFocusable = false;

      if((this.focusableComponent != null) && (this.forcedViewFocus == true))
      {
         this.forcedViewFocus = false;
         this.focusableComponent.scrollToVisible();
      }

      synchronized(this.lock)
      {
         this.updateMainComponent();

         this.mainImage.startDrawMode();
         this.rootPanel.paint(this.mainImage, 0, 0, this.mainImage.getWidth(), this.mainImage.getHeight(), this.mainImage.getWidth(),
               this.mainImage.getHeight());

         final int count = this.dialogStack.getSize();
         int dialog;
         JHelpDialogSmooth dialogSmooth;

         for(int dial = 0; dial < count; dial++)
         {
            dialog = this.dialogStack.getInteger(dial);
            dialogSmooth = this.dialogs.get(dialog);
            dialogSmooth.drawDialog(this.mainImage);
         }

         this.mainImage.endDrawMode();

         this.mainComponent.refresh();
      }
   }

   /**
    * Process key event
    * 
    * @param keyCode
    *           Key code
    * @param keyChar
    *           Key char
    * @param shift
    *           Indicates if SHIFT is down
    * @param control
    *           Indicates if CONTROL is down
    * @param alt
    *           Indicates if ALT is down
    */
   void processKey(final int keyCode, final char keyChar, final boolean shift, final boolean control, final boolean alt)
   {
      switch(keyCode)
      {
         case KeyEvent.VK_SHIFT:
         case KeyEvent.VK_CONTROL:
         case KeyEvent.VK_ALT:
         case KeyEvent.VK_ALT_GRAPH:
         case KeyEvent.VK_WINDOWS:
            // Do nothing for these keys
            return;
         case KeyEvent.VK_TAB:
            if(shift == true)
            {
               this.goPreviousFocusable();
            }
            else
            {
               this.goNextFocusable();
            }

            return;
         case KeyEvent.VK_Z:
            if(control == true)
            {
               if(UndoRedoManager.UNDO_REDO_MANAGER.canUndo() == true)
               {
                  UndoRedoManager.UNDO_REDO_MANAGER.undo();
               }

               return;
            }

         break;
         case KeyEvent.VK_Y:
            if(control == true)
            {
               if(UndoRedoManager.UNDO_REDO_MANAGER.canRedo() == true)
               {
                  UndoRedoManager.UNDO_REDO_MANAGER.redo();
               }

               return;
            }

         break;
      }

      // Try to apply a short cut
      final ShortCut shortCut = new ShortCut(keyCode, shift, control, alt);
      final GenericAction action = this.shortCuts.get(shortCut);

      if(action != null)
      {
         final ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "");
         action.actionPerformed(actionEvent);
         return;
      }

      if(this.focusableComponent != null)
      {
         ThreadManager.THREAD_MANAGER.doThread(this.taskProcessKeyEvent, new Pair<JHelpComponentSmooth, SmoothKeyInformation>(this.focusableComponent,
               new SmoothKeyInformation(keyCode, keyChar, shift, control, alt)));
      }
   }

   /**
    * Add internal listeners <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.JHelpFrame#addListeners()
    */
   @Override
   protected final void addListeners()
   {
      this.eventManager = new EventManager();
      this.mainComponent.addMouseListener(this.eventManager);
      this.mainComponent.addMouseMotionListener(this.eventManager);
      this.mainComponent.addMouseWheelListener(this.eventManager);
      this.addKeyListener(this.eventManager);
   }

   /**
    * Create frame components <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.JHelpFrame#createComponents()
    */
   @Override
   protected final void createComponents()
   {
      this.mainImage = new JHelpImage(512, 512);
      this.mainComponent = new LabelJHelpImage(this.mainImage);
      this.rootPanel = new JHelpPanelSmooth(new JHelpBorderLayoutSmooth());
      this.rootPanel.setShadowLevel(ShadowLevel.NO_SHADOW);
      this.setBackgroundColor(JHelpConstantsSmooth.COLOR_AMBER_0050);
   }

   /**
    * Called when a dialog need to be created
    * 
    * @param dialogID
    *           Dialog ID
    * @return Dialog description or {@code null} if no dialog for the given ID
    */
   protected abstract DialogDecsription createDialog(int dialogID);

   /**
    * Main component
    * 
    * @return Main component
    */
   protected Component getComponent()
   {
      return this.mainComponent;
   }

   /**
    * Layout frame components <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.JHelpFrame#layoutComponents()
    */
   @Override
   protected final void layoutComponents()
   {
      this.setLayout(new BorderLayout());
      this.add(this.mainComponent, BorderLayout.CENTER);
   }

   /**
    * Add component inside the frame
    * 
    * @param component
    *           Component to add
    * @param constraints
    *           Constraints to use
    */
   public final void addComponent(final JHelpComponentSmooth component, final JHelpConstraintsSmooth constraints)
   {
      synchronized(this.lock)
      {
         this.rootPanel.addComponent(component, constraints);
      }
   }

   /**
    * Close the current open dialog (If their one)
    */
   public void closeCurrentDialog()
   {
      if(this.dialogStack.isEmpty() == true)
      {
         return;
      }

      this.hideDialog(this.dialogStack.getInteger(this.dialogStack.getSize() - 1));
   }

   /**
    * Define a short cut.<br>
    * If the short cut already exists, the action is replaced by the given one
    * 
    * @param shortCut
    *           Short cut key
    * @param action
    *           Associated action
    */
   public final void defineShortCut(final ShortCut shortCut, final GenericAction action)
   {
      if(shortCut == null)
      {
         throw new NullPointerException("shortCut musn't be null");
      }

      if(action == null)
      {
         throw new NullPointerException("action musn't be null");
      }

      synchronized(this.shortCuts)
      {
         this.shortCuts.put(shortCut, action);
      }
   }

   /**
    * Frame background color
    * 
    * @return Background color
    */
   public final int getBackgroundColor()
   {
      return this.rootPanel.getBackground();
   }

   /**
    * Frame background image
    * 
    * @return Background image
    */
   public final JHelpImage getBackgroundImage()
   {
      return this.rootPanel.getTextureBackground();
   }

   /**
    * Frame background paint
    * 
    * @return Background paint
    */
   public final JHelpPaint getBackgroundPaint()
   {
      return this.rootPanel.getPaintBackground();
   }

   /**
    * Frame size
    * 
    * @return Frame size
    */
   public final Dimension getFrameSize()
   {
      return new Dimension(this.mainImage.getWidth(), this.mainImage.getHeight());
   }

   /**
    * Force change focusable component to next one
    */
   public final void goNextFocusable()
   {
      JHelpContainerSmooth start = this.rootPanel;
      final int count = this.dialogStack.getSize();

      if(count > 0)
      {
         start = this.dialogs.get(this.dialogStack.getInteger(count - 1)).getRootPanel();
      }

      int indexStart = -1;

      if(this.focusableComponent != null)
      {
         start = this.focusableComponent.getParent();
         indexStart = this.focusableComponent.getIndexInParent();
      }

      final Stack<Pair<JHelpContainerSmooth, Integer>> stack = new Stack<Pair<JHelpContainerSmooth, Integer>>();
      stack.push(new Pair<JHelpContainerSmooth, Integer>(start, indexStart + 1));
      Pair<JHelpContainerSmooth, Integer> pair;
      JHelpContainerSmooth current, next;
      int indexInParent;
      JHelpComponentSmooth component;

      while(stack.isEmpty() == false)
      {
         pair = stack.pop();
         current = pair.element1;
         indexInParent = pair.element2;

         if((current == start) && (indexInParent == indexStart))
         {
            if((this.focusableComponent != null) && (this.focusableComponent.isFocusable() == false))
            {
               this.focusableComponent.lostFocus();
               this.focusableComponent = null;
            }

            return;
         }

         if(indexInParent >= current.getNumberOfChildren())
         {
            next = current.getParent();

            if(next == null)
            {
               if(indexStart < 0)
               {
                  if((this.focusableComponent != null) && (this.focusableComponent.isFocusable() == false))
                  {
                     this.focusableComponent.lostFocus();
                     this.focusableComponent = null;
                  }

                  return;
               }

               stack.push(new Pair<JHelpContainerSmooth, Integer>(current, 0));
            }
            else
            {
               stack.push(new Pair<JHelpContainerSmooth, Integer>(next, current.getIndexInParent() + 1));
            }

            continue;
         }

         component = current.getChild(indexInParent);

         if(component instanceof JHelpContainerSmooth)
         {
            next = (JHelpContainerSmooth) component;

            if(next.getNumberOfChildren() > 0)
            {
               stack.push(new Pair<JHelpContainerSmooth, Integer>(next, 0));
            }
            else
            {
               stack.push(new Pair<JHelpContainerSmooth, Integer>(current, indexInParent + 1));
            }
         }
         else
         {
            if(component.isFocusable() == true)
            {
               if(this.focusableComponent != null)
               {
                  this.focusableComponent.lostFocus();
               }

               this.focusableComponent = component;
               this.focusableComponent.gainFocus();
               this.forcedViewFocus = true;
               return;
            }

            stack.push(new Pair<JHelpContainerSmooth, Integer>(current, indexInParent + 1));
         }
      }
   }

   /**
    * Force to change focusable component to previous one
    */
   public final void goPreviousFocusable()
   {
      JHelpContainerSmooth start = this.rootPanel;
      final int count = this.dialogStack.getSize();

      if(count > 0)
      {
         start = this.dialogs.get(this.dialogStack.getInteger(count - 1)).getRootPanel();
      }

      int indexStart = -1;

      if(this.focusableComponent != null)
      {
         start = this.focusableComponent.getParent();
         indexStart = this.focusableComponent.getIndexInParent();
      }

      final Stack<Pair<JHelpContainerSmooth, Integer>> stack = new Stack<Pair<JHelpContainerSmooth, Integer>>();
      stack.push(new Pair<JHelpContainerSmooth, Integer>(start, indexStart - 1));
      Pair<JHelpContainerSmooth, Integer> pair;
      JHelpContainerSmooth current, previous;
      int indexInParent;
      JHelpComponentSmooth component;

      while(stack.isEmpty() == false)
      {
         pair = stack.pop();
         current = pair.element1;
         indexInParent = pair.element2;

         if((current == start) && (indexInParent == indexStart))
         {
            if((this.focusableComponent != null) && (this.focusableComponent.isFocusable() == false))
            {
               this.focusableComponent.lostFocus();
               this.focusableComponent = null;
            }

            return;
         }

         if(indexInParent < 0)
         {
            previous = current.getParent();

            if(previous == null)
            {
               if(indexStart < 0)
               {
                  if((this.focusableComponent != null) && (this.focusableComponent.isFocusable() == false))
                  {
                     this.focusableComponent.lostFocus();
                     this.focusableComponent = null;
                  }

                  return;
               }

               stack.push(new Pair<JHelpContainerSmooth, Integer>(current, current.getNumberOfChildren() - 1));
            }
            else
            {
               stack.push(new Pair<JHelpContainerSmooth, Integer>(previous, current.getIndexInParent() - 1));
            }

            continue;
         }

         component = current.getChild(indexInParent);

         if(component instanceof JHelpContainerSmooth)
         {
            previous = (JHelpContainerSmooth) component;

            if(previous.getNumberOfChildren() > 0)
            {
               stack.push(new Pair<JHelpContainerSmooth, Integer>(previous, previous.getNumberOfChildren() - 1));
            }
            else
            {
               stack.push(new Pair<JHelpContainerSmooth, Integer>(current, indexInParent - 1));
            }
         }
         else
         {
            if(component.isFocusable() == true)
            {
               if(this.focusableComponent != null)
               {
                  this.focusableComponent.lostFocus();
               }

               this.focusableComponent = component;
               this.focusableComponent.gainFocus();
               this.forcedViewFocus = true;
               return;
            }

            stack.push(new Pair<JHelpContainerSmooth, Integer>(current, indexInParent - 1));
         }
      }
   }

   /**
    * Hide a dialog
    * 
    * @param dialogID
    *           Dialog ID to hide
    */
   public final void hideDialog(final int dialogID)
   {
      synchronized(this.lock)
      {
         final int index = this.dialogStack.getIndex(dialogID);

         if(index < 0)
         {
            return;
         }

         this.dialogStack.remove(index);

         if(this.focusableComponent != null)
         {
            this.focusableComponent.lostFocus();
            this.focusableComponent = null;
         }

         this.needSearchFocusable = true;
      }
   }

   /**
    * Search component by its ID
    * 
    * @param id
    *           Component ID
    * @return Found component or {@code null} if not found
    */
   public final JHelpComponentSmooth searchComponent(final int id)
   {
      return this.rootPanel.searchComponent(id);
   }

   /**
    * Set frame background to one color
    * 
    * @param color
    *           New background color
    */
   public final void setBackgroundColor(final int color)
   {
      this.rootPanel.setBackground(color);
   }

   /**
    * Set frame background to one image
    * 
    * @param image
    *           New Image background
    */
   public final void setBackgroundImage(final JHelpImage image)
   {
      this.rootPanel.setTextureBackground(image);
   }

   /**
    * Set frame background to a paint
    * 
    * @param paint
    *           Background paint
    */
   public final void setBackgroundPaint(final JHelpPaint paint)
   {
      this.rootPanel.setPaintBackground(paint);
   }

   /**
    * Change frame layout.<br>
    * <b>WARNING</b>All already placed components will be removed. So it is a good idea to acall this before adding any
    * component
    * 
    * @param layout
    *           New layout
    */
   public final void setLayout(final JHelpLayoutSmooth layout)
   {
      synchronized(this.lock)
      {
         this.rootPanel.setLayout(layout);
      }
   }

   /**
    * Show one dialog
    * 
    * @param dialogID
    *           Dialog ID to show
    */
   public final void showDialog(final int dialogID)
   {
      synchronized(this.lock)
      {
         final int index = this.dialogStack.getIndex(dialogID);

         // If dialog on stack, put it over others
         if(index >= 0)
         {
            this.dialogStack.remove(index);
            this.dialogStack.add(dialogID);

            if(this.focusableComponent != null)
            {
               this.focusableComponent.lostFocus();
               this.focusableComponent = null;
            }

            this.needSearchFocusable = true;
            return;
         }

         JHelpDialogSmooth dialog = this.dialogs.get(dialogID);

         // Create dialog if need
         if(dialog == null)
         {
            final DialogDecsription decsription = this.createDialog(dialogID);

            if(decsription == null)
            {
               return;
            }

            dialog = new JHelpDialogSmooth(decsription);
            this.dialogs.put(dialogID, dialog);
         }

         // Show the dialog and put it over others
         dialog.startAnimationComeToScreen();
         this.dialogStack.add(dialogID);

         if(this.focusableComponent != null)
         {
            this.focusableComponent.lostFocus();
            this.focusableComponent = null;
         }

         this.needSearchFocusable = true;
      }
   }

   /**
    * Remove a short cut
    * 
    * @param shortCut
    *           Short cut key to remove
    */
   public final void undefineShortCut(final ShortCut shortCut)
   {
      synchronized(this.shortCuts)
      {
         this.shortCuts.remove(shortCut);
      }
   }
}