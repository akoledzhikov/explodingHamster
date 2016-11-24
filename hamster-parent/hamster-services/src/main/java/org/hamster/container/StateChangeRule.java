package org.hamster.container;


import org.hamster.model.runtime.ChallengeStatus;
import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.RuleInstance;
import org.springframework.stereotype.Service;


@Service("stateChange")
public class StateChangeRule
    implements Rule
{
    public void apply(Instance challengeInstance, RuleInstance ruleInstance, ChallengeEvent event)
    {
        if (ChallengeEvent.CREATED.equals(event))
        {
            challengeInstance.setStatus(ChallengeStatus.ACTIVE);
        }
        else if (ChallengeEvent.CONTENT_UPLOADED.equals(event))
        {
            challengeInstance.setStatus(ChallengeStatus.VOTING);
        }
        else if (ChallengeEvent.VOTED_ON.equals(event))
        {
            // ignored
        }
        else if (ChallengeEvent.SUCCESS.equals(event))
        {
            challengeInstance.setStatus(ChallengeStatus.SUCESSFUL);
        }
        else if (ChallengeEvent.FAIL.equals(event))
        {
            challengeInstance.setStatus(ChallengeStatus.FAILED);
        }
        else if (ChallengeEvent.FAIL.equals(event))
        {
            challengeInstance.setStatus(ChallengeStatus.FAILED);
        }
        else if (ChallengeEvent.REJECTED.equals(event))
        {
            challengeInstance.setStatus(ChallengeStatus.REJECTED);
        }
        else if (ChallengeEvent.EXPIRED.equals(event))
        {
            challengeInstance.setStatus(ChallengeStatus.EXPIRED);
        }
    }

}
