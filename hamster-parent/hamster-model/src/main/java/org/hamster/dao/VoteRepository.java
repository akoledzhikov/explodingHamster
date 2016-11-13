package org.hamster.dao;


import java.util.Collection;

import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.Vote;
import org.hamster.model.user.User;
import org.springframework.data.repository.CrudRepository;


public interface VoteRepository
    extends CrudRepository<Vote, Long>
{
    public Collection<Vote> findByChallenge(Instance instance);

    public Vote findByChallengeAndUser(Instance instance, User user);
}
