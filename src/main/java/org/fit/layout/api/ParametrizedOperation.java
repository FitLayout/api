/**
 * ParametrizedOperation.java
 *
 * Created on 16. 1. 2015, 15:37:45 by burgetr
 */
package org.fit.layout.api;

/**
 * An implementation of an operation with external parametres of different types.
 * 
 * @author burgetr
 */
public interface ParametrizedOperation
{

    /** Possible parameter value types */
    public enum ValueType { STRING, FLOAT, BOOLEAN };
    
    /**
     * Obtains a list of available parameter names of the operation.
     * @return an array of parameter names
     */
    public String[] getParamNames();
    
    /**
     * Obtains a list of available parameter value types.
     * @return an array of value types
     */
    public ValueType[] getParamTypes();
    
    /**
     * Sets the value of the given parameter.
     * @param name parameter name
     * @param value parameter value
     * @return true when successfully set, false for unknown parameter or invalid value
     */
    public boolean setParam(String name, Object value);
    
}
