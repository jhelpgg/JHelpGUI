package jhelp.gui.smooth.resources;

import jhelp.gui.smooth.JHelpConstantsSmooth;
import jhelp.util.gui.JHelpFont;
import jhelp.util.gui.JHelpFont.Type;
import jhelp.util.gui.JHelpFont.Value;
import jhelp.util.gui.JHelpImage;
import jhelp.util.resources.ResourceText;
import jhelp.util.resources.Resources;

/**
 * Helper for access smooth resources
 * 
 * @author JHelp
 */
public class JHelpResourcesSmooth
      implements JHelpConstantsSmooth
{
   /** Smooth text access */
   public static final ResourceText RESOURCE_TEXT;
   /** Smooth resources access */
   public static final Resources    RESOURCES;
   static
   {
      RESOURCES = new Resources(JHelpResourcesSmooth.class);
      RESOURCE_TEXT = JHelpResourcesSmooth.RESOURCES.obtainResourceText("texts/texts");
   }

   /**
    * Obtain a smooth font
    * 
    * @param name
    *           Font relative path
    * @param size
    *           Size
    * @return Resource font
    */
   public static JHelpFont getFont(final String name, final int size)
   {
      return JHelpResourcesSmooth.RESOURCES.obtainJHelpFont(Type.TRUE_TYPE, name, size, Value.FREE, Value.FREE, false);
   }

   /**
    * Obtain an image from resource
    * 
    * @param path
    *           Resource path
    * @return The image
    */
   public static JHelpImage obtainJHelpImage(final String path)
   {
      try
      {
         return JHelpResourcesSmooth.RESOURCES.obtainJHelpImage(path);
      }
      catch(final Exception exception)
      {
         return JHelpImage.DUMMY;
      }
   }
}