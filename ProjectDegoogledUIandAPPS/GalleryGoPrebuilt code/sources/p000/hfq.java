package p000;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import java.io.File;

/* renamed from: hfq */
/* compiled from: PG */
public final class hfq {

    /* renamed from: a */
    private final Context f12663a;

    /* renamed from: b */
    private Context f12664b;

    public hfq(Context context) {
        this.f12663a = context;
    }

    /* renamed from: a */
    public final hto mo7371a() {
        return hto.m12123a(m11380a(1, 2), m11380a(1, 1), m11380a(2, 2), m11380a(2, 1));
    }

    /* renamed from: a */
    public final File mo7372a(hfs hfs) {
        hfp hfp = (hfp) hfs;
        return m11380a(hfp.f12661a, hfp.f12662b);
    }

    /* renamed from: a */
    private final File m11380a(int i, int i2) {
        Context context = this.f12663a;
        if (i2 == 1) {
            context = m11381b();
        }
        if (i - 1 != 0) {
            return context.getCacheDir();
        }
        return context.getFilesDir();
    }

    /* renamed from: b */
    private final synchronized Context m11381b() {
        if (this.f12664b == null) {
            Context c = C0071co.m4672c(this.f12663a);
            if (c == null) {
                this.f12664b = this.f12663a;
            } else {
                this.f12664b = c;
            }
        }
        return this.f12664b;
    }

    /* renamed from: a */
    public final Uri mo7370a(hfs hfs, String str, gtf gtf) {
        String str2;
        String str3;
        ife.m12898e((Object) gtf);
        int a = hfs.mo7365a();
        if (hfs.mo7366b() == 1) {
            int i = Build.VERSION.SDK_INT;
            str2 = "directboot-";
        } else {
            str2 = "";
        }
        int i2 = a - 1;
        if (a != 0) {
            if (i2 == 0) {
                str3 = str2.concat("files");
            } else if (i2 == 1) {
                str3 = str2.concat("cache");
            } else {
                throw new IllegalArgumentException();
            }
            String b = hpz.m11900b(str);
            if (b.startsWith("/")) {
                b = b.substring(1);
            }
            return new Uri.Builder().scheme("android").authority(this.f12663a.getPackageName()).path(String.format("/%s/%s", new Object[]{str3, b})).build();
        }
        throw null;
    }
}
