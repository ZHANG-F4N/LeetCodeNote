public class partition_86 {

    public static void main(String[] args) {
        int[] head = {1, 4, 3, 2, 5, 2};
        ListNode start = new ListNode(0);
        ListNode end = start;
        for (int i : head) {
            ListNode temp = new ListNode(i, null);
            end.next = temp;
            end = temp;
        }
//        while(start != null){
//            System.out.println(start.val);
//            start=start.next;
//        }
        int x = 3;
        partition(start.next, x);
    }

    public static ListNode partition(ListNode head, int x) {
        //双指针
        ListNode small = new ListNode();
        ListNode smallHead = small;
        ListNode big = new ListNode();
        ListNode bigHead = big;
        ListNode temp = head;

        //ListNode ans = null;
        while (temp != null) {
            if (temp.val < x) {
                small.next = temp;
                temp = temp.next;
                small = small.next;
                small.next=null;
            } else {
                big.next = temp;
                temp = temp.next;
                big = big.next;
                big.next=null;
            }

        }

        bigHead = bigHead.next;
//        while (smallHead != null) {
//            System.out.println(smallHead.val);
//            smallHead = smallHead.next;
//        }
//        System.out.println();
//        while (bigHead != null) {
//            System.out.println(bigHead.val);
//            bigHead = bigHead.next;
//        }

        if (bigHead != null) {
            small.next = bigHead;
        }
        return smallHead.next;
    }

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