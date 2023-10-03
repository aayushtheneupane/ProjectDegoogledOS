package p000;

import com.google.android.libraries.material.progress.MaterialProgressBar;

/* renamed from: eal */
/* compiled from: PG */
final class eal implements gvc {

    /* renamed from: a */
    private final /* synthetic */ MaterialProgressBar f7784a;

    /* renamed from: b */
    private final /* synthetic */ eam f7785b;

    public eal(eam eam, MaterialProgressBar materialProgressBar) {
        this.f7785b = eam;
        this.f7784a = materialProgressBar;
    }

    /* renamed from: a */
    public final void mo2679a() {
    }

    /* renamed from: a */
    public final void mo2681a(Throwable th) {
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2680a(Object obj) {
        Integer num = (Integer) obj;
        if (this.f7785b.f7787b.f7776c != -1) {
            this.f7784a.setProgress(num.intValue());
        }
    }
}
