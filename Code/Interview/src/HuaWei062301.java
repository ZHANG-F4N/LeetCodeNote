import java.util.*;

public class HuaWei062301 {

    static class Word {
        String name;
        int similarity;
        int repeatTime;

        public Word(String name, int similarity, int repeatTime) {
            this.name = name;
            this.similarity = similarity;
            this.repeatTime = repeatTime;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String target = scanner.nextLine();
        String text = scanner.nextLine();
        text = text.toLowerCase();

        char[] testArray = text.toCharArray();

        StringBuffer tempStr = new StringBuffer("");
        HashMap<StringBuffer, Integer> similarHashMap = new HashMap<>();
        HashMap<StringBuffer, Integer> repeatHashMap = new HashMap<>();

        PriorityQueue<Word> queue = new PriorityQueue<>(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                if (o1.similarity != o2.similarity) {
                    return o2.similarity - o1.similarity;
                }
                if (o1.repeatTime != o2.repeatTime) {
                    return o2.repeatTime - o1.repeatTime;
                }
                return o1.name.compareTo(o2.name);
            }
        });

        for (int i = 0; i < testArray.length; i++) {
            if (testArray[i] == ' ' || testArray[i] == ',' || testArray[i] == '.' || i == testArray.length - 1) {
                if (i == testArray.length - 1) {
                    tempStr.append(testArray[i]);
                }
                if (repeatHashMap.containsKey(tempStr)) {
                    repeatHashMap.put(tempStr, repeatHashMap.get(tempStr) + 1);
                } else if (tempStr.indexOf(target) != -1) {
                    int similar = (int) (((float) target.length() / tempStr.length()) * 100);
                    if (similar >= 50) {
                        similarHashMap.put(tempStr, similar);
                        repeatHashMap.put(tempStr, 1);
                    }
                }
                tempStr = new StringBuffer("");
            } else {
                tempStr.append(testArray[i]);
            }
        }

        for (Map.Entry<StringBuffer, Integer> entry : similarHashMap.entrySet()) {
            queue.add(new Word(entry.getKey().toString(), entry.getValue(), repeatHashMap.get(entry.getKey())));
        }
        while(!queue.isEmpty()){
            System.out.println(queue.poll().name);
        }

        scanner.close();
    }

}
