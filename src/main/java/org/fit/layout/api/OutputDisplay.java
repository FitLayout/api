/**
 * OutputDisplay.java
 *
 * Created on 31. 10. 2014, 10:28:25 by burgetr
 */
package org.fit.layout.api;

import java.util.Set;

import org.fit.layout.model.Area;
import org.fit.layout.model.Box;
import org.fit.layout.model.Tag;

/**
 * An abstraction of the graphical display of the segmentation output. It allows drawing
 * the area bounds and distinguishing the area types by colors.
 * 
 * @author burgetr
 */
public interface OutputDisplay
{

    /**
     * Draws the box bounds at the output display.
     * @param box The box to be displayed
     */
    public void drawExtent(Box box);
    
    /**
     * Draws the area bounds at the output display.
     * @param area The area to be displayed
     */
    public void drawExtent(Area area);
    
    /**
     * Draws the colorized are bounds. The color is defined by the tags. Multiple colors should
     * be used when there are multiple tags (e.g. splitting the area bounds to several parts).
     * The output display should assign different colors to different tags; the exact implementation
     * of the color mapping depends on the OutputDisplay implementation. The area is not displayed
     * when the tag set is empty.
     * @param area The area to be displayed
     * @param s A set of tags used for generating the area colors
     */
    public void colorizeByTags(Area area, Set<Tag> s);
    
    /**
     * Draws the colorized are bounds. The color is defined by a string. The output display
     * should assign different colors to different strings; the exact implementation
     * of the color mapping depends on the OutputDisplay implementation. The area is not displayed
     * when class name is empty.
     * @param area  The area to be displayed
     * @param s The class name used for generating the colors.
     */
    public void colorizeByClass(Area area, String cname);

    /**
     * Draws the layout used for positioning the subareas (e.g. the grid)
     * @param area The area for which the layout should be displayed.
     */
    public void drawLayout(Area area);
    
}
