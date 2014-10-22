/**
 * Area.java
 *
 * Created on 17. 10. 2014, 11:33:36 by burgetr
 */
package org.fit.layout.model;

import java.util.List;

/**
 * An area containing several visual boxes.
 * 
 * @author burgetr
 */
public interface Area extends ContentRect
{
    
    /**
     * Obtains the parent area of this area.
     * @return The parent area or {@code null} when this is the root area.
     */
    public Area getParentArea();
    
    /**
     * Obtains the n-th child area.
     * @param index the child index
     * @return the child area at the given index
     */
    public Area getChildAreaAt(int index)
        throws ArrayIndexOutOfBoundsException;
    
    /**
     * Returns the number of child areas for this area.
     * @return the number of child areas
     */
    public int getChildCount();
    
    /**
     * Returns the position of this area in the parent area grid.
     * @return The grid position.
     */
    public Rectangular getGridBounds();
    
    /**
     * Returns the list of boxes that belong directly to this area.
     * @return the list of boxes (possibly empty)
     */
    public List<Box> getBoxes();
    
    /**
     * Obtains the list of tags assigned to this area.
     * @return the list of tags (possibly empty)
     */
    public List<Tag> getTags();

    /**
     * Adds a tag to this area. Nothing is done when the tag is allready assigned to this area.
     * @param tag The tag to be added.
     */
    public void addTag(Tag tag);
    
}
