package com.onquantum.rockstar.model;

/**
 * Created by Admin on 12/13/15.
 */
public class SoundPackage {
    public static String ID = "id";
    public static String NAME = "name";
    public static String ARTICLE = "article";
    public static String PURCHASE_ID = "purchase_id";
    public static String STATUS_ID = "status_id";
    public static String ICON = "icon";
    public static String SAMPLE_SOUND = "sample_sound";
    public static String DESCRIPTION = "description";

    private long id = 0;
    private String name = null;
    private String article = null;
    private long purchase_id = 0;
    private PackageStatus status = null;
    private String icon = null;
    private String sample_sound = null;


    private PackageStatus packageStatus = null;

    public SoundPackage() {

    }
}
