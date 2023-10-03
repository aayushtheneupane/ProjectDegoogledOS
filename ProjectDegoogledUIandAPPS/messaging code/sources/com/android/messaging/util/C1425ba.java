package com.android.messaging.util;

import java.io.PrintWriter;

/* renamed from: com.android.messaging.util.ba */
public abstract class C1425ba {
    /* synthetic */ C1425ba(C1420Y y) {
    }

    /* renamed from: ja */
    static /* synthetic */ String m3595ja(int i) {
        switch (i) {
            case 2:
                return "V";
            case 3:
                return "D";
            case 4:
                return "I";
            case 5:
                return "W";
            case 6:
                return "E";
            case 7:
                return "A";
            default:
                return "?";
        }
    }

    /* renamed from: Oj */
    public abstract boolean mo8051Oj();

    /* renamed from: b */
    public abstract void mo8052b(int i, String str, String str2);

    public abstract void dump(PrintWriter printWriter);
}
