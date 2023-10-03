package p000;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

@Deprecated
/* renamed from: bcz */
/* compiled from: PG */
public final class bcz extends Fragment {

    /* renamed from: a */
    public final bcm f2067a;

    /* renamed from: b */
    public final bdd f2068b = new bcy(this);

    /* renamed from: c */
    public apn f2069c;

    /* renamed from: d */
    private final Set f2070d = new HashSet();

    /* renamed from: e */
    private bcz f2071e;

    public bcz() {
        bcm bcm = new bcm();
        this.f2067a = bcm;
    }

    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            m2176a();
            bcz a = aow.m1346a((Context) activity).f1293f.mo1826a(activity.getFragmentManager(), bdc.m2180b(activity));
            this.f2071e = a;
            if (!equals(a)) {
                this.f2071e.f2070d.add(this);
            }
        } catch (IllegalStateException e) {
            if (Log.isLoggable("RMFragment", 5)) {
                Log.w("RMFragment", "Unable to register fragment with root", e);
            }
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        this.f2067a.mo1815c();
        m2176a();
    }

    public final void onDetach() {
        super.onDetach();
        m2176a();
    }

    public final void onStart() {
        super.onStart();
        this.f2067a.mo1811a();
    }

    public final void onStop() {
        super.onStop();
        this.f2067a.mo1813b();
    }

    public final String toString() {
        String fragment = super.toString();
        int i = Build.VERSION.SDK_INT;
        Fragment parentFragment = getParentFragment();
        if (parentFragment == null) {
            parentFragment = null;
        }
        String valueOf = String.valueOf(parentFragment);
        StringBuilder sb = new StringBuilder(String.valueOf(fragment).length() + 9 + String.valueOf(valueOf).length());
        sb.append(fragment);
        sb.append("{parent=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }

    /* renamed from: a */
    private final void m2176a() {
        bcz bcz = this.f2071e;
        if (bcz != null) {
            bcz.f2070d.remove(this);
            this.f2071e = null;
        }
    }
}
