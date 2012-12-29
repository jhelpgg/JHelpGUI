package jhelp.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

import jhelp.util.list.Pair;
import jhelp.util.resources.ResourceText;
import jhelp.util.resources.ResourceTextListener;
import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedSimpleTask;

/**
 * Generic action that can be used with JButton, JRadioButton, JMenuItem, ... <br>
 * In UI, its often that you have a button, a menu and a short cut that do exactly the same action, for this its a good idea to
 * create the action once and used it every where at need. For example if text associate to action change or if the action have
 * to be disabled, you have to do it once with the generic action and changes are automatically shared to each compoent that
 * used it.<br>
 * More over here you can also, instead of giving directly the text, specify a resource text key and in doing so if the language
 * change, the action is automatically update, that will updated all linked components
 * 
 * @author JHelp
 */
public abstract class GenericAction
      extends AbstractAction
{
   /**
    * Action called when text resource change language
    * 
    * @author JHelp
    */
   private class ActionResourceTextListener
         implements ResourceTextListener
   {
      /**
       * Create a new instance of ActionResourceTextListener
       */
      ActionResourceTextListener()
      {
      }

      /**
       * Called when language changed
       * 
       * @param resourceText
       *           Resource text that changed
       */
      @Override
      public void resourceTextLanguageChanged(final ResourceText resourceText)
      {
         GenericAction.this.doResourceTextLanguageChanged(resourceText);
      }
   }

   /** Task for doing action */
   private static final ThreadedSimpleTask<Pair<GenericAction, ActionEvent>> TASK_ACTION_PERFORMED = new ThreadedSimpleTask<Pair<GenericAction, ActionEvent>>()
                                                                                                   {
                                                                                                      /**
                                                                                                       * Called when action have
                                                                                                       * to do its action <br>
                                                                                                       * <br>
                                                                                                       * <b>Parent
                                                                                                       * documentation:</b><br>
                                                                                                       * {@inheritDoc}
                                                                                                       * 
                                                                                                       * @param parameter
                                                                                                       *           Action
                                                                                                       *           parameters
                                                                                                       * @see jhelp.util.thread.ThreadedSimpleTask#doSimpleAction(java.lang.Object)
                                                                                                       */
                                                                                                      @Override
                                                                                                      protected void doSimpleAction(final Pair<GenericAction, ActionEvent> parameter)
                                                                                                      {
                                                                                                         parameter.element1.doActionPerformed(parameter.element2);
                                                                                                      }
                                                                                                   };
   /** Resource text key for action label */
   private String                                                            keyName;
   /** Resource text key for action tooltip */
   private String                                                            keyTooltip;

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Action text
    */
   public GenericAction(final String keyName)
   {
      this(keyName, null, null, null, null);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Action text
    * @param icon
    *           Action icon
    */
   public GenericAction(final String keyName, final Icon icon)
   {
      this(keyName, icon, icon, null, null);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Action text
    * @param smallIcon
    *           Action icon small version
    * @param largeIcon
    *           Action icon large version
    */
   public GenericAction(final String keyName, final Icon smallIcon, final Icon largeIcon)
   {
      this(keyName, smallIcon, largeIcon, null, null);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Key for action text
    * @param smallIcon
    *           Action icon small version
    * @param largeIcon
    *           Action icon large version
    * @param resourceText
    *           Resource text where found text for the key
    */
   public GenericAction(final String keyName, final Icon smallIcon, final Icon largeIcon, final ResourceText resourceText)
   {
      this(keyName, smallIcon, largeIcon, null, resourceText);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Action text
    * @param smallIcon
    *           Action icon small version
    * @param largeIcon
    *           Action icon large version
    * @param keyTooltip
    *           Action tool tip text
    */
   public GenericAction(final String keyName, final Icon smallIcon, final Icon largeIcon, final String keyTooltip)
   {
      this(keyName, smallIcon, largeIcon, null, null);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Key for action text
    * @param smallIcon
    *           Action icon small version
    * @param largeIcon
    *           Action icon large version
    * @param keyTooltip
    *           Key for action tool tip text
    * @param resourceText
    *           Resource text where found text for the keys
    */
   public GenericAction(final String keyName, Icon smallIcon, Icon largeIcon, final String keyTooltip, final ResourceText resourceText)
   {
      if(keyName == null)
      {
         throw new NullPointerException("name musn't be null");
      }

      if(resourceText != null)
      {
         resourceText.register(new ActionResourceTextListener());

         this.keyName = keyName;
         this.keyTooltip = keyTooltip;

         this.putValue(Action.NAME, resourceText.getText(keyName));
      }
      else
      {
         this.putValue(Action.NAME, keyName);
      }

      if(smallIcon == null)
      {
         smallIcon = largeIcon;
      }

      if(largeIcon == null)
      {
         largeIcon = smallIcon;
      }

      if(smallIcon != null)
      {
         this.putValue(Action.SMALL_ICON, smallIcon);
      }

      if(largeIcon != null)
      {
         this.putValue(Action.LARGE_ICON_KEY, largeIcon);
      }

      if(keyTooltip != null)
      {
         if(resourceText != null)
         {
            this.putValue(Action.SHORT_DESCRIPTION, resourceText.getText(keyTooltip));
         }
         else
         {
            this.putValue(Action.SHORT_DESCRIPTION, keyTooltip);
         }
      }
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Key for action text
    * @param icon
    *           Action icon
    * @param resourceText
    *           Resource text where found text for the keys
    */
   public GenericAction(final String keyName, final Icon icon, final ResourceText resourceText)
   {
      this(keyName, icon, icon, null, resourceText);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Action text
    * @param icon
    *           Action icon
    * @param keyTooltip
    *           Action tool tip text
    */
   public GenericAction(final String keyName, final Icon icon, final String keyTooltip)
   {
      this(keyName, icon, icon, keyTooltip, null);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Key for action text
    * @param icon
    *           Action icon
    * @param keyTooltip
    *           Key for action tool tip text
    * @param resourceText
    *           Resource text where found text for the keys
    */
   public GenericAction(final String keyName, final Icon icon, final String keyTooltip, final ResourceText resourceText)
   {
      this(keyName, icon, icon, keyTooltip, resourceText);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Key for action text
    * @param resourceText
    *           Resource text where found text for the keys
    */
   public GenericAction(final String keyName, final ResourceText resourceText)
   {
      this(keyName, null, null, null, resourceText);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Action text
    * @param keyTooltip
    *           Action tool tip text
    */
   public GenericAction(final String keyName, final String keyTooltip)
   {
      this(keyName, null, null, keyTooltip, null);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Key for action text
    * @param keyTooltip
    *           Key for action tool tip text
    * @param resourceText
    *           Resource text where found text for the keys
    */
   public GenericAction(final String keyName, final String keyTooltip, final ResourceText resourceText)
   {
      this(keyName, null, null, keyTooltip, resourceText);
   }

   /**
    * Called if resource text associated change of language
    * 
    * @param resourceText
    *           Resource text that changed
    */
   void doResourceTextLanguageChanged(final ResourceText resourceText)
   {
      this.putValue(Action.NAME, resourceText.getText(this.keyName));

      if(this.keyTooltip != null)
      {
         this.putValue(Action.SHORT_DESCRIPTION, resourceText.getText(this.keyTooltip));
      }
   }

   /**
    * Called when action have to do its action
    * 
    * @param actionEvent
    *           Action event description
    */
   protected abstract void doActionPerformed(ActionEvent actionEvent);

   /**
    * Called when action have to do its action <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param actionEvent
    *           Action event description
    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
    */
   @Override
   public final void actionPerformed(final ActionEvent actionEvent)
   {
      ThreadManager.THREAD_MANAGER.doThread(GenericAction.TASK_ACTION_PERFORMED, new Pair<GenericAction, ActionEvent>(this, actionEvent));
   }

   /**
    * Change action text
    * 
    * @param name
    *           New text
    */
   public void setName(final String name)
   {
      if(name == null)
      {
         throw new NullPointerException("name musn't be null");
      }

      this.putValue(Action.NAME, name);
   }
}