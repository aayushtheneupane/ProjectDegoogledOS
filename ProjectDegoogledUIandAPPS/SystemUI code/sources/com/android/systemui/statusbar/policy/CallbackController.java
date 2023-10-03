package com.android.systemui.statusbar.policy;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public interface CallbackController<T> {
    void addCallback(T t);

    void removeCallback(T t);

    T observe(LifecycleOwner lifecycleOwner, T t) {
        return observe(lifecycleOwner.getLifecycle(), t);
    }

    T observe(Lifecycle lifecycle, T t) {
        lifecycle.addObserver(new LifecycleEventObserver(t) {
            private final /* synthetic */ Object f$1;

            {
                this.f$1 = r2;
            }

            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                CallbackController.lambda$observe$0(CallbackController.this, this.f$1, lifecycleOwner, event);
            }
        });
        return t;
    }

    static /* synthetic */ void lambda$observe$0(CallbackController _this, Object obj, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_RESUME) {
            _this.addCallback(obj);
        } else if (event == Lifecycle.Event.ON_PAUSE) {
            _this.removeCallback(obj);
        }
    }
}
