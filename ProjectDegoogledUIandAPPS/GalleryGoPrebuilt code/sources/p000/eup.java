package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: eup */
/* compiled from: PG */
public final class eup extends eqv {
    public static final Parcelable.Creator CREATOR = new euq();

    /* renamed from: a */
    private static final byte[][] f9053a = new byte[0][];

    /* renamed from: b */
    private final String f9054b;

    /* renamed from: c */
    private final byte[] f9055c;

    /* renamed from: d */
    private final byte[][] f9056d;

    /* renamed from: e */
    private final byte[][] f9057e;

    /* renamed from: f */
    private final byte[][] f9058f;

    /* renamed from: g */
    private final byte[][] f9059g;

    /* renamed from: h */
    private final int[] f9060h;

    /* renamed from: i */
    private final byte[][] f9061i;

    /* renamed from: j */
    private final int[] f9062j;

    static {
        byte[][] bArr = f9053a;
        new eup("", (byte[]) null, bArr, bArr, bArr, bArr, (int[]) null, (byte[][]) null, (int[]) null);
    }

    public eup(String str, byte[] bArr, byte[][] bArr2, byte[][] bArr3, byte[][] bArr4, byte[][] bArr5, int[] iArr, byte[][] bArr6, int[] iArr2) {
        this.f9054b = str;
        this.f9055c = bArr;
        this.f9056d = bArr2;
        this.f9057e = bArr3;
        this.f9058f = bArr4;
        this.f9059g = bArr5;
        this.f9060h = iArr;
        this.f9061i = bArr6;
        this.f9062j = iArr2;
    }

    /* renamed from: a */
    private static List m8178a(int[] iArr) {
        if (iArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(r0);
        for (int valueOf : iArr) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /* renamed from: a */
    private static List m8179a(byte[][] bArr) {
        if (bArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(r0);
        for (byte[] encodeToString : bArr) {
            arrayList.add(Base64.encodeToString(encodeToString, 3));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof eup) {
            eup eup = (eup) obj;
            if (!fej.m8701a((Object) this.f9054b, (Object) eup.f9054b) || !Arrays.equals(this.f9055c, eup.f9055c) || !fej.m8701a((Object) m8179a(this.f9056d), (Object) m8179a(eup.f9056d)) || !fej.m8701a((Object) m8179a(this.f9057e), (Object) m8179a(eup.f9057e)) || !fej.m8701a((Object) m8179a(this.f9058f), (Object) m8179a(eup.f9058f)) || !fej.m8701a((Object) m8179a(this.f9059g), (Object) m8179a(eup.f9059g)) || !fej.m8701a((Object) m8178a(this.f9060h), (Object) m8178a(eup.f9060h)) || !fej.m8701a((Object) m8179a(this.f9061i), (Object) m8179a(eup.f9061i)) || !fej.m8701a((Object) m8181b(this.f9062j), (Object) m8181b(eup.f9062j))) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private static List m8181b(int[] iArr) {
        if (iArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(iArr.length >> 1);
        for (int i = 0; i < iArr.length; i += 2) {
            arrayList.add(new euy(iArr[i], iArr[i + 1]));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("ExperimentTokens");
        sb.append("(");
        String str2 = this.f9054b;
        if (str2 != null) {
            StringBuilder sb2 = new StringBuilder(str2.length() + 2);
            sb2.append("'");
            sb2.append(str2);
            sb2.append("'");
            str = sb2.toString();
        } else {
            str = "null";
        }
        sb.append(str);
        byte[] bArr = this.f9055c;
        sb.append(", direct==");
        if (bArr == null) {
            sb.append("null");
        } else {
            sb.append("'");
            sb.append(Base64.encodeToString(bArr, 3));
            sb.append("'");
        }
        sb.append(", ");
        m8180a(sb, "GAIA=", this.f9056d);
        sb.append(", ");
        m8180a(sb, "PSEUDO=", this.f9057e);
        sb.append(", ");
        m8180a(sb, "ALWAYS=", this.f9058f);
        sb.append(", ");
        m8180a(sb, "OTHER=", this.f9059g);
        sb.append(", weak=");
        sb.append(Arrays.toString(this.f9060h));
        sb.append(", ");
        m8180a(sb, "directs=", this.f9061i);
        sb.append(", genDims=");
        sb.append(Arrays.toString(m8181b(this.f9062j).toArray()));
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    private static void m8180a(StringBuilder sb, String str, byte[][] bArr) {
        sb.append(str);
        sb.append("=");
        if (bArr == null) {
            sb.append("null");
            return;
        }
        sb.append("(");
        int length = bArr.length;
        boolean z = true;
        int i = 0;
        while (i < length) {
            byte[] bArr2 = bArr[i];
            if (!z) {
                sb.append(", ");
            }
            sb.append("'");
            sb.append(Base64.encodeToString(bArr2, 3));
            sb.append("'");
            i++;
            z = false;
        }
        sb.append(")");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m98a(parcel, 2, this.f9054b);
        abj.m101a(parcel, 3, this.f9055c);
        abj.m105a(parcel, 4, this.f9056d);
        abj.m105a(parcel, 5, this.f9057e);
        abj.m105a(parcel, 6, this.f9058f);
        abj.m105a(parcel, 7, this.f9059g);
        abj.m102a(parcel, 8, this.f9060h);
        abj.m105a(parcel, 9, this.f9061i);
        abj.m102a(parcel, 10, this.f9062j);
        abj.m92a(parcel, a);
    }
}
