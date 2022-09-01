package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @author beijing.lv
 * @version 1.0
 * @date 2021/12/14 9:32
 */
public class ThreadPoolTest {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);

    /**
     * 1.线程池各个参数的作用，简单阐述一下线程池工作流程
     * 2.常见的线程池有哪些，分别适用于什么场景
     * 3.使用无界队列的线程会导致内存飙升吗？
     **/
    public static void main(String[] args) {
        //线程池的概念，管理线程的池子，相比于手工创建线程、运行线程有以下优点
        // 1.降低线程创建和销毁线程造成的开销
        // 2.提高了响应速度。任务到达时相对于手动创建线程，直接从线程池中拿到线程速度快多了
        // 3.提高线程的可管理性。线程是稀缺资源如果无限制的创建，不仅会消耗系统资源，还会降低系统稳定性
        //   使用线程池可以进行统一分配、调优和监控

        //无论创建何种类型线程池（FixedThreadPool,CachedThreadPool），均会调用ThreadPoolExecutor构造函数

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(512));

        //corePoolSize：核心线程最大数量，线程池中常驻线程的最大数量
        //maximumPoolSize:线程池中运行最大线程数（包括核心线程数和非核心线程数）
        //keepAliveTime:线程池中空闲线程（仅适用于非核心线程）所能存活的最长时间
        //unit：存活时间单位，与keepAliveTime搭配使用
        //workQueue:存放任务的阻塞队列
        //handler:线程池饱和策略


        //线程池执行流程，当一个任务提交时，线程池处理流程如下
        //1.首先判断线程池中可信线程数是否已经达到阙值（corePoolSize）,若否则创建一个新的核心线程池执行任务
        //2.若核心线程池达到阙值（corePoolSize）,判断阻塞队列（workQueue）是否已满，若未满则将新任务添加到阻塞队列中
        //3.若满则判断，线程池中的线程数是否达到最大线程数阙值（maxinumPoolSize）,若否则新建一个非核心线程执行任务。
        //  若达到阙值则执行线程池饱和策略

        int i = Runtime.getRuntime().availableProcessors();
        logger.info("cpu个数--------{}", i);
        Thread t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    logger.info("run---------->{}", i);
                }
            }
        };

        t.start();
        for (int a = 0; a < 10; a++) {
            logger.info("test---------->{}", a);
        }

        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(8);
        // 创建多个有返回值的任务
//        List<Future> list = new ArrayList<Future>();
//        for (int j = 0; j < 8; j++) {
//            Callable c = new MyCallable(j + " ");
//            // 执行任务并获取 Future 对象
//            Future f = pool.submit(c);
//            list.add(f);
//        }
//        // 关闭线程池
//        pool.shutdown();
//        // 获取所有并发任务的运行结果
//        for (Future f : list) {
//            // 从 Future 对象上获取任务的返回值，并输出到控制台
//            System.out.println("res： " + f.get().toString());
//        }


    }

    //几种典型的线程池

    /**
     * 1.SingleThreadExecutor 适用于串联执行任务的场景
     * 创建单个线程。它适用于需要保证顺序的执行各个任务；并且在任意时间点，不会有多个线程活动的应用场景。
     * SingleThreadExecutore的corePoolSize和maxinumPoolSize被设置为1，使用无界队列LinkedBlockingQueue作为线程池的工作队列。
     * <p>
     * <p>
     * 当线程池中没有线程时，会创建一个新线程来执行任务。
     * 当前线程池中有一个线程后，将新任务加入LinkedBlockingQueue
     * 线程执行完第一个任务后，会在一个无线循环中反复从LinkedBlockQueue中获取任务来执行
     **/
    public static ExecutorService newSingleThreadExecutor() {
        return new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                threadFactory);
    }


    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }

    public void b() {
        MyThread myThread = new MyThread();
        myThread.run();
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread.run()");
        }
    }


}
