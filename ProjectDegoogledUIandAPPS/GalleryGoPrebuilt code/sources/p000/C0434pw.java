package p000;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.p002v7.widget.ActionBarContainer;
import android.support.p002v7.widget.ActionBarContextView;
import android.support.p002v7.widget.ActionBarOverlayLayout;
import android.support.p002v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.google.android.apps.photosgo.R;
import java.util.ArrayList;

/* renamed from: pw */
/* compiled from: PG */
public final class C0434pw extends C0383nz implements C0504sl {

    /* renamed from: p */
    private static final Interpolator f15545p = new AccelerateInterpolator();

    /* renamed from: q */
    private static final Interpolator f15546q = new DecelerateInterpolator();

    /* renamed from: A */
    private final C0347mq f15547A = new C0432pu(this);

    /* renamed from: a */
    public Context f15548a;

    /* renamed from: b */
    public ActionBarOverlayLayout f15549b;

    /* renamed from: c */
    public ActionBarContainer f15550c;

    /* renamed from: d */
    public C0566ut f15551d;

    /* renamed from: e */
    public ActionBarContextView f15552e;

    /* renamed from: f */
    public View f15553f;

    /* renamed from: g */
    public C0433pv f15554g;

    /* renamed from: h */
    public C0443qe f15555h;

    /* renamed from: i */
    public C0442qd f15556i;

    /* renamed from: j */
    public int f15557j = 0;

    /* renamed from: k */
    public boolean f15558k = true;

    /* renamed from: l */
    public boolean f15559l;

    /* renamed from: m */
    public boolean f15560m;

    /* renamed from: n */
    public C0453qo f15561n;

    /* renamed from: o */
    public boolean f15562o;

    /* renamed from: r */
    private Context f15563r;

    /* renamed from: s */
    private boolean f15564s;

    /* renamed from: t */
    private boolean f15565t;

    /* renamed from: u */
    private final ArrayList f15566u = new ArrayList();

    /* renamed from: v */
    private boolean f15567v;

    /* renamed from: w */
    private boolean f15568w = true;

    /* renamed from: x */
    private boolean f15569x;

    /* renamed from: y */
    private final C0345mo f15570y = new C0430ps(this);

    /* renamed from: z */
    private final C0345mo f15571z = new C0431pt(this);

    /* renamed from: a */
    static boolean m15085a(boolean z, boolean z2) {
        return z2 || !z;
    }

    public C0434pw(Activity activity, boolean z) {
        new ArrayList();
        View decorView = activity.getWindow().getDecorView();
        m15084a(decorView);
        if (!z) {
            this.f15553f = decorView.findViewById(16908290);
        }
    }

    public C0434pw(Dialog dialog) {
        new ArrayList();
        m15084a(dialog.getWindow().getDecorView());
    }

    /* renamed from: g */
    public final void mo9657g(boolean z) {
        C0344mn mnVar;
        C0344mn mnVar2;
        long j;
        if (z) {
            if (!this.f15567v) {
                this.f15567v = true;
                mo9656f(false);
            }
        } else if (this.f15567v) {
            this.f15567v = false;
            mo9656f(false);
        }
        if (C0340mj.m14732w(this.f15550c)) {
            if (!z) {
                mnVar = this.f15551d.mo10323a(0, 200);
                mnVar2 = this.f15552e.mo10050a(8, 100);
            } else {
                C0344mn a = this.f15551d.mo10323a(4, 100);
                mnVar2 = a;
                mnVar = this.f15552e.mo10050a(0, 200);
            }
            C0453qo qoVar = new C0453qo();
            qoVar.f15665a.add(mnVar2);
            View view = (View) mnVar2.f15239a.get();
            if (view != null) {
                j = view.animate().getDuration();
            } else {
                j = 0;
            }
            View view2 = (View) mnVar.f15239a.get();
            if (view2 != null) {
                view2.animate().setStartDelay(j);
            }
            qoVar.f15665a.add(mnVar);
            qoVar.mo9727a();
        } else if (!z) {
            this.f15551d.mo10330b(0);
            this.f15552e.setVisibility(8);
        } else {
            this.f15551d.mo10330b(4);
            this.f15552e.setVisibility(0);
        }
    }

    /* renamed from: f */
    public final boolean mo9500f() {
        C0566ut utVar = this.f15551d;
        if (utVar == null || !utVar.mo10332c()) {
            return false;
        }
        this.f15551d.mo10333d();
        return true;
    }

    /* renamed from: e */
    public final void mo9498e(boolean z) {
        if (z != this.f15565t) {
            this.f15565t = z;
            int size = this.f15566u.size();
            for (int i = 0; i < size; i++) {
                ((C0382ny) this.f15566u.get(i)).mo9484a();
            }
        }
    }

    /* renamed from: a */
    public final int mo9485a() {
        return this.f15551d.mo10343n();
    }

    /* renamed from: b */
    public final Context mo9491b() {
        if (this.f15563r == null) {
            TypedValue typedValue = new TypedValue();
            this.f15548a.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.f15563r = new ContextThemeWrapper(this.f15548a, i);
            } else {
                this.f15563r = this.f15548a;
            }
        }
        return this.f15563r;
    }

    /* renamed from: a */
    private final void m15084a(View view) {
        C0566ut utVar;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R.id.decor_content_parent);
        this.f15549b = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.f914g = this;
            if (actionBarOverlayLayout.getWindowToken() != null) {
                ((C0434pw) actionBarOverlayLayout.f914g).f15557j = actionBarOverlayLayout.f908a;
                int i = actionBarOverlayLayout.f913f;
                if (i != 0) {
                    actionBarOverlayLayout.onWindowSystemUiVisibilityChanged(i);
                    C0340mj.m14724o(actionBarOverlayLayout);
                }
            }
        }
        View findViewById = view.findViewById(R.id.action_bar);
        if (findViewById instanceof C0566ut) {
            utVar = (C0566ut) findViewById;
        } else if (findViewById instanceof Toolbar) {
            utVar = ((Toolbar) findViewById).mo1104h();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Can't make a decor toolbar out of ");
            sb.append(findViewById != null ? findViewById.getClass().getSimpleName() : "null");
            throw new IllegalStateException(sb.toString());
        }
        this.f15551d = utVar;
        this.f15552e = (ActionBarContextView) view.findViewById(R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(R.id.action_bar_container);
        this.f15550c = actionBarContainer;
        C0566ut utVar2 = this.f15551d;
        if (utVar2 == null || this.f15552e == null || actionBarContainer == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f15548a = utVar2.mo10329b();
        if ((this.f15551d.mo10343n() & 4) != 0) {
            this.f15564s = true;
        }
        C0441qc a = C0441qc.m15109a(this.f15548a);
        int i2 = a.f15602a.getApplicationInfo().targetSdkVersion;
        this.f15551d.mo10345p();
        m15086h(a.mo9693b());
        TypedArray obtainStyledAttributes = this.f15548a.obtainStyledAttributes((AttributeSet) null, C0435px.f15573a, R.attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(14, false)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.f15549b;
            if (actionBarOverlayLayout2.f910c) {
                this.f15562o = true;
                actionBarOverlayLayout2.mo814a(true);
            } else {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(12, 0);
        if (dimensionPixelSize != 0) {
            C0340mj.m14688a((View) this.f15550c, (float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: h */
    public final void mo9502h() {
        m15086h(C0441qc.m15109a(this.f15548a).mo9693b());
    }

    /* renamed from: a */
    public final boolean mo9489a(int i, KeyEvent keyEvent) {
        C0472rg rgVar;
        int i2;
        C0433pv pvVar = this.f15554g;
        if (pvVar == null || (rgVar = pvVar.f15540a) == null) {
            return false;
        }
        if (keyEvent != null) {
            i2 = keyEvent.getDeviceId();
        } else {
            i2 = -1;
        }
        boolean z = true;
        if (KeyCharacterMap.load(i2).getKeyboardType() == 1) {
            z = false;
        }
        rgVar.setQwertyMode(z);
        return rgVar.performShortcut(i, keyEvent, 0);
    }

    /* renamed from: c */
    public final void mo9494c(boolean z) {
        if (!this.f15564s) {
            mo9488a(z);
        }
    }

    /* renamed from: a */
    public final void mo9488a(boolean z) {
        m15083a(!z ? 0 : 4, 4);
    }

    /* renamed from: a */
    private final void m15083a(int i, int i2) {
        int n = this.f15551d.mo10343n();
        if ((i2 & 4) != 0) {
            this.f15564s = true;
        }
        this.f15551d.mo10324a((i & i2) | ((i2 ^ -1) & n));
    }

    /* renamed from: i */
    public final void mo9503i() {
        m15083a(2, 2);
    }

    /* renamed from: b */
    public final void mo9493b(boolean z) {
        m15083a(!z ? 0 : 8, 8);
    }

    /* renamed from: h */
    private final void m15086h(boolean z) {
        if (z) {
            this.f15551d.mo10344o();
        } else {
            this.f15551d.mo10344o();
        }
        this.f15551d.mo10346q();
        this.f15551d.mo10347r();
        this.f15549b.f911d = false;
    }

    /* renamed from: j */
    public final void mo9504j() {
        this.f15551d.mo10325a((Drawable) null);
    }

    /* renamed from: d */
    public final void mo9496d(boolean z) {
        C0453qo qoVar;
        this.f15569x = z;
        if (!z && (qoVar = this.f15561n) != null) {
            qoVar.mo9731b();
        }
    }

    /* renamed from: a */
    public final void mo9487a(CharSequence charSequence) {
        this.f15551d.mo10331b(charSequence);
    }

    /* renamed from: b */
    public final void mo9492b(CharSequence charSequence) {
        this.f15551d.mo10328a(charSequence);
    }

    /* renamed from: a */
    public final C0443qe mo9486a(C0442qd qdVar) {
        C0433pv pvVar = this.f15554g;
        if (pvVar != null) {
            pvVar.mo9650c();
        }
        this.f15549b.mo814a(false);
        this.f15552e.mo794a();
        C0433pv pvVar2 = new C0433pv(this, this.f15552e.getContext(), qdVar);
        pvVar2.f15540a.mo9859e();
        try {
            if (!pvVar2.f15541b.mo9577a((C0443qe) pvVar2, (Menu) pvVar2.f15540a)) {
                return null;
            }
            this.f15554g = pvVar2;
            pvVar2.mo9651d();
            this.f15552e.mo798a((C0443qe) pvVar2);
            mo9657g(true);
            this.f15552e.sendAccessibilityEvent(32);
            return pvVar2;
        } finally {
            pvVar2.f15540a.mo9860f();
        }
    }

    /* renamed from: f */
    public final void mo9656f(boolean z) {
        View view;
        View view2;
        View view3;
        if (!m15085a(this.f15560m, this.f15567v)) {
            if (this.f15568w) {
                this.f15568w = false;
                C0453qo qoVar = this.f15561n;
                if (qoVar != null) {
                    qoVar.mo9731b();
                }
                if (this.f15557j == 0 && (this.f15569x || z)) {
                    this.f15550c.setAlpha(1.0f);
                    this.f15550c.mo781a(true);
                    C0453qo qoVar2 = new C0453qo();
                    float f = (float) (-this.f15550c.getHeight());
                    if (z) {
                        int[] iArr = {0, 0};
                        this.f15550c.getLocationInWindow(iArr);
                        f -= (float) iArr[1];
                    }
                    C0344mn k = C0340mj.m14720k(this.f15550c);
                    k.mo9404b(f);
                    k.mo9403a(this.f15547A);
                    qoVar2.mo9729a(k);
                    if (this.f15558k && (view3 = this.f15553f) != null) {
                        C0344mn k2 = C0340mj.m14720k(view3);
                        k2.mo9404b(f);
                        qoVar2.mo9729a(k2);
                    }
                    qoVar2.mo9728a(f15545p);
                    qoVar2.mo9732c();
                    qoVar2.mo9730a(this.f15570y);
                    this.f15561n = qoVar2;
                    qoVar2.mo9727a();
                    return;
                }
                this.f15570y.mo9406b();
            }
        } else if (!this.f15568w) {
            this.f15568w = true;
            C0453qo qoVar3 = this.f15561n;
            if (qoVar3 != null) {
                qoVar3.mo9731b();
            }
            this.f15550c.setVisibility(0);
            if (this.f15557j == 0 && (this.f15569x || z)) {
                this.f15550c.setTranslationY(0.0f);
                float f2 = (float) (-this.f15550c.getHeight());
                if (z) {
                    int[] iArr2 = {0, 0};
                    this.f15550c.getLocationInWindow(iArr2);
                    f2 -= (float) iArr2[1];
                }
                this.f15550c.setTranslationY(f2);
                C0453qo qoVar4 = new C0453qo();
                C0344mn k3 = C0340mj.m14720k(this.f15550c);
                k3.mo9404b(0.0f);
                k3.mo9403a(this.f15547A);
                qoVar4.mo9729a(k3);
                if (this.f15558k && (view2 = this.f15553f) != null) {
                    view2.setTranslationY(f2);
                    C0344mn k4 = C0340mj.m14720k(this.f15553f);
                    k4.mo9404b(0.0f);
                    qoVar4.mo9729a(k4);
                }
                qoVar4.mo9728a(f15546q);
                qoVar4.mo9732c();
                qoVar4.mo9730a(this.f15571z);
                this.f15561n = qoVar4;
                qoVar4.mo9727a();
            } else {
                this.f15550c.setAlpha(1.0f);
                this.f15550c.setTranslationY(0.0f);
                if (this.f15558k && (view = this.f15553f) != null) {
                    view.setTranslationY(0.0f);
                }
                this.f15571z.mo9406b();
            }
            ActionBarOverlayLayout actionBarOverlayLayout = this.f15549b;
            if (actionBarOverlayLayout != null) {
                C0340mj.m14724o(actionBarOverlayLayout);
            }
        }
    }
}
