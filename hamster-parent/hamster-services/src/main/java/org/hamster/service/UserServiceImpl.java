package org.hamster.service;


import org.hamster.dao.UserRepository;
import org.hamster.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
{
    @Autowired
    private UserRepository userRepo;


    public Iterable<User> findAll()
    {
        return userRepo.findAll();
    }
    
    public Iterable<User> findAll(Iterable<Long> arg0)
    {
        return userRepo.findAll(arg0);
    }


    public User findOne(Long arg0)
    {
        return userRepo.findOne(arg0);
    }


    public <S extends User> S save(S arg0)
    {
        return userRepo.save(arg0);
    }

}
