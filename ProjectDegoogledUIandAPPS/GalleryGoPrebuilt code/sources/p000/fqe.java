package p000;

import android.util.Base64;
import android.util.Log;
import java.io.IOException;

/* renamed from: fqe */
/* compiled from: PG */
public final class fqe extends fqg {
    public fqe(fqf fqf, String str, Object obj) {
        super(fqf, str, obj);
    }

    /* renamed from: a */
    public final Object mo6026a(Object obj) {
        if (obj instanceof String) {
            try {
                return (inv) iix.m13603a((iix) inv.f14593b, Base64.decode((String) obj, 3));
            } catch (IOException | IllegalArgumentException e) {
            }
        }
        String b = super.mo6027b();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(b).length() + 27 + String.valueOf(valueOf).length());
        sb.append("Invalid byte[] value for ");
        sb.append(b);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
