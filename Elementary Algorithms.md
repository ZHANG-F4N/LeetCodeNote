# Elementary Algorithms

[TOC]





## [104. 二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)

给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

**说明:** 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    	3
       / \
      9  20
        /  \
       15   7

返回它的最大深度 3 。

---

解题思路:

​	深度优先搜索,每递归调用一次,就深度加一。

```java
class Solution {
    public int maxDepth(TreeNode root) {
        return DFS(root);
    }
    public  int DFS(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(DFS(root.left),DFS(root.right))+1;
    }
}
```





## [136. 只出现一次的数字](https://leetcode-cn.com/problems/single-number/)

给定一个**非空**整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

```java
输入: [4,1,2,1,2]
输出: 4
```

---

解题思路:

​	位运算，偶数次相同的数异或为 0000。

```java
//          4 	1	0	0
//          1	0	0	1
//         异或------------
//              1	0	1
//          2	0	1	0
//          异或-----------
//              1	1	1
//          1	0	0	1
//          异或-----------
//              1	1	0
//          2	0	1	0
//           异或----------
//              1	0	0
```

```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        //偶数次相同的数异或为 0000
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}
```

## [141. 环形链表](https://leetcode-cn.com/problems/linked-list-cycle/)

给定一个链表，判断链表中是否有环。

如果链表中存在环，则返回 true 。 否则，返回 false 。

**注意：`pos` 不作为参数进行传递**，仅仅是为了标识链表的实际情况。

```java
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```

---

解题思路:

​	快慢指针。

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fastP = head.next;
        ListNode slowP = head;
        while(fastP != slowP){
            if (fastP == null || fastP.next == null){
                return false;
            }
            fastP = fastP.next.next;
            slowP = slowP.next;
        }
        return true;
    }
}
```



## [217. 存在重复元素](https://leetcode-cn.com/problems/contains-duplicate/)

给定一个整数数组，判断是否存在重复元素。

如果存在一值在数组中出现至少两次，函数返回 `true` 。如果数组中每个元素都不相同，则返回 `false` 。

```java
输入: [1,2,3,1]
输出: true
```

---

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        //排序方式 O(nlog(n)) 实际快一点
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]){
                return true;
            }
        }
        return  false;
        /*
        使用统计的方式O(n)
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            if (!hashSet.add(num)) {
                return true;
            }
        }
        return false;
        */
    }
}
```

## [237. 删除链表中的节点](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/)

请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 **要被删除的节点** 。

输入：head = [4,5,1,9], node = 5
输出：[4,1,9]
解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.

---

解题思路:

​	将`node`节点的值用后一个节点的值进行覆盖。

```java
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```

## [234. 回文链表](https://leetcode-cn.com/problems/palindrome-linked-list/)

请判断一个链表是否为回文链表。

```java
输入: 1->2->2->1
输出: true
```

---

解题思路:

- 方法一:复制到数组操作。
- 方法二:将链表后半部分反转,然后让头节点与中间节点依次往后判断。

```java
//方法一:
class Solution {
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (head != null) {
            arrayList.add(head.val);
            head = head.next;
        }
        int len = arrayList.size();
        for (int i = 0; i < len>>1; i++) {
            if (arrayList.get(i) == arrayList.get(len-1-i)){
                continue;
            }else {
                return false;
            }
        }
        //System.out.println(Arrays.toString(arrayList.toArray()));
        return true;
    }
}
//方法二:
class Solution {
    public boolean isPalindrome(ListNode head) {
       if (head == null || head.next == null){
            return true;
        }

        ListNode fastP = head;
        ListNode slowP = head;
        while (fastP.next != null && fastP.next.next != null) {
            fastP = fastP.next.next;
            slowP = slowP.next;
        }

        slowP.next = reverseList(slowP.next);

        fastP = slowP.next;
        slowP = head;

        while (fastP != null) {
            if (slowP.val != fastP.val) {
                return false;
            }
            slowP = slowP.next;
            fastP = fastP.next;
        }
        return true;
    }
    public static ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode cursor = head.next;
        ListNode temp = null;
        head.next = null;
        while (cursor != null) {
            temp = cursor;
            cursor = cursor.next;
            temp.next = head;
            head = temp;
        }
        return head;
    }
}
```





## [350. 两个数组的交集 II](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/)

给定两个数组，编写一个函数来计算它们的交集。

```java
输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
```

---

解题思路:

​	Hash表:由于同一个数字在两个数组中都可能出现多次，因此需要用哈希表存储每个数字出现的次数。对于一个数字，其在交集中出现的次数等于该数字在两个数组中出现次数的最小值。

- 首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对应出现的次数
- 然后遍历第二个数组，对于第二个数组中的每个数字，如果在哈希表中存在这个数字，则将该数字添加到答案，并减少哈希表中该数字出现的次数。

为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，然后遍历较长的数组得到交集。

- 时间复杂度：*O(m+n*)

```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hashMapA = new HashMap<>();
        int temp[] = new int[nums1.length];
        int index = 0;
        for (int i : nums1) {
            hashMapA.put(i, hashMapA.containsKey(i) ? hashMapA.get(i) + 1 : 1);
        }
        for (int i : nums2) {
            if (hashMapA.containsKey(i) && hashMapA.get(i)!=0) {
                temp[index++] = i;
                hashMapA.put(i, hashMapA.get(i) - 1);
            }
        }
        int ans[] = Arrays.copyOf(temp,index);
        return ans;
    }
}
```



