package iou;

public interface Escrow 
{
    // ����IOU����
    Iou issueIou();
    // ��ֹIOU����
    void close(Object o);
}
