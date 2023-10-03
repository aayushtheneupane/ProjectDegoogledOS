package com.android.dialer.app.calllog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.QuickContactBadge;
import com.android.dialer.app.calllog.CallLogAdapter;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;

class DialerQuickContactBadge extends QuickContactBadge {
    private View.OnClickListener extraOnClickListener;
    private CallLogAdapter.OnActionModeStateChangedListener onActionModeStateChangeListener;

    public DialerQuickContactBadge(Context context) {
        super(context);
    }

    public void onClick(View view) {
        if (this.extraOnClickListener == null || !this.onActionModeStateChangeListener.isActionModeStateEnabled()) {
            super.onClick(view);
            return;
        }
        ((LoggingBindingsStub) Logger.get(view.getContext())).logImpression(DialerImpression$Type.MULTISELECT_SINGLE_PRESS_TAP_VIA_CONTACT_BADGE);
        this.extraOnClickListener.onClick(view);
    }

    public void setMulitSelectListeners(View.OnClickListener onClickListener, CallLogAdapter.OnActionModeStateChangedListener onActionModeStateChangedListener) {
        this.extraOnClickListener = onClickListener;
        this.onActionModeStateChangeListener = onActionModeStateChangedListener;
    }

    public DialerQuickContactBadge(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DialerQuickContactBadge(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
