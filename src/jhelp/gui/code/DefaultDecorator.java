/*
 * License :
 * The following code is deliver as is. I take care that code compile and work, but I am not responsible about any damage it may cause.
 * You can use, modify, the code as your need for any usage.
 * But you can't do any action that avoid me or other person use, modify this code.
 * The code is free for usage and modification, you can't change that fact.
 * JHelp
 */

/*
 * License :
 * The following code is deliver as is. I take care that code compile and work, but I am not responsible about any
 * damage it may cause.
 * You can use, modify, the code as your need for any usage.
 * But you can't do any action that avoid me or other person use, modify this code.
 * The code is free for usage and modification, you can't change that fact.
 * JHelp
 */

/*
 * License :
 * The following code is deliver as is. I take care that code compile and work, but I am not responsible about any
 * damage it may cause.
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
package jhelp.gui.code;

import java.awt.Color;

/**
 * Default decorator
 */
public class DefaultDecorator implements Decorator
{
    /**
     * White not too bright
     */
    private static final Color WHITE = new Color(0xFFEEEEEE, true);

    /**
     * Create default decorator
     */
    public DefaultDecorator()
    {
    }

    /**
     * Font familly to use
     *
     * @return Font familly to use
     */
    @Override
    public String fontFamily()
    {
        return "Courrier";
    }

    /**
     * Normal text size (Other sizes are computed on using this value)
     *
     * @return Normal text size
     */
    @Override
    public int normalTextSize()
    {
        return 24;
    }

    /**
     * Obtain decoration for giving rule
     *
     * @param rules Rule
     * @return Decoration
     */
    @Override
    public Decoration obtainDecoration(Rules rules)
    {
        switch (rules)
        {
            case COMMENT:
                return new Decoration(TextSize.NORMAL, false, true, false, new Color(0xFF444444, true), WHITE);
            case KEY_WORD:
                return new Decoration(TextSize.NORMAL, true, false, false, new Color(0xFF30AE30, true), WHITE);
            case PRIMITIVE:
            case SYMBOL:
                return new Decoration(TextSize.NORMAL, true, false, false, new Color(0xFF3030AE, true), WHITE);
            case STRING:
                return new Decoration(TextSize.NORMAL, false, true, false, new Color(0xFF224488, true), WHITE);
            case OPERANDE:
                return new Decoration(TextSize.NORMAL, true, false, false, new Color(0xFFAE3030, true), WHITE);
            case DEFAULT:
            default:
                return new Decoration(TextSize.NORMAL, false, false, false, Color.BLACK, WHITE);
        }
    }
}
