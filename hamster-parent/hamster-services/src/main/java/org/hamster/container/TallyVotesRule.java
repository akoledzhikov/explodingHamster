package org.hamster.container;


import java.util.Collection;

import org.hamster.model.runtime.ChallengeStatus;
import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.Vote;
import org.hamster.service.InstanceServiceImpl;
import org.hamster.service.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("tallyVotesRule")
public class TallyVotesRule
    implements Rule
{
    @Autowired
    private ChallengeEngine engine;

    @Autowired
    private InstanceServiceImpl is;

    @Autowired
    private VoteServiceImpl vs;


    @Override
    public void apply(Instance i, ChallengeEvent event)
    {
        if (ChallengeEvent.VOTING_ENDS.equals(event))
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
                int negativeCount = 0;
                for (Vote v : votes)
                {
                    if (v.isPositive())
                    {
                        positiveCount++;
                    }
                    else
                    {
                        negativeCount++;
                    }
                }

                if (positiveCount >= negativeCount)
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
