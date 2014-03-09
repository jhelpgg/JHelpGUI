package jhelp.gui.samples.d2.list;

import jhelp.gui.twoD.JHelpActionListener;
import jhelp.gui.twoD.JHelpBorderLayout;
import jhelp.gui.twoD.JHelpBorderLayout.JHelpBorderLayoutConstraints;
import jhelp.gui.twoD.JHelpButtonBehavior;
import jhelp.gui.twoD.JHelpComponent2D;
import jhelp.gui.twoD.JHelpFrame2D;
import jhelp.gui.twoD.JHelpLabelImage2D;
import jhelp.gui.twoD.JHelpList2D;
import jhelp.util.gui.JHelpTextAlign;

/**
 * Frame carray smaple of list
 * 
 * @author JHelp
 */
public class FrameList
      extends JHelpFrame2D
      implements JHelpActionListener
{
   /** The list */
   private final JHelpList2D<String> list2d;

   /**
    * Create a new instance of FrameList
    */
   public FrameList()
   {
      super(new JHelpBorderLayout());

      this.list2d = new JHelpList2D<String>(false, new ListModelSample());
      this.addComponent2D(this.list2d, JHelpBorderLayoutConstraints.CENTER);

      final JHelpLabelImage2D labelImage2D = JHelpLabelImage2D.createTextLabel("GO 23", JHelpList2D.FONT, 0xFF000000, 0xFFFFFFFF, JHelpTextAlign.CENTER);
      JHelpButtonBehavior.giveButtonBehavior(42, labelImage2D, this);
      this.addComponent2D(labelImage2D, JHelpBorderLayoutConstraints.BOTTOM_EXPANDED);
   }

   /**
    * Called whe "Go 23" button is pressed <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param component2d
    *           Component pressed
    * @param identifier
    *           Action ID
    * @see jhelp.gui.twoD.JHelpActionListener#actionAppend(jhelp.gui.twoD.JHelpComponent2D, int)
    */
   @Override
   public void actionAppend(final JHelpComponent2D component2d, final int identifier)
   {
      this.list2d.setSelectedIndex(23);
   }
}