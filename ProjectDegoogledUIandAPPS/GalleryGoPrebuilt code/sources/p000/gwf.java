package p000;

import android.util.SparseArray;
import java.util.Map;

/* renamed from: gwf */
/* compiled from: PG */
final class gwf {

    /* renamed from: a */
    public final Map f12181a = new C0290kn();

    /* renamed from: b */
    public final SparseArray f12182b = new SparseArray();

    /* renamed from: c */
    public int f12183c = 0;

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final gwe mo7130a(int i) {
        gwe gwe = (gwe) this.f12182b.get(i);
        ife.m12828a((Object) gwe, "No ViewBinder for the provided viewType: %s", i);
        return gwe;
    }
}
