/**
 * AreaTreeNode.java
 *
 * Created on 19. 3. 2015, 13:00:23 by burgetr
 */
package org.fit.layout.model;

import java.util.List;

/**
 * A typed node in the tree of areas.
 * 
 * @author burgetr
 */
public interface AreaTreeNode<T>
{
    /**
     * Obtains the parent area of this area.
     * @return The parent area or {@code null} when this is the root area.
     */
    public T getParentArea();
    
    /**
     * Obtains the previous siblibg of this area.
     * @return The previous siblibg area or {@code null} when this is the first child.
     */
    public T getPreviousSibling();
    
    /**
     * Obtains the next siblibg of this area.
     * @return The next siblibg area or {@code null} when this is the last child.
     */
    public T getNextSibling();
    
    /**
     * Obtains the n-th child area.
     * @param index the child index
     * @return the child area at the given index
     */
    public T getChildArea(int index)
        throws ArrayIndexOutOfBoundsException;
    
    /**
     * Returns the number of child areas for this area.
     * @return the number of child areas
     */
    public int getChildCount();
    
    /**
     * Returns the list of all the child areas.
     * @return a list containing all the child areas.
     */
    public List<T> getChildAreas();
    
    /**
     * Appends a new child area to the list of child areas of this area.
     * @param child The new child to be appended
     */
    public void appendChild(T child);
    
    /**
     * Inserts a new child at the given position.
     * @param child the child to be inserted
     * @param index the index of the new child
     */
    public void insertChild(T child, int index);
    
    /**
     * Removes a child area from its parent.
     * @param child The new child to be appended
     */
    public void removeChild(T child);
    
    /**
     * Returns the index of the specified child in this node's child array. 
     * If the specified node is not a child of this node, returns -1.
     * @param child the child node to search
     * @return the child index or -1 if not a child
     */
    public int getIndex(T child);
    
    /**
     * Checks whether this area is a leaf area
     * @return {@code true} when the area is a leaf area (it has no children)
     */
    public boolean isLeaf();
    
    /**
     * Obtains the depth of the tree rooted at this area.
     * @return 0 for leaf areas, more than 0 for other areas
     */
    public int getDepth();
    

}
