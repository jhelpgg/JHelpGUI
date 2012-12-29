package jhelp.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import jhelp.util.MemorySweeper;
import jhelp.util.gui.UtilGUI;

/**
 * Generic frame, extend it avoid to do some usual common tasks and suggest strongly to code good
 * 
 * @author JHelp
 */
public abstract class JHelpFrame
      extends JFrame
{
   /** serialVersionUID */
   private static final long serialVersionUID = 9154375270442341171L;
   /** Indicates if closing the frame, will also stop all process and the current application */
   private boolean           exitAllOnClose;

   /**
    * Create a new instance of JHelpFrame Center of the screen and fit size to inside components
    */
   public JHelpFrame()
   {
      this("JHelp Frame", false);
   }

   /**
    * Create a new instance of JHelpFrame
    * 
    * @param full
    *           Indicates if frame have to take all screen ({@code true}) or center of the screen and fit size to inside
    *           components
    */
   public JHelpFrame(final boolean full)
   {
      this("JHelp Frame", full);
   }

   /**
    * Create a new instance of JHelpFrame center of the screen and fit size to contains components
    * 
    * @param title
    *           Frame title
    */
   public JHelpFrame(final String title)
   {
      this(title, false);
   }

   /**
    * Create a new instance of JHelpFrame
    * 
    * @param title
    *           Frame title
    * @param full
    *           Indicates if frame have to take all screen ({@code true}) or center of the screen and fit size to inside
    *           components
    */
   public JHelpFrame(final String title, final boolean full)
   {
      super(title);

      this.setMinimumSize(new Dimension(512, 512));

      MemorySweeper.launch();

      this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

      this.createComponents();
      this.layoutComponents();
      this.addListeners();

      final Rectangle bounds = UtilGUI.getScreenBounds(0);

      if(full == true)
      {
         this.setLocation(bounds.x, bounds.y);
         this.setSize(bounds.width, bounds.height);
      }
      else
      {
         this.pack();

         final Dimension dimension = this.getSize();

         dimension.width = Math.min(dimension.width, bounds.width);
         dimension.height = Math.min(dimension.height, bounds.height);

         this.setLocation(((bounds.width - dimension.width) >> 1) + bounds.x, ((bounds.height - dimension.height) >> 1) + bounds.y);
         this.setSize(dimension);
      }

      this.exitAllOnClose = true;
   }

   /**
    * Add listeners to components
    */
   protected abstract void addListeners();

   /**
    * Indicates if the frame can be close.<br>
    * By default it returns always {@code true}, if you need ask user about, by example, some unsaved change before quit,
    * override this method and return only {@code true} when user is allowed to close the frame.<br>
    * It is called each time the user try to close the frame
    * 
    * @return {@code true} if frame can close
    */
   protected boolean canCloseNow()
   {
      return true;
   }

   /**
    * Create components
    */
   protected abstract void createComponents();

   /**
    * Layout components inside the frame
    */
   protected abstract void layoutComponents();

   /**
    * Call at each window event <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param e
    *           Event description
    * @see javax.swing.JFrame#processWindowEvent(java.awt.event.WindowEvent)
    */
   @Override
   protected final void processWindowEvent(final WindowEvent e)
   {
      switch(e.getID())
      {
         case WindowEvent.WINDOW_CLOSED:
         case WindowEvent.WINDOW_CLOSING:
            this.closeFrame();
         break;
         default:
            super.processWindowEvent(e);
         break;
      }
   }

   /**
    * Close the current frame
    */
   public final void closeFrame()
   {
      if(this.canCloseNow() == false)
      {
         return;
      }

      this.setVisible(false);
      this.dispose();

      if(this.exitAllOnClose == true)
      {
         MemorySweeper.exit(0);
      }
   }

   /**
    * Indicates if we have to exit all process and application when closing the frame
    * 
    * @return {@code true} if we have to exit all process and application when closing the frame
    */
   public final boolean isExitAllOnClose()
   {
      return this.exitAllOnClose;
   }

   /**
    * Change the state of exit of all.<br>
    * Indicates if we have to exit all process and application when closing the frame
    * 
    * @param exitAllOnClose
    *           New exit all status
    */
   public final void setExitAllOnClose(final boolean exitAllOnClose)
   {
      this.exitAllOnClose = exitAllOnClose;
   }
}