import java.util.*;

public class test {
    public static void main(String[] args) {
        LRU obj = new LRU(5);
        int param_1 = obj.get(5);
        obj.put(4, 1);
        obj.put(7, 2);
        obj.put(0, 3);
        obj.put(7, 4);
        obj.put(1, 5);
        obj.put(0, 6);
        obj.put(1, 7);
        obj.put(2, 8);
        obj.put(1, 9);
        obj.put(2, 10);
        obj.put(6, 11);

        System.out.println();
        Integer[] arr = new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode root = new TreeNode().Build(arr);
        ;

        lowestCommonAncestor(root, root.left.left, root.right.left);

    }

    static class LRU {
        class Node {
            int key;
            int val;
            Node pre;
            Node next;

            public Node(int val, int times) {
                this.key = val;
                this.val = times;
                pre = null;
                next = null;
            }

        }

        private HashMap<Integer, Node> hashMap;
        private Node head;
        private Node tail;
        private int cap;

        public LRU(int capacity) {
            cap = capacity;
            hashMap = new HashMap<>();
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            if (hashMap.containsKey(key)) {
                // 调整 并 返回
                Node idx = hashMap.get(key);
                idx.pre.next = idx.next;
                idx.next.pre = idx.pre;
                idx.pre = head;
                idx.next = head.next;

                head.next.pre = idx;
                head.next = idx;
                return idx.val;
            } else {
                return -1;
            }
//            return hashMap.containsKey(key) ? key : -1;
        }

        public void put(int key, int value) {
            if (hashMap.containsKey(key)) {
                Node idx = hashMap.get(key);
                idx.val = value;
                // 调整
                idx.pre.next = idx.next;
                idx.next.pre = idx.pre;
                idx.pre = head;
                idx.next = head.next;

                head.next.pre = idx;
                head.next = idx;

            } else {
                Node node = new Node(key, value);
                // 要添加容量不够要置换
                if (hashMap.size() >= cap) {
                    Node idx = tail.pre;
                    idx.next.pre = idx.pre;
                    idx.pre.next = idx.next;
                    hashMap.remove(idx.key);
                }
                head.next.pre = node;
                node.next = head.next;
                node.pre = head;
                head.next = node;
                hashMap.put(key, node);
            }
        }
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // 1. 记录路径
        // 2. 递归
        ArrayList<TreeNode> ppath = new ArrayList<>();
        ArrayList<TreeNode> qpath = new ArrayList<>();
        preOrder(root, p, ppath);
        preOrder(root, q, qpath);
        int m = Math.min(ppath.size(), qpath.size());
        TreeNode ans = root;
        for (int i = 0; i < m; i++) {
            if (qpath.get(i) != ppath.get(i)) {
                break;
            }
            ans = qpath.get(i);
        }
        return ans;

    }

    public static void preOrder(TreeNode root, TreeNode t, ArrayList<TreeNode> list) {
        if (root == null) return;
        // 已经找到
        if (list.size() != 0 && list.get(list.size() - 1) == t) return;
        list.add(root);
        if (t == root) return;
        if (root.left != null) preOrder(root.left, t, list);
        if (root.right != null) preOrder(root.right, t, list);
        if (list.size() != 0 && list.get(list.size() - 1) == t) return;
        list.remove(list.size() - 1);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode();
        ListNode tail = ans;

        int add = 0;
        while (l1 != null && l2 != null) {
            ListNode t = new ListNode();
            if (l1.val + l2.val + add >= 10) {
                t.val = (l1.val + l2.val + add) % 10;
                add = 1;
            } else {
                t.val = (l1.val + l2.val + add) % 10;
                add = 0;
            }

            tail.next = t;
            tail = t;
            l1 = l1.next;
            l2 = l2.next;
            System.out.println();
            ;
        }
        ListNode l3 = null;
        if (l1 == null && l2 != null) {
            l3 = l2;
        } else if (l1 != null && l2 == null) {
            l3 = l1;
        }
        while (l3 != null) {
            ListNode t = new ListNode();
            if (l3.val + add >= 10) {
                t.val = (l3.val + add) % 10;
                add = 1;


            } else {
                t.val = (l3.val + add) % 10;
                add = 0;

            }
            tail.next = t;
            tail = t;
            l3 = l3.next;
        }
        if (add != 0) {
            ListNode t = new ListNode();
            t.val = 1;
            tail.next = t;
        }
        return ans;
    }

    static class FreqStack {
        class node {
            int key;
            int val;

            public node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        PriorityQueue<node> queue;
        HashMap<Integer, node> hashMap;

        public FreqStack() {
            queue = new PriorityQueue<>(new Comparator<node>() {
                @Override
                public int compare(node o1, node o2) {
                    return o2.val - o1.val;
                }
            });
            hashMap = new HashMap<>();
        }

        public void push(int val) {
            if (hashMap.containsKey(val)) {
                node t = hashMap.get(val);
                queue.remove(t);
                t.val += 1;
                queue.add(t);
            } else {
                node t = new node(val, 1);
                queue.add(t);
            }
        }
    }

}
