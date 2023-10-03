package p000;

import java.util.AbstractMap;

/* renamed from: gwm */
/* compiled from: PG */
final /* synthetic */ class gwm implements hpr {

    /* renamed from: a */
    private final String f12200a;

    /* renamed from: b */
    private final gws f12201b;

    public gwm(String str, gws gws) {
        this.f12200a = str;
        this.f12201b = gws;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        String str = this.f12200a;
        gws gws = this.f12201b;
        gwk gwk = (gwk) obj;
        if (gwk == gwq.f12205a) {
            return new AbstractMap.SimpleEntry(str, gwk);
        }
        if (!gwk.mo7133c() || ((gwh) gws).f12188b) {
            gwi b = gwk.mo7132b();
            hvs i = ((gwh) gws).f12187a.listIterator();
            while (i.hasNext()) {
                if (((gwi) i.next()) == b) {
                    gwi gwi = gwi.KEY_VALUE;
                    int ordinal = b.ordinal();
                    if (ordinal != 0) {
                        if (ordinal == 1) {
                            str = String.valueOf(str).concat(".txt");
                        } else if (ordinal == 2) {
                            str = String.valueOf(str).concat(".html");
                        } else if (ordinal == 3) {
                            str = String.valueOf(str).concat(".bin");
                        } else {
                            throw new UnsupportedOperationException();
                        }
                    }
                    return new AbstractMap.SimpleEntry(str, gwk);
                }
            }
            throw new IllegalArgumentException("Got unexpected DataType");
        }
        throw new IllegalArgumentException("DebugData may contain PII, but no PII was allowed.");
    }
}
