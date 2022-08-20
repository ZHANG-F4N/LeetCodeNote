import java.util.HashMap;
import java.util.Map;

public class a1224maxEqualFreq {
    public static void main(String[] args) {
        System.out.println(maxEqualFreq(new int[]{2, 2, 1, 1, 5, 3, 3, 5}));
    }


    public static int maxEqualFreq(int[] nums) {
        HashMap<Integer, Integer> cnt = new HashMap<>();
        HashMap<Integer, Integer> freq = new HashMap<>();

        int n = nums.length, maxfreq = 0, res = 0;
        for (int i = 0; i < n; i++) {
            if (cnt.getOrDefault(nums[i], 0) > 0) {
                freq.put(cnt.get(nums[i]), freq.get(cnt.get(nums[i])) - 1);
            }
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
            maxfreq = Math.max(maxfreq, cnt.get(nums[i]));
            freq.put(cnt.get(nums[i]), freq.getOrDefault(cnt.get(nums[i]), 0) + 1);
            if (maxfreq == 1) res = i + 1;
            if (maxfreq * freq.get(maxfreq) + (maxfreq - 1) * freq.getOrDefault(maxfreq - 1, 0) == i + 1 && freq.get(maxfreq) == 1)
                res = i + 1;
            if (maxfreq * freq.get(maxfreq) + 1 == i + 1 && freq.get(1) == 1) res = i + 1;
        }
        return res;
//        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
//        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
//        int res = 0, maxFreq = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (count.getOrDefault(nums[i], 0) > 0) {
//                freq.put(count.get(nums[i]), freq.get(count.get(nums[i])) - 1);
//            }
//            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
//            maxFreq = Math.max(maxFreq, count.get(nums[i]));
//            freq.put(count.get(nums[i]), freq.getOrDefault(count.get(nums[i]), 0) + 1);
//            boolean ok = maxFreq == 1 ||
//                    freq.get(maxFreq) * maxFreq + freq.get(maxFreq - 1) * (maxFreq - 1) == i + 1 && freq.get(maxFreq) == 1 ||
//                    freq.get(maxFreq) * maxFreq + 1 == i + 1 && freq.get(1) == 1;
//            if (ok) {
//                res = Math.max(res, i + 1);
//            }
//        }
//        return res;

    }
}
