public class a393validUtf8 {
    public static void main(String[] args) {
        System.out.println(validUtf8(new int[]{197, 130, 1, 197, 130, 1}));
    }

    public static boolean validUtf8(int[] data) {
        int i = 0, n = data.length;
        boolean ans = true;

        while (i < n) {
            byte a = (byte) data[i];


            System.out.println(a);

        }
        return ans;
    }
}
