package jhelp.gui.smooth;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import jhelp.gui.action.GenericAction;
import jhelp.gui.smooth.event.JHelpComboBoxSmoothListener;
import jhelp.gui.smooth.event.JHelpListSmoothModelListener;
import jhelp.gui.smooth.event.JHelpListSmoothSelectListener;
import jhelp.gui.smooth.model.JHelpListSmoothModel;
import jhelp.gui.smooth.renderer.JHelpListSmoothRenderer;
import jhelp.gui.smooth.resources.JHelpResourcesSmooth;
import jhelp.gui.smooth.shape.ShadowLevel;
import jhelp.util.gui.JHelpFont;
import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedSimpleTask;

/**
 * Smooth combo box
 * 
 * @author JHelp
 */
public class JHelpComboBoxSmooth
      extends JHelpButtonSmooth
{
   /**
    * Action when click on combo box
    * 
    * @author JHelp
    */
   private static class ComboBoxAction
         extends GenericAction
   {
      /** Combo box parent */
      private JHelpComboBoxSmooth parent;

      /**
       * Create a new instance of ComboBoxAction
       * 
       * @param currentChoice
       *           Current combo box choice
       */
      ComboBoxAction(final String currentChoice)
      {
         super(currentChoice, JHelpResourcesSmooth.obtainJHelpImage("images/triangle_black_down.png"));
      }

      /**
       * Called when combo box clicked <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param actionEvent
       *           Event description
       * @see jhelp.gui.action.GenericAction#doActionPerformed(java.awt.event.ActionEvent)
       */
      @Override
      protected void doActionPerformed(final ActionEvent actionEvent)
      {
         this.parent.comboBoxClicked();
      }

      /**
       * Defines the combo box parent
       * 
       * @param parent
       *           New combo box parent
       */
      public void setParent(final JHelpComboBoxSmooth parent)
      {
         this.parent = parent;
      }
   }

   /**
    * Embed combo box list
    * 
    * @author JHelp
    */
   private class ComboBoxListInformation
         implements JHelpListSmoothModel<String>, JHelpListSmoothRenderer<String>, JHelpListSmoothSelectListener<String>
   {
      /** Choice list */
      private final String[] choice;

      /**
       * Create a new instance of ComboBoxListInformation
       * 
       * @param choice
       *           Choice list
       */
      public ComboBoxListInformation(final String... choice)
      {
         this.choice = choice;
      }

      /**
       * Create component for given element <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param element
       *           Element
       * @return Component associated
       * @see jhelp.gui.smooth.renderer.JHelpListSmoothRenderer#createComponent(java.lang.Object)
       */
      @Override
      public JHelpComponentSmooth createComponent(final String element)
      {
         return new JHelpLabelTextSmooth(element, JHelpComboBoxSmooth.this.getFont());
      }

      /**
       * Called when an element is selected <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param list
       *           List
       * @param element
       *           Selected element
       * @param index
       *           Element index
       * @see jhelp.gui.smooth.event.JHelpListSmoothSelectListener#elementSelected(jhelp.gui.smooth.JHelpListSmooth,
       *      java.lang.Object, int)
       */
      @Override
      public void elementSelected(final JHelpListSmooth<String> list, final String element, final int index)
      {
         JHelpComboBoxSmooth.this.choiceMade(element);
      }

      /**
       * Obtain an element <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param index
       *           Element index
       * @return Element
       * @see jhelp.gui.smooth.model.JHelpListSmoothModel#getElement(int)
       */
      @Override
      public String getElement(final int index)
      {
         return this.choice[index];
      }

      /**
       * Number of elements <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @return Number of elements
       * @see jhelp.gui.smooth.model.JHelpListSmoothModel#getNumberOfElement()
       */
      @Override
      public int getNumberOfElement()
      {
         return this.choice.length;
      }

      /**
       * Register model listener <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param listener
       *           Listener to register
       * @see jhelp.gui.smooth.model.JHelpListSmoothModel#registerModelListener(jhelp.gui.smooth.event.JHelpListSmoothModelListener)
       */
      @Override
      public void registerModelListener(final JHelpListSmoothModelListener<String> listener)
      {
      }

      /**
       * Transform/update a component <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param component
       *           Component to modify
       * @param element
       *           Element to draw
       * @param indexInList
       *           Index inside list
       * @param selected
       *           Indicates if selected
       * @see jhelp.gui.smooth.renderer.JHelpListSmoothRenderer#transformComponent(jhelp.gui.smooth.JHelpComponentSmooth,
       *      java.lang.Object, int, boolean)
       */
      @Override
      public void transformComponent(final JHelpComponentSmooth component, final String element, final int indexInList, final boolean selected)
      {
         final JHelpLabelTextSmooth labelTextSmooth = (JHelpLabelTextSmooth) component;
         labelTextSmooth.setText(element);

         if(selected == true)
         {
            labelTextSmooth.setBackground(JHelpConstantsSmooth.COLOR_CYAN_0900);
         }
         else
         {
            labelTextSmooth.setBackground(JHelpConstantsSmooth.COLOR_WHITE);
         }
      }

      /**
       * Unregister model listener <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param listener
       *           Listener to unregister
       * @see jhelp.gui.smooth.model.JHelpListSmoothModel#unregisterModelListener(jhelp.gui.smooth.event.JHelpListSmoothModelListener)
       */
      @Override
      public void unregisterModelListener(final JHelpListSmoothModelListener<String> listener)
      {
      }
   }

   /**
    * Task for fire that combo box changed
    * 
    * @author JHelp
    */
   private class TaskFireComboBoxSmoothValueChanged
         extends ThreadedSimpleTask<JHelpComboBoxSmoothListener>
   {
      /**
       * Create a new instance of TaskFireComboBoxSmoothValueChanged
       */
      TaskFireComboBoxSmoothValueChanged()
      {
      }

      /**
       * Do the task <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param parameter
       *           Listener to alert
       * @see jhelp.util.thread.ThreadedSimpleTask#doSimpleAction(java.lang.Object)
       */
      @Override
      protected void doSimpleAction(final JHelpComboBoxSmoothListener parameter)
      {
         parameter.comboBoxSmoothValueChanged(JHelpComboBoxSmooth.this, JHelpComboBoxSmooth.this.getCurrentChoice());
      }
   }

   /** Current choice */
   private String                                   currentChoice;
   /** Dialog description to be used by {@link JHelpFrameSmooth#createDialog(int)} to show combo box embed dialog */
   private final DialogDecsription                  dialogDecsription;
   /** Frame smooth parent */
   private final JHelpFrameSmooth                   frameSmooth;
   /** Dialog ID, used by {@link JHelpFrameSmooth#createDialog(int)} to show combo box embed dialog */
   private final int                                id;
   /** Combo box changes listeners */
   private final List<JHelpComboBoxSmoothListener>  listeners;
   /** Task for fire combo box value changes */
   private final TaskFireComboBoxSmoothValueChanged taskFireComboBoxSmoothValueChanged;

   /**
    * Create a new instance of JHelpComboBoxSmooth
    * 
    * @param id
    *           Dialog ID, used by {@link JHelpFrameSmooth#createDialog(int)} to show combo box embed dialog
    * @param frameSmooth
    *           Frame smooth parent
    * @param font
    *           Font to use
    * @param selectedIndex
    *           Start selected index
    * @param choice
    *           List of choice
    */
   public JHelpComboBoxSmooth(final int id, final JHelpFrameSmooth frameSmooth, final JHelpFont font, final int selectedIndex, final String... choice)
   {
      super(new ComboBoxAction(choice[selectedIndex]), JHelpButtonAlignSmooth.ICON_LEFT_TEXT_RIGHT, font);

      if(frameSmooth == null)
      {
         throw new NullPointerException("frameSmooth musn't be null");
      }

      ((ComboBoxAction) this.getAction()).setParent(this);
      this.id = id;
      this.frameSmooth = frameSmooth;
      this.currentChoice = choice[selectedIndex];
      this.listeners = new ArrayList<JHelpComboBoxSmoothListener>();
      this.taskFireComboBoxSmoothValueChanged = new TaskFireComboBoxSmoothValueChanged();
      final ComboBoxListInformation comboBoxListInformation = new ComboBoxListInformation(choice);
      final JHelpListSmooth<String> list = new JHelpListSmooth<String>(comboBoxListInformation, comboBoxListInformation, JHelpScrollModeSmooth.SCROLL_VERTICAL);
      list.setSelectIndex(selectedIndex);
      list.registerSelectListener(comboBoxListInformation);
      this.dialogDecsription = new DialogDecsription(DialogDecsription.CENTER_IN_PARENT, DialogDecsription.CENTER_IN_PARENT, list,
            JHelpConstantsSmooth.COLOR_WHITE, JHelpConstantsSmooth.COLOR_GREY_0800, ShadowLevel.NEAR);
   }

   /**
    * Called when choice made
    * 
    * @param choose
    *           New choice
    */
   void choiceMade(final String choose)
   {
      this.getAction().setName(choose);
      this.frameSmooth.hideDialog(this.id);
      this.currentChoice = choose;
      this.fireComboBoxSmoothValueChanged();
   }

   /**
    * Called when combo box clicked
    */
   void comboBoxClicked()
   {
      this.frameSmooth.showDialog(this.id);
   }

   /**
    * Alert listeners that combo box changed
    */
   protected void fireComboBoxSmoothValueChanged()
   {
      synchronized(this.listeners)
      {
         for(final JHelpComboBoxSmoothListener listener : this.listeners)
         {
            ThreadManager.THREAD_MANAGER.doThread(this.taskFireComboBoxSmoothValueChanged, listener);
         }
      }
   }

   /**
    * Current choice
    * 
    * @return Current choice
    */
   public String getCurrentChoice()
   {
      return this.currentChoice;
   }

   /**
    * Dialog description, used by {@link JHelpFrameSmooth#createDialog(int)} to show combo box embed dialog
    * 
    * @return Dialog description, used by {@link JHelpFrameSmooth#createDialog(int)} to show combo box embed dialog
    */
   public DialogDecsription getDialogDecsription()
   {
      return this.dialogDecsription;
   }

   /**
    * Dialog ID, used by {@link JHelpFrameSmooth#createDialog(int)} to show combo box embed dialog
    * 
    * @return Dialog ID, used by {@link JHelpFrameSmooth#createDialog(int)} to show combo box embed dialog
    */
   public int getID()
   {
      return this.id;
   }

   /**
    * Register listener of combo box events
    * 
    * @param listener
    *           Listener to register
    */
   public void registerJHelpComboBoxSmoothListener(final JHelpComboBoxSmoothListener listener)
   {
      if(listener == null)
      {
         throw new NullPointerException("listener musn't be null");
      }

      synchronized(this.listeners)
      {
         if(this.listeners.contains(listener) == false)
         {
            this.listeners.add(listener);
         }
      }
   }

   /**
    * Unregister listener of combo box events
    * 
    * @param listener
    *           Listener to unregister
    */
   public void unregisterJHelpComboBoxSmoothListener(final JHelpComboBoxSmoothListener listener)
   {
      synchronized(this.listeners)
      {
         this.listeners.remove(listener);
      }
   }
}