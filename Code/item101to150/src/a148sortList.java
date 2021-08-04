import java.util.List;

public class a148sortList {
    public static void main(String[] args) {
        int[] nums = {-1, 5, 3, 4, 0};
        ListNode head = new ListNode();
        ListNode tail = head;
        for (int i = 0; i < nums.length; i++) {
            tail.next = new ListNode(nums[i]);
            tail = tail.next;
        }
//        head = sortList(head.next);
        head = quickSort(head.next);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    //    二路归并
//    step=1: (3->4)->(1->7)->(8->9)->(2->11)->(5->6)
//    step=2: (1->3->4->7)->(2->8->9->11)->(5->6)
//    step=4: (1->2->3->4->7->8->9->11)->(5->6)
//    step=8: (1->2->3->4->5->6->7->8->9->11)
    public static ListNode sortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode temp = head;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        int mergeNum = 1;
        head = new ListNode(0, head);
        while (mergeNum < length) {
            ListNode preInsert = head;
            temp = head.next;
            while (temp != null) {
                ListNode preChain = temp;

                ListNode preNode = temp;
                for (int i = 0; i < mergeNum && temp != null; i++) {
                    preNode = temp;
                    temp = temp.next;
                }
                preNode.next = null;

                ListNode retroChain = temp;
                for (int i = 0; i < mergeNum && temp != null; i++) {
                    preNode = temp;
                    temp = temp.next;
                }
                preNode.next = null;

                preInsert.next = merge(preChain, retroChain);
                ListNode tail = preInsert.next;
                while (tail.next != null) {
                    tail = tail.next;
                }
                tail.next = temp;
                preInsert = tail;

            }
            mergeNum = mergeNum * 2;
        }

        return head.next;
    }

    // quickSort
    public static ListNode quickSort(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pin = head;
        head = head.next;

        ListNode smallHead = new ListNode();
        ListNode smallTail = smallHead;
        ListNode bigHead = new ListNode();
        ListNode bigTail =  bigHead;


        while (head !=null){
            ListNode nextTemp = head.next;
            head.next = null;
            if (head.val < pin.val){
                smallTail.next = head;
                smallTail = smallTail.next;
            }else {
                bigTail.next = head;
                bigTail = bigTail.next;
            }
            head = nextTemp;
        }
        smallHead = quickSort(smallHead.next);
        bigHead = quickSort(bigHead.next);

        ListNode temp = smallHead;
        if (temp == null){
            pin.next = bigHead;
            head = pin;
        }else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = pin;
            pin.next = bigHead;
            head = smallHead;
        }
        return head;
    }


    //Merge
    public static ListNode merge(ListNode A, ListNode B) {

        ListNode ans = new ListNode();
        ListNode tail = ans;
        if (A == null || B == null) {
            return A == null ? B : A;
        }
        while (A != null && B != null) {
            if (A.val < B.val) {
                tail.next = A;
                A = A.next;

            } else {
                tail.next = B;
                B = B.next;
            }
            tail = tail.next;
        }
        while (A != null) {
            tail.next = A;
            return ans.next;
        }
        while (B != null) {
            tail.next = B;
            return ans.next;
        }
        return ans.next;
    }
}
