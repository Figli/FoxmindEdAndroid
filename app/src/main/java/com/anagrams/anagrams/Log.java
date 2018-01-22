package com.anagrams.anagrams;

import android.text.TextUtils;

import io.michaelrocks.paranoid.Obfuscate;

/**
 * Created by Figli on 21.01.2018.
 */

@Obfuscate
public final class Log {
    private static boolean logEnabled = true;

    // ERROR
    public static void e(String tag, String msg) {
        if (logEnabled)
            android.util.Log.e(tag, getLocation() + msg);
    }

    // WARN
    public static void w(String tag, String msg) {
        if (logEnabled)
            android.util.Log.w(tag, getLocation() + msg);
    }

    // INFO
    public static void i(String tag, String msg) {
        if (logEnabled)
            android.util.Log.i(tag, getLocation() + msg);
    }

    // DEBUG
    public static void d(String tag, String msg) {
        if (logEnabled)
            android.util.Log.d(tag, getLocation() + msg);
    }

    // VERBOSE
    public static void v(String tag, String msg) {
        if (logEnabled)
            android.util.Log.v(tag, getLocation() + msg);
    }

    private static String getLocation() {
        final String className = Log.class.getName();
        final StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        boolean found = false;
        for (int i = 0; i < traces.length; i++) {
            StackTraceElement trace = traces[i];
            try {
                if (found) {
                    if (!trace.getClassName().startsWith(className)) {
                        Class clazz = Class.forName(trace.getClassName());
                        return "[" + getClassName(clazz) + ":" + trace.getMethodName() + ":" + trace.getLineNumber() + "]: ";
                    }
                }
                else if (trace.getClassName().startsWith(className)) {
                    found = true;
                    continue;
                }
            } catch (ClassNotFoundException e) {
            }
        }
        return "[]: ";
    }

    private static String getClassName(Class clazz) {
        if (clazz != null) {
            if (!TextUtils.isEmpty(clazz.getSimpleName())) {
                return clazz.getSimpleName();
            }
            return getClassName(clazz.getEnclosingClass());
        }
        return "";
    }
}

