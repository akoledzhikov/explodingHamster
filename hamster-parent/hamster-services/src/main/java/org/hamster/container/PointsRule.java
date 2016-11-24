package org.hamster.container;


import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.RuleInstance;
import org.springframework.stereotype.Service;

@Service("pointsRule")
public class PointsRule
    implements Rule
{

    public void apply(Instance challengeInstance, RuleInstance ruleInstance, ChallengeEvent event)
    {
        // TODO Auto-generated method stub

    }

}
