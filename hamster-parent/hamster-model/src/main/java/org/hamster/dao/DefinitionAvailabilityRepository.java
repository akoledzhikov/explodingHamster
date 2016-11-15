package org.hamster.dao;


import java.util.Collection;

import org.hamster.model.user.DefinitionAvailability;
import org.hamster.model.user.User;
import org.springframework.data.repository.CrudRepository;


public interface DefinitionAvailabilityRepository
    extends CrudRepository<DefinitionAvailability, Long>
{
    public Collection<DefinitionAvailability> findByUser(User user);
}
