import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class a0403listOfDepth {
    public static void main(String[] args) {
        Integer[] leaf = {1, 2, 3, 4, 5, null, 7, 8};
        ListNode[] ans = listOfDepth(new TreeNode().Build(leaf));
    }

    public static ListNode[] listOfDepth(TreeNode tree) {
        ArrayList<ListNode> ans = new ArrayList<>();
        if (tree == null) return ans.toArray(new ListNode[ans.size()]);

        Deque<TreeNode> deque = new ArrayDeque<>();
        ans.add(new ListNode());
        deque.addLast(tree);
        int level = 1;
        while (!deque.isEmpty()) {
            TreeNode temp = deque.pollFirst();
            if (temp.right != null) deque.addLast(temp.right);
            if (temp.left != null) deque.addLast(temp.left);
            ListNode head = ans.get(ans.size() - 1);
            ListNode addNode = new ListNode();
            addNode.val = temp.val;
            addNode.next = head.next;
            head.next = addNode;
            level--;
            if (level == 0) {
                ListNode tempList = ans.get(ans.size() - 1);
                ans.remove(ans.size() - 1);
                ans.add(tempList.next);
                level = deque.size();
                if (level != 0) ans.add(new ListNode());
            }
        }
        return ans.toArray(new ListNode[ans.size()]);
    }

//    public static ListNode reverse(ListNode head) {
//        if (head == null) return null;
//        ListNode temp = new ListNode();
//        temp.next = head;
//        head = temp;
//
//        temp = head.next;
//        head.next = null;
//        while (temp != null) {
//            ListNode node = temp;
//            temp = temp.next;
//            node.next = head.next;
//            head.next = node;
//        }
//        return head.next;
//    }
}
