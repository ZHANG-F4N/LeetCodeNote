# Elementary Algorithms

[TOC]

##  28. 实现 strStr() KMP

给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

```java
输入: haystack = "hello", needle = "ll"
输出: 2
```

---

解题思路
	使用KMP算法，KMP算法的关键是构建Next数组，为什么要用Next数组呢？是为了在一次匹配失败后可以不用在从头匹配字符串，只需要从子串的某个位置重新匹配，而这个位置就是要又Next数组来提供。

Next数组提供了 `子串` 的内部规律。

核心就是：找到了最长相等的前缀和后缀，匹配失败的位置是后缀子串的后面，那么我们找到与其相同的前缀的后面重新匹配就可以了。

- 假设已经知道了 next[x-1]（以下记为now），如果 P[x] 与 P[now] 一样，那最长相等前后缀的长度就可以扩展一位，很明显 next[x] = now + 1.

  <img src="Elementary Algorithms.assets/v2-6d6a40331cd9e44bfccd27ac5a764618_720w.jpg" alt="img" style="zoom:50%;" />

- 当P[now]与P[x]不相等的时候，我们需要缩小now —— 把now变成next[now-1]，直到P[now]=P[x]为止。P[now]=P[x]时，就可以直接向右扩展了。

  <img src="Elementary Algorithms.assets/v2-ce1d46a1e3603b07a13789b6ece6022f_720w.jpg" alt="img" style="zoom:50%;" />

```python
class Solution {
/*
        A	B	C	A	B	B
next    0	0	0	1	2	0

        a	b	c	a	b	d	d	d	a	b	c	a	b	c
next    0	0	0	1	2	0	0	0	1	2	3	4	5	3
    
    
*/
    public int strStr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }
        int len = needle.length();
        int[] next = new int[len];
        next[0] = 0;
        int k = 0;
        for (int i = 1; i < len; i++) {
            //Java数组会初始化0,所以不需要对j=0进行处理.
            while (k > 0 && needle.charAt(i) != needle.charAt(k)) {
                k = next[k - 1];
            }
            if (needle.charAt(i) == needle.charAt(k)) {
                next[i] = ++k;
            }
        }
        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == needle.length()) {
                return (i - j + 1);
            }
        }
        return -1;
    }
}
```

## [36. 有效的数独](https://leetcode-cn.com/problems/valid-sudoku/)

请你判断一个 `9x9` 的数独是否有效。只需要 **根据以下规则** ，验证已经填入的数字是否有效即可。

1. 数字 `1-9` 在每一行只能出现一次。
2. 数字 `1-9` 在每一列只能出现一次。
3. 数字 `1-9` 在每一个以粗实线分隔的 `3x3` 宫内只能出现一次。（请参考示例图）

数独部分空格内已填入了数字，空白格用 `'.'` 表示。

**注意：**

- 一个有效的数独（部分已被填充）不一定是可解的。
- 只需要根据以上规则，验证已经填入的数字是否有效即可。

```python
输入：board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：true
```

---

解题思路:

​	主要是九个宫格的地址映射，在计算机整数运算里面 $({i}/{3})*3 \neq {i} $ 。


```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashMap<Character, Integer>[] rowHashMap = new HashMap[9];
        HashMap<Character, Integer>[] colHashMap = new HashMap[9];
        HashMap<Character, Integer>[] boxHashMap = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            colHashMap[i] = new HashMap<>();
            rowHashMap[i] = new HashMap<>();
            boxHashMap[i] = new HashMap<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char temp = board[i][j];
                if (temp != '.') {
                    rowHashMap[i].put(temp, rowHashMap[i].getOrDefault(temp, 0) + 1);
                    colHashMap[j].put(temp, colHashMap[j].getOrDefault(temp, 0) + 1);
                    boxHashMap[(i / 3) * 3 + j / 3].put(temp, boxHashMap[(i / 3) * 3 + j / 3].getOrDefault(temp, 0) + 1);
                    if (rowHashMap[i].get(temp) > 1 || colHashMap[j].get(temp) > 1 || boxHashMap[(i / 3) * 3 + j / 3].get(temp) > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
```



## [98. 验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/)

给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

- 节点的左子树只包含小于当前节点的数。
- 节点的右子树只包含大于当前节点的数。
- 所有左子树和右子树自身必须也是二叉搜索树。

```java
输入:
    2
   / \
  1   3
输出: true
```

---

解题思路:

​	BST中序遍历结果递增。

```java
class Solution {
    private  long  pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            if (pre >= root.val) {
                return false;
            }
            pre = root.val;
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }
}
```



## [101. 对称二叉树](https://leetcode-cn.com/problems/symmetric-tree/)

给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 `[1,2,2,3,4,4,3]` 是对称的。

```java
    1
   / \
  2   2
 / \ / \
3  4 4  3
```

但是下面这个 `[1,2,2,null,3,null,3]` 则不是镜像对称的:

```java
    1
   / \
  2   2
   \   \
   3    3
```

---

解题思路:

如果同时满足下面的条件，两个树互为镜像：

- 它们的两个根结点具有相同的值
- 每个树的右子树都与另一个树的左子树镜像对称

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        return check(root,root);
    }
    public  boolean check(TreeNode left,TreeNode right){
        if(left == null && right == null){
            return true;
        }
        if (left == null || right == null){
            return false;
        }
        return left.val == right.val && check(left.left,right.right) && check(left.right,right.left);
    }
}
```



## [102. 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)

给你一个二叉树，请你返回其按 **层序遍历** 得到的节点值。 （即逐层地，从左到右访问所有节点）。

二叉树：[3,9,20,null,null,15,7],

```java
    3
   / \
  9  20
    /  \
   15   7

```
```java
返回其层序遍历结果：
[
  [3],
  [9,20],
  [15,7]
]
```

---

解题思路:

​	每次访问一层,使用队列保存访问节点。

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while(!deque.isEmpty()){
            ArrayList<Integer> levelList = new ArrayList<>();
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = deque.poll();
                levelList.add(temp.val);
                if(temp.left != null){
                    deque.offer(temp.left);
                }
                if(temp.right != null){
                    deque.offer(temp.right);
                }
            }
            list.add(levelList);
        }
        return list;
    }
}
```



## [108. 将有序数组转换为二叉搜索树](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/)

给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。

高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。

```java
输入：nums = [-10,-3,0,5,9]
输出：[0,-3,9,-10,null,5]
解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案
```

![img](Elementary Algorithms.assets/btree2.jpg)

---

解题思路:

​	递归建立二叉树，注意建立方式。

​	二叉搜索树的中序遍历是升序序列，题目给定的数组是按照升序排序的有序数组，因此可以确保数组是二叉搜索树的中序遍历序列。

```java
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        //中序遍历递增
        return build(nums, 0, nums.length - 1);
    }
    public  TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (right + left) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }
}

```



## [118. 杨辉三角](https://leetcode-cn.com/problems/pascals-triangle/)

给定一个非负整数 *numRows，*生成杨辉三角的前 *numRows* 行。

```java
输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

```

---

解题思路:

```java
1				
1	1			
1	2	1		
1	3	3	1	
1	4	6	4	1
```

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> ansList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    temp.add(1);
                } else {
                    temp.add(ansList.get(i-1).get(j-1)+ansList.get(i-1).get(j));
                }
            }
            ansList.add(temp);
        }
        return ansList;
    }
}
```

## [121. 买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)

给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

```python
输入：[7,1,5,3,6,4]
输出：5
解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。

```

---

```java
class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        //  7	1	5	3	6	4

        int minPrice=Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i]<minPrice){
                minPrice = prices[i];
            }
            else if(prices[i]-minPrice >ans){
                ans = prices[i]-minPrice;
            }
        }
        return ans;
    }
}
```



## [125. 验证回文串](https://leetcode-cn.com/problems/valid-palindrome/)

给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

**说明：**本题中，我们将空字符串定义为有效的回文串。

```java
输入: "A man, a plan, a canal: Panama"
输出: true
```

---

```java
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        String temp =  s.toLowerCase();
        while (left < right) {
            while (left<right && !((temp.charAt(left) >= 'a' && temp.charAt(left) <= 'z') || (temp.charAt(left) >= '0' && temp.charAt(left) <= '9')) ){
                left++;
            }
            while (left<right && !((temp.charAt(right) >= 'a' && temp.charAt(right) <= 'z') || (temp.charAt(right) >= '0' && temp.charAt(right) <= '9')) ){
                right--;
            }
            if(left<right && (temp.charAt(left++) != temp.charAt(right--))){
                return false;
            }
        }
        return true;
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

## [190. 颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/)

颠倒给定的 32 位无符号整数的二进制位。

```java
输入: 00000010100101000001111010011100
输出: 00111001011110000010100101000000
解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
     因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
```

---

解题思路:

​	n逐渐右移,每次筛选出n最后一位是0还是1,然后将这一位左移31-i位,再赋值给ans,就可以得到结果.

```java
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans |= (n & 1 )<<(31-i);
            n >>= 1;
        }
        return ans;
    }
}
```



## [191. 位1的个数](https://leetcode-cn.com/problems/number-of-1-bits/)

编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。

提示：

- 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。

- 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 1 中，输入表示有符号整数 -3。

```java
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```

```java
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```

---

解题思路:

我们可以直接循环检查给定整数 n 的二进制位的每一位是否为 1。具体代码中，当检查第 i 位时，我们可以让 n 与 $2^i$进行与运算，当且仅当 n 的第 i 位为 1 时，运算结果不为 0。

```java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if((n & (1<<i)) != 0){
                ans++;
            }
        }
        return ans;
    }
}
```



## [204. 计数质数](https://leetcode-cn.com/problems/count-primes/)

统计所有小于非负整数 *n* 的质数的数量。

```java
输入：n = 10
输出：4
解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
```

---

解题思路:

方法一:枚举

- 如果 *y* 是 *x* 的因数，那么 $\frac{x}{y}$ 也必然是 x 的因数，因此我们只要校验 *y* 或者 $\frac{x}{y} $ 即可。而如果我们每次选择校验两者中的较小数，则不难发现较小数一定落在 \[2,$\sqrt{x}$]的区间中，因此我们只需要枚举 \[2,$\sqrt{x}$] 中的所有数即可，这样单次检查的时间复杂度从O(n) 降低至了 $O(\sqrt{n})$。

方法二:埃氏筛

- 如果 *x* 是质数，那么大于 *x* 的 *x* 的倍数 $2x,3x,\ldots $一定不是质数，因此我们可以从这里入手。
- 我们设isPrime[i] 表示数 i 是不是质数，如果是质数则为 1，否则为 0。从小到大遍历每个数，如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身），即 0，这样在运行结束的时候我们即能知道质数的个数。
- 当然这里还可以继续优化，对于一个质数 x，如果按上文说的我们从 $2x$ 开始标记其实是冗余的，应该直接从 $x\cdot x$ 开始标记，因为 $2x,3x,\ldots$ 这些数一定在 x 之前就被其他数的倍数标记过了，例如 2 的所有倍数，3 的所有倍数等。

```java
class Solution {
    public int countPrimes(int n) {
        if (n == 0 || n == 1) {
            return 0;
        }
        int sqrt_n = (int) Math.sqrt(n);
        int ans = 0;
        boolean[] isPrimes = new boolean[n];
        for (int i = 2; i < isPrimes.length; i++) {
            int temp = 2;
            if (!isPrimes[i] && i <= sqrt_n) {
                while (i * temp < n) {
                    isPrimes[i * temp] = true;
                    temp++;
                }
            }
            if (!isPrimes[i] ) {
                ans++;
            }
        }
        //System.out.println(Arrays.toString(isPrimes));
        return ans;
    }
}
```



```java
class Solution {
    public int countPrimes(int n) {
        if (n == 0 || n == 1) {
            return 0;
        }
        int sqrt_n = (int) Math.sqrt(n);
        int ans = 0;
        boolean[] isPrimes = new boolean[n];
        for (int i = 2; i < isPrimes.length; i++) {
            int temp = 2;
            if (!isPrimes[i] && i <= sqrt_n) {
                while (i * temp < n) {
                    isPrimes[i * temp] = true;
                    temp++;
                }
            }
            if (!isPrimes[i] ) {
                ans++;
            }
        }
        //System.out.println(Arrays.toString(isPrimes));
        return ans;
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

## [242. 有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/)

给定两个字符串 *s* 和 *t* ，编写一个函数来判断 *t* 是否是 *s* 的字母异位词。

```java
输入: s = "anagram", t = "nagaram"
输出: true
```

---

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        int flag[] = new int[26];
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            flag[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            flag[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (flag[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
```



## [278. 第一个错误的版本](https://leetcode-cn.com/problems/first-bad-version/)

你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。

假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。

你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。

```java
给定 n = 5，并且 version = 4 是第一个错误的版本。
1	2	3	4	5	
F	F	F	T	T
调用 isBadVersion(3) -> false
调用 isBadVersion(5) -> true
调用 isBadVersion(4) -> true
所以，4 是第一个错误的版本。 
```

---

解题思路:

​	二分查找。

```java
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if(n == 1){
            return 1;
        }
        int left = 1;
        int right = n;
        // int mid = (left + right)>>1;
        int mid = left + ((right - left +1)>>1);
        while(left <= right){
            //System.out.println("mid--"+mid+"left--"+left+"right--"+right);
            if(isBadVersion(mid)){
                right = mid-1;
            }else{
                left = mid + 1;
            }
            mid =  left + ((right - left +1)>>1);
        }
        return mid;
    }
}
```



## [283. 移动零](https://leetcode-cn.com/problems/move-zeroes/)

给定一个数组 `nums`，编写一个函数将所有 `0` 移动到数组的末尾，同时保持非零元素的相对顺序。

```java
输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
```

---

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int zeroNum = 0;
        int len =  nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0){
                continue;
            }
            nums[zeroNum++] = nums[i];
        }
        for(int i = zeroNum;i<len;i++){
            nums[i] =0;
        }
    }
}
```



## [326. 3的幂](https://leetcode-cn.com/problems/power-of-three/)

给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。

整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x

```java
输入：n = 27
输出：true
```

---

解题思路:

- 方法一:循环迭代

找出数字 n 是否是数字 b 的幂的一个简单方法是，n%3 只要余数为 0，就一直将 n 除以 b。

$\begin{aligned} n &= b^x  \quad n =b \times b \times \ldots \times b \end{aligned}$



因此，应该可以将 n 除以 b x 次，每次都有 0 的余数，最终结果是 1。

- 方法二:整数限制

我们可以看出 `n` 的类型是 `int`。在 Java 中说明了该变量是四个字节，他的最大值为 **2147483647**。

知道了 `n` 的限制，我们现在可以推断出 `n` 的最大值，也就是 3 的幂，是 **1162261467**

计算如下：$3^{\lfloor{}\log_3{MaxInt}\rfloor{}} = 3^{\lfloor{}19.56\rfloor{}} = 3^{19} = 1162261467$

因此，我们应该返回 true 的 n 的可能值是 $3^0$，$3^1$…$3 ^ {19}$。因为 3 是质数，所以 $3^{19}$的除数只有 $3^0$，$3^1$,…$3 ^{19}$，因此我们只需要将 $3^{19}$除以 n。若余数为 0 意味着 n 是$3^{19}$的除数，因此是 3 的幂。

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
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



## [387. 字符串中的第一个唯一字符](https://leetcode-cn.com/problems/first-unique-character-in-a-string/)

给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

```java
s = "leetcode"
返回 0

s = "loveleetcode"
返回 2
```

---

解题思路:

​	统计字符出现频率。

```java
class Solution {
    public int firstUniqChar(String s) {
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if(chars[s.charAt(i)-'a'] == 1){
                return i;
            }
        }
        return -1;
    }
}
```



## [461. 汉明距离](https://leetcode-cn.com/problems/hamming-distance/)

两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。

给出两个整数 x 和 y，计算它们之间的汉明距离。

**注意：**
	0 ≤ x, y < $2^{31}$.

```java
输入: x = 1, y = 4
输出: 2
解释:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
上面的箭头指出了对应二进制位不同的位置。
```

---

解题思路:

​	两数先进行异或,筛选出所有不同位,异或结果中为1的位,就是不同位置的地方,进行计数统计即可。

```java
class Solution {
    public int hammingDistance(int x, int y) {
        int ans = 0;
        int temp = x ^ y;
        for (int i = 0; i < 32; i++) {
            if ((temp&1<<i)!=0){
                ans++;
            }
        }
        return ans;
    }
}
```

