package jhelp.gui.samples.smooth;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import jhelp.gui.action.GenericAction;
import jhelp.gui.smooth.DialogDecsription;
import jhelp.gui.smooth.JHelpButtonAlignSmooth;
import jhelp.gui.smooth.JHelpButtonSmooth;
import jhelp.gui.smooth.JHelpComponentSmooth;
import jhelp.gui.smooth.JHelpConstantsSmooth;
import jhelp.gui.smooth.JHelpEditTextSmooth;
import jhelp.gui.smooth.JHelpFrameSmooth;
import jhelp.gui.smooth.JHelpLabelImageSmooth;
import jhelp.gui.smooth.JHelpLabelTextSmooth;
import jhelp.gui.smooth.JHelpListSmooth;
import jhelp.gui.smooth.JHelpOptionPaneSmooth;
import jhelp.gui.smooth.JHelpPanelSmooth;
import jhelp.gui.smooth.JHelpScrollModeSmooth;
import jhelp.gui.smooth.JHelpScrollPaneSmooth;
import jhelp.gui.smooth.OptionPaneButton;
import jhelp.gui.smooth.OptionPaneType;
import jhelp.gui.smooth.ShortCut;
import jhelp.gui.smooth.event.JHelpListSmoothSelectListener;
import jhelp.gui.smooth.event.JHelpOptionPaneSmoothListener;
import jhelp.gui.smooth.event.SmoothEditTextListener;
import jhelp.gui.smooth.layout.JHelpBorderConstraintsSmooth;
import jhelp.gui.smooth.layout.JHelpBorderLayoutSmooth;
import jhelp.gui.smooth.layout.JHelpTableConstraintsSmooth;
import jhelp.gui.smooth.layout.JHelpTableLayoutSmooth;
import jhelp.gui.smooth.model.JHelpListSmoothModelDefault;
import jhelp.gui.smooth.renderer.JHelpListSmoothRenderer;
import jhelp.gui.smooth.shape.ShadowLevel;
import jhelp.gui.smooth.shape.SmoothEllipse;
import jhelp.gui.smooth.shape.SmoothRoundRectangle;
import jhelp.gui.smooth.shape.SmoothSaussage;
import jhelp.util.debug.Debug;
import jhelp.util.debug.DebugLevel;
import jhelp.util.gui.JHelpGradient;
import jhelp.util.gui.JHelpGradientAlphaCircle;
import jhelp.util.gui.JHelpGradientHorizontal;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.JHelpTextAlign;
import jhelp.util.gui.UtilGUI;
import jhelp.util.math.random.JHelpRandom;

public class SmoothExample
      extends JHelpFrameSmooth
{
   class ActionClick
         extends GenericAction
   {
      private final int id;

      ActionClick(final int id)
      {
         super("Button " + id);
         this.id = id;
      }

      @Override
      protected void doActionPerformed(final ActionEvent actionEvent)
      {
         Debug.println(DebugLevel.INFORMATION, "Click on ", this.id);
      }
   }

   class ActionCloseDialog
         extends GenericAction
   {
      ActionCloseDialog()
      {
         super("Close dialog");
      }

      @Override
      protected void doActionPerformed(final ActionEvent actionEvent)
      {
         SmoothExample.this.hideDialog(42);
      }
   }

   class ActionCloseFrame
         extends GenericAction
   {
      ActionCloseFrame()
      {
         super("Close");

         final JHelpGradientAlphaCircle gradientAlphaCircle = new JHelpGradientAlphaCircle(JHelpConstantsSmooth.COLOR_LIME_0500, JHelpGradientAlphaCircle.MULTIPLIER_VERY_THICK);

         final JHelpImage image = new JHelpImage(64, 64, 0x00FFFFFF);
         image.startDrawMode();
         image.fillRoundRectangle(0, 0, 64, 64, 16, 16, gradientAlphaCircle);
         image.drawThickLine(16, 16, 48, 48, SmoothExample.SIZE, JHelpConstantsSmooth.COLOR_RED_0500);
         image.drawThickLine(16, 48, 48, 16, SmoothExample.SIZE, JHelpConstantsSmooth.COLOR_RED_0500);
         image.endDrawMode();

         this.setIcons(image);
      }

      @Override
      protected void doActionPerformed(final ActionEvent actionEvent)
      {
         SmoothExample.smoothExample.closeFrame();
      }
   }

   class ActionPrintSomething
         extends GenericAction
         implements SmoothEditTextListener, JHelpListSmoothSelectListener<String>, JHelpOptionPaneSmoothListener
   {
      ActionPrintSomething()
      {
         super("Print");
      }

      @Override
      protected void doActionPerformed(final ActionEvent actionEvent)
      {
         final JHelpButtonAlignSmooth alignSmooth = JHelpRandom.random(JHelpButtonAlignSmooth.class);
         SmoothExample.this.buttonSmooth.setButtonAlignSmooth(alignSmooth);
         Debug.println(DebugLevel.INFORMATION, "Something ! : ", alignSmooth);
         SmoothExample.this.actionPrintSomething.setEnabled(SmoothExample.this.actionPrintSomething.isEnabled() == false);
         SmoothExample.this.showDialog(123);
      }

      @Override
      public void editTextEnterTyped(final JHelpEditTextSmooth editTextSmooth)
      {
         // {@todo} TODO Implements editTextEnterTyped
         Debug.printTodo("Implements editTextEnterTyped : ", editTextSmooth.getText());
      }

      @Override
      public void elementSelected(final JHelpListSmooth<String> list, final String element, final int index)
      {
         // {@todo} TODO Implements elementSelected
         Debug.printTodo("Implements elementSelected : ", element);
      }

      @Override
      public void optionPaneButtonClicked(final JHelpOptionPaneSmooth optionPane, final OptionPaneButton button)
      {
         switch(button)
         {
            case YES:
               if(optionPane.getID() == SmoothExample.this.optionPaneSmooth.getID())
               {
                  SmoothExample.this.showDialog(42);
               }
               else if(optionPane.getID() == SmoothExample.this.optionPaneExit.getID())
               {
                  SmoothExample.this.sureToExit = true;
                  SmoothExample.this.closeFrame();
               }
            break;
         }
      }
   }

   class TextRenderer
         implements JHelpListSmoothRenderer<String>
   {
      TextRenderer()
      {
      }

      @Override
      public JHelpComponentSmooth createComponent(final String element)
      {
         final JHelpLabelTextSmooth text = new JHelpLabelTextSmooth(element);
         text.setShadowLevel(ShadowLevel.NO_SHADOW);
         return text;
      }

      @Override
      public void transformComponent(final JHelpComponentSmooth component, final String element, final int indexInList, final boolean selected)
      {
         if(selected == true)
         {
            component.setBackground(JHelpConstantsSmooth.COLOR_RED_0400);
         }
         else if((indexInList % 2) == 0)
         {
            component.setBackground(JHelpConstantsSmooth.COLOR_CYAN_0300);
         }
         else
         {
            component.setBackground(JHelpConstantsSmooth.COLOR_WHITE);
         }
      }
   }

   static final int     SIZE = 6;

   static SmoothExample smoothExample;

   public static void main(final String[] args)
   {
      UtilGUI.initializeGUI();
      SmoothExample.smoothExample = new SmoothExample();
      SmoothExample.smoothExample.setVisible(true);
   }

   ActionPrintSomething  actionPrintSomething;

   JHelpButtonSmooth     buttonSmooth;

   JHelpOptionPaneSmooth optionPaneExit;
   JHelpOptionPaneSmooth optionPaneSmooth;
   JHelpScrollPaneSmooth scrollPaneSmooth;
   boolean               sureToExit = false;

   public SmoothExample()
   {
      super("Example");

      final ActionCloseFrame actionCloseFrame = new ActionCloseFrame();
      this.actionPrintSomething = new ActionPrintSomething();

      try
      {
         this.setBackgroundImage(JHelpImage.loadImage(SmoothExample.class.getResourceAsStream("duck.jpg")));
      }
      catch(final Exception exception)
      {
         Debug.printException(exception, "Failed to load background image");
      }

      this.setLayout(new JHelpBorderLayoutSmooth());
      final JHelpEditTextSmooth editTextSmooth = new JHelpEditTextSmooth(JHelpConstantsSmooth.FONT_BODY_1, 30, JHelpConstantsSmooth.COLOR_BLACK, JHelpConstantsSmooth.COLOR_TEAL_0500);
      editTextSmooth.registerEditTextListener(this.actionPrintSomething);
      this.addComponent(editTextSmooth, JHelpBorderConstraintsSmooth.UP);

      final JHelpButtonSmooth buttonSmooth = new JHelpButtonSmooth(actionCloseFrame, JHelpButtonAlignSmooth.ICON_ONLY_IF_EXISTS);
      buttonSmooth.setBackgroundAndShadowColor(0);
      buttonSmooth.setShadowLevel(ShadowLevel.NO_SHADOW);
      this.addComponent(buttonSmooth, JHelpBorderConstraintsSmooth.CORNER_RIGHT_UP);
      this.addComponent(new JHelpLabelTextSmooth("LEFT"), JHelpBorderConstraintsSmooth.LEFT);
      JHelpLabelTextSmooth textSmooth;

      final JHelpPanelSmooth panel = new JHelpPanelSmooth(new JHelpTableLayoutSmooth());
      textSmooth = new JHelpLabelTextSmooth("(0, 0) 1x1", JHelpTextAlign.CENTER);
      textSmooth.setBackgroundAndShadowColor(JHelpConstantsSmooth.COLOR_AMBER_0500);
      panel.addComponent(textSmooth, new JHelpTableConstraintsSmooth(0, 0));
      textSmooth = new JHelpLabelTextSmooth("- (5, 5) 2x1 -", JHelpTextAlign.CENTER);
      textSmooth.setBackgroundAndShadowColor(JHelpConstantsSmooth.COLOR_GREEN_0500);
      panel.addComponent(textSmooth, new JHelpTableConstraintsSmooth(5, 5, 2, 1));
      textSmooth = new JHelpLabelTextSmooth("A phrase for test the 'ellipse size'\nklll \n (0, 3) 3x2 \n kll\nA phrase for test the 'ellipse size'", JHelpTextAlign.CENTER);
      textSmooth.setShape(SmoothEllipse.ELLIPSE);
      textSmooth.setBackgroundAndShadowColor(JHelpConstantsSmooth.COLOR_RED_0500);
      panel.addComponent(textSmooth, new JHelpTableConstraintsSmooth(0, 3, 3, 2));
      final JHelpLabelImageSmooth imageSmooth = new JHelpLabelImageSmooth();

      try
      {
         final JHelpImage image = JHelpImage.loadImage(SmoothExample.class.getResourceAsStream("elemental.gif"));
         imageSmooth.setImage(image);
         textSmooth.setTextureBackground(image);
         this.actionPrintSomething.setLargeIcon(image);
      }
      catch(final Exception exception)
      {
         Debug.printException(exception, "Failed to load other image");
      }

      textSmooth = new JHelpLabelTextSmooth("(1, 1) 2x2", JHelpTextAlign.CENTER);
      textSmooth.setBackgroundAndShadowColor(JHelpConstantsSmooth.COLOR_BLUE_0500);
      textSmooth.setShape(new SmoothRoundRectangle());
      textSmooth.setPaintBackground(new JHelpGradient(JHelpConstantsSmooth.COLOR_RED_0500, JHelpConstantsSmooth.COLOR_GREEN_0500, JHelpConstantsSmooth.COLOR_BLUE_0500, JHelpConstantsSmooth.COLOR_BROWN_0500));
      panel.addComponent(textSmooth, new JHelpTableConstraintsSmooth(1, 1, 2, 2));

      imageSmooth.setBackgroundAndShadowColor(JHelpConstantsSmooth.COLOR_BLUE_GREY_0200);
      imageSmooth.setShadowLevel(ShadowLevel.FAR);
      imageSmooth.setShape(SmoothSaussage.SAUSSAGE);
      panel.addComponent(imageSmooth, new JHelpTableConstraintsSmooth(5, 7, 10, 2));

      this.buttonSmooth = new JHelpButtonSmooth(this.actionPrintSomething, JHelpButtonAlignSmooth.TEXT_OVER_ICON);
      this.buttonSmooth.setForeground(JHelpConstantsSmooth.COLOR_DEEP_ORANGE_0500);
      panel.addComponent(this.buttonSmooth, new JHelpTableConstraintsSmooth(2, 6, 1, 1));

      this.scrollPaneSmooth = new JHelpScrollPaneSmooth(panel, JHelpScrollModeSmooth.SCROLL_BOTH);
      this.addComponent(/**/this.scrollPaneSmooth /* /panel /* */, JHelpBorderConstraintsSmooth.CENTER);

      this.addComponent(new JHelpLabelTextSmooth("RIGHT"), JHelpBorderConstraintsSmooth.RIGHT);

      textSmooth = new JHelpLabelTextSmooth("Down expand", JHelpTextAlign.CENTER);
      final JHelpGradientHorizontal gradientHorizontal = new JHelpGradientHorizontal(JHelpConstantsSmooth.COLOR_GREEN_A700, JHelpConstantsSmooth.COLOR_GREEN_A700);
      gradientHorizontal.addColor(50, JHelpConstantsSmooth.COLOR_GREEN_0050);
      textSmooth.setPaintBackground(gradientHorizontal);
      this.addComponent(textSmooth, JHelpBorderConstraintsSmooth.DOWN_EXPAND);

      this.defineShortCut(new ShortCut(KeyEvent.VK_P, true, true, true), this.actionPrintSomething);

      textSmooth = new JHelpLabelTextSmooth("Launch the second dialog ?");
      textSmooth.setShape(SmoothSaussage.SAUSSAGE);
      textSmooth.setBackground(JHelpConstantsSmooth.COLOR_ALPHA_HINT | (JHelpConstantsSmooth.COLOR_WHITE & JHelpConstantsSmooth.MASK_COLOR));
      this.optionPaneSmooth = new JHelpOptionPaneSmooth(123, this, JHelpConstantsSmooth.ICON_QUESTION, textSmooth, JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0300, JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0300, OptionPaneType.YES_NO);
      this.optionPaneSmooth.registerJHelpOptionPaneSmoothListener(this.actionPrintSomething);

      textSmooth = new JHelpLabelTextSmooth("Do you really want exit this example ?");
      textSmooth.setShape(SmoothSaussage.SAUSSAGE);
      textSmooth.setBackground(JHelpConstantsSmooth.COLOR_ALPHA_HINT | (JHelpConstantsSmooth.COLOR_WHITE & JHelpConstantsSmooth.MASK_COLOR));
      this.optionPaneExit = new JHelpOptionPaneSmooth(321, this, JHelpConstantsSmooth.ICON_QUESTION, textSmooth, JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0300, JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0300, OptionPaneType.YES_NO);
      this.optionPaneExit.registerJHelpOptionPaneSmoothListener(this.actionPrintSomething);
   }

   @Override
   protected boolean canCloseNow()
   {
      if(this.sureToExit == false)
      {
         this.showDialog(this.optionPaneExit.getID());

         return false;
      }

      return true;
   }

   @Override
   protected DialogDecsription createDialog(final int dialogID)
   {
      if(dialogID == this.optionPaneSmooth.getID())
      {
         return this.optionPaneSmooth.createDialogDescriDecsription();
      }

      if(dialogID == this.optionPaneExit.getID())
      {
         return this.optionPaneExit.createDialogDescriDecsription();
      }

      switch(dialogID)
      {
         case 42:
            final JHelpPanelSmooth panelSmooth = new JHelpPanelSmooth(new JHelpBorderLayoutSmooth());
            final JHelpButtonSmooth buttonSmooth = new JHelpButtonSmooth(new ActionCloseDialog());
            buttonSmooth.setShape(SmoothSaussage.SAUSSAGE);
            buttonSmooth.setShadowLevel(ShadowLevel.NEAR);
            panelSmooth.addComponent(buttonSmooth, JHelpBorderConstraintsSmooth.UP_EXPAND);

            final JHelpListSmoothModelDefault<String> model = new JHelpListSmoothModelDefault<String>();

            for(int i = 0; i < 128; i++)
            {
               model.addElement("Text " + (i + 1));
            }

            final JHelpListSmooth<String> list = new JHelpListSmooth<String>(model, new TextRenderer(), JHelpScrollModeSmooth.SCROLL_VERTICAL);
            list.registerSelectListener(this.actionPrintSomething);
            panelSmooth.addComponent(list, JHelpBorderConstraintsSmooth.CENTER);

            return new DialogDecsription(512, 256, panelSmooth, JHelpConstantsSmooth.COLOR_BLUE_GREY_0200, JHelpConstantsSmooth.COLOR_GREY_0300, ShadowLevel.FAR);
      }

      return null;
   }
}