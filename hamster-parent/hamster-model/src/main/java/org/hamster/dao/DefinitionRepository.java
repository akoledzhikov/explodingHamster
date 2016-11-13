package org.hamster.dao;


import org.hamster.model.def.Definition;
import org.springframework.data.repository.CrudRepository;


public interface DefinitionRepository
    extends CrudRepository<Definition, Long>
{

}
