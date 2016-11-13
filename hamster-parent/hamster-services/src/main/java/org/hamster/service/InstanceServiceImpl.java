package org.hamster.service;

import org.hamster.dao.InstanceRepository;
import org.hamster.model.runtime.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstanceServiceImpl
{
    @Autowired
    private InstanceRepository instRepo;

    public Iterable<Instance> findAll()
    {
        return instRepo.findAll();
    }

    public Instance findOne(Long arg0)
    {
        return instRepo.findOne(arg0);
    }

    public <S extends Instance> S save(S arg0)
    {
        return instRepo.save(arg0);
    }
    
    
}
