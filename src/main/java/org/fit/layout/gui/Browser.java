/**
 * Browser.java
 *
 * Created on 23. 1. 2015, 13:36:15 by burgetr
 */
package org.fit.layout.gui;

import javax.swing.JComponent;
import javax.swing.JToolBar;

import org.fit.layout.api.OutputDisplay;
import org.fit.layout.model.Area;

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
    
    /**
     * Gets the browser page output display.
     * @return the output display
     */
    public OutputDisplay getOutputDisplay();
    
    /**
     * Performs the output display refresh (when something has been
     * painted).
     */
    public void updateDisplay();
    
    /**
     * Gets the last selected visual area.
     * @return the selected visual area or {@code null} when nothing is selected
     */
    public Area getSelectedArea();
    
}
