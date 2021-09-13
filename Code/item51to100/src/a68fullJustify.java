import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class a68fullJustify {
    public static void main(String[] args) {

//        String[] words = {"Science", "is", "what", "we", "understand",
//                "well", "enough", "to", "explain", "to", "a", "computer.",
//                "Art", "is", "everything", "else", "we", "do"};
        //String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        //String[] words = {"Listen", "to", "many,", "speak", "to", "a", "few."};
//        String[] words = {"The","important","thing","is","not","to","stop","questioning.","Curiosity","has","its","own","reason","for","existing."};
        String[] words = {"a"};
        int maxWidth = 1;

        List<String> list = fullJustify(words, maxWidth);

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }

    public static List<String> fullJustify(String[] words, int maxWidth) {

        StringBuilder temp = new StringBuilder();
        List<String> list = new ArrayList<>();

        int index = 0;
        int i = 0;
        int[] coordinate = new int[maxWidth];
        while (index < words.length) {
            // 判断加上下一个单词会不会超出范围
            //  如果第一个单词就达到边界，需要特殊处理
            // 因为除过最后一个单词，其他单词后面默认加一个空格
            // 计算时要注意加一处理
            if (temp.length() + words[index].length() >= maxWidth) {
                if (temp.length() == 0) {
                    coordinate[i++] = words[index].length();
                    temp.append(words[index++]);
                    list.add(adjust(temp, maxWidth, Arrays.copyOfRange(coordinate, 0, i)).toString());
                    //调整均匀
                    i = 0;
                    temp = new StringBuilder();
                    continue;
                }
                list.add(adjust(temp, maxWidth, Arrays.copyOfRange(coordinate, 0, i)).toString());
                //调整均匀
                i = 0;
                temp = new StringBuilder();
            }
            if (index >= words.length) {
                break;
            }
            if (temp.length() != 0) {
                temp.append(' ');
            }

            coordinate[i++] = words[index].length();
            temp.append(words[index++]);
        }
        if (temp.length() != 0) {
            while (temp.length() < maxWidth) {
                temp.append(' ');
            }
            list.add(temp.toString());
        }
        return list;
    }

    public static StringBuilder adjust(StringBuilder str, int maxWidth, int[] coordinate) {
        //n个位置添加空格
        int n = coordinate.length - 1;
        // 需要添加的空格个数
        int space = maxWidth - str.length();
        //平均每个位置add个空格
        if (n <= 0) {
            while (str.length() < maxWidth) {
                str.append(' ');
            }
            return str;
        }
        int add = space / n;
        // 左侧需要额外添加空格的位置
        int left = space - (n * add);
        // 下一个添加空格的位置
        int nextSpace = coordinate[0];

        for (int i = 0; i < n; i++) {
            if (i < left) {
                str.insert(nextSpace++, ' ');
            }
            for (int j = 0; j < add; j++) {
                str.insert(nextSpace++, ' ');
            }
            nextSpace += coordinate[i + 1] + 1;
        }
        return str;
    }
}
