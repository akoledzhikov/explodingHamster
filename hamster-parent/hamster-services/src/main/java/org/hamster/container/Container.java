package org.hamster.container;


import java.util.Collection;

import org.hamster.model.runtime.Instance;


public interface Container
{

    public Collection<Rule> getRules(Instance challengeInstance);

}
