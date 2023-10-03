package p000;

import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import java.util.Arrays;

/* renamed from: gtr */
/* compiled from: PG */
final class gtr implements gba {

    /* renamed from: a */
    private final /* synthetic */ Uri f12038a;

    /* renamed from: b */
    private final /* synthetic */ String[] f12039b;

    /* renamed from: c */
    private final /* synthetic */ String f12040c;

    /* renamed from: d */
    private final /* synthetic */ String[] f12041d;

    /* renamed from: e */
    private final /* synthetic */ String f12042e;

    /* renamed from: f */
    private final /* synthetic */ gtt f12043f;

    public gtr(gtt gtt, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        this.f12043f = gtt;
        this.f12038a = uri;
        this.f12039b = strArr;
        this.f12040c = str;
        this.f12041d = strArr2;
        this.f12042e = str2;
    }

    /* renamed from: a */
    public final String mo6369a() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(Arrays.toString(this.f12039b));
        sb.append(" FROM ");
        sb.append(this.f12038a.getAuthority());
        if (this.f12040c != null) {
            sb.append(" WHERE ");
            sb.append(this.f12040c);
        }
        if (this.f12042e != null) {
            sb.append(" ORDER BY ");
            sb.append(this.f12042e);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public final Cursor mo6368a(CancellationSignal cancellationSignal) {
        Cursor query = this.f12043f.f12044a.query(this.f12038a, this.f12039b, this.f12040c, this.f12041d, this.f12042e, cancellationSignal);
        if (query != null) {
            return query;
        }
        String valueOf = String.valueOf(mo6369a());
        throw new gts(valueOf.length() == 0 ? new String("Null returned from query: ") : "Null returned from query: ".concat(valueOf));
    }
}
