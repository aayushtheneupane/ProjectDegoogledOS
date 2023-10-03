package com.google.android.material.bottomnavigation;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;

class BottomNavigationView$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<BottomNavigationView$SavedState> CREATOR = new Parcelable.ClassLoaderCreator<BottomNavigationView$SavedState>() {
        public BottomNavigationView$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new BottomNavigationView$SavedState(parcel, classLoader);
        }

        public BottomNavigationView$SavedState createFromParcel(Parcel parcel) {
            return new BottomNavigationView$SavedState(parcel, (ClassLoader) null);
        }

        public BottomNavigationView$SavedState[] newArray(int i) {
            return new BottomNavigationView$SavedState[i];
        }
    };
    Bundle menuPresenterState;

    public BottomNavigationView$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        readFromParcel(parcel, classLoader);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeBundle(this.menuPresenterState);
    }

    private void readFromParcel(Parcel parcel, ClassLoader classLoader) {
        this.menuPresenterState = parcel.readBundle(classLoader);
    }
}
