public class a0203deleteNode {
    public static void main(String[] args) {
        Integer[] node = {4, 5, 1, 9};
        ListNode head = new ListNode().build(node);
        deleteNode(head.next.next);
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;

        node.next = node.next.next;
    }
}
