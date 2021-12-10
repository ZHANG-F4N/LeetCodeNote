public class contest267 {
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
    public static ListNode reverseEvenLengthGroups(ListNode head) {

        int idx = 0;
        int partBeg = 0;
        int partLen = 1;
        head = new ListNode(0, head);
        ListNode temp = head.next;
        ListNode pre = head;
        int len = 0;
        //0-/-1-/-2--3-/-4--5--6-/-7--8--9--10
        //0-/-5-/-2--6-/-3--9--1-/-7--3--8--4
        while (temp != null) {
            idx++;
            len++;
            if (temp.next == null) {
                // 处理结尾
                if (len % 2 == 0) {
                    pre.next = reverse(pre.next);
                }
                break;
            }
            if (idx == partBeg + partLen && partLen % 2 == 0) {
                // 是否反转 beg -- end
                ListNode end = temp.next;
                ListNode con = pre.next;
                temp.next = null;

                ListNode newHead = reverse(pre.next);
                pre.next = newHead;
                con.next = end;
                pre = con;
                partLen++;
                partBeg = idx;
                temp = end;
                len = 0;
                continue;
            } else if (idx == partBeg + partLen && partLen % 2 != 0) {
                pre = temp;
                partBeg =idx;
                partLen++;
                len = 0;
            }
            temp = temp.next;
        }
        return head.next;
    }

    public static ListNode reverse(ListNode head) {
        ListNode idx = head;
        // 添加头结点
        head = new ListNode(0);

        while (idx != null) {
            ListNode tempH = idx.next;
            idx.next = head.next;
            head.next = idx;
            idx = tempH;
        }
        return head.next;
    }
    public static int timeRequiredToBuy(int[] tickets, int k) {
        int[] ans = new int[tickets.length];
        int time = 0;


        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] != 0) {
                    flag = true;
                    tickets[i]--;
                    time++;
                    ans[i] = time;
                }
            }
        }
        return ans[k];


    }
}
