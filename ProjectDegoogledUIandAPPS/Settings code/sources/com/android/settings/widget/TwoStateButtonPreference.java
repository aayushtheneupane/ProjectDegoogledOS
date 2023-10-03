package com.android.settings.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import androidx.core.content.res.TypedArrayUtils;
import com.android.settings.R$styleable;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class TwoStateButtonPreference extends LayoutPreference implements View.OnClickListener {
    private final Button mButtonOff;
    private final Button mButtonOn;
    private boolean mIsChecked;

    public TwoStateButtonPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, C1715R.attr.twoStateButtonPreferenceStyle, 16842894));
        if (attributeSet == null) {
            this.mButtonOn = null;
            this.mButtonOff = null;
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.TwoStateButtonPreference);
        int resourceId = obtainStyledAttributes.getResourceId(1, C1715R.string.summary_placeholder);
        int resourceId2 = obtainStyledAttributes.getResourceId(0, C1715R.string.summary_placeholder);
        obtainStyledAttributes.recycle();
        this.mButtonOn = (Button) findViewById(C1715R.C1718id.state_on_button);
        this.mButtonOn.setText(resourceId);
        this.mButtonOn.setOnClickListener(this);
        this.mButtonOff = (Button) findViewById(C1715R.C1718id.state_off_button);
        this.mButtonOff.setText(resourceId2);
        this.mButtonOff.setOnClickListener(this);
        setChecked(isChecked());
    }

    public void onClick(View view) {
        boolean z = view.getId() == C1715R.C1718id.state_on_button;
        setChecked(z);
        callChangeListener(Boolean.valueOf(z));
    }

    public void setChecked(boolean z) {
        this.mIsChecked = z;
        if (z) {
            this.mButtonOn.setVisibility(8);
            this.mButtonOff.setVisibility(0);
            return;
        }
        this.mButtonOn.setVisibility(0);
        this.mButtonOff.setVisibility(8);
    }

    public boolean isChecked() {
        return this.mIsChecked;
    }

    public void setButtonEnabled(boolean z) {
        this.mButtonOn.setEnabled(z);
        this.mButtonOff.setEnabled(z);
    }

    public Button getStateOnButton() {
        return this.mButtonOn;
    }

    public Button getStateOffButton() {
        return this.mButtonOff;
    }
}
