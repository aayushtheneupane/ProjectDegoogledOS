package android.support.p000v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import com.android.tools.p006r8.GeneratedOutlineSupport;

/* renamed from: android.support.v4.app.FragmentTabHost$SavedState */
class FragmentTabHost$SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<FragmentTabHost$SavedState> CREATOR = new Parcelable.Creator<FragmentTabHost$SavedState>() {
        public Object createFromParcel(Parcel parcel) {
            return new FragmentTabHost$SavedState(parcel);
        }

        public Object[] newArray(int i) {
            return new FragmentTabHost$SavedState[i];
        }
    };
    String curTab;

    FragmentTabHost$SavedState(Parcel parcel) {
        super(parcel);
        this.curTab = parcel.readString();
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("FragmentTabHost.SavedState{");
        outline13.append(Integer.toHexString(System.identityHashCode(this)));
        outline13.append(" curTab=");
        return GeneratedOutlineSupport.outline12(outline13, this.curTab, "}");
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.curTab);
    }
}
