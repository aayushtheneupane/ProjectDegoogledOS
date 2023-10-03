package p000;

import android.content.ContentResolver;
import android.net.Uri;

/* renamed from: diq */
/* compiled from: PG */
public final class diq implements dip {

    /* renamed from: a */
    private final dhl f6628a;

    /* renamed from: b */
    private final ContentResolver f6629b;

    /* renamed from: c */
    private final cwq f6630c;

    public diq(dhl dhl, ContentResolver contentResolver, cwq cwq) {
        this.f6628a = dhl;
        this.f6629b = contentResolver;
        this.f6630c = cwq;
    }

    /* renamed from: a */
    public final boolean mo4156a(long j) {
        boolean z;
        new Object[1][0] = Long.valueOf(j);
        hvr a = this.f6628a.mo4133a().iterator();
        loop0:
        while (true) {
            z = true;
            while (true) {
                if (!a.hasNext()) {
                    break loop0;
                }
                String str = (String) a.next();
                if (z) {
                    try {
                        if (this.f6629b.delete(new Uri.Builder().scheme("content").authority(str).appendPath("delete").appendPath(String.valueOf(j)).build(), (String) null, (String[]) null) != 0) {
                        }
                    } catch (Exception e) {
                        this.f6630c.mo3869a(39);
                        Object[] objArr = {str, Long.valueOf(j)};
                    }
                }
                z = false;
            }
        }
        this.f6630c.mo3869a(!z ? 29 : 28);
        return z;
    }
}
