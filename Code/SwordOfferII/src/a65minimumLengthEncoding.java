public class a65minimumLengthEncoding {
    public static void main(String[] args) {
        System.out.println(minimumLengthEncoding(new String[]{ "cba", "dba"}));
        System.out.println(minimumLengthEncoding(new String[]{"time", "me", "bell"}));
        System.out.println(minimumLengthEncoding(new String[]{"t"}));
        System.out.println(minimumLengthEncoding(new String[]{"time","atime","btime"}));

    }

    public static int minimumLengthEncoding(String[] words) {

        Trie root = new Trie();
        for (String word : words) {
            root.insert(word);
        }

        // 代码可以优化,在插入时就计算,这里使用了dfs来计算
        len  = 0;
        dfs(root, 0);
        return len;
    }

    public static int len;

    public static void dfs(Trie root, int deep) {
        boolean flag = false;
        for (int i = 0; i < 26; i++) {
            if (root.child[i] != null) {
                flag = true;
                dfs(root.child[i], deep + 1);
            }
        }
        if (!flag) {
            len += deep;
            if (root.word != null){
                len += 1;
            }
        }
    }

    static class Trie {
        Trie[] child;
        String word;

        //后缀
        public Trie() {
            child = new Trie[26];
            word = null;
        }

        public void insert(String word) {
            Trie temp = this;
            char[] chars = word.toCharArray();
            for (int i = chars.length - 1; i >= 0; i--) {
                int idx = chars[i] - 'a';
                if (temp.child[idx] == null) {
                    temp.child[idx] = new Trie();
                }
                temp = temp.child[idx];
            }
            temp.word = word;
        }
    }
}
