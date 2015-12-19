/**
 * <h1>License :</h1> <br>
 * The following code is deliver as is. I take care that code compile and work, but I am not responsible about any damage it may
 * cause.<br>
 * You can use, modify, the code as your need for any usage. But you can't do any action that avoid me or other person use,
 * modify this code. The code is free for usage and modification, you can't change that fact.<br>
 * <br>
 * 
 * @author JHelp
 */
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
    * Obtain the biggest text that spinner can provide, to be able reserve enough room
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