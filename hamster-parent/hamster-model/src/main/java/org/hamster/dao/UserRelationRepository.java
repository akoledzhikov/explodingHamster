package org.hamster.dao;


import java.util.Collection;

import org.hamster.model.user.User;
import org.hamster.model.user.UserRelation;
import org.springframework.data.repository.CrudRepository;


public interface UserRelationRepository
    extends CrudRepository<UserRelation, Long>
{
    public Collection<UserRelation> findByUser(User user);
}
