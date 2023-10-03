package com.android.settings.password;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

public class CredentialCheckResultTracker extends Fragment {
    private boolean mHasResult = false;
    private Listener mListener;
    private Intent mResultData;
    private int mResultEffectiveUserId;
    private boolean mResultMatched;
    private int mResultTimeoutMs;

    interface Listener {
        void onCredentialChecked(boolean z, Intent intent, int i, int i2, boolean z2);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void setListener(Listener listener) {
        if (this.mListener != listener) {
            this.mListener = listener;
            Listener listener2 = this.mListener;
            if (listener2 != null && this.mHasResult) {
                listener2.onCredentialChecked(this.mResultMatched, this.mResultData, this.mResultTimeoutMs, this.mResultEffectiveUserId, false);
            }
        }
    }

    public void setResult(boolean z, Intent intent, int i, int i2) {
        this.mResultMatched = z;
        this.mResultData = intent;
        this.mResultTimeoutMs = i;
        this.mResultEffectiveUserId = i2;
        this.mHasResult = true;
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onCredentialChecked(this.mResultMatched, this.mResultData, this.mResultTimeoutMs, this.mResultEffectiveUserId, true);
            this.mHasResult = false;
        }
    }

    public void clearResult() {
        this.mHasResult = false;
        this.mResultMatched = false;
        this.mResultData = null;
        this.mResultTimeoutMs = 0;
        this.mResultEffectiveUserId = 0;
    }
}
