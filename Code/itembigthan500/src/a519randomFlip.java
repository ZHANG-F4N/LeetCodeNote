import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class a519randomFlip {
    public static void main(String[] args) {
        Solution obj = new Solution(3, 2);
        int[] param_1 = obj.flip();
        obj.reset();
    }

    static class Solution {
        int m;
        int n;
        int cnt;// cnt 为剩余数个数，同时 cnt - 1 为区间右端点位置
        Map<Integer, Integer> map = new HashMap<>();
        Random random = new Random(300);

        public Solution(int m, int n) {
            this.m = m;
            this.n = n;
            this.cnt = m * n;
        }

        public int[] flip() {
            int x = random.nextInt(cnt--);// 在 0 ~ cnt-1中生成一个随机数
            // 判断是否这个点访问过
            // · 未访问过的话直接使用
            // · 访问就用最后一点，然后把最后一点更新
            int idx = map.getOrDefault(x, x);
            map.put(x, map.getOrDefault(cnt, cnt));
            return new int[]{idx / n, idx % n};
        }

        public void reset() {
            map.clear();
            cnt = m * n;
        }
    }
}
