public class a0204partition {
    public static void main(String[] args) {
        Integer[] node = {1, 4, 3, 2, 5, 2};
        ListNode head = new ListNode().build(node);
        partition(head.next, 3);
    }

    public static ListNode partition(ListNode head, int x) {

        ListNode small = new ListNode();
        ListNode big = new ListNode();

        while (head != null) {
            ListNode temp = head;
            head = head.next;
            if (temp.val < x) {
                temp.next = small.next;
                small.next = temp;
            } else {
                temp.next = big.next;
                big.next = temp;
            }
        }
        ListNode temp = small;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = big.next;
        return small.next;


    }
}
