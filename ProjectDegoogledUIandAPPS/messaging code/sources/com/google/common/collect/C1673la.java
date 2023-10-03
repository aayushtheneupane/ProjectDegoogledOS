package com.google.common.collect;

import android.support.p016v4.media.session.C0107q;
import com.google.common.base.C1507D;
import com.google.common.base.C1508E;
import com.google.common.base.C1525S;
import com.google.common.base.C1546u;
import com.google.common.collect.MapMakerInternalMap;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.common.collect.la */
public final class C1673la {

    /* renamed from: LN */
    boolean f2537LN;

    /* renamed from: MN */
    int f2538MN = -1;

    /* renamed from: NN */
    MapMaker$RemovalCause f2539NN;
    int concurrencyLevel = -1;
    long expireAfterAccessNanos = -1;
    long expireAfterWriteNanos = -1;
    C1546u keyEquivalence;
    MapMakerInternalMap.Strength keyStrength;
    int maximumSize = -1;
    C1670ka removalListener;
    C1525S ticker;
    MapMakerInternalMap.Strength valueStrength;

    /* renamed from: c */
    private void m4593c(long j, TimeUnit timeUnit) {
        C1508E.m3962a(this.expireAfterWriteNanos == -1, "expireAfterWrite was already set to %s ns", Long.valueOf(this.expireAfterWriteNanos));
        C1508E.m3962a(this.expireAfterAccessNanos == -1, "expireAfterAccess was already set to %s ns", Long.valueOf(this.expireAfterAccessNanos));
        C1508E.checkArgument(j >= 0, "duration cannot be negative: %s %s", Long.valueOf(j), timeUnit);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C1673la mo9213a(MapMakerInternalMap.Strength strength) {
        boolean z = false;
        C1508E.m3962a(this.keyStrength == null, "Key strength was already set to %s", this.keyStrength);
        if (strength != null) {
            this.keyStrength = strength;
            if (this.keyStrength != MapMakerInternalMap.Strength.SOFT) {
                z = true;
            }
            C1508E.checkArgument(z, "Soft keys are not supported");
            if (strength != MapMakerInternalMap.Strength.STRONG) {
                this.f2537LN = true;
            }
            return this;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    /* renamed from: b */
    public C1673la mo9214b(long j, TimeUnit timeUnit) {
        m4593c(j, timeUnit);
        this.expireAfterWriteNanos = timeUnit.toNanos(j);
        if (j == 0 && this.f2539NN == null) {
            this.f2539NN = MapMaker$RemovalCause.EXPIRED;
        }
        this.f2537LN = true;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: sl */
    public int mo9215sl() {
        int i = this.concurrencyLevel;
        if (i == -1) {
            return 4;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: tl */
    public int mo9216tl() {
        int i = this.f2538MN;
        if (i == -1) {
            return 16;
        }
        return i;
    }

    public String toString() {
        C1507D x = C1508E.m3965x(this);
        int i = this.f2538MN;
        if (i != -1) {
            x.mo8519h("initialCapacity", i);
        }
        int i2 = this.concurrencyLevel;
        if (i2 != -1) {
            x.mo8519h("concurrencyLevel", i2);
        }
        int i3 = this.maximumSize;
        if (i3 != -1) {
            x.mo8519h("maximumSize", i3);
        }
        if (this.expireAfterWriteNanos != -1) {
            x.add("expireAfterWrite", this.expireAfterWriteNanos + "ns");
        }
        if (this.expireAfterAccessNanos != -1) {
            x.add("expireAfterAccess", this.expireAfterAccessNanos + "ns");
        }
        MapMakerInternalMap.Strength strength = this.keyStrength;
        if (strength != null) {
            x.add("keyStrength", C0107q.toLowerCase(strength.toString()));
        }
        MapMakerInternalMap.Strength strength2 = this.valueStrength;
        if (strength2 != null) {
            x.add("valueStrength", C0107q.toLowerCase(strength2.toString()));
        }
        if (this.keyEquivalence != null) {
            x.mo8521w("keyEquivalence");
        }
        if (this.removalListener != null) {
            x.mo8521w("removalListener");
        }
        return x.toString();
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    /* renamed from: a */
    public C1673la mo9212a(long j, TimeUnit timeUnit) {
        m4593c(j, timeUnit);
        this.expireAfterAccessNanos = timeUnit.toNanos(j);
        if (j == 0 && this.f2539NN == null) {
            this.f2539NN = MapMaker$RemovalCause.EXPIRED;
        }
        this.f2537LN = true;
        return this;
    }
}
