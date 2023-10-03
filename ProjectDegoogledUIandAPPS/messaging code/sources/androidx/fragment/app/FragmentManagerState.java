package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

@SuppressLint({"BanParcelableUsage"})
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0390I();

    /* renamed from: Xo */
    String f340Xo = null;
    ArrayList mActive;
    ArrayList mAdded;
    BackStackState[] mBackStack;
    int mNextFragmentIndex;

    public FragmentManagerState() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mActive);
        parcel.writeStringList(this.mAdded);
        parcel.writeTypedArray(this.mBackStack, i);
        parcel.writeString(this.f340Xo);
        parcel.writeInt(this.mNextFragmentIndex);
    }

    public FragmentManagerState(Parcel parcel) {
        this.mActive = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.mAdded = parcel.createStringArrayList();
        this.mBackStack = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.f340Xo = parcel.readString();
        this.mNextFragmentIndex = parcel.readInt();
    }
}
