package com.example.vymoassignment;

import android.app.Application;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class VymoAssignmentApplication extends Application {
    // Sets the amount of time an idle thread waits before terminating
    private static final int KEEP_ALIVE_TIME = 1;
    // Sets the Time Unit to seconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    /*
     * Gets the number of available cores
     * (not always the same as the maximum number of cores)
     */
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    // Instantiates the queue of Runnables as a LinkedBlockingQueue
    private final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
    // Creates a thread pool manager
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            NUMBER_OF_CORES,       // Initial pool size
            NUMBER_OF_CORES,       // Max pool size
            KEEP_ALIVE_TIME,
            KEEP_ALIVE_TIME_UNIT,
            workQueue
    );

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }
}
