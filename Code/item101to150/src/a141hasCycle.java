public class a141hasCycle {
    public static void main(String[] args) {
        int[] head = {1, 2};
        ListNode start = new ListNode(0);
        ListNode end = start;
        for (int i : head) {
            ListNode temp = new ListNode(i, null);
            end.next = temp;
            end = temp;
        }
        System.out.println(hasCycle(start.next));
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fastP = head.next;
        ListNode slowP = head;
        while(fastP != slowP){
            if (fastP == null || fastP.next == null){
                return false;
            }
            fastP = fastP.next.next;
            slowP = slowP.next;
        }
        return true;
    }
}
