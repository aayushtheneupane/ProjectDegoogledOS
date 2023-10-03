package com.android.p032ex.chips;

import android.content.Context;
import android.os.Build;
import android.os.Process;

/* renamed from: com.android.ex.chips.m */
public class C0688m {

    /* renamed from: ev */
    public static final String[] f805ev = {"android.permission.READ_CONTACTS"};

    /* renamed from: g */
    public static boolean m1070g(Context context) {
        String[] strArr = f805ev;
        int length = strArr.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= length) {
                return true;
            }
            String str = strArr[i];
            int i2 = Build.VERSION.SDK_INT;
            if (context.checkPermission(str, Process.myPid(), Process.myUid()) != 0) {
                z = false;
            }
            if (!z) {
                return false;
            }
            i++;
        }
    }
}
