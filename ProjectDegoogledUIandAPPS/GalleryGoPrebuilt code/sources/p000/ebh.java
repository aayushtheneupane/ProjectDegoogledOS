package p000;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

/* renamed from: ebh */
/* compiled from: PG */
public abstract class ebh {
    /* renamed from: a */
    public abstract Uri mo4656a();

    /* renamed from: d */
    public final String mo4663d() {
        return mo4656a().toString();
    }

    /* renamed from: c */
    public final boolean mo4662c() {
        return m7087a(mo4656a(), "content");
    }

    /* renamed from: b */
    public final boolean mo4661b() {
        return m7087a(mo4656a(), "file");
    }

    /* renamed from: a */
    public final ParcelFileDescriptor mo4660a(Context context) {
        return fra.m9449b(context, mo4656a(), "r");
    }

    /* renamed from: a */
    public static ebh m7086a(String str) {
        return new ebg(Uri.parse(str));
    }

    /* renamed from: a */
    public static boolean m7087a(Uri uri, String str) {
        String scheme = uri.getScheme();
        if (scheme == null) {
            return false;
        }
        return ife.m12854a((CharSequence) scheme, (CharSequence) str);
    }
}
