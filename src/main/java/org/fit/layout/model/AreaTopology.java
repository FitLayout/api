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
    
    public int getWidth();
    
    public int getHeight();
    
    public Rectangular getPosition();
    
    public Area getPreviousOnLine();
    
    public Area getNextOnLine();
    
    public int getMinIndent();
    
    public int getMaxIndent();
    
    public void drawLayout(OutputDisplay disp);
    
}
