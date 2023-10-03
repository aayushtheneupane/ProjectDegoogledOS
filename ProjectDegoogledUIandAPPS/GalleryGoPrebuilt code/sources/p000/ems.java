package p000;

import java.lang.ref.WeakReference;

/* renamed from: ems */
/* compiled from: PG */
final class ems extends ewg {

    /* renamed from: a */
    private final WeakReference f8571a;

    public ems(emv emv) {
        this.f8571a = new WeakReference(emv);
    }

    /* renamed from: a */
    public final void mo5020a(ewp ewp) {
        emv emv = (emv) this.f8571a.get();
        if (emv != null) {
            emv.f8574a.mo5036a((enc) new emr(emv, emv, ewp));
        }
    }
}
