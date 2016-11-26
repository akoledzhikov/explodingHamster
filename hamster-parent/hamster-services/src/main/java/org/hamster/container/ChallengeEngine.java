package org.hamster.container;


import org.hamster.model.runtime.Instance;
import org.hamster.services.util.ApplicationContextUtil;
import org.springframework.stereotype.Component;


@Component
public class ChallengeEngine
{
    public void handleChallengeEvent(Instance ci, ChallengeEvent eventType)
    {
        Container container = ApplicationContextUtil.getInstance().getBean(ci.getContainerInstance()
                                                                             .getContainerClass(),
                                                                           Container.class);
        for (Rule rule : container.getRules(ci))
        {
            rule.apply(ci, eventType);
        }
    }
}
