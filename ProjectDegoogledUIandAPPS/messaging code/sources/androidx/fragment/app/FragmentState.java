package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.lifecycle.Lifecycle$State;

@SuppressLint({"BanParcelableUsage"})
final class FragmentState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0393L();

    /* renamed from: Yo */
    final int f341Yo;
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final boolean mHidden;
    C0424j mInstance;
    final boolean mRemoving;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;
    final String mWho;

    FragmentState(C0424j jVar) {
        this.mClassName = jVar.getClass().getName();
        this.mWho = jVar.mWho;
        this.mFromLayout = jVar.mFromLayout;
        this.mFragmentId = jVar.mFragmentId;
        this.mContainerId = jVar.mContainerId;
        this.mTag = jVar.mTag;
        this.mRetainInstance = jVar.mRetainInstance;
        this.mRemoving = jVar.mRemoving;
        this.mDetached = jVar.mDetached;
        this.mArguments = jVar.mArguments;
        this.mHidden = jVar.mHidden;
        this.f341Yo = jVar.mMaxState.ordinal();
    }

    /* renamed from: a */
    public C0424j mo4063a(ClassLoader classLoader, C0428n nVar) {
        if (this.mInstance == null) {
            Bundle bundle = this.mArguments;
            if (bundle != null) {
                bundle.setClassLoader(classLoader);
            }
            this.mInstance = nVar.mo4424a(classLoader, this.mClassName);
            this.mInstance.setArguments(this.mArguments);
            Bundle bundle2 = this.mSavedFragmentState;
            if (bundle2 != null) {
                bundle2.setClassLoader(classLoader);
                this.mInstance.mSavedFragmentState = this.mSavedFragmentState;
            } else {
                this.mInstance.mSavedFragmentState = new Bundle();
            }
            C0424j jVar = this.mInstance;
            jVar.mWho = this.mWho;
            jVar.mFromLayout = this.mFromLayout;
            jVar.mRestored = true;
            jVar.mFragmentId = this.mFragmentId;
            jVar.mContainerId = this.mContainerId;
            jVar.mTag = this.mTag;
            jVar.mRetainInstance = this.mRetainInstance;
            jVar.mRemoving = this.mRemoving;
            jVar.mDetached = this.mDetached;
            jVar.mHidden = this.mHidden;
            jVar.mMaxState = Lifecycle$State.values()[this.f341Yo];
        }
        return this.mInstance;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.mClassName);
        sb.append(" (");
        sb.append(this.mWho);
        sb.append(")}:");
        if (this.mFromLayout) {
            sb.append(" fromLayout");
        }
        if (this.mContainerId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mContainerId));
        }
        String str = this.mTag;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.mTag);
        }
        if (this.mRetainInstance) {
            sb.append(" retainInstance");
        }
        if (this.mRemoving) {
            sb.append(" removing");
        }
        if (this.mDetached) {
            sb.append(" detached");
        }
        if (this.mHidden) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mClassName);
        parcel.writeString(this.mWho);
        parcel.writeInt(this.mFromLayout ? 1 : 0);
        parcel.writeInt(this.mFragmentId);
        parcel.writeInt(this.mContainerId);
        parcel.writeString(this.mTag);
        parcel.writeInt(this.mRetainInstance ? 1 : 0);
        parcel.writeInt(this.mRemoving ? 1 : 0);
        parcel.writeInt(this.mDetached ? 1 : 0);
        parcel.writeBundle(this.mArguments);
        parcel.writeInt(this.mHidden ? 1 : 0);
        parcel.writeBundle(this.mSavedFragmentState);
        parcel.writeInt(this.f341Yo);
    }

    FragmentState(Parcel parcel) {
        this.mClassName = parcel.readString();
        this.mWho = parcel.readString();
        boolean z = true;
        this.mFromLayout = parcel.readInt() != 0;
        this.mFragmentId = parcel.readInt();
        this.mContainerId = parcel.readInt();
        this.mTag = parcel.readString();
        this.mRetainInstance = parcel.readInt() != 0;
        this.mRemoving = parcel.readInt() != 0;
        this.mDetached = parcel.readInt() != 0;
        this.mArguments = parcel.readBundle();
        this.mHidden = parcel.readInt() == 0 ? false : z;
        this.mSavedFragmentState = parcel.readBundle();
        this.f341Yo = parcel.readInt();
    }
}
