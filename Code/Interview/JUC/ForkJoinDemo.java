import java.util.concurrent.*;

public class ForkJoinDemo {
    static class fibonacci extends RecursiveTask<Integer> {
        final int n;
        // 创建有参构造
        fibonacci(int n) { this.n = n; }
        @Override
        protected Integer compute() {
            if (n <= 1) return n;
            fibonacci f1 = new fibonacci(n - 1);
            f1.fork();
            fibonacci f2 = new fibonacci(n - 2);
            return f2.compute() + f1.join();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建任务对象
        fibonacci f = new fibonacci(10);
        // 创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(f);
        // 获取合并之后的结果
        Integer res = forkJoinTask.get();
        System.out.println(res);
        // 关闭池对象
        forkJoinPool.shutdown();


    }
}
