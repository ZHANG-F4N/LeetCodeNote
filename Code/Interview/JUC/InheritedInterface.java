import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class  InheritedInterface {

    public static class MyThread implements Runnable {
        @Override
        public void run() {
            System.out.println("--- in Runnable ---");
        }
    }

    private static class MyThreadCall implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("--- in Callable ---");
            return "Callable Return";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread t1 = new Thread(new MyThread());

        FutureTask futureTask = new FutureTask(new MyThreadCall());
        Thread t2 = new Thread(futureTask);
        t1.start();
        t2.start();
        System.out.println(futureTask.get());
    }
}
