package p000;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

/* renamed from: wy */
/* compiled from: PG */
final class C0625wy extends C0663yi {

    /* renamed from: i */
    private final /* synthetic */ C0626wz f16279i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0625wy(C0626wz wzVar, Context context) {
        super(context);
        this.f16279i = wzVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final float mo4514a(DisplayMetrics displayMetrics) {
        return 100.0f / ((float) displayMetrics.densityDpi);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final int mo4648a(int i) {
        return Math.min(100, super.mo4648a(i));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo4649a(View view, C0661yg ygVar) {
        C0626wz wzVar = this.f16279i;
        int[] a = wzVar.mo10485a(wzVar.f16316a.getLayoutManager(), view);
        int i = a[0];
        int i2 = a[1];
        int b = mo10625b(Math.max(Math.abs(i), Math.abs(i2)));
        if (b > 0) {
            ygVar.mo10620a(i, i2, b, this.f16349h);
        }
    }
}
