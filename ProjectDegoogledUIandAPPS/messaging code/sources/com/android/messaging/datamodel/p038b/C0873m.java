package com.android.messaging.datamodel.p038b;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.android.messaging.p041ui.C1067U;
import com.android.messaging.util.C1416U;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;

/* renamed from: com.android.messaging.datamodel.b.m */
public class C0873m extends C0881u {

    /* renamed from: VC */
    private boolean f1120VC = true;
    private Bitmap mBitmap;
    private final int mOrientation;

    public C0873m(String str, Bitmap bitmap, int i) {
        super(str, i);
        this.mBitmap = bitmap;
        this.mOrientation = i;
    }

    /* renamed from: R */
    public void mo6153R(boolean z) {
        this.f1120VC = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Sh */
    public boolean mo6104Sh() {
        return this.f1120VC;
    }

    /* renamed from: Uh */
    public Bitmap mo6154Uh() {
        acquireLock();
        try {
            mo6102Qh();
            Bitmap bitmap = this.mBitmap;
            this.mBitmap = null;
            return bitmap;
        } finally {
            releaseLock();
        }
    }

    /* renamed from: Vh */
    public boolean mo6155Vh() {
        return true;
    }

    /* renamed from: b */
    public Drawable mo6156b(Resources resources) {
        acquireLock();
        try {
            C1424b.m3594t(this.mBitmap);
            return C1067U.m2646a(getOrientation(), resources, this.mBitmap);
        } finally {
            releaseLock();
        }
    }

    /* access modifiers changed from: protected */
    public void close() {
        acquireLock();
        try {
            if (this.mBitmap != null) {
                this.mBitmap.recycle();
                this.mBitmap = null;
            }
        } finally {
            releaseLock();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public C0883w mo6109d(C0883w wVar) {
        C1424b.m3591ha(false);
        if (getBitmap().hasAlpha()) {
            return null;
        }
        return new C0872l(this, wVar);
    }

    public Bitmap getBitmap() {
        acquireLock();
        try {
            return this.mBitmap;
        } finally {
            releaseLock();
        }
    }

    /* JADX INFO: finally extract failed */
    public byte[] getBytes() {
        acquireLock();
        try {
            byte[] a = C1416U.m3567a(this.mBitmap, 100);
            releaseLock();
            return a;
        } catch (Exception e) {
            C1430e.m3622e("MessagingApp", "Error trying to get the bitmap bytes " + e);
            releaseLock();
            return null;
        } catch (Throwable th) {
            releaseLock();
            throw th;
        }
    }

    public int getMediaSize() {
        acquireLock();
        try {
            C1424b.m3594t(this.mBitmap);
            if (C1464na.m3757Xj()) {
                return this.mBitmap.getAllocationByteCount();
            }
            int rowBytes = this.mBitmap.getRowBytes() * this.mBitmap.getHeight();
            releaseLock();
            return rowBytes;
        } finally {
            releaseLock();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }
}
