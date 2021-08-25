# LeetBook-Algorithm Interview

[TOC]



## [139. 单词拆分](https://leetcode-cn.com/problems/word-break/)

给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：

拆分时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。

```java
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     注意你可以重复使用字典中的单词。
```

---

解题思路：

- 动态规划

  令dp[i]表示s[0~i]是否能够被划分为wordDict中的字符串的组合，则对于d[i]的取值有如下分析：

  * 1.s[0~i]本身就是wordDict中的字符串，那么dp[i] = true

  * 2.s[0~i]不是wordDict中的字符串，则需要向前遍历dp，看是否存在dp[j] = true同时s[j+1~i]为wordDict中的字符串

    最后需要返回dp[s.length()]

```java
// 依次进行字符串分割，分割成s[0~i] n个子串
// 对于每个子串，从位置j 将子串分成两个更小的子串s[0-j] s[j -i]，
// 判断两个子串是否包含子给定的字符串集合中
// 前一个子串只需要用dp[j]来判断
// 令dp[i]表示s[0~i]是否能够被划分为wordDict中的字符串的组合
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> hashSet = new HashSet<>(wordDict);
        boolean []dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[j] && hashSet.contains(s.substring(j,i))){
                    dp[i]  = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
//优化后 增加了子串长度的筛选。
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> hashSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        int MaxLen = 0;
        int MinLen = Integer.MAX_VALUE;//0x 7fff ffff
        Iterator<String> it = wordDict.iterator();
        while (it.hasNext()) {
            int length = it.next().length();
            if (length > MaxLen) {
                MaxLen = length;
            }
            if (length < MinLen) {
                MinLen = length;
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (i - j  < MinLen) {
                    continue;
                }
                if (i - j > MaxLen) {
                    continue;
                }
                if (dp[j] && hashSet.contains(s.substring(j, i))) {//"smiles".substring(1, 5) returns "mile"
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
```

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



## [208. 实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)

**[
Trie](https://baike.baidu.com/item/字典树/9825209?fr=aladdin)**（发音类似 "try"）或者说 **前缀树** 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

- `Trie()` 初始化前缀树对象。
- `void insert(String word)` 向前缀树中插入字符串 `word` 。
- `boolean search(String word)` 如果字符串 `word` 在前缀树中，返回 `true`（即，在检索之前已经插入）；否则，返回 `false` 。
- `boolean startsWith(String prefix)` 如果之前已经插入的字符串 `word` 的前缀之一为 `prefix` ，返回 `true` ；否则，返回 `false` 。

```java
输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True
```

---

解题思路：

​	Trie 是一颗非典型的多叉树模型，多叉好理解，即每个结点的分支数量可能为多个。

为什么说非典型呢？因为它和一般的多叉树不一样，尤其在结点的数据结构设计上，比如一般的多叉树的结点是这样的：

```c++
struct TreeNode {
    VALUETYPE value;    //结点值
    TreeNode* children[NUM];    //指向孩子结点
};
```

而 Trie 的结点是这样的(假设只包含'a'~'z'中的字符)：

```java
class Trie {
private Trie[] child;//26个字母
private boolean isEnd;
}
```

这时字母映射表next 的妙用就体现了，Trie.child[26]中保存了对当前结点而言下一个可能出现的所有字符的链接，因此我们可以通过一个父结点来预知它所有子结点的值:

```java
 public boolean search(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.child[index] == null) {
                return false;
            }
            node = node.child[index];
        }
        if (node.isEnd == true) {
            return true;
        }
        return false;
    }
```

```java
class Trie {
    private Trie[] child;
    private boolean isEnd;
    /** Initialize your data structure here. */
    public Trie() {
        child = new Trie[26];
        isEnd = false;//初始化没有单词
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.child[index] == null) {
                node.child[index] = new Trie();
            }
            node = node.child[index];
        }
        //单词加完才标记
        node.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.child[index] == null) {
                return false;
            }
            node = node.child[index];
        }
        if (node.isEnd == true) {
            return true;
        }
        return false;
    }
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.child[index] == null) {
                return false;
            }
            node = node.child[index];
        }
        return true;
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

