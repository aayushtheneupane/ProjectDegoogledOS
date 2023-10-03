package p000;

import java.util.EnumMap;
import java.util.Iterator;

/* renamed from: hsi */
/* compiled from: PG */
public final class hsi extends hss {

    /* renamed from: a */
    private final transient EnumMap f13345a;

    public hsi(EnumMap enumMap) {
        this.f13345a = enumMap;
        ife.m12890c(!enumMap.isEmpty());
    }

    /* renamed from: c */
    public final boolean mo7899c() {
        return false;
    }

    public /* synthetic */ hsi(EnumMap enumMap, byte[] bArr) {
        this(enumMap);
    }

    public final boolean containsKey(Object obj) {
        return this.f13345a.containsKey(obj);
    }

    /* renamed from: b */
    public final hvr mo7898b() {
        return new huh(this.f13345a.entrySet().iterator());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hsi) {
            obj = ((hsi) obj).f13345a;
        }
        return this.f13345a.equals(obj);
    }

    public final Object get(Object obj) {
        return this.f13345a.get(obj);
    }

    /* renamed from: a */
    public final hvr mo7897a() {
        Iterator it = this.f13345a.keySet().iterator();
        ife.m12898e((Object) it);
        if (!(it instanceof hvr)) {
            return new htt(it);
        }
        return (hvr) it;
    }

    public final int size() {
        return this.f13345a.size();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new hsh(this.f13345a);
    }
}
