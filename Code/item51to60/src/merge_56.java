import java.util.*;

public class merge_56 {
    // Ctrl+Alt+L格式化代码
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};
//        int[][] intervals = {{1,4},{4,5}};
        merge(intervals);
    }

    public static int[][] merge(int[][] intervals) {
//        //冒泡排序
//        for (int i = 0; i < intervals.length - 1; i++) {
//            for (int j = 0; j < intervals.length - i - 1; j++) {
//                if (intervals[j][0] > intervals[j + 1][0]) {
//                    int[] temp = intervals[j];
//                    intervals[j] = intervals[j + 1];
//                    intervals[j + 1] = temp;
//                }
//            }
//        }

        Arrays.sort(intervals,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        ArrayList<int[]> List = new ArrayList();
        List.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= ( List.get(List.size() - 1))[1]) {
                ( List.get(List.size() - 1))[1] = Math.max(( List.get(List.size() - 1))[1], intervals[i][1]);
            } else {
                List.add(intervals[i]);
            }
        }

//        int[][] ans =
//        for (int i = 0;i<ans.length;i++){
//            System.out.println(ans[i][0]+"\t"+ans[i][1]);
//        }
        return List.toArray(new int[List.size()][]);
    }
}
