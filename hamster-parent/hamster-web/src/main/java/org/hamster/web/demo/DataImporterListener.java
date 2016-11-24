package org.hamster.web.demo;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hamster.model.def.Definition;
import org.hamster.model.def.DefinitionBuilder;
import org.hamster.model.runtime.ChallengeStatus;
import org.hamster.model.runtime.ContainerInstance;
import org.hamster.model.runtime.ContainerInstanceBuilder;
import org.hamster.model.runtime.Instance;
import org.hamster.model.runtime.InstanceBuilder;
import org.hamster.model.runtime.RuleInstance;
import org.hamster.model.runtime.RuleInstanceBuilder;
import org.hamster.model.runtime.Vote;
import org.hamster.model.runtime.VoteBuilder;
import org.hamster.model.user.DefinitionAvailability;
import org.hamster.model.user.DefinitionAvailabilityBuilder;
import org.hamster.model.user.User;
import org.hamster.model.user.UserBuilder;
import org.hamster.model.user.UserRelation;
import org.hamster.service.ContainterInstanceServiceImpl;
import org.hamster.service.DefinitionAvailabilityServiceImpl;
import org.hamster.service.DefinitionServiceImpl;
import org.hamster.service.InstanceServiceImpl;
import org.hamster.service.RuleInstanceServiceImpl;
import org.hamster.service.UserRelationServiceImpl;
import org.hamster.service.UserServiceImpl;
import org.hamster.service.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataImporterListener
{
    @Autowired
    private UserServiceImpl us;

    @Autowired
    private UserRelationServiceImpl urs;

    @Autowired
    private DefinitionServiceImpl ds;

    @Autowired
    private DefinitionAvailabilityServiceImpl das;

    @Autowired
    private ContainterInstanceServiceImpl cis;

    @Autowired
    private RuleInstanceServiceImpl ris;
    
    @Autowired
    private InstanceServiceImpl is;
    
    @Autowired
    private VoteServiceImpl vs;


    @PostConstruct
    public void importData()
    {
        User alex = new UserBuilder().mail("alex@hamster.com")
                                     .credits(30)
                                     .permanentPoints(15)
                                     .currentMonthlyPoints(10)
                                     .firstName("Alex")
                                     .lastName("K.")
                                     .build();
        User stefo = new UserBuilder().mail("stefo@hamster.com")
                                      .credits(30)
                                      .permanentPoints(15)
                                      .currentMonthlyPoints(10)
                                      .firstName("Stefo")
                                      .lastName("G.")
                                      .build();
        User yulia = new UserBuilder().mail("yulia@hamster.com")
                                      .credits(30)
                                      .permanentPoints(15)
                                      .currentMonthlyPoints(10)
                                      .firstName("Yulia")
                                      .lastName("S.")
                                      .build();
        User irina = new UserBuilder().mail("irina@hamster.com")
                                      .credits(30)
                                      .permanentPoints(15)
                                      .currentMonthlyPoints(10)
                                      .firstName("Irina")
                                      .lastName("S.")
                                      .build();
        User pinko = new UserBuilder().mail("pinko@hamster.com")
                                      .credits(30)
                                      .permanentPoints(15)
                                      .currentMonthlyPoints(10)
                                      .firstName("Pinko")
                                      .lastName("P.")
                                      .build();
        List<User> users = new ArrayList<>();
        users.add(alex);
        users.add(stefo);
        users.add(yulia);
        users.add(irina);
        for (User u : users)
        {
            us.save(u);
        }

        // Pinko is no friend of ours..
        us.save(pinko);

        for (int i = 0; i < users.size(); i++)
        {
            for (int j = i + 1; j < users.size(); j++)
            {
                UserRelation ur1 = new UserRelation(users.get(i), users.get(j));
                UserRelation ur2 = new UserRelation(users.get(j), users.get(i));
                urs.save(ur1);
                urs.save(ur2);
            }
        }

        Definition d1 = new DefinitionBuilder().name("Hamster painting")
                                               .category("Art")
                                               .description("Paint a hamster")
                                               .build();

        Definition d2 = new DefinitionBuilder().name("Hamster cosplay")
                                               .category("Art")
                                               .description("Dress as a hamster")
                                               .build();

        Definition d3 = new DefinitionBuilder().name("Hamster song")
                                               .category("Singing")
                                               .description("Sing the intro song to \"Orochuban Ebichu\"")
                                               .build();

        d1 = ds.save(d1);
        d2 = ds.save(d2);
        d3 = ds.save(d3);

        List<Definition> defs = new ArrayList<Definition>();
        defs.add(d1);
        defs.add(d2);
        defs.add(d3);
        for (User user : users)
        {
            for (Definition def : defs)
            {
                DefinitionAvailability da = new DefinitionAvailabilityBuilder().user(user).def(def).build();
                das.save(da);
            }
        }

        ContainerInstance ci1 = new ContainerInstanceBuilder().containerClass("singleInstanceContainer")
                                                              .build();
        ContainerInstance ci2 = new ContainerInstanceBuilder().containerClass("singleInstanceContainer")
                                                              .build();
        ContainerInstance ci3 = new ContainerInstanceBuilder().containerClass("singleInstanceContainer")
                                                              .build();
        ContainerInstance ci4 = new ContainerInstanceBuilder().containerClass("singleInstanceContainer")
                                                              .build();

        ci1 = cis.save(ci1);
        ci2 = cis.save(ci2);
        ci3 = cis.save(ci3);
        ci4 = cis.save(ci4);
        List<ContainerInstance> containerInstances = new ArrayList<ContainerInstance>();
        containerInstances.add(ci1);
        containerInstances.add(ci2);
        containerInstances.add(ci3);
        containerInstances.add(ci4);

        for (ContainerInstance ci : containerInstances)
        {
            HashMap<String, Object> votingMap = new HashMap<>();
            votingMap.put("type", "public");
            RuleInstance voting = new RuleInstanceBuilder().containerInstance(ci)
                                                           .ruleClass("votingRule")
                                                           .parameters(votingMap)
                                                           .build();
            ris.save(voting);

            HashMap<String, Object> pointsMap = new HashMap<>();
            pointsMap.put("points", 100);
            RuleInstance points = new RuleInstanceBuilder().containerInstance(ci)
                                                           .ruleClass("pointsRule")
                                                           .parameters(pointsMap)
                                                           .build();
            ris.save(points);
        }

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 24);
        Date tomorrow = cal.getTime();
        Instance i1 = new InstanceBuilder().challenger(alex)
                                           .target(stefo)
                                           .containerInstance(ci1)
                                           .status(ChallengeStatus.ACTIVE)
                                           .definition(d1)
                                           .submittedOn(today)
                                           .build();
        
        Instance i2 = new InstanceBuilder().challenger(stefo)
                        .target(yulia)
                        .containerInstance(ci2)
                        .status(ChallengeStatus.VOTING)
                        .definition(d2)
                        .submittedOn(today)
                        .votingStartedOn(today)
                        .build();
        
        Instance i3 = new InstanceBuilder().challenger(yulia)
                        .target(irina)
                        .containerInstance(ci3)
                        .status(ChallengeStatus.SUCESSFUL)
                        .definition(d3)
                        .submittedOn(today)
                        .votingStartedOn(today)
                        .completedOn(tomorrow)
                        .build();
        
        Instance i4 = new InstanceBuilder().challenger(irina)
                        .target(alex)
                        .containerInstance(ci4)
                        .status(ChallengeStatus.FAILED)
                        .definition(d3)
                        .submittedOn(today)
                        .votingStartedOn(today)
                        .completedOn(tomorrow)
                        .build();
        
        is.save(i1);
        is.save(i2);
        is.save(i3);
        is.save(i4);
        
        Vote v1 = new VoteBuilder().challenge(i2).positive(true).user(alex).build();
        Vote v2 = new VoteBuilder().challenge(i3).positive(true).user(alex).build();
        Vote v3 = new VoteBuilder().challenge(i3).positive(true).user(yulia).build();
        Vote v4 = new VoteBuilder().challenge(i3).positive(true).user(stefo).build();
        Vote v5 = new VoteBuilder().challenge(i4).positive(true).user(yulia).build();
        Vote v6 = new VoteBuilder().challenge(i4).positive(false).user(irina).build();
        Vote v7 = new VoteBuilder().challenge(i4).positive(false).user(stefo).build();
        
        vs.save(v1);
        vs.save(v2);
        vs.save(v3);
        vs.save(v4);
        vs.save(v5);
        vs.save(v6);
        vs.save(v7);
        // content
    }
}
