package org.hamster.web.dto;


import org.hamster.model.runtime.InstanceBuilder;
import org.hamster.model.runtime.Vote;
import org.hamster.model.runtime.VoteBuilder;
import org.hamster.model.user.UserBuilder;


public class VoteDTO
{
    private long id;

    private long user;

    private long challenge;

    private boolean positive;


    public VoteDTO()
    {
        super();
    }


    public VoteDTO(Vote vote)
    {
        this.id = vote.getId();
        this.user = vote.getUser().getId();
        this.challenge = vote.getChallenge().getId();
        this.positive = vote.isPositive();
    }


    public long getId()
    {
        return id;
    }


    public void setId(long id)
    {
        this.id = id;
    }


    public long getUser()
    {
        return user;
    }


    public void setUser(long user)
    {
        this.user = user;
    }


    public long getChallenge()
    {
        return challenge;
    }


    public void setChallenge(long challenge)
    {
        this.challenge = challenge;
    }


    public boolean isPositive()
    {
        return positive;
    }


    public void setPositive(boolean positive)
    {
        this.positive = positive;
    }
    
    
    public Vote toEntity() {
        Vote result = new VoteBuilder().id(id).positive(positive).build();
        result.setChallenge(new InstanceBuilder().id(challenge).build());
        result.setUser(new UserBuilder().id(user).build());
        return result;
    }

}
