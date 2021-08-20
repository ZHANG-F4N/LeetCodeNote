# LeetBook-LinkedList

[TOC]



## 链表操作：

- 插入操作

  【主要】先让新来的节点有所指向。

- 删除操作

  <img src="asset/LeetBook-LinkedList.assets/screen-shot-2018-04-26-at-203558-16287585297222.png" alt="img" style="zoom:67%;" />

  `prev.next = prev.next.next;`  

  还有一种删除操作是:

  1. 将当前节点的后继节点值赋给当前节点，`cur.val = cur.next.val` ，相当于把后继节点提前；

  2. 这时有两个重复的节点，然后`cur.next = cur.next.next`，相当于删除了后继节点。

     总结来说，也就是把后继节点的值保留在当前节点上，然后删除后继节点，避免寻找前序节点的复杂操作。

## 双指针：

```java
// 双指针模板
// Initialize slow & fast pointers
ListNode slow = head;
ListNode fast = head;
/**
 * Change this condition to fit specific problem.
 * Attention: remember to avoid null-pointer error
 **/
while (slow != null && fast != null && fast.next != null) {
    slow = slow.next;           // move slow pointer one step each time
    fast = fast.next.next;      // move fast pointer two steps each time
    if (slow == fast) {         // change this condition to fit specific problem
        return true;
    }
}
return false;   // change return value to fit specific problem
```

1. 判断链表中是否有环。

   这正是我们在链表中使用两个速度不同的指针时会遇到的情况：

   1. `如果没有环，快指针将停在链表的末尾。`
   2. `如果有环，快指针最终将与慢指针相遇。`

   如何判断相交点呢？

   <img src="asset/LeetBook-LinkedList.assets/142_fig1-16287585366313.png" alt="fig1" style="zoom: 50%;" />

   ​		当个 fast指针 和 slow指针 相遇时，fast指针 走了$a  +b +n(b+c) $， slow指针 走了 $ a+b$ ，fast指针 由于每次走2步，所以$ a+b+n(b+c) = 2(a+b)$，可以导出$a = c+(n-1)(b+c)$。

   从相遇点到入环点的距离加上 n-1n−1 圈的环长，恰好等于从链表头部到入环点的距离。

   ​		因此，当发现 slow 与 fast 相遇时，我们再额外使用一个指针 ptr 起始，它指向链表头部；随后，它和 slow 每次向后移动一个位置。最终，它们会在入环点相遇。

2. 寻找链表的中间值。

3. 删除倒数第n个结点。

4. 判断两个链表是否相交。

   ​		如果指针pA 不为空，则将指针 pA 移到下一个节点；如果指针 pB 不为空，则将指针 pB 移到下一个节点。

   ​		如果指针 pA 为空，则将指针 pA 移到链表 headB 的头节点；如果指针pB 为空，则将指针 pB 移到链表headA 的头节点。最后；两个指针会在相交处相遇。



## [138. 复制带随机指针的链表](https://leetcode-cn.com/problems/copy-list-with-random-pointer/)

给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。

构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。

例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。返回复制链表的头节点。

用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：

val：一个表示 Node.val 的整数。
random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
你的代码 只 接受原链表的头节点 head 作为传入参数。

![img](asset/LeetBook-LinkedList.assets/e1-16287585390984.png)

输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]

----

解题思路：

​		由于随机指针指向的位置是随机的，而链表只能顺序访问，并且顺序创建，所有需要在创建好复制节点后再进行随机结点的赋值。

​		使用HashMap来保存新旧节点的映射关系，所以可以直接映射复制后的随机结点位置。

```java

class Solution {
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
        return ans.next;
        
    }
}
```



## [142. 环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。

进阶：你是否可以使用 O(1) 空间解决此题？

![img](asset/LeetBook-LinkedList.assets/circularlinkedlist-16287585418385.png)

```
输入：head = [3,2,0,-4], pos = 1
输出：返回索引为 1 的链表节点
解释：链表中有一个环，其尾部连接到第二个节点。
```

---

解题思路：

​		当个 fast指针 和 slow指针 相遇时，fast指针 走了$a  +b +n(b+c) $， slow指针 走了 $ a+b$ ，fast指针 由于每次走2步，所以$ a+b+n(b+c) = 2(a+b)$，可以导出$a = c+(n-1)(b+c)$。从相遇点到入环点的距离加上 n-1n−1 圈的环长，恰好等于从链表头部到入环点的距离。

​		因此，当发现 slow 与 fast 相遇时，我们再额外使用一个指针 ptr 起始，它指向链表头部；随后，它和 slow 每次向后移动一个位置。最终，它们会在入环点相遇。

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fastP = head;
        ListNode slowP = head;

        while (fastP != null && fastP.next != null) {
            fastP = fastP.next.next;
            slowP = slowP.next;

            if (fastP == slowP) {
                ListNode pr = head;
                while (pr != slowP) {
                    pr = pr.next;
                    slowP = slowP.next;
                }
                return slowP;
            }
        }

        return null;
    }
}
```



## [203. 移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/)

给你一个链表的头节点 `head` 和一个整数 `val` ，请你删除链表中所有满足 `Node.val == val` 的节点，并返回 **新的头节点** 。

![img](asset/LeetBook-LinkedList.assets/removelinked-list-16287585448936.jpg)

```java
输入：head = [1,2,6,3,4,5,6], val = 6
输出：[1,2,3,4,5]
```

---

解题思路：

​		可以添加头节点来简单处理。

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        head = new ListNode(0, head);
        ListNode index = head;
        while (index != null && index.next != null) {
            while (index.next != null && index.next.val == val) {
                index.next = index.next.next;
            }
            index = index.next;
        }
        return head.next;
    }
}
```



## [707. 设计链表](https://leetcode-cn.com/problems/design-linked-list/)

```java
class MyLinkedList {
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
    //带头尾结点，方便操作。

    private static Node head = null;
    private static Node tail = null;
    private static int ListSize = 0;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        ListSize = 0;
        head = new Node();
        tail = head;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
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
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        head.next = new Node(val, head.next);
        if (head.next.next == null) {
            tail = head.next;
        }
        ListSize++;
        return;
    }
    
    /** Append a node of value val to the last element of the linked list. */
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
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
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
    
    /** Delete the index-th node in the linked list, if the index is valid. */
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

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
```

