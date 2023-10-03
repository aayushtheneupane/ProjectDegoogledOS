package com.android.settings.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceViewHolder;
import com.havoc.config.center.C1715R;

public class RadioButtonPreference extends CheckBoxPreference {
    private View appendix;
    private int appendixVisibility;
    private OnClickListener mListener;

    public interface OnClickListener {
        void onRadioButtonClicked(RadioButtonPreference radioButtonPreference);
    }

    public RadioButtonPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mListener = null;
        this.appendixVisibility = -1;
        setWidgetLayoutResource(C1715R.layout.preference_widget_radiobutton);
        setLayoutResource(C1715R.layout.preference_radio);
        setIconSpaceReserved(false);
    }

    public RadioButtonPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, C1715R.attr.preferenceStyle, 16842894));
    }

    public RadioButtonPreference(Context context) {
        this(context, (AttributeSet) null);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    public void onClick() {
        OnClickListener onClickListener = this.mListener;
        if (onClickListener != null) {
            onClickListener.onRadioButtonClicked(this);
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        int i;
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(C1715R.C1718id.summary_container);
        if (findViewById != null) {
            findViewById.setVisibility(TextUtils.isEmpty(getSummary()) ? 8 : 0);
            this.appendix = preferenceViewHolder.findViewById(C1715R.C1718id.appendix);
            View view = this.appendix;
            if (!(view == null || (i = this.appendixVisibility) == -1)) {
                view.setVisibility(i);
            }
        }
        TextView textView = (TextView) preferenceViewHolder.findViewById(16908310);
        if (textView != null) {
            textView.setSingleLine(false);
            textView.setMaxLines(3);
        }
    }

    public void setAppendixVisibility(int i) {
        View view = this.appendix;
        if (view != null) {
            view.setVisibility(i);
        }
        this.appendixVisibility = i;
    }
}
