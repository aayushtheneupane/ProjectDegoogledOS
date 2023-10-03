package p000;

import android.animation.Animator;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/* renamed from: fh */
/* compiled from: PG */
public class C0147fh implements ComponentCallbacks, View.OnCreateContextMenuListener, C0681z, C0026ax, aeo {

    /* renamed from: e */
    public static final Object f9561e = new Object();

    /* renamed from: A */
    public int f9562A;

    /* renamed from: B */
    public int f9563B;

    /* renamed from: C */
    public String f9564C;

    /* renamed from: D */
    public boolean f9565D;

    /* renamed from: E */
    public boolean f9566E;

    /* renamed from: F */
    public boolean f9567F;

    /* renamed from: G */
    public boolean f9568G;

    /* renamed from: H */
    public boolean f9569H;

    /* renamed from: I */
    public final boolean f9570I = true;

    /* renamed from: J */
    public boolean f9571J;

    /* renamed from: K */
    public ViewGroup f9572K;

    /* renamed from: L */
    public View f9573L;

    /* renamed from: M */
    public boolean f9574M;

    /* renamed from: N */
    public boolean f9575N = true;

    /* renamed from: O */
    public C0145ff f9576O;

    /* renamed from: P */
    public boolean f9577P;

    /* renamed from: Q */
    public boolean f9578Q;

    /* renamed from: R */
    public float f9579R;

    /* renamed from: S */
    public LayoutInflater f9580S;

    /* renamed from: T */
    public boolean f9581T;

    /* renamed from: U */
    public C0573v f9582U;

    /* renamed from: V */
    public C0002ab f9583V;

    /* renamed from: W */
    public C0203hh f9584W;

    /* renamed from: X */
    public final C0010aj f9585X;

    /* renamed from: Y */
    public aen f9586Y;

    /* renamed from: f */
    public int f9587f = -1;

    /* renamed from: g */
    public Bundle f9588g;

    /* renamed from: h */
    public SparseArray f9589h;

    /* renamed from: i */
    public Boolean f9590i;

    /* renamed from: j */
    public String f9591j = UUID.randomUUID().toString();

    /* renamed from: k */
    public Bundle f9592k;

    /* renamed from: l */
    public C0147fh f9593l;

    /* renamed from: m */
    public String f9594m = null;

    /* renamed from: n */
    public int f9595n;

    /* renamed from: o */
    public Boolean f9596o = null;

    /* renamed from: p */
    public boolean f9597p;

    /* renamed from: q */
    public boolean f9598q;

    /* renamed from: r */
    public boolean f9599r;

    /* renamed from: s */
    public boolean f9600s;

    /* renamed from: t */
    public boolean f9601t;

    /* renamed from: u */
    public boolean f9602u;

    /* renamed from: v */
    public int f9603v;

    /* renamed from: w */
    public C0171gd f9604w;

    /* renamed from: x */
    public C0160fu f9605x;

    /* renamed from: y */
    public C0171gd f9606y = new C0171gd((byte[]) null);

    /* renamed from: z */
    public C0147fh f9607z;

    /* renamed from: D */
    public final int mo5623D() {
        C0145ff ffVar = this.f9576O;
        if (ffVar == null) {
            return 0;
        }
        return ffVar.f9426d;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: E */
    public final View mo5624E() {
        C0145ff ffVar = this.f9576O;
        if (ffVar != null) {
            return ffVar.f9423a;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: F */
    public final int mo5625F() {
        C0145ff ffVar = this.f9576O;
        if (ffVar == null) {
            return 0;
        }
        return ffVar.f9425c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: G */
    public final boolean mo5626G() {
        C0145ff ffVar = this.f9576O;
        if (ffVar == null) {
            return false;
        }
        return ffVar.f9432j;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: L */
    public final void mo5631L() {
        C0145ff ffVar = this.f9576O;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: M */
    public final void mo5632M() {
        C0145ff ffVar = this.f9576O;
    }

    /* renamed from: N */
    public boolean mo5633N() {
        return false;
    }

    /* renamed from: O */
    public void mo5634O() {
    }

    /* renamed from: a */
    public View mo2630a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    /* renamed from: a */
    public void mo2665a(int i, int i2, Intent intent) {
    }

    /* renamed from: a */
    public void mo2705a(int i, String[] strArr, int[] iArr) {
    }

    /* renamed from: a */
    public void mo2758a(Menu menu, MenuInflater menuInflater) {
    }

    /* renamed from: a */
    public void mo2632a(View view, Bundle bundle) {
    }

    /* renamed from: a */
    public boolean mo2666a(MenuItem menuItem) {
        return false;
    }

    /* renamed from: ad */
    public C0600w mo5ad() {
        return this.f9583V;
    }

    /* renamed from: ai */
    public final aem mo6ai() {
        return this.f9586Y.f277a;
    }

    /* renamed from: e */
    public void mo168e(Bundle bundle) {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public final boolean mo5650i() {
        return this.f9603v > 0;
    }

    /* renamed from: k */
    public Context mo2634k() {
        C0160fu fuVar = this.f9605x;
        if (fuVar != null) {
            return fuVar.f10593c;
        }
        return null;
    }

    /* renamed from: m */
    public final C0149fj mo5653m() {
        C0160fu fuVar = this.f9605x;
        if (fuVar != null) {
            return (C0149fj) fuVar.f10592b;
        }
        return null;
    }

    /* renamed from: o */
    public final Object mo5654o() {
        C0160fu fuVar = this.f9605x;
        if (fuVar != null) {
            return ((C0148fi) fuVar).f9696a;
        }
        return null;
    }

    /* renamed from: s */
    public final boolean mo5660s() {
        return this.f9605x != null && this.f9597p;
    }

    public C0147fh() {
        new C0141fb(this);
        this.f9582U = C0573v.RESUMED;
        this.f9585X = new C0010aj();
        mo5648h();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: B */
    public final void mo5621B() {
        C0145ff ffVar = this.f9576O;
        if (ffVar != null) {
            ffVar.f9431i = false;
        }
    }

    /* renamed from: a */
    public void mo5088a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.f9562A));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.f9563B));
        printWriter.print(" mTag=");
        printWriter.println(this.f9564C);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f9587f);
        printWriter.print(" mWho=");
        printWriter.print(this.f9591j);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.f9603v);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.f9597p);
        printWriter.print(" mRemoving=");
        printWriter.print(this.f9598q);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.f9599r);
        printWriter.print(" mInLayout=");
        printWriter.println(this.f9600s);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.f9565D);
        printWriter.print(" mDetached=");
        printWriter.print(this.f9566E);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.f9570I);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.f9569H);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.f9567F);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.f9575N);
        if (this.f9604w != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.f9604w);
        }
        if (this.f9605x != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.f9605x);
        }
        if (this.f9607z != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.f9607z);
        }
        if (this.f9592k != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.f9592k);
        }
        if (this.f9588g != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f9588g);
        }
        if (this.f9589h != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.f9589h);
        }
        C0147fh j = mo5651j();
        if (j != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(j);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.f9595n);
        }
        if (mo5623D() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(mo5623D());
        }
        if (this.f9572K != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.f9572K);
        }
        if (this.f9573L != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.f9573L);
        }
        if (mo5624E() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(mo5624E());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(mo5625F());
        }
        if (mo2634k() != null) {
            C0205hj.m11569a(this).mo7491a(str, printWriter);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.f9606y + ":");
        C0171gd gdVar = this.f9606y;
        gdVar.mo6426a(str + "  ", fileDescriptor, printWriter, strArr);
    }

    /* renamed from: C */
    public final C0145ff mo5622C() {
        if (this.f9576O == null) {
            this.f9576O = new C0145ff();
        }
        return this.f9576O;
    }

    /* renamed from: r */
    public final C0171gd mo5659r() {
        if (this.f9605x != null) {
            return this.f9606y;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    @Deprecated
    /* renamed from: H */
    public final LayoutInflater mo5627H() {
        C0160fu fuVar = this.f9605x;
        if (fuVar != null) {
            C0148fi fiVar = (C0148fi) fuVar;
            LayoutInflater cloneInContext = fiVar.f9696a.getLayoutInflater().cloneInContext(fiVar.f9696a);
            C0350mt.m14761a(cloneInContext, (LayoutInflater.Factory2) this.f9606y.f10984c);
            return cloneInContext;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    /* renamed from: q */
    public final C0171gd mo5658q() {
        C0171gd gdVar = this.f9604w;
        if (gdVar != null) {
            return gdVar;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    /* renamed from: z */
    public final Object mo5665z() {
        C0145ff ffVar = this.f9576O;
        if (ffVar == null || ffVar.f9429g == f9561e) {
            return null;
        }
        return this.f9576O.f9429g;
    }

    /* renamed from: p */
    public final Resources mo5657p() {
        return mo5652l().getResources();
    }

    /* renamed from: y */
    public final Object mo5664y() {
        C0145ff ffVar = this.f9576O;
        if (ffVar == null || ffVar.f9428f == f9561e) {
            return null;
        }
        return this.f9576O.f9428f;
    }

    /* renamed from: A */
    public final Object mo5620A() {
        C0145ff ffVar = this.f9576O;
        if (ffVar == null || ffVar.f9430h == f9561e) {
            return null;
        }
        return this.f9576O.f9430h;
    }

    /* renamed from: a */
    public final String mo5635a(int i) {
        return mo5657p().getString(i);
    }

    /* renamed from: j */
    public final C0147fh mo5651j() {
        String str;
        C0147fh fhVar = this.f9593l;
        if (fhVar != null) {
            return fhVar;
        }
        C0171gd gdVar = this.f9604w;
        if (gdVar == null || (str = this.f9594m) == null) {
            return null;
        }
        return gdVar.mo6440c(str);
    }

    /* renamed from: aa */
    public final C0025aw mo4aa() {
        C0171gd gdVar = this.f9604w;
        if (gdVar != null) {
            C0175gh ghVar = gdVar.f10994m;
            C0025aw awVar = (C0025aw) ghVar.f11333e.get(this.f9591j);
            if (awVar != null) {
                return awVar;
            }
            C0025aw awVar2 = new C0025aw();
            ghVar.f11333e.put(this.f9591j, awVar2);
            return awVar2;
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    /* renamed from: h */
    public final void mo5648h() {
        this.f9583V = new C0002ab(this);
        this.f9586Y = aen.m284a((aeo) this);
        int i = Build.VERSION.SDK_INT;
        this.f9583V.mo64a((C0654y) new C0142fc(this));
    }

    @Deprecated
    /* renamed from: a */
    public static C0147fh m8816a(Context context, String str) {
        try {
            return (C0147fh) C0159ft.m9580b(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (InstantiationException e) {
            throw new C0146fg("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e);
        } catch (IllegalAccessException e2) {
            throw new C0146fg("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (NoSuchMethodException e3) {
            throw new C0146fg("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e3);
        } catch (InvocationTargetException e4) {
            throw new C0146fg("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e4);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: t */
    public final boolean mo5662t() {
        C0147fh fhVar = this.f9607z;
        return fhVar != null && (fhVar.f9598q || fhVar.mo5662t());
    }

    /* renamed from: d */
    public void mo2667d(Bundle bundle) {
        this.f9571J = true;
    }

    @Deprecated
    /* renamed from: a */
    public void mo2631a(Activity activity) {
        this.f9571J = true;
    }

    /* renamed from: a */
    public void mo1832a(Context context) {
        Activity activity;
        this.f9571J = true;
        C0160fu fuVar = this.f9605x;
        if (fuVar != null) {
            activity = fuVar.f10592b;
        } else {
            activity = null;
        }
        if (activity != null) {
            this.f9571J = false;
            mo2631a(activity);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.f9571J = true;
    }

    /* renamed from: a */
    public void mo165a(Bundle bundle) {
        this.f9571J = true;
        mo5649h(bundle);
        C0171gd gdVar = this.f9606y;
        if (gdVar.f10990i <= 0) {
            gdVar.mo6446e();
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        mo5641aj().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    /* renamed from: x */
    public void mo1836x() {
        this.f9571J = true;
    }

    /* renamed from: f */
    public void mo212f() {
        this.f9571J = true;
    }

    /* renamed from: c */
    public void mo1834c() {
        this.f9571J = true;
    }

    /* renamed from: b */
    public LayoutInflater mo2633b(Bundle bundle) {
        return mo5627H();
    }

    /* renamed from: I */
    public final void mo5628I() {
        this.f9571J = true;
        C0160fu fuVar = this.f9605x;
        if (fuVar != null) {
            Activity activity = fuVar.f10592b;
        }
    }

    public void onLowMemory() {
        this.f9571J = true;
    }

    /* renamed from: w */
    public void mo2669w() {
        this.f9571J = true;
    }

    /* renamed from: v */
    public void mo2668v() {
        this.f9571J = true;
    }

    /* renamed from: d */
    public void mo210d() {
        this.f9571J = true;
    }

    /* renamed from: e */
    public void mo211e() {
        this.f9571J = true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo5643b(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f9606y.noteStateNotSaved();
        this.f9602u = true;
        this.f9584W = new C0203hh();
        View a = mo2630a(layoutInflater, viewGroup, bundle);
        this.f9573L = a;
        if (a != null) {
            this.f9584W.mo7436b();
            this.f9585X.mo512a(this.f9584W);
        } else if (this.f9584W.f12724a == null) {
            this.f9584W = null;
        } else {
            throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final LayoutInflater mo5647g(Bundle bundle) {
        LayoutInflater b = mo2633b(bundle);
        this.f9580S = b;
        return b;
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public final void mo5640a(String[] strArr) {
        C0160fu fuVar = this.f9605x;
        if (fuVar != null) {
            C0149fj fjVar = ((C0148fi) fuVar).f9696a;
            C0149fj.m9012b(1);
            try {
                fjVar.f9774b = true;
                int a = ((fjVar.mo5849a(this) + 1) << 16) + 1;
                int i = Build.VERSION.SDK_INT;
                fjVar.mo5082a(a);
                fjVar.requestPermissions(strArr, a);
                fjVar.f9774b = false;
            } catch (Throwable th) {
                fjVar.f9774b = false;
                throw th;
            }
        } else {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
    }

    /* renamed from: aj */
    public final C0149fj mo5641aj() {
        C0149fj m = mo5653m();
        if (m != null) {
            return m;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    /* renamed from: l */
    public final Context mo5652l() {
        Context k = mo2634k();
        if (k != null) {
            return k;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    /* renamed from: u */
    public final View mo5663u() {
        View view = this.f9573L;
        if (view != null) {
            return view;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public final void mo5649h(Bundle bundle) {
        Parcelable parcelable;
        if (bundle != null && (parcelable = bundle.getParcelable("android:support:fragments")) != null) {
            this.f9606y.mo6421a(parcelable);
            this.f9606y.mo6446e();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo5637a(View view) {
        mo5622C().f9423a = view;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo5636a(Animator animator) {
        mo5622C().f9424b = animator;
    }

    /* renamed from: f */
    public final void mo5646f(Bundle bundle) {
        C0171gd gdVar = this.f9604w;
        if (gdVar == null || !gdVar.mo6443c()) {
            this.f9592k = bundle;
            return;
        }
        throw new IllegalStateException("Fragment already added and state has been saved");
    }

    /* renamed from: J */
    public final void mo5629J() {
        if (!this.f9569H) {
            this.f9569H = true;
            if (mo5660s() && !this.f9565D) {
                this.f9605x.mo5744c();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo5639a(boolean z) {
        mo5622C().f9432j = z;
    }

    /* renamed from: b */
    public final void mo5642b(int i) {
        if (this.f9576O != null || i != 0) {
            mo5622C().f9426d = i;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo5644c(int i) {
        if (this.f9576O != null || i != 0) {
            mo5622C();
            this.f9576O.f9427e = i;
        }
    }

    @Deprecated
    /* renamed from: K */
    public final void mo5630K() {
        this.f9567F = true;
        C0171gd gdVar = this.f9604w;
        if (gdVar != null) {
            gdVar.mo6422a(this);
        } else {
            this.f9568G = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final void mo5645d(int i) {
        mo5622C().f9425c = i;
    }

    /* renamed from: a */
    public final void mo5638a(C0147fh fhVar) {
        C0171gd gdVar = this.f9604w;
        C0171gd gdVar2 = fhVar.f9604w;
        if (gdVar == null || gdVar2 == null || gdVar == gdVar2) {
            C0147fh fhVar2 = fhVar;
            while (fhVar2 != null) {
                if (fhVar2 != this) {
                    fhVar2 = fhVar2.mo5651j();
                } else {
                    throw new IllegalArgumentException("Setting " + fhVar + " as the target of " + this + " would create a target cycle");
                }
            }
            if (this.f9604w == null || fhVar.f9604w == null) {
                this.f9594m = null;
                this.f9593l = fhVar;
            } else {
                this.f9594m = fhVar.f9591j;
                this.f9593l = null;
            }
            this.f9595n = 0;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fhVar + " must share the same FragmentManager to be set as a target fragment");
    }

    public final void startActivityForResult(Intent intent, int i) {
        C0160fu fuVar = this.f9605x;
        if (fuVar != null) {
            fuVar.mo5743a(this, intent, i);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} (");
        sb.append(this.f9591j);
        sb.append(")");
        if (this.f9562A != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f9562A));
        }
        if (this.f9564C != null) {
            sb.append(" ");
            sb.append(this.f9564C);
        }
        sb.append('}');
        return sb.toString();
    }
}
