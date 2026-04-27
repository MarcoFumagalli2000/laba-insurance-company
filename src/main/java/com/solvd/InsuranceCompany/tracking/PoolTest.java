package com.solvd.InsuranceCompany.tracking;

import com.solvd.InsuranceCompany.tools.Connections;
import com.solvd.InsuranceCompany.tools.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

public class PoolTest {

    private static final Logger LOGGER = LogManager.getLogger(PoolTest.class);

    public static void runTest() {
        LOGGER.info("Test");

        ConnectionPool pool = ConnectionPool.getInstance();
        ExecutorService executor = Executors.newFixedThreadPool(7);

        for (int i = 0; i < 7; i++) {
            int taskId = i;

            executor.submit(() -> {
                try {
                    LOGGER.info("Task {} requesting connection...", taskId);

                    Connections conn = pool.getConnection();

                    LOGGER.info("Task {} acquired Connection {}", taskId, conn.getId());

                    Thread.sleep(1000);

                    pool.releaseConnection(conn);

                    LOGGER.info("Task {} released Connection {}", taskId, conn.getId());

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LOGGER.error("Task {} interrupted", taskId);
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    public static void runSecondTest() {
        LOGGER.info("Second test");

        ConnectionPool pool = ConnectionPool.getInstance();
        ExecutorService executor = Executors.newFixedThreadPool(7);

        CompletableFuture<?>[] futures = new CompletableFuture[7];

        for (int i = 0; i < 7; i++) {
            int taskId = i;

            futures[i] = CompletableFuture
                    .supplyAsync(() -> {
                        try {
                            LOGGER.info("CF Task {} requesting connection...", taskId);

                            Connections conn = pool.getConnection();

                            LOGGER.info("CF Task {} acquired Connection {}", taskId, conn.getId());

                            Thread.sleep(1000);

                            return conn;

                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException("Interrupted");
                        }
                    }, executor)

                    .thenAccept(conn -> {
                        pool.releaseConnection(conn);
                        LOGGER.info("CF Task {} released Connection {}", taskId, conn.getId());
                    });
        }

        CompletableFuture.allOf(futures).join();
        executor.shutdown();
    }
}
