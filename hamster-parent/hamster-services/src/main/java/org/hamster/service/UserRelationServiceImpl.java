package org.hamster.service;


import org.hamster.dao.UserRelationRepository;
import org.hamster.model.user.UserRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserRelationServiceImpl
{
    @Autowired
    private UserRelationRepository urRepo;


    public Iterable<UserRelation> findAll()
    {
        return urRepo.findAll();
    }


    public Iterable<UserRelation> findAll(Iterable<Long> arg0)
    {
        return urRepo.findAll(arg0);
    }


    public UserRelation findOne(Long arg0)
    {
        return urRepo.findOne(arg0);
    }


    public <S extends UserRelation> S save(S arg0)
    {
        return urRepo.save(arg0);
    }

}
