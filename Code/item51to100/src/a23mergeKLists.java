public class a23mergeKLists {
    public static void main(String[] args) {
        int[][] input = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        ListNode[] list = new ListNode[input.length];
        for (int i = 0; i < input.length; i++) {
            ListNode head = new ListNode();
            ListNode tail = head;
            for (int j = 0; j < input[i].length; j++) {
                tail.next = new ListNode(input[i][j], null);
                tail = tail.next;
            }
            list[i] = head.next;
        }

        ListNode ans = mergeKLists(list);
        while (ans!=null){
            System.out.println(ans.val);
            ans = ans.next;
        }


    }

    public static ListNode mergeKLists(ListNode[] lists) {
        //二路归并
        if (lists.length == 1) {
            return lists[0];
        }
        if (lists.length == 0) {
            return null;
        }
        int unSort = lists.length;
        int mid = (lists.length + 1) / 2;
        while (mid >= 1) {
            for (int i = 0; i < unSort - mid; i++) {
                lists[i] = twoMerge(lists[i], lists[mid + i]);
            }
            if (mid == 1) {
                break;
            }
            unSort = mid;
            mid = (unSort + 1) / 2;
        }
        return lists[0];
    }

    public static ListNode twoMerge(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }

        ListNode head = new ListNode(), tail = head, tempA = a, tempB = b;
        while (tempA != null && tempB != null) {
            if (tempA.val < tempB.val) {
                tail.next = tempA;
                tempA = tempA.next;
                tail = tail.next;
            } else {
                tail.next = tempB;
                tempB = tempB.next;
                tail = tail.next;
            }
        }
        if (tempA != null) {
            tail.next = tempA;
        }
        if (tempB != null) {
            tail.next = tempB;
        }
        return head.next;
    }
}
