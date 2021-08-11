import java.util.HashMap;
import java.util.Map;

public class a138copyRandomList {
    /*
// Definition for a Node.
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
*/
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
    public Node copyRandomList(Node head) {

        Node ans = new Node(0);
        HashMap<Node,Node>  hashMap= new HashMap<>();

        Node ansTail = ans;
        Node temp = head;

        while(temp != null){
            Node newNode=new Node(temp.val);
            ansTail.next = newNode;
            hashMap.put(temp,ansTail.next);
            ansTail = ansTail.next;
            temp = temp.next;
        }
        // for (Map.Entry<Node, Node> entry : hashMap.entrySet()) {
        //     System.out.println(entry.getKey().val+"--"+ entry.getValue().val);
        // }
        ansTail = ans.next;
        temp = head;
        while(temp != null && ansTail!= null){
            if(temp.random ==null){
                ansTail.random = null;
            }else{
                ansTail.random = hashMap.get(temp.random);
            }
            temp = temp.next;
            ansTail = ansTail.next;
        }
        // ansTail = ans.next;
        // while(ansTail != null){
        //     if(ansTail.random != null){
        //         System.out.println(ansTail.val+"--"+ ansTail.random.val);
        //     }

        //     ansTail = ansTail.next;
        // }
        return ans.next;

    }
}
