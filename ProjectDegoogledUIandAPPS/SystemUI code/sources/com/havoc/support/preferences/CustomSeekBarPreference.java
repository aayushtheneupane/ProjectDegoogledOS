package com.havoc.support.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.R$attr;
import com.havoc.support.R$color;
import com.havoc.support.R$id;
import com.havoc.support.R$layout;
import com.havoc.support.R$string;
import com.havoc.support.R$styleable;
import com.havoc.support.util.VibrationUtils;

public class CustomSeekBarPreference extends Preference implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, View.OnLongClickListener {
    protected final String TAG;
    private final Context mContext;
    protected boolean mContinuousUpdates;
    protected int mDefaultValue;
    protected boolean mDefaultValueExists;
    protected int mInterval;
    protected int mMaxValue;
    protected int mMinValue;
    protected ImageView mMinusImageView;
    protected ImageView mPlusImageView;
    protected ImageView mResetImageView;
    protected SeekBar mSeekBar;
    protected boolean mShowSign;
    protected boolean mTrackingTouch;
    protected int mTrackingValue;
    protected String mUnits;
    protected int mValue;
    protected TextView mValueTextView;

    /* access modifiers changed from: protected */
    public void changeValue(int i) {
    }

    /* JADX INFO: finally extract failed */
    public CustomSeekBarPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.TAG = getClass().getName();
        boolean z = true;
        this.mInterval = 1;
        this.mShowSign = false;
        this.mUnits = "";
        this.mContinuousUpdates = false;
        this.mMinValue = 0;
        this.mMaxValue = 100;
        this.mDefaultValueExists = false;
        this.mTrackingTouch = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CustomSeekBarPreference);
        try {
            this.mShowSign = obtainStyledAttributes.getBoolean(R$styleable.CustomSeekBarPreference_showSign, this.mShowSign);
            String string = obtainStyledAttributes.getString(R$styleable.CustomSeekBarPreference_units);
            if (string != null) {
                this.mUnits = " " + string;
            }
            this.mContinuousUpdates = obtainStyledAttributes.getBoolean(R$styleable.CustomSeekBarPreference_continuousUpdates, this.mContinuousUpdates);
            obtainStyledAttributes.recycle();
            try {
                String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/res/com.android.settings", "interval");
                if (attributeValue != null) {
                    this.mInterval = Integer.parseInt(attributeValue);
                }
            } catch (Exception e) {
                Log.e(this.TAG, "Invalid interval value", e);
            }
            this.mMinValue = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/com.android.settings", "min", this.mMinValue);
            this.mMaxValue = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "max", this.mMaxValue);
            int i3 = this.mMaxValue;
            int i4 = this.mMinValue;
            if (i3 < i4) {
                this.mMaxValue = i4;
            }
            String attributeValue2 = attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "defaultValue");
            this.mDefaultValueExists = (attributeValue2 == null || attributeValue2.isEmpty()) ? false : z;
            if (this.mDefaultValueExists) {
                this.mDefaultValue = getLimitedValue(Integer.parseInt(attributeValue2));
                this.mValue = this.mDefaultValue;
            } else {
                this.mValue = this.mMinValue;
            }
            this.mSeekBar = new SeekBar(context, attributeSet);
            setLayoutResource(R$layout.preference_custom_seekbar);
            this.mContext = context;
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public CustomSeekBarPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public CustomSeekBarPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.preferenceStyle, 16842894));
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        try {
            ViewParent parent = this.mSeekBar.getParent();
            ViewGroup viewGroup = (ViewGroup) preferenceViewHolder.findViewById(R$id.seekbar);
            if (parent != viewGroup) {
                if (parent != null) {
                    ((ViewGroup) parent).removeView(this.mSeekBar);
                }
                viewGroup.removeAllViews();
                viewGroup.addView(this.mSeekBar, -1, -2);
            }
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "Error binding view: " + e.toString());
        }
        this.mSeekBar.setMax(getSeekValue(this.mMaxValue));
        this.mSeekBar.setProgress(getSeekValue(this.mValue));
        this.mSeekBar.setEnabled(isEnabled());
        this.mValueTextView = (TextView) preferenceViewHolder.findViewById(R$id.value);
        this.mResetImageView = (ImageView) preferenceViewHolder.findViewById(R$id.reset);
        this.mMinusImageView = (ImageView) preferenceViewHolder.findViewById(R$id.minus);
        this.mPlusImageView = (ImageView) preferenceViewHolder.findViewById(R$id.plus);
        updateValueViews();
        this.mSeekBar.setOnSeekBarChangeListener(this);
        this.mResetImageView.setOnClickListener(this);
        this.mMinusImageView.setOnClickListener(this);
        this.mPlusImageView.setOnClickListener(this);
        this.mResetImageView.setOnLongClickListener(this);
        this.mMinusImageView.setOnLongClickListener(this);
        this.mPlusImageView.setOnLongClickListener(this);
    }

    /* access modifiers changed from: protected */
    public int getLimitedValue(int i) {
        int i2 = this.mMinValue;
        if (i < i2) {
            return i2;
        }
        int i3 = this.mMaxValue;
        return i > i3 ? i3 : i;
    }

    /* access modifiers changed from: protected */
    public int getSeekValue(int i) {
        return 0 - Math.floorDiv(this.mMinValue - i, this.mInterval);
    }

    /* access modifiers changed from: protected */
    public String getTextValue(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append((!this.mShowSign || i <= 0) ? "" : "+");
        sb.append(String.valueOf(i));
        sb.append(this.mUnits);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void updateValueViews() {
        String str;
        String str2;
        TextView textView = this.mValueTextView;
        if (textView != null) {
            Context context = getContext();
            int i = R$string.custom_seekbar_value;
            Object[] objArr = new Object[1];
            if (!this.mTrackingTouch || this.mContinuousUpdates) {
                StringBuilder sb = new StringBuilder();
                sb.append(getTextValue(this.mValue));
                if (!this.mDefaultValueExists || this.mValue != this.mDefaultValue) {
                    str2 = "";
                } else {
                    str2 = " (" + getContext().getString(R$string.custom_seekbar_default_value) + ")";
                }
                sb.append(str2);
                str = sb.toString();
            } else {
                str = getTextValue(this.mTrackingValue);
            }
            objArr[0] = str;
            textView.setText(context.getString(i, objArr));
        }
        ImageView imageView = this.mResetImageView;
        if (imageView != null) {
            if (!this.mDefaultValueExists || this.mValue == this.mDefaultValue || this.mTrackingTouch) {
                this.mResetImageView.setVisibility(4);
            } else {
                imageView.setVisibility(0);
            }
        }
        ImageView imageView2 = this.mMinusImageView;
        if (imageView2 != null) {
            if (this.mValue == this.mMinValue || this.mTrackingTouch) {
                this.mMinusImageView.setClickable(false);
                this.mMinusImageView.setColorFilter(getContext().getColor(R$color.disabled_text_color), PorterDuff.Mode.MULTIPLY);
            } else {
                imageView2.setClickable(true);
                this.mMinusImageView.clearColorFilter();
            }
        }
        ImageView imageView3 = this.mPlusImageView;
        if (imageView3 == null) {
            return;
        }
        if (this.mValue == this.mMaxValue || this.mTrackingTouch) {
            this.mPlusImageView.setClickable(false);
            this.mPlusImageView.setColorFilter(getContext().getColor(R$color.disabled_text_color), PorterDuff.Mode.MULTIPLY);
            return;
        }
        imageView3.setClickable(true);
        this.mPlusImageView.clearColorFilter();
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        int limitedValue = getLimitedValue(this.mMinValue + (i * this.mInterval));
        if (this.mTrackingTouch && !this.mContinuousUpdates) {
            this.mTrackingValue = limitedValue;
            updateValueViews();
            VibrationUtils.doHapticFeedback(this.mContext, 21);
        } else if (this.mValue == limitedValue) {
        } else {
            if (!callChangeListener(Integer.valueOf(limitedValue))) {
                this.mSeekBar.setProgress(getSeekValue(this.mValue));
                return;
            }
            changeValue(limitedValue);
            persistInt(limitedValue);
            this.mValue = limitedValue;
            updateValueViews();
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        this.mTrackingValue = this.mValue;
        this.mTrackingTouch = true;
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        this.mTrackingTouch = false;
        if (!this.mContinuousUpdates) {
            onProgressChanged(this.mSeekBar, getSeekValue(this.mTrackingValue), false);
        }
        notifyChanged();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R$id.reset) {
            Toast.makeText(getContext(), getContext().getString(R$string.custom_seekbar_default_value_to_set, new Object[]{getTextValue(this.mDefaultValue)}), 1).show();
        } else if (id == R$id.minus) {
            setValue(this.mValue - this.mInterval, true);
        } else if (id == R$id.plus) {
            setValue(this.mValue + this.mInterval, true);
        }
        VibrationUtils.doHapticFeedback(this.mContext, 0);
    }

    public boolean onLongClick(View view) {
        int id = view.getId();
        if (id == R$id.reset) {
            setValue(this.mDefaultValue, true);
        } else if (id == R$id.minus) {
            int i = this.mMaxValue;
            int i2 = this.mMinValue;
            setValue((i - i2 <= this.mInterval * 2 || i + i2 >= this.mValue * 2) ? this.mMinValue : Math.floorDiv(i + i2, 2), true);
        } else if (id == R$id.plus) {
            int i3 = this.mMaxValue;
            int i4 = this.mMinValue;
            setValue((i3 - i4 <= this.mInterval * 2 || i3 + i4 <= this.mValue * 2) ? this.mMaxValue : Math.floorDiv((i3 + i4) * -1, 2) * -1, true);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(boolean z, Object obj) {
        if (z) {
            this.mValue = getPersistedInt(this.mValue);
        }
    }

    public void setValue(int i, boolean z) {
        int limitedValue = getLimitedValue(i);
        if (this.mValue == limitedValue) {
            return;
        }
        if (z) {
            this.mSeekBar.setProgress(getSeekValue(limitedValue));
        } else {
            this.mValue = limitedValue;
        }
    }
}
