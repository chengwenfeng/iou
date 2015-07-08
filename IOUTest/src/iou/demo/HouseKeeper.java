package iou.demo;

import iou.Escrow;
import iou.Iou;
import iou.RealIouEscrow;
import iou.RealIouEscrowEx;

import java.util.Date;
import java.util.Random;

public class HouseKeeper 
{
    public static void main(String args[])
    {
        // 初始化待处理的衣服和食物对象
        Clothes clothesToWash = new ClothesImpl();
        Food foodToCook = new FoodImpl();
        
        // 设定洗衣事务
        Iou clothesWashed = wash(clothesToWash);
        // 继续做其他事情
        doSomethingOther();
        // 设定烹饪事务
        Food foodCooked = cook(foodToCook);
        // 继续做其他事情
        doSomethingOther();
        // 开始享用食物
        eat(foodCooked);
        // 开始晾晒衣服
        hangout(clothesWashed);
    }
    
    private static Iou wash(Clothes clothes)
    {
        logger("Schedule to wash " + clothes);
        Escrow escrow = new RealIouEscrow();
        new AsyncService("wash clothes", clothes, escrow).start();
        return escrow.issueIou();
    }
    
    private static Food cook(Food food)
    {
        logger("Schedule to cook " + food);
        Escrow escrow = new RealIouEscrowEx(Food.class);
        new AsyncService("cook food", food, escrow).start();
        return (Food)escrow.issueIou();
    }
    
    private static void eat(Food food)
    {
        logger("Be about to eat food...add some spice first...");
        food.addSpice();
        logger(food + " is eaten.");
    }
    
    private static void hangout(Iou iou)
    {
        logger("Be about to hang out clothes...");
        if( !iou.closed() )
        {
            logger("Clothes are not ready, stand by...");
            iou.standBy();
        }
        Object clothes = iou.redeem();
        logger(clothes + " are hung out.");
    }
    
    private static void doSomethingOther()
    {
        try 
        {
            Random r = new Random(new Date().getTime());
            int time = (Math.abs(r.nextInt())%1000 + 0); 
            Thread.sleep(time);
            logger("Do something other [" + time + " millis]");
        } 
        catch (InterruptedException e) 
        {
        }
    }
    private static void logger(String message)
    {
        System.out.println("["+new Date()+"] "+message);
    }
}

class AsyncService extends Thread
{
    private Escrow escrow;
    private String task;
    private Processable o;
    private int time;
    public AsyncService(String task, Processable o, Escrow escrow)
    {
        this.escrow = escrow;
        this.task = task;
        this.o = o;
    }
    public void run()
    {
        System.out.println("   >>> Starting to " + this.task );
        Object r = service(o);
        this.escrow.close(r);
        System.out.println("   <<< Finished " + this.task + " [" + time + " millis]");
    }
    private Processable  service(Processable  o)
    {
        try
        {
            Random r = new Random(new Date().getTime());
            time = (Math.abs(r.nextInt())%1000 + 2000); 
            sleep(time);
            this.o.process();
        }
        catch(InterruptedException e)
        {
        }
        return this.o;
    }
}