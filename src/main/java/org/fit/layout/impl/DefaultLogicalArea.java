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

/**
 * Default LogicalArea implementation.
 * 
 * @author burgetr
 */
public class DefaultLogicalArea extends GenericTreeNode implements LogicalArea
{
    private List<Area> areas;
    private String text;
    
    
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

}
