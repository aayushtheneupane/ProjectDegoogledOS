package p000;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;

/* renamed from: an */
/* compiled from: PG */
public final class C0014an extends Fragment {
    /* renamed from: a */
    static void m798a(Activity activity, C0546u uVar) {
        if (activity instanceof C0003ac) {
            ((C0003ac) activity).mo154b().mo62a(uVar);
        } else if (activity instanceof C0681z) {
            C0600w ad = ((C0681z) activity).mo5ad();
            if (ad instanceof C0002ab) {
                ((C0002ab) ad).mo62a(uVar);
            }
        }
    }

    /* renamed from: a */
    private final void m799a(C0546u uVar) {
        if (Build.VERSION.SDK_INT < 29) {
            m798a(getActivity(), uVar);
        }
    }

    /* renamed from: a */
    public static void m797a(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            activity.registerActivityLifecycleCallbacks(new C0013am());
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new C0014an(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        m799a(C0546u.ON_CREATE);
    }

    public final void onDestroy() {
        super.onDestroy();
        m799a(C0546u.ON_DESTROY);
    }

    public final void onPause() {
        super.onPause();
        m799a(C0546u.ON_PAUSE);
    }

    public final void onResume() {
        super.onResume();
        m799a(C0546u.ON_RESUME);
    }

    public final void onStart() {
        super.onStart();
        m799a(C0546u.ON_START);
    }

    public final void onStop() {
        super.onStop();
        m799a(C0546u.ON_STOP);
    }
}
