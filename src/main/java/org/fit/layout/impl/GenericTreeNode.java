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
public class GenericTreeNode
{
    private GenericTreeNode root;
    private GenericTreeNode parent;
    private Vector<GenericTreeNode> children;

    public GenericTreeNode()
    {
        children = new Vector<GenericTreeNode>();
        parent = null;
        root = this;
    }

    public GenericTreeNode getParent()
    {
        return parent;
    }

    public GenericTreeNode getRoot()
    {
        return root;
    }

    public boolean isRoot()
    {
        return (root == this);
    }
    
    public List<GenericTreeNode> getChildren()
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

    public void add(GenericTreeNode child)
    {
        if (child.parent != null)
            child.parent.remove(child);
        child.parent = this;
        children.add(child);
    }

    public void insert(GenericTreeNode child, int index)
            throws IndexOutOfBoundsException
    {
        if (child.parent != null)
            child.parent.remove(child);
        child.parent = this;
        children.add(index, child);
    }

    public void removeAllChildren()
    {
        for (GenericTreeNode child : children)
        {
            child.parent = null;
            child.root = child;
        }
        children.removeAllElements();
    }

    public void remove(int index) throws IndexOutOfBoundsException
    {
        GenericTreeNode child = children.elementAt(index); 
        child.parent = null;
        child.root = child;
        children.remove(index);
    }

    public void remove(GenericTreeNode child) throws IllegalArgumentException
    {
        if (children.remove(child))
        {
            child.parent = null;
            child.root = child;
        }
        else
            throw new IllegalArgumentException("Given node is not a child of this node");
    }

    public GenericTreeNode getChildAt(int index)
            throws IndexOutOfBoundsException
    {
        return children.get(index);
    }
    
    public int getIndex(GenericTreeNode child)
    {
    	if (child != null)
    		return children.indexOf(child);
    	else
    		throw new IllegalArgumentException("The child cannot be null");
    }
    
    public GenericTreeNode getPreviousSiblingNode()
    {
    	if (getParent() != null)
    	{
    		int index = getParent().getIndex(this);
    		if (index == 0)
    			return null;
    		else
    			return getParent().getChildAt(index - 1);
    	}
    	else
    		return null;
    }

    public GenericTreeNode getNextSiblingNode()
    {
    	if (getParent() != null)
    	{
    		int index = getParent().getIndex(this);
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
    	return recursiveGetDepth(this);
    }
    
    private int recursiveGetDepth(GenericTreeNode root)
    {
    	if (root.isLeaf())
    		return 0;
    	else
    	{
    		int max = 0;
    		for (GenericTreeNode child : root.getChildren())
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
        return recursiveGetLeafCount(this);
    }
    
    private int recursiveGetLeafCount(GenericTreeNode root)
    {
        if (root.isLeaf())
            return 1;
        else
        {
            int sum = 0;
            for (GenericTreeNode child : root.getChildren())
                sum += recursiveGetLeafCount(child);
            return sum;
        }
    }
    
    
}
