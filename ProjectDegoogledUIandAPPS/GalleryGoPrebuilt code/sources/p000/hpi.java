package p000;

import java.util.Iterator;
import java.util.NoSuchElementException;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: hpi */
/* compiled from: PG */
abstract class hpi implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    public final CharSequence f13220a;

    /* renamed from: b */
    private Object f13221b;

    /* renamed from: c */
    private int f13222c = 2;

    /* renamed from: d */
    private final boolean f13223d;

    /* renamed from: e */
    private int f13224e = 0;

    /* renamed from: f */
    private int f13225f;

    /* renamed from: a */
    public abstract int mo7652a(int i);

    /* renamed from: b */
    public abstract int mo7653b(int i);

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        int a;
        int i;
        ife.m12896d(this.f13222c != 4);
        int i2 = this.f13222c;
        int i3 = i2 - 1;
        String str = null;
        if (i2 == 0) {
            throw null;
        } else if (i3 == 0) {
            return true;
        } else {
            if (i3 == 2) {
                return false;
            }
            this.f13222c = 4;
            int i4 = this.f13224e;
            while (true) {
                int i5 = this.f13224e;
                if (i5 == -1) {
                    this.f13222c = 3;
                    break;
                }
                a = mo7652a(i5);
                if (a == -1) {
                    a = this.f13220a.length();
                    this.f13224e = -1;
                    i = -1;
                } else {
                    i = mo7653b(a);
                    this.f13224e = i;
                }
                if (i == i4) {
                    int i6 = i + 1;
                    this.f13224e = i6;
                    if (i6 > this.f13220a.length()) {
                        this.f13224e = -1;
                    }
                } else {
                    if (i4 < a) {
                        this.f13220a.charAt(i4);
                    }
                    if (i4 < a) {
                        this.f13220a.charAt(a - 1);
                    }
                    if (this.f13223d && i4 == a) {
                        i4 = this.f13224e;
                    }
                }
            }
            int i7 = this.f13225f;
            if (i7 == 1) {
                a = this.f13220a.length();
                this.f13224e = -1;
                if (a > i4) {
                    this.f13220a.charAt(a - 1);
                }
            } else {
                this.f13225f = i7 - 1;
            }
            str = this.f13220a.subSequence(i4, a).toString();
            this.f13221b = str;
            if (this.f13222c == 3) {
                return false;
            }
            this.f13222c = 1;
            return true;
        }
    }

    public final Object next() {
        if (hasNext()) {
            this.f13222c = 2;
            Object obj = this.f13221b;
            this.f13221b = null;
            return obj;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    protected hpi(hqj hqj, CharSequence charSequence) {
        this.f13223d = hqj.f13258b;
        this.f13225f = hqj.f13260d;
        this.f13220a = charSequence;
    }
}
