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

## [面试题 01.05. 一次编辑](https://leetcode-cn.com/problems/one-away-lcci/)

字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。

```java
输入: 
first = "pale"
second = "ple"
输出: True
```

---

解题思路:

- 一次遍历，第一次遇到不相同的地方，将长的字符串往后移一位。

```java
class Solution {
    public boolean oneEditAway(String first, String second) {
        // lStr是长字符串，sStr是短字符串
        String lStr = first.length() >= second.length() ? first : second,
                sStr = first.length() < second.length() ? first : second;
        // diff是字符串中不同的字符数量，sStrIdx是短字符串的index，lStrIdx是长字符串的index
        int diff = 0, sStrIdx = 0, lStrIdx = 0;
        // 如果两个字符串长度差>1，即字符串中不同的字符数量>1，则无法通过一次编辑完成，返回false
        if (lStr.length() - sStr.length() > 1) return false;
        while (sStrIdx < sStr.length()) {
            if (sStr.charAt(sStrIdx++) != lStr.charAt(lStrIdx++)) {
                // 如果字符串长度不同，此时只将长字符串的index向后移动一位
                if (lStr.length() != sStr.length()) sStrIdx--;
                // 字符串不同字符数量+1
                diff++;
            }
            // 字符串中不同的字符数量>1，则无法通过一次编辑完成，返回false
            if (diff > 1) return false;
        }
        return true;
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

## [面试题 03.01. 三合一](https://leetcode-cn.com/problems/three-in-one-lcci/)

三合一。描述如何只用一个数组来实现三个栈。

你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。stackNum表示栈下标，value表示压入的值。

构造函数会传入一个stackSize参数，代表每个栈的大小。

```java
输入：
["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
[[1], [0, 1], [0, 2], [0], [0], [0], [0]]
 输出：
[null, null, null, 1, -1, -1, true]
说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
```

---

解题思路:

- 主要是计算下标，可以用数组简化栈号的匹配。

```java
class TripleInOne {
    int[] arr;
    int[] topIdx;
    int[] borderIdx;
    int[] bottomIdx;
    public TripleInOne(int stackSize) {
        arr = new int[stackSize * 3 + 2];
        topIdx = new int[]{0, stackSize + 1, stackSize * 2 + 2};
        borderIdx = new int[]{stackSize, stackSize * 2 + 1, stackSize * 3 + 2};
        bottomIdx = new int[]{0, stackSize + 1, stackSize * 2 + 2};
    }
    public void push(int stackNum, int value) {
        if (topIdx[stackNum] == borderIdx[stackNum]) return;
        arr[topIdx[stackNum]++] = value;
    }
    public int pop(int stackNum) {
        if (topIdx[stackNum] == bottomIdx[stackNum]) return -1;
        return arr[--topIdx[stackNum]];
    }
    public int peek(int stackNum) {
        if (topIdx[stackNum] == bottomIdx[stackNum]) return -1;
        return arr[topIdx[stackNum] - 1];

    }
    public boolean isEmpty(int stackNum) {
        return topIdx[stackNum] == bottomIdx[stackNum];
    }
}
```



## [面试题 03.03. 堆盘子](https://leetcode-cn.com/problems/stack-of-plates-lcci/)

堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。

当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.

```java
 输入：
["StackOfPlates", "push", "push", "push", "popAt", "popAt", "popAt"]
[[2], [1], [2], [3], [0], [0], [0]]
 输出：
[null, null, null, null, 2, 1, 3]

```

---

解题思路:

- 主要是题目要理解清除，栈和栈之间的普通弹出也是后进先出的，要注意存在栈容量为0的情况。

```java
class StackOfPlates {
        ArrayList<Deque<Integer>> stack;
        Integer capacity;
        public StackOfPlates(int cap) {
            stack = new ArrayList<>();
            capacity = cap;
        }
        public void push(int val) {
            if (capacity == 0 || stack.isEmpty() || stack.get(stack.size() - 1).size() == capacity) {
                stack.add(new LinkedList<>());
            }
            stack.get(stack.size() - 1).push(val);
        }
        public int pop() {
            if (capacity == 0 || stack.isEmpty()) {
                return -1;
            }
            Deque<Integer> topStack = stack.get(stack.size() - 1);
            Integer ans = topStack.pop();
            if (topStack.isEmpty()) {
                stack.remove(stack.size() - 1);
            }
            return ans;
        }
        public int popAt(int index) {
            if (capacity == 0 || index < 0 || index >= stack.size()) {
                return -1;
            }
            Deque<Integer> topStack = stack.get(index);
            if (topStack.isEmpty()) {
                stack.remove(stack.size() - 1 - index);
                return -1;
            }
            Integer ans = topStack.pop();
            if (topStack.isEmpty()) {
                stack.remove(index);
            }
            return ans;
        }
    }
```



## [面试题 04.01. 节点间通路](https://leetcode-cn.com/problems/route-between-nodes-lcci/)

节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。

```java
 输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
 输出：true
```

```java
输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
 输出 true
```

---

- DFS。构造领接表，然后DFS搜索即可
- 递归，也可以使用从后往前找。

```java
public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
    if (start == target) return true;
        for (int[] en : graph) {
            if (en[1] == target) {
                return findWhetherExistsPathBrief(n, graph, start, en[0]);
            }
        }
        return false;
}
class Solution {
    public boolean ans;
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            int x = graph[i][0];
            int y = graph[i][1];
            if (x == y) continue;
            ArrayList<Integer> list;
            if (map.containsKey(x)) {
                list = map.get(x);
                if (list.lastIndexOf(y) == -1)
                    list.add(y);
            } else {
                list = new ArrayList<>();
                list.add(y);
            }
            map.put(x, list);
        }

        ans = false;
        DFS(map, start, target);
        return ans;
    }
    public void DFS(HashMap<Integer, ArrayList<Integer>> map, int start, int target) {
        if (ans) return;
        if (!map.containsKey(start)) {
            return;
        }
        ArrayList<Integer> list = map.get(start);
        for (int i = 0; i < list.size(); i++) {
            int tar = list.get(i);
            if (tar == target) {
                ans = true;
                return;
            }
            DFS(map, tar, target);
        }
    }
}
```

## [面试题 04.10. 检查子树](https://leetcode-cn.com/problems/check-subtree-lcci/)

检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。

如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。

```
 输入：t1 = [1, null, 2, 4], t2 = [3, 2]
 输出：false
```

---

解题思路:

- 递归检查
  - 如果节点相等,那么检查对应左右子树也要相等.
  - 如果节点不相等, 那么分别检查T1.left 和 T2、T1.right 和T2 是否相等.

```java
class Solution {
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null && t2 != null) {
            return false;
        } else if (t2 == null && t1 != null) {
            return false;
        } else if (t1.val == t2.val) {
            return checkSubTree(t1.left, t2.left) && checkSubTree(t1.right, t2.right);
        } else {
            return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
        }
    }
}
```







## [面试题 05.01. 插入](https://leetcode-cn.com/problems/insert-into-bits-lcci/)

给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。

编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处用 0 补齐。具体插入过程如图所示。

<img src="asset/LeetCode%20Interview.assets/1610104070-NuLVQi-05.01.gif" alt="img" style="zoom:50%;" />

题目保证从 i 位到 j 位足以容纳 M， 例如： M = 10011，则 i～j 区域至少可容纳 5 位。

```java
 输入：N = 1024(10000000000), M = 19(10011), i = 2, j = 6
 输出：N = 1100(10001001100)
```

---

解题思路:

- 位运算，先将 ==M 和 N 对齐==，然后将 N 对应位 ==置0==，或上 M 的对应位置

```java
class Solution {
    public int insertBits(int N, int M, int i, int j) {
        M <<= i;
        for (; i <= j; i++) {
            N = (N & ~(1 << i)) | ((M & (1 << i)));
        }
        return N;
    }
}
```



## [面试题 05.02. 二进制数转字符串](https://leetcode-cn.com/problems/bianry-number-to-string-lcci/)

二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。

```
 输入：0.625
 输出："0.101"
 输入：0.1
 输出："ERROR"
 提示：0.1无法被二进制准确表示
 32位包括输出中的"0."这两位。
```

---

解题思路:

- 小数十进制转二进制。
  - 十进制的小数转换为二进制小数，主要是利用小数部分乘2后，取整数部分，直至小数点后为0

```java
class Solution {
    public String printBin(double num) {
        String ans = "0.";
        for (int i = 0; i < 30; i++) {
            if (num == 0) {
                break;
            }
            num *= 2;
            if (num >= 1) {
                ans += "1";
                num = num - 1;
            } else {
                ans += "0";
            }
        }
        if (num != 0.0) {
            return "ERROR";
        }
        return ans;
    }
}
```

## [面试题 05.03. 翻转数位](https://leetcode-cn.com/problems/reverse-bits-lcci/)

给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。

```java
示例 1：
输入: num = 1775(110111011112)
输出: 8
```

---

解题思路:

- 一次遍历，再替换掉一个0之后，如果又遇到0，则初始化状态，将指针移到上一次替换的地方再开始查询。

```java
class Solution {
    public int reverseBits(int num) {
        int max = 0;
        int partLen = 0;
        int beg = 0;
        boolean flag = false;
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) != 0) {
                partLen++;
            } else {
                if (flag) {
                    partLen = 0;
                    flag = false;
                    // 返回去刚才断掉的地方
                    i = beg;
                } else {
                    flag = true;
                    partLen++;
                    beg = i;
                }
            }
            max = partLen > max ? partLen : max;
        }
        return max;
    }
}
```





## [面试题 05.04. 下一个数](https://leetcode-cn.com/problems/closed-number-lcci/)

下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。

```java
输入：num = 2（或者0b10）
输出：[4, 1] 或者（[0b100, 0b1]）
num的范围在[1, 2147483647]之间；
如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
```

---

解题思路:

- 找规律，注意把某一位置零和置一的技巧。

  - ```java
    // 最大 : 把所有 1 放到最前面
    // 最小 : 把所有 1 放到最后面
    // 略小 : 从右往左，找到第一个 10 位置，然后把 10 转为 01，
    //          右侧剩下的 1 移到右侧的 高位，右侧剩下的位置0。
    // 略大 : 从右往左，找到第一个 01 位置，然后把 01 转为 10，
    //          右侧剩下的 1 移到右侧的 低位，右侧剩下的位置0。
    ```

  - 把第i位置1：num | (1<<i)

  - 把第i位置0: num &~(1<<i)

  - 构造1111000形式: -(1<<3)

  - 构造0001111形式: ~(-(1<<4))

```java
class Solution {
    public int[] findClosedNumbers(int num) {
        int first10 = -1;
        int numOne = num & 1;
        for (int i = 1; i < 32; i++) {
            if ((num & (1 << i)) != 0 && (num & (1 << (i - 1))) == 0) {
                first10 = i;
                break;
            }
            if ((num & (1 << i)) != 0) {
                numOne++;
            }
        }
        int small = num;
        if (first10 == -1) {
            small = -1;
        } else {
            small = (num & ~(1 << first10)) | (1 << (first10 - 1));
            first10 -= 2;
            while (numOne != 0) {
                small = small | (1 << first10);
                numOne--;
                first10--;
            }
            while (first10 != -1) {
                small = small & ~(1 << first10);
                first10--;
            }
        }
        int first01 = -1;
        int numZero = ~num & 1;
        for (int i = 1; i < 32; i++) {
            if ((num & (1 << i)) == 0 && (num & (1 << (i - 1))) != 0) {
                first01 = i;
                break;
            }
            if ((num & (1 << i)) == 0) {
                numZero++;
            }
        }
        int bigger = -1;
        if (first01 == 31) {
            bigger = -1;
        } else {
            bigger = (num | (1 << first01)) & ~(1 << (first01 - 1));
            first01 -= 2;
            while (numZero != 0) {
                bigger = bigger & ~(1 << first01);
                numZero--;
                first01--;
            }
            while (first01 != -1) {
                bigger = bigger | (1 << first01);
                first01--;
            }
        }
        return new int[]{bigger, small};
    }   
}
```



## [面试题 05.07. 配对交换](https://leetcode-cn.com/problems/exchange-lcci/)

配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。

```java
示例1:
 输入：num = 2（或者0b10）
 输出 1 (或者 0b01)
```

---

解题思路:

- 位运算，将奇数位和偶数位拆开，奇数位左移1位，偶数位右移一位，再相加即为答案。

```java
class Solution {
    public int exchangeBits(int num) {
        int t = 0b0101_0101_0101_0101_0101_0101_0101_0101;
        return ((num & t) << 1) + ((num & (t << 1)) >> 1);
    }
}
```

## [面试题 08.02. 迷路的机器人](https://leetcode-cn.com/problems/robot-in-a-grid-lcci/)

设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角移动到右下角的路径。

网格中的障碍物和空位置分别用 1 和 0 来表示。

返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。如果没有可行的路径，返回空数组。

```java
示例 1:
输入:
[[0,0,0],
 [0,1,0],
 [0,0,0]]
输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
解释: 
输入中标粗的位置即为输出表示的路径，即
0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角）
```

---

解题思路:

- DFS。由于 只能向右或向下走，产生了动态规划的最关键的影响**无后效性**。 所以在DFS时可以将访问过的地方置为1后不再重置回0.
- 动态规划, dp\[i][j]位置的情况只能由 dp\[i-1][j] 和 dp\[i][j-1]转移而来.

```java
class Solution {
    public static List<List<Integer>> ans;
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        ans = new ArrayList<>();
        DFS(obstacleGrid, 0, 0, new ArrayList<List<Integer>>());
        return ans;
    }
    public void DFS(int[][] obstacle, int i, int j, List<List<Integer>> tempAns) {
        if (i < 0 || i >= obstacle.length || j < 0 || j >= obstacle[0].length || obstacle[i][j] == 1)
            return;

        obstacle[i][j] = 1;
        tempAns.add(Arrays.asList(new Integer[]{i, j}));
        if (i == obstacle.length - 1 && j == obstacle[0].length - 1) {
            Iterator<List<Integer>> it = tempAns.iterator();
            while (it.hasNext()) {
                ans.add(it.next());
            }
            return;
        }
        DFS(obstacle, i + 1, j, tempAns);
        DFS(obstacle, i, j + 1, tempAns);
        tempAns.remove(tempAns.size() - 1);
    }
}
```



## [面试题 08.07. 无重复字符串的排列组合](https://leetcode-cn.com/problems/permutation-i-lcci/)

无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。

```java
示例1:
 输入：S = "qwe"
 输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
```

---

解题思路:

- 交换, 每个数和它后面的数进行交换

```java
class Solution {
    public String[] permutation(String S) {
        LinkedList<String> ans = new LinkedList<>();
        dfs(S.toCharArray(), 0, ans);
        return ans.toArray(new String[ans.size()]);
    }
    public void dfs(char[] temp, int i, LinkedList<String> ans) {
        if (i == temp.length) {
            ans.addLast(String.valueOf(temp));
            return;
        }
        for (int j = i; j < temp.length; j++) {
            swap(temp, i, j);
            dfs(temp, i+1, ans);
            swap(temp, i, j);
        }
    }
    public void swap(char[] arr, int i, int j) {
        char ch = arr[i];
        arr[i] = arr[j];
        arr[j] = ch;
    }
}
```





## [面试题 08.12. 八皇后](https://leetcode-cn.com/problems/eight-queens-lcci/)

设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。

注意：本题相对原题做了扩展

示例:

```java
 输入：4
 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 解释: 4 皇后问题存在如下两个不同的解法。
[
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],
 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]
```

---

解题思路:

- 回溯搜索。 

  对行进行枚举，由于每行每列每个对角线只会出现一个皇后，所以我们先枚举行，然后用三个数组标记其他三维是否有空位。

  对角线的标记有如下技巧:

  - 主对角线 dg （左上到右下）:每条对角线，横坐标和列坐标的差值固定。
  - 副对角线 udg （右上到左下）：每条对角线的和固定。

  所以满足:

  ```java
  !col[j] && !dg[j - i + n] && !udg[j + i]
  ```

  就说明这个位置可以放一个皇后。

  然后对所有情况进行回溯即可。

```java
class Solution {
    // 枚举行 ,三个数组标记 列 对角线 反对角线
    public static boolean col[] = new boolean[100];
    public static boolean dg[] = new boolean[100];
    public static boolean udg[] = new boolean[100];
    public static List<List<String>> ans;
    public List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        ArrayList<Character[]> strings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Character[] map = new Character[n];
            Arrays.fill(map, '.');
            strings.add(map);
        }
        backtrack(0, n, strings);
        return ans;
    }
    public void backtrack(int i, int n, List<Character[]> str) {
        if (i == n) {
            List<String> temp = new ArrayList<>();
            Iterator<Character[]> it = str.iterator();
            while (it.hasNext()) {
                Character[] p = it.next();
                StringBuilder string = new StringBuilder();
                for (int k = 0; k < n; k++) {
                    string.append(p[k]);
                }
                temp.add(string.toString());
            }
            ans.add(temp);
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!col[j] && !dg[j - i + n] && !udg[j + i]) {
                col[j] = true;
                dg[j - i + n] = true;
                udg[j + i] = true;
                str.get(i)[j] = 'Q';
                backtrack(i + 1, n, str);
                str.get(i)[j] = '.';
                col[j] = false;
                dg[j - i + n] = false;
                udg[j + i] = false;
            }
        }
    }
}
```

## [面试题 10.03. 搜索旋转数组](https://leetcode-cn.com/problems/search-rotate-array-lcci/)

搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。不存在返回-1.

```java
输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
输出: 8（元素5在该数组中的索引）
```

---

解题思路:

- 二分查找。
  - 【注意】: 一个数组如果只从一个点旋转,不管转多少次,都可以分为有序的两部分所以进行二分,二分的两段,肯定有一段是有序的.
  - 分两种情况:
    - 左边有序 arr[l] <= arr[mid]
      - arr[mid] > target && target >= arr[l] 则 r = mid - 1; 否则 l = mid + 1;
    - 右边有序
      - arr[mid] < target && target <= arr[r] 则 l = mid + 1; 否则 r = mid - 1;
  - 注意 预处理 有一个值超过一半,即会造成 arr[l] == arr[mid] == arr[r]
  - 【终止条件两个】:
    - 最终找到 arr[mid] == target
    - 最终未找到 l > r || (l == r && arr[mid] != target)

```java
class Solution {
    public int search(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        if (r == -1) return -1;
        int idx = -1;
        // 预处理 有一个值超过一半,即会造成 arr[l] == arr[mid] == arr[r]
        while (r != 0 && arr[l] == arr[r]) r--;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] == target) {
                idx = mid;
                break;
            }
            if (l == r && arr[mid] != target) {
                idx = -1;
                break;
            }
            // 左边有序
            if (arr[l] <= arr[mid]) {
                if (arr[mid] > target && target >= arr[l]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
                // 右边有序
            } else {
                if (arr[mid] < target && target <= arr[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        //往前找个最小
        if (idx == -1) return -1;
        while (idx != 0 && arr[idx - 1] == target) {
            idx--;
        }
        return idx;
    }
}
```


## [面试题 16.06. 最小差](https://leetcode-cn.com/problems/smallest-difference-lcci/)

给定两个整数数组`a`和`b`，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差

```
输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
输出：3，即数值对(11, 8)
```

---

解题思路:

- 排序 + 双指针。
  - 排序后，每次比较两个数组的最小值即可。因为 当前a[i] - b[j] 肯定是小于 a[i] - b[j+1],的因为是有序的,反过来 也同理。
- 排序 + 二分

```java
class Solution {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        long ans = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) return 0;
            if (a[i] < b[j]) {
                ans = Math.min(ans, (long) b[j] - a[i]);
                i++;
            } else if (a[i] > b[j]) {
                ans = Math.min(ans, (long) a[i] - b[j]);
                j++;
            }
        }
        return (int)ans;
    }
}
```



## [面试题 16.13. 平分正方形](https://leetcode-cn.com/problems/bisect-squares-lcci/)

给定两个正方形及一个二维平面。请找出将这两个正方形分割成两半的一条直线。假设正方形顶边和底边与 x 轴平行。

每个正方形的数据square包含3个数值，正方形的左下顶点坐标[X,Y] = [square[0],square[1]]，以及正方形的边长square[2]。所求直线穿过两个正方形会形成4个交点，请返回4个交点形成线段的两端点坐标（两个端点即为4个交点中距离最远的2个点，这2个点所连成的线段一定会穿过另外2个交点）。2个端点坐标[X1,Y1]和[X2,Y2]的返回格式为{X1,Y1,X2,Y2}，要求若X1 != X2，需保证X1 < X2，否则需保证Y1 <= Y2。

若同时有多条直线满足要求，则选择斜率最大的一条计算并返回（与Y轴平行的直线视为斜率无穷大）。

```java
示例：
输入：
square1 = {-1, -1, 2}
square2 = {0, -1, 2}
输出： {-1,0,2,0}
解释： 直线 y = 0 能将两个正方形同时分为等面积的两部分，返回的两线段端点为[-1,0]和[2,0]
```

---

解题思路:

1. 计算两个正方形的中心坐标

2. 判断此时两点形成的直线斜率是否存在

3. 若不存在则说明两点在同一条x轴上

4. 若存在，则计算斜率k和系数b

5. 根据斜率的绝对值的大小，可以知道直线与正方形的上下相交还是左右相交

6. 斜率绝对值小于等于1，左右相交，斜率等于1就是对角线，既是上下相交又左右相交，随便放哪边计算都行

```java
class Solution {
    public double[] cutSquares(int[] square1, int[] square2) {
        double midX1 = square1[0] + (square1[2] / 2.0);
        double midY1 = square1[1] + (square1[2] / 2.0);
        double midX2 = square2[0] + (square2[2] / 2.0);
        double midY2 = square2[1] + (square2[2] / 2.0);

        double[] ans = new double[4];
        if (midX1 - midX2 == 0) {
            ans[0] = midX1;
            ans[1] = Math.min(square1[1], square2[1]);
            ans[2] = midX1;
            ans[3] = Math.max(square1[1]+ square1[2], square2[1] + square2[2]);
        }else{
            double k = (midY1 - midY2) / (midX1 - midX2);
            double b = midY1 - k * midX1;
            if (Math.abs(k) >= 1) {
                ans[1] = Math.min(square1[1] , square2[1]);
                ans[0] = (ans[1] - b) / k;
                ans[3] = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
                ans[2] = (ans[3] - b) / k;
            } else {
                //斜率绝对值小于等于1，说明与正方形的左边和右边相交，同理
                ans[0] = Math.min(square1[0], square2[0]);
                ans[1] = ans[0] * k + b;
                ans[2] = Math.max(square1[0] + square1[2], square2[0] + square2[2]);
                ans[3] = ans[2] * k + b;
            }
        }
        if(ans[0] > ans[2]){
            swap(ans);
        }
        return ans;
    }
    public  void swap(double t[]) {
        double temp = t[0];
        t[0] = t[2];
        t[2] = temp;
        temp = t[1];
        t[1] = t[3];
        t[3] = temp;
    }
}
```

## [面试题 17.12. BiNode](https://leetcode-cn.com/problems/binode-lcci/)

二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。

返回转换后的单向链表的头节点。

注意：本题相对原题稍作改动

```
输入： [4,2,5,1,3,null,6,0]
输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
```

---

解题思路:

- 中序遍历。
  - 难点是记录前节点和后节点。
  - 如图 节点4 的后继,需要访问到 节点5 时才能确定。

<img src="asset/LeetCode%20Interview.assets/image-20211222151944671.png" alt="image-20211222151944671" style="zoom: 33%;" />

```java
class Solution {
    public static TreeNode head;
    public static TreeNode pre;
    public TreeNode convertBiNode(TreeNode root) {
        head = null;
        pre = null;
        inOrder(root);
        return head;
    }
    public void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        root.left = null;
        if (pre == null) head = root; 
        else pre.right = root;
        pre = root;
        inOrder(root.right);
    }
}
```

