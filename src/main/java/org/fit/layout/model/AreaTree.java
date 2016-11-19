/**
 * AreaTree.java
 *
 * Created on 14. 1. 2015, 15:16:42 by burgetr
 */
package org.fit.layout.model;

import java.util.List;

/**
 * A tree of visual areas created from a box tree.
 * 
 * @author burgetr
 */
public interface AreaTree extends SearchableAreaContainer
{

    /**
     * Obtains the source page for this area tree.
     * @return The source page.
     */
    public Page getPage();
    
    /**
     * Obtains the root node of the area tree.
     * 
     * @return the root node of the tree of areas
     */
    public Area getRoot();

    /**
     * Updates the topology structures (e.g. grids) for all the areas in the tree.
     */
    public void updateTopologies();
 
    /*
     * Factory functions
     */
    
    public Area createArea(Rectangular r);
    
    public Area createArea(Box box);
    
    public Area createArea(List<Box> boxes);
    
}
