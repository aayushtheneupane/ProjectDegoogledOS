package p000;

import android.net.Uri;

/* renamed from: fqb */
/* compiled from: PG */
public final class fqb {

    /* renamed from: a */
    private static final C0290kn f10242a = new C0290kn();

    /* renamed from: a */
    public static synchronized Uri m9396a(String str) {
        Uri uri;
        synchronized (fqb.class) {
            uri = (Uri) f10242a.get(str);
            if (uri == null) {
                String valueOf = String.valueOf(Uri.encode(str));
                uri = Uri.parse(valueOf.length() == 0 ? new String("content://com.google.android.gms.phenotype/") : "content://com.google.android.gms.phenotype/".concat(valueOf));
                f10242a.put(str, uri);
            }
        }
        return uri;
    }

    /* renamed from: b */
    public static String m9397b(String str) {
        int i;
        int indexOf = str.indexOf("#");
        if (indexOf < 0 || (i = indexOf + 1) >= str.length() || str.charAt(i) != '@') {
            return str;
        }
        int i2 = indexOf + 2;
        if (str.length() == i2) {
            return str.substring(0, indexOf);
        }
        String valueOf = String.valueOf(str.substring(0, i));
        String valueOf2 = String.valueOf(str.substring(i2));
        return valueOf2.length() == 0 ? new String(valueOf) : valueOf.concat(valueOf2);
    }
}
