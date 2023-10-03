package android.support.design.widget;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.AbsSavedState;

class BottomNavigationView$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<BottomNavigationView$SavedState> CREATOR = new Parcelable.ClassLoaderCreator<BottomNavigationView$SavedState>() {
        public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new BottomNavigationView$SavedState(parcel, classLoader);
        }

        public Object[] newArray(int i) {
            return new BottomNavigationView$SavedState[i];
        }

        public Object createFromParcel(Parcel parcel) {
            return new BottomNavigationView$SavedState(parcel, (ClassLoader) null);
        }
    };
    Bundle menuPresenterState;

    public BottomNavigationView$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.menuPresenterState = parcel.readBundle(classLoader);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeBundle(this.menuPresenterState);
    }
}
