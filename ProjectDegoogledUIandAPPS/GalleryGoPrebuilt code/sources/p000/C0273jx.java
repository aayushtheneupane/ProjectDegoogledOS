package p000;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: jx */
/* compiled from: PG */
public final class C0273jx {

    /* renamed from: a */
    public static final C0297ku f15104a = new C0297ku(16);

    /* renamed from: b */
    public static final Object f15105b = new Object();

    /* renamed from: c */
    public static final C0309lf f15106c = new C0309lf();

    /* renamed from: d */
    private static final C0280kd f15107d = new C0280kd("fonts");

    /* renamed from: e */
    private static final Comparator f15108e = new C0269jt();

    /* JADX WARNING: Removed duplicated region for block: B:68:0x0199 A[Catch:{ NameNotFoundException -> 0x0213 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static p000.C0272jw m14499a(android.content.Context r25, p000.C0265jp r26, int r27) {
        /*
            r0 = r26
            java.lang.String r1 = "result_code"
            java.lang.String r2 = "font_italic"
            java.lang.String r3 = "font_weight"
            java.lang.String r4 = "font_ttc_index"
            java.lang.String r5 = "file_id"
            java.lang.String r6 = "_id"
            java.lang.String r7 = "content"
            android.content.pm.PackageManager r10 = r25.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0213 }
            r25.getResources()     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.String r11 = r0.f15084a     // Catch:{ NameNotFoundException -> 0x0213 }
            r12 = 0
            android.content.pm.ProviderInfo r13 = r10.resolveContentProvider(r11, r12)     // Catch:{ NameNotFoundException -> 0x0213 }
            if (r13 == 0) goto L_0x01fc
            java.lang.String r14 = r13.packageName     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.String r15 = r0.f15085b     // Catch:{ NameNotFoundException -> 0x0213 }
            boolean r14 = r14.equals(r15)     // Catch:{ NameNotFoundException -> 0x0213 }
            if (r14 == 0) goto L_0x01db
            java.lang.String r11 = r13.packageName     // Catch:{ NameNotFoundException -> 0x0213 }
            r14 = 64
            android.content.pm.PackageInfo r10 = r10.getPackageInfo(r11, r14)     // Catch:{ NameNotFoundException -> 0x0213 }
            android.content.pm.Signature[] r10 = r10.signatures     // Catch:{ NameNotFoundException -> 0x0213 }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ NameNotFoundException -> 0x0213 }
            r11.<init>()     // Catch:{ NameNotFoundException -> 0x0213 }
            r14 = 0
        L_0x003a:
            int r15 = r10.length     // Catch:{ NameNotFoundException -> 0x0213 }
            if (r14 < r15) goto L_0x01c8
            java.util.Comparator r10 = f15108e     // Catch:{ NameNotFoundException -> 0x0213 }
            java.util.Collections.sort(r11, r10)     // Catch:{ NameNotFoundException -> 0x0213 }
            java.util.List r10 = r0.f15087d     // Catch:{ NameNotFoundException -> 0x0213 }
            r14 = 0
        L_0x0045:
            int r15 = r10.size()     // Catch:{ NameNotFoundException -> 0x0213 }
            if (r14 >= r15) goto L_0x008c
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.Object r16 = r10.get(r14)     // Catch:{ NameNotFoundException -> 0x0213 }
            r8 = r16
            java.util.Collection r8 = (java.util.Collection) r8     // Catch:{ NameNotFoundException -> 0x0213 }
            r15.<init>(r8)     // Catch:{ NameNotFoundException -> 0x0213 }
            java.util.Comparator r8 = f15108e     // Catch:{ NameNotFoundException -> 0x0213 }
            java.util.Collections.sort(r15, r8)     // Catch:{ NameNotFoundException -> 0x0213 }
            int r8 = r11.size()     // Catch:{ NameNotFoundException -> 0x0213 }
            int r9 = r15.size()     // Catch:{ NameNotFoundException -> 0x0213 }
            if (r8 == r9) goto L_0x0068
            goto L_0x0087
        L_0x0068:
            r8 = 0
        L_0x0069:
            int r9 = r11.size()     // Catch:{ NameNotFoundException -> 0x0213 }
            if (r8 >= r9) goto L_0x008b
            java.lang.Object r9 = r11.get(r8)     // Catch:{ NameNotFoundException -> 0x0213 }
            byte[] r9 = (byte[]) r9     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.Object r17 = r15.get(r8)     // Catch:{ NameNotFoundException -> 0x0213 }
            r12 = r17
            byte[] r12 = (byte[]) r12     // Catch:{ NameNotFoundException -> 0x0213 }
            boolean r9 = java.util.Arrays.equals(r9, r12)     // Catch:{ NameNotFoundException -> 0x0213 }
            if (r9 == 0) goto L_0x0087
            int r8 = r8 + 1
            r12 = 0
            goto L_0x0069
        L_0x0087:
            int r14 = r14 + 1
            r12 = 0
            goto L_0x0045
        L_0x008b:
            goto L_0x008e
        L_0x008c:
            r13 = 0
        L_0x008e:
            r8 = 1
            if (r13 == 0) goto L_0x019d
            java.lang.String r9 = r13.authority     // Catch:{ NameNotFoundException -> 0x0213 }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ NameNotFoundException -> 0x0213 }
            r10.<init>()     // Catch:{ NameNotFoundException -> 0x0213 }
            android.net.Uri$Builder r11 = new android.net.Uri$Builder     // Catch:{ NameNotFoundException -> 0x0213 }
            r11.<init>()     // Catch:{ NameNotFoundException -> 0x0213 }
            android.net.Uri$Builder r11 = r11.scheme(r7)     // Catch:{ NameNotFoundException -> 0x0213 }
            android.net.Uri$Builder r11 = r11.authority(r9)     // Catch:{ NameNotFoundException -> 0x0213 }
            android.net.Uri r11 = r11.build()     // Catch:{ NameNotFoundException -> 0x0213 }
            android.net.Uri$Builder r12 = new android.net.Uri$Builder     // Catch:{ NameNotFoundException -> 0x0213 }
            r12.<init>()     // Catch:{ NameNotFoundException -> 0x0213 }
            android.net.Uri$Builder r7 = r12.scheme(r7)     // Catch:{ NameNotFoundException -> 0x0213 }
            android.net.Uri$Builder r7 = r7.authority(r9)     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.String r9 = "file"
            android.net.Uri$Builder r7 = r7.appendPath(r9)     // Catch:{ NameNotFoundException -> 0x0213 }
            android.net.Uri r7 = r7.build()     // Catch:{ NameNotFoundException -> 0x0213 }
            int r9 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0195 }
            android.content.ContentResolver r18 = r25.getContentResolver()     // Catch:{ all -> 0x0195 }
            r9 = 7
            java.lang.String[] r9 = new java.lang.String[r9]     // Catch:{ all -> 0x0195 }
            r12 = 0
            r9[r12] = r6     // Catch:{ all -> 0x0195 }
            r9[r8] = r5     // Catch:{ all -> 0x0195 }
            r12 = 2
            r9[r12] = r4     // Catch:{ all -> 0x0195 }
            r12 = 3
            java.lang.String r13 = "font_variation_settings"
            r9[r12] = r13     // Catch:{ all -> 0x0195 }
            r12 = 4
            r9[r12] = r3     // Catch:{ all -> 0x0195 }
            r12 = 5
            r9[r12] = r2     // Catch:{ all -> 0x0195 }
            r12 = 6
            r9[r12] = r1     // Catch:{ all -> 0x0195 }
            java.lang.String r21 = "query = ?"
            java.lang.String[] r12 = new java.lang.String[r8]     // Catch:{ all -> 0x0195 }
            java.lang.String r0 = r0.f15086c     // Catch:{ all -> 0x0195 }
            r13 = 0
            r12[r13] = r0     // Catch:{ all -> 0x0195 }
            r23 = 0
            r24 = 0
            r19 = r11
            r20 = r9
            r22 = r12
            android.database.Cursor r9 = r18.query(r19, r20, r21, r22, r23, r24)     // Catch:{ all -> 0x0195 }
            if (r9 == 0) goto L_0x0121
            int r0 = r9.getCount()     // Catch:{ all -> 0x017c }
            if (r0 <= 0) goto L_0x0121
            int r0 = r9.getColumnIndex(r1)     // Catch:{ all -> 0x017c }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x017c }
            r10.<init>()     // Catch:{ all -> 0x017c }
            int r1 = r9.getColumnIndex(r6)     // Catch:{ all -> 0x017c }
            int r5 = r9.getColumnIndex(r5)     // Catch:{ all -> 0x017c }
            int r4 = r9.getColumnIndex(r4)     // Catch:{ all -> 0x017c }
            int r3 = r9.getColumnIndex(r3)     // Catch:{ all -> 0x017c }
            int r2 = r9.getColumnIndex(r2)     // Catch:{ all -> 0x017c }
        L_0x011b:
            boolean r6 = r9.moveToNext()     // Catch:{ all -> 0x017c }
            if (r6 != 0) goto L_0x0122
        L_0x0121:
            goto L_0x017f
        L_0x0122:
            r6 = -1
            if (r0 == r6) goto L_0x012c
            int r6 = r9.getInt(r0)     // Catch:{ all -> 0x017c }
            r23 = r6
            goto L_0x012f
        L_0x012c:
            r23 = 0
        L_0x012f:
            r6 = -1
            if (r4 == r6) goto L_0x0139
            int r6 = r9.getInt(r4)     // Catch:{ all -> 0x017c }
            r20 = r6
            goto L_0x013c
        L_0x0139:
            r20 = 0
        L_0x013c:
            r6 = -1
            if (r5 == r6) goto L_0x014a
            long r12 = r9.getLong(r5)     // Catch:{ all -> 0x017c }
            android.net.Uri r6 = android.content.ContentUris.withAppendedId(r7, r12)     // Catch:{ all -> 0x017c }
            r19 = r6
            goto L_0x0154
        L_0x014a:
            long r12 = r9.getLong(r1)     // Catch:{ all -> 0x017c }
            android.net.Uri r6 = android.content.ContentUris.withAppendedId(r11, r12)     // Catch:{ all -> 0x017c }
            r19 = r6
        L_0x0154:
            r6 = -1
            if (r3 == r6) goto L_0x015e
            int r6 = r9.getInt(r3)     // Catch:{ all -> 0x017c }
            r21 = r6
            goto L_0x0162
        L_0x015e:
            r6 = 400(0x190, float:5.6E-43)
            r21 = 400(0x190, float:5.6E-43)
        L_0x0162:
            r6 = -1
            if (r2 == r6) goto L_0x016f
            int r6 = r9.getInt(r2)     // Catch:{ all -> 0x017c }
            if (r6 != r8) goto L_0x016e
            r22 = 1
            goto L_0x0171
        L_0x016e:
        L_0x016f:
            r22 = 0
        L_0x0171:
            jv r6 = new jv     // Catch:{ all -> 0x017c }
            r18 = r6
            r18.<init>(r19, r20, r21, r22, r23)     // Catch:{ all -> 0x017c }
            r10.add(r6)     // Catch:{ all -> 0x017c }
            goto L_0x011b
        L_0x017c:
            r0 = move-exception
            goto L_0x0197
        L_0x017f:
            if (r9 != 0) goto L_0x0182
            goto L_0x0185
        L_0x0182:
            r9.close()     // Catch:{ NameNotFoundException -> 0x0213 }
        L_0x0185:
            r0 = 0
            jv[] r1 = new p000.C0271jv[r0]     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.Object[] r0 = r10.toArray(r1)     // Catch:{ NameNotFoundException -> 0x0213 }
            jv[] r0 = (p000.C0271jv[]) r0     // Catch:{ NameNotFoundException -> 0x0213 }
            ju r1 = new ju     // Catch:{ NameNotFoundException -> 0x0213 }
            r9 = 0
            r1.<init>(r9, r0)     // Catch:{ NameNotFoundException -> 0x0213 }
            goto L_0x01a4
        L_0x0195:
            r0 = move-exception
            r9 = 0
        L_0x0197:
            if (r9 == 0) goto L_0x019c
            r9.close()     // Catch:{ NameNotFoundException -> 0x0213 }
        L_0x019c:
            throw r0     // Catch:{ NameNotFoundException -> 0x0213 }
        L_0x019d:
            r9 = 0
            ju r1 = new ju     // Catch:{ NameNotFoundException -> 0x0213 }
            r2 = 0
            r1.<init>(r8, r2)     // Catch:{ NameNotFoundException -> 0x0213 }
        L_0x01a4:
            int r0 = r1.f15095a
            if (r0 != 0) goto L_0x01c0
            jv[] r0 = r1.f15096b
            ja r1 = p000.C0241is.f14960a
            r8 = r25
            r12 = r27
            android.graphics.Typeface r0 = r1.mo9110a((android.content.Context) r8, (p000.C0271jv[]) r0, (int) r12)
            if (r0 == 0) goto L_0x01b8
            r12 = 0
            goto L_0x01ba
        L_0x01b8:
            r12 = -3
        L_0x01ba:
            jw r1 = new jw
            r1.<init>(r0, r12)
            return r1
        L_0x01c0:
            jw r0 = new jw
            r1 = -2
            r2 = 0
            r0.<init>(r2, r1)
            return r0
        L_0x01c8:
            r8 = r25
            r12 = r27
            r9 = 0
            r15 = r10[r14]     // Catch:{ NameNotFoundException -> 0x0213 }
            byte[] r15 = r15.toByteArray()     // Catch:{ NameNotFoundException -> 0x0213 }
            r11.add(r15)     // Catch:{ NameNotFoundException -> 0x0213 }
            int r14 = r14 + 1
            r12 = 0
            goto L_0x003a
        L_0x01db:
            android.content.pm.PackageManager$NameNotFoundException r1 = new android.content.pm.PackageManager$NameNotFoundException     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x0213 }
            r2.<init>()     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.String r3 = "Found content provider "
            r2.append(r3)     // Catch:{ NameNotFoundException -> 0x0213 }
            r2.append(r11)     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.String r3 = ", but package was not "
            r2.append(r3)     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.String r0 = r0.f15085b     // Catch:{ NameNotFoundException -> 0x0213 }
            r2.append(r0)     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.String r0 = r2.toString()     // Catch:{ NameNotFoundException -> 0x0213 }
            r1.<init>(r0)     // Catch:{ NameNotFoundException -> 0x0213 }
            throw r1     // Catch:{ NameNotFoundException -> 0x0213 }
        L_0x01fc:
            android.content.pm.PackageManager$NameNotFoundException r0 = new android.content.pm.PackageManager$NameNotFoundException     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x0213 }
            r1.<init>()     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.String r2 = "No package found for authority: "
            r1.append(r2)     // Catch:{ NameNotFoundException -> 0x0213 }
            r1.append(r11)     // Catch:{ NameNotFoundException -> 0x0213 }
            java.lang.String r1 = r1.toString()     // Catch:{ NameNotFoundException -> 0x0213 }
            r0.<init>(r1)     // Catch:{ NameNotFoundException -> 0x0213 }
            throw r0     // Catch:{ NameNotFoundException -> 0x0213 }
        L_0x0213:
            r0 = move-exception
            jw r0 = new jw
            r1 = 0
            r2 = -1
            r0.<init>(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0273jx.m14499a(android.content.Context, jp, int):jw");
    }

    /* renamed from: a */
    public static Typeface m14497a(Context context, C0265jp jpVar, C0237io ioVar, boolean z, int i, int i2) {
        ReentrantLock reentrantLock;
        Object obj;
        String str = jpVar.f15088e + "-" + i2;
        Typeface typeface = (Typeface) f15104a.mo9237a((Object) str);
        if (typeface != null) {
            ioVar.mo6614a(typeface);
            return typeface;
        } else if (z && i == -1) {
            C0272jw a = m14499a(context, jpVar, i2);
            int i3 = a.f15103b;
            if (i3 == 0) {
                ioVar.mo9036b(a.f15102a);
            } else {
                ioVar.mo9035a(i3);
            }
            return a.f15102a;
        } else {
            C0266jq jqVar = new C0266jq(context, jpVar, i2, str);
            if (!z) {
                C0267jr jrVar = new C0267jr(ioVar);
                synchronized (f15105b) {
                    ArrayList arrayList = (ArrayList) f15106c.get(str);
                    if (arrayList == null) {
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(jrVar);
                        f15106c.put(str, arrayList2);
                        f15107d.mo9182a(new C0277ka(jqVar, new Handler(), new C0268js(str)));
                        return null;
                    }
                    arrayList.add(jrVar);
                    return null;
                }
            }
            try {
                C0280kd kdVar = f15107d;
                reentrantLock = new ReentrantLock();
                Condition newCondition = reentrantLock.newCondition();
                AtomicReference atomicReference = new AtomicReference();
                AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                kdVar.mo9182a(new C0278kb(atomicReference, jqVar, reentrantLock, atomicBoolean, newCondition));
                reentrantLock.lock();
                if (atomicBoolean.get()) {
                    long nanos = TimeUnit.MILLISECONDS.toNanos((long) i);
                    while (true) {
                        try {
                            nanos = newCondition.awaitNanos(nanos);
                        } catch (InterruptedException e) {
                        }
                        if (!atomicBoolean.get()) {
                            obj = atomicReference.get();
                            break;
                        } else if (nanos <= 0) {
                            throw new InterruptedException("timeout");
                        }
                    }
                } else {
                    obj = atomicReference.get();
                }
                reentrantLock.unlock();
                return ((C0272jw) obj).f15102a;
            } catch (InterruptedException e2) {
                return null;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0048, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        p000.ifn.m12935a(r7, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0051, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0054, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005d, code lost:
        throw r7;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map m14498a(android.content.Context r14, p000.C0271jv[] r15) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            int r1 = r15.length
            r2 = 0
        L_0x0007:
            if (r2 >= r1) goto L_0x0068
            r3 = r15[r2]
            int r4 = r3.f15101e
            if (r4 == 0) goto L_0x0010
            goto L_0x0065
        L_0x0010:
            android.net.Uri r3 = r3.f15097a
            boolean r4 = r0.containsKey(r3)
            if (r4 != 0) goto L_0x0065
            android.content.ContentResolver r4 = r14.getContentResolver()
            r5 = 0
            java.lang.String r6 = "r"
            android.os.ParcelFileDescriptor r4 = r4.openFileDescriptor(r3, r6, r5)     // Catch:{ IOException -> 0x0060 }
            if (r4 == 0) goto L_0x005e
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ all -> 0x0052 }
            java.io.FileDescriptor r7 = r4.getFileDescriptor()     // Catch:{ all -> 0x0052 }
            r6.<init>(r7)     // Catch:{ all -> 0x0052 }
            java.nio.channels.FileChannel r8 = r6.getChannel()     // Catch:{ all -> 0x0046 }
            long r12 = r8.size()     // Catch:{ all -> 0x0046 }
            java.nio.channels.FileChannel$MapMode r9 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ all -> 0x0046 }
            r10 = 0
            java.nio.MappedByteBuffer r7 = r8.map(r9, r10, r12)     // Catch:{ all -> 0x0046 }
            r6.close()     // Catch:{ all -> 0x0052 }
            r4.close()     // Catch:{ IOException -> 0x0060 }
            r5 = r7
            goto L_0x0062
        L_0x0046:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r8 = move-exception
            r6.close()     // Catch:{ all -> 0x004d }
            goto L_0x0051
        L_0x004d:
            r6 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r7, (java.lang.Throwable) r6)     // Catch:{ all -> 0x0052 }
        L_0x0051:
            throw r8     // Catch:{ all -> 0x0052 }
        L_0x0052:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r7 = move-exception
            r4.close()     // Catch:{ all -> 0x0059 }
            goto L_0x005d
        L_0x0059:
            r4 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r6, (java.lang.Throwable) r4)     // Catch:{ IOException -> 0x0060 }
        L_0x005d:
            throw r7     // Catch:{ IOException -> 0x0060 }
        L_0x005e:
            goto L_0x0062
        L_0x0060:
            r4 = move-exception
        L_0x0062:
            r0.put(r3, r5)
        L_0x0065:
            int r2 = r2 + 1
            goto L_0x0007
        L_0x0068:
            java.util.Map r14 = java.util.Collections.unmodifiableMap(r0)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0273jx.m14498a(android.content.Context, jv[]):java.util.Map");
    }
}
