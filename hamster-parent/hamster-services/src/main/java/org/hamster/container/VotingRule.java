package org.hamster.container;


import java.util.Date;

import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.RuleInstance;
import org.hamster.model.runtime.VotingType;
import org.springframework.stereotype.Service;


@Service("votingRule")
public class VotingRule
    implements Rule
{

    public void apply(Instance challengeInstance, RuleInstance ruleInstance, ChallengeEvent event)
    {
        if (ChallengeEvent.CONTENT_UPLOADED.equals(event))
        {
            VotingType vt = VotingType.valueOf(ruleInstance.getParameters().get("type").toString().toUpperCase());
            challengeInstance.setVotingType(vt);
            challengeInstance.setVotingStartedOn(new Date()); // this 
        }
    }
}
