import java.util.HashMap;
import java.util.Map;

public class a1748sumOfUnique {
    public static void main(String[] args) {

    }
    public static int sumOfUnique(int[] nums) {
        int ans = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (hashMap.containsKey(num)){
                if (hashMap.get(num) == 1) {
                    ans -= num;
                    hashMap.put(num,2);
                }
            }else {
                ans+= num;
                hashMap.put(num,1);
            }

        }
        return ans;
    }
}
