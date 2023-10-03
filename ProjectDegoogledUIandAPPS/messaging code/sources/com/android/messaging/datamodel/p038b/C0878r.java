package com.android.messaging.datamodel.p038b;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.rastermill.FrameSequence;
import android.support.rastermill.FrameSequenceDrawable;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.datamodel.b.r */
public class C0878r extends C0881u {
    private FrameSequence mFrameSequence;

    public C0878r(String str, FrameSequence frameSequence) {
        super(str, 1);
        this.mFrameSequence = frameSequence;
    }

    /* renamed from: Sh */
    public boolean mo6104Sh() {
        return false;
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
        try {
            return new FrameSequenceDrawable(this.mFrameSequence);
        } catch (Throwable th) {
            C1430e.m3623e("MessagingApp", "Error getting drawable for GIF", th);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void close() {
        acquireLock();
        try {
            if (this.mFrameSequence != null) {
                this.mFrameSequence = null;
            }
        } finally {
            releaseLock();
        }
    }

    public Bitmap getBitmap() {
        C1424b.fail("GetBitmap() should never be called on a gif.");
        return null;
    }

    public byte[] getBytes() {
        C1424b.fail("GetBytes() should never be called on a gif.");
        return null;
    }

    public int getMediaSize() {
        C1424b.fail("GifImageResource should not be used by a media cache");
        return 0;
    }
}
