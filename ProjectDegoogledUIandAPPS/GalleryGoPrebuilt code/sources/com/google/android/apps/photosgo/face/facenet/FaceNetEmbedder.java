package com.google.android.apps.photosgo.face.facenet;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import java.io.Closeable;

/* compiled from: PG */
public final class FaceNetEmbedder implements Closeable {

    /* renamed from: a */
    public final long f4853a;

    /* renamed from: b */
    public boolean f4854b;

    public /* synthetic */ FaceNetEmbedder(long j) {
        this.f4853a = j;
    }

    private native void nativeClose(long j);

    public static native long nativeCreate(AssetManager assetManager, String str);

    public native byte[] nativeRecognizeFace(long j, Bitmap bitmap, byte[] bArr);

    public final void close() {
        if (!this.f4854b) {
            nativeClose(this.f4853a);
            this.f4854b = true;
        }
    }

    /* access modifiers changed from: protected */
    public final void finalize() {
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
