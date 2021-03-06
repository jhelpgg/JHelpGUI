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
package jhelp.gui.model;

import jhelp.gui.JHelpSuggestion;

/**
 * Suggestion element use with {@link JHelpSuggestion}
 *
 * @author JHelp <br>
 * @param <INFORMATION>
 *           Information carry type
 */
public class SuggestionElement<INFORMATION>
{
   /** Details on suggestion */
   private String            helpDetails;
   /** Carry information */
   private final INFORMATION information;
   /** Keyword,for suggestion */
   private final String      keyWord;

   /**
    * Create a new instance of SuggestionElement
    *
    * @param keyWord
    *           Key word
    * @param information
    *           Information carry (Can be <code>null</code> if none)
    */
   public SuggestionElement(final String keyWord, final INFORMATION information)
   {
      this(keyWord, information, null);
   }

   /**
    * Create a new instance of SuggestionElement
    *
    * @param keyWord
    *           Key word
    * @param information
    *           Information carry (Can be <code>null</code> if none)
    * @param helpDetails
    *           Help details (Can be <code>null</code> if none)
    */
   public SuggestionElement(final String keyWord, final INFORMATION information, final String helpDetails)
   {
      if(keyWord == null)
      {
         throw new NullPointerException("keyWord musn't be null");
      }

      this.keyWord = keyWord.trim();

      if(this.keyWord.length() == 0)
      {
         throw new IllegalArgumentException("Key word musn't be empty of full of white space");
      }

      this.information = information;
      this.helpDetails = helpDetails;
   }

   /**
    * Suggestion details
    *
    * @return Suggestion details, <code>null</code> if none
    */
   public String getHelpDetails()
   {
      return this.helpDetails;
   }

   /**
    * Carry information
    *
    * @return Carry information, <code>null</code> if none
    */
   public INFORMATION getInformation()
   {
      return this.information;
   }

   /**
    * Suggestion key word
    *
    * @return Suggestion key word
    */
   public String getKeyWord()
   {
      return this.keyWord;
   }
}