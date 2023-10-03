package androidx.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/* renamed from: androidx.lifecycle.v */
public class C0465v extends Fragment {
    /* renamed from: a */
    public static void m488a(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new C0465v(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    /* renamed from: c */
    private void m489c(Lifecycle$Event lifecycle$Event) {
        Activity activity = getActivity();
        if (activity instanceof C0456m) {
            ((C0456m) activity).getLifecycle().mo4464a(lifecycle$Event);
        } else if (activity instanceof C0453j) {
            C0450g lifecycle = ((C0453j) activity).getLifecycle();
            if (lifecycle instanceof C0455l) {
                ((C0455l) lifecycle).mo4464a(lifecycle$Event);
            }
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        m489c(Lifecycle$Event.ON_CREATE);
    }

    public void onDestroy() {
        super.onDestroy();
        m489c(Lifecycle$Event.ON_DESTROY);
    }

    public void onPause() {
        super.onPause();
        m489c(Lifecycle$Event.ON_PAUSE);
    }

    public void onResume() {
        super.onResume();
        m489c(Lifecycle$Event.ON_RESUME);
    }

    public void onStart() {
        super.onStart();
        m489c(Lifecycle$Event.ON_START);
    }

    public void onStop() {
        super.onStop();
        m489c(Lifecycle$Event.ON_STOP);
    }
}
