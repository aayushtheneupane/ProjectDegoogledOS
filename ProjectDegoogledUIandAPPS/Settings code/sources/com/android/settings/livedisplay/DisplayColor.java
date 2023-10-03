package com.android.settings.livedisplay;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import com.android.internal.custom.hardware.LiveDisplayManager;
import com.android.settings.IntervalSeekBar;
import com.android.settings.custom.preference.CustomDialogPreference;
import com.havoc.config.center.C1715R;

public class DisplayColor extends CustomDialogPreference<AlertDialog> {
    private static final int[] SEEKBAR_ID = {C1715R.C1718id.color_red_seekbar, C1715R.C1718id.color_green_seekbar, C1715R.C1718id.color_blue_seekbar};
    private static final int[] SEEKBAR_VALUE_ID = {C1715R.C1718id.color_red_value, C1715R.C1718id.color_green_value, C1715R.C1718id.color_blue_value};
    private final Context mContext;
    /* access modifiers changed from: private */
    public final float[] mCurrentColors = new float[3];
    private final LiveDisplayManager mLiveDisplay;
    private final float[] mOriginalColors = new float[3];
    private ColorSeekBar[] mSeekBars = new ColorSeekBar[SEEKBAR_ID.length];

    public DisplayColor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mLiveDisplay = LiveDisplayManager.getInstance(this.mContext);
        setDialogLayoutResource(C1715R.layout.display_color_calibration);
    }

    /* access modifiers changed from: protected */
    public void onPrepareDialogBuilder(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
        super.onPrepareDialogBuilder(builder, onClickListener);
        builder.setNeutralButton(17041004, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onBindDialogView(View view) {
        super.onBindDialogView(view);
        System.arraycopy(this.mLiveDisplay.getColorAdjustment(), 0, this.mOriginalColors, 0, 3);
        System.arraycopy(this.mOriginalColors, 0, this.mCurrentColors, 0, 3);
        int i = 0;
        while (true) {
            int[] iArr = SEEKBAR_ID;
            if (i < iArr.length) {
                TextView textView = (TextView) view.findViewById(SEEKBAR_VALUE_ID[i]);
                this.mSeekBars[i] = new ColorSeekBar((IntervalSeekBar) view.findViewById(iArr[i]), textView, i);
                this.mSeekBars[i].mSeekBar.setMinimum(0.1f);
                this.mSeekBars[i].mSeekBar.setMaximum(1.0f);
                this.mSeekBars[i].mSeekBar.setProgressFloat(this.mCurrentColors[i]);
                textView.setText(String.format("%d%%", new Object[]{Integer.valueOf(Math.round(this.mCurrentColors[i] * 100.0f))}));
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean onDismissDialog(AlertDialog alertDialog, int i) {
        if (i != -3) {
            return true;
        }
        int i2 = 0;
        while (true) {
            ColorSeekBar[] colorSeekBarArr = this.mSeekBars;
            if (i2 < colorSeekBarArr.length) {
                colorSeekBarArr[i2].mSeekBar.setProgressFloat(1.0f);
                this.mCurrentColors[i2] = 1.0f;
                i2++;
            } else {
                updateColors(this.mCurrentColors);
                return false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDialogClosed(boolean z) {
        super.onDialogClosed(z);
        updateColors(z ? this.mCurrentColors : this.mOriginalColors);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (getDialog() == null || !((AlertDialog) getDialog()).isShowing()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.currentColors = this.mCurrentColors;
        float[] fArr = this.mOriginalColors;
        savedState.originalColors = fArr;
        updateColors(fArr);
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        int i = 0;
        System.arraycopy(savedState.originalColors, 0, this.mOriginalColors, 0, 3);
        System.arraycopy(savedState.currentColors, 0, this.mCurrentColors, 0, 3);
        while (true) {
            ColorSeekBar[] colorSeekBarArr = this.mSeekBars;
            if (i < colorSeekBarArr.length) {
                colorSeekBarArr[i].mSeekBar.setProgressFloat(this.mCurrentColors[i]);
                i++;
            } else {
                updateColors(this.mCurrentColors);
                return;
            }
        }
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
        float[] currentColors;
        float[] originalColors;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.originalColors = parcel.createFloatArray();
            this.currentColors = parcel.createFloatArray();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloatArray(this.originalColors);
            parcel.writeFloatArray(this.currentColors);
        }
    }

    /* access modifiers changed from: private */
    public void updateColors(float[] fArr) {
        this.mLiveDisplay.setColorAdjustment(fArr);
    }

    private class ColorSeekBar implements SeekBar.OnSeekBarChangeListener {
        private int mIndex;
        /* access modifiers changed from: private */
        public final IntervalSeekBar mSeekBar;
        private TextView mValue;

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        public ColorSeekBar(IntervalSeekBar intervalSeekBar, TextView textView, int i) {
            this.mSeekBar = intervalSeekBar;
            this.mValue = textView;
            this.mIndex = i;
            this.mSeekBar.setOnSeekBarChangeListener(this);
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            float progressFloat = ((IntervalSeekBar) seekBar).getProgressFloat();
            if (z) {
                float[] access$100 = DisplayColor.this.mCurrentColors;
                int i2 = this.mIndex;
                float f = 1.0f;
                if (progressFloat <= 1.0f) {
                    f = progressFloat;
                }
                access$100[i2] = f;
                DisplayColor displayColor = DisplayColor.this;
                displayColor.updateColors(displayColor.mCurrentColors);
            }
            int round = Math.round(progressFloat * 100.0f);
            this.mValue.setText(String.format("%d%%", new Object[]{Integer.valueOf(round)}));
        }
    }
}
