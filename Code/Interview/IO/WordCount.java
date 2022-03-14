import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) throws IOException {
        wordCount();
    }

    // 统计 comment.txt中每个字符出现的次数
    public static void wordCount() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("Interview//IO//comment.txt")));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Interview//IO//commentCount.txt")));
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int ch;
        while ((ch = br.read()) != -1) {
            char c = (char) ch;
            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
        }
        br.close();
        for (Map.Entry<Character, Integer> en : hashMap.entrySet()) {
            char c = en.getKey();
            switch (c){
                case ' ':
                    bw.write("空格 = "+en.getValue());
                    break;
                case '\t':
                    bw.write("tab = "+en.getValue());
                    break;
                case '\r':
                    bw.write("回车 = "+en.getValue());
                    break;
                case '\n':
                    bw.write("换行 = "+en.getValue());
                    break;
                default:
                    bw.write(c+" = "+en.getValue());
                    break;
            }
            bw.newLine();
        }
        bw.close();
    }

}
