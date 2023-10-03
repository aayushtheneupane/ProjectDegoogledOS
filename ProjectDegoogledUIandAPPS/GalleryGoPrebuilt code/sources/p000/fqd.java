package p000;

import android.util.Log;

/* renamed from: fqd */
/* compiled from: PG */
final class fqd extends fqg {
    public fqd(fqf fqf, String str, Boolean bool) {
        super(fqf, str, bool);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo6026a(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (exi.f9171b.matcher(str).matches()) {
                return true;
            }
            if (exi.f9172c.matcher(str).matches()) {
                return false;
            }
        }
        String b = super.mo6027b();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(b).length() + 28 + String.valueOf(valueOf).length());
        sb.append("Invalid boolean value for ");
        sb.append(b);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
