# LeetCode Interview

[TOC]

## [LCP 12. 小张刷题计划](https://leetcode-cn.com/problems/xiao-zhang-shua-ti-ji-hua/)

为了提高自己的代码能力，小张制定了 LeetCode 刷题计划，他选中了 LeetCode 题库中的 n 道题，编号从 0 到 n-1，并计划在 m 天内按照题目编号顺序刷完所有的题目（注意，小张不能用多天完成同一题）。

在小张刷题计划中，小张需要用 time[i] 的时间完成编号 i 的题目。此外，小张还可以使用场外求助功能，通过询问他的好朋友小杨题目的解法，可以省去该题的做题时间。为了防止“小张刷题计划”变成“小杨刷题计划”，小张每天最多使用一次求助。

我们定义 m 天中做题时间最多的一天耗时为 T（小杨完成的题目不计入做题总时间）。请你帮小张求出最小的 T是多少。

```java
输入：time = [1,2,3,3], m = 2
输出：3
解释：第一天小张完成前三题，其中第三题找小杨帮忙；第二天完成第四题，并且找小杨帮忙。这样做题时间最多的一天花费了 3 的时间，并且这个值是最小的。
```

---

解题思路:

- 二分查找。给定一个数组，将其划分成 *M* 份，使得每份元素之和最大值最小，每份可以任意减去其中一个元素。

  - 如果不考虑每份可以任意减去一个元素，就是一个经典的二分问题，具有单调最优的性质：如果最大值为 t 可以满足条件划分，那么最大值为 t+1 也可以。所以就直接二分最大值 t，找到最小满足条件的 t 即可。
  - 二分问题的关键要素:  ==具有单调最优的性质。==

```java
class Solution {
    public int minTime(int[] time, int m) {
        if (m >= time.length) {
            return 0;
        }
        int left = 0;
        int right = (int) 1e9;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(time, m, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    public boolean check(int[] time, int m, int T) {
        int maxT = 0, sum = 0, day = 1;
        for (int i = 0; i < time.length; i++) {
            sum += time[i];
            maxT = Math.max(maxT, time[i]);
            if (T < sum - maxT) {
                if (++day > m) return false;
                sum = time[i];
                maxT = time[i];
            }
        }
        return day <= m;
    }
}
```

## [LCS 03. 主题空间](https://leetcode-cn.com/problems/YesdPw/)

「以扣会友」线下活动所在场地由若干主题空间与走廊组成，场地的地图记作由一维字符串型数组 grid，字符串中仅包含 "0"～"5" 这 6 个字符。地图上每一个字符代表面积为 1 的区域，其中 "0" 表示走廊，其他字符表示主题空间。相同且连续（连续指上、下、左、右四个方向连接）的字符组成同一个主题空间。

假如整个 grid 区域的外侧均为走廊。请问，不与走廊直接相邻的主题空间的最大面积是多少？如果不存在这样的空间请返回 0。

```java
输入：grid = ["11111100000","21243101111","21224101221","11111101111"]
输出：3
解释：8 个主题空间中，有 5 个不与走廊相邻，面积分别为 3、1、1、1、2，最大面积为 3。
```

![image.png](asset/LeetCode%20Interview.assets/1613707985-KJyiXJ-image.png)

---

解题思路:

- DFS.只是在处理时要注意一下,包括标记访问,访问时桥廊的处理

```java
class Solution {
    public int ans;
    public int largestArea(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = grid[i].toCharArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '0') {
                    map[i][j] = '#';
                    if (i + 1 < map.length) DFS(map, i + 1, j, map[i + 1][j]);
                    if (i - 1 >= 0) DFS(map, i - 1, j, map[i - 1][j]);
                    if (j + 1 < map[0].length) DFS(map, i, j + 1, map[i][j + 1]);
                    if (j - 1 >= 0) DFS(map, i, j - 1, map[i][j - 1]);
                } else if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    char ch = map[i][j];
                    map[i][j] = '#';
                    if (i + 1 < map.length) DFS(map, i + 1, j, ch);
                    if (i - 1 >= 0) DFS(map, i - 1, j, ch);
                    if (j + 1 < map[0].length) DFS(map, i, j + 1, ch);
                    if (j - 1 >= 0) DFS(map, i, j - 1, ch);
                }
            }
        }

        ans = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (map[i][j] != '#') {
                    ans = Math.max(count(map, i, j, map[i][j]), ans);
                }
            }
        }
        return ans;
    }
    public void DFS(char[][] map, int i, int j, char num) {
        if (i < 0 || i >= map.length || j < 0 || j >= map[0].length || map[i][j] == '#') {
            return;
        }
        if (map[i][j] == '0') {
            map[i][j] = '#';
            if (i + 1 < map.length) DFS(map, i + 1, j, map[i + 1][j]);
            if (i - 1 >= 0) DFS(map, i - 1, j, map[i - 1][j]);
            if (j + 1 < map[0].length) DFS(map, i, j + 1, map[i][j + 1]);
            if (j - 1 >= 0) DFS(map, i, j - 1, map[i][j - 1]);
        }else if (map[i][j] == num){
            map[i][j] = '#';
            if (i + 1 < map.length) DFS(map, i + 1, j, num);
            if (i - 1 >= 0) DFS(map, i - 1, j, num);
            if (j + 1 < map[0].length) DFS(map, i, j + 1, num);
            if (j - 1 >= 0) DFS(map, i, j - 1, num);
        }
    }

    public int count(char[][] map, int i, int j, char num) {
        if (i < 0 || i >= map.length || j < 0 || j >= map[0].length || map[i][j] != num) {
            return 0;
        }
        map[i][j] = '#';
        int area = 1;
        if (i + 1 < map.length) area += count(map, i + 1, j, num);
        if (i - 1 >= 0) area += count(map, i - 1, j, num);
        if (j + 1 < map[0].length) area += count(map, i, j + 1, num);
        if (j - 1 >= 0) area += count(map, i, j - 1, num);
        return area;
    }
}
```







## [面试题 01.03. URL化](https://leetcode-cn.com/problems/string-to-url-lcci/)

URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）

```
输入："Mr John Smith    ", 13
输出："Mr%20John%20Smith"
```

---

解题思路:

- 从后往前统计，双指针进行操作。

```java
class Solution {
    public String replaceSpaces(String S, int length) {
        char[] str = S.toCharArray();
        int i = length - 1;
        int j = S.length() - 1;
        while (i >= 0) {
            if (str[i] == ' ') {
                str[j--] = '0';
                str[j--] = '2';
                str[j--] = '%';
            } else {
                str[j--] = str[i];
            }
            i--;
        }
        return String.valueOf(str, j + 1, S.length() - j - 1);
    }
}
```

## [面试题 01.09. 字符串轮转](https://leetcode-cn.com/problems/string-rotation-lcci/)

字符串轮转。给定两个字符串`s1`和`s2`，请编写代码检查`s2`是否为`s1`旋转而成（比如，`waterbottle`是`erbottlewat`旋转后的字符串）。

```
 输入：s1 = "waterbottle", s2 = "erbottlewat"
 输出：True
```

```java
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        return (s2 + s2).indexOf(s1) != -1;
    }
}
```



## [面试题 02.07. 链表相交](https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/)

给你两个单链表的头节点 `headA` 和 `headB` ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 `null` 。

优雅写法: 

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }
}
```

