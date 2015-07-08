package iou.demo;

public class ClothesImpl implements Clothes 
{
    boolean clean = false;;
    public void process() 
    {
        this.clean = true;
    }
    public String toString()
    {
        return (this.clean? "'Clean'" : "'Dirty'") + " clothes";
    }
}
