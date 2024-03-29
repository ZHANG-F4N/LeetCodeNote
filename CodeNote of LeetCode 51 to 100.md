# Code-note of LeetCode 51 to 100

[TOC]

$$
ans=\frac{mc^2}{\int_{1}^{2}{x}dx(\sum_{n=1}^{100}{a_n})\lim_{n\rightarrow+\infty}f(n)}
$$
## Question 53 最大子序和

给定一个整数数组 `nums` ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

```java
输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
```

---

解题思路：

动态规划的是首先对数组进行遍历，当前最大连续子序列和为 sum，结果为 ans

- 如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
- 如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
- 每次比较 sum 和 ans的大小，将最大值置为ans，遍历结束返回结果
- 时间复杂度：O(n)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int MAXArray = nums[0];
        for (int i = 1;i<nums.length;i++){
            if(nums[i-1] > 0){
                nums[i]=nums[i]+nums[i-1];
            }
            if (MAXArray < nums[i]){
                MAXArray = nums[i];
            }
        }
        return MAXArray;
    }
}
```

## Question 56 合并区间

以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

```java
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
```

---

解题思路：

首先，我们将列表中的区间按照左端点升序排序。然后我们将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：

- 如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，我们可以直接将这个区间加入数组 merged 的末尾；

- 否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        
        ArrayList<int[]> List = new ArrayList();
        List.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= ( List.get(List.size() - 1))[1]) {
                ( List.get(List.size() - 1))[1] = Math.max(( List.get(List.size() - 1))[1], intervals[i][1]);
            } else {
                List.add(intervals[i]);
            }
        }
        return List.toArray(new int[List.size()][]);
    }
}
```



## Question 58 最后一个单词的长度



给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0 。

单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。

```s
输入：s = "Hello World"
输出：5
```

---

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int ans = 0;
        String str = s.trim();
        for(int i = str.length()-1;i >= 0;i--){
            if ( str.charAt(i) == ' '){
                break;
            }
            ans++;
        }
        System.out.println(ans);
        return ans;
    }
}
```



## Question 61 旋转链表

给你一个链表的头节点 `head` ，旋转链表，将链表每个节点向右移动 `k` 个位置。

 ![img](asset/CodeNote of LeetCode 51 to 100.assets/rotate1.jpg)

```java
输入：head = [1,2,3,4,5], k = 2
输出：[4,5,1,2,3]
```

---

解题思路：

- 右移的长度可能超过链表长，所以需要取模。
- 也可以采用循环链表来处理：
  - 首先找到链表尾部，将链表转化为循环链表。
  - 然后根据K的值确定出新表头的位置，在新表头前面进行断环操作。

```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        int listLength = 0;
        ListNode  tour = head;
        while( tour != null){
            tour= tour.next;
            listLength++;
        }
        if (listLength <=1){
            return head;
        }
        tour = head;
        k = listLength - (k % listLength)  ;
        if (k == listLength ){
            return head;
        }
        while(--k > 0 ){
            tour = tour.next;
        }
        ListNode temp = tour.next;
        tour.next = null;
        tour = temp;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next = head;
        head=tour;
        return  head;
    }
}
```



## Question 62 不同路径

一个机器人位于一个 `m x n` 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？

```java
输入：m = 3, n = 7
输出：28
```

---

解题思路：

- 组合数学方法

  机器人只能朝下或右走，将其横向和纵向走的路程平移到一起，相当于组合数学问题，m+n个位置里取n个。

- 动态规划法

  转移方程*f*(*i*,*j*)=*f*(*i*−1,*j*)+*f*(*i*,*j*−1)

  <img src="asset/CodeNote of LeetCode 51 to 100.assets/image-20210525204846175.png" alt="image-20210525204846175" style="zoom:50%;" />

```java 
class Solution {
    public int uniquePaths(int m, int n) {
        int min = Math.min(m - 1, n - 1);
        int total = m + n - 2;
        long ans = 1;
        for (int i = total, j = 1; i >= total - min + 1; i--, j++) {
            ans = ans * i / j;
        }
        return (int) ans;
    }
}
```



## Question 63 不同路径II

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

![img](asset/CodeNote of LeetCode 51 to 100.assets/robot1.jpg)

```java
输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
输出：2
解释：
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
```

---

解题思路：

  - 动态规划

    将有障碍物的位置置为0

    - 我们可以运用「滚动数组思想」把空间复杂度优化称 *O*(*m*)。
    - 「滚动数组思想」是一种常见的动态规划优化方法

<img src="asset/CodeNote of LeetCode 51 to 100.assets/image-20210525205205735.png" alt="image-20210525205205735" style="zoom:67%;" />

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] ==1){
            return 0;
        }
        int ans = 0;
        int col = obstacleGrid[0].length;
        int row = obstacleGrid.length;
        obstacleGrid[0][0] = 1;
        for (int i = 1; i < col; i++) {
            if (obstacleGrid[0][i] == 1) {
                obstacleGrid[0][i] = 0;
            } else {
                obstacleGrid[0][i] = obstacleGrid[0][i-1];
            }
        }
        for (int i = 1; i < row; i++) {
            if (obstacleGrid[i][0] == 1) {
                obstacleGrid[i][0] = 0;
            } else {
                obstacleGrid[i][0] = obstacleGrid[i-1][0];
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = 0;
                }else {
                    obstacleGrid[i][j] = obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
                }
            }
        }
        ans = obstacleGrid[row-1][col-1];
        return ans;
    }
}
```



## Question 64 最小路径和

给定一个包含非负整数的 *m* x *n* 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

**说明：**每次只能向下或者向右移动一步。

![img](asset/CodeNote of LeetCode 51 to 100.assets/minpath.jpg)

```java
输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。
```

---

动态规划法

- 转移方程*f*(*i*,*j*)=*dp*\[i][*j*]=min(*dp*\[*i*−1][*j*],*dp*\[*i*][*j*−1])+*grid*\[*i*][*j*]。
- 我们可以运用「滚动数组思想」把空间复杂度优化称 *O*(*m*)。

```java
class Solution {
    public int minPathSum(int[][] grid) {
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] += grid[0][i-1];
        }
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] += grid[i-1][0];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j]=Math.min(grid[i-1][j]+grid[i][j],grid[i][j-1]+grid[i][j]);
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
}
```



## Question 66 加一

给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

```java
输入：digits = [1,2,3]
输出：[1,2,4]
解释：输入数组表示数字 123。
```

---

```java
class Solution {
    public int[] plusOne(int[] digits) {
      for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            if(digits[i]!=0){
                return digits;
            }
        }
        int[] ints = new int[digits.length+1];
        ints[0] = 1;
        return ints;
    }
}
```



## Question 69 $x$的平方根

难度简单682收藏分享切换为英文接收动态反馈

实现 int sqrt(int x) 函数。

计算并返回 x 的平方根，其中 x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

```java
输入: 8
输出: 2
说明: 8 的平方根是 2.82842..., 
     由于返回类型是整数，小数部分将被舍去。
```

```java
class Solution {
    public int mySqrt(int x) {
        //1特殊处理
        if (x == 1) {
            return 1;
        }
        int left = 1, right = x >> 1;
        int mid = (left + right) >> 1;
        //<=才能向下取整
        while (left <= right) {
            //防爆x=2147395599
            if ((long)mid * mid == x) {
                return mid;
            }
            if ((long)mid * mid > x) {
                right = mid - 1;
            }
            if ((long)mid * mid < x) {
                left = mid + 1;
            }
            mid = (left + right) >> 1;
        }
        return mid;
    }
}
```



## Question 70 爬楼梯

假设你正在爬楼梯。需要 *n* 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

```java
输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶
```

---

解题思路：

​		斐波那契Fibonacci数列，递推算法。

```java
class Solution {
    public int climbStairs(int n) {
        int a1 = 1;
        int a2 = 1;
        if(n == 1){
            return 1;
        }
        int ans = 1;
        for(int i = 1;i<n;i++){
            ans = a1+a2;
            a1 = a2;
            a2 = ans;
        }
        return ans;
    }
}
```



## Question 80 删除有序数组中的重复项II

给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

```java
输入：nums = [1,1,1,2,2,3]
输出：5, nums = [1,1,2,2,3]
解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
```

---

另外一种解法是，使用步长为2的快慢指针来进行判断，不相等就赋值。

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int voidIndex = 1;
        if(nums.length <=1){
            return 1;
        }
        int  flag =1 ;//相等个数
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[voidIndex-1] ){
                flag = 1;
                nums[voidIndex++] = nums[i];
            }else {
                flag++;
                if (flag>2){
                    continue;
                }
                else {
                    nums[voidIndex++] = nums[i];
                }
            }
        }
        //System.out.println(Arrays.toString(nums)+voidIndex);
        return  voidIndex;
    }
}
```



## Question 82 删除排序链表中的重复元素 II

存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。

返回同样按升序排列的结果链表。

<img src="asset/CodeNote of LeetCode 51 to 100.assets/linkedlist1.jpg" alt="img" style="zoom:67%;" />

```java
输入：head = [1,2,3,3,4,4,5]
输出：[1,2,5]
```

---





## Question 83 删除排序链表中的重复元素

存在一个按升序排列的链表，给你这个链表的头节点 `head` ，请你删除所有重复的元素，使每个元素 **只出现一次** 。

返回同样按升序排列的结果链表。

![img](asset/CodeNote of LeetCode 51 to 100.assets/list2.jpg)

```java
输入：head = [1,1,2,3,3]
输出：[1,2,3]
```

---

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode preTemp = head;
        ListNode ofterTemp = head.next;
        while(ofterTemp != null){
            if(preTemp.val == ofterTemp.val){
                ofterTemp = ofterTemp.next;
            }else {
                preTemp.next = ofterTemp;
                ofterTemp = ofterTemp.next;
                preTemp = preTemp.next;
            }
        }
        //这一句在最后有重复元素时,删除多余的重复元素.
        preTemp.next = null;
        return head;
    }
}
```



## Question 86 分隔链表
给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。

你应当 保留 两个分区中每个节点的初始相对位置。

![img](asset/CodeNote of LeetCode 51 to 100.assets/partition.jpg)

```java
输入：head = [1,4,3,2,5,2], x = 3
输出：[1,2,2,4,3,5]
```

---

```java
class Solution {
    public ListNode partition(ListNode head, int x) {
                //双指针
        ListNode small = new ListNode();
        ListNode smallHead = small;
        ListNode big = new ListNode();
        ListNode bigHead = big;
        ListNode temp = head;

        //ListNode ans = null;
        while (temp != null) {
            if (temp.val < x) {
                small.next = temp;
                temp = temp.next;
                small = small.next;
                small.next=null;
            } else {
                big.next = temp;
                temp = temp.next;
                big = big.next;
                big.next=null;
            }
        }
        bigHead = bigHead.next;
        if (bigHead != null) {
            small.next = bigHead;
        }
        return smallHead.next;
    }
}
```




## Question 88 合并两个有序数组

给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。

初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。

```shell
输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
输出：[1,2,2,3,5,6]
```

---

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int j = n - 1, i = m - 1, k = m + n - 1;
        for (; i >= 0 && j >= 0 && k >= 0; k--) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
        }
        if (i == -1) {
            for (;k>=0;k--){
                nums1[k] =nums2[j];
                j--;
            }
            // System.out.println(Arrays.toString(nums1));
            return;
        }
        if (j == -1){
            for (;k>=0;k--){
                nums1[k] =nums1[i];
                i--;
            }
            // System.out.println(Arrays.toString(nums1));
            return;
        }
    }
}
```

## [89. 格雷编码](https://leetcode-cn.com/problems/gray-code/)

n 位格雷码序列 是一个由 2n 个整数组成的序列，其中：
每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
第一个整数是 0
一个整数在序列中出现 不超过一次
每对 相邻 整数的二进制表示 恰好一位不同 ，且
第一个 和 最后一个 整数的二进制表示 恰好一位不同
给你一个整数 n ，返回任一有效的 n 位格雷码序列 。

```java
输入：n = 2
输出：[0,1,3,2]
解释：
[0,1,3,2] 的二进制表示是 [00,01,11,10] 。
- 00 和 01 有一位不同
- 01 和 11 有一位不同
- 11 和 10 有一位不同
- 10 和 00 有一位不同
```

---

解题思路:

- 找规律-模拟 规律:他们除过第一位上下镜像，开始的半部分即为前一个阶的格雷编码

  ```java
  n   1   2   3
      0   00  000
      1   01  001
          --  011
          11  010
          10  ---
              110
              111
              101
              100
  ```

```java
class Solution {
    public List<Integer> grayCode(int n) {
        if (n == 1) {
            return Arrays.asList(new Integer[]{0, 1});
        }
        Integer[] ints = {0, 1, 3, 2};
        List<Integer> ans = new ArrayList<>(Arrays.asList(ints));
        for (int i = 3; i <= n; i++) {
            int num = ans.size();
            for (int j = num - 1; j >= 0; j--) {
                ans.add(ans.get(j) + (1 << (i-1)));
            }
        }
        return ans;
    }
}
```





## Question 94 二叉树的中序遍历

给定一个二叉树的根节点 `root` ，返回它的 **中序** 遍历。

<img src="asset/CodeNote of LeetCode 51 to 100.assets/inorder_1.jpg" alt="img" style="zoom:50%;" />

```java
输入：root = [1,null,2,3]
输出：[1,3,2]
```

---

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public static ArrayList<Integer> nums=new ArrayList<Integer>();
    public List<Integer> inorderTraversal(TreeNode root) {
        nums.clear();
        inorder(root);
        //  Iterator it1 = nums.iterator();
        // while(it1.hasNext()){
        //     System.out.println(it1.next());
        // }
        return nums;
    }
    public static void  inorder(TreeNode root){
        if(root == null ){
            return ;
        }
        inorder(root.left);
        nums.add(root.val);
        inorder(root.right);
    }
}
```







## Question 122 买卖股票的最佳时机 II

给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

```java
输入: prices = [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
```

---

```java
class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        if (prices.length == 1 || prices.length == 0){
            return 0;
        }
        //收集所有上坡,上坡就有利可图
        for (int i = 1; i < prices.length; i++) {
            if(prices[i-1]<prices[i]){
                ans+=prices[i]-prices[i-1];
            }
        }
        return ans;
    }
}
```



## Question 189 旋转数组

给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

进阶：

- 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
- 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？

```java
输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
```

---

解题思路:

1. 翻转所有元素。
2. 翻转 \[0, k\bmod n - 1][0,kmodn−1] 区间的元素	
3. 翻转 \[k\bmod n, n - 1][kmodn,n−1] 区间的元素	

- 时间复杂度：*O(n)*，其中 n 为数组的长度。每个元素被翻转两次，一共 n 个元素，因此总时间复杂度为 $O(2n)=O(n)$。

- 空间复杂度：*O(1)*。

```java
class Solution {
    public void rotate(int[] nums, int k) {
        if (k == 0 || nums.length<=1) {
            return;
        }
        int len = nums.length;
        k = k % len;
        reverse(nums,0,len-1);
        reverse(nums,0,k-1);
        reverse(nums,k,len-1);
    }
    public void reverse(int[] nums, int left, int right) {
        int temp = 0;
        while(left < right){
            temp = nums[right];
            nums[right--] = nums[left];
            nums[left++] = temp;
        }
    }
}
```

## Question 206 反转链表

给你单链表的头节点 `head` ，请你反转链表，并返回反转后的链表。

![img](asset/CodeNote of LeetCode 51 to 100.assets/rev1ex1.jpg)

```java
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
```

---

解题思路：
	采用头插法进行反转。

进阶：可以使用递归实现。

```java
class Solution {
    public ListNode reverseList(ListNode head) {
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



## Question 445 [两数相加 II](https://leetcode-cn.com/problems/add-two-numbers-ii/)

​	给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。你可以假设除了数字 0 之外，这两个数字都不会以零开头。

<img src="asset/CodeNote of LeetCode 51 to 100.assets/1626420025-fZfzMX-image.png" alt="img" style="zoom:50%;" />

```latex
输入：l1 = [7,2,4,3], l2 = [5,6,4]
输出：[7,8,0,7]
```

---

解题思路:

​	使用栈进行链表反转。这题主要问题是要注意链表方向，如果不使用反转的方法的话，使用栈来保存逆序后的两个链表，然后弹栈进行计算，并进行头插来逆序插入。

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       ListNode ans = null;
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        int total = 0;
        while (!s1.empty() || !s2.empty() || carry != 0) {
            int a = s1.empty() ? 0 : s1.pop();
            int b = s2.empty() ? 0 : s2.pop();
            total = carry + a + b;
            if (total > 9) {
                carry = 1;
            } else {
                carry = 0;
            }
            ans = new ListNode(total % 10, ans);
        }
        return ans;
    }
}
```

## [641. 设计循环双端队列](https://leetcode-cn.com/problems/design-circular-deque/)

设计实现双端队列。
你的实现需要支持以下操作：

MyCircularDeque(k)：构造函数,双端队列的大小为k。
insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
isEmpty()：检查双端队列是否为空。
isFull()：检查双端队列是否满了。

```java
示例：

MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
circularDeque.insertLast(1);			        // 返回 true
circularDeque.insertLast(2);			        // 返回 true
circularDeque.insertFront(3);			        // 返回 true
circularDeque.insertFront(4);			        // 已经满了，返回 false
circularDeque.getRear();  				// 返回 2
circularDeque.isFull();				        // 返回 true
circularDeque.deleteLast();			        // 返回 true
circularDeque.insertFront(4);			        // 返回 true
circularDeque.getFront();				// 返回 4
```

---

解题思路:

- 循环双端队列的实现，可以使用数组加头尾指针实现。

  在逻辑上，循环双端队列是个圆形的链表，用数组可以实现快速访问，要注意头尾指针代表的含义，因为判空和判满操作要使用头尾指针的性质。

  头指针：指向第一个元素。

  尾指针：指向最后一个元素的下一个位置。（数组是N+1的，所以两个指针只有在链表空时才相遇。）

  判空：`tail == head`	判满：`(tail + 1) % size == head`

```java
class MyCircularDeque {

        int[] val;
        int head;
        int tail;
        int size;

        /**
         * Initialize your data structure here. Set the size of the deque to be k.
         */
        public MyCircularDeque(int k) {
            val = new int[k + 1];
            head = 0;
            tail = 0;
            size = k + 1;
        }
        /**
         * Adds an item at the front of Deque. Return true if the operation is successful.
         */
        public boolean insertFront(int value) {
            if ((tail + 1) % size == head) {
                return false;
            }
            if (tail == head) {
                val[head] = value;
                tail = (tail + 1) % size;
                return true;
            }
            head = (head + size-1) % size;
            val[head] = value;
            return true;
        }
        /**
         * Adds an item at the rear of Deque. Return true if the operation is successful.
         */
        public boolean insertLast(int value) {
            if ((tail + 1) % size == head) {
                return false;
            }
            val[tail] = value;
            tail = (tail + 1) % size;
            return true;
        }
        /**
         * Deletes an item from the front of Deque. Return true if the operation is successful.
         */
        public boolean deleteFront() {
            if (tail == head) {
                return false;
            }
            head = (head + size + 1) % size;
            return true;
        }
        /**
         * Deletes an item from the rear of Deque. Return true if the operation is successful.
         */
        public boolean deleteLast() {
            if (tail == head) {
                return false;
            }
            tail = (tail + size-1) % size;
            return true;
        }
        /**
         * Get the front item from the deque.
         */
        public int getFront() {
            if (tail == head) {
                return -1;
            }
            return val[head];
        }
        /**
         * Get the last item from the deque.
         */
        public int getRear() {
            if (tail == head) {
                return -1;
            }
            int index = (tail + size-1) % size;
            return val[index];
        }
        /**
         * Checks whether the circular deque is empty or not.
         */
        public boolean isEmpty() {
            if (tail == head) {
                return true;
            }
            return false;

        }
        /**
         * Checks whether the circular deque is full or not.
         */
        public boolean isFull() {
            if ((tail + 1) % size == head) {
                return true;
            }
            return false;
        }
    }
```

