package org.hamster.container;


import org.hamster.model.runtime.Instance;
import org.hamster.service.InstanceServiceImpl;
import org.hamster.services.util.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ChallengeEngine
{
    @Autowired
    private InstanceServiceImpl is;


    public void handleChallengeEvent(long challengeId, ChallengeEvent eventType)
    {
        Instance ci = is.findOne(challengeId);
        Container container = ApplicationContextUtil.getInstance().getBean(ci.getContainerInstance()
                                                                             .getContainerClass(),
                                                                           Container.class);
        for (Rule rule : container.getRules(ci))
        {
            rule.apply(ci, eventType);
        }
    }
}
