import java.util.concurrent.*;

public class MyThreadPool {
    private ThreadPoolExecutor executor;

    private static class Singleton {
        private static final MyThreadPool pool = new MyThreadPool();
    }


    private MyThreadPool() {
        // I/O密集型应用
//        int corePoolSize = Runtime.getRuntime().availableProcessors();
//        int maximumPoolSize = corePoolSize * 2 + 1;
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
        int maximumPoolSize = corePoolSize + 1;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        /*
        考虑到阻塞队列设的小了，可能更容易触发拒绝策略导致很多文件交给main线程复制，
        设的大了会使线程池一直使用核心线程无法扩容，达到效率最大化，
        因此，考虑将核心线程数改为 cpu核数*2 ，最大线程数改为cpu核数*2+1，阻塞队列设大，设为256
         */
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>(256);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler rejectedPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        this.executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, rejectedPolicy);
    }

    public static MyThreadPool getThreadPool() {
        return Singleton.pool;
    }

    public void execute(Runnable task) {
        executor.execute(task);
    }

    public void stop() {
        executor.shutdown();
    }
}
