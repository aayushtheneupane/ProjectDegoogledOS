package p000;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.PopupWindow;
import com.google.android.apps.photosgo.R;

/* renamed from: rs */
/* compiled from: PG */
public class C0484rs {

    /* renamed from: a */
    public View f15818a;

    /* renamed from: b */
    public int f15819b;

    /* renamed from: c */
    public PopupWindow.OnDismissListener f15820c;

    /* renamed from: d */
    private final Context f15821d;

    /* renamed from: e */
    private final C0472rg f15822e;

    /* renamed from: f */
    private final boolean f15823f;

    /* renamed from: g */
    private final int f15824g;

    /* renamed from: h */
    private boolean f15825h;

    /* renamed from: i */
    private C0485rt f15826i;

    /* renamed from: j */
    private C0482rq f15827j;

    /* renamed from: k */
    private final PopupWindow.OnDismissListener f15828k;

    public C0484rs(Context context, C0472rg rgVar, View view, boolean z) {
        this(context, rgVar, view, z, R.attr.actionOverflowMenuStyle);
    }

    public C0484rs(Context context, C0472rg rgVar, View view, boolean z, int i) {
        this.f15819b = 8388611;
        this.f15828k = new C0483rr(this);
        this.f15821d = context;
        this.f15822e = rgVar;
        this.f15818a = view;
        this.f15823f = z;
        this.f15824g = i;
    }

    /* renamed from: c */
    public final void mo10000c() {
        if (mo10002e()) {
            this.f15827j.mo9810d();
        }
    }

    /* JADX WARNING: type inference failed for: r0v10, types: [ra] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.C0482rq mo9995a() {
        /*
            r11 = this;
            rq r0 = r11.f15827j
            if (r0 != 0) goto L_0x0071
            android.content.Context r0 = r11.f15821d
            java.lang.String r1 = "window"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.view.Display r0 = r0.getDefaultDisplay()
            android.graphics.Point r1 = new android.graphics.Point
            r1.<init>()
            int r2 = android.os.Build.VERSION.SDK_INT
            r0.getRealSize(r1)
            int r0 = r1.x
            int r1 = r1.y
            int r0 = java.lang.Math.min(r0, r1)
            android.content.Context r1 = r11.f15821d
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131165206(0x7f070016, float:1.7944623E38)
            int r1 = r1.getDimensionPixelSize(r2)
            if (r0 < r1) goto L_0x0041
            ra r0 = new ra
            android.content.Context r1 = r11.f15821d
            android.view.View r2 = r11.f15818a
            int r3 = r11.f15824g
            boolean r4 = r11.f15823f
            r0.<init>(r1, r2, r3, r4)
            goto L_0x0051
        L_0x0041:
            sb r0 = new sb
            android.content.Context r6 = r11.f15821d
            rg r7 = r11.f15822e
            android.view.View r8 = r11.f15818a
            int r9 = r11.f15824g
            boolean r10 = r11.f15823f
            r5 = r0
            r5.<init>(r6, r7, r8, r9, r10)
        L_0x0051:
            rg r1 = r11.f15822e
            r0.mo9803a((p000.C0472rg) r1)
            android.widget.PopupWindow$OnDismissListener r1 = r11.f15828k
            r0.mo9802a((android.widget.PopupWindow.OnDismissListener) r1)
            android.view.View r1 = r11.f15818a
            r0.mo9801a((android.view.View) r1)
            rt r1 = r11.f15826i
            r0.mo9787a((p000.C0485rt) r1)
            boolean r1 = r11.f15825h
            r0.mo9804a((boolean) r1)
            int r1 = r11.f15819b
            r0.mo9800a((int) r1)
            r11.f15827j = r0
        L_0x0071:
            rq r0 = r11.f15827j
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0484rs.mo9995a():rq");
    }

    /* renamed from: e */
    public final boolean mo10002e() {
        C0482rq rqVar = this.f15827j;
        return rqVar != null && rqVar.mo9811e();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo10001d() {
        this.f15827j = null;
        PopupWindow.OnDismissListener onDismissListener = this.f15820c;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    /* renamed from: a */
    public final void mo9998a(boolean z) {
        this.f15825h = z;
        C0482rq rqVar = this.f15827j;
        if (rqVar != null) {
            rqVar.mo9804a(z);
        }
    }

    /* renamed from: a */
    public final void mo9997a(C0485rt rtVar) {
        this.f15826i = rtVar;
        C0482rq rqVar = this.f15827j;
        if (rqVar != null) {
            rqVar.mo9787a(rtVar);
        }
    }

    /* renamed from: a */
    public final void mo9996a(int i, int i2, boolean z, boolean z2) {
        C0482rq a = mo9995a();
        a.mo9808b(z2);
        if (z) {
            if ((C0321lr.m14621a(this.f15819b, C0340mj.m14714f(this.f15818a)) & 7) == 5) {
                i -= this.f15818a.getWidth();
            }
            a.mo9807b(i);
            a.mo9809c(i2);
            int i3 = (int) ((this.f15821d.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            a.f15816g = new Rect(i - i3, i2 - i3, i + i3, i2 + i3);
        }
        a.mo9805ab();
    }

    /* renamed from: b */
    public final boolean mo9999b() {
        if (mo10002e()) {
            return true;
        }
        if (this.f15818a == null) {
            return false;
        }
        mo9996a(0, 0, false, false);
        return true;
    }
}
