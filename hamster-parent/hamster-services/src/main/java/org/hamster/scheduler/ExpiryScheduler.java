package org.hamster.scheduler;


import java.util.Collection;
import java.util.Date;

import org.hamster.container.ChallengeEngine;
import org.hamster.model.runtime.ChallengeStatus;
import org.hamster.model.runtime.Instance;
import org.hamster.service.InstanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ExpiryScheduler
{
    @Autowired
    private ChallengeEngine engine;

    @Autowired
    private InstanceServiceImpl is;


    @Scheduled(initialDelay = 60000, fixedRate = 60000)
    public void handleExpiredChallenges()
    {
        Collection<Instance> expired = is.findChallengesForExpiry(new Date());
        for (Instance i : expired)
        {
            i.setStatus(ChallengeStatus.EXPIRED);
            is.expire(i);
        }
    }
}
