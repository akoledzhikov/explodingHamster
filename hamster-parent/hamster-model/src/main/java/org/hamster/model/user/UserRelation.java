package org.hamster.model.user;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "UserRelations")
public class UserRelation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private User friend;


    public UserRelation()
    {

    }


    public UserRelation(User user, User friend)
    {
        super();
        this.user = user;
        this.friend = friend;
    }


    public long getId()
    {
        return id;
    }


    public void setId(long id)
    {
        this.id = id;
    }


    public User getUser()
    {
        return user;
    }


    public void setUser(User user)
    {
        this.user = user;
    }


    public User getFriend()
    {
        return friend;
    }


    public void setFriend(User friend)
    {
        this.friend = friend;
    }
}
