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

    @Autowired
    private VoteServiceImpl vs;


    @Scheduled(initialDelay = 30000, fixedRate = 60000)
    public void handleVotingEnds()
    {
        Collection<Instance> toBeTallied = is.findChallengesForVoteTallying(new Date());
        for (Instance i : toBeTallied)
        {
            Collection<Vote> votes = vs.findByChallenge(i);
            if (votes.isEmpty())
            {
                i.setStatus(ChallengeStatus.EXPIRED);
                is.save(i);
                engine.handleChallengeEvent(i.getId(), ChallengeEvent.EXPIRED);
            }
            else
            {
                int positiveCount = 0;
                for (Vote v : votes)
                {
                    if (v.isPositive())
                    {
                        positiveCount++;
                    }
                }

                if (positiveCount >= votes.size() / 2)
                {
                    i.setStatus(ChallengeStatus.SUCESSFUL);
                    is.save(i);
                    engine.handleChallengeEvent(i.getId(), ChallengeEvent.SUCCESS);
                }
                else
                {
                    i.setStatus(ChallengeStatus.FAILED);
                    is.save(i);
                    engine.handleChallengeEvent(i.getId(), ChallengeEvent.FAIL);
                }
            }
        }
    }
}
