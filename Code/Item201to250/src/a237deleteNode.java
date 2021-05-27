public class a237deleteNode {
    public static void main(String[] args) {
        int[] head = {1, 2, 3, 4, 5};
        ListNode start = new ListNode(0);
        ListNode end = start;
        for (int i : head) {
            ListNode temp = new ListNode(i, null);
            end.next = temp;
            end = temp;
        }
        int x = 3;
        deleteNode(start.next.next.next);
        while (start!=null){
            System.out.println(start.val);
            start=start.next;
        }
    }

    public static void deleteNode(ListNode node) {
        //System.out.println(node.val);

//        ListNode temp = node.next;
//        node.val = temp.val;
//        while(temp.next != null){
//            node = temp;
//            temp=temp.next;
//            node.val = temp.val;
//        }
//        node.next =null;
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
