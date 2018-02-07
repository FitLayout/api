/**
 * GridTopology.java
 *
 * Created on 12. 11. 2014, 10:33:00 by burgetr
 */
package org.fit.layout.impl;

import org.fit.layout.model.Area;
import org.fit.layout.model.Rectangular;

/**
 * The default topology that creates a grid of child areas within a parent area.
 * 
 * @author burgetr
 */
public class DefaultGridTopology extends AreaListGridTopology
{
    private Area area;
    
    public DefaultGridTopology(Area area)
    {
        super(area.getChildAreas());
        this.area = area;
    }

    public Area getArea()
    {
        return area;
    }

    @Override
    protected Rectangular computeAreaBounds()
    {
        return area.getBounds();
    }
    

}
