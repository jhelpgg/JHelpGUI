package jhelp.gui;

import java.awt.BorderLayout;

/**
 * Frame for editing an image area
 * 
 * @author JHelp
 */
public class FrameEditArea
      extends JHelpFrame
{
   /** Component that edit an image area */
   private EditImageArea editImageArea;
   /** File chooser */
   private FileChooser   fileChooser;

   /**
    * Create a new instance of FrameEditArea
    */
   public FrameEditArea()
   {
      super("Edit area", false, true);
   }

   /**
    * Add UI listeners <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.JHelpFrame#addListeners()
    */
   @Override
   protected void addListeners()
   {
   }

   /***
    * Create frame components <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.JHelpFrame#createComponents()
    */
   @Override
   protected void createComponents()
   {
      this.fileChooser = new FileChooser(this);
      this.editImageArea = new EditImageArea(this.fileChooser);
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
   protected void layoutComponents()
   {
      this.setLayout(new BorderLayout());
      this.add(this.editImageArea, BorderLayout.CENTER);
   }
}