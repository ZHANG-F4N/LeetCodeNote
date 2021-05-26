public class a82deleteDuplicates {
    public static void main(String[] args) {
        int[] head = {1, 1, 1, 2, 3, 3};
        ListNode start = new ListNode(0);
        ListNode end = start;
        for (int i : head) {
            ListNode temp = new ListNode(i, null);
            end.next = temp;
            end = temp;
        }
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
        head = new ListNode(-1, head);

        ListNode endList = head;
        ListNode preTemp = head;
        ListNode check = head.next;
        ListNode ofterTemp = check.next;


        if (check.val != ofterTemp.val){
            check=check.next;
        }

//        if (preTemp.val != ofterTemp.val) {
//            head.next = preTemp;
//            preTemp = ofterTemp;
//            ofterTemp = ofterTemp.next;
//        }else {
//            head.next = null;
//        }
//        while (ofterTemp != null) {
//            if (ofterTemp.val == preTemp.val) {
//                ofterTemp = ofterTemp.next;
//            } else {
//                check.next = ofterTemp;
//                check = ofterTemp;
//                preTemp = ofterTemp;
//                ofterTemp = ofterTemp.next;
//            }
//        }
        //if ()
        System.out.println(check.val);
        //System.out.println(preTemp.next);

        return head;
    }
}
