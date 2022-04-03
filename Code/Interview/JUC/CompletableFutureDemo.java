import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步调用 无返回值
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "--异步调用 无返回值");
        });
        completableFuture.get();

        // 异步调用 有返回值
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "----异步调用 有返回值");
            int i  =1 / 0;
            return 1024;
        });
        completableFuture1.whenComplete((t, u) -> {
            System.out.println("--t=" + t);
            System.out.println("--u=" + u);
        }).get();
    }
}
