package p000;

import android.content.ContentResolver;
import android.net.Uri;

/* renamed from: dhc */
/* compiled from: PG */
public final class dhc {

    /* renamed from: a */
    public final ContentResolver f6533a;

    /* renamed from: b */
    public final gtt f6534b;

    /* renamed from: c */
    public final dhl f6535c;

    public dhc(ContentResolver contentResolver, gtt gtt, dhl dhl) {
        this.f6533a = contentResolver;
        this.f6534b = gtt;
        this.f6535c = dhl;
    }

    /* renamed from: a */
    public static Uri m6104a(String str) {
        return new Uri.Builder().scheme("content").authority(str).build();
    }
}
