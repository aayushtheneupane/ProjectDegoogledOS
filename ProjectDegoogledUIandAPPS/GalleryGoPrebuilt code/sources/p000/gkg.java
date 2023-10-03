package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* renamed from: gkg */
/* compiled from: PG */
public final class gkg extends C0313lj {
    public static final Parcelable.Creator CREATOR = new gkf();

    /* renamed from: c */
    public CharSequence f11538c;

    /* renamed from: d */
    public boolean f11539d;

    public gkg(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f11538c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f11539d = parcel.readInt() != 1 ? false : true;
    }

    public gkg(Parcelable parcelable) {
        super(parcelable);
    }

    public final String toString() {
        String hexString = Integer.toHexString(System.identityHashCode(this));
        String valueOf = String.valueOf(this.f11538c);
        StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 35 + String.valueOf(valueOf).length());
        sb.append("TextInputLayout.SavedState{");
        sb.append(hexString);
        sb.append(" error=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        TextUtils.writeToParcel(this.f11538c, parcel, i);
        parcel.writeInt(this.f11539d ? 1 : 0);
    }
}
