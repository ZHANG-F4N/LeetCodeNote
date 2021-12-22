import java.util.Stack;

public class a445addTwoNumbers {
    public static void main(String[] args) {
        int[] nums1 = {7, 2, 4, 3};
        int[] nums2 = {5, 6, 4};
        ListNode l1 = new ListNode();
        ListNode temp = l1;
        for (int i : nums1) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }

        ListNode l2 = new ListNode();
        temp = l2;
        for (int i : nums2) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        ListNode ans = addTwoNumbers(l1.next, l2.next);
        while (ans != null) {
            System.out.print(ans.val + "-");
            ans = ans.next;
        }
    }

    //链表反转考虑栈;
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = null;
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        int total = 0;
        while (!s1.empty() || !s2.empty() || carry != 0) {
            int a = s1.empty() ? 0 : s1.pop();
            int b = s2.empty() ? 0 : s2.pop();
            total = carry + a + b;
            if (total > 9) {
                carry = 1;
            } else {
                carry = 0;
            }
            ans = new ListNode(total % 10, ans);
        }
        return ans;
    }
}
