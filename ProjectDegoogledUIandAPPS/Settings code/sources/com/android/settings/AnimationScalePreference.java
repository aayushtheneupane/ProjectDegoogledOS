package com.android.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.settingslib.CustomDialogPreferenceCompat;
import com.havoc.config.center.C1715R;

public class AnimationScalePreference extends CustomDialogPreferenceCompat implements SeekBar.OnSeekBarChangeListener {
    private float mScale = 1.0f;
    private TextView mScaleText;
    private IntervalSeekBar mSeekBar;

    /* access modifiers changed from: protected */
    public void onClick() {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public AnimationScalePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPositiveButtonText(17039370);
        setNegativeButtonText(17039360);
        setDialogLayoutResource(C1715R.layout.preference_dialog_animation_scale);
    }

    /* access modifiers changed from: protected */
    public void onBindDialogView(View view) {
        super.onBindDialogView(view);
        this.mScaleText = (TextView) view.findViewById(C1715R.C1718id.scale);
        TextView textView = this.mScaleText;
        textView.setText(String.valueOf(this.mScale) + "x");
        this.mSeekBar = (IntervalSeekBar) view.findViewById(C1715R.C1718id.scale_seekbar);
        this.mSeekBar.setProgressFloat(this.mScale);
        this.mSeekBar.setOnSeekBarChangeListener(this);
    }

    public void setScale(float f) {
        this.mScale = f;
        setSummary((CharSequence) String.valueOf(f) + "x");
    }

    /* access modifiers changed from: protected */
    public void onDialogClosed(boolean z) {
        if (z) {
            callChangeListener(Float.valueOf(this.mSeekBar.getProgressFloat()));
        }
    }

    public void click() {
        super.onClick();
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        TextView textView = this.mScaleText;
        textView.setText(String.valueOf(this.mSeekBar.getProgressFloat()) + "x");
    }
}
