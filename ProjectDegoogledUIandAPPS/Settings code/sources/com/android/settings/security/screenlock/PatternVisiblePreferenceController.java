package com.android.settings.security.screenlock;

import android.content.Context;
import com.android.internal.widget.LockPatternUtils;

public class PatternVisiblePreferenceController extends AbstractPatternSwitchPreferenceController {
    public PatternVisiblePreferenceController(Context context, int i, LockPatternUtils lockPatternUtils) {
        super(context, "visiblepattern", i, lockPatternUtils);
    }

    /* access modifiers changed from: protected */
    public boolean isEnabled(LockPatternUtils lockPatternUtils, int i) {
        return lockPatternUtils.isVisiblePatternEnabled(i);
    }

    /* access modifiers changed from: protected */
    public void setEnabled(LockPatternUtils lockPatternUtils, int i, boolean z) {
        lockPatternUtils.setVisiblePatternEnabled(z, i);
    }
}
