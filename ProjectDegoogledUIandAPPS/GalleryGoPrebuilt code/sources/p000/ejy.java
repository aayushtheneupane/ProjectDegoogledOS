package p000;

import android.os.RemoteException;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* renamed from: ejy */
/* compiled from: PG */
abstract class ejy extends eqh {

    /* renamed from: a */
    private final int f8459a;

    protected ejy(byte[] bArr) {
        boolean z;
        if (bArr.length == 25) {
            z = true;
        } else {
            z = false;
        }
        abj.m116b(z);
        this.f8459a = Arrays.hashCode(bArr);
    }

    /* renamed from: a */
    public abstract byte[] mo4921a();

    /* renamed from: c */
    public final int mo4923c() {
        return this.f8459a;
    }

    public final int hashCode() {
        return this.f8459a;
    }

    public final boolean equals(Object obj) {
        erf b;
        if (obj == null || !(obj instanceof eqi)) {
            return false;
        }
        try {
            eqi eqi = (eqi) obj;
            if (eqi.mo4923c() != this.f8459a || (b = eqi.mo4922b()) == null) {
                return false;
            }
            return Arrays.equals(mo4921a(), (byte[]) erg.m8052a(b));
        } catch (RemoteException e) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            return false;
        }
    }

    /* renamed from: a */
    protected static byte[] m7648a(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: b */
    public final erf mo4922b() {
        return erg.m8051a((Object) mo4921a());
    }
}
