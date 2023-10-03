package com.havoc.support.colorpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.havoc.support.R$dimen;
import com.havoc.support.R$drawable;
import com.havoc.support.R$layout;
import com.havoc.support.colorpicker.ColorPickerDialog;
import com.havoc.support.util.VibrationUtils;

public class ColorPickerPreference extends Preference implements Preference.OnPreferenceClickListener, ColorPickerDialog.OnColorChangedListener {
    private boolean mAlphaSliderEnabled;
    /* access modifiers changed from: private */
    public final Context mContext;
    private int mCurrentValue;
    /* access modifiers changed from: private */
    public int mDefaultValue;
    private float mDensity;
    ColorPickerDialog mDialog;
    private boolean mDividerAbove;
    private boolean mDividerBelow;
    private EditText mEditText;
    private boolean mShowPreview;
    private boolean mShowReset;
    PreferenceViewHolder mView;
    LinearLayout mWidgetFrameView;

    public boolean onPreferenceClick(Preference preference) {
        return false;
    }

    public ColorPickerPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ColorPickerPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorPickerPreference(Context context) {
        this(context, (AttributeSet) null);
    }

    public ColorPickerPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mDefaultValue = -16777216;
        this.mCurrentValue = this.mDefaultValue;
        this.mDensity = 0.0f;
        this.mAlphaSliderEnabled = false;
        setLayoutResource(R$layout.preference_material_settings);
        init(context, attributeSet);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, -16777216));
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(boolean z, Object obj) {
        if (obj == null) {
            obj = -16777216;
        }
        this.mCurrentValue = getPersistedInt(((Integer) obj).intValue());
        onColorChanged(this.mCurrentValue);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mDensity = getContext().getResources().getDisplayMetrics().density;
        setOnPreferenceClickListener(this);
        if (attributeSet != null) {
            this.mAlphaSliderEnabled = attributeSet.getAttributeBooleanValue((String) null, "alphaSlider", false);
            this.mDefaultValue = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "defaultValue", -16777216);
            this.mShowReset = attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/com.android.settings", "showReset", true);
            this.mShowPreview = attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/com.android.settings", "showPreview", true);
            this.mDividerAbove = attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/com.android.settings", "dividerAbove", false);
            this.mDividerBelow = attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/com.android.settings", "dividerBelow", false);
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        this.mView = preferenceViewHolder;
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.setDividerAllowedAbove(this.mDividerAbove);
        preferenceViewHolder.setDividerAllowedBelow(this.mDividerBelow);
        preferenceViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ColorPickerPreference.this.showDialog((Bundle) null);
            }
        });
        this.mWidgetFrameView = (LinearLayout) preferenceViewHolder.findViewById(16908312);
        this.mWidgetFrameView.setOrientation(0);
        this.mWidgetFrameView.setVisibility(0);
        this.mWidgetFrameView.setMinimumWidth(0);
        LinearLayout linearLayout = this.mWidgetFrameView;
        linearLayout.setPadding(linearLayout.getPaddingLeft(), this.mWidgetFrameView.getPaddingTop(), (int) (this.mDensity * 8.0f), this.mWidgetFrameView.getPaddingBottom());
        setDefaultButton();
        setPreviewColor();
    }

    private void setDefaultButton() {
        LinearLayout linearLayout;
        if (this.mShowReset && this.mView != null && (linearLayout = this.mWidgetFrameView) != null) {
            if (linearLayout.getChildCount() > 0) {
                View findViewWithTag = this.mWidgetFrameView.findViewWithTag("default");
                View findViewWithTag2 = this.mWidgetFrameView.findViewWithTag("spacer");
                if (findViewWithTag != null) {
                    this.mWidgetFrameView.removeView(findViewWithTag);
                }
                if (findViewWithTag2 != null) {
                    this.mWidgetFrameView.removeView(findViewWithTag2);
                }
            }
            if (isEnabled()) {
                ImageView imageView = new ImageView(getContext());
                this.mWidgetFrameView.addView(imageView);
                imageView.setImageDrawable(getContext().getDrawable(R$drawable.ic_settings_backup_restore));
                imageView.setTag("default");
                imageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ColorPickerPreference colorPickerPreference = ColorPickerPreference.this;
                        colorPickerPreference.onColorChanged(colorPickerPreference.mDefaultValue);
                        VibrationUtils.doHapticFeedback(ColorPickerPreference.this.mContext, 0);
                    }
                });
                View view = new View(getContext());
                view.setTag("spacer");
                view.setLayoutParams(new LinearLayout.LayoutParams((int) (this.mDensity * 16.0f), -1));
                this.mWidgetFrameView.addView(view);
            }
        }
    }

    private void setPreviewColor() {
        LinearLayout linearLayout;
        View findViewWithTag;
        if (this.mShowPreview && this.mView != null && (linearLayout = this.mWidgetFrameView) != null) {
            if (linearLayout.getChildCount() > 0 && (findViewWithTag = this.mWidgetFrameView.findViewWithTag("preview")) != null) {
                this.mWidgetFrameView.removeView(findViewWithTag);
            }
            if (isEnabled()) {
                ImageView imageView = new ImageView(getContext());
                this.mWidgetFrameView.addView(imageView);
                int dimension = (int) getContext().getResources().getDimension(R$dimen.oval_notification_size);
                int i = this.mCurrentValue;
                if ((i & 15790320) == 15790320) {
                    i -= 1052688;
                }
                imageView.setImageDrawable(createOvalShape(dimension, i - 16777216));
                imageView.setTag("preview");
            }
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setPreviewColor();
        setDefaultButton();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0013 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onColorChanged(int r3) {
        /*
            r2 = this;
            r2.mCurrentValue = r3
            r2.setPreviewColor()
            r2.persistInt(r3)
            androidx.preference.Preference$OnPreferenceChangeListener r0 = r2.getOnPreferenceChangeListener()     // Catch:{ NullPointerException -> 0x0013 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)     // Catch:{ NullPointerException -> 0x0013 }
            r0.onPreferenceChange(r2, r1)     // Catch:{ NullPointerException -> 0x0013 }
        L_0x0013:
            android.widget.EditText r2 = r2.mEditText     // Catch:{ NullPointerException -> 0x001e }
            r0 = 16
            java.lang.String r3 = java.lang.Integer.toString(r3, r0)     // Catch:{ NullPointerException -> 0x001e }
            r2.setText(r3)     // Catch:{ NullPointerException -> 0x001e }
        L_0x001e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.havoc.support.colorpicker.ColorPickerPreference.onColorChanged(int):void");
    }

    /* access modifiers changed from: protected */
    public void showDialog(Bundle bundle) {
        this.mDialog = new ColorPickerDialog(getContext(), this.mCurrentValue);
        this.mDialog.setOnColorChangedListener(this);
        if (this.mAlphaSliderEnabled) {
            this.mDialog.setAlphaSliderVisible(true);
        }
        if (bundle != null) {
            this.mDialog.onRestoreInstanceState(bundle);
        }
        this.mDialog.show();
        this.mDialog.getWindow().setSoftInputMode(2);
    }

    public void setAlphaSliderEnabled(boolean z) {
        this.mAlphaSliderEnabled = z;
    }

    public void setNewPreviewColor(int i) {
        onColorChanged(i);
    }

    public void setDefaultValue(int i) {
        this.mDefaultValue = i;
    }

    public static String convertToARGB(int i) {
        String hexString = Integer.toHexString(Color.alpha(i));
        String hexString2 = Integer.toHexString(Color.red(i));
        String hexString3 = Integer.toHexString(Color.green(i));
        String hexString4 = Integer.toHexString(Color.blue(i));
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        if (hexString2.length() == 1) {
            hexString2 = "0" + hexString2;
        }
        if (hexString3.length() == 1) {
            hexString3 = "0" + hexString3;
        }
        if (hexString4.length() == 1) {
            hexString4 = "0" + hexString4;
        }
        return "#" + hexString + hexString2 + hexString3 + hexString4;
    }

    public static String convertToRGB(int i) {
        String hexString = Integer.toHexString(Color.red(i));
        String hexString2 = Integer.toHexString(Color.green(i));
        String hexString3 = Integer.toHexString(Color.blue(i));
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        if (hexString2.length() == 1) {
            hexString2 = "0" + hexString2;
        }
        if (hexString3.length() == 1) {
            hexString3 = "0" + hexString3;
        }
        return "#" + hexString + hexString2 + hexString3;
    }

    public static int convertToColorInt(String str) throws NumberFormatException {
        int i;
        int i2;
        int i3;
        if (str.startsWith("#")) {
            str = str.replace("#", "");
        }
        int i4 = -1;
        if (str.length() == 8) {
            i4 = Integer.parseInt(str.substring(0, 2), 16);
            i3 = Integer.parseInt(str.substring(2, 4), 16);
            i2 = Integer.parseInt(str.substring(4, 6), 16);
            i = Integer.parseInt(str.substring(6, 8), 16);
        } else if (str.length() == 6) {
            i4 = 255;
            i3 = Integer.parseInt(str.substring(0, 2), 16);
            i2 = Integer.parseInt(str.substring(2, 4), 16);
            i = Integer.parseInt(str.substring(4, 6), 16);
        } else {
            i = -1;
            i3 = -1;
            i2 = -1;
        }
        return Color.argb(i4, i3, i2, i);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        ColorPickerDialog colorPickerDialog = this.mDialog;
        if (colorPickerDialog == null || !colorPickerDialog.isShowing()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.dialogBundle = this.mDialog.onSaveInstanceState();
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        showDialog(savedState.dialogBundle);
    }

    private static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        Bundle dialogBundle;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.dialogBundle = parcel.readBundle();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.dialogBundle);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    private static ShapeDrawable createOvalShape(int i, int i2) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.setIntrinsicHeight(i);
        shapeDrawable.setIntrinsicWidth(i);
        shapeDrawable.getPaint().setColor(i2);
        return shapeDrawable;
    }
}
