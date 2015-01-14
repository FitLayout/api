/**
 * AreaTreeOperator.java
 *
 * Created on 24. 10. 2013, 9:46:04 by burgetr
 */
package org.fit.layout.api;

import org.fit.layout.model.Area;
import org.fit.layout.model.AreaTree;

/**
 * A generic procedure that processes the area tree. The procedures may be applied in any order.
 * @author burgetr
 */
public interface AreaTreeOperator
{
    
    /** Obtains a unique ID of the operator */
    public String getId();
    
    /**
     * Obtains a descriptive name of the operator that may be presented to the user.
     * @return the operator name
     */
    public String getName();
    
    /**
     * Obtains a longer description of the operator.
     * @return the description
     */
    public String getDescription();
    
    /**
     * Applies the operation to the given tree.
     * @param atree the area tree to be modified.
     */
    public void apply(AreaTree atree);
    
    /**
     * Applies the operation to the given subtree of the tree.
     * @param atree the area tree to be modified.
     * @param root the root node of the affected subtree.
     */
    public void apply(AreaTree atree, Area root);
    
    
}
