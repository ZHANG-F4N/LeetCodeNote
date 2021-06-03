public class a160getIntersectionNode {
    public static void main(String[] args) {
        int[] head = {4, 1, 8, 4, 5};
        int[] head2 = {5, 6, 1, 8, 4, 5};
        ListNode start = new ListNode(0);
        ListNode start2 = new ListNode(0);
        ListNode end = start;
        ListNode end2 = start2;
        for (int i : head) {
            ListNode temp = new ListNode(i, null);
            end.next = temp;
            end = temp;
        }
        for (int i : head2) {
            ListNode temp = new ListNode(i, null);
            end2.next = temp;
            end2 = temp;
        }
        //主函数没写完....
        getIntersectionNode(start.next, start2.next);

    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        方法一:
//          可以先把两个链表长度测出来,然后较长的一方可以先走差值
//          然后再进行两两比较.
//        方法二:
//          链表A和链表B同时向后走,两个各自到达尾结点后转向对方的头结点
//          如果相交,则会在第一个相交节点相遇
        ListNode APoint = headA;
        ListNode BPoint = headB;
        while (APoint != null || BPoint != null) {
            if (APoint == BPoint) {
                return APoint;
            }

            if (APoint == null) {
                APoint = headB;
            } else {
                APoint = APoint.next;
            }

            if (BPoint == null) {
                BPoint = headA;
            } else {
                BPoint = BPoint.next;
            }

        }
        return null;
    }
}
