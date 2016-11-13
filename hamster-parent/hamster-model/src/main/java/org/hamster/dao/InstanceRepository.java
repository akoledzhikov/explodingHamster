package org.hamster.dao;

import org.hamster.model.runtime.Instance;
import org.springframework.data.repository.CrudRepository;

public interface InstanceRepository extends CrudRepository<Instance, Long>
{

}
