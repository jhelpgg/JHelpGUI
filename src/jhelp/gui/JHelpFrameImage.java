package jhelp.gui;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.UtilGUI;

/**
 * Frame based on unique "full screen" image
 * 
 * @author JHelp
 */
public class JHelpFrameImage
      extends JHelpFrame
{
   /** serialVersionUID */
   private static final long   serialVersionUID = 3717150952548300651L;
   /** Main component that carry the unique image */
   private ComponentJHelpImage componentJHelpImage;

   /**
    * Create a new instance of JHelpFrameImage
    */
   public JHelpFrameImage()
   {
      super(true);
   }

   /**
    * Create a new instance of JHelpFrameImage
    * 
    * @param title
    *           Frame title
    */
   public JHelpFrameImage(final String title)
   {
      super(title, true);
   }

   /**
    * Remove key listener to embed image
    * 
    * @param keyListener
    *           Listener to remove
    */
   void componentRemoveKeyListener(final KeyListener keyListener)
   {
      this.componentJHelpImage.removeKeyListener(keyListener);
   }

   /**
    * Remove mouse listener to embed image
    * 
    * @param mouseListener
    *           Listener to remove
    */
   void componentRemoveMouseListener(final MouseListener mouseListener)
   {
      this.componentJHelpImage.removeMouseListener(mouseListener);
   }

   /**
    * Remove mouse motion listener to embed image
    * 
    * @param mouseMotionListener
    *           Listener to remove
    */
   void componentRemoveMouseMotionListener(final MouseMotionListener mouseMotionListener)
   {
      this.componentJHelpImage.removeMouseMotionListener(mouseMotionListener);
   }

   /**
    * Add listeners <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.JHelpFrame#addListeners()
    */
   @Override
   protected final void addListeners()
   {
   }

   /**
    * Add key listener to embed image
    * 
    * @param keyListener
    *           Listener to add
    */
   protected void componentAddKeyListener(final KeyListener keyListener)
   {
      this.componentJHelpImage.setFocusable(true);
      this.componentJHelpImage.requestFocusInWindow();

      this.componentJHelpImage.addKeyListener(keyListener);
   }

   /**
    * Add mouse listener to embed image
    * 
    * @param mouseListener
    *           Listener to add
    */
   protected void componentAddMouseListener(final MouseListener mouseListener)
   {
      this.componentJHelpImage.addMouseListener(mouseListener);
   }

   /**
    * Add mouse motion listener to embed image
    * 
    * @param mouseMotionListener
    *           Listener to add
    */
   protected void componentAddMouseMotionListener(final MouseMotionListener mouseMotionListener)
   {
      this.componentJHelpImage.addMouseMotionListener(mouseMotionListener);
   }

   /**
    * Add a mouse wheel listener
    * 
    * @param mouseWheelListener
    *           Listener to add
    */
   protected void componentAddMouseWheelListener(final MouseWheelListener mouseWheelListener)
   {
      this.componentJHelpImage.addMouseWheelListener(mouseWheelListener);
   }

   /**
    * Create inside components <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.JHelpFrame#createComponents()
    */
   @Override
   protected final void createComponents()
   {
      final Rectangle bounds = UtilGUI.getScreenBounds(0);

      this.componentJHelpImage = new ComponentJHelpImage(/* 1024, 1024);/ */bounds.width, bounds.height);
   }

   /**
    * Layout components <br>
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

      this.add(this.componentJHelpImage, BorderLayout.CENTER);
   }

   /**
    * Update frame content size
    */
   protected void updateSize()
   {
      this.componentJHelpImage.updateSize();
   }

   /**
    * The embed image
    * 
    * @return The embed image
    */
   public final JHelpImage getImage()
   {
      return this.componentJHelpImage.getImage();
   }

   /**
    * Image over the frame
    * 
    * @return Image over the frame
    */
   public JHelpImage getImageOver()
   {
      return this.componentJHelpImage.getImageOver();
   }

   /**
    * Indicates if image over active
    * 
    * @return {@code true} if image over active
    */
   public boolean isImageOverActive()
   {
      return this.componentJHelpImage.isImageOverActive();
   }

   /**
    * Force refresh/update the embed image
    */
   public final void refresh()
   {
      this.componentJHelpImage.getImage().update();
   }

   /**
    * Change image over active state
    * 
    * @param active
    *           New image over active state
    */
   public void setImageOverActive(final boolean active)
   {
      this.componentJHelpImage.setImageOverActive(active);
   }
}