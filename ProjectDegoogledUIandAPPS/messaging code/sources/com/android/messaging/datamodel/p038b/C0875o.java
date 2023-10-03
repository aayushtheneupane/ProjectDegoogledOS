package com.android.messaging.datamodel.p038b;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import com.android.messaging.util.C1424b;
import java.util.Arrays;

/* renamed from: com.android.messaging.datamodel.b.o */
public class C0875o extends C0881u {
    /* access modifiers changed from: private */

    /* renamed from: WC */
    public final byte[] f1121WC;

    public C0875o(String str, byte[] bArr, int i) {
        super(str, i);
        this.f1121WC = bArr;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Th */
    public boolean mo6105Th() {
        return true;
    }

    /* renamed from: Uh */
    public Bitmap mo6154Uh() {
        return null;
    }

    /* renamed from: Vh */
    public boolean mo6155Vh() {
        return false;
    }

    /* renamed from: b */
    public Drawable mo6156b(Resources resources) {
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public C0883w mo6107c(C0883w wVar) {
        C1424b.m3592ia(true);
        return new C0874n(this);
    }

    /* access modifiers changed from: protected */
    public void close() {
    }

    public Bitmap getBitmap() {
        acquireLock();
        try {
            C1424b.m3584Gj();
            return BitmapFactory.decodeByteArray(this.f1121WC, 0, this.f1121WC.length);
        } finally {
            releaseLock();
        }
    }

    public byte[] getBytes() {
        acquireLock();
        try {
            return Arrays.copyOf(this.f1121WC, this.f1121WC.length);
        } finally {
            releaseLock();
        }
    }

    public int getMediaSize() {
        return this.f1121WC.length;
    }
}
