package iou.demo;

public class FoodImpl implements Food 
{
    boolean cooked = false;
    public void process() 
    {
        this.cooked = true;
    }
    public void addSpice()
    {
        System.out.println("   >>> Adding spice...");
        System.out.println("   <<< Spice is added.");
    }
    public String toString()
    {
        return (this.cooked ? "'Cooked'" : "'Uncooked'" ) + " food";
    }
}
