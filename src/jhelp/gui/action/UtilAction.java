/*
 * License :
 * The following code is deliver as is. I take care that code compile and work, but I am not responsible about any damage it may cause.
 * You can use, modify, the code as your need for any usage.
 * But you can't do any action that avoid me or other person use, modify this code.
 * The code is free for usage and modification, you can't change that fact.
 * JHelp
 */

/**
 * <h1>License :</h1> <br>
 * The following code is deliver as is. I take care that code compile and work, but I am not responsible about any
 * damage it may
 * cause.<br>
 * You can use, modify, the code as your need for any usage. But you can't do any action that avoid me or other person use,
 * modify this code. The code is free for usage and modification, you can't change that fact.<br>
 * <br>
 *
 * @author JHelp
 */
package jhelp.gui.action;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

/**
 * Utilities for {@link GenericAction}
 */
public class UtilAction
{
    /**
     * Define a short cut and register it
     *
     * @param keyStroke     Shortcut to register
     * @param genericAction Action to associate to short cut
     * @param frame         Frame where action play
     */
    public static void defineShortCut(KeyStroke keyStroke, GenericAction genericAction, JFrame frame)
    {
        UtilAction.defineShortCut(keyStroke, genericAction, frame.getRootPane());
    }

    /**
     * Define a short cut and register it
     *
     * @param keyStroke     Short cut to register
     * @param genericAction Action to associate to short cut
     * @param component     Component of window linked to short cut
     */
    public static void defineShortCut(KeyStroke keyStroke, GenericAction genericAction, JComponent component)
    {
        final ActionMap actionMap = component.getActionMap();
        final InputMap  inputMap  = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        genericAction.setShortcut(keyStroke);
        actionMap.put(genericAction.getName(), genericAction);
        inputMap.put(keyStroke, genericAction.getName());
    }
}
