/**
 * Project : JHelpGUI<br>
 * Package : jhelp.gui<br>
 * Class : ButtonChooseColor<br>
 * Date : 12 mai 2009<br>
 * By JHelp
 */
package jhelp.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.border.Border;

/**
 * Button for choose a color<br>
 * <br>
 * Last modification : 12 mai 2009<br>
 * Version 0.0.0<br>
 * 
 * @author JHelp
 */
public class ButtonChooseColor
      extends JComponent
      implements MouseListener
{
   /** Actual color */
   private Color  color;
   /** Action command */
   private String command;

   /**
    * Constructs ButtonChooseColor
    */
   public ButtonChooseColor()
   {
      this(Color.BLACK, 50);
   }

   /**
    * Constructs ButtonChooseColor
    * 
    * @param color
    *           Start color
    */
   public ButtonChooseColor(final Color color)
   {
      this(color, 50);
   }

   /**
    * Constructs ButtonChooseColor
    * 
    * @param color
    *           Start color
    * @param size
    *           Button size
    */
   public ButtonChooseColor(final Color color, int size)
   {
      if(color == null)
      {
         throw new NullPointerException("color musn't be null");
      }

      this.color = color;

      if(size < 25)
      {
         size = 25;
      }

      final Dimension dimension = new Dimension(size, size);
      this.setSize(dimension);
      this.setPreferredSize(dimension);
      this.setMaximumSize(dimension);
      this.setMinimumSize(dimension);

      this.addMouseListener(this);

      this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   }

   /**
    * Constructs ButtonChooseColor
    * 
    * @param size
    *           Button size
    */
   public ButtonChooseColor(final int size)
   {
      this(Color.BLACK, size);
   }

   /**
    * Signal to listeners that color change
    */
   protected void fireActionPerformed()
   {
      ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, this.command);

      for(final ActionListener actionListener : this.listenerList.getListeners(ActionListener.class))
      {
         actionListener.actionPerformed(actionEvent);
      }

      actionEvent = null;
   }

   /**
    * Paint the button
    * 
    * @param g
    *           Graphics context
    * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
    */
   @Override
   protected void paintComponent(final Graphics g)
   {
      if(this.color != null)
      {
         int x = 0;
         int y = 0;
         int width = this.getWidth();
         int height = this.getHeight();

         final Border border = this.getBorder();
         if(border != null)
         {
            final Insets insets = border.getBorderInsets(this);
            x += insets.left;
            y += insets.top;
            width -= insets.right + insets.left;
            height -= insets.bottom + insets.top;
         }

         g.setColor(this.color);
         g.fillRect(x, y, width, height);
      }
   }

   /**
    * Add listener of change color
    * 
    * @param actionListener
    *           Listener to add
    */
   public void addActionListener(final ActionListener actionListener)
   {
      this.listenerList.add(ActionListener.class, actionListener);
   }

   /**
    * Return color
    * 
    * @return color
    */
   public Color getColor()
   {
      return this.color;
   }

   /**
    * Return command
    * 
    * @return command
    */
   public String getCommand()
   {
      return this.command;
   }

   /**
    * Action on clicking on the button
    * 
    * @param e
    *           Event description
    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseClicked(final MouseEvent e)
   {
      final Color color = JColorChooser.showDialog(this, "Choose a color", this.color);

      if((color == null) || (color.equals(this.color) == true))
      {
         return;
      }

      this.color = color;
      this.repaint();

      this.fireActionPerformed();
   }

   /**
    * Action on mouse enter
    * 
    * @param e
    *           Event description
    * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseEntered(final MouseEvent e)
   {
   }

   /**
    * Action on mouse exited
    * 
    * @param e
    *           Event description
    * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseExited(final MouseEvent e)
   {
   }

   /**
    * Action on mouse pressed
    * 
    * @param e
    *           Event description
    * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
    */
   @Override
   public void mousePressed(final MouseEvent e)
   {
   }

   /**
    * Action on mouse released
    * 
    * @param e
    *           Event description
    * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
    */
   @Override
   public void mouseReleased(final MouseEvent e)
   {
   }

   /**
    * Remove listener
    * 
    * @param actionListener
    *           Listener to remove
    */
   public void removeActionListener(final ActionListener actionListener)
   {
      this.listenerList.remove(ActionListener.class, actionListener);
   }

   /**
    * Modify color
    * 
    * @param color
    *           New color value
    */
   public void setColor(final Color color)
   {
      if(color == null)
      {
         throw new NullPointerException("color musn't be null");
      }

      if(color.equals(this.color) == true)
      {
         return;
      }

      this.color = color;
      this.repaint();

      this.fireActionPerformed();
   }

   /**
    * Modify command
    * 
    * @param command
    *           New command value
    */
   public void setCommand(final String command)
   {
      this.command = command;
   }
}