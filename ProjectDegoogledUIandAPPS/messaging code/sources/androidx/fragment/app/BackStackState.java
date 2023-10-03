package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.lifecycle.Lifecycle$State;
import java.util.ArrayList;

@SuppressLint({"BanParcelableUsage"})
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0409b();
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int mIndex;
    final String mName;
    final int[] mOps;
    final boolean mReorderingAllowed;
    final ArrayList mSharedElementSourceNames;
    final ArrayList mSharedElementTargetNames;
    final int mTransition;
    final int mTransitionStyle;

    /* renamed from: oo */
    final ArrayList f336oo;

    /* renamed from: po */
    final int[] f337po;

    /* renamed from: qo */
    final int[] f338qo;

    public BackStackState(C0407a aVar) {
        int size = aVar.mOps.size();
        this.mOps = new int[(size * 5)];
        if (aVar.mAddToBackStack) {
            this.f336oo = new ArrayList(size);
            this.f337po = new int[size];
            this.f338qo = new int[size];
            int i = 0;
            int i2 = 0;
            while (i < size) {
                C0395N n = (C0395N) aVar.mOps.get(i);
                int i3 = i2 + 1;
                this.mOps[i2] = n.mCmd;
                ArrayList arrayList = this.f336oo;
                C0424j jVar = n.f358Zo;
                arrayList.add(jVar != null ? jVar.mWho : null);
                int[] iArr = this.mOps;
                int i4 = i3 + 1;
                iArr[i3] = n.mEnterAnim;
                int i5 = i4 + 1;
                iArr[i4] = n.mExitAnim;
                int i6 = i5 + 1;
                iArr[i5] = n.mPopEnterAnim;
                iArr[i6] = n.mPopExitAnim;
                this.f337po[i] = n.f359_o.ordinal();
                this.f338qo[i] = n.f360ap.ordinal();
                i++;
                i2 = i6 + 1;
            }
            this.mTransition = aVar.mTransition;
            this.mTransitionStyle = aVar.mTransitionStyle;
            this.mName = aVar.mName;
            this.mIndex = aVar.mIndex;
            this.mBreadCrumbTitleRes = aVar.mBreadCrumbTitleRes;
            this.mBreadCrumbTitleText = aVar.mBreadCrumbTitleText;
            this.mBreadCrumbShortTitleRes = aVar.mBreadCrumbShortTitleRes;
            this.mBreadCrumbShortTitleText = aVar.mBreadCrumbShortTitleText;
            this.mSharedElementSourceNames = aVar.mSharedElementSourceNames;
            this.mSharedElementTargetNames = aVar.mSharedElementTargetNames;
            this.mReorderingAllowed = aVar.mReorderingAllowed;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    /* renamed from: a */
    public C0407a mo3998a(C0389H h) {
        C0407a aVar = new C0407a(h);
        int i = 0;
        int i2 = 0;
        while (i < this.mOps.length) {
            C0395N n = new C0395N();
            int i3 = i + 1;
            n.mCmd = this.mOps[i];
            String str = (String) this.f336oo.get(i2);
            if (str != null) {
                n.f358Zo = (C0424j) h.mActive.get(str);
            } else {
                n.f358Zo = null;
            }
            n.f359_o = Lifecycle$State.values()[this.f337po[i2]];
            n.f360ap = Lifecycle$State.values()[this.f338qo[i2]];
            int[] iArr = this.mOps;
            int i4 = i3 + 1;
            n.mEnterAnim = iArr[i3];
            int i5 = i4 + 1;
            n.mExitAnim = iArr[i4];
            int i6 = i5 + 1;
            n.mPopEnterAnim = iArr[i5];
            n.mPopExitAnim = iArr[i6];
            aVar.mEnterAnim = n.mEnterAnim;
            aVar.mExitAnim = n.mExitAnim;
            aVar.mPopEnterAnim = n.mPopEnterAnim;
            aVar.mPopExitAnim = n.mPopExitAnim;
            aVar.mo4177a(n);
            i2++;
            i = i6 + 1;
        }
        aVar.mTransition = this.mTransition;
        aVar.mTransitionStyle = this.mTransitionStyle;
        aVar.mName = this.mName;
        aVar.mIndex = this.mIndex;
        aVar.mAddToBackStack = true;
        aVar.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
        aVar.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
        aVar.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
        aVar.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
        aVar.mSharedElementSourceNames = this.mSharedElementSourceNames;
        aVar.mSharedElementTargetNames = this.mSharedElementTargetNames;
        aVar.mReorderingAllowed = this.mReorderingAllowed;
        aVar.bumpBackStackNesting(1);
        return aVar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.mOps);
        parcel.writeStringList(this.f336oo);
        parcel.writeIntArray(this.f337po);
        parcel.writeIntArray(this.f338qo);
        parcel.writeInt(this.mTransition);
        parcel.writeInt(this.mTransitionStyle);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mIndex);
        parcel.writeInt(this.mBreadCrumbTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbTitleText, parcel, 0);
        parcel.writeInt(this.mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, parcel, 0);
        parcel.writeStringList(this.mSharedElementSourceNames);
        parcel.writeStringList(this.mSharedElementTargetNames);
        parcel.writeInt(this.mReorderingAllowed ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.mOps = parcel.createIntArray();
        this.f336oo = parcel.createStringArrayList();
        this.f337po = parcel.createIntArray();
        this.f338qo = parcel.createIntArray();
        this.mTransition = parcel.readInt();
        this.mTransitionStyle = parcel.readInt();
        this.mName = parcel.readString();
        this.mIndex = parcel.readInt();
        this.mBreadCrumbTitleRes = parcel.readInt();
        this.mBreadCrumbTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mBreadCrumbShortTitleRes = parcel.readInt();
        this.mBreadCrumbShortTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mSharedElementSourceNames = parcel.createStringArrayList();
        this.mSharedElementTargetNames = parcel.createStringArrayList();
        this.mReorderingAllowed = parcel.readInt() != 0;
    }
}
