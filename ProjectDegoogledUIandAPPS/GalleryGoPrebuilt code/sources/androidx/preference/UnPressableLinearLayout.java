package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* compiled from: PG */
public class UnPressableLinearLayout extends LinearLayout {
    public UnPressableLinearLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* access modifiers changed from: protected */
    public final void dispatchSetPressed(boolean z) {
    }

    public UnPressableLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
