import java.util.HashMap;
import java.util.Map;

public class contest66a2 {
    public static void main(String[] args) {
        System.out.println(minimumBuckets("H"));
        System.out.println(minimumBuckets("H.H"));
        System.out.println(minimumBuckets("."));
        System.out.println(minimumBuckets(".HHH."));
        System.out.println(minimumBuckets("H..H"));
    }

    public static int minimumBuckets(String street) {
        // 判断 -1
        if (street.length() == 1 && street.equals("H")) {
            return -1;
        }

        int ans = 0;

        char[] house = street.toCharArray();
        for (int i = 0; i < house.length; i++) {
            if (house[i] == '.' || house[i] == 'T') {
                continue;
            }
            if (i == 0) {
                if (house[i + 1] == 'H') {
                    return -1;
                }
                house[i + 1] = 'T';
            } else if (i == house.length - 1) {
                if (house[i - 1] == 'H') {
                    return -1;
                }
                house[i - 1] = 'T';
            } else {
                // 可能要约束下标
                if (house[i - 1] == 'H' && house[i + 1] == 'H') {
                    return -1;
                }
                if (house[i - 1] == 'T' || house[i + 1] == 'T') {
                    continue;
                }
                if (house[i - 1] == '.' && house[i + 1] == '.') {
                    house[i + 1] = 'T';
                } else if (house[i - 1] == '.') {
                    house[i - 1] = 'T';

                } else {
                    house[i + 1] = 'T';
                }
            }
        }
        for (char c : house) {
            if (c == 'T') {
                ans++;
            }
        }
        return ans;


    }

}
