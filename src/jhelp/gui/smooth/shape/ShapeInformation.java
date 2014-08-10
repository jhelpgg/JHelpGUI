package jhelp.gui.smooth.shape;

import java.awt.Rectangle;
import java.awt.Shape;

/**
 * Describe a shape
 * 
 * @author JHelp
 */
public class ShapeInformation
{
   /** Background and shadow shape */
   public final Shape     backroundShape;
   /** Area where draw component content */
   public final Rectangle insideArea;

   /**
    * Create a new instance of ShapeInformation
    * 
    * @param backroundShape
    *           Background and shadow shape
    * @param insideArea
    *           Area where draw component content
    */
   public ShapeInformation(final Shape backroundShape, final Rectangle insideArea)
   {
      this.backroundShape = backroundShape;
      this.insideArea = insideArea;
   }
}