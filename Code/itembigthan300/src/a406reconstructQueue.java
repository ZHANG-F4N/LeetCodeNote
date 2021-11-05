import java.awt.desktop.AppReopenedEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class a406reconstructQueue {
    public static void main(String[] args) {
        System.out.println(reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1},
                {5, 2}}));
    }

    public static int[][] reconstructQueue(int[][] people) {

        int N = people.length;
        int[][] ans = new int[N][2];

        ArrayList<int[]> list = new ArrayList<>();

        Arrays.sort(people, (o1, o2) -> o1[1] - o2[1]);


        for (int i = 0; i < N; i++) {

            int[] cur = people[i];
            




        }


    }
}
