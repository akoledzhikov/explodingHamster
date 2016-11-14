package org.hamster.web.dto.request;


import java.util.Collection;


public class MultiIdBasedRequest
{
    private Collection<Long> ids;


    public MultiIdBasedRequest()
    {
        super();
    }


    public MultiIdBasedRequest(Collection<Long> ids)
    {
        super();
        this.ids = ids;
    }


    public Collection<Long> getIds()
    {
        return ids;
    }


    public void setIds(Collection<Long> ids)
    {
        this.ids = ids;
    }

}
