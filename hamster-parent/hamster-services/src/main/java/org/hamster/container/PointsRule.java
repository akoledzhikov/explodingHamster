package org.hamster.container;


import java.util.Collection;

import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.Vote;
import org.hamster.model.runtime.VotingType;
import org.hamster.service.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("pointsRule")
public class PointsRule
    implements Rule
{
    @Autowired
    private VoteServiceImpl vs;


    public void apply(Instance challengeInstance, ChallengeEvent event)
    {
        if (ChallengeEvent.SUCCESS.equals(event))
        {
            challengeInstance.getTarget().addPermanentPoints(100);
            if (VotingType.PUBLIC.equals(challengeInstance.getVotingType()))
            {
                challengeInstance.getTarget().addMonthlyPoints(100);
            }
        }
        else if (ChallengeEvent.CONTENT_UPLOADED.equals(event))
        {
            challengeInstance.getChallenger().addPermanentPoints(10); // TODO points based on challenge and
                                                                      // rules.
        }

        if (ChallengeEvent.SUCCESS.equals(event) || ChallengeEvent.FAIL.equals(event))
        {
            boolean positive = ChallengeEvent.SUCCESS.equals(event);
            Collection<Vote> votes = vs.findByChallenge(challengeInstance);
            for (Vote vote : votes)
            {
                if (vote.isPositive() == positive)
                {
                    vote.getUser().addPermanentPoints(10);
                }
            }
        }
    }
}
