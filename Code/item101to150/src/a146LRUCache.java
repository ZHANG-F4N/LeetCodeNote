import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class a146LRUCache {
    public static void main(String[] args) {

        LRUCache obj = new LRUCache(2);
        obj.put(2, 1);
        obj.put(2, 2);
        obj.get(2);

        obj.put(1, 1);
        obj.put(4, 1);
        obj.get(1);


//        LRUCache obj = new LRUCache(5);
//        int param_1 = obj.get(5);
//        obj.put(4, 1);
//        obj.put(7, 2);
//        obj.put(0, 3);
//        obj.put(7, 4);
//        obj.put(1, 5);
//        obj.put(0, 6);
//        obj.put(1, 7);
//        obj.put(2, 8);
//        obj.put(1, 9);
//        obj.put(2, 10);
//        obj.put(6, 11);
    }
}

class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.value = val;
        }
    }

    // 实现O(1) 查找,定位链表
    private HashMap<Integer, Node> hashMap;
    private int capacity;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        hashMap = new HashMap<>();
        // 创建双向链表
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;


        this.capacity = capacity;
    }

    public int get(int key) {
        if (hashMap.containsKey(key)) {
            Node index = hashMap.get(key);

            //摘除
            index.prev.next = index.next;
            index.next.prev = index.prev;

            //尾插
            index.prev = tail.prev;
            index.next = tail;
            tail.prev.next = index;
            tail.prev = index;

            return hashMap.get(key).value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (hashMap.containsKey(key)) {
            //包含关键字,调整位置
            Node index = hashMap.get(key);
            index.value = value;

            //摘除
            index.prev.next = index.next;
            index.next.prev = index.prev;

            //尾插
            index.prev = tail.prev;
            index.next = tail;
            tail.prev.next = index;
            tail.prev = index;

        } else {
            //不包含关键字
            if (hashMap.size() < capacity) {
                // 容量够,进组
                Node temp = new Node(key, value);
                //尾插
                temp.prev = tail.prev;
                temp.next = tail;
                tail.prev.next = temp;
                tail.prev = temp;

                hashMap.put(key, temp);

            } else {
                //容量不够, 置换
                Node temp = new Node(key, value);
                //尾插
                temp.prev = tail.prev;
                temp.next = tail;
                tail.prev.next = temp;
                tail.prev = temp;
                hashMap.put(key, temp);
                // 移除LRU
                hashMap.remove(head.next.key);
                Node remove = head.next;
                remove.next.prev = head;
                head.next = remove.next;


            }
        }
    }
}
