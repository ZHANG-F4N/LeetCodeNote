public class a142detectCycle {
    public static void main(String[] args) {
        int[] nums = {3, 2, 0, 1, -4};
        ListNode head = new ListNode().build(nums);
        ListNode temp = head;
        ListNode tail = head;
        while (tail.next != null) {

            tail = tail.next;
        }
//        for (int i = 0; i < 3; i++) {
//            temp = temp.next;
//        }
//        tail.next = temp;
        System.out.println(detectCycle(head));
    }

    public static ListNode detectCycle(ListNode head) {

        //ListNode ans = null;
        ListNode fastP = head;
        ListNode slowP = head;

        while (fastP != null && fastP.next != null) {
            fastP = fastP.next.next;
            slowP = slowP.next;

            if (fastP == slowP) {
                ListNode pr = head;
                while (pr != slowP) {
                    pr = pr.next;
                    slowP = slowP.next;
                }
                return slowP;
            }
        }

        return null;
    }
}
