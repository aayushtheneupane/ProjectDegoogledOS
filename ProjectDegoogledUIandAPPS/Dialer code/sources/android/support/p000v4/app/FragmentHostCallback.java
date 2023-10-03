package android.support.p000v4.app;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.design.R$dimen;

/* renamed from: android.support.v4.app.FragmentHostCallback */
public abstract class FragmentHostCallback<E> extends FragmentContainer {
    private final Activity mActivity;
    private final Context mContext;
    final FragmentManagerImpl mFragmentManager = new FragmentManagerImpl();
    private final Handler mHandler;

    FragmentHostCallback(FragmentActivity fragmentActivity) {
        Handler handler = fragmentActivity.mHandler;
        this.mActivity = fragmentActivity;
        R$dimen.checkNotNull(fragmentActivity, "context == null");
        this.mContext = fragmentActivity;
        R$dimen.checkNotNull(handler, "handler == null");
        this.mHandler = handler;
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
}
