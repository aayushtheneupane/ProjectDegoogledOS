package p000;

import android.os.Build;
import android.os.Bundle;
import android.support.p001v4.widget.NestedScrollView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ScrollView;

/* renamed from: np */
/* compiled from: PG */
public final class C0373np extends C0315ll {
    /* renamed from: d */
    public final void mo6587d(View view, AccessibilityEvent accessibilityEvent) {
        boolean z;
        super.mo6587d(view, accessibilityEvent);
        NestedScrollView nestedScrollView = (NestedScrollView) view;
        accessibilityEvent.setClassName(ScrollView.class.getName());
        if (nestedScrollView.mo702a() > 0) {
            z = true;
        } else {
            z = false;
        }
        accessibilityEvent.setScrollable(z);
        accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
        accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
        int scrollX = nestedScrollView.getScrollX();
        int i = Build.VERSION.SDK_INT;
        accessibilityEvent.setMaxScrollX(scrollX);
        int a = nestedScrollView.mo702a();
        int i2 = Build.VERSION.SDK_INT;
        accessibilityEvent.setMaxScrollY(a);
    }

    /* renamed from: a */
    public final void mo232a(View view, C0354mx mxVar) {
        int a;
        super.mo232a(view, mxVar);
        NestedScrollView nestedScrollView = (NestedScrollView) view;
        mxVar.mo9423a((CharSequence) ScrollView.class.getName());
        if (nestedScrollView.isEnabled() && (a = nestedScrollView.mo702a()) > 0) {
            mxVar.mo9445j();
            if (nestedScrollView.getScrollY() > 0) {
                mxVar.mo9425a(C0351mu.f15245b);
                mxVar.mo9425a(C0351mu.f15249f);
            }
            if (nestedScrollView.getScrollY() < a) {
                mxVar.mo9425a(C0351mu.f15244a);
                mxVar.mo9425a(C0351mu.f15250g);
            }
        }
    }

    /* renamed from: a */
    public final boolean mo233a(View view, int i, Bundle bundle) {
        if (super.mo233a(view, i, bundle)) {
            return true;
        }
        NestedScrollView nestedScrollView = (NestedScrollView) view;
        if (!nestedScrollView.isEnabled()) {
            return false;
        }
        if (i != 4096) {
            if (i == 8192 || i == 16908344) {
                int height = nestedScrollView.getHeight();
                int paddingBottom = nestedScrollView.getPaddingBottom();
                int max = Math.max(nestedScrollView.getScrollY() - ((height - paddingBottom) - nestedScrollView.getPaddingTop()), 0);
                if (max == nestedScrollView.getScrollY()) {
                    return false;
                }
                nestedScrollView.mo703a(max);
                return true;
            } else if (i != 16908346) {
                return false;
            }
        }
        int height2 = nestedScrollView.getHeight();
        int paddingBottom2 = nestedScrollView.getPaddingBottom();
        int min = Math.min(nestedScrollView.getScrollY() + ((height2 - paddingBottom2) - nestedScrollView.getPaddingTop()), nestedScrollView.mo702a());
        if (min == nestedScrollView.getScrollY()) {
            return false;
        }
        nestedScrollView.mo703a(min);
        return true;
    }
}
