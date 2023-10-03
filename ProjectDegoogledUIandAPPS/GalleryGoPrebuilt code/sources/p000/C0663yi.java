package p000;

import android.content.Context;
import android.graphics.PointF;
import android.support.p002v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* renamed from: yi */
/* compiled from: PG */
public class C0663yi {

    /* renamed from: a */
    public int f16342a;

    /* renamed from: b */
    public RecyclerView f16343b;

    /* renamed from: c */
    public C0647xt f16344c;

    /* renamed from: d */
    public boolean f16345d;

    /* renamed from: e */
    public boolean f16346e;

    /* renamed from: f */
    public View f16347f;

    /* renamed from: g */
    public boolean f16348g;

    /* renamed from: h */
    public final DecelerateInterpolator f16349h;

    /* renamed from: i */
    private final C0661yg f16350i;

    /* renamed from: j */
    private final LinearInterpolator f16351j;

    /* renamed from: k */
    private PointF f16352k;

    /* renamed from: l */
    private final DisplayMetrics f16353l;

    /* renamed from: m */
    private boolean f16354m;

    /* renamed from: n */
    private float f16355n;

    /* renamed from: o */
    private int f16356o;

    /* renamed from: p */
    private int f16357p;

    public C0663yi(Context context) {
        this.f16342a = -1;
        this.f16350i = new C0661yg();
        this.f16351j = new LinearInterpolator();
        this.f16349h = new DecelerateInterpolator();
        this.f16354m = false;
        this.f16356o = 0;
        this.f16357p = 0;
        this.f16353l = context.getResources().getDisplayMetrics();
    }

    /* renamed from: a */
    private static int m16100a(int i, int i2, int i3, int i4, int i5) {
        if (i5 == -1) {
            return i3 - i;
        }
        if (i5 != 0) {
            return i4 - i2;
        }
        int i6 = i3 - i;
        if (i6 > 0) {
            return i6;
        }
        int i7 = i4 - i2;
        if (i7 < 0) {
            return i7;
        }
        return 0;
    }

    /* renamed from: b */
    private static int m16101b(int i, int i2) {
        int i3 = i - i2;
        if (i * i3 <= 0) {
            return 0;
        }
        return i3;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public float mo4514a(DisplayMetrics displayMetrics) {
        return 25.0f / ((float) displayMetrics.densityDpi);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final int mo10625b(int i) {
        double a = (double) mo4648a(i);
        Double.isNaN(a);
        return (int) Math.ceil(a / 0.3356d);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo4648a(int i) {
        float abs = (float) Math.abs(i);
        if (!this.f16354m) {
            this.f16355n = mo4514a(this.f16353l);
            this.f16354m = true;
        }
        return (int) Math.ceil((double) (abs * this.f16355n));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo4649a(View view, C0661yg ygVar) {
        int i;
        PointF pointF = this.f16352k;
        int i2 = 1;
        int i3 = 0;
        int i4 = (pointF == null || pointF.x == 0.0f) ? 0 : this.f16352k.x > 0.0f ? 1 : -1;
        C0647xt xtVar = this.f16344c;
        if (xtVar == null || !xtVar.mo2620i()) {
            i = 0;
        } else {
            C0648xu xuVar = (C0648xu) view.getLayoutParams();
            i = m16100a(C0647xt.m15970d(view) - xuVar.leftMargin, C0647xt.m15973g(view) + xuVar.rightMargin, xtVar.mo10586s(), xtVar.f16310u - xtVar.mo10588u(), i4);
        }
        PointF pointF2 = this.f16352k;
        if (pointF2 == null || pointF2.y == 0.0f) {
            i2 = 0;
        } else if (this.f16352k.y <= 0.0f) {
            i2 = -1;
        }
        C0647xt xtVar2 = this.f16344c;
        if (xtVar2 != null && xtVar2.mo10477j()) {
            C0648xu xuVar2 = (C0648xu) view.getLayoutParams();
            i3 = m16100a(C0647xt.m15974h(view) - xuVar2.topMargin, C0647xt.m15969c(view) + xuVar2.bottomMargin, xtVar2.mo10587t(), xtVar2.f16311v - xtVar2.mo10589v(), i2);
        }
        int b = mo10625b((int) Math.sqrt((double) ((i * i) + (i3 * i3))));
        if (b > 0) {
            ygVar.mo10620a(-i, -i3, b, this.f16349h);
        }
    }

    public C0663yi() {
        this.f16342a = -1;
        this.f16350i = new C0661yg();
    }

    /* renamed from: c */
    private final PointF m16102c(int i) {
        C0647xt xtVar = this.f16344c;
        if (xtVar instanceof C0662yh) {
            return ((C0662yh) xtVar).mo10468c(i);
        }
        Log.w(RecyclerView.TAG, "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + C0662yh.class.getCanonicalName());
        return null;
    }

    /* renamed from: a */
    public final int mo10622a(View view) {
        return this.f16343b.getChildLayoutPosition(view);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10624a(int i, int i2) {
        PointF c;
        RecyclerView recyclerView = this.f16343b;
        if (this.f16342a == -1 || recyclerView == null) {
            mo10623a();
        }
        if (!(!this.f16345d || this.f16347f != null || this.f16344c == null || (c = m16102c(this.f16342a)) == null || (c.x == 0.0f && c.y == 0.0f))) {
            recyclerView.scrollStep((int) Math.signum(c.x), (int) Math.signum(c.y), (int[]) null);
        }
        this.f16345d = false;
        View view = this.f16347f;
        if (view != null) {
            if (mo10622a(view) == this.f16342a) {
                View view2 = this.f16347f;
                C0664yj yjVar = recyclerView.mState;
                mo4649a(view2, this.f16350i);
                this.f16350i.mo10621a(recyclerView);
                mo10623a();
            } else {
                Log.e(RecyclerView.TAG, "Passed over target position while smooth scrolling.");
                this.f16347f = null;
            }
        }
        if (this.f16346e) {
            C0664yj yjVar2 = recyclerView.mState;
            C0661yg ygVar = this.f16350i;
            if (this.f16343b.mLayout.mo10585r() != 0) {
                this.f16356o = m16101b(this.f16356o, i);
                int b = m16101b(this.f16357p, i2);
                this.f16357p = b;
                if (this.f16356o == 0 && b == 0) {
                    PointF c2 = m16102c(this.f16342a);
                    if (c2 == null || (c2.x == 0.0f && c2.y == 0.0f)) {
                        ygVar.f16335a = this.f16342a;
                        mo10623a();
                    } else {
                        float sqrt = (float) Math.sqrt((double) ((c2.x * c2.x) + (c2.y * c2.y)));
                        c2.x /= sqrt;
                        c2.y /= sqrt;
                        this.f16352k = c2;
                        this.f16356o = (int) (c2.x * 10000.0f);
                        this.f16357p = (int) (c2.y * 10000.0f);
                        ygVar.mo10620a((int) (((float) this.f16356o) * 1.2f), (int) (((float) this.f16357p) * 1.2f), (int) (((float) mo4648a(10000)) * 1.2f), this.f16351j);
                    }
                }
            } else {
                mo10623a();
            }
            C0661yg ygVar2 = this.f16350i;
            int i3 = ygVar2.f16335a;
            ygVar2.mo10621a(recyclerView);
            if (i3 >= 0 && this.f16346e) {
                this.f16345d = true;
                recyclerView.mViewFlinger.mo10630a();
            }
        }
    }

    /* renamed from: a */
    public final void mo10623a() {
        if (this.f16346e) {
            this.f16346e = false;
            this.f16357p = 0;
            this.f16356o = 0;
            this.f16352k = null;
            this.f16343b.mState.f16358a = -1;
            this.f16347f = null;
            this.f16342a = -1;
            this.f16345d = false;
            C0647xt xtVar = this.f16344c;
            if (xtVar.f16302m == this) {
                xtVar.f16302m = null;
            }
            this.f16344c = null;
            this.f16343b = null;
        }
    }
}
