import java.util.Arrays;

public class LeetCode1020a1 {
    public static void main(String[] args) {
        int[] seats = {2,2,6,6};
        int[] students = {1, 3, 2, 6};
        System.out.println(minMovesToSeat(seats, students));
    }

    public static int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int ans = 0;

        int N = seats.length;
        for (int i = 0; i < N; i++) {
            ans += Math.abs(students[i] - seats[i]);
        }
        return ans;

    }
}
