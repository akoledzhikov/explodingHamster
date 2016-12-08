package org.hamster.test;


import java.util.ArrayList;
import java.util.List;

import org.hamster.container.ChallengeEngine;
import org.hamster.model.def.Definition;
import org.hamster.model.def.DefinitionBuilder;
import org.hamster.model.user.DefinitionAvailability;
import org.hamster.model.user.DefinitionAvailabilityBuilder;
import org.hamster.model.user.User;
import org.hamster.model.user.UserBuilder;
import org.hamster.model.user.UserRelation;
import org.hamster.service.ContainterInstanceServiceImpl;
import org.hamster.service.DefinitionAvailabilityServiceImpl;
import org.hamster.service.DefinitionServiceImpl;
import org.hamster.service.InstanceServiceImpl;
import org.hamster.service.UserRelationServiceImpl;
import org.hamster.service.UserServiceImpl;
import org.hamster.service.VoteServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-context.xml"})
public abstract class AbstractHamsterTest
{
    @Autowired
    protected UserServiceImpl us;

    @Autowired
    protected UserRelationServiceImpl urs;

    @Autowired
    protected DefinitionServiceImpl ds;

    @Autowired
    protected DefinitionAvailabilityServiceImpl das;

    @Autowired
    protected ContainterInstanceServiceImpl cis;

    @Autowired
    protected InstanceServiceImpl is;

    @Autowired
    protected VoteServiceImpl vs;

    @Autowired
    protected ChallengeEngine engine;


    @Before
    public void createNonRuntimeData()
    {
        System.out.println("Importing non-runtime data");
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
                                               .cost(10)
                                               .points(100)
                                               .build();

        Definition d2 = new DefinitionBuilder().name("Hamster cosplay")
                                               .category("Art")
                                               .description("Dress as a hamster")
                                               .cost(10)
                                               .points(100)
                                               .build();

        Definition d3 = new DefinitionBuilder().name("Hamster song")
                                               .category("Singing")
                                               .description("Sing the intro song to \"Orochuban Ebichu\"")
                                               .cost(10)
                                               .points(100)
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

        System.out.println("Done importing non-runtime data");
    }
}
