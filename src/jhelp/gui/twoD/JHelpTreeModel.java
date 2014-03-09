package jhelp.gui.twoD;

import java.util.ArrayList;

import jhelp.util.gui.JHelpImage;
import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedSimpleTask;

/**
 * Model for tree
 * 
 * @author JHelp
 * @param <INFORMATION>
 *           Information type
 */
public abstract class JHelpTreeModel<INFORMATION>
{
   /** Task for signal to listener that tree model changed */
   private final ThreadedSimpleTask<JHelpTreeModelListener<INFORMATION>> taskFireModelChanged = new ThreadedSimpleTask<JHelpTreeModelListener<INFORMATION>>()
                                                                                              {
                                                                                                 /**
                                                                                                  * Called when task tur come.<br>
                                                                                                  * Here it signal to listener
                                                                                                  * that model changed <br>
                                                                                                  * <br>
                                                                                                  * <b>Parent documentation:</b><br>
                                                                                                  * {@inheritDoc}
                                                                                                  * 
                                                                                                  * @param parameter
                                                                                                  *           Listener to alert
                                                                                                  * @see jhelp.util.thread.ThreadedSimpleTask#doSimpleAction(java.lang.Object)
                                                                                                  */
                                                                                                 @Override
                                                                                                 protected void doSimpleAction(final JHelpTreeModelListener<INFORMATION> parameter)
                                                                                                 {
                                                                                                    parameter.treeModelChanged(JHelpTreeModel.this);
                                                                                                 }
                                                                                              };
   /** Listeners of tree model changes */
   private final ArrayList<JHelpTreeModelListener<INFORMATION>>          treeModelListeners;

   /**
    * Create a new instance of JHelpTreeModel
    */
   public JHelpTreeModel()
   {
      this.treeModelListeners = new ArrayList<JHelpTreeModelListener<INFORMATION>>();
   }

   /**
    * Call this method to signal to listeners that tree model has changed
    */
   protected final synchronized void fireModelChanged()
   {
      for(final JHelpTreeModelListener<INFORMATION> treeModelListener : this.treeModelListeners)
      {
         ThreadManager.THREAD_MANAGER.doThread(this.taskFireModelChanged, treeModelListener);
      }
   }

   /**
    * Force refresh the model
    */
   public void forceRefresh()
   {
      this.fireModelChanged();
   }

   /**
    * Obtain a child of a parent
    * 
    * @param parent
    *           Parent
    * @param index
    *           Child index
    * @return Child
    */
   public abstract INFORMATION getChild(INFORMATION parent, int index);

   /**
    * Get information parent
    * 
    * @param child
    *           Child to get its parent
    * @return Parent or {@code null} if child is root, so haven't parent
    */
   public abstract INFORMATION getParent(INFORMATION child);

   /**
    * Tree root
    * 
    * @return Tree root
    */
   public abstract INFORMATION getRoot();

   /**
    * Indicates if a node is expanded
    * 
    * @param node
    *           Tested node
    * @return {@code true} if node is expanded
    */
   public abstract boolean isExpanded(INFORMATION node);

   /**
    * Number of children in a parent node
    * 
    * @param parent
    *           Parent node
    * @return Number of children
    */
   public abstract int numberOfChildren(INFORMATION parent);

   /**
    * Image representation of a node.<br>
    * Can be {@code null} for no image
    * 
    * @param information
    *           Information
    * @return Image represention or {@code null} if theire are no image representation
    */
   public abstract JHelpImage obtainImageRepresentation(INFORMATION information);

   /**
    * Text representation of a node.<br>
    * Can be {@code null} if no text representation
    * 
    * @param information
    *           Information
    * @return Text representation or {@code null} if there are no text representation
    */
   public abstract String obtainTextRepresentation(INFORMATION information);

   /**
    * Register a tree model listener
    * 
    * @param treeModelListener
    *           Listener to register
    */
   public final synchronized void registerTreeModelListener(final JHelpTreeModelListener<INFORMATION> treeModelListener)
   {
      if(treeModelListener == null)
      {
         throw new NullPointerException("treeModelListener musn't be null");
      }

      this.treeModelListeners.add(treeModelListener);
   }

   /**
    * Change node expand state
    * 
    * @param information
    *           Information
    * @param expand
    *           New expand state
    */
   public abstract void setExpand(INFORMATION information, final boolean expand);

   /**
    * Unregister a tree model listener
    * 
    * @param treeModelListener
    *           Listener to unregister
    */
   public final synchronized void unregisterTreeModelListener(final JHelpTreeModelListener<INFORMATION> treeModelListener)
   {
      this.treeModelListeners.remove(treeModelListener);
   }

   /**
    * Imdicates if have to use image representation for a node .<br>
    * If not image is used, that text is used
    * 
    * @param information
    *           Informatio tested
    * @return {@code true} for use image representation. {@code false} for text representation
    */
   public abstract boolean useImageRepresentation(INFORMATION information);
}