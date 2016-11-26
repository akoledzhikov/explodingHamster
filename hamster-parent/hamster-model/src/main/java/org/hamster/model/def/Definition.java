package org.hamster.model.def;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.karneim.pojobuilder.GeneratePojoBuilder;


@Entity
@Table(name = "ChallengeDefinitions")
@GeneratePojoBuilder(withSetterNamePattern = "*")
public class Definition
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(length = 1024)
    private String description;

    private String category; // TODO enum?

    private int cost;


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


    public int getCost()
    {
        return cost;
    }


    public void setCost(int cost)
    {
        this.cost = cost;
    }
}
