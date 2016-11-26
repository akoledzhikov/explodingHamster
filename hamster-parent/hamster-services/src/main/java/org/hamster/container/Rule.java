package org.hamster.container;


import org.hamster.model.runtime.Instance;


public interface Rule
{

    public void apply(Instance challengeInstance, ChallengeEvent event);

}
