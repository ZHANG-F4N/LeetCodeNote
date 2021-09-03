public class a24reverseList {
    public static void main(String[] args) {
        int[] head = {1, 2, 3, 4, 5};
        ListNode list = new ListNode();
        ListNode temp = list;
        for (int i = 0; i < head.length; i++) {
            temp.next = new ListNode(head[i]);
            temp = temp.next;
        }

        temp = reverseList(list.next);
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }

    }

    public static ListNode reverseList(ListNode head) {
        ListNode ans = new ListNode();
//        ListNode tail = ans;

        ListNode temp = head;
        while (head != null) {
            temp = head;
            head = head.next;

            temp.next = ans.next;
            ans.next = temp;

        }
        return ans.next;

    }
}
