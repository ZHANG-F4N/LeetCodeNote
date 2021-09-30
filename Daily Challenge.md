# Daily Challenge

[TOC]



## [68. 文本左右对齐](https://leetcode-cn.com/problems/text-justification/)

给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。

你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。

要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。

文本的最后一行应为左对齐，且单词之间不插入额外的空格。

说明:

单词是指由非空格字符组成的字符序列。
每个单词的长度大于 0，小于等于 maxWidth。
输入单词数组 words 至少包含一个单词。

```java
输入:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
输出:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
```

---

解题思路：

- 模拟。
  - 先统计一行会==最多==多少个单词。
  - 然后均匀处理。

```java
// 模拟，又臭又长
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        StringBuilder temp = new StringBuilder();
        List<String> list = new ArrayList<>();

        int index = 0;
        int i = 0;
        int[] coordinate = new int[maxWidth];
        while (index < words.length) {
            // 判断加上下一个单词会不会超出范围
            //  如果第一个单词就达到边界，需要特殊处理
            // 因为除过最后一个单词，其他单词后面默认加一个空格
            // 计算时要注意加一处理
            if (temp.length() + words[index].length() >= maxWidth) {
                if (temp.length() == 0){
                    coordinate[i++] = words[index].length();
                    temp.append(words[index++]);
                    list.add(adjust(temp, maxWidth, Arrays.copyOfRange(coordinate, 0, i)).toString());
                    //调整均匀
                    i = 0;
                    temp = new StringBuilder();
                    continue;
                }
                list.add(adjust(temp, maxWidth, Arrays.copyOfRange(coordinate, 0, i)).toString());
                //调整均匀
                i = 0;
                temp = new StringBuilder();
            }
            if (temp.length() != 0) {
                temp.append(' ');
            }
            if (index >= words.length) {
                break;
            }
            coordinate[i++] = words[index].length();
            temp.append(words[index++]);
        }
        if (temp.length() != 0){
            while (temp.length() < maxWidth) {
                temp.append(' ');
            }
            list.add(temp.toString());
        }
        return list;
    }
    public StringBuilder adjust(StringBuilder str, int maxWidth, int[] coordinate) {
        //n个位置添加空格
        int n = coordinate.length - 1;
        // 需要添加的空格个数
        int space = maxWidth - str.length();
        //平均每个位置add个空格
        if (n == 0) {
             while (str.length() < maxWidth) {
                str.append(' ');
            }
            return str;
        }
        int add = space / n;
        // 左侧需要额外添加空格的位置
        int left = space - (n * add);
        // 下一个添加空格的位置
        int nextSpace = coordinate[0];
        for (int i = 0; i < n; i++) {
            if (i < left) {
                str.insert(nextSpace++, ' ');
            }
            for (int j = 0; j < add; j++) {
                str.insert(nextSpace++, ' ');
            }
            nextSpace += coordinate[i+1]+1;
        }
        return str;
    }
}
```

## [91. 解码方法](https://leetcode-cn.com/problems/decode-ways/)

一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：

'A' -> 1
'B' -> 2
...
'Z' -> 26
要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：

"AAJF" ，将消息分组为 (1 1 10 6)
"KJF" ，将消息分组为 (11 10 6)
注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。

给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。

题目数据保证答案肯定是一个 32 位 的整数。

```
输入：s = "226"
输出：3
解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
输入：s = "06"
输出：0
解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
```

---

解题思路:

- 动态规划。

  ![image.png](asset/Daily%20Challenge.assets/c09dc70d3085792b2b8417843e297f6841fd12f921b0e4fe28a2c4a8dc86dd1e-image.png)

```java
class Solution {
    public int numDecodings(String s) {
        //含前导0就返回false
        int N = s.length();
        if (N == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int dp[] = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < N; i++) {
            char prCh = s.charAt(i - 1);
            char ch = s.charAt(i);
            if (ch == '0') {
                if (prCh == '2' || prCh == '1') {
                    dp[i + 1] = dp[i - 1];
                } else {
                    return 0;
                }
            } else if (prCh == '1' || (ch <= '6' && ch > '0') && prCh == '2') {
                dp[i + 1] = dp[i] + dp[i - 1];
            } else {
                dp[i + 1] = dp[i];
            }
        }
        return dp[N];
    }
}
```



## [165. 比较版本号](https://leetcode-cn.com/problems/compare-version-numbers/)

给你两个版本号 version1 和 version2 ，请你比较它们。

版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。

比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。

返回规则如下：

如果 version1 > version2 返回 1，
如果 version1 < version2 返回 -1，
除此之外返回 0。

```java
输入：version1 = "1.0", version2 = "1.0.0"
输出：0
解释：version1 没有指定下标为 2 的修订号，即视为 "0"
```

---

解题思路：

- 分割字符串后转为数字比较。
- 可以使用双指针。

```java
class Solution {
    public int compareVersion(String version1, String version2) {
       String[] number1 = version1.split("\\.");
        String[] number2 = version2.split("\\.");
        int i = 0, j = 0;
        while (i < number1.length && j < number2.length) {
            int ver1 = Integer.parseInt(number1[i]);
            int ver2 = Integer.parseInt(number2[j]);
            if (ver1 > ver2) {
                return 1;
            } else if (ver1 < ver2) {
                return -1;
            }
            i++;
            j++;
        }

        while (i < number1.length) {
            if (Integer.parseInt(number1[i]) != 0) {
                return 1;
            }
            i++;
        }
        while (j < number2.length) {
            if (Integer.parseInt(number2[j]) != 0) {
                return -1;
            }
            j++;
        }
        return 0;
    }
}
```



## [300. 最长递增子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)

给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

```java
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
```

---

解题思路:

- 动态规划。O(N^2)

  *dp*[*i*]=max(*dp*[*j*])+1,其中0≤*j*<*i*且*num*[*j*]<*num*[*i*]

- 动态规划 + 二分。O(NlogN)

```java
//动态规划 + 二分。O(NlogN)
	// dp+二分
    // 我们考虑维护一个列表 tails，
    // 其中每个元素 tails[k] 的值代表 长度为 k+1 的子序列尾部元素的值。
    // nums     10  9   2   5   3   7  21  18
    // index    0   1   2   3   4   5   6
    // tails    10  5   7   21
    //          9   3       18
    //          2
    // tails 的长度就是答案
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }
}
//动态规划
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = 1;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = max > dp[j] ? max : dp[j];
                }
            }
            dp[i] = max + 1;
            ans = ans > dp[i] ? ans : dp[i];
        }
        return ans;
    }
}

```



## [307. 区域和检索 - 数组可修改](https://leetcode-cn.com/problems/range-sum-query-mutable/)

给你一个数组 nums ，请你完成两类查询，其中一类查询要求更新数组下标对应的值，另一类查询要求返回数组中某个范围内元素的总和。

```java
实现 NumArray 类：

NumArray(int[] nums) 用整数数组 nums 初始化对象
void update(int index, int val) 将 nums[index] 的值更新为 val
int sumRange(int left, int right) 返回子数组 nums[left, right] 的总和（即，nums[left] + nums[left + 1], ..., nums[right]）
```

```java
输入：
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
输出：
[null, 9, null, 8]

解释：
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // 返回 9 ，sum([1,3,5]) = 9
numArray.update(1, 2);   // nums = [1,2,5]
numArray.sumRange(0, 2); // 返回 8 ，sum([1,2,5]) = 8
```

---

解题思路：

- 直接使用数组实现。 时间复杂度O(n).

- 线段树。时间复杂度$O(log(n))$

  ​    线段树是一种非常灵活的数据结构，它可以用于解决多种范围查询问题，比如在对数时间内从数组中找到最小值、最大值、总和、最大公约数、最小公倍数等。

  图来自：https://leetcode-cn.com/u/red_devil/

<img src="asset/Daily Challenge.assets/6525fc7015b2873e744c6f0e8b0470d3fee72cace457126e91416cd2dd1a8a8a-TIM图片20200729165821.jpg" alt="TIM图片20200729165821.jpg" style="zoom: 33%;" />

<img src="asset/Daily Challenge.assets/960961525d043f52731f52f951f6630f1de61527b38860104a2992375fc11f4c-TIM图片20200729165840.jpg" alt="TIM图片20200729165840.jpg" style="zoom: 33%;" />

```java
class NumArray {
    private int[] tree;
    private int n;
    public NumArray(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[n * 2];
            buildTree(nums);
        }
    }
    private void buildTree(int[] nums) {
        for (int i = n, j = 0; i < 2 * n; i++, j++) {
            tree[i] = nums[j];
        }
        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }
    public void update(int index, int val) {
        index = n + index;

        tree[index] = val;
        index = index / 2;
        while (index > 0) {
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
            index = index / 2;
        }
    }
    public int sumRange(int left, int right) {
        int sum = 0;
        left += n;
        right += n;

        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left];
                left++;
            }
            if (right % 2 == 0) {
                sum += tree[right];
                right--;
            }
            left /= 2;
            right /= 2;

        }
        //sum += tree[right];

        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
```

## [430. 扁平化多级双向链表](https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/)

多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。

给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。

示例:

<img src="asset/Daily%20Challenge.assets/multilevellinkedlist.png" alt="img" style="zoom:67%;" />

扁平化后:

![img](asset/Daily%20Challenge.assets/multilevellinkedlistflattened.png)

---

解题思路:

- 顺序遍历。

  当遇到一个含有子链表的节点时，先把子链表插入到当前节点的后面，不用处理子链表包含的子链表。

  当连接之后，继续顺序访问，依次处理，就可以将所有子链表顺序化。

```java
class Solution {
    public Node flatten(Node head) {
        if(head == null){
            return null;
        }
        Node temp = head;
        while(temp != null){
            if(temp.child == null){
                temp = temp.next;
                continue;
            }
            Node subHead = temp.child;
            Node subEnd = temp.child;
            while(subEnd.next!=null){
                subEnd = subEnd.next;
            }
            subHead.prev = temp;
            subEnd.next = temp.next;
            Node tempNext = temp.next;
            temp.next = subHead;
            if(tempNext!=null){
                tempNext.prev = subEnd;
            }
            
            temp.child= null;
            temp = temp.next;
        }
        return head;
    }
}
```



## [447. 回旋镖的数量](https://leetcode-cn.com/problems/number-of-boomerangs/)

给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。

返回平面上所有回旋镖的数量。

```java
输入：points = [[0,0],[1,0],[2,0]]
输出：2
解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
```

---

解题思路:

- 枚举 + 哈希表。

  ​	题目所描述的回旋镖可以视作一个 V 型的折线。我们可以枚举每个 points[i]，将其当作 V 型的拐点。设 points 中有 m 个点到 points[i] 的距离均相等，我们需要从这 m 点中选出 2 个点当作回旋镖的 2 个端点，由于题目要求考虑元组的顺序，因此方案数即为在 m 个元素中选出 22 个不同元素的排列数，即：$A_m^2 = m\cdot(m-1)$

  ​	我们可以遍历 points，计算并统计所有点到 points[i] 的距离，将每个距离的出现次数记录在哈希表中，然后遍历哈希表，并用上述公式计算并累加回旋镖的个数。

```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int[] point : points) {

            HashMap<Integer, Integer> hashMap = new HashMap<>();

            for (int[] ints : points) {
                int dis = (point[0] - ints[0]) * (point[0] - ints[0]) + (point[1] - ints[1]) * (point[1] - ints[1]);
                hashMap.put(dis, hashMap.getOrDefault(dis, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> en : hashMap.entrySet()) {

                res += en.getValue() * (en.getValue() - 1);
            }
        }
        return res;
    }
}
```





## [470. 用 Rand7() 实现 Rand10()](https://leetcode-cn.com/problems/implement-rand10-using-rand7/)

已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。

不要使用系统的 Math.random() 方法。

---

解题思路：

- 7进制+拒绝采样。

  7进制解法，将用7进制映射到0-48；采用拒绝采样 ，使用0-39共40个数，取模。

```java
/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        // 7进制解法，将用7进制映射到0-48；
        // 采用拒绝采样 ，使用0-39共40个数，取模。
        int num1 = rand7()-1;
        int num2 = rand7()-1;
        int ans = num1*7 + num2;
        while(ans >= 40){
            num1 = rand7()-1;
            num2 = rand7()-1;
            ans = num1*7 + num2;
        }
        return (ans) % 10 +1;
    }
}
```



## [502. IPO](https://leetcode-cn.com/problems/ipo/)

假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。

给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。

最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。

总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。

答案保证在 32 位有符号整数范围内。

```java
输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
输出：4
解释：
由于你的初始资本为 0，你仅可以从 0 号项目开始。
在完成后，你将获得 1 的利润，你的总资本将变为 1。
此时你可以选择开始 1 号或 2 号项目。
由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
```

---

解题思路：

- 贪心算法。和解决死锁问题的银行家算法一致，但是有许多编程上的技巧。

  先排成本，从成本里可以启动的里面选利润最高的。

  - 使用数组要比使用优先级队列`PriorityQueue`快的多。
  - 可以快速分支，将一些简单情况直接处理，代码虽然长了，但是快了。

```java
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
       // 快速分支-当初始资本可以开启所有项目时，从最赚钱的项目开始投资，用到优先队列
        int n = profits.length;
        boolean speedUp = true;
        for (int i = 0; i < n; i++) {
            if (w < capital[i]) {
                speedUp = false;
                break;
            }
        }
        if (speedUp) {
            Arrays.sort(profits);
            for (int i = 0; i < Math.min(k, n); i++) {
                w += profits[n - 1 - i];
            }
            return w;
        }

        for (int i = 0; i < Math.min(k, n); i++) {
            //记录上一个最大资本的位置
            int idx = -1;
            for (int j = 0; j < n; j++) {
                if (w >= capital[j]) {
                    if (idx == -1) {
                        idx = j;
                    } else if (profits[j] > profits[idx]) {
                        idx = j;
                    }
                }
            }
            if (idx == -1) break;
            w += profits[idx];
            //使用过的项目置为最大
            capital[idx] = Integer.MAX_VALUE;
        }
        return w;
    }
}
```

## [517. 超级洗衣机](https://leetcode-cn.com/problems/super-washing-machines/)

假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。

在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。

给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。

```java
示例 1：

输入：machines = [1,0,5]
输出：3
解释：
第一步:    1     0 <-- 5    =>    1     1     4
第二步:    1 <-- 1 <-- 4    =>    2     1     3    
第三步:    2     1 <-- 3    =>    2     2     2   
```

---

阶梯思路:

- 贪心算法。

  将前 *i* 台洗衣机看成一组，记作 *A*，其余洗衣机看成另一组，记作 *B*。

  再构造两个数组 

  - sum -- 表示前i个洗衣机总共 缺了少了或多了多少件衣服。
  - status -- 表示位置i的洗衣机需要或多了 machine[i] - avg件衣服。

  组间 : A 与 B 两组之间的衣服，最多需要 $\max_{i=0}^{n-1}|\textit{sum}[i]$ 次衣服移动；

  组内 : 某一台洗衣机内的衣服数量过多，需要向左右两侧移出衣服，这最多需要 $\max_{i=0}^{n-1}\textit{machines}[i]$次衣服移动。

```java
class Solution {
    public int findMinMoves(int[] machines) {
        int N = machines.length;
        int count = 0;
        count = Arrays.stream(machines).sum();
//        for (int machine : machines) {
//            count += machine;
//        }
        if (count % N != 0) {
            return -1;
        }
        int ans = 0;
        int status = 0;
        int sum = 0;
        int average = count / N;
        for (int i = 0; i < N; i++) {
            status = machines[i] - average;
            sum += status;
            ans = Math.max(ans, Math.max(status, Math.abs(sum)));
        }
        return ans;
    }   
}
```









## [524. 通过删除字母匹配到字典里最长单词](https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/)

给你一个字符串 `s` 和一个字符串数组 `dictionary` 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 `s` 中的某些字符得到。

如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。

**示例 1：**

```
输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
输出："apple"
```

---

解题思路:

- 双指针+排序

  我们初始化两个指针 i 和 j，分别指向 t 和 s 的初始位置。每次贪心地匹配，匹配成功则 i 和 j 同时右移，匹配 t 的下一个位置，匹配失败则 j 右移，i 不变，尝试用 s 的下一个字符匹配 t。

  最终如果 i 移动到 t 的末尾，则说明 t 是 s 的子序列。

  我们可以先将 dictionary 依据字符串长度的降序和字典序的升序进行排序，然后从前向后找到第一个符合条件的字符串直接返回即可。


```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
         String ans = "{";
        for (int i = 0; i < dictionary.size(); i++) {
            String str = dictionary.get(i);
            if (judge(s, str)) {
                if (ans.length() < str.length()) {
                    ans = str;
                    continue;
                }
                if (ans.length() == str.length() && ans.compareTo(str) > 0) {
                    ans = str;
                }
            }
        }
        if (ans.equals("{")) {
            return "";
        }
        return ans;
    }
    public boolean judge(String str, String sub) {
        int index1 = 0;
        int index2 = 0;
        int M = str.length() - 1;
        int N = sub.length() - 1;
        while (index1 <= M && index2 <= N) {
            if (str.charAt(index1) == sub.charAt(index2)) {
                index1++;
                index2++;
                continue;
            }
            while (index1 <= M && str.charAt(index1) != sub.charAt(index2)) {
                index1++;
            }
            if (index1 > M && index2 <= N) {
                return false;
            }
        }
        if (index1 > M && index2 <= N) {
            return false;
        }
        return true;
    }
}
```



## [725. 分隔链表](https://leetcode-cn.com/problems/split-linked-list-in-parts/)

给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。

每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。

这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。

返回一个由上述 k 部分组成的数组。

```java
输入：head = [1,2,3], k = 5
输出：[[1],[2],[3],[],[]]
解释：
第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
```

```java
输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
输出：[[1,2,3,4],[5,6,7],[8,9,10]]
解释：
输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
```

---

解题思路:

- 分隔链表时，注意一些微操作。
  - 在切断链表时，需要前一个节点的指针，可以手动添加一个头结点简化操作。
  - 在处理 `每部分的长度相差不超过 1` 时,可以使用下标操作。

```java
class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int nodeNum = count / k;
        int residue = count - nodeNum * k - 1;

        temp = head;

        ListNode[] ans = new ListNode[k];
        for (int i = 0; i < k; i++) {
            int num = nodeNum;
            if (i <= residue) {
                num = num + 1;
            }

            ListNode node = new ListNode();
            node.next = temp;
            ListNode pre = node;
            for (int j = 0; j < num && temp != null; j++) {
                pre = temp;
                temp = temp.next;
            }
            pre.next = null;
            ans[i] = node.next;
        }
        return ans;
    }
}
```







## [1109. 航班预订统计](https://leetcode-cn.com/problems/corporate-flight-bookings/)

这里有 n 个航班，它们分别从 1 到 n 进行编号。

有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。

请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。

```java
输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
输出：[10,55,45,25,25]
解释：
航班编号        1   2   3   4   5
预订记录 1 ：   10  10
预订记录 2 ：       20  20
预订记录 3 ：       25  25  25  25
总座位数：      10  55  45  25  25
因此，answer = [10,55,45,25,25]
```

---

解题思路：

- 暴力 $O(n*m)$

- 差分-前缀和数组。

  ​	差分数组对应的概念是前缀和数组，对于数组 \[1,2,2,4]，其差分数组为 \[1,1,0,2]，差分数组的第 i 个数即为原数组的第 i-1个元素和第 i 个元素的差值，也就是说我们对差分数组求前缀和即可得到原数组。

  `差分数组的性质`是，当我们希望对原数组的某一个区间 \[l,r] 施加一个增量 *inc* 时，差分数组 d 对应的改变是：

  - d[l]增加*inc*, `d[l] = d[l] + inc` 。
  - d[r+1]减少 *inc*，`d[r+1] = d[r+1] - inc` 。

  ​    这样对于区间的修改就变为了对于两个位置的修改。并且这种修改是可以叠加的，即当我们多次对原数组的不同区间施加不同的增量，我们只要按规则修改差分数组即可。

  ​	在本题中，我们可以遍历给定的预定记录数组，每次 O(1) 地完成对差分数组的修改即可。当我们完成了差分数组的修改，只需要最后求出差分数组的前缀和即可得到目标数组。


```java
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i < bookings.length; i++) {
            ans[bookings[i][0]] += bookings[i][2];
            if (bookings[i][1] + 1 <= n) {
                ans[bookings[i][1] + 1] -= bookings[i][2];
            }
        }
        for (int i = 1; i < n + 1; i++) {
            ans[i] += ans[i - 1];
        }
        return Arrays.copyOfRange(ans, 1, ans.length);
    }
}
```

