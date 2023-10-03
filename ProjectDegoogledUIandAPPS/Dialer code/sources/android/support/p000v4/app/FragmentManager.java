package android.support.p000v4.app;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* renamed from: android.support.v4.app.FragmentManager */
public abstract class FragmentManager {

    /* renamed from: android.support.v4.app.FragmentManager$BackStackEntry */
    public interface BackStackEntry {
    }

    /* renamed from: android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks */
    public static abstract class FragmentLifecycleCallbacks {
    }

    /* renamed from: android.support.v4.app.FragmentManager$OnBackStackChangedListener */
    public interface OnBackStackChangedListener {
        void onBackStackChanged();
    }

    public abstract FragmentTransaction beginTransaction();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract boolean executePendingTransactions();

    public abstract Fragment findFragmentById(int i);

    public abstract Fragment findFragmentByTag(String str);

    public abstract Fragment getFragment(Bundle bundle, String str);

    public abstract List<Fragment> getFragments();

    public abstract boolean isDestroyed();

    public abstract boolean isStateSaved();

    public abstract void popBackStack(int i, int i2);

    public abstract boolean popBackStackImmediate();

    public abstract void putFragment(Bundle bundle, String str, Fragment fragment);

    public abstract Fragment.SavedState saveFragmentInstanceState(Fragment fragment);
}
