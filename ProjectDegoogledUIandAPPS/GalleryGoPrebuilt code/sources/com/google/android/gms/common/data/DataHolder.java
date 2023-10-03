package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.io.Closeable;

/* compiled from: PG */
public final class DataHolder extends eqv implements Closeable {
    public static final Parcelable.Creator CREATOR = new eow();

    /* renamed from: a */
    public final String[] f5002a;

    /* renamed from: b */
    public Bundle f5003b;

    /* renamed from: c */
    public final CursorWindow[] f5004c;

    /* renamed from: d */
    public int[] f5005d;

    /* renamed from: e */
    private final int f5006e;

    /* renamed from: f */
    private final int f5007f;

    /* renamed from: g */
    private final Bundle f5008g;

    /* renamed from: h */
    private boolean f5009h = false;

    /* renamed from: i */
    private boolean f5010i = true;

    static {
        new eov(new String[0]);
    }

    public DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.f5006e = i;
        this.f5002a = strArr;
        this.f5004c = cursorWindowArr;
        this.f5007f = i2;
        this.f5008g = bundle;
    }

    public final void close() {
        synchronized (this) {
            if (!this.f5009h) {
                this.f5009h = true;
                int i = 0;
                while (true) {
                    CursorWindow[] cursorWindowArr = this.f5004c;
                    if (i >= cursorWindowArr.length) {
                        break;
                    }
                    cursorWindowArr[i].close();
                    i++;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void finalize() {
        boolean z;
        try {
            if (this.f5010i && this.f5004c.length > 0) {
                synchronized (this) {
                    z = this.f5009h;
                }
                if (!z) {
                    close();
                    String obj = toString();
                    StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 178);
                    sb.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
                    sb.append(obj);
                    sb.append(")");
                    Log.e("DataBuffer", sb.toString());
                }
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m104a(parcel, 1, this.f5002a);
        abj.m103a(parcel, 2, (Parcelable[]) this.f5004c, i);
        abj.m114b(parcel, 3, this.f5007f);
        abj.m95a(parcel, 4, this.f5008g);
        abj.m114b(parcel, 1000, this.f5006e);
        abj.m92a(parcel, a);
        if ((i & 1) != 0) {
            close();
        }
    }
}
