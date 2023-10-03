package com.android.messaging.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* renamed from: com.android.messaging.util.X */
public class C1419X {
    private static volatile C1419X sInstance;

    /* renamed from: b */
    public static void m3578b(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static C1419X get() {
        if (sInstance == null) {
            synchronized (C1419X.class) {
                if (sInstance == null) {
                    sInstance = new C1419X();
                }
            }
        }
        return sInstance;
    }

    public static void set(C1419X x) {
        sInstance = x;
    }

    /* renamed from: a */
    public void mo8048a(Context context, View view) {
        C1424b.m3594t(context);
        C1424b.m3594t(view);
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /* renamed from: c */
    public void mo8049c(Context context, View view) {
        C1424b.m3594t(context);
        C1424b.m3594t(view);
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            view.requestFocus();
            inputMethodManager.showSoftInput(view, 0);
        }
    }
}
