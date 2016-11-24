package org.hamster.container;


import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.RuleInstance;


public interface Rule
{

    public void apply(Instance challengeInstance, RuleInstance ruleInstance, ChallengeEvent event);

}
