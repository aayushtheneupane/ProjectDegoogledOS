package com.android.p003ex.editstyledtext;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.text.NoCopySpan;
import android.text.Selection;
import android.view.View;
import android.widget.EditText;

/* renamed from: com.android.ex.editstyledtext.EditStyledText */
public class EditStyledText extends EditText {
    private static final NoCopySpan.Concrete SELECTING = new NoCopySpan.Concrete();

    /* renamed from: com.android.ex.editstyledtext.EditStyledText$SoftKeyReceiver */
    private static class SoftKeyReceiver extends ResultReceiver {
        EditStyledText mEST;
        int mNewEnd;
        int mNewStart;

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            if (i != 2) {
                Selection.setSelection(this.mEST.getText(), this.mNewStart, this.mNewEnd);
            }
        }
    }

    /* renamed from: com.android.ex.editstyledtext.EditStyledText$SavedStyledTextState */
    public static class SavedStyledTextState extends View.BaseSavedState {
        public int mBackgroundColor;

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mBackgroundColor);
        }

        public String toString() {
            return "EditStyledText.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " bgcolor=" + this.mBackgroundColor + "}";
        }
    }
}
