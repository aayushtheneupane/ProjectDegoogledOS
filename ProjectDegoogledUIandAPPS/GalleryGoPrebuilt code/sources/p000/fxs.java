package p000;

import android.accounts.Account;

/* renamed from: fxs */
/* compiled from: PG */
public final class fxs {

    /* renamed from: a */
    public static final Account f10684a = new Account("shared", "mobstore");

    /* renamed from: a */
    public static Account m9850a(String str) {
        if ("shared".equals(str)) {
            return f10684a;
        }
        int indexOf = str.indexOf(58);
        fym.m9880a(indexOf >= 0, "Malformed account", new Object[0]);
        return new Account(str.substring(indexOf + 1), str.substring(0, indexOf));
    }
}
