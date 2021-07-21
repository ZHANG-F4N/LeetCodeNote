# Advanced algorithms

[TOC]



## [23. 合并K个升序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。

 ```tex
 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 输出：[1,1,2,3,4,4,5,6]
 解释：链表数组如下：
 [
   1->4->5,
   1->3->4,
   2->6
 ]
 将它们合并到一个有序链表中得到。
 1->1->2->3->4->4->5->6
 ```

---

解题思路:

方法一: 顺序合并。以第一组为主链表，将后面的所有的链表依次插入第一组中，先插第二组，第二组插完再插第三组....以此类推。

渐进时间复杂度为$ O(k^2 n)$

方法二: 两路归并合并。每次选取两组进行合并，每轮合并结束剩下k/2个链表。

渐进时间复杂度为 $O(nk\log k )$​

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //二路归并
        if (lists.length == 1) {
            return lists[0];
        }
        if (lists.length == 0) {
            return null;
        }
        int unSort = lists.length;
        int mid = (lists.length + 1) / 2;
        while (mid >= 1) {
            for (int i = 0; i < unSort - mid; i++) {
                lists[i] = twoMerge(lists[i], lists[mid + i]);
            }
            if (mid == 1) {
                break;
            }
            unSort = mid;
            mid = (unSort + 1) / 2;
        }
        return lists[0];
    }
    public ListNode twoMerge(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }

        ListNode head = new ListNode(), tail = head, tempA = a, tempB = b;
        while (tempA != null && tempB != null) {
            if (tempA.val < tempB.val) {
                tail.next = tempA;
                tempA = tempA.next;
                tail = tail.next;
            } else {
                tail.next = tempB;
                tempB = tempB.next;
                tail = tail.next;
            }
        }
        if (tempA != null) {
            tail.next = tempA;
        }
        if (tempB != null) {
            tail.next = tempB;
        }
        return head.next;
    }
}
```





## [41. 缺失的第一个正数](https://leetcode-cn.com/problems/first-missing-positive/)

给你一个未排序的整数数组 `nums` ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 `O(n)` 并且只使用常数级别额外空间的解决方案。

```tex
输入：nums = [3,4,-1,1]
输出：2
```

---

解题思路:

方法一: 原地Hash	 由于我们只在意 \[1, N] 中的数，因此我们可以先对数组进行遍历，把不在 \[1, N] 范围内的数修改成任意一个大于 N 的数（例如 N+1）。这样一来，数组中的所有数就都是正数了，因此我们就可以将「标记」表示为「负号」。算法的流程如下：

我们将数组中所有小于等于 0 的数修改为 N+1；

我们遍历数组中的每一个数 x，它可能已经被打了标记，因此原本对应的数为 |x|。如果$ |x| \in [1, N]$，那么我们给数组中的第 |x| - 1个位置的数添加一个负号。注意如果它已经有负号，不需要重复添加；

在遍历完成之后，如果数组中的每一个数都是负数，那么答案是 N+1，否则答案是第一个正数的位置加 1。

方法二:	使用额外空间

我们可以将数组所有的数放入哈希表，随后从 1 开始依次枚举正整数，并判断其是否在哈希表中；

我们可以从 1 开始依次枚举正整数，并遍历数组，判断其是否在数组中。

```java
//原地HASH
class Solution {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (0 >= nums[i] || nums[i] > nums.length) {
                nums[i] = nums.length + 1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i]) <= nums.length ) {
                if (nums[Math.abs(nums[i]) - 1] > 0) {
                    nums[Math.abs(nums[i]) - 1] = -nums[Math.abs(nums[i]) - 1];
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
```

```JAVA
//额外空间
class Solution {
    public int firstMissingPositive(int[] nums) {
        int[] flag = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= nums.length && nums[i] > 0) {
                flag[nums[i]] = 1;
            }
        }
        for (int i = 1; i < flag.length; i++) {
            if (flag[i] ==0) {
                return i;
            }
        }
        return nums.length + 1;
    }
}
```







## [54. 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)

给你一个 `m` 行 `n` 列的矩阵 `matrix` ，请按照 **顺时针螺旋顺序** ，返回矩阵中的所有元素。

![img](asset/Advanced algorithms.assets/spiral.jpg)

```
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
```

---

解题思路:

​	模拟，设置四个边界变量，模拟过程中进行改变。

​	有个大佬的两行python代码，膜拜一下。

```python
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        res = []
        while matrix:
            res += matrix.pop(0)
            matrix = list(zip(*matrix))[::-1]
        return res
```

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int i = 0, j = 0;
        ans.add(matrix[0][0]);
        while (up <= down && left <= right) {
            while (j < right && up <= down) {
                j++;
                ans.add(matrix[i][j]);
            }
            up++;
            while (i < down && left <= right) {
                i++;
                ans.add(matrix[i][j]);
            }
            right--;
            while (j > left && up <= down) {
                j--;
                ans.add(matrix[i][j]);
            }
            down--;
            while (i > up && left <= right) {
                i--;
                ans.add(matrix[i][j]);
            }
            left++;
        }
        return ans;
    }
}
```



## [128. 最长连续序列](https://leetcode-cn.com/problems/longest-consecutive-sequence/)

给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

```
输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
```

---

解题思路:

​	首先想到的思路是排序，时间复杂度是$O(nlog_2(n))$，不符合题意，所以我们考虑其他方法。

​	哈希Set。首先假设我们可以找到所有连续序列的左端点，那么我们只需要统计每个序列的长度，然后返回最长的长度。那么如何找到所有的左端点呢？

​	假设判断当前点为 x ，我们只需要查找 x-1 是否包含在集合中，如果不存在，那么 x 至少是一个左端点（长度可能为 1 ）。如果存在，那么x就是某个子序列的中间一个值，我们只需要跳过这个值即可（在后面的查找中，会找到 x 属于的那个序列的头，然后遍历到 x ）。

​	在找到 x 为左端点之后，向后查找是否存在 x+1，x+2，x+3....记录长度。在向后查找时，使用HashSet，可以保证在查找过程中的复杂度为O(1),那么总的复杂度就为O(n)。

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        int ans = 0;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            int val = iterator.next();
            int tempLong = 0;
            if (!hashSet.contains(val - 1)) {
                do {
                    tempLong++;
                    val++;
                } while (hashSet.contains(val));
            }
            if (tempLong > ans) {
                ans = tempLong;
            }
        }
        return ans;
    }
}
```



## [289. 生命游戏](https://leetcode-cn.com/problems/game-of-life/)

根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。

给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。

![img](asset/Advanced algorithms.assets/grid1.jpg)

```tex
输入：board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
输出：[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
```

---

解题思路:

​	模拟操作即可,主要是原地优化,如果使用额外数组保存并不难,原地优化不可以 `直接`修改,因为附近的细胞需要访问未修改前的状态,所以需要进行优化,这样就可以原地保存下一个状态。

​	因为这道题目的输入是int[][]，而int是可以存储更多的比特位的。原有的最低位存储的是当前状态，那倒数第二低位存储下一个状态就行了。

​	也可以使用额外状态，题目中每个细胞只有两种状态 live(1) 或 dead(0)，但我们可以拓展一些复合状态使其包含之前的状态。举个例子，如果细胞之前的状态是 0，但是在更新之后变成了 1，我们就可以给它定义一个复合状态 2。这样我们看到 2，既能知道目前这个细胞是活的，还能知道它之前是死的。

```java
class Solution {
    public void gameOfLife(int[][] board) {
        // 因为这道题目的输入是int[][]，而int是可以存储更多的比特位的。
        // 原有的最低位存储的是当前状态，那倒数第二低位存储下一个状态就行了。
        int row = board.length;
        int col = board[0].length;
        int[][] dxdy = {
                {-1, 0}, {1, 0}, {0, 1}, {0, -1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int alive = 0;
                for (int k = 0; k < dxdy.length; k++) {
                    int x = i + dxdy[k][0];
                    int y = j + dxdy[k][1];
                    if (x < 0 || x >= row || y < 0 || y >= col) {
                        continue;
                    }
                    //取最后一位
                    if ((board[x][y] & 1) == 1) {
                        alive++;
                    }
                }
                if ((board[i][j] & 1) == 1) {
                    if (alive < 2) {
                        board[i][j] = 0b01;
                    } else if (alive > 3) {
                        board[i][j] = 0b01;
                    } else {
                        board[i][j] = 0b11;
                    }
                } else {
                    if (alive == 3) {
                        board[i][j] = 0b10;
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] >>= 1;
            }
        }
    }
}
```





## [238. 除自身以外数组的乘积](https://leetcode-cn.com/problems/product-of-array-except-self/)

给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

```
输入: [1,2,3,4]
输出: [24,12,8,6]
```

提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。

说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

进阶：
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

----

解题思路:

​	左右乘积列表，我们不必将所有数字的乘积除以给定索引处的数字得到相应的答案，而是利用索引左侧所有数字的乘积和右侧所有数字的乘积（即前缀与后缀）相乘得到答案。

​	对于给定索引 i，我们将使用它左边所有数字的乘积乘以右边所有数字的乘积。

```java
//L_multi [1, 1, 2, 6]
//R_multi [24, 12, 4, 1]
//ans     [24, 12, 8, 6]
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int L_multi[] = new int[nums.length];
        int R_multi[] = new int[nums.length];
        L_multi[0] = 1;
        R_multi[nums.length - 1] = 1;

        for (int i = 1; i < nums.length; i++) {
            L_multi[i] = L_multi[i - 1] * nums[i - 1];
            R_multi[nums.length - i - 1] = R_multi[nums.length - i] * nums[nums.length - i];
        }
        int ans[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = L_multi[i] * R_multi[i];
        }
        return ans;
    }
}
//空间复杂度O(1),将左右数组都保存到ans数组中
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int ans[] = new int[nums.length];
        ans[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        int temp = 1;
        for (int i = nums.length - 1; i >= 0; i--) {

            ans[i] = ans[i] * temp;
            temp *= nums[i];
        }
        return ans;
    }
}
```





## [239. 滑动窗口最大值](https://leetcode-cn.com/problems/sliding-window-maximum/)

给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。

```tex
输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
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

方法一:大顶堆.时间复杂度：$O(n \log n)$

方法二: 维护一个最大值队列。

​	我们可以使用一个队列存储所有还没有被移除的下标。在队列中，这些下标按照从小到大的顺序被存储，并且它们在数组 nums 中对应的值是严格单调递减的。因为如果队列中有两个相邻的下标，它们对应的值相等或者递增，那么令前者为 i，后者为 j，就对应了上面所说的情况，即 nums[i] 会被移除，这就产生了矛盾。

​	当滑动窗口向右移动时，我们需要把一个新的元素放入队列中。为了保持队列的性质，我们会不断地将新的元素与队尾的元素相比较，如果前者大于等于后者，那么队尾的元素就可以被永久地移除，我们将其弹出队列。我们需要不断地进行此项操作，直到队列为空或者新的元素小于队尾的元素。

​	由于队列中下标对应的元素是严格单调递减的，因此此时队首下标对应的元素就是滑动窗口中的最大值。但与方法一中相同的是，此时的最大值可能在滑动窗口左边界的左侧，并且随着窗口向右移动，它永远不可能出现在滑动窗口中了。因此我们还需要不断从队首弹出元素，直到队首元素在窗口中为止。

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}
```









## [287. 寻找重复数](https://leetcode-cn.com/problems/find-the-duplicate-number/)

给定一个==包含 n + 1 个整数的数组 nums== ，其==数字都在 1 到 n 之间（包括 1 和 n）==，可知至少存在一个重复的整数。

假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。

你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。

```tex
输入：nums = [1,3,4,2,2]
输出：2
```

---

解题思路:

方法一: 统计元素次数，使用了额外空间。HashMap统计，最简单。

方法二：快慢指针。

​	题目设定的问题是 N+1 个元素都在 [1,n] 这个范围内。将这个题目给的特殊的数组当作一个链表来看，数组的下标就是指向元素的指针，把数组的元素也看作指针。如 0 是指针，指向 nums[0]，而 nums[0] 也是指针，指向 nums[nums[0]]。

​	假设有这样一个样例：[1,2,3,4,5,6,7,8,9,5]。如果我们按照上面的循环下去就会得到这样一个路径: 1 2 3 4 5 [6 7 8 9] [6 7 8 9] [6 7 8 9] . . .这样就有了一个环，也就是6 7 8 9。

​	slow和fast会在环中相遇，先假设一些量：起点到环的入口长度为m，环的周长为c，在fast和slow相遇时slow走了n步。则fast走了2n步，fast比slow多走了n步，而这n步全用在了在环里循环（n%c==0）。

​	当fast和last相遇之后，我们设置第三个指针finder，它从起点开始和slow(在fast和slow相遇处)同步前进，当finder和slow相遇时，就是在环的入口处相遇，也就是重复的那个数字相遇。

​	fast 和 slow 相遇时，slow 在环中行进的距离是n-m，其中 n%c==0。这时我们再让 slow 前进 m 步——也就是在环中走了 n 步了。而 n%c\==0 即 slow 在环里面走的距离是环的周长的整数倍，就回到了环的入口了，而入口就是重复的数字。

​	我们不知道起点到入口的长度m，所以弄个 finder 和 slow 一起走，他们必定会在入口处相遇。

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (fast != slow);
        //慢指针回到头部
        //快指针在环内循环,一次一步
        //慢指针走到环入口,快指针也到入口
        slow = 0;
        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
}
```



## [454. 四数相加 II](https://leetcode-cn.com/problems/4sum-ii/)

给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

```tex
输入:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

输出:
2

解释:
两个元组如下:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
```

---

解题思路:

​	分组。采用分为两组，HashMap 存一组，另一组和 HashMap 进行比对。

这样的话情况就可以分为三种：
	HashMap 存一个数组，如 A。然后计算三个数组之和，如 BCD。时间复杂度为：O(n)+O(n^3)，得到 O(n^3).
	HashMap 存三个数组之和，如 ABC。然后计算一个数组，如 D。时间复杂度为：O(n^3)+O(n)，得到 O(n^3).
	HashMap存两个数组之和，如AB。然后计算两个数组之和，如 CD。时间复杂度为：O(n^2)+O(n^2)，得到 O(n^2).
根据第二点我们可以得出要存两个数组算两个数组。
	我们以存 AB 两数组之和为例。首先求出 A 和 B 任意两数之和 sumAB，以 sumAB 为 key，sumAB 出现的次数为 value，存入 hashmap 中。然后计算 C 和 D 中任意两数之和的相反数 sumCD，在 hashmap 中查找是否存在 key 为 sumCD。
	算法时间复杂度为 $O(n^2)。$

```java
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
       HashMap<Integer, Integer> ABHashMap = new HashMap<>();
        int ans = 0;

        for (int a : nums1) {
            for (int b : nums2) {
                ABHashMap.put(a + b, ABHashMap.getOrDefault(a + b, 0) + 1);
            }
        }
        for (int c : nums3) {
            for (int d : nums4) {
                if (ABHashMap.containsKey(0 - c - d)) {
                    ans += ABHashMap.get(0 - c - d);
                }
            }
        }
        return ans;
    }
}
```



