package org.hamster.service;

import org.hamster.dao.RuleInstanceRepository;
import org.hamster.model.runtime.RuleInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleInstanceServiceImpl
{
    @Autowired
    private RuleInstanceRepository ruleRepo;

    public Iterable<RuleInstance> findAll()
    {
        return ruleRepo.findAll();
    }

    public RuleInstance findOne(Long arg0)
    {
        return ruleRepo.findOne(arg0);
    }

    public <S extends RuleInstance> S save(S arg0)
    {
        return ruleRepo.save(arg0);
    }
    
    
}
