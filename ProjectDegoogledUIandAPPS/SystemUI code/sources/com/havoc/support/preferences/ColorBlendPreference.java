package com.havoc.support.preferences;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceDataStore;
import androidx.preference.PreferenceViewHolder;
import com.android.internal.util.havoc.Utils;
import com.havoc.support.R$id;
import com.havoc.support.R$layout;
import com.havoc.support.R$string;
import com.havoc.support.R$styleable;
import com.havoc.support.colorpicker.ColorPickerDialog;

public class ColorBlendPreference extends Preference implements Preference.OnPreferenceClickListener {
    private boolean mBlendReverse = this.mDefaultBlendReverse;
    private String mBlendReverseKey;
    private int mColorEnd = this.mDefaultColorEnd;
    private String mColorEndKey;
    private int mColorStart = this.mDefaultColorStart;
    private String mColorStartKey;
    /* access modifiers changed from: private */
    public boolean mDefaultBlendReverse = false;
    /* access modifiers changed from: private */
    public int mDefaultColorEnd = -16711936;
    /* access modifiers changed from: private */
    public int mDefaultColorStart = -65536;
    /* access modifiers changed from: private */
    public AlertDialog mDialog;
    private TextView mDialogColorPreviewBetweenText;
    private TextView mDialogColorPreviewEndText;
    /* access modifiers changed from: private */
    public SeekBar mDialogColorPreviewSlider;
    private TextView mDialogColorPreviewStartText;
    /* access modifiers changed from: private */
    public TextView mDialogColorPreviewText;
    private View.OnClickListener mDialogPreviewClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (view == ColorBlendPreference.this.mDialogPreviewColorStart) {
                ColorPickerDialog colorPickerDialog = new ColorPickerDialog(ColorBlendPreference.this.getContext(), ColorBlendPreference.this.mPreviewColorStart);
                colorPickerDialog.setOnColorChangedListener(ColorBlendPreference.this.mStartColorChangedListener);
                colorPickerDialog.show();
            } else if (view == ColorBlendPreference.this.mDialogPreviewColorEnd) {
                ColorPickerDialog colorPickerDialog2 = new ColorPickerDialog(ColorBlendPreference.this.getContext(), ColorBlendPreference.this.mPreviewColorEnd);
                colorPickerDialog2.setOnColorChangedListener(ColorBlendPreference.this.mEndColorChangedListener);
                colorPickerDialog2.show();
            } else if (view == ColorBlendPreference.this.mDialogPreviewColorBetween) {
                ColorBlendPreference colorBlendPreference = ColorBlendPreference.this;
                boolean unused = colorBlendPreference.mPreviewBlendReverse = !colorBlendPreference.mPreviewBlendReverse;
                int progress = ColorBlendPreference.this.mDialogColorPreviewSlider.getProgress();
                if (progress < 25 || progress > 75) {
                    ColorBlendPreference.this.mDialogColorPreviewSlider.setProgress(50);
                }
                ColorBlendPreference.this.updateDialogSliderPreview();
            } else if (view == ColorBlendPreference.this.mDialogColorPreviewText) {
                ColorBlendPreference.this.mDialogColorPreviewSlider.setProgress(50);
                ColorBlendPreference.this.updateDialogSliderPreview();
            } else {
                Log.e("ColorBlendPreference", "DialogPreviewClickListener not prepared for " + view);
            }
        }
    };
    /* access modifiers changed from: private */
    public View mDialogPreviewColorBetween;
    /* access modifiers changed from: private */
    public View mDialogPreviewColorEnd;
    /* access modifiers changed from: private */
    public View mDialogPreviewColorStart;
    private SeekBar.OnSeekBarChangeListener mDialogPreviewSliderChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            ColorBlendPreference.this.updateDialogSliderPreview();
        }
    };
    /* access modifiers changed from: private */
    public ColorPickerDialog.OnColorChangedListener mEndColorChangedListener = new ColorPickerDialog.OnColorChangedListener() {
        public void onColorChanged(int i) {
            int unused = ColorBlendPreference.this.mPreviewColorEnd = i;
            ColorBlendPreference.this.updateDialogPreview();
        }
    };
    private String mPreviewBetweenText;
    /* access modifiers changed from: private */
    public boolean mPreviewBlendReverse;
    /* access modifiers changed from: private */
    public int mPreviewColorEnd;
    /* access modifiers changed from: private */
    public int mPreviewColorStart;
    private String mPreviewEndText;
    private String mPreviewStartText;
    /* access modifiers changed from: private */
    public ColorPickerDialog.OnColorChangedListener mStartColorChangedListener = new ColorPickerDialog.OnColorChangedListener() {
        public void onColorChanged(int i) {
            int unused = ColorBlendPreference.this.mPreviewColorStart = i;
            ColorBlendPreference.this.updateDialogPreview();
        }
    };
    private View mViewColorBetween;
    private View mViewColorEnd;
    private View mViewColorStart;

    public ColorBlendPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        setOnPreferenceClickListener(this);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ColorBlendPreference);
            this.mDefaultColorStart = obtainStyledAttributes.getColor(R$styleable.ColorBlendPreference_defaultValueColorStart, this.mDefaultColorStart);
            this.mDefaultColorEnd = obtainStyledAttributes.getColor(R$styleable.ColorBlendPreference_defaultValueColorEnd, this.mDefaultColorEnd);
            this.mDefaultBlendReverse = obtainStyledAttributes.getBoolean(R$styleable.ColorBlendPreference_defaultValueBlendReverse, this.mDefaultBlendReverse);
            this.mColorStartKey = obtainStyledAttributes.getString(R$styleable.ColorBlendPreference_keyColorStart);
            this.mColorEndKey = obtainStyledAttributes.getString(R$styleable.ColorBlendPreference_keyColorEnd);
            this.mBlendReverseKey = obtainStyledAttributes.getString(R$styleable.ColorBlendPreference_keyBlendReverse);
            this.mPreviewStartText = obtainStyledAttributes.getString(R$styleable.ColorBlendPreference_previewStartText);
            if (this.mPreviewStartText == null) {
                this.mPreviewStartText = getContext().getString(R$string.color_preview_start);
            }
            this.mPreviewEndText = obtainStyledAttributes.getString(R$styleable.ColorBlendPreference_previewEndText);
            if (this.mPreviewEndText == null) {
                this.mPreviewEndText = getContext().getString(R$string.color_preview_end);
            }
            this.mPreviewBetweenText = obtainStyledAttributes.getString(R$styleable.ColorBlendPreference_previewBetweenText);
            if (this.mPreviewBetweenText == null) {
                this.mPreviewBetweenText = getContext().getString(R$string.color_preview_between);
            }
            obtainStyledAttributes.recycle();
            loadPreferences();
        }
        setWidgetLayoutResource(R$layout.color_blend_preview);
    }

    public void setPreferenceDataStore(PreferenceDataStore preferenceDataStore) {
        super.setPreferenceDataStore(preferenceDataStore);
        loadPreferences();
    }

    private void loadPreferences() {
        PreferenceDataStore preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore == null) {
            Log.i("ColorBlendPreference", "No preference data store available, not using persisted values");
            this.mColorStart = this.mDefaultColorStart;
            this.mColorEnd = this.mDefaultColorEnd;
            this.mBlendReverse = this.mDefaultBlendReverse;
        } else {
            String str = this.mColorStartKey;
            if (str != null) {
                this.mColorStart = preferenceDataStore.getInt(str, this.mDefaultColorStart);
            }
            String str2 = this.mColorEndKey;
            if (str2 != null) {
                this.mColorEnd = preferenceDataStore.getInt(str2, this.mDefaultColorEnd);
            }
            String str3 = this.mBlendReverseKey;
            if (str3 != null) {
                this.mBlendReverse = preferenceDataStore.getBoolean(str3, this.mDefaultBlendReverse);
            }
        }
        this.mPreviewColorStart = this.mColorStart;
        this.mPreviewColorEnd = this.mColorEnd;
        this.mPreviewBlendReverse = this.mBlendReverse;
    }

    /* access modifiers changed from: private */
    public void setValues(int i, int i2, boolean z) {
        this.mColorStart = i;
        this.mColorEnd = i2;
        this.mBlendReverse = z;
        this.mPreviewColorStart = this.mColorStart;
        this.mPreviewColorEnd = this.mColorEnd;
        this.mPreviewBlendReverse = this.mBlendReverse;
        PreferenceDataStore preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            preferenceDataStore.putInt(this.mColorStartKey, this.mColorStart);
            preferenceDataStore.putInt(this.mColorEndKey, this.mColorEnd);
            preferenceDataStore.putBoolean(this.mBlendReverseKey, this.mBlendReverse);
        } else {
            Log.i("ColorBlendPreference", "No preference data store available, not persisting values");
        }
        updatePreview();
    }

    /* access modifiers changed from: private */
    public void persistValues() {
        if (this.mDialog == null) {
            Log.e("ColorBlendPreference", "persistValues() called without open dialog");
            return;
        }
        setValues(this.mPreviewColorStart, this.mPreviewColorEnd, this.mPreviewBlendReverse);
        Log.d("ColorBlendPreference", "Persisting values");
        PreferenceDataStore preferenceDataStore = getPreferenceDataStore();
        if (preferenceDataStore != null) {
            String str = this.mColorStartKey;
            if (str == null) {
                Log.e("ColorBlendPreference", "Missing color start key");
            } else {
                preferenceDataStore.putInt(str, this.mPreviewColorStart);
            }
            String str2 = this.mColorEndKey;
            if (str2 == null) {
                Log.e("ColorBlendPreference", "Missing color end key");
            } else {
                preferenceDataStore.putInt(str2, this.mPreviewColorEnd);
            }
            String str3 = this.mBlendReverseKey;
            if (str3 == null) {
                Log.e("ColorBlendPreference", "Missing blend reverse key");
            } else {
                preferenceDataStore.putBoolean(str3, this.mPreviewBlendReverse);
            }
            loadPreferences();
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        showDialog();
        return true;
    }

    private void showDialog() {
        this.mPreviewColorStart = this.mColorStart;
        this.mPreviewColorEnd = this.mColorEnd;
        this.mPreviewBlendReverse = this.mBlendReverse;
        this.mDialog = new AlertDialog.Builder(getContext()).setTitle(getTitle()).setView(R$layout.color_blend_preference_dialog).setPositiveButton(R$string.color_blend_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ColorBlendPreference.this.persistValues();
                AlertDialog unused = ColorBlendPreference.this.mDialog = null;
            }
        }).setNegativeButton(R$string.color_blend_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AlertDialog unused = ColorBlendPreference.this.mDialog = null;
            }
        }).setNeutralButton(R$string.color_blend_reset, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ColorBlendPreference colorBlendPreference = ColorBlendPreference.this;
                colorBlendPreference.setValues(colorBlendPreference.mDefaultColorStart, ColorBlendPreference.this.mDefaultColorEnd, ColorBlendPreference.this.mDefaultBlendReverse);
                ColorBlendPreference.this.persistValues();
                AlertDialog unused = ColorBlendPreference.this.mDialog = null;
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                AlertDialog unused = ColorBlendPreference.this.mDialog = null;
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                AlertDialog unused = ColorBlendPreference.this.mDialog = null;
            }
        }).show();
        this.mDialogPreviewColorStart = this.mDialog.findViewById(R$id.color_start);
        this.mDialogPreviewColorEnd = this.mDialog.findViewById(R$id.color_end);
        this.mDialogPreviewColorBetween = this.mDialog.findViewById(R$id.color_between);
        this.mDialogColorPreviewSlider = (SeekBar) this.mDialog.findViewById(R$id.color_preview_slider);
        this.mDialogColorPreviewText = (TextView) this.mDialog.findViewById(R$id.color_preview_text);
        this.mDialogColorPreviewStartText = (TextView) this.mDialog.findViewById(R$id.color_preview_start_text);
        this.mDialogColorPreviewEndText = (TextView) this.mDialog.findViewById(R$id.color_preview_end_text);
        this.mDialogColorPreviewBetweenText = (TextView) this.mDialog.findViewById(R$id.color_preview_between_text);
        this.mDialogPreviewColorStart.setOnClickListener(this.mDialogPreviewClickListener);
        this.mDialogPreviewColorEnd.setOnClickListener(this.mDialogPreviewClickListener);
        this.mDialogPreviewColorBetween.setOnClickListener(this.mDialogPreviewClickListener);
        this.mDialogColorPreviewText.setOnClickListener(this.mDialogPreviewClickListener);
        this.mDialogColorPreviewSlider.setOnSeekBarChangeListener(this.mDialogPreviewSliderChangeListener);
        this.mDialogColorPreviewStartText.setText(this.mPreviewStartText);
        this.mDialogColorPreviewEndText.setText(this.mPreviewEndText);
        this.mDialogColorPreviewBetweenText.setText(this.mPreviewBetweenText);
        updateDialogPreview();
    }

    /* access modifiers changed from: private */
    public void updateDialogPreview() {
        if (this.mDialog == null) {
            Log.e("ColorBlendPreference", "updateDialogPreview() called without open dialog");
            return;
        }
        this.mDialogPreviewColorStart.setBackgroundColor(this.mPreviewColorStart);
        this.mDialogPreviewColorEnd.setBackgroundColor(this.mPreviewColorEnd);
        updateDialogSliderPreview();
    }

    /* access modifiers changed from: private */
    public void updateDialogSliderPreview() {
        int progress = this.mDialogColorPreviewSlider.getProgress();
        this.mDialogPreviewColorBetween.setBackgroundColor(Utils.getBlendColorForPercent(this.mPreviewColorEnd, this.mPreviewColorStart, this.mPreviewBlendReverse, progress));
        this.mDialogColorPreviewText.setText(getContext().getString(R$string.color_blend_preview, new Object[]{Integer.valueOf(progress)}));
    }

    private void updatePreview() {
        View view = this.mViewColorStart;
        if (view != null) {
            view.setBackgroundColor(this.mColorStart);
        }
        View view2 = this.mViewColorEnd;
        if (view2 != null) {
            view2.setBackgroundColor(this.mColorEnd);
        }
        View view3 = this.mViewColorBetween;
        if (view3 != null) {
            view3.setBackgroundColor(Utils.getBlendColorForPercent(this.mColorEnd, this.mColorStart, this.mBlendReverse, 50));
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        this.mViewColorStart = preferenceViewHolder.findViewById(R$id.color_preview_start);
        this.mViewColorEnd = preferenceViewHolder.findViewById(R$id.color_preview_end);
        this.mViewColorBetween = preferenceViewHolder.findViewById(R$id.color_preview_between);
        updatePreview();
    }
}
