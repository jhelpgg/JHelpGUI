package jhelp.gui.smooth.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Default list model with a embed list
 * 
 * @author JHelp
 * @param <ELEMENT>
 *           Element type
 */
public class JHelpListSmoothModelDefault<ELEMENT>
      extends JHelpListSmoothModelAbstract<ELEMENT>
{
   /** Elements inside the model */
   private final List<ELEMENT> elements;

   /**
    * Create a new instance of JHelpListSmoothModelDefault
    */
   public JHelpListSmoothModelDefault()
   {
      this.elements = new ArrayList<ELEMENT>();
   }

   /**
    * Add one element
    * 
    * @param element
    *           Element to add
    */
   public final void addElement(final ELEMENT element)
   {
      final int index = this.elements.size();
      this.elements.add(element);
      this.fireElementAdded(index);
   }

   /**
    * Clear the model.<br>
    * The model will be empty after that
    */
   public final void clearModel()
   {
      this.elements.clear();
      this.fireAllChange();
   }

   /**
    * Obtain one element <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param index
    *           Element index
    * @return The element
    * @see jhelp.gui.smooth.model.JHelpListSmoothModel#getElement(int)
    */
   @Override
   public final ELEMENT getElement(final int index)
   {
      return this.elements.get(index);
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
   public final int getNumberOfElement()
   {
      return this.elements.size();
   }

   /**
    * Obtain an element index.<br>
    * Return -1 if not found
    * 
    * @param element
    *           Element search
    * @return Element index OR -1 if not found
    */
   public final int indexOfElement(final ELEMENT element)
   {
      return this.elements.indexOf(element);
   }

   /**
    * Insert an element
    * 
    * @param index
    *           Insert index
    * @param element
    *           Element to insert
    */
   public final void insertElement(int index, final ELEMENT element)
   {
      index = Math.max(0, index);

      if(index >= this.elements.size())
      {
         this.addElement(element);
         return;
      }

      this.elements.add(index, element);
      this.fireElementAdded(index);
   }

   /**
    * Remove one element
    * 
    * @param element
    *           Element to remove
    */
   public final void removeElement(final ELEMENT element)
   {
      final int index = this.elements.indexOf(element);

      if(index < 0)
      {
         return;
      }

      this.elements.remove(index);
      this.fireElementRemoved(index);
   }

   /**
    * Remove one element
    * 
    * @param index
    *           Element index to remove
    */
   public final void removeElement(final int index)
   {
      if((index < 0) || (index >= this.elements.size()))
      {
         return;
      }

      this.elements.remove(index);
      this.fireElementRemoved(index);
   }
}