package org.hamster.dao;


import org.hamster.model.runtime.ContainerInstance;
import org.springframework.data.repository.CrudRepository;


public interface ContainerInstanceRepository
    extends CrudRepository<ContainerInstance, Long>
{

}
