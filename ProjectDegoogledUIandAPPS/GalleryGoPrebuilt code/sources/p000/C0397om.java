package p000;

import android.app.Activity;
import android.app.Dialog;
import android.support.p002v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* renamed from: om */
/* compiled from: PG */
public abstract class C0397om {

    /* renamed from: a */
    public static int f15415a = -100;

    /* renamed from: b */
    public static final C0292kp f15416b = new C0292kp();

    /* renamed from: c */
    public static final Object f15417c = new Object();

    /* renamed from: a */
    public abstract C0383nz mo9546a();

    /* renamed from: a */
    public void mo9547a(int i) {
    }

    /* renamed from: a */
    public abstract void mo9548a(Toolbar toolbar);

    /* renamed from: a */
    public abstract void mo9549a(View view);

    /* renamed from: a */
    public abstract void mo9550a(View view, ViewGroup.LayoutParams layoutParams);

    /* renamed from: a */
    public abstract void mo9551a(CharSequence charSequence);

    /* renamed from: b */
    public abstract MenuInflater mo9552b();

    /* renamed from: b */
    public abstract View mo9553b(int i);

    /* renamed from: b */
    public abstract void mo9554b(View view, ViewGroup.LayoutParams layoutParams);

    /* renamed from: c */
    public abstract void mo9555c();

    /* renamed from: c */
    public abstract void mo9556c(int i);

    /* renamed from: d */
    public abstract void mo9557d();

    /* renamed from: e */
    public abstract void mo9558e();

    /* renamed from: e */
    public abstract void mo9559e(int i);

    /* renamed from: f */
    public abstract void mo9560f();

    /* renamed from: g */
    public abstract void mo9561g();

    /* renamed from: h */
    public abstract void mo9562h();

    /* renamed from: i */
    public int mo9563i() {
        throw null;
    }

    /* renamed from: j */
    public abstract void mo9564j();

    /* renamed from: k */
    public void mo9565k() {
    }

    /* renamed from: l */
    public abstract void mo9566l();

    /* renamed from: m */
    public abstract void mo9567m();

    /* renamed from: n */
    public abstract void mo9568n();

    /* renamed from: o */
    public abstract void mo9569o();

    /* renamed from: a */
    public static C0397om m14919a(Activity activity, C0396ol olVar) {
        return new C0416pe(activity, olVar);
    }

    /* renamed from: a */
    public static C0397om m14920a(Dialog dialog, C0396ol olVar) {
        return new C0416pe(dialog, olVar);
    }

    /* renamed from: a */
    static void m14921a(C0397om omVar) {
        synchronized (f15417c) {
            m14922b(omVar);
        }
    }

    /* renamed from: b */
    public static void m14922b(C0397om omVar) {
        synchronized (f15417c) {
            Iterator it = f15416b.iterator();
            while (it.hasNext()) {
                C0397om omVar2 = (C0397om) ((WeakReference) it.next()).get();
                if (omVar2 != omVar) {
                    if (omVar2 != null) {
                    }
                }
                it.remove();
            }
        }
    }

    /* renamed from: d */
    public static void m14923d(int i) {
        if ((i == -1 || i == 0 || i == 1 || i == 2 || i == 3) && f15415a != i) {
            f15415a = i;
            synchronized (f15417c) {
                Iterator it = f15416b.iterator();
                while (it.hasNext()) {
                    C0397om omVar = (C0397om) ((WeakReference) it.next()).get();
                    if (omVar != null) {
                        omVar.mo9564j();
                    }
                }
            }
        }
    }
}
