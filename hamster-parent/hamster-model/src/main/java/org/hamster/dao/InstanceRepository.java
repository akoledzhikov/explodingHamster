package org.hamster.dao;


import java.util.Collection;
import java.util.Date;

import org.hamster.model.runtime.Instance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface InstanceRepository
    extends CrudRepository<Instance, Long>
{

    @Query("SELECT i FROM Instance i where i.expiresOn < :date AND i.status = 'ACTIVE'")
    Collection<Instance> findChallengesForExpiry(@Param("date") Date date);
    @Query("SELECT i FROM Instance i where i.votingEndsOn < :date AND i.status = 'VOTING'")
    Collection<Instance> findChallengesForVoteTallying(@Param("date") Date date);

}
