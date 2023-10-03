package p000;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import java.util.Set;

/* renamed from: eoj */
/* compiled from: PG */
public final class eoj extends ewg implements ekt, eku {

    /* renamed from: h */
    private static final eov f8706h = ewb.f9124b;

    /* renamed from: a */
    public final Context f8707a;

    /* renamed from: b */
    public final Handler f8708b;

    /* renamed from: c */
    public final Set f8709c;

    /* renamed from: d */
    public final epk f8710d;

    /* renamed from: e */
    public ewc f8711e;

    /* renamed from: f */
    public eoi f8712f;

    /* renamed from: g */
    public final eov f8713g;

    public eoj(Context context, Handler handler, epk epk) {
        eov eov = f8706h;
        this.f8707a = context;
        this.f8708b = handler;
        this.f8710d = (epk) abj.m86a((Object) epk, (Object) "ClientSettings must not be null");
        this.f8709c = epk.f8776a;
        this.f8713g = eov;
    }

    /* renamed from: a */
    public final void mo4993a(Bundle bundle) {
        this.f8711e.mo5332a(this);
    }

    /* renamed from: a */
    public final void mo4994a(ejq ejq) {
        this.f8712f.mo5057b(ejq);
    }

    /* renamed from: a */
    public final void mo4992a(int i) {
        this.f8711e.mo4931d();
    }

    /* renamed from: a */
    public final void mo5020a(ewp ewp) {
        this.f8708b.post(new eoh(this, ewp));
    }
}
