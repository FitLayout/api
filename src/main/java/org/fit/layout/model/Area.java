/**
 * Area.java
 *
 * Created on 17. 10. 2014, 11:33:36 by burgetr
 */
package org.fit.layout.model;

import java.awt.Color;
import java.util.Map;
import java.util.Vector;

/**
 * An area containing several visual boxes.
 * 
 * @author burgetr
 */
public interface Area extends ContentRect, AreaTreeNode<Area>
{
    
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
     * Adds a tag to this area. If the tag is already assigned to the area, the greater of the
     * original and new support will be used.
     * @param tag The tag to be added.
     * @param probability The assigned tag support from 0.0 to 1.0
     */
    public void addTag(Tag tag, float support);
    
    /**
     * Removes the given tag from the area.
     * @param tag the tag to be removed
     */
    public void removeTag(Tag tag);
    
    /**
     * Tests whether the area has this tag.
     * @param tag the tag to be tested.
     * @return <code>true</code> if the area has this tag
     */
    public boolean hasTag(Tag tag);
    
    /**
     * Tests whether the area has this tag with a support greater or equal to the specified value.
     * @param tag the tag to be tested
     * @param minSupport minimal required support
     * @return <code>true</code> if the area has this tag
     */
    public boolean hasTag(Tag tag, float minSupport);
    
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
