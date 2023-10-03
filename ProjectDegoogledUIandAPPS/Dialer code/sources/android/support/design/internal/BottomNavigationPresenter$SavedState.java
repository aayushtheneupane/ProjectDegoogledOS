package android.support.design.internal;

import android.os.Parcel;
import android.os.Parcelable;

class BottomNavigationPresenter$SavedState implements Parcelable {
    public static final Parcelable.Creator<BottomNavigationPresenter$SavedState> CREATOR = new Parcelable.Creator<BottomNavigationPresenter$SavedState>() {
        public Object createFromParcel(Parcel parcel) {
            return new BottomNavigationPresenter$SavedState(parcel);
        }

        public Object[] newArray(int i) {
            return new BottomNavigationPresenter$SavedState[i];
        }
    };
    int selectedItemId;

    BottomNavigationPresenter$SavedState() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.selectedItemId);
    }

    BottomNavigationPresenter$SavedState(Parcel parcel) {
        this.selectedItemId = parcel.readInt();
    }
}
