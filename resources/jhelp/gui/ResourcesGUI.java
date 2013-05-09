package jhelp.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.ImageIcon;

import jhelp.util.debug.Debug;
import jhelp.util.gui.JHelpRichText;
import jhelp.util.resources.Resources;

/**
 * Default/Common GUI resources
 * 
 * @author JHelp
 */
public class ResourcesGUI
{
   /** Base name for "Can drop" cursor */
   private static final String       BASE_NAME_CAN_DROP  = "canDrop";
   /** Base name for "Can't drop" cursor */
   private static final String       BASE_NAME_CANT_DROP = "cantDrop";
   /** End file name for 16x16 cursors */
   private static final String       SIZE_16x16          = "16x16.png";
   /** End file name for 32x32 cursors */
   private static final String       SIZE_32x32          = "32x32.png";

   /** Can drop cursor */
   public static final Cursor        CAN_DROP_CURSOR;
   /** Can't drop cursor */
   public static final Cursor        CANT_DROP_CURSOR;
   /** Attach window icon */
   public static final ImageIcon     ICON_ATTACH_WINDOW;
   /** Over close icon */
   public static final ImageIcon     ICON_CLOSE_OVER;

   /** Close icon */
   public static final ImageIcon     ICON_COSE_NORMAL;

   /** Detach window icon */
   public static final ImageIcon     ICON_DETACH_WINDOW;
   /** Hide icon */
   public static final ImageIcon     ICON_HIDE_NORMAL;
   /** Hide over icon */
   public static final ImageIcon     ICON_HIDE_OVER;
   /** Plus + icon */
   public static final ImageIcon     ICON_PLUS;
   /** Show icon */
   public static final ImageIcon     ICON_SHOW_NORMAL;
   /** Show over icon */
   public static final ImageIcon     ICON_SHOW_OVER;
   /** Indicates if we use 32x32 cursors */
   public static final boolean       IS_CURSOR_SIZE_32x32;
   /** "Free" access to GUI resources */
   public static final Resources     RESOURCES;
   /** Rich text with standard smiley replacement */
   public static final JHelpRichText STANDARD_SMILEYS;
   /** Translate for cursor */
   public static final int           TRANSLATE_CURSOR;

   static
   {
      RESOURCES = new Resources(ResourcesGUI.class);

      final Dimension dimension = Toolkit.getDefaultToolkit().getBestCursorSize(32, 32);
      IS_CURSOR_SIZE_32x32 = ((dimension.width == 32) && (dimension.height == 32));

      if(ResourcesGUI.IS_CURSOR_SIZE_32x32 == true)
      {
         TRANSLATE_CURSOR = -16;
      }
      else
      {
         TRANSLATE_CURSOR = -8;
      }

      CAN_DROP_CURSOR = ResourcesGUI.createCursor(ResourcesGUI.BASE_NAME_CAN_DROP);
      CANT_DROP_CURSOR = ResourcesGUI.createCursor(ResourcesGUI.BASE_NAME_CANT_DROP);
      ICON_ATTACH_WINDOW = ResourcesGUI.RESOURCES.obtainImageIcon("AttachWindow.png");
      ICON_CLOSE_OVER = ResourcesGUI.RESOURCES.obtainImageIcon("CloseOver.png");
      ICON_COSE_NORMAL = ResourcesGUI.RESOURCES.obtainImageIcon("CloseNormal.png");
      ICON_DETACH_WINDOW = ResourcesGUI.RESOURCES.obtainImageIcon("DetachWindow.png");
      ICON_HIDE_NORMAL = ResourcesGUI.RESOURCES.obtainImageIcon("HideNormal.png");
      ICON_HIDE_OVER = ResourcesGUI.RESOURCES.obtainImageIcon("HideOver.png");
      ICON_SHOW_NORMAL = ResourcesGUI.RESOURCES.obtainImageIcon("ShowNormal.png");
      ICON_SHOW_OVER = ResourcesGUI.RESOURCES.obtainImageIcon("ShowOver.png");
      ICON_PLUS = ResourcesGUI.RESOURCES.obtainImageIcon("plus.gif");

      STANDARD_SMILEYS = new JHelpRichText(ResourcesGUI.RESOURCES);

      ResourcesGUI.STANDARD_SMILEYS.associate(";)", "clien_d_oeil.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(";-)", "clien_d_oeil.png");
      ResourcesGUI.STANDARD_SMILEYS.associate("8)", "cool.png");
      ResourcesGUI.STANDARD_SMILEYS.associate("8-)", "cool.png");
      ResourcesGUI.STANDARD_SMILEYS.associate("B)", "cool.png");
      ResourcesGUI.STANDARD_SMILEYS.associate("B-)", "cool.png");
      ResourcesGUI.STANDARD_SMILEYS.associate("8)", "cool.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":S", "depite.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":-S", "depite.png");
      ResourcesGUI.STANDARD_SMILEYS.associate("<:-)", "fou.png");
      ResourcesGUI.STANDARD_SMILEYS.associate("<:)", "fou.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":-E", "frappe.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":E", "frappe.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":-K", "malade.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":K", "malade.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":-k", "malade.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":k", "malade.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":-|", "neutre.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":|", "neutre.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":,-(", "pleure.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":,(", "pleure.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":'-(", "pleure.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":'(", "pleure.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":-X", "silence.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":-x", "silence.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":X", "silence.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":x", "silence.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":-)", "sourire.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":)", "sourire.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":-O", "surpris.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":-o", "surpris.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":O", "surpris.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":o", "surpris.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":-(", "triste.png");
      ResourcesGUI.STANDARD_SMILEYS.associate(":(", "triste.png");
   }

   /**
    * Create a cursor
    * 
    * @param baseNameCursor
    *           Base name cursor
    * @return Created cursor
    */
   private static Cursor createCursor(final String baseNameCursor)
   {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append(baseNameCursor);
      int x = 0;
      int y = 0;

      if(ResourcesGUI.IS_CURSOR_SIZE_32x32 == true)
      {
         stringBuffer.append(ResourcesGUI.SIZE_32x32);
         x = 16;
         y = 16;
      }
      else
      {
         stringBuffer.append(ResourcesGUI.SIZE_16x16);
         x = 8;
         y = 8;
      }

      try
      {
         return Toolkit.getDefaultToolkit().createCustomCursor(ResourcesGUI.RESOURCES.obtainBufferedImage(stringBuffer.toString()), new Point(x, y), baseNameCursor);
      }
      catch(final IOException e)
      {
         stringBuffer = null;
         Debug.printException(e);
         return null;
      }
   }
}