/**
 * GenericTreeNode.java
 *
 * Created on 19. 11. 2014, 21:46:53 by burgetr
 */
package org.fit.layout.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fit.layout.model.GenericTreeNode;

/**
 * A generic tree node used as a base for the Box and Area implementation.
 * TODO override
 * @author burgetr
 */
public class DefaultTreeNode<T extends GenericTreeNode<T>> implements GenericTreeNode<T>
{
    private T myself;
    private T root;
    private T parent;
    private List<T> children;
    private HashMap<Class<?>, Object> attributes;

    public DefaultTreeNode(Class<T> myType)
    {
        children = new ArrayList<>();
        parent = null;
        myself = myType.cast(this);
        root = myself;
        attributes = new HashMap<Class<?>, Object>();
    }

    public T getParent()
    {
        return parent;
    }

    public void setParent(T parent)
    {
        this.parent = parent;
    }
    
    public T getRoot()
    {
        return root;
    }

    public void setRoot(T root)
    {
        this.root = root;
    }

    public boolean isRoot()
    {
        return (getRoot() == this);
    }
    
    public <P> P getUserAttribute(Class<P> clazz)
    {
        return clazz.cast(attributes.get(clazz));
    }

    public void addUserAttribute(Object attribute)
    {
        attributes.put(attribute.getClass(), attribute);
    }

    public List<T> getChildren()
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

    public void appendChild(T child)
    {
        if (child.getParent() != null)
            child.getParent().remove(child);
        child.setParent(myself);
        children.add(child);
    }

    @Override
    public void appendChildren(List<T> list)
    {
        for (T child : list)
            appendChild(child);
    }
    
    public void insertChild(T child, int index)
            throws IndexOutOfBoundsException
    {
        if (child.getParent() != null)
            child.getParent().remove(child);
        child.setParent(myself);
        children.add(index, child);
    }

    public void removeAllChildren()
    {
        for (T child : children)
        {
            child.setParent(null);
            child.setRoot(child);
        }
        children.clear();
    }

    public void remove(int index) throws IndexOutOfBoundsException
    {
        T child = children.get(index); 
        child.setParent(null);
        child.setRoot(child);
        children.remove(index);
    }

    public void remove(T child) throws IllegalArgumentException
    {
        if (children.remove(child))
        {
            child.setParent(null);
            child.setRoot(child);
        }
        else
            throw new IllegalArgumentException("Given node is not a child of this node");
    }

    public T getChildAt(int index)
            throws IndexOutOfBoundsException
    {
        return children.get(index);
    }
    
    public int getIndex(T child)
    {
    	if (child != null)
    		return children.indexOf(child);
    	else
    		throw new IllegalArgumentException("The child cannot be null");
    }
    
    public T getPreviousSibling()
    {
    	if (getParent() != null)
    	{
    		int index = getParent().getIndex(myself);
    		if (index == 0)
    			return null;
    		else
    			return getParent().getChildAt(index - 1);
    	}
    	else
    		return null;
    }

    public T getNextSibling()
    {
    	if (getParent() != null)
    	{
    		int index = getParent().getIndex(myself);
    		if (index == getParent().getChildCount() - 1)
    			return null;
    		else
    			return getParent().getChildAt(index + 1);
    	}
    	else
    		return null;
    }

    public int getDepth()
    {
    	return recursiveGetDepth(myself);
    }
    
    private int recursiveGetDepth(T root)
    {
    	if (root.isLeaf())
    		return 0;
    	else
    	{
    		int max = 0;
    		for (T child : root.getChildren())
    		{
    			int cdepth = recursiveGetDepth(child);
    			if (cdepth > max)
    				max = cdepth;
    		}
    		return max + 1;
    	}
    }
    
    public int getLeafCount()
    {
        return recursiveGetLeafCount(myself);
    }
    
    private int recursiveGetLeafCount(T root)
    {
        if (root.isLeaf())
            return 1;
        else
        {
            int sum = 0;
            for (T child : root.getChildren())
                sum += recursiveGetLeafCount(child);
            return sum;
        }
    }
    
    
}
