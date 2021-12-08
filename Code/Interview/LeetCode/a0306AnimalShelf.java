import java.util.LinkedList;

public class a0306AnimalShelf {
    public static void main(String[] args) {
        AnimalShelf obj = new AnimalShelf();
        obj.enqueue(new int[]{0, 0});
        obj.enqueue(new int[]{1, 0});
        obj.enqueue(new int[]{2, 1});
        int[] param_2 = obj.dequeueDog();
        int[] param_3 = obj.dequeueCat();
        int[] param_4 = obj.dequeueAny();
    }

    static class AnimalShelf {

        LinkedList<Integer> dogShelf;
        LinkedList<Integer> catShelf;

        public AnimalShelf() {
            dogShelf = new LinkedList<>();
            catShelf = new LinkedList<>();
        }

        public void enqueue(int[] animal) {
            if (animal[1] == 0) {
                catShelf.offerLast(animal[0]);
            } else {
                dogShelf.offerLast(animal[0]);
            }
        }

        public int[] dequeueAny() {
            if (dogShelf.isEmpty() && catShelf.isEmpty()) {
                return new int[]{-1, -1};
            }
            if (dogShelf.isEmpty()) {
                return new int[]{catShelf.pollFirst(), 0};
            }
            if (catShelf.isEmpty()) {
                return new int[]{dogShelf.pollFirst(), 1};
            }

            if (catShelf.peekFirst() < dogShelf.peekFirst()) {
                return new int[]{catShelf.pollFirst(), 0};
            }else {
                return new int[]{dogShelf.pollFirst(), 1};
            }
        }

        public int[] dequeueDog() {
            if (dogShelf.isEmpty()) {
                return new int[]{-1, -1};
            }else {
                return new int[]{dogShelf.pollFirst(), 1};
            }
        }

        public int[] dequeueCat() {
            if (catShelf.isEmpty()) {
                return new int[]{-1, -1};
            }else {
                return new int[]{catShelf.pollFirst(), 0};
            }

        }
    }
}
