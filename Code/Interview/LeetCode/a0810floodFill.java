public class a0810floodFill {
    public static void main(String[] args) {
        floodFill(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2);
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (newColor == image[sr][sc]) return image;
        DFS(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    public static void DFS(int[][] image, int i, int j, int oldColor, int newColor) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != oldColor)
            return;
        image[i][j] = newColor;
        DFS(image, i + 1, j, oldColor, newColor);
        DFS(image, i - 1, j, oldColor, newColor);
        DFS(image, i, j + 1, oldColor, newColor);
        DFS(image, i, j - 1, oldColor, newColor);
    }
}
