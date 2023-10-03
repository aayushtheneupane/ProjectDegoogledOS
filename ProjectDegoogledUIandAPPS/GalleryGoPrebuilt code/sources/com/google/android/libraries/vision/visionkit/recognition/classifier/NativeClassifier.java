package com.google.android.libraries.vision.visionkit.recognition.classifier;

import android.graphics.Bitmap;

/* compiled from: PG */
public class NativeClassifier {
    public static native byte[] classify(long j, Bitmap bitmap);

    public static native void close(long j);

    public static native String getClassName(long j, int i, int i2);

    public static native long initialize(byte[] bArr);
}
