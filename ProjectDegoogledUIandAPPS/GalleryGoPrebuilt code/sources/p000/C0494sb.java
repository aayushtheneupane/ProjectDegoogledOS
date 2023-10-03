package p000;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* renamed from: sb */
/* compiled from: PG */
final class C0494sb extends C0482rq implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, View.OnKeyListener, C0486ru {

    /* renamed from: a */
    public final C0619ws f15834a;

    /* renamed from: b */
    public final ViewTreeObserver.OnGlobalLayoutListener f15835b = new C0491rz(this);

    /* renamed from: c */
    public View f15836c;

    /* renamed from: d */
    public ViewTreeObserver f15837d;

    /* renamed from: e */
    private final Context f15838e;

    /* renamed from: f */
    private final C0472rg f15839f;

    /* renamed from: h */
    private final C0469rd f15840h;

    /* renamed from: i */
    private final boolean f15841i;

    /* renamed from: j */
    private final int f15842j;

    /* renamed from: k */
    private final int f15843k;

    /* renamed from: l */
    private final View.OnAttachStateChangeListener f15844l = new C0493sa(this);

    /* renamed from: m */
    private PopupWindow.OnDismissListener f15845m;

    /* renamed from: n */
    private View f15846n;

    /* renamed from: o */
    private C0485rt f15847o;

    /* renamed from: p */
    private boolean f15848p;

    /* renamed from: q */
    private boolean f15849q;

    /* renamed from: r */
    private int f15850r;

    /* renamed from: s */
    private int f15851s = 0;

    /* renamed from: t */
    private boolean f15852t;

    public C0494sb(Context context, C0472rg rgVar, View view, int i, boolean z) {
        this.f15838e = context;
        this.f15839f = rgVar;
        this.f15841i = z;
        this.f15840h = new C0469rd(rgVar, LayoutInflater.from(context), this.f15841i, R.layout.abc_popup_menu_item_layout);
        this.f15843k = i;
        Resources resources = context.getResources();
        this.f15842j = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.f15846n = view;
        this.f15834a = new C0619ws(this.f15838e, this.f15843k);
        rgVar.mo9834a((C0486ru) this, context);
    }

    /* renamed from: a */
    public final void mo9803a(C0472rg rgVar) {
    }

    /* renamed from: a */
    public final boolean mo9788a() {
        return false;
    }

    /* renamed from: ac */
    public final ListView mo9806ac() {
        return this.f15834a.f16247e;
    }

    /* renamed from: d */
    public final void mo9810d() {
        if (mo9811e()) {
            this.f15834a.mo9810d();
        }
    }

    /* renamed from: e */
    public final boolean mo9811e() {
        return !this.f15848p && this.f15834a.mo9811e();
    }

    /* renamed from: a */
    public final void mo9786a(C0472rg rgVar, boolean z) {
        if (rgVar == this.f15839f) {
            mo9810d();
            C0485rt rtVar = this.f15847o;
            if (rtVar != null) {
                rtVar.mo9574a(rgVar, z);
            }
        }
    }

    public final void onDismiss() {
        this.f15848p = true;
        this.f15839f.close();
        ViewTreeObserver viewTreeObserver = this.f15837d;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f15837d = this.f15836c.getViewTreeObserver();
            }
            this.f15837d.removeGlobalOnLayoutListener(this.f15835b);
            this.f15837d = null;
        }
        this.f15836c.removeOnAttachStateChangeListener(this.f15844l);
        PopupWindow.OnDismissListener onDismissListener = this.f15845m;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        mo9810d();
        return true;
    }

    /* renamed from: a */
    public final boolean mo9790a(C0495sc scVar) {
        if (scVar.hasVisibleItems()) {
            C0484rs rsVar = new C0484rs(this.f15838e, scVar, this.f15836c, this.f15841i, this.f15843k);
            rsVar.mo9997a(this.f15847o);
            rsVar.mo9998a(C0482rq.m15299b((C0472rg) scVar));
            rsVar.f15820c = this.f15845m;
            this.f15845m = null;
            this.f15839f.mo9835a(false);
            C0619ws wsVar = this.f15834a;
            int i = wsVar.f16249g;
            int c = wsVar.mo10503c();
            if ((Gravity.getAbsoluteGravity(this.f15851s, C0340mj.m14714f(this.f15846n)) & 7) == 5) {
                i += this.f15846n.getWidth();
            }
            if (!rsVar.mo10002e()) {
                if (rsVar.f15818a != null) {
                    rsVar.mo9996a(i, c, true, true);
                }
            }
            C0485rt rtVar = this.f15847o;
            if (rtVar != null) {
                rtVar.mo9575a(scVar);
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public final void mo9801a(View view) {
        this.f15846n = view;
    }

    /* renamed from: a */
    public final void mo9787a(C0485rt rtVar) {
        this.f15847o = rtVar;
    }

    /* renamed from: a */
    public final void mo9804a(boolean z) {
        this.f15840h.f15743b = z;
    }

    /* renamed from: a */
    public final void mo9800a(int i) {
        this.f15851s = i;
    }

    /* renamed from: b */
    public final void mo9807b(int i) {
        this.f15834a.f16249g = i;
    }

    /* renamed from: a */
    public final void mo9802a(PopupWindow.OnDismissListener onDismissListener) {
        this.f15845m = onDismissListener;
    }

    /* renamed from: b */
    public final void mo9808b(boolean z) {
        this.f15852t = z;
    }

    /* renamed from: c */
    public final void mo9809c(int i) {
        this.f15834a.mo10497a(i);
    }

    /* renamed from: ab */
    public final void mo9805ab() {
        View view;
        if (mo9811e()) {
            return;
        }
        if (this.f15848p || (view = this.f15846n) == null) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
        this.f15836c = view;
        this.f15834a.mo10500a((PopupWindow.OnDismissListener) this);
        C0619ws wsVar = this.f15834a;
        wsVar.f16255m = this;
        wsVar.mo10509k();
        View view2 = this.f15836c;
        ViewTreeObserver viewTreeObserver = this.f15837d;
        ViewTreeObserver viewTreeObserver2 = view2.getViewTreeObserver();
        this.f15837d = viewTreeObserver2;
        if (viewTreeObserver == null) {
            viewTreeObserver2.addOnGlobalLayoutListener(this.f15835b);
        }
        view2.addOnAttachStateChangeListener(this.f15844l);
        C0619ws wsVar2 = this.f15834a;
        wsVar2.f16254l = view2;
        wsVar2.f16252j = this.f15851s;
        if (!this.f15849q) {
            this.f15850r = m15297a(this.f15840h, this.f15838e, this.f15842j);
            this.f15849q = true;
        }
        this.f15834a.mo10504d(this.f15850r);
        this.f15834a.mo10508j();
        this.f15834a.mo10498a(this.f15816g);
        this.f15834a.mo9805ab();
        C0582vi viVar = this.f15834a.f16247e;
        viVar.setOnKeyListener(this);
        if (this.f15852t && this.f15839f.f15753e != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f15838e).inflate(R.layout.abc_popup_menu_header_item_layout, viVar, false);
            TextView textView = (TextView) frameLayout.findViewById(16908310);
            if (textView != null) {
                textView.setText(this.f15839f.f15753e);
            }
            frameLayout.setEnabled(false);
            viVar.addHeaderView(frameLayout, (Object) null, false);
        }
        this.f15834a.mo10184a((ListAdapter) this.f15840h);
        this.f15834a.mo9805ab();
    }

    /* renamed from: b */
    public final void mo9791b() {
        this.f15849q = false;
        C0469rd rdVar = this.f15840h;
        if (rdVar != null) {
            rdVar.notifyDataSetChanged();
        }
    }
}
