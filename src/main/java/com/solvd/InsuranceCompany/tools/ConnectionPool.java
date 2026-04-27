package com.solvd.InsuranceCompany.tools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static final int POOL_SIZE = 5;
    private final BlockingQueue<Connections> pool;

    // Private constructor
    private ConnectionPool() {
        pool = new ArrayBlockingQueue<>(POOL_SIZE);

        // initialize pool with mock connections
        for (int i = 1; i <= POOL_SIZE; i++) {
            pool.add(new Connections(i));
        }
    }

    // ✅ Holder Idiom (lazy + thread-safe)
    private static class Holder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return Holder.INSTANCE;
    }

    // Borrow connection (blocks if none available)
    public Connections getConnection() throws InterruptedException {
        Connections conn = pool.take(); // thread-safe
        conn.open();
        return conn;
    }

    // Return connection to pool
    public void releaseConnection(Connections conn) {
        if (conn != null) {
            conn.close();
            pool.offer(conn); // thread-safe
        }
    }
}
