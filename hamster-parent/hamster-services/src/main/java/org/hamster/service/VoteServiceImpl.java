package org.hamster.service;


import java.util.Collection;

import org.hamster.api.exc.DuplicateVoteException;
import org.hamster.dao.VoteRepository;
import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.Vote;
import org.hamster.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VoteServiceImpl
{
    @Autowired
    private VoteRepository voteRepo;


    public Iterable<Vote> findAll()
    {
        return voteRepo.findAll();
    }


    public Iterable<Vote> findAll(Iterable<Long> arg0)
    {
        return voteRepo.findAll(arg0);
    }


    public Collection<Vote> findByChallenge(Instance instance)
    {
        return voteRepo.findByChallenge(instance);
    }


    public Vote findByChallengeAndUser(Instance instance, User user)
    {
        return voteRepo.findByChallengeAndUser(instance, user);
    }


    public Vote findOne(Long arg0)
    {
        return voteRepo.findOne(arg0);
    }


    public <S extends Vote> S save(S arg0) throws DuplicateVoteException
    {
        if (voteRepo.findByChallengeAndUser(arg0.getChallenge(), arg0.getUser()) == null)
        {
            return voteRepo.save(arg0);
        }
        else
        {
            throw new DuplicateVoteException();
        }
    }

}
