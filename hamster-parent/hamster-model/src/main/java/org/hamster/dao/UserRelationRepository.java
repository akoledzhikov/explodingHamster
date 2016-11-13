package org.hamster.dao;


import org.hamster.model.user.UserRelation;
import org.springframework.data.repository.CrudRepository;


public interface UserRelationRepository
    extends CrudRepository<UserRelation, Long>
{
 
}
