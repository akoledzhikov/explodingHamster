package org.hamster.web.dto;


import java.util.Date;

import org.hamster.model.runtime.ChallengeStatus;
import org.hamster.model.runtime.ContainerInstanceBuilder;
import org.hamster.model.runtime.ContentBuilder;
import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.InstanceBuilder;
import org.hamster.model.user.UserBuilder;


public class InstanceDTO
{
    private long id;

    private long containerInstance;
    private long definition;
    private long challenger;
    private long target;

    private Date submittedOn;
    private Date expiresOn;
    private Date votingStartedOn;
    private Date votingEnds;
    private Date completedOn;

    private long content;

    private ChallengeStatus status;


    public InstanceDTO()
    {
        super();
    }


    public InstanceDTO(Instance instance)
    {
        this.id = instance.getId();
        this.containerInstance = instance.getContainerInstance().getId();
        this.definition = instance.getDefinition().getId();
        this.challenger = instance.getChallenger().getId();
        this.target = instance.getTarget().getId();
        this.submittedOn = instance.getSubmittedOn();
        this.expiresOn = instance.getExpiresOn();
        this.votingStartedOn = instance.getVotingStartedOn();
        this.votingEnds = instance.getVotingEndsOn();
        this.completedOn = instance.getCompletedOn();
        this.content = instance.getContent().getId();
        this.status = instance.getStatus();
    }


    public long getId()
    {
        return id;
    }


    public void setId(long id)
    {
        this.id = id;
    }


    public long getContainerInstance()
    {
        return containerInstance;
    }


    public void setContainerInstance(long containerInstance)
    {
        this.containerInstance = containerInstance;
    }


    public long getDefinition()
    {
        return definition;
    }


    public void setDefinition(long definition)
    {
        this.definition = definition;
    }


    public long getChallenger()
    {
        return challenger;
    }


    public void setChallenger(long challenger)
    {
        this.challenger = challenger;
    }


    public long getTarget()
    {
        return target;
    }


    public void setTarget(long target)
    {
        this.target = target;
    }


    public Date getSubmittedOn()
    {
        return submittedOn;
    }


    public void setSubmittedOn(Date submittedOn)
    {
        this.submittedOn = submittedOn;
    }


    public Date getVotingStartedOn()
    {
        return votingStartedOn;
    }


    public void setVotingStartedOn(Date votingStartedOn)
    {
        this.votingStartedOn = votingStartedOn;
    }


    public Date getCompletedOn()
    {
        return completedOn;
    }


    public void setCompletedOn(Date completedOn)
    {
        this.completedOn = completedOn;
    }


    public long getContent()
    {
        return content;
    }


    public void setContent(long content)
    {
        this.content = content;
    }


    public ChallengeStatus getStatus()
    {
        return status;
    }


    public void setStatus(ChallengeStatus status)
    {
        this.status = status;
    }


    public Date getExpiresOn()
    {
        return expiresOn;
    }


    public void setExpiresOn(Date expiresOn)
    {
        this.expiresOn = expiresOn;
    }


    public Date getVotingEnds()
    {
        return votingEnds;
    }


    public void setVotingEnds(Date votingEnds)
    {
        this.votingEnds = votingEnds;
    }


    public Instance toEntity()
    {
        Instance result = new InstanceBuilder().id(id)
                                               .completedOn(completedOn)
                                               .submittedOn(submittedOn)
                                               .votingStartedOn(votingStartedOn)
                                               .expiresOn(expiresOn)
                                               .votingEndsOn(votingEnds)
                                               .status(status)
                                               .build();
        result.setChallenger(new UserBuilder().id(challenger).build());
        result.setTarget(new UserBuilder().id(target).build());
        result.setContainerInstance(new ContainerInstanceBuilder().id(containerInstance).build());
        result.setContent(new ContentBuilder().id(content).build());
        return result;
    }
}
