public class a0508drawLine {
    public static void main(String[] args) {
        System.out.println(drawLine(1, 32, 30, 31, 0));
        System.out.println(drawLine(3, 96, 0, 95, 0));
    }

    public static int[] drawLine(int length, int w, int x1, int x2, int y) {

        int[] ans = new int[length];
        int num = w / 32;
        for (int i = 0; i < num; i++) {
            int temp = 0;
            for (int j = 0; j < 32; j++) {
                if (i * 32 + j >= x1 && i * 32 + j <= x2) {
                    temp |= (1 << (31 - j));
                }
            }
            ans[y * num + i] = temp;
        }

        return ans;

    }
}
