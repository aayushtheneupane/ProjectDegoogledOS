package com.android.messaging.sms;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.mms.MmsManager;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.MmsFileProvider;
import com.android.messaging.mmslib.p039a.C0971a;
import com.android.messaging.mmslib.p039a.C0975e;
import com.android.messaging.mmslib.p039a.C0979i;
import com.android.messaging.mmslib.p039a.C0995y;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.receiver.SendStatusReceiver;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1474sa;
import com.android.vcard.VCardConfig;

/* renamed from: com.android.messaging.sms.u */
public class C1025u {
    /* renamed from: Cm */
    private static String m2390Cm() {
        return C0967f.get().getApplicationContext().getPackageName() + ":smsstoragelow";
    }

    /* renamed from: Di */
    public static void m2391Di() {
        NotificationManagerCompat.from(C0967f.get().getApplicationContext()).cancel(m2390Cm(), 3);
    }

    /* renamed from: Ei */
    public static void m2392Ei() {
        if (C1474sa.getDefault().mo8230lk()) {
            Context applicationContext = C0967f.get().getApplicationContext();
            Resources resources = applicationContext.getResources();
            PendingIntent s = C1040Ea.get().mo6982s(applicationContext);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(applicationContext, (String) null);
            builder.setContentTitle(resources.getString(R.string.sms_storage_low_title)).setTicker(resources.getString(R.string.sms_storage_low_notification_ticker)).setSmallIcon(R.drawable.ic_failed_light).setPriority(0).setOngoing(true).setAutoCancel(false).setContentIntent(s);
            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle(builder);
            bigTextStyle.bigText(resources.getString(R.string.sms_storage_low_text));
            NotificationManagerCompat.from(C0967f.get().getApplicationContext()).notify(m2390Cm(), 3, bigTextStyle.build());
        }
    }

    /* renamed from: a */
    public static void m2393a(Context context, int i, Uri uri, C0995y yVar, Bundle bundle) {
        m2394a(context, i, uri, (String) null, yVar, true, bundle);
    }

    /* renamed from: w */
    public static int m2398w(int i, int i2) {
        C1424b.m3591ha(i == -1);
        switch (i) {
            case 1:
            case 2:
            case 7:
            case 8:
                return 2;
            case 3:
            case 5:
                break;
            case 4:
                if (i2 == 404) {
                    return 3;
                }
                break;
            default:
                return 2;
        }
        return 1;
    }

    /* renamed from: a */
    public static void m2397a(Context context, int i, byte[] bArr, String str, int i2) {
        C0979i iVar = new C0979i(18, bArr, i2);
        Uri parse = Uri.parse(str);
        if (!C1024t.get(i).mo6847si()) {
            str = null;
        }
        m2394a(context, i, parse, str, iVar, false, (Bundle) null);
    }

    /* renamed from: a */
    public static void m2396a(Context context, int i, byte[] bArr, String str) {
        String la = C1474sa.get(i).mo8229la(true);
        C0971a aVar = new C0971a(18, bArr);
        aVar.mo6670b(new C0975e(106, la));
        Uri parse = Uri.parse(str);
        if (!C1024t.get(i).mo6847si()) {
            str = null;
        }
        m2394a(context, i, parse, str, aVar, false, (Bundle) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0075 A[SYNTHETIC, Splitter:B:31:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0087 A[Catch:{ IOException -> 0x0084, OutOfMemoryError -> 0x0072, all -> 0x006f }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ad A[SYNTHETIC, Splitter:B:41:0x00ad] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void m2394a(android.content.Context r8, int r9, android.net.Uri r10, java.lang.String r11, com.android.messaging.mmslib.p039a.C0976f r12, boolean r13, android.os.Bundle r14) {
        /*
            java.lang.String r0 = "MessagingApp"
            android.net.Uri r1 = com.android.messaging.datamodel.MmsFileProvider.m1276Ua()
            java.io.File r2 = com.android.messaging.datamodel.MmsFileProvider.m1278e(r1)
            r3 = 10000(0x2710, float:1.4013E-41)
            r4 = 0
            java.io.File r5 = r2.getParentFile()     // Catch:{ IOException -> 0x0084, OutOfMemoryError -> 0x0072 }
            r5.mkdirs()     // Catch:{ IOException -> 0x0084, OutOfMemoryError -> 0x0072 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0084, OutOfMemoryError -> 0x0072 }
            r5.<init>(r2)     // Catch:{ IOException -> 0x0084, OutOfMemoryError -> 0x0072 }
            com.android.messaging.mmslib.a.o r4 = new com.android.messaging.mmslib.a.o     // Catch:{ IOException -> 0x006c, OutOfMemoryError -> 0x0069, all -> 0x0067 }
            r4.<init>(r8, r12)     // Catch:{ IOException -> 0x006c, OutOfMemoryError -> 0x0069, all -> 0x0067 }
            byte[] r12 = r4.mo6702di()     // Catch:{ IOException -> 0x006c, OutOfMemoryError -> 0x0069, all -> 0x0067 }
            r4 = 3
            if (r12 == 0) goto L_0x005f
            int r6 = r12.length     // Catch:{ IOException -> 0x006c, OutOfMemoryError -> 0x0069, all -> 0x0067 }
            com.android.messaging.sms.t r7 = com.android.messaging.sms.C1024t.get(r9)     // Catch:{ IOException -> 0x006c, OutOfMemoryError -> 0x0069, all -> 0x0067 }
            int r7 = r7.getMaxMessageSize()     // Catch:{ IOException -> 0x006c, OutOfMemoryError -> 0x0069, all -> 0x0067 }
            if (r6 > r7) goto L_0x0059
            r5.write(r12)     // Catch:{ IOException -> 0x006c, OutOfMemoryError -> 0x0069, all -> 0x0067 }
            r5.close()     // Catch:{ IOException -> 0x0036 }
        L_0x0036:
            android.content.Intent r12 = new android.content.Intent
            java.lang.Class<com.android.messaging.receiver.SendStatusReceiver> r0 = com.android.messaging.receiver.SendStatusReceiver.class
            java.lang.String r2 = "com.android.messaging.receiver.SendStatusReceiver.MMS_SENT"
            r12.<init>(r2, r10, r8, r0)
            java.lang.String r10 = "content_uri"
            r12.putExtra(r10, r1)
            java.lang.String r10 = "response_important"
            r12.putExtra(r10, r13)
            if (r14 == 0) goto L_0x004e
            r12.putExtras(r14)
        L_0x004e:
            r10 = 0
            r13 = 134217728(0x8000000, float:3.85186E-34)
            android.app.PendingIntent r10 = android.app.PendingIntent.getBroadcast(r8, r10, r12, r13)
            androidx.appcompat.mms.MmsManager.sendMultimediaMessage(r9, r8, r1, r11, r10)
            return
        L_0x0059:
            com.android.messaging.sms.MmsFailureException r8 = new com.android.messaging.sms.MmsFailureException     // Catch:{ IOException -> 0x006c, OutOfMemoryError -> 0x0069, all -> 0x0067 }
            r8.<init>((int) r4, (int) r3)     // Catch:{ IOException -> 0x006c, OutOfMemoryError -> 0x0069, all -> 0x0067 }
            throw r8     // Catch:{ IOException -> 0x006c, OutOfMemoryError -> 0x0069, all -> 0x0067 }
        L_0x005f:
            com.android.messaging.sms.MmsFailureException r8 = new com.android.messaging.sms.MmsFailureException     // Catch:{ IOException -> 0x006c, OutOfMemoryError -> 0x0069, all -> 0x0067 }
            java.lang.String r9 = "Failed to compose PDU"
            r8.<init>((int) r4, (java.lang.String) r9)     // Catch:{ IOException -> 0x006c, OutOfMemoryError -> 0x0069, all -> 0x0067 }
            throw r8     // Catch:{ IOException -> 0x006c, OutOfMemoryError -> 0x0069, all -> 0x0067 }
        L_0x0067:
            r8 = move-exception
            goto L_0x00ab
        L_0x0069:
            r8 = move-exception
            r4 = r5
            goto L_0x0073
        L_0x006c:
            r8 = move-exception
            r4 = r5
            goto L_0x0085
        L_0x006f:
            r8 = move-exception
            r5 = r4
            goto L_0x00ab
        L_0x0072:
            r8 = move-exception
        L_0x0073:
            if (r2 == 0) goto L_0x0078
            r2.delete()     // Catch:{ all -> 0x006f }
        L_0x0078:
            java.lang.String r9 = "Out of memory in composing PDU"
            com.android.messaging.util.C1430e.m3623e(r0, r9, r8)     // Catch:{ all -> 0x006f }
            com.android.messaging.sms.MmsFailureException r8 = new com.android.messaging.sms.MmsFailureException     // Catch:{ all -> 0x006f }
            r9 = 2
            r8.<init>((int) r9, (int) r3)     // Catch:{ all -> 0x006f }
            throw r8     // Catch:{ all -> 0x006f }
        L_0x0084:
            r8 = move-exception
        L_0x0085:
            if (r2 == 0) goto L_0x008a
            r2.delete()     // Catch:{ all -> 0x006f }
        L_0x008a:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            r9.<init>()     // Catch:{ all -> 0x006f }
            java.lang.String r10 = "Cannot create temporary file "
            r9.append(r10)     // Catch:{ all -> 0x006f }
            java.lang.String r10 = r2.getAbsolutePath()     // Catch:{ all -> 0x006f }
            r9.append(r10)     // Catch:{ all -> 0x006f }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x006f }
            com.android.messaging.util.C1430e.m3623e(r0, r9, r8)     // Catch:{ all -> 0x006f }
            com.android.messaging.sms.MmsFailureException r8 = new com.android.messaging.sms.MmsFailureException     // Catch:{ all -> 0x006f }
            r9 = 1
            java.lang.String r10 = "Cannot create raw mms file"
            r8.<init>((int) r9, (java.lang.String) r10)     // Catch:{ all -> 0x006f }
            throw r8     // Catch:{ all -> 0x006f }
        L_0x00ab:
            if (r5 == 0) goto L_0x00b0
            r5.close()     // Catch:{ IOException -> 0x00b0 }
        L_0x00b0:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.sms.C1025u.m2394a(android.content.Context, int, android.net.Uri, java.lang.String, com.android.messaging.mmslib.a.f, boolean, android.os.Bundle):void");
    }

    /* renamed from: a */
    public static void m2395a(Context context, int i, String str, Bundle bundle) {
        Uri parse = Uri.parse(str);
        Uri Ua = MmsFileProvider.m1276Ua();
        Intent intent = new Intent("com.android.messaging.receiver.SendStatusReceiver.MMS_DOWNLOADED", parse, context, SendStatusReceiver.class);
        intent.putExtra("content_uri", Ua);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        MmsManager.downloadMultimediaMessage(i, context, str, Ua, PendingIntent.getBroadcast(context, 0, intent, VCardConfig.FLAG_CONVERT_PHONETIC_NAME_STRINGS));
    }
}
