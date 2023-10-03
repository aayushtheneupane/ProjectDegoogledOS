package com.android.systemui.privacy;

import com.android.systemui.privacy.PrivacyItemController;
import java.lang.ref.WeakReference;
import java.util.function.Predicate;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PrivacyItemController.kt */
final class PrivacyItemController$removeCallback$1<T> implements Predicate<WeakReference<PrivacyItemController.Callback>> {
    final /* synthetic */ WeakReference $callback;

    PrivacyItemController$removeCallback$1(WeakReference weakReference) {
        this.$callback = weakReference;
    }

    public final boolean test(WeakReference<PrivacyItemController.Callback> weakReference) {
        Intrinsics.checkParameterIsNotNull(weakReference, "it");
        PrivacyItemController.Callback callback = (PrivacyItemController.Callback) weakReference.get();
        if (callback != null) {
            return callback.equals(this.$callback.get());
        }
        return true;
    }
}
