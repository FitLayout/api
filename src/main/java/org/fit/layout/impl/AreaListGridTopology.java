/**
 * 
 */
package org.fit.layout.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fit.layout.api.OutputDisplay;
import org.fit.layout.impl.AreaGrid;
import org.fit.layout.model.Area;
import org.fit.layout.model.AreaTopology;
import org.fit.layout.model.Rectangular;

/**
 * A grid topology on a generic list of areas.
 * 
 * @author burgetr
 */
public class AreaListGridTopology implements AreaTopology
{
    private List<Area> areas;
    private Rectangular abspos;
    private Map<Area, Rectangular> positions;
    private AreaGrid grid;
    
    public AreaListGridTopology(List<Area> areas)
    {
        this.areas = areas;
        //default positions for all the areas
        positions = new HashMap<>(areas.size());
        for (Area a : areas)
            positions.put(a, new Rectangular());
        //create the grid
        update();
    }

    @Override
    public int getTopologyWidth()
    {
        return grid.getWidth();
    }

    @Override
    public int getTopologyHeight()
    {
        return grid.getHeight();
    }

    @Override
    public Rectangular getTopologyPosition()
    {
        return grid.getAbsolutePosition();
    }

    @Override
    public Rectangular getPosition(Area area)
    {
        return positions.get(area);
    }

    @Override
    public void setPosition(Area area, Rectangular gp)
    {
        positions.put(area, gp);
    }

    @Override
    public Area findAreaAt(int x, int y)
    {
        //TODO some indexing?
        for (Map.Entry<Area, Rectangular> entry : positions.entrySet())
        {
            if (entry.getValue().contains(x, y))
                return entry.getKey();
        }
        return null;
    }

    @Override
    public Rectangular toPixelPosition(Rectangular gp)
    {
        return new Rectangular(grid.getColOfs(gp.getX1()),
                grid.getRowOfs(gp.getY1()),
                grid.getColOfs(gp.getX2()+1) - 1,
                grid.getRowOfs(gp.getY2()+1) - 1);
    }
    
    @Override
    public void update()
    {
        abspos = computeAreaBounds();
        grid = new AreaGrid(abspos, areas, this);
    }

    @Override
    public void drawLayout(OutputDisplay disp)
    {
        Graphics ig = disp.getGraphics();
        Color c = ig.getColor();
        ig.setColor(Color.BLUE);
        int xo = abspos.getX1();
        for (int i = 1; i <= grid.getWidth(); i++)
        {
            xo += grid.getCols()[i-1];
            ig.drawLine(xo, abspos.getY1(), xo, abspos.getY2());
        }
        int yo = abspos.getY1();
        for (int i = 0; i < grid.getHeight(); i++)
        {
            yo += grid.getRows()[i];
            ig.drawLine(abspos.getX1(), yo, abspos.getX2(), yo);
        }
        ig.setColor(c);
    }

    //=================================================================================
    
    /**
     * Obtains the absolute bounds of the parent area where the child areas are positioned.
     * @return The absloute area bounds in pixels.
     */
    protected Rectangular computeAreaBounds()
    {
        Rectangular ret = null;
        for (Area a : areas)
        {
            if (ret == null)
                ret = new Rectangular(a.getBounds());
            else
                ret.expandToEnclose(a.getBounds());
        }
        return ret;
    }
    
}
