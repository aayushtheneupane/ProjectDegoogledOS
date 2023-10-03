package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* renamed from: ew */
/* compiled from: PG */
final class C0135ew implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0134ev();

    /* renamed from: a */
    public final int[] f9109a;

    /* renamed from: b */
    public final ArrayList f9110b;

    /* renamed from: c */
    public final int[] f9111c;

    /* renamed from: d */
    public final int[] f9112d;

    /* renamed from: e */
    public final int f9113e;

    /* renamed from: f */
    public final String f9114f;

    /* renamed from: g */
    public final int f9115g;

    /* renamed from: h */
    public final int f9116h;

    /* renamed from: i */
    public final CharSequence f9117i;

    /* renamed from: j */
    public final int f9118j;

    /* renamed from: k */
    public final CharSequence f9119k;

    /* renamed from: l */
    public final ArrayList f9120l;

    /* renamed from: m */
    public final ArrayList f9121m;

    /* renamed from: n */
    public final boolean f9122n;

    public final int describeContents() {
        return 0;
    }

    public C0135ew(Parcel parcel) {
        this.f9109a = parcel.createIntArray();
        this.f9110b = parcel.createStringArrayList();
        this.f9111c = parcel.createIntArray();
        this.f9112d = parcel.createIntArray();
        this.f9113e = parcel.readInt();
        this.f9114f = parcel.readString();
        this.f9115g = parcel.readInt();
        this.f9116h = parcel.readInt();
        this.f9117i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f9118j = parcel.readInt();
        this.f9119k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f9120l = parcel.createStringArrayList();
        this.f9121m = parcel.createStringArrayList();
        this.f9122n = parcel.readInt() != 0;
    }

    public C0135ew(C0133eu euVar) {
        String str;
        int size = euVar.f11644c.size();
        this.f9109a = new int[(size * 5)];
        if (euVar.f11650i) {
            this.f9110b = new ArrayList(size);
            this.f9111c = new int[size];
            this.f9112d = new int[size];
            int i = 0;
            int i2 = 0;
            while (i < size) {
                C0180gm gmVar = (C0180gm) euVar.f11644c.get(i);
                int i3 = i2 + 1;
                this.f9109a[i2] = gmVar.f11606a;
                ArrayList arrayList = this.f9110b;
                C0147fh fhVar = gmVar.f11607b;
                if (fhVar != null) {
                    str = fhVar.f9591j;
                } else {
                    str = null;
                }
                arrayList.add(str);
                int[] iArr = this.f9109a;
                int i4 = i3 + 1;
                iArr[i3] = gmVar.f11608c;
                int i5 = i4 + 1;
                iArr[i4] = gmVar.f11609d;
                int i6 = i5 + 1;
                iArr[i5] = gmVar.f11610e;
                iArr[i6] = gmVar.f11611f;
                this.f9111c[i] = gmVar.f11612g.ordinal();
                this.f9112d[i] = gmVar.f11613h.ordinal();
                i++;
                i2 = i6 + 1;
            }
            this.f9113e = euVar.f11649h;
            this.f9114f = euVar.f11652k;
            this.f9115g = euVar.f9019b;
            this.f9116h = euVar.f11653l;
            this.f9117i = euVar.f11654m;
            this.f9118j = euVar.f11655n;
            this.f9119k = euVar.f11656o;
            this.f9120l = euVar.f11657p;
            this.f9121m = euVar.f11658q;
            this.f9122n = euVar.f11659r;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f9109a);
        parcel.writeStringList(this.f9110b);
        parcel.writeIntArray(this.f9111c);
        parcel.writeIntArray(this.f9112d);
        parcel.writeInt(this.f9113e);
        parcel.writeString(this.f9114f);
        parcel.writeInt(this.f9115g);
        parcel.writeInt(this.f9116h);
        TextUtils.writeToParcel(this.f9117i, parcel, 0);
        parcel.writeInt(this.f9118j);
        TextUtils.writeToParcel(this.f9119k, parcel, 0);
        parcel.writeStringList(this.f9120l);
        parcel.writeStringList(this.f9121m);
        parcel.writeInt(this.f9122n ? 1 : 0);
    }
}
