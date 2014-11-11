/**
 * Tag.java
 *
 * Created on 17. 10. 2014, 11:36:39 by burgetr
 */
package org.fit.layout.model;

/**
 * A tag that can be assigned to an area.
 * 
 * @author burgetr
 */
public interface Tag
{
    
    /**
     * Obtains the string value (the name) of the tag.
     * @return the tag value
     */
    public String getValue();

    /**
     * The tag level if the tags are hiearchically organized.
     * @return The level, where 0 corresponds to the root tag.
     */
    public int getLevel();
    
}
