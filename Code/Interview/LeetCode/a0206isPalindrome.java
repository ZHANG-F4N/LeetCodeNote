public class a0206isPalindrome {
    public static void main(String[] args) {
        Integer[] node = {1, 1};
//        Integer[] node = {1, 2};
//        Integer[] node = {1, 2, 3, 2, 1};
//        Integer[] node = {1, 2, 2, 1};
        ListNode head = new ListNode().build(node);
        System.out.println(isPalindrome(head.next));
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head.next.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        ListNode pre = slow;
        slow = slow.next;
        mid.next = null;
        while (slow != null) {
            ListNode temp = slow.next;
            slow.next = pre.next;
            pre.next = slow;
            slow = temp;
        }
        pre = pre.next;
        while (head != mid.next) {
            if (head.val != pre.val) {
                return false;
            }
            head = head.next;
            pre = pre.next;
        }
        return true;

    }
}
