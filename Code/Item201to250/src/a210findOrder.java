import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class a210findOrder {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findOrder(2, new int[][]{{0, 1}, {1, 0}})));
        System.out.println(Arrays.toString(findOrder(5, new int[][]{{1, 4}, {2, 4}, {3, 1}, {3, 2}})));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];
        int[] ans = new int[numCourses];

        ArrayList<Integer>[] edges = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) edges[i] = new ArrayList<>();

        for (int i = 0; i < prerequisites.length; i++) {
            in[prerequisites[i][0]]++;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) list.add(i);
        }


        int idx = 0;
        while (!list.isEmpty()) {
            for (Integer i : list) {
                ans[idx++] = i;
            }
            List<Integer> nextList = new ArrayList<>();
            for (Integer integer : list) {
                ArrayList<Integer> edge = edges[integer];
                for (int i = 0; i < edge.size(); i++) {
                    in[edge.get(i)]--;
                    if (in[edge.get(i)] == 0) {
                        nextList.add(edge.get(i));
                    }
                }
            }
            list = nextList;
        }
        if (idx != numCourses) return new int[]{};
        return ans;


    }

}
