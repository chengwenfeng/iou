package iou;

import java.util.Iterator;
import java.util.Vector;

public class RealIouEscrow implements Iou, Escrow 
{
    /**
     * Vector to hold all callbacks
     */
    private Vector callbacks;
    
    /**
     * boolean indicate if IOU has been closed
     */
    private boolean closed;
    
    /**
     * Object that I own you
     */
    private Object objectIou;
    
    public RealIouEscrow()
    {
        this.callbacks = new Vector();
        this.closed = false;
    }

    public Iou issueIou() 
    {
        return this;
    }

    public synchronized void addCallback(Callback cb) 
    {
        if( this.closed )
        {
            cb.callback(this.objectIou);
        }
        else
        {
            this.callbacks.add(cb);
        }
    }

    public synchronized void removeCallback(Callback cb) 
    {
        this.callbacks.remove(cb);
    }

    public synchronized boolean closed() 
    {
        return this.closed;
    }

    public synchronized Object redeem() 
    {
        if( !this.closed )
        {
            standBy();
        }
        return this.objectIou;
    }

    public synchronized void standBy() 
    {
        if( !this.closed )
        {
            try 
            {
                wait();
            } 
            catch (InterruptedException e) 
            {
            }
        }
    }

    public synchronized void close(Object o) 
    {
        if( !this.closed )
        {
            this.objectIou = o;
            this.closed = true;
            this.notifyAll();
            Iterator it = this.callbacks.iterator();
            while(it.hasNext())
            {
                Callback callback = (Callback)it.next();
                callback.callback(this.objectIou);
            }
        }
    }

}
