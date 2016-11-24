package org.hamster.container;


import org.hamster.model.runtime.ContainerInstance;
import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.RuleInstance;
import org.hamster.services.util.ApplicationContextUtil;
import org.springframework.stereotype.Component;


@Component
public class ChallengeEngine
{
    public void handleChallengeEvent(Instance ci, ChallengeEvent eventType)
    {
        ContainerInstance container = ci.getContainerInstance();
        for (RuleInstance ruleInstance : container.getRuleInstances())
        {
            Rule rule = ApplicationContextUtil.getInstance().getBean(ruleInstance.getRuleClass(), Rule.class);
            rule.apply(ci, ruleInstance, eventType);
        }
    }
}
