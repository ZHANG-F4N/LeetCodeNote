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

