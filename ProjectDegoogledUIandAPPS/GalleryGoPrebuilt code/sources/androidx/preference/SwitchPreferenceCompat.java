package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p002v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public class SwitchPreferenceCompat extends TwoStatePreference {

    /* renamed from: c */
    private final aef f1150c = new aef(this);

    /* renamed from: d */
    private CharSequence f1151d;

    /* renamed from: e */
    private CharSequence f1152e;

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.switchPreferenceCompatStyle);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, adz.f262m, R.attr.switchPreferenceCompatStyle, 0);
        mo1210c(C0071co.m4663a(obtainStyledAttributes, 7, 0));
        mo1211d((CharSequence) C0071co.m4663a(obtainStyledAttributes, 6, 1));
        this.f1151d = C0071co.m4663a(obtainStyledAttributes, 9, 3);
        mo1157b();
        this.f1152e = C0071co.m4663a(obtainStyledAttributes, 8, 4);
        mo1157b();
        this.f1154b = C0071co.m4666a(obtainStyledAttributes, 5, 2, false);
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    public final void mo169a(ady ady) {
        super.mo169a(ady);
        m1088c(ady.mo235c(R.id.switchWidget));
        mo1208b(ady);
    }

    /* renamed from: a */
    public final void mo1155a(View view) {
        mo1192k();
        if (((AccessibilityManager) this.f1111j.getSystemService("accessibility")).isEnabled()) {
            m1088c(view.findViewById(R.id.switchWidget));
            mo1209b(view.findViewById(16908304));
        }
    }

    /* renamed from: c */
    private final void m1088c(View view) {
        boolean z = view instanceof SwitchCompat;
        if (z) {
            ((SwitchCompat) view).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.f1153a);
        }
        if (z) {
            SwitchCompat switchCompat = (SwitchCompat) view;
            switchCompat.f974a = this.f1151d;
            switchCompat.requestLayout();
            switchCompat.f975b = this.f1152e;
            switchCompat.requestLayout();
            switchCompat.setOnCheckedChangeListener(this.f1150c);
        }
    }
}
