public class a641MyCircularDeque {
    public static void main(String[] args) {
        /**
         * Your MyCircularDeque object will be instantiated and called as such:
         * MyCircularDeque obj = new MyCircularDeque(k);
         * boolean param_1 = obj.insertFront(value);
         * boolean param_2 = obj.insertLast(value);
         * boolean param_3 = obj.deleteFront();
         * boolean param_4 = obj.deleteLast();
         * int param_5 = obj.getFront();
         * int param_6 = obj.getRear();
         * boolean param_7 = obj.isEmpty();
         * boolean param_8 = obj.isFull();
         */
        MyCircularDeque obj = new MyCircularDeque(3);
        boolean param_1 = obj.insertLast(1);
        boolean param_2 = obj.insertLast(2);
        param_2 = obj.insertFront(3);
        param_2 = obj.insertFront(4);
        int param_3 = obj.getRear();
        boolean param_4 = obj.isFull();
        boolean param_5 = obj.deleteLast();
        param_2 = obj.insertLast(4);
        int param_6 = obj.getFront();

////        param_2 = obj.insertLast(5);
////        param_2 = obj.insertLast(6);
//        boolean param_3 = obj.deleteFront();
//        param_3 = obj.deleteFront();
//
//        param_2 = obj.insertLast(6);
//        param_2 = obj.insertLast(6);
//        param_2 = obj.insertLast(6);
////        boolean param_4 = obj.deleteLast();
//        int param_5 = obj.getFront();
//        int param_6 = obj.getRear();
//        boolean param_7 = obj.isEmpty();
//        boolean param_8 = obj.isFull();

    }

    static class MyCircularDeque {

        int[] val;
        int head;
        int tail;
        int size;

        /**
         * Initialize your data structure here. Set the size of the deque to be k.
         */
        public MyCircularDeque(int k) {
            val = new int[k + 1];
            head = 0;
            tail = 0;
            size = k + 1;
        }

        /**
         * Adds an item at the front of Deque. Return true if the operation is successful.
         */
        public boolean insertFront(int value) {
            if ((tail + 1) % size == head) {
                return false;
            }
            if (tail == head) {
                val[head] = value;
                tail = (tail + 1) % size;
                return true;
            }
            head = (head + size-1) % size;
            val[head] = value;
            return true;
        }

        /**
         * Adds an item at the rear of Deque. Return true if the operation is successful.
         */
        public boolean insertLast(int value) {
            if ((tail + 1) % size == head) {
                return false;
            }
            val[tail] = value;
            tail = (tail + 1) % size;
            return true;
        }

        /**
         * Deletes an item from the front of Deque. Return true if the operation is successful.
         */
        public boolean deleteFront() {
            if (tail == head) {
                return false;
            }
            head = (head + size + 1) % size;
            return true;
        }

        /**
         * Deletes an item from the rear of Deque. Return true if the operation is successful.
         */
        public boolean deleteLast() {
            if (tail == head) {
                return false;
            }
            tail = (tail + size-1) % size;
            return true;
        }

        /**
         * Get the front item from the deque.
         */
        public int getFront() {
            if (tail == head) {
                return -1;
            }
            return val[head];
        }

        /**
         * Get the last item from the deque.
         */
        public int getRear() {
            if (tail == head) {
                return -1;
            }
            int index = (tail + size-1) % size;
            return val[index];
        }

        /**
         * Checks whether the circular deque is empty or not.
         */
        public boolean isEmpty() {
            if (tail == head) {
                return true;
            }
            return false;

        }

        /**
         * Checks whether the circular deque is full or not.
         */
        public boolean isFull() {
            if ((tail + 1) % size == head) {
                return true;
            }
            return false;
        }
    }


}
