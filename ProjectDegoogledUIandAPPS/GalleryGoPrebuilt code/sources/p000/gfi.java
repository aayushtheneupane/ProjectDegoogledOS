package p000;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import com.google.android.apps.photosgo.R;

/* renamed from: gfi */
/* compiled from: PG */
public final class gfi extends C0393oi {

    /* renamed from: b */
    private Drawable f11164b;

    /* renamed from: c */
    private final Rect f11165c;

    public gfi(Context context) {
        this(context, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gfi(android.content.Context r12, int r13) {
        /*
            r11 = this;
            int r0 = m10198a(r12)
            r1 = 0
            r2 = 2130968616(0x7f040028, float:1.754589E38)
            r3 = 2131951916(0x7f13012c, float:1.954026E38)
            android.content.Context r4 = p000.gkl.m10444a(r12, r1, r2, r3)
            if (r0 == 0) goto L_0x0018
            qg r5 = new qg
            r5.<init>((android.content.Context) r4, (int) r0)
            r4 = r5
            goto L_0x0019
        L_0x0018:
        L_0x0019:
            if (r13 != 0) goto L_0x0020
            int r13 = m10198a(r12)
            goto L_0x0021
        L_0x0020:
        L_0x0021:
            r11.<init>(r4, r13)
            android.content.Context r12 = r11.mo9518a()
            android.content.res.Resources$Theme r13 = r12.getTheme()
            r6 = 0
            int[] r7 = p000.gfj.f11166a
            r8 = 2130968616(0x7f040028, float:1.754589E38)
            r9 = 2131951916(0x7f13012c, float:1.954026E38)
            r0 = 0
            int[] r10 = new int[r0]
            r5 = r12
            android.content.res.TypedArray r4 = p000.gga.m10238a(r5, r6, r7, r8, r9, r10)
            r5 = 2
            android.content.res.Resources r6 = r12.getResources()
            r7 = 2131165480(0x7f070128, float:1.7945178E38)
            int r6 = r6.getDimensionPixelSize(r7)
            int r5 = r4.getDimensionPixelSize(r5, r6)
            r6 = 3
            android.content.res.Resources r7 = r12.getResources()
            r8 = 2131165481(0x7f070129, float:1.794518E38)
            int r7 = r7.getDimensionPixelSize(r8)
            int r6 = r4.getDimensionPixelSize(r6, r7)
            android.content.res.Resources r7 = r12.getResources()
            r8 = 2131165479(0x7f070127, float:1.7945176E38)
            int r7 = r7.getDimensionPixelSize(r8)
            r8 = 1
            int r7 = r4.getDimensionPixelSize(r8, r7)
            android.content.res.Resources r9 = r12.getResources()
            r10 = 2131165478(0x7f070126, float:1.7945174E38)
            int r9 = r9.getDimensionPixelSize(r10)
            int r0 = r4.getDimensionPixelSize(r0, r9)
            r4.recycle()
            int r4 = android.os.Build.VERSION.SDK_INT
            android.content.res.Resources r4 = r12.getResources()
            android.content.res.Configuration r4 = r4.getConfiguration()
            int r4 = r4.getLayoutDirection()
            if (r4 == r8) goto L_0x0091
            r9 = r7
            goto L_0x0092
        L_0x0091:
            r9 = r5
        L_0x0092:
            if (r4 == r8) goto L_0x0095
            goto L_0x0096
        L_0x0095:
            r5 = r7
        L_0x0096:
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>(r5, r6, r9, r0)
            r11.f11165c = r4
            java.lang.Class r0 = r11.getClass()
            java.lang.String r0 = r0.getCanonicalName()
            int r0 = p000.ggf.m10245a((android.content.Context) r12, (java.lang.String) r0)
            ggu r4 = new ggu
            r4.<init>(r12, r1, r2, r3)
            r4.mo6634a((android.content.Context) r12)
            android.content.res.ColorStateList r12 = android.content.res.ColorStateList.valueOf(r0)
            r4.mo6635a((android.content.res.ColorStateList) r12)
            int r12 = android.os.Build.VERSION.SDK_INT
            r0 = 28
            if (r12 < r0) goto L_0x00ee
            android.util.TypedValue r12 = new android.util.TypedValue
            r12.<init>()
            r0 = 16844145(0x1010571, float:2.3697462E-38)
            r13.resolveAttribute(r0, r12, r8)
            android.content.Context r13 = r11.mo9518a()
            android.content.res.Resources r13 = r13.getResources()
            android.util.DisplayMetrics r13 = r13.getDisplayMetrics()
            float r13 = r12.getDimension(r13)
            int r12 = r12.type
            r0 = 5
            if (r12 != r0) goto L_0x00ee
            r12 = 0
            int r12 = (r13 > r12 ? 1 : (r13 == r12 ? 0 : -1))
            if (r12 < 0) goto L_0x00ee
            ggt r12 = r4.f11293a
            gha r12 = r12.f11274a
            gha r12 = r12.mo6670a((float) r13)
            r4.mo3619a((p000.gha) r12)
        L_0x00ee:
            r11.f11164b = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.gfi.<init>(android.content.Context, int):void");
    }

    /* renamed from: b */
    public final C0394oj mo6550b() {
        C0394oj b = super.mo6550b();
        Window window = b.getWindow();
        View decorView = window.getDecorView();
        Drawable drawable = this.f11164b;
        if (drawable instanceof ggu) {
            ((ggu) drawable).mo6637b(C0340mj.m14721l(decorView));
        }
        Drawable drawable2 = this.f11164b;
        Rect rect = this.f11165c;
        window.setBackgroundDrawable(new InsetDrawable(drawable2, rect.left, rect.top, rect.right, rect.bottom));
        decorView.setOnTouchListener(new gfh(b, this.f11165c));
        return b;
    }

    /* renamed from: a */
    private static int m10198a(Context context) {
        TypedValue a = ggf.m10250a(context, (int) R.attr.materialAlertDialogTheme);
        if (a == null) {
            return 0;
        }
        return a.data;
    }

    /* renamed from: c */
    public final void mo6554c() {
        this.f15411a.f15350c = R.drawable.quantum_gm_ic_delete_forever_vd_theme_24;
    }

    /* renamed from: b */
    public final void mo6551b(int i) {
        super.mo9519a(i);
    }

    /* renamed from: c */
    public final void mo6557c(CharSequence charSequence) {
        super.mo9523a(charSequence);
    }

    /* renamed from: b */
    public final void mo6552b(int i, DialogInterface.OnClickListener onClickListener) {
        C0389oe oeVar = this.f15411a;
        oeVar.f15358k = oeVar.f15348a.getText(i);
        this.f15411a.f15360m = onClickListener;
    }

    /* renamed from: c */
    public final void mo6556c(int i, DialogInterface.OnClickListener onClickListener) {
        super.mo9520a(i, onClickListener);
    }

    /* renamed from: c */
    public final void mo6555c(int i) {
        C0389oe oeVar = this.f15411a;
        oeVar.f15352e = oeVar.f15348a.getText(i);
    }

    /* renamed from: d */
    public final void mo6558d(CharSequence charSequence) {
        super.mo9525b(charSequence);
    }

    /* renamed from: b */
    public final void mo6553b(View view) {
        super.mo9522a(view);
    }
}
