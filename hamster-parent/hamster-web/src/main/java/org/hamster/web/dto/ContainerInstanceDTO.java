package org.hamster.web.dto;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hamster.model.runtime.ContainerInstance;
import org.hamster.model.runtime.ContainerInstanceBuilder;
import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.InstanceBuilder;


public class ContainerInstanceDTO
{

    private long id;

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
        this.challengeInstances = ci.getChallengeInstances()
                                    .stream()
                                    .map(Instance::getId)
                                    .collect(Collectors.toList());
    }


    public long getId()
    {
        return id;
    }


    public void setId(long id)
    {
        this.id = id;
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


    public ContainerInstance toEntity()
    {
        ContainerInstance result = new ContainerInstanceBuilder().id(id)
                                                                 .containerClass(containerClass)
                                                                 .build();
        List<Instance> challenges = new ArrayList<>();
        for (long id : challengeInstances)
        {
            challenges.add(new InstanceBuilder().id(id).build());
        }

        return result;
    }
}
