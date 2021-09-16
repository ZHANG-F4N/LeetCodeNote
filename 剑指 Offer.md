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

## [剑指 Offer 26. 树的子结构](https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/)

输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。



    例如:
    给定的树 A:  
      3
     / \
    4   5
       / \
      1   2
    给定的树 B：
       4 
      /
     1
    返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
---

解题思路：

​	若树 B 是树 A 的子结构，则子结构的根节点可能为树 A 的任意一个节点。因此，判断树 B 是否是树 A 的子结构，需完成以下两步工作：

- 先序遍历树 A 中的每个节点 $n_A$ 对应函数 isSubStructure(A, B)
- 判断树 A 中 以 $n_A$为根节点的子树 是否包含树 B 。（对应函数 recur(A, B)）

```java
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }

        return recur(A, B) || (isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }
    public  boolean recur(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        } else if (A == null) {
            return false;
        }

        if (A.val != B.val) {
            return false;
        } else {
            return recur(A.left, B.left) && recur(A.right, B.right);
        }
    }
}
```

## [剑指 Offer 28. 对称的二叉树](https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/)



请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

        1
       / \
      2   2
     / \ / \
    3  4 4  3

但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

        1
       / \
      2   2
       \   \
       3    3
---

解题思路：

递归判断。

- 判断两节点 `L.left` 和 `R.right` 是否对称；
- 判断两节点 `L.right` 和 `R.left` 是否对称；

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return judge(root.left, root.right);
    }
    public boolean judge(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null && left.val == right.val) {
            return judge(left.left, right.right) && judge(left.right, right.left);
        } else {
            return false;
        }
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



## [剑指 Offer 34. 二叉树中和为某一值的路径](https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/)

输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。

```java
示例:
给定如下二叉树，以及目标和 target = 22，
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:
[
   [5,4,11,2],
   [5,8,4,5]
]
```

---

解题思路:

- DFS
- BFS

```java
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        DFS(root, list, target, 0, ans);
        return ans;
    }
    public  void DFS(TreeNode root, List<Integer> list, int tar, int sum, List<List<Integer>> ans) {
        if (root == null ) {
            return;
        }
        // if (root.val + sum == tar && (root.left != null || root.right != null)) {
        //     return;
        // }
        if (root.val + sum == tar && root.left == null && root.right == null) {
            list.add(root.val);
            List<Integer> temp = new ArrayList<Integer>();
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                temp.add(it.next());
            }
            ans.add(temp);
            list.remove(list.size() - 1);
            return;
        }
        list.add(root.val);
        DFS(root.left, list, tar, sum + root.val, ans);
        DFS(root.right, list, tar, sum + root.val, ans);
        list.remove(list.size() - 1);

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

## [剑指 Offer 45. 把数组排成最小的数](https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/)

输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

```python
输入: [3,30,34,5,9]
输出: "3033459"
```

---

解题思路:

- 重写排序。

  设数组 nums 中任意两数字的字符串为 x 和 y ，则规定 排序判断规则 为：

  若拼接字符串 x + y > y + x ，则 x “大于” y ；
  反之，若 x + y < y + x，则 x “小于” y ；
  x “小于” y 代表：排序完成后，数组中 x 应在 y 左边；“大于” 则反之。

  根据以上规则，套用任何排序方法对 numsnums 执行排序即可。


```java
class Solution {
    public String minNumber(int[] nums) {
        String[] number = new String[nums.length];
        for (int i = 0; i < number.length; i++) {
            number[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(number, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return s1.compareTo(s2);
            }
        });
        StringBuilder ans = new StringBuilder();
        for (String s : number) {
            ans.append(s);
        }
        return ans.toString();
    }
}
```



## [剑指 Offer 46. 把数字翻译成字符串](https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/)

给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

```java
示例 1:
输入: 12258
输出: 5
解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
```

---

解题思路：

- 动态规划。
  $$
  dp[i] = \begin{cases}
  dp[i-1]+dp[i-2],& 10\le x_i x_{i-1} \le25 \\
  dp[i-1],&else \\
  \end{cases}
  $$
  

```java
class Solution {
    public int translateNum(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            if (num <= 25) {
                return 2;
            }
            return 1;
        }
        int[] dp = new int[str.length()];
        dp[0] = 1;
        int temp = Integer.parseInt(str.substring(0, 2));
        dp[1] = temp <= 25  && temp >= 10? 2 : 1;
        for (int i = 2; i < n; i++) {
            temp = Integer.parseInt(str.substring(i - 1, i + 1));
            if (temp >= 10 && temp <= 25) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[n - 1];
    }
}
```



## [剑指 Offer 36. 二叉搜索树与双向链表](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/)

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

为了让您更好地理解问题，以下面的二叉搜索树为例：

<img src="asset/%E5%89%91%E6%8C%87%20Offer.assets/1599401091-PKIjds-Picture1.png" alt="Picture1.png" style="zoom:67%;" />

我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。

特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。

---

解题思路:

- 中序遍历，中序遍历排序二叉树，所得的结果序列递增。所以选择中序遍历，在遍历过程中修改指针。

```java
class Solution {
    static Node pre, head;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        pre= null;
        head = null;
        midOrder(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    public void midOrder(Node root) {
        if (root == null) {
            return;
        }
        midOrder(root.left);
//      1. 修改前一个节点的后 为当前节点
//      2. 修改当前节点的前 为前一个节点
//      3. 更新当前节点为前一个节点
        if (pre != null) {
            pre.right = root;
        } else {
            head = root;
        }
        root.left = pre;

        pre = root;
        midOrder(root.right);

    }
}
```

## [剑指 Offer 41. 数据流中的中位数](https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/)

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。

```java
示例 1：
输入：
["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
[[],[1],[2],[],[3],[]]
输出：[null,null,null,1.50000,null,2.00000]
```

---

解题思路:

- 大顶堆 + 小顶堆。

  建立一个 小顶堆 AA 和 大顶堆 BB ，各保存列表的一半元素，且规定：

  - A 保存 较大 的一半，长度为 $\frac{N}{2}$ （N 为偶数）或 $\frac{N+1}{2}$ （N为奇数）;

  - BB 保存 较小 的一半，长度为 $\frac{N}{2}$ （N 为偶数）或 $\frac{N-1}{2} $（NN 为奇数）；

    随后，中位数可仅根据 A,B 的堆顶元素计算得到。

```java
class MedianFinder {
    /** initialize your data structure here. */
    private PriorityQueue<Integer> smallHeap;
        private PriorityQueue<Integer> bigHeap;
    public MedianFinder() {
        smallHeap = new PriorityQueue<>();
            bigHeap = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
    }
    public void addNum(int num) {
        if (bigHeap.isEmpty() || num <= bigHeap.peek()) {
                bigHeap.offer(num);
            } else {
                smallHeap.offer(num);
            }
            int N1 = bigHeap.size();
            int N2 = smallHeap.size();
            if (N1 - N2 > 1) {
                smallHeap.offer(bigHeap.poll());
            }
            if (N1 - N2 <= -1) {
                bigHeap.offer(smallHeap.poll());
            }
    }
    public double findMedian() {
        int N1 = bigHeap.size();
            int N2 = smallHeap.size();
            if (N1 == N2) {
                return ((double) smallHeap.peek() + bigHeap.peek()) / 2;
            }
            return bigHeap.peek();
    }
}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```



## [剑指 Offer 48. 最长不含重复字符的子字符串](https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/)

请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

```java
示例 1:
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

---

解题思路：

- 双指针+哈希表。

  哈希表存储字符最后一次出现的索引，遍历字符串，双指针统计`最长不含重复字符`的子串。

  右指针依次遍历每个字符。

  当右指针指向的元素在前面出现过时，更新左指针。$$left = Math.max(hashMap.get(ch) + 1, left);$$

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
         HashMap<Character, Integer> hashMap = new HashMap<>();
        if (s.length() == 0) {
            return 0;
        }
        int max = 1;
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            char ch = s.charAt(right);
            if (hashMap.containsKey(ch)) {
                left = Math.max(hashMap.get(ch) + 1, left);
                hashMap.replace(ch, right);
            } else {
                hashMap.put(ch, right);
            }
            max = max > right - left + 1 ? max : right - left + 1;
            right++;
        }
        return max;
    }
}
```

