public class contest66a3 {
    public static void main(String[] args) {
        System.out.println();
    }

    public static int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        if (startPos[0] == homePos[0] && startPos[1] == homePos[1]) {
            return 0;
        }
        int ans = 0;

        int i = startPos[0];
        int delta = 0;
        if (startPos[0] > homePos[0]) {
            delta = -1;
        }
        if (startPos[0] < homePos[0]) {
            delta = +1;
        }
        while (i != homePos[0] && delta != 0) {
            i += delta;
            ans += rowCosts[i];
        }
        int j = startPos[1];
        delta = 0;
        if (startPos[1] > homePos[1]) {
            delta = -1;
        }
        if (startPos[1] < homePos[1]) {
            delta = +1;
        }
        while (j != homePos[1] && delta != 0) {
            j += delta;
            ans += colCosts[j];
        }
        return ans;
    }

}
