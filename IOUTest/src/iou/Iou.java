package iou;


public interface Iou
{
    // �ж�IOU�����Ƿ�����ֹ
    boolean closed();
    // ���ֵȴ�ֱ��IOU������ֹ
    void standBy();
    // ���IOU��������IOU������δ����ֹ��ȴ�ֱ����ֹ�������
    Object redeem();
    // ��ӻص�����
    void addCallback(Callback cb);
    // ɾ���ص�����
    void removeCallback(Callback cb);
}
