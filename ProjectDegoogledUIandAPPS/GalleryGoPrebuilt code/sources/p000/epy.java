package p000;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import java.util.Arrays;

/* renamed from: epy */
/* compiled from: PG */
public final class epy {

    /* renamed from: a */
    public final String f8816a;

    /* renamed from: b */
    private final String f8817b;

    static {
        new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();
    }

    public epy(String str, String str2) {
        this.f8817b = abj.m88a(str);
        this.f8816a = abj.m88a(str2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof epy) {
            epy epy = (epy) obj;
            return C0652xy.m16068a((Object) this.f8817b, (Object) epy.f8817b) && C0652xy.m16068a((Object) this.f8816a, (Object) epy.f8816a) && C0652xy.m16068a((Object) null, (Object) null);
        }
    }

    /* renamed from: a */
    public final Intent mo5143a() {
        String str = this.f8817b;
        if (str != null) {
            return new Intent(str).setPackage(this.f8816a);
        }
        return new Intent().setComponent((ComponentName) null);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f8817b, this.f8816a, null, 129, false});
    }

    public final String toString() {
        String str = this.f8817b;
        if (str != null) {
            return str;
        }
        throw null;
    }
}
