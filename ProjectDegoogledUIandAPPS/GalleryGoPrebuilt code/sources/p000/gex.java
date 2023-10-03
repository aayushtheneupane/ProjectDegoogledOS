package p000;

import android.os.Build;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.material.datepicker.MaterialCalendarGridView;

/* renamed from: gex */
/* compiled from: PG */
public final class gex extends C0667ym {

    /* renamed from: p */
    public final TextView f11142p;

    /* renamed from: q */
    public final MaterialCalendarGridView f11143q;

    public gex(LinearLayout linearLayout, boolean z) {
        super(linearLayout);
        TextView textView = (TextView) linearLayout.findViewById(R.id.month_title);
        this.f11142p = textView;
        C0337mg a = C0340mj.m14685a();
        Boolean bool = true;
        if (a.mo9390a()) {
            textView.setAccessibilityHeading(bool.booleanValue());
        } else {
            int i = Build.VERSION.SDK_INT;
            Boolean bool2 = (Boolean) a.mo9391b(textView);
            if (true ^ ((bool2 != null ? bool2.booleanValue() : false) == bool.booleanValue())) {
                C0340mj.m14678E(textView);
                textView.setTag(a.f15227a, bool);
                C0340mj.m14713e(textView, 0);
            }
        }
        this.f11143q = (MaterialCalendarGridView) linearLayout.findViewById(R.id.month_grid);
        if (!z) {
            this.f11142p.setVisibility(8);
        }
    }
}
