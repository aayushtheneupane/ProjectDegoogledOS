package com.android.messaging.p041ui.appsettings;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1040Ea;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.appsettings.ApnPreference */
public class ApnPreference extends Preference implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    /* renamed from: _d */
    private static CompoundButton f1716_d;

    /* renamed from: na */
    private static String f1717na;

    /* renamed from: Yd */
    private boolean f1718Yd = false;

    /* renamed from: Zd */
    private boolean f1719Zd = true;
    private int mSubId = -1;

    public ApnPreference(Context context) {
        super(context, (AttributeSet) null, R.attr.apnPreferenceStyle);
    }

    public View getView(View view, ViewGroup viewGroup) {
        View view2 = super.getView(view, viewGroup);
        View findViewById = view2.findViewById(R.id.apn_radiobutton);
        if (findViewById != null && (findViewById instanceof RadioButton)) {
            RadioButton radioButton = (RadioButton) findViewById;
            if (this.f1719Zd) {
                radioButton.setOnCheckedChangeListener(this);
                boolean equals = getKey().equals(f1717na);
                if (equals) {
                    f1716_d = radioButton;
                    f1717na = getKey();
                }
                this.f1718Yd = true;
                radioButton.setChecked(equals);
                this.f1718Yd = false;
            } else {
                radioButton.setVisibility(8);
            }
            radioButton.setContentDescription(getTitle());
        }
        View findViewById2 = view2.findViewById(R.id.text_layout);
        if (findViewById2 != null && (findViewById2 instanceof RelativeLayout)) {
            findViewById2.setOnClickListener(this);
        }
        return view2;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        StringBuilder Pa = C0632a.m1011Pa("ID: ");
        Pa.append(getKey());
        Pa.append(" :");
        Pa.append(z);
        Log.i("ApnPreference", Pa.toString());
        if (!this.f1718Yd) {
            if (z) {
                CompoundButton compoundButton2 = f1716_d;
                if (compoundButton2 != null) {
                    compoundButton2.setChecked(false);
                }
                f1716_d = compoundButton;
                f1717na = getKey();
                callChangeListener(f1717na);
            } else {
                f1716_d = null;
                f1717na = null;
            }
            compoundButton.setContentDescription(getTitle());
        }
    }

    public void onClick(View view) {
        Context context;
        if (view != null && R.id.text_layout == view.getId() && (context = getContext()) != null) {
            context.startActivity(C1040Ea.get().mo6964b(context, getKey(), this.mSubId));
        }
    }

    public void setSelectable(boolean z) {
        this.f1719Zd = z;
    }

    /* renamed from: xb */
    public void mo7139xb() {
        f1717na = getKey();
    }
}
