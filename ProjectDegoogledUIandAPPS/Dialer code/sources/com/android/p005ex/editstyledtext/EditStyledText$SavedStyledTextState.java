package com.android.p005ex.editstyledtext;

import android.os.Parcel;
import android.view.View;
import com.android.tools.p006r8.GeneratedOutlineSupport;

/* renamed from: com.android.ex.editstyledtext.EditStyledText$SavedStyledTextState */
public class EditStyledText$SavedStyledTextState extends View.BaseSavedState {
    public int mBackgroundColor;

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("EditStyledText.SavedState{");
        outline13.append(Integer.toHexString(System.identityHashCode(this)));
        outline13.append(" bgcolor=");
        outline13.append(this.mBackgroundColor);
        outline13.append("}");
        return outline13.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mBackgroundColor);
    }
}
