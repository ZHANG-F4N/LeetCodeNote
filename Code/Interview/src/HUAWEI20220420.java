import java.util.*;
import java.util.concurrent.locks.Condition;

public class HUAWEI20220420 {
    public static void main(String[] args) {
//        Q1();
//        Q2();
        Q3();
    }

//    //Q1
//    public static int ans;
//    public static HashSet<Integer> used;
//
//    public static void Q1() {
//        Scanner scanner = new Scanner(System.in);
//        int val = scanner.nextInt();
//        ans = 0;
//        used = new HashSet<>();
////        dfs(1, 0, 0, val, 0);
//        dfs(val, 0, 0, 0, 0);
//        System.out.println(ans);
//    }

//    public static void dfs(int i, int score, int kind, int tar, int error) {
////        System.out.println(score);
//
//        if (score > tar || error > 3 || i > 25) return;
//        if (error == 3 && score == tar) {
//            ans++;
//            return;
//        }
//
//        if (i > 25 && score == tar) {
//            ans++;
//            return;
//        } else if (i > 25 && score != tar) return;
//
//
//        if (i <= 10) kind = 1;
//        else if (i > 10 && i <= 20) kind = 2;
//        else kind = 3;
//
//        if (kind == 1) {
//            score += 2;
//        } else if (kind == 2) {
//            score += 4;
//        } else {
//            score += 8;
//        }
//
//        dfs(i + 1, score, kind, tar, error);
//        if (kind == 1) {
//            score -= 2;
//        } else if (kind == 2) {
//            score -= 4;
//        } else {
//            score -= 8;
//        }
//        dfs(i + 1, score, kind, tar, error + 1);
//    }


//    public static void dfs(int score, int curA, int curB, int curC, int cnt) {
//        if (cnt >= 3) return;
//        if (score == 0 && cnt <= 2) {
//            ans++;
//            return;
//        }
//        if (curA < 10) {
//            dfs(score - 2, curA + 1, curB, curC, cnt);
//            dfs(score, curA + 1, curB, curC, cnt + 1);
//        } else if (curB < 10) {
//            dfs(score - 4, curA, curB + 1, curC, cnt);
//            dfs(score, curA, curB + 1, curC, cnt + 1);
//        } else if (curC < 5) {
//            dfs(score - 8, curA, curB, curC + 1, cnt);
//            dfs(score, curA, curB, curC + 1, cnt + 1);
//        }
//        return;
//    }
//
//    public static int combine(int k, int n) {
//        int up = 1;
//        int down = 1;
//        for (int i = 2; i <= k; i++) {
//            down *= i;
//        }
//        for (int i = n; i > (n - k); i--) {
//            up *= i;
//        }
//        return (up / down);
//    }
//
//
//    public static class TreeNode {
//        public int val;
//        public TreeNode left;
//        public TreeNode right;
//
//        public TreeNode(int val) {
//            this.val = val;
//        }
//    }
//
//    public static TreeNode root;
//
//    // Q2
//    public static void Q2() {
//        Scanner scanner = new Scanner(System.in);
//        String line = scanner.nextLine();
//        String[] treeArr = line.substring(1, line.length() - 1).split(",");
//
//        root = new TreeNode(Integer.parseInt(treeArr[0]));
//
//
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        for (int i = 1; i < treeArr.length - 1; i += 2) {
//            TreeNode node = queue.poll();
//            node.left = new TreeNode(Integer.parseInt(treeArr[i]));
//            node.right = new TreeNode(Integer.parseInt(treeArr[i + 1]));
//            if (node.left.val != 0) queue.add(node.left);
//            if (node.right.val != 0) queue.add(node.right);
//        }
//        String regex = scanner.nextLine();
//        TreeNode node = root;
//        for (int i = 3; i < regex.length(); i += 2) {
//            if (node.left.val == regex.charAt(i) - '0') {
//                node = node.left;
//            } else node = node.right;
//        }
//        line = scanner.nextLine();
//
//        String[] subArr = line.substring(1, line.length() - 1).split(",");
//        node.val = Integer.parseInt(subArr[0]);
//        queue = new LinkedList<>();
//
//        queue.add(node);
//
//        for (int i = 1; i < subArr.length - 1; i += 2) {
//            node = queue.poll();
//            node.left = new TreeNode(Integer.parseInt(subArr[i]));
//            node.right = new TreeNode(Integer.parseInt(subArr[i + 1]));
//            if (node.left.val != 0) queue.add(node.left);
//            if (node.right.val != 0) queue.add(node.right);
//        }
//        StringBuilder resSB = new StringBuilder();
//        resSB.append("[");
//
//        queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            node = queue.poll();
//            resSB.append(node.val + ",");
//            if (node.left != null && node.left.val != 0) {
//                queue.add(node.left);
//            }
//            if (node.right != null && node.right.val != 0) {
//                queue.add(node.right);
//            }
//        }
//        System.out.println(resSB.substring(0, resSB.length() - 1) + "]");
//
//    }


    public static class TreeNode {
        public int id;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int id) {
            this.id = id;
        }

        public TreeNode() {
        }
    }

    public static class CostUnit {
        public long cost;
        public long fit;
    }

    public static TreeNode root;
    public static int n;
    public static int[] ids;
    public static int[] fathers;
    public static int count;
    public static long minCost;

    public static void buildTree() {
        minCost = Long.MAX_VALUE;
        HashMap<Integer, TreeNode> helpMap = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            helpMap.computeIfAbsent(ids[i], val -> new TreeNode());
            helpMap.computeIfAbsent(fathers[i], val -> new TreeNode());
            helpMap.get(ids[i]).id = ids[i];
            if (helpMap.get(fathers[i]).left == null) {
                helpMap.get(fathers[i]).left = helpMap.get(ids[i]);
            } else {
                helpMap.get(fathers[i]).right = helpMap.get(ids[i]);
            }
        }
        root = helpMap.get(0).left;
    }

    public static List<CostUnit> dfs(TreeNode node) {
        if (node == null) return new ArrayList<>();
        List<CostUnit> leftCost = dfs(node.left);
        List<CostUnit> rightCost = dfs(node.right);
        ArrayList<CostUnit> res = new ArrayList<>();

        for (CostUnit costUnit : leftCost) {
            CostUnit copy = new CostUnit();
            copy.cost = costUnit.cost;
            copy.fit = costUnit.fit;

            copy.cost += Math.abs(node.id - node.left.id);
            copy.cost += node.id;
            copy.fit += node.id;
            if (copy.fit >= count)
                minCost = Math.min(minCost, copy.cost);
            else
                res.add(copy);
        }
        for (CostUnit costUnit : rightCost) {
            CostUnit copy = new CostUnit();
            copy.cost = costUnit.cost;
            copy.fit = costUnit.fit;

            copy.cost += Math.abs(node.id - node.right.id);
            copy.cost += node.id;
            copy.fit += node.id;
            if (copy.fit >= count)
                minCost = Math.min(minCost, copy.cost);
            else
                res.add(copy);
        }

        for (CostUnit costLeft : leftCost) {
            for (CostUnit costRight : rightCost) {
                CostUnit copy = new CostUnit();
                copy.cost = costLeft.cost + costRight.cost;
                copy.fit = costLeft.fit + costRight.fit;
                copy.cost += Math.abs(node.id - node.right.id);
                copy.cost += Math.abs(node.id - node.left.id);
                copy.cost += node.id;
                copy.fit += node.id;
                if (copy.fit >= count)
                    minCost = Math.min(minCost, copy.cost);
            }
        }
        CostUnit unit = new CostUnit();
        unit.cost = node.id;
        unit.fit = node.id;
        if (unit.fit >= count)
            minCost = Math.min(minCost, unit.cost);
        else
            res.add(unit);

        return res;
    }


    //Q3
    public static void Q3() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        ids = new int[n];
        fathers = new int[n];
        int idx = 0;
        while (idx < n) {
            ids[idx++] = scanner.nextInt();
        }
        idx = 0;
        while (idx < n) {
            fathers[idx++] = scanner.nextInt();
        }
        count = scanner.nextInt();
        buildTree();
        dfs(root);
        System.out.println(minCost == Long.MAX_VALUE ? -1 : minCost);

    }
}
