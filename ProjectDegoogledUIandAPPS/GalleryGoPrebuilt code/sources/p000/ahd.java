package p000;

import android.net.Uri;
import java.util.HashSet;
import java.util.Set;

/* renamed from: ahd */
/* compiled from: PG */
public final class ahd {

    /* renamed from: a */
    public final Set f485a = new HashSet();

    /* renamed from: a */
    public final void mo465a(Uri uri, boolean z) {
        this.f485a.add(new ahc(uri, z));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f485a.equals(((ahd) obj).f485a);
    }

    public final int hashCode() {
        return this.f485a.hashCode();
    }

    /* renamed from: a */
    public final int mo464a() {
        return this.f485a.size();
    }
}
