package p000;

import android.os.Build;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/* renamed from: fph */
/* compiled from: PG */
public final class fph implements foy {

    /* renamed from: a */
    private final List f10199a;

    /* renamed from: c */
    private final iqk f10200c;

    public fph(Collection collection, iqk iqk) {
        this.f10199a = new ArrayList(collection);
        this.f10200c = iqk;
    }

    /* renamed from: a */
    public final void mo5891a(isc isc) {
        List list = this.f10199a;
        int size = list.size();
        RuntimeException runtimeException = null;
        for (int i = 0; i < size; i++) {
            try {
                ((foy) ((iqk) list.get(i)).mo2097a()).mo5891a(isc);
            } catch (RuntimeException e) {
                flw.m9198b("CompositeTransmitter", "One transmitter failed to send message", e, new Object[0]);
                if (runtimeException != null) {
                    int i2 = Build.VERSION.SDK_INT;
                    ifn.m12935a((Throwable) runtimeException, (Throwable) e);
                } else {
                    runtimeException = e;
                }
            }
        }
        iqk iqk = this.f10200c;
        if (iqk != null) {
            for (foy a : (Set) iqk.mo2097a()) {
                try {
                    a.mo5891a(isc);
                } catch (RuntimeException e2) {
                    flw.m9198b("CompositeTransmitter", "One transmitter failed to send message", e2, new Object[0]);
                    if (runtimeException != null) {
                        int i3 = Build.VERSION.SDK_INT;
                        ifn.m12935a((Throwable) runtimeException, (Throwable) e2);
                    } else {
                        runtimeException = e2;
                    }
                }
            }
        }
        if (runtimeException != null) {
            throw runtimeException;
        }
    }
}
