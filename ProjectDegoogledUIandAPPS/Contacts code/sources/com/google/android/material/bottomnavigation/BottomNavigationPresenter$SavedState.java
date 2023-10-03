package com.google.android.material.bottomnavigation;

import android.os.Parcel;
import android.os.Parcelable;

class BottomNavigationPresenter$SavedState implements Parcelable {
    public static final Parcelable.Creator<BottomNavigationPresenter$SavedState> CREATOR = new Parcelable.Creator<BottomNavigationPresenter$SavedState>() {
        public BottomNavigationPresenter$SavedState createFromParcel(Parcel parcel) {
            return new BottomNavigationPresenter$SavedState(parcel);
        }

        public BottomNavigationPresenter$SavedState[] newArray(int i) {
            return new BottomNavigationPresenter$SavedState[i];
        }
    };
    int selectedItemId;

    public int describeContents() {
        return 0;
    }

    BottomNavigationPresenter$SavedState() {
    }

    BottomNavigationPresenter$SavedState(Parcel parcel) {
        this.selectedItemId = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.selectedItemId);
    }
}
