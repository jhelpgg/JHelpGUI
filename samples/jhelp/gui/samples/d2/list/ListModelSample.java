package jhelp.gui.samples.d2.list;

import jhelp.gui.twoD.JHelpListModel;
import jhelp.util.gui.JHelpImage;

/**
 * Model for list sample
 * 
 * @author JHelp
 */
public class ListModelSample
      extends JHelpListModel<String>
{
   /** Model content */
   private static final String[] LINES;
   /** Number of elelents in model */
   private static final int      LINES_LENGTH;
   static
   {
      LINES = new String[]
      {
         "apple", "bannana", "car", "dolmen", "elephant", "fruit", "garlic", "helium", "illegal", "java", "kwinaman", "lemon", "no", "operation", "program", "quit", "real", "string", "type", "upper", "velocity", "waggon",
         "xylophone", "yell", "zebra"
      };
      LINES_LENGTH = ListModelSample.LINES.length;
   }

   /**
    * Create a new instance of ListModelSample
    */
   public ListModelSample()
   {
   }

   /**
    * Obtain element at specific index <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param index
    *           Index
    * @return Element at the index
    * @see jhelp.gui.twoD.JHelpListModel#getElement(int)
    */
   @Override
   public String getElement(final int index)
   {
      return ListModelSample.LINES[index];
   }

   @Override
   public String getToolTip(final String information)
   {
      return null;
   }

   /**
    * Number of element <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Number of element
    * @see jhelp.gui.twoD.JHelpListModel#numberOfElement()
    */
   @Override
   public int numberOfElement()
   {
      return ListModelSample.LINES_LENGTH;
   }

   /**
    * Image representation of an element <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param information
    *           Element
    * @return {@code null}
    * @see jhelp.gui.twoD.JHelpListModel#obtainImageRepresentation(java.lang.Object)
    */
   @Override
   public JHelpImage obtainImageRepresentation(final String information)
   {
      return null;
   }

   /**
    * Text representation of an element <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param information
    *           Information
    * @return Text representation
    * @see jhelp.gui.twoD.JHelpListModel#obtainTextRepresentation(java.lang.Object)
    */
   @Override
   public String obtainTextRepresentation(final String information)
   {
      return information;
   }

   /**
    * Indicates if use image for an element <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param information
    *           Element
    * @return {@code false}
    * @see jhelp.gui.twoD.JHelpListModel#useImageRepresentation(java.lang.Object)
    */
   @Override
   public boolean useImageRepresentation(final String information)
   {
      return false;
   }
}