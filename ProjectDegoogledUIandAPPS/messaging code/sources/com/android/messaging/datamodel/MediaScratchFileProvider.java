package com.android.messaging.datamodel;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.text.TextUtils;
import com.android.messaging.C0967f;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import java.io.File;
import java.io.IOException;
import java.util.List;
import p000a.p005b.C0027n;
import p026b.p027a.p030b.p031a.C0632a;

public class MediaScratchFileProvider extends FileProvider {
    public static final String AUTHORITY = "com.android.messaging.datamodel.MediaScratchFileProvider";

    /* renamed from: yb */
    private static final C0027n f1019yb = new C0027n();

    /* renamed from: D */
    private static File m1254D(Context context) {
        return new File(context.getCacheDir(), "mediascratchspace");
    }

    /* renamed from: Ta */
    public static Uri.Builder m1255Ta() {
        return new Uri.Builder().authority(AUTHORITY).scheme("content");
    }

    /* renamed from: a */
    public static void m1256a(Uri uri, String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (f1019yb) {
                f1019yb.put(uri, str);
            }
        }
    }

    /* renamed from: c */
    public static File m1257c(Uri uri) {
        C1424b.equals((Object) AUTHORITY, (Object) uri.getAuthority());
        return m1260l(uri.getPath(), uri.getQueryParameter("ext"));
    }

    /* renamed from: d */
    public static boolean m1258d(Uri uri) {
        if (uri == null) {
            return false;
        }
        List<String> pathSegments = uri.getPathSegments();
        if (!TextUtils.equals(uri.getScheme(), "content") || !TextUtils.equals(uri.getAuthority(), AUTHORITY) || pathSegments.size() != 1 || !FileProvider.m1235j(pathSegments.get(0))) {
            return false;
        }
        return true;
    }

    /* renamed from: k */
    public static Uri m1259k(String str) {
        Uri a = FileProvider.m1233a(AUTHORITY, str);
        File l = m1260l(a.getPath(), str);
        if (!FileProvider.m1234a(l)) {
            StringBuilder Pa = C0632a.m1011Pa("Failed to create temp file ");
            Pa.append(l.getAbsolutePath());
            C1430e.m3622e("MessagingApp", Pa.toString());
        }
        return a;
    }

    /* renamed from: l */
    private static File m1260l(String str, String str2) {
        Context applicationContext = C0967f.get().getApplicationContext();
        File D = m1254D(applicationContext);
        if (!TextUtils.isEmpty(str2)) {
            str = C0632a.m1023d(str, ".", str2);
        }
        File file = new File(D, str);
        try {
            if (file.getCanonicalPath().startsWith(m1254D(applicationContext).getCanonicalPath())) {
                return file;
            }
            C1430e.m3622e("MessagingApp", "getFileWithExtension: path " + file.getCanonicalPath() + " does not start with " + m1254D(applicationContext).getCanonicalPath());
            return null;
        } catch (IOException e) {
            C1430e.m3623e("MessagingApp", "getFileWithExtension: getCanonicalPath failed ", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public File mo5870b(String str, String str2) {
        return m1260l(str, str2);
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String str3;
        if (strArr == null || strArr.length <= 0 || !TextUtils.equals(strArr[0], "_display_name") || !m1258d(uri)) {
            return null;
        }
        synchronized (f1019yb) {
            str3 = (String) f1019yb.get(uri);
        }
        if (TextUtils.isEmpty(str3)) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"_display_name"});
        matrixCursor.newRow().add(str3);
        return matrixCursor;
    }
}
