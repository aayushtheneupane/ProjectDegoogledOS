package p000;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import java.io.File;
import java.util.HashMap;

/* renamed from: fym */
/* compiled from: PG */
public final class fym {
    /* renamed from: a */
    public static File m9878a(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            SystemClock.sleep(100);
            filesDir = context.getFilesDir();
            if (filesDir == null) {
                throw new IllegalStateException("getFilesDir returned null twice.");
            }
        }
        return filesDir;
    }

    /* renamed from: a */
    public static File m9879a(Uri uri) {
        if (!uri.getScheme().equals("file")) {
            throw new fyb("Scheme must be 'file'");
        } else if (!TextUtils.isEmpty(uri.getQuery())) {
            throw new fyb("Did not expect uri to have query");
        } else if (TextUtils.isEmpty(uri.getAuthority())) {
            return new File(uri.getPath());
        } else {
            throw new fyb("Did not expect uri to have authority");
        }
    }

    public fym() {
        new HashMap();
    }

    /* renamed from: a */
    public static void m9880a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }
}
