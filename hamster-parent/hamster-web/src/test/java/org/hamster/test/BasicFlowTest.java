package org.hamster.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-context.xml"})
public class BasicFlowTest
    extends AbstractHamsterTest
{
    @Test   
    public void sillyTest()
    {
        System.out.println("whee");
    }
}
