package com.android.contacts.editor;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

public class ViewSelectedFilter extends View.AccessibilityDelegate {
    private View mView;

    private ViewSelectedFilter(View view) {
        this.mView = view;
    }

    public static void suppressViewSelectedEvent(View view) {
        ((View) view.getParent()).setAccessibilityDelegate(new ViewSelectedFilter(view));
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        if (view == this.mView && accessibilityEvent.getEventType() == 4) {
            return false;
        }
        return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }
}
