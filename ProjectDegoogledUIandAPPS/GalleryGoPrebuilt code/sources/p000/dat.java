package p000;

import java.io.File;
import java.util.concurrent.Callable;

/* renamed from: dat */
/* compiled from: PG */
final /* synthetic */ class dat implements Callable {

    /* renamed from: a */
    private final String f6145a;

    /* renamed from: b */
    private final String f6146b;

    public dat(String str, String str2) {
        this.f6145a = str;
        this.f6146b = str2;
    }

    public final Object call() {
        String str = this.f6145a;
        String str2 = this.f6146b;
        int i = dav.f6148b;
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1) {
            str = str.substring(0, lastIndexOf);
        }
        int lastIndexOf2 = str.lastIndexOf(126);
        int i2 = 2;
        if (lastIndexOf2 != -1 && lastIndexOf2 < str.length() - 1) {
            try {
                i2 = Integer.parseInt(str.substring(lastIndexOf2 + 1)) + 1;
                str = str.substring(0, lastIndexOf2);
            } catch (NumberFormatException e) {
            }
        }
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf(str2);
        String str3 = valueOf2.length() == 0 ? new String(valueOf) : valueOf.concat(valueOf2);
        File file = new File(str3);
        while (file.exists()) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12 + String.valueOf(str2).length());
            sb.append(str);
            sb.append('~');
            sb.append(i2);
            sb.append(str2);
            str3 = sb.toString();
            file = new File(str3);
            i2++;
        }
        return str3;
    }
}
