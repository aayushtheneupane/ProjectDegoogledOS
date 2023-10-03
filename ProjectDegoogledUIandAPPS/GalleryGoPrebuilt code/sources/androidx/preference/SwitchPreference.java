package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Switch;

/* compiled from: PG */
public class SwitchPreference extends TwoStatePreference {

    /* renamed from: c */
    private final aee f1147c;

    /* renamed from: d */
    private CharSequence f1148d;

    /* renamed from: e */
    private CharSequence f1149e;

    public SwitchPreference(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SwitchPreference(android.content.Context r4, android.util.AttributeSet r5) {
        /*
            r3 = this;
            r0 = 2130969289(0x7f0402c9, float:1.7547256E38)
            r1 = 16843629(0x101036d, float:2.3696016E-38)
            int r0 = p000.C0071co.m4652a((android.content.Context) r4, (int) r0, (int) r1)
            r3.<init>(r4, r5, r0)
            aee r1 = new aee
            r1.<init>(r3)
            r3.f1147c = r1
            int[] r1 = p000.adz.f261l
            r2 = 0
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r1, r0, r2)
            r5 = 7
            java.lang.String r5 = p000.C0071co.m4663a((android.content.res.TypedArray) r4, (int) r5, (int) r2)
            r3.mo1210c(r5)
            r5 = 6
            r0 = 1
            java.lang.String r5 = p000.C0071co.m4663a((android.content.res.TypedArray) r4, (int) r5, (int) r0)
            r3.mo1211d((java.lang.CharSequence) r5)
            r5 = 9
            r0 = 3
            java.lang.String r5 = p000.C0071co.m4663a((android.content.res.TypedArray) r4, (int) r5, (int) r0)
            r3.f1148d = r5
            r3.mo1157b()
            r5 = 8
            r0 = 4
            java.lang.String r5 = p000.C0071co.m4663a((android.content.res.TypedArray) r4, (int) r5, (int) r0)
            r3.f1149e = r5
            r3.mo1157b()
            r5 = 5
            r0 = 2
            boolean r5 = p000.C0071co.m4666a((android.content.res.TypedArray) r4, (int) r5, (int) r0, (boolean) r2)
            r3.f1154b = r5
            r4.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.SwitchPreference.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* renamed from: a */
    public void mo169a(ady ady) {
        super.mo169a(ady);
        m1085c(ady.mo235c(16908352));
        mo1208b(ady);
    }

    /* renamed from: a */
    public final void mo1155a(View view) {
        mo1192k();
        if (((AccessibilityManager) this.f1111j.getSystemService("accessibility")).isEnabled()) {
            m1085c(view.findViewById(16908352));
            mo1209b(view.findViewById(16908304));
        }
    }

    /* renamed from: c */
    private final void m1085c(View view) {
        boolean z = view instanceof Switch;
        if (z) {
            ((Switch) view).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.f1153a);
        }
        if (z) {
            Switch switchR = (Switch) view;
            switchR.setTextOn(this.f1148d);
            switchR.setTextOff(this.f1149e);
            switchR.setOnCheckedChangeListener(this.f1147c);
        }
    }
}
