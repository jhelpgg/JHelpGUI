package jhelp.gui.samples.d2.tree;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tree node
 * 
 * @author JHelp
 */
public class TreeNodeSample
{
   /** Next trre node ID */
   private static final AtomicInteger NEXT_ID = new AtomicInteger(0);
   /** Indicates if node is exapnd */
   private boolean                    expand;
   /** Node ID */
   private int                        id;
   /** Carry message */
   private final String               message;

   /**
    * Create a new instance of TreeNodeSample
    * 
    * @param message
    *           Message to carry
    */
   public TreeNodeSample(final String message)
   {
      synchronized(TreeNodeSample.NEXT_ID)
      {
         this.id = TreeNodeSample.NEXT_ID.getAndIncrement();
      }

      this.message = message;
      this.expand = false;
   }

   /**
    * Cahnge expand state
    * 
    * @param expand
    *           New expand state
    */
   void setExpand(final boolean expand)
   {
      this.expand = expand;
   }

   /**
    * Node ID
    * 
    * @return Node ID
    */
   public int getID()
   {
      return this.id;
   }

   /**
    * Carry message
    * 
    * @return Carry message
    */
   public String getMessage()
   {
      return this.message;
   }

   /**
    * Indicates if node is exapnd
    * 
    * @return {@code true} if node is expand
    */
   public boolean isExpand()
   {
      return this.expand;
   }
}