package com.android.messaging.datamodel.p038b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.SparseArray;
import com.android.messaging.C0967f;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.b.G */
public class C0844G {

    /* renamed from: QC */
    private volatile int f1095QC = 0;

    /* renamed from: RC */
    private volatile int f1096RC = 0;

    /* renamed from: SC */
    private final SparseArray f1097SC = new SparseArray();
    final /* synthetic */ C0845H this$0;

    public C0844G(C0845H h) {
        this.this$0 = h;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ba, code lost:
        return null;
     */
    /* renamed from: X */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap m1513X(int r10, int r11) {
        /*
            r9 = this;
            com.android.messaging.datamodel.b.H r0 = r9.this$0
            monitor-enter(r0)
            r1 = 65535(0xffff, float:9.1834E-41)
            r2 = 0
            if (r10 > r1) goto L_0x0010
            if (r11 <= r1) goto L_0x000c
            goto L_0x0010
        L_0x000c:
            int r10 = r10 << 16
            r10 = r10 | r11
            goto L_0x0011
        L_0x0010:
            r10 = r2
        L_0x0011:
            r11 = 0
            if (r10 == 0) goto L_0x00b9
            android.util.SparseArray r1 = r9.f1097SC     // Catch:{ all -> 0x00bd }
            java.lang.Object r10 = r1.get(r10)     // Catch:{ all -> 0x00bd }
            java.util.LinkedList r10 = (java.util.LinkedList) r10     // Catch:{ all -> 0x00bd }
            if (r10 == 0) goto L_0x00b9
            int r1 = r10.size()     // Catch:{ all -> 0x00bd }
            if (r1 <= 0) goto L_0x00b9
            r1 = r2
        L_0x0025:
            int r3 = r10.size()     // Catch:{ all -> 0x00bd }
            r4 = 1
            if (r1 >= r3) goto L_0x0055
            java.lang.Object r3 = r10.get(r1)     // Catch:{ all -> 0x00bd }
            com.android.messaging.datamodel.b.u r3 = (com.android.messaging.datamodel.p038b.C0881u) r3     // Catch:{ all -> 0x00bd }
            int r5 = r3.getRefCount()     // Catch:{ all -> 0x00bd }
            if (r5 != r4) goto L_0x0052
            r3.acquireLock()     // Catch:{ all -> 0x00bd }
            int r5 = r3.getRefCount()     // Catch:{ all -> 0x00bd }
            if (r5 != r4) goto L_0x0048
            java.lang.Object r1 = r10.remove(r1)     // Catch:{ all -> 0x00bd }
            com.android.messaging.datamodel.b.u r1 = (com.android.messaging.datamodel.p038b.C0881u) r1     // Catch:{ all -> 0x00bd }
            goto L_0x0056
        L_0x0048:
            java.lang.String r4 = "MessagingAppImage"
            java.lang.String r5 = "Image refCount changed from 1 in getReusableBitmapFromPool()"
            com.android.messaging.util.C1430e.m3630w(r4, r5)     // Catch:{ all -> 0x00bd }
            r3.releaseLock()     // Catch:{ all -> 0x00bd }
        L_0x0052:
            int r1 = r1 + 1
            goto L_0x0025
        L_0x0055:
            r1 = r11
        L_0x0056:
            if (r1 != 0) goto L_0x005a
            monitor-exit(r0)     // Catch:{ all -> 0x00bd }
            return r11
        L_0x005a:
            r1.mo6101Ph()     // Catch:{ all -> 0x00b4 }
            long r5 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x00b4 }
            long r7 = r1.mo6103Rh()     // Catch:{ all -> 0x00b4 }
            long r5 = r5 - r7
            r7 = 5000(0x1388, double:2.4703E-320)
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x0093
            java.lang.String r9 = "MessagingAppImage"
            r2 = 2
            boolean r9 = android.util.Log.isLoggable(r9, r2)     // Catch:{ all -> 0x00b4 }
            if (r9 == 0) goto L_0x008b
            java.lang.String r9 = "MessagingAppImage"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b4 }
            r2.<init>()     // Catch:{ all -> 0x00b4 }
            java.lang.String r3 = "Not reusing reusing first available bitmap from the pool because it has not been in the pool long enough. timeSinceLastRef="
            r2.append(r3)     // Catch:{ all -> 0x00b4 }
            r2.append(r5)     // Catch:{ all -> 0x00b4 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00b4 }
            com.android.messaging.util.C1430e.m3628v(r9, r2)     // Catch:{ all -> 0x00b4 }
        L_0x008b:
            r10.addLast(r1)     // Catch:{ all -> 0x00b4 }
            r1.releaseLock()     // Catch:{ all -> 0x00bd }
            monitor-exit(r0)     // Catch:{ all -> 0x00bd }
            return r11
        L_0x0093:
            r1.mo6100Oh()     // Catch:{ all -> 0x00b4 }
            com.android.messaging.datamodel.b.H r9 = r9.this$0     // Catch:{ all -> 0x00b4 }
            java.lang.String r10 = r1.getKey()     // Catch:{ all -> 0x00b4 }
            java.lang.Object r9 = r9.remove(r10)     // Catch:{ all -> 0x00b4 }
            com.android.messaging.datamodel.b.u r9 = (com.android.messaging.datamodel.p038b.C0881u) r9     // Catch:{ all -> 0x00b4 }
            if (r9 != r1) goto L_0x00a5
            r2 = r4
        L_0x00a5:
            com.android.messaging.util.C1424b.m3592ia(r2)     // Catch:{ all -> 0x00b4 }
            android.graphics.Bitmap r9 = r1.mo6154Uh()     // Catch:{ all -> 0x00b4 }
            r1.release()     // Catch:{ all -> 0x00b4 }
            r1.releaseLock()     // Catch:{ all -> 0x00bd }
            monitor-exit(r0)     // Catch:{ all -> 0x00bd }
            return r9
        L_0x00b4:
            r9 = move-exception
            r1.releaseLock()     // Catch:{ all -> 0x00bd }
            throw r9     // Catch:{ all -> 0x00bd }
        L_0x00b9:
            monitor-exit(r0)     // Catch:{ all -> 0x00bd }
            return r11
        L_0x00bb:
            monitor-exit(r0)     // Catch:{ all -> 0x00bd }
            throw r9
        L_0x00bd:
            r9 = move-exception
            goto L_0x00bb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.p038b.C0844G.m1513X(int, int):android.graphics.Bitmap");
    }

    /* renamed from: d */
    private void m1514d(C0881u uVar) {
        synchronized (this.this$0) {
            int e = m1515e(uVar);
            C1424b.m3592ia(e != 0);
            LinkedList linkedList = (LinkedList) this.f1097SC.get(e);
            if (linkedList == null) {
                linkedList = new LinkedList();
                this.f1097SC.put(e, linkedList);
            }
            linkedList.addLast(uVar);
        }
    }

    /* renamed from: e */
    private int m1515e(C0881u uVar) {
        Bitmap bitmap;
        if (!uVar.mo6155Vh() || (bitmap = uVar.getBitmap()) == null || !bitmap.isMutable()) {
            return 0;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0 || width > 65535 || height > 65535) {
            return 0;
        }
        return (width << 16) | height;
    }

    /* renamed from: f */
    private void m1516f(C0881u uVar) {
        synchronized (this.this$0) {
            int e = m1515e(uVar);
            C1424b.m3592ia(e != 0);
            LinkedList linkedList = (LinkedList) this.f1097SC.get(e);
            if (linkedList != null) {
                linkedList.remove(uVar);
            }
        }
    }

    /* renamed from: ko */
    private void m1517ko() {
        this.f1095QC++;
        if (this.f1095QC % 100 == 0) {
            StringBuilder Pa = C0632a.m1011Pa("Pooled bitmap consistently not being reused. Failure count = ");
            Pa.append(this.f1095QC);
            Pa.append(", success count = ");
            Pa.append(this.f1096RC);
            C1430e.m3630w("MessagingAppImage", Pa.toString());
        }
    }

    /* renamed from: a */
    public Bitmap mo6089a(InputStream inputStream, BitmapFactory.Options options, int i, int i2) {
        Bitmap bitmap;
        if (i <= 0 || i2 <= 0) {
            C1430e.m3630w("MessagingAppImage", "PoolableImageCache: Decoding bitmap with invalid size");
            throw new IOException("Invalid size / corrupted image");
        }
        C1424b.m3594t(inputStream);
        if (!options.inJustDecodeBounds) {
            options.inBitmap = m1513X(i, i2);
        }
        try {
            bitmap = BitmapFactory.decodeStream(inputStream, (Rect) null, options);
            try {
                this.f1096RC++;
                return bitmap;
            } catch (IllegalArgumentException unused) {
            } catch (OutOfMemoryError unused2) {
                C1430e.m3630w("MessagingAppImage", "Oom decoding inputStream");
                C0967f.get().mo6656Sd();
                return bitmap;
            }
        } catch (IllegalArgumentException unused3) {
            bitmap = null;
            Bitmap bitmap2 = options.inBitmap;
            if (bitmap2 == null) {
                return bitmap;
            }
            bitmap2.recycle();
            options.inBitmap = null;
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, (Rect) null, options);
            m1517ko();
            return decodeStream;
        } catch (OutOfMemoryError unused4) {
            bitmap = null;
            C1430e.m3630w("MessagingAppImage", "Oom decoding inputStream");
            C0967f.get().mo6656Sd();
            return bitmap;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo6091b(C0881u uVar) {
        if (m1515e(uVar) != 0) {
            m1514d(uVar);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo6092c(C0881u uVar) {
        if (m1515e(uVar) != 0) {
            m1516f(uVar);
        }
    }

    /* renamed from: v */
    public Bitmap mo6093v(int i, int i2) {
        return mo6088a(i, i2, 0);
    }

    /* renamed from: a */
    public Bitmap mo6090a(byte[] bArr, BitmapFactory.Options options, int i, int i2) {
        Bitmap bitmap;
        if (i <= 0 || i2 <= 0) {
            C1430e.m3630w("MessagingAppImage", "PoolableImageCache: Decoding bitmap with invalid size");
            throw new IOException("Invalid size / corrupted image");
        }
        C1424b.m3594t(bArr);
        C1424b.m3594t(options);
        if (!options.inJustDecodeBounds) {
            options.inBitmap = m1513X(i, i2);
        }
        try {
            bitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            try {
                this.f1096RC++;
                return bitmap;
            } catch (IllegalArgumentException unused) {
            } catch (OutOfMemoryError unused2) {
                C1430e.m3630w("MessagingAppImage", "Oom decoding inputStream");
                C0967f.get().mo6656Sd();
                return bitmap;
            }
        } catch (IllegalArgumentException unused3) {
            bitmap = null;
            Bitmap bitmap2 = options.inBitmap;
            if (bitmap2 == null) {
                return bitmap;
            }
            bitmap2.recycle();
            options.inBitmap = null;
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            m1517ko();
            return decodeByteArray;
        } catch (OutOfMemoryError unused4) {
            bitmap = null;
            C1430e.m3630w("MessagingAppImage", "Oom decoding inputStream");
            C0967f.get().mo6656Sd();
            return bitmap;
        }
    }

    /* renamed from: a */
    public Bitmap mo6088a(int i, int i2, int i3) {
        Bitmap bitmap = null;
        try {
            Bitmap X = m1513X(i, i2);
            if (X == null) {
                X = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            }
            bitmap = X;
            bitmap.eraseColor(i3);
        } catch (OutOfMemoryError unused) {
            C1430e.m3630w("MessagingAppImage", "PoolableImageCache:try to createOrReuseBitmap");
            C0967f.get().mo6656Sd();
        }
        return bitmap;
    }
}
