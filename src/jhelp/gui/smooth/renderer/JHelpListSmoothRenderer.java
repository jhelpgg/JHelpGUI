package jhelp.gui.smooth.renderer;

import jhelp.gui.smooth.JHelpComponentSmooth;
import jhelp.gui.smooth.JHelpListSmooth;

/**
 * Renderer for {@link JHelpListSmooth}.<br>
 * It defines the component to draw for each list item and how draw them in different situation.<br>
 * When a component need to be created {@link #createComponent(Object)} is called.<br>
 * When a component need to be refresh {@link #transformComponent(JHelpComponentSmooth, Object, int, boolean)} is called. The
 * given component here is the same returned by {@link #createComponent(Object)} for the same element, so you can cast it as
 * appropriate.
 * 
 * @author JHelp
 * @param <ELEMENT>
 *           Element type
 */
public interface JHelpListSmoothRenderer<ELEMENT>
{
   /**
    * Create a component for a given element
    * 
    * @param element
    *           Element to have its component
    * @return Created component
    */
   public JHelpComponentSmooth createComponent(ELEMENT element);

   /**
    * Transform a component previously created by {@link #createComponent(Object)} to give it the right appearance.<br>
    * Transform can be change : background, shadow, shadow level ,...
    * 
    * @param component
    *           Component to transform
    * @param element
    *           Element on component
    * @param indexInList
    *           Index inside the list
    * @param selected
    *           Indicates if component is selected
    */
   public void transformComponent(JHelpComponentSmooth component, ELEMENT element, int indexInList, boolean selected);
}