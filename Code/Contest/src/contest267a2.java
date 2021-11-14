import java.util.List;

public class contest267a2 {
    public static void main(String[] args) {
        int[] node = new int[]{5, 2, 6, 3, 9, 1, 7, 3, 8, 4};
//        int[] node = new int[]{5, 2, 6};
        ListNode head = new ListNode().build(node);
        reverseEvenLengthGroups(head.next);
    }

    public static ListNode reverseEvenLengthGroups(ListNode head) {

        int idx = 0;
        int partBeg = 0;
        int partLen = 1;
        head = new ListNode(0, head);
        ListNode temp = head.next;
        ListNode pre = head;
        int len = 0;
        //0-/-1-/-2--3-/-4--5--6-/-7--8--9--10
        //0-/-5-/-2--6-/-3--9--1-/-7--3--8--4
        while (temp != null) {
            idx++;
            len++;
            if (temp.next == null) {
                // 处理结尾
                if (len % 2 == 0) {
                    pre.next = reverse(pre.next);
                }
                break;
            }
            if (idx == partBeg + partLen && partLen % 2 == 0) {
                // 是否反转 beg -- end
                ListNode end = temp.next;
                ListNode con = pre.next;
                temp.next = null;

                ListNode newHead = reverse(pre.next);
                pre.next = newHead;
                con.next = end;
                pre = con;
                partLen++;
                partBeg = idx;
                temp = end;
                len = 0;
                continue;
            } else if (idx == partBeg + partLen && partLen % 2 != 0) {
                pre = temp;
                partBeg =idx;
                partLen++;
                len = 0;
            }
            temp = temp.next;
        }
        return head.next;
    }

    public static ListNode reverse(ListNode head) {
        ListNode idx = head;
        // 添加头结点
        head = new ListNode(0);

        while (idx != null) {
            ListNode tempH = idx.next;
            idx.next = head.next;
            head.next = idx;
            idx = tempH;
        }
        return head.next;
    }
}
