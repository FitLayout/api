/**
 * ContentRect.java
 *
 * Created on 17. 10. 2014, 14:00:16 by burgetr
 */
package org.fit.layout.model;

/**
 * A generic rectangular content within a page
 * 
 * @author burgetr
 */
public interface ContentRect 
{

    /**
     * Obtains the page this block belongs to.
     * @return the page
     */
    public Page getPage();
    
    /**
     * Obtains the pixel position within in the page.
     * @return The rectangular pixel position. 
     */
    public Rectangular getBounds();
    
}
