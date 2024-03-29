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

## [187. 重复的DNA序列](https://leetcode-cn.com/problems/repeated-dna-sequences/)

所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。

编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。

```
输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
输出：["AAAAACCCCC","CCCCCAAAAA"]
输入：s = "AAAAAAAAAAAAA"
输出：["AAAAAAAAAA"]
```

---

解题思路:

- hash表，把每十个字符保存进hashmap来检验，统计出现次数。
- 二进制，把每个字符转化为二进制，然后滑动窗口检验。

```java
//二进制
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>() {
            {
                put('A', 0);
                put('C', 1);
                put('G', 2);
                put('T', 3);
            }
        };
        if (s.length() < 11) {
            return ans;
        }
        int temp = 0;
        for (int i = 0; i < 9; i++) {
            temp = (temp << 2) | map.get(s.charAt(i));
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length() - 9; i++) {
            temp = (((temp << 2) | map.get(s.charAt(i + 9))) & ((1 << 20) - 1));
            hashMap.put(temp, hashMap.getOrDefault(temp, 0) + 1);
            if (hashMap.get(temp) == 2) {
                ans.add(s.substring(i, i + 10));
            }
        }
        return ans;
    }
}
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        if (s.length() < 11) {
            return ans;
        }
        for (int i = 9; i < s.length(); i++) {
            String subStr = s.substring(i - 9, i + 1);
            hashMap.put(subStr, hashMap.getOrDefault(subStr, 0) + 1);
            if (hashMap.get(subStr) == 2) {
                ans.add(subStr);
            }
        }
        return ans;
    }
}
```

## [226. 翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/)

翻转一棵二叉树。

**示例：**

输入：

```
     4
   /   \
  2     7
 / \   / \
1   3 6   9
```

输出：

```
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

---

解题思路：

- 递归，从底往上翻转。

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        reverse(root);
        return root;
    }
    public void reverse(TreeNode root) {
        if (root == null) return;
        // 先达到最底部
        reverse(root.left);
        reverse(root.right);
        // 将左右交换
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
```

## [260. 只出现一次的数字 III](https://leetcode-cn.com/problems/single-number-iii/)

给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。

进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？

```java
输入：nums = [1,2,1,3,2,5]
输出：[3,5]
解释：[5, 3] 也是有效的答案。
```

---

解题思路：

- 哈希表。

- 位运算，由于有两个出现了一次的数，如果我们对所有数进行异或运算，假设最后得到结果XOR。

  XOR肯定是两个出现一次的数异或的结果，对XOR进行lowbit运算(X&(-X))，此时得到这一位的位置，肯定是两个数一个为0，一个为1的位。

  那么可以依次将这组数分为两组，一组这位为1，一组这位为0，分别得到两个出现一次的数。

  注意溢出操作。

```java
class Solution {
    public int[] singleNumber(int[] nums) {
        int XOR = 0;
        for (int num : nums) {
            XOR ^= num;
        }
        int lowBit = XOR & (-XOR);

        // 一组lowbit位为1
        // 另外一组为0

        // 移32位移除
        // 防止溢出
        int temp = (XOR == Integer.MIN_VALUE ? XOR : XOR & (-XOR));
        //int temp = 1 << (lowBit - 1);
        int num1 = 0;
        int num2 = 0;
        for (int num : nums) {
            if ((num & temp) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        return new int[]{num1, num2};
    }
}
```

## [299. 猜数字游戏](https://leetcode-cn.com/problems/bulls-and-cows/)

你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：

写出一个秘密数字，并请朋友猜这个数字是多少。朋友每猜测一次，你就会给他一个包含下述信息的提示：

猜测数字中有多少位属于数字和确切位置都猜对了 A（称为 "Bulls", 公牛），
有多少位属于数字猜对了但是位置不对 B（称为 "Cows", 奶牛）。也就是说，这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字。
给你一个秘密数字 secret 和朋友猜测的数字 guess ，请你返回对朋友这次猜测的提示。

提示的格式为 "xAyB" ，x 是公牛个数， y 是奶牛个数，A 表示公牛，B 表示奶牛。

请注意秘密数字和朋友猜测的数字都可能含有重复数字。

---

解题思路:

- 统计就可以了。

```java
class Solution {
    public String getHint(String secret, String guess) {
         int N = secret.length();
        // compute Bulls
        char[] right = secret.toCharArray();
        char[] gue = guess.toCharArray();
        int[] num1 = new int[10];
        int[] num2 = new int[10];
        int count1 = 0;
        for (int i = 0; i < N; i++) {
            if (right[i] == gue[i]) {
                count1++;
                gue[i] = '*';
                right[i] = '*';
            }
            if (right[i] != '*')
                num1[right[i] - '0']++;
            if (gue[i] != '*')
                num2[gue[i] - '0']++;
        }
        int count2 = 0;
        for (int i = 0; i < 10; i++) {
            count2 +=  Math.min(num1[i] , num2[i]);
        }
        return count1 + "A" + count2 + "B";
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

## [319. 灯泡开关](https://leetcode-cn.com/problems/bulb-switcher/)

初始时有 n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭一个。

第三轮，你每三个灯泡就切换一个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换一个灯泡的开关。直到第 n 轮，你只需要切换最后一个灯泡的开关。

找出并返回 n 轮后有多少个亮着的灯泡。

```java
输入：n = 3
输出：1 
解释：
初始时, 灯泡状态 [关闭, 关闭, 关闭].
第一轮后, 灯泡状态 [开启, 开启, 开启].
第二轮后, 灯泡状态 [开启, 关闭, 开启].
第三轮后, 灯泡状态 [开启, 关闭, 关闭]. 

你应该返回 1，因为只有一个灯泡还亮着。
```

---

解题思路:

- 数学规律:对于第 k 个灯泡，它被切换的次数恰好就是 k 的约数个数。如果 k 有偶数个约数，那么最终第 k 个灯泡的状态为暗；如果 k 有奇数个约数，那么最终第 k 个灯泡的状态为亮。对于 k 而言，如果它有约数 x，那么一定有约数 $\dfrac{k}{x} $ 。因此只要当 $x^2 \neq k$ 时，约数都是「成对」出现的。这就说明，只有当 k 是「完全平方数」时，它才会有奇数个约数，否则一定有偶数个约数。

```java
class Solution {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n + 0.5);
    }
}
```



## [352. 将数据流变为多个不相交区间](https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/)

 给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。

实现 SummaryRanges 类：

SummaryRanges() 使用一个空数据流初始化对象。
void addNum(int val) 向数据流中加入整数 val 。
int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。

```java
输入：
["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
[[], [1], [], [3], [], [7], [], [2], [], [6], []]
输出：
[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]

解释：
SummaryRanges summaryRanges = new SummaryRanges();
summaryRanges.addNum(1);      // arr = [1]
summaryRanges.getIntervals(); // 返回 [[1, 1]]
summaryRanges.addNum(3);      // arr = [1, 3]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
summaryRanges.addNum(7);      // arr = [1, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
```

---

解题思路:

- 分类讨论,每次插入,对每个区间的进行判断,总共五种情况:
  - 区间绝对包含 区间不变
  - 插入后 左边包含 左边-1
  - 插入后 右边包含 右边+1
  - 插入后合并 两个合并
  - 插入后单独一组

```java
class SummaryRanges {
    // 并不需要存储所有值,只需要存储区间
    TreeMap<Integer, Integer> section;
    public SummaryRanges() {
        section = new TreeMap<>();
    }
    public void addNum(int val) {
        if (section.isEmpty()) {
            section.put(val, val);
            return;
        }
        Integer left = 0;
        Integer right = 0;
        Integer preLeft = Integer.MAX_VALUE - 1;
        Integer preRight = Integer.MAX_VALUE - 1;
        for (Map.Entry<Integer, Integer> en : section.entrySet()) {
            left = en.getKey();
            right = en.getValue();
            // 包含
            if (val <= right && val >= left) {
                return;
            }
            //与前一个区间合并
            if (val == left - 1 && val == preRight + 1) {
                section.remove(left);
                section.replace(preLeft, right);
                return;
            }
            //添加在当前的前面
            if (val == left - 1) {
                section.remove(left);
                section.put(val, right);
                return;
            }
            // 添加在前面的后面
            if (val == preRight + 1) {
                section.replace(preLeft, val);
                return;
            }
            if (val < left && val > preRight) {
                section.put(val, val);
                return;
            }
            preLeft = left;
            preRight = right;
        }
        if (val == right + 1) {
            section.replace(left, val);
            return;
        }
        section.put(val, val);
    }
    public int[][] getIntervals() {
        int[][] ans = new int[section.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> en : section.entrySet()) {
            ans[i++] = new int[]{en.getKey(), en.getValue()};
        }
        return ans;
    }
}
/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
```

## [368. 最大整除子集](https://leetcode-cn.com/problems/largest-divisible-subset/)

给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
answer[i] % answer[j] == 0 ，或
answer[j] % answer[i] == 0
如果存在多个有效解子集，返回其中任何一个均可。

```
输入：nums = [1,2,3]
输出：[1,2]
解释：[1,3] 也会被视为正确答案。
输入：nums = [1,2,4,8]
输出：[1,2,4,8]
```

---

解题思路:

- 动态规划，

- 根据整除关系具有传递性，即如果 a|b，并且 b|c ，那么 a|c，可知：

  - 如果整数 a 是整除子集 $S_1$的最小整数 b 的约数（即 a|b），那么可以将 a 添加到 $S_1$中得到一个更大的整除子集；

  - ==如果整数 c 是整除子集 $S_2$的最大整数 d 的倍数（即d|c），那么可以将 c 添加到 $S_2$中得到一个更大的整除子集。==

  我们需要将输入数组 nums 按照升序排序，以便获得一个子集的最小整数或者最大整数。又根据动态规划的「无后效性」状态设计准则，我们需要将状态定义成「某个元素必须选择」。

  状态定义：dp[i] 表示在输入数组 nums 升序排列的前提下，以 nums[i] 为最大整数的「整除子集」的大小（在这种定义下 nums[i] 必须被选择）。

  状态转移方程：枚举 j=0…i−1 的所有整数 nums[j]，如果 nums[j] 能整除 nums[i]，说明 nums[i] 可以扩充在以 nums[j] 为最大整数的整除子集里成为一个更大的整除子集。

  - 保存路径有个技巧，每次在状态转移时，记录转移的地方，保存在数组中，最后从最大值点往回找，和并查集找祖先节点一致。

  ```java
  class Solution {
      public List<Integer> largestDivisibleSubset(int[] nums) {
          int[] dp = new int[nums.length];
          int[] path = new int[nums.length];
          int idx = 0;
          int max = 1;
          Arrays.sort(nums);
          dp[0] = 1;
          path[0] = 0;
          for (int i = 1; i < nums.length; i++) {
              int len = 1;
              int prev = i;
              for (int j = i - 1; j >= 0; j--) {
                  if (nums[i] % nums[j] == 0) {
                      if (dp[j] + 1 > len) {
                          prev = j;
                          len = dp[j] + 1;
                      }
                  }
              }
              path[i] = prev;
              dp[i] = len;
              if (len > max) {
                  idx = i;
                  max = len;
              }
          }
          List<Integer> ans = new ArrayList<>();
          while (ans.size() != max) {
              ans.add(0, nums[idx]);
              idx = path[idx];
          }
          return ans;
      }
  }
  ```

  

## [384. 打乱数组](https://leetcode-cn.com/problems/shuffle-an-array/)

给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。

实现 Solution class:

Solution(int[] nums) 使用整数数组 nums 初始化对象
int[] reset() 重设数组到它的初始状态并返回
int[] shuffle() 返回数组随机打乱后的结果

```java
输入
["Solution", "shuffle", "reset", "shuffle"]
[[[1, 2, 3]], [], [], []]
输出
[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]

解释
Solution solution = new Solution([1, 2, 3]);
solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
```

---

解题思路:

- 待乱序的数组长度为 n , 随机生成 0~n 中的数 k, 将第 k 个元素与数组的最后 1 个元素交换，这样我们只需要 O(1) 的时间复杂度即可完成第 k 个元素的操作。此时，被交换后数组的最后 1 个元素即为我们根据随机下标获取的元素。再进行 0~n-1 的下标交换,以此类推。

```java
class Solution {
        int[] array;
        public Solution(int[] nums) {
            array = Arrays.copyOf(nums, nums.length);
        }
        public int[] reset() {
            int[] ans = Arrays.copyOf(array, array.length);
            return ans;
        }
        public int[] shuffle() {
            int n = array.length;
            Random random = new Random();
            int[] ans = Arrays.copyOf(array, n);
            for (int i = 0; i < n; i++) {
                //greater than or equal to {0.0} and less than {1.0}.
                // 不会等于1,所以会少最后一个位置.
                //int idx = (int) (Math.random() * (n - i - 1));
                int idx =random.nextInt(n - i);
                int temp = ans[n - i - 1];
                ans[n - i - 1] = ans[idx];
                ans[idx] = temp;
            }
            return ans;
        }
    }
```



## [397. 整数替换](https://leetcode-cn.com/problems/integer-replacement/)

给定一个正整数 n ，你可以做如下操作：

如果 n 是偶数，则用 n / 2替换 n 。
如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
n 变为 1 所需的最小替换次数是多少？

```
输入：n = 7
输出：4
解释：7 -> 8 -> 4 -> 2 -> 1
或 7 -> 6 -> 3 -> 2 -> 1
```

---

解题思路:

- 二进制。
  - 偶数时只能进行 /2 操作。
  - 奇数时，如果尾部存在连续的 1 ，那么 +1 可以产生进位，连续生成尾部 0 ，从而加速缩小数字。但需要注意边界 x = 3 时的情况（此时选择 -1 操作）。

```java
class Solution {
    public int integerReplacement(int n) {
        int ans = 0;
        //于 x 为奇数所能执行的两种操作，+1 能够消除连续一段的 1，
        // 只要次低位为 1（存在连续段），应当优先使用 +1 操作，
        // 但需要注意边界 x = 3 时的情况（此时选择 -1 操作）。
        //
        long _n = n;
        while (_n != 1) {
            if ((_n & 1) == 0)  _n >>= 1;
            else {
                if (_n != 3 && ((_n >> 1) & 1) == 1) _n++;
                else _n--;
            }
            ans++;
        }
        return ans;
    }
}
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

## [496. 下一个更大元素 I](https://leetcode-cn.com/problems/next-greater-element-i/)

给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。

请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。

nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。

```java
输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
输出: [-1,3,-1]
解释:
    对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
    对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
    对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
```

---

解题思路:

- 暴力。

- 单调栈+hashMap。

  只需要求nums2中所有元素的右边第一个大于它本身的数即可。单调栈求第一个数，hashMap保存下标。

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = nums2.length - 1; i >= 0; --i) {
            int num = nums2[i];
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.get(nums1[i]);
        }
        return res;
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

## [519. 随机翻转矩阵](https://leetcode-cn.com/problems/random-flip-matrix/)

给你一个 m x n 的二元矩阵 matrix ，且所有值被初始化为 0 。请你设计一个算法，随机选取一个满足 matrix[i][j] == 0 的下标 (i, j) ，并将它的值变为 1 。所有满足 matrix[i][j] == 0 的下标 (i, j) 被选取的概率应当均等。

尽量最少调用内置的随机函数，并且优化时间和空间复杂度。

```java
实现 Solution 类：
Solution(int m, int n) 使用二元矩阵的大小 m 和 n 初始化该对象
int[] flip() 返回一个满足 matrix[i][j] == 0 的随机下标 [i, j] ，并将其对应格子中的值变为 1
void reset() 将矩阵中所有的值重置为 0
```

---

解题思路:

- 二维映射一维，加哈希表记录访问过的点。
  - 未访问过就直接使用
  - 访问过就用最后一个点

```java
class Solution {
        int m;
        int n;
        int cnt;// cnt 为剩余数个数，同时 cnt - 1 为区间右端点位置
        Map<Integer, Integer> map = new HashMap<>();
        Random random = new Random(300);

        public Solution(int m, int n) {
            this.m = m;
            this.n = n;
            this.cnt = m * n;
        }

        public int[] flip() {
            int x = random.nextInt(cnt--);// 在 0 ~ cnt-1中生成一个随机数
            // 判断是否这个点访问过
            // · 未访问过的话直接使用
            // · 访问就用最后一点，然后把最后一点更新
            int idx = map.getOrDefault(x, x);
            map.put(x, map.getOrDefault(cnt, cnt));
            return new int[]{idx / n, idx % n};
        }

        public void reset() {
            map.clear();
            cnt = m * n;
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

## [563. 二叉树的坡度](https://leetcode-cn.com/problems/binary-tree-tilt/)

给定一个二叉树，计算 整个树 的坡度 。

一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。

整个树 的坡度就是其所有节点的坡度之和。

<img src="asset/Daily%20Challenge.assets/tilt2.jpg" alt="img" style="zoom: 50%;" />

```java
输入：root = [4,2,9,3,5,null,7]
输出：15
解释：
节点 3 的坡度：|0-0| = 0（没有子节点）
节点 5 的坡度：|0-0| = 0（没有子节点）
节点 7 的坡度：|0-0| = 0（没有子节点）
节点 2 的坡度：|3-5| = 2（左子树就是左子节点，所以和是 3 ；右子树就是右子节点，所以和是 5 ）
节点 9 的坡度：|0-7| = 7（没有左子树，所以和是 0 ；右子树正好是右子节点，所以和是 7 ）
节点 4 的坡度：|(3+5+2)-(9+7)| = |10-16| = 6（左子树值为 3、5 和 2 ，和是 10 ；右子树值为 9 和 7 ，和是 16 ）
坡度总和：0 + 0 + 0 + 2 + 7 + 6 = 15
```

---

解题思路:

- 深度有限遍历即可，使用全局变量计算总结果。

```java
class Solution {
    public int ans = 0;
    public int findTilt(TreeNode root) {
        ans = 0;
        sum(root);
        return ans;
    }
    public int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 0;
        if (root.left != null) {
            left = root.left.val + sum(root.left);
        }
        int right = 0;
        if (root.right != null) {
            right = root.right.val + sum(root.right);
        }
        int val = left + right;
        root.val = Math.abs(left - right);
        ans += root.val;
        return val;
    }
}
```





## [575. 分糖果](https://leetcode-cn.com/problems/distribute-candies/)

给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。

示例 1:

输入: candies = [1,1,2,2,3,3]
输出: 3
解析: 一共有三种种类的糖果，每一种都有两个。
     最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。

---

解题思路:

- 数组实现`HashSet`。

```java
class Solution {
    public int distributeCandies(int[] candyType) {
        int N = candyType.length >> 1;
        int type = 0;
        boolean[] flag = new boolean[200001];
        for (int i : candyType) {
            if (!flag[i + 100000]) type++;
            flag[i + 100000] = true;
        }
        return type < N ? type : N;
    }
}
```

## [630. 课程表 III](https://leetcode-cn.com/problems/course-schedule-iii/)

这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。

你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。

返回你最多可以修读的课程数目。

```java
输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
输出：3
解释：
这里一共有 4 门课程，但是你最多可以修 3 门：
首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
输入：courses = [[3,2],[4,3]]
输出：0
```

---

解题思路:

- 贪心+优先级队列。

  - 首先贪心，将courses==按照结束时间增序排列==，处理过程中维护一个总时长 beg(也是下一个课程开始的时间,也是已选课程的总时间)，对于某个课程 courses[i] 而言，根据如果学习该课程，是否满足「最晚完成时间」要求进行分情况讨论：

    - 学习该课程后，满足「最晚完成时间」要求，即 ==sum + courses\[i][0] <= courses\[i][1]==，则进行学习；

    - 学习该课程后，不满足「最晚完成时间」要求，此时==从过往学习的课程中找出「持续时间」最长的课程进行「回退」(删除)操作==（这个持续时长最长的课程有可能是当前课程）。

  - 为什么回退时间最长的课程可以保证总的课程数量最多呢?

    - 1.在课程数量相同的前提下，该做法得到的排列总耗时最少。
    - 2.在1满足时,该做法能够确保取得最大课程数量。

```java
class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (o1, o2) -> o1[1] - o2[1]);
        PriorityQueue<int[]> learned = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        int beg = 0;
        for (int[] cours : courses) {
            learned.offer(cours);
            beg += cours[0];
            if (beg  > cours[1]) {
                // 不满足时间约束，回退，删除选择的课程中时长最长的课程
                beg -= learned.poll()[0];
            } 
        }
        return learned.size();
    }
}
```









## [689. 三个无重叠子数组的最大和](https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/)

给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。

以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。

```java
输入：nums = [1,2,1,2,6,7,5,1], k = 2
输出：[0,3,5]
解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
```

---

解题思路:

- 动态规划。

  只需要使用动态规划求解即可：定义 f\[i][j] 为考虑前 i 个数，凑成无重叠子数组数量为 j 个时的最大值。最终答案为 f\[n - 1][3]

  不失一般性的考虑 f\[i][j] 该如何计算（以最优方案是否包含 nums[i] 进行讨论）：

  - 最优方案中包含 num[i]：由于这 j 个无重叠，因此前面的 j - 1 个子数组不能覆盖 [i−k+1,i]。即只能在 \[0, i - k] 中选 j - 1 个子数组。此时有：$f[i][j] = f[i - k][j - 1] + \sum_{idx = i - k + 1}^{i} nums[idx]$其中求解 $\sum_{idx = i - k + 1}^{i} $nums[idx] 部分可以使用「前缀和」优化

  - 最优方案不包含 num[i]：当明确了 nums[i]nums[i] 对最优方案无贡献，此时问题等价于考虑前 i - 1i−1 个数，凑成 jj 个不重叠子数组的最大值。此时有：f\[i][j] = f\[i - 1][j]

  最终 f\[i][j]为上述两种方案中的最大值。通过判断 f\[n - 1][3] 等于 f\[n - 2][3] 还是 $f[n - 1 - k][2] + \sum_{idx = n - k }^{n - 1} nums[idx]$来决定回溯点为何值。

```java
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        long[] pre = new long[nums.length + 1];
        reverse(nums);
        for (int i = 0; i < nums.length; i++) {
            pre[i + 1] += pre[i] + nums[i];
        }
        long[][] dp = new long[nums.length + 1][4];
        for (int i = k; i <= nums.length; i++) {
            for (int j = 1; j < 4; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - k][j - 1] + (pre[i] - pre[i - k]));
            }
        }
        int[] ans = new int[3];
        int j = 3;
        int i = nums.length;
        while (j > 0) {
            if (dp[i - 1][j] > dp[i - k][j - 1] + (pre[i] - pre[i - k])) {
                i--;
            } else {
                ans[--j] = nums.length - i;
                i -= k;
            }
        }
        reverse(ans);
        return ans;
    }
    void reverse(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int c = nums[l];
            nums[l++] = nums[r];
            nums[r--] = c;
        }
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

## [740. 删除并获得点数](https://leetcode-cn.com/problems/delete-and-earn/)

给你一个整数数组 nums ，你可以对它进行一些操作。

每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。

开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。

```java
输入：nums = [2,2,3,3,3,4]
输出：9
解释：
删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
总共获得 9 个点数。
```

---

解题思路:

- 动态规划: 如果值是连续的，则就转换成了 ==打家劫舍== 问题，状态方程定义为 dp[i]表示 nums[0~i] 所能得到的最大点数，状态转移方程就为$dp[i] = Max(nums[i] + dp[i-2],dp[i-1])$.
  - 那么如何将其变成连续值,只需要观察范围并找到最大值,开一个长度为此最大值的数组val,数组里保存当前值出现了几次,算得分只需要==i*val[i];==

```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        int[] val = new int[maxVal + 1];
        for (int i = 0; i < nums.length; i++) {
            val[nums[i]]++;
        }
        int[] dp = new int[val.length];
        int ans = val[1];
        dp[1] = val[1];
        for (int i = 2; i < val.length; i++) {
            dp[i] = Math.max(i * val[i] + dp[i - 2], dp[i - 1]);
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }
}
```











## [794. 有效的井字游戏](https://leetcode-cn.com/problems/valid-tic-tac-toe-state/)

给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。

井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。

以下是井字游戏的规则：

```
玩家轮流将字符放入空位（' '）中。
玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
当所有位置非空时，也算为游戏结束。
如果游戏结束，玩家不允许再放置字符。
```

<img src="asset/Daily%20Challenge.assets/tictactoe3-grid.jpg" alt="img" style="zoom:33%;" />

```
输入：board = ["XXX","   ","OOO"]
输出：false
```

---

解题思路:

- 找规律，寻找棋盘不合法的情况。
  - |x| - |o| >1  ，不合法
  - X 赢时，|x| - |o| != 1， 不合法
  - O 赢时, |x| - |o| ! = 0,不合法

```java
class Solution {
    public boolean validTicTacToe(String[] board) {
        char[][] map = new char[3][3];

        for (int i = 0; i < 3; i++) {
            map[i] = board[i].toCharArray();
        }
        int numX = 0;
        int numO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 'X') {
                    numX++;
                } else if (map[i][j] == 'O') {
                    numO++;
                }
            }
        }

        if (numX - numO > 1 || numO - numX >= 1) {
            return false;
        }
        if (judge(map, 'X') && numX - numO != 1) {
            return false;
        }
        if (judge(map, 'O') && numX - numO != 0) {
            return false;
        }
        return true;
    }
    public boolean judge(char[][] board, char pin) {
        for (int i = 0; i < 3; i++) {
            int colN = 0;
            int rowN = 0;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == pin) {
                    colN++;
                }
                if (board[j][i] == pin) {
                    rowN++;
                }
            }
            if (colN == 3 || rowN == 3) {
                return true;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == pin) {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] == pin) {
            return true;
        }
        return false;
    }
}
```

## [798. 得分最高的最小轮调](https://leetcode-cn.com/problems/smallest-rotation-with-highest-score/)

给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，这样可以使数组变为 [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]] 的形式。此后，任何值小于或等于其索引的项都可以记作一分。

例如，数组为 nums = [2,4,1,3,0]，我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。

```java
输入：nums = [2,3,1,4,0]
输出：3
解释：
下面列出了每个 k 的得分：
k = 0,  nums = [2,3,1,4,0],    score 2
k = 1,  nums = [3,1,4,0,2],    score 3
k = 2,  nums = [1,4,0,2,3],    score 3
k = 3,  nums = [4,0,2,3,1],    score 4
k = 4,  nums = [0,2,3,1,4],    score 3
所以我们应当选择 k = 3，得分最高。
1 <= nums.length <= 105
0 <= nums[i] < nums.length
```

---

解题思路:

- 预处理 + 差分 + 前缀和。

  由于 0 <= nums[i] < nums.length ,k的取值范围为[0,n-1],第i个元素轮转后的下标为 (i-k+n)%n

  解题的关键是求 `每个元素` 可以加一分时`k的取值范围`。

  假设元素 x 的初始下标为 i。
  当 i < x 时应将 points 的下标范围 [i+1,i−x+n] 内的所有元素加 1，
  当 i ≥ x 时应将 points 的下标范围 [0,i+1] 和 [i−x,n−1] 内的所有元素加 1。

  使用差分统计每个k的得分，然后前缀和求最大的分对应的k。

```java
class Solution {
    public int bestRotation(int[] nums) {
        // 0 <= nums[i] < nums.length
        int n = nums.length;
        // 下标为 i 的元素 进行 k 论调后的下标为 (i - k + n) % n
        //  points[k] 表示轮调下标为 k 时的得分
        // 用差分数组保存
        int[] points = new int[n];
        // 对于数组 nums 中的每个元素，得到该元素记 1 分的轮调下标范围，
        // 然后将数组 points 的该下标范围内的所有元素加 1。

        //假设元素 x 的初始下标为 i。
        // 当 i < x 时应将 points 的下标范围 [i+1,i−x+n] 内的所有元素加 1，
        // 当 i ≥ x 时应将 points 的下标范围 [0,i+1] 和 [i−x,n−1] 内的所有元素加 1。
        for (int i = 0; i < n; i++) {
            int low = (i + 1) % n;
            // +1 配合差分
            int high = (i - nums[i] + n + 1) % n;
            points[low]++;
            points[high]--;
            // 差分首项
            if (low >= high) {
                points[0]++;
            }
        }
        // 算最高分
        int ans = 0;
        int max = 0;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            if (points[i] + pre > max) {
                max = points[i] + pre;
                ans = i;
            }
            pre = points[i] + pre;
        }
        return ans;
    }
}
```



## [869. 重新排序得到 2 的幂](https://leetcode-cn.com/problems/reordered-power-of-2/)

给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。

如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。

```java
输入：46
输出：true
输入：16
输出：true
输入：10
输出：false
```

---

解题思路:

- 预处理所有2的次幂+变位词验证。

```java
class Solution {
    public boolean reorderedPowerOf2(int n) {
        // 判断一个数 是不是二的整数幂
        // x & (-x) == x
        if ((n & (-n)) == n) {
            return true;
        }
        long[] table = new long[32];
        for (int i = 0; i < 32; i++) {
            table[i] = (long) 1 << i;
        }
        String string = Integer.toString(n);
        int len = string.length();
        for (int i = 0; i < 32; i++) {
            String str = Long.toString(table[i]);
            if (str.length() < len) continue;
            if (str.length() > len) break;
            int[] flag = new int[10];
            for (char ch : string.toCharArray()) {
                flag[ch - '0']++;
            }
            for (char ch : str.toCharArray()) {
                flag[ch - '0']--;
            }
            boolean ans = true;
            for (int f : flag) {
                if (f != 0) {
                    ans = false;
                }
            }
            if (ans) {
                return true;
            }
        }
        return false;
    }
}
```

## [1034. 边界着色](https://leetcode-cn.com/problems/coloring-a-border/)

给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。

当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。

连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。

请你使用指定颜色 color 为所有包含网格块 grid\[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。

```
输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
输出：[[2,2,2],[2,1,2],[2,2,2]]
```

---

解题思路:

- DFS，找到联通分量，然后判断是否为边界，标记-1，然后再判断是否四周环绕，不是标记-1，是标记-2，这样就可以将整个图划分为三部分，负数为整个联通分量，内部-1表示边界，-2表示四周都环绕的，不改色。

```java
class Solution {
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        if (grid[row][col] == color) {
            return grid;
        }
        int temp = grid[row][col];
        DFS(grid, row, col, grid[row][col], color);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == -1) {
                    grid[i][j] = color;
                }
                if (grid[i][j] == -2) {
                    grid[i][j] = temp;
                }
            }
        }
        return grid;
    }
    public void DFS(int[][] grid, int i, int j, int paint, int color) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == -1 || grid[i][j] != paint || grid[i][j] == -2) {
            return;
        }
        // 数组边界
        if (i - 1 < 0 || i + 1 >= grid.length || j - 1 < 0 || j + 1 >= grid[0].length) {
            grid[i][j] = -1;
            // 联通分量边界
        } else if ((grid[i - 1][j] != paint && grid[i - 1][j] != -1 && grid[i - 1][j] != -2) ||
                (grid[i + 1][j] != paint && grid[i + 1][j] != -1 && grid[i + 1][j] != -2) ||
                (grid[i][j - 1] != paint && grid[i][j - 1] != -1 && grid[i][j - 1] != -2) ||
                (grid[i][j + 1] != paint && grid[i][j + 1] != -1 && grid[i][j + 1] != -2)) {
            grid[i][j] = -1;
        } else {
            // 被访问 且 为内部
            grid[i][j] = -2;
        }
        DFS(grid, i - 1, j, paint, color);
        DFS(grid, i + 1, j, paint, color);
        DFS(grid, i, j - 1, paint, color);
        DFS(grid, i, j + 1, paint, color);
        
    }
}
```

## [1044. 最长重复子串](https://leetcode-cn.com/problems/longest-duplicate-substring/)

给你一个字符串 s ，考虑其所有 重复子串 ：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。

返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。

```java
示例 1：
输入：s = "banana"
输出："ana"
```

---

解题思路:

- 二分+字符串Hash

  - 题目要求得「能取得最大长度的任一方案」，首先以「最大长度」为分割点的数轴具有「二段性」：

    - 小于等于最大长度方案均存在（考虑在最大长度方案上做删减）；
    - 大于最大长度的方案不存在。

    二分范围为[0,n]，关键在于如何 check 函数，即实现「检查某个长度 lenlen 作为最大长度，是否存在合法方案」。

  - 查重方法采用 字符串hash

```java
class Solution {
    public static long[] h = new long[3 * 10000 + 10];
    public static long[] p = new long[3 * 10000 + 10];
    public String longestDupSubstring(String s) {
        // 哈希数组
        int P = 131313;
        // 次方数组
        p[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            // 哈希函数 自然溢出
            h[i] = h[i - 1] * P + s.charAt(i - 1);
            // 记录前缀，用以还原字符串
            p[i] = p[i - 1] * P;
        }

        int l = 0, r = s.length();
        String ans = "";
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            String t = check(s, mid);
            if (t.equals("")) r = mid - 1;
            else l = mid+1;
            ans = t.length() > ans.length() ? t : ans;
        }
        return ans;
    }
    public String check(String str, int len) {
        int n = str.length();
        HashSet<Long> set = new HashSet<>();
        for (int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            long hash = h[j] - h[i - 1] * p[len];
            if (set.contains(hash)) return str.substring(i-1, j);
            set.add(hash);
        }
        return "";
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

## [1218. 最长定差子序列](https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference/)

给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。

子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。

```java
输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
输出：4
解释：最长的等差子序列是 [7,5,3,1]。
```

---

解题思路:

- 动态规划。 dp[i]表示以arr[i]结尾的子序列长度。 再用个HashMap保存 arr[i] - differ 的长度,这样计算当前dp[i] = 1 + dp[arr[i]-differ]。

```java
class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        int max = 1;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int beg = arr[i];
            int pre = beg - difference;
            int len = hashMap.getOrDefault(pre, 0) + 1;
            hashMap.put(beg, len);
            max = len > max ? len : max;
        }
        return max;
    }
}
```

## [1219. 黄金矿工](https://leetcode-cn.com/problems/path-with-maximum-gold/)

你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。

为了使收益最大化，矿工需要按以下规则来开采黄金：

每当矿工进入一个单元，就会收集该单元格中的所有黄金。
矿工每次可以从当前位置向上下左右四个方向走。
每个单元格只能被开采（进入）一次。
不得开采（进入）黄金数目为 0 的单元格。
矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。

```java
输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
输出：24
解释：
[[0,6,0],
 [5,8,7],
 [0,9,0]]
一种收集最多黄金的路线是：9 -> 8 -> 7。
```

---

解题思路：

- 回溯，每个不为0的点出发找路。

```java
class Solution {
    public  int max = 0;
    public int getMaximumGold(int[][] grid) {
        max = 0;
         int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != 0) {
                    dfs(grid, i, j, 0);
                }
            }
        }
        return max;
    }
    // 回溯
    public  void dfs(int[][] grid, int i, int j, int ans) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            max = Math.max(max, ans);
            return;
        }
        int temp = grid[i][j];
        grid[i][j] = 0;
        dfs(grid, i + 1, j, ans + temp);
        dfs(grid, i - 1, j, ans + temp);
        dfs(grid, i, j + 1, ans + temp);
        dfs(grid, i, j - 1, ans + temp);
        grid[i][j] = temp;
    }
}
```





## [1277. 统计全为 1 的正方形子矩阵](https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/)

给你一个 `m * n` 的矩阵，矩阵中的元素不是 `0` 就是 `1`，请你统计并返回其中完全由 `1` 组成的 **正方形** 子矩阵的个数。

```java
输入：matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
输出：15
解释： 
边长为 1 的正方形有 10 个。
边长为 2 的正方形有 4 个。
边长为 3 的正方形有 1 个。
正方形的总数 = 10 + 4 + 1 = 15.
```

---

解题思路:

- dp。我们用 `f[i][j]` 表示以 `(i, j)` 为右下角的正方形的最大边长。

  *f*\[*i*][*j*]=min( *f*\[*i*][*j*−1],*f*\[*i*− 1][*j*],*f*\[*i*−1][*j*−1])+1；

```java
class Solution {
    public int countSquares(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            if (matrix[i][0] == 1) {
                dp[i][0] = 1;
                ans++;
            }
        }
        for (int i = 1; i < M; i++) {
            if (matrix[0][i] == 1) {
                dp[0][i] = 1;
                ans++;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    ans += dp[i][j];
                }
            }
        }
        return ans;
    }
}
```

## [1405. 最长快乐字符串](https://leetcode-cn.com/problems/longest-happy-string/)

如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。

给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：

s 是一个尽可能长的快乐字符串。
s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
s 中只含有 'a'、'b' 、'c' 三种字母。
如果不存在这样的字符串 s ，请返回一个空字符串 ""。

```
输入：a = 7, b = 1, c = 0
输出："aabaa"
解释：这是该测试用例的唯一正确答案。
输入：a = 1, b = 1, c = 7
输出："ccaccbcc"
解释："ccbccacc" 也是一种正确答案。
```

---

解题思路：

- 贪心算法 + 优先队列

  每次添加剩余数量最多的字符，如果添加当前字符会和3个连续，那么添加第二多的字符（这个字符肯定和前面的字符不一样），这样就满足条件了。

```java
class Solution {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder ans = new StringBuilder("");
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        if (a > 0) q.add(new int[]{'a', a});
        if (b > 0) q.add(new int[]{'b', b});
        if (c > 0) q.add(new int[]{'c', c});
        while (!q.isEmpty()) {
            // 一次添加一个字符
            int[] top = q.poll();
            int n = ans.length();
            if (n >= 2 && (ans.charAt(n - 1) == ans.charAt(n - 2)) && (ans.charAt(n - 1) == (char) top[0])) {
                if (q.isEmpty()) break;
                int[] cur = q.poll();
                ans.append((char) cur[0]);
                cur[1]--;
                if (cur[1] != 0) q.add(cur);
                q.add(top);
            } else {
                ans.append((char) top[0]);
                top[1]--;
                if (top[1] != 0) q.add(top);
            }
        }
        return ans.toString();
    }
}
```





## [1610. 可见点的最大数目](https://leetcode-cn.com/problems/maximum-number-of-visible-points/)

给你一个点数组 points 和一个表示角度的整数 angle ，你的位置是 location ，其中 location = [posx, posy] 且 points[i] = [xi, yi] 都表示 X-Y 平面上的整数坐标。

最开始，你面向东方进行观测。你 不能 进行移动改变位置，但可以通过 自转 调整观测角度。换句话说，posx 和 posy 不能改变。你的视野范围的角度用 angle 表示， 这决定了你观测任意方向时可以多宽。设 d 为你逆时针自转旋转的度数，那么你的视野就是角度范围 [d - angle/2, d + angle/2] 所指示的那片区域。

对于每个点，如果由该点、你的位置以及从你的位置直接向东的方向形成的角度 位于你的视野中 ，那么你就可以看到它。

同一个坐标上可以有多个点。你所在的位置也可能存在一些点，但不管你的怎么旋转，总是可以看到这些点。同时，点不会阻碍你看到其他点。

返回你能看到的点的最大数目。

<img src="asset/Daily%20Challenge.assets/89a07e9b-00ab-4967-976a-c723b2aa8656.png" alt="img" style="zoom:67%;" />

```java
输入：points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
输出：3
解释：阴影区域代表你的视野。在你的视野中，所有的点都清晰可见，尽管 [2,2] 和 [3,3]在同一条直线上，你仍然可以看到 [3,3] 。
```

---

解题思路:

- 难点在于角度和表示和范围寻找。

  - 角的表示，可以用 ==arctan(dy/dx)== 算出角度的弧度值,然后再乘 angle*180\*π转换为角度。

  - 对这个角度进行排序，然后滑动窗口形式的扫描最大窗口即可。

    - 因为收尾相接可能需要循环扫描，可以将数组进行拓展为原来的2倍，然后线性扫描即可得到首尾相接的结果。

    - ```java
      // 因为360是循环的,自转可以顺时针和逆时针两个方向转
      // 所以 将角度拓展为两倍, 遍历一次即可
      // [1   2   3   4]   循环搜索就可以用常规线性搜索
      // [1   2   3   4   1   2   3   4]
      ```

```java
class Solution {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int n = points.size();
        int pox = location.get(0);
        int poy = location.get(1);
        int sameCnt = 0;
        ArrayList<Double> ang = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = points.get(i).get(0);
            int y = points.get(i).get(1);
            if (x == pox && y == poy) {
                sameCnt++;
                continue;
            }
            ang.add(Math.atan2(y - poy, x - pox));
        }
        Collections.sort(ang);
        int m = ang.size();
        // 因为360是循环的,自转可以顺时针和逆时针两个方向转
        // 所以 将角度拓展为两倍, 遍历一次即可
        // [1   2   3   4]   循环搜索就可以用常规线性搜索
        // [1   2   3   4   1   2   3   4]
        for (int i = 0; i < m; i++) {
            ang.add(ang.get(i) + Math.PI * 2);
        }
        int ans = 0;
        int right = 0;
        // 将角度 π 形式转换为 度
        // π = 180
        double toDegree = angle * Math.PI / 180;
        for (int i = 0; i < m; ++i) {
            Double curr = ang.get(i) + toDegree;
            while (right < ang.size() && ang.get(right) <= curr) {
                right++;
            }
            ans = Math.max(ans, right - i);
        }
        return ans + sameCnt;
    }
}
```

## [1705. 吃苹果的最大数目](https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/)

有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。

你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。

给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。

```java
输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
输出：7
解释：你可以吃掉 7 个苹果：
- 第一天，你吃掉第一天长出来的苹果。
- 第二天，你吃掉一个第二天长出来的苹果。
- 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
- 第四天到第七天，你吃的都是第四天长出来的苹果。
```

---

解题思路:

- 优先级队列:
  - 每天吃马上坏的苹果,贪心达到最多。
  - 如果保存苹果剩余几天坏的话，需要在每次遍历后-1，所以直接算出将要坏的日期，当时间戳使用。这样后面不用再修改。

```java
class Solution {
    public int eatenApples(int[] apples, int[] days) {
       PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int n = apples.length;
        int time = 0;
        int ans = 0;
        while (time < n || !queue.isEmpty()) {
            if (time < n && days[time] != 0 && apples[time] != 0)
                queue.offer(new int[]{apples[time], days[time] + time - 1});
            while (!queue.isEmpty() && queue.peek()[1] < time) queue.poll();
            if (!queue.isEmpty()){
                int[] now = queue.poll();
                if (--now[0] > 0 && now[1] > time) queue.offer(now);
                ans++;
            }
            time++;
        }
        return ans;
    }
}
```

## [1765. 地图中的最高点](https://leetcode-cn.com/problems/map-of-highest-peak/)

给你一个大小为 m x n 的整数矩阵 isWater ，它代表了一个由 陆地 和 水域 单元格组成的地图。

如果 isWater[i][j] == 0 ，格子 (i, j) 是一个 陆地 格子。
如果 isWater[i][j] == 1 ，格子 (i, j) 是一个 水域 格子。
你需要按照如下规则给每个单元格安排高度：

每个格子的高度都必须是非负的。
如果一个格子是是 水域 ，那么它的高度必须为 0 。
任意相邻的格子高度差 至多 为 1 。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）
找到一种安排高度的方案，使得矩阵中的最高高度值 最大 。

请你返回一个大小为 m x n 的整数矩阵 height ，其中 height\[i][j] 是格子 (i, j) 的高度。如果有多种解法，请返回 任意一个 。

<img src="asset/Daily%20Challenge.assets/screenshot-2021-01-11-at-82045-am.png" alt="img" style="zoom:50%;" />

```java
输入：isWater = [[0,1],[0,0]]
输出：[[1,0],[2,1]]
解释：上图展示了给各个格子安排的高度。
蓝色格子是水域格，绿色格子是陆地格。
```

---

解题思路：

- 多源BFS

  多源BFS 即就是有多个起点的BFS 

  对于一个「陆地」区域（高度可变）而言，其所能填入的高度，取决于其距离其他「水域」区域的距离，而我们最终要让整个答案矩阵合法，因此==每个「陆地」区域应该取其所能填入的高度的「下界」，即只由「距离它最近的水域」区域所更新，这符合 BFS 的性质。==


```java
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int n = isWater.length;
        int m = isWater[0].length;
        int[][] ans = new int[n][m];
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isWater[i][j] == 1) queue.offer(new int[]{i, j});
                ans[i][j] = isWater[i][j] == 1 ? 0 : -1;
            }
        }
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            for (int i = 0; i < dirs.length; i++) {
                int dx = x + dirs[i][0], dy = y + dirs[i][1];
                if (dx < 0 || dx >= n || dy < 0 || dy >= m) continue;
                if (ans[dx][dy] != -1) continue;
                ans[dx][dy] = ans[x][y] + 1;
                queue.offer(new int[]{dx,dy});
            }
        }
        return ans;
    }
}
```

## [2055. 蜡烛之间的盘子](https://leetcode-cn.com/problems/plates-between-candles/)

给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。

同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti] （包含左右端点的字符）。对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。

比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。

```java
输入：s = "**|**|***|", queries = [[2,5],[5,9]]
输出：[2,3]
解释：

- queries[0] 有两个盘子在蜡烛之间。
- queries[1] 有三个盘子在蜡烛之间。
```

![ex-1](asset/Daily%20Challenge.assets/ex-1.png)

---

解题思路:

- 前缀和。
  1. 使用前缀和的方式统计数组中盘子的数量。
  2. 然后用两个数组分别标记离i点最近的左右蜡烛的位置。
  3. 最后只需要通过蜡烛的标记数组确定边界的蜡烛位置，然后通过前缀和计算盘子总数。

```java
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] plates = new int[n + 1];
        // lc 记录左侧最近的盘子
        // rc 记录右侧最近的盘子
        int[] lc = new int[n];
        int[] rc = new int[n];
        char[] chars = s.toCharArray();
        int l = -1, r = -1;
        for (int i = 0, j = n - 1; i < chars.length; i++, j--) {
            plates[i + 1] = plates[i] + (chars[i] == '*' ? 1 : 0);
            if (chars[i] == '|') l = i;
            if (chars[j] == '|') r = j;
            lc[i] = l;
            rc[j] = r;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            l = rc[queries[i][0]];
            r = lc[queries[i][1]];

            ans[i] = (l < r && l != -1 && r != -1) ? plates[r] - plates[l] : 0;
        }
        return ans;
    }
}
```



## [2171. 拿出最少数目的魔法豆](https://leetcode-cn.com/problems/removing-minimum-number-of-magic-beans/)

给你一个 正 整数数组 beans ，其中每个整数表示一个袋子里装的魔法豆的数目。

请你从每个袋子中 拿出 一些豆子（也可以 不拿出），使得剩下的 非空 袋子中（即 至少 还有 一颗 魔法豆的袋子）魔法豆的数目 相等 。一旦魔法豆从袋子中取出，你不能将它放到任何其他的袋子中。

请你返回你需要拿出魔法豆的 最少数目。

```java
输入：beans = [4,1,6,5]
输出：4
解释：
- 我们从有 1 个魔法豆的袋子中拿出 1 颗魔法豆。
  剩下袋子中魔法豆的数目为：[4,0,6,5]
- 然后我们从有 6 个魔法豆的袋子中拿出 2 个魔法豆。
  剩下袋子中魔法豆的数目为：[4,0,4,5]
- 然后我们从有 5 个魔法豆的袋子中拿出 1 个魔法豆。
  剩下袋子中魔法豆的数目为：[4,0,4,4]
总共拿出了 1 + 2 + 1 = 4 个魔法豆，剩下非空袋子中魔法豆的数目相等。
没有比取出 4 个魔法豆更少的方案。
```

---

解题思路：

- 先排序，我们会发现当k是beans数组里本来就有的数字时，拿走的魔法豆会更小，而当k=beans[i]的时候，下标小于i的所有袋子，全部拿走，下标大于i的袋子，拿走到只有bean[i]个豆子。所以剩下的豆子如图所示：

<img src="asset/Daily%20Challenge.assets/1644729173-kRSgig-f3c41be66e163ba713b10ad185bed53.jpg" alt="f3c41be66e163ba713b10ad185bed53.jpg" style="zoom: 33%;" />

```java
class Solution {
    public long minimumRemoval(int[] beans) {
        long ans = Long.MAX_VALUE;
        long total = 0;
        int n = beans.length;
        for (int i = 0; i < n; i++) {
            total += beans[i];
        }
        Arrays.sort(beans);
        for (int i = 0; i < n; i++) {
            long temp = 0;
            temp = total - (long)(n - i) * (long)beans[i];
            ans = Math.min(ans,temp);
        }
        return ans;
    }
}
```

