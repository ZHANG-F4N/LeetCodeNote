import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode1024a3 {
    public static void main(String[] args) {
//        int[] parents = {-1, 2, 0, 2, 0};
        int[] parents = {-1, 2, 0};
        System.out.println(countHighestScoreNodes(parents));
    }

    public static int countHighestScoreNodes(int[] parents) {
        // 并查集思想


        // 统计每个节点的子节点个数
        int N = parents.length;
        int[] nodeNum = new int[N];
        for (int i = 0; i < N; i++) {
            if (parents[i] == -1) {
                continue;
            }
            nodeNum[parents[i]]++;
        }
        for (int i = nodeNum.length - 1; i >= 0; i--) {
            if (parents[i] == -1) {
                continue;
            }
            nodeNum[parents[i]] += nodeNum[i];
        }
        int[][] son = new int[N][2];
        for (int i = 0; i < N; i++) {
            Arrays.fill(son[i], -1);
        }
        int ans = 0;
        for (int i = 1; i < N; i++) {
            if (son[parents[i]][0] == -1) {
                son[parents[i]][0] = i;
            } else {
                son[parents[i]][1] = i;
            }
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            int score = 0;
            if (son[i][0] == -1 && son[i][1] == -1) {
                score = nodeNum[i];
            } else if (son[i][0] == -1) {
                score = nodeNum[i] * nodeNum[son[i][1]];
            } else if (son[i][1] == -1) {
                score = nodeNum[i] * nodeNum[son[i][0]];
            } else {
                score = nodeNum[i] * nodeNum[son[i][0]] * nodeNum[son[i][1]];
            }
            if (score == max) {
                ans++;
            }
            if (score > max) {
                max = score;
                ans = 1;
            }
        }

        return ans;

    }


}
