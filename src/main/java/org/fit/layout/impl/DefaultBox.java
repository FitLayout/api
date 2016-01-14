/**
 * DefaultBox.java
 *
 * Created on 21. 11. 2014, 9:45:18 by burgetr
 */
package org.fit.layout.impl;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.fit.layout.model.Border;
import org.fit.layout.model.Border.Side;
import org.fit.layout.model.Box;
import org.fit.layout.model.ContentObject;
import org.fit.layout.model.Rectangular;

/**
 * Default generic box implementation.
 *  
 * @author burgetr
 */
public class DefaultBox extends DefaultContentRect implements Box
{
    private boolean visible;
    private Color color;
    private String fontFamily;
    private String text;
    private ContentObject contentObject;
    
    private Rectangular contentBounds;
    private Rectangular visualBounds;
    
    private String tagName;
    private Map<String, String> attributes;
    private Box.Type type;
    private Box.DisplayType displayType;
    
    private Border topBorder;
    private Border bottomBorder;
    private Border leftBorder;
    private Border rightBorder;
    
    public DefaultBox()
    {
        super();
        topBorder = new Border();
        bottomBorder = new Border();
        leftBorder = new Border();
        rightBorder = new Border();
    }
    
    public DefaultBox(DefaultBox src)
    {
        super(src);
        topBorder = new Border(src.topBorder);
        bottomBorder = new Border(src.bottomBorder);
        leftBorder = new Border(src.leftBorder);
        rightBorder = new Border(src.rightBorder);
    }
    
    @Override
    public Box getChildBox(int index)
    {
        return (Box) getChildAt(index);
    }

    @Override
    public Box getParentBox()
    {
        return (Box) getParent();
    }
    
    @Override
    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    @Override
    public Color getColor()
    {
        return color;
    }
    
    public void setColor(Color color)
    {
        this.color = color;
    }
    
    @Override
    public String getFontFamily()
    {
        return fontFamily;
    }
    
    public void setFontFamily(String fontFamily)
    {
        this.fontFamily = fontFamily;
    }
    
    @Override
    public String getText()
    {
        return text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    @Override
    public ContentObject getContentObject()
    {
        return contentObject;
    }
    
    public void setContentObject(ContentObject contentObject)
    {
        this.contentObject = contentObject;
    }
    
    @Override
    public Rectangular getContentBounds()
    {
        return contentBounds;
    }
    
    public void setContentBounds(Rectangular contentBounds)
    {
        this.contentBounds = contentBounds;
    }
    
    @Override
    public Rectangular getVisualBounds()
    {
        return visualBounds;
    }
    
    public void setVisualBounds(Rectangular visualBounds)
    {
        this.visualBounds = visualBounds;
    }
    
    @Override
    public String getTagName()
    {
        return tagName;
    }
    
    public void setTagName(String tagName)
    {
        this.tagName = tagName;
    }
    
    @Override
    public String getAttribute(String name)
    {
        if (attributes != null)
            return attributes.get(name);
        else
            return null;
    }
    
    public void setAttribute(String name, String value)
    {
        if (attributes == null)
            attributes = new HashMap<String, String>();
        attributes.put(name, value);
    }
    
    public void removeAttribute(String name)
    {
        if (attributes != null)
            attributes.remove(name);
    }
    
    @Override
    public Box.Type getType()
    {
        return type;
    }
    
    public void setType(Box.Type type)
    {
        this.type = type;
    }
    
    @Override
    public Box.DisplayType getDisplayType()
    {
        return displayType;
    }
    
    public void setDisplayType(Box.DisplayType displayType)
    {
        this.displayType = displayType;
    }
    
    @Override
    public int getX1()
    {
        return getVisualBounds().getX1();
    }

    @Override
    public int getY1()
    {
        return getVisualBounds().getY1();
    }

    @Override
    public int getX2()
    {
        return getVisualBounds().getX2();
    }

    @Override
    public int getY2()
    {
        return getVisualBounds().getY2();
    }

    @Override
    public int getWidth()
    {
        return getVisualBounds().getWidth();
    }

    @Override
    public int getHeight()
    {
        return getVisualBounds().getHeight();
    }

    @Override
    public Border getBorderStyle(Side side)
    {
        switch (side)
        {
            case TOP:
                return topBorder;
            case LEFT:
                return leftBorder;
            case BOTTOM:
                return bottomBorder;
            case RIGHT:
                return rightBorder;
        }
        return null;
    }
    
    public void setBorderStyle(Side side, Border style)
    {
        switch (side)
        {
            case TOP:
                topBorder = new Border(style);
            case LEFT:
                leftBorder = new Border(style);
            case BOTTOM:
                bottomBorder = new Border(style);
            case RIGHT:
                rightBorder = new Border(style);
        }
    }

    @Override
    public int getTopBorder()
    {
        return topBorder.getWidth();
    }

    @Override
    public void setTopBorder(int width)
    {
        topBorder.setWidth(width);
    }

    @Override
    public int getBottomBorder()
    {
        return bottomBorder.getWidth();
    }

    @Override
    public void setBottomBorder(int width)
    {
        bottomBorder.setWidth(width);
    }

    @Override
    public int getLeftBorder()
    {
        return leftBorder.getWidth();
    }

    @Override
    public void setLeftBorder(int width)
    {
        leftBorder.setWidth(width);
    }

    @Override
    public int getRightBorder()
    {
        return rightBorder.getWidth();
    }

    @Override
    public void setRightBorder(int width)
    {
        rightBorder.setWidth(width);
    }
    
}
