package androidx.drawerlayout.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;

public class DrawerLayout$SavedState extends AbsSavedState {
    public static final Parcelable.Creator CREATOR = new C0380a();

    /* renamed from: io */
    int f329io = 0;

    /* renamed from: jo */
    int f330jo;

    /* renamed from: ko */
    int f331ko;

    /* renamed from: lo */
    int f332lo;

    /* renamed from: mo */
    int f333mo;

    public DrawerLayout$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f329io = parcel.readInt();
        this.f330jo = parcel.readInt();
        this.f331ko = parcel.readInt();
        this.f332lo = parcel.readInt();
        this.f333mo = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f329io);
        parcel.writeInt(this.f330jo);
        parcel.writeInt(this.f331ko);
        parcel.writeInt(this.f332lo);
        parcel.writeInt(this.f333mo);
    }
}
