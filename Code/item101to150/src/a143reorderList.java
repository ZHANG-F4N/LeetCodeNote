import java.util.LinkedList;

public class a143reorderList {
    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4};
        ListNode head = new ListNode().build(num);
        reorderList(head.next);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void reorderList(ListNode head) {
        int N = 0;
        ListNode temp = head;
        while (temp != null) {
            N++;
            temp = temp.next;
        }
        if (N <= 2) {
            return;
        }
        int pre = ((N + 1) >> 1);
        temp = head;
        ListNode rearList = null;
        int i = 0;
        while (temp != null) {
            if (i == pre - 1) {
                rearList = temp.next;
                temp.next = null;
                break;
            }
            i++;
            temp = temp.next;
        }
        // 头插反转
        ListNode index = rearList.next;
        rearList.next = null;
        while (index != null) {
            temp = index;
            index = index.next;
            temp.next = rearList;
            rearList = temp;
        }

        //合并
        ListNode ans = head;
        ListNode idx1 = head;
        ListNode idx2 = rearList;
        //index = rearList;
        while (rearList != null) {
            head = head.next;
            rearList = rearList.next;
            idx1.next = idx2;
            idx2.next = head;
            idx1 = head;
            idx2 = rearList;
        }
        head = ans;
        return;
    }
}
