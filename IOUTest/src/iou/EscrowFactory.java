package iou;

public class EscrowFactory 
{
    public static Escrow createEscrow()
    {
        return new RealIouEscrow();
    }
    
    public static Escrow createEscrow(Class type) throws IllegalArgumentException
    {
        return new RealIouEscrowEx(type);
    }
}

