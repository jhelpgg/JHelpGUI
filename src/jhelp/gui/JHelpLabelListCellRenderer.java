package jhelp.gui;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * List cell renderer based on {@link JHelpLabel} .<br>
 * It automatically set focus and selection status
 * 
 * @author JHelp
 * @param <ELEMENT>
 *           Element to draw
 */
public abstract class JHelpLabelListCellRenderer<ELEMENT>
      extends JHelpLabel
      implements ListCellRenderer<ELEMENT>
{
   /**
    * Create a new instance of JHelpLabelListCellRenderer
    */
   public JHelpLabelListCellRenderer()
   {
   }

   /**
    * Update the label with an element
    * 
    * @param element
    *           Element to draw
    * @param index
    *           Element index
    */
   protected abstract void update(ELEMENT element, int index);

   /**
    * Obtain component for given cell <br>
    * <br>
    * <b>Parent documentation :</b><br>
    * {@inheritDoc}
    * 
    * @param list
    *           List where component draw
    * @param value
    *           Value on cell
    * @param index
    *           Cell index
    * @param isSelected
    *           Indicates if cell selected
    * @param cellHasFocus
    *           Indicates if cell have focus
    * @return Component for draw
    * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
    */
   @Override
   public final Component getListCellRendererComponent(final JList<? extends ELEMENT> list, final ELEMENT value, final int index, final boolean isSelected,
         final boolean cellHasFocus)
   {
      this.update(value, index);
      this.setSelected(isSelected);
      this.setFocused(cellHasFocus);
      return this;
   }
}