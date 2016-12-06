package org.hamster.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.hamster.api.exc.DuplicateVoteException;
import org.hamster.container.ChallengeEvent;
import org.hamster.model.def.Definition;
import org.hamster.model.runtime.ChallengeStatus;
import org.hamster.model.runtime.ContainerInstance;
import org.hamster.model.runtime.ContainerInstanceBuilder;
import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.InstanceBuilder;
import org.hamster.model.runtime.Vote;
import org.hamster.model.runtime.VoteBuilder;
import org.hamster.model.runtime.VotingType;
import org.hamster.model.user.User;
import org.hamster.scheduler.VotingScheduler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-context.xml"})
public class BasicFlowTest
    extends AbstractHamsterTest
{
    @Autowired
    private VotingScheduler votingScheduler;


    @Test
    public void sillyTest()
        throws DuplicateVoteException
    {
        User challenger = us.findOne(1L);
        User target = us.findOne(1L);
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
        instance = is.save(instance);
        int count = ((Collection<Instance>)is.findAll()).size();
        // verify challenge is persisted and is in correct state
        assertEquals(count, 1);
        assertEquals(ChallengeStatus.ACTIVE, instance.getStatus());

        int challengerCredsBeforePricing = challenger.getCredits();
        engine.handleChallengeEvent(instance.getId(), ChallengeEvent.CREATED);
        int challengerCredsAfterPricing = us.findOne(challenger.getId()).getCredits();
        // verify that credits are taken from the account of the challenger
        assertEquals(challengerCredsBeforePricing - def.getCost(), challengerCredsAfterPricing);

        // simulate content upload
        engine.handleChallengeEvent(instance.getId(), ChallengeEvent.CONTENT_UPLOADED);
        instance = is.findOne(instance.getId());
        assertEquals(ChallengeStatus.VOTING, instance.getStatus());
        assertEquals(VotingType.PUBLIC, instance.getVotingType());
        assertNotNull(instance.getVotingStartedOn());
        assertNotNull(instance.getVotingEndsOn());

        User voter1 = us.findOne(1L);
        User voter2 = us.findOne(3L);
        User voter3 = us.findOne(4L);

        Vote v1 = new VoteBuilder().challenge(instance).positive(true).user(voter1).build();
        vs.save(v1);
        engine.handleChallengeEvent(instance.getId(), ChallengeEvent.VOTED_ON);
        Vote v2 = new VoteBuilder().challenge(instance).positive(true).user(voter2).build();
        vs.save(v2);
        engine.handleChallengeEvent(instance.getId(), ChallengeEvent.VOTED_ON);
        Vote v3 = new VoteBuilder().challenge(instance).positive(false).user(voter3).build();
        vs.save(v3);
        engine.handleChallengeEvent(instance.getId(), ChallengeEvent.VOTED_ON);

        // in order to be able to tally votes.
        instance.setVotingEndsOn(new Date());
        is.save(instance);
        votingScheduler.handleVotingEnds();
        // check points and credits
    }
}
