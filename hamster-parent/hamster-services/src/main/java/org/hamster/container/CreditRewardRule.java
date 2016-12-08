package org.hamster.container;


import org.hamster.model.runtime.Instance;
import org.hamster.model.user.User;
import org.hamster.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("creditRewardRule")
public class CreditRewardRule
    implements Rule
{
    @Autowired
    private UserServiceImpl us;


    @Override
    public void apply(Instance challengeInstance, ChallengeEvent event)
    {
        if (ChallengeEvent.SUCCESS.equals(event))
        {
            User target = challengeInstance.getTarget();
            target.addCredits(challengeInstance.getDefinition().getCost() * 2);
            us.save(target);
        }
    }

}
