package com.solvd.InsuranceCompany.tools;

import static com.solvd.InsuranceCompany.Main.LOGGER;

public class NameThread extends Thread {

    public NameThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            LOGGER.info("{} - iteration {}", Thread.currentThread().getName(), i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                LOGGER.info("{} interrupted", getName());
            }
        }
    }
}
