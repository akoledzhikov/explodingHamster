package org.hamster.container;


import org.hamster.model.runtime.Instance;
import org.hamster.model.user.User;
import org.springframework.stereotype.Component;

@Component("pricingRule")
public class PricingRule
    implements Rule
{

    public void apply(Instance challengeInstance, ChallengeEvent event)
    {
        if (ChallengeEvent.CREATED.equals(event))
        {
            User challenger = challengeInstance.getChallenger();
            challenger.subtractCredits(challengeInstance.getDefinition().getCost());
        }
        else if (ChallengeEvent.REJECTED.equals(event) || ChallengeEvent.EXPIRED.equals(event))
        {
            User challenger = challengeInstance.getChallenger();
            challenger.addCredits(challengeInstance.getDefinition().getCost());
        }
    }
}
