public class a25mergeTwoList {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 4};
        int[] nums2 = {1, 3, 4};

        ListNode head1 = new ListNode().build(nums1);
        ListNode head2 = new ListNode().build(nums2);

        new ListNode().print(mergeTwoLists(head1.next, head2.next));
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {


        ListNode ans = new ListNode();
        ListNode tail = ans;
        ListNode index1 = l1;
        ListNode index2 = l2;
        while (index1 != null && index2 != null) {
            if (index1.val < index2.val) {
                tail.next = index1;
                index1 = index1.next;
            } else {
                tail.next = index2;
                index2 = index2.next;
            }
            tail = tail.next;
        }
        if (index1 != null) {
            tail.next = index1;
        }
        if (index2 != null) {
            tail.next = index2;
        }

        return ans.next;

    }
}
