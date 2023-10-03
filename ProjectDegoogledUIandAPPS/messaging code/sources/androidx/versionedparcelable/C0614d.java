package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import p000a.p005b.C0015b;

/* renamed from: androidx.versionedparcelable.d */
class C0614d extends C0613c {

    /* renamed from: Gu */
    private final SparseIntArray f695Gu;

    /* renamed from: Hu */
    private int f696Hu;

    /* renamed from: Iu */
    private int f697Iu;

    /* renamed from: Ju */
    private int f698Ju;
    private final int mEnd;
    private final int mOffset;
    private final Parcel mParcel;
    private final String mPrefix;

    C0614d(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new C0015b(), new C0015b(), new C0015b());
    }

    /* renamed from: Z */
    public boolean mo5296Z(int i) {
        while (this.f697Iu < this.mEnd) {
            int i2 = this.f698Ju;
            if (i2 == i) {
                return true;
            }
            if (String.valueOf(i2).compareTo(String.valueOf(i)) > 0) {
                return false;
            }
            this.mParcel.setDataPosition(this.f697Iu);
            int readInt = this.mParcel.readInt();
            this.f698Ju = this.mParcel.readInt();
            this.f697Iu += readInt;
        }
        if (this.f698Ju == i) {
            return true;
        }
        return false;
    }

    /* renamed from: aa */
    public void mo5301aa(int i) {
        mo5312od();
        this.f696Hu = i;
        this.f695Gu.put(i, this.mParcel.dataPosition());
        this.mParcel.writeInt(0);
        this.mParcel.writeInt(i);
    }

    /* renamed from: b */
    public void mo5302b(Parcelable parcelable) {
        this.mParcel.writeParcelable(parcelable, 0);
    }

    /* renamed from: od */
    public void mo5312od() {
        int i = this.f696Hu;
        if (i >= 0) {
            int i2 = this.f695Gu.get(i);
            int dataPosition = this.mParcel.dataPosition();
            this.mParcel.setDataPosition(i2);
            this.mParcel.writeInt(dataPosition - i2);
            this.mParcel.setDataPosition(dataPosition);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: pd */
    public C0613c mo5313pd() {
        Parcel parcel = this.mParcel;
        int dataPosition = parcel.dataPosition();
        int i = this.f697Iu;
        if (i == this.mOffset) {
            i = this.mEnd;
        }
        int i2 = i;
        return new C0614d(parcel, dataPosition, i2, this.mPrefix + "  ", this.f692Du, this.f693Eu, this.f694Fu);
    }

    /* renamed from: qd */
    public Parcelable mo5315qd() {
        return this.mParcel.readParcelable(C0614d.class.getClassLoader());
    }

    public boolean readBoolean() {
        return this.mParcel.readInt() != 0;
    }

    public byte[] readByteArray() {
        int readInt = this.mParcel.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.mParcel.readByteArray(bArr);
        return bArr;
    }

    /* access modifiers changed from: protected */
    public CharSequence readCharSequence() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.mParcel);
    }

    public int readInt() {
        return this.mParcel.readInt();
    }

    public String readString() {
        return this.mParcel.readString();
    }

    public void writeBoolean(boolean z) {
        this.mParcel.writeInt(z ? 1 : 0);
    }

    public void writeByteArray(byte[] bArr) {
        if (bArr != null) {
            this.mParcel.writeInt(bArr.length);
            this.mParcel.writeByteArray(bArr);
            return;
        }
        this.mParcel.writeInt(-1);
    }

    /* access modifiers changed from: protected */
    public void writeCharSequence(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.mParcel, 0);
    }

    public void writeInt(int i) {
        this.mParcel.writeInt(i);
    }

    public void writeString(String str) {
        this.mParcel.writeString(str);
    }

    private C0614d(Parcel parcel, int i, int i2, String str, C0015b bVar, C0015b bVar2, C0015b bVar3) {
        super(bVar, bVar2, bVar3);
        this.f695Gu = new SparseIntArray();
        this.f696Hu = -1;
        this.f697Iu = 0;
        this.f698Ju = -1;
        this.mParcel = parcel;
        this.mOffset = i;
        this.mEnd = i2;
        this.f697Iu = this.mOffset;
        this.mPrefix = str;
    }
}
