import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class a0806hanota {
    public static void main(String[] args) {
        hanota(Arrays.asList(new Integer[]{2,1,0}),Arrays.asList(new Integer[]{}),
                Arrays.asList(new Integer[]{}));
    }

    public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(), A, B, C);
    }

    public static void move(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (n == 1) {
            C.add(0, A.remove(0));
        }
        B.add(0, A.remove(0));
        move(n - 1, A, B, C);
        C.add(0, B.remove(0));
    }

}
