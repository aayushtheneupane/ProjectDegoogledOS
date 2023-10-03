package p000;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.p002v7.view.menu.ExpandedMenuView;
import android.support.p002v7.widget.ActionBarContextView;
import android.support.p002v7.widget.ContentFrameLayout;
import android.support.p002v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import java.lang.ref.WeakReference;
import java.util.Map;

/* renamed from: pe */
/* compiled from: PG */
public final class C0416pe extends C0397om implements LayoutInflater.Factory2, C0470re {

    /* renamed from: y */
    private static final Map f15456y = new C0290kn();

    /* renamed from: z */
    private static final int[] f15457z = {16842836};

    /* renamed from: A */
    private final Object f15458A;

    /* renamed from: B */
    private C0407ow f15459B;

    /* renamed from: C */
    private C0383nz f15460C;

    /* renamed from: D */
    private MenuInflater f15461D;

    /* renamed from: E */
    private CharSequence f15462E;

    /* renamed from: F */
    private C0404ot f15463F;

    /* renamed from: G */
    private C0415pd f15464G;

    /* renamed from: H */
    private boolean f15465H;

    /* renamed from: I */
    private TextView f15466I;

    /* renamed from: J */
    private boolean f15467J;

    /* renamed from: K */
    private boolean f15468K;

    /* renamed from: L */
    private boolean f15469L;

    /* renamed from: M */
    private boolean f15470M;

    /* renamed from: N */
    private boolean f15471N;

    /* renamed from: O */
    private C0414pc[] f15472O;

    /* renamed from: P */
    private boolean f15473P;

    /* renamed from: Q */
    private boolean f15474Q;

    /* renamed from: R */
    private boolean f15475R;

    /* renamed from: S */
    private int f15476S;

    /* renamed from: T */
    private int f15477T;

    /* renamed from: U */
    private boolean f15478U;

    /* renamed from: V */
    private boolean f15479V;

    /* renamed from: W */
    private C0410oz f15480W;

    /* renamed from: X */
    private C0410oz f15481X;

    /* renamed from: Y */
    private final Runnable f15482Y;

    /* renamed from: Z */
    private boolean f15483Z;

    /* renamed from: aa */
    private C0420pi f15484aa;

    /* renamed from: d */
    public final Context f15485d;

    /* renamed from: e */
    public Window f15486e;

    /* renamed from: f */
    public final C0396ol f15487f;

    /* renamed from: g */
    public C0565us f15488g;

    /* renamed from: h */
    public C0443qe f15489h;

    /* renamed from: i */
    public ActionBarContextView f15490i;

    /* renamed from: j */
    public PopupWindow f15491j;

    /* renamed from: k */
    public Runnable f15492k;

    /* renamed from: l */
    public C0344mn f15493l;

    /* renamed from: m */
    public boolean f15494m;

    /* renamed from: n */
    public ViewGroup f15495n;

    /* renamed from: o */
    public View f15496o;

    /* renamed from: p */
    public boolean f15497p;

    /* renamed from: q */
    public boolean f15498q;

    /* renamed from: r */
    public boolean f15499r;

    /* renamed from: s */
    public C0414pc f15500s;

    /* renamed from: t */
    public boolean f15501t;

    /* renamed from: u */
    public boolean f15502u;

    /* renamed from: v */
    public int f15503v;

    /* renamed from: w */
    public Rect f15504w;

    /* renamed from: x */
    public Rect f15505x;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    /* renamed from: i */
    public final int mo9563i() {
        return this.f15476S;
    }

    public C0416pe(Activity activity, C0396ol olVar) {
        this(activity, (Window) null, olVar, activity);
    }

    public C0416pe(Dialog dialog, C0396ol olVar) {
        this(dialog.getContext(), dialog.getWindow(), olVar, dialog);
    }

    private C0416pe(Context context, Window window, C0396ol olVar, Object obj) {
        Integer num;
        C0395ok okVar = null;
        this.f15493l = null;
        this.f15494m = true;
        this.f15476S = -100;
        this.f15482Y = new C0398on(this);
        this.f15485d = context;
        this.f15487f = olVar;
        this.f15458A = obj;
        if (this.f15476S == -100 && (obj instanceof Dialog)) {
            while (true) {
                if (context != null) {
                    if (context instanceof C0395ok) {
                        okVar = (C0395ok) context;
                        break;
                    } else if (!(context instanceof ContextWrapper)) {
                        break;
                    } else {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                } else {
                    break;
                }
            }
            if (okVar != null) {
                this.f15476S = okVar.mo9537i().mo9563i();
            }
        }
        if (this.f15476S == -100 && (num = (Integer) f15456y.get(this.f15458A.getClass())) != null) {
            this.f15476S = num.intValue();
            f15456y.remove(this.f15458A.getClass());
        }
        if (window != null) {
            m14980a(window);
        }
        C0529tj.m15438a();
    }

    /* renamed from: b */
    public final void mo9554b(View view, ViewGroup.LayoutParams layoutParams) {
        m14987w();
        ((ViewGroup) this.f15495n.findViewById(16908290)).addView(view, layoutParams);
        this.f15459B.f15671a.onContentChanged();
    }

    /* renamed from: j */
    public final void mo9564j() {
        m14981a(true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00ef A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0182  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01a4  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m14981a(boolean r11) {
        /*
            r10 = this;
            boolean r0 = r10.f15501t
            if (r0 != 0) goto L_0x01a8
            int r0 = r10.f15476S
            r1 = -100
            if (r0 != r1) goto L_0x000c
            int r0 = p000.C0397om.f15415a
        L_0x000c:
            r2 = 3
            r3 = 2
            r4 = -1
            r5 = 1
            if (r0 == r1) goto L_0x004c
            if (r0 == r4) goto L_0x0049
            if (r0 == 0) goto L_0x002d
            if (r0 == r5) goto L_0x0049
            if (r0 == r3) goto L_0x0049
            if (r0 != r2) goto L_0x0025
            oz r1 = r10.m14978A()
            int r4 = r1.mo9590a()
            goto L_0x004d
        L_0x0025:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate."
            r11.<init>(r0)
            throw r11
        L_0x002d:
            int r1 = android.os.Build.VERSION.SDK_INT
            android.content.Context r1 = r10.f15485d
            java.lang.Class<android.app.UiModeManager> r6 = android.app.UiModeManager.class
            java.lang.Object r1 = r1.getSystemService(r6)
            android.app.UiModeManager r1 = (android.app.UiModeManager) r1
            int r1 = r1.getNightMode()
            if (r1 == 0) goto L_0x0048
            oz r1 = r10.m14990z()
            int r4 = r1.mo9590a()
            goto L_0x004d
        L_0x0048:
            goto L_0x004c
        L_0x0049:
            r4 = r0
            goto L_0x004d
        L_0x004c:
        L_0x004d:
            android.content.Context r1 = r10.f15485d
            android.content.Context r1 = r1.getApplicationContext()
            android.content.res.Resources r1 = r1.getResources()
            android.content.res.Configuration r1 = r1.getConfiguration()
            int r1 = r1.uiMode
            r1 = r1 & 48
            if (r4 == r5) goto L_0x0068
            if (r4 == r3) goto L_0x0065
            r3 = r1
            goto L_0x006c
        L_0x0065:
            r3 = 32
            goto L_0x006a
        L_0x0068:
            r3 = 16
        L_0x006a:
        L_0x006c:
            boolean r4 = r10.f15479V
            r6 = 0
            if (r4 == 0) goto L_0x0072
            goto L_0x00a6
        L_0x0072:
            java.lang.Object r4 = r10.f15458A
            boolean r4 = r4 instanceof android.app.Activity
            if (r4 == 0) goto L_0x00a6
            android.content.Context r4 = r10.f15485d
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            if (r4 == 0) goto L_0x00a3
            android.content.ComponentName r7 = new android.content.ComponentName     // Catch:{ NameNotFoundException -> 0x009f }
            android.content.Context r8 = r10.f15485d     // Catch:{ NameNotFoundException -> 0x009f }
            java.lang.Object r9 = r10.f15458A     // Catch:{ NameNotFoundException -> 0x009f }
            java.lang.Class r9 = r9.getClass()     // Catch:{ NameNotFoundException -> 0x009f }
            r7.<init>(r8, r9)     // Catch:{ NameNotFoundException -> 0x009f }
            android.content.pm.ActivityInfo r4 = r4.getActivityInfo(r7, r6)     // Catch:{ NameNotFoundException -> 0x009f }
            if (r4 == 0) goto L_0x009b
            int r4 = r4.configChanges     // Catch:{ NameNotFoundException -> 0x009f }
            r4 = r4 & 512(0x200, float:7.175E-43)
            if (r4 == 0) goto L_0x009b
            r4 = 1
            goto L_0x009c
        L_0x009b:
            r4 = 0
        L_0x009c:
            r10.f15478U = r4     // Catch:{ NameNotFoundException -> 0x009f }
            goto L_0x00a6
        L_0x009f:
            r4 = move-exception
            r10.f15478U = r6
            goto L_0x00a6
        L_0x00a3:
            r4 = 0
            goto L_0x00ab
        L_0x00a6:
            r10.f15479V = r5
            boolean r4 = r10.f15478U
        L_0x00ab:
            if (r3 != r1) goto L_0x00ae
        L_0x00ad:
            goto L_0x00dd
        L_0x00ae:
            if (r4 != 0) goto L_0x00ad
            int r1 = android.os.Build.VERSION.SDK_INT
            boolean r1 = r10.f15474Q
            if (r1 != 0) goto L_0x00ad
            java.lang.Object r1 = r10.f15458A
            boolean r1 = r1 instanceof android.view.ContextThemeWrapper
            if (r1 == 0) goto L_0x00dc
            android.content.res.Configuration r1 = new android.content.res.Configuration
            r1.<init>()
            int r7 = r1.uiMode
            r7 = r7 & -49
            r7 = r7 | r3
            r1.uiMode = r7
            java.lang.Object r7 = r10.f15458A     // Catch:{ IllegalStateException -> 0x00d2 }
            android.view.ContextThemeWrapper r7 = (android.view.ContextThemeWrapper) r7     // Catch:{ IllegalStateException -> 0x00d2 }
            r7.applyOverrideConfiguration(r1)     // Catch:{ IllegalStateException -> 0x00d2 }
            r6 = 1
            goto L_0x00dd
        L_0x00d2:
            r1 = move-exception
            java.lang.String r7 = "AppCompatDelegate"
            java.lang.String r8 = "updateForNightMode. Calling applyOverrideConfiguration() failed with an exception. Will fall back to using Resources.updateConfiguration()"
            android.util.Log.e(r7, r8, r1)
            goto L_0x00dd
        L_0x00dc:
            goto L_0x00ad
        L_0x00dd:
            android.content.Context r1 = r10.f15485d
            android.content.res.Resources r1 = r1.getResources()
            android.content.res.Configuration r1 = r1.getConfiguration()
            int r1 = r1.uiMode
            r1 = r1 & 48
            if (r6 == 0) goto L_0x00ef
            goto L_0x017c
        L_0x00ef:
            if (r1 == r3) goto L_0x0118
            if (r11 == 0) goto L_0x0118
            if (r4 != 0) goto L_0x0118
            boolean r11 = r10.f15474Q
            if (r11 == 0) goto L_0x0118
            int r11 = android.os.Build.VERSION.SDK_INT
            java.lang.Object r11 = r10.f15458A
            boolean r6 = r11 instanceof android.app.Activity
            if (r6 != 0) goto L_0x0102
            goto L_0x0118
        L_0x0102:
            android.app.Activity r11 = (android.app.Activity) r11
            int r1 = android.os.Build.VERSION.SDK_INT
            r3 = 28
            if (r1 < r3) goto L_0x010e
            r11.recreate()
            goto L_0x017c
        L_0x010e:
            boolean r1 = p000.C0132et.m8136a(r11)
            if (r1 != 0) goto L_0x017c
            r11.recreate()
            goto L_0x017c
        L_0x0118:
            if (r1 == r3) goto L_0x0184
            android.content.Context r11 = r10.f15485d
            android.content.res.Resources r11 = r11.getResources()
            android.content.res.Configuration r1 = new android.content.res.Configuration
            android.content.res.Configuration r6 = r11.getConfiguration()
            r1.<init>(r6)
            android.content.res.Configuration r6 = r11.getConfiguration()
            int r6 = r6.uiMode
            r6 = r6 & -49
            r3 = r3 | r6
            r1.uiMode = r3
            r3 = 0
            r11.updateConfiguration(r1, r3)
            int r11 = android.os.Build.VERSION.SDK_INT
            int r11 = r10.f15477T
            if (r11 == 0) goto L_0x0150
            android.content.Context r3 = r10.f15485d
            r3.setTheme(r11)
            int r11 = android.os.Build.VERSION.SDK_INT
            android.content.Context r11 = r10.f15485d
            android.content.res.Resources$Theme r11 = r11.getTheme()
            int r3 = r10.f15477T
            r11.applyStyle(r3, r5)
        L_0x0150:
            if (r4 == 0) goto L_0x017c
            java.lang.Object r11 = r10.f15458A
            boolean r3 = r11 instanceof android.app.Activity
            if (r3 == 0) goto L_0x017c
            android.app.Activity r11 = (android.app.Activity) r11
            boolean r3 = r11 instanceof p000.C0681z
            if (r3 == 0) goto L_0x0175
            r3 = r11
            z r3 = (p000.C0681z) r3
            w r3 = r3.mo5ad()
            v r3 = r3.mo61a()
            v r4 = p000.C0573v.STARTED
            boolean r3 = r3.mo10359a(r4)
            if (r3 == 0) goto L_0x017c
            r11.onConfigurationChanged(r1)
            goto L_0x017c
        L_0x0175:
            boolean r3 = r10.f15475R
            if (r3 == 0) goto L_0x017c
            r11.onConfigurationChanged(r1)
        L_0x017c:
            java.lang.Object r11 = r10.f15458A
            boolean r1 = r11 instanceof p000.C0395ok
            if (r1 == 0) goto L_0x0184
            ok r11 = (p000.C0395ok) r11
        L_0x0184:
            if (r0 == 0) goto L_0x0199
            oz r11 = r10.f15480W
            if (r11 != 0) goto L_0x018b
            goto L_0x018e
        L_0x018b:
            r11.mo9595e()
        L_0x018e:
            if (r0 == r2) goto L_0x0191
            goto L_0x01a0
        L_0x0191:
            oz r11 = r10.m14978A()
            r11.mo9594d()
            return
        L_0x0199:
            oz r11 = r10.m14990z()
            r11.mo9594d()
        L_0x01a0:
            oz r11 = r10.f15481X
            if (r11 == 0) goto L_0x01a7
            r11.mo9595e()
        L_0x01a7:
            return
        L_0x01a8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0416pe.m14981a(boolean):void");
    }

    /* renamed from: k */
    public final void mo9565k() {
        m14981a(false);
        this.f15474Q = true;
    }

    /* renamed from: a */
    private final void m14980a(Window window) {
        if (this.f15486e == null) {
            Window.Callback callback = window.getCallback();
            if (!(callback instanceof C0407ow)) {
                C0407ow owVar = new C0407ow(this, callback);
                this.f15459B = owVar;
                window.setCallback(owVar);
                C0684zc a = C0684zc.m16191a(this.f15485d, (AttributeSet) null, f15457z);
                Drawable b = a.mo10727b(0);
                if (b != null) {
                    window.setBackgroundDrawable(b);
                }
                a.mo10724a();
                this.f15486e = window;
                return;
            }
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo9601a(int i, C0414pc pcVar, Menu menu) {
        if (menu == null) {
            if (pcVar == null) {
                C0414pc[] pcVarArr = this.f15472O;
                if (i < pcVarArr.length) {
                    pcVar = pcVarArr[i];
                }
            }
            if (pcVar != null) {
                menu = pcVar.f15445h;
            }
        }
        if ((pcVar == null || pcVar.f15450m) && !this.f15501t) {
            this.f15459B.f15671a.onPanelClosed(i, menu);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo9608b(C0472rg rgVar) {
        if (!this.f15471N) {
            this.f15471N = true;
            this.f15488g.mo828h();
            Window.Callback p = mo9613p();
            if (p != null && !this.f15501t) {
                p.onPanelClosed(108, rgVar);
            }
            this.f15471N = false;
        }
    }

    /* renamed from: u */
    private final void m14985u() {
        C0410oz ozVar = this.f15480W;
        if (ozVar != null) {
            ozVar.mo9595e();
        }
        C0410oz ozVar2 = this.f15481X;
        if (ozVar2 != null) {
            ozVar2.mo9595e();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo9602a(C0414pc pcVar, boolean z) {
        ViewGroup viewGroup;
        C0565us usVar;
        if (z && pcVar.f15438a == 0 && (usVar = this.f15488g) != null && usVar.mo816c()) {
            mo9608b(pcVar.f15445h);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f15485d.getSystemService("window");
        if (!(windowManager == null || !pcVar.f15450m || (viewGroup = pcVar.f15442e) == null)) {
            windowManager.removeView(viewGroup);
            if (z) {
                mo9601a(pcVar.f15438a, pcVar, (Menu) null);
            }
        }
        pcVar.f15448k = false;
        pcVar.f15449l = false;
        pcVar.f15450m = false;
        pcVar.f15443f = null;
        pcVar.f15452o = true;
        if (this.f15500s == pcVar) {
            this.f15500s = null;
        }
    }

    /* renamed from: a */
    private final View m14979a(View view, String str, Context context, AttributeSet attributeSet) {
        if (this.f15484aa == null) {
            String string = this.f15485d.obtainStyledAttributes(C0435px.f15582j).getString(114);
            if (string == null || C0420pi.class.getName().equals(string)) {
                this.f15484aa = new C0420pi();
            } else {
                try {
                    this.f15484aa = (C0420pi) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    "Failed to instantiate custom view inflater " + string + ". Falling back to default.";
                    this.f15484aa = new C0420pi();
                }
            }
        }
        return this.f15484aa.createView(view, str, context, attributeSet, false, false, true, false);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c0  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo9604a(android.view.KeyEvent r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.f15458A
            boolean r1 = r0 instanceof p000.C0322ls
            r2 = 1
            if (r1 == 0) goto L_0x0008
        L_0x0007:
            goto L_0x000d
        L_0x0008:
            boolean r0 = r0 instanceof p000.C0418pg
            if (r0 == 0) goto L_0x001d
            goto L_0x0007
        L_0x000d:
            android.view.Window r0 = r6.f15486e
            android.view.View r0 = r0.getDecorView()
            if (r0 == 0) goto L_0x001d
            boolean r0 = p000.C0323lt.m14636a(r0, r7)
            if (r0 != 0) goto L_0x001c
            goto L_0x001d
        L_0x001c:
            return r2
        L_0x001d:
            int r0 = r7.getKeyCode()
            r1 = 82
            if (r0 != r1) goto L_0x0032
            ow r0 = r6.f15459B
            android.view.Window$Callback r0 = r0.f15671a
            boolean r0 = r0.dispatchKeyEvent(r7)
            if (r0 != 0) goto L_0x0030
            goto L_0x0032
        L_0x0030:
            return r2
        L_0x0032:
            int r0 = r7.getKeyCode()
            int r3 = r7.getAction()
            r4 = 4
            r5 = 0
            if (r3 == 0) goto L_0x00f7
            if (r0 == r4) goto L_0x00c7
            if (r0 == r1) goto L_0x0044
            goto L_0x00fb
        L_0x0044:
            qe r0 = r6.f15489h
            if (r0 != 0) goto L_0x0111
            pc r0 = r6.mo9610g(r5)
            us r1 = r6.f15488g
            if (r1 == 0) goto L_0x0084
            boolean r1 = r1.mo815b()
            if (r1 == 0) goto L_0x0084
            android.content.Context r1 = r6.f15485d
            android.view.ViewConfiguration r1 = android.view.ViewConfiguration.get(r1)
            boolean r1 = r1.hasPermanentMenuKey()
            if (r1 != 0) goto L_0x0084
            us r1 = r6.f15488g
            boolean r1 = r1.mo816c()
            if (r1 == 0) goto L_0x0071
            us r7 = r6.f15488g
            boolean r7 = r7.mo821f()
            goto L_0x00a9
        L_0x0071:
            boolean r1 = r6.f15501t
            if (r1 != 0) goto L_0x0111
            boolean r7 = r6.mo9606a((p000.C0414pc) r0, (android.view.KeyEvent) r7)
            if (r7 == 0) goto L_0x0082
            us r7 = r6.f15488g
            boolean r7 = r7.mo820e()
            goto L_0x00a9
        L_0x0082:
            goto L_0x0111
        L_0x0084:
            boolean r1 = r0.f15450m
            if (r1 != 0) goto L_0x00a4
            boolean r3 = r0.f15449l
            if (r3 == 0) goto L_0x008d
            goto L_0x00a4
        L_0x008d:
            boolean r1 = r0.f15448k
            if (r1 == 0) goto L_0x0111
            boolean r1 = r0.f15453p
            if (r1 == 0) goto L_0x00a0
            r0.f15448k = r5
            boolean r1 = r6.mo9606a((p000.C0414pc) r0, (android.view.KeyEvent) r7)
            if (r1 == 0) goto L_0x009e
            goto L_0x00a0
        L_0x009e:
            goto L_0x0111
        L_0x00a0:
            r6.m14982b((p000.C0414pc) r0, (android.view.KeyEvent) r7)
            goto L_0x00ab
        L_0x00a4:
            r6.mo9602a((p000.C0414pc) r0, (boolean) r2)
            r7 = r1
        L_0x00a9:
            if (r7 == 0) goto L_0x00c6
        L_0x00ab:
            android.content.Context r7 = r6.f15485d
            java.lang.String r0 = "audio"
            java.lang.Object r7 = r7.getSystemService(r0)
            android.media.AudioManager r7 = (android.media.AudioManager) r7
            if (r7 != 0) goto L_0x00c0
            java.lang.String r7 = "AppCompatDelegate"
            java.lang.String r0 = "Couldn't get audio manager"
            android.util.Log.w(r7, r0)
            goto L_0x0112
        L_0x00c0:
            r7.playSoundEffect(r5)
            goto L_0x0112
        L_0x00c6:
            goto L_0x0111
        L_0x00c7:
            boolean r7 = r6.f15473P
            r6.f15473P = r5
            pc r0 = r6.mo9610g(r5)
            if (r0 != 0) goto L_0x00d2
            goto L_0x00de
        L_0x00d2:
            boolean r1 = r0.f15450m
            if (r1 == 0) goto L_0x00de
            if (r7 == 0) goto L_0x00d9
            goto L_0x0111
        L_0x00d9:
            r6.mo9602a((p000.C0414pc) r0, (boolean) r2)
            return r2
        L_0x00de:
            qe r7 = r6.f15489h
            if (r7 != 0) goto L_0x00f2
            nz r7 = r6.mo9546a()
            if (r7 != 0) goto L_0x00e9
            goto L_0x00fb
        L_0x00e9:
            boolean r7 = r7.mo9500f()
            if (r7 != 0) goto L_0x00f0
            goto L_0x00fb
        L_0x00f0:
            return r2
        L_0x00f2:
            r7.mo9650c()
            goto L_0x0112
        L_0x00f7:
            if (r0 == r4) goto L_0x0113
            if (r0 == r1) goto L_0x00fd
        L_0x00fb:
            r2 = 0
            goto L_0x0112
        L_0x00fd:
            int r0 = r7.getRepeatCount()
            if (r0 != 0) goto L_0x0110
            pc r0 = r6.mo9610g(r5)
            boolean r1 = r0.f15450m
            if (r1 == 0) goto L_0x010c
            goto L_0x0111
        L_0x010c:
            r6.mo9606a((p000.C0414pc) r0, (android.view.KeyEvent) r7)
            return r2
        L_0x0110:
        L_0x0111:
        L_0x0112:
            return r2
        L_0x0113:
            int r7 = r7.getFlags()
            r7 = r7 & 128(0x80, float:1.794E-43)
            if (r7 == 0) goto L_0x011c
            goto L_0x011d
        L_0x011c:
            r2 = 0
        L_0x011d:
            r6.f15473P = r2
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0416pe.mo9604a(android.view.KeyEvent):boolean");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final void mo9609f(int i) {
        C0414pc g;
        C0414pc g2 = mo9610g(i);
        if (g2.f15445h != null) {
            Bundle bundle = new Bundle();
            g2.f15445h.mo9831a(bundle);
            if (bundle.size() > 0) {
                g2.f15454q = bundle;
            }
            g2.f15445h.mo9859e();
            g2.f15445h.clear();
        }
        g2.f15453p = true;
        g2.f15452o = true;
        if ((i == 108 || i == 0) && this.f15488g != null && (g = mo9610g(0)) != null) {
            g.f15448k = false;
            mo9606a(g, (KeyEvent) null);
        }
    }

    /* renamed from: s */
    public final void mo9616s() {
        C0344mn mnVar = this.f15493l;
        if (mnVar != null) {
            mnVar.mo9399a();
        }
    }

    /* renamed from: w */
    private final void m14987w() {
        ViewGroup viewGroup;
        if (!this.f15465H) {
            TypedArray obtainStyledAttributes = this.f15485d.obtainStyledAttributes(C0435px.f15582j);
            if (obtainStyledAttributes.hasValue(115)) {
                if (obtainStyledAttributes.getBoolean(124, false)) {
                    mo9559e(1);
                } else if (obtainStyledAttributes.getBoolean(115, false)) {
                    mo9559e(108);
                }
                if (obtainStyledAttributes.getBoolean(116, false)) {
                    mo9559e(109);
                }
                if (obtainStyledAttributes.getBoolean(117, false)) {
                    mo9559e(10);
                }
                this.f15499r = obtainStyledAttributes.getBoolean(0, false);
                obtainStyledAttributes.recycle();
                m14986v();
                this.f15486e.getDecorView();
                LayoutInflater from = LayoutInflater.from(this.f15485d);
                if (this.f15470M) {
                    if (!this.f15498q) {
                        viewGroup = (ViewGroup) from.inflate(R.layout.abc_screen_simple, (ViewGroup) null);
                    } else {
                        viewGroup = (ViewGroup) from.inflate(R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null);
                    }
                    int i = Build.VERSION.SDK_INT;
                    C0340mj.m14699a((View) viewGroup, (C0329lz) new C0399oo(this));
                } else if (this.f15499r) {
                    viewGroup = (ViewGroup) from.inflate(R.layout.abc_dialog_title_material, (ViewGroup) null);
                    this.f15469L = false;
                    this.f15497p = false;
                } else if (this.f15497p) {
                    TypedValue typedValue = new TypedValue();
                    this.f15485d.getTheme().resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                    viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new C0445qg(this.f15485d, typedValue.resourceId) : this.f15485d).inflate(R.layout.abc_screen_toolbar, (ViewGroup) null);
                    C0565us usVar = (C0565us) viewGroup.findViewById(R.id.decor_content_parent);
                    this.f15488g = usVar;
                    usVar.mo812a(mo9613p());
                    if (this.f15469L) {
                        this.f15488g.mo810a(109);
                    }
                    if (this.f15467J) {
                        this.f15488g.mo810a(2);
                    }
                    if (this.f15468K) {
                        this.f15488g.mo810a(5);
                    }
                } else {
                    viewGroup = null;
                }
                if (viewGroup != null) {
                    if (this.f15488g == null) {
                        this.f15466I = (TextView) viewGroup.findViewById(R.id.title);
                    }
                    C0703zv.m16281b(viewGroup);
                    ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R.id.action_bar_activity_content);
                    ViewGroup viewGroup2 = (ViewGroup) this.f15486e.findViewById(16908290);
                    if (viewGroup2 != null) {
                        while (viewGroup2.getChildCount() > 0) {
                            View childAt = viewGroup2.getChildAt(0);
                            viewGroup2.removeViewAt(0);
                            contentFrameLayout.addView(childAt);
                        }
                        viewGroup2.setId(-1);
                        contentFrameLayout.setId(16908290);
                        if (viewGroup2 instanceof FrameLayout) {
                            ((FrameLayout) viewGroup2).setForeground((Drawable) null);
                        }
                    }
                    this.f15486e.setContentView(viewGroup);
                    contentFrameLayout.f956h = new C0400op(this);
                    this.f15495n = viewGroup;
                    CharSequence x = m14988x();
                    if (!TextUtils.isEmpty(x)) {
                        C0565us usVar2 = this.f15488g;
                        if (usVar2 == null) {
                            C0383nz nzVar = this.f15460C;
                            if (nzVar == null) {
                                TextView textView = this.f15466I;
                                if (textView != null) {
                                    textView.setText(x);
                                }
                            } else {
                                nzVar.mo9492b(x);
                            }
                        } else {
                            usVar2.mo813a(x);
                        }
                    }
                    ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.f15495n.findViewById(16908290);
                    View decorView = this.f15486e.getDecorView();
                    contentFrameLayout2.f955g.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
                    if (C0340mj.m14732w(contentFrameLayout2)) {
                        contentFrameLayout2.requestLayout();
                    }
                    TypedArray obtainStyledAttributes2 = this.f15485d.obtainStyledAttributes(C0435px.f15582j);
                    if (contentFrameLayout2.f949a == null) {
                        contentFrameLayout2.f949a = new TypedValue();
                    }
                    obtainStyledAttributes2.getValue(122, contentFrameLayout2.f949a);
                    if (contentFrameLayout2.f950b == null) {
                        contentFrameLayout2.f950b = new TypedValue();
                    }
                    obtainStyledAttributes2.getValue(123, contentFrameLayout2.f950b);
                    if (obtainStyledAttributes2.hasValue(120)) {
                        if (contentFrameLayout2.f951c == null) {
                            contentFrameLayout2.f951c = new TypedValue();
                        }
                        obtainStyledAttributes2.getValue(120, contentFrameLayout2.f951c);
                    }
                    if (obtainStyledAttributes2.hasValue(121)) {
                        if (contentFrameLayout2.f952d == null) {
                            contentFrameLayout2.f952d = new TypedValue();
                        }
                        obtainStyledAttributes2.getValue(121, contentFrameLayout2.f952d);
                    }
                    if (obtainStyledAttributes2.hasValue(118)) {
                        if (contentFrameLayout2.f953e == null) {
                            contentFrameLayout2.f953e = new TypedValue();
                        }
                        obtainStyledAttributes2.getValue(118, contentFrameLayout2.f953e);
                    }
                    if (obtainStyledAttributes2.hasValue(119)) {
                        if (contentFrameLayout2.f954f == null) {
                            contentFrameLayout2.f954f = new TypedValue();
                        }
                        obtainStyledAttributes2.getValue(119, contentFrameLayout2.f954f);
                    }
                    obtainStyledAttributes2.recycle();
                    contentFrameLayout2.requestLayout();
                    this.f15465H = true;
                    C0414pc g = mo9610g(0);
                    if (this.f15501t) {
                        return;
                    }
                    if (g == null || g.f15445h == null) {
                        m14983h(108);
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.f15497p + ", windowActionBarOverlay: " + this.f15469L + ", android:windowIsFloating: " + this.f15499r + ", windowActionModeOverlay: " + this.f15498q + ", windowNoTitle: " + this.f15470M + " }");
            }
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
    }

    /* renamed from: v */
    private final void m14986v() {
        if (this.f15486e == null) {
            Object obj = this.f15458A;
            if (obj instanceof Activity) {
                m14980a(((Activity) obj).getWindow());
            }
        }
        if (this.f15486e == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final C0414pc mo9600a(Menu menu) {
        C0414pc[] pcVarArr = this.f15472O;
        int length = pcVarArr != null ? pcVarArr.length : 0;
        for (int i = 0; i < length; i++) {
            C0414pc pcVar = pcVarArr[i];
            if (pcVar != null && pcVar.f15445h == menu) {
                return pcVar;
            }
        }
        return null;
    }

    /* renamed from: b */
    public final View mo9553b(int i) {
        m14987w();
        return this.f15486e.findViewById(i);
    }

    /* renamed from: q */
    public final Context mo9614q() {
        C0383nz a = mo9546a();
        Context b = a != null ? a.mo9491b() : null;
        return b == null ? this.f15485d : b;
    }

    /* renamed from: A */
    private final C0410oz m14978A() {
        if (this.f15481X == null) {
            this.f15481X = new C0408ox(this, this.f15485d);
        }
        return this.f15481X;
    }

    /* renamed from: z */
    private final C0410oz m14990z() {
        if (this.f15480W == null) {
            Context context = this.f15485d;
            if (C0429pr.f15533a == null) {
                Context applicationContext = context.getApplicationContext();
                C0429pr.f15533a = new C0429pr(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.f15480W = new C0412pa(this, C0429pr.f15533a);
        }
        return this.f15480W;
    }

    /* renamed from: b */
    public final MenuInflater mo9552b() {
        Context context;
        if (this.f15461D == null) {
            m14984t();
            C0383nz nzVar = this.f15460C;
            if (nzVar == null) {
                context = this.f15485d;
            } else {
                context = nzVar.mo9491b();
            }
            this.f15461D = new C0451qm(context);
        }
        return this.f15461D;
    }

    /* renamed from: g */
    public final C0414pc mo9610g(int i) {
        C0414pc[] pcVarArr = this.f15472O;
        if (pcVarArr == null || pcVarArr.length <= i) {
            C0414pc[] pcVarArr2 = new C0414pc[(i + 1)];
            if (pcVarArr != null) {
                System.arraycopy(pcVarArr, 0, pcVarArr2, 0, pcVarArr.length);
            }
            this.f15472O = pcVarArr2;
            pcVarArr = pcVarArr2;
        }
        C0414pc pcVar = pcVarArr[i];
        if (pcVar != null) {
            return pcVar;
        }
        C0414pc pcVar2 = new C0414pc(i);
        pcVarArr[i] = pcVar2;
        return pcVar2;
    }

    /* renamed from: a */
    public final C0383nz mo9546a() {
        m14984t();
        return this.f15460C;
    }

    /* renamed from: x */
    private final CharSequence m14988x() {
        Object obj = this.f15458A;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.f15462E;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: p */
    public final Window.Callback mo9613p() {
        return this.f15486e.getCallback();
    }

    /* renamed from: t */
    private final void m14984t() {
        m14987w();
        if (this.f15497p && this.f15460C == null) {
            Object obj = this.f15458A;
            if (obj instanceof Activity) {
                this.f15460C = new C0434pw((Activity) obj, this.f15469L);
            } else if (obj instanceof Dialog) {
                this.f15460C = new C0434pw((Dialog) obj);
            }
            C0383nz nzVar = this.f15460C;
            if (nzVar != null) {
                nzVar.mo9494c(this.f15483Z);
            }
        }
    }

    /* renamed from: h */
    public final void mo9562h() {
        LayoutInflater from = LayoutInflater.from(this.f15485d);
        if (from.getFactory() == null) {
            C0350mt.m14761a(from, (LayoutInflater.Factory2) this);
        } else {
            from.getFactory2();
        }
    }

    /* renamed from: f */
    public final void mo9560f() {
        C0383nz a = mo9546a();
        if (a == null || !a.mo9499e()) {
            m14983h(0);
        }
    }

    /* renamed from: h */
    private final void m14983h(int i) {
        this.f15503v = (1 << i) | this.f15503v;
        if (!this.f15502u) {
            C0340mj.m14695a(this.f15486e.getDecorView(), this.f15482Y);
            this.f15502u = true;
        }
    }

    /* renamed from: o */
    public final void mo9569o() {
        C0383nz a;
        if (this.f15497p && this.f15465H && (a = mo9546a()) != null) {
            a.mo9502h();
        }
        C0529tj.m15440b().mo10132a(this.f15485d);
        m14981a(false);
    }

    /* renamed from: l */
    public final void mo9566l() {
        String str;
        this.f15474Q = true;
        m14981a(false);
        m14986v();
        Object obj = this.f15458A;
        if (obj instanceof Activity) {
            try {
                str = C0350mt.m14770b((Activity) obj);
            } catch (IllegalArgumentException e) {
                str = null;
            }
            if (str != null) {
                C0383nz nzVar = this.f15460C;
                if (nzVar == null) {
                    this.f15483Z = true;
                } else {
                    nzVar.mo9494c(true);
                }
            }
        }
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return m14979a(view, str, context, attributeSet);
    }

    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return m14979a((View) null, str, context, attributeSet);
    }

    /* renamed from: g */
    public final void mo9561g() {
        m14921a((C0397om) this);
        if (this.f15502u) {
            this.f15486e.getDecorView().removeCallbacks(this.f15482Y);
        }
        this.f15475R = false;
        this.f15501t = true;
        C0383nz nzVar = this.f15460C;
        if (nzVar != null) {
            nzVar.mo9501g();
        }
        m14985u();
    }

    /* renamed from: a */
    public final boolean mo9607a(C0472rg rgVar, MenuItem menuItem) {
        C0414pc a;
        Window.Callback p = mo9613p();
        if (p == null || this.f15501t || (a = mo9600a((Menu) rgVar.mo9868j())) == null) {
            return false;
        }
        return p.onMenuItemSelected(a.f15438a, menuItem);
    }

    /* renamed from: a */
    public final void mo9603a(C0472rg rgVar) {
        C0565us usVar = this.f15488g;
        if (usVar != null && usVar.mo815b() && (!ViewConfiguration.get(this.f15485d).hasPermanentMenuKey() || this.f15488g.mo818d())) {
            Window.Callback p = mo9613p();
            if (this.f15488g.mo816c()) {
                this.f15488g.mo821f();
                if (!this.f15501t) {
                    p.onPanelClosed(108, mo9610g(0).f15445h);
                }
            } else if (p != null && !this.f15501t) {
                if (this.f15502u && (1 & this.f15503v) != 0) {
                    this.f15486e.getDecorView().removeCallbacks(this.f15482Y);
                    this.f15482Y.run();
                }
                C0414pc g = mo9610g(0);
                C0472rg rgVar2 = g.f15445h;
                if (rgVar2 != null && !g.f15453p && p.onPreparePanel(0, g.f15444g, rgVar2)) {
                    p.onMenuOpened(108, g.f15445h);
                    this.f15488g.mo820e();
                }
            }
        } else {
            C0414pc g2 = mo9610g(0);
            g2.f15452o = true;
            mo9602a(g2, false);
            m14982b(g2, (KeyEvent) null);
        }
    }

    /* renamed from: m */
    public final void mo9567m() {
        m14987w();
    }

    /* renamed from: e */
    public final void mo9558e() {
        C0383nz a = mo9546a();
        if (a != null) {
            a.mo9496d(true);
        }
    }

    /* renamed from: n */
    public final void mo9568n() {
        if (this.f15476S != -100) {
            f15456y.put(this.f15458A.getClass(), Integer.valueOf(this.f15476S));
        }
    }

    /* renamed from: c */
    public final void mo9555c() {
        this.f15475R = true;
        mo9564j();
        synchronized (C0397om.f15417c) {
            C0397om.m14922b((C0397om) this);
            C0397om.f15416b.add(new WeakReference(this));
        }
    }

    /* renamed from: d */
    public final void mo9557d() {
        this.f15475R = false;
        m14921a((C0397om) this);
        C0383nz a = mo9546a();
        if (a != null) {
            a.mo9496d(false);
        }
        if (this.f15458A instanceof Dialog) {
            m14985u();
        }
    }

    /* renamed from: b */
    private final void m14982b(C0414pc pcVar, KeyEvent keyEvent) {
        int i;
        ExpandedMenuView expandedMenuView;
        ViewGroup.LayoutParams layoutParams;
        if (!pcVar.f15450m && !this.f15501t) {
            if (pcVar.f15438a != 0 || (this.f15485d.getResources().getConfiguration().screenLayout & 15) != 4) {
                Window.Callback p = mo9613p();
                if (p != null && !p.onMenuOpened(pcVar.f15438a, pcVar.f15445h)) {
                    mo9602a(pcVar, true);
                    return;
                }
                WindowManager windowManager = (WindowManager) this.f15485d.getSystemService("window");
                if (windowManager != null && mo9606a(pcVar, keyEvent)) {
                    ViewGroup viewGroup = pcVar.f15442e;
                    if (viewGroup == null || pcVar.f15452o) {
                        if (viewGroup == null) {
                            Context q = mo9614q();
                            TypedValue typedValue = new TypedValue();
                            Resources.Theme newTheme = q.getResources().newTheme();
                            newTheme.setTo(q.getTheme());
                            newTheme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
                            if (typedValue.resourceId != 0) {
                                newTheme.applyStyle(typedValue.resourceId, true);
                            }
                            newTheme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
                            if (typedValue.resourceId != 0) {
                                newTheme.applyStyle(typedValue.resourceId, true);
                            } else {
                                newTheme.applyStyle(2131952143, true);
                            }
                            C0445qg qgVar = new C0445qg(q, 0);
                            qgVar.getTheme().setTo(newTheme);
                            pcVar.f15447j = qgVar;
                            TypedArray obtainStyledAttributes = qgVar.obtainStyledAttributes(C0435px.f15582j);
                            pcVar.f15439b = obtainStyledAttributes.getResourceId(84, 0);
                            pcVar.f15441d = obtainStyledAttributes.getResourceId(1, 0);
                            obtainStyledAttributes.recycle();
                            pcVar.f15442e = new C0413pb(this, pcVar.f15447j);
                            pcVar.f15440c = 81;
                            if (pcVar.f15442e == null) {
                                return;
                            }
                        } else if (pcVar.f15452o && viewGroup.getChildCount() > 0) {
                            pcVar.f15442e.removeAllViews();
                        }
                        View view = pcVar.f15444g;
                        if (view != null) {
                            pcVar.f15443f = view;
                        } else if (pcVar.f15445h != null) {
                            if (this.f15464G == null) {
                                this.f15464G = new C0415pd(this);
                            }
                            C0415pd pdVar = this.f15464G;
                            if (pcVar.f15445h != null) {
                                if (pcVar.f15446i == null) {
                                    pcVar.f15446i = new C0468rc(pcVar.f15447j);
                                    C0468rc rcVar = pcVar.f15446i;
                                    rcVar.f15739d = pdVar;
                                    pcVar.f15445h.mo9833a((C0486ru) rcVar);
                                }
                                C0468rc rcVar2 = pcVar.f15446i;
                                ViewGroup viewGroup2 = pcVar.f15442e;
                                if (rcVar2.f15738c == null) {
                                    rcVar2.f15738c = (ExpandedMenuView) rcVar2.f15736a.inflate(R.layout.abc_expanded_menu_layout, viewGroup2, false);
                                    if (rcVar2.f15740e == null) {
                                        rcVar2.f15740e = new C0467rb(rcVar2);
                                    }
                                    rcVar2.f15738c.setAdapter(rcVar2.f15740e);
                                    rcVar2.f15738c.setOnItemClickListener(rcVar2);
                                }
                                expandedMenuView = rcVar2.f15738c;
                            } else {
                                expandedMenuView = null;
                            }
                            pcVar.f15443f = expandedMenuView;
                            if (pcVar.f15443f == null) {
                                return;
                            }
                        } else {
                            return;
                        }
                        if (pcVar.f15443f == null) {
                            return;
                        }
                        if (pcVar.f15444g != null || pcVar.f15446i.mo9821c().getCount() > 0) {
                            ViewGroup.LayoutParams layoutParams2 = pcVar.f15443f.getLayoutParams();
                            if (layoutParams2 == null) {
                                layoutParams2 = new ViewGroup.LayoutParams(-2, -2);
                            }
                            pcVar.f15442e.setBackgroundResource(pcVar.f15439b);
                            ViewParent parent = pcVar.f15443f.getParent();
                            if (parent instanceof ViewGroup) {
                                ((ViewGroup) parent).removeView(pcVar.f15443f);
                            }
                            pcVar.f15442e.addView(pcVar.f15443f, layoutParams2);
                            if (!pcVar.f15443f.hasFocus()) {
                                pcVar.f15443f.requestFocus();
                                i = -2;
                                pcVar.f15449l = false;
                                WindowManager.LayoutParams layoutParams3 = new WindowManager.LayoutParams(i, -2, 0, 0, 1002, 8519680, -3);
                                layoutParams3.gravity = pcVar.f15440c;
                                layoutParams3.windowAnimations = pcVar.f15441d;
                                windowManager.addView(pcVar.f15442e, layoutParams3);
                                pcVar.f15450m = true;
                            }
                        } else {
                            return;
                        }
                    } else {
                        View view2 = pcVar.f15444g;
                        if (!(view2 == null || (layoutParams = view2.getLayoutParams()) == null || layoutParams.width != -1)) {
                            i = -1;
                            pcVar.f15449l = false;
                            WindowManager.LayoutParams layoutParams32 = new WindowManager.LayoutParams(i, -2, 0, 0, 1002, 8519680, -3);
                            layoutParams32.gravity = pcVar.f15440c;
                            layoutParams32.windowAnimations = pcVar.f15441d;
                            windowManager.addView(pcVar.f15442e, layoutParams32);
                            pcVar.f15450m = true;
                        }
                    }
                    i = -2;
                    pcVar.f15449l = false;
                    WindowManager.LayoutParams layoutParams322 = new WindowManager.LayoutParams(i, -2, 0, 0, 1002, 8519680, -3);
                    layoutParams322.gravity = pcVar.f15440c;
                    layoutParams322.windowAnimations = pcVar.f15441d;
                    windowManager.addView(pcVar.f15442e, layoutParams322);
                    pcVar.f15450m = true;
                }
            }
        }
    }

    /* renamed from: a */
    public final boolean mo9605a(C0414pc pcVar, int i, KeyEvent keyEvent) {
        C0472rg rgVar;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((!pcVar.f15448k && !mo9606a(pcVar, keyEvent)) || (rgVar = pcVar.f15445h) == null) {
            return false;
        }
        return rgVar.performShortcut(i, keyEvent, 1);
    }

    /* renamed from: a */
    public final boolean mo9606a(C0414pc pcVar, KeyEvent keyEvent) {
        boolean z;
        C0565us usVar;
        int i;
        boolean z2;
        C0565us usVar2;
        Resources.Theme theme;
        C0565us usVar3;
        if (this.f15501t) {
            return false;
        }
        if (pcVar.f15448k) {
            return true;
        }
        C0414pc pcVar2 = this.f15500s;
        if (!(pcVar2 == null || pcVar2 == pcVar)) {
            mo9602a(pcVar2, false);
        }
        Window.Callback p = mo9613p();
        if (p != null) {
            pcVar.f15444g = p.onCreatePanelView(pcVar.f15438a);
        }
        int i2 = pcVar.f15438a;
        if (i2 == 0 || i2 == 108) {
            z = true;
        } else {
            z = false;
        }
        if (z && (usVar3 = this.f15488g) != null) {
            usVar3.mo823g();
        }
        if (pcVar.f15444g == null && (!z || !(this.f15460C instanceof C0426po))) {
            C0472rg rgVar = pcVar.f15445h;
            if (rgVar == null || pcVar.f15453p) {
                if (rgVar == null) {
                    Context context = this.f15485d;
                    int i3 = pcVar.f15438a;
                    if ((i3 == 0 || i3 == 108) && this.f15488g != null) {
                        TypedValue typedValue = new TypedValue();
                        Resources.Theme theme2 = context.getTheme();
                        theme2.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            theme = context.getResources().newTheme();
                            theme.setTo(theme2);
                            theme.applyStyle(typedValue.resourceId, true);
                            theme.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
                        } else {
                            theme2.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
                            theme = null;
                        }
                        if (typedValue.resourceId != 0) {
                            if (theme == null) {
                                theme = context.getResources().newTheme();
                                theme.setTo(theme2);
                            }
                            theme.applyStyle(typedValue.resourceId, true);
                        }
                        if (theme != null) {
                            C0445qg qgVar = new C0445qg(context, 0);
                            qgVar.getTheme().setTo(theme);
                            context = qgVar;
                        }
                    }
                    C0472rg rgVar2 = new C0472rg(context);
                    rgVar2.f15750b = this;
                    pcVar.mo9599a(rgVar2);
                    if (pcVar.f15445h == null) {
                        return false;
                    }
                }
                if (z && this.f15488g != null) {
                    if (this.f15463F == null) {
                        this.f15463F = new C0404ot(this);
                    }
                    this.f15488g.mo811a(pcVar.f15445h, this.f15463F);
                }
                pcVar.f15445h.mo9859e();
                if (!p.onCreatePanelMenu(pcVar.f15438a, pcVar.f15445h)) {
                    pcVar.mo9599a((C0472rg) null);
                    if (z && (usVar2 = this.f15488g) != null) {
                        usVar2.mo811a((Menu) null, this.f15463F);
                    }
                    return false;
                }
                pcVar.f15453p = false;
            }
            pcVar.f15445h.mo9859e();
            Bundle bundle = pcVar.f15454q;
            if (bundle != null) {
                pcVar.f15445h.mo9849b(bundle);
                pcVar.f15454q = null;
            }
            if (p.onPreparePanel(0, pcVar.f15444g, pcVar.f15445h)) {
                if (keyEvent != null) {
                    i = keyEvent.getDeviceId();
                } else {
                    i = -1;
                }
                if (KeyCharacterMap.load(i).getKeyboardType() != 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                pcVar.f15451n = z2;
                pcVar.f15445h.setQwertyMode(z2);
                pcVar.f15445h.mo9860f();
            } else {
                if (z && (usVar = this.f15488g) != null) {
                    usVar.mo811a((Menu) null, this.f15463F);
                }
                pcVar.f15445h.mo9860f();
                return false;
            }
        }
        pcVar.f15448k = true;
        pcVar.f15449l = false;
        this.f15500s = pcVar;
        return true;
    }

    /* renamed from: e */
    public final void mo9559e(int i) {
        if (i == 8) {
            i = 108;
        } else if (i == 9) {
            i = 109;
        }
        if (!this.f15470M || i != 108) {
            if (this.f15497p && i == 1) {
                this.f15497p = false;
            }
            if (i == 1) {
                m14989y();
                this.f15470M = true;
            } else if (i == 2) {
                m14989y();
                this.f15467J = true;
            } else if (i == 5) {
                m14989y();
                this.f15468K = true;
            } else if (i == 10) {
                m14989y();
                this.f15498q = true;
            } else if (i == 108) {
                m14989y();
                this.f15497p = true;
            } else if (i != 109) {
                this.f15486e.requestFeature(i);
            } else {
                m14989y();
                this.f15469L = true;
            }
        }
    }

    /* renamed from: c */
    public final void mo9556c(int i) {
        m14987w();
        ViewGroup viewGroup = (ViewGroup) this.f15495n.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f15485d).inflate(i, viewGroup);
        this.f15459B.f15671a.onContentChanged();
    }

    /* renamed from: a */
    public final void mo9549a(View view) {
        m14987w();
        ViewGroup viewGroup = (ViewGroup) this.f15495n.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f15459B.f15671a.onContentChanged();
    }

    /* renamed from: a */
    public final void mo9550a(View view, ViewGroup.LayoutParams layoutParams) {
        m14987w();
        ViewGroup viewGroup = (ViewGroup) this.f15495n.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f15459B.f15671a.onContentChanged();
    }

    /* renamed from: a */
    public final void mo9548a(Toolbar toolbar) {
        if (this.f15458A instanceof Activity) {
            C0383nz a = mo9546a();
            if (!(a instanceof C0434pw)) {
                this.f15461D = null;
                if (a != null) {
                    a.mo9501g();
                }
                if (toolbar != null) {
                    C0426po poVar = new C0426po(toolbar, m14988x(), this.f15459B);
                    this.f15460C = poVar;
                    this.f15486e.setCallback(poVar.f15521c);
                } else {
                    this.f15460C = null;
                    this.f15486e.setCallback(this.f15459B);
                }
                mo9560f();
                return;
            }
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
    }

    /* renamed from: a */
    public final void mo9547a(int i) {
        this.f15477T = i;
    }

    /* renamed from: a */
    public final void mo9551a(CharSequence charSequence) {
        this.f15462E = charSequence;
        C0565us usVar = this.f15488g;
        if (usVar == null) {
            C0383nz nzVar = this.f15460C;
            if (nzVar == null) {
                TextView textView = this.f15466I;
                if (textView != null) {
                    textView.setText(charSequence);
                    return;
                }
                return;
            }
            nzVar.mo9492b(charSequence);
            return;
        }
        usVar.mo813a(charSequence);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.f15495n;
     */
    /* renamed from: r */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo9615r() {
        /*
            r1 = this;
            boolean r0 = r1.f15465H
            if (r0 == 0) goto L_0x0010
            android.view.ViewGroup r0 = r1.f15495n
            if (r0 == 0) goto L_0x0010
            boolean r0 = p000.C0340mj.m14732w(r0)
            if (r0 == 0) goto L_0x0010
            r0 = 1
            return r0
        L_0x0010:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0416pe.mo9615r():boolean");
    }

    /* renamed from: y */
    private final void m14989y() {
        if (this.f15465H) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }
}
