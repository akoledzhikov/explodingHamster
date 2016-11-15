package org.hamster.web.dto;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.hamster.model.user.User;


public class UserDTO
{
    private long id;

    private String mail;

    private String firstName;

    private String lastName;

    private int permanentPoints;

    private int currentMonthlyPoints;

    private int credits;

    private Collection<Long> friends;


    public UserDTO()
    {
        super();
    }


    public UserDTO(User user)
    {
        this(user, Collections.emptyList());
    }


    public UserDTO(User user, Collection<User> friends)
    {
        this.id = user.getId();
        this.mail = user.getMail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.credits = user.getCredits();
        this.currentMonthlyPoints = user.getCurrentMonthlyPoints();
        this.permanentPoints = user.getPermanentPoints();
        this.friends = friends.stream().map(User::getId).collect(Collectors.toList());
    }


    public long getId()
    {
        return id;
    }


    public void setId(long id)
    {
        this.id = id;
    }


    public String getMail()
    {
        return mail;
    }


    public void setMail(String mail)
    {
        this.mail = mail;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    public String getLastName()
    {
        return lastName;
    }


    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }


    public int getPermanentPoints()
    {
        return permanentPoints;
    }


    public void setPermanentPoints(int permanentPoints)
    {
        this.permanentPoints = permanentPoints;
    }


    public int getCurrentMonthlyPoints()
    {
        return currentMonthlyPoints;
    }


    public void setCurrentMonthlyPoints(int currentMonthlyPoints)
    {
        this.currentMonthlyPoints = currentMonthlyPoints;
    }


    public int getCredits()
    {
        return credits;
    }


    public void setCredits(int credits)
    {
        this.credits = credits;
    }


    public Collection<Long> getFriends()
    {
        return friends;
    }


    public void setFriends(Collection<Long> friends)
    {
        this.friends = friends;
    }

}
