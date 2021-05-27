import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class a234isPalindrome {
    public static void main(String[] args) {
        int[] head = {1, 2};
        ListNode start = new ListNode(0);
        ListNode end = start;
        for (int i : head) {
            ListNode temp = new ListNode(i, null);
            end.next = temp;
            end = temp;
        }
        System.out.println(isPalindrome(start.next));
    }

    public static boolean isPalindrome(ListNode head) {

//        ArrayList<Integer> arrayList = new ArrayList<>();
//        while (head != null) {
//            arrayList.add(head.val);
//            head = head.next;
//        }
//        int len = arrayList.size();
//        for (int i = 0; i < len>>1; i++) {
//            if (arrayList.get(i) == arrayList.get(len-1-i)){
//                continue;
//            }else {
//                return false;
//            }
//        }
//        //System.out.println(Arrays.toString(arrayList.toArray()));
//        return true;
        if (head == null || head.next == null){
            return true;
        }

        ListNode fastP = head;
        ListNode slowP = head;
        while (fastP.next != null && fastP.next.next != null) {
            fastP = fastP.next.next;
            slowP = slowP.next;
        }

        slowP.next = reverseList(slowP.next);

        fastP = slowP.next;
        slowP = head;

        while (fastP != null) {
            if (slowP.val != fastP.val) {
                return false;
            }
            slowP = slowP.next;
            fastP = fastP.next;
        }
        return true;
    }

    public static ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode cursor = head.next;
        ListNode temp = null;
        head.next = null;
        while (cursor != null) {
            temp = cursor;
            cursor = cursor.next;
            temp.next = head;
            head = temp;
        }
        return head;
    }
}
