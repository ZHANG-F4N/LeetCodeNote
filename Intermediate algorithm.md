# Intermediate algorithm

[TOC]

## [73. 矩阵置零](https://leetcode-cn.com/problems/set-matrix-zeroes/)

给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。

进阶：

一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个仅使用常量空间的解决方案吗？

```python
输入：matrix = [
    [1,1,1],
    [1,0,1],
    [1,1,1]
]
输出：[
    [1,0,1],
    [0,0,0],
    [1,0,1]
]
```

---

解决方法:

​	使用两个集合来记录出现过0的行和列的编号，然后进行遍历置零。


```java
class Solution {
    public void setZeroes(int[][] matrix) {
        //使用数组可以加快速度。
        HashSet<Integer> xHashSet = new LinkedHashSet<>();
        HashSet<Integer> yHashSet = new LinkedHashSet<>(); 
        int xlen = matrix.length;
        int ylen = matrix[0].length;
        for (int i = 0; i < xlen; i++) {
            for (int j = 0; j < ylen; j++) {
                if(matrix[i][j] == 0){
                    xHashSet.add(i);
                    yHashSet.add(j);
                }
            }
        }
        for (int i = 0; i < xlen; i++) {
            for (int j = 0; j < ylen; j++) {
                if(xHashSet.contains(i) || yHashSet.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
```

