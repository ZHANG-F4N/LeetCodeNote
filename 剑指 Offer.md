# 剑指 Offer

[TOC]

## [剑指 Offer II 004. 只出现一次的数字 ](https://leetcode-cn.com/problems/WGki4K/)

给你一个整数数组 `nums` ，除某个元素仅出现 **一次** 外，其余每个元素都恰出现 **三次 。**请你找出并返回那个只出现了一次的元素。

```
输入：nums = [0,1,0,1,0,1,100]
输出：100
```

---

解题思路：

- 状态机 不会。

- 位运算

  <img src="asset/剑指 Offer.assets/1629341475-GhErJW-image.png" alt="image.png" style="zoom:50%;" />

```java
class Solution {
    public int singleNumber(int[] nums) {
       int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int j = 0; j < nums.length; j++) {
                //计算每一位和
                cnt += (nums[j] >> i) & 1;
            }
            if (cnt % 3 != 0) {
                //取余后只有 0 1 两种情况
                //边保存边左移。
                ans = ans | 1 << i;
            }
        }
        return ans;
    }
}
```



## [剑指 Offer 06. 从尾到头打印链表](https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

```
输入：head = [1,3,2]
输出：[2,3,1]
```

---

解题思路：

用栈进行逆序。

```java
class Solution {
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int size = stack.size();
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = stack.pop();
        }
        return array;
    }
}
```



## [剑指 Offer 09. 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]

---

解题思路:

双栈实现队列，当一个序列经过一个栈会变成倒序，经过两个栈会重新变为顺序。维护两个栈，第一个栈支持插入操作，第二个栈支持删除操作。

- 加入队尾 appendTail()函数： 将数字 val 加入栈 A 即可。
- 删除队首 deleteHead()函数： 有以下三种情况。
  - 当栈 B 不为空： B中仍有已完成倒序的元素，因此直接返回 B 的栈顶元素。
  - 否则，当 A 为空： 即两个栈都为空，无元素，因此返回 -1−1 。
  - 否则： 将栈 A 元素全部转移至栈 B 中，实现元素倒序，并返回栈 B 的栈顶元素。

```java
class CQueue {
    private Stack<Integer> stackA;
    private Stack<Integer> stackB;
    public CQueue() {
        stackA = new Stack<Integer>();
        stackB = new Stack<Integer>();
    }

    public void appendTail(int value) {
        stackA.push(value);
    }

    public int deleteHead() {
        if (stackB.isEmpty()) {
            if (stackA.isEmpty()) {
                return -1;
            } else {
                while (!stackA.empty()) {
                    stackB.push(stackA.pop());
                }
            }
        }
        return stackB.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
```



## [剑指 Offer 24. 反转链表](https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/)

定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

```java
示例:
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

---

解题思路：

头插法反转链表。

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode ans = new ListNode();

        ListNode temp = head;
        while (head != null) {
            temp = head;
            head = head.next;

            temp.next = ans.next;
            ans.next = temp;

        }
        return ans.next;
    }
}
```



## [剑指 Offer 30. 包含min函数的栈](https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/)

定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

```java
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.min();   --> 返回 -2.
```

---

解题思路：

双栈实现最小栈。

​	两个栈`stack`，`minStack`维持相同的大小，minStack的栈顶元素是当前对应的stack栈中的最小值。

​	在栈插入时，stack正常插入，minStack插入时，将插入元素与栈顶元素相比，如果栈顶元素更小，则插入栈顶元素，如果插入元素小，就插入需要插入的元素。这样维持着最小栈，当弹出最小值时只需要将minStack中的栈顶元素弹出。

```java
class MinStack {
    //双栈实现最小栈
    Stack<Integer> stack;
    Stack<Integer> minStack;
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    public void push(int x) {
        stack.push(x);
        if (minStack.empty()) {
            minStack.push(x);
            return;
        }
        minStack.push(x > minStack.peek() ? minStack.peek() : x);
    }
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    public int top() {
        return stack.peek();
    }
    public int min() {
        return minStack.peek();
    }
}
```





## [剑指 Offer 35. 复杂链表的复制](https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/)

请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。

<img src="asset/剑指 Offer.assets/e1.png" alt="img" style="zoom: 50%;" />

```java
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
```

---

解题思路：

​	使用Hash表保存新旧节点的映射关系，然后待新链表创建完成后再进行随机节点的复制。

```java

class Solution {
    public Node copyRandomList(Node head) {
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
        return ans.next;

    }
}
```

