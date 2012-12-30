package jhelp.gui.twoD;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import jhelp.gui.JHelpFrameImage;
import jhelp.gui.JHelpMouseListener;
import jhelp.util.gui.JHelpFont;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpTextAlign;
import jhelp.util.gui.JHelpTextLine;
import jhelp.util.list.Pair;
import jhelp.util.list.Triplet;
import jhelp.util.thread.Mutex;
import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedVerySimpleTask;

/**
 * Frame that carry components 2D
 * 
 * @author JHelp
 */
public class JHelpFrame2D
      extends JHelpFrameImage
      implements MouseListener, MouseMotionListener, MouseWheelListener
{
   /** serialVersionUID */
   private static final long              serialVersionUID     = 8861480138627452386L;
   /** Default tips font */
   public static final JHelpFont          DEFAULT_TIPS_FONT    = new JHelpFont("Arial", 14);
   /** Indicate if automatic refresh is running */
   private boolean                        automaticRefresh;
   /** Task that refresh automatically */
   private final ThreadedVerySimpleTask   automaticRefreshTask = new ThreadedVerySimpleTask()
                                                               {
                                                                  /**
                                                                   * Refresh the frmae <br>
                                                                   * <br>
                                                                   * <b>Parent documentation:</b><br>
                                                                   * {@inheritDoc}
                                                                   * 
                                                                   * @see jhelp.util.thread.ThreadedVerySimpleTask#doVerySimpleAction()
                                                                   */
                                                                  @Override
                                                                  protected void doVerySimpleAction()
                                                                  {
                                                                     JHelpFrame2D.this.update();
                                                                  }
                                                               };
   /** Background color */
   private final int                      backgroundColor;
   /** Synchronization mutex */
   private final Mutex                    mutex;
   /** Actual component where lies the mouse */
   private JHelpComponent2D               overComponent;
   /** Main 2D panel */
   private final JHelpPanel2D             panel;
   /** Dialogs stack */
   private final ArrayList<JHelpDialog2D> stackDialog;
   /** Waiting time */
   private int                            wait;

   /**
    * Create a new instance of JHelpFrame2D
    * 
    * @param layout
    *           Layout to use on main panel
    */
   public JHelpFrame2D(final JHelpLayout layout)
   {
      this("JHelp Frame 2D", layout);
   }

   /**
    * Create a new instance of JHelpFrame2D
    * 
    * @param title
    *           Frame title
    * @param layout
    *           Layout to use on main panel
    */
   public JHelpFrame2D(final String title, final JHelpLayout layout)
   {
      super(title);

      this.panel = new JHelpPanel2D(layout);
      this.backgroundColor = 0xFFFEDCBA;
      this.automaticRefresh = false;
      this.mutex = new Mutex();

      this.componentAddMouseListener(this);
      this.componentAddMouseMotionListener(this);
      this.componentAddMouseWheelListener(this);

      this.wait = 1024;
      this.stackDialog = new ArrayList<JHelpDialog2D>();

      this.setImageOverActive(true);
   }

   /**
    * Compute component under the mouse
    * 
    * @param event
    *           Mouse event description (Contains mouse position)
    * @return The component, the associated mouse listener and translated mouse event to be relative to the component
    */
   private Triplet<JHelpComponent2D, JHelpMouseListener, MouseEvent> obtainMouseArea(final MouseEvent event)
   {
      final int size = this.stackDialog.size();
      Pair<JHelpComponent2D, JHelpMouseListener> pair;

      int xx = 0;
      int yy = 0;

      if(size > 0)
      {
         final JHelpDialog2D dialog = this.stackDialog.get(size - 1);
         xx = -dialog.getX();
         yy = -dialog.getY();
         final int mx = event.getX();
         final int my = event.getY();
         final int dx = dialog.getX();
         final int dy = dialog.getY();
         final Dimension dimension = dialog.componentIternSize();

         pair = dialog.mouseOver(mx + xx, my + yy);

         if((pair == null) && (MouseEvent.MOUSE_CLICKED == event.getID()) && (dialog.isClikOutClose() == true) && ((mx < dx) || (mx >= (dx + dimension.width)) || (my < dy) || (my >= (dy + dimension.height))))
         {
            dialog.setVisible(false);
         }
      }
      else
      {
         pair = this.panel.mouseOver(event.getX(), event.getY());
      }

      if(pair == null)
      {
         return null;
      }

      final Rectangle bounds = pair.element1.getBounds();
      event.translatePoint(xx - bounds.x, yy - bounds.y);
      event.setSource(pair.element1);

      return new Triplet<JHelpComponent2D, JHelpMouseListener, MouseEvent>(pair.element1, pair.element2, event);
   }

   /**
    * 
    Update mouse position
    * 
    * @param component2d
    *           Component under the mouse
    * @param mx
    *           Mouse X
    * @param my
    *           Mouse Y
    * @param mouseEvent
    *           Mouse event description
    */
   private void updateMousePosition(final JHelpComponent2D component2d, final int mx, final int my, final MouseEvent mouseEvent)
   {
      if(this.overComponent != null)
      {
         if(this.overComponent == component2d)
         {
            return;
         }

         final MouseEvent me = new MouseEvent(this, MouseEvent.MOUSE_EXITED, System.currentTimeMillis(), mouseEvent.getModifiers(), mx - this.overComponent.getAbsoluteX(), my - this.overComponent.getAbsoluteY(),
               mouseEvent.getClickCount(), mouseEvent.isPopupTrigger());
         me.setSource(this.overComponent);

         this.overComponent.getMouseListener().mouseExited(me);
      }

      if(component2d != null)
      {
         final MouseEvent me = new MouseEvent(this, MouseEvent.MOUSE_ENTERED, System.currentTimeMillis(), mouseEvent.getModifiers(), mx - component2d.getAbsoluteX(), my - component2d.getAbsoluteY(), mouseEvent.getClickCount(),
               mouseEvent.isPopupTrigger());
         me.setSource(component2d);

         component2d.getMouseListener().mouseEntered(me);
      }

      this.overComponent = component2d;
   }

   /**
    * Change a dialog visibility
    * 
    * @param dialog
    *           Dialog to change visibility
    * @param visible
    *           New visibility state
    */
   void setDialogVisible(final JHelpDialog2D dialog, final boolean visible)
   {
      this.mutex.lock();

      final int size = this.stackDialog.size();
      final int index = this.stackDialog.indexOf(dialog);

      if(visible == false)
      {
         if(index >= 0)
         {
            if(dialog.isVisible() == true)
            {
               dialog.updateVisible(false);
            }

            this.stackDialog.remove(index);

            if((index == (size - 1)) && (index > 0))
            {
               this.stackDialog.get(index - 1).updateVisible(true);
            }
         }

         this.mutex.unlock();
         return;
      }

      if(index < 0)
      {
         if(size > 0)
         {
            this.stackDialog.get(size - 1).updateVisible(false);
         }

         this.stackDialog.add(dialog);
         dialog.updateVisible(true);

         this.mutex.unlock();
         return;
      }

      if(index == (size - 1))
      {
         this.mutex.unlock();
         return;
      }

      this.stackDialog.get(size - 1).updateVisible(false);
      this.stackDialog.remove(index);
      this.stackDialog.add(dialog);
      dialog.updateVisible(true);

      this.mutex.unlock();
   }

   /**
    * Add a component 2D to main panel
    * 
    * @param component
    *           Component to add
    * @param constraints
    *           Constraints to use
    */
   public void addComponent2D(final JHelpComponent2D component, final JHelpConstraints constraints)
   {
      this.panel.addComponent2D(component, constraints);
   }

   /**
    * Create a dialog associated to the frame
    * 
    * @param component2d
    *           Dialog content
    * @return Created dialog
    */
   public JHelpDialog2D createDialog(final JHelpComponent2D component2d)
   {
      if(component2d == null)
      {
         throw new NullPointerException("component2d musn't be null");
      }

      this.mutex.lock();
      final JHelpDialog2D dialog2d = new JHelpDialog2D(component2d, this);
      this.mutex.unlock();

      return dialog2d;
   }

   /**
    * Hide the tips
    */
   public void hideTips()
   {
      this.printTips(0, 0, null);
   }

   /**
    * Indicates if automatic refresh is started
    * 
    * @return {@code true} if automatic refresh is started
    */
   public boolean isAutomaticRefresh()
   {
      return this.automaticRefresh;
   }

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
      final Triplet<JHelpComponent2D, JHelpMouseListener, MouseEvent> triplet = this.obtainMouseArea(e);

      if(triplet != null)
      {
         triplet.element2.mouseClicked(triplet.element3);
      }
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
      final int mx = e.getX();
      final int my = e.getY();

      final Triplet<JHelpComponent2D, JHelpMouseListener, MouseEvent> triplet = this.obtainMouseArea(e);

      if(triplet != null)
      {
         triplet.element2.mouseDragged(triplet.element3);
         this.updateMousePosition(triplet.element1, mx, my, triplet.element3);
      }
      else
      {
         this.updateMousePosition(null, mx, my, e);
      }
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
      final Triplet<JHelpComponent2D, JHelpMouseListener, MouseEvent> triplet = this.obtainMouseArea(e);

      if(triplet != null)
      {
         triplet.element2.mouseEntered(triplet.element3);
      }
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
      final Triplet<JHelpComponent2D, JHelpMouseListener, MouseEvent> triplet = this.obtainMouseArea(e);

      if(triplet != null)
      {
         triplet.element2.mouseExited(triplet.element3);
      }
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
      final int mx = e.getX();
      final int my = e.getY();

      final Triplet<JHelpComponent2D, JHelpMouseListener, MouseEvent> triplet = this.obtainMouseArea(e);

      if(triplet != null)
      {
         triplet.element2.mouseMoved(triplet.element3);
         this.updateMousePosition(triplet.element1, mx, my, triplet.element3);
      }
      else
      {
         this.updateMousePosition(null, mx, my, e);
      }
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
      final Triplet<JHelpComponent2D, JHelpMouseListener, MouseEvent> triplet = this.obtainMouseArea(e);

      if(triplet != null)
      {
         triplet.element2.mousePressed(triplet.element3);
      }
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
      final Triplet<JHelpComponent2D, JHelpMouseListener, MouseEvent> triplet = this.obtainMouseArea(e);

      if(triplet != null)
      {
         triplet.element2.mouseReleased(triplet.element3);
      }
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
      final Triplet<JHelpComponent2D, JHelpMouseListener, MouseEvent> triplet = this.obtainMouseArea(e);

      if(triplet != null)
      {
         triplet.element2.mouseWheelMoved((MouseWheelEvent) triplet.element3);
      }
   }

   /**
    * Print a tips
    * 
    * @param x
    *           X
    * @param y
    *           Y
    * @param tips
    *           Tips text ({@code null} for hide the tip)
    */
   public void printTips(final int x, final int y, final String tips)
   {
      this.printTips(x, y, tips, JHelpFrame2D.DEFAULT_TIPS_FONT, 0xFF000000, 0x80FFFF00, 0xFF000000, JHelpTextAlign.CENTER, true);
   }

   /**
    * Print a tips
    * 
    * @param x
    *           X
    * @param y
    *           Y
    * @param tips
    *           Tips text ({@code null} for hide the tip)
    * @param font
    *           Font to use
    * @param colorText
    *           Text color
    * @param colorBackground
    *           Background color
    * @param colorBorder
    *           Border color
    * @param textAlign
    *           Text alignment
    * @param borderLetter
    *           Indicates if draw border letters
    */
   public void printTips(final int x, final int y, final String tips, final JHelpFont font, final int colorText, final int colorBackground, final int colorBorder, final JHelpTextAlign textAlign, final boolean borderLetter)
   {
      final JHelpImage imageOver = this.getImageOver();

      if(tips == null)
      {
         imageOver.startDrawMode();
         imageOver.clear(0);
         imageOver.endDrawMode();

         return;
      }

      final Pair<List<JHelpTextLine>, Dimension> pair = font.computeTextLines(tips, textAlign);

      imageOver.startDrawMode();

      imageOver.clear(0);

      imageOver.fillRectangle(x, y, pair.element2.width + 6, pair.element2.height + 6, colorBackground);

      final int col = colorText ^ 0x00FFFFFF;

      for(final JHelpTextLine textLine : pair.element1)
      {
         if(borderLetter == true)
         {
            for(int yy = -1; yy <= 1; yy++)
            {
               for(int xx = -1; xx <= 1; xx++)
               {
                  imageOver.paintMask(x + textLine.getX() + 3 + xx, y + textLine.getY() + 3 + yy, textLine.getMask(), col, 0, true);
               }
            }
         }

         imageOver.paintMask(x + textLine.getX() + 3, y + textLine.getY() + 3, textLine.getMask(), colorText, 0, true);
      }

      imageOver.drawRectangle(x, y, pair.element2.width + 6, pair.element2.height + 6, colorBorder);

      imageOver.endDrawMode();
   }

   /**
    * Change automatic refresh status
    * 
    * @param automaticRefresh
    *           New automatic refresh status
    */
   public void setAutomaticRefresh(final boolean automaticRefresh)
   {
      if(this.automaticRefresh == automaticRefresh)
      {
         return;
      }

      this.automaticRefresh = automaticRefresh;
      this.wait = 1;

      if(automaticRefresh == true)
      {
         this.update();
      }
   }

   /**
    * Update the frame
    */
   public void update()
   {
      this.mutex.lock();

      this.updateSize();

      final JHelpImage image = this.getImage();

      this.panel.getPrefrerredSize(image.getWidth(), image.getHeight());

      image.startDrawMode();

      image.clear(this.backgroundColor);

      this.panel.paintInternal(0, 0, image);

      image.endDrawMode();

      final int size = this.stackDialog.size();
      if(size > 0)
      {
         this.stackDialog.get(size - 1).updateImage();
      }

      if(this.automaticRefresh == true)
      {
         ThreadManager.THREAD_MANAGER.delayedThread(this.automaticRefreshTask, null, this.wait);
         this.wait = 32;
      }

      this.mutex.unlock();
   }
}