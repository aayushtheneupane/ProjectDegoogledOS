package p000;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* renamed from: eme */
/* compiled from: PG */
final class eme implements ekw {

    /* renamed from: a */
    private final /* synthetic */ BasePendingResult f8548a;

    /* renamed from: b */
    private final /* synthetic */ emg f8549b;

    public eme(emg emg, BasePendingResult basePendingResult) {
        this.f8549b = emg;
        this.f8548a = basePendingResult;
    }

    /* renamed from: a */
    public final void mo4951a(Status status) {
        this.f8549b.f8552a.remove(this.f8548a);
    }
}
