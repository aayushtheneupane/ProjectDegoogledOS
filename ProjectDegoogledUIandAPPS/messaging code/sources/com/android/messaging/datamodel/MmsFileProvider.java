package com.android.messaging.datamodel;

import android.content.Context;
import android.net.Uri;
import com.android.messaging.C0967f;
import com.android.messaging.util.C1430e;
import java.io.File;
import java.io.IOException;
import p026b.p027a.p030b.p031a.C0632a;

public class MmsFileProvider extends FileProvider {
    static final String AUTHORITY = "com.android.messaging.datamodel.MmsFileProvider";

    /* renamed from: D */
    private static File m1275D(Context context) {
        return new File(context.getCacheDir(), "rawmms");
    }

    /* renamed from: Ua */
    public static Uri m1276Ua() {
        Uri a = FileProvider.m1233a(AUTHORITY, (String) null);
        File _a = m1277_a(a.getPath());
        if (!FileProvider.m1234a(_a)) {
            StringBuilder Pa = C0632a.m1011Pa("Failed to create temp file ");
            Pa.append(_a.getAbsolutePath());
            C1430e.m3622e("MessagingApp", Pa.toString());
        }
        return a;
    }

    /* renamed from: _a */
    private static File m1277_a(String str) {
        Context applicationContext = C0967f.get().getApplicationContext();
        File file = new File(m1275D(applicationContext), C0632a.m1025k(str, ".dat"));
        try {
            if (file.getCanonicalPath().startsWith(m1275D(applicationContext).getCanonicalPath())) {
                return file;
            }
            C1430e.m3622e("MessagingApp", "getFile: path " + file.getCanonicalPath() + " does not start with " + m1275D(applicationContext).getCanonicalPath());
            return null;
        } catch (IOException e) {
            C1430e.m3623e("MessagingApp", "getFile: getCanonicalPath failed ", e);
            return null;
        }
    }

    /* renamed from: e */
    public static File m1278e(Uri uri) {
        return m1277_a(uri.getPath());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public File mo5870b(String str, String str2) {
        return m1277_a(str);
    }
}
