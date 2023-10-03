package com.android.dialer.callcomposer;

import android.content.Context;
import android.support.p000v4.app.Fragment;
import com.android.dialer.common.Assert;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;

public abstract class CallComposerFragment extends Fragment {

    public interface CallComposerListener {
        void composeCall(CallComposerFragment callComposerFragment);

        boolean isFullscreen();

        boolean isLandscapeLayout();

        void sendAndCall();

        void showFullscreen(boolean z);
    }

    public abstract void clearComposer();

    public CallComposerListener getListener() {
        return (CallComposerListener) FragmentUtils.getParent((Fragment) this, CallComposerListener.class);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (FragmentUtils.getParent((Fragment) this, CallComposerListener.class) == null) {
            LogUtil.m8e("CallComposerFragment.onAttach", "Container activity must implement CallComposerListener.", new Object[0]);
            Assert.fail();
            throw null;
        }
    }

    public abstract boolean shouldHide();
}
