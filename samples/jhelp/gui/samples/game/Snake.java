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
package jhelp.gui.samples.game;

/**
 * Launch the snake game. <br>
 * By default arrow for control Snake, E for exit, W for menu and change key mapping
 * 
 * @author JHelp
 */
public class Snake
{

   /**
    * Launch snake game<br>
    * By default arrow for control Snake, E for exit, W for menu and change key mapping
    * 
    * @param args
    *           Unused
    */
   public static void main(final String[] args)
   {
      final SnakeFrame snakeFrame = new SnakeFrame();
      snakeFrame.setVisible(true);
   }
}