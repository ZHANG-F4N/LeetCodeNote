public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode build(Integer[] nums) {

        //带头结点
        ListNode head = new ListNode();
        ListNode tail = head;
        for (Integer num : nums) {
            tail.next = new ListNode(num);
            tail = tail.next;
        }
        return head;
    }

    public void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
