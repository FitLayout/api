/**
 * PageSet.java
 *
 * Created on 7. 1. 2016, 17:53:42 by burgetr
 */
package org.fit.layout.api;

import java.util.Date;
import java.util.List;

import org.fit.layout.model.Page;

/**
 * A set of pages processed together.
 * 
 * @author burgetr
 */
public class PageSet
{
    private String name;
    private String description;
    private Date dateCreated;
    private List<Page> pages;
    
    
    public PageSet(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public List<Page> getPages()
    {
        return pages;
    }

    public void addPage(Page page)
    {
        this.pages.add(page);
    }
    
}
