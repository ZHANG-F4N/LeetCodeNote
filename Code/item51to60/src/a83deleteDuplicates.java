public class a83deleteDuplicates {
    public static void main(String[] args) {
        int[] head = {1, 1, 2, 3};
        ListNode start = new ListNode(0);
        ListNode end = start;
        for (int i : head) {
            ListNode temp = new ListNode(i, null);
            end.next = temp;
            end = temp;
        }
        int k = 4;
        ListNode ans = deleteDuplicates(start.next);
        while (ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preTemp = head;
        ListNode ofterTemp = head.next;
        while (ofterTemp != null) {
            if (preTemp.val == ofterTemp.val) {
                ofterTemp = ofterTemp.next;
            } else {
                preTemp.next = ofterTemp;
                ofterTemp = ofterTemp.next;
                preTemp = preTemp.next;
            }
        }
        //这一句在最后有重复元素时,删除多余的重复元素.
        preTemp.next = null;
        return head;
    }
}
