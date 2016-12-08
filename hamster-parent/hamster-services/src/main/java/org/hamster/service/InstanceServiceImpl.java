package org.hamster.service;


import java.util.Collection;
import java.util.Date;

import org.hamster.container.ChallengeEngine;
import org.hamster.container.ChallengeEvent;
import org.hamster.dao.InstanceRepository;
import org.hamster.model.runtime.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class InstanceServiceImpl
{
    @Autowired
    private InstanceRepository instRepo;

    @Autowired
    private ChallengeEngine engine;


    public Iterable<Instance> findAll()
    {
        return instRepo.findAll();
    }


    public Iterable<Instance> findAll(Iterable<Long> arg0)
    {
        return instRepo.findAll(arg0);
    }


    public Instance findOne(Long arg0)
    {
        return instRepo.findOne(arg0);
    }


    public <S extends Instance> S save(S arg0)
    {
        return instRepo.save(arg0);
    }


    public Collection<Instance> findChallengesForExpiry(Date date)
    {
        return instRepo.findChallengesForExpiry(date);
    }


    public Collection<Instance> findChallengesForVoteTallying(Date date)
    {
        return instRepo.findChallengesForVoteTallying(date);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public Instance createNew(Instance entity)
    {
        Instance result =  save(entity);
        engine.handleChallengeEvent(result.getId(), ChallengeEvent.CREATED);
        return result;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public Instance reject(Instance entity)
    {
        Instance result =  save(entity);
        engine.handleChallengeEvent(result.getId(), ChallengeEvent.REJECTED);
        return result;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public Instance expire(Instance entity)
    {
        Instance result =  save(entity);
        engine.handleChallengeEvent(result.getId(), ChallengeEvent.EXPIRED);
        return result;
    }

}
