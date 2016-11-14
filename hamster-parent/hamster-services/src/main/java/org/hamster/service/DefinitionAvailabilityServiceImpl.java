package org.hamster.service;


import org.hamster.dao.DefinitionAvailabilityRepository;
import org.hamster.model.user.DefinitionAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DefinitionAvailabilityServiceImpl
{
    @Autowired
    private DefinitionAvailabilityRepository defAvailRepo;


    public Iterable<DefinitionAvailability> findAll()
    {
        return defAvailRepo.findAll();
    }


    public Iterable<DefinitionAvailability> findAll(Iterable<Long> arg0)
    {
        return defAvailRepo.findAll(arg0);
    }


    public DefinitionAvailability findOne(Long arg0)
    {
        return defAvailRepo.findOne(arg0);
    }


    public <S extends DefinitionAvailability> S save(S arg0)
    {
        return defAvailRepo.save(arg0);
    }
}
