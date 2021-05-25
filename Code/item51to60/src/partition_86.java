public class partition_86 {

    public static void main(String[] args) {
        int[]head = {1,4,3,2,5,2};
        ListNode start = new ListNode(0);
        ListNode end = start;
        for (int i : head) {
            ListNode temp = new ListNode(i,null);
            end.next = temp;
            end = temp;
        }

        while(start.next != null){
            System.out.println(start.val);
            start=start.next;
        }
    }
//    public ListNode partition(ListNode head, int x) {
//
//    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}