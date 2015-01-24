/**
 * Browser.java
 *
 * Created on 23. 1. 2015, 13:36:15 by burgetr
 */
package org.fit.layout.gui;

import javax.swing.JComponent;
import javax.swing.JToolBar;

/**
 * A GUI browser interface used for creating browser plugins.
 * 
 * @author burgetr
 */
public interface Browser
{

    /**
     * Adds a toolbar to the browser's toolbar area.
     * @param toolbar the toolbar to be added
     */
    public void addToolBar(JToolBar toolbar);
    
    /**
     * Adds a new tab to the structure view tabs.
     * @param title the tab title
     * @param component the component to be added
     */
    public void addStructurePanel(String title, JComponent component);
    
    /**
     * Adds a new panel to the details area.
     * @param component the component to be added
     * @param weighty the resizing vertical weight
     */
    public void addInfoPanel(JComponent component, double weighty);
    
}
