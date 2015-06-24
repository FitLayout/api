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
    public enum ValueType { STRING, INTEGER, FLOAT, BOOLEAN };
    
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
    
    /**
     * Obtains the value of the given parameter.
     * @param name the parameter name
     * @return the parameter value or {@code null} for unknown parameter
     */
    public Object getParam(String name);
    
    /**
     * Obtains the minimal and maximal value of the given parameter.
     * @param name the name of the parameter
     * @return Array of two objects [min, max] for numeric types,
     *  [min_length, max_length] for string types, {@code null} for other types or non-existing parameters
     */
    public Object[] getParamRange(String name);
    
}
