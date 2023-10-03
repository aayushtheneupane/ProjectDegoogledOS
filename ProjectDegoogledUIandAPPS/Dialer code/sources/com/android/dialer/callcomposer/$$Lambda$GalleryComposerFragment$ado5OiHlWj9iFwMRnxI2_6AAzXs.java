package com.android.dialer.callcomposer;

import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutor;

/* renamed from: com.android.dialer.callcomposer.-$$Lambda$GalleryComposerFragment$ado5OiHlWj9iFwMRnxI2_6AAzXs  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$GalleryComposerFragment$ado5OiHlWj9iFwMRnxI2_6AAzXs implements DialerExecutor.FailureListener {
    public static final /* synthetic */ $$Lambda$GalleryComposerFragment$ado5OiHlWj9iFwMRnxI2_6AAzXs INSTANCE = new $$Lambda$GalleryComposerFragment$ado5OiHlWj9iFwMRnxI2_6AAzXs();

    private /* synthetic */ $$Lambda$GalleryComposerFragment$ado5OiHlWj9iFwMRnxI2_6AAzXs() {
    }

    public final void onFailure(Throwable th) {
        LogUtil.m7e("GalleryComposerFragment.onFailure", "data preparation failed", th);
    }
}
