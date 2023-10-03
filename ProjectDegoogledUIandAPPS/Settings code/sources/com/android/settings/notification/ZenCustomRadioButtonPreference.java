package com.android.settings.notification;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.TwoTargetPreference;
import com.havoc.config.center.C1715R;

public class ZenCustomRadioButtonPreference extends TwoTargetPreference implements View.OnClickListener {
    private RadioButton mButton;
    private boolean mChecked;
    private OnGearClickListener mOnGearClickListener;
    private OnRadioButtonClickListener mOnRadioButtonClickListener;

    public interface OnGearClickListener {
        void onGearClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference);
    }

    public interface OnRadioButtonClickListener {
        void onRadioButtonClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference);
    }

    /* access modifiers changed from: protected */
    public int getSecondTargetResId() {
        return C1715R.layout.preference_widget_gear;
    }

    public ZenCustomRadioButtonPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setLayoutResource(C1715R.layout.preference_two_target_radio);
    }

    public ZenCustomRadioButtonPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutResource(C1715R.layout.preference_two_target_radio);
    }

    public ZenCustomRadioButtonPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(C1715R.layout.preference_two_target_radio);
    }

    public ZenCustomRadioButtonPreference(Context context) {
        super(context);
        setLayoutResource(C1715R.layout.preference_two_target_radio);
    }

    public void setOnGearClickListener(OnGearClickListener onGearClickListener) {
        this.mOnGearClickListener = onGearClickListener;
        notifyChanged();
    }

    public void setOnRadioButtonClickListener(OnRadioButtonClickListener onRadioButtonClickListener) {
        this.mOnRadioButtonClickListener = onRadioButtonClickListener;
        notifyChanged();
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(C1715R.C1718id.checkbox_frame);
        if (findViewById != null) {
            findViewById.setOnClickListener(this);
        }
        this.mButton = (RadioButton) preferenceViewHolder.findViewById(16908289);
        RadioButton radioButton = this.mButton;
        if (radioButton != null) {
            radioButton.setChecked(this.mChecked);
        }
        View findViewById2 = preferenceViewHolder.findViewById(16908312);
        View findViewById3 = preferenceViewHolder.findViewById(C1715R.C1718id.two_target_divider);
        if (this.mOnGearClickListener != null) {
            findViewById3.setVisibility(0);
            findViewById2.setVisibility(0);
            findViewById2.setOnClickListener(this);
            return;
        }
        findViewById3.setVisibility(8);
        findViewById2.setVisibility(8);
        findViewById2.setOnClickListener((View.OnClickListener) null);
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
        RadioButton radioButton = this.mButton;
        if (radioButton != null) {
            radioButton.setChecked(z);
        }
    }

    public void onClick() {
        OnRadioButtonClickListener onRadioButtonClickListener = this.mOnRadioButtonClickListener;
        if (onRadioButtonClickListener != null) {
            onRadioButtonClickListener.onRadioButtonClick(this);
        }
    }

    public void onClick(View view) {
        OnRadioButtonClickListener onRadioButtonClickListener;
        if (view.getId() == 16908312) {
            OnGearClickListener onGearClickListener = this.mOnGearClickListener;
            if (onGearClickListener != null) {
                onGearClickListener.onGearClick(this);
            }
        } else if (view.getId() == C1715R.C1718id.checkbox_frame && (onRadioButtonClickListener = this.mOnRadioButtonClickListener) != null) {
            onRadioButtonClickListener.onRadioButtonClick(this);
        }
    }
}
