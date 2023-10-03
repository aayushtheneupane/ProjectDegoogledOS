package com.android.dialer.app.list;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

public class ContentChangedFilter extends View.AccessibilityDelegate {
    private View view;

    private ContentChangedFilter(View view2) {
        this.view = view2;
    }

    public static void addToParent(View view2) {
        ((View) view2.getParent()).setAccessibilityDelegate(new ContentChangedFilter(view2));
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view2, AccessibilityEvent accessibilityEvent) {
        if (view2 == this.view && accessibilityEvent.getEventType() == 2048) {
            return false;
        }
        return super.onRequestSendAccessibilityEvent(viewGroup, view2, accessibilityEvent);
    }
}
