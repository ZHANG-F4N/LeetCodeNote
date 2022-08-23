import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class a207canFinish {
    public static void main(String[] args) {
        System.out.println(canFinish(5, new int[][]{{1, 4}, {2, 4}, {3, 1}, {3, 2}}));
        System.out.println(canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) return true;
        int[] out = new int[numCourses];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            out[prerequisites[i][0]]++;
            if (hashMap.containsKey(prerequisites[i][1])) hashMap.get(prerequisites[i][1]).add(prerequisites[i][0]);
            else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(prerequisites[i][0]);
                hashMap.put(prerequisites[i][1], list);
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (out[i] == 0) queue.add(i);
        }

        int num = 0;
        while (!queue.isEmpty()) {
            Integer i = queue.poll();
            num++;
            List<Integer> list = hashMap.get(i);
            if (list != null) {
                for (int k = 0; k < list.size(); k++) {
                    out[list.get(k)]--;
                    if (out[list.get(k)] == 0) {
                        queue.add(list.get(k));
                    }
                }
            }

        }
        return num == numCourses;


    }
}
