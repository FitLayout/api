/**
 * DefaultLogicalArea.java
 *
 * Created on 19. 3. 2015, 13:10:48 by burgetr
 */
package org.fit.layout.impl;

import java.util.List;
import java.util.Vector;

import org.fit.layout.model.Area;
import org.fit.layout.model.LogicalArea;
import org.fit.layout.model.Tag;

/**
 * Default LogicalArea implementation.
 * 
 * @author burgetr
 */
public class DefaultLogicalArea extends GenericTreeNode implements LogicalArea
{
    private List<Area> areas;
    private String text;
    private Tag mainTag;
    
    public DefaultLogicalArea()
    {
        super();
        areas = new Vector<Area>();
        text = "";
    }
    
    public DefaultLogicalArea(Area src)
    {
        super();
        areas = new Vector<Area>();
        areas.add(src);
        text = src.getText();
    }
    
    public DefaultLogicalArea(Area src, String text)
    {
        super();
        areas = new Vector<Area>();
        areas.add(src);
        this.text = text;
    }
    
    //==============================================================================
    
    @Override
    public void addArea(Area a)
    {
        areas.add(a);
    }

    @Override
    public List<Area> getAreas()
    {
        return areas;
    }

    @Override
    public Area getFirstArea()
    {
        return ((Vector<Area>) areas).firstElement();
    }
    
    @Override
    public int getAreaCount()
    {
        return areas.size();
    }

    @Override
    public void setText(String text)
    {
        this.text = text;
    }

    @Override
    public String getText()
    {
        return text;
    }
    
    @Override
    public void setMainTag(Tag mainTag)
    {
        this.mainTag = mainTag;
    }

    @Override
    public Tag getMainTag()
    {
        return mainTag;
    }

    @Override
    public String toString()
    {
        final String tagstr = (mainTag == null) ? "---" : mainTag.getValue();
        return "(" + tagstr + ") " + getText();
    }
    
    //==============================================================================
    
    @Override
    public LogicalArea getParentArea()
    {
        return (LogicalArea) getParent();
    }

    @Override
    public LogicalArea getPreviousSibling()
    {
        return (LogicalArea) getPreviousSiblingNode();
    }

    @Override
    public LogicalArea getNextSibling()
    {
        return (LogicalArea) getNextSiblingNode();
    }

    @Override
    public LogicalArea getChildArea(int index)
            throws ArrayIndexOutOfBoundsException
    {
        return (LogicalArea) getChildAt(index);
    }

    @Override
    public List<LogicalArea> getChildAreas()
    {
        Vector<LogicalArea> ret = new Vector<LogicalArea>(getChildCount());
        for (GenericTreeNode child : getChildren())
            ret.add((LogicalArea) child);
        return ret;
    }

    @Override
    public void appendChild(LogicalArea child)
    {
        add((DefaultLogicalArea) child);
    }

    @Override
    public void appendChildren(List<LogicalArea> children)
    {
        for (LogicalArea child : children)
            add((DefaultLogicalArea) child);
    }

    @Override
    public void insertChild(LogicalArea child, int index)
    {
        insert((DefaultLogicalArea) child, index);
    }

    @Override
    public void removeChild(LogicalArea child)
    {
        remove((DefaultLogicalArea) child); 
    }

    @Override
    public int getIndex(LogicalArea child)
    {
        return super.getIndex((DefaultLogicalArea) child);
    }

    @Override
    public LogicalArea findArea(Area area)
    {
        LogicalArea ret = null;
        //scan the subtree
        for (int i = 0; i < getChildCount() && ret == null; i++)
        {
            ret = getChildArea(i).findArea(area);
        }
        //not in the subtree -- is it this area?
        if (ret == null && getAreas().contains(area))
            ret = this; //in our area nodes
        return ret;
    }
    
    @Override
    public boolean isAncestorOf(LogicalArea other)
    {
        LogicalArea parent = other.getParentArea();
        while (parent != null)
        {
            if (parent == this)
                return true;
            parent = parent.getParentArea();
        }
        return false;
    }
    
}
