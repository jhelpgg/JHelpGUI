package jhelp.gui.twoD;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import jhelp.gui.JHelpMouseListener;
import jhelp.gui.twoD.JHelpHorizontalLayout.JHelpHorizontalLayoutConstraints;
import jhelp.gui.twoD.JHelpVerticalLayout.JHelpVerticalLayoutConstraints;
import jhelp.util.gui.JHelpFont;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpMask;

/**
 * A list od elements
 * 
 * @author JHelp
 * @param <INFORMATION>
 *           Elements type
 */
public class JHelpList2D<INFORMATION>
      extends JHelpScrollPane2D
{
   /**
    * Event manager to react to model change and mouse events
    * 
    * @author JHelp
    */
   class EventManager
         implements JHelpListModelListener<INFORMATION>, JHelpMouseListener
   {
      /**
       * Create a new instance of EventManager
       */
      EventManager()
      {
      }

      /**
       * Called when list model changed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param listModel
       *           List model that changed
       * @see jhelp.gui.twoD.JHelpListModelListener#listModelChanged(jhelp.gui.twoD.JHelpListModel)
       */
      @Override
      public void listModelChanged(final JHelpListModel<INFORMATION> listModel)
      {
         JHelpList2D.this.updateList();
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
         final JHelpComponent2D component2d = UtilTwoD.getComponent2DFromMouseEvent(e);
         JHelpList2D.this.setSelectedIndex(component2d.getId());
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
       * Callled when mouse enter <br>
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
       * Called when mouse exit <br>
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
       * Called when mouse release <br>
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
       * Called when mouse whell moved <br>
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
   }

   /** Default background color */
   public static final int                   BACKGROUND = 0xFFFFFFFF;
   /** Default font for texts */
   public static final JHelpFont             FONT       = new JHelpFont("Arial", 16);
   /** Default foreground color */
   public static final int                   FOREGROUND = 0xFF000000;
   /** Defult selection color */
   public static final int                   SELECTION  = 0xFFC0C0FF;
   /** Actual background color */
   private int                               background;
   /** Event manager */
   private final EventManager                eventManager;
   /** Actual font for texts */
   private JHelpFont                         font;
   /** Actual foreground color */
   private int                               foreground;
   /** Indicates if the list is horizontal or vertical */
   private final boolean                     horizontal;
   /** Listener of list events */
   private JHelpListListener<INFORMATION>    listListener;
   /** Embed model */
   private final JHelpListModel<INFORMATION> listModel;
   /** Current selection color */
   private int                               selection;
   /** Selected index */
   int                                       selectedIndex;

   /**
    * Create a new instance of JHelpList2D
    * 
    * @param horizontal
    *           Indicates if the list is horizontal ({@code true}) or vertical ({@code false})
    * @param listModel
    *           Model to use
    */
   public JHelpList2D(final boolean horizontal, final JHelpListModel<INFORMATION> listModel)
   {
      super(new JHelpPanel2D(horizontal == true
            ? new JHelpHorizontalLayout()
            : new JHelpVerticalLayout()));

      this.font = JHelpTree2D.FONT;
      this.background = JHelpTree2D.BACKGROUND;
      this.foreground = JHelpTree2D.FOREGROUND;
      this.selection = JHelpList2D.SELECTION;

      this.selectedIndex = -1;
      this.horizontal = horizontal;
      this.eventManager = new EventManager();
      this.listModel = listModel;
      this.updateList();
      listModel.registerJHelpListModelListener(this.eventManager);
   }

   /**
    * Update the list content
    */
   void updateList()
   {
      final JHelpPanel2D panel2d = (JHelpPanel2D) this.getScrollView();
      panel2d.clearComponents();

      final int size = this.listModel.numberOfElement();
      this.selectedIndex = (this.selectedIndex >= 0) && (this.selectedIndex < size)
            ? this.selectedIndex
            : -1;
      INFORMATION information;
      JHelpImage image;
      String text;
      JHelpBackgroundRoundRectangle backgroundRoundRectangle;
      JHelpLabelImage2D labelImage2D;
      final JHelpConstraints constraints = this.horizontal == true
            ? JHelpHorizontalLayoutConstraints.EXPANDED
            : JHelpVerticalLayoutConstraints.EXPANDED;

      for(int i = 0; i < size; i++)
      {
         image = null;
         text = null;
         information = this.listModel.getElement(i);

         if(this.listModel.useImageRepresentation(information) == true)
         {
            image = this.listModel.obtainImageRepresentation(information);
         }

         if(image == null)
         {
            text = this.listModel.obtainTextRepresentation(information);

            if(text == null)
            {
               text = "null";
            }

            final JHelpMask mask = this.font.createMask(text);
            image = new JHelpImage(mask.getWidth(), mask.getHeight());
            image.startDrawMode();
            image.paintMask(0, 0, mask, this.foreground, 0, false);
            image.endDrawMode();
         }

         labelImage2D = new JHelpLabelImage2D(image);
         labelImage2D.setId(i);
         labelImage2D.setMouseListener(this.eventManager);
         backgroundRoundRectangle = new JHelpBackgroundRoundRectangle(labelImage2D, i == this.selectedIndex
               ? this.selection
               : this.background);
         panel2d.addComponent2D(backgroundRoundRectangle, constraints);
      }
   }

   /**
    * Background color
    * 
    * @return Background color
    */
   public int getBackground()
   {
      return this.background;
   }

   /**
    * Text font
    * 
    * @return Text font
    */
   public JHelpFont getFont()
   {
      return this.font;
   }

   /**
    * Text color
    * 
    * @return Text color
    */
   public int getForeground()
   {
      return this.foreground;
   }

   /**
    * Linked list listener
    * 
    * @return List listener
    */
   public JHelpListListener<INFORMATION> getListListener()
   {
      return this.listListener;
   }

   /**
    * List model
    * 
    * @return List model
    */
   public JHelpListModel<INFORMATION> getListModel()
   {
      return this.listModel;
   }

   /**
    * Selected index.<br>
    * Value &lt;0 for no selection
    * 
    * @return Selected index or -1 for no selection
    */
   public int getSelectedIndex()
   {
      return this.selectedIndex;
   }

   /**
    * Selected information
    * 
    * @return Selected information
    */
   public INFORMATION getSelectedInformation()
   {
      if(this.selectedIndex < 0)
      {
         return null;
      }

      return this.listModel.getElement(this.selectedIndex);
   }

   /**
    * Selection color
    * 
    * @return Selection color
    */
   public int getSelection()
   {
      return this.selection;
   }

   /**
    * Change background color
    * 
    * @param background
    *           New background color
    */
   public void setBackground(final int background)
   {
      if(this.background == background)
      {
         return;
      }

      this.background = background;
      this.updateList();
   }

   /**
    * Change font for texts
    * 
    * @param font
    *           New font for texts
    */
   public void setFont(final JHelpFont font)
   {
      if(font == null)
      {
         throw new NullPointerException("font musn't be null");
      }

      if(this.font.equals(font) == true)
      {
         return;
      }

      this.font = font;
      this.updateList();
   }

   /**
    * Change texts color
    * 
    * @param foreground
    *           New texts color
    */
   public void setForeground(final int foreground)
   {
      if(this.foreground == foreground)
      {
         return;
      }

      this.foreground = foreground;
      this.updateList();
   }

   /**
    * Defines the list listener.<br>
    * Use {@code null} for remove listener
    * 
    * @param listListener
    *           New list listener or {@code null} for no listener
    */
   public void setListListener(final JHelpListListener<INFORMATION> listListener)
   {
      this.listListener = listListener;
   }

   /**
    * Cahnge slected index
    * 
    * @param selectedIndex
    *           New selected index or -1 for no selection
    */
   public void setSelectedIndex(final int selectedIndex)
   {
      final int size = this.listModel.numberOfElement();
      final int newSelectedIndex = (selectedIndex >= 0) && (selectedIndex < size)
            ? selectedIndex
            : -1;

      if(newSelectedIndex == this.selectedIndex)
      {
         return;
      }

      final JHelpPanel2D panel2d = (JHelpPanel2D) this.getScrollView();

      if(this.selectedIndex >= 0)
      {
         final JHelpBackgroundRoundRectangle roundRectangle = (JHelpBackgroundRoundRectangle) panel2d.children().get(this.selectedIndex);
         roundRectangle.setColorBackground(this.background);
      }

      this.selectedIndex = newSelectedIndex;
      INFORMATION information = null;

      if(this.selectedIndex >= 0)
      {
         final JHelpBackgroundRoundRectangle roundRectangle = (JHelpBackgroundRoundRectangle) panel2d.children().get(this.selectedIndex);
         roundRectangle.setColorBackground(this.selection);

         this.tryMakeVisible(roundRectangle.getBounds());

         information = this.listModel.getElement(this.selectedIndex);
      }

      if(this.listListener != null)
      {
         this.listListener.listSelectionChanged(this, this.selectedIndex, information);
      }
   }

   /**
    * Change selection color
    * 
    * @param selection
    *           New selection color
    */
   public void setSelection(final int selection)
   {
      if(this.selection == selection)
      {
         return;
      }

      this.selection = selection;
      this.updateList();
   }
}