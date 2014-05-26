package jhelp.gui.twoD;

import java.awt.Dimension;

import jhelp.util.gui.JHelpImage;

public class JHelpCheckbox2D
      extends JHelpComponent2D
      implements JHelpActionListener
{
   public static final int    COLOR_BOX   = 0xFF000000;
   public static final int    COLOR_CHECK = 0xFFFF0000;
   public static final int    SIZE        = 16;
   private CheckBox2DListener checkBox2DListener;
   private boolean            checked;
   private int                colorBox;
   private int                colorCheck;
   private int                size;

   public JHelpCheckbox2D()
   {
      this(JHelpCheckbox2D.COLOR_BOX, JHelpCheckbox2D.COLOR_CHECK, false, JHelpCheckbox2D.SIZE);
   }

   public JHelpCheckbox2D(final boolean checked)
   {
      this(JHelpCheckbox2D.COLOR_BOX, JHelpCheckbox2D.COLOR_CHECK, checked, JHelpCheckbox2D.SIZE);
   }

   public JHelpCheckbox2D(final boolean checked, final int size)
   {
      this(JHelpCheckbox2D.COLOR_BOX, JHelpCheckbox2D.COLOR_CHECK, checked, size);
   }

   public JHelpCheckbox2D(final int colorBox, final int colorCheck)
   {
      this(colorBox, colorCheck, false, JHelpCheckbox2D.SIZE);
   }

   public JHelpCheckbox2D(final int colorBox, final int colorCheck, final boolean checked)
   {
      this(colorBox, colorCheck, checked, JHelpCheckbox2D.SIZE);
   }

   public JHelpCheckbox2D(final int colorBox, final int colorCheck, final boolean checked, final int size)
   {
      this.setColorBox(colorBox);
      this.setColorCheck(colorCheck);
      this.setChecked(checked);
      this.setSize(size);
      JHelpButtonBehavior.giveButtonBehavior(42, this, this);
   }

   @Override
   protected Dimension computePreferredSize(final int parentWidth, final int parentHeight)
   {
      return new Dimension(this.size, this.size);
   }

   @Override
   protected void paint(final int x, final int y, final JHelpImage parent)
   {
      parent.drawRectangle(x, y, this.size, this.size, this.colorBox);

      if(this.checked == true)
      {
         parent.drawLine(x, y, x + this.size, y + this.size, this.colorCheck);
         parent.drawLine(x, y + this.size, x + this.size, y, this.colorCheck);
      }
   }

   @Override
   public void actionAppend(final JHelpComponent2D component2d, final int identifier)
   {
      this.checked = !this.checked;

      if(this.checkBox2DListener != null)
      {
         this.checkBox2DListener.checkBoxCheckChange(this, this.checked);
      }

      this.invalidate();
   }

   public CheckBox2DListener getCheckBox2DListener()
   {
      return this.checkBox2DListener;
   }

   public int getColorBox()
   {
      return this.colorBox;
   }

   public int getColorCheck()
   {
      return this.colorCheck;
   }

   public int getSize()
   {
      return this.size;
   }

   public boolean isChecked()
   {
      return this.checked;
   }

   public void setCheckBox2DListener(final CheckBox2DListener checkBox2DListener)
   {
      this.checkBox2DListener = checkBox2DListener;
   }

   public void setChecked(final boolean checked)
   {
      this.checked = checked;
      this.invalidate();
   }

   public void setColorBox(final int colorBox)
   {
      this.colorBox = colorBox;
      this.invalidate();
   }

   public void setColorCheck(final int colorCheck)
   {
      this.colorCheck = colorCheck;
      this.invalidate();
   }

   public void setSize(final int size)
   {
      this.size = Math.max(size, JHelpCheckbox2D.SIZE);
      this.invalidate();
   }
}