package org.hamster.service;


import org.hamster.dao.DefinitionRepository;
import org.hamster.model.def.Definition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DefinitionServiceImpl
{
    @Autowired
    private DefinitionRepository defRepo;


    public Iterable<Definition> findAll()
    {
        return defRepo.findAll();
    }


    public Iterable<Definition> findAll(Iterable<Long> arg0)
    {
        return defRepo.findAll(arg0);
    }


    public Definition findOne(Long arg0)
    {
        return defRepo.findOne(arg0);
    }


    public <S extends Definition> S save(S arg0)
    {
        return defRepo.save(arg0);
    }
}
