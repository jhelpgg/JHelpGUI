package jhelp.gui.samples.d2.tree;

import jhelp.util.list.Tree.TestFoundListener;

/**
 * Search by tree node ID
 * 
 * @author JHelp
 */
public class SearchByID
      implements TestFoundListener<TreeNodeSample>
{
   /** Searched ID */
   private final int id;

   /**
    * Create a new instance of SearchByID
    * 
    * @param id
    *           Searched ID
    */
   public SearchByID(final int id)
   {
      this.id = id;
   }

   /**
    * Create a new instance of SearchByID
    * 
    * @param nodeSample
    *           Node sample searched
    */
   public SearchByID(final TreeNodeSample nodeSample)
   {
      this.id = nodeSample.getID();
   }

   /**
    * Test if a tree node match <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @param information
    *           Tested tree node
    * @return {@code true} if the node match the search
    * @see jhelp.util.list.Tree.TestFoundListener#isElementSearched(java.lang.Object)
    */
   @Override
   public boolean isElementSearched(final TreeNodeSample information)
   {
      return this.id == information.getID();
   }
}