# Intermediate Algorithm

[TOC]



## [22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/)

数字 `n` 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 **有效的** 括号组合。

```
输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
```

---

解题思路:

使用回溯法，动态生成括号对并加入结果集。

在加入结果集时，生成括号遵循以下约束：

- 如果左括号数量不大于 *n*，我们可以放一个左括号。
- 如果右括号数量小于左括号的数量，我们可以放一个右括号。

```java
class Solution {
    //DFS回溯法
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        backtrack(ans, str, 0, 0, n);
        return ans;
    }
    //ans   ---     结果集
    //str   ---     零时存储中间结果 StringBuilder可变长,线程不安全,最快
    //left  ---     左括号个数
    //right ---     右括号个数
    //n     ---     总括号对数
    public  void backtrack(List<String> ans, StringBuilder str, int left, int right, int n) {
        if (str.length() >= n << 1) {
            ans.add(str.toString());
            return;
        }
        if (left < n) {
            backtrack(ans, str.append('('), left + 1, right, n);
            str.deleteCharAt(str.length() - 1);
        }
        if (right < left) {
            backtrack(ans, str.append(')'), left, right + 1, n);
            str.deleteCharAt(str.length() - 1);
        }
    }
}
```



## [46. 全排列](https://leetcode-cn.com/problems/permutations/)

给定一个不含重复数字的数组 `nums` ，返回其 **所有可能的全排列** 。你可以 **按任意顺序** 返回答案。

```java
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

---

解题思路:

回溯法，采用 试错 的思想，它尝试分步的去解决一个问题。

在分步解决问题的过程中，当它通过尝试发现现有的分步答案不能得到有效的正确的解答的时候，它将取消上一步甚至是上几步的计算，再通过其它的可能的分步解答再次尝试寻找问题的答案。

回溯法通常用最简单的递归方法来实现。

<img src="asset/Intermediate algorithm.assets/0bf18f9b86a2542d1f6aa8db6cc45475fce5aa329a07ca02a9357c2ead81eec1-image.png" alt="image.png" style="zoom: 33%;" />

\[注意]:

​	Java基础打的还不牢固，tempList(临时结果) 在传递入下一次的递归时，需要进行拷贝。如果只传引用，会在再后面覆盖前面的已有结果。

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        //tempList	--	临时结果
        //ansList	--	结果集
        //flag		--	标记数组	
        List tempList = new ArrayList<Integer>();
        List ansList = new ArrayList<List<Integer>>();
        int[] flag = new int[nums.length];
        backtrack(ansList, flag, tempList, nums, 0);
        return ansList;
    }
    public void backtrack(List<List<Integer>> ansList, int[] flag, List<Integer> tempList, int[] nums, int len) {
        if (len == nums.length) {
            ansList.add(tempList);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (flag[i] == 0) {
                tempList.add(nums[i]);
                flag[i] = 1;
                backtrack(ansList, flag, new ArrayList<>(tempList), nums, len + 1);
                flag[i] = 0;
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
```



## [49. 字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/)

给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

```python
输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```

---

解题思路:

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            ArrayList<String> strings;
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            //在Java中，每一个对象的toString方法，
            // 都会打印出“类名@他的hashCode值”这样的一个字符串， getClass().getName() + '@' + Integer.toHexString(hashCode())
            // 数组是“[”这个符号C就代表char，后面的就是这个数组的hashCode值，如果是int类型的数组的话，
            // 就会使[I@*****了
            //strings = hashMap.getOrDefault(temp.toString()), new ArrayList<>());
            // 这种写法错误,key 就变成了[C@10f87f48   [C@b4c966a,
            strings = hashMap.getOrDefault(new String(temp), new ArrayList<>());
            strings.add(strs[i]);
            hashMap.put(new String(temp), strings);
        }
        return new ArrayList<>(hashMap.values());
    }
}
```



给定一个非负整数数组 `nums` ，你最初位于数组的 **第一个下标** 。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个下标。

```java
输入：nums = [3,2,1,0,4]
输出：false
解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
```

---

解题思路:

方法一:

​	从前往后扫描，维护一个可以到达的最大距离maxStep，每走一步，更新当前可以到达的最大距离，如果最大距离可以到终点，则成功；否则返回false。

方法二：

​	从后往前扫描，每个点遍历一遍，如果可以到达每一个点，最后返回到初始点，则成功，否则返回false。

```java
//从前往后,每次更新最大可以到达的距离
class Solution {
    public boolean canJump(int[] nums) {
        int maxStep = 0;
        int len = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (maxStep >= len) {
                return true;
            }
            if (maxStep >= i) {
                if (maxStep < i + nums[i]) {
                    maxStep = nums[i] + i;
                }
            } 
        }
        return false;
    }
}
```

```java
//从后往前,判断每一个点是否可达
class Solution {
    public boolean canJump(int[] nums) {
        int min = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= min) min = i;
        }
        return min == 0;
    }
}
```



## [73. 矩阵置零](https://leetcode-cn.com/problems/set-matrix-zeroes/)

给定一个 $m * n$ 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。

进阶：

一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个仅使用常量空间的解决方案吗？

```python
输入：matrix = [
    [1,1,1],
    [1,0,1],
    [1,1,1]
]
输出：[
    [1,0,1],
    [0,0,0],
    [1,0,1]
]
```

---

解决方法:

​	使用两个集合来记录出现过0的行和列的编号，然后进行遍历置零。


```java
class Solution {
    public void setZeroes(int[][] matrix) {
        //使用数组可以加快速度。
        HashSet<Integer> xHashSet = new LinkedHashSet<>();
        HashSet<Integer> yHashSet = new LinkedHashSet<>(); 
        int xlen = matrix.length;
        int ylen = matrix[0].length;
        for (int i = 0; i < xlen; i++) {
            for (int j = 0; j < ylen; j++) {
                if(matrix[i][j] == 0){
                    xHashSet.add(i);
                    yHashSet.add(j);
                }
            }
        }
        for (int i = 0; i < xlen; i++) {
            for (int j = 0; j < ylen; j++) {
                if(xHashSet.contains(i) || yHashSet.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
```

## [75. 颜色分类](https://leetcode-cn.com/problems/sort-colors/)

给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

- 你可以不使用代码库中的排序函数来解决这道题吗？
- 你能想出一个仅使用常数空间的一趟扫描算法吗？

```java
输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
```

---

解题思路:

两趟扫描:

​	在第一次遍历中，我们将数组中所有的 00 交换到数组的头部。在第二次遍历中，我们将数组中所有的 11 交换到头部的 00 之后。此时，所有的 22 都出现在数组的尾部，这样我们就完成了排序。

一趟扫描:

​	我们也可以考虑使用指针$ p_0$来交换 0，$p_2$来交换 2。此时，$p_0$的初始值仍然为 0，而 $p_2$的初始值为 n-1。在遍历的过程中，我们需要找出所有的 0 交换至数组的头部，并且找出所有的 2 交换至数组的尾部。

我们从左向右遍历整个数组，设当前遍历到的位置为 i，对应的元素为$ \textit{nums}[i]$

​	如果找到了 0，那么与前面两种方法类似，将其与$ \textit{nums}[p_0]$进行交换，并将 $p_0$向后移动一个位置；

​	如果找到了 2，那么将其与$ \textit{nums}[p_2]$ 进行交换，并将$ p_2$向前移动一个位置。挪完如果换出来的不是 1，那么指针要回退，因为 0 和 2 都是需要再次移动的。

```java
class Solution {
    public void sortColors(int[] nums) {
        int point_0 = 0;
        int point_2 = nums.length - 1;
        for (int i = 0; i <= point_2; i++) {
            while (i < point_2 && nums[i] == 2) {
                int temp = nums[point_2];
                nums[point_2] = nums[i];
                nums[i] = temp;
                point_2--;
             }
            if (nums[i] == 0) {
                int temp = nums[point_0];
                nums[point_0] = nums[i];
                nums[i] = temp;
                point_0++;
            }
        }
    }
}
```



## [78. 子集](https://leetcode-cn.com/problems/subsets/)

给你一个整数数组 `nums` ，数组中的元素 **互不相同** 。返回该数组所有可能的子集（幂集）。

解集 **不能** 包含重复的子集。你可以按 **任意顺序** 返回解集。

```python
输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
```

---

解题思路:

时间复杂度：$O(n \times 2^n)$

方法一：

​	对每一个元素`nums[i]`，包含`nums[i]`的子集是`nums[0]~nums[i-1]`的每个子集加上元素`nums[i]`。

方法二：

​	迭代法实现子集枚举,记原序列中元素的总数为 n。原序列中的每个数字 $a_i$的状态可能有两种，即「在子集中」和「不在子集中」。我们用 1 表示「在子集中」，0 表示不在子集中，那么每一个子集可以对应一个长度为 n 的 0/1 序列，第 i 位表示 $a_i$ 是否在子集中。例如，$n = 3，a = \{ 1, 2, 3 \}$ 时：

| 0/1 **序列** | **子集** | 0/1 **序列对应的二进制数** |
| :----------: | :------: | :------------------------: |
|     000      |    {}    |             0              |
|     001      |   {1}    |             1              |
|     010      |   {2}    |             2              |
|     011      |  {1,2}   |             3              |
|     ...      |   ...    |            ...             |
|     111      | {1,2,3}  |             7              |

​	可以发现 0/1 序列对应的二进制数正好从 0 到$ 2^n - 1$。我们可以枚举$ \textit{mask} \in [0, 2^n - 1] $，mask 的二进制表示是一个 0/1 序列，我们可以按照这个 0/1 序列在原集合当中取数。当我们枚举完所有 $2^n$个 mask，我们也就能构造出所有的子集。

```java
//方法一
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ansList = new ArrayList<>();
        ansList.add(new ArrayList<Integer>());
        for (int i = 0; i < nums.length; i++) {
            int subsetNum = ansList.size();
            for (int j = 0; j < subsetNum; j++) {
                List<Integer> tempList =  new ArrayList<>(ansList.get(j));
                tempList.add(nums[i]);
                ansList.add(tempList);
            }
        }
        return ansList;
    }
}
//方法二
class Solution {
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }
}
```



## [103. 二叉树的锯齿形层序遍历](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/)

给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],

        3
       / \
      9  20
        /  \
       15   7
    返回锯齿形层序遍历如下：
    [
      [3],
      [20,9],
      [15,7]
    ]
---

解题思路:

​	使用队列存储每次访问的节点，在每层访问时使用 size 统计队列中节点个数，然后使用节点个数来遍历这一层，然后每一层使用不同头尾插入方法，来实现锯齿遍历。

```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ansList = new ArrayList<>();
        Deque<TreeNode> nodeDeque = new ArrayDeque<>();
        if (root == null) {
            return ansList;
        }
        nodeDeque.push(root);
        int level = 0;
        while (!nodeDeque.isEmpty()) {
            int size = nodeDeque.size();
            Deque<Integer> list = new ArrayDeque<>();
            for (int i = 0; i < size; i++) {
                TreeNode temp = nodeDeque.poll();
                if (level % 2 == 0) {
                    list.offerLast(temp.val);
                } else {
                    list.offerFirst(temp.val);
                }
                if (temp.left != null) {
                    nodeDeque.offer(temp.left);
                }
                if (temp.right != null) {
                    nodeDeque.offer(temp.right);
                }
            }
            level++;
            ansList.add(new ArrayList<>(list));
        }
        return ansList;
    }
}
```



## [105. 从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

根据一棵树的前序遍历与中序遍历构造二叉树。

**注意:**
你可以假设树中没有重复的元素。

例如，给出

```python
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
```

返回如下的二叉树：

```python
    3
   / \
  9  20
    /  \
   15   7
```

---

解题思路:

对于任意一颗树而言，前序遍历的形式总是

```java
[ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
```

而中序遍历的形式总是

```java
[ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
```

只要我们在中序遍历中**定位**到根节点，那么我们就可以分别知道左子树和右子树中的节点数目。

在中序遍历中对根节点进行定位时，一种简单的方法是直接扫描整个中序遍历的结果并找出根节点，但这样做的时间复杂度较高。我们可以考虑使用哈希表来帮助我们快速地定位根节点。

```java
class Solution {
    private HashMap<Integer, Integer> indexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root;
        int n = preorder.length;
        //树中没有重复元素
        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        root = myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
        return root;
    }
    public  TreeNode myBuildTree(int[] preorder, int[] inorder, int pleft, int pright, int ileft, int iright) {
        if (pleft > pright) {
            return null;
        }
        if (ileft > iright) {
            return null;
        }
        TreeNode treeNode = new TreeNode(preorder[pleft]);
        int rootIndex = indexMap.get(preorder[pleft]);
        int leftSize = rootIndex - ileft;
        
        treeNode.left = myBuildTree(preorder, inorder, pleft + 1, pleft + leftSize, ileft, rootIndex - 1);
        treeNode.right = myBuildTree(preorder, inorder, pleft + leftSize + 1, pright, rootIndex + 1, iright);
        return treeNode;
    }
}
```

## [116. 填充每个节点的下一个右侧节点指针](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/)

给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

```c++
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```

填充它的每个 *next* 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 *next* 指针设置为 *null*。

初始状态下，所有 *next* 指针都被设置为*null*。

<img src="asset/Intermediate algorithm.assets/116_sample.png" alt="img" style="zoom:67%;" />

```java
输入：root = [1,2,3,4,5,6,7]
输出：[1,#,2,3,#,4,5,6,7,#]
解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
```

---

解题思路:

​	方法一：层次遍历

​	层次遍历基于广度优先搜索，它与广度优先搜索的不同之处在于，广度优先搜索每次只会取出一个节点来拓展，而层次遍历会每次将队列中的所有元素都拿出来拓展，这样能保证每次从队列中拿出来遍历的元素都是属于同一层的

​	方法二: **递归使用已建立的 next 指针**

```java
root.left.next = root.right;
root.right.next = (root.next == null ? null:root.next.left);
connect(root.left);
connect(root.right);
```

```java
//迭代,层次遍历
class Solution {
    public Node connect(Node root) {
        ArrayDeque<Node> nodeDeque = new ArrayDeque<>();
        nodeDeque.offer(root);
        while(!nodeDeque.isEmpty()){
            int size = nodeDeque.size();
            for (int i = 0; i < size; i++) {
                Node temp = nodeDeque.poll();
                if (nodeDeque.isEmpty() || i == size-1){
                    temp.next = null;
                }else {
                    temp.next = nodeDeque.peek();
                }
                if (temp.left != null){
                    nodeDeque.offer(temp.left);
                }
                if (temp.right != null){
                    nodeDeque.offer(temp.right);
                }
            }
        }
    }
}
```

```java
//递归
//使用了已经建立的next指针
class Solution {
    public Node connect(Node root) {
        if (root == null){
            return root;
        }
        if (root.left!=null){
            root.left.next = root.right;
            root.right.next = (root.next == null ? null:root.next.left);
            connect(root.left);
            connect(root.right);
        }
        return root;
    }
}
```





## [160. 相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)

编写一个程序，找到两个单链表相交的起始节点。

![img](asset/Intermediate algorithm.assets/160_example_1.png)

```java
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```

---

解题思路:

方法一:

​	可以先把两个链表长度测出来,然后较长的一方可以先走差值

​	然后再进行两两比较.
方法二:

​	链表A和链表B同时向后走,两个各自到达尾结点后转向对方的头结点

​	如果相交,则会在第一个相交节点相遇


```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode APoint = headA;
        ListNode BPoint = headB;
        while (APoint != null || BPoint != null) {
            if (APoint == BPoint) {
                return APoint;
            }
            if (APoint == null) {
                APoint = headB;
            } else {
                APoint = APoint.next;
            }
            if (BPoint == null) {
                BPoint = headA;
            } else {
                BPoint = BPoint.next;
            }
        }
        return null;
    }
}
```

## [162. 寻找峰值](https://leetcode-cn.com/problems/find-peak-element/)

峰值元素是指其值大于左右相邻值的元素。

给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。

```
输入：nums = [1,2,1,3,5,6,4]
输出：1 或 5 
解释：你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。
```

---

解题思路:

方法一:

​	线性扫描，时间复杂度*O(n)*。

​	利用了连续的两个元素 nums[j] 和 nums[j+1] 不会相等这一事实。于是，我们可以从头开始遍历 nums 数组。每当我们遇到数字 nums[i]，只需要检查它是否大于下一个元素 nums[i+1] 即可判断 nums[i] 是否是峰值。

​	由于“遍历会到达第i个元素”本身就说明上一个元素（第i- 1个）不满足 nums[i] > nums[i + 1] 这一条件，也就说明 nums[i-1] < nums[i]。

<img src="asset/Intermediate algorithm.assets/802bad70c4444bf708f4c63e30e054a33c27ace43b3c7b4fa64a0ffb8201fb7d-image.png" alt="image.png" style="zoom: 67%;" />

方法二：

​	二分查找。时间复杂度$O(log_2{n})$。

​	在简单的二分查找中，我们处理的是一个有序数列，并通过在每一步减少搜索空间来找到所需要的数字。在本例中，我们对二分查找进行一点修改。首先从数组 nums 中找到中间的元素 mid。若该元素恰好位于降序序列或者一个局部下降坡度中（通过将 nums[i] 与右侧比较判断)，则说明峰值会在本元素的左边。于是，我们将搜索空间缩小为 mid 的左边(包括其本身)，并在左侧子数组上重复上述过程。

​	若该元素恰好位于升序序列或者一个局部上升坡度中（通过将 nums[i] 与右侧比较判断)，则说明峰值会在本元素的右边。于是，我们将搜索空间缩小为 mid 的右边，并在右侧子数组上重复上述过程。

​	就这样，我们不断地缩小搜索空间，直到搜索空间中只有一个元素，该元素即为峰值元素。

```java
//二分扫描
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (nums[mid] < nums[mid + 1]) {
                //注意这一步
                //mid和下一个元素比较,左边右移一个
                left = mid + 1;
                continue;
            }
            //注意这一步
            right = mid;
        }
        return left;
    }
}
```

```java
//线性扫描
class Solution {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }
}
```



## [171. Excel表列序号](https://leetcode-cn.com/problems/excel-sheet-column-number/)

给定一个Excel表格中的列名称，返回其相应的列序号。

例如，

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...

```java
输入: "AB"
输出: 28
```

---

解题思路:

​	类似于进制转换，在计算时可以优化。

```java
class Solution {
    public int titleToNumber(String columnTitle) {
        //ZY = 26 * 26 + 25
        //AAA = 1*26*26 + 1*26 + 1 = (1*26+1)*26 + 1
        //ABCD = 1*26*26*26 + 2*26*26 + 3*26 + 4 = (((1)*26+2)*26+3)*26 + 4 = 19010
        int ans = 0;
        int index = columnTitle.length();
        for (int i = 0; i < index; i++) {
            ans = ans * 26 + columnTitle.charAt(i) - 'A' + 1;
        }
        return ans;
    }
}
```



## [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)

给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。

```java
输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出：3
```

---

解题思路:

方法一: BFS搜索

方法二: DFS搜索

方法三: 并查集

\[注意]:

​	搜索方向为上下左右，岛屿的个数就是DFS或者BFS函数调用的次数，即就是 连通分量个数 。

​	对于数组下标的保存，可以使用 $i * col + j$ 来将二维下标转换成一个唯一整数，也可以使用一个数组 new int[]{i, j} 来保存。

```java
//BFS
class Solution {
    public int numIslands(char[][] grid) {
        int ans = 0;
        int col = grid[0].length;
        int row = grid.length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    grid[i][j] = '0';
                    ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
                    arrayDeque.offer(i * col + j);
                    while (!arrayDeque.isEmpty()) {
                        int temp = arrayDeque.poll();
                        int iTemp = temp / col;
                        int jTemp = temp % col;

                        if (iTemp - 1 >= 0 && grid[iTemp - 1][jTemp] == '1') {
                            arrayDeque.offer((iTemp - 1) * col + jTemp);
                            grid[iTemp - 1][jTemp] = '0';
                        }
                        if (iTemp + 1 < row && grid[iTemp + 1][jTemp] == '1') {
                            arrayDeque.offer((iTemp + 1) * col + jTemp);
                            grid[iTemp + 1][jTemp] = '0';
                        }
                        if (jTemp - 1 >= 0 && grid[iTemp][jTemp - 1] == '1') {
                            arrayDeque.offer(iTemp * col + jTemp - 1);
                            grid[iTemp][jTemp - 1] = '0';
                        }
                        if (jTemp + 1 < col && grid[iTemp][jTemp + 1] == '1') {
                            arrayDeque.offer(iTemp * col + jTemp + 1);
                            grid[iTemp][jTemp + 1] = '0';
                        }

                    }
                }
            }
        }
        return ans;
    }
}
```

```java
//DFS
class Solution {
    public int numIslands(char[][] grid) {
        int ans = 0;
        int col = grid[0].length;
        int row = grid.length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    DFS(grid, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }
    public  void DFS(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }
        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            grid[i - 1][j] = '0';
            DFS(grid, i - 1, j);
        }
        if (i + 1 < grid.length && grid[i + 1][j] == '1') {
            grid[i + 1][j] = '0';
            DFS(grid, i + 1, j);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            grid[i][j - 1] = '0';
            DFS(grid, i, j - 1);
        }
        if (j + 1 < grid[0].length && grid[i][j + 1] == '1') {
            grid[i][j + 1] = '0';
            DFS(grid, i, j + 1);
        }
    }
}
```





## [202. 快乐数](https://leetcode-cn.com/problems/happy-number/)

编写一个算法来判断一个数 n 是不是快乐数。

「快乐数」定义为：

​	对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
​	如果 可以变为  1，那么这个数就是快乐数。
​	如果 n 是快乐数就返回 true ；不是，则返回 false 。

```java
输入：19
输出：true
解释：
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

输入：n = 2
输出：false
```

---

解题思路:

有3种情况:
	第一种:收敛回到1。
	第二种:最后产生循环。
	第三种:不收敛，到无限大。

​	对于 3 位数的数字，它不可能大于 (999的快乐数243) 243。这意味着它要么被困在 243 以下的循环内，要么跌到 1。
​	4 位或 4 位以上的数字在每一步都会丢失一位,这是因为每个位数的平方最大为81，不可能达到三位,所以位数会越来越少,直到降到 3 位为止。但它不会无限期地进行下去，所以我们排除第三种选择。

```java
class Solution {
    public  int getNext(int val) {
        int ans = 0;
        //用val / 10 == 0 判断需要最后判断一位数
        while (val > 0) {
            ans += (val % 10) * (val % 10);
            val /= 10;
        }
        return ans;
    }
    //HashSet判断有没有重复元素。
    //HashSet查重会慢一点
    public boolean isHappy(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        while (!hashSet.contains(getNext(n)) && n != 1) {
            hashSet.add(getNext(n));
            n = getNext(n);
        }
        return n == 1;
    }
    //快慢指针判断是否存在循环
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && fast != slow) {
            fast = getNext(getNext(fast));
            slow = getNext(slow);
        }
        return fast == 1;
    }
}
```



## [215. 数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)

在未排序的数组中找到第 **k** 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

```java
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
```

---

解题思路:

方法一:

​	快速排序变形。

​	平均时间复杂度 *O(n)*

方法二：

​	大顶堆。我们也可以使用堆排序来解决这个问题——建立一个大根堆，做 *k*−1 次删除操作后堆顶元素就是我们要找的答案。

​	时间复杂度：$O(n \log n)$

方法三:

​	排序。

​	时间复杂度：$O(n \log n)$

```java
//自己实现大顶堆MaxHeap
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        //移除前K-1大的数
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            heapSize--;
            swap(nums, 0, i);
            adjustMaxHeap(nums, 0, heapSize);
        }

        return nums[0];
    }
    public void buildMaxHeap(int[] a, int heapSize) {
        //从第一个拥有子节点的位置开始调整。
        for (int i = (heapSize >> 1); i >= 0; --i) {
            adjustMaxHeap(a, i, heapSize);
        }

    }
    //调整大顶堆
    // i--局部堆头结点
    // heapSize--堆尾结点
    public  void adjustMaxHeap(int[] a, int i, int heapSize) {
        int leftChild = i * 2 + 1;
        int rightChild = i * 2 + 2;
        int bigNode = i;
        if (leftChild < heapSize && a[leftChild] > a[bigNode]) {
            bigNode = leftChild;
        }
        if (rightChild < heapSize && a[rightChild] > a[bigNode]) {
            bigNode = rightChild;
        }
        if (bigNode != i) {
            swap(a, bigNode, i);
            adjustMaxHeap(a, bigNode, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
```

```java
//使用自带的优先队列API
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (heap.size() == k) {
                if (heap.peek() <= nums[i]) {
                    heap.poll();
                    heap.offer(nums[i]);
                }
            } else {
                heap.offer(nums[i]);
            }
        }
        return heap.peek();
    }
}
```



## [230. 二叉搜索树中第K小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/)

给定一个二叉搜索树的根节点 `root` ，和一个整数 `k` ，请你设计一个算法查找其中第 `k` 个最小元素（从 1 开始计数）。

![img](asset/Intermediate algorithm.assets/kthtree2.jpg)

```python
输入：root = [5,3,6,2,4,null,null,1], k = 3
输出：3
```

---

解题思路:

​	DFS搜索 + 剪枝。

​	中序(左-中-右)深度优先搜索BST(二叉搜索树)，搜索序列有序。所以使用中序搜索，并且维护全局变量`count`，`count`表示已经搜索过的节点个数，也就是序号了，在 $count = k$ 时停止搜索。

```java

class Solution {
    private  int count = 0;
    private  int ans = 0;
    public int kthSmallest(TreeNode root, int k) {
        inOrderDFS(root,k);
        return ans;
    }
    public  void inOrderDFS(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        inOrderDFS(root.left, k);
        count++;
        if (count == k){
            ans = root.val;
            return;
        }
        inOrderDFS(root.right,k);
    }
}
```



编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

- 每行的元素从左到右升序排列。
- 每列的元素从上到下升序排列。

![img](asset/Intermediate algorithm.assets/searchgrid2.jpg)

```python
输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
输出：true
```

---

解题思路:

方法一:

​	暴力，复杂度$O(m \times n)$

方法二:

​	搜索空间的缩减。时间复杂度：$O(nlgn)$。

​	我们可以将已排序的二维矩阵划分为四个子矩阵，其中两个可能包含目标，其中两个肯定不包含。

方法三:

​	主动寻找元素。时间复杂度：*O(n+m)*。

​	首先，我们初始化一个指向矩阵左下角的 (row，col)指针。然后，直到找到目标并返回 true（或者指针指向矩阵维度之外为止）。我们执行以下操作：

​	如果当前指向的值大于目标值，则可以 “向上” 移动一行。 

​	否则，如果当前指向的值小于目标值，则可以移动一列。

​	不难理解为什么这样做永远不会删减正确的答案；因为行是从左到右排序的，所以我们知道当前值右侧的每个值都较大。 因此，如果当前值已经大于目标值，我们知道它右边的每个值会比较大。也可以对列进行非常类似的论证，因此这种搜索方式将始终在矩阵中找到目标（如果存在）。

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

方法一:

​	动态规划，时间复杂度$O(n^2)$。 定义 dp[i] 为考虑前 i 个元素，以第 i 个数字结尾的最长上升子序列的长度，注意 nums[i] 必须被选取。

​	我们从小到大计算 dp 数组的值，在计算 dp[i] 之前，我们已经计算出 dp[0…i−1] 的值，则状态转移方程为：
$$
dp[i]=max(dp[j])+1, 其中0≤j<i 且 num[j]<num[i]
$$
即考虑往 dp[0…i−1] 中最长的上升子序列后面再加一个 nums[i]。由于dp[j] 代表 nums[0…j] 中以 nums[j] 结尾的最长上升子序列，所以如果能从 dp[j] 这个状态转移过来，那么 nums[i] 必然要大于 nums[j]，才能将 nums[i] 放在 nums[j] 后面以形成更长的上升子序列。

​	最后，整个数组的最长上升子序列即所有 \textit{dp}[i]dp[i] 中的最大值。

方法二:

​	贪心+二分，时间复杂度$O(nlog_2{n})$。

```java
//DP
class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int ans = 1;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
        }
        for (int i = 0; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = ans < dp[i] ? dp[i] : ans;
        }
        return ans;
    }
}
```



## [322. 零钱兑换](https://leetcode-cn.com/problems/coin-change/)

给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

你可以认为每种硬币的数量是无限的。

```java
输入：coins = [1, 2, 5], amount = 11
输出：3 
解释：11 = 5 + 5 + 1
```

---

解题思路:

​	完全背包问题。动态规划解决，创建DP数组dp[amount+1]，dp[i] 表示总金额为i时，需要的最少硬币数。状态转移方程为:
$$
dp[i] = \min_{j = 0,1,...n-1}(dp[i-coins[j]]+1)
$$
相当于测试了可以凑出当金额 *i* 的组成可能中(i-coins[j])，需要金币数最小的那个。

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int max = amount+1;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = max;
        }
        //API自带,但费时间
        //Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i - coins[j]]+1, dp[i]) ;
                }
            }
        }
        return dp[amount] > amount ? -1 :dp[amount];
    }
}
```



## [328. 奇偶链表](https://leetcode-cn.com/problems/odd-even-linked-list/)

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

---

解题思路:

​	对于原始链表，每个节点都是奇数节点或偶数节点。头节点是奇数节点，头节点的后一个节点是偶数节点，相邻节点的奇偶性不同。

​	因此可以将奇数节点和偶数节点分离成奇数链表和偶数链表，然后将偶数链表连接在奇数链表之后，合并后的链表即为结果链表。

```java
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oddPoint;//奇数
        ListNode evenPoint;//偶数
        oddPoint = head;
        evenPoint = head.next;
        ListNode oddEndPoint = oddPoint;
        ListNode evenEndPoint = evenPoint;
        ListNode temp = evenPoint.next;
        boolean flag = true;//true - odd ;false = even;
        while (temp != null) {
            if (flag) {
                oddEndPoint.next = temp;
                oddEndPoint = oddEndPoint.next;
            } else {
                evenEndPoint.next = temp;
                evenEndPoint = evenEndPoint.next;
            }
            temp = temp.next;
            flag = !flag;
        }
        evenEndPoint.next=null;
        oddEndPoint.next = evenPoint;
        return head;
    }
}
```



## [334. 递增的三元子序列](https://leetcode-cn.com/problems/increasing-triplet-subsequence/)

给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。

如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。

```python
输入：nums = [2,1,5,0,4,6]
输出：true
解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
```

---

解题思路:

方法一：

​	暴力，三次遍历，时间复杂度 $O(n^3)$

方法二：

1. 新建两个变量 `small` 和 `mid` ，分别用来保存题目要我们求的长度为 3 的递增子序列的最小值和中间值。  
2. 遍历数组，每遇到一个数字，我们将它和 small 和 mid 相比，若小于等于 small ，则替换 small；否则，若小于等于 mid，则替换 mid；否则，若大于 mid，则说明我们找到了长度为 3 的递增数组！

- 当已经找到了长度为 2 的递增序列，这时又来了一个比 small 还小的数字，为什么可以直接替换 small 呢，这样 small 和 mid 在原数组中并不是按照索引递增的关系呀？

  ​	即使我们更新了 small ，这个 small 在 mid 后面，没有严格遵守递增顺序，但它隐含着的真相是，有一个比 small 大比 mid 小的前·最小值出现在 mid 之前。因此，当后续出现比 mid 大的值的时候，我们一样可以通过当前 small 和 mid 推断的确存在着长度为 3 的递增序列。

  ​	假如当前的 small 和 mid 为 [3, 5]，这时又来了个 1。假如我们不将 small 替换为 1，那么，当下一个数字是 2，后面再接上一个 3 的时候，我们就没有办法发现这个 [1,2,3] 的递增数组了！也就是说，我们替换最小值，是为了后续能够更好地更新中间值！

```java
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]<=small){
                small = nums[i];
            }else if (nums[i]<=mid){
                mid = nums[i];
            }else {
                return true;
            }
        }
        return false;
    }
}
```

## [347. 前 K 个高频元素](https://leetcode-cn.com/problems/top-k-frequent-elements/)

给你一个整数数组 `nums` 和一个整数 `k` ，请你返回其中出现频率前 `k` 高的元素。你可以按 **任意顺序** 返回答案。

```java
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
```

---

解题思路:

方法一:

​	大顶堆。先使用HashMap统计所有的元素以及出现的频率，将频率前K大的元素组成大顶堆，然后将大顶堆元素按序放入数组中即可。

​	时间复杂度：$O(nlog(k))$

方法二：

​	快速排序。

​	时间复杂度：$O(n^2)$ 

​	平均时间复杂度：$O(n)$

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
           /*
           Parameters:
            o1 - the first object to be compared.
            o2 - the second object to be compared.
           Returns:
            a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
            从小到大排序 o1 - o2
            从大到小排序 o2 - o1
            */
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
//
        });

        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            int item = entry.getKey();
            int freq = entry.getValue();
            if (priorityQueue.size() == k){
                if (priorityQueue.peek()[1] < freq){
                    priorityQueue.poll();
                    priorityQueue.offer(new int[]{item,freq});
                }
            }else {
                priorityQueue.offer(new int[]{item,freq});
            }
        }
        int[] ans=new int[k];
        for (int i = k-1; i >=0; i--) {
            ans[i] = priorityQueue.poll()[0];
        }
        return ans;
    }
}
```

