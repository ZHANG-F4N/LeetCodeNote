# HuaWei LeetCode

[TOC]

## [135. 分发糖果](https://leetcode-cn.com/problems/candy/)

老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。

你需要按照以下要求，帮助老师给这些孩子分发糖果：

每个孩子至少分配到 1 个糖果。
评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
那么这样下来，老师至少需要准备多少颗糖果呢？

```
输入：[1,0,2]
输出：5
解释：你可以分别给这三个孩子分发 2、1、2 颗糖果。
```

---

解题思路:

- 两趟遍历。
  - 第一趟==从左向右遍历==单调上升区间，当前的糖果数是前一个糖果+1。
  - 第二趟==从右向左遍历==单调上升区间，判断是否符合当前高于前一个，如果不高于，当前的糖果数是前一糖果数+1。

```java
class Solution {
    public int candy(int[] ratings) {
        int N = ratings.length;
        if (N == 1) {
            return 1;
        }
        int[] candies = new int[N];
        candies[0] = 1;
        //左向右上升
        for (int i = 1; i < N; i++) {
            candies[i] = 1;
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        int ans = candies[N - 1];
        //右向左上升
        for (int i = N - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
            ans += candies[i];
        }
        //ans = IntStream.of(candies).sum();
        return ans;
    }
}
```

## ·[146. LRU 缓存机制](https://leetcode-cn.com/problems/lru-cache/)

运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
实现 LRUCache 类：

LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。


进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？

```java
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
```

---

解题思路:

- Hash + 双向链表。

  `HashMap<Integer, Node>`负责查找和定位，通过查找到的位置来进行置换和操作。

  `双向链表负责保存值`，并且按访问顺序保持，作为`LRU置换`的依据

```java
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
        // 创建双向链表，为了方便操作，添加头尾伪节点。
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
            //可能修改值
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

```









## [1190. 反转每对括号间的子串](https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/)

给出一个字符串 s（仅含有小写英文字母和括号）。

请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。

注意，您的结果中 不应 包含任何括号。

```
输入：s = "(ed(et(oc))el)"
输出："leetcode"
解释：先反转子字符串 "oc" ，接着反转 "etco" ，然后反转整个字符串。
```

---

解题思路:

- 栈。

- 转向遍历。

  我们可以将括号的反转理解为逆序地遍历括号，如下图：

  ![fig1](asset/HuaWei%20LeetCode.assets/1.png)

  1. 第一步我们向右移动到左括号，此时我们跳跃到该左括号对应的右括号（进入了更深一层）；
  2. 第二到第三步我们在括号内部向左移动（完成了更深层的遍历）；
  3. 第四步我们向左移动到左括号，此时我们跳跃到该左括号对应的右括号（返回到上一层）；
  4. 第五步我们在括号外向右移动（继续遍历）。

  在实际代码中，我们需要预处理出每一个括号对应的另一个括号所在的位置，这一部分我们可以使用栈解决。当我们预处理完成后，即可在线性时间内完成遍历，遍历的字符串顺序即为反转后的字符串。


```java
class Solution {
    public String reverseParentheses(String s) {
        if (s.length() == 0) {
            return "";
        }
        Stack<Integer> stack = new Stack<>();
        int[] pair = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            if (s.charAt(i) == ')') {
                pair[i] = stack.peek();
                pair[stack.peek()] = i;
                stack.pop();
            }
        }
        StringBuilder ans = new StringBuilder();
        int index = 0;
        int direct = 1;
        while (index < s.length()) {
            if (s.charAt(index) == '(' || s.charAt(index) == ')') {
                index = pair[index];
                direct = -direct;
            } else {
                ans.append(s.charAt(index));
            }
            index += direct;
        }
        return ans.toString();
    }
}
```

