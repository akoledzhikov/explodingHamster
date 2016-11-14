package org.hamster.web.dto;


import org.hamster.model.def.Definition;


public class DefinitionDTO
{
    private long id;

    private String name;

    private String description;

    private String category;


    public DefinitionDTO()
    {
        super();
    }


    public DefinitionDTO(Definition def)
    {
        this.id = def.getId();
        this.name = def.getName();
        this.category = def.getCategory();
        this.description = def.getDescription();
    }


    public long getId()
    {
        return id;
    }


    public void setId(long id)
    {
        this.id = id;
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


    public String getCategory()
    {
        return category;
    }


    public void setCategory(String category)
    {
        this.category = category;
    }

}
