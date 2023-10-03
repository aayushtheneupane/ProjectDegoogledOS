package com.google.android.material.theme;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import com.google.android.material.button.MaterialButton;

/* compiled from: PG */
public class MaterialComponentsViewInflater extends C0420pi {
    public static int floatingToolbarItemBackgroundResId = -1;

    /* access modifiers changed from: protected */
    public C0522tc createAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new gki(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0524te createButton(Context context, AttributeSet attributeSet) {
        if (shouldInflateAppCompatButton(context, attributeSet)) {
            return new C0524te(context, attributeSet);
        }
        return new MaterialButton(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0525tf createCheckBox(Context context, AttributeSet attributeSet) {
        return new gdw(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0537tr createRadioButton(Context context, AttributeSet attributeSet) {
        return new ggc(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0558ul createTextView(Context context, AttributeSet attributeSet) {
        return new gkj(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public boolean shouldInflateAppCompatButton(Context context, AttributeSet attributeSet) {
        int i = Build.VERSION.SDK_INT;
        int i2 = Build.VERSION.SDK_INT;
        int i3 = Build.VERSION.SDK_INT;
        return false;
    }
}
