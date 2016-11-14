package org.hamster.web.dto;


import java.util.HashMap;

import org.hamster.model.runtime.RuleInstance;


public class RuleInstanceDTO
{
    private long id;

    private HashMap<String, Object> parameters;

    private String ruleClass;

    private long containerInstance;


    public RuleInstanceDTO()
    {
        super();
    }


    public RuleInstanceDTO(RuleInstance ri)
    {
        this.id = ri.getId();
        this.parameters = ri.getParameters();
        this.ruleClass = ri.getRuleClass();
        this.containerInstance = ri.getContainerInstance().getId();
    }


    public long getId()
    {
        return id;
    }


    public void setId(long id)
    {
        this.id = id;
    }


    public HashMap<String, Object> getParameters()
    {
        return parameters;
    }


    public void setParameters(HashMap<String, Object> parameters)
    {
        this.parameters = parameters;
    }


    public String getRuleClass()
    {
        return ruleClass;
    }


    public void setRuleClass(String ruleClass)
    {
        this.ruleClass = ruleClass;
    }


    public long getContainerInstance()
    {
        return containerInstance;
    }


    public void setContainerInstance(long containerInstance)
    {
        this.containerInstance = containerInstance;
    }

}
