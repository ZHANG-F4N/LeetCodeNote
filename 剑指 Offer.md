# 剑指 Offer

[TOC]

## [剑指 Offer II 4. 只出现一次的数字 ](https://leetcode-cn.com/problems/WGki4K/)

给你一个整数数组 `nums` ，除某个元素仅出现 **一次** 外，其余每个元素都恰出现 **三次 。**请你找出并返回那个只出现了一次的元素。

```
输入：nums = [0,1,0,1,0,1,100]
输出：100
```

---

解题思路：

- 状态机 不会。

- 位运算

  <img src="asset/剑指 Offer.assets/1629341475-GhErJW-image.png" alt="image.png" style="zoom:50%;" />

```java
class Solution {
    public int singleNumber(int[] nums) {
       int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int j = 0; j < nums.length; j++) {
                //计算每一位和
                cnt += (nums[j] >> i) & 1;
            }
            if (cnt % 3 != 0) {
                //取余后只有 0 1 两种情况
                //边保存边左移。
                ans = ans | 1 << i;
            }
        }
        return ans;
    }
}
```

## [剑指 Offer II 14. 剪绳子 II](https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/)

给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

```
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
2 <= n <= 1000
```

---

解题思路:

- 基本不等式+快速幂

  大数求余解法：
  大数越界： 当 a 增大时，最后返回的 $3^a$大小以指数级别增长，可能超出 int32 甚至 int64 的取值范围，导致返回值错误。
  大数求余问题： 在仅使用 int32 类型存储的前提下，正确计算 $x^a$ 对 p 求余（即 $x^a \odot p$）的值。解决方案： 循环求余 、 快速幂求余 ，其中后者的时间复杂度更低，两种方法均基于以下求余运算规则推出：$(xy) \odot p = [(x \odot p)(y \odot p)] \odot p
  (xy)⊙p=[(x⊙p)(y⊙p)]⊙p$

  - 循环求余 可通过循环操作依次求 $x^1, x^2, ..., x^{a-1}, x^a$  对 p 的余数，保证每轮中间值 rem 都在 int32 取值范围中。

  - 快速幂求余

    ```java
     // 求 (x^a) % p —— 快速幂求余
        public long remainder(long num, int quotient, int MOD) {
            long rem = 1;
            while (quotient > 0) {
                if ((quotient & 1) == 1) {
                    rem *=  num;
                    rem %= MOD;
                }
                num *= num;
                num %= MOD;
                quotient >>= 1;
            }
            return rem;
        }
    ```

```java
class Solution {
    public int cuttingRope(int n) {
        final int MOD = 1000000007;
        int ans = 0;
        if (n <= 3) {
            return n-1;
        }
        int residue = n % 3;
        int quotient = n / 3;
        if (residue == 0) {
            ans = (int)remainder(3, quotient, MOD) % MOD;
        } else if (residue == 1) {
            ans = (int)((remainder(3, quotient - 1, MOD) * 4) % MOD);
        } else {
            ans = (int)(remainder(3, quotient, MOD) * 2) % MOD;
        }
        return ans;
    }
    // 求 (x^a) % p —— 快速幂求余
    public long remainder(long num, int quotient, int MOD) {
        long rem = 1;
        while (quotient > 0) {
            if ((quotient & 1) == 1) {
                rem *=  num;
                rem %= MOD;
            }
            num *= num;
            num %= MOD;
            quotient >>= 1;
        }
        return rem;
    }
}
```

## [剑指 Offer II 15. 字符串中的所有变位词](https://leetcode-cn.com/problems/VabMRr/)

给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

变位词 指字母相同，但排列不同的字符串。

```java
输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的变位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的变位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的变位词。
```

---

解题思路:

- 滑动窗口来统计两个字符串中字符出现的次数，进行对比，当`都为 0 且长度相等`时就是变位词。

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int N = s.length();
        int M = p.length();
        ArrayList<Integer> list = new ArrayList<>();
        if (M > N) {
            return list;
        }
        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        for (char ch : p.toCharArray()) {
            pCnt[ch - 'a']++;
        }
        int left = 0;
        for (int i = 0; i < N; i++) {
            int x = s.charAt(i) - 'a';
            sCnt[x]++;
            while (sCnt[x] > pCnt[x]) {
                int l = s.charAt(left) - 'a';
                sCnt[l]--;
                left++;
            }
            if(i - left + 1 == M){
                list.add(left);
            }
        }
        return list;
    }
}
```





## [剑指 Offer II 30. 插入、删除和随机访问都是 O(1) 的容器](https://leetcode-cn.com/problems/FortPu/)

设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构：

insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。

```java
示例 :
输入: inputs = ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
输出: [null, true, false, true, 2, true, false, 2]
解释:
RandomizedSet randomSet = new RandomizedSet();  // 初始化一个空的集合
randomSet.insert(1); // 向集合中插入 1 ， 返回 true 表示 1 被成功地插入
randomSet.remove(2); // 返回 false，表示集合中不存在 2 
randomSet.insert(2); // 向集合中插入 2 返回 true ，集合现在包含 [1,2] 
randomSet.getRandom(); // getRandom 应随机返回 1 或 2 
randomSet.remove(1); // 从集合中移除 1 返回 true 。集合现在包含 [2] 
randomSet.insert(2); // 2 已在集合中，所以返回 false 
randomSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2
```

---

解题思路:

- 本来数组是最合适的，随机按值访问采用hashMap保存<值,小标>下标，但是有删除操作，所以使用变长数组`ArrayList`。删除时用最后一个值替换，然后尾删。

```java
class RandomizedSet {
    ArrayList<Integer> list;
    HashMap<Integer, Integer> hashMap;
    public RandomizedSet() {
        this.hashMap = new HashMap<>();
        this.list = new ArrayList<>();
    }
    public boolean insert(int val) {
        if (hashMap.containsKey(val)) {
            return false;
        }
        int idx = list.size();
        list.add(idx, val);
        hashMap.put(val, idx);
        return true;
    }
    public boolean remove(int val) {
        if (hashMap.containsKey(val)) {
            int idx = hashMap.get(val);
            list.set(idx, list.get(list.size() - 1));
            hashMap.replace(list.get(list.size()-1),idx);
            list.remove(list.size() - 1);
            hashMap.remove(val);
            return true;
        } else {
            return false;
        }
    }
    public int getRandom() {
        Random rand = new Random();
        int random = rand.nextInt(hashMap.size());
        return list.get(random);
    }
}
```



## [剑指 Offer II 37. 小行星碰撞](https://leetcode-cn.com/problems/XagZNi/)

给定一个整数数组 asteroids，表示在同一行的小行星。

对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。

找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。

```java
输入：asteroids = [-2,-1,1,2]
输出：[-2,-1,1,2]
解释：-2 和 -1 向左移动，而 1 和 2 向右移动。 由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。
```

```
输入：asteroids = [5,10,-5]
输出：[5,10]
解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
```

---

解题思路:

- 栈。由于碰撞是单向的，所以使用栈可以很好的模拟。

```java
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (stack.empty()) {
                stack.push(asteroids[i]);
                continue;
            }
            int top = stack.peek();
            if ((top ^ asteroids[i]) >= 0 || (top < 0 && asteroids[i] > 0)) {
                stack.push(asteroids[i]);
                continue;
            }
            boolean collision = false;
            while (!stack.empty() && (top > 0 && asteroids[i] < 0)) {
                if (Math.abs(top) > Math.abs(asteroids[i])) {
                    collision = true;
                    break;
                } else if (Math.abs(top) < Math.abs(asteroids[i])) {
                    stack.pop();
                    collision = false;
                    if (stack.empty()) {
                        break;
                    }
                    top = stack.peek();
                    continue;
                } else {
                    collision = true;
                    stack.pop();
                    break;
                }
            }
            if (!collision) {
                stack.push(asteroids[i]);
            }
        }
        Iterator<Integer> it = stack.iterator();
        int[] ans = new int[stack.size()];
        int i = 0;
        while (it.hasNext()) {
            ans[i++] = it.next();
        }
        return ans;
    }
}
```



## [剑指 Offer II 39. 直方图最大矩形面积](https://leetcode-cn.com/problems/0ynMMM/)

给定非负整数数组 heights ，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

![img](asset/%E5%89%91%E6%8C%87%20Offer.assets/histogram.jpg)

```
输入：heights = [2,1,5,6,2,3]
输出：10
解释：最大的矩形为图中红色区域，面积为 10
```

---

解题思路:

- 单调栈。

  首先，要想找到第 i 位置最大面积是什么？

  是以i 为中心，向左找第一个小于 heights[i] 的位置 left_i；向右找第一个小于于 heights[i] 的位置 right_i，即最大面积为 heights[i] * (right_i - left_i -1)，如下图所示：


  所以，我们的问题就变成如何找 right_i 和 left_i ? 

  最简单的思路就是，就是暴力法，直接分别在 i 左右移动。


![1559826097853.png](asset/%E5%89%91%E6%8C%87%20Offer.assets/441ac778821dc26689b31466bced9f61ec241f092bf7e4f0f8699ef4fa3be1b2-1559826097853.png)

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] new_heights = new int[heights.length + 2];
        for (int i = 1; i < heights.length + 1; i++) new_heights[i] = heights[i - 1];
        for (int i = 0; i < new_heights.length; i++) {
            while (!stack.isEmpty() && new_heights[stack.peek()] > new_heights[i]) {
                int cur = stack.pop();
                ans = Math.max(ans, (i - stack.peek()-1) * new_heights[cur]);
            }
            stack.push(i);
        }
        return ans;
    }
}
```

## [剑指 Offer II 40. 矩阵中最大的矩形](https://leetcode-cn.com/problems/PLYXKQ/)

给定一个由 `0` 和 `1` 组成的矩阵 `matrix` ，找出只包含 `1` 的最大矩形，并返回其面积。

**注意：**此题 `matrix` 输入格式为一维 `01` 字符串数组。

```
输入：matrix = ["10100","10111","11111","10010"]
输出：6
解释：最大矩形如上图所示。
```

<img src="asset/%E5%89%91%E6%8C%87%20Offer.assets/maximal.jpg" alt="img" style="zoom:50%;" />

---

解题思路：

- 单调栈。

```java
class Solution {
    public int maximalRectangle(String[] matrix) {
        int N = matrix.length;
        if (N == 0) {
            return 0;
        }
        int M = matrix[0].length();
        int[][] preSum = new int[N][M + 2];
        for (int j = 1; j <= M; j++) {
            for (int i = 0; i < N; i++) {
                if (i == 0) {
                    preSum[i][j] = matrix[i].charAt(j - 1) - '0';
                } else if (matrix[i].charAt(j - 1) != '0') {
                    preSum[i][j] = preSum[i - 1][j] + matrix[i].charAt(j - 1) - '0';
                }
            }
        }
        //单调栈
        Deque<Integer> deque = new ArrayDeque<>();
        int ans = 0;
        for (int j = 0; j < N; j++) {
            int[] temp = preSum[j];
            for (int i = 0; i < M + 2; i++) {
                while (!deque.isEmpty() && temp[deque.peek()] > temp[i]) {
                    int cur = deque.pop();
                    int area = (i - deque.peek() - 1) * (temp[cur]);
                    ans = area > ans ? area : ans;
                }
                deque.push(i);
            }
        }
        return ans;
    }
}
```



## [剑指 Offer II 47. 二叉树剪枝](https://leetcode-cn.com/problems/pOCWxh/)

给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。

节点 node 的子树为 node 本身，以及所有 node 的后代。

```java
输入: [1,null,0,0,1]
输出: [1,null,0,null,1] 
解释: 
只有红色节点满足条件“所有不包含 1 的子树”。
图为返回的答案。
```

<img src="asset/%E5%89%91%E6%8C%87%20Offer.assets/1028_2.png" alt="img" style="zoom: 50%;" />

---

解题思路:

- 递归删除。

```java
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }
}
```

## [剑指 Offer II 48. 序列化与反序列化二叉树](https://leetcode-cn.com/problems/h54YBf/)

序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

<img src="asset/%E5%89%91%E6%8C%87%20Offer.assets/serdeser.jpg" alt="img" style="zoom:50%;" />

```
输入：root = [1,2,3,null,null,4,5]
输出：[1,2,3,null,null,4,5]
```

---

解题思路:

- 层次遍历来保存和反序列化。

```java
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
        StringBuilder ans = new StringBuilder();
        deque.offer(root);
        ans.append(root.val);
        ans.append(',');
        while (!deque.isEmpty()) {
            TreeNode temp = deque.poll();
            if (temp.left == null) {
                ans.append("null");
            } else {
                ans.append(temp.left.val);
                deque.offer(temp.left);
            }
            ans.append(',');
            if (temp.right == null) {
                ans.append("null");
            } else {
                ans.append(temp.right.val);
                deque.offer(temp.right);
            }
            ans.append(',');
        }
        return ans.toString();
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") {
            return null;
        }
        String[] leaf = data.split(",");
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.parseInt(leaf[0]));
        deque.offer(root);
        int index = 1;
        while (!deque.isEmpty()) {
            TreeNode temp = deque.poll();
            if (leaf[index].equals("null") ) {
                temp.left = null;
            } else {
                temp.left = new TreeNode(Integer.parseInt(leaf[index]));
                deque.offer(temp.left);
            }
            index++;
            if (leaf[index].equals("null")) {
                temp.right = null;
            } else {
                temp.right = new TreeNode(Integer.parseInt(leaf[index]));
                deque.offer(temp.right);
            }
            index++;
        }
        return root;
    }
}
```

## [剑指 Offer II 49. 从根节点到叶节点的路径数字之和](https://leetcode-cn.com/problems/3Etpl5/)

给定一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。

每条从根节点到叶节点的路径都代表一个数字：

例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
计算从根节点到叶节点生成的 所有数字之和 。

叶节点 是指没有子节点的节点。

```java
输入：root = [4,9,0,5,1]
输出：1026
解释：
从根到叶子节点路径 4->9->5 代表数字 495
从根到叶子节点路径 4->9->1 代表数字 491
从根到叶子节点路径 4->0 代表数字 40
因此，数字总和 = 495 + 491 + 40 = 1026
```

<img src="asset/%E5%89%91%E6%8C%87%20Offer.assets/num2tree.jpg" alt="img" style="zoom:50%;" />

---

解题思路:

- DFS，到叶节点相加即可。

```java
class Solution {
    private static int ans;
    public int sumNumbers(TreeNode root) {
        ans = 0;
        DFS(root, 0);
        return ans;
    }
    public  void DFS(TreeNode root, int num) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            ans += num * 10 + root.val;
            return;
        }
        DFS(root.left, num*10+root.val);
        DFS(root.right, num*10+root.val);
    }
}
```

## [剑指 Offer II 50. 向下的路径节点之和](https://leetcode-cn.com/problems/6eUYwP/)

给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

```java
输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
输出：3
解释：和等于 8 的路径有 3 条，如图所示。
```

<img src="asset/%E5%89%91%E6%8C%87%20Offer.assets/pathsum3-1-tree.jpg" alt="img" style="zoom:50%;" />

---

解题思路:

- 前缀和。

  ​	统计以每个节点为「路径结尾」的合法数量的话，配合原本就是「从上往下」进行的数的遍历（最完整的路径必然是从原始根节点到当前节点的唯一路径），相当于只需要在完整路径中找到有多少个节点到当前节点的路径总和为 targetSum。

  ​	于是这个树上问题彻底转换一维问题：求解从原始起点（根节点）到当前节点 b 的路径中，有多少节点 a 满足 sum[a...b]=targetSum，由于从原始起点（根节点）到当前节点的路径唯一，因此这其实是一个「一维前缀和」问题。

  ​	我们可以在进行树的遍历时，记录下从原始根节点 rootroot 到当前节点 curcur 路径中，从 rootroot 到任意中间节点 xx 的路径总和，配合哈希表，快速找到满足以 curcur 为「路径结尾」的、使得路径总和为 targetSumtargetSum 的目标「路径起点」有多少个。


```java
class Solution {
    static Map<Integer, Integer> hashMap;
    static int ans, tar;
    public int pathSum(TreeNode root, int targetSum) {
        hashMap = new HashMap<>();
        ans = 0;
        if (root == null) {
            return 0;
        }
        tar = targetSum;
        hashMap.put(0, 1);
        DFS(root, root.val);
        return ans;
    }
    //DFS 从 root 节点出发的和为Sum的路径个数
    public  void DFS(TreeNode root, int sum) {
        if (hashMap.containsKey(sum - tar)) {
            ans += hashMap.get(sum - tar);
        }
        hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);

        if (root.left != null) DFS(root.left, sum + root.left.val);
        if (root.right != null) DFS(root.right, sum + root.right.val);

        hashMap.put(sum, hashMap.getOrDefault(sum, 0) - 1);
    }
}
```

## [剑指 Offer II 53. 二叉搜索树中的中序后继](https://leetcode-cn.com/problems/P5rCT8/)

给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。

节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。

```java
输入：root = [5,3,6,2,4,null,null,1], p = 6
输出：null
解释：因为给出的节点没有中序后继，所以答案就返回 null 了。
```

![img](asset/%E5%89%91%E6%8C%87%20Offer.assets/285_example_2.PNG)

---

解题思路:

- 标记法。 二叉搜索树的中序后继的特点是：中序遍历时，当找到节点P后，下一个==第一次==访问的节点就是中序后继节点。

```java
class Solution {
    static TreeNode ans;
    static boolean isP;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        isP = false;
        ans = null;
        //TreeNode pre = null;
        inOrder(root, p);
        return ans;   
    }
    public void inOrder(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }
        inOrder(root.left, p);
        if (isP) {
            ans = root;
            isP = false;
        }
        if (p == root) {
            isP = true;
        }
        inOrder(root.right, p);
    }
}
```

## [剑指 Offer II 54. 所有大于等于节点的值之和](https://leetcode-cn.com/problems/w6cpku/)

给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。

提醒一下，二叉搜索树满足下列约束条件：

节点的左子树仅包含键 小于 节点键的节点。
节点的右子树仅包含键 大于 节点键的节点。
左右子树也必须是二叉搜索树。

输入：root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

<img src="asset/%E5%89%91%E6%8C%87%20Offer.assets/tree.png" alt="img" style="zoom:50%;" />

---

解题思路:

- 右-中-左遍历。

```java
class Solution {
    static int ans;
    public TreeNode convertBST(TreeNode root) {
        ans = 0;
        reverseOrder(root);
        return root;
    }
    public static void reverseOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        reverseOrder(root.right);
        ans += root.val;
        root.val = ans;

        reverseOrder(root.left);
    }
}
```

## [剑指 Offer II 55. 平衡二叉树](https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/)

输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。

 

示例 1:

给定二叉树 [3,9,20,null,null,15,7]

    	3
       / \
      9  20
        /  \
       15   7
    返回 true 。
---

解题思路:

- 自底而上递归。

  自底向上递归的做法类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡。如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回 -1。如果存在一棵子树不平衡，则整个二叉树一定不平衡。


```java
class Solution {
    static boolean ans = true;
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        ans = true;
        DFS(root);
        return ans;
    }
    public int DFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = DFS(root.left);
        int right = DFS(root.right);
        if (ans && Math.abs(left - right) <= 1) {
            return Math.max(left, right) + 1;
        } else {
            ans = false;
            return -1;
        }
    }
}
```

## [剑指 Offer II 059. 数据流的第 K 大数值](https://leetcode-cn.com/problems/jBjn9C/)

设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。

请实现 KthLargest 类：

KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。

```java
输入：
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
输出：
[null, 4, 5, 5, 8, 8]

解释：
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3);   // return 4
kthLargest.add(5);   // return 5
kthLargest.add(10);  // return 5
kthLargest.add(9);   // return 8
kthLargest.add(4);   // return 8
```

---

解题思路：

- 大根堆。
- 快排。

```java
class KthLargest {
    PriorityQueue<Integer> queue;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<>((o1, o2) -> o1 - o2);
        int min = k < nums.length ? k : nums.length;
        for (int i = 0; i < min; i++) {
            queue.offer(nums[i]);
        }

        for (int i = min; i < nums.length; i++) {
            if (nums[i] > queue.peek()) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }
    }

    public int add(int val) {
        if (queue.size() < k) {
            queue.offer(val);
        } else if (val > queue.peek()) {
            queue.poll();
            queue.offer(val);
        }
        return queue.peek();
    }
}
```

## [剑指 Offer II 055. 二叉搜索树迭代器](https://leetcode-cn.com/problems/kTOapQ/)
实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：

BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
int next()将指针向右移动，然后返回指针处的数字。
注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。

可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。

```java
输入
inputs = ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
inputs = [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
输出
[null, 3, 7, true, 9, true, 15, true, 20, false]
	3
   /  \
  7	   15
       / \
      9   20
解释
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
bSTIterator.next();    // 返回 3
bSTIterator.next();    // 返回 7
bSTIterator.hasNext(); // 返回 True
bSTIterator.next();    // 返回 9
bSTIterator.hasNext(); // 返回 True
bSTIterator.next();    // 返回 15
bSTIterator.hasNext(); // 返回 True
bSTIterator.next();    // 返回 20
bSTIterator.hasNext(); // 返回 False
```

---

解题思路:

- 将二叉搜索树的值转换为顺序数组，然后进行操作即可。

```java
class BSTIterator {
    static int next;
    TreeNode root;
    // 中序遍历后有序的节点数组
    List<Integer> array;
    public BSTIterator(TreeNode root) {
        next = 0;
        this.root = root;
        array = new ArrayList<>();
        midOrder(root);
    }
    public int next() {
        return array.get(next++);
    }
    public boolean hasNext() {
        return next < array.size();
    }
    public void midOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        midOrder(root.left);
        array.add(root.val);
        midOrder(root.right);
    }
}
```

## [剑指 Offer II 060. 出现频率最高的 k 个数字](https://leetcode-cn.com/problems/g5c51o/)

给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。

```java
示例 1:
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
```

---

解题思路:

- ==HashMap==统计,然后==PriorityQueue==排序。

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int idx = 0;
        for (Map.Entry<Integer, Integer> en : hashMap.entrySet()) {
            if (idx < k) {
                queue.offer(new Integer[]{en.getKey(), en.getValue()});
            } else {
                if (en.getValue() >= queue.peek()[1]) {
                    queue.poll();
                    queue.offer(new Integer[]{en.getKey(), en.getValue()});
                }
            }
            idx++;
        }
        int[] ans = new int[k];
        idx = 0;
        Iterator<Integer[]> it = queue.iterator();
        while (it.hasNext()) {
            ans[idx++] = it.next()[0];
        }
        return ans;
    }
}
```

## [剑指 Offer II 063. 替换单词](https://leetcode-cn.com/problems/UhWRSj/)

在英语中，有一个叫做 词根(root) 的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。

现在，给定一个由许多词根组成的词典和一个句子，需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。

需要输出替换之后的句子。

```java
输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
输出："the cat was rat by the bat"
```

---

解题思路:

- 前缀树。题目本质是找前缀。

```java
class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        trie root = new trie();
        Iterator<String> it = dictionary.iterator();
        while (it.hasNext()) {
            root.insert(it.next());
        }
        String[] arr = sentence.trim().split("\\s+");
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            ans.append(root.preSearch(arr[i])+" ");
        }
        return ans.toString().trim();
    }
    static class trie {
        trie[] child;
        String isWord;
        public trie() {
            child = new trie[26];
            isWord = "";
        }
        public void insert(String word) {
            char[] chars = word.toCharArray();
            trie temp = this;
            for (int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                if (temp.child[ch - 'a'] == null) {
                    temp.child[ch - 'a'] = new trie();
                }
                temp = temp.child[ch - 'a'];
            }
            temp.isWord = word;
        }
        public String preSearch(String word) {
            char[] chars = word.toCharArray();
            trie temp = this;
            for (int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                if (temp.child[ch - 'a'] == null) {
                    return word;
                }
                temp = temp.child[ch - 'a'];
                if (!temp.isWord.equals("")) {
                    return temp.isWord;
                }
            }
            return word;
        }
    }
}
```

## [剑指 Offer II 064. 神奇的字典](https://leetcode-cn.com/problems/US1pGT/)

设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于已构建的神奇字典中。

实现 MagicDictionary 类：

MagicDictionary() 初始化对象
void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。

```java
示例：
输入
inputs = ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
inputs = [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
输出
[null, null, false, true, false, false]

解释
MagicDictionary magicDictionary = new MagicDictionary();
magicDictionary.buildDict(["hello", "leetcode"]);
magicDictionary.search("hello"); // 返回 False
magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
magicDictionary.search("hell"); // 返回 False
magicDictionary.search("leetcoded"); // 返回 False
```

---

解题思路:

- 前缀树 + DFS。
- 或者直接统计两个字符串不相同字符数，等于 1 就返回真。

```java
class MagicDictionary {
    MagicDictionary[] child;
    String word;
    public MagicDictionary() {
        child = new MagicDictionary[26];
        word = null;
    }
    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            MagicDictionary temp = this;
            for (char ch : word.toCharArray()) {
                if (temp.child[ch - 'a'] == null) {
                    temp.child[ch - 'a'] = new MagicDictionary();
                }
                temp = temp.child[ch - 'a'];
            }
            temp.word = word;
        }
    }
    static boolean ans;
    public boolean search(String searchWord) {
        ans = false;
        char[] arr = searchWord.toCharArray();
        DFS(this, arr, 0, false);
        return ans;
    }
    public void DFS(MagicDictionary root, char[] arr, int idx, boolean change) {
        if (root == null) {
            return;
        }
        if (arr.length == idx) {
            if (root.word != null && change) {
                ans = ans || true;
            }else {
                ans = ans || false;
            }
            return;
        }
        int index = arr[idx] - 'a';
        if (change && root.child[index] == null) {
            ans = ans || false;
            return;
        }
        if (change && root.child[index] != null) {
            DFS(root.child[index], arr, idx + 1, true);
        }

        if (!change) {
            for (int i = 0; i < 26; i++) {
                if (root.child[i] != null && i != index) {
                    DFS(root.child[i], arr, idx + 1, true);
                }
            }
            DFS(root.child[index], arr, idx + 1, false);
        }
    }
}
```



## [剑指 Offer II 065. 最短的单词编码](https://leetcode-cn.com/problems/iSwD2y/)

单词数组 words 的 有效编码 由任意助记字符串 s 和下标数组 indices 组成，且满足：

words.length == indices.length
助记字符串 s 以 '#' 字符结尾
对于每个下标 indices[i] ，s 的一个从 indices[i] 开始、到下一个 '#' 字符结束（但不包括 '#'）的 子字符串 恰好与 words[i] 相等
给定一个单词数组 words ，返回成功对 words 进行编码的最小助记字符串 s 的长度 。

```java
输入：words = ["time", "me", "bell"]
输出：10
解释：一组有效编码为 s = "time#bell#" 和 indices = [0, 2, 5] 。
words[0] = "time" ，s 开始于 indices[0] = 0 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
words[1] = "me" ，s 开始于 indices[1] = 2 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
words[2] = "bell" ，s 开始于 indices[2] = 5 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
```

---

解题思路:

- 前缀树。 此题的本质是找后缀重合的单词。所以从后往前建立"后缀树"，然后搜索，可以优化为在建树的过程中计算。

```java
class Solution {
    public int minimumLengthEncoding(String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            root.insert(word);
        }
        len  = 0;
        dfs(root, 0);
        return len;
    }
    public static int len;
    public static void dfs(Trie root, int deep) {
        boolean flag = false;
        for (int i = 0; i < 26; i++) {
            if (root.child[i] != null) {
                flag = true;
                dfs(root.child[i], deep + 1);
            }
        }
        if (!flag) {
            len += deep;
            if (root.word != null){
                len += 1;
            }
        }
    }
    static class Trie {
        Trie[] child;
        String word;
        //后缀
        public Trie() {
            child = new Trie[26];
            word = null;
        }
        public void insert(String word) {
            Trie temp = this;
            char[] chars = word.toCharArray();
            for (int i = chars.length - 1; i >= 0; i--) {
                int idx = chars[i] - 'a';
                if (temp.child[idx] == null) {
                    temp.child[idx] = new Trie();
                }
                temp = temp.child[idx];
            }
            temp.word = word;
        }
    }
}
```

## [剑指 Offer II 066. 单词之和](https://leetcode-cn.com/problems/z1R5dt/)

实现一个 MapSum 类，支持两个方法，insert 和 sum：

MapSum() 初始化 MapSum 对象
void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。

```java
示例：
输入：
inputs = ["MapSum", "insert", "sum", "insert", "sum"]
inputs = [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
输出：
[null, null, 3, null, 5]
解释：
MapSum mapSum = new MapSum();
mapSum.insert("apple", 3);  
mapSum.sum("ap");           // return 3 (apple = 3)
mapSum.insert("app", 2);    
mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
```

---

解题思路:

- 前缀树。把节点存储的单词换为值即可，然后遍历。

```java
class MapSum {
    MapSum[] child;
    int val;
    static int ans = 0;
    public MapSum() {
        val = -1;
        child = new MapSum[26];
    }
    public void insert(String key, int val) {
        MapSum temp = this;
        for (char ch : key.toCharArray()) {
            if (temp.child[ch - 'a'] == null) {
                temp.child[ch - 'a'] = new MapSum();
            }
            temp = temp.child[ch - 'a'];
        }
        temp.val = val;
    }
    public int sum(String prefix) {
        MapSum temp = this;
        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';
            if (temp.child[idx] == null) {
                return 0;
            }
            temp = temp.child[idx];
        }
        ans = 0;
        DFS(temp);
        return ans;
    }
    public static void DFS(MapSum root) {
        if (root == null) return;
        if (root.val != -1) ans += root.val;
        for (int i = 0; i < 26; i++) {
            if (root.child[i] != null) {
                DFS(root.child[i]);
            }
        }
    }
}
```



## [剑指 Offer II 067. 最大的异或](https://leetcode-cn.com/problems/ms70jA/)

给定一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。

```java
示例 1：
输入：nums = [3,10,5,25,2,8]
输出：28
解释：最大运算结果是 5 XOR 25 = 28.
```

---

解题思路:

- 异或。

- 前缀树:

  搜索的方法
  异或值最大，我们就要尽量让每个异或位都和 num 对应的二进制位不同。

  如果 num 当前位为 0，就到 next[1] 去搜索；
  如果 num 当前位为 1，就到 next[0] 去搜索;
  如果与 num 当前位相反的那一位为空，那就只能到相同的那一位去搜索了。

```java
class Solution {
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        int max = -1;
        for (int i = 0; i < nums.length; i++) {
            trie.build(nums[i]);
            int val = trie.search(nums[i]);
            max = max > val ? max : val;
        }
        return max;
    }
    static class Trie {
        Trie[] child;

        public Trie() {
            child = new Trie[2];
        }

        public void build(Integer num) {
            Trie temp = this;
            for (int i = 30; i >= 0; i--) {
                int flag = (num & (1 << i)) == 0 ? 0 : 1;
                if (temp.child[flag] == null) {
                    temp.child[flag] = new Trie();
                }
                temp = temp.child[flag];
            }
        }

        public int search(Integer val) {
            Trie temp = this;
            int ans = 0;
            for (int i = 30; i >= 0; i--) {
                int flag = (val & (1 << i)) == 0 ? 0 : 1;
                if (flag == 1) {
                    if (temp.child[0] != null) {
                        temp = temp.child[0];
                        ans = ans | (1 << i);
                    } else {
                        temp = temp.child[1];
                    }
                } else {
                    if (temp.child[1] != null) {
                        temp = temp.child[1];
                        ans = ans | (1 << i);
                    } else {
                        temp = temp.child[0];
                    }
                }
            }
            return ans;
        }
    }
}
```









## [剑指 Offer II 070. 排序数组中只出现一次的数字](https://leetcode-cn.com/problems/skFtm2/)

给定一个只包含整数的有序数组 `nums` ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。

```java
输入: nums = [1,1,2,3,3,4,4,8,8]
输出: 2
```

---

解题思路：

- 二分查找，区分 区间长度的奇偶 来进行二分。

```java
public static int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if ((mid == 0 && nums[mid] != nums[mid + 1]) || (mid == nums.length - 1 && nums[mid] != nums[mid - 1])) {
                return nums[mid];
            }
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }
            if (mid - 1 >= 0 && nums[mid] == nums[mid - 1]) {
                if ((mid - l) % 2 == 0) {
                    r = mid - 2;
                } else {
                    l = mid + 1;
                }
            }
            if (mid + 1 < nums.length && nums[mid] == nums[mid + 1]) {
                if ((r - mid) % 2 == 0) {
                    l = mid + 2;
                } else {
                    r = mid - 1;
                }
            }
        }
        return nums[l];
    }
```

## [剑指 Offer II 071. 按权重生成随机数](https://leetcode-cn.com/problems/cuyjEf/)

给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，它可以随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比。

例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。

也就是说，选取下标 i 的概率为 w[i] / sum(w) 。

```java
输入：
inputs = ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
inputs = [[[1,3]],[],[],[],[],[]]
输出：
[null,1,1,1,1,0]
解释：
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。

由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
诸若此类。
```

---

解题思路:

- 前缀和 + 二分查找。

  把==权重和==total求出来，在1~total上求随机数，然后和每个权重的区间(通过前缀和求)进行映射，求得下标随机数。

       w   3   1   4   2  total = 10 (0-3] 代表下标0  (3-4]代表1 (4-8] 代表2 ...
      pre  3   4   8   10 代表具体随机的值。
       i   [1   2   3]   [4]   [5   6   7   8]   [9   10] 

```java
class Solution {
        int[] preSum;
        int total;
        //  w   3   1   4   2
        // pre  3   4   (8   10] 代表具体随机的值。
        //  i   [1   2   3]   [4]   [5   6   7   8]   [9   10]
        public Solution(int[] w) {
            preSum = new int[w.length];
            total = Arrays.stream(w).sum();
            for (int i = 0; i < w.length; i++) {
                preSum[i] = i == 0 ? w[0] : w[i] + preSum[i - 1];
            }

        }
        public int pickIndex() {
            int seed = (int) (Math.random() * total) + 1;
            int idx = Arrays.binarySearch(preSum, seed);
            if (idx < 0) {
                idx = -idx -1;
            }
            return idx;
        }
    }
```



## [剑指 Offer II 073. 狒狒吃香蕉](https://leetcode-cn.com/problems/nZZqjQ/)

狒狒喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。

狒狒可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉，下一个小时才会开始吃另一堆的香蕉。  

狒狒喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。

返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。

```
输入: piles = [3,6,7,11], H = 8
输出: 4
```

---

解题思路:

- 二分查找最小速度。

  - ```java
    以此可以缩短判断时间。
    time += (pile-1)/K + 1;
    //time += pile % K == 0 ? pile / K : pile / K + 1;
    ```

  - 最大值范围确定可以直接赋值，省去了搜索最大值过程。

```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left =1,right = 1000000000;
        while(left<right){
            int mid = left + (right - left)/2;
            if(isFinished(piles,h,mid)){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
    private boolean isFinished(int[] piles,int h,int speed){
        int time = 0;
        for(int pile:piles){
            time += (pile-1)/speed + 1;
        }
        return time<=h;
    }
}
```

## [剑指 Offer II 074. 合并区间](https://leetcode-cn.com/problems/SsGoHC/)

以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

```java
示例 1：
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
```

---

解题思路:

- 按开始时间排序。

  可以合并的肯定是连续的。

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals,(o1, o2) -> o1[0]-o2[0]);
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
```

## [剑指 Offer II 079. 所有子集](https://leetcode-cn.com/problems/TVdhkn/)

给定一个整数数组 `nums` ，数组中的元素 **互不相同** 。返回该数组所有可能的子集（幂集）。

解集 **不能** 包含重复的子集。你可以按 **任意顺序** 返回解集。



---

解题思路:

- 两种回溯方法。
  - ==第一种，递归实现。== 每个元素的两种情况往下遍历。
  - ==第二种，for循环实现。==每个元素及其后面的元素一起遍历。

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        traceback(ans, new ArrayList<>(), nums, 0);
        return ans;
    }
    public  void traceback(List<List<Integer>> ans, List<Integer> list, int[] nums,
                                 int idx) {
        if (idx >= nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[idx]);
        traceback(ans, list, nums, idx + 1);
        list.remove(list.size() - 1);
        traceback(ans, list, nums, idx + 1);
    }
    public  void traceback2(List<List<Integer>> ans, List<Integer> list, int[] nums,
                                 int idx) {
        ans.add(new ArrayList<>(list));
        for (int i = idx; i < nums.length; i++) {
            list.add(nums[i]);
            traceback(ans, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
```

## [剑指 Offer II 080. 含有 k 个元素的组合](https://leetcode-cn.com/problems/uUsW3B/)

给定两个整数 `n` 和 `k`，返回 `1 ... n` 中所有可能的 `k` 个数的组合。

```java
输入: n = 4, k = 2
输出:
[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4],]
```

---

解题思路：

- 回溯方法。

```java
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        traceback(ans, new ArrayList<>(), 1, n, k);
        return ans;
    }
    public void traceback(List<List<Integer>> ans, List<Integer> list, int idx, int n,
                                 int k) {
        if (n - idx + 1 < k)return;
        if (k == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = idx; i <= n; i++) {
            list.add(i);
            traceback(ans, list, i + 1, n, k - 1);
            list.remove(list.size() - 1);
        }
    }
    public void traceback2(List<List<Integer>> ans, List<Integer> list, int idx, int n,
                                  int k) {
        if (n - idx + 1 < k)return;
        if (k == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        list.add(idx);
        traceback(ans, list, idx + 1, n, k - 1);
        list.remove(list.size() - 1);
        traceback(ans, list, idx + 1, n, k );
    }
}
```

## [剑指 Offer II 081. 允许重复选择元素的组合](https://leetcode-cn.com/problems/Ygoe9J/)

给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。

candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 

对于给定的输入，保证和为 target 的唯一组合数少于 150 个。

```
输入: candidates = [2,3,6,7], target = 7
输出: [[7],[2,2,3]]
```

---

解题思路:

- 回溯法

```java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        traceback(ans, new ArrayList<>(), candidates, 0, target);
        return ans;
    }
    public  void traceback(List<List<Integer>> ans, List<Integer> list, int[] candidates,
                                 int idx, int target) {
        if (target < 0 || idx >= candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            list.add(candidates[i]);
            traceback(ans, list, candidates, i, target - candidates[i]);
            list.remove(list.size() - 1);
        }
    }
    public  void traceback(List<List<Integer>> ans, List<Integer> list, int[] candidates,
                                 int idx, int target) {
        if (target < 0 || idx >= candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        list.add(candidates[idx]);
        // 加上当前这个数
        traceback(ans, list, candidates, idx, target - candidates[idx]);
        list.remove(list.size() - 1);
        if (idx + 1 >= candidates.length) {
            return;
        }
        // 不加当前这个数
        traceback(ans, list, candidates, idx + 1, target);
    }
}
```





## [剑指 Offer II 082. 含有重复元素集合的组合](https://leetcode-cn.com/problems/4sjJUc/)

给定一个可能有重复数字的整数数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。 

```java
输入: candidates = [10,1,2,7,6,1,5], target = 8,
输出: [[1,1,6],[1,2,5],[1,7],[2,6]]
```

---

解题思路:

- 回溯法。去重用排序的方法。遇到相同的数就不再进行访问，但是要拿当前的这个数。

```java
class Solution {
    static HashSet<List<Integer>> ans;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        // 为了避免重复答案
        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        traceback(candidates, ans, list, 0, 0, target);
        return ans;
    }
    public  void traceback(int[] candidates, List<List<Integer>> ans,
                                 List<Integer> list, int idx, int sum,
                                 int target) {
        if (sum == target) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            if (i > idx && candidates[i] == candidates[i-1]){
                // 因为前面那个同样的数已经经历过dfs，再拿同样的数dfs就会得到重复的答案
                continue;
            }
            list.add(candidates[i]);
            traceback(candidates, ans, list, i+1, sum + candidates[i], target);
            list.remove(list.size() - 1);
        }
    }
}
```



## [剑指 Offer II 083. 没有重复元素集合的全排列](https://leetcode-cn.com/problems/VvJkup/)

给定一个不含重复数字的整数数组 `nums` ，返回其 **所有可能的全排列** 。可以 **按任意顺序** 返回答案。

```
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

---

解题思路:

- 回溯。

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        traceback(ans, new ArrayList<>(), nums);
        return ans;
    }
    public void traceback(List<List<Integer>> ans, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]== -100)continue;
            int temp = nums[i];
            nums[i] = -100;
            list.add(temp);
            traceback(ans, list, nums);
            list.remove(list.size() - 1);
            nums[i] = temp;
        }
    }
}
```







## [剑指 Offer II 116. 省份数量](https://leetcode-cn.com/problems/bLyHh0/)

有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。

省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

给你一个 n x n 的矩阵 isConnected ，其中 isConnected\[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected\[i][j] = 0 表示二者不直接相连。

返回矩阵中 省份 的数量。

![img](asset/%E5%89%91%E6%8C%87%20Offer.assets/graph1.jpg)

```
输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
输出：2
```

---

解题思路:

- DFS

- BFS

- 并查集。

  每找到两个联通的节点，将其祖先统一。 即就是 isConnected\[i][j] = 1 时，Union(i,j)

```java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;
        int[] original = new int[N];
        for (int i = 0; i < N; i++) {
            original[i] = i;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (isConnected[i][j] == 1) {
                    // 连接节点 i j
                    int left = i;
                    int right = j;
                    // 找左节点的 祖先
                    while (original[left] != left) left = original[left];
                    // 找右节点的 祖先
                    while (original[right] != right) right = original[right];
                    // 找到后将其中一个的祖先赋给另外一个, 这样就可以统一祖先了
                    original[left] = right;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (original[i] == i) {
                ans++;
            }
        }
        return ans;
    }
}
```

## [剑指 Offer II 090. 环形房屋偷盗](https://leetcode-cn.com/problems/PzWKhm/)

一个专业的小偷，计划偷窃一个环形街道上沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。

```java
输入：nums = [1,2,3,1]
输出：4
解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     偷窃到的最高金额 = 1 + 3 = 4 。
```

---

解题思路:

- 动态规划 + 分类讨论。

  前后相接，只需要讨论第一个位置是否被抢，进行两次动态规划即可。

  抢 1 , 则不抢最后一个。不抢 1 , 则考虑抢最后一个。

```java
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[n];
        // rob 0
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int j = 2; j < n - 1; j++) {
            dp[j] = Math.max(nums[j] + dp[j - 2], dp[j - 1]);
        }
        int ans = dp[n - 2];
        // rob 1
        dp[0] = 0;
        dp[1] = nums[1];
        for (int j = 2; j < n; j++) {
            dp[j] = Math.max(nums[j] + dp[j - 2], dp[j - 1]);
        }
        return Math.max(ans, dp[n - 1]);
    }
}
```

## [剑指 Offer II 092. 翻转字符](https://leetcode-cn.com/problems/cyJERH/)

如果一个由 '0' 和 '1' 组成的字符串，是以一些 '0'（可能没有 '0'）后面跟着一些 '1'（也可能没有 '1'）的形式组成的，那么该字符串是 单调递增 的。

我们给出一个由字符 '0' 和 '1' 组成的字符串 s，我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'。

返回使 s 单调递增 的最小翻转次数。

```
输入：s = "010110"
输出：2
解释：我们翻转得到 011111，或者是 000111。
```

---

解题思路：

- 动态规划。答案字符串左侧肯定都是 0 ，右侧都是 1 。所以枚举每一个分割点，统计一次左侧 1 出现的次数，右侧 0 出现的次数，加在一起便是需要反转的字符个数。

```java
class Solution {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[] pre = new int[n];
        int[] dp = new int[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            num += s.charAt(i) - '0';
            pre[i] = num;
        }
        dp[0] = n - pre[n - 1];
        dp[n - 1] = pre[n - 1];
        int min = Math.min(dp[0], dp[n - 1]);
        for (int i = 1; i < n - 1; i++) {
            dp[i] = pre[i - 1] + (n - 1 - i - pre[n - 1] + pre[i]);
            min = Math.min(min, dp[i]);
        }
        return min;
    }
}
```

## [剑指 Offer II 093. 最长斐波那契数列](https://leetcode-cn.com/problems/Q91FMA/)

如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：

- n >= 3
- 对于所有 i + 2 <= n，都有 $X_i + X_{i+1} = X_{i+2}$
  给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。

（回想一下，子序列是从原序列  arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）

```java
输入: arr = [1,3,7,11,12,14,18]
输出: 3
解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
```

---

解题思路:

- 动态规划。

  ```
    dp[i][j]：表示以A[i],A[j]结尾的斐波那契数列的最大长度
                dp[i][j]=Len(......,A[i],A[j])
                A[k]+A[i]==A[j]
    dp[i][j] = max (dp[k][i]+1) 其中 A[k]+A[i]==A[j]
  ```

```java
class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        if (n == 0 || n == 1 || n == 2) {
            return 0;
        }
        int[][] dp = new int[n][n];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            hashMap.put(arr[i], i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = 2;
            }
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // i 开始 j 结束 A[j] - A[i] = A[k]
                if (hashMap.containsKey(arr[j] - arr[i])) {
                    int idx = hashMap.get(arr[j] - arr[i]);
                    dp[i][j] = Math.max(dp[i][j], dp[idx][i]+1);
                    ans = Math.max(dp[i][j], ans);
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }
}
```







## [剑指 Offer 06. 从尾到头打印链表](https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

```
输入：head = [1,3,2]
输出：[2,3,1]
```

---

解题思路：

用栈进行逆序。

```java
class Solution {
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int size = stack.size();
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = stack.pop();
        }
        return array;
    }
}
```



## [剑指 Offer 09. 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]

---

解题思路:

双栈实现队列，当一个序列经过一个栈会变成倒序，经过两个栈会重新变为顺序。维护两个栈，第一个栈支持插入操作，第二个栈支持删除操作。

- 加入队尾 appendTail()函数： 将数字 val 加入栈 A 即可。
- 删除队首 deleteHead()函数： 有以下三种情况。
  - 当栈 B 不为空： B中仍有已完成倒序的元素，因此直接返回 B 的栈顶元素。
  - 否则，当 A 为空： 即两个栈都为空，无元素，因此返回 -1−1 。
  - 否则： 将栈 A 元素全部转移至栈 B 中，实现元素倒序，并返回栈 B 的栈顶元素。

```java
class CQueue {
    private Stack<Integer> stackA;
    private Stack<Integer> stackB;
    public CQueue() {
        stackA = new Stack<Integer>();
        stackB = new Stack<Integer>();
    }

    public void appendTail(int value) {
        stackA.push(value);
    }

    public int deleteHead() {
        if (stackB.isEmpty()) {
            if (stackA.isEmpty()) {
                return -1;
            } else {
                while (!stackA.empty()) {
                    stackB.push(stackA.pop());
                }
            }
        }
        return stackB.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
```

## [剑指 Offer 14- I. 剪绳子](https://leetcode-cn.com/problems/jian-sheng-zi-lcof/)

给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

```java
示例 1：
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1
    
示例 2:
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
```

---

解题思路:

- 数学推理。

  设将长度为 n 的绳子切为 a 段：
  $n = n_1 + n_2 + ... + n_a$ 本题等价于求解：$\max(n_1 \times n_2 \times ... \times n_a)$

  以下公式为“算术几何均值不等式” ，等号当且仅当 $n_1 = n_2 = ... = n_a $时成立。$\frac{n_1 + n_2 + ... + n_a}{a} \geq \sqrt[a]{n_1 n_2 ... n_a}$

  > **推论一：** 将绳子 **以相等的长度等分为多段** ，得到的乘积最大。

  即就是求下式的最值，求导后得最值点为 x = e，取最近的整数点3。
  $$
  x^a=x^{\frac {x}{n}}=(x^{\frac {1}{x}})^n
  $$

  > **推论二：** 尽可能将绳子以长度 33 等分为多段时，乘积最大。

  当 n≤3 时，按照规则应不切分，但由于题目要求必须剪成 m>1 段，因此必须剪出一段长度为 1 的绳子，即返回 n - 1 。
  当 n>3 时，求 n 除以 3 的 整数部分 a 和 余数部分 b （即 n = 3a + b），并分为以下三种情况：
  当 b = 0 时，直接返回 $3^a $

  当 b = 1 时，要将一个 1 + 3 转换为 2+2，因此返回 $3^{a-1} \times 4$

  当 b = 2 时，返回 $3^a \times 2$

- 动态规划。

```java
class Solution {
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int residue = n % 3;
        int quotient = n / 3;
        if (residue == 0) {
            return (int) (Math.pow(3,quotient));
        } else if (residue == 1) {
            return (int) (Math.pow(3,quotient-1))*4;
        } else {
            return (int) (Math.pow(3,quotient)) *2 ;
        }
    }
}
```







## [剑指 Offer 24. 反转链表](https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/)

定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

```java
示例:
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

---

解题思路：

头插法反转链表。

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode ans = new ListNode();

        ListNode temp = head;
        while (head != null) {
            temp = head;
            head = head.next;

            temp.next = ans.next;
            ans.next = temp;

        }
        return ans.next;
    }
}
```

## [剑指 Offer 26. 树的子结构](https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/)

输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。



    例如:
    给定的树 A:  
      3
     / \
    4   5
       / \
      1   2
    给定的树 B：
       4 
      /
     1
    返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
---

解题思路：

​	若树 B 是树 A 的子结构，则子结构的根节点可能为树 A 的任意一个节点。因此，判断树 B 是否是树 A 的子结构，需完成以下两步工作：

- 先序遍历树 A 中的每个节点 $n_A$ 对应函数 isSubStructure(A, B)
- 判断树 A 中 以 $n_A$为根节点的子树 是否包含树 B 。（对应函数 recur(A, B)）

```java
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }

        return recur(A, B) || (isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }
    public  boolean recur(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        } else if (A == null) {
            return false;
        }

        if (A.val != B.val) {
            return false;
        } else {
            return recur(A.left, B.left) && recur(A.right, B.right);
        }
    }
}
```

## [剑指 Offer 28. 对称的二叉树](https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/)



请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

        1
       / \
      2   2
     / \ / \
    3  4 4  3

但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

        1
       / \
      2   2
       \   \
       3    3
---

解题思路：

递归判断。

- 判断两节点 `L.left` 和 `R.right` 是否对称；
- 判断两节点 `L.right` 和 `R.left` 是否对称；

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return judge(root.left, root.right);
    }
    public boolean judge(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null && left.val == right.val) {
            return judge(left.left, right.right) && judge(left.right, right.left);
        } else {
            return false;
        }
    }
}
```



## [剑指 Offer 30. 包含min函数的栈](https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/)

定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

```java
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.min();   --> 返回 -2.
```

---

解题思路：

双栈实现最小栈。

​	两个栈`stack`，`minStack`维持相同的大小，minStack的栈顶元素是当前对应的stack栈中的最小值。

​	在栈插入时，stack正常插入，minStack插入时，将插入元素与栈顶元素相比，如果栈顶元素更小，则插入栈顶元素，如果插入元素小，就插入需要插入的元素。这样维持着最小栈，当弹出最小值时只需要将minStack中的栈顶元素弹出。

```java
class MinStack {
    //双栈实现最小栈
    Stack<Integer> stack;
    Stack<Integer> minStack;
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    public void push(int x) {
        stack.push(x);
        if (minStack.empty()) {
            minStack.push(x);
            return;
        }
        minStack.push(x > minStack.peek() ? minStack.peek() : x);
    }
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    public int top() {
        return stack.peek();
    }
    public int min() {
        return minStack.peek();
    }
}
```

## [剑指 Offer 31. 栈的压入、弹出序列](https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/)

入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

```
输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
输出：true
解释：我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1

输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
输出：false
解释：1 不能在 2 之前弹出。
```

---

解题思路：

- 模拟。创建一个栈，用出栈顺序和进栈顺序可以模拟是否可以为一个弹出序列。

```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0;
        int popIndex = 0;
        while (pushIndex < pushed.length && popIndex < popped.length) {
            if (stack.empty()) {
                stack.push(pushed[pushIndex++]);
            }
            while (pushIndex < pushed.length && stack.peek() != popped[popIndex]) {
                stack.push(pushed[pushIndex++]);
            }
            while (popIndex < popped.length && !stack.empty() && stack.peek() == popped[popIndex]) {
                popIndex++;
                stack.pop();
            }
            if (stack.empty() && popIndex == popped.length) {
                return true;
            }
            if (pushIndex == pushed.length && !stack.empty()) {
                return false;
            }
        }
        return false;
    }
}
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for(int num : pushed) {
            stack.push(num); // num 入栈
            while(!stack.isEmpty() && stack.peek() == popped[i]) { // 循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}



```



## [剑指 Offer 34. 二叉树中和为某一值的路径](https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/)

输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。

```java
示例:
给定如下二叉树，以及目标和 target = 22，
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:
[
   [5,4,11,2],
   [5,8,4,5]
]
```

---

解题思路:

- DFS
- BFS

```java
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        DFS(root, list, target, 0, ans);
        return ans;
    }
    public  void DFS(TreeNode root, List<Integer> list, int tar, int sum, List<List<Integer>> ans) {
        if (root == null ) {
            return;
        }
        // if (root.val + sum == tar && (root.left != null || root.right != null)) {
        //     return;
        // }
        if (root.val + sum == tar && root.left == null && root.right == null) {
            list.add(root.val);
            List<Integer> temp = new ArrayList<Integer>();
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                temp.add(it.next());
            }
            ans.add(temp);
            list.remove(list.size() - 1);
            return;
        }
        list.add(root.val);
        DFS(root.left, list, tar, sum + root.val, ans);
        DFS(root.right, list, tar, sum + root.val, ans);
        list.remove(list.size() - 1);

    }
}
```





## [剑指 Offer 35. 复杂链表的复制](https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/)

请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。

<img src="asset/剑指 Offer.assets/e1.png" alt="img" style="zoom: 50%;" />

```java
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
```

---

解题思路：

​	使用Hash表保存新旧节点的映射关系，然后待新链表创建完成后再进行随机节点的复制。

```java

class Solution {
    public Node copyRandomList(Node head) {
        Node ans = new Node(0);
        Node tail = ans;
        Node tempHead = head;
        HashMap<Node, Node> hashMap = new HashMap<>();
        while (tempHead != null) {
            Node temp = new Node(tempHead.val);
            temp.next = tail.next;
            tail.next = temp;
            hashMap.put(tempHead, temp);
            tail = tail.next;
            tempHead = tempHead.next;
        }

        tempHead = head;
        tail = ans.next;
        while (tempHead != null) {
            if (tempHead.random != null) {
                tail.random = hashMap.get(tempHead.random);
            }
            //System.out.println(tempHead+"--"+tempHead.random);
            tempHead = tempHead.next;
            tail = tail.next;
        }
        return ans.next;

    }
}
```



## [剑指 Offer 36. 二叉搜索树与双向链表](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/)

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

为了让您更好地理解问题，以下面的二叉搜索树为例：

<img src="asset/%E5%89%91%E6%8C%87%20Offer.assets/1599401091-PKIjds-Picture1.png" alt="Picture1.png" style="zoom:67%;" />

我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。

特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。

---

解题思路:

- 中序遍历，中序遍历排序二叉树，所得的结果序列递增。所以选择中序遍历，在遍历过程中修改指针。

```java
class Solution {
    static Node pre, head;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        pre= null;
        head = null;
        midOrder(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    public void midOrder(Node root) {
        if (root == null) {
            return;
        }
        midOrder(root.left);
//      1. 修改前一个节点的后 为当前节点
//      2. 修改当前节点的前 为前一个节点
//      3. 更新当前节点为前一个节点
        if (pre != null) {
            pre.right = root;
        } else {
            head = root;
        }
        root.left = pre;

        pre = root;
        midOrder(root.right);

    }
}
```



## [剑指 Offer 45. 把数组排成最小的数](https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/)

输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

```python
输入: [3,30,34,5,9]
输出: "3033459"
```

---

解题思路:

- 重写排序。

  设数组 nums 中任意两数字的字符串为 x 和 y ，则规定 排序判断规则 为：

  若拼接字符串 x + y > y + x ，则 x “大于” y ；
  反之，若 x + y < y + x，则 x “小于” y ；
  x “小于” y 代表：排序完成后，数组中 x 应在 y 左边；“大于” 则反之。

  根据以上规则，套用任何排序方法对 numsnums 执行排序即可。


```java
class Solution {
    public String minNumber(int[] nums) {
        String[] number = new String[nums.length];
        for (int i = 0; i < number.length; i++) {
            number[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(number, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return s1.compareTo(s2);
            }
        });
        StringBuilder ans = new StringBuilder();
        for (String s : number) {
            ans.append(s);
        }
        return ans.toString();
    }
}
```



## [剑指 Offer 46. 把数字翻译成字符串](https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/)

给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

```java
示例 1:
输入: 12258
输出: 5
解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
```

---

解题思路：

- 动态规划。
  $$
  dp[i] = \begin{cases}
  dp[i-1]+dp[i-2],& 10\le x_i x_{i-1} \le25 \\
  dp[i-1],&else \\
  \end{cases}
  $$
  

```java
class Solution {
    public int translateNum(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            if (num <= 25) {
                return 2;
            }
            return 1;
        }
        int[] dp = new int[str.length()];
        dp[0] = 1;
        int temp = Integer.parseInt(str.substring(0, 2));
        dp[1] = temp <= 25  && temp >= 10? 2 : 1;
        for (int i = 2; i < n; i++) {
            temp = Integer.parseInt(str.substring(i - 1, i + 1));
            if (temp >= 10 && temp <= 25) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[n - 1];
    }
}
```



## [剑指 Offer 41. 数据流中的中位数](https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/)

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。

```java
示例 1：
输入：
["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
[[],[1],[2],[],[3],[]]
输出：[null,null,null,1.50000,null,2.00000]
```

---

解题思路:

- 大顶堆 + 小顶堆。

  建立一个 小顶堆 AA 和 大顶堆 BB ，各保存列表的一半元素，且规定：

  - A 保存 较大 的一半，长度为 $\frac{N}{2}$ （N 为偶数）或 $\frac{N+1}{2}$ （N为奇数）;

  - BB 保存 较小 的一半，长度为 $\frac{N}{2}$ （N 为偶数）或 $\frac{N-1}{2} $（NN 为奇数）；

    随后，中位数可仅根据 A,B 的堆顶元素计算得到。

```java
class MedianFinder {
    /** initialize your data structure here. */
    private PriorityQueue<Integer> smallHeap;
        private PriorityQueue<Integer> bigHeap;
    public MedianFinder() {
        smallHeap = new PriorityQueue<>();
            bigHeap = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
    }
    public void addNum(int num) {
        if (bigHeap.isEmpty() || num <= bigHeap.peek()) {
                bigHeap.offer(num);
            } else {
                smallHeap.offer(num);
            }
            int N1 = bigHeap.size();
            int N2 = smallHeap.size();
            if (N1 - N2 > 1) {
                smallHeap.offer(bigHeap.poll());
            }
            if (N1 - N2 <= -1) {
                bigHeap.offer(smallHeap.poll());
            }
    }
    public double findMedian() {
        int N1 = bigHeap.size();
            int N2 = smallHeap.size();
            if (N1 == N2) {
                return ((double) smallHeap.peek() + bigHeap.peek()) / 2;
            }
            return bigHeap.peek();
    }
}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```



## [剑指 Offer 48. 最长不含重复字符的子字符串](https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/)

请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

```java
示例 1:
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

---

解题思路：

- 双指针+哈希表。

  哈希表存储字符最后一次出现的索引，遍历字符串，双指针统计`最长不含重复字符`的子串。

  右指针依次遍历每个字符。

  当右指针指向的元素在前面出现过时，更新左指针。$$left = Math.max(hashMap.get(ch) + 1, left);$$

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
         HashMap<Character, Integer> hashMap = new HashMap<>();
        if (s.length() == 0) {
            return 0;
        }
        int max = 1;
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            char ch = s.charAt(right);
            if (hashMap.containsKey(ch)) {
                left = Math.max(hashMap.get(ch) + 1, left);
                hashMap.replace(ch, right);
            } else {
                hashMap.put(ch, right);
            }
            max = max > right - left + 1 ? max : right - left + 1;
            right++;
        }
        return max;
    }
}
```

## [剑指 Offer 49. 丑数](https://leetcode-cn.com/problems/chou-shu-lcof/)

我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。

```java
示例:
输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
```

---

解题思路:

- 动态规划。 和选择素数一样，只需要从1开始，选择所有丑数的2 、 3 、5 倍数，自然就是丑数，当需要顺序找到第几个时，需要用三个指针来判断顺序。

```java
class Solution {
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }

        int[] flag = new int[n + 1];
        flag[1] = 1;
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = flag[p2] * 2;
            int num3 = flag[p3] * 3;
            int num5 = flag[p5] * 5;
            int min = Math.min(num2, Math.min(num3, num5));
            flag[i] = min;
            if (min == num2) {
                p2++;
            }
            if (min == num3) {
                p3++;
            }
            if (min == num5) {
                p5++;
            }
        }
        return flag[n];
    }
}
```





## [剑指 Offer 60. n个骰子的点数](https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/)

把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

```java
输入: 2
输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
```

---

解题思路:

- 暴力。超时

- 动态规划。

  ![image-20210929162210939](asset/%E5%89%91%E6%8C%87%20Offer.assets/image-20210929162210939.png)

  ![image-20210929162222971](asset/%E5%89%91%E6%8C%87%20Offer.assets/image-20210929162222971.png)

  ![image-20210929162247423](asset/%E5%89%91%E6%8C%87%20Offer.assets/image-20210929162247423.png)

  以此类推，前一次掷的骰子概率×1/6，然后求和就好。

```java
class Solution {
    public double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6);
        for (int i = 2; i <= n; i++) {
            double[] ans = new double[i * 5 + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    ans[j + k] += dp[j] / 6.0;
                }
            }
            dp = ans;
        }
        return dp;
    }
}
```







## [剑指 Offer 64. 求1+2+…+n](https://leetcode-cn.com/problems/qiu-12n-lcof/)

求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

```java
示例 1：
输入: n = 3
输出: 6
```

---

解题思路:

- 递归。

  不使用循环时，递归是一个很好的办法，但是要求不使用if语句，所以难点是处理递归出口，解决办法是利用`&&`的短路运算性质。

```java
class Solution {
    public int sumNums(int n) {
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }
}
```





