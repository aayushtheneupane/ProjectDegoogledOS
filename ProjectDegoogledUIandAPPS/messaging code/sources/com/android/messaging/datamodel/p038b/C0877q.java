package com.android.messaging.datamodel.p038b;

import android.content.Context;
import com.android.messaging.util.C1488za;

/* renamed from: com.android.messaging.datamodel.b.q */
public class C0877q extends C0849L {

    /* renamed from: HC */
    public final boolean f1123HC;
    public final String path;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0877q(String str, int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3) {
        super(C1488za.m3867Na(str), i, i2, i3, i4, z2, z3, false, 0, 0);
        this.path = str;
        this.f1123HC = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getKey() {
        /*
            r5 = this;
            android.net.Uri r0 = r5.uri
            r1 = 124(0x7c, float:1.74E-43)
            r2 = 0
            if (r0 == 0) goto L_0x0064
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r3 = r5.desiredWidth
            r0.append(r3)
            r0.append(r1)
            int r3 = r5.f1128yC
            r0.append(r3)
            r0.append(r1)
            boolean r3 = r5.f1125CC
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r0.append(r3)
            r0.append(r1)
            int r3 = r5.f1126DC
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r0.append(r3)
            r0.append(r1)
            boolean r3 = r5.isStatic
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            if (r0 == 0) goto L_0x0064
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            android.net.Uri r4 = r5.uri
            r3.append(r4)
            r3.append(r1)
            boolean r4 = r5.f1102FC
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r3.append(r4)
            r3.append(r1)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            goto L_0x0065
        L_0x0064:
            r0 = r2
        L_0x0065:
            if (r0 != 0) goto L_0x0068
            goto L_0x0079
        L_0x0068:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r0)
            r2.append(r1)
            boolean r5 = r5.f1123HC
            r2.append(r5)
            java.lang.String r2 = r2.toString()
        L_0x0079:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.p038b.C0877q.getKey():java.lang.String");
    }

    /* renamed from: n */
    public C0883w mo6084n(Context context) {
        return new C0876p(context, this);
    }
}
