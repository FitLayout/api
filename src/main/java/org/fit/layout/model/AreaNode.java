/**
 * VisualAreaNode.java
 *
 * Created on 28.6.2006, 15:13:48 by burgetr
 */
package org.fit.layout.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 * A node in the area tree that can be placed in a grid.
 * 
 * @author burgetr
 */
public class AreaNode extends TreeNode<Area>
{
    private static final long serialVersionUID = 3342686210987580546L;
    
    /** Position in the grid */
    protected Rectangular gp;
    
    /** A grid of inserted elements */
    protected AreaGrid grid;
    
    /** Assigned tags */
    protected Set<Tag> tags;
    
    
    //====================================================================================
    
    public AreaNode(Area area)
    {
        super(area);
        grid = null;
        gp = new Rectangular();
        tags = new HashSet<Tag>();
    }

    //====================================================================================

    /**
     * Obtains the contained area.
     * @return the contained area
     */
    public Area getArea()
    {
        return getUserObject();
    }
    
    /**
     * Adds a new area as a subarea. Updates the area bounds if necessary.
     * @param area the area node to be added
     */
    public void addArea(AreaNode sub)
    {
        add(sub);
        getArea().getBounds().expandToEnclose(sub.getArea().getBounds());
        getArea().updateAverages(sub.getArea());
    }
    
    /**
     * Adds all the areas in the vector as the subareas. It the areas
     * have already had another parent, they are removed from the old
     * parent.
     * @param areas the collection of subareas
     */
    public void addAll(Collection<AreaNode> areas)
    {
        for (Iterator<AreaNode> it = areas.iterator(); it.hasNext(); )
            addArea(it.next());
    }
    
    /**
     * Obtains the parent are of this node in the tree.
     * @return the parent area node or {@code null} for root areas.
     */
    public AreaNode getParentArea()
    {
        return (AreaNode) getParent();
    }

    /**
     * Returns a specified child area node.
     * @param index the child index
     * @return the specified child box
     */
    public AreaNode getChildArea(int index)
    {
        return (AreaNode) getChildAt(index);
    }

    /**
     * Obtains all the child areas.
     * @return a vector of the child areas 
     */
    @SuppressWarnings("unchecked")
    public Vector<AreaNode> getChildAreas()
    {
        return this.children;
    }
    
    //====================================================================================
    // absolute coordinates
    //====================================================================================
    
    //TODO unify name to getX1()
    public int getX()
    {
        return getArea().getX1();
    }
    
    //TODO unify name to getY1()
    public int getY()
    {
        return getArea().getY1();
    }
    
    public int getX2()
    {
        return getArea().getX2();
    }
    
    public int getY2()
    {
        return getArea().getY2();
    }
    
    public int getWidth()
    {
        return getArea().getWidth();
    }
    
    public int getHeight()
    {
        return getArea().getHeight();
    }
    
    //====================================================================================
    // grid operations
    //====================================================================================
    
    /**
     * Creates the grid of areas from the child areas.
     */
    public void createGrid()
    {
        grid = new AreaGrid(this);
    }
    
    /**
     * Obtains the gird of contained areas.
     * @return the grid
     */
    public AreaGrid getGrid()
    {
        return grid;
    }
    
    /**
     * @return Returns the height of the area in the grid height in rows
     */
    public int getGridHeight()
    {
        return gp.getHeight();
    }

    /**
     * @return Returns the width of the area in the grid in rows
     */
    public int getGridWidth()
    {
        return gp.getWidth();
    }

    /**
     * @return Returns the gridX.
     */
    public int getGridX()
    {
        return gp.getX1();
    }

    /**
     * @param gridX The gridX to set.
     */
    public void setGridX(int gridX)
    {
        gp.setX1(gridX);
    }

    /**
     * @return Returns the gridY.
     */
    public int getGridY()
    {
        return gp.getY1();
    }

    /**
     * @param gridY The gridY to set.
     */
    public void setGridY(int gridY)
    {
        gp.setY1(gridY);
    }
    
    /**
     * @return the position of this area in the grid of its parent area
     */
    public Rectangular getGridPosition()
    {
        return gp;
    }
    
    /**
     * Sets the position in the parent area grid for this area
     * @param pos the position
     */
    public void setGridPosition(Rectangular pos)
    {
        gp = new Rectangular(pos);
    }
    
    /**
     * Returns the child area at the specified grid position or null, if there is no
     * child area at this position.
     */
    public AreaNode getChildAtGridPos(int x, int y)
    {
        for (int i = 0; i < getChildCount(); i++)
        {
            AreaNode child = getChildArea(i);
            if (child.getGridPosition().contains(x, y))
                return child;
        }
        return null;
    }
    
    /**
     * Returns the child areas whose absolute coordinates intersect with the specified rectangle.
     */
    public Vector<AreaNode> getChildNodesInside(Rectangular r)
    {
        Vector<AreaNode> ret = new Vector<AreaNode>();
        for (int i = 0; i < getChildCount(); i++)
        {
            AreaNode child = getChildArea(i);
            if (child.getArea().getBounds().intersects(r))
                ret.add(child);
        }
        return ret;
    }
    
    /**
     * Check if there are some children in the given subarea of the area.
     */
    public boolean isAreaEmpty(Rectangular r)
    {
        for (int i = 0; i < getChildCount(); i++)
        {
            AreaNode child = getChildArea(i);
            if (child.getArea().getBounds().intersects(r))
                return false;
        }
        return true;
    }

    //====================================================================================
    // tagging
    //====================================================================================
    
    /**
     * Adds a tag to this area.
     * @param tag the tag to be added.
     */
    public void addTag(Tag tag)
    {
        tags.add(tag);
    }
    
    /**
     * Tests whether the area has this tag.
     * @param tag the tag to be tested.
     * @return <code>true</code> if the area has this tag
     */
    public boolean hasTag(Tag tag)
    {
        return tags.contains(tag);
    }
    
    /**
     * Removes all tags that belong to the given collection.
     * @param c A collection of tags to be removed.
     */
    public void removeAllTags(Collection<Tag> c)
    {
        tags.removeAll(c);
    }
    
    /**
     * Tests whether the area or any of its <b>direct child</b> areas have the given tag.
     * @param tag the tag to be tested.
     * @return <code>true</code> if the area or its direct child areas have the given tag
     */
    public boolean containsTag(Tag tag)
    {
        if (hasTag(tag))
            return true;
        else
        {
            for (int i = 0; i < getChildCount(); i++)
                if (getChildArea(i).hasTag(tag))
                    return true;
            return false;
        }
    }
    
    /**
     * Obtains the set of tags assigned to the area.
     * @return a set of tags
     */
    public Set<Tag> getTags()
    {
        return tags;
    }
    
    /**
     * Obtains all the tags assigned to this area and its child areas (not all descendant areas).
     * @return a set of tags
     */
    public Set<Tag> getAllTags()
    {
        Set<Tag> ret = new HashSet<Tag>(tags);
        for (int i = 0; i < getChildCount(); i++)
            ret.addAll(getChildArea(i).getTags());
        return ret;
    }

}
