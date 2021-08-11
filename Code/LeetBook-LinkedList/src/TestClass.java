public class TestClass {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
    //        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
//        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3

        System.out.println(linkedList.get(0) );//返回2
//        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
//        System.out.println(linkedList.get(1));//返回3


    }
}
