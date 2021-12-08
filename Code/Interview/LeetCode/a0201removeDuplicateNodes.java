import java.util.HashSet;
import java.util.List;

public class a0201removeDuplicateNodes {
    public static void main(String[] args) {
        Integer[] node = {1, 2, 3, 3, 2, 1};
        ListNode head = new ListNode().build(node);
        removeDuplicateNodes(head.next);
    }

    public static ListNode removeDuplicateNodes(ListNode head) {
        HashSet<Integer> hashSet = new HashSet<>();
        ListNode temp = new ListNode(0);

        temp.next = head;
        head = temp;
        ListNode pre = head;
        head = head.next;
        temp = head;
        while (head != null) {
            if (hashSet.contains(head.val)) {
                pre.next = head.next;
                head = head.next;
                continue;
            }
            hashSet.add(head.val);
            pre = pre.next;
            head = pre.next;
        }
        return temp;
    }
}
