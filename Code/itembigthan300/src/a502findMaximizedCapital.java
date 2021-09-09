import java.lang.management.MonitorInfo;
import java.util.*;

public class a502findMaximizedCapital {
    public static void main(String[] args) {
        int k = 3, w = 0;
        int[] profits = {1, 2, 3};
        int[] capital = {0, 1, 2};
        System.out.println(findMaximizedCapitalOptimize(k, w, profits, capital));


        int[] nu = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nu));

    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int pre = nums[0];
        int max = pre;
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            if (max < pre) {
                max = pre;
            }
        }
        return max;
    }

    static class Mission {
        int profits;
        int capital;

        public Mission(int profits, int capital) {
            this.profits = profits;
            this.capital = capital;
        }
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;

        PriorityQueue<Mission> missions = new PriorityQueue<>(new Comparator<Mission>() {
            @Override
            public int compare(Mission o1, Mission o2) {
                if (o1.capital != o2.capital) {
                    return o1.capital - o2.capital;
                }
                return o1.profits - o2.capital;

            }
        });
        PriorityQueue<Mission> activeMissions = new PriorityQueue<>(new Comparator<Mission>() {
            @Override
            public int compare(Mission o1, Mission o2) {
                if (o1.profits != o2.profits) {
                    return o2.profits - o1.profits;
                }
                return o1.capital - o2.capital;

            }
        });
        for (int i = 0; i < n; i++) {
            missions.add(new Mission(profits[i], capital[i]));
        }
        int ans = w;
        for (int i = 0; i < k; i++) {
            if ((activeMissions.isEmpty() && missions.isEmpty())) {
                return ans;
            }


            while (!missions.isEmpty() && ans >= missions.peek().capital) {
                activeMissions.add(missions.poll());
            }
            if (!activeMissions.isEmpty()) {
                ans += activeMissions.poll().profits;
            }
        }
        return ans;
    }

    //思路一样，代码优化
    public static int findMaximizedCapitalOptimize(int k, int w, int[] profits, int[] capital) {
        // 快速分支-当初始资本可以开启所有项目时，从最赚钱的项目开始投资，用到优先队列
        int n = profits.length;
        boolean speedUp = true;
        for (int i = 0; i < n; i++) {
            if (w < capital[i]) {
                speedUp = false;
                break;
            }
        }
        if (speedUp) {
            Arrays.sort(profits);
            for (int i = 0; i < Math.min(k, n); i++) {
                w += profits[n - 1 - i];
            }
            return w;
        }

        //用数组操作果然还是快的多。
        for (int i = 0; i < Math.min(k, n); i++) {
            //记录上一个最大资本的位置
            int idx = -1;
            for (int j = 0; j < n; j++) {
                if (w >= capital[j]) {
                    if (idx == -1) {
                        idx = j;
                    } else if (profits[j] > profits[idx]) {
                        idx = j;
                    }
                }
            }
            if (idx == -1) break;
            w += profits[idx];

            capital[idx] = Integer.MAX_VALUE;
        }
        return w;

    }
}
