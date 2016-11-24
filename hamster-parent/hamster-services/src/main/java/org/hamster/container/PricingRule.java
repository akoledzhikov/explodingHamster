package org.hamster.container;


import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.RuleInstance;
import org.hamster.model.user.User;


public class PricingRule
    implements Rule
{

    public void apply(Instance challengeInstance, RuleInstance ruleInstance, ChallengeEvent event)
    {
        if (ChallengeEvent.CREATED.equals(event))
        {
            User challenger = challengeInstance.getChallenger();
            challenger.subtractCredits(10); // TODO different cost for different challenges based on .. stuff
        }
        else if (ChallengeEvent.REJECTED.equals(event) || ChallengeEvent.EXPIRED.equals(event))
        {
            User challenger = challengeInstance.getChallenger();
            challenger.addCredits(10); // TODO different cost for different challenges based on .. stuff
        }
    }
}
