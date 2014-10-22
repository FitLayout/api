/**
 * Tag.java
 *
 * Created on 21.11.2011, 13:47:31 by burgetr
 */
package org.fit.layout.impl;


/**
 * A single tag that can be assigned to the visual areas. Each tag is identified with a string value.
 * 
 * @author burgetr
 */
public class Tag
{
    private String value;
    private Tagger source;
    
    public Tag(String value, Tagger source)
    {
        this.value = value;
        this.source = source;
    }

    public String getValue()
    {
        return value;
    }

    public Tagger getSource()
    {
        return source;
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
    
    public boolean allowsJoining()
    {
        if (source != null)
            return source.allowsJoining();
        else
            return false;
    }
    
    public boolean allowsContinutation(AreaNode node)
    {
        if (source != null)
            return source.allowsContinuation(node);
        else
            return false;
    }
    
}