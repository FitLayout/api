/**
 * AreaTopology.java
 *
 * Created on 12. 11. 2014, 9:12:11 by burgetr
 */
package org.fit.layout.model;

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
     * Obtains the position of the given area within this topology.
     * @param area The area whose position we want to obtain.
     * @return The area position in this topology or {@code null} when the area position is not described by this topology.
     */
    public Rectangular getPosition(Area area);
    
    /**
     * Recomputes the topology. This should be used when the underlying areas
     * have changed (some areas have been added, removed or resized).
     */
    public void update();
    
    /**
     * Obtains the width the area occupies.
     * @return the width of the area
     * @deprecated Use {@link #getPosition(Area)}{@code .getWidth()} instead.
     */
    public int getWidth();
    
    /**
     * Obtains the height the area occupies.
     * @return the height of the area
     * @deprecated Use {@link #getPosition(Area)}{@code .getHeight()} instead.
     */
    public int getHeight();
    
    /**
     * Obtains the position of the area in its parent area.
     * @return the position of the area
     * @deprecated Use {@link #getPosition(Area)} instead.
     */
    public Rectangular getPosition();
    
    /**
     * @deprecated
     */
    public Area getPreviousOnLine();
    
    /**
     * @deprecated
     */
    public Area getNextOnLine();
    
    /**
     * @deprecated
     */
    public int getMinIndent();
    
    /**
     * @deprecated
     */
    public int getMaxIndent();
    
    /**
     * Graphically displays the topology on a graphical output device.
     * @param disp the ouptut display to be used
     */
    public void drawLayout(OutputDisplay disp);
    
}
