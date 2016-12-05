package org.hamster.container;


import java.util.Calendar;
import java.util.Date;

import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.VotingType;
import org.springframework.stereotype.Component;


@Component("votingTypeRule")
public class VotingTypeRule
    implements Rule
{

    public void apply(Instance challengeInstance, ChallengeEvent event)
    {
        if (ChallengeEvent.CONTENT_UPLOADED.equals(event))
        {
            VotingType vt = VotingType.valueOf(challengeInstance.getParameters()
                                                                .get("votingType")
                                                                .toString()
                                                                .toUpperCase());
            Integer votingDays = (Integer)challengeInstance.getParameters().get("votingDays");
            if (votingDays == null)
            {
                votingDays = new Integer(1);
            }

            challengeInstance.setVotingType(vt);
            challengeInstance.setVotingStartedOn(new Date()); // this
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, votingDays);
            challengeInstance.setVotingEndsOn(cal.getTime());
        }
    }
}
