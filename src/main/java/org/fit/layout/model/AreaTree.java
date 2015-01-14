/**
 * AreaTree.java
 *
 * Created on 14. 1. 2015, 15:16:42 by burgetr
 */
package org.fit.layout.model;

/**
 * A tree of visual areas created from a box tree.
 * 
 * @author burgetr
 */
public interface AreaTree extends SearchableAreaContainer
{
    
    /**
     * Obtains the root node of the area tree.
     * 
     * @return the root node of the tree of areas
     */
    public Area getRoot();

}
