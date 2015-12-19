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
package jhelp.gui.smooth;

import jhelp.gui.smooth.resources.JHelpResourcesSmooth;
import jhelp.util.gui.JHelpFont;
import jhelp.util.gui.JHelpImage;

/**
 * Constants defines for smooth
 * 
 * @author JHelp
 */
public interface JHelpConstantsSmooth
{
   // **************
   // *** ALPHAS ***
   // **************

   /** Alpha suggested for hint */
   public static final int        COLOR_ALPHA_HINT        = 0x66000000;
   /** Alpha suggested for lower */
   public static final int        COLOR_ALPHA_LOWER       = 0x89000000;
   /** Alpha suggested for main */
   public static final int        COLOR_ALPHA_MAIN        = 0xDD000000;
   /** Alpha suggested for opaque */
   public static final int        COLOR_ALPHA_OPAQUE      = 0xFF000000;
   /** Alpha suggested for totaly transparent */
   public static final int        COLOR_ALPHA_TRANSPARENT = 0x00000000;

   /** Alphas list */
   public static final int[]      COLOR_ALPHAS            =
                                                          {
         JHelpConstantsSmooth.COLOR_ALPHA_OPAQUE, JHelpConstantsSmooth.COLOR_ALPHA_MAIN, JHelpConstantsSmooth.COLOR_ALPHA_LOWER,
         JHelpConstantsSmooth.COLOR_ALPHA_HINT, JHelpConstantsSmooth.COLOR_ALPHA_TRANSPARENT
                                                          };

   // **************
   // *** COLORS ***
   // **************

   /** Amber 50 */
   public static final int        COLOR_AMBER_0050        = 0xFFFFF8E1;
   /** Amber 100 */
   public static final int        COLOR_AMBER_0100        = 0xFFFFECB3;
   /** Amber 200 */
   public static final int        COLOR_AMBER_0200        = 0xFFFFE082;
   /** Amber 300 */
   public static final int        COLOR_AMBER_0300        = 0xFFFFD54F;
   /** Amber 400 */
   public static final int        COLOR_AMBER_0400        = 0xFFFFCA28;
   /** Amber 500 : Reference */
   public static final int        COLOR_AMBER_0500        = 0xFFFFC107;
   /** Amber 600 */
   public static final int        COLOR_AMBER_0600        = 0xFFFFB300;
   /** Amber 700 */
   public static final int        COLOR_AMBER_0700        = 0xFFFFA000;
   /** Amber 800 */
   public static final int        COLOR_AMBER_0800        = 0xFFFF8F00;
   /** Amber 900 */
   public static final int        COLOR_AMBER_0900        = 0xFFFF6F00;
   /** Amber A100 */
   public static final int        COLOR_AMBER_A100        = 0xFFFFE57F;
   /** Amber A200 */
   public static final int        COLOR_AMBER_A200        = 0xFFFFD740;
   /** Amber A400 */
   public static final int        COLOR_AMBER_A400        = 0xFFFFC400;
   /** Amber A700 */
   public static final int        COLOR_AMBER_A700        = 0xFFFFAB00;

   /** Amber list : light to dark */
   public static final int[]      COLOR_AMBERS            =
                                                          {
         JHelpConstantsSmooth.COLOR_AMBER_0050, JHelpConstantsSmooth.COLOR_AMBER_0100, JHelpConstantsSmooth.COLOR_AMBER_0200,
         JHelpConstantsSmooth.COLOR_AMBER_0300, JHelpConstantsSmooth.COLOR_AMBER_0400, JHelpConstantsSmooth.COLOR_AMBER_0500,
         JHelpConstantsSmooth.COLOR_AMBER_0600, JHelpConstantsSmooth.COLOR_AMBER_0700, JHelpConstantsSmooth.COLOR_AMBER_0800,
         JHelpConstantsSmooth.COLOR_AMBER_0900, JHelpConstantsSmooth.COLOR_AMBER_A100, JHelpConstantsSmooth.COLOR_AMBER_A200,
         JHelpConstantsSmooth.COLOR_AMBER_A400, JHelpConstantsSmooth.COLOR_AMBER_A700
                                                          };

   /** Black */
   public static final int        COLOR_BLACK             = 0xFF000000;

   /** Blue 50 */
   public static final int        COLOR_BLUE_0050         = 0xFFE7E9FD;
   /** Blue 100 */
   public static final int        COLOR_BLUE_0100         = 0xFFD0D9FF;
   /** Blue 200 */
   public static final int        COLOR_BLUE_0200         = 0xFFAFBFFF;
   /** Blue 300 */
   public static final int        COLOR_BLUE_0300         = 0xFF91A7FF;
   /** Blue 400 */
   public static final int        COLOR_BLUE_0400         = 0xFF738FFE;
   /** Blue 500 */
   public static final int        COLOR_BLUE_0500         = 0xFF5677FC;
   /** Blue 600 */
   public static final int        COLOR_BLUE_0600         = 0xFF4E6CEF;
   /** Blue 700 */
   public static final int        COLOR_BLUE_0700         = 0xFF455EDE;
   /** Blue 800 */
   public static final int        COLOR_BLUE_0800         = 0xFF3B50CE;
   /** Blue 900 */
   public static final int        COLOR_BLUE_0900         = 0xFF2A36B1;
   /** Blue A100 */
   public static final int        COLOR_BLUE_A100         = 0xFFA6BAFF;
   /** Blue A200 */
   public static final int        COLOR_BLUE_A200         = 0xFF6889FF;
   /** Blue A400 */
   public static final int        COLOR_BLUE_A400         = 0xFF4D73FF;
   /** Blue A700 */
   public static final int        COLOR_BLUE_A700         = 0xFF4D69FF;

   /** Blue grey 50 */
   public static final int        COLOR_BLUE_GREY_0050    = 0xFFECEFF1;
   /** Blue grey 100 */
   public static final int        COLOR_BLUE_GREY_0100    = 0xFFCFD8DC;
   /** Blue grey 200 */
   public static final int        COLOR_BLUE_GREY_0200    = 0xFFB0BEC5;
   /** Blue grey 300 */
   public static final int        COLOR_BLUE_GREY_0300    = 0xFF90A4AE;
   /** Blue grey 400 */
   public static final int        COLOR_BLUE_GREY_0400    = 0xFF78909C;
   /** Blue grey 500 */
   public static final int        COLOR_BLUE_GREY_0500    = 0xFF607D8B;
   /** Blue grey 600 */
   public static final int        COLOR_BLUE_GREY_0600    = 0xFF546E7A;
   /** Blue grey 700 */
   public static final int        COLOR_BLUE_GREY_0700    = 0xFF455A64;
   /** Blue grey 800 */
   public static final int        COLOR_BLUE_GREY_0800    = 0xFF37474F;
   /** Blue grey 900 */
   public static final int        COLOR_BLUE_GREY_0900    = 0xFF263238;

   /** Blue grey list : light to dark */
   public static final int[]      COLOR_BLUE_GREYS        =
                                                          {
         JHelpConstantsSmooth.COLOR_BLUE_GREY_0050, JHelpConstantsSmooth.COLOR_BLUE_GREY_0100, JHelpConstantsSmooth.COLOR_BLUE_GREY_0200,
         JHelpConstantsSmooth.COLOR_BLUE_GREY_0300, JHelpConstantsSmooth.COLOR_BLUE_GREY_0400, JHelpConstantsSmooth.COLOR_BLUE_GREY_0500,
         JHelpConstantsSmooth.COLOR_BLUE_GREY_0600, JHelpConstantsSmooth.COLOR_BLUE_GREY_0700, JHelpConstantsSmooth.COLOR_BLUE_GREY_0800,
         JHelpConstantsSmooth.COLOR_BLUE_GREY_0900
                                                          };

   /** Blue list : light to dark */
   public static final int[]      COLOR_BLUES             =
                                                          {
         JHelpConstantsSmooth.COLOR_BLUE_0050, JHelpConstantsSmooth.COLOR_BLUE_0100, JHelpConstantsSmooth.COLOR_BLUE_0200,
         JHelpConstantsSmooth.COLOR_BLUE_0300, JHelpConstantsSmooth.COLOR_BLUE_0400, JHelpConstantsSmooth.COLOR_BLUE_0500,
         JHelpConstantsSmooth.COLOR_BLUE_0600, JHelpConstantsSmooth.COLOR_BLUE_0700, JHelpConstantsSmooth.COLOR_BLUE_0800,
         JHelpConstantsSmooth.COLOR_BLUE_0900, JHelpConstantsSmooth.COLOR_BLUE_A100, JHelpConstantsSmooth.COLOR_BLUE_A200,
         JHelpConstantsSmooth.COLOR_BLUE_A400, JHelpConstantsSmooth.COLOR_BLUE_A700
                                                          };

   /** Brown 50 */
   public static final int        COLOR_BROWN_0050        = 0xFFEFEBE9;
   /** Brown 100 */
   public static final int        COLOR_BROWN_0100        = 0xFFD7CCC8;
   /** Brown 200 */
   public static final int        COLOR_BROWN_0200        = 0xFFBCAAA4;
   /** Brown 300 */
   public static final int        COLOR_BROWN_0300        = 0xFFA1887F;
   /** Brown 400 */
   public static final int        COLOR_BROWN_0400        = 0xFF8D6E63;
   /** Brown 500 */
   public static final int        COLOR_BROWN_0500        = 0xFF795548;
   /** Brown 600 */
   public static final int        COLOR_BROWN_0600        = 0xFF6D4C41;
   /** Brown 700 */
   public static final int        COLOR_BROWN_0700        = 0xFF5D4037;
   /** Brown 800 */
   public static final int        COLOR_BROWN_0800        = 0xFF4E342E;
   /** Brown 900 */
   public static final int        COLOR_BROWN_0900        = 0xFF3E2723;

   /** Brown list : light to dark */
   public static final int[]      COLOR_BROWNS            =
                                                          {
         JHelpConstantsSmooth.COLOR_BROWN_0050, JHelpConstantsSmooth.COLOR_BROWN_0100, JHelpConstantsSmooth.COLOR_BROWN_0200,
         JHelpConstantsSmooth.COLOR_BROWN_0300, JHelpConstantsSmooth.COLOR_BROWN_0400, JHelpConstantsSmooth.COLOR_BROWN_0500,
         JHelpConstantsSmooth.COLOR_BROWN_0600, JHelpConstantsSmooth.COLOR_BROWN_0700, JHelpConstantsSmooth.COLOR_BROWN_0800,
         JHelpConstantsSmooth.COLOR_BROWN_0900
                                                          };

   /** Cyan 50 */
   public static final int        COLOR_CYAN_0050         = 0xFFE0F7FA;
   /** Cyan 100 */
   public static final int        COLOR_CYAN_0100         = 0xFFB2EBF2;
   /** Cyan 200 */
   public static final int        COLOR_CYAN_0200         = 0xFF80DEEA;
   /** Cyan 300 */
   public static final int        COLOR_CYAN_0300         = 0xFF4DD0E1;
   /** Cyan 400 */
   public static final int        COLOR_CYAN_0400         = 0xFF26C6DA;
   /** Cyan 500 */
   public static final int        COLOR_CYAN_0500         = 0xFF00BCD4;
   /** Cyan 600 */
   public static final int        COLOR_CYAN_0600         = 0xFF00ACC1;
   /** Cyan 700 */
   public static final int        COLOR_CYAN_0700         = 0xFF0097A7;
   /** Cyan 800 */
   public static final int        COLOR_CYAN_0800         = 0xFF00838F;
   /** Cyan 900 */
   public static final int        COLOR_CYAN_0900         = 0xFF006064;
   /** Cyan A100 */
   public static final int        COLOR_CYAN_A100         = 0xFF84FFFF;
   /** Cyan A200 */
   public static final int        COLOR_CYAN_A200         = 0xFF18FFFF;
   /** Cyan A400 */
   public static final int        COLOR_CYAN_A400         = 0xFF00E5FF;
   /** Cyan A700 */
   public static final int        COLOR_CYAN_A700         = 0xFF00B8D4;

   /** Cyan list : light to dark */
   public static final int[]      COLOR_CYANS             =
                                                          {
         JHelpConstantsSmooth.COLOR_CYAN_0050, JHelpConstantsSmooth.COLOR_CYAN_0100, JHelpConstantsSmooth.COLOR_CYAN_0200,
         JHelpConstantsSmooth.COLOR_CYAN_0300, JHelpConstantsSmooth.COLOR_CYAN_0400, JHelpConstantsSmooth.COLOR_CYAN_0500,
         JHelpConstantsSmooth.COLOR_CYAN_0600, JHelpConstantsSmooth.COLOR_CYAN_0700, JHelpConstantsSmooth.COLOR_CYAN_0800,
         JHelpConstantsSmooth.COLOR_CYAN_0900, JHelpConstantsSmooth.COLOR_CYAN_A100, JHelpConstantsSmooth.COLOR_CYAN_A200,
         JHelpConstantsSmooth.COLOR_CYAN_A400, JHelpConstantsSmooth.COLOR_CYAN_A700
                                                          };

   /** Deep orange 50 */
   public static final int        COLOR_DEEP_ORANGE_0050  = 0xFFFBE9E7;
   /** Deep orange 100 */
   public static final int        COLOR_DEEP_ORANGE_0100  = 0xFFFFCCBC;
   /** Deep orange 200 */
   public static final int        COLOR_DEEP_ORANGE_0200  = 0xFFFFAB91;
   /** Deep orange 300 */
   public static final int        COLOR_DEEP_ORANGE_0300  = 0xFFFF8A65;
   /** Deep orange 400 */
   public static final int        COLOR_DEEP_ORANGE_0400  = 0xFFFF7043;
   /** Deep orange 500 */
   public static final int        COLOR_DEEP_ORANGE_0500  = 0xFFFF5722;
   /** Deep orange 600 */
   public static final int        COLOR_DEEP_ORANGE_0600  = 0xFFF4511E;
   /** Deep orange 700 */
   public static final int        COLOR_DEEP_ORANGE_0700  = 0xFFE64A19;
   /** Deep orange 800 */
   public static final int        COLOR_DEEP_ORANGE_0800  = 0xFFD84315;
   /** Deep orange 900 */
   public static final int        COLOR_DEEP_ORANGE_0900  = 0xFFBF360C;
   /** Deep orange A100 */
   public static final int        COLOR_DEEP_ORANGE_A100  = 0xFFFF9E80;
   /** Deep orange A200 */
   public static final int        COLOR_DEEP_ORANGE_A200  = 0xFFFF6E40;
   /** Deep orange A400 */
   public static final int        COLOR_DEEP_ORANGE_A400  = 0xFFFF3D00;
   /** Deep orange A700 */
   public static final int        COLOR_DEEP_ORANGE_A700  = 0xFFDD2C00;

   /** Deep orange list : light to dark */
   public static final int[]      COLOR_DEEP_ORANGES      =
                                                          {
         JHelpConstantsSmooth.COLOR_DEEP_ORANGE_0050, JHelpConstantsSmooth.COLOR_DEEP_ORANGE_0100, JHelpConstantsSmooth.COLOR_DEEP_ORANGE_0200,
         JHelpConstantsSmooth.COLOR_DEEP_ORANGE_0300, JHelpConstantsSmooth.COLOR_DEEP_ORANGE_0400, JHelpConstantsSmooth.COLOR_DEEP_ORANGE_0500,
         JHelpConstantsSmooth.COLOR_DEEP_ORANGE_0600, JHelpConstantsSmooth.COLOR_DEEP_ORANGE_0700, JHelpConstantsSmooth.COLOR_DEEP_ORANGE_0800,
         JHelpConstantsSmooth.COLOR_DEEP_ORANGE_0900, JHelpConstantsSmooth.COLOR_DEEP_ORANGE_A100, JHelpConstantsSmooth.COLOR_DEEP_ORANGE_A200,
         JHelpConstantsSmooth.COLOR_DEEP_ORANGE_A400, JHelpConstantsSmooth.COLOR_DEEP_ORANGE_A700
                                                          };

   /** Deep purple 50 */
   public static final int        COLOR_DEEP_PURPLE_0050  = 0xFFEDE7F6;
   /** Deep purple 100 */
   public static final int        COLOR_DEEP_PURPLE_0100  = 0xFFD1C4E9;
   /** Deep purple 200 */
   public static final int        COLOR_DEEP_PURPLE_0200  = 0xFFB39DDB;
   /** Deep purple 300 */
   public static final int        COLOR_DEEP_PURPLE_0300  = 0xFF9575CD;
   /** Deep purple 400 */
   public static final int        COLOR_DEEP_PURPLE_0400  = 0xFF7E57C2;
   /** Deep purple 500 */
   public static final int        COLOR_DEEP_PURPLE_0500  = 0xFF673AB7;
   /** Deep purple 600 */
   public static final int        COLOR_DEEP_PURPLE_0600  = 0xFF5E35B1;
   /** Deep purple 700 */
   public static final int        COLOR_DEEP_PURPLE_0700  = 0xFF512DA8;
   /** Deep purple 800 */
   public static final int        COLOR_DEEP_PURPLE_0800  = 0xFF4527A0;
   /** Deep purple 900 */
   public static final int        COLOR_DEEP_PURPLE_0900  = 0xFF311B92;
   /** Deep purple A100 */
   public static final int        COLOR_DEEP_PURPLE_A100  = 0xFFB388FF;
   /** Deep purple A200 */
   public static final int        COLOR_DEEP_PURPLE_A200  = 0xFF7C4DFF;
   /** Deep purple A400 */
   public static final int        COLOR_DEEP_PURPLE_A400  = 0xFF651FFF;
   /** Deep purple A700 */
   public static final int        COLOR_DEEP_PURPLE_A700  = 0xFF6200EA;

   /** Deep purple list : light to dark */
   public static final int[]      COLOR_DEEP_PURPLES      =
                                                          {
         JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0050, JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0100, JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0200,
         JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0300, JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0400, JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0500,
         JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0600, JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0700, JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0800,
         JHelpConstantsSmooth.COLOR_DEEP_PURPLE_0900, JHelpConstantsSmooth.COLOR_DEEP_PURPLE_A100, JHelpConstantsSmooth.COLOR_DEEP_PURPLE_A200,
         JHelpConstantsSmooth.COLOR_DEEP_PURPLE_A400, JHelpConstantsSmooth.COLOR_DEEP_PURPLE_A700
                                                          };

   /** Green 50 */
   public static final int        COLOR_GREEN_0050        = 0xFFD0F8CE;
   /** Green 100 */
   public static final int        COLOR_GREEN_0100        = 0xFFA3E9A4;
   /** Green 200 */
   public static final int        COLOR_GREEN_0200        = 0xFF72D572;
   /** Green 300 */
   public static final int        COLOR_GREEN_0300        = 0xFF42BD41;
   /** Green 400 */
   public static final int        COLOR_GREEN_0400        = 0xFF2BAF2B;
   /** Green 500 */
   public static final int        COLOR_GREEN_0500        = 0xFF259B24;
   /** Green 600 */
   public static final int        COLOR_GREEN_0600        = 0xFF0A8F08;
   /** Green 700 */
   public static final int        COLOR_GREEN_0700        = 0xFF0A7E07;
   /** Green 800 */
   public static final int        COLOR_GREEN_0800        = 0xFF056F00;
   /** Green 900 */
   public static final int        COLOR_GREEN_0900        = 0xFF0D5302;
   /** Green A100 */
   public static final int        COLOR_GREEN_A100        = 0xFFA2F78D;
   /** Green A200 */
   public static final int        COLOR_GREEN_A200        = 0xFF5AF158;
   /** Green A400 */
   public static final int        COLOR_GREEN_A400        = 0xFF14E715;
   /** Green A700 */
   public static final int        COLOR_GREEN_A700        = 0xFF12C700;

   /** Green list : light to dark */
   public static final int[]      COLOR_GREENS            =
                                                          {
         JHelpConstantsSmooth.COLOR_GREEN_0050, JHelpConstantsSmooth.COLOR_GREEN_0100, JHelpConstantsSmooth.COLOR_GREEN_0200,
         JHelpConstantsSmooth.COLOR_GREEN_0300, JHelpConstantsSmooth.COLOR_GREEN_0400, JHelpConstantsSmooth.COLOR_GREEN_0500,
         JHelpConstantsSmooth.COLOR_GREEN_0600, JHelpConstantsSmooth.COLOR_GREEN_0700, JHelpConstantsSmooth.COLOR_GREEN_0800,
         JHelpConstantsSmooth.COLOR_GREEN_0900, JHelpConstantsSmooth.COLOR_GREEN_A100, JHelpConstantsSmooth.COLOR_GREEN_A200,
         JHelpConstantsSmooth.COLOR_GREEN_A400, JHelpConstantsSmooth.COLOR_GREEN_A700
                                                          };

   /** Grey 50 */
   public static final int        COLOR_GREY_0050         = 0xFFFAFAFA;
   /** Grey 100 */
   public static final int        COLOR_GREY_0100         = 0xFFF5F5F5;
   /** Grey 200 */
   public static final int        COLOR_GREY_0200         = 0xFFEEEEEE;
   /** Grey 300 */
   public static final int        COLOR_GREY_0300         = 0xFFE0E0E0;
   /** Grey 400 */
   public static final int        COLOR_GREY_0400         = 0xFFBDBDBD;
   /** Grey 500 */
   public static final int        COLOR_GREY_0500         = 0xFF9E9E9E;
   /** Grey 600 */
   public static final int        COLOR_GREY_0600         = 0xFF757575;
   /** Grey 700 */
   public static final int        COLOR_GREY_0700         = 0xFF616161;
   /** Grey 800 */
   public static final int        COLOR_GREY_0800         = 0xFF424242;
   /** Grey 900 */
   public static final int        COLOR_GREY_0900         = 0xFF212121;

   /** Grey list : light to dark (white to black) */
   public static final int[]      COLOR_GREYS             =
                                                          {
         JHelpConstantsSmooth.COLOR_WHITE, JHelpConstantsSmooth.COLOR_GREY_0050, JHelpConstantsSmooth.COLOR_GREY_0100, JHelpConstantsSmooth.COLOR_GREY_0200,
         JHelpConstantsSmooth.COLOR_GREY_0300, JHelpConstantsSmooth.COLOR_GREY_0400, JHelpConstantsSmooth.COLOR_GREY_0500,
         JHelpConstantsSmooth.COLOR_GREY_0600, JHelpConstantsSmooth.COLOR_GREY_0700, JHelpConstantsSmooth.COLOR_GREY_0800,
         JHelpConstantsSmooth.COLOR_GREY_0900, JHelpConstantsSmooth.COLOR_BLACK
                                                          };

   /** Indigo 50 */
   public static final int        COLOR_INDIGO_0050       = 0xFFE8EAF6;
   /** Indigo 100 */
   public static final int        COLOR_INDIGO_0100       = 0xFFC5CAE9;
   /** Indigo 200 */
   public static final int        COLOR_INDIGO_0200       = 0xFF9FA8DA;
   /** Indigo 300 */
   public static final int        COLOR_INDIGO_0300       = 0xFF7986CB;
   /** Indigo 400 */
   public static final int        COLOR_INDIGO_0400       = 0xFF5C6BC0;
   /** Indigo 500 */
   public static final int        COLOR_INDIGO_0500       = 0xFF3F51B5;
   /** Indigo 600 */
   public static final int        COLOR_INDIGO_0600       = 0xFF3949AB;
   /** Indigo 700 */
   public static final int        COLOR_INDIGO_0700       = 0xFF303F9F;
   /** Indigo 800 */
   public static final int        COLOR_INDIGO_0800       = 0xFF283593;
   /** Indigo 900 */
   public static final int        COLOR_INDIGO_0900       = 0xFF1A237E;
   /** Indigo A100 */
   public static final int        COLOR_INDIGO_A100       = 0xFF8C9EFF;
   /** Indigo A200 */
   public static final int        COLOR_INDIGO_A200       = 0xFF536DFE;
   /** Indigo A400 */
   public static final int        COLOR_INDIGO_A400       = 0xFF3D5AFE;
   /** Indigo A700 */
   public static final int        COLOR_INDIGO_A700       = 0xFF304FFE;

   /** Indigo list : light to dark */
   public static final int[]      COLOR_INDIGOS           =
                                                          {
         JHelpConstantsSmooth.COLOR_INDIGO_0050, JHelpConstantsSmooth.COLOR_INDIGO_0100, JHelpConstantsSmooth.COLOR_INDIGO_0200,
         JHelpConstantsSmooth.COLOR_INDIGO_0300, JHelpConstantsSmooth.COLOR_INDIGO_0400, JHelpConstantsSmooth.COLOR_INDIGO_0500,
         JHelpConstantsSmooth.COLOR_INDIGO_0600, JHelpConstantsSmooth.COLOR_INDIGO_0700, JHelpConstantsSmooth.COLOR_INDIGO_0800,
         JHelpConstantsSmooth.COLOR_INDIGO_0900, JHelpConstantsSmooth.COLOR_INDIGO_A100, JHelpConstantsSmooth.COLOR_INDIGO_A200,
         JHelpConstantsSmooth.COLOR_INDIGO_A400, JHelpConstantsSmooth.COLOR_INDIGO_A700
                                                          };

   /** Light blue 50 */
   public static final int        COLOR_LIGHT_BLUE_0050   = 0xFFE1F5FE;
   /** Light blue 100 */
   public static final int        COLOR_LIGHT_BLUE_0100   = 0xFFB3E5FC;
   /** Light blue 200 */
   public static final int        COLOR_LIGHT_BLUE_0200   = 0xFF81D4FA;
   /** Light blue 300 */
   public static final int        COLOR_LIGHT_BLUE_0300   = 0xFF4FC3F7;
   /** Light blue 400 */
   public static final int        COLOR_LIGHT_BLUE_0400   = 0xFF29B6F6;
   /** Light blue 500 */
   public static final int        COLOR_LIGHT_BLUE_0500   = 0xFF03A9F4;
   /** Light blue 600 */
   public static final int        COLOR_LIGHT_BLUE_0600   = 0xFF039BE5;
   /** Light blue 700 */
   public static final int        COLOR_LIGHT_BLUE_0700   = 0xFF0288D1;
   /** Light blue 800 */
   public static final int        COLOR_LIGHT_BLUE_0800   = 0xFF0277BD;
   /** Light blue 900 */
   public static final int        COLOR_LIGHT_BLUE_0900   = 0xFF01579B;
   /** Light blue A100 */
   public static final int        COLOR_LIGHT_BLUE_A100   = 0xFF80D8FF;
   /** Light blue A200 */
   public static final int        COLOR_LIGHT_BLUE_A200   = 0xFF40C4FF;
   /** Light blue A400 */
   public static final int        COLOR_LIGHT_BLUE_A400   = 0xFF00B0FF;
   /** Light blue A700 */
   public static final int        COLOR_LIGHT_BLUE_A700   = 0xFF0091EA;

   /** Light blue list : light to dark */
   public static final int[]      COLOR_LIGHT_BLUES       =
                                                          {
         JHelpConstantsSmooth.COLOR_LIGHT_BLUE_0050, JHelpConstantsSmooth.COLOR_LIGHT_BLUE_0100, JHelpConstantsSmooth.COLOR_LIGHT_BLUE_0200,
         JHelpConstantsSmooth.COLOR_LIGHT_BLUE_0300, JHelpConstantsSmooth.COLOR_LIGHT_BLUE_0400, JHelpConstantsSmooth.COLOR_LIGHT_BLUE_0500,
         JHelpConstantsSmooth.COLOR_LIGHT_BLUE_0600, JHelpConstantsSmooth.COLOR_LIGHT_BLUE_0700, JHelpConstantsSmooth.COLOR_LIGHT_BLUE_0800,
         JHelpConstantsSmooth.COLOR_LIGHT_BLUE_0900, JHelpConstantsSmooth.COLOR_LIGHT_BLUE_A100, JHelpConstantsSmooth.COLOR_LIGHT_BLUE_A200,
         JHelpConstantsSmooth.COLOR_LIGHT_BLUE_A400, JHelpConstantsSmooth.COLOR_LIGHT_BLUE_A700
                                                          };

   /** Light green 50 */
   public static final int        COLOR_LIGHT_GREEN_0050  = 0xFFF1F8E9;
   /** Light green 100 */
   public static final int        COLOR_LIGHT_GREEN_0100  = 0xFFDCEDC8;
   /** Light green 200 */
   public static final int        COLOR_LIGHT_GREEN_0200  = 0xFFC5E1A5;
   /** Light green 300 */
   public static final int        COLOR_LIGHT_GREEN_0300  = 0xFFAED581;
   /** Light green 400 */
   public static final int        COLOR_LIGHT_GREEN_0400  = 0xFF9CCC65;
   /** Light green 500 */
   public static final int        COLOR_LIGHT_GREEN_0500  = 0xFF8BC34A;
   /** Light green 600 */
   public static final int        COLOR_LIGHT_GREEN_0600  = 0xFF7CB342;
   /** Light green 700 */
   public static final int        COLOR_LIGHT_GREEN_0700  = 0xFF689F38;
   /** Light green 800 */
   public static final int        COLOR_LIGHT_GREEN_0800  = 0xFF558B2F;
   /** Light green 900 */
   public static final int        COLOR_LIGHT_GREEN_0900  = 0xFF33691E;
   /** Light green A100 */
   public static final int        COLOR_LIGHT_GREEN_A100  = 0xFFCCFF90;
   /** Light green A200 */
   public static final int        COLOR_LIGHT_GREEN_A200  = 0xFFB2FF59;
   /** Light green A400 */
   public static final int        COLOR_LIGHT_GREEN_A400  = 0xFF76FF03;
   /** Light green A700 */
   public static final int        COLOR_LIGHT_GREEN_A700  = 0xFF64DD17;

   /** Light green list : light to dark */
   public static final int[]      COLOR_LIGHT_GREENS      =
                                                          {
         JHelpConstantsSmooth.COLOR_LIGHT_GREEN_0050, JHelpConstantsSmooth.COLOR_LIGHT_GREEN_0100, JHelpConstantsSmooth.COLOR_LIGHT_GREEN_0200,
         JHelpConstantsSmooth.COLOR_LIGHT_GREEN_0300, JHelpConstantsSmooth.COLOR_LIGHT_GREEN_0400, JHelpConstantsSmooth.COLOR_LIGHT_GREEN_0500,
         JHelpConstantsSmooth.COLOR_LIGHT_GREEN_0600, JHelpConstantsSmooth.COLOR_LIGHT_GREEN_0700, JHelpConstantsSmooth.COLOR_LIGHT_GREEN_0800,
         JHelpConstantsSmooth.COLOR_LIGHT_GREEN_0900, JHelpConstantsSmooth.COLOR_LIGHT_GREEN_A100, JHelpConstantsSmooth.COLOR_LIGHT_GREEN_A200,
         JHelpConstantsSmooth.COLOR_LIGHT_GREEN_A400, JHelpConstantsSmooth.COLOR_LIGHT_GREEN_A700
                                                          };

   /** Lime 50 */
   public static final int        COLOR_LIME_0050         = 0xFFF9FBE7;
   /** Lime 100 */
   public static final int        COLOR_LIME_0100         = 0xFFF0F4C3;
   /** Lime 200 */
   public static final int        COLOR_LIME_0200         = 0xFFE6EE9C;
   /** Lime 300 */
   public static final int        COLOR_LIME_0300         = 0xFFDCE775;
   /** Lime 400 */
   public static final int        COLOR_LIME_0400         = 0xFFD4E157;
   /** Lime 500 */
   public static final int        COLOR_LIME_0500         = 0xFFCDDC39;
   /** Lime 600 */
   public static final int        COLOR_LIME_0600         = 0xFFC0CA33;
   /** Lime 700 */
   public static final int        COLOR_LIME_0700         = 0xFFAFB42B;
   /** Lime 800 */
   public static final int        COLOR_LIME_0800         = 0xFF9E9D24;
   /** Lime 900 */
   public static final int        COLOR_LIME_0900         = 0xFF827717;
   /** Lime A100 */
   public static final int        COLOR_LIME_A100         = 0xFFF4FF81;
   /** Lime A200 */
   public static final int        COLOR_LIME_A200         = 0xFFEEFF41;
   /** Lime A400 */
   public static final int        COLOR_LIME_A400         = 0xFFC6FF00;
   /** Lime A700 */
   public static final int        COLOR_LIME_A700         = 0xFFAEEA00;

   /** Lime list : light to dark */
   public static final int[]      COLOR_LIMES             =
                                                          {
         JHelpConstantsSmooth.COLOR_LIME_0050, JHelpConstantsSmooth.COLOR_LIME_0100, JHelpConstantsSmooth.COLOR_LIME_0200,
         JHelpConstantsSmooth.COLOR_LIME_0300, JHelpConstantsSmooth.COLOR_LIME_0400, JHelpConstantsSmooth.COLOR_LIME_0500,
         JHelpConstantsSmooth.COLOR_LIME_0600, JHelpConstantsSmooth.COLOR_LIME_0700, JHelpConstantsSmooth.COLOR_LIME_0800,
         JHelpConstantsSmooth.COLOR_LIME_0900, JHelpConstantsSmooth.COLOR_LIME_A100, JHelpConstantsSmooth.COLOR_LIME_A200,
         JHelpConstantsSmooth.COLOR_LIME_A400, JHelpConstantsSmooth.COLOR_LIME_A700
                                                          };

   /** Orange 00 */
   public static final int        COLOR_ORANGE_0050       = 0xFFFFF3E0;
   /** Orange 100 */
   public static final int        COLOR_ORANGE_0100       = 0xFFFFE0B2;
   /** Orange 200 */
   public static final int        COLOR_ORANGE_0200       = 0xFFFFCC80;
   /** Orange 300 */
   public static final int        COLOR_ORANGE_0300       = 0xFFFFB74D;
   /** Orange 400 */
   public static final int        COLOR_ORANGE_0400       = 0xFFFFA726;
   /** Orange 500 */
   public static final int        COLOR_ORANGE_0500       = 0xFFFF9800;
   /** Orange 600 */
   public static final int        COLOR_ORANGE_0600       = 0xFFFB8C00;
   /** Orange 700 */
   public static final int        COLOR_ORANGE_0700       = 0xFFF57C00;
   /** Orange 800 */
   public static final int        COLOR_ORANGE_0800       = 0xFFEF6C00;
   /** Orange 900 */
   public static final int        COLOR_ORANGE_0900       = 0xFFE65100;
   /** Orange A100 */
   public static final int        COLOR_ORANGE_A100       = 0xFFFFD180;
   /** Orange A200 */
   public static final int        COLOR_ORANGE_A200       = 0xFFFFAB40;
   /** Orange A400 */
   public static final int        COLOR_ORANGE_A400       = 0xFFFF9100;
   /** Orange A700 */
   public static final int        COLOR_ORANGE_A700       = 0xFFFF6D00;

   /** Orange list : light to dark */
   public static final int[]      COLOR_ORANGES           =
                                                          {
         JHelpConstantsSmooth.COLOR_ORANGE_0050, JHelpConstantsSmooth.COLOR_ORANGE_0100, JHelpConstantsSmooth.COLOR_ORANGE_0200,
         JHelpConstantsSmooth.COLOR_ORANGE_0300, JHelpConstantsSmooth.COLOR_ORANGE_0400, JHelpConstantsSmooth.COLOR_ORANGE_0500,
         JHelpConstantsSmooth.COLOR_ORANGE_0600, JHelpConstantsSmooth.COLOR_ORANGE_0700, JHelpConstantsSmooth.COLOR_ORANGE_0800,
         JHelpConstantsSmooth.COLOR_ORANGE_0900, JHelpConstantsSmooth.COLOR_ORANGE_A100, JHelpConstantsSmooth.COLOR_ORANGE_A200,
         JHelpConstantsSmooth.COLOR_ORANGE_A400, JHelpConstantsSmooth.COLOR_ORANGE_A700
                                                          };

   /** Pink 50 */
   public static final int        COLOR_PINK_0050         = 0xFFFCE4EC;
   /** Pink 100 */
   public static final int        COLOR_PINK_0100         = 0xFFF8BBD0;
   /** Pink 200 */
   public static final int        COLOR_PINK_0200         = 0xFFF48FB1;
   /** Pink 300 */
   public static final int        COLOR_PINK_0300         = 0xFFF06292;
   /** Pink 400 */
   public static final int        COLOR_PINK_0400         = 0xFFEC407A;
   /** Pink 500 */
   public static final int        COLOR_PINK_0500         = 0xFFE91E63;
   /** Pink 600 */
   public static final int        COLOR_PINK_0600         = 0xFFD81B60;
   /** Pink 700 */
   public static final int        COLOR_PINK_0700         = 0xFFC2185B;
   /** Pink 800 */
   public static final int        COLOR_PINK_0800         = 0xFFAD1457;
   /** Pink 900 */
   public static final int        COLOR_PINK_0900         = 0xFF880E4F;
   /** Pink A100 */
   public static final int        COLOR_PINK_A100         = 0xFFFF80AB;
   /** Pink A200 */
   public static final int        COLOR_PINK_A200         = 0xFFFF4081;
   /** Pink A400 */
   public static final int        COLOR_PINK_A400         = 0xFFF50057;
   /** Pink A700 */
   public static final int        COLOR_PINK_A700         = 0xFFC51162;

   /** Pink list : light to dark */
   public static final int[]      COLOR_PINKS             =
                                                          {
         JHelpConstantsSmooth.COLOR_PINK_0050, JHelpConstantsSmooth.COLOR_PINK_0100, JHelpConstantsSmooth.COLOR_PINK_0200,
         JHelpConstantsSmooth.COLOR_PINK_0300, JHelpConstantsSmooth.COLOR_PINK_0400, JHelpConstantsSmooth.COLOR_PINK_0500,
         JHelpConstantsSmooth.COLOR_PINK_0600, JHelpConstantsSmooth.COLOR_PINK_0700, JHelpConstantsSmooth.COLOR_PINK_0800,
         JHelpConstantsSmooth.COLOR_PINK_0900, JHelpConstantsSmooth.COLOR_PINK_A100, JHelpConstantsSmooth.COLOR_PINK_A200,
         JHelpConstantsSmooth.COLOR_PINK_A400, JHelpConstantsSmooth.COLOR_PINK_A700
                                                          };

   /** Purple 50 */
   public static final int        COLOR_PURPLE_0050       = 0xFFF3E5F5;
   /** Purple 100 */
   public static final int        COLOR_PURPLE_0100       = 0xFFE1BEE7;
   /** Purple 200 */
   public static final int        COLOR_PURPLE_0200       = 0xFFCE93D8;
   /** Purple 300 */
   public static final int        COLOR_PURPLE_0300       = 0xFFBA68C8;
   /** Purple 400 */
   public static final int        COLOR_PURPLE_0400       = 0xFFAB47BC;
   /** Purple 500 */
   public static final int        COLOR_PURPLE_0500       = 0xFF9C27B0;
   /** Purple 600 */
   public static final int        COLOR_PURPLE_0600       = 0xFF8E24AA;
   /** Purple 700 */
   public static final int        COLOR_PURPLE_0700       = 0xFF7B1FA2;
   /** Purple 800 */
   public static final int        COLOR_PURPLE_0800       = 0xFF6A1B9A;
   /** Purple 900 */
   public static final int        COLOR_PURPLE_0900       = 0xFF4A148C;
   /** Purple A100 */
   public static final int        COLOR_PURPLE_A100       = 0xFFEA80FC;
   /** Purple A200 */
   public static final int        COLOR_PURPLE_A200       = 0xFFE040FB;
   /** Purple A400 */
   public static final int        COLOR_PURPLE_A400       = 0xFFD500F9;
   /** Purple A700 */
   public static final int        COLOR_PURPLE_A700       = 0xFFAA00FF;

   /** Purple list : light to dark */
   public static final int[]      COLOR_PURPLES           =
                                                          {
         JHelpConstantsSmooth.COLOR_PURPLE_0050, JHelpConstantsSmooth.COLOR_PURPLE_0100, JHelpConstantsSmooth.COLOR_PURPLE_0200,
         JHelpConstantsSmooth.COLOR_PURPLE_0300, JHelpConstantsSmooth.COLOR_PURPLE_0400, JHelpConstantsSmooth.COLOR_PURPLE_0500,
         JHelpConstantsSmooth.COLOR_PURPLE_0600, JHelpConstantsSmooth.COLOR_PURPLE_0700, JHelpConstantsSmooth.COLOR_PURPLE_0800,
         JHelpConstantsSmooth.COLOR_PURPLE_0900, JHelpConstantsSmooth.COLOR_PURPLE_A100, JHelpConstantsSmooth.COLOR_PURPLE_A200,
         JHelpConstantsSmooth.COLOR_PURPLE_A400, JHelpConstantsSmooth.COLOR_PURPLE_A700
                                                          };

   /** Red 50 */
   public static final int        COLOR_RED_0050          = 0xFFFDE0DC;
   /** Red 100 */
   public static final int        COLOR_RED_0100          = 0xFFF9BDBB;
   /** Red 200 */
   public static final int        COLOR_RED_0200          = 0xFFF69988;
   /** Red 300 */
   public static final int        COLOR_RED_0300          = 0xFFF36C60;
   /** Red 400 */
   public static final int        COLOR_RED_0400          = 0xFFE84E40;
   /** Red 500 */
   public static final int        COLOR_RED_0500          = 0xFFE51C23;
   /** Red 600 */
   public static final int        COLOR_RED_0600          = 0xFFDD191D;
   /** Red 700 */
   public static final int        COLOR_RED_0700          = 0xFFD01716;
   /** Red 800 */
   public static final int        COLOR_RED_0800          = 0xFFC41411;
   /** Red 900 */
   public static final int        COLOR_RED_0900          = 0xFFB0120A;
   /** Red A100 */
   public static final int        COLOR_RED_A100          = 0xFFFF7997;
   /** Red A200 */
   public static final int        COLOR_RED_A200          = 0xFFFF5177;
   /** Red A400 */
   public static final int        COLOR_RED_A400          = 0xFFFF2D6F;
   /** Red A700 */
   public static final int        COLOR_RED_A700          = 0xFFE00032;

   /** Red list : light to dark */
   public static final int[]      COLOR_REDS              =
                                                          {
         JHelpConstantsSmooth.COLOR_RED_0050, JHelpConstantsSmooth.COLOR_RED_0100, JHelpConstantsSmooth.COLOR_RED_0200, JHelpConstantsSmooth.COLOR_RED_0300,
         JHelpConstantsSmooth.COLOR_RED_0400, JHelpConstantsSmooth.COLOR_RED_0500, JHelpConstantsSmooth.COLOR_RED_0600, JHelpConstantsSmooth.COLOR_RED_0700,
         JHelpConstantsSmooth.COLOR_RED_0800, JHelpConstantsSmooth.COLOR_RED_0900, JHelpConstantsSmooth.COLOR_RED_A100, JHelpConstantsSmooth.COLOR_RED_A200,
         JHelpConstantsSmooth.COLOR_RED_A400, JHelpConstantsSmooth.COLOR_RED_A700
                                                          };

   /** Teal 50 */
   public static final int        COLOR_TEAL_0050         = 0xFFE0F2F1;
   /** Teal 100 */
   public static final int        COLOR_TEAL_0100         = 0xFFB2DFDB;
   /** Teal 200 */
   public static final int        COLOR_TEAL_0200         = 0xFF80CBC4;
   /** Teal 300 */
   public static final int        COLOR_TEAL_0300         = 0xFF4DB6AC;
   /** Teal 400 */
   public static final int        COLOR_TEAL_0400         = 0xFF26A69A;
   /** Teal 500 */
   public static final int        COLOR_TEAL_0500         = 0xFF009688;
   /** Teal 600 */
   public static final int        COLOR_TEAL_0600         = 0xFF00897B;
   /** Teal 700 */
   public static final int        COLOR_TEAL_0700         = 0xFF00796B;
   /** Teal 800 */
   public static final int        COLOR_TEAL_0800         = 0xFF00695C;
   /** Teal 900 */
   public static final int        COLOR_TEAL_0900         = 0xFF004D40;
   /** Teal A100 */
   public static final int        COLOR_TEAL_A100         = 0xFFA7FFEB;
   /** Teal A200 */
   public static final int        COLOR_TEAL_A200         = 0xFF64FFDA;
   /** Teal A400 */
   public static final int        COLOR_TEAL_A400         = 0xFF1DE9B6;
   /** Teal A700 */
   public static final int        COLOR_TEAL_A700         = 0xFF00BFA5;

   /** Teal list : light to dark */
   public static final int[]      COLOR_TEALS             =
                                                          {
         JHelpConstantsSmooth.COLOR_TEAL_0050, JHelpConstantsSmooth.COLOR_TEAL_0100, JHelpConstantsSmooth.COLOR_TEAL_0200,
         JHelpConstantsSmooth.COLOR_TEAL_0300, JHelpConstantsSmooth.COLOR_TEAL_0400, JHelpConstantsSmooth.COLOR_TEAL_0500,
         JHelpConstantsSmooth.COLOR_TEAL_0600, JHelpConstantsSmooth.COLOR_TEAL_0700, JHelpConstantsSmooth.COLOR_TEAL_0800,
         JHelpConstantsSmooth.COLOR_TEAL_0900, JHelpConstantsSmooth.COLOR_TEAL_A100, JHelpConstantsSmooth.COLOR_TEAL_A200,
         JHelpConstantsSmooth.COLOR_TEAL_A400, JHelpConstantsSmooth.COLOR_TEAL_A700
                                                          };

   /** White color */
   public static final int        COLOR_WHITE             = 0xFFFFFFFF;

   /** Yellow 50 */
   public static final int        COLOR_YELLOW_0050       = 0xFFFFFDE7;
   /** Yellow 100 */
   public static final int        COLOR_YELLOW_0100       = 0xFFFFF9C4;
   /** Yellow 200 */
   public static final int        COLOR_YELLOW_0200       = 0xFFFFF59D;
   /** Yellow 300 */
   public static final int        COLOR_YELLOW_0300       = 0xFFFFF176;
   /** Yellow 400 */
   public static final int        COLOR_YELLOW_0400       = 0xFFFFEE58;
   /** Yellow 500 */
   public static final int        COLOR_YELLOW_0500       = 0xFFFFEB3B;
   /** Yellow 600 */
   public static final int        COLOR_YELLOW_0600       = 0xFFFDD835;
   /** Yellow 700 */
   public static final int        COLOR_YELLOW_0700       = 0xFFFBC02D;
   /** Yellow 800 */
   public static final int        COLOR_YELLOW_0800       = 0xFFF9A825;
   /** Yellow 900 */
   public static final int        COLOR_YELLOW_0900       = 0xFFF57F17;
   /** Yellow A100 */
   public static final int        COLOR_YELLOW_A100       = 0xFFFFFF8D;
   /** Yellow A200 */
   public static final int        COLOR_YELLOW_A200       = 0xFFFFFF00;
   /** Yellow A400 */
   public static final int        COLOR_YELLOW_A400       = 0xFFFFEA00;
   /** Yellow A700 */
   public static final int        COLOR_YELLOW_A700       = 0xFFFFD600;

   /** Yellow list : light to dark */
   public static final int[]      COLOR_YELLOWS           =
                                                          {
         JHelpConstantsSmooth.COLOR_YELLOW_0050, JHelpConstantsSmooth.COLOR_YELLOW_0100, JHelpConstantsSmooth.COLOR_YELLOW_0200,
         JHelpConstantsSmooth.COLOR_YELLOW_0300, JHelpConstantsSmooth.COLOR_YELLOW_0400, JHelpConstantsSmooth.COLOR_YELLOW_0500,
         JHelpConstantsSmooth.COLOR_YELLOW_0600, JHelpConstantsSmooth.COLOR_YELLOW_0700, JHelpConstantsSmooth.COLOR_YELLOW_0800,
         JHelpConstantsSmooth.COLOR_YELLOW_0900, JHelpConstantsSmooth.COLOR_YELLOW_A100, JHelpConstantsSmooth.COLOR_YELLOW_A200,
         JHelpConstantsSmooth.COLOR_YELLOW_A400, JHelpConstantsSmooth.COLOR_YELLOW_A700
                                                          };

   /** Body font regular */
   public static final JHelpFont  FONT_BODY_1             = JHelpResourcesSmooth.getFont("font/Roboto-Regular.ttf", 24);
   /** Body font medium */
   public static final JHelpFont  FONT_BODY_2             = JHelpResourcesSmooth.getFont("font/Roboto-Medium.ttf", 24);
   /** Button font */
   public static final JHelpFont  FONT_BUTTON             = JHelpResourcesSmooth.getFont("font/Roboto-Medium.ttf", 32);
   /** Caption font */
   public static final JHelpFont  FONT_CAPTION            = JHelpResourcesSmooth.getFont("font/Roboto-Regular.ttf", 20);
   /** Display font small */
   public static final JHelpFont  FONT_DISPLAY_1          = JHelpResourcesSmooth.getFont("font/Roboto-Regular.ttf", 34);
   /** Display font normal */
   public static final JHelpFont  FONT_DISPLAY_2          = JHelpResourcesSmooth.getFont("font/Roboto-Regular.ttf", 45);
   /** Display font big */
   public static final JHelpFont  FONT_DISPLAY_3          = JHelpResourcesSmooth.getFont("font/Roboto-Regular.ttf", 56);
   /** Display font very big */
   public static final JHelpFont  FONT_DISPLAY_4          = JHelpResourcesSmooth.getFont("font/Roboto-Light.ttf", 112);
   /** Headline font */
   public static final JHelpFont  FONT_HEADLINE           = JHelpResourcesSmooth.getFont("font/Roboto-Regular.ttf", 24);
   /** Menu font */
   public static final JHelpFont  FONT_MENU               = JHelpResourcesSmooth.getFont("font/Roboto-Medium.ttf", 24);
   /** Operation font */
   public static final JHelpFont  FONT_OPERATION          = JHelpResourcesSmooth.getFont("font/Roboto-Thin.ttf", 24);
   /** Sub-head font */
   public static final JHelpFont  FONT_SUB_HEAD           = JHelpResourcesSmooth.getFont("font/Roboto-Regular.ttf", 26);
   /** Title font */
   public static final JHelpFont  FONT_TITLE              = JHelpResourcesSmooth.getFont("font/Roboto-Medium.ttf", 28);

   /** Error icon */
   public static final JHelpImage ICON_ERROR              = JHelpResourcesSmooth.obtainJHelpImage("images/errorIcon.png");
   /** Information icon */
   public static final JHelpImage ICON_INFORMATION        = JHelpResourcesSmooth.obtainJHelpImage("images/informationIcon.png");
   /** Question icon */
   public static final JHelpImage ICON_QUESTION           = JHelpResourcesSmooth.obtainJHelpImage("images/questionIcon.png");
   /** Warning icon */
   public static final JHelpImage ICON_WARNING            = JHelpResourcesSmooth.obtainJHelpImage("images/warningIcon.png");

   /** Mask for extract color part (RGB) from complete color (ARGB) */
   public static final int        MASK_COLOR              = 0x00FFFFFF;

   /** Text key for "CANCEL" */
   public static final String     TEXT_KEY_CANCEL         = "CANCEL";
   /** Text key for "NO" */
   public static final String     TEXT_KEY_NO             = "NO";
   /** Text key for "OK" */
   public static final String     TEXT_KEY_OK             = "OK";
   /** Text key for "YES" */
   public static final String     TEXT_KEY_YES            = "YES";
}