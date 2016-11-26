package org.hamster.container;


import java.util.Date;

import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.VotingType;
import org.springframework.stereotype.Component;


@Component("votingRule")
public class VotingRule
    implements Rule
{

    public void apply(Instance challengeInstance, ChallengeEvent event)
    {
        if (ChallengeEvent.CONTENT_UPLOADED.equals(event))
        {
            VotingType vt = VotingType.valueOf(challengeInstance.getParameters().get("votingType").toString().toUpperCase());
            challengeInstance.setVotingType(vt);
            challengeInstance.setVotingStartedOn(new Date()); // this 
        }
    }
}
