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

## [212. 单词搜索 II](https://leetcode-cn.com/problems/word-search-ii/)



给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

 示例 1：![img](asset/LeetBook-Algorithm Interview.assets/search1.jpg)

```java
输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
输出：["eat","oath"]
```

---

解题思路:

- 朴素 DFS

  对每一个单词进行一次搜索，然后进行保存即可。但是会超时。

- 字段树+DFS

  首先将所有的单词保存到字典树中，字典树节点直接保存`String`。

  然后深度优先搜索DFS矩阵，同时，每深度进入一次DFS，字典树同时也会向下进行一层，所以可以降低搜索的时间复杂度。



```java
//字段树+DFS
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        ArrayList<String> list = new ArrayList<>();
        TrieNode root = buildTrie(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                DFSbyTrie(board, i, j, root, list);
            }
        }
        return list;
    }
    
    public  void DFSbyTrie(char[][] board, int i, int j, TrieNode root, ArrayList<String> ans) {
        char ch = board[i][j];
        if (ch == '#' || root.child[ch - 'a'] == null) return;
        root = root.child[ch - 'a'];
        if (root.word != null) {
            ans.add(root.word);
            root.word = null;
        }
        board[i][j] = '#';
        if (i > 0) DFSbyTrie(board, i - 1, j, root, ans);
        if (j > 0) DFSbyTrie(board, i, j - 1, root, ans);
        if (i < board.length - 1) DFSbyTrie(board, i + 1, j, root, ans);
        if (j < board[0].length - 1) DFSbyTrie(board, i, j + 1, root, ans);

        board[i][j] = ch;
    }
     class TrieNode {
        TrieNode[] child = new TrieNode[26];
        String word;
    }
    public  TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            TrieNode temp = root;
            for (int j = 0; j < words[i].length(); j++) {
                char ch = words[i].charAt(j);
                int index = ch - 'a';
                if (temp.child[index] == null) {
                    temp.child[index] = new TrieNode();
                }
                temp = temp.child[index];
            }
            temp.word = words[i];
        }
        return root;
    }
}
```

```java
//朴素DFS c
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            if (search(board, words[i])) {
                list.add(words[i]);
            }
        }
        return list;
    }
    public boolean search(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (DFS(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean DFS(char[][] board, String word, int i, int j, int index) {
       boolean ans = false;
        if (board[i][j] == word.charAt(index)) {
            index++;
            if (index >= word.length()) {
                return true;
            }
            int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
            int m = board.length;
            int n = board[0].length;

            for (int k = 0; k < dir.length; k++) {
                int dx = i + dir[k][0];
                int dy = j + dir[k][1];
                if (dx < 0 || dy < 0 || dx >= m || dy >= n || board[dx][dy] == '#') {
                    continue;
                }
                char temp = board[i][j];
                board[i][j] = '#';
                ans = ans || DFS(board, word, dx, dy, index);
                board[i][j] = temp;
            }
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

## [279. 完全平方数](https://leetcode-cn.com/problems/perfect-squares/)

给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

```java
输入：n = 12
输出：3 
解释：12 = 4 + 4 + 4
```

---

解题思路：

- 动态规划

  ​	表示整数 *i*，假设当前枚举到 *j*，那么我们还需要取若干数的平方，构成 $ i-j^2$。此时我们发现该子问题和原问题类似，只是规模变小了。这符合了动态规划的要求，状态转移方程为：
  $$
  dp[i] = min(dp[i-j^2]+1,dp[i]);
  $$
  时间复杂度：$O(n\sqrt{n})$

- 数学方法

  四平方和定理证明了任意一个正整数都可以被表示为至多四个正整数的平方和。这给出了本题的答案的上界。

  同时四平方和定理包含了一个更强的结论：当且仅当 $n \neq 4^k \times (8m+7)$时，n 可以被表示为至多三个正整数的平方和。因此，当$ n = 4^k \times (8m+7)$ 时，n 只能被表示为四个正整数的平方和。此时我们可以直接返回 4。

  当$ n \neq 4^k \times (8m+7)$时，我们需要判断到底多少个完全平方数能够表示 nn，我们知道答案只会是 1,2,3 中的一个：

  - 答案为 1 时，则必有 n 为完全平方数，这很好判断；

  - 答案为 2 时，则有 $n=a^2+b^2$，我们只需要枚举所有的$ a(1 \leq a \leq \sqrt{n})$，判断$ n-a^2$否为完全平方数即可；

  - 答案为 3 时，我们很难在一个优秀的时间复杂度内解决它，但我们只需要检查答案为 1 或 2 的两种情况，即可利用排除法确定答案。

```java
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        //Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i - j * j] + 1, dp[i]);
            }
        }
        return dp[n];
    }
}
```

```java
class Solution {
    public int numSquares(int n) {
        if (isPerfectSquare(n)) {
            return 1;
        }
        if (checkAnswer4(n)) {
            return 4;
        }
        for (int i = 1; i * i <= n; i++) {
            int j = n - i * i;
            if (isPerfectSquare(j)) {
                return 2;
            }
        }
        return 3;
    }
    // 判断是否为完全平方数
    public boolean isPerfectSquare(int x) {
        int y = (int) Math.sqrt(x);
        return y * y == x;
    }
    // 判断是否能表示为 4^k*(8m+7)
    public boolean checkAnswer4(int x) {
        while (x % 4 == 0) {
            x /= 4;
        }
        return x % 8 == 7;
    }
}
```



## [378. 有序矩阵中第 K 小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/)

给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。

```java
输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
输出：13
解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
```

---

解题思路：

- 转换为一维数组然后排序。

- 归并排序。

- 利用矩阵性质，进行二分。

  我们知道整个二维数组中 matrix\[0][0] 为最小值，matrix\[n - 1][n - 1]为最大值，现在我们将其分别记作 l 和 r。

  可以发现一个性质：任取一个数 mid 满足 $l\leq mid \leq r$，那么矩阵中不大于 mid 的数，肯定全部分布在矩阵的左上角。

  ---

  每次对于「猜测」的答案 mid，计算矩阵中有多少数不大于 mid ：

  - 如果数量不少于 k，那么说明最终答案 x 不大于 mid；

  - 如果数量少于 k，那么说明最终答案 x 大于 mid。

  这样我们就可以计算出最终的结果 x 了。

  ---
  
  当 left == right 时，可以保证 mid (此时 mid = left = right) 在矩阵中。

```java
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = matrix[m - 1][n - 1];
        int min = matrix[0][0];
        while (max > min) {
            // max+min 会导致 溢出
            //int mid = (max + min) / 2;
             int mid = min + ((max - min) >> 1);
            if (search(matrix, mid) >= k) {
                max = mid;
                continue;
            }
            if (search(matrix, mid) < k) {
                min = mid + 1;
                continue;
            }
        }
        return min;
    }
//计算矩阵中小于等于tar元素的个数
    public static int search(int[][] matrix, int tar) {
        int num = 0;
        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        int i = 0;
        int j = col;
        while (i <= row && j >= 0) {
            if (matrix[i][j] == tar) {
                num += j + 1;
                i++;
                //j--;
                continue;
            }
            if (matrix[i][j] < tar) {
                i++;
                num += j + 1;
                continue;
            }
            if (matrix[i][j] > tar) {
                j--;
                continue;
            }
        }
        return num;
    }
}
```



