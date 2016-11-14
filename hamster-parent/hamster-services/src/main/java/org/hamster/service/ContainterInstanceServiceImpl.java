package org.hamster.service;


import org.hamster.dao.ContainerInstanceRepository;
import org.hamster.model.runtime.ContainerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContainterInstanceServiceImpl
{
    @Autowired
    private ContainerInstanceRepository containerRepo;


    public Iterable<ContainerInstance> findAll()
    {
        return containerRepo.findAll();
    }


    public Iterable<ContainerInstance> findAll(Iterable<Long> arg0)
    {
        return containerRepo.findAll(arg0);
    }


    public ContainerInstance findOne(Long arg0)
    {
        return containerRepo.findOne(arg0);
    }


    public <S extends ContainerInstance> S save(S arg0)
    {
        return containerRepo.save(arg0);
    }
}
