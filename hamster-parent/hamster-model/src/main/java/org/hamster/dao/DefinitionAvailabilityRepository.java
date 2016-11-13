package org.hamster.dao;


import org.hamster.model.user.DefinitionAvailability;
import org.springframework.data.repository.CrudRepository;


public interface DefinitionAvailabilityRepository
    extends CrudRepository<DefinitionAvailability, Long>
{

}
