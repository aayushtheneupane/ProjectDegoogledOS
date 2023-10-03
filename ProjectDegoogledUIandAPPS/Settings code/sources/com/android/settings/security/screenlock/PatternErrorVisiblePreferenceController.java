package com.android.settings.security.screenlock;

import android.content.Context;
import com.android.internal.widget.LockPatternUtils;

public class PatternErrorVisiblePreferenceController extends AbstractPatternSwitchPreferenceController {
    public PatternErrorVisiblePreferenceController(Context context, int i, LockPatternUtils lockPatternUtils) {
        super(context, "visible_error_pattern", i, lockPatternUtils);
    }

    /* access modifiers changed from: protected */
    public boolean isEnabled(LockPatternUtils lockPatternUtils, int i) {
        return lockPatternUtils.isShowErrorPath(i);
    }

    /* access modifiers changed from: protected */
    public void setEnabled(LockPatternUtils lockPatternUtils, int i, boolean z) {
        lockPatternUtils.setShowErrorPath(z, i);
    }
}
