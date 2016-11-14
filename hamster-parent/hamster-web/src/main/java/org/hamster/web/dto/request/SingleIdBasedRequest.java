package org.hamster.web.dto.request;


public class SingleIdBasedRequest
{
    private long id;


    public SingleIdBasedRequest()
    {
        super();
    }


    public SingleIdBasedRequest(long id)
    {
        super();
        this.id = id;
    }


    public long getId()
    {
        return id;
    }


    public void setId(long id)
    {
        this.id = id;
    }
}
