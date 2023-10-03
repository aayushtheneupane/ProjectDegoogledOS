package p000;

import java.util.concurrent.Executor;

/* renamed from: cud */
/* compiled from: PG */
public final class cud implements cue {

    /* renamed from: a */
    public boolean f5661a = false;

    /* renamed from: b */
    private final /* synthetic */ cue f5662b;

    public cud(cue cue) {
        this.f5662b = cue;
    }

    /* renamed from: a */
    public final ieh mo3143a(ice ice, Executor executor) {
        return cun.m5445a((cue) this, ice, executor);
    }

    /* renamed from: a */
    public final ieh mo3142a() {
        if (!this.f5661a) {
            return gte.m10770a(this.f5662b.mo3142a(), (hpr) new cuc(this), (Executor) idh.f13918a);
        }
        return ife.m12820a((Object) false);
    }
}
