/**
 * AreaTopology.java
 *
 * Created on 12. 11. 2014, 9:12:11 by burgetr
 */
package org.fit.layout.model;

import org.fit.layout.api.OutputDisplay;

/**
 * This is an abstraction of a topology that represents the mutual positions
 * of sub-areas in the parent area.
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
     * Obtains the width the area occupies.
     * @return the width of the area
     */
    public int getWidth();
    
    /**
     * Obtains the height the area occupies.
     * @return the height of the area
     */
    public int getHeight();
    
    /**
     * Obtains the position of the area in its parent area.
     * @return the position of the area
     */
    public Rectangular getPosition();
    
    public Area getPreviousOnLine();
    
    public Area getNextOnLine();
    
    public int getMinIndent();
    
    public int getMaxIndent();
    
    public void drawLayout(OutputDisplay disp);
    
}
