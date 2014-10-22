/**
 * Box.java
 *
 * Created on 17. 10. 2014, 11:28:11 by burgetr
 */
package org.fit.layout.model;

import java.awt.Color;

/**
 * This class represents a box in the rendered page tree. It may contain a text or a content
 * object (e.g. an image).
 * 
 * @author burgetr
 */
public interface Box extends ContentRect
{

    /**
     * Obtains the parent box of this box.
     * @return The parent box or {@code null} when this is the root box.
     */
    public Box getParentBox();
    
    /**
     * Obtains the n-th child box.
     * @param index the child index
     * @return the child box at the given index
     */
    public Box getChildBoxAt(int index)
        throws ArrayIndexOutOfBoundsException;
    
    /**
     * Returns the number of child boxes for this box.
     * @return the number of child boxes
     */
    public int getChildCount();
    
    /**
     * Obtains the box text color.
     * @return the text color
     */
    public Color getColor();

    /**
     * Obtains the font family name used by the box.
     * @return The font family name.
     */
    public String getFontFamily();
    
    /**
     * Obtains the complete text contained in this box. 
     * @return a text string (possibly empty)
     */
    public String getText();
    
    /**
     * Obtains the content object contained in this box.
     * @return the content object or {@code null} when there is no object contained.
     */
    public ContentObject getContentObject();
    
}
