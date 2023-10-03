package com.google.android.apps.photosgo.face.facenet;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import java.io.Closeable;

/* compiled from: PG */
public final class FaceNetDetector implements Closeable {

    /* renamed from: a */
    public final long f4851a;

    /* renamed from: b */
    public boolean f4852b;

    public /* synthetic */ FaceNetDetector(long j) {
        this.f4851a = j;
    }

    private native void nativeClose(long j);

    public static native long nativeCreate(AssetManager assetManager, String str, String str2);

    public native byte[] nativeDetectFaces(long j, Bitmap bitmap);

    public final void close() {
        if (!this.f4852b) {
            nativeClose(this.f4851a);
            this.f4852b = true;
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
