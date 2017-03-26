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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import jhelp.gui.JHelpSuggestion;
import jhelp.util.text.UtilText;

/**
 * Filter use for {@link JHelpSuggestion}
 *
 * @author JHelp <br>
 * @param <INFORMATION>
 *           Suggestion information carry type
 */
public class FilterSuggestionElement<INFORMATION>
      implements Filter<SuggestionElement<INFORMATION>>
{
   /** Indicates if accent sensitive */
   private boolean                                                    accentSensitive;
   /** Indicates if case sensitive */
   private boolean                                                    caseSensitive;
   /** Listeners of filter changes */
   private final List<FilterListener<SuggestionElement<INFORMATION>>> listeners;
   /** Regular expression */
   private Pattern                                                    pattern;

   /**
    * Create a new instance of FilterSuggestionElement
    */
   public FilterSuggestionElement()
   {
      this.listeners = new ArrayList<FilterListener<SuggestionElement<INFORMATION>>>();
      this.caseSensitive = true;
      this.accentSensitive = true;
   }

   /**
    * Signal to listeners that filter changed
    */
   protected void fireFilterChange()
   {
      synchronized(this.listeners)
      {
         for(final FilterListener<SuggestionElement<INFORMATION>> listener : this.listeners)
         {
            listener.filterChange(this);
         }
      }
   }

   /**
    * Accept all String
    */
   public void acceptAll()
   {
      this.setRegex(null);
   }

   /**
    * Indicates if given suggestion filtered <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param suggestion
    *           Given suggestion
    * @return <code>true</code> if given suggestion filtered
    * @see jhelp.gui.model.Filter#isFiltered(java.lang.Object)
    */
   @Override
   public boolean isFiltered(final SuggestionElement<INFORMATION> suggestion)
   {
      if(this.pattern == null)
      {
         return true;
      }

      String string = suggestion.getKeyWord();

      if(!this.caseSensitive)
      {
         string = string.toLowerCase();
      }

      if(!this.accentSensitive)
      {
         string = UtilText.removeAccent(string);
      }

      return this.pattern.matcher(string).matches();
   }

   /**
    * Register listener to filter changes <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param listener
    *           Listener to register
    * @see jhelp.gui.model.Filter#registerFilterListener(jhelp.gui.model.FilterListener)
    */
   @Override
   public void registerFilterListener(final FilterListener<SuggestionElement<INFORMATION>> listener)
   {
      if(listener == null)
      {
         return;
      }

      synchronized(this.listeners)
      {
         if(!this.listeners.contains(listener))
         {
            this.listeners.add(listener);
         }
      }
   }

   /**
    * Define the regex.<br>
    * If given <code>regex</code> is null, the filter will accept all texts.<br>
    *
    * @param regex
    *           Regex to verify. {@code null} for match all
    */
   public void setRegex(final String regex)
   {
      this.setRegex(regex, true, true);
   }

   /**
    * Define the regex.<br>
    * If given <code>regex</code> is null, the filter will accept all texts.<br>
    * If <code>caseSensitive</code> is false, consider the text is put in lower case before test match with regex, so use only
    * lower case matching (A-Z will not match).<br>
    *
    * @param regex
    *           Regex to verify. {@code null} for match all
    * @param caseSensitive
    *           Indicates if case sensitive (If not, text will put lower case)
    */
   public void setRegex(final String regex, final boolean caseSensitive)
   {
      this.setRegex(regex, caseSensitive, caseSensitive);
   }

   /**
    * Define the regex.<br>
    * If given <code>regex</code> is null, the filter will accept all texts.<br>
    * If <code>caseSensitive</code> is false, consider the text is put in lower case before test match with regex, so use only
    * lower case matching (A-Z will not match).<br>
    * If <code>accentSensitve</code> is false, accent are removed before test matching, so don't include them in your regex
    * because they will never match
    *
    * @param regex
    *           Regex to verify. {@code null} for match all
    * @param caseSensitive
    *           Indicates if case sensitive (If not, text will put lower case)
    * @param accentSensitve
    *           Indicates if accent sensitive (If not, accents will be remove from text)
    */
   public void setRegex(final String regex, final boolean caseSensitive, final boolean accentSensitve)
   {
      if(regex == null)
      {
         this.pattern = null;
         this.caseSensitive = true;
         this.accentSensitive = true;
      }
      else
      {
         this.pattern = Pattern.compile(regex);
         this.caseSensitive = caseSensitive;
         this.accentSensitive = accentSensitve;
      }

      this.fireFilterChange();
   }

   /**
    * Unregister listener from filter changes <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param listener
    *           Listener to unregister
    * @see jhelp.gui.model.Filter#unregisterFilterListener(jhelp.gui.model.FilterListener)
    */
   @Override
   public void unregisterFilterListener(final FilterListener<SuggestionElement<INFORMATION>> listener)
   {
      synchronized(this.listeners)
      {
         this.listeners.remove(listener);
      }
   }
}