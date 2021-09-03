import java.util.Arrays;
import java.util.Stack;

public class a06reversePrint {
    public static void main(String[] args) {
        int[] head = {1, 3, 2};
        ListNode list = new ListNode();
        ListNode temp = list;
        for (int i = 0; i < head.length; i++) {
            temp.next = new ListNode(head[i]);
            temp = temp.next;
        }
        System.out.println(Arrays.toString(reversePrint(list.next)));

    }

    public static int[] reversePrint(ListNode head) {

        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int size = stack.size();
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = stack.pop();
        }
        return array;
    }
}

