package iou;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RealIouEscrowEx extends RealIouEscrow implements InvocationHandler 
{
    private Class type;
    
    public RealIouEscrowEx(Class type) throws IllegalArgumentException
    {
        if( type == null || !type.isInterface() )
        {
            throw new IllegalArgumentException("Unsupport non-interface type.");
        }
        this.type = type;
    }
    
    public Iou issueIou() 
    {
        return (Iou)Proxy.newProxyInstance(Iou.class.getClassLoader(), new Class[] {type, Iou.class}, this);
    }
    
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
    {
        Object obj;
        if( method.getDeclaringClass() == Iou.class )
        {
            obj = this;
        }
        else
        {
            if( !this.closed() )
            {
                System.out.println("   >>> Object is not ready, stand by at calling " + method.getName() + "()");
                this.standBy();
                System.out.println("   <<< Object is ready, continue from calling " + method.getName() + "()");
            }
            obj = this.redeem();
        }
        return method.invoke(obj, args);
    }

}
