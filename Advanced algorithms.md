# Advanced algorithms

[TOC]



## [238. 除自身以外数组的乘积](https://leetcode-cn.com/problems/product-of-array-except-self/)

给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

```
输入: [1,2,3,4]
输出: [24,12,8,6]
```

提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。

说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

进阶：
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

----

解题思路:

​	左右乘积列表，我们不必将所有数字的乘积除以给定索引处的数字得到相应的答案，而是利用索引左侧所有数字的乘积和右侧所有数字的乘积（即前缀与后缀）相乘得到答案。

​	对于给定索引 i，我们将使用它左边所有数字的乘积乘以右边所有数字的乘积。

```java
//L_multi [1, 1, 2, 6]
//R_multi [24, 12, 4, 1]
//ans     [24, 12, 8, 6]
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int L_multi[] = new int[nums.length];
        int R_multi[] = new int[nums.length];
        L_multi[0] = 1;
        R_multi[nums.length - 1] = 1;

        for (int i = 1; i < nums.length; i++) {
            L_multi[i] = L_multi[i - 1] * nums[i - 1];
            R_multi[nums.length - i - 1] = R_multi[nums.length - i] * nums[nums.length - i];
        }
        int ans[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = L_multi[i] * R_multi[i];
        }
        return ans;
    }
}
//空间复杂度O(1),将左右数组都保存到ans数组中
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int ans[] = new int[nums.length];
        ans[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        int temp = 1;
        for (int i = nums.length - 1; i >= 0; i--) {

            ans[i] = ans[i] * temp;
            temp *= nums[i];
        }
        return ans;
    }
}
```



