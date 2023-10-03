package android.support.p000v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.AbsSavedState;

/* renamed from: android.support.v4.widget.SlidingPaneLayout$SavedState */
class SlidingPaneLayout$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SlidingPaneLayout$SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SlidingPaneLayout$SavedState>() {
        public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new SlidingPaneLayout$SavedState(parcel, (ClassLoader) null);
        }

        public Object[] newArray(int i) {
            return new SlidingPaneLayout$SavedState[i];
        }

        public Object createFromParcel(Parcel parcel) {
            return new SlidingPaneLayout$SavedState(parcel, (ClassLoader) null);
        }
    };
    boolean isOpen;

    SlidingPaneLayout$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.isOpen = parcel.readInt() != 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.isOpen ? 1 : 0);
    }
}
