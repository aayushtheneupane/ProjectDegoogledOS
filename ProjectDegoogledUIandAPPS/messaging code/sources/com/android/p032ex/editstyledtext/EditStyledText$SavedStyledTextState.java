package com.android.p032ex.editstyledtext;

import android.os.Parcel;
import android.view.View;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.ex.editstyledtext.EditStyledText$SavedStyledTextState */
public class EditStyledText$SavedStyledTextState extends View.BaseSavedState {

    /* renamed from: Re */
    public int f830Re;

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("EditStyledText.SavedState{");
        Pa.append(Integer.toHexString(System.identityHashCode(this)));
        Pa.append(" bgcolor=");
        Pa.append(this.f830Re);
        Pa.append("}");
        return Pa.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f830Re);
    }
}
