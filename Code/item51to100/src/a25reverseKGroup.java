public class a25reverseKGroup {
    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5, 6, 7, 8};
        ListNode head = new ListNode().build(num);
        head = reverseKGroup(head.next, 7);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }

        //添加伪头结点
        head = new ListNode(0, head);

        ListNode pre = head;
        ListNode temp = head.next;
        int i = 0;
        while (temp != null) {
            i++;
            if (i == k) {
                i = 0;
                ListNode tempEnd = temp.next;
                ListNode[] out =  reverse(pre.next, temp);
                pre.next =out[0];
                pre = out[1];
                temp = tempEnd;
                continue;
            }
            temp = temp.next;
        }
        return head.next;
    }

    public static ListNode[] reverse(ListNode beg, ListNode end) {
        ListNode head = beg;
        ListNode op = beg;

        beg = beg.next;
        head.next = end.next;
        while (beg != end) {
            ListNode temp = beg;
            beg = beg.next;
            temp.next = head;
            head = temp;
        }
        beg.next = head;
        head = beg;

        return new ListNode[]{head, op};
    }
}
