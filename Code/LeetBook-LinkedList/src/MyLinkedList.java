public class MyLinkedList {

    /**
     * Your MyLinkedList object will be instantiated and called as such:
     * MyLinkedList obj = new MyLinkedList();
     * int param_1 = obj.get(index);
     * obj.addAtHead(val);
     * obj.addAtTail(val);
     * obj.addAtIndex(index,val);
     * obj.deleteAtIndex(index);
     */
    static class Node {
        int val;
        Node next;

        public Node() {
            this.val = 0;
            this.next = null;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    private static Node head = null;
    private static Node tail = null;
    private static int ListSize = 0;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        ListSize = 0;
        head = new Node();
        tail = head;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= ListSize) {
            return -1;
        }
        Node temp = head.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {

        head.next = new Node(val, head.next);
        if (head.next.next == null) {
            tail = head.next;
        }
        ListSize++;
        return;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        tail.next = new Node(val, null);
        tail = tail.next;
//        Node tail = head;
//        while (tail.next != null) {
//            tail = tail.next;
//        }
//        tail.next = new Node(val, null);
        ListSize++;
        return;

    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > ListSize) {
            return;
        }
        if (index <= 0) {
            //头插
            this.addAtHead(val);
            return;
        }
        if (index == ListSize) {
            //尾插
            this.addAtTail(val);

//            Node tail = head;
//            while (tail.next != null) {
//                tail = tail.next;
//            }
//            tail.next = new Node(val, null);
//            ListSize++;
            return;
        }
        Node Temp = head;
        for (int i = 0; i < index; i++) {
            Temp = Temp.next;
        }
        Temp.next = new Node(val, Temp.next);
        ListSize++;
        return;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= ListSize) {
            return;
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        if (temp.next.next == null) {
            tail = temp;
        }
        temp.next = temp.next.next;
        ListSize--;
        return;


    }


}

