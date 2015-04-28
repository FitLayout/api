/**
 * ScriptObject.java
 *
 * Created on 28. 4. 2015, 16:38:19 by burgetr
 */
package org.fit.layout.api;

/**
 * An object that should be made available in the JavaScript engine.
 *  
 * @author burgetr
 */
public interface ScriptObject
{

    /**
     * Gets the name of the object in the JavaScript engine.
     * @return the JS identifier
     */
    public String getName();
    
}
