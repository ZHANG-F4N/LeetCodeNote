import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class contest268a3 {
    public static void main(String[] args) {
        RangeFreqQuery obj = new RangeFreqQuery(new int[]{1, 1, 1, 2, 2});
        int param_1 = obj.query(0, 1, 2);
        int param_2 = obj.query(0, 2, 1);
        int param_3 = obj.query(3, 3, 2);
        int param_4 = obj.query(2, 2, 1);
    }

    /*
     *      0   1   2   3   4   5   6   7   8   9   10  11
     *      12  33  4   56  22  2   34  33  22  12  34  56
     *  12  0   9
     *  33  1   7
     *  4   2
     *  56  3   11
     *  22  4   8
     *  2   5
     *  34  6   10
     * */

}

class RangeFreqQuery {
    HashMap<Integer, ArrayList> hashMap;

    public RangeFreqQuery(int[] arr) {
        hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(arr[i])) {
                hashMap.get(arr[i]).add(i);
            } else {
                ArrayList list = new ArrayList<Integer>();
                list.add(i);
                hashMap.put(arr[i], list);
            }
        }
    }

    public int query(int left, int right, int value) {
        List<Integer> list = hashMap.get(value);
        int len = list.size();
        if (left <= list.get(0) && right >= list.get(len - 1)) {
            return len;
        }
        if (right < list.get(0)) {
            return 0;
        }
        if (left > list.get(len - 1)) {
            return 0;
        }

        int l = 0;
        int r = len - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (left == list.get(mid)) {
                l = mid;
                break;
            }
            if (left < list.get(mid)) {
                r = mid - 1;
            }
            if (left > list.get(mid)) {
                l = mid + 1;
            }
        }
        int numl = l;
        l = 0;
        r = len - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (right == list.get(mid)) {
                r = mid;
                break;
            }
            if (right < list.get(mid)) {
                r = mid - 1;
            }
            if (right > list.get(mid)) {
                l = mid + 1;
            }
        }
        int numr = r;
        if (left == list.get(numl) && right == list.get(numr)) {
            return numr - numl + 1;
        }
        if (left != list.get(numl) && right != list.get(numr)) {
            return numr - numl - 1;
        }
        return numr - numl;
    }
}
