package p000;

import android.net.Uri;
import p003j$.util.Optional;

/* renamed from: cki */
/* compiled from: PG */
final /* synthetic */ class cki implements hpr {

    /* renamed from: a */
    private final Uri f4557a;

    /* renamed from: b */
    private final String f4558b;

    public cki(Uri uri, String str) {
        this.f4557a = uri;
        this.f4558b = str;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Uri uri = this.f4557a;
        String str = this.f4558b;
        Optional optional = (Optional) obj;
        if (optional.isPresent()) {
            uri = Uri.parse((String) optional.get());
        }
        String str2 = (String) ife.m12898e((Object) uri.getLastPathSegment());
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + str2.length());
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }
}
