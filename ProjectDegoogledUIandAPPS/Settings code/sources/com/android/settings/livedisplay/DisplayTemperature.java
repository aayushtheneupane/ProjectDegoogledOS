package com.android.settings.livedisplay;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import com.android.internal.custom.hardware.LiveDisplayConfig;
import com.android.internal.custom.hardware.LiveDisplayManager;
import com.android.internal.util.custom.MathUtils;
import com.android.settings.custom.preference.CustomDialogPreference;
import com.havoc.config.center.C1715R;

public class DisplayTemperature extends CustomDialogPreference<AlertDialog> {
    /* access modifiers changed from: private */
    public final LiveDisplayConfig mConfig = this.mLiveDisplay.getConfig();
    /* access modifiers changed from: private */
    public final Context mContext;
    private ColorTemperatureSeekBar mDayTemperature;
    private final LiveDisplayManager mLiveDisplay = LiveDisplayManager.getInstance(this.mContext);
    private ColorTemperatureSeekBar mNightTemperature;
    private int mOriginalDayTemperature;
    private int mOriginalNightTemperature;

    public DisplayTemperature(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        setDialogLayoutResource(C1715R.layout.display_temperature);
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
        this.mOriginalDayTemperature = this.mLiveDisplay.getDayColorTemperature();
        this.mOriginalNightTemperature = this.mLiveDisplay.getNightColorTemperature();
        this.mDayTemperature = new ColorTemperatureSeekBar((SeekBar) view.findViewById(C1715R.C1718id.day_temperature_seekbar), (TextView) view.findViewById(C1715R.C1718id.day_temperature_value));
        this.mNightTemperature = new ColorTemperatureSeekBar((SeekBar) view.findViewById(C1715R.C1718id.night_temperature_seekbar), (TextView) view.findViewById(C1715R.C1718id.night_temperature_value));
        this.mDayTemperature.setTemperature(this.mOriginalDayTemperature);
        this.mNightTemperature.setTemperature(this.mOriginalNightTemperature);
    }

    /* access modifiers changed from: protected */
    public boolean onDismissDialog(AlertDialog alertDialog, int i) {
        if (i != -3) {
            return true;
        }
        this.mDayTemperature.setTemperature(this.mConfig.getDefaultDayTemperature());
        this.mNightTemperature.setTemperature(this.mConfig.getDefaultNightTemperature());
        updateTemperature(true);
        return false;
    }

    /* access modifiers changed from: protected */
    public void onDialogClosed(boolean z) {
        super.onDialogClosed(z);
        updateTemperature(z);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (getDialog() == null || !((AlertDialog) getDialog()).isShowing()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.originalDayTemperature = this.mOriginalDayTemperature;
        savedState.originalNightTemperature = this.mOriginalNightTemperature;
        savedState.currentDayTemperature = this.mDayTemperature.getTemperature();
        savedState.currentNightTemperature = this.mNightTemperature.getTemperature();
        updateTemperature(false);
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
        this.mOriginalDayTemperature = savedState.originalDayTemperature;
        this.mOriginalNightTemperature = savedState.originalNightTemperature;
        this.mDayTemperature.setTemperature(savedState.currentDayTemperature);
        this.mNightTemperature.setTemperature(savedState.currentNightTemperature);
        updateTemperature(true);
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
        int currentDayTemperature;
        int currentNightTemperature;
        int originalDayTemperature;
        int originalNightTemperature;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.originalDayTemperature = parcel.readInt();
            this.originalNightTemperature = parcel.readInt();
            this.currentDayTemperature = parcel.readInt();
            this.currentNightTemperature = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.originalDayTemperature);
            parcel.writeInt(this.originalNightTemperature);
            parcel.writeInt(this.currentDayTemperature);
            parcel.writeInt(this.currentNightTemperature);
        }
    }

    /* access modifiers changed from: private */
    public void updateTemperature(boolean z) {
        int temperature = z ? this.mDayTemperature.getTemperature() : this.mOriginalDayTemperature;
        int temperature2 = z ? this.mNightTemperature.getTemperature() : this.mOriginalNightTemperature;
        callChangeListener(new Integer[]{Integer.valueOf(temperature), Integer.valueOf(temperature2)});
        this.mLiveDisplay.setDayColorTemperature(temperature);
        this.mLiveDisplay.setNightColorTemperature(temperature2);
    }

    /* access modifiers changed from: package-private */
    public int roundUp(int i) {
        return ((i + 50) / 100) * 100;
    }

    private class ColorTemperatureSeekBar implements SeekBar.OnSeekBarChangeListener {
        private final double[] mBalanceCurve;
        private final int mBalanceMax;
        private final int mBalanceMin;
        private final int mBarMax;
        private final int mMax;
        private final int mMin;
        private final SeekBar mSeekBar;
        private final boolean mUseBalance;
        private final TextView mValue;

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        public ColorTemperatureSeekBar(SeekBar seekBar, TextView textView) {
            this.mSeekBar = seekBar;
            this.mValue = textView;
            this.mMin = ((Integer) DisplayTemperature.this.mConfig.getColorTemperatureRange().getLower()).intValue();
            this.mMax = ((Integer) DisplayTemperature.this.mConfig.getColorTemperatureRange().getUpper()).intValue();
            this.mBalanceMin = ((Integer) DisplayTemperature.this.mConfig.getColorBalanceRange().getLower()).intValue();
            this.mBalanceMax = ((Integer) DisplayTemperature.this.mConfig.getColorBalanceRange().getUpper()).intValue();
            this.mUseBalance = DisplayTemperature.this.mConfig.hasFeature(16) && !(this.mBalanceMin == 0 && this.mBalanceMax == 0);
            if (this.mUseBalance) {
                this.mBalanceCurve = MathUtils.powerCurve((double) this.mMin, (double) DisplayTemperature.this.mConfig.getDefaultDayTemperature(), (double) this.mMax);
                this.mBarMax = this.mBalanceMax - this.mBalanceMin;
            } else {
                this.mBalanceCurve = null;
                this.mBarMax = (this.mMax - this.mMin) / 100;
            }
            this.mSeekBar.setMax(this.mBarMax);
            this.mSeekBar.setOnSeekBarChangeListener(this);
            onProgressChanged(this.mSeekBar, this.mSeekBar.getProgress(), false);
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            int i2;
            if (z) {
                DisplayTemperature.this.updateTemperature(true);
            }
            if (this.mUseBalance) {
                i2 = DisplayTemperature.this.roundUp(Math.round((float) MathUtils.linearToPowerCurve(this.mBalanceCurve, ((double) i) / ((double) this.mBarMax))));
            } else {
                i2 = (i * 100) + this.mMin;
            }
            Log.d("DisplayTemperature", "onProgressChanged: progress=" + i + " displayValue=" + i2);
            this.mValue.setText(DisplayTemperature.this.mContext.getResources().getString(C1715R.string.live_display_color_temperature_label, new Object[]{Integer.valueOf(i2)}));
        }

        public void setTemperature(int i) {
            if (this.mUseBalance) {
                this.mSeekBar.setProgress(Math.round((float) (MathUtils.powerCurveToLinear(this.mBalanceCurve, (double) i) * ((double) this.mBarMax))));
                return;
            }
            this.mSeekBar.setProgress(Math.round(((float) (Math.max(i, this.mMin) - this.mMin)) / 100.0f));
        }

        public int getTemperature() {
            if (this.mUseBalance) {
                return Math.round((float) MathUtils.linearToPowerCurve(this.mBalanceCurve, ((double) this.mSeekBar.getProgress()) / ((double) this.mBarMax)));
            }
            return (this.mSeekBar.getProgress() * 100) + this.mMin;
        }
    }
}
