public class a22getKthFromEnd {
    public static void main(String[] args) {
        int[] text = {1, 2, 3, 4, 5, 6};
        int k = 2;
        ListNode head = new ListNode();
        ListNode tail = head;
        for (int i = 0; i < text.length; i++) {
            tail.next = new ListNode(text[i], tail.next);
            tail = tail.next;
        }
        System.out.println(getKthFromEnd(head.next, k).val);

    }

    public static ListNode getKthFromEnd(ListNode head, int k) {

        ListNode fastP = head;
        ListNode slowP = head;
        if (head == null) {
            return null;
        }

        for (int i = 0; i < k; i++) {
            fastP = fastP.next;
        }


        while (fastP != null) {
            fastP = fastP.next;
            slowP = slowP.next;
        }

        return slowP;

    }
}
