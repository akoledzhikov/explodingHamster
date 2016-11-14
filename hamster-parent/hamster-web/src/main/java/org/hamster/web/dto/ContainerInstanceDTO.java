package org.hamster.web.dto;


import java.util.Collection;
import java.util.stream.Collectors;

import org.hamster.model.runtime.ContainerInstance;
import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.RuleInstance;


public class ContainerInstanceDTO
{

    private long id;

    private Collection<Long> ruleInstances;

    private Collection<Long> challengeInstances;

    private String containerClass;


    public ContainerInstanceDTO()
    {
        super();
    }


    public ContainerInstanceDTO(ContainerInstance ci)
    {
        this.id = ci.getId();
        this.containerClass = ci.getContainerClass();
        this.ruleInstances = ci.getRuleInstances().stream().map(RuleInstance::getId).collect(Collectors.toList());
        this.challengeInstances = ci.getChallengeInstances().stream().map(Instance::getId).collect(Collectors.toList());
    }


    public long getId()
    {
        return id;
    }


    public void setId(long id)
    {
        this.id = id;
    }


    public Collection<Long> getRuleInstances()
    {
        return ruleInstances;
    }


    public void setRuleInstances(Collection<Long> ruleInstances)
    {
        this.ruleInstances = ruleInstances;
    }


    public Collection<Long> getChallengeInstances()
    {
        return challengeInstances;
    }


    public void setChallengeInstances(Collection<Long> challengeInstances)
    {
        this.challengeInstances = challengeInstances;
    }


    public String getContainerClass()
    {
        return containerClass;
    }


    public void setContainerClass(String containerClass)
    {
        this.containerClass = containerClass;
    }
}
