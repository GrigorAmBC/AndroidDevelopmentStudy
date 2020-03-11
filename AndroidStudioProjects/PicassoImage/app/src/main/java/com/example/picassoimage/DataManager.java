package com.example.picassoimage;

public class DataManager {

    private static boolean wasClosed = false;

    private static boolean isFirstOpened = true;

    public static boolean isFirstOpened() {
        return isFirstOpened;
    }

    public static void isFirstOpened(boolean opened) {
        isFirstOpened = opened;
    }

    public static boolean wasClosed() {
        return wasClosed;
    }

    public static void wasClosed(boolean wasClosed) {
        DataManager.wasClosed = wasClosed;
    }
}
