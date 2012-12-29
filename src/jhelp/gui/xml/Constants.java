package jhelp.gui.xml;

/**
 * Defines the constants
 */
public interface Constants
{
	// ***************
	// *** Markups ***
	// ***************
	/** Markup for describe the main frame   <ul>    <li><b>{@link #PARAMETER_NAME name}</b> : Name of the class (With the package name) [Mandatory]</li>    <li><b>{@link #PARAMETER_TITLE title}</b> : Frame title [Optional]</li>    <li><b>{@link #PARAMETER_LAYOUT layout}</b> : Layout class to use in {{@link #JHelpBorderLayout} (Default value), {@link #JHelpHorizontalLayout}, {@link #JHelpVerticalLayout}, {@link #JHelpTableLayout}} [Optional]</li>   </ul> */
	public static final String MARKUP_FRAME="JHelpFrame2D";
	/** Markup for describe a background component with round rectangle shape. It must contains a component as sub_markup     <ul>    <li><b>{@link #PARAMETER_NAME name}</b> : Name of the component [Mandatory]</li>    <li><b>arc</b> : Arc size (Default 20) [Optional]</li>    <li><b>background</b> : Background color in hexadecimal (AARRGGBB) (Default 80808080) [Optional]</li>   </ul> */
	public static final String MARKUP_BACKGROUND_ROUND_REACTANLGE="JHelpBackgroundRoundRectangle";
	/** Markup for describe a background component with round sausage shape. It must contains a component as sub_markup     <ul>    <li><b>{@link #PARAMETER_NAME name}</b> : Name of the component [Mandatory]</li>    <li><b>space</b> : Empty space (Default 10) [Optional]</li>    <li><b>background</b> : Background color in hexadecimal (AARRGGBB) (Default 80808080) [Optional]</li>   </ul> */
	public static final String MARKUP_BACKGROUND_SAUSSAGE="JHelpBackgroundSaussage";
	// ******************
	// *** Parameters ***
	// ******************
	/** Name of component.<br>Used by : {@link #MARKUP_FRAME}, {@link #MARKUP_BACKGROUND_ROUND_REACTANLGE} and {@link #MARKUP_BACKGROUND_SAUSSAGE} */
	public static final String PARAMETER_NAME="name";
	/** Title of the main frame.<br>Used by : {@link #MARKUP_FRAME} */
	public static final String PARAMETER_TITLE="title";
	/** Layout class to use in {{@link #JHelpBorderLayout} (Default value), {@link #JHelpHorizontalLayout}, {@link #JHelpVerticalLayout}, {@link #JHelpTableLayout}}.<br>Used by : {@link #MARKUP_FRAME} */
	public static final String PARAMETER_LAYOUT="layout";
	// *********************
	// *** Layout values ***
	// *********************
	/** Represents the {@link jhelp.gui.twoD.JHelpBorderLayout} class. It layout components one in center, other in "borders" */
	public static final String JHelpBorderLayout="JHelpBorderLayout";
	/** Represents the {@link jhelp.gui.twoD.JHelpHorizontalLayout} class. It layout components one side other one let to right" */
	public static final String JHelpHorizontalLayout="JHelpHorizontalLayout";
	/** Represents the {@link jhelp.gui.twoD.JHelpVerticalLayout} class. It layout components one bellow other */
	public static final String JHelpVerticalLayout="JHelpVerticalLayout";
	/** Represents the {@link jhelp.gui.twoD.JHelpTableLayout} class. It layout components in table with cells */
	public static final String JHelpTableLayout="JHelpTableLayout";
}