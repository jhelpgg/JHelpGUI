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
package jhelp.gui.smooth;

/**
 * Represents a key combination for short cut
 * 
 * @author JHelp
 */
public class ShortCut
      implements Comparable<ShortCut>
{
   /** Indicate if ALT is down */
   private final boolean alt;
   /** Indicate if CONTROL is down */
   private final boolean control;
   /** Key code */
   private final int     keyCode;
   /** Indicate if SHIFT is down */
   private final boolean shift;

   /**
    * Create a new instance of ShortCut
    * 
    * @param keyCode
    *           Key code
    * @param shift
    *           Indicate if SHIFT is down
    * @param control
    *           Indicate if CONTROL is down
    * @param alt
    *           Indicate if ALT is down
    */
   public ShortCut(final int keyCode, final boolean shift, final boolean control, final boolean alt)
   {
      this.keyCode = keyCode;
      this.shift = shift;
      this.control = control;
      this.alt = alt;
   }

   /**
    * Compare with an other short cut.<br>
    * It returns
    * <table border=0>
    * <tr>
    * <th>&lt;0</th>
    * <td>If this short cut before given one</td>
    * </tr>
    * <tr>
    * <th>0</th>
    * <td>If this short cut is equals given one</td>
    * </tr>
    * <tr>
    * <th>&gt;0</th>
    * <td>If this short cut after given one</td>
    * </tr>
    * </table>
    * <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param shortCut
    *           Short cut to compare with
    * @return Comparison result
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   @Override
   public int compareTo(final ShortCut shortCut)
   {
      final int comp = this.keyCode - shortCut.keyCode;

      if(comp != 0)
      {
         return comp;
      }

      if(this.shift == true)
      {
         if(shortCut.shift == false)
         {
            return -1;
         }
      }
      else if(shortCut.shift == true)
      {
         return 1;
      }

      if(this.control == true)
      {
         if(shortCut.control == false)
         {
            return -1;
         }
      }
      else if(shortCut.control == true)
      {
         return 1;
      }

      if(this.alt == true)
      {
         if(shortCut.alt == false)
         {
            return -1;
         }
      }
      else if(shortCut.alt == true)
      {
         return 1;
      }

      return 0;
   }

   /**
    * Indicates if an object is equals to this short cut <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param object
    *           Object to compare with
    * @return {@code true} if object is equals to this short cut
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object object)
   {
      if(object == null)
      {
         return false;
      }

      if(object == this)
      {
         return true;
      }

      if((object instanceof ShortCut) == false)
      {
         return false;
      }

      final ShortCut shortCut = (ShortCut) object;

      return (this.keyCode == shortCut.keyCode) && (this.shift == shortCut.shift) && (this.control == shortCut.control) && (this.alt == shortCut.alt);
   }

   /**
    * Key code
    * 
    * @return Key code
    */
   public int getKeyCode()
   {
      return this.keyCode;
   }

   /**
    * Hash code <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return Hash code
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode()
   {
      int hash = this.keyCode << 3;

      if(this.shift == true)
      {
         hash += 4;
      }

      if(this.control == true)
      {
         hash += 2;
      }

      if(this.alt == true)
      {
         hash += 1;
      }

      return hash;
   }

   /**
    * Indicate if ALT is down
    * 
    * @return {@code true} if ALT is down
    */
   public boolean isAlt()
   {
      return this.alt;
   }

   /**
    * Indicate if CONTROL is down
    * 
    * @return {@code true} if CONTROL is down
    */
   public boolean isControl()
   {
      return this.control;
   }

   /**
    * Indicate if SHIFT is down
    * 
    * @return {@code true} if SHIFT is down
    */
   public boolean isShift()
   {
      return this.shift;
   }
}