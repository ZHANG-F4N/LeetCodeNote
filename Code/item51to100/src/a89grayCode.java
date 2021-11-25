import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class a89grayCode {
    public static void main(String[] args) {
        System.out.println(grayCode(1).toString());
        System.out.println(grayCode(2).toString());
        System.out.println(grayCode(3).toString());
        System.out.println(grayCode(4).toString());
        System.out.println(grayCode(5).toString());
    }

    /*
     *  规律 他们除过第一位上下镜像，开始的半部分即为前一个阶的格雷编码
     *  n=  1   2   3
     *      0   00  000
     *      1   01  001
     *          --  011
     *          11  010
     *          10  ---
     *              110
     *              111
     *              101
     *              100
     * */
    public static List<Integer> grayCode(int n) {
        if (n == 1) {
            return Arrays.asList(new Integer[]{0, 1});
        }
        Integer[] ints = {0, 1, 3, 2};
        List<Integer> ans = new ArrayList<>(Arrays.asList(ints));
        for (int i = 3; i <= n; i++) {
            int num = ans.size();
            for (int j = num - 1; j >= 0; j--) {
                ans.add(ans.get(j) + (1 << (i - 1)));
            }
        }
        return ans;
    }
}
