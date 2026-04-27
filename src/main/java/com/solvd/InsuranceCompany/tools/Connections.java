package com.solvd.InsuranceCompany.tools;

import static com.solvd.InsuranceCompany.Main.LOGGER;

public class Connections {

    private final int id;
    private boolean open;

    public Connections(int id) {
        this.id = id;
    }

    public void open() {
        open = true;
        LOGGER.info("Connection {} opened", getId());
    }

    public void close() {
        open = false;
        LOGGER.info("Connection {} closed", getId());
    }

    public int getId() {
        return id;
    }
}
