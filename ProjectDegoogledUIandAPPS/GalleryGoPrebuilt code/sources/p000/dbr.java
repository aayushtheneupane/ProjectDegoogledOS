package p000;

import android.net.Uri;
import p003j$.util.Optional;

/* renamed from: dbr */
/* compiled from: PG */
final /* synthetic */ class dbr implements icf {

    /* renamed from: a */
    private final dbs f6211a;

    /* renamed from: b */
    private final Optional f6212b;

    /* renamed from: c */
    private final dbo f6213c;

    /* renamed from: d */
    private final boolean f6214d;

    public dbr(dbs dbs, Optional optional, dbo dbo, boolean z) {
        this.f6211a = dbs;
        this.f6212b = optional;
        this.f6213c = dbo;
        this.f6214d = z;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        return this.f6211a.mo4039a(this.f6212b, Uri.parse((String) obj), this.f6213c, this.f6214d, false, false, "save as");
    }
}
