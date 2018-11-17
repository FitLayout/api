/**
 * 
 */
package org.fit.layout.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
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
        this(areas, true);
    }
    
    public AreaListGridTopology(List<Area> areas, boolean doInit)
    {
        this.areas = areas;
        //create the grid if required
        if (doInit)
            update();
    }

    @Override
    public List<Area> getAreas()
    {
        return areas;
    }

    public void setAreas(List<Area> areas)
    {
        this.areas = areas;
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
    public Collection<Area> findAllAreasAt(int x, int y)
    {
        Collection<Area> ret = new ArrayList<>();
        //TODO some indexing?
        for (Map.Entry<Area, Rectangular> entry : positions.entrySet())
        {
            if (entry.getValue().contains(x, y))
                ret.add(entry.getKey());
        }
        return ret;
    }

    @Override
    public Collection<Area> findAllAreasIntersecting(Rectangular r)
    {
        Collection<Area> ret = new ArrayList<>();
        for (Map.Entry<Area, Rectangular> entry : positions.entrySet())
        {
            if (entry.getValue().intersects(r))
                ret.add(entry.getKey());
        }
        return ret;
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
    public Rectangular toPixelPositionAbsolute(Rectangular gp)
    {
        Rectangular ret = new Rectangular(grid.getColOfs(gp.getX1()),
                grid.getRowOfs(gp.getY1()),
                grid.getColOfs(gp.getX2()+1) - 1,
                grid.getRowOfs(gp.getY2()+1) - 1);
        ret.move(grid.getAbsolutePosition().getX1(), grid.getAbsolutePosition().getY1());
        return ret;
    }
    
    @Override
    public int toTopologyX(int pixelX)
    {
        return grid.findCellX(pixelX);
    }

    @Override
    public int toTopologyY(int pixelY)
    {
        return grid.findCellY(pixelY);
    }

    @Override
    public void update()
    {
        abspos = computeAreaBounds();
        //default positions for all the areas
        positions = new HashMap<>(areas.size());
        for (Area a : areas)
            positions.put(a, new Rectangular());
        //re-create the grid
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
