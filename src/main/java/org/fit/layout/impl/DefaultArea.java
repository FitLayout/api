/**
 * DefaultArea.java
 *
 * Created on 21. 11. 2014, 11:16:51 by burgetr
 */
package org.fit.layout.impl;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.fit.layout.model.Area;
import org.fit.layout.model.AreaTopology;
import org.fit.layout.model.AreaTree;
import org.fit.layout.model.Border;
import org.fit.layout.model.Box;
import org.fit.layout.model.Box.Type;
import org.fit.layout.model.ContentLine;
import org.fit.layout.model.Rectangular;
import org.fit.layout.model.Tag;

/**
 * Default generic Area implementation.
 * 
 * @author burgetr
 */
public class DefaultArea extends DefaultContentRect<Area> implements Area
{
    /** Area name to be displayed to the users */
    private String name;
    
    /** The area tree this node belongs to */
    private AreaTree areaTree;
    
    /** The topology assigned to the area */
    private AreaTopology topology;
    
    /** The content line the area belongs to */
    private ContentLine line;

    /** The visual boxes that form this area. */
    private Vector<Box> boxes;
    
    /** Assigned tags */
    private Map<Tag, Float> tags;

    /** Effective bounds of the area content. */
    private Rectangular contentBounds;

    /** Previous area on the same line */
    private Area previousOnLine = null;
    
    /** Next area on the same line */
    private Area nextOnLine = null;

    /** Is the area a horizontal separator? */
    private boolean hsep;

    /** Is the area a vertical separator? */
    private boolean vsep;

    public DefaultArea(Rectangular r)
    {
        super(Area.class);
        name = null;
        boxes = new Vector<Box>();
        tags = new HashMap<Tag, Float>();
        setBounds(new Rectangular(r));
        setBackgroundColor(null);
        hsep = false;
        vsep = false;
    }
    
    public DefaultArea(DefaultArea src)
    {
        super(Area.class, src);
        name = (src.name == null) ? null : new String(src.name);
        boxes = new Vector<Box>(src.getBoxes());
        tags = new HashMap<Tag, Float>();
        contentBounds = (src.contentBounds == null) ? null : new Rectangular(src.contentBounds);
        vsep = src.vsep;
        hsep = src.hsep;
    }
    
    public DefaultArea(int x1, int y1, int x2, int y2)
    {
        this(new Rectangular(x1, y1, x2, y2));
    }
    
    public DefaultArea(Box box)
    {
        this(box.getBounds());
        setPage(box.getPage());
        addBox(box);
        setBounds(new Rectangular(contentBounds)); //update bounds to the box content bounds
        setName(getBoxDescription(box));
        setBackgroundColor(box.getBackgroundColor());
        setBackgroundSeparated(box.isBackgroundSeparated());
        for (Border.Side side : Border.Side.values())
            setBorderStyle(side, box.getBorderStyle(side));
    }
    
    public DefaultArea(List<Box> boxList)
    {
        //use the first box for the area properties
        this(boxList.get(0));
        //update bounds to the box content bounds
        for (Box box : boxList)
            addBox(box); //expands the content bounds appropriately
        setBounds(new Rectangular(contentBounds));
    }
    
    /**
     * Sets the name of the area. The name is used when the area information is displayed
     * using <code>toString()</code>
     * @param name The new area name
     */
    @Override
    public void setName(String name)
    {
        this.name = name;
    }
    
    @Override
    public String getName()
    {
        return name;
    }
    
    public AreaTree getAreaTree()
    {
        return areaTree;
    }

    public void setAreaTree(AreaTree areaTree)
    {
        this.areaTree = areaTree;
    }

    public Rectangular getContentBounds()
    {
        return contentBounds;
    }
    
    @Override
    public void move(int xofs, int yofs)
    {
        getContentBounds().move(xofs, yofs);
        super.move(xofs, yofs);
    }
    
    @Override
    public ContentLine getLine()
    {
        return line;
    }

    @Override
    public void setLine(ContentLine line)
    {
        this.line = line;
    }

    @Override
    public AreaTopology getTopology()
    {
        if (topology == null)
            topology = createTopology();
        return topology;
    }
    
    @Override
    public void updateTopologies()
    {
        if (topology != null)
            topology.update();
    }
    
    /**
     * Creates a topology for this area. This method should be overriden
     * when another topology implementation is used. By default, it returns
     * the default grid-based topology. When another topology is used, the
     * {@code invalidateTopology()} function should be overriden as well.
     * 
     * @return The created topology for this area
     */
    protected AreaTopology createTopology()
    {
        return new DefaultGridTopology(this);
    }
    
    /**
     * Marks the topology as dirty when the list of areas has been altered.
     */
    protected void invalidateTopology()
    {
        if (topology != null)
            ((DefaultGridTopology) topology).setDirty(true);
    }
    
    @Override
    public Color getEffectiveBackgroundColor()
    {
        if (getBackgroundColor() != null)
            return getBackgroundColor();
        else
        {
            if (getParent() != null)
                return getParent().getEffectiveBackgroundColor();
            else
                return Color.WHITE; //use white as the default root color
        }
    }

    @Override
    public void appendChild(Area child)
    {
        invalidateTopology();
        child.setAreaTree(areaTree);
        super.appendChild(child);
        getBounds().expandToEnclose(child.getBounds());
    }
    
    @Override
    public void insertChild(Area child, int index)
            throws IndexOutOfBoundsException
    {
        invalidateTopology();
        super.insertChild(child, index);
    }

    @Override
    public void removeAllChildren()
    {
        invalidateTopology();
        super.removeAllChildren();
    }

    @Override
    public void removeChild(int index) throws IndexOutOfBoundsException
    {
        invalidateTopology();
        super.removeChild(index);
    }

    @Override
    public void removeChild(Area child) throws IllegalArgumentException
    {
        invalidateTopology();
        super.removeChild(child);
    }

    public Area getPreviousOnLine()
    {
        return previousOnLine;
    }

    public void setPreviousOnLine(Area previousOnLine)
    {
        this.previousOnLine = previousOnLine;
    }

    public Area getNextOnLine()
    {
        return nextOnLine;
    }

    public void setNextOnLine(Area nextOnLine)
    {
        this.nextOnLine = nextOnLine;
    }
    
    @Override
    public String getText()
    {
        String ret = "";
        if (isLeaf())
            ret = getBoxText();
        else
            for (int i = 0; i < getChildCount(); i++)
                ret += getChildAt(i).getText();
        return ret;
    }
    
    @Override
    public String getText(String separator)
    {
        String ret = "";
        if (isLeaf())
            ret = getBoxText();
        else
        {
            for (int i = 0; i < getChildCount(); i++)
            {
                if (getChildAt(i).isLeaf() && !ret.isEmpty())
                    ret += separator;
                ret += getChildAt(i).getText(separator);
            }
        }
        return ret;
    }
    
    @Override
    public boolean isReplaced()
    {
        boolean empty = true;
        for (Box root : boxes)
        {
            empty = false;
            if (root.getType() != Box.Type.REPLACED_CONTENT)
                return false;
        }
        return !empty;
    }
    
    //====================================================================================
    // boxes
    //====================================================================================
    
    /**
     * Adds a new box to the area.
     * @param box
     */
    public void addBox(Box box)
    {
        boxes.add(box);
        
        Rectangular sb = box.getVisualBounds();
        if (contentBounds == null)
            contentBounds = new Rectangular(sb);
        else if (sb.getWidth() > 0 && sb.getHeight() > 0)
            contentBounds.expandToEnclose(sb);
    }
    
    /**
     * Returns a vector of boxes that are inside of this area
     * @return A vector containing the {@link org.fit.layout.model.Box Box} objects
     */
    public Vector<Box> getBoxes()
    {
        return boxes;
    }
    
    /** 
     * Obtains all the boxes from this area and all the child areas.
     * @return The list of boxes
     */
    @Override
    public Vector<Box> getAllBoxes()
    {
        Vector<Box> ret = new Vector<Box>();
        recursiveFindBoxes(this, ret);
        return ret;
    }
    
    private void recursiveFindBoxes(Area root, Vector<Box> result)
    {
        result.addAll(root.getBoxes());
        for (int i = 0; i < root.getChildCount(); i++)
            recursiveFindBoxes((Area) root.getChildAt(i), result);
    }

    /**
     * Returns the text string represented by a concatenation of all
     * the boxes contained directly in this area (no subareas)
     */
    public String getBoxText()
    {
        StringBuilder ret = new StringBuilder();
        boolean start = true;
        for (Iterator<Box> it = boxes.iterator(); it.hasNext(); )
        {
            if (!start) ret.append(' ');
            else start = false;
            ret.append(it.next().getText());
        }
        return ret.toString();
    }
    
    /**
     * Removes the given box from the given area. This does not change the size of the area.
     * @param box the box to be removed
     */
    public void removeBox(Box box)
    {
        boxes.remove(box);
    }
    
    /**
     * Removes the given boxes from the given area. This does not change the size of the area.
     * @param box the collection of boxes to be removed
     */
    public void removeBoxes(Collection<Box> box)
    {
        boxes.removeAll(box);
    }
    
    
    /**
     * Returns the child area at the specified grid position or null, if there is no
     * child area at this position.
     */
    public DefaultArea getChildAtGridPos(int x, int y)
    {
        return (DefaultArea) getTopology().findAreaAt(x, y);
    }
    
    //====================================================================================
    // tagging
    //====================================================================================
    
    @Override
    public void addTag(Tag tag, float support)
    {
        Float oldsupport = tags.get(tag);
        if (oldsupport == null || oldsupport < support)
            tags.put(tag, support);
    }
    
    @Override
    public boolean hasTag(Tag tag)
    {
        return tags.get(tag) != null;
    }
    
    @Override
    public boolean hasTag(Tag tag, float minSupport)
    {
        final Float sp = tags.get(tag); 
        return (sp != null && sp >= minSupport);
    }

    @Override
    public Set<Tag> getSupportedTags(float minSupport)
    {
        Set<Tag> ret = new HashSet<Tag>();
        for (Map.Entry<Tag, Float> entry : tags.entrySet())
        {
            if (entry.getValue() >= minSupport)
                ret.add(entry.getKey());
        }
        return ret;
    }

    @Override
    public float getTagSupport(Tag tag)
    {
        Float f = tags.get(tag);
        if (f == null)
            return 0.0f;
        else
            return f;
    }
    
    @Override
    public Tag getMostSupportedTag()
    {
        float max = -1.0f;
        Tag ret = null;
        for (Map.Entry<Tag, Float> entry : tags.entrySet())
        {
            if (entry.getValue() > max)
            {
                max = entry.getValue();
                ret = entry.getKey();
            }
        }
        return ret;
    }
    
    /**
     * Removes all tags that belong to the given collection.
     * @param c A collection of tags to be removed.
     */
    public void removeAllTags(Collection<Tag> c)
    {
        for (Tag t : c)
            tags.remove(t);
    }
    
    /**
     * Removes the specific tag
     * @param tag
     */
    @Override
    public void removeTag(Tag tag) 
    {
        tags.remove(tag);	
    }
    
    /**
     * Tests whether the area or any of its <b>direct child</b> areas have the given tag.
     * @param tag the tag to be tested.
     * @return <code>true</code> if the area or its direct child areas have the given tag
     */
    public boolean containsTag(Tag tag)
    {
        if (hasTag(tag))
            return true;
        else
        {
            for (Area child : getChildren())
                if (child.hasTag(tag))
                    return true;
            return false;
        }
    }
    
    /**
     * Obtains the set of tags assigned to the area.
     * @return a set of tags
     */
    @Override
    public Map<Tag, Float> getTags()
    {
        return tags;
    }
    
    public void SetHorizontalSeparator(boolean hsep)
    {
        this.hsep = hsep;
    }
    
    @Override
    public boolean isHorizontalSeparator()
    {
        return hsep;
    }

    public void SetVerticalSeparator(boolean vsep)
    {
        this.hsep = vsep;
    }
    
    @Override
    public boolean isVerticalSeparator()
    {
        return vsep;
    }

    @Override
    public boolean isSeparator()
    {
        return isHorizontalSeparator() || isVerticalSeparator();
    }

    @Override
    public Area createSuperArea(Rectangular gp, List<Area> selected, String name)
    {
        if (getChildCount() > 1 && selected.size() > 1 && selected.size() != getChildCount())
        {
            //absolute position of the new area
            Rectangular abspos = getTopology().toPixelPosition(gp);
            abspos.move(getX1(), getY1());
            //create the new area
            DefaultArea area = new DefaultArea(abspos);
            area.setName(name);
            int index = getIndex(selected.get(0));
            insertChild(area, index);
            area.appendChildren(selected);
            area.updateTopologies();
            updateTopologies();
            return area;
        }
        else
            return null;
    }
    
    @Override
    public void insertParent(Area newParent, Area child)
    {
        final int index = getIndex(child);
        if (index == -1)
            throw new IllegalArgumentException("child must be a child area");
        insertChild(newParent, index);
        newParent.appendChild(child);
    }
    
    @Override
    public Area copy()
    {
        Area ret = new DefaultArea(this);
        if (getParent() != null)
        {
            int ndx = getParent().getIndex(this);
            getParent().insertChild(ret, ndx + 1);
        }
        return ret;
    }
    
    @Override
    public String toString()
    {
        String bs = "";
        if (hasTopBorder()) bs += "^";
        if (hasLeftBorder()) bs += "<";
        if (hasRightBorder()) bs += ">";
        if (hasBottomBorder()) bs += "_";
        if (isBackgroundSeparated()) bs += "*";
        
        if (name != null)
            return bs + " " + name + " " + getBounds().toString();
        else
            return bs + " " + "<area> " + getBounds().toString();
          
    }

    /**
     * Sets the grid position of this area within the parent topology.
     * @param gp the new grid position
     */
    public void setGridPosition(Rectangular gp)
    {
        if (getParent() != null)
            getParent().getTopology().setPosition(this, gp);
    }
    
    /**
     * Gets the grid position of this area within the parent topology.
     * @return the grid position or a unit rectangle when there is no parent
     */
    public Rectangular getGridPosition()
    {
        if (getParent() != null)
            return getParent().getTopology().getPosition(this);
        else
            return new Rectangular(0, 0, 0, 0);
    }
    
    /**
     * Obtains a box description used as the default area name when the area
     * is created from a box.
     * @param box
     * @return
     */
    protected String getBoxDescription(Box box)
    {
        if (box.getType() == Type.TEXT_CONTENT)
            return box.getText();
        else
        {
            final String cls = box.getAttribute("class");
            final String id = box.getAttribute("id");
            StringBuilder ret = new StringBuilder("<");
            ret.append(box.getTagName());
            if (id != null)
                ret.append(" id=").append(id);
            if (cls != null)
                ret.append(" class=").append(cls);
            ret.append(">");
            return ret.toString();
        }
    }

}
