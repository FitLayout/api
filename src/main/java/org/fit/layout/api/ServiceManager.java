/**
 * ServiceManager.java
 *
 * Created on 26. 2. 2015, 22:59:16 by burgetr
 */
package org.fit.layout.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * This class provides static methods for managing the global services.
 * 
 * @author burgetr
 */
public class ServiceManager
{

    private static Map<String, BoxTreeProvider> boxProviders;
    private static Map<String, AreaTreeProvider> areaProviders;
    private static Map<String, AreaTreeOperator> operators;

    /**
     * Discovers all the BoxTreeProvider service implementations.
     * @return A map that assigns the service {@code id} to the appropriate implementation.
     */
    public static Map<String, BoxTreeProvider> findBoxTreeProviders()
    {
        if (boxProviders == null)
        {
            ServiceLoader<BoxTreeProvider> loader = ServiceLoader.load(BoxTreeProvider.class);
            Iterator<BoxTreeProvider> it = loader.iterator();
            boxProviders = new HashMap<String, BoxTreeProvider>();
            while (it.hasNext())
            {
                BoxTreeProvider op = it.next();
                boxProviders.put(op.getId(), op);
            }
        }
        return boxProviders;
    }
    
    /**
     * Discovers all the AreaTreeProvider service implementations.
     * @return A map that assigns the service {@code id} to the appropriate implementation.
     */
    public static Map<String, AreaTreeProvider> findAreaTreeProviders()
    {
        if (areaProviders == null)
        {
            ServiceLoader<AreaTreeProvider> loader = ServiceLoader.load(AreaTreeProvider.class);
            Iterator<AreaTreeProvider> it = loader.iterator();
            areaProviders = new HashMap<String, AreaTreeProvider>();
            while (it.hasNext())
            {
                AreaTreeProvider op = it.next();
                areaProviders.put(op.getId(), op);
            }
        }
        return areaProviders;
    }
    
    /**
     * Discovers all the AreaTreeOperator service implementations.
     * @return A map that assigns the service {@code id} to the appropriate implementation.
     */
    public static Map<String, AreaTreeOperator> findAreaTreeOperators()
    {
        if (operators == null)
        {
            ServiceLoader<AreaTreeOperator> loader = ServiceLoader.load(AreaTreeOperator.class);
            Iterator<AreaTreeOperator> it = loader.iterator();
            operators = new HashMap<String, AreaTreeOperator>();
            while (it.hasNext())
            {
                AreaTreeOperator op = it.next();
                operators.put(op.getId(), op);
            }
        }
        return operators;
    }

    /**
     * Sets the operation parametres based on a map of values.
     * @param op The operation whose parametres should be set
     * @param params A map that assigns values to operator names
     */
    public static void setServiceParams(ParametrizedOperation op, Map<String, Object> params)
    {
        if (params != null)
        {
            for (Map.Entry<String, Object> entry : params.entrySet())
            {
                op.setParam(entry.getKey(), entry.getValue());
            }
        }
    }
    

}
