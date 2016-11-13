package org.hamster.dao;

import org.hamster.model.runtime.RuleInstance;
import org.springframework.data.repository.CrudRepository;

public interface RuleInstanceRepository extends CrudRepository<RuleInstance, Long>
{

}
