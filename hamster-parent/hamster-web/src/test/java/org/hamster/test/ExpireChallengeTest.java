package org.hamster.test;


import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.hamster.model.def.Definition;
import org.hamster.model.runtime.ChallengeStatus;
import org.hamster.model.runtime.ContainerInstance;
import org.hamster.model.runtime.ContainerInstanceBuilder;
import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.InstanceBuilder;
import org.hamster.model.user.User;
import org.hamster.scheduler.ExpiryScheduler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ExpireChallengeTest
    extends AbstractHamsterTest
{
    @Autowired
    private ExpiryScheduler expSched;


    @Test
    public void expireChallengeTest()
    {
        User challenger = us.findOne(1L);
        User target = us.findOne(2L);
        Definition def = ds.findOne(1L);
        ContainerInstance ci = new ContainerInstanceBuilder().containerClass("singleInstanceContainer")
                                                             .build();
        cis.save(ci);

        Date today = new Date();
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("votingType", "public");
        Instance instance = new InstanceBuilder().challenger(challenger)
                                                 .target(target)
                                                 .containerInstance(ci)
                                                 .status(ChallengeStatus.ACTIVE)
                                                 .definition(def)
                                                 .submittedOn(today)
                                                 .parameters(params)
                                                 .build();
        int challengerCredsBeforePricing = challenger.getCredits();
        instance = is.createNew(instance);
        int count = ((Collection<Instance>)is.findAll()).size();
        // verify challenge is persisted and is in correct state
        assertEquals(count, 1);
        assertEquals(ChallengeStatus.ACTIVE, instance.getStatus());

        int challengerCredsAfterPricing = us.findOne(challenger.getId()).getCredits();
        // verify that credits are taken from the account of the challenger
        assertEquals(challengerCredsBeforePricing - def.getCost(), challengerCredsAfterPricing);

        instance.setExpiresOn(new Date());
        is.save(instance);
        expSched.handleExpiredChallenges();

        assertEquals(challengerCredsBeforePricing, us.findOne(challenger.getId()).getCredits());
        assertEquals(ChallengeStatus.EXPIRED, is.findOne(instance.getId()).getStatus());
    }
}
