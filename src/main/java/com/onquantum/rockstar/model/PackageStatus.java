package com.onquantum.rockstar.model;

/**
 * Created by Admin on 12/13/15.
 */
public class PackageStatus {

    public enum Status {
        all,
        none,
        disable,
        enable
    }

    private int id = 0;
    private Status status = Status.disable;

    public PackageStatus(int id) {
        this.id = id;
        status = Status.values()[id];
    }
}
