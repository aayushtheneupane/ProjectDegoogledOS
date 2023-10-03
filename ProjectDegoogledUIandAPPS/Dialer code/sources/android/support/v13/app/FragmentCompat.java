package android.support.v13.app;

import android.app.Fragment;
import android.os.Build;

@Deprecated
public class FragmentCompat {
    static final FragmentCompatImpl IMPL = new FragmentCompatApi24Impl();

    static class FragmentCompatApi15Impl extends FragmentCompatBaseImpl {
        FragmentCompatApi15Impl() {
        }
    }

    static class FragmentCompatApi23Impl extends FragmentCompatApi15Impl {
        FragmentCompatApi23Impl() {
        }

        public void requestPermissions(Fragment fragment, String[] strArr, int i) {
            fragment.requestPermissions(strArr, i);
        }
    }

    static class FragmentCompatApi24Impl extends FragmentCompatApi23Impl {
        FragmentCompatApi24Impl() {
        }

        public void setUserVisibleHint(Fragment fragment, boolean z) {
            fragment.setUserVisibleHint(z);
        }
    }

    static class FragmentCompatBaseImpl implements FragmentCompatImpl {
        FragmentCompatBaseImpl() {
        }
    }

    interface FragmentCompatImpl {
        void requestPermissions(Fragment fragment, String[] strArr, int i);

        void setUserVisibleHint(Fragment fragment, boolean z);
    }

    @Deprecated
    public interface OnRequestPermissionsResultCallback {
    }

    static {
        int i = Build.VERSION.SDK_INT;
    }

    @Deprecated
    public static void requestPermissions(Fragment fragment, String[] strArr, int i) {
        IMPL.requestPermissions(fragment, strArr, i);
    }
}
