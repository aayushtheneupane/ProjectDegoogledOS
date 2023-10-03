package p000;

import androidx.work.impl.WorkDatabase;
import java.util.UUID;

/* renamed from: alw */
/* compiled from: PG */
public final class alw extends aly {

    /* renamed from: a */
    private final /* synthetic */ aip f743a;

    /* renamed from: b */
    private final /* synthetic */ UUID f744b;

    public alw(aip aip, UUID uuid) {
        this.f743a = aip;
        this.f744b = uuid;
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public final void mo622a() {
        WorkDatabase workDatabase = this.f743a.f554c;
        workDatabase.mo2845e();
        try {
            m754a(this.f743a, this.f744b.toString());
            workDatabase.mo2847g();
            workDatabase.mo2846f();
            aip aip = this.f743a;
            agz agz = aip.f553b;
            aib.m533a(aip.f554c, aip.f556e);
        } catch (Throwable th) {
            workDatabase.mo2846f();
            throw th;
        }
    }
}
