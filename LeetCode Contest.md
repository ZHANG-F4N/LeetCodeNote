# LeetCode Contest

[TOC]



## 20210911-2-心算挑战

「力扣挑战赛」心算项目的挑战比赛中，要求选手从 `N` 张卡牌中选出 `cnt` 张卡牌，若这 `cnt` 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 `cnt` 张卡牌数字总和。给定数组 `cards` 和 `cnt`，其中 `cards[i]` 表示第 `i` 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。

```java
输入：cards = [1,2,8,9], cnt = 3
输出：18
解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。.
输入：cards = [3,3,1], cnt = 1
输出：0
解释：不存在获取有效得分的卡牌方案。
```

**提示：**

- `1 <= cnt <= cards.length <= 10^5`
- `1 <= cards[i] <= 1000`

```java
class Solution {
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int ans = 0;
        int oddIndex = Integer.MAX_VALUE;
        int evenIndex = Integer.MAX_VALUE;
        for (int i = cards.length - 1; i >= cards.length - cnt; i--) {
            if (cards[i] % 2 == 0) {
                evenIndex = i;
            } else {
                oddIndex = i;
            }
            ans += cards[i];
        }
        if (ans % 2 == 0) {
            return ans;
        }
        int maxOdd = Integer.MAX_VALUE;
        int maxEven = Integer.MAX_VALUE;
        for (int i = 0; i < cards.length - cnt; i++) {
            if (cards[i] % 2 == 0) {
                maxEven = i;
            } else {
                maxOdd = i;
            }
        }
        int temp1 = 0;
        int temp2 = 0;
        if (maxEven != Integer.MAX_VALUE && oddIndex != Integer.MAX_VALUE) {
            temp1 = ans - cards[oddIndex] + cards[maxEven];
        }
        if (maxOdd != Integer.MAX_VALUE && evenIndex != Integer.MAX_VALUE) {
            temp2 = ans - cards[evenIndex] + cards[maxOdd];
        }
        ans = Math.max(temp1, temp2);
        ans = Math.max(0,ans);
       
        return ans;
    }
}
```

## 250-3 [扣分后的最大得分](https://leetcode-cn.com/problems/maximum-number-of-points-with-cost/)

给你一个 m x n 的整数矩阵 points （下标从 0 开始）。一开始你的得分为 0 ，你想最大化从矩阵中得到的分数。

你的得分方式为：每一行 中选取一个格子，选中坐标为 (r, c) 的格子会给你的总得分 增加 points[r][c] 。

然而，相邻行之间被选中的格子如果隔得太远，你会失去一些得分。对于相邻行 r 和 r + 1 （其中 0 <= r < m - 1），选中坐标为 (r, c1) 和 (r + 1, c2) 的格子，你的总得分 减少 abs(c1 - c2) 。

请你返回你能得到的 最大 得分。

<img src="asset/LeetCode%20Contest.assets/screenshot-2021-07-12-at-13-40-26-diagram-drawio-diagrams-net.png" alt="img" style="zoom:33%;" />

```java
输入：points = [[1,2,3],[1,5,1],[3,1,1]]
输出：9
解释：
蓝色格子是最优方案选中的格子，坐标分别为 (0, 2)，(1, 1) 和 (2, 0) 。
你的总得分增加 3 + 5 + 3 = 11 。
但是你的总得分需要扣除 abs(2 - 1) + abs(1 - 0) = 2 。
你的最终得分为 11 - 2 = 9 。
```

---

解题思路:

- 动态规划。

  ```
  /*
  * dp[i][j] : 第i行 选择 points[i][j] 的元素时,最大值
  * dp[i][j] = Max{ dp[i-1][j'] -|j-j'|} + points[i][j]
  *               其中 j' 是上一行的每个 j
  * 这时的转移方程的实现复杂度是O(mnn)
  * 可以对 |j-j'| 进行优化,分 j' < j 和 j' > j
  * j' < j 时, dp[i][j] = Max{ dp[i-1][j'] - j + j' } + points[i][j]
  *                     = Max{ dp[i-1][j'] + j' } + points[i][j] - j
  * j' > j 时, dp[i][j] = Max{ dp[i-1][j'] - j' + j } + points[i][j]
  *                     = Max{ dp[i-1][j'] - j' } + points[i][j] + j
  *
  * */
  //正向和反向计算一遍 dp[i-1][j']+j' 和 dp[i-1][j']-j'
  // j'<j 时,记录 0~j' 上 dp[i-1][j'] + j'的最大值
  //      同时算出 Max{ dp[i-1][j'] + j' } + points[i][j] - j
  // j'>j 时, 记录 j'~ m-1 上 dp[i-1][j'] - j'的最大值
  //      同时算出 Max{ dp[i-1][j'] - j' } + points[i][j] + j
  ```

```java
class Solution {
    public long maxPoints(int[][] points) {
        int m = points[0].length;
        long[] pre = new long[m];
        long[] dp = new long[m];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            pre[i] = points[0][i];
            max = Math.max(max, points[0][i]);
        }
        if (points.length == 1) {
            return max;
        }
        for (int i = 1; i < points.length; i++) {
            //正向和反向计算一遍 dp[i-1][j']+j' 和 dp[i-1][j']-j'
            long[] forth = new long[m];
            long[] back = new long[m];
            long preMax = Long.MIN_VALUE;
            for (int j = 0; j < m; j++) {
                forth[j] = pre[j] + j;
                preMax = Math.max(preMax, forth[j]);
                dp[j] = points[i][j] - j + preMax;
            }
            preMax = Long.MIN_VALUE;
            for (int j = m - 1; j >= 0; j--) {
                back[j] = pre[j] - j;
                preMax = Math.max(preMax, back[j]);
                dp[j] = Math.max(dp[j], points[i][j] + j + preMax);
            }
            pre = Arrays.copyOf(dp, m);
        }
        long ans = 0;
        for (int i = 0; i < m; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```





## 266-3 [分配给商店的最多商品的最小值](https://leetcode-cn.com/problems/minimized-maximum-of-products-distributed-to-any-store/)

给你一个整数 n ，表示有 n 间零售商店。总共有 m 种产品，每种产品的数目用一个下标从 0 开始的整数数组 quantities 表示，其中 quantities[i] 表示第 i 种商品的数目。

你需要将 所有商品 分配到零售商店，并遵守这些规则：

一间商店 至多 只能有 一种商品 ，但一间商店拥有的商品数目可以为 任意 件。
分配后，每间商店都会被分配一定数目的商品（可能为 0 件）。用 x 表示所有商店中分配商品数目的最大值，你希望 x 越小越好。也就是说，你想 最小化 分配给任意商店商品数目的 最大值 。
请你返回最小的可能的 x 。

```java
输入：n = 6, quantities = [11,6]
输出：3
解释： 一种最优方案为：
- 11 件种类为 0 的商品被分配到前 4 间商店，分配数目分别为：2，3，3，3 。
- 6 件种类为 1 的商品被分配到另外 2 间商店，分配数目分别为：3，3 。
分配给所有商店的最大商品数目为 max(2, 3, 3, 3, 3, 3) = 3 。
```

---

解题思路:

- 二分查找，对每个商店能接受的最大值进行二分，不断逼近能接受的最大值的最小值。

  注意 除 0 case. 

```java
class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < quantities.length; i++) {
            right = right > quantities[i] ? right : quantities[i];
        }
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (judge(n, quantities, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    // 最多分配 x 满足条件返回 true 不满足条件返回 false
    public  boolean judge(int n, int[] quantities, int x) {
        if (x == 0) {
            return Arrays.stream(quantities).sum() == 0;
        }
        int need = 0;
        for (int i = 0; i < quantities.length; i++) {
            if (quantities[i] <= x) {
                need++;
            } else {
                need += ((quantities[i]-1) / x)+1;
            }
            if (need > n) {
                return false;
            }
        }
        return need <= n;
    }
}
```

