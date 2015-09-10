/**
 * Border.java
 *
 * Created on 10. 9. 2015, 20:40:32 by burgetr
 */
package org.fit.layout.model;

import java.awt.Color;

/**
 * A structure that describes a box border properties.
 * 
 * @author burgetr
 */
public class Border
{
    /** Border side specification */
    public enum Side 
    {
        TOP("top"), LEFT("left"), BOTTOM("bottom"), RIGHT("right");
        
        private String text;
        
        private Side(String text)
        {
            this.text = text;
        }
        
        public String toString()
        {
            return text;
        }

    };
    
    /** Border line style */
    public enum Style { NONE, SOLID, DOTTED, DASHED, DOUBLE };
    
    private int width;
    private Style style;
    private Color color;

    public Border()
    {
        width = 0;
        style = Style.NONE;
        color = Color.BLACK;
    }
    
    public Border(int width, Style style, Color color)
    {
        this.width = width;
        this.style = style;
        this.color = color;
    }
    
    public Border(Border src)
    {
        this.width = src.width;
        this.style = src.style;
        this.color = src.color;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public void setWidth(int width)
    {
        this.width = width;
    }
    
    public Style getStyle()
    {
        return style;
    }
    
    public void setStyle(Style style)
    {
        this.style = style;
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public void setColor(Color color)
    {
        this.color = color;
    }
    
}
