package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import p026b.p027a.p030b.p031a.C0632a;

class FragmentTabHost$SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator CREATOR = new C0394M();

    /* renamed from: Qe */
    String f342Qe;

    FragmentTabHost$SavedState(Parcel parcel) {
        super(parcel);
        this.f342Qe = parcel.readString();
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("FragmentTabHost.SavedState{");
        Pa.append(Integer.toHexString(System.identityHashCode(this)));
        Pa.append(" curTab=");
        Pa.append(this.f342Qe);
        Pa.append("}");
        return Pa.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f342Qe);
    }
}
