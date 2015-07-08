package iou;


public interface Iou
{
    // 判断IOU对象是否已终止
    boolean closed();
    // 保持等待直至IOU对象被终止
    void standBy();
    // 赎回IOU结果，如果IOU对象尚未被终止则等待直至终止后再赎回
    Object redeem();
    // 添加回调对象
    void addCallback(Callback cb);
    // 删除回调对象
    void removeCallback(Callback cb);
}
