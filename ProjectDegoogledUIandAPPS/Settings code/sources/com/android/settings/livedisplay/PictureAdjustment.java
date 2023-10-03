package com.android.settings.livedisplay;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Range;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import com.android.internal.custom.hardware.HSIC;
import com.android.internal.custom.hardware.LiveDisplayManager;
import com.android.settings.IntervalSeekBar;
import com.android.settings.custom.preference.CustomDialogPreference;
import com.havoc.config.center.C1715R;
import java.util.List;

public class PictureAdjustment extends CustomDialogPreference<AlertDialog> {
    private static final int[] SEEKBAR_ID = {C1715R.C1718id.adj_hue_seekbar, C1715R.C1718id.adj_saturation_seekbar, C1715R.C1718id.adj_intensity_seekbar, C1715R.C1718id.adj_contrast_seekbar};
    private static final int[] SEEKBAR_VALUE_ID = {C1715R.C1718id.adj_hue_value, C1715R.C1718id.adj_saturation_value, C1715R.C1718id.adj_intensity_value, C1715R.C1718id.adj_contrast_value};
    private final Context mContext;
    /* access modifiers changed from: private */
    public final float[] mCurrentAdj = new float[5];
    private final LiveDisplayManager mLiveDisplay;
    private final float[] mOriginalAdj = new float[5];
    /* access modifiers changed from: private */
    public final List<Range<Float>> mRanges;
    private ColorSeekBar[] mSeekBars = new ColorSeekBar[SEEKBAR_ID.length];

    public PictureAdjustment(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mLiveDisplay = LiveDisplayManager.getInstance(this.mContext);
        this.mRanges = this.mLiveDisplay.getConfig().getPictureAdjustmentRanges();
        setDialogLayoutResource(C1715R.layout.display_picture_adjustment);
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

    private void updateBars() {
        for (int i = 0; i < SEEKBAR_ID.length; i++) {
            this.mSeekBars[i].setValue(this.mCurrentAdj[i]);
        }
    }

    /* access modifiers changed from: protected */
    public void onBindDialogView(View view) {
        super.onBindDialogView(view);
        int i = 0;
        System.arraycopy(this.mLiveDisplay.getPictureAdjustment().toFloatArray(), 0, this.mOriginalAdj, 0, 5);
        System.arraycopy(this.mOriginalAdj, 0, this.mCurrentAdj, 0, 5);
        while (true) {
            int[] iArr = SEEKBAR_ID;
            if (i < iArr.length) {
                this.mSeekBars[i] = new ColorSeekBar((IntervalSeekBar) view.findViewById(iArr[i]), this.mRanges.get(i), (TextView) view.findViewById(SEEKBAR_VALUE_ID[i]), i);
                i++;
            } else {
                updateBars();
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean onDismissDialog(AlertDialog alertDialog, int i) {
        if (i != -3) {
            return true;
        }
        System.arraycopy(this.mLiveDisplay.getDefaultPictureAdjustment().toFloatArray(), 0, this.mCurrentAdj, 0, 5);
        updateBars();
        updateAdjustment(this.mCurrentAdj);
        return false;
    }

    /* access modifiers changed from: protected */
    public void onDialogClosed(boolean z) {
        super.onDialogClosed(z);
        updateAdjustment(z ? this.mCurrentAdj : this.mOriginalAdj);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (getDialog() == null || !((AlertDialog) getDialog()).isShowing()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.currentAdj = this.mCurrentAdj;
        float[] fArr = this.mOriginalAdj;
        savedState.originalAdj = fArr;
        updateAdjustment(fArr);
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
        System.arraycopy(savedState.originalAdj, 0, this.mOriginalAdj, 0, 5);
        System.arraycopy(savedState.currentAdj, 0, this.mCurrentAdj, 0, 5);
        updateBars();
        updateAdjustment(this.mCurrentAdj);
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
        float[] currentAdj;
        float[] originalAdj;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.originalAdj = parcel.createFloatArray();
            this.currentAdj = parcel.createFloatArray();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloatArray(this.originalAdj);
            parcel.writeFloatArray(this.currentAdj);
        }
    }

    /* access modifiers changed from: private */
    public void updateAdjustment(float[] fArr) {
        this.mLiveDisplay.setPictureAdjustment(HSIC.fromFloatArray(fArr));
    }

    private class ColorSeekBar implements SeekBar.OnSeekBarChangeListener {
        private int mIndex;
        private Range<Float> mRange;
        private final IntervalSeekBar mSeekBar;
        private TextView mValue;

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        public ColorSeekBar(IntervalSeekBar intervalSeekBar, Range<Float> range, TextView textView, int i) {
            this.mSeekBar = intervalSeekBar;
            this.mValue = textView;
            this.mIndex = i;
            this.mRange = range;
            this.mSeekBar.setMinimum(range.getLower().floatValue());
            this.mSeekBar.setMaximum(range.getUpper().floatValue());
            this.mSeekBar.setOnSeekBarChangeListener(this);
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            float progressFloat = ((IntervalSeekBar) seekBar).getProgressFloat();
            if (z) {
                PictureAdjustment.this.mCurrentAdj[this.mIndex] = ((Float) ((Range) PictureAdjustment.this.mRanges.get(this.mIndex)).clamp(Float.valueOf(progressFloat))).floatValue();
                PictureAdjustment pictureAdjustment = PictureAdjustment.this;
                pictureAdjustment.updateAdjustment(pictureAdjustment.mCurrentAdj);
            }
            this.mValue.setText(getLabel(PictureAdjustment.this.mCurrentAdj[this.mIndex]));
        }

        private String getLabel(float f) {
            if (this.mRange.getUpper().floatValue() == 1.0f) {
                return String.format("%d%%", new Object[]{Integer.valueOf(Math.round(f * 100.0f))});
            }
            return String.format("%d", new Object[]{Integer.valueOf(Math.round(f))});
        }

        public void setValue(float f) {
            this.mSeekBar.setProgressFloat(f);
            this.mValue.setText(getLabel(f));
        }
    }
}
