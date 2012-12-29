package jhelp.gui.serializer;

/**
 * Defines the constants
 */
public interface Constants
{
   // **************************
   // *** Markups for parser ***
   // **************************
   /**
    * Main markup for describe a list of a gradient:
    * <ul>
    * <li><b>upLeft</b> : Up left color</li>
    * <li><b>upRight</b> : Up right color</li>
    * <li><b>downLeft</b> : Down left color</li>
    * <li><b>downRight</b> : Down right color</li>
    * </ul>
    */
   public static final String MARKUP_GRADIENT            = "Gradient";
   /** Main markup for describe a gradient horizontal.It have Percent ({@link #MARKUP_PERCENT}) as children */
   public static final String MARKUP_GRADIENT_HORIZONTAL = "GradientHorizontal";
   /** Main markup for describe a gradient vertical.It have Percent ({@link #MARKUP_PERCENT}) as children */
   public static final String MARKUP_GRADIENT_VERTICAL   = "GradientVertical";
   /**
    * A percent, color couple
    * <ul>
    * <li><b>color</b> : the color</li>
    * <li><b>percent</b> : the percent</li>
    * </ul>
    */
   public static final String MARKUP_PERCENT             = "Percent";
   /**
    * Main markup for describe a font:
    * <ul>
    * <li><b>family</b> : Font family</li>
    * <li><b>size</b> : Font size</li>
    * <li><b>bold</b> : Indicates if its bold (false by default)</li>
    * <li><b>italic</b> : Indicates if its italic (false by default)</li>
    * <li><b>underline</b> : Indicates if its underline (false by default)</li>
    * </ul>
    */
   public static final String MARKUP_FONT                = "Font";
   // *****************************
   // *** Parameters for parser ***
   // *****************************
   /** Up left color. Used by : {@link #MARKUP_GRADIENT} */
   public static final String PARAMETER_UP_LEFT          = "upLeft";
   /** Up right color. Used by : {@link #MARKUP_GRADIENT} */
   public static final String PARAMETER_UP_RIGHT         = "upRight";
   /** Down left color. Used by : {@link #MARKUP_GRADIENT} */
   public static final String PARAMETER_DOWN_LEFT        = "downLeft";
   /** Down right color. Used by : {@link #MARKUP_GRADIENT} */
   public static final String PARAMETER_DOWN_RIGHT       = "downRight";
   /** A color. Used by : {@link #MARKUP_PERCENT} */
   public static final String PARAMETER_COLOR            = "color";
   /** A percent. Used by : {@link #MARKUP_PERCENT} */
   public static final String PARAMETER_PERCENT          = "percent";
   /** Font family name. Used by : {@link #MARKUP_FONT} */
   public static final String PARAMETER_FAMILY           = "family";
   /** Font size. Used by : {@link #MARKUP_FONT} */
   public static final String PARAMETER_SIZE             = "size";
   /** Bold status. Used by : {@link #MARKUP_FONT} */
   public static final String PARAMETER_BOLD             = "bold";
   /** Italic status. Used by : {@link #MARKUP_FONT} */
   public static final String PARAMETER_ITALIC           = "italic";
   /** Underline status. Used by : {@link #MARKUP_FONT} */
   public static final String PARAMETER_UNDERLINE        = "underline";
}