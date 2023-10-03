package p000;

import android.graphics.Bitmap;
import android.text.TextUtils;
import java.util.Collection;

/* renamed from: cns */
/* compiled from: PG */
public class cns {
    /* renamed from: a */
    public static void m4637a(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    /* renamed from: a */
    public static String m4634a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        throw new IllegalArgumentException("Must not be null or empty");
    }

    /* renamed from: a */
    public static Collection m4635a(Collection collection) {
        if (!collection.isEmpty()) {
            return collection;
        }
        throw new IllegalArgumentException("Must not be empty.");
    }

    /* renamed from: a */
    public static Object m4632a(Object obj) {
        return m4633a(obj, "Argument must not be null");
    }

    /* renamed from: a */
    public static Object m4633a(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }

    public cns(byte[] bArr) {
    }

    public cns() {
    }

    /* renamed from: a */
    public static void m4636a(C0147fh fhVar) {
        Object a = ggq.m10280a((Object) fhVar);
        if (a instanceof cnc) {
            ((cnc) a).mo2638j();
        }
    }

    /* renamed from: b */
    public static void m4638b(C0147fh fhVar) {
        Object a = ggq.m10280a((Object) fhVar);
        if (a instanceof cnc) {
            ((cnc) a).mo2637b();
        }
    }

    /* renamed from: a */
    public static Bitmap m4631a(Bitmap bitmap, Bitmap.Config config) {
        if (config.equals(bitmap.getConfig()) || (bitmap = bitmap.copy(config, false)) != null) {
            return bitmap;
        }
        String valueOf = String.valueOf(config.name());
        throw new IllegalArgumentException(valueOf.length() == 0 ? new String("Couldn't convert bitmap config to ") : "Couldn't convert bitmap config to ".concat(valueOf));
    }
}
