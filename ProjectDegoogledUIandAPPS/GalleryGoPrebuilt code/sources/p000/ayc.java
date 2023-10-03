package p000;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

/* renamed from: ayc */
/* compiled from: PG */
public final class ayc implements axo {

    /* renamed from: a */
    private final axo f1857a;

    /* renamed from: b */
    private final Resources f1858b;

    public ayc(Resources resources, axo axo) {
        this.f1858b = resources;
        this.f1857a = axo;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ axn mo1697a(Object obj, int i, int i2, aqz aqz) {
        Uri a = m1885a((Integer) obj);
        if (a != null) {
            return this.f1857a.mo1697a(a, i, i2, aqz);
        }
        return null;
    }

    /* renamed from: a */
    private final Uri m1885a(Integer num) {
        try {
            String resourcePackageName = this.f1858b.getResourcePackageName(num.intValue());
            String resourceTypeName = this.f1858b.getResourceTypeName(num.intValue());
            String resourceEntryName = this.f1858b.getResourceEntryName(num.intValue());
            StringBuilder sb = new StringBuilder(String.valueOf(resourcePackageName).length() + 21 + String.valueOf(resourceTypeName).length() + String.valueOf(resourceEntryName).length());
            sb.append("android.resource://");
            sb.append(resourcePackageName);
            sb.append('/');
            sb.append(resourceTypeName);
            sb.append('/');
            sb.append(resourceEntryName);
            return Uri.parse(sb.toString());
        } catch (Resources.NotFoundException e) {
            if (!Log.isLoggable("ResourceLoader", 5)) {
                return null;
            }
            String valueOf = String.valueOf(num);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 30);
            sb2.append("Received invalid resource id: ");
            sb2.append(valueOf);
            Log.w("ResourceLoader", sb2.toString(), e);
            return null;
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1698a(Object obj) {
        Integer num = (Integer) obj;
        return true;
    }
}
