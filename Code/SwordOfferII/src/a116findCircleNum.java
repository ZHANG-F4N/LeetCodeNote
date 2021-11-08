import java.util.Arrays;

public class a116findCircleNum {
    public static void main(String[] args) {
        System.out.println(findCircleNum(new int[][]{{1, 0, 0, 1}, {0, 1, 1, 0},
                {0, 1, 1, 1}, {1, 0, 1, 1}}));
        System.out.println(findCircleNum(new int[][]{{1, 0, 0, 0, 0}, {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0}, {1, 1, 0, 1, 0}, {0, 0, 1, 0, 1}}));
        System.out.println(findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));

    }

    public static int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;
        int[] original = new int[N];
        for (int i = 0; i < N; i++) {
            original[i] = i;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (isConnected[i][j] == 1) {
                    // 连接节点 i j
                    int left = i;
                    int right = j;
                    // 找左节点的 祖先
                    while (original[left] != left) left = original[left];
                    // 找右节点的 祖先
                    while (original[right] != right) right = original[right];
                    // 找到后将其中一个的祖先赋给另外一个, 这样就可以统一祖先了
                    original[left] = right;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (original[i] == i) {
                ans++;
            }
        }
        return ans;
    }
}
