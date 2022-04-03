import java.util.HashMap;

public class a432AllOne {
    public static void main(String[] args) {
        AllOne obj = new AllOne();
        obj.inc("a");
        obj.inc("b");
        obj.inc("b");
        obj.inc("c");
        obj.inc("c");
        obj.inc("c");
        obj.dec("b");
        obj.dec("b");
//        String param_1 = obj.getMaxKey();
        String param_2 = obj.getMinKey();
        obj.dec("a");
        String param_3 = obj.getMaxKey();
        String param_4 = obj.getMinKey();
    }

    static class AllOne {
        private HashMap<String, Integer> count;
        private HashMap<String, Node> hashMap;

        class Node {
            private Node prev;
            private Node next;
            private int num;
            private String str;
        }

        private Node first;
        private Node tail;

        public AllOne() {
            count = new HashMap<>();
            hashMap = new HashMap<>();
            first = null;
            tail = null;
        }

        public void inc(String key) {
            if (count.containsKey(key)) {
                count.put(key, count.get(key) + 1);
                // 向前交换
                Node temp = hashMap.get(key);
                Node idx = temp;
                temp.num++;

                while (temp.prev != null && temp.prev.num <= idx.num) {
                    temp = temp.prev;
                }


                if (temp.prev != null) temp.prev.next = idx;
                if (temp.next != null) temp.next.prev = idx;
                if (idx.prev != null) idx.prev.next = temp;
                if (idx.next != null) idx.next.prev = temp;

                if (temp.next == idx) {
                    Node tp = temp.prev;
//                    Node tn = temp.next;
                    temp.prev = idx;
                    temp.next = idx.next;
                    idx.prev = tp;
                    idx.next = temp;

                } else {
                    Node tp = temp.prev;
                    Node tn = temp.next;
                    temp.prev = idx.prev;
                    temp.next = idx.next;
                    idx.prev = tp;
                    idx.next = tn;
                }


                if (first == temp) first = idx;
                if (tail == idx) tail = temp;

            } else {
                // 尾插
                count.put(key, 1);
                Node temp = new Node();
                temp.num = 1;
                temp.str = key;
                if (first == null) {
                    first = temp;
                } else {
                    temp.next = null;
                    temp.prev = tail;
                    tail.next = temp;
                }
                tail = temp;
                hashMap.put(key, temp);
            }
        }

        public void dec(String key) {
            if (count.get(key) == 1) {
                // 删除
                count.remove(key);
                Node temp = hashMap.get(key);
                if (first == temp) first = temp.next;
                if (tail == temp) tail = tail.prev;
                if (temp.prev != null) temp.prev.next = temp.next;
                if (temp.next != null) temp.next.prev = temp.prev;

            } else {
                // 后移
                count.put(key, count.get(key) - 1);
                Node temp = hashMap.get(key);
                Node idx = temp;
                temp.num--;

                while (temp.next != null && temp.next.num > idx.num) {
                    temp = temp.next;
                }

                if (temp.prev != null) temp.prev.next = idx;
                if (temp.next != null) temp.next.prev = idx;
                if (idx.prev != null) idx.prev.next = temp;
                if (idx.next != null) idx.next.prev = temp;

                Node tp = temp.prev;
                Node tn = temp.next;
                temp.prev = idx.prev;
                temp.next = idx.next;
                idx.prev = tp;
                idx.next = tn;

                if (first == temp) first = idx;
                if (tail == idx) tail = temp;
            }
        }

        public String getMaxKey() {
            if (first == null) return "";
            return first.str;
        }

        public String getMinKey() {
            if (tail == null) return "";
            return tail.str;
        }
    }

}
