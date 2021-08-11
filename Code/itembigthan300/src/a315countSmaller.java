import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class a315countSmaller {
    public static void main(String[] args) {
        int[] nums = {2, 0, 1};

        List<Integer> ans = countSmaller(nums);
        Iterator<Integer> it = ans.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
    }

    public static List<Integer> countSmaller(int[] nums) {

//        if (nums.length == 0) {
//            return null;
//        }

        int[] ans = new int[nums.length];
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int j = i + 1;
            int x = 0;
            while (j < nums.length && nums[i] <= nums[j]) {
                j++;
                x++;
            }
            if (j == nums.length) {
                ans[i] = x;
            } else {
                ans[i] = ans[j] + x;
            }
        }

        for (int i = 0; i < ans.length; i++) {
            arrayList.add(ans[i]);
        }
        return arrayList;


    }
}
