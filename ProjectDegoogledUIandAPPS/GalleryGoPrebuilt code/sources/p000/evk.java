package p000;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: evk */
/* compiled from: PG */
public final class evk extends bil implements evl {
    public evk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.phenotype.internal.IPhenotypeService");
    }

    /* renamed from: a */
    public final void mo5324a(evj evj, String str) {
        Parcel a = mo2085a();
        bin.m2618a(a, (IInterface) evj);
        a.writeString(str);
        mo2088b(5, a);
    }

    /* renamed from: a */
    public final void mo5326a(evj evj, String str, String str2) {
        Parcel a = mo2085a();
        bin.m2618a(a, (IInterface) evj);
        a.writeString(str);
        a.writeString(str2);
        a.writeString((String) null);
        mo2088b(11, a);
    }

    /* renamed from: a */
    public final void mo5325a(evj evj, String str, int i, String[] strArr, byte[] bArr) {
        Parcel a = mo2085a();
        bin.m2618a(a, (IInterface) evj);
        a.writeString(str);
        a.writeInt(i);
        a.writeStringArray(strArr);
        a.writeByteArray(bArr);
        mo2088b(1, a);
    }
}
