/**
 * Page.java
 *
 * Created on 17. 10. 2014, 14:02:05 by burgetr
 */
package org.fit.layout.model;

import java.net.URL;

/**
 * This class represents the whole rendered (and segmented) page.
 * 
 * @author burgetr
 */
public interface Page
{

    /**
     * Obtains the source URL of the page.
     * @return the URL
     */
    public URL getSourceURL();
    
    /**
     * Obtains the page width.
     * @return the width in pixels
     */
    public int getWidth();

    /**
     * Obtains the page height.
     * @return the height in pixels
     */
    public int getHeight();
    
    
    //TODO some factory functions (create area, etc?)
}
