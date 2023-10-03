package p000;

import android.content.Context;
import android.content.ContextWrapper;
import java.util.List;
import java.util.Map;

/* renamed from: apa */
/* compiled from: PG */
public final class apa extends ContextWrapper {

    /* renamed from: a */
    public static final apo f1314a = new apo((byte[]) null);

    /* renamed from: b */
    public final aui f1315b;

    /* renamed from: c */
    public final aph f1316c;

    /* renamed from: d */
    public final List f1317d;

    /* renamed from: e */
    public final Map f1318e;

    /* renamed from: f */
    public final atj f1319f;

    /* renamed from: g */
    public final int f1320g = 4;

    /* renamed from: h */
    private final aov f1321h;

    /* renamed from: i */
    private bdx f1322i;

    public apa(Context context, aui aui, aph aph, aov aov, Map map, List list, atj atj) {
        super(context.getApplicationContext());
        this.f1315b = aui;
        this.f1316c = aph;
        this.f1321h = aov;
        this.f1317d = list;
        this.f1318e = map;
        this.f1319f = atj;
    }

    /* renamed from: a */
    public final synchronized bdx mo1402a() {
        if (this.f1322i == null) {
            this.f1322i = this.f1321h.mo1394a().mo1871j();
        }
        return this.f1322i;
    }
}
