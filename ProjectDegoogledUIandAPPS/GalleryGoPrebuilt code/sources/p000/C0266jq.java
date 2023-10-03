package p000;

import android.content.Context;
import java.util.concurrent.Callable;

/* renamed from: jq */
/* compiled from: PG */
final class C0266jq implements Callable {

    /* renamed from: a */
    private final /* synthetic */ Context f15089a;

    /* renamed from: b */
    private final /* synthetic */ C0265jp f15090b;

    /* renamed from: c */
    private final /* synthetic */ int f15091c;

    /* renamed from: d */
    private final /* synthetic */ String f15092d;

    public C0266jq(Context context, C0265jp jpVar, int i, String str) {
        this.f15089a = context;
        this.f15090b = jpVar;
        this.f15091c = i;
        this.f15092d = str;
    }

    /* renamed from: a */
    public final C0272jw call() {
        C0272jw a = C0273jx.m14499a(this.f15089a, this.f15090b, this.f15091c);
        if (a.f15102a != null) {
            C0273jx.f15104a.mo9238a((Object) this.f15092d, (Object) a.f15102a);
        }
        return a;
    }
}
