package p000;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: atn */
/* compiled from: PG */
final class atn implements Iterable {

    /* renamed from: a */
    public final List f1629a;

    public atn() {
        this(new ArrayList(2));
    }

    private atn(List list) {
        this.f1629a = list;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo1592a(bef bef) {
        return this.f1629a.contains(m1624b(bef));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final atn mo1594c() {
        return new atn(new ArrayList(this.f1629a));
    }

    /* renamed from: b */
    public static atm m1624b(bef bef) {
        return new atm(bef, bfj.f2209b);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo1591a() {
        return this.f1629a.isEmpty();
    }

    public final Iterator iterator() {
        return this.f1629a.iterator();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final int mo1593b() {
        return this.f1629a.size();
    }
}
