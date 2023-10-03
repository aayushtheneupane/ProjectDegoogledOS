package p000;

import android.database.Cursor;
import android.os.CancellationSignal;
import java.util.concurrent.CancellationException;

/* renamed from: gae */
/* compiled from: PG */
public final class gae implements gba {

    /* renamed from: a */
    private final /* synthetic */ Object[] f10780a;

    /* renamed from: b */
    private final /* synthetic */ String f10781b;

    /* renamed from: c */
    private final /* synthetic */ gag f10782c;

    public gae(gag gag, Object[] objArr, String str) {
        this.f10782c = gag;
        this.f10780a = objArr;
        this.f10781b = str;
    }

    /* renamed from: a */
    public final String mo6369a() {
        return this.f10781b;
    }

    /* renamed from: a */
    public final Cursor mo6368a(CancellationSignal cancellationSignal) {
        boolean z;
        gaf gaf = this.f10782c.f10786d;
        synchronized (((gaq) gaf).f10797a.f10811k) {
            int i = ((gaq) gaf).f10797a.f10816p;
            if (i != 0) {
                if (i > 0) {
                    z = true;
                } else {
                    z = false;
                }
                ife.m12877b(z, "Refcount went negative!", i);
                ((gaq) gaf).f10797a.f10816p++;
            } else {
                throw new CancellationException("database is closed");
            }
        }
        try {
            return this.f10782c.f10783a.rawQueryWithFactory(new gbo(this.f10780a), this.f10781b, (String[]) null, (String) null, cancellationSignal);
        } finally {
            this.f10782c.f10786d.mo6370a();
        }
    }

    /* synthetic */ gae() {
    }
}
