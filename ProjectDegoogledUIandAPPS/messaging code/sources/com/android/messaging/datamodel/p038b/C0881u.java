package com.android.messaging.datamodel.p038b;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/* renamed from: com.android.messaging.datamodel.b.u */
public abstract class C0881u extends C0846I {
    protected final int mOrientation;

    public C0881u(String str, int i) {
        super(str);
        this.mOrientation = i;
    }

    /* renamed from: Uh */
    public abstract Bitmap mo6154Uh();

    /* renamed from: Vh */
    public abstract boolean mo6155Vh();

    /* renamed from: b */
    public abstract Drawable mo6156b(Resources resources);

    public abstract Bitmap getBitmap();

    public abstract byte[] getBytes();

    public int getOrientation() {
        return this.mOrientation;
    }
}
