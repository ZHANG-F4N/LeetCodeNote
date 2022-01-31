import java.util.List;

public class a725splitListToParts {
    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] nums = {1, 2, 3};


        ListNode head = new ListNode().build(nums);
        splitListToParts(head.next, 5);

    }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int nodeNum = count / k;
        int residue = count - nodeNum * k - 1;

        temp = head;

        ListNode[] ans = new ListNode[k];
        for (int i = 0; i < k; i++) {
            int num = nodeNum;
            if (i <= residue) {
                num = num + 1;
            }

            ListNode node = new ListNode();
            node.next = temp;
            ListNode pre = node;
            for (int j = 0; j < num && temp != null; j++) {
                pre = temp;
                temp = temp.next;
            }
            pre.next = null;
            ans[i] = node.next;
        }
        return ans;
    }
}
