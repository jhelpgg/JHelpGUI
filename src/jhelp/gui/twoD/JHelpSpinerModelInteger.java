package jhelp.gui.twoD;

/**
 * Spinner model based on integer
 * 
 * @author JHelp
 */
public class JHelpSpinerModelInteger
      implements JHelpSpinnerModel<Integer>
{
   /** Actual value */
   private int       actual;
   /** Biggest text */
   private String    bigest;
   /** Maximum vlaue */
   private final int maximum;
   /** Minimum value */
   private final int minimum;
   /** Step to use between changes */
   private final int step;

   /**
    * Create a new instance of JHelpSpinerModelInteger with 1 as step and minimum as start value
    * 
    * @param minimum
    *           Minimum value
    * @param maximum
    *           Maximum value
    */
   public JHelpSpinerModelInteger(final int minimum, final int maximum)
   {
      this(minimum, maximum, minimum);
   }

   /**
    * Create a new instance of JHelpSpinerModelInteger with 1 step
    * 
    * @param minimum
    *           Minimum
    * @param maximum
    *           Maximum
    * @param actual
    *           Start value
    */
   public JHelpSpinerModelInteger(final int minimum, final int maximum, final int actual)
   {
      this(minimum, maximum, actual, 1);
   }

   /**
    * Create a new instance of JHelpSpinerModelInteger
    * 
    * @param minimum
    *           Minimum
    * @param maximum
    *           Maximum
    * @param actual
    *           Start value
    * @param step
    *           Step to use
    */
   public JHelpSpinerModelInteger(final int minimum, final int maximum, final int actual, final int step)
   {
      this.minimum = Math.min(minimum, maximum);
      this.maximum = Math.max(minimum, maximum);
      this.actual = Math.max(this.minimum, Math.min(this.maximum, actual));
      this.step = Math.max(1, Math.abs(step));
   }

   /**
    * Model biggest text <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Model biggest text
    * @see jhelp.gui.twoD.JHelpSpinnerModel#biggestText()
    */
   @Override
   public String biggestText()
   {
      if(this.bigest == null)
      {
         final String min = String.valueOf(this.minimum);
         final String max = String.valueOf(this.maximum);

         if(min.length() <= max.length())
         {
            this.bigest = max;
         }
         else
         {
            this.bigest = min;
         }
      }

      return this.bigest;
   }

   /**
    * Actual value text representation <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Actual value text representation
    * @see jhelp.gui.twoD.JHelpSpinnerModel#getActualText()
    */
   @Override
   public String getActualText()
   {
      return String.valueOf(this.actual);
   }

   /**
    * Actual value <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Actual value
    * @see jhelp.gui.twoD.JHelpSpinnerModel#getActualValue()
    */
   @Override
   public Integer getActualValue()
   {
      return this.actual;
   }

   /**
    * Indicates if theire are a next value <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return {@code true} if theire are a next value
    * @see jhelp.gui.twoD.JHelpSpinnerModel#hasNextValue()
    */
   @Override
   public boolean hasNextValue()
   {
      return this.actual < this.maximum;
   }

   /**
    * Indicates if theire are a previous value <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return {@code true} if theire are a previous value
    * @see jhelp.gui.twoD.JHelpSpinnerModel#hasPreviousValue()
    */
   @Override
   public boolean hasPreviousValue()
   {
      return this.actual > this.minimum;
   }

   /**
    * Go to next value <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.twoD.JHelpSpinnerModel#nextValue()
    */
   @Override
   public void nextValue()
   {
      this.actual = Math.min(this.maximum, this.actual + this.step);
   }

   /**
    * Go to previous value <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @see jhelp.gui.twoD.JHelpSpinnerModel#previousValue()
    */
   @Override
   public void previousValue()
   {
      this.actual = Math.max(this.minimum, this.actual - this.step);
   }

   /**
    * Change current value.<br>
    * Value is automatic put between minimum and maximum <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param value
    *           New value
    * @see jhelp.gui.twoD.JHelpSpinnerModel#setActualValue(java.lang.Object)
    */
   @Override
   public void setActualValue(final Integer value)
   {
      this.actual = Math.max(this.minimum, Math.min(this.maximum, value));
   }
}