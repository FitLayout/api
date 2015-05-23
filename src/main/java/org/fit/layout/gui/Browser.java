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
import org.fit.layout.model.AreaTree;
import org.fit.layout.model.LogicalAreaTree;
import org.fit.layout.model.Page;

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
     * Adds a new tab to the tool panel at the top of the window
     * @param title the tab title
     * @param component the component to be added
     */
    public void addToolPanel(String title, JComponent component);
    
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
    
    /**
     * Registers an area selection listener that is called when the area selection changes.
     * @param listener the listener to be registered
     */
    public void addAreaSelectionListener(AreaSelectionListener listener);
    
    /**
     * Sets page model from the external source
     * @param page
     */
    public void setPage(Page page);
    
    /**
     * Gets the actual page model
     * @return the actual page model
     */
    public Page getPage();
    
    
    /**
     * Gets the area tree of the current page
     * @return the current area tree or {@code null} if the tree has not been built yet
     */
    public AreaTree getAreaTree();
    
    /**
     * Gets the logical area tree of the actual page
     * @return the current logical tree or {@code null} if the tree has not been built yet
     */
    public LogicalAreaTree getLogicalTree();
    
    /**
     * Sets area tree from external source
     */
    public void setAreaTree(AreaTree areaTree);
    
    /**
     * Sets logical area tree from external source
     */
    public void setLogicalTree(LogicalAreaTree logicalTree);

}
