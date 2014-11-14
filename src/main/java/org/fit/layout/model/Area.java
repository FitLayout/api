/**
 * Area.java
 *
 * Created on 17. 10. 2014, 11:33:36 by burgetr
 */
package org.fit.layout.model;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
     * Obtains the previous siblibg of this area.
     * @return The previous siblibg area or {@code null} when this is the first child.
     */
    public Area getPreviousSibling();
    
    /**
     * Obtains the next siblibg of this area.
     * @return The next siblibg area or {@code null} when this is the last child.
     */
    public Area getNextSibling();
    
    /**
     * Obtains the n-th child area.
     * @param index the child index
     * @return the child area at the given index
     */
    public Area getChildArea(int index)
        throws ArrayIndexOutOfBoundsException;
    
    /**
     * Returns the number of child areas for this area.
     * @return the number of child areas
     */
    public int getChildCount();
    
    /**
     * Returns the list of all the child areas.
     * @return a list containing all the child areas.
     */
    public List<Area> getChildren();
    
    /**
     * Appends a new child area to the list of child areas of this area.
     * @param child The new child to be appended
     */
    public void appendChild(Area child);
    
    /**
     * Removes a child area from its parent.
     * @param child The new child to be appended
     */
    public void removeChild(Area child);
    
    /**
     * Checks whether this area is a leaf area
     * @return {@code true} when the area is a leaf area (it has no children)
     */
    public boolean isLeaf();
    
    /**
     * Obtains the depth of the tree rooted at this area.
     * @return 0 for leaf areas, more than 0 for other areas
     */
    public int getDepth();
    
    /**
     * Returns the list of boxes that belong directly to this area.
     * @return the list of boxes (possibly empty)
     */
    public Vector<Box> getBoxes();
    
    /** 
     * Obtains all the boxes from this area and all the child areas.
     * @return The list of boxes
     */
    public Vector<Box> getAllBoxes();
    
    /**
     * Returns the complete text contained in this area and its sub area.
     * @return A text string (possibly empty)
     */
    public String getText();
    
    /**
     * Checks whether this area is formed by replaced boxes.
     * @return {@code true} if the area contains replaced boxes only
     */
    public boolean isReplaced();
    
    /**
     * Returns the topology of this area. 
     * @return The area topology.
     */
    public AreaTopology getTopology();
    
    /**
     * Obtains the effective background color visible under the area.
     * @return The background color.
     */
    public Color getEffectiveBackgroundColor();
    
    
    //====================================================================================
    // tagging
    //====================================================================================
    
    /**
     * Obtains the list of tags assigned to this area and their support.
     * @return the map of tags and their support (possibly empty)
     */
    public Map<Tag, Float> getTags();

    /**
     * Adds a tag to this area. Nothing is done when the tag is allready assigned to this area.
     * @param tag The tag to be added.
     * @param probability The assigned tag support from 0.0 to 1.0
     */
    public void addTag(Tag tag, float support);
    
    /**
     * Tests whether the area has this tag.
     * @param tag the tag to be tested.
     * @return <code>true</code> if the area has this tag
     */
    public boolean hasTag(Tag tag);
    
    /**
     * Obtains the support of the given tag assignment
     * @param tag The tag to be tested
     * @return The support of the given tag in the range 0.0 to 1.0. Returns 0.0 when the tag is not assigned
     * to this area.
     */
    public float getTagSupport(Tag tag);
    
    /**
     * Obtains the tag with the greatest support that is assigned to this area.
     * @return The tag with the greatest support or {@code null} if there are no tags assigned to this area
     */
    public Tag getMostSupportedTag();
    
}
