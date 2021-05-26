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

 ![img](CodeNote of LeetCode 51 to 100.assets/rotate1.jpg)

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

  <img src="CodeNote of LeetCode 51 to 100.assets/image-20210525204846175.png" alt="image-20210525204846175" style="zoom:50%;" />

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

![img](CodeNote of LeetCode 51 to 100.assets/robot1.jpg)

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

<img src="CodeNote of LeetCode 51 to 100.assets/image-20210525205205735.png" alt="image-20210525205205735" style="zoom:67%;" />

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

![img](CodeNote of LeetCode 51 to 100.assets/minpath.jpg)

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

<img src="CodeNote of LeetCode 51 to 100.assets/linkedlist1.jpg" alt="img" style="zoom:67%;" />

```java
输入：head = [1,2,3,3,4,4,5]
输出：[1,2,5]
```

---





## Question 83 删除排序链表中的重复元素

存在一个按升序排列的链表，给你这个链表的头节点 `head` ，请你删除所有重复的元素，使每个元素 **只出现一次** 。

返回同样按升序排列的结果链表。

![img](CodeNote of LeetCode 51 to 100.assets/list2.jpg)

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

![img](CodeNote of LeetCode 51 to 100.assets/partition.jpg)

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



## Question 94 二叉树的中序遍历

给定一个二叉树的根节点 `root` ，返回它的 **中序** 遍历。

<img src="CodeNote of LeetCode 51 to 100.assets/inorder_1.jpg" alt="img" style="zoom:50%;" />

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



## Question 206 反转链表

给你单链表的头节点 `head` ，请你反转链表，并返回反转后的链表。

![img](CodeNote of LeetCode 51 to 100.assets/rev1ex1.jpg)

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

