package org.hamster.model.runtime;


import java.util.Date;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import org.hamster.model.def.Definition;
import org.hamster.model.user.User;


@Entity
@Table(name = "ChallengeInstances")
@GeneratePojoBuilder(withSetterNamePattern = "*")
public class Instance
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private ContainerInstance containerInstance;
    @ManyToOne
    private Definition definition;
    @ManyToOne
    private User challenger;
    @ManyToOne
    private User target;

    @Temporal(TemporalType.TIMESTAMP)
    private Date submittedOn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date votingStartedOn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date completedOn;

    @OneToOne
    private Content content;

    @Enumerated(EnumType.STRING)
    private ChallengeStatus status;
    @Enumerated(EnumType.STRING)
    private VotingType votingType;

    @Lob
    private HashMap<String, Object> parameters;


    public long getId()
    {
        return id;
    }


    public void setId(long id)
    {
        this.id = id;
    }


    public ContainerInstance getContainerInstance()
    {
        return containerInstance;
    }


    public void setContainerInstance(ContainerInstance containerInstance)
    {
        this.containerInstance = containerInstance;
    }


    public Definition getDefinition()
    {
        return definition;
    }


    public void setDefinition(Definition definition)
    {
        this.definition = definition;
    }


    public User getChallenger()
    {
        return challenger;
    }


    public void setChallenger(User challenger)
    {
        this.challenger = challenger;
    }


    public User getTarget()
    {
        return target;
    }


    public void setTarget(User target)
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


    public ChallengeStatus getStatus()
    {
        return status;
    }


    public void setStatus(ChallengeStatus status)
    {
        this.status = status;
    }


    public Content getContent()
    {
        return content;
    }


    public void setContent(Content content)
    {
        this.content = content;
    }


    public VotingType getVotingType()
    {
        return votingType;
    }


    public void setVotingType(VotingType votingType)
    {
        this.votingType = votingType;
    }


    public HashMap<String, Object> getParameters()
    {
        return parameters;
    }


    public void setParameters(HashMap<String, Object> parameters)
    {
        this.parameters = parameters;
    }
}
