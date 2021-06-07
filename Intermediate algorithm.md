# Intermediate algorithm

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

<img src="Intermediate algorithm.assets/116_sample.png" alt="img" style="zoom:67%;" />

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

![img](Intermediate algorithm.assets/160_example_1.png)

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



## [230. 二叉搜索树中第K小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/)

给定一个二叉搜索树的根节点 `root` ，和一个整数 `k` ，请你设计一个算法查找其中第 `k` 个最小元素（从 1 开始计数）。

![img](Intermediate algorithm.assets/kthtree2.jpg)

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

