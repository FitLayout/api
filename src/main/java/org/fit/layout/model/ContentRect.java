/**
 * ContentRect.java
 *
 * Created on 17. 10. 2014, 14:00:16 by burgetr
 */
package org.fit.layout.model;

import java.awt.Color;

/**
 * A generic rectangular content within a page
 * 
 * @author burgetr
 */
public interface ContentRect extends Rect
{

    /**
     * Obtains the page this block belongs to.
     * @return the page
     */
    public Page getPage();
    
    /**
     * Obtains the pixel position within in the page.
     * @return The rectangular pixel position. 
     */
    public Rectangular getBounds();
    
    /**
     * Obtains the background color of the area. 
     * @return A color or {@code null} for transparent background
     */
    public Color getBackgroundColor();

    /**
     * Obtains the amount of underlined text. 0 means no underlined text, 1 means all the text is underlined.
     * @return a value in the range 0..1
     */
    public float getUnderline();
    
    /**
     * Obtains the amount of line-through text. 0 means no underlined text, 1 means all the text is underlined.
     * @return a value in the range 0..1
     */
    public float getLineThrough();
    
    /**
     * Obtains an average font size in the are in pixels.
     * @return the average font pixel size
     */
    public float getFontSize();
    
    /**
     * Obtains the average font style. 0 means no text in italics, 1 means all the text in italics.
     * @return a value in the range 0..1
     */
    public float getFontStyle();
    
    /**
     * Obtains the average font style. 0 means no text is bold, 1 means all the text is bold
     * @return a value in the range 0..1
     */
    public float getFontWeight();
    
    
    //=================================================================================================
    // Background
    //=================================================================================================
    
    /**
     * Checks whether the box is separated by background, i.e. its background color is not transparent
     * and it is different from the ancestor background.
     * @return {@code true} for background-separated boxes
     */
    public boolean isBackgroundSeparated();

    
}
