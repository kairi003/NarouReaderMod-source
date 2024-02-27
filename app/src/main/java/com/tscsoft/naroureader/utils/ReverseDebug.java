package com.tscsoft.naroureader.utils;

import android.util.Log;

public class ReverseDebug {

    public static class ReverseDebugTrace extends Exception {
    }

    private static final String TAG = "ReverseDebug";

    public static void trace() {
        new ReverseDebugTrace().printStackTrace();
    }
    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void d(Object msg) {
        d(String.valueOf(msg));
    }

    public static void d(int msg) {
        d(String.valueOf(msg));
    }

    public static void d(long msg) {
        d(String.valueOf(msg));
    }

    public static void d(float msg) {
        d(String.valueOf(msg));
    }

    public static void d(double msg) {
        d(String.valueOf(msg));
    }

    public static void d(char msg) {
        d(String.valueOf(msg));
    }

    public static void d(boolean msg) {
        d(String.valueOf(msg));
    }

    public static void d(byte msg) {
        d(String.valueOf(msg));
    }

    public static void d(short msg) {
        d(String.valueOf(msg));
    }

    public static void test() {
        d("TEST");
        d(Integer.valueOf(123));
        trace();
    }
}
