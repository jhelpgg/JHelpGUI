package jhelp.gui.smooth;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import jhelp.gui.action.GenericAction;
import jhelp.gui.smooth.event.JHelpOptionPaneSmoothListener;
import jhelp.gui.smooth.layout.JHelpBorderConstraintsSmooth;
import jhelp.gui.smooth.layout.JHelpBorderLayoutSmooth;
import jhelp.gui.smooth.layout.JHelpTableConstraintsSmooth;
import jhelp.gui.smooth.layout.JHelpTableLayoutSmooth;
import jhelp.gui.smooth.resources.JHelpResourcesSmooth;
import jhelp.gui.smooth.shape.ShadowLevel;
import jhelp.gui.smooth.shape.SmoothSaussage;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpPaint;

/**
 * Represents an option pane
 * 
 * @author JHelp
 */
public class JHelpOptionPaneSmooth
      implements JHelpConstantsSmooth
{
   class ActionButton
         extends GenericAction
   {
      private final OptionPaneButton optionPaneButton;

      public ActionButton(final OptionPaneButton optionPaneButton)
      {
         super(optionPaneButton.getKeyText(), JHelpResourcesSmooth.RESOURCE_TEXT);
         this.optionPaneButton = optionPaneButton;
      }

      @Override
      protected void doActionPerformed(final ActionEvent actionEvent)
      {
         JHelpOptionPaneSmooth.this.buttonClicked(this.optionPaneButton);
      }
   }

   private final int                                 background;

   private final JHelpFrameSmooth                    frameSmooth;
   private final int                                 id;
   private final List<JHelpOptionPaneSmoothListener> listeners;
   private final JHelpPanelSmooth                    mainPanel;
   private final JHelpPaint                          paintBackground;
   private final int                                 shadow;
   private final JHelpImage                          textureBackground;

   private JHelpOptionPaneSmooth(final int id, final JHelpFrameSmooth frameSmooth, final JHelpImage icon, final JHelpComponentSmooth component, final int background, final JHelpPaint paint, final JHelpImage texture, final int shadow,
         final OptionPaneType optionPaneType)
   {
      this.id = id;
      this.frameSmooth = frameSmooth;
      this.background = background;
      this.paintBackground = paint;
      this.textureBackground = texture;
      this.shadow = shadow;
      this.listeners = new ArrayList<JHelpOptionPaneSmoothListener>();

      this.mainPanel = new JHelpPanelSmooth(new JHelpBorderLayoutSmooth());

      if(icon != null)
      {
         final JHelpLabelImageSmooth imageSmooth = new JHelpLabelImageSmooth(icon);
         imageSmooth.setShadowLevel(ShadowLevel.NO_SHADOW);
         this.mainPanel.addComponent(imageSmooth, JHelpBorderConstraintsSmooth.LEFT);
      }

      this.mainPanel.addComponent(component, JHelpBorderConstraintsSmooth.CENTER);
      component.setShadowLevel(ShadowLevel.NO_SHADOW);

      final JHelpPanelSmooth panelButtons = new JHelpPanelSmooth(new JHelpTableLayoutSmooth());

      switch(optionPaneType)
      {
         case CANCEL_YES_NO:
            panelButtons.addComponent(this.createButton(OptionPaneButton.CANCEL), new JHelpTableConstraintsSmooth(0, 0));
            panelButtons.addComponent(this.createButton(OptionPaneButton.YES), new JHelpTableConstraintsSmooth(3, 0));
            panelButtons.addComponent(this.createButton(OptionPaneButton.NO), new JHelpTableConstraintsSmooth(4, 0));
         break;
         case OK:
            panelButtons.addComponent(this.createButton(OptionPaneButton.OK), new JHelpTableConstraintsSmooth(0, 0));
         break;
         case YES_NO:
            panelButtons.addComponent(this.createButton(OptionPaneButton.YES), new JHelpTableConstraintsSmooth(0, 0));
            panelButtons.addComponent(this.createButton(OptionPaneButton.NO), new JHelpTableConstraintsSmooth(1, 0));
         break;
      }

      this.mainPanel.addComponent(panelButtons, JHelpBorderConstraintsSmooth.DOWN_EXPAND);
   }

   public JHelpOptionPaneSmooth(final int id, final JHelpFrameSmooth frameSmooth, final JHelpComponentSmooth component, final int background, final int shadow, final OptionPaneType optionPaneType)
   {
      this(id, frameSmooth, null, component, background, shadow, optionPaneType);
   }

   public JHelpOptionPaneSmooth(final int id, final JHelpFrameSmooth frameSmooth, final JHelpComponentSmooth component, final JHelpImage background, final int shadow, final OptionPaneType optionPaneType)
   {
      this(id, frameSmooth, null, component, background, shadow, optionPaneType);
   }

   public JHelpOptionPaneSmooth(final int id, final JHelpFrameSmooth frameSmooth, final JHelpComponentSmooth component, final JHelpPaint background, final int shadow, final OptionPaneType optionPaneType)
   {
      this(id, frameSmooth, null, component, background, shadow, optionPaneType);
   }

   public JHelpOptionPaneSmooth(final int id, final JHelpFrameSmooth frameSmooth, final JHelpImage icon, final JHelpComponentSmooth component, final int background, final int shadow, final OptionPaneType optionPaneType)
   {
      this(id, frameSmooth, icon, component, background, null, null, shadow, optionPaneType);
   }

   public JHelpOptionPaneSmooth(final int id, final JHelpFrameSmooth frameSmooth, final JHelpImage icon, final JHelpComponentSmooth component, final JHelpImage background, final int shadow, final OptionPaneType optionPaneType)
   {
      this(id, frameSmooth, icon, component, JHelpConstantsSmooth.COLOR_CYAN_0500, null, background, shadow, optionPaneType);
   }

   public JHelpOptionPaneSmooth(final int id, final JHelpFrameSmooth frameSmooth, final JHelpImage icon, final JHelpComponentSmooth component, final JHelpPaint background, final int shadow, final OptionPaneType optionPaneType)
   {
      this(id, frameSmooth, icon, component, JHelpConstantsSmooth.COLOR_CYAN_0500, background, null, shadow, optionPaneType);
   }

   private JHelpButtonSmooth createButton(final OptionPaneButton optionPaneButton)
   {
      final JHelpButtonSmooth buttonSmooth = new JHelpButtonSmooth(new ActionButton(optionPaneButton));
      buttonSmooth.setShape(SmoothSaussage.SAUSSAGE);
      return buttonSmooth;
   }

   void buttonClicked(final OptionPaneButton optionPaneButton)
   {
      this.frameSmooth.hideDialog(this.id);

      for(final JHelpOptionPaneSmoothListener listener : this.listeners)
      {
         listener.optionPaneButtonClicked(this, optionPaneButton);
      }
   }

   /**
    * Create dialog description for show the option pane.<br>
    * Use it with {@link JHelpFrameSmooth#createDialog(int)}
    * 
    * @return Dialog description
    */
   public DialogDecsription createDialogDescriDecsription()
   {
      final Dimension size = this.mainPanel.getPreferredSize();
      final Dimension screen = this.frameSmooth.getFrameSize();
      final int x = (screen.width - size.width) >> 1;
      final int y = (screen.height - size.height) >> 1;

      if(this.paintBackground != null)
      {
         return new DialogDecsription(x, y, this.mainPanel, this.paintBackground, this.shadow, ShadowLevel.FAR);
      }

      if(this.textureBackground != null)
      {
         return new DialogDecsription(x, y, this.mainPanel, this.textureBackground, this.shadow, ShadowLevel.FAR);
      }

      return new DialogDecsription(x, y, this.mainPanel, this.background, this.shadow, ShadowLevel.FAR);
   }

   public int getID()
   {
      return this.id;
   }

   public void registerJHelpOptionPaneSmoothListener(final JHelpOptionPaneSmoothListener listener)
   {
      if((listener != null) && (this.listeners.contains(listener) == false))
      {
         this.listeners.add(listener);
      }
   }

   public void unregisterJHelpOptionPaneSmoothListener(final JHelpOptionPaneSmoothListener listener)
   {
      this.listeners.remove(listener);
   }
}