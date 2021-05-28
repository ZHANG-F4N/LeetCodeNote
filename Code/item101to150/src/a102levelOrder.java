import java.util.*;

public class a102levelOrder {
    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        Integer[] nums = {3,9,20,null,null,15,7};
        root = root.Build(nums);
        levelOrder(root);
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        ArrayDeque<Object> deque1 = new ArrayDeque<>();
        
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while(!deque.isEmpty()){
            ArrayList<Integer> levelList = new ArrayList<>();
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = deque.poll();
                levelList.add(temp.val);
                if(temp.left != null){
                    deque.offer(temp.left);
                }
                if(temp.right != null){
                    deque.offer(temp.right);
                }
            }
            list.add(levelList);
        }
//        Iterator<List<Integer>> it = list.iterator();
//        while(it.hasNext()){
//            System.out.println(it.next());
//        }
        return list;
    }
}
