package com.android.messaging.mmslib.p039a;

import android.util.SparseArray;
import androidx.core.view.PointerIconCompat;
import java.io.UnsupportedEncodingException;
import p000a.p005b.C0027n;

/* renamed from: com.android.messaging.mmslib.a.c */
public class C0973c {

    /* renamed from: Ul */
    private static final int[] f1399Ul = {0, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 17, 18, 38, 39, 40, 106, 113, 114, 2025, 2026, 1000, PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, 2085};

    /* renamed from: Vl */
    private static final String[] f1400Vl = {DefaultApnSettingsLoader.APN_TYPE_ALL, "us-ascii", "iso-8859-1", "iso-8859-2", "iso-8859-3", "iso-8859-4", "iso-8859-5", "iso-8859-6", "iso-8859-7", "iso-8859-8", "iso-8859-9", "shift_JIS", "euc-jp", "euc-kr", "iso-2022-jp", "iso-2022-jp-2", "utf-8", "gbk", "gb18030", "gb2312", "big5", "iso-10646-ucs-2", "utf-16", "hz-gb-2312"};

    /* renamed from: Wl */
    private static final SparseArray f1401Wl = new SparseArray();

    /* renamed from: Xl */
    private static final C0027n f1402Xl = new C0027n();

    static {
        int length = f1399Ul.length - 1;
        for (int i = 0; i <= length; i++) {
            f1401Wl.put(f1399Ul[i], f1400Vl[i]);
            f1402Xl.put(f1400Vl[i], Integer.valueOf(f1399Ul[i]));
        }
    }

    /* renamed from: va */
    public static String m2208va(int i) {
        String str = (String) f1401Wl.get(i);
        if (str != null) {
            return str;
        }
        throw new UnsupportedEncodingException();
    }

    /* renamed from: x */
    public static int m2209x(String str) {
        if (str == null) {
            return -1;
        }
        Integer num = (Integer) f1402Xl.get(str);
        if (num != null) {
            return num.intValue();
        }
        throw new UnsupportedEncodingException();
    }
}
