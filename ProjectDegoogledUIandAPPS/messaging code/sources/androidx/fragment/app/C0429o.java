package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import androidx.core.util.Preconditions;

/* renamed from: androidx.fragment.app.o */
public abstract class C0429o extends C0426l {
    private final Activity mActivity;
    private final Context mContext;
    final C0389H mFragmentManager = new C0389H();
    private final Handler mHandler;
    private final int mWindowAnimations;

    C0429o(FragmentActivity fragmentActivity) {
        Handler handler = new Handler();
        this.mActivity = fragmentActivity;
        Preconditions.checkNotNull(fragmentActivity, "context == null");
        this.mContext = fragmentActivity;
        Preconditions.checkNotNull(handler, "handler == null");
        this.mHandler = handler;
        this.mWindowAnimations = 0;
    }

    /* access modifiers changed from: package-private */
    public Activity getActivity() {
        return this.mActivity;
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: package-private */
    public Handler getHandler() {
        return this.mHandler;
    }

    public View onFindViewById(int i) {
        return null;
    }

    public boolean onHasView() {
        return true;
    }
}
