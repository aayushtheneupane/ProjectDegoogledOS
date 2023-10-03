package com.android.messaging.mmslib.p040b;

import android.drm.DrmConvertedStatus;
import android.drm.DrmManagerClient;
import android.util.Log;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.mmslib.b.d */
public class C0999d {

    /* renamed from: KD */
    private DrmManagerClient f1447KD;

    /* renamed from: LD */
    private int f1448LD;

    private C0999d(DrmManagerClient drmManagerClient, int i) {
        this.f1447KD = drmManagerClient;
        this.f1448LD = i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x004e A[ADDED_TO_REGION] */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.messaging.mmslib.p040b.C0999d m2304f(android.content.Context r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "DrmConvertSession"
            r1 = 0
            r2 = -1
            if (r6 == 0) goto L_0x004b
            if (r7 == 0) goto L_0x004b
            java.lang.String r3 = ""
            boolean r3 = r7.equals(r3)
            if (r3 != 0) goto L_0x004b
            android.drm.DrmManagerClient r3 = new android.drm.DrmManagerClient     // Catch:{ IllegalArgumentException -> 0x0044, IllegalStateException -> 0x003d }
            r3.<init>(r6)     // Catch:{ IllegalArgumentException -> 0x0044, IllegalStateException -> 0x003d }
            int r6 = r3.openConvertSession(r7)     // Catch:{ IllegalArgumentException -> 0x0022, IllegalStateException -> 0x001b }
            r2 = r6
            goto L_0x004c
        L_0x001b:
            r6 = move-exception
            java.lang.String r7 = "Could not access Open DrmFramework."
            android.util.Log.w(r0, r7, r6)     // Catch:{ IllegalArgumentException -> 0x0045, IllegalStateException -> 0x003e }
            goto L_0x004c
        L_0x0022:
            r6 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0045, IllegalStateException -> 0x003e }
            r4.<init>()     // Catch:{ IllegalArgumentException -> 0x0045, IllegalStateException -> 0x003e }
            java.lang.String r5 = "Conversion of Mimetype: "
            r4.append(r5)     // Catch:{ IllegalArgumentException -> 0x0045, IllegalStateException -> 0x003e }
            r4.append(r7)     // Catch:{ IllegalArgumentException -> 0x0045, IllegalStateException -> 0x003e }
            java.lang.String r7 = " is not supported."
            r4.append(r7)     // Catch:{ IllegalArgumentException -> 0x0045, IllegalStateException -> 0x003e }
            java.lang.String r7 = r4.toString()     // Catch:{ IllegalArgumentException -> 0x0045, IllegalStateException -> 0x003e }
            android.util.Log.w(r0, r7, r6)     // Catch:{ IllegalArgumentException -> 0x0045, IllegalStateException -> 0x003e }
            goto L_0x004c
        L_0x003d:
            r3 = r1
        L_0x003e:
            java.lang.String r6 = "DrmManagerClient didn't initialize properly."
            android.util.Log.w(r0, r6)
            goto L_0x004c
        L_0x0044:
            r3 = r1
        L_0x0045:
            java.lang.String r6 = "DrmManagerClient instance could not be created, context is Illegal."
            android.util.Log.w(r0, r6)
            goto L_0x004c
        L_0x004b:
            r3 = r1
        L_0x004c:
            if (r3 == 0) goto L_0x0057
            if (r2 >= 0) goto L_0x0051
            goto L_0x0057
        L_0x0051:
            com.android.messaging.mmslib.b.d r6 = new com.android.messaging.mmslib.b.d
            r6.<init>(r3, r2)
            return r6
        L_0x0057:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.mmslib.p040b.C0999d.m2304f(android.content.Context, java.lang.String):com.android.messaging.mmslib.b.d");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0085 A[SYNTHETIC, Splitter:B:40:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00a2 A[SYNTHETIC, Splitter:B:50:0x00a2] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00d6 A[SYNTHETIC, Splitter:B:60:0x00d6] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0107 A[SYNTHETIC, Splitter:B:69:0x0107] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0126 A[SYNTHETIC, Splitter:B:79:0x0126] */
    /* JADX WARNING: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:57:0x00bb=Splitter:B:57:0x00bb, B:66:0x00ec=Splitter:B:66:0x00ec} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int close(java.lang.String r11) {
        /*
            r10 = this;
            java.lang.String r0 = "."
            java.lang.String r1 = "Failed to close File:"
            java.lang.String r2 = "DrmConvertSession"
            android.drm.DrmManagerClient r3 = r10.f1447KD
            r4 = 491(0x1eb, float:6.88E-43)
            if (r3 == 0) goto L_0x015a
            int r5 = r10.f1448LD
            if (r5 < 0) goto L_0x015a
            r6 = 492(0x1ec, float:6.9E-43)
            android.drm.DrmConvertedStatus r3 = r3.closeConvertSession(r5)     // Catch:{ IllegalStateException -> 0x003d }
            if (r3 == 0) goto L_0x0145
            int r5 = r3.statusCode     // Catch:{ IllegalStateException -> 0x003d }
            r7 = 1
            if (r5 != r7) goto L_0x0145
            byte[] r5 = r3.convertedData     // Catch:{ IllegalStateException -> 0x003d }
            if (r5 != 0) goto L_0x0023
            goto L_0x0145
        L_0x0023:
            r5 = 0
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ FileNotFoundException -> 0x00eb, IOException -> 0x00ba, IllegalArgumentException -> 0x009a, SecurityException -> 0x0069 }
            java.lang.String r8 = "rw"
            r7.<init>(r11, r8)     // Catch:{ FileNotFoundException -> 0x00eb, IOException -> 0x00ba, IllegalArgumentException -> 0x009a, SecurityException -> 0x0069 }
            int r5 = r3.offset     // Catch:{ FileNotFoundException -> 0x0061, IOException -> 0x005e, IllegalArgumentException -> 0x005b, SecurityException -> 0x0058, all -> 0x0055 }
            long r8 = (long) r5     // Catch:{ FileNotFoundException -> 0x0061, IOException -> 0x005e, IllegalArgumentException -> 0x005b, SecurityException -> 0x0058, all -> 0x0055 }
            r7.seek(r8)     // Catch:{ FileNotFoundException -> 0x0061, IOException -> 0x005e, IllegalArgumentException -> 0x005b, SecurityException -> 0x0058, all -> 0x0055 }
            byte[] r3 = r3.convertedData     // Catch:{ FileNotFoundException -> 0x0061, IOException -> 0x005e, IllegalArgumentException -> 0x005b, SecurityException -> 0x0058, all -> 0x0055 }
            r7.write(r3)     // Catch:{ FileNotFoundException -> 0x0061, IOException -> 0x005e, IllegalArgumentException -> 0x005b, SecurityException -> 0x0058, all -> 0x0055 }
            r4 = 200(0xc8, float:2.8E-43)
            r7.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x015a
        L_0x003d:
            r11 = move-exception
            goto L_0x0148
        L_0x0040:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.<init>()     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r1)     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r11)     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r0)     // Catch:{ IllegalStateException -> 0x00a7 }
        L_0x004f:
            java.lang.String r11 = r4.toString()     // Catch:{ IllegalStateException -> 0x00a7 }
            goto L_0x011c
        L_0x0055:
            r3 = move-exception
            goto L_0x0124
        L_0x0058:
            r3 = move-exception
            r5 = r7
            goto L_0x006a
        L_0x005b:
            r3 = move-exception
            r5 = r7
            goto L_0x009b
        L_0x005e:
            r3 = move-exception
            r5 = r7
            goto L_0x00bb
        L_0x0061:
            r3 = move-exception
            r5 = r7
            goto L_0x00ec
        L_0x0065:
            r3 = move-exception
            r7 = r5
            goto L_0x0124
        L_0x0069:
            r3 = move-exception
        L_0x006a:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0065 }
            r7.<init>()     // Catch:{ all -> 0x0065 }
            java.lang.String r8 = "Access to File: "
            r7.append(r8)     // Catch:{ all -> 0x0065 }
            r7.append(r11)     // Catch:{ all -> 0x0065 }
            java.lang.String r8 = " was denied denied by SecurityManager."
            r7.append(r8)     // Catch:{ all -> 0x0065 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0065 }
            android.util.Log.w(r2, r7, r3)     // Catch:{ all -> 0x0065 }
            if (r5 == 0) goto L_0x015a
            r5.close()     // Catch:{ IOException -> 0x008a }
            goto L_0x015a
        L_0x008a:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.<init>()     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r1)     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r11)     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r0)     // Catch:{ IllegalStateException -> 0x00a7 }
            goto L_0x004f
        L_0x009a:
            r3 = move-exception
        L_0x009b:
            java.lang.String r4 = "Could not open file in mode: rw"
            android.util.Log.w(r2, r4, r3)     // Catch:{ all -> 0x0121 }
            if (r5 == 0) goto L_0x011f
            r5.close()     // Catch:{ IOException -> 0x00aa }
            goto L_0x011f
        L_0x00a7:
            r11 = move-exception
            goto L_0x0142
        L_0x00aa:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.<init>()     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r1)     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r11)     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r0)     // Catch:{ IllegalStateException -> 0x00a7 }
            goto L_0x004f
        L_0x00ba:
            r3 = move-exception
        L_0x00bb:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0121 }
            r4.<init>()     // Catch:{ all -> 0x0121 }
            java.lang.String r7 = "Could not access File: "
            r4.append(r7)     // Catch:{ all -> 0x0121 }
            r4.append(r11)     // Catch:{ all -> 0x0121 }
            java.lang.String r7 = " ."
            r4.append(r7)     // Catch:{ all -> 0x0121 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0121 }
            android.util.Log.w(r2, r4, r3)     // Catch:{ all -> 0x0121 }
            if (r5 == 0) goto L_0x011f
            r5.close()     // Catch:{ IOException -> 0x00da }
            goto L_0x011f
        L_0x00da:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.<init>()     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r1)     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r11)     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r0)     // Catch:{ IllegalStateException -> 0x00a7 }
            goto L_0x004f
        L_0x00eb:
            r3 = move-exception
        L_0x00ec:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0121 }
            r4.<init>()     // Catch:{ all -> 0x0121 }
            java.lang.String r7 = "File: "
            r4.append(r7)     // Catch:{ all -> 0x0121 }
            r4.append(r11)     // Catch:{ all -> 0x0121 }
            java.lang.String r7 = " could not be found."
            r4.append(r7)     // Catch:{ all -> 0x0121 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0121 }
            android.util.Log.w(r2, r4, r3)     // Catch:{ all -> 0x0121 }
            if (r5 == 0) goto L_0x011f
            r5.close()     // Catch:{ IOException -> 0x010b }
            goto L_0x011f
        L_0x010b:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.<init>()     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r1)     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r11)     // Catch:{ IllegalStateException -> 0x00a7 }
            r4.append(r0)     // Catch:{ IllegalStateException -> 0x00a7 }
            goto L_0x004f
        L_0x011c:
            android.util.Log.w(r2, r11, r3)     // Catch:{ IllegalStateException -> 0x00a7 }
        L_0x011f:
            r4 = r6
            goto L_0x015a
        L_0x0121:
            r3 = move-exception
            r7 = r5
            r4 = r6
        L_0x0124:
            if (r7 == 0) goto L_0x0144
            r7.close()     // Catch:{ IOException -> 0x012a }
            goto L_0x0144
        L_0x012a:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x00a7 }
            r5.<init>()     // Catch:{ IllegalStateException -> 0x00a7 }
            r5.append(r1)     // Catch:{ IllegalStateException -> 0x00a7 }
            r5.append(r11)     // Catch:{ IllegalStateException -> 0x00a7 }
            r5.append(r0)     // Catch:{ IllegalStateException -> 0x00a7 }
            java.lang.String r11 = r5.toString()     // Catch:{ IllegalStateException -> 0x00a7 }
            android.util.Log.w(r2, r11, r4)     // Catch:{ IllegalStateException -> 0x00a7 }
            r4 = r6
            goto L_0x0144
        L_0x0142:
            r4 = r6
            goto L_0x0148
        L_0x0144:
            throw r3     // Catch:{ IllegalStateException -> 0x003d }
        L_0x0145:
            r4 = 406(0x196, float:5.69E-43)
            goto L_0x015a
        L_0x0148:
            java.lang.String r0 = "Could not close convertsession. Convertsession: "
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            int r10 = r10.f1448LD
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            android.util.Log.w(r2, r10, r11)
        L_0x015a:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.mmslib.p040b.C0999d.close(java.lang.String):int");
    }

    /* renamed from: e */
    public byte[] mo6763e(byte[] bArr, int i) {
        DrmConvertedStatus drmConvertedStatus;
        byte[] bArr2;
        if (bArr != null) {
            try {
                if (i != bArr.length) {
                    byte[] bArr3 = new byte[i];
                    System.arraycopy(bArr, 0, bArr3, 0, i);
                    drmConvertedStatus = this.f1447KD.convertData(this.f1448LD, bArr3);
                } else {
                    drmConvertedStatus = this.f1447KD.convertData(this.f1448LD, bArr);
                }
                if (!(drmConvertedStatus == null || drmConvertedStatus.statusCode != 1 || (bArr2 = drmConvertedStatus.convertedData) == null)) {
                    return bArr2;
                }
            } catch (IllegalArgumentException e) {
                StringBuilder Pa = C0632a.m1011Pa("Buffer with data to convert is illegal. Convertsession: ");
                Pa.append(this.f1448LD);
                Log.w("DrmConvertSession", Pa.toString(), e);
            } catch (IllegalStateException e2) {
                StringBuilder Pa2 = C0632a.m1011Pa("Could not convert data. Convertsession: ");
                Pa2.append(this.f1448LD);
                Log.w("DrmConvertSession", Pa2.toString(), e2);
            }
            return null;
        }
        throw new IllegalArgumentException("Parameter inBuffer is null");
    }
}
