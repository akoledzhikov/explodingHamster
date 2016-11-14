package org.hamster.web.dto;


import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hamster.model.def.Definition;
import org.hamster.model.user.DefinitionAvailability;
import org.hamster.model.user.User;


public class DefinitionAvailabilityDTO
{
    private long id;

    private long user;

    private long def;

    private Date validUntil;


    public DefinitionAvailabilityDTO()
    {
        super();
    }


    public DefinitionAvailabilityDTO(DefinitionAvailability defAvailability)
    {
        this.id = defAvailability.getId();
        this.def = defAvailability.getDef().getId();
        this.user = defAvailability.getUser().getId();
        this.validUntil = defAvailability.getValidUntil();
    }


    public long getId()
    {
        return id;
    }


    public void setId(long id)
    {
        this.id = id;
    }


    public long getUser()
    {
        return user;
    }


    public void setUser(long user)
    {
        this.user = user;
    }


    public long getDef()
    {
        return def;
    }


    public void setDef(long def)
    {
        this.def = def;
    }


    public Date getValidUntil()
    {
        return validUntil;
    }


    public void setValidUntil(Date validUntil)
    {
        this.validUntil = validUntil;
    }

}
