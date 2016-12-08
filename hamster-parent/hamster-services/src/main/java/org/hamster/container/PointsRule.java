package org.hamster.container;


import java.util.Collection;

import org.hamster.model.def.Definition;
import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.Vote;
import org.hamster.model.runtime.VotingType;
import org.hamster.service.UserServiceImpl;
import org.hamster.service.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("pointsRule")
public class PointsRule
    implements Rule
{
    @Autowired
    private VoteServiceImpl vs;
    
    @Autowired
    private UserServiceImpl us;


    public void apply(Instance challengeInstance, ChallengeEvent event)
    {
        Definition def = challengeInstance.getDefinition();
        if (ChallengeEvent.SUCCESS.equals(event))
        {
            challengeInstance.getTarget().addPermanentPoints(def.getPoints());
            if (VotingType.PUBLIC.equals(challengeInstance.getVotingType()))
            {
                challengeInstance.getTarget().addMonthlyPoints(def.getPoints());
            }
            
            us.save(challengeInstance.getTarget());
        }
        else if (ChallengeEvent.CONTENT_UPLOADED.equals(event))
        {
            challengeInstance.getChallenger().addPermanentPoints(def.getPoints() / 10);
            us.save(challengeInstance.getChallenger());
        }

        if (ChallengeEvent.SUCCESS.equals(event) || ChallengeEvent.FAIL.equals(event))
        {
            boolean positive = ChallengeEvent.SUCCESS.equals(event);
            Collection<Vote> votes = vs.findByChallenge(challengeInstance);
            for (Vote vote : votes)
            {
                if (vote.isPositive() == positive)
                {
                    vote.getUser().addPermanentPoints(def.getPoints() / 10);
                    us.save(vote.getUser());
                }
            }
        }
    }
}
