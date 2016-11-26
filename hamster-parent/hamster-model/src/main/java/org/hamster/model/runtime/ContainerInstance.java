package org.hamster.model.runtime;


import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.karneim.pojobuilder.GeneratePojoBuilder;


@Entity
@Table(name = "ContainerInstances")
@GeneratePojoBuilder(withSetterNamePattern = "*")
public class ContainerInstance
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "containerInstance")
    private Collection<Instance> challengeInstances;

    private String containerClass;


    public long getId()
    {
        return id;
    }


    public void setId(long id)
    {
        this.id = id;
    }


    public Collection<Instance> getChallengeInstances()
    {
        return challengeInstances;
    }


    public void setChallengeInstances(Collection<Instance> challengeInstances)
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
