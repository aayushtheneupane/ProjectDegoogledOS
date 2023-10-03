package p000;

import android.net.Uri;
import p003j$.util.Optional;

/* renamed from: bje */
/* compiled from: PG */
final class bje implements dbt {

    /* renamed from: a */
    private Optional f2570a;

    /* renamed from: b */
    private Uri f2571b;

    /* renamed from: c */
    private dbo f2572c;

    /* renamed from: d */
    private Boolean f2573d;

    /* renamed from: e */
    private Boolean f2574e;

    /* renamed from: f */
    private Boolean f2575f;

    /* renamed from: g */
    private imq f2576g;

    /* renamed from: h */
    private final /* synthetic */ bjw f2577h;

    public /* synthetic */ bje(bjw bjw) {
        this.f2577h = bjw;
    }

    /* renamed from: a */
    public final dbu mo2121a() {
        iol.m14233a((Object) this.f2570a, Optional.class);
        iol.m14233a((Object) this.f2571b, Uri.class);
        iol.m14233a((Object) this.f2572c, dbo.class);
        iol.m14233a((Object) this.f2573d, Boolean.class);
        iol.m14233a((Object) this.f2574e, Boolean.class);
        iol.m14233a((Object) this.f2575f, Boolean.class);
        iol.m14233a((Object) this.f2576g, imq.class);
        return new bjg(this.f2577h, this.f2570a, this.f2571b, this.f2572c, this.f2573d, this.f2574e, this.f2575f, this.f2576g);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ dbt mo2116a(Uri uri) {
        this.f2571b = (Uri) iol.m14228a((Object) uri);
        return this;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ dbt mo2119a(Optional optional) {
        this.f2570a = (Optional) iol.m14228a((Object) optional);
        return this;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ dbt mo2120a(boolean z) {
        this.f2575f = (Boolean) iol.m14228a((Object) Boolean.valueOf(z));
        return this;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ dbt mo2122b(boolean z) {
        this.f2574e = (Boolean) iol.m14228a((Object) Boolean.valueOf(z));
        return this;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ dbt mo2117a(dbo dbo) {
        this.f2572c = (dbo) iol.m14228a((Object) dbo);
        return this;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ dbt mo2118a(imq imq) {
        this.f2576g = (imq) iol.m14228a((Object) imq);
        return this;
    }

    /* renamed from: c */
    public final /* bridge */ /* synthetic */ dbt mo2123c(boolean z) {
        this.f2573d = (Boolean) iol.m14228a((Object) Boolean.valueOf(z));
        return this;
    }
}
