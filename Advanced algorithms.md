# Advanced algorithms

[TOC]



## [54. 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)

给你一个 `m` 行 `n` 列的矩阵 `matrix` ，请按照 **顺时针螺旋顺序** ，返回矩阵中的所有元素。

![img](asset/Advanced algorithms.assets/spiral.jpg)

```
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
```

---

解题思路:

​	模拟，设置四个边界变量，模拟过程中进行改变。

​	有个大佬的两行python代码，膜拜一下。

```python
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        res = []
        while matrix:
            res += matrix.pop(0)
            matrix = list(zip(*matrix))[::-1]
        return res
```

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int i = 0, j = 0;
        ans.add(matrix[0][0]);
        while (up <= down && left <= right) {
            while (j < right && up <= down) {
                j++;
                ans.add(matrix[i][j]);
            }
            up++;
            while (i < down && left <= right) {
                i++;
                ans.add(matrix[i][j]);
            }
            right--;
            while (j > left && up <= down) {
                j--;
                ans.add(matrix[i][j]);
            }
            down--;
            while (i > up && left <= right) {
                i--;
                ans.add(matrix[i][j]);
            }
            left++;
        }
        return ans;
    }
}
```

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



## [454. 四数相加 II](https://leetcode-cn.com/problems/4sum-ii/)

给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

```tex
输入:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

输出:
2

解释:
两个元组如下:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
```

---

解题思路:

​	分组。采用分为两组，HashMap 存一组，另一组和 HashMap 进行比对。

这样的话情况就可以分为三种：
	HashMap 存一个数组，如 A。然后计算三个数组之和，如 BCD。时间复杂度为：O(n)+O(n^3)，得到 O(n^3).
	HashMap 存三个数组之和，如 ABC。然后计算一个数组，如 D。时间复杂度为：O(n^3)+O(n)，得到 O(n^3).
	HashMap存两个数组之和，如AB。然后计算两个数组之和，如 CD。时间复杂度为：O(n^2)+O(n^2)，得到 O(n^2).
根据第二点我们可以得出要存两个数组算两个数组。
	我们以存 AB 两数组之和为例。首先求出 A 和 B 任意两数之和 sumAB，以 sumAB 为 key，sumAB 出现的次数为 value，存入 hashmap 中。然后计算 C 和 D 中任意两数之和的相反数 sumCD，在 hashmap 中查找是否存在 key 为 sumCD。
	算法时间复杂度为 $O(n^2)。$

```java
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
       HashMap<Integer, Integer> ABHashMap = new HashMap<>();
        int ans = 0;

        for (int a : nums1) {
            for (int b : nums2) {
                ABHashMap.put(a + b, ABHashMap.getOrDefault(a + b, 0) + 1);
            }
        }
        for (int c : nums3) {
            for (int d : nums4) {
                if (ABHashMap.containsKey(0 - c - d)) {
                    ans += ABHashMap.get(0 - c - d);
                }
            }
        }
        return ans;
    }
}
```



