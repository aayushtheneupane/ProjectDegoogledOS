package com.google.common.base;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

/* renamed from: com.google.common.base.A */
public class C1504A {
    /* access modifiers changed from: private */
    public final String separator;

    private C1504A(String str) {
        if (str != null) {
            this.separator = str;
            return;
        }
        throw new NullPointerException();
    }

    /* renamed from: Ua */
    public static C1504A m3943Ua(String str) {
        return new C1504A(str);
    }

    /* renamed from: e */
    public static C1504A m3945e(char c) {
        return new C1504A(String.valueOf(c));
    }

    /* renamed from: Va */
    public C1504A mo8509Va(String str) {
        if (str != null) {
            return new C1548w(this, this, str);
        }
        throw new NullPointerException();
    }

    /* renamed from: Vk */
    public C1504A mo8510Vk() {
        return new C1549x(this, this);
    }

    /* renamed from: Wa */
    public C1551z mo8511Wa(String str) {
        return new C1551z(this, str, (C1548w) null);
    }

    /* renamed from: c */
    public final String mo8516c(Object[] objArr) {
        return mo8513a((Iterable) Arrays.asList(objArr));
    }

    /* access modifiers changed from: package-private */
    public CharSequence toString(Object obj) {
        if (obj != null) {
            return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    public Appendable mo8512a(Appendable appendable, Iterator it) {
        if (appendable != null) {
            if (it.hasNext()) {
                appendable.append(toString(it.next()));
                while (it.hasNext()) {
                    appendable.append(this.separator);
                    appendable.append(toString(it.next()));
                }
            }
            return appendable;
        }
        throw new NullPointerException();
    }

    /* synthetic */ C1504A(C1504A a, C1548w wVar) {
        this.separator = a.separator;
    }

    /* renamed from: a */
    public final StringBuilder mo8515a(StringBuilder sb, Iterator it) {
        try {
            mo8512a((Appendable) sb, it);
            return sb;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public final String mo8513a(Iterable iterable) {
        Iterator it = iterable.iterator();
        StringBuilder sb = new StringBuilder();
        mo8515a(sb, it);
        return sb.toString();
    }

    /* renamed from: a */
    public final String mo8514a(Object obj, Object obj2, Object... objArr) {
        if (objArr != null) {
            return mo8513a((Iterable) new C1550y(objArr, obj, obj2));
        }
        throw new NullPointerException();
    }
}
