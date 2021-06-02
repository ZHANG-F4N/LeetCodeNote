# Intermediate algorithm

[TOC]





## [49. 字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/)

给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

```python
输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```

---

解题思路:

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            ArrayList<String> strings;
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            //在Java中，每一个对象的toString方法，
            // 都会打印出“类名@他的hashCode值”这样的一个字符串， getClass().getName() + '@' + Integer.toHexString(hashCode())
            // 数组是“[”这个符号C就代表char，后面的就是这个数组的hashCode值，如果是int类型的数组的话，
            // 就会使[I@*****了
            //strings = hashMap.getOrDefault(temp.toString()), new ArrayList<>());
            // 这种写法错误,key 就变成了[C@10f87f48   [C@b4c966a,
            strings = hashMap.getOrDefault(new String(temp), new ArrayList<>());
            strings.add(strs[i]);
            hashMap.put(new String(temp), strings);
        }
        return new ArrayList<>(hashMap.values());
    }
}
```



## [73. 矩阵置零](https://leetcode-cn.com/problems/set-matrix-zeroes/)

给定一个 $m * n$ 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。

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

