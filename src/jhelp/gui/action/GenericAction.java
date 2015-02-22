package jhelp.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

import jhelp.util.debug.Debug;
import jhelp.util.debug.DebugLevel;
import jhelp.util.gui.JHelpImage;
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
    * Manage text events
    * 
    * @author JHelp
    */
   class EventManager
         implements ResourceTextListener
   {
      /**
       * Create a new instance of EventManager
       */
      EventManager()
      {
      }

      /**
       * Called when resource text language changed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       * 
       * @param resourceText
       *           Resource text source
       * @see jhelp.util.resources.ResourceTextListener#resourceTextLanguageChanged(jhelp.util.resources.ResourceText)
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
                                                                                                      protected void doSimpleAction(
                                                                                                            final Pair<GenericAction, ActionEvent> parameter)
                                                                                                      {
                                                                                                         parameter.element1.doActionPerformed(parameter.element2);
                                                                                                      }
                                                                                                   };

   /** Resource text key for action label */
   private String                                                            keyName;
   /** Resource text key for action tooltip */
   private String                                                            keyTooltip;
   /** Resource text for resolve action key name and tool tip key */
   private final ResourceText                                                resourceText;

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
   public GenericAction(final String keyName, final JHelpImage icon)
   {
      this(keyName, icon, icon, null, null);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Action text
    * @param smallJHelpImage
    *           Action icon small version
    * @param largeJHelpImage
    *           Action icon large version
    */
   public GenericAction(final String keyName, final JHelpImage smallJHelpImage, final JHelpImage largeJHelpImage)
   {
      this(keyName, smallJHelpImage, largeJHelpImage, null, null);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Key for action text
    * @param smallJHelpImage
    *           Action icon small version
    * @param largeJHelpImage
    *           Action icon large version
    * @param resourceText
    *           Resource text where found text for the key
    */
   public GenericAction(final String keyName, final JHelpImage smallJHelpImage, final JHelpImage largeJHelpImage, final ResourceText resourceText)
   {
      this(keyName, smallJHelpImage, largeJHelpImage, null, resourceText);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Action text
    * @param smallJHelpImage
    *           Action icon small version
    * @param largeJHelpImage
    *           Action icon large version
    * @param keyTooltip
    *           Action tool tip text
    */
   public GenericAction(final String keyName, final JHelpImage smallJHelpImage, final JHelpImage largeJHelpImage, final String keyTooltip)
   {
      this(keyName, smallJHelpImage, largeJHelpImage, null, null);
   }

   /**
    * Create a new instance of GenericAction
    * 
    * @param keyName
    *           Key for action text
    * @param smallJHelpImage
    *           Action icon small version
    * @param largeJHelpImage
    *           Action icon large version
    * @param keyTooltip
    *           Key for action tool tip text
    * @param resourceText
    *           Resource text where found text for the keys
    */
   public GenericAction(final String keyName, JHelpImage smallJHelpImage, JHelpImage largeJHelpImage, final String keyTooltip, final ResourceText resourceText)
   {
      if(keyName == null)
      {
         throw new NullPointerException("name musn't be null");
      }

      this.resourceText = resourceText;

      if(this.resourceText != null)
      {
         this.resourceText.register(new EventManager());
      }

      this.keyName = keyName;
      this.keyTooltip = keyTooltip;
      this.putValue(Action.NAME, keyName);

      if(smallJHelpImage == null)
      {
         smallJHelpImage = largeJHelpImage;
      }

      if(largeJHelpImage == null)
      {
         largeJHelpImage = smallJHelpImage;
      }

      if(smallJHelpImage != null)
      {
         this.putValue(Action.SMALL_ICON, smallJHelpImage);
      }

      if(largeJHelpImage != null)
      {
         this.putValue(Action.LARGE_ICON_KEY, largeJHelpImage);
      }

      if(keyTooltip != null)
      {
         this.putValue(Action.SHORT_DESCRIPTION, keyTooltip);
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
   public GenericAction(final String keyName, final JHelpImage icon, final ResourceText resourceText)
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
   public GenericAction(final String keyName, final JHelpImage icon, final String keyTooltip)
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
   public GenericAction(final String keyName, final JHelpImage icon, final String keyTooltip, final ResourceText resourceText)
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
   final void doResourceTextLanguageChanged(final ResourceText resourceText)
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
    * Large icon
    * 
    * @return Large icon
    */
   public final JHelpImage getLargeIcon()
   {
      return (JHelpImage) this.getValue(Action.LARGE_ICON_KEY);
   }

   /**
    * Action name
    * 
    * @return Action name
    */
   public final String getName()
   {
      return this.keyName;
   }

   /**
    * Action name currently show to the user
    * 
    * @return Action name currently show to the user
    */
   public final String getPrintName()
   {
      return (String) this.getValue(Action.NAME);
   }

   /**
    * Tool tip text may show to the user
    * 
    * @return Tool tip text may show to the user
    */
   public final String getPrintToolTip()
   {
      return (String) this.getValue(Action.SHORT_DESCRIPTION);
   }

   /**
    * Small icon
    * 
    * @return Small icon
    */
   public final JHelpImage getSmallIcon()
   {
      return (JHelpImage) this.getValue(Action.SMALL_ICON);
   }

   /**
    * Tool tip key
    * 
    * @return Tool tip key
    */
   public final String getToolTip()
   {
      return this.keyTooltip;
   }

   /**
    * Change a value <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param key
    *           Value key
    * @param value
    *           Value value
    * @see javax.swing.AbstractAction#putValue(java.lang.String, java.lang.Object)
    */
   @Override
   public final void putValue(final String key, Object value)
   {
      if(key == null)
      {
         throw new NullPointerException("key musn't be null");
      }

      if(Action.NAME.equals(key) == true)
      {
         if(value == null)
         {
            throw new NullPointerException("value for name musn't be null");
         }

         final String name = value.toString();
         this.keyName = name;

         if(this.resourceText == null)
         {
            value = name;
         }
         else
         {
            value = this.resourceText.getText(name);
         }
      }
      else if(Action.SHORT_DESCRIPTION.equals(key) == true)
      {
         if(value != null)
         {
            final String name = value.toString();
            this.keyTooltip = name;

            if(this.resourceText == null)
            {
               value = name;
            }
            else
            {
               value = this.resourceText.getText(name);
            }
         }
         else
         {
            this.keyTooltip = null;
         }
      }
      else if((Action.SMALL_ICON.equals(key) == true) || (Action.LARGE_ICON_KEY.equals(key) == true))
      {
         if(value != null)
         {
            if((value instanceof Icon) == false)
            {
               throw new IllegalArgumentException("A javax.swing.Icon or a jhelp.util.gui.JHelpImage should be associate to the key " + key);
            }

            value = JHelpImage.toJHelpImage((Icon) value);
         }
      }
      else if("enabled".equals(key) == false)
      {
         Debug.println(DebugLevel.VERBOSE, "The key ", key, " is not managed here");
         return;
      }

      super.putValue(key, value);
   }

   /**
    * Change large and small icons in same time
    * 
    * @param icon
    *           New large and small icons
    */
   public final void setIcons(final JHelpImage icon)
   {
      this.putValue(Action.SMALL_ICON, icon);
      this.putValue(Action.LARGE_ICON_KEY, icon);
   }

   /**
    * Change large icon
    * 
    * @param icon
    *           New large icon
    */
   public final void setLargeIcon(final JHelpImage icon)
   {
      this.putValue(Action.LARGE_ICON_KEY, icon);
   }

   /**
    * Change action text
    * 
    * @param name
    *           New text
    */
   public final void setName(final String name)
   {
      if(name == null)
      {
         throw new NullPointerException("name musn't be null");
      }

      this.putValue(Action.NAME, name);
   }

   /**
    * Change small icon
    * 
    * @param icon
    *           New small icon
    */
   public final void setSmallIcon(final JHelpImage icon)
   {
      this.putValue(Action.SMALL_ICON, icon);
   }

   /**
    * Change tool tip text or key
    * 
    * @param toolTip
    *           New tool tip text or key
    */
   public final void setToolTip(final String toolTip)
   {
      this.putValue(Action.SHORT_DESCRIPTION, toolTip);
   }
}