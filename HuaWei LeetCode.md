# HuaWei LeetCode

[TOC]

## [面试题 17.24. 最大子矩阵](https://leetcode-cn.com/problems/max-submatrix-lcci/)

给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。

返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。

注意：本题相对书上原题稍作改动

```
输入：
[
   [-1,0],
   [0,-1]
]
输出：[0,1,0,1]
解释：输入中标粗的元素即为输出所表示的矩阵
```

---

解题思路：

- 将二维数组降为一维，然后最大子序列和。对于矩阵的每一列，我们将其加在一起，成为了一维上的一个数，二维矩阵的和转化为了一维数组的和。

  ![捕获.JPG](asset/HuaWei%20LeetCode.assets/3db064ede3bc148f63d04c48fc8f852bf0e5a3e9d7ddbf58c8d526f201f08075-%E6%8D%95%E8%8E%B7.JPG)

```java
class Solution {
    public int[] getMaxMatrix(int[][] matrix) {
        //二维列前缀和
        int M = matrix.length, N = matrix[0].length;
        for(int i = 0; i < N;  ++i){
            for(int j = 1 ; j < M ; ++j){
                matrix[j][i] += matrix[j-1][i];
            }
        }
        int gobalMax = Integer.MIN_VALUE;
        int[] ret = new int[4];
        for(int top = 0; top < M; ++top){
            for(int down = top; down < M ; ++ down){
                int pre = top != 0?matrix[down][0] - matrix[top-1][0]: matrix[down][0];
                //int ans = pre;
                int index_1 = top,index_2 = 0,index_3 = down, index_4 = 0;
                if(pre > gobalMax){
                    ret[0] = top;ret[1] = 0; ret[2] = down; ret[3] = 0;
                    gobalMax = pre;
                }
                for( int i = 1; i < N; ++i){
                    int num = top != 0?matrix[down][i] - matrix[top-1][i]: matrix[down][i];
                    //pre = Math.max(pre + num, num);
                    if((pre + num) > num){
                        pre += num;
                        
                        index_4 = i;
                    }else{
                        pre = num;
                        index_2 = i;
                        index_4 = i;
                    }
                   // ans = Math.max(pre,ans);
                   if( pre > gobalMax){
                        ret[0] = index_1;ret[1] = index_2; ret[2] = index_3; ret[3] = index_4;
                        gobalMax = pre; 
                   }
                }
            }
        }
        //System.out.println(m);
        return ret;
    }
}
```





## [剑指 Offer 51. 数组中的逆序对](https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/)

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

```
输入: [7,5,6,4]
输出: 5
```

---

解题思路:

- 归并排序。

  关键就在于「归并」当中「并」的过程。我们通过一个实例来看看。假设我们有两个已排序的序列等待合并，分别是L={8,12,16,22,100} 和 R={7,26,55,64,91}。一开始我们用指针 l = 0 指向 L 的首部，r = 0 指向 R 的头部。

  当R[r] = 7 , L[l] = 8 时,我们可以发现 L 中所有的元素都比 7 大,而且在 7 之前,所以在这一步7就有五个逆序对。 以此类推。

- 离散化树状数组。

  「树状数组」是一种可以动态维护序列前缀和的数据结构，它的功能是：

  单点更新 update(i, v)： 把序列 i 位置的数加上一个值 v，这题 v = 1
  区间查询 query(i)： 查询序列 [1⋯i] 区间的区间和，即 i 位置的前缀和
  修改和查询的时间代价都是O(logn)，其中 n 为需要维护前缀和的序列的长度。

```java
// 归并排序
class Solution {
    public int reversePairs(int[] nums) {
        int N = nums.length;
        int ans = 0;
        // 一组的长度
        int len = 1;
        int i = 0;
        while (len < N) {
            int[] tempArr = new int[N];
            while (i < N) {
                int lidx = i;
                int lend = i + len - 1;
                int ridx = i + len;
                int rend = i + len + len - 1;
                if (lend >= N) {
                    lend = N - 1;
                    while (lidx <= lend) {
                        tempArr[i++] = nums[lidx++];
                    }
                    break;
                }
                if (rend >= N) {
                    rend = N - 1;
                }
                while (lidx <= lend && ridx <= rend) {
                    if (nums[lidx] <= nums[ridx]) {
                        tempArr[i++] = nums[lidx++];
                    } else {
                        tempArr[i++] = nums[ridx++];
                        ans += lend - lidx + 1;
                    }
                }
                while (lidx <= lend) {
                    tempArr[i++] = nums[lidx++];
                }
                while (ridx <= rend) {
                    tempArr[i++] = nums[ridx++];
                }
            }
            len <<= 1;
            i = 0;
            nums = tempArr;
        }
        return ans;
    }
}
// 离散化数组
class Solution {
    public int reversePairs(int[] nums) {
        int N = nums.length;
        int ans = 0;
        int[] copy = new int[N];
        System.arraycopy(nums, 0, copy, 0, N);
        Arrays.sort(copy);

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Arrays.binarySearch(copy, nums[i]) + 1;
        }
        BIT bit = new BIT(N);

        for (int i = N - 1; i >= 0; --i) {
            // 查询前面有几个比他小的数
            ans += bit.query(nums[i]-1);
            bit.update(nums[i]);
        }
        return ans;
    }
}
class BIT {
    private int[] tree;
    private int n;

    public BIT(int n) {
        this.n = n;
        // 一般从 1 开始
        this.tree = new int[n + 1];
    }
    // 定义一个Lowbit函数，返回参数转为二进制后,最后一个1的位置所代表的数值.
    // 例如,Lowbit(34)的返回值将是2；而Lowbit(12)返回4；Lowbit(8)返回8。
    public static int lowbit(int x) {
        // 这一步更是妙蛙坐火箭，妙上天了。
        // 这利用了补码的性质
        // 对于整数运算 x&(-x)有:
        // - 当x为0时，即 0 & 0，结果为0；
        // - 当x为奇数时，最后一个比特位为1，
        //   取反加1没有进位，故x和-x除最后一位外前面的位正好相反，按位与结果为0。结果为1。
        // - 当x为偶数，实际上就是把x用一个奇数左移k位来表示。
        //   这时，x的二进制表示最右边有k个0，从右往左第k+1位为1。
        //   x  =   000110100
        //   −x =   111001100
        //   x&(−x)=000000100
        return x & (-x);
    }
    public int query(int x) {
        int ret = 0;
        // 这一步真是妙呀,妙蛙他妈给妙蛙开门,妙到家了。
        // 如 tree[i]
        // tree[6] = num[110] + num[100];
        // tree[7] = num[111] + num[110] + num[100];
        while (x != 0) {
            ret += tree[x];
            x -= lowbit(x);
        }
        return ret;
    }
    // 更新是插入操作
    public void update(int x) {
        // n 为数组容量
        while (x <= n) {
            // tree的节点x++,相当于统计
            // 可以用作统计 小于当前数的 个数,也可以用作求 前缀和
            tree[x]++;
            // 包含这个节点的父节点也得加1
            x += lowbit(x);
        }
        // 包含 1 的节点有 1、10、100、1000···
        // 包含 3 的节点有 11、100、1000···
    }
}

```

## [剑指 Offer 59 - I. 滑动窗口的最大值](https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/)

给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

```java
给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

---

解题思路:

- 窗口滑动，只需要判断每次加进来的数是不是大于\[最大值]，但是由于左边也向右滑动，有可能将最大值滑出窗口，所以需要数据结构来保存最大值的次序。

  ==当滑动窗口向右移动时，只要 i 还在窗口中，那么 j 一定也还在窗口中。==

  这是 i 在 j 的左侧所保证的。因此，由于 nums[j] 的存在，nums[i] 一定不会是滑动窗口中的最大值了，nums[i] 永久地移除。因此我们可以使用一个队列存储所有还没有被移除的下标。

  在队列中，这些下标按照从小到大的顺序被存储，并且它们在数组 nums 中对应的值是严格单调递减的。

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        if (N < k || N == 0) {
            return new int[]{};
        }
        int[] ans = new int[N - k + 1];

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int index = 0;
        ans[index] = nums[deque.peekFirst()];

        int l = 1;
        int r = k - 1;
        for (r = k; r < N; r++, l++) {
            while (!deque.isEmpty() && nums[r] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(r);
            if (l > deque.peekFirst()) {
                deque.pollFirst();
            }
            ans[++index] = nums[deque.peekFirst()];
        }

        return ans;
    }
}
```







## ·[25. K 个一组翻转链表](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)

给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

进阶：

你可以设计一个只使用常数额外空间的算法来解决此问题吗？
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

![img](asset/HuaWei%20LeetCode.assets/reverse_ex1.jpg)

```
输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]
```

---

解题思路:

- 添加伪头结点简化操作，部分反转时返回链表的部分头和尾。

```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }

        //添加伪头结点
        head = new ListNode(0, head);

        ListNode pre = head;
        ListNode temp = head.next;
        int i = 0;
        while (temp != null) {
            i++;
            if (i == k) {
                i = 0;
                ListNode tempEnd = temp.next;
                ListNode[] out =  reverse(pre.next, temp);
                pre.next =out[0];
                pre = out[1];
                temp = tempEnd;
                continue;
            }
            temp = temp.next;
        }
        return head.next;
    }
    public  ListNode[] reverse(ListNode beg, ListNode end) {
        ListNode head = beg;
        ListNode op = beg;

        beg = beg.next;
        head.next = end.next;
        while (beg != end) {
            ListNode temp = beg;
            beg = beg.next;
            temp.next = head;
            head = temp;
        }
        beg.next = head;
        head = beg;

        return new ListNode[]{head, op};
    }
}
```



## ·[76. 最小覆盖子串](https://leetcode-cn.com/problems/minimum-window-substring/)

给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

注意：

对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。

```java
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
```

---

解题思路:

- 双指针(滑动窗口)。

  ==通过右指针扩大窗口，通过左指针缩小窗口。==

  我们在 *s* 上滑动窗口，通过移动 *r* 指针不断扩张窗口。当窗口包含 *t*全部所需的字符后，如果能收缩，我们就利用*l*指针收缩窗口直到得到最小窗口。

  如何判断当前的窗口包含所有 *t* 所需的字符呢？我们可以用一个哈希表表示 *t* 中所有的字符以及它们的个数，用一个哈希表动态维护窗口中所有的字符以及它们的个数

  ==有个技巧是使用一个distance变量表示窗口中有用的字符个数，如果有用的字符个数等于子串的长度，那么就代表可以缩短了，通过每次移动左右指针来实现O(1)复杂度维护窗口是否包含完整的子串。==

```java
class Solution {
    public String minWindow(String s, String t) {
        int N = s.length();
        int M = t.length();

        if (M == 0 || N == 0 || N < M) {
            return "";
        }
        StringBuilder ans = new StringBuilder();

        // 用数组记录
        HashMap<Character, Integer> winHashMap = new HashMap<>();
        HashMap<Character, Integer> tHashMap = new HashMap<>();
        for (int k = 0; k < M; k++) {
            char ch = t.charAt(k);
            tHashMap.put(ch, tHashMap.getOrDefault(ch, 0) + 1);
        }
        int i = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;
        int distance = 0;
        while (j < N) {
            if (distance < M) {
                char ch = s.charAt(j);
                if (winHashMap.getOrDefault(ch, 0) < tHashMap.getOrDefault(ch, 0)) {
                    distance++;
                }
                winHashMap.put(ch, winHashMap.getOrDefault(ch, 0) + 1);
                j++;
            }
            while (distance == M) {
                char ch = s.charAt(i);
                if (winHashMap.getOrDefault(ch, 0) <= tHashMap.getOrDefault(ch, 0)) {
                    distance--;
                    if (j - i < min) {
                        min = j - i;
                        ans = new StringBuilder(s.substring(i, j));
                    }
                }
                i++;
                if (winHashMap.getOrDefault(ch, 0) > 1) {
                    winHashMap.replace(ch, winHashMap.get(ch) - 1);
                } else {
                    winHashMap.remove(ch);
                }

            }
        }
        return ans.toString();
    }
}
```



## [77. 组合](https://leetcode-cn.com/problems/combinations/)

给定两个整数 `n` 和 `k`，返回范围 `[1, n]` 中所有可能的 `k` 个数的组合。

你可以按 **任何顺序** 返回答案。

```
输入：n = 4, k = 2
输出：
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```

---

解题思路:

- 回溯。
- 二进制标志位表示是否选择。

```java
class Solution {
    public List<List<Integer>> combine(int n, int k) {
       List<List<Integer>> ans = new ArrayList<>();
        Integer[] tempAns = new Integer[k];
        traceback(ans, tempAns, n, 0, k, 1);
        return ans;
    }
    public void traceback(List<List<Integer>> ans, Integer[] tempAns, int n, int len, int k, int i) {
        if (len == k) {
            ArrayList<Integer> temp = new ArrayList();
            for (Integer tempAn : tempAns) {
                temp.add(tempAn);
            }
            ans.add(temp);
            return;
        }
        if (i > n) {
            return;
        }
        tempAns[len] = i;
        traceback(ans, tempAns, n, len + 1, k, i + 1);
        tempAns[len] = null;
        traceback(ans, tempAns, n, len, k, i + 1);
    }
}
```





## [135. 分发糖果](https://leetcode-cn.com/problems/candy/)

老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。

你需要按照以下要求，帮助老师给这些孩子分发糖果：

每个孩子至少分配到 1 个糖果。
评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
那么这样下来，老师至少需要准备多少颗糖果呢？

```
输入：[1,0,2]
输出：5
解释：你可以分别给这三个孩子分发 2、1、2 颗糖果。
```

---

解题思路:

- 两趟遍历。
  - 第一趟==从左向右遍历==单调上升区间，当前的糖果数是前一个糖果+1。
  - 第二趟==从右向左遍历==单调上升区间，判断是否符合当前高于前一个，如果不高于，当前的糖果数是前一糖果数+1。

```java
class Solution {
    public int candy(int[] ratings) {
        int N = ratings.length;
        if (N == 1) {
            return 1;
        }
        int[] candies = new int[N];
        candies[0] = 1;
        //左向右上升
        for (int i = 1; i < N; i++) {
            candies[i] = 1;
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        int ans = candies[N - 1];
        //右向左上升
        for (int i = N - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
            ans += candies[i];
        }
        //ans = IntStream.of(candies).sum();
        return ans;
    }
}
```

## [143. 重排链表](https://leetcode-cn.com/problems/reorder-list/)

给定一个单链表 L 的头节点 head ，单链表 L 表示为：L0 → L1 → … → Ln-1 → Ln 

请将其重新排列后变为：L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

```java
输入: head = [1,2,3,4,5]
输出: [1,5,2,4,3]
```

---

```java
class Solution {
    public void reorderList(ListNode head) {
        int N = 0;
        ListNode temp = head;
        while (temp != null) {
            N++;
            temp = temp.next;
        }
        if (N <= 2) {
            return;
        }
        int pre = ((N + 1) >> 1);
        temp = head;
        ListNode rearList = null;
        int i = 0;
        while (temp != null) {
            if (i == pre - 1) {
                rearList = temp.next;
                temp.next = null;
                break;
            }
            i++;
            temp = temp.next;
        }
        // 头插反转
        ListNode index = rearList.next;
        rearList.next = null;
        while (index != null) {
            temp = index;
            index = index.next;
            temp.next = rearList;
            rearList = temp;
        }
        //合并
        ListNode ans = head;
        ListNode idx1 = head;
        ListNode idx2 = rearList;
        //index = rearList;
        while (rearList != null) {
            head = head.next;
            rearList = rearList.next;
            idx1.next = idx2;
            idx2.next = head;
            idx1 = head;
            idx2 = rearList;
        }
        head = ans;
        return;
    }
}
```



## ·[146. LRU 缓存机制](https://leetcode-cn.com/problems/lru-cache/)

运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
实现 LRUCache 类：

LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。


进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？

```java
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
```

---

解题思路:

- Hash + 双向链表。

  `HashMap<Integer, Node>`负责查找和定位，通过查找到的位置来进行置换和操作。

  `双向链表负责保存值`，并且按访问顺序保持，作为`LRU置换`的依据

```java
class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.value = val;
        }
    }

    // 实现O(1) 查找,定位链表
    private HashMap<Integer, Node> hashMap;
    private int capacity;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        hashMap = new HashMap<>();
        // 创建双向链表，为了方便操作，添加头尾伪节点。
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        if (hashMap.containsKey(key)) {
            Node index = hashMap.get(key);
            //摘除
            index.prev.next = index.next;
            index.next.prev = index.prev;
            //尾插
            index.prev = tail.prev;
            index.next = tail;
            tail.prev.next = index;
            tail.prev = index;
            return hashMap.get(key).value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (hashMap.containsKey(key)) {
            //包含关键字,调整位置
            Node index = hashMap.get(key);
            //可能修改值
            index.value = value;
            //摘除
            index.prev.next = index.next;
            index.next.prev = index.prev;
            //尾插
            index.prev = tail.prev;
            index.next = tail;
            tail.prev.next = index;
            tail.prev = index;

        } else {
            //不包含关键字
            if (hashMap.size() < capacity) {
                // 容量够,进组
                Node temp = new Node(key, value);
                //尾插
                temp.prev = tail.prev;
                temp.next = tail;
                tail.prev.next = temp;
                tail.prev = temp;
                hashMap.put(key, temp);
            } else {
                //容量不够, 置换
                Node temp = new Node(key, value);
                //尾插
                temp.prev = tail.prev;
                temp.next = tail;
                tail.prev.next = temp;
                tail.prev = temp;
                hashMap.put(key, temp);
                // 移除LRU
                hashMap.remove(head.next.key);
                Node remove = head.next;
                remove.next.prev = head;
                head.next = remove.next;
            }
        }
    }
}

```



## [560. 和为 K 的子数组](https://leetcode-cn.com/problems/subarray-sum-equals-k/)

给你一个整数数组 `nums` 和一个整数 `k` ，请你统计并返回该数组中和为 `k` 的连续子数组的个数。

```
输入：nums = [1,2,3], k = 3
输出：2
```

---

阶梯思路:

- 前缀和 + 哈希优化。

  当我们构造前缀和数组pre后，求子数组和等于 k 相当于求 `pre[i] - pre[j-1] == k`，可以进行移项得到 `pre[i] = pre[j-1] + k`， 将所有的 pre[j-1] + k 出现的个数保存在HashMap中，在保存过程（保证了 j < i）中查找pre[i] == pre[j-1]+k的个数 , 就可以得到结果。

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        int N = nums.length;
        //      pre[i] - pre[j-1] == k
        // =>   pre[i] == k + pre[j-1]
        // =>   pre[j-1] == pre[i] - k 
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int preSum =0;
        // 这相当于 保存一个 前缀和是0的位置, 就相当于 没有数时的和 0+k
        // =>   pre[i] == k + pre[j-1]
        hashMap.put(k, 1);
        for (int i = 0; i < N; i++) {
            preSum +=  nums[i];
            ans += hashMap.getOrDefault(preSum, 0);
            hashMap.put(preSum + k, hashMap.getOrDefault(preSum + k, 0) + 1);
        }
        return ans;
    }
}
```



## [1190. 反转每对括号间的子串](https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/)

给出一个字符串 s（仅含有小写英文字母和括号）。

请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。

注意，您的结果中 不应 包含任何括号。

```
输入：s = "(ed(et(oc))el)"
输出："leetcode"
解释：先反转子字符串 "oc" ，接着反转 "etco" ，然后反转整个字符串。
```

---

解题思路:

- 栈。

- 转向遍历。

  我们可以将括号的反转理解为逆序地遍历括号，如下图：

  ![fig1](asset/HuaWei%20LeetCode.assets/1.png)

  1. 第一步我们向右移动到左括号，此时我们跳跃到该左括号对应的右括号（进入了更深一层）；
  2. 第二到第三步我们在括号内部向左移动（完成了更深层的遍历）；
  3. 第四步我们向左移动到左括号，此时我们跳跃到该左括号对应的右括号（返回到上一层）；
  4. 第五步我们在括号外向右移动（继续遍历）。

  在实际代码中，我们需要预处理出每一个括号对应的另一个括号所在的位置，这一部分我们可以使用栈解决。当我们预处理完成后，即可在线性时间内完成遍历，遍历的字符串顺序即为反转后的字符串。


```java
class Solution {
    public String reverseParentheses(String s) {
        if (s.length() == 0) {
            return "";
        }
        Stack<Integer> stack = new Stack<>();
        int[] pair = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            if (s.charAt(i) == ')') {
                pair[i] = stack.peek();
                pair[stack.peek()] = i;
                stack.pop();
            }
        }
        StringBuilder ans = new StringBuilder();
        int index = 0;
        int direct = 1;
        while (index < s.length()) {
            if (s.charAt(index) == '(' || s.charAt(index) == ')') {
                index = pair[index];
                direct = -direct;
            } else {
                ans.append(s.charAt(index));
            }
            index += direct;
        }
        return ans.toString();
    }
}
```

