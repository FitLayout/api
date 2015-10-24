/**
 * Area.java
 *
 * Created on 17. 10. 2014, 11:33:36 by burgetr
 */
package org.fit.layout.model;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

/**
 * An area containing several visual boxes.
 * 
 * @author burgetr
 */
public interface Area extends ContentRect, AreaTreeNode<Area>, Taggable
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
     * Returns the complete text contained in this area and its sub area. The individual
     * areas are separated by the given string separator.
     * @param separator the string separating the individual areas
     * @return A text string (possibly empty)
     */
    public String getText(String separator);
    
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

    /**
     * Checks whether the area can be interpreted as a horizontal separator.
     * @return {@code true} when this area is a horizontal separator
     */
    public boolean isHorizontalSeparator();

    /**
     * Checks whether the area can be interpreted as a vertical separator.
     * @return {@code true} when this area is a vertical separator
     */
    public boolean isVerticalSeparator();
    
    /**
     * Checks whether the area can be interpreted as any kind of separator.
     * @return {@code true} when this area is a separator
     */
    public boolean isSeparator();
    
    /**
     * Creates a new subarea from a specified region of the area and moves the selected child
     * nodes to the new area.
     * @param gp the subarea bounds
     * @param selected nodes to be moved to the new area
     * @param name the name (identification) of the new area
     * @return the new AreaNode created in the tree or null, if nothing was created
     */ 
    public Area createSuperArea(Rectangular gp, List<Area> selected, String name);

    /**
     * Creates a copy of the area and makes it a next sibling of the source area.
     * @return the new area
     */
    public Area copy();
    
}
