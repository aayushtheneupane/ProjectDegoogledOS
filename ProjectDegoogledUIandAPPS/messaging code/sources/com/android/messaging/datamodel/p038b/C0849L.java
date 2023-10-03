package com.android.messaging.datamodel.p038b;

import android.content.Context;
import android.net.Uri;
import com.android.messaging.util.C1488za;

/* renamed from: com.android.messaging.datamodel.b.L */
public class C0849L extends C0880t {

    /* renamed from: FC */
    public final boolean f1102FC;
    public final Uri uri;

    public C0849L(Uri uri2, int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4) {
        this(uri2, i, i2, -1, -1, z, z2, z3, i3, i4);
    }

    public String getKey() {
        if (this.uri == null) {
            return null;
        }
        String str = this.desiredWidth + '|' + this.f1128yC + '|' + String.valueOf(this.f1125CC) + '|' + String.valueOf(this.f1126DC) + '|' + String.valueOf(this.isStatic);
        if (str == null) {
            return null;
        }
        return this.uri + '|' + String.valueOf(this.f1102FC) + '|' + str;
    }

    /* renamed from: n */
    public C0883w mo6084n(Context context) {
        Uri uri2 = this.uri;
        if (uri2 == null || C1488za.m3861A(uri2)) {
            return new C0848K(context, this);
        }
        return new C0843F(context, this);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0849L(Uri uri2, int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, int i5, int i6) {
        super(i, i2, i3, i4, z2, z3, i5, i6);
        this.uri = uri2;
        this.f1102FC = z;
    }
}
