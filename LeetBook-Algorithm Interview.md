# LeetBook-Algorithm Interview

[TOC]





## [152. 乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/)

给你一个整数数组 `nums` ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

```
输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
```

---

解题思路：

- 动态规划。

     由于乘积存在正负，当我们访问到一个元素`nums[i]`时，有以下两种情况：

     - `nums[i]`为正，那么和`nums[i]`之前的最大正积，相乘即为最大值。
     - `nums[i]`为负，那么和`nums[i]`之前的最小负积(绝对值最大)，相乘为正最大值。

     因此维护两个变量，`nums[i]`之前的正最大积和最小负积。

```java
class Solution {
    public int maxProduct(int[] nums) {
        int maxTemp = nums[0];
        int minTemp = nums[0];
        // ans的默认值应为第一个值，相当于已经处理过了。
        // 因为存在负值，所以不能默认为0。
        int ans = nums[0];
        //保存上个一个状态的最大值与最小值
        int minT = minTemp;
        int maxT = maxTemp;
        for (int i = 1; i < nums.length; i++) {
            minT = minTemp;
            maxT = maxTemp;
            maxTemp = Math.max(Math.max(nums[i], nums[i] * minT), maxT * nums[i]);
            minTemp = Math.min(Math.min(nums[i], nums[i] * maxT), minT * nums[i]);
            ans = Math.max(maxTemp, ans);
        }
        return ans;
    }
}
```

## [240. 搜索二维矩阵 II](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/)

编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

- 每行的元素从左到右升序排列。
- 每列的元素从上到下升序排列。

![img](asset/LeetBook-Algorithm%20Interview.assets/searchgrid2-16287585010821.jpg)

```java
输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
输出：true
```

---

解题思路：

- 方法一：对角线元素递增，二分查找元素属于的子矩阵。

- 方法二：行列都是递增的，所以可以按行递增，列递减来同时缩小搜索范围。

  ​		每一行最大元素在最后，每一列最小元素也在最后，所以从右上角可以开始搜索，减小行和列，来缩小搜索范围。

  ​		比右上角元素大，行++，比右上角元素小，列--；

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int col = matrix[0].length;
        int cTemp = 0;
        int rTemp = matrix.length - 1;
        while (cTemp < col && rTemp >= 0) {
            if (matrix[rTemp][cTemp] == target) {
                return true;
            }
            if (matrix[rTemp][cTemp] > target) {
                rTemp--;
                continue;
            }
            if (matrix[rTemp][cTemp] < target) {
                cTemp++;
                continue;
            }
        }
        return false;
    }
}
```

