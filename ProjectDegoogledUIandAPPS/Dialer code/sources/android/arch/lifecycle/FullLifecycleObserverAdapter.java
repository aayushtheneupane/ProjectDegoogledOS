package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle;
import com.android.dialer.searchfragment.list.NewSearchFragment;

class FullLifecycleObserverAdapter implements GenericLifecycleObserver {
    private final FullLifecycleObserver mObserver;

    FullLifecycleObserverAdapter(FullLifecycleObserver fullLifecycleObserver) {
        this.mObserver = fullLifecycleObserver;
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        switch (event.ordinal()) {
            case 0:
                this.mObserver.onCreate(lifecycleOwner);
                return;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                this.mObserver.onStart(lifecycleOwner);
                return;
            case 2:
                this.mObserver.onResume(lifecycleOwner);
                return;
            case 3:
                this.mObserver.onPause(lifecycleOwner);
                return;
            case 4:
                this.mObserver.onStop(lifecycleOwner);
                return;
            case 5:
                this.mObserver.onDestroy(lifecycleOwner);
                return;
            case 6:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            default:
                return;
        }
    }
}
