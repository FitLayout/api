/**
 * AreaTopology.java
 *
 * Created on 12. 11. 2014, 9:12:11 by burgetr
 */
package org.fit.layout.model;

import java.util.List;

import org.fit.layout.api.OutputDisplay;

/**
 * This is an abstraction of a topology that represents the mutual positions
 * of areas in an abstract space (e.g. in a parent area).
 * 
 * @author burgetr
 */
public interface AreaTopology
{
    
    /**
     * Gets the list of areas that are being managed by this topology.
     * @return the list of areas
     */
    public List<Area> getAreas();
    
    /**
     * Obtains the total width of the topology used for placing the child
     * areas within this area.
     * @return the topology width; the units depend on the used topology
     */
    public int getTopologyWidth();
    
    /**
     * Obtains the total height of the topology used for placing the child
     * areas within this area.
     * @return the topology height; the units depend on the topology
     */
    public int getTopologyHeight();
    
    /**
     * Obtains the absolute position of the whole topology within the page. 
     * @return the absolute position of this topology
     */
    public Rectangular getTopologyPosition();
    
    /**
     * Obtains the position of the given area within this topology.
     * @param area The area whose position we want to obtain.
     * @return The area position in this topology or {@code null} when the area position is not described by this topology.
     */
    public Rectangular getPosition(Area area);
    
    /**
     * Sets the position of the given area in this topology.
     * @param area The area whose position we want to set.
     * @param gp The new position.
     */
    public void setPosition(Area area, Rectangular gp);
    
    /**
     * Finds an area at the specified position in the grid.
     * @param x the x coordinate of the grid cell  
     * @param y the y coordinate of the grid cell  
     * @return the node at the specified position or {@code null} when there is no such area
     */
    public Area findAreaAt(int x, int y);
    
    /**
     * Translates the bounds in the topology to pixel bounds.
     * @param topologyPosition the position within the topology 
     * @return the pixle position
     */
    public Rectangular toPixelPosition(Rectangular topologyPosition);
    
    /**
     * Translates the bounds in the topology to absolute pixel bounds.
     * @param topologyPosition the position within the topology 
     * @return the pixle position
     */
    public Rectangular toPixelPositionAbsolute(Rectangular topologyPosition);
    
    /**
     * Translates the X coordinate from pixels to topology position
     * @param pixelX the pixel X coordinate
     * @return the topology X coordinate
     */
    public int toTopologyX(int pixelX);
    
    /**
     * Translates the Y coordinate from pixels to topology position
     * @param pixelX the pixel Y coordinate
     * @return the topology Y coordinate
     */
    public int toTopologyY(int pixelY);
    
     /**
     * Recomputes the topology. This should be used when the underlying areas
     * have changed (some areas have been added, removed or resized).
     */
    public void update();
    
    /**
     * Graphically displays the topology on a graphical output device.
     * @param disp the ouptut display to be used
     */
    public void drawLayout(OutputDisplay disp);
    
}
