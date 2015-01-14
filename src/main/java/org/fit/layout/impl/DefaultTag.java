/**
 * DefaultTag.java
 *
 * Created on 27. 11. 2014, 22:50:30 by burgetr
 */
package org.fit.layout.impl;

import org.fit.layout.model.Tag;

/**
 * A default simple tag implementation that allows to specify the tag value and level.
 * 
 * @author burgetr
 */
public class DefaultTag implements Tag
{
    private String value;
    private int level;

    public DefaultTag(String value)
    {
        this.value = value;
        this.level = 0;
    }

    public DefaultTag(String value, int level)
    {
        this.value = value;
        this.level = level;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    @Override
    public String toString()
    {
        return value;
    }

    @Override
    public int hashCode()
    {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        return value.equals(obj.toString());
    }
    
}