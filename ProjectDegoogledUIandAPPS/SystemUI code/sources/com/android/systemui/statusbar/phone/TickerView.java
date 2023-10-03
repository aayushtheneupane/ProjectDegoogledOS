package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextSwitcher;

public class TickerView extends TextSwitcher {
    private Ticker mTicker;

    public TickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Ticker ticker = this.mTicker;
        if (ticker != null) {
            ticker.reflowText();
        }
    }

    public void setTicker(Ticker ticker) {
        this.mTicker = ticker;
    }
}
