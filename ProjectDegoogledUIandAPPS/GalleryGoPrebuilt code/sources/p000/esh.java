package p000;

import android.app.ApplicationErrorReport;
import android.text.TextUtils;

/* renamed from: esh */
/* compiled from: PG */
public final class esh extends esg {
    public esh() {
        this.f8918k = new ApplicationErrorReport();
        this.f8918k.crashInfo = new ApplicationErrorReport.CrashInfo();
        this.f8918k.crashInfo.throwLineNumber = -1;
    }

    /* renamed from: a */
    public final esi mo5198a() {
        abj.m85a((Object) this.f8918k.crashInfo.exceptionClassName);
        abj.m85a((Object) this.f8918k.crashInfo.throwClassName);
        abj.m85a((Object) this.f8918k.crashInfo.throwMethodName);
        abj.m85a((Object) this.f8918k.crashInfo.stackTrace);
        if (TextUtils.isEmpty(this.f8918k.crashInfo.throwFileName)) {
            this.f8918k.crashInfo.throwFileName = "unknown";
        }
        esi a = super.mo5198a();
        a.f8927d.crashInfo = this.f8918k.crashInfo;
        a.f8930g = null;
        return a;
    }
}
