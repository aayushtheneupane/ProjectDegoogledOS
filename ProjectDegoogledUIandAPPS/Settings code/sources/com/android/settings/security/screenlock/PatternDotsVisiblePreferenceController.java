package com.android.settings.security.screenlock;

import android.content.Context;
import com.android.internal.widget.LockPatternUtils;

public class PatternDotsVisiblePreferenceController extends AbstractPatternSwitchPreferenceController {
    public PatternDotsVisiblePreferenceController(Context context, int i, LockPatternUtils lockPatternUtils) {
        super(context, "visibledots", i, lockPatternUtils);
    }

    /* access modifiers changed from: protected */
    public boolean isEnabled(LockPatternUtils lockPatternUtils, int i) {
        return lockPatternUtils.isVisibleDotsEnabled(i);
    }

    /* access modifiers changed from: protected */
    public void setEnabled(LockPatternUtils lockPatternUtils, int i, boolean z) {
        lockPatternUtils.setVisibleDotsEnabled(z, i);
    }
}
