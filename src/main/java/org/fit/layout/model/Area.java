/**
 * Area.java
 *
 * Created on 17. 10. 2014, 11:33:36 by burgetr
 */
package org.fit.layout.model;

import java.util.List;

/**
 * An area containing several visual boxes.
 * 
 * @author burgetr
 */
public interface Area extends ContentRect
{
    
    public Area getParentArea();
    
    public Area getChildAreaAt(int index);
    
    public int getChildCount();
    
    public Rectangular getGridBounds();
    
    public List<Box> getBoxes();
    
}
