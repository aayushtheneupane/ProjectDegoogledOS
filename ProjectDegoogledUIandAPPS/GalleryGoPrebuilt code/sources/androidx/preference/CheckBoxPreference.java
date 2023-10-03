package androidx.preference;

import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;

/* compiled from: PG */
public class CheckBoxPreference extends TwoStatePreference {

    /* renamed from: c */
    private final ace f1068c = new ace(this);

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CheckBoxPreference(android.content.Context r4, android.util.AttributeSet r5) {
        /*
            r3 = this;
            r0 = 2130968704(0x7f040080, float:1.754607E38)
            r1 = 16842895(0x101008f, float:2.369396E-38)
            int r0 = p000.C0071co.m4652a((android.content.Context) r4, (int) r0, (int) r1)
            r3.<init>(r4, r5, r0)
            ace r1 = new ace
            r1.<init>(r3)
            r3.f1068c = r1
            int[] r1 = p000.adz.f251b
            r2 = 0
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r1, r0, r2)
            r5 = 5
            java.lang.String r5 = p000.C0071co.m4663a((android.content.res.TypedArray) r4, (int) r5, (int) r2)
            r3.mo1210c(r5)
            r5 = 4
            r0 = 1
            java.lang.String r5 = p000.C0071co.m4663a((android.content.res.TypedArray) r4, (int) r5, (int) r0)
            r3.mo1211d((java.lang.CharSequence) r5)
            r5 = 3
            r0 = 2
            boolean r5 = p000.C0071co.m4666a((android.content.res.TypedArray) r4, (int) r5, (int) r0, (boolean) r2)
            r3.f1154b = r5
            r4.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.CheckBoxPreference.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* renamed from: a */
    public final void mo169a(ady ady) {
        super.mo169a(ady);
        m986c(ady.mo235c(16908289));
        mo1208b(ady);
    }

    /* renamed from: a */
    public final void mo1155a(View view) {
        mo1192k();
        if (((AccessibilityManager) this.f1111j.getSystemService("accessibility")).isEnabled()) {
            m986c(view.findViewById(16908289));
            mo1209b(view.findViewById(16908304));
        }
    }

    /* renamed from: c */
    private final void m986c(View view) {
        boolean z = view instanceof CompoundButton;
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.f1153a);
        }
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(this.f1068c);
        }
    }
}
