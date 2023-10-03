package p000;

import android.net.Uri;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: dge */
/* compiled from: PG */
public final class dge {

    /* renamed from: a */
    public final Map f6494a;

    /* renamed from: b */
    public final cyr f6495b;

    /* renamed from: c */
    public final iek f6496c;

    /* renamed from: d */
    private final det f6497d;

    public dge(det det, Map map, cyr cyr, iek iek) {
        this.f6497d = det;
        this.f6494a = map;
        this.f6495b = cyr;
        this.f6496c = iek;
    }

    /* renamed from: a */
    public final ieh mo4114a() {
        ieh ieh;
        try {
            ieh = this.f6497d.f6416i.mo2539a();
        } catch (Exception e) {
            ieh = ife.m12822a((Throwable) e);
        }
        return gte.m10771a(ieh, (icf) new dgc(this), (Executor) this.f6496c);
    }

    /* renamed from: a */
    public final ieh mo4115a(Uri uri) {
        return gte.m10771a(mo4114a(), (icf) new dgd(this, uri), (Executor) idh.f13918a);
    }
}
