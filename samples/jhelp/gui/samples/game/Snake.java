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