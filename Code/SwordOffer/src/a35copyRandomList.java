import java.util.HashMap;
import java.util.Map;

public class a35copyRandomList {
    public static void main(String[] args) {

    }

    public static Node copyRandomList(Node head) {
        Node ans = new Node(0);
        Node tail = ans;
        Node tempHead = head;
        HashMap<Node, Node> hashMap = new HashMap<>();
        while (tempHead != null) {
            Node temp = new Node(tempHead.val);
            temp.next = tail.next;
            tail.next = temp;
            hashMap.put(tempHead, temp);
            tail = tail.next;
            tempHead = tempHead.next;
        }

        tempHead = head;
        tail = ans.next;
        while (tempHead != null) {
            if (tempHead.random != null) {
                tail.random = hashMap.get(tempHead.random);
            }
            //System.out.println(tempHead+"--"+tempHead.random);
            tempHead = tempHead.next;
            tail = tail.next;
        }

        //System.out.println("--++++++++++++++++++++");
        // for (Map.Entry<Node, Node> en : hashMap.entrySet()){
        //     System.out.println(en.getKey()+"--"+en.getValue());
        // }

        return ans.next;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}