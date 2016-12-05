package org.hamster.scheduler;


import java.util.Collection;
import java.util.Date;

import org.hamster.container.ChallengeEngine;
import org.hamster.container.ChallengeEvent;
import org.hamster.model.runtime.ChallengeStatus;
import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.Vote;
import org.hamster.service.InstanceServiceImpl;
import org.hamster.service.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class VotingScheduler
{
    @Autowired
    private ChallengeEngine engine;

    @Autowired
    private InstanceServiceImpl is;


    @Scheduled(initialDelay = 30000, fixedRate = 60000)
    public void handleVotingEnds()
    {
        Collection<Instance> toBeTallied = is.findChallengesForVoteTallying(new Date());
        for (Instance i : toBeTallied)
        {
            engine.handleChallengeEvent(i.getId(), ChallengeEvent.VOTING_ENDS);
        }
    }
}
