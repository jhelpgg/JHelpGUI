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
package jhelp.gui.game.builder;

import java.awt.Rectangle;
import java.util.List;

import jhelp.gui.game.SensitiveElement;
import jhelp.util.list.Pair;

/**
 * Layout for sensitive animation
 * 
 * @author JHelp
 */
public interface SensitiveAnimationLayout
{
   /**
    * Indicates if given constraints can be used with this layout
    * 
    * @param constraints
    *           Tested constraints
    * @return {@code true} if given constraints can be used with this layout
    */
   public boolean isCompatible(SensitiveAnimationConstraints constraints);

   /**
    * Layout given components with their constraints.<br>
    * Call {@link SensitiveElement#setPosition(int, int)} for place a component.<br>
    * Remember an {@link SensitiveElement#isVisible() invisible} component musn't be take in count in computing and not need to
    * be located
    * 
    * @param elements
    *           Element/constraint pairs to layout
    * @param x
    *           Up left corner X (Every position X should be >= that value)
    * @param y
    *           Up left corner Y (Every position Y should be >= that value)
    * @param width
    *           Maximum width where layout components
    * @param height
    *           Maximum height where layout components
    * @return The rectangle that contains all located components
    */
   public Rectangle layout(List<Pair<SensitiveElement, SensitiveAnimationConstraints>> elements, int x, int y, int width, int height);
}