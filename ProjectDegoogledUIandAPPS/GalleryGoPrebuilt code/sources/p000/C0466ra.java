package p000;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.transition.Transition;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: ra */
/* compiled from: PG */
public final class C0466ra extends C0482rq implements View.OnKeyListener, PopupWindow.OnDismissListener, C0486ru {

    /* renamed from: a */
    public final Handler f15709a;

    /* renamed from: b */
    public final List f15710b = new ArrayList();

    /* renamed from: c */
    public final ViewTreeObserver.OnGlobalLayoutListener f15711c = new C0460qv(this);

    /* renamed from: d */
    public View f15712d;

    /* renamed from: e */
    public ViewTreeObserver f15713e;

    /* renamed from: f */
    public boolean f15714f;

    /* renamed from: h */
    private final Context f15715h;

    /* renamed from: i */
    private final int f15716i;

    /* renamed from: j */
    private final int f15717j;

    /* renamed from: k */
    private final boolean f15718k;

    /* renamed from: l */
    private final List f15719l = new ArrayList();

    /* renamed from: m */
    private final View.OnAttachStateChangeListener f15720m = new C0461qw(this);

    /* renamed from: n */
    private final C0617wq f15721n = new C0463qy(this);

    /* renamed from: o */
    private int f15722o = 0;

    /* renamed from: p */
    private int f15723p = 0;

    /* renamed from: q */
    private View f15724q;

    /* renamed from: r */
    private int f15725r;

    /* renamed from: s */
    private boolean f15726s;

    /* renamed from: t */
    private boolean f15727t;

    /* renamed from: u */
    private int f15728u;

    /* renamed from: v */
    private int f15729v;

    /* renamed from: w */
    private boolean f15730w;

    /* renamed from: x */
    private boolean f15731x;

    /* renamed from: y */
    private C0485rt f15732y;

    /* renamed from: z */
    private PopupWindow.OnDismissListener f15733z;

    public C0466ra(Context context, View view, int i, boolean z) {
        this.f15715h = context;
        this.f15724q = view;
        this.f15717j = i;
        this.f15718k = z;
        this.f15730w = false;
        this.f15725r = m15197h();
        Resources resources = context.getResources();
        this.f15716i = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.f15709a = new Handler();
    }

    /* renamed from: a */
    public final boolean mo9788a() {
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public final boolean mo9812g() {
        return false;
    }

    /* renamed from: a */
    public final void mo9803a(C0472rg rgVar) {
        rgVar.mo9834a((C0486ru) this, this.f15715h);
        if (!mo9811e()) {
            this.f15719l.add(rgVar);
        } else {
            m15196c(rgVar);
        }
    }

    /* renamed from: d */
    public final void mo9810d() {
        int size = this.f15710b.size();
        if (size > 0) {
            C0464qz[] qzVarArr = (C0464qz[]) this.f15710b.toArray(new C0464qz[size]);
            for (int i = size - 1; i >= 0; i--) {
                C0464qz qzVar = qzVarArr[i];
                if (qzVar.f15706a.mo9811e()) {
                    qzVar.f15706a.mo9810d();
                }
            }
        }
    }

    /* renamed from: h */
    private final int m15197h() {
        return C0340mj.m14714f(this.f15724q) == 1 ? 0 : 1;
    }

    /* renamed from: ac */
    public final ListView mo9806ac() {
        if (this.f15710b.isEmpty()) {
            return null;
        }
        List list = this.f15710b;
        return ((C0464qz) list.get(list.size() - 1)).mo9799a();
    }

    /* renamed from: e */
    public final boolean mo9811e() {
        return this.f15710b.size() > 0 && ((C0464qz) this.f15710b.get(0)).f15706a.mo9811e();
    }

    /* renamed from: a */
    public final void mo9786a(C0472rg rgVar, boolean z) {
        int size = this.f15710b.size();
        int i = 0;
        while (true) {
            if (i < size) {
                if (rgVar == ((C0464qz) this.f15710b.get(i)).f15707b) {
                    break;
                }
                i++;
            } else {
                i = -1;
                break;
            }
        }
        if (i >= 0) {
            int i2 = i + 1;
            if (i2 < this.f15710b.size()) {
                ((C0464qz) this.f15710b.get(i2)).f15707b.mo9835a(false);
            }
            C0464qz qzVar = (C0464qz) this.f15710b.remove(i);
            qzVar.f15707b.mo9850b((C0486ru) this);
            if (this.f15714f) {
                C0619ws wsVar = qzVar.f15706a;
                int i3 = Build.VERSION.SDK_INT;
                wsVar.f16259q.setExitTransition((Transition) null);
                qzVar.f15706a.f16259q.setAnimationStyle(0);
            }
            qzVar.f15706a.mo9810d();
            int size2 = this.f15710b.size();
            if (size2 > 0) {
                this.f15725r = ((C0464qz) this.f15710b.get(size2 - 1)).f15708c;
            } else {
                this.f15725r = m15197h();
            }
            if (size2 == 0) {
                mo9810d();
                C0485rt rtVar = this.f15732y;
                if (rtVar != null) {
                    rtVar.mo9574a(rgVar, true);
                }
                ViewTreeObserver viewTreeObserver = this.f15713e;
                if (viewTreeObserver != null) {
                    if (viewTreeObserver.isAlive()) {
                        this.f15713e.removeGlobalOnLayoutListener(this.f15711c);
                    }
                    this.f15713e = null;
                }
                this.f15712d.removeOnAttachStateChangeListener(this.f15720m);
                this.f15733z.onDismiss();
            } else if (z) {
                ((C0464qz) this.f15710b.get(0)).f15707b.mo9835a(false);
            }
        }
    }

    public final void onDismiss() {
        C0464qz qzVar;
        int size = this.f15710b.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                qzVar = null;
                break;
            }
            qzVar = (C0464qz) this.f15710b.get(i);
            if (!qzVar.f15706a.mo9811e()) {
                break;
            }
            i++;
        }
        if (qzVar != null) {
            qzVar.f15707b.mo9835a(false);
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
        List list = this.f15710b;
        int size = list.size();
        int i = 0;
        while (i < size) {
            C0464qz qzVar = (C0464qz) list.get(i);
            i++;
            if (scVar == qzVar.f15707b) {
                qzVar.mo9799a().requestFocus();
                return true;
            }
        }
        if (!scVar.hasVisibleItems()) {
            return false;
        }
        mo9803a((C0472rg) scVar);
        C0485rt rtVar = this.f15732y;
        if (rtVar != null) {
            rtVar.mo9575a(scVar);
        }
        return true;
    }

    /* renamed from: a */
    public final void mo9801a(View view) {
        if (this.f15724q != view) {
            this.f15724q = view;
            this.f15723p = C0321lr.m14621a(this.f15722o, C0340mj.m14714f(view));
        }
    }

    /* renamed from: a */
    public final void mo9787a(C0485rt rtVar) {
        this.f15732y = rtVar;
    }

    /* renamed from: a */
    public final void mo9804a(boolean z) {
        this.f15730w = z;
    }

    /* renamed from: a */
    public final void mo9800a(int i) {
        if (this.f15722o != i) {
            this.f15722o = i;
            this.f15723p = C0321lr.m14621a(i, C0340mj.m14714f(this.f15724q));
        }
    }

    /* renamed from: b */
    public final void mo9807b(int i) {
        this.f15726s = true;
        this.f15728u = i;
    }

    /* renamed from: a */
    public final void mo9802a(PopupWindow.OnDismissListener onDismissListener) {
        this.f15733z = onDismissListener;
    }

    /* renamed from: b */
    public final void mo9808b(boolean z) {
        this.f15731x = z;
    }

    /* renamed from: c */
    public final void mo9809c(int i) {
        this.f15727t = true;
        this.f15729v = i;
    }

    /* renamed from: ab */
    public final void mo9805ab() {
        if (!mo9811e()) {
            List list = this.f15719l;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                m15196c((C0472rg) list.get(i));
            }
            this.f15719l.clear();
            View view = this.f15724q;
            this.f15712d = view;
            if (view != null) {
                ViewTreeObserver viewTreeObserver = this.f15713e;
                ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
                this.f15713e = viewTreeObserver2;
                if (viewTreeObserver == null) {
                    viewTreeObserver2.addOnGlobalLayoutListener(this.f15711c);
                }
                this.f15712d.addOnAttachStateChangeListener(this.f15720m);
            }
        }
    }

    /* renamed from: c */
    private final void m15196c(C0472rg rgVar) {
        View view;
        C0464qz qzVar;
        int i;
        MenuItem menuItem;
        View view2;
        int i2;
        C0469rd rdVar;
        int firstVisiblePosition;
        C0472rg rgVar2 = rgVar;
        LayoutInflater from = LayoutInflater.from(this.f15715h);
        C0469rd rdVar2 = new C0469rd(rgVar2, from, this.f15718k, R.layout.abc_cascading_menu_item_layout);
        if (!mo9811e() && this.f15730w) {
            rdVar2.f15743b = true;
        } else if (mo9811e()) {
            rdVar2.f15743b = C0482rq.m15299b(rgVar);
        }
        int a = m15297a(rdVar2, this.f15715h, this.f15716i);
        C0619ws wsVar = new C0619ws(this.f15715h, this.f15717j);
        wsVar.f16274b = this.f15721n;
        wsVar.f16255m = this;
        wsVar.mo10500a((PopupWindow.OnDismissListener) this);
        wsVar.f16254l = this.f15724q;
        wsVar.f16252j = this.f15723p;
        wsVar.mo10509k();
        wsVar.mo10508j();
        wsVar.mo10184a((ListAdapter) rdVar2);
        wsVar.mo10504d(a);
        wsVar.f16252j = this.f15723p;
        if (this.f15710b.size() > 0) {
            List list = this.f15710b;
            C0464qz qzVar2 = (C0464qz) list.get(list.size() - 1);
            C0472rg rgVar3 = qzVar2.f15707b;
            int size = rgVar3.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    menuItem = null;
                    break;
                }
                menuItem = rgVar3.getItem(i3);
                if (menuItem.hasSubMenu() && rgVar2 == menuItem.getSubMenu()) {
                    break;
                }
                i3++;
            }
            if (menuItem != null) {
                ListView a2 = qzVar2.mo9799a();
                ListAdapter adapter = a2.getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    i2 = headerViewListAdapter.getHeadersCount();
                    rdVar = (C0469rd) headerViewListAdapter.getWrappedAdapter();
                } else {
                    rdVar = (C0469rd) adapter;
                    i2 = 0;
                }
                int count = rdVar.getCount();
                int i4 = 0;
                while (true) {
                    if (i4 < count) {
                        if (menuItem == rdVar.getItem(i4)) {
                            break;
                        }
                        i4++;
                    } else {
                        i4 = -1;
                        break;
                    }
                }
                if (i4 != -1 && (firstVisiblePosition = (i4 + i2) - a2.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < a2.getChildCount()) {
                    view2 = a2.getChildAt(firstVisiblePosition);
                    view = view2;
                    qzVar = qzVar2;
                }
            }
            view2 = null;
            view = view2;
            qzVar = qzVar2;
        } else {
            qzVar = null;
            view = null;
        }
        if (view != null) {
            if (Build.VERSION.SDK_INT > 28) {
                wsVar.f16259q.setTouchModal(false);
            } else if (C0619ws.f16273a != null) {
                try {
                    C0619ws.f16273a.invoke(wsVar.f16259q, new Object[]{false});
                } catch (Exception e) {
                }
            }
            int i5 = Build.VERSION.SDK_INT;
            wsVar.f16259q.setEnterTransition((Transition) null);
            List list2 = this.f15710b;
            ListView a3 = ((C0464qz) list2.get(list2.size() - 1)).mo9799a();
            int[] iArr = new int[2];
            a3.getLocationOnScreen(iArr);
            Rect rect = new Rect();
            this.f15712d.getWindowVisibleDisplayFrame(rect);
            if (this.f15725r != 1 ? iArr[0] - a < 0 : iArr[0] + a3.getWidth() + a <= rect.right) {
                i = 1;
            } else {
                i = 0;
            }
            this.f15725r = i;
            int i6 = Build.VERSION.SDK_INT;
            wsVar.f16254l = view;
            if ((this.f15723p & 5) != 5) {
                if (i == 0) {
                    a = -a;
                } else {
                    a = view.getWidth();
                }
            } else if (i == 0) {
                a = -view.getWidth();
            }
            wsVar.f16249g = a;
            wsVar.f16251i = true;
            wsVar.f16250h = true;
            wsVar.mo10497a(0);
        } else {
            if (this.f15726s) {
                wsVar.f16249g = this.f15728u;
            }
            if (this.f15727t) {
                wsVar.mo10497a(this.f15729v);
            }
            wsVar.mo10498a(this.f15816g);
        }
        this.f15710b.add(new C0464qz(wsVar, rgVar2, this.f15725r));
        wsVar.mo9805ab();
        C0582vi viVar = wsVar.f16247e;
        viVar.setOnKeyListener(this);
        if (qzVar == null && this.f15731x && rgVar2.f15753e != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(R.layout.abc_popup_menu_header_item_layout, viVar, false);
            frameLayout.setEnabled(false);
            ((TextView) frameLayout.findViewById(16908310)).setText(rgVar2.f15753e);
            viVar.addHeaderView(frameLayout, (Object) null, false);
            wsVar.mo9805ab();
        }
    }

    /* renamed from: b */
    public final void mo9791b() {
        List list = this.f15710b;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            m15298a(((C0464qz) list.get(i)).mo9799a().getAdapter()).notifyDataSetChanged();
        }
    }
}
