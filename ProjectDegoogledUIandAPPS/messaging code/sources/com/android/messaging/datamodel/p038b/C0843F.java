package com.android.messaging.datamodel.p038b;

import android.content.Context;
import com.android.messaging.util.C1424b;
import java.io.InputStream;

/* renamed from: com.android.messaging.datamodel.b.F */
public class C0843F extends C0879s {
    public C0843F(Context context, C0849L l) {
        super(context, l);
        this.mOrientation = 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Jh */
    public InputStream mo6085Jh() {
        C1424b.m3584Gj();
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0068 A[Catch:{ MalformedURLException -> 0x0069, IOException -> 0x0047, all -> 0x0045 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* renamed from: Lh */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo6086Lh() {
        /*
            r6 = this;
            java.lang.String r0 = "MessagingApp"
            com.android.messaging.util.C1424b.m3584Gj()
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0069, IOException -> 0x0047 }
            com.android.messaging.datamodel.b.t r3 = r6.mDescriptor     // Catch:{ MalformedURLException -> 0x0069, IOException -> 0x0047 }
            com.android.messaging.datamodel.b.L r3 = (com.android.messaging.datamodel.p038b.C0849L) r3     // Catch:{ MalformedURLException -> 0x0069, IOException -> 0x0047 }
            android.net.Uri r3 = r3.uri     // Catch:{ MalformedURLException -> 0x0069, IOException -> 0x0047 }
            java.lang.String r3 = r3.toString()     // Catch:{ MalformedURLException -> 0x0069, IOException -> 0x0047 }
            r2.<init>(r3)     // Catch:{ MalformedURLException -> 0x0069, IOException -> 0x0047 }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ MalformedURLException -> 0x0069, IOException -> 0x0047 }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ MalformedURLException -> 0x0069, IOException -> 0x0047 }
            r2.connect()     // Catch:{ MalformedURLException -> 0x0040, IOException -> 0x003b, all -> 0x0038 }
            int r1 = r2.getResponseCode()     // Catch:{ MalformedURLException -> 0x0040, IOException -> 0x003b, all -> 0x0038 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r1 != r3) goto L_0x0034
            java.lang.String r1 = "image/gif"
            java.lang.String r3 = r2.getContentType()     // Catch:{ MalformedURLException -> 0x0040, IOException -> 0x003b, all -> 0x0038 }
            boolean r6 = r1.equalsIgnoreCase(r3)     // Catch:{ MalformedURLException -> 0x0040, IOException -> 0x003b, all -> 0x0038 }
            r2.disconnect()
            return r6
        L_0x0034:
            r2.disconnect()
            goto L_0x008d
        L_0x0038:
            r6 = move-exception
            r1 = r2
            goto L_0x008f
        L_0x003b:
            r1 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
            goto L_0x0048
        L_0x0040:
            r1 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
            goto L_0x006a
        L_0x0045:
            r6 = move-exception
            goto L_0x008f
        L_0x0047:
            r2 = move-exception
        L_0x0048:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0045 }
            r3.<init>()     // Catch:{ all -> 0x0045 }
            java.lang.String r4 = "IOException trying to get inputStream for image with url: "
            r3.append(r4)     // Catch:{ all -> 0x0045 }
            com.android.messaging.datamodel.b.t r6 = r6.mDescriptor     // Catch:{ all -> 0x0045 }
            com.android.messaging.datamodel.b.L r6 = (com.android.messaging.datamodel.p038b.C0849L) r6     // Catch:{ all -> 0x0045 }
            android.net.Uri r6 = r6.uri     // Catch:{ all -> 0x0045 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0045 }
            r3.append(r6)     // Catch:{ all -> 0x0045 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x0045 }
            com.android.messaging.util.C1430e.m3623e(r0, r6, r2)     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x008d
            goto L_0x008a
        L_0x0069:
            r2 = move-exception
        L_0x006a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0045 }
            r3.<init>()     // Catch:{ all -> 0x0045 }
            java.lang.String r4 = "MalformedUrl for image with url: "
            r3.append(r4)     // Catch:{ all -> 0x0045 }
            com.android.messaging.datamodel.b.t r6 = r6.mDescriptor     // Catch:{ all -> 0x0045 }
            com.android.messaging.datamodel.b.L r6 = (com.android.messaging.datamodel.p038b.C0849L) r6     // Catch:{ all -> 0x0045 }
            android.net.Uri r6 = r6.uri     // Catch:{ all -> 0x0045 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0045 }
            r3.append(r6)     // Catch:{ all -> 0x0045 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x0045 }
            com.android.messaging.util.C1430e.m3623e(r0, r6, r2)     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x008d
        L_0x008a:
            r1.disconnect()
        L_0x008d:
            r6 = 0
            return r6
        L_0x008f:
            if (r1 == 0) goto L_0x0094
            r1.disconnect()
        L_0x0094:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.p038b.C0843F.mo6086Lh():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00ae, code lost:
        if (r2 != null) goto L_0x0032;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063 A[Catch:{ MalformedURLException -> 0x008e, OutOfMemoryError -> 0x0064, IOException -> 0x0041, all -> 0x003d, all -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008d A[Catch:{ MalformedURLException -> 0x008e, OutOfMemoryError -> 0x0064, IOException -> 0x0041, all -> 0x003d, all -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b5  */
    /* renamed from: Mh */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap mo6087Mh() {
        /*
            r6 = this;
            java.lang.String r0 = "MessagingApp"
            com.android.messaging.util.C1424b.m3584Gj()
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ MalformedURLException -> 0x008e, OutOfMemoryError -> 0x0064, IOException -> 0x0041, all -> 0x003d }
            com.android.messaging.datamodel.b.t r3 = r6.mDescriptor     // Catch:{ MalformedURLException -> 0x008e, OutOfMemoryError -> 0x0064, IOException -> 0x0041, all -> 0x003d }
            com.android.messaging.datamodel.b.L r3 = (com.android.messaging.datamodel.p038b.C0849L) r3     // Catch:{ MalformedURLException -> 0x008e, OutOfMemoryError -> 0x0064, IOException -> 0x0041, all -> 0x003d }
            android.net.Uri r3 = r3.uri     // Catch:{ MalformedURLException -> 0x008e, OutOfMemoryError -> 0x0064, IOException -> 0x0041, all -> 0x003d }
            java.lang.String r3 = r3.toString()     // Catch:{ MalformedURLException -> 0x008e, OutOfMemoryError -> 0x0064, IOException -> 0x0041, all -> 0x003d }
            r2.<init>(r3)     // Catch:{ MalformedURLException -> 0x008e, OutOfMemoryError -> 0x0064, IOException -> 0x0041, all -> 0x003d }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ MalformedURLException -> 0x008e, OutOfMemoryError -> 0x0064, IOException -> 0x0041, all -> 0x003d }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ MalformedURLException -> 0x008e, OutOfMemoryError -> 0x0064, IOException -> 0x0041, all -> 0x003d }
            r3 = 1
            r2.setDoInput(r3)     // Catch:{ MalformedURLException -> 0x003b, OutOfMemoryError -> 0x0039, IOException -> 0x0037 }
            r2.connect()     // Catch:{ MalformedURLException -> 0x003b, OutOfMemoryError -> 0x0039, IOException -> 0x0037 }
            int r3 = r2.getResponseCode()     // Catch:{ MalformedURLException -> 0x003b, OutOfMemoryError -> 0x0039, IOException -> 0x0037 }
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 != r4) goto L_0x0032
            java.io.InputStream r3 = r2.getInputStream()     // Catch:{ MalformedURLException -> 0x003b, OutOfMemoryError -> 0x0039, IOException -> 0x0037 }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r3)     // Catch:{ MalformedURLException -> 0x003b, OutOfMemoryError -> 0x0039, IOException -> 0x0037 }
        L_0x0032:
            r2.disconnect()
            goto L_0x00b1
        L_0x0037:
            r3 = move-exception
            goto L_0x0043
        L_0x0039:
            r3 = move-exception
            goto L_0x0066
        L_0x003b:
            r3 = move-exception
            goto L_0x0090
        L_0x003d:
            r6 = move-exception
            r2 = r1
            goto L_0x00b3
        L_0x0041:
            r3 = move-exception
            r2 = r1
        L_0x0043:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b2 }
            r4.<init>()     // Catch:{ all -> 0x00b2 }
            java.lang.String r5 = "IOException trying to get inputStream for image with url: "
            r4.append(r5)     // Catch:{ all -> 0x00b2 }
            com.android.messaging.datamodel.b.t r6 = r6.mDescriptor     // Catch:{ all -> 0x00b2 }
            com.android.messaging.datamodel.b.L r6 = (com.android.messaging.datamodel.p038b.C0849L) r6     // Catch:{ all -> 0x00b2 }
            android.net.Uri r6 = r6.uri     // Catch:{ all -> 0x00b2 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00b2 }
            r4.append(r6)     // Catch:{ all -> 0x00b2 }
            java.lang.String r6 = r4.toString()     // Catch:{ all -> 0x00b2 }
            com.android.messaging.util.C1430e.m3623e(r0, r6, r3)     // Catch:{ all -> 0x00b2 }
            if (r2 == 0) goto L_0x00b1
            goto L_0x0032
        L_0x0064:
            r3 = move-exception
            r2 = r1
        L_0x0066:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b2 }
            r4.<init>()     // Catch:{ all -> 0x00b2 }
            java.lang.String r5 = "OutOfMemoryError for image with url: "
            r4.append(r5)     // Catch:{ all -> 0x00b2 }
            com.android.messaging.datamodel.b.t r6 = r6.mDescriptor     // Catch:{ all -> 0x00b2 }
            com.android.messaging.datamodel.b.L r6 = (com.android.messaging.datamodel.p038b.C0849L) r6     // Catch:{ all -> 0x00b2 }
            android.net.Uri r6 = r6.uri     // Catch:{ all -> 0x00b2 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00b2 }
            r4.append(r6)     // Catch:{ all -> 0x00b2 }
            java.lang.String r6 = r4.toString()     // Catch:{ all -> 0x00b2 }
            com.android.messaging.util.C1430e.m3623e(r0, r6, r3)     // Catch:{ all -> 0x00b2 }
            com.android.messaging.f r6 = com.android.messaging.C0967f.get()     // Catch:{ all -> 0x00b2 }
            r6.mo6656Sd()     // Catch:{ all -> 0x00b2 }
            if (r2 == 0) goto L_0x00b1
            goto L_0x0032
        L_0x008e:
            r3 = move-exception
            r2 = r1
        L_0x0090:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b2 }
            r4.<init>()     // Catch:{ all -> 0x00b2 }
            java.lang.String r5 = "MalformedUrl for image with url: "
            r4.append(r5)     // Catch:{ all -> 0x00b2 }
            com.android.messaging.datamodel.b.t r6 = r6.mDescriptor     // Catch:{ all -> 0x00b2 }
            com.android.messaging.datamodel.b.L r6 = (com.android.messaging.datamodel.p038b.C0849L) r6     // Catch:{ all -> 0x00b2 }
            android.net.Uri r6 = r6.uri     // Catch:{ all -> 0x00b2 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00b2 }
            r4.append(r6)     // Catch:{ all -> 0x00b2 }
            java.lang.String r6 = r4.toString()     // Catch:{ all -> 0x00b2 }
            com.android.messaging.util.C1430e.m3623e(r0, r6, r3)     // Catch:{ all -> 0x00b2 }
            if (r2 == 0) goto L_0x00b1
            goto L_0x0032
        L_0x00b1:
            return r1
        L_0x00b2:
            r6 = move-exception
        L_0x00b3:
            if (r2 == 0) goto L_0x00b8
            r2.disconnect()
        L_0x00b8:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.p038b.C0843F.mo6087Mh():android.graphics.Bitmap");
    }
}
