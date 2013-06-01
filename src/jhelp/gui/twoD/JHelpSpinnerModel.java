package jhelp.gui.twoD;

/**
 * Spinner model
 * 
 * @author JHelp
 * @param <VALUE>
 *           Value type
 */
public interface JHelpSpinnerModel<VALUE>
{
   /**
    * Obtain the bigest text that spinner can provide, to be able reserve enough room
    * 
    * @return Biggest text spinner can provide
    */
   public String biggestText();

   /**
    * Current value text representation
    * 
    * @return Current value text representation
    */
   public String getActualText();

   /**
    * Current value
    * 
    * @return Current value
    */
   public VALUE getActualValue();

   /**
    * Indicates if theire are a next value
    * 
    * @return {@code true} if theire are a next value
    */
   public boolean hasNextValue();

   /**
    * Indicates if theire are a prevoius value
    * 
    * @return {@code true} if theire are a previous value
    */
   public boolean hasPreviousValue();

   /**
    * Go next value.<br>
    * Do nothing if have no next value
    */
   public void nextValue();

   /**
    * Go prevoius value.<br>
    * Do nothing if have no previous value
    */
   public void previousValue();

   /**
    * Change current value.<br>
    * Implementation choice to do things if value is not valid
    * 
    * @param value
    *           New value
    */
   public void setActualValue(VALUE value);
}