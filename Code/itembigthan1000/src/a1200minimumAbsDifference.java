import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.MissingFormatArgumentException;

public class a1200minimumAbsDifference {
    public static void main(String[] args) {
        minimumAbsDifference(new int[]{3, 8, -10, 23, 19, -4, -14, 27});
        minimumAbsDifference(new int[]{4, 2, 1, 3});
    }

    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
//        for (int i = 0; i < arr.length; i++) arr[i] = Math.abs(arr[i]);
        Arrays.sort(arr);
        int MIN = Integer.MAX_VALUE;

        for (int i = 0, j = 1; j < arr.length; j++, i++) {
            int diff = arr[j] - arr[i];
            if (diff < MIN) {
                ans = new ArrayList<>();
                MIN = diff;
                ArrayList<Integer> list = new ArrayList<>();
                list.add(arr[i]);
                list.add(arr[j]);
                ans.add(list);
            } else if (diff == MIN){
                ArrayList<Integer> list = new ArrayList<>();
                list.add(arr[i]);
                list.add(arr[j]);
                ans.add(list);
            }
        }
        return ans;
    }

}
