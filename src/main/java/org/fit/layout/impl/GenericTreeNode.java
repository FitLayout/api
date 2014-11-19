/**
 * GenericTreeNode.java
 *
 * Created on 19. 11. 2014, 21:46:53 by burgetr
 */
package org.fit.layout.impl;

import java.util.List;
import java.util.Vector;

/**
 * A generic tree node used as a base for the Box and Area implementation.
 * 
 * @author burgetr
 */
public class GenericTreeNode<T>
{
    private GenericTreeNode<T> root;
    private GenericTreeNode<T> parent;
    private Vector<GenericTreeNode<T>> children;
    private T userObject;

    public GenericTreeNode()
    {
        children = new Vector<GenericTreeNode<T>>();
        parent = null;
        root = this;
    }

    public GenericTreeNode(T data)
    {
        this();
        setUserObject(data);
    }

    public GenericTreeNode<T> getParent()
    {
        return parent;
    }

    public GenericTreeNode<T> getRoot()
    {
        return root;
    }

    public boolean isRoot()
    {
        return (root == this);
    }
    
    public T getUserObject()
    {
        return userObject;
    }

    public void setUserObject(T data)
    {
        userObject = data;
    }

    public List<GenericTreeNode<T>> getChildren()
    {
        return children;
    }

    public int getChildCount()
    {
        return getChildren().size();
    }

    public boolean isLeaf()
    {
        return (getChildCount() == 0);
    }

    public void add(GenericTreeNode<T> child)
    {
        if (child.parent != null)
            child.parent.remove(child);
        child.parent = this;
        children.add(child);
    }

    public void insert(GenericTreeNode<T> child, int index)
            throws IndexOutOfBoundsException
    {
        if (child.parent != null)
            child.parent.remove(child);
        child.parent = this;
        children.add(index, child);
    }

    public void removeAllChildren()
    {
        for (GenericTreeNode<T> child : children)
        {
            child.parent = null;
            child.root = child;
        }
        children.removeAllElements();
    }

    public void remove(int index) throws IndexOutOfBoundsException
    {
        GenericTreeNode<T> child = children.elementAt(index); 
        child.parent = null;
        child.root = child;
        children.remove(index);
    }

    public void remove(GenericTreeNode<T> child) throws IllegalArgumentException
    {
        if (children.remove(child))
        {
            child.parent = null;
            child.root = child;
        }
        else
            throw new IllegalArgumentException("Given node is not a child of this node");
    }

    public GenericTreeNode<T> getChildAt(int index)
            throws IndexOutOfBoundsException
    {
        return children.get(index);
    }

    @Override
    public String toString()
    {
        return getUserObject().toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        GenericTreeNode<?> other = (GenericTreeNode<?>) obj;
        if (userObject == null)
        {
            if (other.userObject != null) { return false; }
        }
        else if (!userObject.equals(other.userObject)) { return false; }
        return true;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userObject == null) ? 0 : userObject.hashCode());
        return result;
    }

}
