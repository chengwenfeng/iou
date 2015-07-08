package iou;

public interface Escrow 
{
    // 发行IOU对象
    Iou issueIou();
    // 终止IOU对象
    void close(Object o);
}
