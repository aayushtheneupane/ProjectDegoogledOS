package com.android.messaging.datamodel.p038b;

import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.datamodel.b.t */
public abstract class C0880t extends C0884x {

    /* renamed from: AC */
    public final int f1124AC;

    /* renamed from: CC */
    public final boolean f1125CC;

    /* renamed from: DC */
    public final int f1126DC;

    /* renamed from: EC */
    public final int f1127EC;
    public final int desiredWidth;
    public final boolean isStatic;

    /* renamed from: yC */
    public final int f1128yC;

    /* renamed from: zC */
    public final int f1129zC;

    public C0880t() {
        this(-1, -1, -1, -1, false, false, 0, 0);
    }

    public String getKey() {
        return this.desiredWidth + '|' + this.f1128yC + '|' + String.valueOf(this.f1125CC) + '|' + String.valueOf(this.f1126DC) + '|' + String.valueOf(this.isStatic);
    }

    /* renamed from: u */
    public void mo6083u(int i, int i2) {
    }

    public C0880t(int i, int i2, int i3, int i4, boolean z, boolean z2, int i5, int i6) {
        boolean z3 = false;
        C1424b.m3592ia(i == -1 || i > 0);
        C1424b.m3592ia(i2 == -1 || i2 > 0);
        C1424b.m3592ia(i3 == -1 || i3 > 0);
        C1424b.m3592ia((i4 == -1 || i4 > 0) ? true : z3);
        this.desiredWidth = i;
        this.f1128yC = i2;
        this.f1129zC = i3;
        this.f1124AC = i4;
        this.isStatic = z;
        this.f1125CC = z2;
        this.f1126DC = i5;
        this.f1127EC = i6;
    }
}
