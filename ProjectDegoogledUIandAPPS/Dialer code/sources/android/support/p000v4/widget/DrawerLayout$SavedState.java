package android.support.p000v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.AbsSavedState;

/* renamed from: android.support.v4.widget.DrawerLayout$SavedState */
public class DrawerLayout$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<DrawerLayout$SavedState> CREATOR = new Parcelable.ClassLoaderCreator<DrawerLayout$SavedState>() {
        public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new DrawerLayout$SavedState(parcel, classLoader);
        }

        public Object[] newArray(int i) {
            return new DrawerLayout$SavedState[i];
        }

        public Object createFromParcel(Parcel parcel) {
            return new DrawerLayout$SavedState(parcel, (ClassLoader) null);
        }
    };
    int lockModeEnd;
    int lockModeLeft;
    int lockModeRight;
    int lockModeStart;
    int openDrawerGravity = 0;

    public DrawerLayout$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.openDrawerGravity = parcel.readInt();
        this.lockModeLeft = parcel.readInt();
        this.lockModeRight = parcel.readInt();
        this.lockModeStart = parcel.readInt();
        this.lockModeEnd = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.openDrawerGravity);
        parcel.writeInt(this.lockModeLeft);
        parcel.writeInt(this.lockModeRight);
        parcel.writeInt(this.lockModeStart);
        parcel.writeInt(this.lockModeEnd);
    }
}
