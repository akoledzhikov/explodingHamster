package org.hamster.container;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamster.model.runtime.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("singleInstanceContainer")
public class SingleInstanceContainer
    implements Container
{
    @Autowired
    @Qualifier("pricingRule")
    private Rule pricingRule;

    @Autowired
    @Qualifier("stateChangeRule")
    private Rule stateChangeRule;

    @Autowired
    @Qualifier("votingRule")
    private Rule votingRule;

    @Autowired
    @Qualifier("pointsRule")
    private Rule pointsRule;


    public Collection<Rule> getRules(Instance challengeInstance)
    {
        List<Rule> result = new ArrayList<>();
        result.add(pointsRule);
        result.add(votingRule);
        result.add(pricingRule);
        result.add(stateChangeRule);
        return result;
    }

}
