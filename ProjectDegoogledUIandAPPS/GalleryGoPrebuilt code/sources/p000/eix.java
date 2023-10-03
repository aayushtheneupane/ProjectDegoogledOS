package p000;

import android.os.SystemClock;
import java.util.TimeZone;

/* renamed from: eix */
/* compiled from: PG */
public final class eix {

    /* renamed from: a */
    public final eja f8384a;

    /* renamed from: b */
    public final eiz f8385b;

    /* renamed from: c */
    public boolean f8386c;

    /* renamed from: d */
    public String f8387d;

    /* renamed from: e */
    public String f8388e;

    /* renamed from: f */
    public int f8389f;

    /* renamed from: g */
    public int f8390g;

    /* renamed from: h */
    public final iit f8391h;

    /* renamed from: i */
    private boolean f8392i;

    public /* synthetic */ eix(eja eja, eiz eiz) {
        this(eja, (ihw) null, eiz);
    }

    private eix(eja eja, ihw ihw, eiz eiz) {
        this.f8386c = true;
        this.f8391h = (iit) ins.f14576h.mo8793g();
        this.f8392i = false;
        this.f8384a = eja;
        this.f8389f = eja.f8403f;
        this.f8388e = eja.f8402e;
        this.f8387d = eja.f8404g;
        this.f8390g = eja.f8409m;
        iit iit = this.f8391h;
        long currentTimeMillis = System.currentTimeMillis();
        if (iit.f14319c) {
            iit.mo8751b();
            iit.f14319c = false;
        }
        ins ins = (ins) iit.f14318b;
        ins.f14578a = 1 | ins.f14578a;
        ins.f14579b = currentTimeMillis;
        iit iit2 = this.f8391h;
        long offset = (long) (TimeZone.getDefault().getOffset(((ins) iit2.f14318b).f14579b) / 1000);
        if (iit2.f14319c) {
            iit2.mo8751b();
            iit2.f14319c = false;
        }
        ins ins2 = (ins) iit2.f14318b;
        ins2.f14578a |= 65536;
        ins2.f14583f = offset;
        if (exp.m8336a(eja.f8400c)) {
            iit iit3 = this.f8391h;
            boolean a = exp.m8336a(eja.f8400c);
            if (iit3.f14319c) {
                iit3.mo8751b();
                iit3.f14319c = false;
            }
            ins ins3 = (ins) iit3.f14318b;
            ins3.f14578a |= 8388608;
            ins3.f14584g = a;
        }
        if (SystemClock.elapsedRealtime() != 0) {
            iit iit4 = this.f8391h;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (iit4.f14319c) {
                iit4.mo8751b();
                iit4.f14319c = false;
            }
            ins ins4 = (ins) iit4.f14318b;
            ins4.f14578a |= 2;
            ins4.f14580c = elapsedRealtime;
        }
        if (ihw != null) {
            iit iit5 = this.f8391h;
            if (iit5.f14319c) {
                iit5.mo8751b();
                iit5.f14319c = false;
            }
            ins ins5 = (ins) iit5.f14318b;
            ihw.getClass();
            ins5.f14578a |= 1024;
            ins5.f14582e = ihw;
        }
        this.f8385b = eiz;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ eix(eja eja, byte[] bArr) {
        this(eja, bArr != null ? ihw.m13162a(bArr) : null, (eiz) null);
    }

    @Deprecated
    /* renamed from: a */
    public final ekx mo4864a() {
        if (!this.f8392i) {
            this.f8392i = true;
            return this.f8384a.f8406i.mo4868a(this);
        }
        throw new IllegalStateException("do not reuse LogEventBuilder");
    }

    /* renamed from: a */
    public final void mo4865a(int i) {
        iit iit = this.f8391h;
        if (iit.f14319c) {
            iit.mo8751b();
            iit.f14319c = false;
        }
        ins ins = (ins) iit.f14318b;
        ins ins2 = ins.f14576h;
        ins.f14578a |= 16;
        ins.f14581d = i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ClearcutLogger.LogEventBuilder[");
        sb.append("uploadAccount: ");
        sb.append(this.f8387d);
        sb.append(", logSourceName: ");
        sb.append(this.f8388e);
        sb.append(", logSource#: ");
        sb.append(this.f8389f);
        sb.append(", qosTier: ");
        sb.append(this.f8390g == 0 ? "null" : Integer.toString(0));
        sb.append(", loggingId: null, MessageProducer: ");
        sb.append(this.f8385b);
        sb.append(", veMessageProducer: ");
        sb.append((Object) null);
        sb.append(", testCodes: ");
        ekn ekn = eja.f8395a;
        sb.append("null, dimensions: null, mendelPackages: null, experimentIds: null, experimentTokens: null, experimentTokensBytes: null, addPhenotype: ");
        sb.append(this.f8386c);
        sb.append("]");
        return sb.toString();
    }
}
