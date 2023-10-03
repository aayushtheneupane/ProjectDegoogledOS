package com.android.dialer.widget;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import com.android.dialer.R;

public class DialerFloatingActionButton extends FloatingActionButton {
    public DialerFloatingActionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.floatingActionButtonStyle);
    }

    public void hide(FloatingActionButton.OnVisibilityChangedListener onVisibilityChangedListener) {
        super.hide(onVisibilityChangedListener);
        setClickable(false);
    }

    public void setVisibility(int i) {
        throw new UnsupportedOperationException("Do not call setVisibility, call show/hide instead");
    }

    public void show(FloatingActionButton.OnVisibilityChangedListener onVisibilityChangedListener) {
        super.show(onVisibilityChangedListener);
        setClickable(true);
    }

    public void hide() {
        hide((FloatingActionButton.OnVisibilityChangedListener) null);
        setClickable(false);
    }

    public void show() {
        show((FloatingActionButton.OnVisibilityChangedListener) null);
        setClickable(true);
    }
}
