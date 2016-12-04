package org.hamster.api.exc;


public class DuplicateVoteException
    extends Exception
{

    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 20160412L;


    public DuplicateVoteException()
    {
        super();
        // TODO Auto-generated constructor stub
    }


    public DuplicateVoteException(String message,
                                  Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    public DuplicateVoteException(String message, Throwable cause)
    {
        super(message, cause);
    }


    public DuplicateVoteException(String message)
    {
        super(message);
 
    }


    public DuplicateVoteException(Throwable cause)
    {
        super(cause);
    }

}
