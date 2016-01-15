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
     * Appends a list of child areas to the list of child areas of this area.
     * @param list The list of new children to be appended
     */
    public void appendChildren(List<T> list);
    
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
     * Removes all the child areas from their parent.
     */
    public void removeAllChildren();
    
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
     * Check whether this area is an ancestor of another area.
     * @param other the other area
     * @return {@code true} when this area is an ancestor of the other area.
     */
    public boolean isAncestorOf(T other);
    
    /**
     * Obtains the depth of the tree rooted at this area.
     * @return 0 for leaf areas, more than 0 for other areas
     */
    public int getDepth();
    
    /**
     * Returns the total number of leaves that are descendants of this node.
     * If this node is a leaf, returns 1.
     * @return the number of leaves beneath this node
     */
    public int getLeafCount();

    /**
     * Sets a user-defined attribute for the tree node. This allows to assign multiple
     * attributes with different classes. One object of each class is allowed.
     * @param attribute an object representing the user attributes (application-specific)
     */
    public void addUserAttribute(Object attribute);
    
    /**
     * Obtains the user-defined attributes of the node.
     * @param clazz the class of the required attribute
     * @return an object of the given class representing the user attributes (application-specific)
     * or {@code null} when no such attribute is present.
     */
    public <P> P getUserAttribute(Class<P> clazz);

}
