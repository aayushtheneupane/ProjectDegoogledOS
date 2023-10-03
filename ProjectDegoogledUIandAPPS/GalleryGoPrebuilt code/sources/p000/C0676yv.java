package p000;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

/* renamed from: yv */
/* compiled from: PG */
final class C0676yv extends C0663yi {

    /* renamed from: i */
    private final /* synthetic */ C0650xw f16423i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0676yv(C0650xw xwVar, Context context) {
        super(context);
        this.f16423i = xwVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final float mo4514a(DisplayMetrics displayMetrics) {
        return 100.0f / ((float) displayMetrics.densityDpi);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo4649a(View view, C0661yg ygVar) {
        C0650xw xwVar = this.f16423i;
        RecyclerView recyclerView = xwVar.f16316a;
        if (recyclerView != null) {
            int[] a = xwVar.mo10485a(recyclerView.getLayoutManager(), view);
            int i = a[0];
            int i2 = a[1];
            int b = mo10625b(Math.max(Math.abs(i), Math.abs(i2)));
            if (b > 0) {
                ygVar.mo10620a(i, i2, b, this.f16349h);
            }
        }
    }
}
