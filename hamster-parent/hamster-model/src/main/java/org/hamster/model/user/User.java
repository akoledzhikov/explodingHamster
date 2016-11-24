package org.hamster.model.user;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.karneim.pojobuilder.GeneratePojoBuilder;


@Entity
@Table(name = "Users")
@GeneratePojoBuilder(withSetterNamePattern = "*")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mail;

    private String firstName;

    private String lastName;

    private int permanentPoints;

    private int currentMonthlyPoints;

    // TODO historic tracking of past monthly points

    private int credits;


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


    public void addMonthlyPoints(int points)
    {
        this.currentMonthlyPoints += points;
    }


    public void addPermanentPoints(int points)
    {
        this.permanentPoints += points;
    }


    public void addCredits(int credits)
    {
        this.credits += credits;
    }


    public void subtractCredits(int credits)
    {
        this.credits -= credits;
    }
}
