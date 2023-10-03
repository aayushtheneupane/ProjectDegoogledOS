package android.support.p002v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import com.google.android.apps.photosgo.R;

/* renamed from: android.support.v7.widget.SearchView$SearchAutoComplete */
/* compiled from: PG */
public class SearchView$SearchAutoComplete extends C0522tc {

    /* renamed from: a */
    private int f957a;

    public SearchView$SearchAutoComplete(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void performCompletion() {
    }

    /* access modifiers changed from: protected */
    public final void replaceText(CharSequence charSequence) {
    }

    public SearchView$SearchAutoComplete(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.autoCompleteTextViewStyle);
    }

    public SearchView$SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f957a = getThreshold();
    }

    public final boolean enoughToFilter() {
        return this.f957a <= 0 || super.enoughToFilter();
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        int i;
        super.onFinishInflate();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        int i3 = configuration.screenHeightDp;
        if (i2 >= 960 && i3 >= 720 && configuration.orientation == 2) {
            i = 256;
        } else if (i2 >= 600) {
            i = 192;
        } else {
            i = 160;
        }
        setMinWidth((int) TypedValue.applyDimension(1, (float) i, displayMetrics));
    }

    /* access modifiers changed from: protected */
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        throw null;
    }

    public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                if (keyDispatcherState != null) {
                    keyDispatcherState.startTracking(keyEvent, this);
                }
                return true;
            } else if (keyEvent.getAction() == 1) {
                KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.handleUpEvent(keyEvent);
                }
                if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                    throw null;
                }
            }
        }
        return super.onKeyPreIme(i, keyEvent);
    }

    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            throw null;
        }
    }

    public final void setThreshold(int i) {
        super.setThreshold(i);
        this.f957a = i;
    }
}
