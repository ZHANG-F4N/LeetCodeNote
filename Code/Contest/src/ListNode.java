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

    public ListNode build(int[] nums) {
        ListNode head = new ListNode();
        if (nums.length == 0) {
            return head;
        }
        ListNode tail = head;
        for (int num : nums) {
            tail.next = new ListNode(num, null);
            tail = tail.next;
        }
        return head;
    }

}
