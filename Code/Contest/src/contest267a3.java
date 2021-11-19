public class contest267a3 {
    public static void main(String[] args) {

        System.out.println(decodeCiphertext("iveo    eed   l te   olc", 4));
    }

    public static String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) {
            return encodedText;
        }
        int len = encodedText.length();
        int col = len / rows;
        char[][] map = new char[rows][col];


        int idx = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = encodedText.charAt(idx++);
            }
        }


        StringBuilder ans = new StringBuilder();

        for (int j = 0; j < col; j++) {
            for (int i = 0; i < rows; i++) {
                if (i + j < col) {
                    ans.append(map[i][i + j]);
                }
            }
        }
        return ans.toString().replaceAll("\\s+$", "");


    }
}
