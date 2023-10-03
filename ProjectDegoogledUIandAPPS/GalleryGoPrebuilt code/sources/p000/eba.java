package p000;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

/* renamed from: eba */
/* compiled from: PG */
final class eba extends C0663yi {

    /* renamed from: i */
    private final /* synthetic */ C0647xt f7821i;

    /* renamed from: j */
    private final /* synthetic */ ebb f7822j;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public eba(ebb ebb, Context context, C0647xt xtVar) {
        super(context);
        this.f7822j = ebb;
        this.f7821i = xtVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final float mo4514a(DisplayMetrics displayMetrics) {
        return 100.0f / ((float) displayMetrics.densityDpi);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final int mo4648a(int i) {
        return Math.min(this.f7822j.f7823c, super.mo4648a(i));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo4649a(View view, C0661yg ygVar) {
        int[] iArr = (int[]) ife.m12898e((Object) this.f7822j.mo10485a(this.f7821i, view));
        int i = iArr[0];
        int i2 = iArr[1];
        int b = mo10625b(Math.max(Math.abs(i), Math.abs(i2)));
        if (b > 0) {
            ygVar.mo10620a(i, i2, b, this.f16349h);
        }
    }
}
