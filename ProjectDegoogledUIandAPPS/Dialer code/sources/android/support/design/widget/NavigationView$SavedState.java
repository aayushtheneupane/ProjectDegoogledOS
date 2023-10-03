package android.support.design.widget;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.AbsSavedState;

public class NavigationView$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<NavigationView$SavedState> CREATOR = new Parcelable.ClassLoaderCreator<NavigationView$SavedState>() {
        public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new NavigationView$SavedState(parcel, classLoader);
        }

        public Object[] newArray(int i) {
            return new NavigationView$SavedState[i];
        }

        public Object createFromParcel(Parcel parcel) {
            return new NavigationView$SavedState(parcel, (ClassLoader) null);
        }
    };
    public Bundle menuState;

    public NavigationView$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.menuState = parcel.readBundle(classLoader);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeBundle(this.menuState);
    }
}
