package org.hamster.service;


import org.hamster.dao.DefinitionRepository;
import org.hamster.model.def.Definition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefinitionAvailabilityServiceImpl
{
    @Autowired
    private DefinitionRepository defAvailRepo;

    public Iterable<Definition> findAll()
    {
        return defAvailRepo.findAll();
    }

    public Definition findOne(Long arg0)
    {
        return defAvailRepo.findOne(arg0);
    }

    public <S extends Definition> S save(S arg0)
    {
        return defAvailRepo.save(arg0);
    }
}
