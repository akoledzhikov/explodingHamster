package org.hamster.container;


import org.hamster.model.runtime.Instance;
import org.hamster.model.user.User;
import org.hamster.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("pricingRule")
public class PricingRule
    implements Rule
{
    @Autowired
    private UserServiceImpl us;
    
    public void apply(Instance challengeInstance, ChallengeEvent event)
    {
        if (ChallengeEvent.CREATED.equals(event))
        {
            User challenger = challengeInstance.getChallenger();
            challenger.subtractCredits(challengeInstance.getDefinition().getCost());
            us.save(challenger);
        }
        else if (ChallengeEvent.REJECTED.equals(event) || ChallengeEvent.EXPIRED.equals(event))
        {
            User challenger = challengeInstance.getChallenger();
            challenger.addCredits(challengeInstance.getDefinition().getCost());
            us.save(challenger);
        }
    }
}
