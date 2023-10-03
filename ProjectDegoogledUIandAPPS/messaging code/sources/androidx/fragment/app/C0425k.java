package androidx.fragment.app;

import android.view.View;
import android.view.Window;
import androidx.activity.C0124f;
import androidx.activity.C0125g;
import androidx.lifecycle.C0442A;
import androidx.lifecycle.C0443B;
import androidx.lifecycle.C0450g;

/* renamed from: androidx.fragment.app.k */
class C0425k extends C0429o implements C0443B, C0125g {
    final /* synthetic */ FragmentActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0425k(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.this$0 = fragmentActivity;
    }

    public C0450g getLifecycle() {
        return this.this$0.mFragmentLifecycleRegistry;
    }

    public C0124f getOnBackPressedDispatcher() {
        return this.this$0.getOnBackPressedDispatcher();
    }

    public C0442A getViewModelStore() {
        return this.this$0.getViewModelStore();
    }

    public View onFindViewById(int i) {
        return this.this$0.findViewById(i);
    }

    public boolean onHasView() {
        Window window = this.this$0.getWindow();
        return (window == null || window.peekDecorView() == null) ? false : true;
    }
}
