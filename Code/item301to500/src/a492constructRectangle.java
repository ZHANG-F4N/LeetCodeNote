import java.util.Arrays;

public class a492constructRectangle {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(constructRectangle(0)));
    }

    public static int[] constructRectangle(int area) {
        int w = (int) Math.sqrt(area);
        while (area % w != 0) {
            --w;
        }
        return new int[]{area / w, w};
    }
}
