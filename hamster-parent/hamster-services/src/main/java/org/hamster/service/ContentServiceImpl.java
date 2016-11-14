package org.hamster.service;

import org.hamster.dao.ContentRepository;
import org.hamster.model.runtime.ContainerInstance;
import org.hamster.model.runtime.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl
{
    @Autowired
    private ContentRepository contentRepo;

    public Iterable<Content> findAll()
    {
        return contentRepo.findAll();
    }
    
    public Iterable<Content> findAll(Iterable<Long> arg0)
    {
        return contentRepo.findAll(arg0);
    }

    public Content findOne(Long arg0)
    {
        return contentRepo.findOne(arg0);
    }

    public <S extends Content> S save(S arg0)
    {
        return contentRepo.save(arg0);
    }
    
    
}
