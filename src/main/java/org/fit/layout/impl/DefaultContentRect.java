/**
 * DefaultContentRect.java
 *
 * Created on 21. 11. 2014, 11:25:00 by burgetr
 */
package org.fit.layout.impl;

import java.awt.Color;

import org.fit.layout.model.ContentRect;
import org.fit.layout.model.Page;
import org.fit.layout.model.Rectangular;

/**
 * A default ContentRect implementation. This class is usually not used
 * directly; the {@link DefaultBox} and {@link DefaultArea} subclasses
 * should be used instead. 
 * 
 * @author burgetr
 */
public class DefaultContentRect extends GenericTreeNode implements ContentRect
{
    private static int nextid = 1;
    
    private int id;
    private Page page;
    private Rectangular bounds;
    private Color backgroundColor;
    private float underline;
    private float lineThrough;
    private float fontSize;
    private float fontStyle;
    private float fontWeight;
    private int topBorder;
    private int bottomBorder;
    private int leftBorder;
    private int rightBorder;
    private boolean backgroundSeparated;
    
    public DefaultContentRect()
    {
        id = nextid++;
    }
    
    public DefaultContentRect(DefaultContentRect src)
    {
        id = nextid++;
        page = src.page;
        bounds = new Rectangular(src.bounds);
        backgroundColor = (src.backgroundColor == null) ? null : new Color(src.backgroundColor.getRed(), src.backgroundColor.getGreen(), src.backgroundColor.getBlue());
        underline = src.underline;
        lineThrough = src.lineThrough;
        fontSize = src.fontSize;
        fontWeight= src.fontWeight;
        topBorder = src.topBorder;
        bottomBorder = src.bottomBorder;
        leftBorder = src.leftBorder;
        rightBorder = src.rightBorder;
        backgroundSeparated = src.backgroundSeparated;
    }
    
    @Override
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    @Override
    public Page getPage()
    {
        return page;
    }
    
    public void setPage(Page page)
    {
        this.page = page;
    }
    
    @Override
    public Color getBackgroundColor()
    {
        return backgroundColor;
    }
    
    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }
    
    @Override
    public boolean isBackgroundSeparated()
    {
        return backgroundSeparated;
    }
    
    public void setBackgroundSeparated(boolean backgroundSeparated)
    {
        this.backgroundSeparated = backgroundSeparated;
    }
    
    @Override
    public float getUnderline()
    {
        return underline;
    }
    
    public void setUnderline(float underline)
    {
        this.underline = underline;
    }
    
    @Override
    public float getLineThrough()
    {
        return lineThrough;
    }
    
    public void setLineThrough(float lineThrough)
    {
        this.lineThrough = lineThrough;
    }
    
    @Override
    public float getFontSize()
    {
        return fontSize;
    }
    
    public void setFontSize(float fontSize)
    {
        this.fontSize = fontSize;
    }
    
    @Override
    public float getFontStyle()
    {
        return fontStyle;
    }
    
    public void setFontStyle(float fontStyle)
    {
        this.fontStyle = fontStyle;
    }
    
    @Override
    public float getFontWeight()
    {
        return fontWeight;
    }
    
    public void setFontWeight(float fontWeight)
    {
        this.fontWeight = fontWeight;
    }
    
    @Override
    public int getTopBorder()
    {
        return topBorder;
    }
    
    public void setTopBorder(int topBorder)
    {
        this.topBorder = topBorder;
    }
    
    @Override
    public int getBottomBorder()
    {
        return bottomBorder;
    }
    
    public void setBottomBorder(int bottomBorder)
    {
        this.bottomBorder = bottomBorder;
    }
    
    @Override
    public int getLeftBorder()
    {
        return leftBorder;
    }
    
    public void setLeftBorder(int leftBorder)
    {
        this.leftBorder = leftBorder;
    }
    
    @Override
    public int getRightBorder()
    {
        return rightBorder;
    }
    
    public void setRightBorder(int rightBorder)
    {
        this.rightBorder = rightBorder;
    }
    
    /**
     * Sets all the border values.
     * @param top
     * @param right
     * @param bottom
     * @param left
     */
    public void setBorders(int top, int right, int bottom, int left)
    {
        setTopBorder(top);
        setRightBorder(right);
        setBottomBorder(bottom);
        setLeftBorder(left);
    }
    
    @Override
    public Rectangular getBounds()
    {
        return bounds;
    }
    
    public void setBounds(Rectangular bounds)
    {
        this.bounds = bounds;
    }
    
    @Override
    public int getBorderCount()
    {
        int bcnt = 0;
        if (hasTopBorder()) bcnt++;
        if (hasBottomBorder()) bcnt++;
        if (hasLeftBorder()) bcnt++;
        if (hasRightBorder()) bcnt++;
        return bcnt;
    }

    @Override
    public boolean hasTopBorder()
    {
        return topBorder > 0;
    }
    
    @Override
    public boolean hasBottomBorder()
    {
        return bottomBorder > 0;
    }
    
    @Override
    public boolean hasLeftBorder()
    {
        return leftBorder > 0;
    }
    
    @Override
    public boolean hasRightBorder()
    {
        return rightBorder > 0;
    }

    @Override
    public int getX1()
    {
        return getBounds().getX1();
    }

    @Override
    public int getY1()
    {
        return getBounds().getY1();
    }

    @Override
    public int getX2()
    {
        return getBounds().getX2();
    }

    @Override
    public int getY2()
    {
        return getBounds().getY2();
    }

    @Override
    public int getWidth()
    {
        return getBounds().getWidth();
    }

    @Override
    public int getHeight()
    {
        return getBounds().getHeight();
    }

    @Override
    public int hashCode()
    {
        return id;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        DefaultContentRect other = (DefaultContentRect) obj;
        if (id != other.id) return false;
        return true;
    }

}
