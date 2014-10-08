/**
 * VisualAreaNode.java
 *
 * Created on 28.6.2006, 15:13:48 by burgetr
 */
package org.fit.layout.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**
 * A node in the area tree. Applications are expected to create subclases with a particular purpose
 *  (e.g. for segmentation)
 * 
 * @author burgetr
 */
public class AreaNode extends TreeNode<Area>
{
    private static final long serialVersionUID = 3342686210987580546L;
    
    public AreaNode(Area obj)
    {
        super(obj);
    }

    //====================================================================================

    /**
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
     * @return the parent area node of this area in the tree
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
     * @return a vector of the child areas 
     */
    @SuppressWarnings("unchecked")
    public Vector<AreaNode> getChildAreas()
    {
        return this.children;
    }
    
    //====================================================================================
    
    public int getX()
    {
        return getArea().getX1();
    }
    
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
    

}
