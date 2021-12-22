public class a328oddEvenList {
    public static void main(String[] args) {
        int[] head = {1, 2, 3, 4, 5};
        ListNode start = new ListNode(0);
        ListNode end = start;
        for (int i : head) {
            ListNode temp = new ListNode(i, null);
            end.next = temp;
            end = temp;
        }
        start =  oddEvenList(start.next);
        while (start != null) {
            System.out.println(start.val);
            start = start.next;
        }
    }

    public static ListNode oddEvenList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode oddPoint;//奇数
        ListNode evenPoint;//偶数
        oddPoint = head;
        evenPoint = head.next;
        ListNode oddEndPoint = oddPoint;
        ListNode evenEndPoint = evenPoint;
        ListNode temp = evenPoint.next;
        boolean flag = true;//true - odd ;false = even;
        while (temp != null) {
            if (flag) {
                oddEndPoint.next = temp;
                oddEndPoint = oddEndPoint.next;
            } else {
                evenEndPoint.next = temp;
                evenEndPoint = evenEndPoint.next;
            }
            temp = temp.next;
            flag = !flag;
        }
        evenEndPoint.next=null;
        oddEndPoint.next = evenPoint;
        return head;
    }
}
