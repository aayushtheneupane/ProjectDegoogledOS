package com.android.messaging.util;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.p016v4.media.session.C0107q;
import android.text.TextUtils;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.MediaScratchFileProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.za */
public class C1488za {

    /* renamed from: cL */
    public static final HashSet f2359cL = new HashSet(Arrays.asList(new String[]{"sms", DefaultApnSettingsLoader.APN_TYPE_MMS, "smsto", "smsto"}));

    /* renamed from: dL */
    public static final HashSet f2360dL = new HashSet(Arrays.asList(new String[]{"android.resource", "content", "file", "bugle"}));

    /* renamed from: A */
    public static boolean m3861A(Uri uri) {
        C1424b.m3594t(uri);
        return f2360dL.contains(uri.getScheme());
    }

    /* renamed from: B */
    public static boolean m3862B(Uri uri) {
        String authority = uri.getAuthority();
        return TextUtils.equals("content", uri.getScheme()) && (TextUtils.equals("media", authority) || TextUtils.equals("com.android.providers.media.documents", authority));
    }

    /* renamed from: C */
    public static String[] m3863C(Uri uri) {
        if (!(uri != null && f2359cL.contains(uri.getScheme()))) {
            return null;
        }
        String[] split = uri.getSchemeSpecificPart().split("\\?");
        if (TextUtils.isEmpty(split[0])) {
            return null;
        }
        return C0107q.replaceUnicodeDigits(split[0]).replace(';', ',').split(",");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0056 A[SYNTHETIC, Splitter:B:26:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0063 A[SYNTHETIC, Splitter:B:34:0x0063] */
    /* renamed from: D */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.net.Uri m3864D(android.net.Uri r6) {
        /*
            java.lang.String r0 = "error trying to close the inputStream"
            java.lang.String r1 = "MessagingApp"
            com.android.messaging.f r2 = com.android.messaging.C0967f.get()
            android.content.Context r2 = r2.getApplicationContext()
            r3 = 0
            boolean r4 = m3877z(r6)     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            if (r4 == 0) goto L_0x001c
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.io.InputStream r6 = r2.openInputStream(r6)     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            goto L_0x0033
        L_0x001c:
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            r2.<init>(r6)     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.net.URLConnection r6 = r2.openConnection()     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.io.InputStream r6 = r6.getInputStream()     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            r2.<init>(r6)     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            r6 = r2
        L_0x0033:
            android.net.Uri r2 = m3872c(r6)     // Catch:{ Exception -> 0x0046, all -> 0x0042 }
            if (r6 == 0) goto L_0x0041
            r6.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0041
        L_0x003d:
            r6 = move-exception
            com.android.messaging.util.C1430e.m3623e(r1, r0, r6)
        L_0x0041:
            return r2
        L_0x0042:
            r2 = move-exception
            r3 = r6
            r6 = r2
            goto L_0x0061
        L_0x0046:
            r2 = move-exception
            r5 = r2
            r2 = r6
            r6 = r5
            goto L_0x004f
        L_0x004b:
            r6 = move-exception
            goto L_0x0061
        L_0x004d:
            r6 = move-exception
            r2 = r3
        L_0x004f:
            java.lang.String r4 = "Error while retrieving media "
            com.android.messaging.util.C1430e.m3623e(r1, r4, r6)     // Catch:{ all -> 0x005f }
            if (r2 == 0) goto L_0x005e
            r2.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r6 = move-exception
            com.android.messaging.util.C1430e.m3623e(r1, r0, r6)
        L_0x005e:
            return r3
        L_0x005f:
            r6 = move-exception
            r3 = r2
        L_0x0061:
            if (r3 == 0) goto L_0x006b
            r3.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x006b
        L_0x0067:
            r2 = move-exception
            com.android.messaging.util.C1430e.m3623e(r1, r0, r2)
        L_0x006b:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.C1488za.m3864D(android.net.Uri):android.net.Uri");
    }

    /* renamed from: E */
    public static String m3865E(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }

    /* renamed from: F */
    public static Uri m3866F(long j) {
        return MediaStore.Files.getContentUri("external", j);
    }

    /* renamed from: Na */
    public static Uri m3867Na(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Uri.fromFile(new File(str));
    }

    /* renamed from: Oa */
    public static Uri m3868Oa(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Uri.parse(str);
    }

    /* renamed from: a */
    public static Uri m3871a(InputStream inputStream, File file, String str) {
        if (file.exists() || file.mkdirs()) {
            try {
                return m3869a(C0967f.get().getApplicationContext(), inputStream, Uri.fromFile(C1430e.m3611a(file, str)));
            } catch (IOException unused) {
                StringBuilder Pa = C0632a.m1011Pa("Error creating file in ");
                Pa.append(file.getAbsolutePath());
                C1430e.m3622e("MessagingApp", Pa.toString());
                return null;
            }
        } else {
            StringBuilder Pa2 = C0632a.m1011Pa("Error creating ");
            Pa2.append(file.getAbsolutePath());
            C1430e.m3622e("MessagingApp", Pa2.toString());
            return null;
        }
    }

    /* renamed from: c */
    public static Uri m3872c(InputStream inputStream) {
        return m3869a(C0967f.get().getApplicationContext(), inputStream, MediaScratchFileProvider.m1259k((String) null));
    }

    /* renamed from: i */
    public static Uri m3873i(Context context, int i) {
        return new Uri.Builder().scheme("android.resource").authority(context.getPackageName()).appendPath(String.valueOf(i)).build();
    }

    /* renamed from: w */
    public static long m3874w(Uri uri) {
        C1424b.m3584Gj();
        if (m3877z(uri)) {
            ParcelFileDescriptor parcelFileDescriptor = null;
            try {
                ParcelFileDescriptor openFileDescriptor = C0967f.get().getApplicationContext().getContentResolver().openFileDescriptor(uri, "r");
                long max = Math.max(openFileDescriptor.getStatSize(), 0);
                try {
                    openFileDescriptor.close();
                } catch (IOException unused) {
                }
                return max;
            } catch (FileNotFoundException e) {
                C1430e.m3623e("MessagingApp", "Error getting content size", e);
                if (parcelFileDescriptor != null) {
                    try {
                        parcelFileDescriptor.close();
                    } catch (IOException unused2) {
                    }
                }
            } catch (Throwable th) {
                if (parcelFileDescriptor != null) {
                    try {
                        parcelFileDescriptor.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } else {
            C1424b.fail("Unsupported uri type!");
            return 0;
        }
    }

    /* renamed from: x */
    public static boolean m3875x(Uri uri) {
        return TextUtils.equals(uri.getScheme(), "android.resource");
    }

    /* renamed from: y */
    public static boolean m3876y(Uri uri) {
        return uri != null && TextUtils.equals(uri.getScheme(), "file");
    }

    /* renamed from: z */
    public static boolean m3877z(Uri uri) {
        String scheme = uri.getScheme();
        return TextUtils.equals(scheme, "android.resource") || TextUtils.equals(scheme, "content") || TextUtils.equals(scheme, "file");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0056 A[SYNTHETIC, Splitter:B:26:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0063 A[SYNTHETIC, Splitter:B:34:0x0063] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.net.Uri m3870a(android.net.Uri r6, java.io.File r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "error trying to close the inputStream"
            java.lang.String r1 = "MessagingApp"
            com.android.messaging.f r2 = com.android.messaging.C0967f.get()
            android.content.Context r2 = r2.getApplicationContext()
            r3 = 0
            boolean r4 = m3877z(r6)     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            if (r4 == 0) goto L_0x001c
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.io.InputStream r6 = r2.openInputStream(r6)     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            goto L_0x0033
        L_0x001c:
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            r2.<init>(r6)     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.net.URLConnection r6 = r2.openConnection()     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            java.io.InputStream r6 = r6.getInputStream()     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            r2.<init>(r6)     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            r6 = r2
        L_0x0033:
            android.net.Uri r7 = m3871a((java.io.InputStream) r6, (java.io.File) r7, (java.lang.String) r8)     // Catch:{ Exception -> 0x0046, all -> 0x0042 }
            if (r6 == 0) goto L_0x0041
            r6.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0041
        L_0x003d:
            r6 = move-exception
            com.android.messaging.util.C1430e.m3623e(r1, r0, r6)
        L_0x0041:
            return r7
        L_0x0042:
            r7 = move-exception
            r3 = r6
            r6 = r7
            goto L_0x0061
        L_0x0046:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
            goto L_0x004f
        L_0x004b:
            r6 = move-exception
            goto L_0x0061
        L_0x004d:
            r6 = move-exception
            r7 = r3
        L_0x004f:
            java.lang.String r8 = "Error while retrieving media "
            com.android.messaging.util.C1430e.m3623e(r1, r8, r6)     // Catch:{ all -> 0x005f }
            if (r7 == 0) goto L_0x005e
            r7.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r6 = move-exception
            com.android.messaging.util.C1430e.m3623e(r1, r0, r6)
        L_0x005e:
            return r3
        L_0x005f:
            r6 = move-exception
            r3 = r7
        L_0x0061:
            if (r3 == 0) goto L_0x006b
            r3.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x006b
        L_0x0067:
            r7 = move-exception
            com.android.messaging.util.C1430e.m3623e(r1, r0, r7)
        L_0x006b:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.C1488za.m3870a(android.net.Uri, java.io.File, java.lang.String):android.net.Uri");
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0038 A[SYNTHETIC, Splitter:B:33:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0050 A[SYNTHETIC, Splitter:B:52:0x0050] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.net.Uri m3869a(android.content.Context r3, java.io.InputStream r4, android.net.Uri r5) {
        /*
            java.lang.String r0 = "error trying to flush the outputStream"
            java.lang.String r1 = "MessagingApp"
            com.android.messaging.util.C1424b.m3584Gj()
            r2 = 0
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch:{ Exception -> 0x002f, all -> 0x002c }
            java.io.OutputStream r3 = r3.openOutputStream(r5)     // Catch:{ Exception -> 0x002f, all -> 0x002c }
            com.google.common.p043io.C1715d.copy(r4, r3)     // Catch:{ Exception -> 0x002a }
            r3.flush()     // Catch:{ IOException -> 0x001c }
            r3.close()     // Catch:{ IOException -> 0x0019 }
        L_0x0019:
            return r5
        L_0x001a:
            r4 = move-exception
            goto L_0x0024
        L_0x001c:
            r4 = move-exception
            com.android.messaging.util.C1430e.m3623e(r1, r0, r4)     // Catch:{ all -> 0x001a }
            r3.close()     // Catch:{ IOException -> 0x0023 }
        L_0x0023:
            return r2
        L_0x0024:
            r3.close()     // Catch:{ IOException -> 0x0027 }
        L_0x0027:
            throw r4
        L_0x0028:
            r4 = move-exception
            goto L_0x004e
        L_0x002a:
            r4 = move-exception
            goto L_0x0031
        L_0x002c:
            r4 = move-exception
            r3 = r2
            goto L_0x004e
        L_0x002f:
            r4 = move-exception
            r3 = r2
        L_0x0031:
            java.lang.String r5 = "Error while copying content "
            com.android.messaging.util.C1430e.m3623e(r1, r5, r4)     // Catch:{ all -> 0x0028 }
            if (r3 == 0) goto L_0x004d
            r3.flush()     // Catch:{ IOException -> 0x0041 }
            r3.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x004d
        L_0x003f:
            r4 = move-exception
            goto L_0x0049
        L_0x0041:
            r4 = move-exception
            com.android.messaging.util.C1430e.m3623e(r1, r0, r4)     // Catch:{ all -> 0x003f }
            r3.close()     // Catch:{ IOException -> 0x0048 }
        L_0x0048:
            return r2
        L_0x0049:
            r3.close()     // Catch:{ IOException -> 0x004c }
        L_0x004c:
            throw r4
        L_0x004d:
            return r2
        L_0x004e:
            if (r3 == 0) goto L_0x0065
            r3.flush()     // Catch:{ IOException -> 0x0059 }
            r3.close()     // Catch:{ IOException -> 0x0065 }
            goto L_0x0065
        L_0x0057:
            r4 = move-exception
            goto L_0x0061
        L_0x0059:
            r4 = move-exception
            com.android.messaging.util.C1430e.m3623e(r1, r0, r4)     // Catch:{ all -> 0x0057 }
            r3.close()     // Catch:{ IOException -> 0x0060 }
        L_0x0060:
            return r2
        L_0x0061:
            r3.close()     // Catch:{ IOException -> 0x0064 }
        L_0x0064:
            throw r4
        L_0x0065:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.C1488za.m3869a(android.content.Context, java.io.InputStream, android.net.Uri):android.net.Uri");
    }
}
