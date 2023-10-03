package p000;

import android.os.Build;
import java.io.File;
import java.util.Set;
import java.util.concurrent.Callable;

/* renamed from: hal */
/* compiled from: PG */
final /* synthetic */ class hal implements Callable {

    /* renamed from: a */
    private final Set f12410a;

    /* renamed from: b */
    private final String f12411b;

    /* renamed from: c */
    private final hfq f12412c;

    public hal(Set set, String str, hfq hfq) {
        this.f12410a = set;
        this.f12411b = str;
        this.f12412c = hfq;
    }

    public final Object call() {
        Set set = this.f12410a;
        String str = this.f12411b;
        hfq hfq = this.f12412c;
        hfs a = hfs.m11385a(1);
        int i = Build.VERSION.SDK_INT;
        if (set.contains(str)) {
            a = hfs.m11386c();
        }
        String str2 = File.separator;
        String str3 = File.separator;
        int length = String.valueOf(str2).length();
        StringBuilder sb = new StringBuilder(length + 9 + String.valueOf(str3).length() + String.valueOf(str).length());
        sb.append(str2);
        sb.append("phenotype");
        sb.append(str3);
        sb.append(str);
        return hfq.mo7370a(a, sb.toString(), gtf.f12011a);
    }
}
