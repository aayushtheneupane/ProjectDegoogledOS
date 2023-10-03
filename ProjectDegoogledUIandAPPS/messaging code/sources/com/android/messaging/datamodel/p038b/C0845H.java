package com.android.messaging.datamodel.p038b;

import android.graphics.BitmapFactory;

/* renamed from: com.android.messaging.datamodel.b.H */
public class C0845H extends C0882v {

    /* renamed from: Pe */
    private final C0844G f1098Pe = new C0844G(this);

    public C0845H(int i, int i2, String str) {
        super(i, i2, str);
    }

    /* renamed from: Cb */
    public C0844G mo6094Cb() {
        return this.f1098Pe;
    }

    /* renamed from: a */
    public static BitmapFactory.Options m1524a(boolean z, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = z;
        options.inDensity = i;
        options.inTargetDensity = i2;
        options.inSampleSize = 1;
        options.inJustDecodeBounds = false;
        options.inMutable = true;
        return options;
    }

    /* renamed from: a */
    public synchronized C0881u mo6095a(String str, C0881u uVar) {
        this.f1098Pe.mo6091b(uVar);
        return (C0881u) super.mo6095a(str, uVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void entryRemoved(boolean z, String str, C0881u uVar, C0881u uVar2) {
        this.f1098Pe.mo6092c(uVar);
        super.entryRemoved(z, str, uVar, uVar2);
    }
}
